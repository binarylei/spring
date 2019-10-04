package binarylei.hystrix.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.Single;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author binarylei
 * @version 2019-09-22
 */
public class RxJavaTest {
    public static void main(String[] args) {
        Single.just(1)
                .subscribe(RxJavaTest::println);
    }

    @Test
    public void testObservable() throws ExecutionException, InterruptedException {
        Future<Integer> future = Observable.from(Arrays.asList(1))
                .toBlocking().toFuture();
        System.out.println(future.get());
    }

    @Test
    public void test() {
        System.out.println(3);
    }

    public static void println(Object obj) {
        System.out.println(String.format("%s: %s",
                Thread.currentThread().getName(),
                obj.toString()));
    }
}
