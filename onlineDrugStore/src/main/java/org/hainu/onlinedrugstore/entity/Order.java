package org.hainu.onlinedrugstore.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hainu.onlinedrugstore.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;


@Data
@TableName("order")
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {


	/**
     * 顾客id
     */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long customerId;

	/**
     * 订单目的地
     */
	private String destination;

	/**
     * 药品金额
     */
	private Float drugPrice;

	/**
     * 主键
     */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
     * 1代表数据已被删除，0代表没有
     */
	@TableLogic(value = "0",delval = "1")
	private Integer isDeleted;

	/**
     * 以数字1-6对应订单的六个状态
     */
	private Integer status;

	/**
     * 总金额
     */
	private Integer totalPrice;

	/**
     * 配送员id
     */
	private Long transferId;

	/**
     * 配送费
     */
	private Integer transferPrice;

	/**
     * 订单类型，1代表线上，0代表线下
     */
	private Integer type;

	/**
     *
     */
	@Version
	private Integer version;

}
