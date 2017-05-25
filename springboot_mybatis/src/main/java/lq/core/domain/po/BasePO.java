package lq.core.domain.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 公用数据库对象，方便后期反射。
 *
 * @author 刘泉 2017年05月25日 16:35
 */
@NameStyle(value = Style.camelhumpAndLowercase)
public class BasePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
