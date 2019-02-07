package binarylei.comm;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: leigang
 * @version: 2018-09-01
 */
public class JsonUtil {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new User());
        System.out.println(json);
    }
}
