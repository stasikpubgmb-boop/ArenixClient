package com.arenix.module;

import java.util.List;

public class ModeSetting extends Setting {
    private String value;
    private List<String> modes;

    public ModeSetting(String defaultValue, List<String> modes) {
        this.value = defaultValue;
        this.modes = modes;
    }

    @Override
    public void setValue(Object value) {
        this.value = (String) value;
    }
}
