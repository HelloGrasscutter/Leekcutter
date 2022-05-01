package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "stop", usage = "stop",
        description = "关闭服务端", permission = "server.stop")
public final class StopCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        CommandHandler.sendMessage(null, "服务器关闭中...");
        for (Player p : Grasscutter.getGameServer().getPlayers().values()) {
            CommandHandler.sendMessage(p, "服务器关闭中...");
        }

        System.exit(1);
    }
}
