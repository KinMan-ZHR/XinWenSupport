package com.kinman.controller;

import com.kinman.pojo.PageBean;
import com.kinman.pojo.Result;
import com.kinman.pojo.User;
import com.kinman.service.AdUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/adUser")
public class AdUserController {
    @Autowired
    private AdUserService adUserService;
    /**
     * 根据id删除用户
     * @param id 用户id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result deleteUserById(@PathVariable long id){
        log.info("根据id删除用户");
        int result=adUserService.deleteUserById(id);
        if(result>0){
            return Result.success();
        }
        return Result.error("删除失败");
    }
    /**
     * 查询用户列表,按条件查询分页
     * @param page 当前页
     *             &#064;RequestParam(defaultValue  = "1") Integer page,
     *             &#064;RequestParam(defaultValue  = "5") Integer pageSize,
     *             String username,
     *             Boolean identity,
     * @return Result
     */
    @GetMapping()
    public Result pageIf(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "5") Integer pageSize,
                         String username,
                         Boolean identity) {
        // 1.接收前端传递的参数,前端传递的参数是page和pageSize，这里接收的参数名要和前端传递的参数名一致
        // 2.调用service层方法，查询用户列表
        log.info("查询用户列表,按条件查询分页,page={},pageSize={},username={},identity={}",page,pageSize,username,identity);
        PageBean pageBean=adUserService.getUserList(page, pageSize, username, identity);
        // 3.将查询到的用户列表封装到PageBean对象中
        // 4.将PageBean对象封装到Result对象中
        return Result.success(pageBean);
    }
    /**
     * 添加用户
     * @param user 用户对象
     * @return Result
     */
    @PostMapping()
    public Result addUser(@RequestBody User user){
        log.info("添加用户");
        adUserService.addUser(user);
        return Result.success();
    }
    /**
     * 根据id查询用户
     * @param id 用户id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable long id){
        log.info("根据id查询用户");
        User user=adUserService.getUserById(id);
        return Result.success(user);
    }
    /**
     * 修改用户
     * @param user 用户对象
     * @return Result
     */
    @PutMapping()
    public Result updateUser(@RequestBody User user){
        log.info("修改用户");
        adUserService.updateUser(user);
        return Result.success();
    }
    /**
     * 根据id修改用户状态
     * @param id 用户id
     * @param status 用户状态
     * @return Result
     */
    @PutMapping("/{id}/{status}")
    public Result updateUserStatus(@PathVariable long id,@PathVariable Boolean status){
        log.info("根据id修改用户状态");
        adUserService.updateUserStatus(id,status);
        return Result.success();
    }

}
