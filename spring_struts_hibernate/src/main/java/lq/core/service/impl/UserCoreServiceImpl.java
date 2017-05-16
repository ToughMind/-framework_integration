package lq.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lq.core.dao.UserDao;
import lq.core.domain.bo.UserBO;
import lq.core.domain.po.converter.UserPOconvertor;
import lq.core.service.UserCoreService;

/**
 * 数据持久层业务接口实现类：用户。
 * 
 * @author 刘泉
 * @date 2016年11月8日 上午10:46:34
 */
@Service
public class UserCoreServiceImpl implements UserCoreService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserBO getById(long id) {
		return UserPOconvertor.poToBo(userDao.get(id));
	}

	@Override
	public List<UserBO> queryAll() {
		return UserPOconvertor.poToBo(userDao.listAll());
	}

	@Override
	public boolean insert(UserBO bo) {
		userDao.save(UserPOconvertor.boToPo(bo));
		return true;
	}

	@Override
	public boolean delete(long id) {
		userDao.delete(id);
		return true;
	}

}
