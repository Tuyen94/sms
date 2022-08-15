package tuyen.bui.sms.common;

import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

public class Transaction {

    @Transactional
    public static <T, R> R execute(Function<T, R> function, T t) {
        return function.apply(t);
    }
}
