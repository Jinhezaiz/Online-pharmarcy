package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.dto.DrugDto;
import org.hainu.onlinedrugstore.entity.Drug;
import org.hainu.onlinedrugstore.entity.DrugStore;
import org.hainu.onlinedrugstore.service.DrugService;
import org.hainu.onlinedrugstore.service.DrugStoreService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/drug")
public class DrugController extends BaseController {
    @Autowired
    DrugService drugService;
    @Autowired
    DrugStoreService drugStoreService;

    /**
     * 新增
     */
    @PostMapping
    public String insertDrug(@RequestBody Drug drug) {
        QueryWrapper<Drug> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", drug.getName())
                .eq("drug_store_id", drug.getDrugStoreId())
                .eq("company", drug.getCompany());
        Drug drug1 = drugService.getOne(queryWrapper);
        if (drug1 != null) {
            return fail("添加失败，该药品已存在");
        }
        drugService.save(drug);
        return sucess();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public String deleteDrugById(@PathVariable Long id) {
        drugService.removeById(id);
        return sucess();
    }

    /**
     * 数据更新
     */
    @PutMapping
    public String update(@RequestBody Drug drug) {
        QueryWrapper<Drug> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", drug.getName())
                .eq("drug_store_id", drugService.getById(drug.getId()).getDrugStoreId())
                .eq("company", drug.getCompany());
        Drug drug1 = drugService.getOne(queryWrapper);
        if (drug1 != null) {
            return fail("更新失败，该药品已存在");
        }
        drug.setVersion(drugService.getById(drug.getId()).getVersion());
        drugService.updateById(drug);
        return sucess();
    }

    /**
     * 根据id查询单条数据
     */
    @GetMapping("/{id}")
    public String selectById(@PathVariable Long id) {
        Drug drug = drugService.getById(id);
        if (drug == null) {
            return fail();
        }
        return sucess(drug);
    }

    /**
     * 列表查询
     */
    @GetMapping("/list")
    public String selectList(HttpServletRequest request) {
        QueryWrapper<Drug> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(drugService.list(queryWrapper));
    }

    @PostMapping("/storepage")
    public String selectStorePage(@RequestBody DrugDto drugDto) {
        QueryWrapper<DrugStore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("boss_id", drugDto.getId());
        DrugStore drugStore = drugStoreService.getOne(queryWrapper);
        Page<Drug> page = new Page<>();
        page.setSize(drugDto.getSize());
        page.setCurrent(drugDto.getCurrent());
        QueryWrapper<Drug> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("drug_store_id", drugStore.getId());
        return sucess(drugService.page(page, queryWrapper1));
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public String selectPage(HttpServletRequest request, @RequestBody Page<Drug> page) {
        QueryWrapper<Drug> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, page, request);
        return sucess(drugService.page(page, queryWrapper));
    }
}
