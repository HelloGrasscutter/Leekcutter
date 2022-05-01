package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.PlayerProperty;

import java.util.List;

@Command(label = "setworldlevel", usage = "setworldlevel <level>",
        description = "设置世界等级(重新登录即可生效)",
        aliases = {"setworldlvl"}, permission = "player.setworldlevel")
public final class SetWorldLevelCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏内执行该指令");
            return; // TODO: set player's world level from console or other players
        }

        if (args.size() < 1) {
            CommandHandler.sendMessage(sender, "用法: setworldlevel <level>");
            return;
        }

        try {
            int level = Integer.parseInt(args.get(0));

            // Set in both world and player props
            sender.getWorld().setWorldLevel(level);
            sender.setWorldLevel(level);

            sender.dropMessage("世界等级已设定为 " + level + ".");
        } catch (NumberFormatException ignored) {
            CommandHandler.sendMessage(null, "无效的世界等级");
        }
    }
}
