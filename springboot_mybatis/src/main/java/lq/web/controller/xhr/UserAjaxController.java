package lq.web.controller.xhr;

import lq.web.service.UserWebService;
import lq.web.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/xhr/user")
public class UserAjaxController {

    @Autowired
    private UserWebService userWebService;

    private long id;

    public void setId(long id) {
        this.id = id;
    }

    /**
     * http://localhost:8080/xhr/user/add
     */
    @RequestMapping("/add")
    public Object add() {
        UserVO vo = new UserVO();
        vo.setName("good");
        vo.setUpdateTime(new Date());
        vo.setPrice(new BigDecimal("9999999999"));
        vo.setMoney(9999999999D);
        vo.setStatus(255);
        userWebService.insert(vo);
        return "success";
    }

    /**
     * http://localhost:8080/xhr/user/get?id=4
     */
    @RequestMapping("/get")
    public Object get(@RequestParam(value = "id", defaultValue = "0") long id) {
        return userWebService.getById(id);
    }

    /**
     * http://localhost:8080/xhr/user/list
     */
    @RequestMapping("/list")
    public Object list() {
        return userWebService.queryAll();
    }

}
