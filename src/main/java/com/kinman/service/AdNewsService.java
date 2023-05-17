package com.kinman.service;

import com.kinman.pojo.News;
import com.kinman.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface AdNewsService {
    List<News> getNewsList();

    int deleteNewsById(long id);

    List<News> getNewsListByName(String name);

    PageBean findByPage(Integer page, Integer pageSize, String title, LocalDate begin, LocalDate end);
}
