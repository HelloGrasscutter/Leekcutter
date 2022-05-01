package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "godmode", usage = "godmode [playerId]",
        description = "开纪让你无敌", permission = "player.godmode")
public final class GodModeCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏内执行该指令");
            return; // TODO: toggle player's godmode statue from console or other players
        }

        int target;
        if (args.size() == 1) {
            try {
                target = Integer.parseInt(args.get(0));
                if (Grasscutter.getGameServer().getPlayerByUid(target) == null) {
                    target = sender.getUid();
                }
            } catch (NumberFormatException e) {
                CommandHandler.sendMessage(sender, "无效的玩家id");
                return;
            }
        } else {
            target = sender.getUid();
        }
        Player targetPlayer = Grasscutter.getGameServer().getPlayerByUid(target);
        if (targetPlayer == null) {
            CommandHandler.sendMessage(sender, "玩家都找不到还开nm纪");
            return;
        }

        targetPlayer.setGodmode(!targetPlayer.inGodmode());
        sender.dropMessage("纪现在已经" + (targetPlayer.inGodmode() ? "开启，" : "关闭，") +
                "只为我们的纪狗 " + targetPlayer.getNickname() + " 。");
    }
}
