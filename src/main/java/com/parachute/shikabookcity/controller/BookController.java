package com.parachute.shikabookcity.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.parachute.shikabookcity.constant.ResultConstant;
import com.parachute.shikabookcity.entity.Book;
import com.parachute.shikabookcity.service.BookService;
import com.parachute.shikabookcity.util.Page;
import com.parachute.shikabookcity.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 本控制器
 * (Book)表控制层
 *
 * @author machi
 * @date 2022/04/25
 * @since 2022-04-18 17:30:51
 */
@RestController
@RequestMapping("book")
@Slf4j
@CrossOrigin
public class BookController{
    /**
     * 书籍
     * BookService
     */
    @Autowired
    private BookService bookService;


    /**
     * 修改书籍信息
     *
     * @param book 实体类book
     * @return {@link Result}
     * @throws ParseException 解析异常
     */
    @RequestMapping("submit")
    public Result submit(@RequestBody Book book) throws ParseException {
        //校验表单
        Result result = bookService.validateForm(book);
        if (result.isFlag()){
            try {
                bookService.update(book);
                return Result.of(true,ResultConstant.UPDATE_SUCCEED);
            }catch (Exception e){
                log.error(e.getMessage(),e);
                return Result.of(false, ResultConstant.SERVER_EXCEPTION);
            }
        }
        return result;
    }

    /**
     * ▽
     * 删除
     *
     * @param userId 用户id
     * @param bookId 书id
     * @return {@link Result}
     */
    @RequestMapping ("del")
    public Result del(Integer userId,Integer bookId) {

        try {
           bookService.delById(userId,bookId);
            return Result.of(true,ResultConstant.DELETE_SUCCEED);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }

    }


    /**
     *
     * 获取上架书籍
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping ("getIsAdded")
    public Result getIsAdded(Page page) {
        //传入当前页和每页大小
        PageMethod.startPage(page.getCurrent(),page.getSize());
        try {
            List<Book> all = bookService.getIsAdded(page.getUserName());
            PageInfo<Book> pageInfo = new PageInfo<>(all);
            return Result.of(true,"",pageInfo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }

    }

    /**
     *
     * 获取下架书籍
     *
     * @param page 页面
     * @return {@link Result}
     */
    @RequestMapping ("getNoAdded")
    public Result getNoAdded(Page page) {
        PageMethod.startPage(page.getCurrent(),page.getSize());
        try {
            List<Book> all = bookService.getNoAdded(page.getUserName());
            PageInfo<Book> pageInfo = new PageInfo<>(all);
            return Result.of(true,"",pageInfo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }

    }


    /**
     * 添加
     *
     * @param data 前端数据
     * @return {@link Result}
     * @throws ParseException 解析异常
     */
    @RequestMapping ("/add")
    public Result add(@RequestBody Map<String,Object> data) throws ParseException {
       try {

           return bookService.insert(data);
       }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }

    }




    /**
     *
     * 上架
     *
     * @param id id
     * @return {@link Result}
     */
    @RequestMapping("isAdded")
    public Result isAdded(int id) {

        LambdaUpdateWrapper<Book> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Book::getId,id)
                .set(Book::getIsAdded,1);
        try {
            boolean update = bookService.update(updateWrapper);
            if (update){
                return Result.of(true,ResultConstant.PUTAWAY_SUCCEED);
            }else {
                return Result.of(false,ResultConstant.SERVER_EXCEPTION);
            }
        }catch (Exception e){
            e.printStackTrace();
             return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }
    }

    /**
     *
     * 下架
     *
     * @param id id
     * @return {@link Result}
     */
    @RequestMapping("noAdded")
    public Result noAdded(int id) {

        LambdaUpdateWrapper<Book> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Book::getId,id)
                .set(Book::getIsAdded,0);
        try {
            boolean update = bookService.update(updateWrapper);
            if (update){
                return Result.of(true,ResultConstant.UNSHELVE_SUCCEED);
            }else {
                return Result.of(false,ResultConstant.SERVER_EXCEPTION);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.of(false,ResultConstant.SERVER_EXCEPTION);
        }
    }






}

