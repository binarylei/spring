package com.github.binarylei.rpc.netty;

import java.util.Arrays;

/**
 * @author: leigang
 * @version: 2018-08-28
 */
public class InvokerMsg {

    private String className;
    private String methodName;
    private Class[] paramterTypes;
    private Object[] args;

    public InvokerMsg() {
    }

    public InvokerMsg(String className, String methodName, Class[] paramterTypes, Object[] args) {
        this.className = className;
        this.methodName = methodName;
        this.paramterTypes = paramterTypes;
        this.args = args;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamterTypes() {
        return paramterTypes;
    }

    public void setParamterTypes(Class[] paramterTypes) {
        this.paramterTypes = paramterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "InvokerMsg{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramterTypes=" + Arrays.toString(paramterTypes) +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
