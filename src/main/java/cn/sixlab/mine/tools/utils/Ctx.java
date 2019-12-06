package cn.sixlab.mine.tools.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Ctx {
    public static ApplicationContext ctx;

    @Autowired
    public void setCtx(ApplicationContext context){
        ctx = context;
    }

    public static <T> T getBean(Class<T> clz){
        return ctx.getBean(clz);
    }

    public static <T> T getBean(String name,Class<T> clz){
        return ctx.getBean(name, clz);
    }
}
