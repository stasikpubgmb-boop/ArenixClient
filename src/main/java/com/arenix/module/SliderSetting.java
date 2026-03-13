package com.arenix.module;

public class SliderSetting extends Setting {
    private double value;
    private double minValue;
    private double maxValue;

    public SliderSetting(double defaultValue, double min, double max) {
        this.value = defaultValue;
        this.minValue = min;
        this.maxValue = max;
    }

    @Override
    public void setValue(Object value) {
        this.value = (double) value;
    }
}
