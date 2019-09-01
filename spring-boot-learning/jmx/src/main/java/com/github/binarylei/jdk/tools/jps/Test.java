package com.github.binarylei.jdk.tools.jps;

/**
 * @author leigang
 * @version 2019-05-06
 * @since 2.0.0
 */
public class Test {
    static Test2 t1 = new Test2();
    Test2 t2 = new Test2();

    public void fn() {
        Test2 t3 = new Test2();
    }
}

class Test2 {

}
