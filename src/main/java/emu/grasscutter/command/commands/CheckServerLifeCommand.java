package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "checklife", usage = "checklife", aliases = {"cf"},
        description = "确认服务器是否还在运行")
public final class CheckServerLifeCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏中执行该指令");
            return;
        }

        sender.dropMessage(String.format("爷还活着捏！"));
    }
}
