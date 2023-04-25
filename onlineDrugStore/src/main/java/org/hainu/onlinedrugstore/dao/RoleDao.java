package org.hainu.onlinedrugstore.dao;

import org.apache.ibatis.annotations.Select;
import org.hainu.onlinedrugstore.base.dao.BaseDao;
import org.hainu.onlinedrugstore.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends BaseDao<Role> {
}
