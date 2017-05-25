package lq.web.controller.xhr;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lq.web.service.UserWebService;
import lq.web.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @ApiOperation(value = "增加一条用户列表", notes = "目前没有页面，代码写死")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
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
    @ApiOperation(value = "根据id获取某条用户记录", notes = "传参id")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "long")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object get(@RequestParam(value = "id", defaultValue = "0") long id) {
        return userWebService.getById(id);
    }

    /**
     * http://localhost:8080/xhr/user/list
     */
    @ApiOperation(value = "获取用户列表信息", notes = "传参id")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list() {
        return userWebService.queryAll();
    }

}
