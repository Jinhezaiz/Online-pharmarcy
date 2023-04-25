package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.dto.DrugStoreDto;
import org.hainu.onlinedrugstore.entity.DrugStore;
import org.hainu.onlinedrugstore.service.DrugStoreService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/drugstore")
public class DrugStoreController extends BaseController {
    @Autowired
    DrugStoreService drugStoreService;

	/**
	 * 新增
	 */
	@PostMapping
	public String insertDrugStore(@RequestBody DrugStore drugStore) {
	    boolean result = drugStoreService.save(drugStore);
        if (result)
		return sucess();
        return fail();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	public String deleteDrugStoreById(@PathVariable Long id) {
	    drugStoreService.removeById(id);
		return sucess();
	}

	/**
	 * 数据更新
	 */
	@PutMapping
	public String update(@RequestBody DrugStoreDto drugStoreDto) {
//		drugStore.setVersion(drugStoreService.getById(drugStore.getId()).getVersion());
		QueryWrapper<DrugStore> queryWrapper =new QueryWrapper<>();
		queryWrapper.eq("boss_id",drugStoreDto.getId());
		DrugStore drugStore = drugStoreService.getOne(queryWrapper);
		drugStore.setAddress(drugStoreDto.getAddress());
		drugStore.setName(drugStoreDto.getName());
		drugStore.setAnnouncement(drugStoreDto.getAnnouncement());
		drugStoreService.updateById(drugStore);
        return sucess();
	}

	/**
	 * 根据id查询单条数据
	 */
	@GetMapping("/{id}")
	public String selectById(@PathVariable Long id) {
		QueryWrapper<DrugStore> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("boss_id",id);
		DrugStore drugStore = drugStoreService.getOne(queryWrapper);
		if(drugStore == null) {
			return fail();
		}
		return sucess(drugStore);
	}

	/**
	 * 列表查询
	 */
	@GetMapping("/list")
	public String selectList(HttpServletRequest request) {
		QueryWrapper<DrugStore> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(drugStoreService.list());
	}

	/**
	 * 分页查询
	 */
	@PostMapping("/page")
	public String selectPage(HttpServletRequest request,@RequestBody Page<DrugStore> page) {
		QueryWrapper<DrugStore> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, page, request);
        return sucess(drugStoreService.page(page, queryWrapper));
	}
}
