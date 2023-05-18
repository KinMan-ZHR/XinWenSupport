package com.kinman.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kinman.mapper.NewsMapper;
import com.kinman.pojo.News;
import com.kinman.pojo.PageBean;
import com.kinman.service.AdNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class AdNewsServiceImpl implements AdNewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public List<News> getNewsList() {
      return   newsMapper.getNewsList();
    }

    @Override
    public int deleteNewsById(long id) {
        return newsMapper.deleteNewsById(id);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public List<News> getNewsListByName(String name) {
        return newsMapper.getNewsListByName(name);
    }

    /**
     * @param page    当前页码
     * @param pageSize 每页显示条数
     * @param title  新闻标题
     * @param begin 开始时间
     * @param end  结束时间
     * @return 分页对象
     */
    @Override
    public PageBean findByPage(Integer page, Integer pageSize, String title, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<News> newsList = newsMapper.findByPage(title,begin,end);
        //装载
        Page<News> newsPage= (Page<News>) newsList;
        return new PageBean(newsPage.getTotal(), newsPage.getResult());
    }

    /**
     * @param news 新闻对象
     */
    @Override
    public void add(News news) {
         news.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
         newsMapper.addNews(news);

    }

    /**
     * @param news
     */
    @Override
    public void updateNews(News news) {
        news.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        newsMapper.updateNews(news);
    }

}
