package binarylei.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: leigang
 * @version: 2018-09-01
 */
@Component("global")
public class Global {


    @Value("${web.images-path}")
    public String path;
}
