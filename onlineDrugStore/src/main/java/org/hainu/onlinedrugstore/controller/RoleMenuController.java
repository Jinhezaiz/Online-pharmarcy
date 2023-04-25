package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.checkerframework.checker.units.qual.A;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.dto.RoleMenuDto;
import org.hainu.onlinedrugstore.dto.RoleMenuPageDto;
import org.hainu.onlinedrugstore.entity.Menu;
import org.hainu.onlinedrugstore.entity.Role;
import org.hainu.onlinedrugstore.entity.RoleMenu;
import org.hainu.onlinedrugstore.service.MenuService;
import org.hainu.onlinedrugstore.service.RoleMenuService;
import org.hainu.onlinedrugstore.service.RoleService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/rolemenu")
public class RoleMenuController extends BaseController {
    @Autowired
    RoleMenuService roleMenuService;
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    /**
     * 新增
     */
    @PostMapping
    public String insertRoleMenu(@RequestBody RoleMenu roleMenu) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_id",roleMenu.getMenuId());
        queryWrapper.eq("role_id",roleMenu.getRoleId());
        RoleMenu roleMenu1 = roleMenuService.getOne(queryWrapper);
        if(roleMenu1!=null){
            return fail();
        }
        roleMenuService.save(roleMenu);
        return sucess();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public String deleteRoleMenuById(@PathVariable Long id) {
        roleMenuService.removeById(id);
        return sucess();
    }

    /**
     * 数据更新
     */
    @PutMapping
    public String update(@RequestBody RoleMenu roleMenu) {
        roleMenu.setVersion(roleMenuService.getById(roleMenu.getId()).getVersion());
        boolean result = roleMenuService.updateById(roleMenu);
        if (result) return sucess();
        return fail();
    }

    /**
     * 根据id查询单条数据
     */
    @GetMapping("/{id}")
    public String selectById(@PathVariable Long id) {
        RoleMenu roleMenu = roleMenuService.getById(id);
        if (roleMenu == null) {
            return fail();
        }
        return sucess(roleMenu);
    }

    /**
     * 列表查询
     */
    @GetMapping("/list/{id}")
    public String selectMenuListByRoleId(@PathVariable Long id) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        List<RoleMenu> roleMenuList = roleMenuService.list(queryWrapper);
        List<Long> menuIdList = new ArrayList<>();
        for(int i=0;i<roleMenuList.size();i++){
            menuIdList.add(roleMenuList.get(i).getMenuId());
        }
        return sucess(menuIdList);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public String selectPage(HttpServletRequest request, @RequestBody Page<RoleMenu> page) {
        QueryWrapper<RoleMenu> queryWrapper = Wrappers.query();
        queryWrapper.ne("is_deleted",1);
        ParameterUtil.getParametersMap(queryWrapper, page, request);
        roleMenuService.page(page, queryWrapper);
        RoleMenuDto[] roleMenuDtos = new RoleMenuDto[page.getRecords().size()];
        for (int i = 0; i < roleMenuDtos.length; i++) {
            roleMenuDtos[i]= new RoleMenuDto();
            roleMenuDtos[i].setMenuId(page.getRecords().get(i).getMenuId());
            roleMenuDtos[i].setId(page.getRecords().get(i).getId());
            roleMenuDtos[i].setRoleId(page.getRecords().get(i).getRoleId());
            Role role = roleService.getById(roleMenuDtos[i].getRoleId());
            Menu menu = menuService.getById(roleMenuDtos[i].getMenuId());
            roleMenuDtos[i].setMenuName(menu.getName());
            roleMenuDtos[i].setRoleName(role.getName());
        }
        RoleMenuPageDto roleMenuPageDto = new RoleMenuPageDto();
        roleMenuPageDto.setRecords(roleMenuDtos);
        roleMenuPageDto.setTotal(page.getTotal());
        return sucess(roleMenuPageDto);
    }
}
