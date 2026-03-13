package com.arenix.module;

public class BooleanSetting extends Setting {
    private boolean value;

    public BooleanSetting(boolean defaultValue) {
        this.value = defaultValue;
    }

    @Override
    public void setValue(Object value) {
        this.value = (boolean) value;
    }
}
