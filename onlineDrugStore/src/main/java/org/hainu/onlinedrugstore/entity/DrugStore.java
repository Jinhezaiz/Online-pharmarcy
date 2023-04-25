package org.hainu.onlinedrugstore.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hainu.onlinedrugstore.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;


@Data
@TableName("drug_store")
@EqualsAndHashCode(callSuper = true)
public class DrugStore extends BaseEntity {


	/**
     * 门店地址
     */
	private String address;

	/**
     * 门店公告
     */
	private String announcement;

	/**
     * 店长id
     */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long bossId;

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
     * 门店名称
     */
	private String name;

	/**
     *
     */
	@Version
	private Integer version;

}
