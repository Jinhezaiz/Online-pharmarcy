package org.hainu.onlinedrugstore.controller;

import org.hainu.onlinedrugstore.entity.DrugOrder;
import org.hainu.onlinedrugstore.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.HashMap;
import java.util.Map;
import org.springframework.test.web.servlet.MvcResult;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DrugOrderControllerTest {
	
	@Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;
	@Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	
	@Test
	public void insertDrugOrder() throws Exception {
		DrugOrder drugOrder = new DrugOrder();
		//请在下方填入想要测试的数据
		//drugOrder.setCount();
		//drugOrder.setDrugId();
		//drugOrder.setIsDeleted();
		//drugOrder.setOrderId();
		//drugOrder.setVersion();
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/drugorder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JacksonUtil.toJson(drugOrder)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
				.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("{\"code\":\"1\",\"message\":\"成功\"}",result);	
	}
	
	@Test
	public void deleteDrugOrderById() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/drugorder/{id}",
                1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
				.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("{\"code\":\"1\",\"message\":\"成功\"}",result);			
	}
	
	@Test
	public void update() throws Exception {
		DrugOrder drugOrder = new DrugOrder();
		//请在下方填入想要测试的数据
		//drugOrder.setCount();
		//drugOrder.setDrugId();
		//drugOrder.setId();
		//drugOrder.setIsDeleted();
		//drugOrder.setOrderId();
		//drugOrder.setVersion();
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/drugorder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JacksonUtil.toJson(drugOrder)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
				.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("{\"code\":\"1\",\"message\":\"成功\"}",result);
	}
	
	@Test
	public void selectById() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/drugorder/{id}",
                1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
				.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
        Assert.assertNotEquals("{\"code\":\"0\",\"message\":\"失败\"}",result);
	}
	
	@Test
	public void selectList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/drugorder/list"
                ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void selectPage() throws Exception {
		Map<String,Long> map = new HashMap<>();
        map.put("current",2L);
        map.put("size",3L);
        mockMvc.perform(MockMvcRequestBuilders.get("/drugorder/page")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JacksonUtil.toJson(map)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
	}
}