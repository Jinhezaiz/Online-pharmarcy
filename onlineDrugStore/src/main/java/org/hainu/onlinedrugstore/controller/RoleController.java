package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Update;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.entity.DrugDoctor;
import org.hainu.onlinedrugstore.entity.Role;
import org.hainu.onlinedrugstore.entity.RoleMenu;
import org.hainu.onlinedrugstore.entity.User;
import org.hainu.onlinedrugstore.service.DrugDoctorService;
import org.hainu.onlinedrugstore.service.RoleMenuService;
import org.hainu.onlinedrugstore.service.RoleService;
import org.hainu.onlinedrugstore.service.UserService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    RoleService roleService;
    @Autowired
    RoleMenuService roleMenuService;
    @Autowired
    DrugDoctorService drugDoctorService;
    @Autowired
    UserService userService;

    /**
     * 新增
     */
    @PostMapping
    public String insertRole(@RequestBody Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", role.getName());
        Role role1 = roleService.getOne(queryWrapper);
        if (role1 != null) {
            return fail();
        }
        roleService.save(role);
        return sucess();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    @Transactional
    public String deleteRoleById(@PathVariable Long id) {
        roleService.removeById(id);

        UpdateWrapper<RoleMenu> wrapper = new UpdateWrapper<>();
        wrapper.eq("role_id", id);
        roleMenuService.remove(wrapper);

        List<Long> userIds = userService.getIdsByRoleId(id);
        UpdateWrapper<User> wrapper1 = new UpdateWrapper<>();
        wrapper.eq("role_id", id);
        userService.remove(wrapper1);


        UpdateWrapper<DrugDoctor> wrapper2 = new UpdateWrapper<>();
        for (int i = 0; i < userIds.size(); i++) {
            wrapper2.eq("id", userIds.get(i));
            drugDoctorService.remove(wrapper2);
        }
        return sucess();
    }

    /**
     * 数据更新
     */
    @PutMapping
    public String update(@RequestBody Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", role.getName());
        Role role1 = roleService.getOne(queryWrapper);
        if (role1 != null) {
            return fail();
        }
        role.setVersion(roleService.getById(role.getId()).getVersion());
        roleService.updateById(role);
        return sucess();

    }

    /**
     * 根据id查询单条数据
     */
    @GetMapping("/{id}")
    public String selectById(@PathVariable Long id) {
        Role role = roleService.getById(id);
        if (role == null) {
            return fail();
        }
        return sucess(role);
    }

    /**
     * 列表查询
     */
    @GetMapping("/list")
    public String selectList(HttpServletRequest request) {
        QueryWrapper<Role> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(roleService.list(queryWrapper));
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public String selectPage(HttpServletRequest request, @RequestBody Page<Role> page) {
        QueryWrapper<Role> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, page, request);
        return sucess(roleService.page(page, queryWrapper));
    }
}
