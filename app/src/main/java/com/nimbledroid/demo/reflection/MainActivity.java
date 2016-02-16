package com.nimbledroid.demo.reflection;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    public static class DummyItem {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        getFields();
        end = System.currentTimeMillis();
        Log.d(MainActivity.class.getSimpleName(), "getFields: " + (end - start));

        start = System.currentTimeMillis();
        getDeclaredFields();
        end = System.currentTimeMillis();
        Log.d(MainActivity.class.getSimpleName(), "getDeclaredFields: " + (end - start));

        start = System.currentTimeMillis();
        getGenericInterfaces();
        end = System.currentTimeMillis();
        Log.d(MainActivity.class.getSimpleName(), "getGenericInterfaces: " + (end - start));

        start = System.currentTimeMillis();
        getGenericSuperclass();
        end = System.currentTimeMillis();
        Log.d(MainActivity.class.getSimpleName(), "getGenericSuperclass: " + (end - start));

        start = System.currentTimeMillis();
        makeAccessible();
        end = System.currentTimeMillis();
        Log.d(MainActivity.class.getSimpleName(), "makeAccessible: " + (end - start));

        start = System.currentTimeMillis();
        getObject();
        end = System.currentTimeMillis();
        Log.d(MainActivity.class.getSimpleName(), "getObject: " + (end - start));

        start = System.currentTimeMillis();
        setObject();
        end = System.currentTimeMillis();
        Log.d(MainActivity.class.getSimpleName(), "setObject: " + (end - start));

        start = System.currentTimeMillis();
        createDummyItems();
        end = System.currentTimeMillis();
        Log.d(MainActivity.class.getSimpleName(), "createDummyItems: " + (end - start));

        start = System.currentTimeMillis();
        createDummyItemsWithReflection();
        end = System.currentTimeMillis();
        Log.d(MainActivity.class.getSimpleName(), "createDummyItemsWithReflection: " + (end - start));
    }

    public void getFields() {
        Class<?> clazz = android.app.Activity.class;
        for (int i = 0; i < 10_000; i++) {
            clazz.getFields();
        }
    }

    public void getDeclaredFields() {
        Class<?> clazz = android.app.Activity.class;
        for (int i = 0; i < 10_000; i++) {
            clazz.getDeclaredFields();
        }
    }

    public void getGenericInterfaces() {
        Class<?> clazz = android.app.Activity.class;
        for (int i = 0; i < 10_000; i++) {
            clazz.getGenericInterfaces();
        }
    }

    public void getGenericSuperclass() {
        Class<?> clazz = android.app.Activity.class;
        for (int i = 0; i < 10_000; i++) {
            clazz.getGenericSuperclass();
        }
    }

    public void makeAccessible() {
        Class<?> clazz = android.app.Activity.class;
        try {
            for (int i = 0; i < 10_000; i++) {
                Field f = clazz.getDeclaredField("mApplication");
                f.setAccessible(true);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void getObject() {
        Class<?> clazz = android.app.Activity.class;
        try {
            for (int i = 0; i < 10_000; i++) {
                Field f = clazz.getDeclaredField("mApplication");
                f.setAccessible(true);
                Application application = (Application) f.get(this);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setObject() {
        String newTitle = "Reflection";

        Class<?> clazz = android.app.Activity.class;
        try {
            for (int i = 0; i < 10_000; i++) {
                Field f = clazz.getDeclaredField("mTitle");
                f.setAccessible(true);
                f.set(this, newTitle);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void createDummyItems() {
        for (int i = 0; i < 1_000_000; i++) {
            new DummyItem();
        }
    }

    private void createDummyItemsWithReflection() {
        try {
            for (int i = 0; i < 1_000_000; i++) {
                DummyItem.class.newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
