package lq.web.vo.convertor;

import lq.core.domain.bo.UserBO;
import lq.web.vo.UserVO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 页面对象转换器：用户。
 * 
 * @author 刘泉
 * @date 2016年11月7日 上午10:58:36
 */
public class UserVOconvertor {

    public static List<UserBO> voToBo(List<UserVO> voList) {
        List<UserBO> boList = new ArrayList<UserBO>(voList.size());
        for (UserVO vo: voList) {
            boList.add(voToBo(vo));
        }
        return boList;
    }

    public static UserBO voToBo(UserVO vo) {
        if (vo == null) {
            return null;
        }
        UserBO bo = new UserBO();
        bo.setId(vo.getId());
        bo.setName(vo.getName());
        bo.setPrice(vo.getPrice());
        bo.setStatus(vo.getStatus());
        bo.setCreateTime(vo.getCreateTime());
        bo.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            .format(vo.getUpdateTime()));
        bo.setMoney(vo.getMoney());
        return bo;
    }

    public static List<UserVO> boToVo(List<UserBO> boList) {
        List<UserVO> voList = new ArrayList<UserVO>(boList.size());
        for (UserBO bo: boList) {
            voList.add(boToVo(bo));
        }
        return voList;
    }

    public static UserVO boToVo(UserBO bo) {
        if (bo == null) {
            return null;
        }
        UserVO vo = new UserVO();
        vo.setId(bo.getId());
        vo.setName(bo.getName());
        vo.setPrice(bo.getPrice());
        vo.setCreateTime(bo.getCreateTime());
        vo.setStatus(bo.getStatus());
        try {
            vo.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                .parse(bo.getUpdateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        vo.setMoney(bo.getMoney());
        return vo;
    }
}
