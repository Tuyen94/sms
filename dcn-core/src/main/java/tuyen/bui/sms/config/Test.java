package tuyen.bui.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Test {

    @Autowired
    DcnProperties dcnProperties;

    @PostConstruct
    public void test() {
        System.out.println(dcnProperties);
    }
}
