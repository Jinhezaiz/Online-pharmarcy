package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.entity.Order;
import org.hainu.onlinedrugstore.service.OrderService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    OrderService orderService;

	/**
	 * 新增
	 */
	@PostMapping
	public String insertOrder(@RequestBody Order order) {
	    boolean result = orderService.save(order);
        if (result)
		return sucess();
        return fail();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	public String deleteOrderById(@PathVariable Long id) {
	    orderService.removeById(id);
		return sucess();
	}

	/**
	 * 数据更新
	 */
	@PutMapping
	public String update(@RequestBody Order order) {
		order.setVersion(orderService.getById(order.getId()).getVersion());
		boolean result = orderService.updateById(order);
        if (result) return sucess();
        return fail();
	}

	/**
	 * 根据id查询单条数据
	 */
	@GetMapping("/{id}")
	public String selectById(@PathVariable Long id) {
		Order order = orderService.getById(id);
		if(order == null) {
			return fail();
		}
		return sucess(order);
	}

	/**
	 * 列表查询
	 */
	@GetMapping("/list")
	public String selectList(HttpServletRequest request) {
		QueryWrapper<Order> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(orderService.list(queryWrapper));
	}

	/**
	 * 分页查询
	 */
	@GetMapping("/page")
	public String selectPage(HttpServletRequest request,@RequestBody Page<Order> page) {
		QueryWrapper<Order> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, page, request);
        return sucess(orderService.page(page, queryWrapper));
	}
}
