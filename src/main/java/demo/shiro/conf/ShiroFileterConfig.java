package demo.shiro.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * filter属性类
 *
 * @author 郭友文
 * @create 2017-10-12 15:02
 **/
@Component
public class ShiroFileterConfig {

    @Value("${auth.unauthorizedUrl}")
    private String unauthorizedUrl;

    @Value("${auth.loginUrl}")
    private String loginUrl;

    @Value("${auth.filterString}")
    private String filterString;

    public ShiroFileterConfig() {
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getFilterString() {
        return filterString;
    }

    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }
}
