package com.kinman.controller;
import com.kinman.pojo.News;
import com.kinman.pojo.PageBean;
import com.kinman.pojo.Result;
import com.kinman.service.AdNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/adNews")
public class AdNewsController {
    @Autowired
    private AdNewsService adNewsService;
    /**
     * 查询全部新闻数据
     * @return Result
     */
    @GetMapping
    public Result adNews(){
        log.info("查询全部新闻数据");
        List<News>newsList= adNewsService.getNewsList();
        return Result.success(newsList);
    }
    /**
     * 根据id删除新闻
     * @param id 新闻id
     * @return Result
     */
    @DeleteMapping("/{id}")
public Result deleteNewsById(@PathVariable long id){
        log.info("根据id删除新闻");
        int result=adNewsService.deleteNewsById(id);
        if(result>0){
            return Result.success();
        }
        return Result.error("删除失败");
    }
    /**
     * 根据name模糊查询新闻
     * @param name 新闻标题
     * @return Result
     */
    @GetMapping("/search")
    public Result getNewsListByName(String name){
        log.info("根据name模糊查询新闻");
        List<News>newsList=adNewsService.getNewsListByName(name);
        return Result.success(newsList);
    }
    /**
     * 动态sql分页查询新闻
     * @param page 当前页
     * @param pageSize 每页显示条数
     * @param title 新闻标题
     * @param begin 开始时间
     * @param end 结束时间
     * @return Result
     */
    @GetMapping("/pageIf")
    public Result pageIf(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         String title,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                         @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end) {
        // 1.接收前端传递的参数,前端传递的参数是page和pageSize，这里接收的参数名要和前端传递的参数名一致
        // 2.调用service查询分页数据
        // 3.将分页数据返回给前端
        log.info("page = {},pageSize = {},{},{},{}", page,pageSize,title,begin,end);
        PageBean pageBean = adNewsService.findByPage(page, pageSize,title,begin,end);
        return Result.success(pageBean);

    }


}
