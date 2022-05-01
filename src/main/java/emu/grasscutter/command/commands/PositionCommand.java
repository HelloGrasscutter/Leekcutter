package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "position", usage = "position", aliases = {"pos"},
        description = "获取玩家坐标")
public final class PositionCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏内执行此指令");
            return;
        }

        sender.dropMessage(String.format("坐标: X：%.3f, Y：%.3f, Z：%.3f\n场景id: %d",
                sender.getPos().getX(), sender.getPos().getY(), sender.getPos().getZ(), sender.getSceneId()));
    }
}
