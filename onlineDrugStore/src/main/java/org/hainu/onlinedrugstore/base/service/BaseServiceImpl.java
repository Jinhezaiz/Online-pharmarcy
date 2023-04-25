package org.hainu.onlinedrugstore.base.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * BaseServiceImpl - 基类
 *
 * @version 1.0
 * @date 2023/2/21
 */
@Transactional
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
}
