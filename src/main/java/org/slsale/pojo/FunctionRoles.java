package org.slsale.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dll on 2017/10/21.
 * @author dll
 */
public class FunctionRoles implements Serializable{
    /**
     * 主功能
     */
    private Function mainFunction;
    /**
     * 子功能
     */
    private List<Function> subFunctionList;

    public FunctionRoles() {
    }

    public FunctionRoles(Function mainFunction, List<Function> subFunctionList) {
        this.mainFunction = mainFunction;
        this.subFunctionList = subFunctionList;
    }

    public Function getMainFunction() {
        return mainFunction;
    }

    public void setMainFunction(Function mainFunction) {
        this.mainFunction = mainFunction;
    }

    public List<Function> getSubFunctionList() {
        return subFunctionList;
    }

    public void setSubFunctionList(List<Function> subFunctionList) {
        this.subFunctionList = subFunctionList;
    }

    @Override
    public String toString() {
        return "FunctionRoles{" +
                "mainFunction=" + mainFunction +
                ", subFunctionList=" + subFunctionList +
                '}';
    }
}
