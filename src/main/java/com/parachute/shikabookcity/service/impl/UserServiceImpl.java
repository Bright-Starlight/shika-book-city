package com.parachute.shikabookcity.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.shikabookcity.config.CustomObjectMapper;
import com.parachute.shikabookcity.constant.ResultConstant;
import com.parachute.shikabookcity.constant.SysConstant;
import com.parachute.shikabookcity.dao.UserDao;
import com.parachute.shikabookcity.entity.User;
import com.parachute.shikabookcity.exception.NetworkAnomalyException;
import com.parachute.shikabookcity.service.UserService;
import com.parachute.shikabookcity.util.ImgtuUtils;
import com.parachute.shikabookcity.util.NetworkUtil;
import com.parachute.shikabookcity.util.Result;
import lombok.SneakyThrows;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * 用户服务impl
 * (User)
 *
 * @author machi
 * @date 2022/04/25
 * @since 2022-04-14 22:39:21
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CustomObjectMapper objectMapper;

    /**
     * 注册
     *
     * @param user 用户
     * @return {@link Result}
     */

    @Override
    public Result register(User user) throws NetworkAnomalyException, IOException {
        String userName = user.getUserName();
        String password = user.getPassword();
        if (userName.isEmpty()){
            return Result.of(false, ResultConstant.USERNAME_IS_EMPTY);
        }
        if (password.isEmpty()){
            return Result.of(false, ResultConstant.PASSWORD_IS_EMPTY);
        }
        String pass = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        User user1 = super.baseMapper.selectOne(wrapper);
        //验证是否注册过
        if (user1 != null) {
            return Result.of(false, ResultConstant.USERNAME_IS_REGISTERED);
        }
        Map<String, String> map = NetworkUtil.getIPBody(restTemplate, objectMapper);

        String location =  map.get("pro") + map.get("city") + map.get("region");
        int i = new Random().nextInt(100000);
        String nickName = "用户_" + i;
        //注入信息
        User user2 = new User();
        //账号
        user2.setUserName(userName);
        //用户名
        user2.setNickName(nickName);
        //密码
        user2.setPassword(pass);
        //是否冻结 ，0为不冻结
        user2.setStatus("0");
        //登录时间
        user2.setLoginDate(new Date());
        //登录地址ip
        user2.setLoginIp(map.get("ip"));
        //登录地址
        user2.setIpaddr(location);
        //创建时间
        user2.setCreateTime(new Date());
        //修改时间
        user2.setUpdateTime(new Date());
        user2.setUpdateName("system");
        user2.setProfile("https://s1.ax1x.com/2022/04/17/LNdyM4.png");
        //存入数据库
        super.baseMapper.insert(user2);
        return Result.of(true, ResultConstant.REGISTER_SUCCEED);
    }

    /**
     * 登录
     *
     * @param user 用户
     */

    @Override
    public void login(User user) throws NetworkAnomalyException, IOException {

        Map<String, String> map = NetworkUtil.getIPBody(restTemplate, objectMapper);
        String location =  map.get("pro") + map.get("city") + map.get("region");

        LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate();
        //登录时间
        updateWrapper.set(User::getLoginDate, new Date())
                //登录地址
                .set(User::getIpaddr, location)
                .set(User::getLoginIp, map.get("ip")).eq(User::getUserName, user.getUserName());
        super.baseMapper.update(null, updateWrapper);

    }

    /**
     * 验证表单
     *
     * @param user 用户
     * @return {@link Result}
     */
    @Override
    public Result validateForm(User user) {
        if (user != null) {
            String userName = user.getUserName();
            String nickName = user.getNickName();
            String phone = user.getPhone();
            user.setUpdateTime(new Date());
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUserName, userName);
            User one = super.baseMapper.selectOne(queryWrapper);
            queryWrapper.clear();
            //是否昵称重复
            if (!one.getNickName().equals(nickName)) {
                queryWrapper.eq(User::getNickName, nickName);
                List<User> nicknames = user.selectList(queryWrapper);
                if (!nicknames.isEmpty()) {
                    return Result.of(false, ResultConstant.NICKNAMES_IS_EXIST);
                }
            }
            queryWrapper.clear();
            //是否电话号码重复绑定
            if (!one.getPhone().equals(phone)) {
                queryWrapper.eq(User::getPhone, phone);
                List<User> phones = user.selectList(queryWrapper);
                if (!phones.isEmpty()) {
                    return Result.of(false, ResultConstant.TELEPHONE_NUMBER_IS_BOUND);
                }
            }
        }
        return Result.of(true,ResultConstant.UPDATE_SUCCEED);
    }

    /**
     * 更新
     *
     * @param user 用户
     */
    @Override
    public void update(User user) {
        LambdaUpdateWrapper<User> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(User::getUserName, user.getUserName())
                .set(User::getNickName, user.getNickName())
                .set(User::getSex, user.getSex())
                .set(User::getPhone, user.getPhone())
                .set(User::getProfile, user.getProfile())
                .set(User::getUpdateTime, user.getUpdateTime())
                .set(User::getUpdateName, SysConstant.USER_UPDATE);
        super.baseMapper.update(user, wrapper);
    }

    /**
     * 上传
     *
     * @param imgFile img文件
     * @return {@link String}
     */
    @Override
    public Result upload(MultipartFile imgFile) {
        //随机文件名称防止覆盖
        String fileName = UUID.randomUUID().toString();
        Result result = null;
            try {
                //上传文件到路过图床，返回图片Url到前端展示
                result = ImgtuUtils.upload(imgFile.getBytes(), fileName, ContentType.IMAGE_JPEG);
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return result;
            }
        return result;
    }

}