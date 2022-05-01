package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.Account;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "permission", usage = "permission <add|remove> <username> <permission>",
        description = "添加或移除玩家的权限", permission = "*")
public final class PermissionCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (args.size() < 3) {
            CommandHandler.sendMessage(sender, "用法: permission <add|remove> <username> <permission>");
            return;
        }

        String action = args.get(0);
        String username = args.get(1);
        String permission = args.get(2);

        Account account = Grasscutter.getGameServer().getAccountByName(username);
        if (account == null) {
            CommandHandler.sendMessage(sender, "找不到该账号");
            return;
        }

        switch (action) {
            default:
                CommandHandler.sendMessage(sender, "用法: permission <add|remove> <username> <permission>");
                break;
            case "add":
                if (account.addPermission(permission)) {
                    CommandHandler.sendMessage(sender, "权限已添加，这可必须是你信任的人呐！");
                } else CommandHandler.sendMessage(sender, "他已经有这个权限了诶");
                break;
            case "remove":
                if (account.removePermission(permission)) {
                    CommandHandler.sendMessage(sender, "权限删除成功，是不是在清理带恶人？");
                } else CommandHandler.sendMessage(sender, "啊这...这个玩家本来就没有此权限好吧");
                break;
        }

        account.save();
    }
}