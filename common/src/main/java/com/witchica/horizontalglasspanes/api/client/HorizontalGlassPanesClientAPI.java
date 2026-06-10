package com.witchica.horizontalglasspanes.api.client;

import java.lang.reflect.InvocationTargetException;

public class HorizontalGlassPanesClientAPI {

    private static final InternalClientMethods __internalMethods;

    static {
        try {
            __internalMethods = (InternalClientMethods) Class.forName("com.witchica.horizontalglasspanes.client.InternalClientMethodsImpl").getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
