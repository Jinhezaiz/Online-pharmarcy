package org.hainu.onlinedrugstore.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hainu.onlinedrugstore.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@TableName("user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    /**
     * 身份1,2,3分别代表店长，药师，顾客
     */
    private Integer auth;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 主键，根据身份，与boss,customer，drug_doctor的id相同
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
     * 密码
     */
    private String password;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 状态,1代表正常使用，0代表账号已被暂停
     */
    private Integer status;

    /**
     * 用户名
     */
    private String userName;

    /**
     *
     */
    @Version
    private Integer version;

}
