package tuyen.bui.sms.common;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.function.Function;

@Component
public class Transaction {

    @Transactional
    public <T, R> R execute(Function<T, R> function, T t) {
        return function.apply(t);
    }
}
