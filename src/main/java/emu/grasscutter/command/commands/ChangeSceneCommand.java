package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "changescene", usage = "changescene <scene id>",
        description = "切换你的场景（Scene）", aliases = {"scene"}, permission = "player.changescene")
public final class ChangeSceneCommand implements CommandHandler {
    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏里执行该指令");
            return;
        }

        if (args.size() < 1) {
            CommandHandler.sendMessage(sender, "用法: changescene <scene id>");
            return;
        }

        try {
            int sceneId = Integer.parseInt(args.get(0));
            
            if (sceneId == sender.getSceneId()) {
            	CommandHandler.sendMessage(sender, "你本身已经在此场景里辣！");
            	return;
            }
            
            boolean result = sender.getWorld().transferPlayerToScene(sender, sceneId, sender.getPos());
            CommandHandler.sendMessage(sender, "尝试切换至场景： " + sceneId);
            
            if (!result) {
                CommandHandler.sendMessage(sender, "找不到此场景");
            }
        } catch (Exception e) {
            CommandHandler.sendMessage(sender, "报错了！错误信息：" + e + "记住，用法是: changescene <scene id>");
        }
    }
}
