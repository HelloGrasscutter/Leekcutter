package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "resetconst", usage = "resetconst [all]",
        description = "重置当前角色的命座,重新登录即可生效",
        aliases = {"resetconstellation"}, permission = "player.resetconstellation")
public final class ResetConstCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏内执行此指令");
            return;
        }

        if (args.size() > 0 && args.get(0).equalsIgnoreCase("all")) {
            sender.getAvatars().forEach(this::resetConstellation);
            sender.dropMessage("重置所有角色的命座");
        } else {
            EntityAvatar entity = sender.getTeamManager().getCurrentAvatarEntity();
            if (entity == null) {
                return;
            }

            Avatar avatar = entity.getAvatar();
            this.resetConstellation(avatar);

            sender.dropMessage(avatar.getAvatarData().getName() + "的命座已重置。重新登陆以查看结果");
        }
    }

    private void resetConstellation(Avatar avatar) {
        avatar.getTalentIdList().clear();
        avatar.setCoreProudSkillLevel(0);
        avatar.recalcStats();
        avatar.save();
    }
}