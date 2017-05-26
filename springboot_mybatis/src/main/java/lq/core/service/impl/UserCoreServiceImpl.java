package lq.core.service.impl;

import lq.core.dao.UserDao;
import lq.core.domain.bo.UserBO;
import lq.core.domain.po.converter.UserPOconvertor;
import lq.core.service.UserCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据持久层业务接口实现类：用户。
 * 
 * @author 刘泉 2017年05月24日 15:34
 */
@Service
public class UserCoreServiceImpl implements UserCoreService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserBO getById(long id) {
        return UserPOconvertor.poToBo(userDao.getEntityById(id));
    }

    @Override
    public List<UserBO> queryAll() {
        return UserPOconvertor.poToBo(userDao.queryAll());
    }

    @Override
    public boolean insert(UserBO bo) {
        System.out.println(userDao.addEntity(UserPOconvertor.boToPo(bo)));
        return true;
    }

    @Override
    public boolean delete(long id) {
        System.out.println(userDao.deleteEntityById(id));
        return true;
    }

}
