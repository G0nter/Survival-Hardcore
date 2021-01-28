package net.narwell.survivalhc.command;

import net.narwell.survivalhc.Survival;
import net.narwell.survivalhc.configuration.FileCreator;
import net.narwell.survivalhc.game.menu.ArenaCreatorGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SurvivalCommand implements CommandExecutor {

    private final Survival main;

    public SurvivalCommand(final Survival main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("No");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("Usa cualquiera de los comandos");
            return true;
        }


        Player player = (Player)sender;

        switch(args[0].toLowerCase()) {
            case "create":

                if (args.length == 1) {
                    sender.sendMessage("usa /shc create [solo/team]");
                    return true;
                }

                switch (args[1].toLowerCase()) {

                    case "solo":

                        ArenaCreatorGui arenaCreatorGui = new ArenaCreatorGui(
                                "Solo");
                        arenaCreatorGui.openGui(player);

                        return false;

                    case "team":
                        sender.sendMessage("OK.");

                        return false;
                }

                return false;
        }


        return false;
    }
}
