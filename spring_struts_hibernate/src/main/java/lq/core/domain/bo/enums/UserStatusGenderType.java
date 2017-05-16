package lq.core.domain.bo.enums;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

/**
 * 自定义枚举类型处理器。
 * 
 * @author 刘泉
 * @date 2016年11月8日 下午2:58:47
 */
public class UserStatusGenderType implements UserType {

	/** 告诉Hibernate要使用什么SQL列类型生成DDL */
	@Override
	public int[] sqlTypes() {
		// TODO Auto-generated method stub
		return new int[]{0};
	}

	/** 告诉Hibernate这个UserType用来映射的数据类型。 */
	@Override
	public Class returnedClass() {
		return UserStatus.class;
	}

	@Override
	public boolean equals(Object paramObject1, Object paramObject2) throws HibernateException {
		return paramObject1 == paramObject2;
	}

	@Override
	public int hashCode(Object paramObject) throws HibernateException {
		return paramObject.hashCode();
	}

	/** 从JDBC的ResultSet读取属性值。这个方法是在从数据库查询数据时用到。 */
	@Override
	public Object nullSafeGet(ResultSet paramResultSet, String[] paramArrayOfString,
			SharedSessionContractImplementor paramSharedSessionContractImplementor, Object paramObject)
			throws HibernateException, SQLException {
		int value = paramResultSet.getInt(paramArrayOfString[0]);
		return UserStatus.NULL.generateEnum(value);
	}

	/** 将属性的值设置到PreparedStatement。 */
	@Override
	public void nullSafeSet(PreparedStatement paramPreparedStatement, Object paramObject, int paramInt,
			SharedSessionContractImplementor paramSharedSessionContractImplementor)
			throws HibernateException, SQLException {
		if (paramObject == null) {
			paramPreparedStatement.setInt(paramInt, 0);
		} else {
			paramPreparedStatement.setInt(paramInt, ((UserStatus) paramObject).getValue());
		}
	}

	/** 这是用于Hibernate缓存生成的快照，由于设定了类型是不可变的，直接返回就好了。 */
	@Override
	public Object deepCopy(Object paramObject) throws HibernateException {
		// TODO Auto-generated method stub
		return paramObject;
	}

	/** 告诉hibernate这个类型是不可变的。有微小的性能优化 */
	@Override
	public boolean isMutable() {
		return false;
	}

	/** hibernate把这个数据放入二级缓存时要调用的方法 */
	@Override
	public Serializable disassemble(Object paramObject) throws HibernateException {
		// TODO Auto-generated method stub
		return (Serializable) paramObject;
	}

	/** 从二级缓存中取这个对象数据时要调用的方法 */
	@Override
	public Object assemble(Serializable paramSerializable, Object paramObject) throws HibernateException {
		// TODO Auto-generated method stub
		return paramSerializable;
	}

	/** 处理脱管对象状态的合并。 */
	@Override
	public Object replace(Object paramObject1, Object paramObject2, Object paramObject3) throws HibernateException {
		// TODO Auto-generated method stub
		return paramObject1;
	}

}
