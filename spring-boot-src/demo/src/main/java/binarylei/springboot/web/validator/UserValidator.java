package binarylei.springboot.web.validator;

import com.github.binarylei.springboot.web.bean.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    // 判断是否需要支持校验
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(User.class);
    }

    // target 要检验的对象，当校验非法时，通过 Errors 对象返回
    @Override
    public void validate(Object target, Errors errors) {
        User user = User.class.cast(target);
        if (user.getUsername().isEmpty()) {
            errors.reject("不能为空");
        }
    }

}
