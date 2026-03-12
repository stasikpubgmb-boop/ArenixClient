package com.arenix.module.modules;

import com.arenix.module.Category;
import com.arenix.module.Module;

public class ExampleModule extends Module {

    public ExampleModule() {
        super("Example", Category.MISC);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        System.out.println(getName() + " module enabled!");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        System.out.println(getName() + " module disabled!");
    }

    @Override
    public void onTick() {
        super.onTick();
        // Example: print something to console every tick when enabled
        // System.out.println("ExampleModule ticking...");
    }
}
