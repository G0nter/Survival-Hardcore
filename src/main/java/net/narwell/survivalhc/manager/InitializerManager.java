package net.narwell.survivalhc.manager;

import net.narwell.survivalhc.Survival;
import net.narwell.survivalhc.command.SurvivalCommand;
import net.narwell.survivalhc.manager.hooks.PlaceholdersHook;

public class InitializerManager {

    public InitializerManager(final Survival main) {
        initializerHook();
        initializerCommand(main);
    }

    private void initializerHook() {
        new PlaceholdersHook().register();
    }

    private void initializerCommand(final Survival main) {
        main.getCommand("survival").setExecutor(new SurvivalCommand(main));
    }
}
