package lq.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

import lq.common.action.BaseAction;
import lq.core.domain.po.UserPO;
import lq.web.domain.vo.UserVO;
import lq.web.service.UserWebService;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserWebService userWebService;

	private long id;

	public void setId(long id) {
		this.id = id;
	}

	public String add() {
		UserVO vo = new UserVO();
		vo.setName("good");
		vo.setUpdateTime(new Date());
		super.logger.info(" ===> [op: add] Action: userPO={}", JSON.toJSONString(vo));
		userWebService.insert(vo);
		getValueStack().set(super.PAGE, vo);
		return "success";
	}

	public String get() {
		super.logger.info(" ===> [op: get] Action: id={}, result={}", id,
				JSON.toJSONString(userWebService.getById(id)));
		getValueStack().set(super.PAGE, userWebService.getById(id));
		return "success";
	}

	public String list() {
		super.logger.info(" ===> [op: list] Action: result={}", JSON.toJSONString(userWebService.queryAll()));
		getValueStack().set(super.PAGE, userWebService.queryAll());
		return "success";
	}

	public String execute() throws Exception {
		super.logger.info(" ===> [op: execute] Action: id={}, result={}", id,
				JSON.toJSONString(userWebService.getById(id)));
		getValueStack().set(super.PAGE, userWebService.getById(1));
		return "success";
	}

}
