package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.utils.Position;

import java.util.List;

@Command(label = "tpall", usage = "tpall",
        description = "传送多人世界中所有的玩家到自身地点", permission = "player.tpall")
public final class TeleportAllCommand implements CommandHandler {
    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏内执行此指令");
            return;
        }
        
        if (!sender.getWorld().isMultiplayer()) {
            CommandHandler.sendMessage(sender, "你只能在多人游戏中使用此指令");
            return;
        }
        
        for (Player player : sender.getWorld().getPlayers()) {
            if (player.equals(sender))
                continue;
            Position pos = sender.getPos();

            player.getWorld().transferPlayerToScene(player, sender.getSceneId(), pos);
        }
    }
}
