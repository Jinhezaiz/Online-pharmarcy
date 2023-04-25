package org.hainu.onlinedrugstore.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hainu.onlinedrugstore.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;


@Data
@TableName("drug_order")
@EqualsAndHashCode(callSuper = true)
public class DrugOrder extends BaseEntity {


	/**
     * 数量
     */
	private Integer count;

	/**
     * 药品id
     */
	private Long drugId;

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
     * 订单id
     */
	private Long orderId;

	/**
     *
     */
	@Version
	private Integer version;

}
