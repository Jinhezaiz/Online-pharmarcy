package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.entity.Menu;
import org.hainu.onlinedrugstore.service.MenuService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {
    @Autowired
    MenuService menuService;
	
	/**
	 * 新增
	 */
	@PostMapping
	public String insertMenu(@RequestBody Menu menu) {
	    boolean result = menuService.save(menu);
        if (result) 
		return sucess();
        return fail();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	public String deleteMenuById(@PathVariable Long id) {
	    menuService.removeById(id);
		return sucess();
	}
	
	/**
	 * 数据更新
	 */
	@PutMapping
	public String update(@RequestBody Menu menu) {
		boolean result = menuService.updateById(menu);
        if (result) return sucess();
        return fail();
	}
	
	/**
	 * 根据id查询单条数据
	 */
	@GetMapping("/{id}")
	public String selectById(@PathVariable Long id) {
		Menu menu = menuService.getById(id);
		if(menu == null) {
			return fail();
		}
		return sucess(menu);
	}
	
	/**
	 * 列表查询
	 */
	@GetMapping("/list")
	public String selectList(HttpServletRequest request) {
		QueryWrapper<Menu> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(menuService.list(queryWrapper));
	}
	
	/**
	 * 分页查询
	 */
	@GetMapping("/page") 
	public String selectPage(HttpServletRequest request,@RequestBody Page<Menu> page) {
		QueryWrapper<Menu> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, page, request);
        return sucess(menuService.page(page, queryWrapper));
	} 
}