package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.inventory.Inventory;
import emu.grasscutter.game.inventory.ItemType;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "clear", usage = "clear <all|wp|art|mat>", //Merged /clearartifacts and /clearweapons to /clear <args> [uid]
        description = "删除所有未装备及未解锁的圣遗物(art)或武器(wp)或材料(mat)或者所有(all)，包括五星！！！",
        aliases = {"clear"}, permission = "player.clearinv")

public final class ClearCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        int target;
        String cmdSwitch = "";
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏内执行该指令");
            return;
        }
        Inventory playerInventory = sender.getInventory();
        try {
            if (args.size() == 1) {
                cmdSwitch = args.get(0);
                target = sender.getUid();
            }else {
                cmdSwitch = args.get(1);
                target = Integer.parseInt(args.get(0));
            }
            Player targetPlayer = Grasscutter.getGameServer().getPlayerByUid(target);
            switch (cmdSwitch) {
                case "wp" -> {
                    playerInventory.getItems().values().stream()
                            .filter(item -> item.getItemType() == ItemType.ITEM_WEAPON)
                            .filter(item -> !item.isLocked() && !item.isEquipped())
                            .forEach(item -> playerInventory.removeItem(item, item.getCount()));
                    sender.dropMessage("清理了此角色的武器：" + targetPlayer.getNickname());
                }
                case "art" -> {
                    playerInventory.getItems().values().stream()
                            .filter(item -> item.getItemType() == ItemType.ITEM_RELIQUARY)
                            .filter(item -> item.getLevel() == 1 && item.getExp() == 0)
                            .filter(item -> !item.isLocked() && !item.isEquipped())
                            .forEach(item -> playerInventory.removeItem(item, item.getCount()));
                    sender.dropMessage("清理了此角色圣遗物：" + targetPlayer.getNickname());
                }
                case "mat" -> {
                    playerInventory.getItems().values().stream()
                            .filter(item -> item.getItemType() == ItemType.ITEM_MATERIAL)
                            .filter(item -> item.getLevel() == 1 && item.getExp() == 0)
                            .filter(item -> !item.isLocked() && !item.isEquipped())
                            .forEach(item -> playerInventory.removeItem(item, item.getCount()));
                    sender.dropMessage("清理了此角色的材料：" + targetPlayer.getNickname());
                }
                case "all" -> {
                    playerInventory.getItems().values().stream()
                            .filter(item1 -> item1.getItemType() == ItemType.ITEM_RELIQUARY)
                            .filter(item1 -> item1.getLevel() == 1 && item1.getExp() == 0)
                            .filter(item1 -> !item1.isLocked() && !item1.isEquipped())
                            .forEach(item1 -> playerInventory.removeItem(item1, item1.getCount()));
                    sender.dropMessage("清理了该玩家的圣遗物：" + targetPlayer.getNickname() + " .");
                    playerInventory.getItems().values().stream()
                            .filter(item2 -> item2.getItemType() == ItemType.ITEM_MATERIAL)
                            .filter(item2 -> !item2.isLocked() && !item2.isEquipped())
                            .forEach(item2 -> playerInventory.removeItem(item2, item2.getCount()));
                    sender.dropMessage("清理了该玩家的材料：" + targetPlayer.getNickname() + " .");
                    playerInventory.getItems().values().stream()
                            .filter(item3 -> item3.getItemType() == ItemType.ITEM_WEAPON)
                            .filter(item3 -> item3.getLevel() == 1 && item3.getExp() == 0)
                            .filter(item3 -> !item3.isLocked() && !item3.isEquipped())
                            .forEach(item3 -> playerInventory.removeItem(item3, item3.getCount()));
                    sender.dropMessage("清理了该玩家的武器：" + targetPlayer.getNickname() + " .");
                    playerInventory.getItems().values().stream()
                            .filter(item4 -> item4.getItemType() == ItemType.ITEM_FURNITURE)
                            .filter(item4 -> !item4.isLocked() && !item4.isEquipped())
                            .forEach(item4 -> playerInventory.removeItem(item4, item4.getCount()));
                    sender.dropMessage("清理了该玩家的家具：" + targetPlayer.getNickname() + " .");
                    playerInventory.getItems().values().stream()
                            .filter(item5 -> item5.getItemType() == ItemType.ITEM_DISPLAY)
                            .filter(item5 -> !item5.isLocked() && !item5.isEquipped())
                            .forEach(item5 -> playerInventory.removeItem(item5, item5.getCount()));
                    sender.dropMessage("清理了该玩家的Displays：" + targetPlayer.getNickname() + " .");
                    playerInventory.getItems().values().stream()
                            .filter(item6 -> item6.getItemType() == ItemType.ITEM_VIRTUAL)
                            .filter(item6 -> !item6.isLocked() && !item6.isEquipped())
                            .forEach(item6 -> playerInventory.removeItem(item6, item6.getCount()));
                    sender.dropMessage("清理了该玩家的virtuals：" + targetPlayer.getNickname() + " .");
                    sender.dropMessage("清理了该玩家的一切：" + targetPlayer.getNickname() + " .");
                }
            }
        } catch (NumberFormatException ignored) {
            // TODO: Parse from item name using GM Handbook.
            CommandHandler.sendMessage(sender, "无效的玩家ID");
            return;
        }

        Player targetPlayer = Grasscutter.getGameServer().getPlayerByUid(target);
        if (targetPlayer == null) {
            CommandHandler.sendMessage(sender, "这玩家根本不存在好吧，至少他没有活在阳间捏");
            return;
        }
    }
}
