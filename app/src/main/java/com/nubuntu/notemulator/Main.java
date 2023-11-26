package com.nubuntu.notemulator;

import java.util.Arrays;
import java.util.Objects;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
public class Main implements IXposedHookLoadPackage, IXposedHookZygoteInit {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        // Hook getDeviceId()
        XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", loadPackageParam.classLoader, "getDeviceId", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                // Return a valid Galaxy S4 IMEI
                XposedBridge.log("Hook getDeviceId method");
                return "357196053112533";
            }
        });
    }

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        XposedBridge.hookAllMethods(Class.forName("android.os.SystemProperties"), "get", new LogXCHook());
        XposedBridge.hookAllMethods(Class.forName("android.os.SystemProperties"), "getString", new LogXCHook());
        XposedBridge.hookAllMethods(Class.forName("android.os.SystemProperties"), "getInt", new LogXCHook());
        XposedBridge.hookAllMethods(Class.forName("android.os.SystemProperties"), "getBoolean", new LogXCHook());
    }
}