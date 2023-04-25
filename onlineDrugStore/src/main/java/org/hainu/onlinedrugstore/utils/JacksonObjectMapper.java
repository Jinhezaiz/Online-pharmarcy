package org.hainu.onlinedrugstore.utils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonObjectMapper extends ObjectMapper {
	public JacksonObjectMapper() {
		super();
		this.disable(MapperFeature.DEFAULT_VIEW_INCLUSION); //避免IE执行AJAX时,返回JSON出现下载文件
	}
}
