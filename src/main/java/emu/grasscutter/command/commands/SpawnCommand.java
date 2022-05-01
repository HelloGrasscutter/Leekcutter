package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.def.MonsterData;
import emu.grasscutter.game.entity.EntityMonster;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.utils.Position;

import java.util.List;

@Command(label = "spawn", usage = "spawn <entityId|entityName> [level] [amount]",
        description = "在你周围生成实体", permission = "server.spawn")
public final class SpawnCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏内执行此指令");
            return;
        }

        if (args.size() < 1) {
            CommandHandler.sendMessage(sender, "用法: spawn <entityId|entityName> [amount]");
            return;
        }

        try {
            int entity = Integer.parseInt(args.get(0));
            int level = args.size() > 1 ? Integer.parseInt(args.get(1)) : 1;
            int amount = args.size() > 2 ? Integer.parseInt(args.get(2)) : 1;

            MonsterData entityData = GameData.getMonsterDataMap().get(entity);
            if (entityData == null) {
                CommandHandler.sendMessage(sender, "无效的实体id");
                return;
            }

            float range = (5f + (.1f * amount));
            for (int i = 0; i < amount; i++) {
                Position pos = sender.getPos().clone().addX((float) (Math.random() * range) - (range / 2)).addY(3f).addZ((float) (Math.random() * range) - (range / 2));
                EntityMonster monster = new EntityMonster(sender.getScene(), entityData, pos, level);
                sender.getScene().addEntity(monster);
            }
            CommandHandler.sendMessage(sender, String.format("已生成 %s 个 %s.", amount, entity));
        } catch (NumberFormatException ignored) {
            CommandHandler.sendMessage(sender, "无效的物品或玩家id");
        }
    }
}
