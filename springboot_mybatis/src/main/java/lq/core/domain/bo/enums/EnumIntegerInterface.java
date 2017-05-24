package lq.core.domain.bo.enums;

/**
 * 枚举模板：公用枚举类接口。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午2:33:03
 */
public interface EnumIntegerInterface<T> {

	public int getValue();

	public T generateEnum(int value);

}
