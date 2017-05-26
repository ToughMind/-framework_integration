package lq.core.common.dao.impl;

import org.springframework.stereotype.Repository;
import lq.core.dao.UserDao;
import lq.core.domain.po.UserPO;

/**
 * 数据持久层DAO实现类：用户。
 * 
 * @author 刘泉
 * @date 2016年11月8日 下午7:55:21
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<UserPO, Long> implements UserDao {

}
