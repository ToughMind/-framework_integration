/**
 * @(#)UserDao.JAVA, 2017年05月26日.
 *
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package lq.core.dao;

import lq.core.domain.po.UserPO;
import lq.core.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户相关。
 *
 * @author 刘泉 2017年05月26日 15:39
 */
@Repository
public class UserDao extends AbstactBaseDao<UserPO> {

    @Autowired
    private UserMapper userMapper;

    public List<UserPO> queryAll() {
        return userMapper.queryAll();
    }

}
