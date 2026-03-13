package com.arenix.module;

import com.arenix.module.modules.ExampleModule;
import com.arenix.client.module.impl.combat.Scaffold;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();
    private boolean moduleListVisible = false; // Controls the visibility of the module list UI

    public ModuleManager() {
        // Register your modules here
        registerModule(new ExampleModule());
        registerModule(new Scaffold());
        // Add more modules as you create them
    }

    private void registerModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getModulesByCategory(Category category) {
        List<Module> modulesInCategory = new ArrayList<>();
        for (Module module : modules) {
            if (module.getCategory() == category) {
                modulesInCategory.add(module);
            }
        }
        return modulesInCategory;
    }

    public Module getModuleByName(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }

    public void toggleModule(String moduleName) {
        Module module = getModuleByName(moduleName);
        if (module != null) {
            module.setEnabled(!module.isEnabled());
        }
    }

    public void toggleModuleList() {
        this.moduleListVisible = !this.moduleListVisible;
        System.out.println("Module list visibility toggled: " + this.moduleListVisible);
        // In a real mod, you would open or close your GUI here
        // For example:
        // if (this.moduleListVisible) {
        //     MinecraftClient.getInstance().setScreen(new ModuleListScreen());
        // } else {
        //     MinecraftClient.getInstance().setScreen(null);
        // }
    }

    public boolean isModuleListVisible() {
        return moduleListVisible;
    }

    public void onClientTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }

    public void onRenderTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onRenderTick();
            }
        }
    }
}
