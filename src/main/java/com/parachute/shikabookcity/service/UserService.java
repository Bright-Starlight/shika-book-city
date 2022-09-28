package com.parachute.shikabookcity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.shikabookcity.entity.User;
import com.parachute.shikabookcity.exception.NetworkAnomalyException;
import com.parachute.shikabookcity.util.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * (User)表服务接口
 *
 * @author machi
 * @since 2022-04-15 23:57:08
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param user 用户
     */
    void login(User user) throws NetworkAnomalyException, IOException;

    /**
     * 注册
     *
     * @param user 用户
     * @return {@link Result}
     */
    Result register(User user) throws NetworkAnomalyException, IOException;



    /**
     * 验证表单
     *
     * @param user 用户
     * @return {@link Result}
     */
     Result validateForm(User user);

    /**
     * 更新
     *
     * @param user 用户
     */
     void update(User user);

    /**
     * 上传
     *
     * @param imgFile img文件
     * @return {@link String}
     */
    Result upload(MultipartFile imgFile);
}

