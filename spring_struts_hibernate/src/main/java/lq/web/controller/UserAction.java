package lq.web.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

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

    /**
     * http://localhost:8080/user!add
     */
    public String add() {
        UserVO vo = new UserVO();
        vo.setName("good");
        vo.setUpdateTime(new Date());
        vo.setPrice(new BigDecimal("9999999999"));
        vo.setMoney(9999999999D);
        vo.setStatus(255);
        super.logger.info(" ===> [op: add] Action: userPO={}",
            JSON.toJSONString(vo));
        userWebService.insert(vo);
        getValueStack().set(super.PAGE, vo);
        return "success";
    }

    /**
     * http://localhost:8080/user!get?id=4
     */
    public String get() {
        super.logger.info(" ===> [op: get] Action: id={}, result={}", id,
            JSON.toJSONString(userWebService.getById(id)));
        getValueStack().set(super.MODEL, userWebService.getById(id));
        return "success";
    }

    /**
     * http://localhost:8080/user!list
     */
    public String list() {
        super.logger.info(" ===> [op: list] Action: result={}",
            JSON.toJSONString(userWebService.queryAll()));
        getValueStack().set(super.PAGE, userWebService.queryAll());
        return "success";
    }

    /**
     * http://localhost:8080/user!execute 或者 http://localhost:8080/user
     */
    public String execute() throws Exception {
        super.logger.info("打印一行日志");
        return "success";
    }

}
