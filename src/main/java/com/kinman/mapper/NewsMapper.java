package com.kinman.mapper;

import com.kinman.pojo.News;
import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NewsMapper {
    @Select({"select * from news"})
    List<News> getNewsList();

    @Delete({"delete from news where id=#{id}"})
    int deleteNewsById(long id);

    @Select({"select * from news where title like concat('%',#{name},'%')"})
    List<News> getNewsListByName(String name);

    List<News> findByPage(String title, LocalDate begin, LocalDate end);

    @Delete({"delete from news where category_id=#{id}"})
    int deleteNewsByCategoryId(long id);
}