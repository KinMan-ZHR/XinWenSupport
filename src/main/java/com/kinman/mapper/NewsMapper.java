package com.kinman.mapper;

import com.kinman.pojo.News;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface NewsMapper {
    //ok
    @Select({"select * from news"})
    List<News> getNewsList();
    //ok
    @Delete({"delete from news where id=#{id}"})
    int deleteNewsById(long id);
    //todo
    @Select({"select * from news where title like concat('%',#{name},'%')"})
    List<News> getNewsListByName(String name);
    //todo
    List<News> findByPage(String title, LocalDate begin, LocalDate end);
    //todo
    @Delete({"delete from news where category_id=#{id}"})
    int deleteNewsByCategoryId(long id);
    //ok
    @Insert({"insert into news(title,url,update_time) values(#{title},#{url},#{updateTime})"})
    void addNews(News news);
    //todo
    @Update({"update news set title=#{title},url=#{url},update_time=#{updateTime} where id=#{id}"})
    void updateNews(News news);
}

