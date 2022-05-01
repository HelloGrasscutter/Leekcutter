package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "resetshop", usage = "resetshop",
        description = "重置目标玩家的商店刷新时间", permission = "server.resetshop")
public final class ResetShopLimitCommand implements CommandHandler {
    @Override
    public void execute(Player sender, List<String> args) {
        if (args.size() < 1) {
            CommandHandler.sendMessage(sender,"用法: /resetshop <player id>");
            return;
        }

        int target = Integer.parseInt(args.get(0));
        Player targetPlayer = Grasscutter.getGameServer().getPlayerByUid(target);
        if (targetPlayer == null) {
            CommandHandler.sendMessage(sender, "查无此人");
            return;
        }

        targetPlayer.getShopLimit().forEach(x -> x.setNextRefreshTime(0));
        targetPlayer.save();
        CommandHandler.sendMessage(sender, "完毕");
    }
}
