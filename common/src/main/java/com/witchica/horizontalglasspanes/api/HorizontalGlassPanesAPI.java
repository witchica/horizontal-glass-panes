package com.witchica.horizontalglasspanes.api;

import java.lang.reflect.InvocationTargetException;

public class HorizontalGlassPanesAPI {

    public static final String MOD_ID = "horizontalglasspanes";

    private static final InternalMethods __internalMethods;

    static {
        try {
            __internalMethods = (InternalMethods) Class.forName("com.witchica.horizontalglasspanes.InternalMethodsImpl").getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
