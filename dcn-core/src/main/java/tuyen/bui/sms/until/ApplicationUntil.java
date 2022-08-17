package tuyen.bui.sms.until;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class ApplicationUntil {
    @Autowired
    private static ApplicationContext context;

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
