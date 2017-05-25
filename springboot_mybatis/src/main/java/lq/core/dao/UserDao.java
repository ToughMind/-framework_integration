package lq.core.dao;

import lq.core.domain.bo.UserBO;
import lq.core.domain.po.UserPO;
import lq.core.domain.po.converter.UserPOconvertor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户相关。
 * 
 * @author 刘泉 2017年05月24日 15:15
 */
@Mapper
public interface UserDao extends BaseDao<UserPO> {

    @Insert("insert into user(name,status,createTime,updateTime,price,money) values(#{name},#{status},#{createTime},#{updateTime},#{price},#{money})")
    boolean insert(UserPO po);

    @Delete("delete from user where id = #{id}")
    boolean delete(long id);

    @Select("select * from user where id = #{id}")
    UserPO getById(long id);

    @Select("select * from user")
    List<UserPO> queryAll();

}
