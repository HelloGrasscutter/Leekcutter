package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.database.DatabaseHelper;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.utils.FileUtils;

import java.io.*;
import java.util.*;

@Command(label = "account", usage = "account <create|delete> <username> [uid]", description = "修改用户账号")
public final class AccountCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender != null) {
            CommandHandler.sendMessage(sender, "这个指令只能在控制台上运行");
            return;
        }

        if (args.size() < 2) {
            CommandHandler.sendMessage(null, "用法: account <create|delete> <username> [uid]");
            return;
        }

        String action = args.get(0);
        String username = args.get(1);

        switch (action) {
            default:
                CommandHandler.sendMessage(null, "用法: account <create|delete> <username> [uid]");
                return;
            case "create":
                int uid = 0;
                if (args.size() > 2) {
                    try {
                        uid = Integer.parseInt(args.get(2));
                    } catch (NumberFormatException ignored) {
                        CommandHandler.sendMessage(null, "UID无效");
                        return;
                    }
                }

                emu.grasscutter.game.Account account = DatabaseHelper.createAccountWithId(username, uid);
                if (account == null) {
                    CommandHandler.sendMessage(null, "请不要尝试创建一个已存在的账号");
                    return;
                } else {
                    account.addPermission("*");
                    account.save(); // Save account to database.
                    File new_account_file_command = new File("auth/passwords/" + username + ".leekpassword");
                    try {
                        new_account_file_command.createNewFile();
                    } catch (IOException e) {
                        Grasscutter.getLogger().info(e.getMessage());
                    }
                    Grasscutter.getLogger().info(String.format("创建并向%s写入文件",new String("auth/passwords/" + username + ".leekpassword")));
                    FileUtils.write("auth/passwords/" + username + ".leekpassword", "123".getBytes());

                    CommandHandler.sendMessage(null, "账号已创建，uid为：" + account.getPlayerUid() + "密码为123");
                }
                return;
            case "delete":
                if (DatabaseHelper.deleteAccount(username)) {
                    CommandHandler.sendMessage(null, "账号删除成功");
                } else {
                    CommandHandler.sendMessage(null, "找不到该账号");
                }
        }
    }
}
