package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.data.def.AvatarSkillDepotData;
import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.server.packet.send.PacketAvatarSkillChangeNotify;
import emu.grasscutter.server.packet.send.PacketAvatarSkillUpgradeRsp;

import java.util.List;

@Command(label = "talent", usage = "talent <talentID> <value>",
        description = "设置当前角色的天赋等级", permission = "player.settalent")
public final class TalentCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏内执行该指令");
            return;
        }

        if (args.size() < 1){
            CommandHandler.sendMessage(sender, "设定天赋等级: /talent set <talentID> <value>");
            CommandHandler.sendMessage(sender, "另一种设定天赋等级的方法: /talent <n or e or q> <value>");
            CommandHandler.sendMessage(sender, "获取天赋id: /talent getid");
            return;
        }

        String cmdSwitch = args.get(0);
        switch (cmdSwitch) {
            default:
                CommandHandler.sendMessage(sender, "设定天赋等级: /talent set <talentID> <value>");
                CommandHandler.sendMessage(sender, "另一种设定天赋等级的方法: /talent <n or e or q> <value>");
                CommandHandler.sendMessage(sender, "获取天赋id: /talent getid");
            return;
            case "set":
                    try {
                        int skillId = Integer.parseInt(args.get(1));
                        int nextLevel = Integer.parseInt(args.get(2));
                        EntityAvatar entity = sender.getTeamManager().getCurrentAvatarEntity();
                        Avatar avatar = entity.getAvatar(); 
                        int skillIdNorAtk = avatar.getData().getSkillDepot().getSkills().get(0);
                        int skillIdE = avatar.getData().getSkillDepot().getSkills().get(1);
                        int skillIdQ = avatar.getData().getSkillDepot().getEnergySkill();
                        int currentLevelNorAtk = avatar.getSkillLevelMap().get(skillIdNorAtk);
                        int currentLevelE = avatar.getSkillLevelMap().get(skillIdE);
                        int currentLevelQ = avatar.getSkillLevelMap().get(skillIdQ);
                        if (args.size() < 2){
                            CommandHandler.sendMessage(sender, "设置天赋等级: /talent set <talentID> <value>");
                            CommandHandler.sendMessage(sender, "获取天赋id: /talent getid");
                            return;
                        }
                        if (nextLevel > 16){ 
                            CommandHandler.sendMessage(sender, "天赋等级无效，等级应低于16！");
                            return;
                        }
                            if (skillId == skillIdNorAtk){ 
                            // Upgrade skill
                            avatar.getSkillLevelMap().put(skillIdNorAtk, nextLevel);
                            avatar.save();
                
                            // Packet
                            sender.sendPacket(new PacketAvatarSkillChangeNotify(avatar, skillIdNorAtk, currentLevelNorAtk, nextLevel));
                            sender.sendPacket(new PacketAvatarSkillUpgradeRsp(avatar, skillIdNorAtk, currentLevelNorAtk, nextLevel));
                            CommandHandler.sendMessage(sender, "普攻天赋等级设置为 " + nextLevel + ".");
                        }    
                        if (skillId == skillIdE){ 
                            // Upgrade skill
                            avatar.getSkillLevelMap().put(skillIdE, nextLevel);
                            avatar.save();
                
                            // Packet
                            sender.sendPacket(new PacketAvatarSkillChangeNotify(avatar, skillIdE, currentLevelE, nextLevel));
                            sender.sendPacket(new PacketAvatarSkillUpgradeRsp(avatar, skillIdE, currentLevelE, nextLevel));
                            CommandHandler.sendMessage(sender, "E技能天赋等级设置为 " + nextLevel + ".");
                        }
                        if (skillId == skillIdQ){ 
                            // Upgrade skill
                            avatar.getSkillLevelMap().put(skillIdQ, nextLevel);
                            avatar.save();
                
                            // Packet
                            sender.sendPacket(new PacketAvatarSkillChangeNotify(avatar, skillIdQ, currentLevelQ, nextLevel));
                            sender.sendPacket(new PacketAvatarSkillUpgradeRsp(avatar, skillIdQ, currentLevelQ, nextLevel));
                            CommandHandler.sendMessage(sender, "Q技能天赋等级设置为 " + nextLevel + ".");
                        }       
                                
                    } catch (NumberFormatException ignored) {
                        CommandHandler.sendMessage(sender, "无效的技能id");
                        return;
                    }
                
                break;
            case "n": case "e": case "q":
                try {
                    EntityAvatar entity = sender.getTeamManager().getCurrentAvatarEntity();
                    Avatar avatar = entity.getAvatar();
                    AvatarSkillDepotData SkillDepot = avatar.getData().getSkillDepot();
                    int skillId;
                    switch (cmdSwitch) {
                        default:
                            skillId = SkillDepot.getSkills().get(0);
                            break;
                        case "e":
                            skillId = SkillDepot.getSkills().get(1);
                            break;
                        case "q":
                            skillId = SkillDepot.getEnergySkill();
                            break;
                    }
                    int nextLevel = Integer.parseInt(args.get(1));
                    int currentLevel = avatar.getSkillLevelMap().get(skillId);
                    if (args.size() < 1){
                        CommandHandler.sendMessage(sender, "设置天赋等级: /talent <n or e or q> <value>");
                        return;
                    }
                    if (nextLevel > 16){
                        CommandHandler.sendMessage(sender, "天赋等级无效，等级应低于16！");
                        return;
                    }
                    // Upgrade skill
                    avatar.getSkillLevelMap().put(skillId, nextLevel);
                    avatar.save();
                    // Packet
                    sender.sendPacket(new PacketAvatarSkillChangeNotify(avatar, skillId, currentLevel, nextLevel));
                    sender.sendPacket(new PacketAvatarSkillUpgradeRsp(avatar, skillId, currentLevel, nextLevel));
                    CommandHandler.sendMessage(sender, "设置此天赋等级为 " + nextLevel + ".");
                } catch (NumberFormatException ignored) {
                    CommandHandler.sendMessage(sender, "无效的天赋等级");
                    return;
                }
                break;
            case "getid":           
                    EntityAvatar entity = sender.getTeamManager().getCurrentAvatarEntity();
                    Avatar avatar = entity.getAvatar(); 
                    int skillIdNorAtk = avatar.getData().getSkillDepot().getSkills().get(0);
                    int skillIdE = avatar.getData().getSkillDepot().getSkills().get(1);
                    int skillIdQ = avatar.getData().getSkillDepot().getEnergySkill();
                    
                    CommandHandler.sendMessage(sender, "普攻 ID " + skillIdNorAtk + ".");
                    CommandHandler.sendMessage(sender, "E技能 ID " + skillIdE + ".");
                    CommandHandler.sendMessage(sender, "Q技能 ID " + skillIdQ + ".");
                break;
        }
    }
}
