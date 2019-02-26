package org.springframework;

import org.junit.Test;
import org.springframework.core.OrderComparator;
import org.springframework.core.PriorityOrdered;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class xxxTest {

    @Test
    public void test1() {
        int compare = new OrderComparator().compare(new A(), new B());

        List<Object> list = Arrays.asList(new B(), new A());
        OrderComparator.sort(list);
        list.forEach(o -> System.out.println(o.getClass().getSimpleName()));
        System.out.println(compare);
        System.out.println(Integer.compare(1, 2));
    }

    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(2, 1, 4, 3);
        Collections.sort(list);
        list.forEach(System.out::println);
    }

    public class A implements PriorityOrdered {
        @Override
        public int getOrder() {
            return 0;
        }
    }

    public class B {
    }
}
