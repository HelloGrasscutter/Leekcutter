package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.ClimateType;
import emu.grasscutter.server.packet.send.PacketSceneAreaWeatherNotify;

import java.util.List;

@Command(label = "weather", usage = "weather <weatherId> [climateId]",
        description = "改变weather", aliases = {"w"}, permission = "player.weather")
public final class WeatherCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "请在游戏内执行此指令");
            return;
        }

        if (args.size() < 1) {
            CommandHandler.sendMessage(sender, "用法: weather <weatherId> [climateId]");
            return;
        }

        try {
            int weatherId = Integer.parseInt(args.get(0));
            int climateId = args.size() > 1 ? Integer.parseInt(args.get(1)) : 1;

            ClimateType climate = ClimateType.getTypeByValue(climateId);

            sender.getScene().setWeather(weatherId);
            sender.getScene().setClimate(climate);
            sender.getScene().broadcastPacket(new PacketSceneAreaWeatherNotify(sender));
            CommandHandler.sendMessage(sender, "已将天气修改为 " + weatherId + " 并将气候修改为 " + climateId);
        } catch (NumberFormatException ignored) {
            CommandHandler.sendMessage(sender, "无效的ID");
        }
    }
}
