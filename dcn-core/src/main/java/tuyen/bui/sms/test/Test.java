package tuyen.bui.sms.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tuyen.bui.sms.config.DcnProperties;

import javax.annotation.PostConstruct;


public class Test {

    @Autowired
    DcnProperties dcnProperties;

    @PostConstruct
    public void test() {
        System.out.println(dcnProperties);
    }
}
