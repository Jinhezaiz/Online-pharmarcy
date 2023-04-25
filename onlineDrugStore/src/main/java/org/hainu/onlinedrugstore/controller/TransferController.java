package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.dto.TransferDto;
import org.hainu.onlinedrugstore.entity.DrugDoctor;
import org.hainu.onlinedrugstore.entity.DrugStore;
import org.hainu.onlinedrugstore.entity.Transfer;
import org.hainu.onlinedrugstore.service.DrugStoreService;
import org.hainu.onlinedrugstore.service.TransferService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/transfer")
public class TransferController extends BaseController {
    @Autowired
    TransferService transferService;
	@Autowired
	DrugStoreService drugStoreService;

	/**
	 * 新增
	 */
	@PostMapping
	public String insertTransfer(@RequestBody Transfer transfer) {
	    boolean result = transferService.save(transfer);
        if (result)
		return sucess();
        return fail();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	public String deleteTransferById(@PathVariable Long id) {
	    transferService.removeById(id);
		return sucess();
	}

	/**
	 * 数据更新
	 */
	@PutMapping
	public String update(@RequestBody Transfer transfer) {
		boolean result = transferService.updateById(transfer);
        if (result) return sucess();
        return fail();
	}

	/**
	 * 根据id查询单条数据
	 */
	@GetMapping("/{id}")
	public String selectById(@PathVariable Long id) {
		Transfer transfer = transferService.getById(id);
		if(transfer == null) {
			return fail();
		}
		return sucess(transfer);
	}

	/**
	 * 列表查询
	 */
	@GetMapping("/list")
	public String selectList(HttpServletRequest request) {
		QueryWrapper<Transfer> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(transferService.list(queryWrapper));
	}

	/**
	 * 分页查询
	 */
	@PostMapping("/page")
	public String selectPage(HttpServletRequest request,@RequestBody TransferDto transferDto) {
		Page<Transfer> page = new Page<>();
		page.setCurrent(transferDto.getCurrent());
		page.setSize(transferDto.getSize());

		QueryWrapper<DrugStore> drugStoreQueryWrapper = new QueryWrapper<>();
		drugStoreQueryWrapper.eq("boss_id",transferDto.getId());
		DrugStore drugStore = drugStoreService.getOne(drugStoreQueryWrapper);


		QueryWrapper<Transfer> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("drug_store_id",drugStore.getId());
		return sucess(transferService.page(page, queryWrapper));
	}
}
