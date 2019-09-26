package com.xiao.powermock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PowerMockClass {

    public static void void_static() {
        throw new NullPointerException("void_static");
    }

    public static int static_method() {
        return 1;
    }

    public static void static_arg(int i) {
        throw new NullPointerException("static_arg");
    }

    private int private_method(int i) {
        return i;
    }

    public final int final_method(int i) {
        return i;
    }

    public int method(int i) {
        return i;
    }

    public void new_object() throws IOException {
        File file = new File("123");
        try {
            FileInputStream in = new FileInputStream(file);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
