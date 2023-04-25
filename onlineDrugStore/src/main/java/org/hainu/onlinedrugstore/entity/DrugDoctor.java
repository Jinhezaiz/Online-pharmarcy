package org.hainu.onlinedrugstore.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hainu.onlinedrugstore.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;


@Data
@TableName("drug_doctor")
@EqualsAndHashCode(callSuper = true)
public class DrugDoctor extends BaseEntity {


	/**
     * 所属门店id
     */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long drugStoreId;

	/**
     * 主键
     */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
     * 1代表数据已被删除，0代表没有
     */
	@TableLogic(value = "0",delval = "1")
	private Integer isDeleted;

	/**
     * 姓名
     */
	private String name;

	/**
     * 性别
     */
	private Integer sex;

	/**
     *
     */
	@Version
	private Integer version;

}
