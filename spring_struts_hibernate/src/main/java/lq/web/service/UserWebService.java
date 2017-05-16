package lq.web.service;

import java.util.List;

import lq.web.domain.vo.UserVO;

/**
 * web端业务接口：用户。
 * 
 * @author 刘泉
 * @date 2016年11月7日 上午10:56:55
 */
public interface UserWebService {

	List<UserVO> queryAll();

	UserVO getById(long id);

	boolean insert(UserVO vo);

	boolean delete(long id);
}