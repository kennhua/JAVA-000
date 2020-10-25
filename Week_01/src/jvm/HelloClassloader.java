package jvm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liugenghua
 * @date：2020/10/20
 * @Description jvm二进制文件转换 类加载器
 * @Version:1.0
 **/
public class HelloClassloader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> aClass = new HelloClassloader().findClass("Hello");
            Object instance = aClass.newInstance();
            Method method = aClass.getMethod("hello");
            method.invoke(instance);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filepath = "D:/JAVA-000/Week1/Hello/Hello.xlass";
        try(InputStream inputStream = new FileInputStream(filepath)) {
            int available = inputStream.available();
            byte[] bytes = new byte[available];
            inputStream.read(bytes);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
