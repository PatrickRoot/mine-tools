package cn.sixlab.mine.tools;

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
}
