package com.umesh.datamodel;


import com.umesh.Main;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.List;


public class Fonts {

    public Font FRL1;

    private static final Fonts OUR_INSTANCE = new Fonts();

    public static Fonts getInstance() {
        return OUR_INSTANCE;
    }

    public void load() {

        List<String> strings = Arrays.asList("APP6A02", "APP6A16", "APP6A04", "APP6A18", "APP6A03", "APP6A17", "APP6A01", "APP6A15");
        for (String s : strings) {
            Font.loadFont(Main.class.getClassLoader().getResourceAsStream(s+".TTF"), 150);

        }

    }
}