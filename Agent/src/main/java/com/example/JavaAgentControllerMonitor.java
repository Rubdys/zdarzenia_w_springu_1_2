package com.example;

import net.bytebuddy.asm.Advice;

import java.util.logging.Logger;


public class JavaAgentControllerMonitor {
    public static final Logger logger = Logger.getLogger(JavaAgentControllerMonitor.class.getName());
    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin Class clazz, @Advice.Origin("#m") String methodName) {
        logger.info("Entering method: " + methodName);
    }

    @Advice.OnMethodExit
    public static void exit(){
        logger.info("Exiting method");
    }
}
