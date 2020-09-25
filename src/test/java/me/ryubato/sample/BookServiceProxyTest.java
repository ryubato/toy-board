package me.ryubato.sample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class BookServiceProxyTest {

    @Test
    void proxyTest() {
        BookService bookService = new BookServiceProxy(new DefaultBookService());
        bookService.rentBook();
        bookService.returnBook();
    }

    @Test
    void dynamicProxyTest() {

        BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class},
                new InvocationHandler() {

                    BookService bookService = new DefaultBookService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("prepare");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("completed");
                        return invoke;
                    }
                });

        bookService.rentBook();
        bookService.returnBook();
    }

    @Test
    void dynamicProxyTest_withByteBuddy() {
        BookService bookService = BookServiceByteBuddyProxy.createInstance(DefaultBookService.class);
        Assertions.assertThat(bookService).isNotNull();

        bookService.rentBook();
        bookService.returnBook();
    }
}