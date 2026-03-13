package com.arenix;

import com.arenix.event.EventBus;
import com.arenix.module.ModuleManager;
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

        // Initialize EventBus
        eventBus = new EventBus();

        // Initialize ModuleManager
        moduleManager = new ModuleManager();

        // Initialize Keybinding
        moduleKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.arenix.toggle_menu", // The translation key of the keybinding
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT, // The default key
                "category.arenix.main" // The translation key of the keybinding's category
        ));

        // Register event for keybinding
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (moduleKeybind.wasPressed()) {
                // Toggle the UI or handle module activation/deactivation
                // For now, let's print a message
                System.out.println("Module key pressed!");
                // You would typically open a GUI here or manage modules.
                if (moduleManager != null) {
                    moduleManager.toggleModuleList(); // Example method call
                }
            }
            // Tick the module manager to process module ticks
            if (moduleManager != null) {
                moduleManager.onClientTick();
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
