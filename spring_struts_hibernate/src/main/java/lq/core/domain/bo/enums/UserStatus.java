package lq.core.domain.bo.enums;

/**
 * 枚举类：用户状态。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午2:32:45
 */
public enum UserStatus implements EnumIntegerInterface<UserStatus> {

	NULL(-1, "NULL"),

	GOLD(1, "金牌会员"),

	SILVER(2, "银牌会员"),

	COPPER(3, "铜牌会员");
	
	private final int value;
	
	private final String desc;
	
	private UserStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public UserStatus generateEnum(int value) {
		for (UserStatus item : UserStatus.values()) {
			if (item.value == value)
				return item;
		}
		return NULL;
	}
}
