package org.hainu.onlinedrugstore.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hainu.onlinedrugstore.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@TableName("customer")
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {


    /**
     * 地址
     */
    private String address;

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
     * 手机号
     */
    private Long phone;

    /**
     * 1代表男，2代表女
     */
    private Integer sex;

    /**
     *
     */
    @Version
    private Integer version;


}
