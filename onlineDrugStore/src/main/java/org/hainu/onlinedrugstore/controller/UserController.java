package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.dto.DrugDoctorDto;
import org.hainu.onlinedrugstore.dto.RegisterDto;
import org.hainu.onlinedrugstore.entity.User;
import org.hainu.onlinedrugstore.service.LoginService;
import org.hainu.onlinedrugstore.service.RegisterService;
import org.hainu.onlinedrugstore.service.UserService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    @Autowired
    RegisterService registerService;


    @PostMapping("/login")
    public String login(@RequestBody User user) throws Exception {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String firstLoginTime = simpleFormat.format(new Date());
        String token = loginService.login(user, firstLoginTime);
        if (token.equals("账号或密码错误"))
            return fail("账号或密码错误");
        return sucess(token);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto) {
        if (!registerService.register(registerDto))
            return fail("该用户名或该邮箱已被注册");
        return sucess();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.removeById(id);
        return sucess();
    }

    /**
     * 数据更新
     */
    @PutMapping
    public String update(@RequestBody User user) {
        user.setVersion(userService.getById(user.getId()).getVersion());
        boolean result = userService.updateById(user);
        if (result) return sucess();
        return fail();
    }

    /**
     * 根据id查询单条数据
     */
    @GetMapping("/{id}")
    public String selectById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return fail();
        }
        return sucess(user);
    }

    /**
     * 列表查询
     */
    @GetMapping("/list")
    public String selectList(HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(userService.list(queryWrapper));
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public String selectPage(HttpServletRequest request, @RequestBody Page<User> page) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, page, request);
        return sucess(userService.page(page, queryWrapper));
    }
}
