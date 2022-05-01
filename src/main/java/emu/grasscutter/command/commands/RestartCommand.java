package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "restart", usage = "restart - ÖØÆô·şÎñ¶Ë")
public final class RestartCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        sender.getSession().close();
    }
}
