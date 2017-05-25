package lq.core.service.impl;

import lq.core.dao.BaseDao;
import lq.core.dao.UserDao;
import lq.core.domain.bo.UserBO;
import lq.core.domain.po.UserPO;
import lq.core.domain.po.converter.UserPOconvertor;
import lq.core.service.UserCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据持久层业务接口实现类：用户。
 * 
 * @author 刘泉 2017年05月24日 15:34
 */
@Service
public class UserCoreServiceImpl implements UserCoreService {

    //@Resource
    //private UserDao userDao;

    @Resource(name="baseDao")
    private BaseDao userDao;

    @Override
    public UserBO getById(long id) {
        return UserPOconvertor.poToBo((UserPO) userDao.getEntityById(UserPO.class, id));
        //return UserPOconvertor.poToBo(userDao.getById(id));
    }

    @Override
    public List<UserBO> queryAll() {
        //return UserPOconvertor.poToBo(userDao.queryAll());
        return UserPOconvertor.poToBo(userDao.selectAll(UserPO.class));
    }

    @Override
    public boolean insert(UserBO bo) {
        //userDao.insert(UserPOconvertor.boToPo(bo));
        return true;
    }

    @Override
    public boolean delete(long id) {
        //userDao.delete(id);
        return true;
    }

}
