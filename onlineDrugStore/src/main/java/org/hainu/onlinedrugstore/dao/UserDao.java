package org.hainu.onlinedrugstore.dao;

import org.apache.ibatis.annotations.Select;
import org.hainu.onlinedrugstore.base.dao.BaseDao;
import org.hainu.onlinedrugstore.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseDao<User> {
    @Select("SELECT * FROM user WHERE  user_name = #{userName} AND is_deleted!=1")
    public User getUserMessage(String userName);
    @Select("SELECT * FROM user WHERE email = #{email}")
    public User getUserByEmail(String email);
    @Select("SELECT id FROM user WHERE role_id =#{id}")
    public List<Long> getIdsByRoleId(Long id);
}
