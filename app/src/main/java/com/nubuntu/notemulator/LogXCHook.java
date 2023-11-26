package com.nubuntu.notemulator;

import java.util.Arrays;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class LogXCHook extends XC_MethodHook {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        if(param.args.length > 0) {
            if(param.args[0].toString().equals("dalvik.vm.usejitprofiles"))
                param.setResult(false);
            if(param.args[0].toString().equals("dalvik.vm.dexopt.secondary"))
                param.setResult(false);
            if(param.args[0].toString().equals("net.qtaguid_enabled"))
                param.setResult(false);
            if(param.args[0].toString().equals("ro.bionic.ld.warning"))
                param.setResult(0);
            if(param.args[0].toString().equals("ro.art.hiddenapi.warning"))
                param.setResult(0);
            if(param.args[0].toString().equals("dalvik.vm.heapgrowthlimit"))
                param.setResult("");

        }
    }
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        // get className
        String className = param.thisObject!= null ? param.thisObject.getClass().getName() : "null";
        // get methodName
        String methodName = param.method!=null ? param.method.getName() : "null";
        // get args
        String args = Arrays.toString(param.args);
        // get result
        String result = param.getResult()!=null ? param.getResult().toString() : "null";
        // log all
        XposedBridge.log(className + "." + methodName + "(" + args + ") = " + result);
    }
}
