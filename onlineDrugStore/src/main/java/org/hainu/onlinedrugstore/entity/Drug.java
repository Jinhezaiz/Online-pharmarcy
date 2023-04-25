package org.hainu.onlinedrugstore.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hainu.onlinedrugstore.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@TableName("drug")
@EqualsAndHashCode(callSuper = true)
public class Drug extends BaseEntity {


    /**
     * 生产企业
     */
    private String company;

    /**
     * 主键
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 1代表数据已被删除，0代表没有
     */
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;

    /**
     * 1代表处方药，0代表不是处方药
     */
    private Integer isLimited;

    /**
     * 保质期
     */
    private Integer life;

    /**
     * 药品名称
     */
    private String name;

    /**
     * 药品价格
     */
    private Float price;

    /**
     * 生产日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date produceTime;

    /**
     * 药品种类
     */
    private Integer type;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long drugStoreId;
    private int count;

    /**
     *
     */
    @Version
    private Integer version;

}
