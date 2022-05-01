package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.def.ItemData;
import emu.grasscutter.game.entity.EntityItem;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.utils.Position;

import java.util.List;

@Command(label = "drop", usage = "drop <itemId|itemName> [amount]",
        description = "在玩家附件掉落你指定的物品", aliases = {"d", "dropitem"}, permission = "server.drop")
public final class DropCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏中执行该指令");
            return;
        }

        if (args.size() < 1) {
            CommandHandler.sendMessage(sender, "用法: drop <itemId|itemName> [amount]");
            return;
        }

        try {
            int item = Integer.parseInt(args.get(0));
            int amount = 1;
            if (args.size() > 1) amount = Integer.parseInt(args.get(1));

            ItemData itemData = GameData.getItemDataMap().get(item);
            if (itemData == null) {
                CommandHandler.sendMessage(sender, "无效的物品id，你或许需要一份HandBook？");
                return;
            }

            if (itemData.isEquip()) {
                float range = (5f + (.1f * amount));
                for (int i = 0; i < amount; i++) {
                    Position pos = sender.getPos().clone().addX((float) (Math.random() * range) - (range / 2)).addY(3f).addZ((float) (Math.random() * range) - (range / 2));
                    EntityItem entity = new EntityItem(sender.getScene(), sender, itemData, pos, 1);
                    sender.getScene().addEntity(entity);
                }
            } else {
                EntityItem entity = new EntityItem(sender.getScene(), sender, itemData, sender.getPos().clone().addY(3f), amount);
                sender.getScene().addEntity(entity);
            }
            CommandHandler.sendMessage(sender, String.format("已掉落了 %s 个 %s", amount, item));
        } catch (NumberFormatException ignored) {
            CommandHandler.sendMessage(sender, "无效的物品或玩家id");
        }
    }
}