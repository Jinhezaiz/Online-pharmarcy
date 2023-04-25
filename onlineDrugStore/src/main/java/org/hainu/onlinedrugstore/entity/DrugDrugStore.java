package org.hainu.onlinedrugstore.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hainu.onlinedrugstore.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;


@Data
@TableName("drug_drug_store")
@EqualsAndHashCode(callSuper = true)
public class DrugDrugStore extends BaseEntity {


	/**
     * 数量
     */
	private Integer count;

	/**
     * 药品id
     */
	private Long drugId;

	/**
     * 门店id
     */
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
     *
     */
	@Version
	private Integer version;

}
