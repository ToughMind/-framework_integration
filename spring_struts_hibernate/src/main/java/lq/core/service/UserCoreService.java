package lq.core.service;

import java.util.List;

import lq.core.domain.bo.UserBO;

/**
 * 数据持久层业务接口：用户。
 * 
 * @author 刘泉
 * @date 2016年11月8日 上午10:45:31
 */
public interface UserCoreService {

	List<UserBO> queryAll();

	UserBO getById(long id);

	boolean insert(UserBO bo);

	boolean delete(long id);
}
