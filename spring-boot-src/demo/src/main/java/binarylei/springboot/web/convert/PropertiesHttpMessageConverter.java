package binarylei.springboot.web.convert;

import com.github.binarylei.springboot.web.bean.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author: leigang
 * @version: 2018-12-31
 */
public class PropertiesHttpMessageConverter extends AbstractHttpMessageConverter<User> {

    public PropertiesHttpMessageConverter() {
        super(Charset.forName("utf-8"), MediaType.valueOf("application/properties"));
    }

    @Override
    protected boolean supports(Class clazz) {
        return clazz == User.class;
    }

    @Override
    protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        Properties properties = new Properties();
        properties.load(inputMessage.getBody());
        User user = new User();
        user.setUserId(properties.getProperty("user.id"));
        user.setUsername(properties.getProperty("user.name"));
        return user;
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        Properties properties = new Properties();
        properties.setProperty("user.id", user.getUserId());
        properties.setProperty("user.name", user.getUsername());
        properties.setProperty("user.password", user.getPassword());
        properties.store(outputMessage.getBody(), "write");
    }
}
