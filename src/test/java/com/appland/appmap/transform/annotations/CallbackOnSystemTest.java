package com.appland.appmap.transform.annotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.appland.appmap.output.v1.Event;
import com.appland.appmap.test.util.ClassBuilder;

import org.junit.Before;
import org.junit.Test;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtMethod;

public class CallbackOnSystemTest {
  private final static String TargetClassName = "CallbackOnSystemTest.TargetClass";
  private final static String HookClassName = "CallbackOnSystemTest.HookClass";
  private final static Integer UNUSED_PARAMETER = -1;
  private CtClass targetClass;

  @Before
  public void initializeTestClasses() throws Exception {
    this.targetClass = new ClassBuilder(TargetClassName)
        .beginMethod()
          .setName("methodNoArgs")
        .endMethod()
        .beginMethod()
          .setName("methodSingleArg")
          .beginParameter()
            .setType(CtClass.intType)
            .setId("x")
          .endParameter()
        .endMethod()
        .beginMethod()
          .setName("methodManyArgs")
          .beginParameter()
            .setType(CtClass.intType)
            .setId("x")
          .endParameter()
          .beginParameter()
            .setType(CtClass.intType)
            .setId("y")
          .endParameter()
        .endMethod()
        .ctClass();
  }

  @Test
  public void testValidate() throws Exception {
    List<Hook> hooks = new ArrayList<Hook>();
    List<HookBinding> bindings = new ArrayList<HookBinding>();
    CtClass hookClass = new ClassBuilder(HookClassName)
        .beginMethod()
          .setName("methodNoArgs")
          .addParameter(Event.class.getName(), "event")
          .addParameter("java.lang.Object[]", "args")
          .beginAnnotation(HookClass.class.getName())
            .setMember("value", TargetClassName)
          .endAnnotation()
          .beginAnnotation(ArgumentArray.class.getName())
            .setMember("value", MethodEvent.METHOD_INVOCATION)
          .endAnnotation()
        .endMethod()
        .beginMethod()
          .setName("methodSingleArg")
          .addParameter(Event.class.getName(), "event")
          .addParameter("java.lang.Object", "returnValue")
          .addParameter("java.lang.Object[]", "args")
          .beginAnnotation(HookClass.class.getName())
            .setMember("value", TargetClassName)
          .endAnnotation()
          .beginAnnotation(ArgumentArray.class.getName())
            .setMember("value", MethodEvent.METHOD_RETURN)
          .endAnnotation()
        .endMethod()
        .beginMethod()
          .setName("methodManyArgs")
          .addParameter(Event.class.getName(), "event")
          .addParameter("java.lang.Throwable", "exception")
          .addParameter("java.lang.Object[]", "args")
          .beginAnnotation(HookClass.class.getName())
            .setMember("value", TargetClassName)
          .endAnnotation()
          .beginAnnotation(ArgumentArray.class.getName())
            .setMember("value", MethodEvent.METHOD_EXCEPTION)
          .endAnnotation()
        .endMethod()
        .ctClass();

    for (CtMethod behavior : hookClass.getDeclaredMethods()) {
      Hook hook = Hook.from(behavior);
      assertNotNull(hook);
      hooks.add(hook);
    }

    for (CtBehavior behavior : targetClass.getDeclaredBehaviors()) {
      hooks.stream()
            .filter(hook -> hook.getSourceSystem().match(behavior))
            .forEach(hook -> bindings.add(new HookBinding(hook, behavior, UNUSED_PARAMETER)));
    }

    for (HookBinding hookBinding : bindings) {
      Boolean isValid = hookBinding.getHook()
                                   .getSystem(CallbackOnSystem.class)
                                   .validate(hookBinding);
      assertTrue(isValid);
    }
  }

  @Test
  public void testInvalidHooksFailStaticValidation() throws Exception {
    CtClass hookClass = new ClassBuilder(HookClassName)
        .beginMethod()
          .setName("methodManyArgs")
          .addParameter(Event.class.getName(), "event")
          .addParameter("java.lang.String", "invalidArg")
          .beginAnnotation(HookClass.class.getName())
            .setMember("value", TargetClassName)
          .endAnnotation()
          .beginAnnotation(CallbackOn.class.getName())
            .setMember("value", MethodEvent.METHOD_RETURN)
          .endAnnotation()
        .endMethod()
        .beginMethod()
        .setName("methodManyArgs")
          .addParameter(Event.class.getName(), "event")
          .addParameter("java.lang.String", "invalidArg")
          .addParameter("java.lang.String", "anotherInvalidArg")
          .beginAnnotation(HookClass.class.getName())
            .setMember("value", TargetClassName)
          .endAnnotation()
          .beginAnnotation(CallbackOn.class.getName())
            .setMember("value", MethodEvent.METHOD_EXCEPTION)
          .endAnnotation()
        .endMethod()
        .ctClass();

    // CtMethod invalidHookMethod = hookClass.getDeclaredMethods()[0];
    // assertNull(Hook.from(invalidHookMethod));
  }
}
