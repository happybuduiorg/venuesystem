package com.happybudui.venuesystem.service;

import com.github.pagehelper.PageHelper;
import com.happybudui.venuesystem.mapper.UserExternMapper;
import com.happybudui.venuesystem.bean.PageBean;
import com.happybudui.venuesystem.entity.NewsEntity;
import com.happybudui.venuesystem.mapper.NewsMapper;
import com.happybudui.venuesystem.wrapper.ResponseResult;
import com.happybudui.venuesystem.wrapper.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Service
public class NewsService {

    private final UserExternMapper userExternMapper;
    private final NewsMapper newsMapper;
    private int newsMaxId;

    @Autowired
    public NewsService(UserExternMapper userExternMapper,NewsMapper newsMapper){
        this.userExternMapper=userExternMapper;
        this.newsMapper=newsMapper;
        newsMaxId=this.getNewsMaxId();
    }

    @Transactional
    public ResponseResult<Integer> publicNews(String content, HttpSession session){
        String userId=(String)session.getAttribute("userId");
        String userName=(String)session.getAttribute("userName");
        NewsEntity newsEntity=new NewsEntity(content,userId,userName,0);
        int res = newsMapper.insertNews(newsEntity);
        this.newsMaxId=newsMapper.getNewsMaxId();
        if(res==1){
            return ResultGenerator.success("Success!");
        }else{
            return ResultGenerator.error("Inner Error!");
        }
    }

    @Transactional
    public ResponseResult<PageBean<NewsEntity>> getNewsItemByPage(int currentPage, int pageSize,HttpSession session) {
        PageHelper.startPage(currentPage, pageSize);

        List<NewsEntity> allItems = newsMapper.getAllNews();        //全部商品
        int countNums = newsMapper.getNewsNumber();            //总记录数
        PageBean<NewsEntity> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        String userId=(String)session.getAttribute("userId");
        if(userId!=null) {
            setUserReadCount(userId,getNewsMaxId());
        }

        return ResultGenerator.success("success",pageData);
    }

    @Transactional
    public ResponseResult<Integer> deleteNewsById(int newsId){
        this.newsMaxId=newsMapper.getNewsMaxId();
        int res = newsMapper.deleteNewByNewsId(newsId);
        if(res==1){
            return ResultGenerator.success("Success!");
        }else{
            return ResultGenerator.error("Inner Error!");
        }
    }

    public boolean hasUnReadNews(HttpSession session){
        int maxNewsId=this.getNewsMaxId();
        int readCount=this.getUserReadCount(session);

        return (maxNewsId+1)>readCount;
    }

    //Internal call
    private int getNewsMaxId(){
        return this.newsMaxId;
    }

    @Transactional
    protected int getUserReadCount(HttpSession session){
        String userId=(String)session.getAttribute("userId");
        return userExternMapper.getUserExternInfoById(userId).getUserReadCount();
    }

    @Transactional
    protected int setUserReadCount(String userId,int count){
        return userExternMapper.updateUserReadCount(userId,count);
    }

}
