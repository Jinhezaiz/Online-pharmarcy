package org.hainu.onlinedrugstore.controller;

import lombok.SneakyThrows;
import org.hainu.onlinedrugstore.entity.Boss;
import org.hainu.onlinedrugstore.service.BossService;
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
public class BossControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;
    @Autowired
    private BossService bossService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void insertBoss() throws Exception {
        Boss boss = new Boss();
        //请在下方填入想要测试的数据
        //boss.setIsDeleted();
        boss.setName("李");
        boss.setSex(1);
        //boss.setVersion();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/boss")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.toJson(boss)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("{\"code\":\"1\",\"message\":\"成功\"}", result);
    }

    @Test
    public void deleteBossById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/boss/{id}",
                        1643127115274641410L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("{\"code\":\"1\",\"message\":\"成功\"}", result);
    }

    @Test
    public void update() throws Exception {
        Boss boss = new Boss();
        boss.setId(1643142550938206210L);
        boss.setName("迹部");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/boss")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.toJson(boss)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(bossService.getById(1643142550938206210L).getName());
        String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("{\"code\":\"1\",\"message\":\"成功\"}",result);

    }


    @Test
    public void selectById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/boss/{id}",
                        1643142550938206210L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assert.assertNotEquals("{\"code\":\"0\",\"message\":\"失败\"}", result);
    }

    @Test
    public void selectList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/boss/list"
                ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void selectPage() throws Exception {
        Map<String, Long> map = new HashMap<>();
        map.put("current", 2L);
        map.put("size", 3L);
        mockMvc.perform(MockMvcRequestBuilders.get("/boss/page")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtil.toJson(map)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
