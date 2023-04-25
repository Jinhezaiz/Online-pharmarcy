package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.dto.DoctorRoleDto;
import org.hainu.onlinedrugstore.dto.DoctorRolePageDTO;
import org.hainu.onlinedrugstore.dto.DrugDoctorDto;
import org.hainu.onlinedrugstore.dto.DrugStoreDto;
import org.hainu.onlinedrugstore.entity.*;
import org.hainu.onlinedrugstore.service.DrugDoctorService;
import org.hainu.onlinedrugstore.service.DrugStoreService;
import org.hainu.onlinedrugstore.service.RoleService;
import org.hainu.onlinedrugstore.service.UserService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/drugdoctor")
public class DrugDoctorController extends BaseController {
    @Autowired
    DrugDoctorService drugDoctorService;
    @Autowired
    DrugStoreService drugStoreService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    /**
     * 新增
     */
    @PostMapping
    public String insertDrugDoctor(@RequestBody DrugDoctor drugDoctor) {
        boolean result = drugDoctorService.save(drugDoctor);
        if (result)
            return sucess();
        return fail();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public String deleteDrugDoctorById(@PathVariable Long id) {
        drugDoctorService.removeById(id);
        return sucess();
    }

    /**
     * 数据更新
     */
    @PutMapping
    public String update(@RequestBody DrugDoctor drugDoctor) {
        drugDoctor.setVersion(drugDoctorService.getById(drugDoctor.getId()).getVersion());
        boolean result = drugDoctorService.updateById(drugDoctor);
        if (result) return sucess();
        return fail();
    }

    /**
     * 根据id查询单条数据
     */
    @GetMapping("/{id}")
    public String selectById(@PathVariable Long id) {
        DrugDoctor drugDoctor = drugDoctorService.getById(id);
        if (drugDoctor == null) {
            return fail();
        }
        return sucess(drugDoctor);
    }

    /**
     * 列表查询
     */
    @GetMapping("/list")
    public String selectList(HttpServletRequest request) {
        QueryWrapper<DrugDoctor> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(drugDoctorService.list(queryWrapper));
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public String selectPage(HttpServletRequest request, @RequestBody DrugDoctorDto drugDoctorDto) {
        Page<DrugDoctor> page = new Page<>();
        page.setCurrent(drugDoctorDto.getCurrent());
        page.setSize(drugDoctorDto.getSize());

        QueryWrapper<DrugStore> drugStoreQueryWrapper = new QueryWrapper<>();
        drugStoreQueryWrapper.eq("boss_id", drugDoctorDto.getId());
        DrugStore drugStore = drugStoreService.getOne(drugStoreQueryWrapper);

        QueryWrapper<DrugDoctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("drug_store_id", drugStore.getId());
        return sucess(drugDoctorService.page(page, queryWrapper));
    }

    /**
     * 分页查询
     */
    @PostMapping("/rolepage")
    public String selectRolePage(@RequestBody DoctorRoleDto doctorRoleDto) {
        Page<DrugDoctor> page = new Page<>();
        page.setCurrent(doctorRoleDto.getCurrent());
        page.setSize(doctorRoleDto.getSize());

        QueryWrapper<DrugStore> drugStoreQueryWrapper = new QueryWrapper<>();
        drugStoreQueryWrapper.eq("boss_id", doctorRoleDto.getId());
        DrugStore drugStore = drugStoreService.getOne(drugStoreQueryWrapper);

        QueryWrapper<DrugDoctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("drug_store_id", drugStore.getId());
        drugDoctorService.page(page, queryWrapper);
        DoctorRoleDto[] doctorRoleDtos = new DoctorRoleDto[page.getRecords().size()];
        for (int i = 0; i < doctorRoleDtos.length; i++) {
            doctorRoleDtos[i] = new DoctorRoleDto();
            doctorRoleDtos[i].setName(page.getRecords().get(i).getName());
            doctorRoleDtos[i].setDoctorId(page.getRecords().get(i).getId());
            doctorRoleDtos[i].setSex(page.getRecords().get(i).getSex());
            User user = userService.getById(page.getRecords().get(i).getId());
            Role role = roleService.getById(user.getRoleId());
            doctorRoleDtos[i].setRoleId(role.getId());
            doctorRoleDtos[i].setRoleName(role.getName());
        }
        DoctorRolePageDTO doctorRolePageDTO = new DoctorRolePageDTO();
        doctorRolePageDTO.setRecords(doctorRoleDtos);
        doctorRolePageDTO.setTotal(page.getTotal());
        return sucess(doctorRolePageDTO);
    }
}
