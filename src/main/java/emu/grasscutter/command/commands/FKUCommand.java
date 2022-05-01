package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "Fuck You", usage = "Fuck The Server", aliases = {"cf"},
        description = "Look the usage lol")
public final class FKUCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        sender.dropMessage(String.format("Fuck u too"));
        sender.dropMessage(String.format("yourmom"));
    }
}
