package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "checklife", usage = "checklife", aliases = {"cf"},
        description = "Check Server Life.")
public final class FKUCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        sender.dropMessage(String.format("Fu*k u too"));
    }
}
