package lq.core.mapper;

import lq.core.domain.po.UserPO;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户相关。
 * 
 * @author 刘泉 2017年05月24日 15:15
 */
public interface UserMapper extends Mapper<UserPO> {

    // 作为测试
    @Select("select * from user")
    List<UserPO> queryAll();

}
