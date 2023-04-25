package org.hainu.onlinedrugstore.controller;

import org.hainu.onlinedrugstore.entity.User;
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
public class UserControllerTest {
	
	@Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;
	@Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	
	@Test
	public void insertUser() throws Exception {
		User user = new User();
		//请在下方填入想要测试的数据
		//user.setAccountLocked();
		//user.setAlterPriv();
		//user.setAlterRoutinePriv();
		//user.setAuthenticationString();
		//user.setCreatePriv();
		//user.setCreateRolePriv();
		//user.setCreateRoutinePriv();
		//user.setCreateTablespacePriv();
		//user.setCreateTmpTablePriv();
		//user.setCreateUserPriv();
		//user.setCreateViewPriv();
		//user.setDeletePriv();
		//user.setDropPriv();
		//user.setDropRolePriv();
		//user.setEventPriv();
		//user.setExecutePriv();
		//user.setFilePriv();
		//user.setGrantPriv();
		//user.setIndexPriv();
		//user.setInsertPriv();
		//user.setLockTablesPriv();
		//user.setMaxConnections();
		//user.setMaxQuestions();
		//user.setMaxUpdates();
		//user.setMaxUserConnections();
		//user.setPasswordExpired();
		//user.setPasswordLastChanged();
		//user.setPasswordLifetime();
		//user.setPasswordRequireCurrent();
		//user.setPasswordReuseHistory();
		//user.setPasswordReuseTime();
		//user.setPlugin();
		//user.setProcessPriv();
		//user.setReferencesPriv();
		//user.setReloadPriv();
		//user.setReplClientPriv();
		//user.setReplSlavePriv();
		//user.setSelectPriv();
		//user.setShowDbPriv();
		//user.setShowViewPriv();
		//user.setShutdownPriv();
		//user.setSslCipher();
		//user.setSslType();
		//user.setSuperPriv();
		//user.setTriggerPriv();
		//user.setUpdatePriv();
		//user.setUserAttributes();
		//user.setX509Issuer();
		//user.setX509Subject();
		//user.setAuth();
		//user.setEmail();
		//user.setIsDeleted();
		//user.setPassword();
		//user.setRoleId();
		//user.setStatus();
		//user.setUserName();
		//user.setVersion();
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JacksonUtil.toJson(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
				.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("{\"code\":\"1\",\"message\":\"成功\"}",result);	
	}
	
	@Test
	public void deleteUserById() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}",
                1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
				.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("{\"code\":\"1\",\"message\":\"成功\"}",result);			
	}
	
	@Test
	public void update() throws Exception {
		User user = new User();
		//请在下方填入想要测试的数据
		//user.setAccountLocked();
		//user.setAlterPriv();
		//user.setAlterRoutinePriv();
		//user.setAuthenticationString();
		//user.setCreatePriv();
		//user.setCreateRolePriv();
		//user.setCreateRoutinePriv();
		//user.setCreateTablespacePriv();
		//user.setCreateTmpTablePriv();
		//user.setCreateUserPriv();
		//user.setCreateViewPriv();
		//user.setDeletePriv();
		//user.setDropPriv();
		//user.setDropRolePriv();
		//user.setEventPriv();
		//user.setExecutePriv();
		//user.setFilePriv();
		//user.setGrantPriv();
		//user.setHost();
		//user.setIndexPriv();
		//user.setInsertPriv();
		//user.setLockTablesPriv();
		//user.setMaxConnections();
		//user.setMaxQuestions();
		//user.setMaxUpdates();
		//user.setMaxUserConnections();
		//user.setPasswordExpired();
		//user.setPasswordLastChanged();
		//user.setPasswordLifetime();
		//user.setPasswordRequireCurrent();
		//user.setPasswordReuseHistory();
		//user.setPasswordReuseTime();
		//user.setPlugin();
		//user.setProcessPriv();
		//user.setReferencesPriv();
		//user.setReloadPriv();
		//user.setReplClientPriv();
		//user.setReplSlavePriv();
		//user.setSelectPriv();
		//user.setShowDbPriv();
		//user.setShowViewPriv();
		//user.setShutdownPriv();
		//user.setSslCipher();
		//user.setSslType();
		//user.setSuperPriv();
		//user.setTriggerPriv();
		//user.setUpdatePriv();
		//user.setUser();
		//user.setUserAttributes();
		//user.setX509Issuer();
		//user.setX509Subject();
		//user.setAuth();
		//user.setEmail();
		//user.setId();
		//user.setIsDeleted();
		//user.setPassword();
		//user.setRoleId();
		//user.setStatus();
		//user.setUserName();
		//user.setVersion();
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JacksonUtil.toJson(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
				.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("{\"code\":\"1\",\"message\":\"成功\"}",result);
	}
	
	@Test
	public void selectById() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}",
                1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
				.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
        Assert.assertNotEquals("{\"code\":\"0\",\"message\":\"失败\"}",result);
	}
	
	@Test
	public void selectList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/list"
                ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void selectPage() throws Exception {
		Map<String,Long> map = new HashMap<>();
        map.put("current",2L);
        map.put("size",3L);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/page")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JacksonUtil.toJson(map)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
	}
}