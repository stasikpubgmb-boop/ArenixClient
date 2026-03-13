package com.arenix.client;

import com.arenix.client.event.EventBus;
import com.arenix.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ArenixClient implements ClientModInitializer {

    public static final String MOD_ID = "arenix";
    public static ArenixClient INSTANCE;

    private KeyBinding moduleKeybind;
    private ModuleManager moduleManager;
    private EventBus eventBus;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;

        moduleManager = new ModuleManager();
        eventBus = new EventBus();

        moduleKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.arenix.toggle_menu", // The translation key of the keybinding
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT, // The default key
                "category.arenix.main" // The translation key of the keybinding's category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (moduleKeybind.wasPressed()) {
                moduleManager.getModules().forEach(Module::toggle);
                moduleKeybind.setPressed(false);
            }
        });

        System.out.println("Arenix Client Initialized!");
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
