package com.example;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String args, Instrumentation instrumentation){
        System.out.println("Agent is running");
        AgentBuilder agentBuilder = new AgentBuilder.Default()
                .type(ElementMatchers.hasSuperType(ElementMatchers.named("User")))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.visit(Advice.to(JavaAgentControllerMonitor.class).on(ElementMatchers.nameStartsWith("doSomething"))));
        agentBuilder.installOn(instrumentation);
    }
    public static void agentmain(String args, Instrumentation instrumentation){
        premain(args, instrumentation);
    }
}
