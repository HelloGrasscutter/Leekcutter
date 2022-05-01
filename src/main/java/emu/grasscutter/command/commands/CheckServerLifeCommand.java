package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "checklife", usage = "checklife", aliases = {"cf"},
        description = "Check Server Life.")
public final class CheckServerLifeCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "Please run this command in-game.");
            return;
        }

        sender.dropMessage(String.format("Life."));
    }
}
