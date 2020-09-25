package me.ryubato.sample;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BookServiceByteBuddyProxy {

    public static <T> T createInstance(Class<T> classType) {

        T newInstance;

        try {
            newInstance = classType.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        Class<? extends T> proxyClass = new ByteBuddy().subclass(classType)
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("prepare : " + method.getName());
                        Object invoke = method.invoke(newInstance, args);
                        System.out.println("completed : " + method.getName());
                        return invoke;
                    }
                }))
                .make().load(classType.getClassLoader()).getLoaded();

        T proxyInstance;

        try {
            proxyInstance = proxyClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return proxyInstance;
    }
}
