package fr.pinguet62.jcar.util;

public final class LogUtils {

    public static String currentMethod() {
        StackTraceElement cause = Thread.currentThread().getStackTrace()[2];
        return cause.getClassName() + "#" + cause.getMethodName();
    }

}
