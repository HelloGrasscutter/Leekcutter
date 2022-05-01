package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "kick", usage = "kick <player>",
        description = "从服务器中踢出指定玩家 (WIP)", permission = "server.kick")
public final class KickCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        int target = Integer.parseInt(args.get(0));

        Player targetPlayer = Grasscutter.getGameServer().getPlayerByUid(target);
        if (targetPlayer == null) {
            CommandHandler.sendMessage(sender, "查无此人，神权失败");
            return;
        }

        if (sender != null) {
            CommandHandler.sendMessage(sender, String.format("玩家 [%s:%s] 踢出了玩家 [%s:%s]", sender.getAccount().getPlayerUid(), sender.getAccount().getUsername(), target, targetPlayer.getAccount().getUsername()));
        }
        CommandHandler.sendMessage(sender, String.format("正在踢出玩家 [%s:%s]", target, targetPlayer.getAccount().getUsername()));

        targetPlayer.getSession().close();
    }
}
