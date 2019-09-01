package com.github.binarylei.servlet.web.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author: leigang
 * @version: 2019-01-01
 */
@WebServlet(value = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println(String.format("%s 主线程 start, %s",
                Thread.currentThread().getName(), System.currentTimeMillis()));
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            System.out.println(String.format("%s 子线程 start, %s",
                    Thread.currentThread().getName(), System.currentTimeMillis()));
            process();
            asyncContext.complete();
            try {
                PrintWriter writer = asyncContext.getResponse().getWriter();
                writer.write("async");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s 子线程 end, %s",
                    Thread.currentThread().getName(), System.currentTimeMillis()));
        });
        System.out.println(String.format("%s 主线程 end, %s",
                Thread.currentThread().getName(), System.currentTimeMillis()));
    }

    private void process() {
        System.out.println(String.format("%s 子线程 processing, %s",
                Thread.currentThread().getName(), System.currentTimeMillis()));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
