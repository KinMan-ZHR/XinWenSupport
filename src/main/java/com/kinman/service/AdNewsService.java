package com.kinman.service;

import com.kinman.pojo.News;
import com.kinman.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface AdNewsService {
    List<News> getNewsList();

    int deleteNewsById(long id);

    List<News> getNewsListByName(String name);

    PageBean findByPage(Integer page, Integer pageSize, String title,String image,Boolean category, LocalDate begin, LocalDate end);


    void add(News news);

    void updateNews(News news);

    void batchAddNews(List<News> newsList);
}
