package com.parachute.shikabookcity.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.parachute.shikabookcity.constant.ResultConstant;
import com.parachute.shikabookcity.entity.Activity;
import com.parachute.shikabookcity.entity.Book;
import com.parachute.shikabookcity.exception.CommodityCodeException;
import com.parachute.shikabookcity.service.ActivityService;
import com.parachute.shikabookcity.service.BookService;
import com.parachute.shikabookcity.util.Page;
import com.parachute.shikabookcity.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 活动
 * (Activity)表控制层
 *
 * @author machi
 * @date 2022/04/25
 * @since 2022-04-23 15:58:30
 */
@Slf4j
@RestController
@RequestMapping("activity")
public class ActivityController  {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private BookService bookService;

    /**
     *
     * 获取活动进行中的活动数据
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping("getNoDie")
    public Result getNoDie(Page page) {
        //传入当前页和每页大小
        PageMethod.startPage(page.getCurrent(), page.getSize());
        try {
            List<Activity> activities = activityService.getNoDie(page.getUserName());
            PageInfo<Activity> pageInfo = new PageInfo<>(activities);
            return Result.of(true, "", pageInfo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }


    }

    /**
     *
     * 获取用户所有书籍数据
     *
     * @param userName 用户名
     * @return {@link Result}
     */
    @RequestMapping("getAllBook")
    public Result getAllBook(String userName) {
        try {
            List<Book> isAdded = bookService.getIsAdded(userName);
            return Result.of(true, "", isAdded);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }

    }

    /**
     *
     * 获取活动已结束的数据
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping("getIsDie")
    public Result getIsDie(Page page) {
        //传入当前页和每页大小
        PageMethod.startPage(page.getCurrent(), page.getSize());
        try {
            List<Activity> activities = activityService.getIsDie(page.getUserName());
            PageInfo<Activity> pageInfo = new PageInfo<>(activities);
            return Result.of(true, "", pageInfo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }

    }

    /**
     *
     * 获得未发布活动数据
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping("getPublish")
    public Result getPublish(Page page) {
        //传入当前页和每页大小
        PageMethod.startPage(page.getCurrent(), page.getSize());
        try {
            List<Activity> activities = activityService.getPublish(page.getUserName());
            PageInfo<Activity> pageInfo = new PageInfo<>(activities);
            return Result.of(true, "", pageInfo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }
    }

    /**
     *
     * 获取还未进行的活动
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping("getPending")
    public Result getPending(Page page) {
        //传入当前页和每页大小
        PageMethod.startPage(page.getCurrent(), page.getSize());
        try {
            List<Activity> activities = activityService.getPending(page.getUserName());
            PageInfo<Activity> pageInfo = new PageInfo<>(activities);
            return Result.of(true, "", pageInfo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }
    }

    /**
     *
     * 强制停止活动停止
     *
     * @param activityId 活动id
     * @return {@link Result}
     */
    @RequestMapping("stop")
    public Result stop(Integer activityId) {
        //传入当前页和每页大小
        LambdaUpdateWrapper<Activity> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Activity::getId, activityId).set(Activity::getIsDie, 1);
        try {
            activityService.update(updateWrapper);
            return Result.of(true, ResultConstant.FORCE_STOP);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Result.of(false, ResultConstant.SERVER_EXCEPTION);
        }
    }

    /**
     *
     * 删除活动
     *
     * @param userId     用户id
     * @param activityId 活动id
     * @return {@link Result}
     */
    @RequestMapping("delActivity")
    public Result delActivity(Integer userId, Integer activityId) {

       try {
           //删除activity
           activityService.delActivity(userId,activityId);
           return Result.of(true,ResultConstant.DELETE_SUCCEED);
       }catch (Exception e){
           log.error(e.getMessage(),e);
           return Result.of(false,ResultConstant.SERVER_EXCEPTION);
       }


    }


    /**
     *
     * 发布活动
     *
     * @param data 数据
     * @return {@link Result}
     */
    @RequestMapping("publish")
    public Result publish(@RequestBody Map<String,Object> data) {
        Integer activityId = (Integer) data.get("activityId");
        String startTime = (String) data.get("startTime");
        String activityDeadline = (String) data.get("activityDeadline");
        try {
            //建立联系
            activityService.publish(activityId,startTime,activityDeadline);
            return Result.of(true,ResultConstant.PUBLISH_SUCCEED);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }

    }

    /**
     *
     * 添加活动
     *
     * @param data 数据
     * @return {@link Result}
     * @throws ParseException 解析异常
     */
    @RequestMapping("add")
    public Result add(@RequestBody Map<String,Object> data) throws ParseException {

        try {
            //新增活动
            return activityService.insert(data);
        }catch (CommodityCodeException e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.COMMODITY_CODE_IS_DUPLICATION);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }

    }


    /**
     *
     * 修改活动信息
     *
     * @param activity 活动
     * @return {@link Result}
     *
     */
    @RequestMapping("updateConfig")
    public Result updateConfig(@RequestBody Activity activity) {
        //校验表单
        Result result = activityService.validateForm(activity);
        if (result.isFlag()){
            try {
                activityService.update(activity);
                return Result.of(true,ResultConstant.UPDATE_SUCCEED);
            }catch (Exception e){
                log.error(e.getMessage(),e);
                return Result.of(false,ResultConstant.SERVER_EXCEPTION);
            }

        }
      return result;

    }





}

