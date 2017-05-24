package lq.web.service.impl;

import lq.core.service.UserCoreService;
import lq.web.service.UserWebService;
import lq.web.vo.UserVO;
import lq.web.vo.convertor.UserVOconvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * web端业务接口实现类：用户。
 * 
 * @author 刘泉
 * @date 2016年11月7日 上午11:02:08
 */
@Service
public class UserWebServiceImpl implements UserWebService {

    @Autowired
    private UserCoreService userCoreService;

    @Override
    public List<UserVO> queryAll() {
        return UserVOconvertor.boToVo(userCoreService.queryAll());
    }

    @Override
    public UserVO getById(long id) {
        return UserVOconvertor.boToVo(userCoreService.getById(id));
    }

    @Override
    public boolean insert(UserVO vo) {
        return userCoreService.insert(UserVOconvertor.voToBo(vo));
    }

    @Override
    public boolean delete(long id) {
        return userCoreService.delete(id);
    }

}
