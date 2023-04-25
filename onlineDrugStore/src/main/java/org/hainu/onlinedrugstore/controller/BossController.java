package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.entity.Boss;
import org.hainu.onlinedrugstore.entity.DrugDoctor;
import org.hainu.onlinedrugstore.entity.DrugStore;
import org.hainu.onlinedrugstore.service.BossService;
import org.hainu.onlinedrugstore.service.DrugDoctorService;
import org.hainu.onlinedrugstore.service.DrugStoreService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/boss")
public class BossController extends BaseController {
    @Autowired
    BossService bossService;
    @Autowired
    DrugDoctorService drugDoctorService;
    @Autowired
    DrugStoreService drugStoreService;

    /**
     * 新增
     */
    @PostMapping
    public String insertBoss(@RequestBody Boss boss) {
        boolean result = bossService.save(boss);
        if (result) return sucess();
        return fail();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public String deleteBossById(@PathVariable Long id) {
        bossService.removeById(id);
        return sucess();
    }

    /**
     * 数据更新
     */
    @PutMapping
    public String update(@RequestBody Boss boss) {
        boss.setVersion(bossService.getById(boss.getId()).getVersion());
        boolean result = bossService.updateById(boss);
        if (result) return sucess();
        return fail();
    }

    /**
     * 根据id查询单条数据
     */
    @GetMapping("/{id}")
    public String selectIdByDoctorId(@PathVariable Long id) {
        DrugDoctor drugDoctor = drugDoctorService.getById(id);
        DrugStore drugStore = drugStoreService.getById(drugDoctor.getDrugStoreId());
        return sucess(drugStore.getBossId());
    }

    /**
     * 列表查询
     */
    @GetMapping("/list")
    public String selectList(HttpServletRequest request) {
        QueryWrapper<Boss> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(bossService.list(queryWrapper));
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public String selectPage(HttpServletRequest request, @RequestBody Page<Boss> page) {
        QueryWrapper<Boss> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, page, request);
        return sucess(bossService.page(page, queryWrapper));
    }
}
