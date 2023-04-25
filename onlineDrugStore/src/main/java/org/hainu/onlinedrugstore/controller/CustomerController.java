package org.hainu.onlinedrugstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hainu.onlinedrugstore.base.controller.BaseController;
import org.hainu.onlinedrugstore.dto.CustomerDto;
import org.hainu.onlinedrugstore.entity.Customer;
import org.hainu.onlinedrugstore.entity.User;
import org.hainu.onlinedrugstore.service.CustomerService;
import org.hainu.onlinedrugstore.service.UserService;
import org.hainu.onlinedrugstore.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController {
    @Autowired
    CustomerService customerService;
	@Autowired
	UserService userService;

	/**
	 * 新增
	 */
	@PostMapping
	public String insertCustomer(@RequestBody Customer customer) {
	    boolean result = customerService.save(customer);
        if (result)
		return sucess();
        return fail();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/{id}")
	public String deleteCustomerById(@PathVariable Long id) {
	    customerService.removeById(id);
		return sucess();
	}

	/**
	 * 数据更新
	 */
	@PutMapping
	public String update(@RequestBody CustomerDto customerDto) {
		Customer customer = customerService.getById(customerDto.getId());
		customer.setName(customerDto.getName());
		customer.setAddress(customerDto.getAddress());
		customer.setPhone(Long.parseLong(customerDto.getPhone()));
		customer.setSex(customerDto.getSex());
		User user = userService.getById(customerDto.getId());
		user.setPassword(customerDto.getPassword());
		customerService.updateById(customer);
		userService.updateById(user);
		return sucess();
	}

	/**
	 * 根据id查询单条数据
	 */
	@GetMapping("/{id}")
	public String selectById(@PathVariable Long id) {
		Customer customer = customerService.getById(id);
		User user = userService.getById(id);
		CustomerDto customerDto = new CustomerDto();
		customerDto.setAddress(customer.getAddress());
		customerDto.setName(customer.getName());
		customerDto.setPassword(user.getPassword());
		customerDto.setSex(customer.getSex());
		customerDto.setPhone(Long.toString(customer.getPhone()));
		return sucess(customerDto);
	}

	/**
	 * 列表查询
	 */
	@GetMapping("/list")
	public String selectList(HttpServletRequest request) {
		QueryWrapper<Customer> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, null, request);
        return sucess(customerService.list(queryWrapper));
	}

	/**
	 * 分页查询
	 */
	@GetMapping("/page")
	public String selectPage(HttpServletRequest request,@RequestBody Page<Customer> page) {
		QueryWrapper<Customer> queryWrapper = Wrappers.query();
        ParameterUtil.getParametersMap(queryWrapper, page, request);
        return sucess(customerService.page(page, queryWrapper));
	}
}
