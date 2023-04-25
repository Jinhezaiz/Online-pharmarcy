package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.entity.DrugOrder;
import org.hainu.onlinedrugstore.service.DrugOrderService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/drugorder")
public class DrugOrderController extends BaseController {
    @Autowired
    DrugOrderService drugOrderService;

	/**
	 * 新增
	 */
	@PostMapping
	public String insertDrugOrder(@RequestBody DrugOrder drugOrder) {
	    boolean result = drugOrderService.save(drugOrder);
        if (result)
		return sucess();
        return fail();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	public String deleteDrugOrderById(@PathVariable Long id) {
	    drugOrderService.removeById(id);
		return sucess();
	}

	/**
	 * 数据更新
	 */
	@PutMapping
	public String update(@RequestBody DrugOrder drugOrder) {
		drugOrder.setVersion(drugOrderService.getById(drugOrder.getId()).getVersion());
		boolean result = drugOrderService.updateById(drugOrder);
        if (result) return sucess();
        return fail();
	}

	/**
	 * 根据id查询单条数据
	 */
	@GetMapping("/{id}")
	public String selectById(@PathVariable Long id) {
		DrugOrder drugOrder = drugOrderService.getById(id);
		if(drugOrder == null) {
			return fail();
		}
		return sucess(drugOrder);
	}

	/**
	 * 列表查询
	 */
	@GetMapping("/list")
	public String selectList(HttpServletRequest request) {
		QueryWrapper<DrugOrder> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(drugOrderService.list(queryWrapper));
	}

	/**
	 * 分页查询
	 */
	@GetMapping("/page")
	public String selectPage(HttpServletRequest request,@RequestBody Page<DrugOrder> page) {
		QueryWrapper<DrugOrder> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, page, request);
        return sucess(drugOrderService.page(page, queryWrapper));
	}
}
