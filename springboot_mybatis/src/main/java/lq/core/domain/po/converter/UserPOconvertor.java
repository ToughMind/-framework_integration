package lq.core.domain.po.converter;

import lq.core.domain.bo.UserBO;
import lq.core.domain.po.UserPO;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据持久层对象转换器：用户。
 * 
 * @author 刘泉
 * @date 2016年11月8日 上午10:52:54
 */
public class UserPOconvertor {

	public static List<UserBO> poToBo(List<UserPO> poList) {
		List<UserBO> boList = new ArrayList<UserBO>(poList.size());
		for (UserPO po : poList) {
			boList.add(poToBo(po));
		}
		return boList;
	}

	public static UserBO poToBo(UserPO po) {
		if (po == null) {
			return null;
		}
		UserBO bo = new UserBO();
		bo.setId(po.getId());
		bo.setName(po.getName());
		bo.setPrice(po.getPrice());
		bo.setStatus(po.getStatus());
		bo.setCreateTime(po.getCreateTime());
		bo.setUpdateTime(po.getUpdateTime());
		bo.setMoney(po.getMoney());
		return bo;
	}

	public static List<UserPO> boToPo(List<UserBO> boList) {
		List<UserPO> poList = new ArrayList<UserPO>(boList.size());
		for (UserBO bo : boList) {
			poList.add(boToPo(bo));
		}
		return poList;
	}

	public static UserPO boToPo(UserBO bo) {
		if (bo == null) {
			return null;
		}
		UserPO po = new UserPO();
		po.setId(bo.getId());
		po.setName(bo.getName());
		po.setPrice(bo.getPrice());
		po.setCreateTime(bo.getCreateTime());
		po.setStatus(bo.getStatus());
		po.setUpdateTime(bo.getUpdateTime());
		po.setMoney(bo.getMoney());
		return po;
	}
}
