package emu.grasscutter;

import emu.grasscutter.Grasscutter.ServerDebugMode;
import emu.grasscutter.Grasscutter.ServerRunMode;
import emu.grasscutter.game.mail.Mail;

public final class Config {

	public String DatabaseUrl = "mongodb://localhost:27017";
	public String DatabaseCollection = "grasscutter";

	public String RESOURCE_FOLDER = "./resources/";
	public String DATA_FOLDER = "./data/";
	public String PACKETS_FOLDER = "./packets/";
	public String DUMPS_FOLDER = "./dumps/";
	public String KEY_FOLDER = "./keys/";
	public String SCRIPTS_FOLDER = "./resources/Scripts/";
	public String PLUGINS_FOLDER = "./plugins/";

	public ServerDebugMode DebugMode = ServerDebugMode.NONE; // ALL, MISSING, NONE
	public ServerRunMode RunMode = ServerRunMode.HYBRID; // HYBRID, DISPATCH_ONLY, GAME_ONLY
	public GameServerOptions GameServer = new GameServerOptions();
	public DispatchServerOptions DispatchServer = new DispatchServerOptions();

	public GameServerOptions getGameServerOptions() {
		return GameServer;
	}

	public DispatchServerOptions getDispatchOptions() { return DispatchServer; }

	public static class DispatchServerOptions {
		public String Ip = "0.0.0.0";
		public String PublicIp = "127.0.0.1";
		public int Port = 443;
		public int PublicPort = 0;
		public String KeystorePath = "./keystore.p12";
		public String KeystorePassword = "123456";
		public Boolean UseSSL = true;
		public Boolean FrontHTTPS = true;

		public boolean AutomaticallyCreateAccounts = false;
		public String[] defaultPermissions = new String[] { "" };

		public RegionInfo[] GameServers = {};

		public RegionInfo[] getGameServers() {
			return GameServers;
		}

		public static class RegionInfo {
			public String Name = "os_usa";
			public String Title = "Test";
			public String Ip = "127.0.0.1";
			public int Port = 22102;
		}
	}
	
	public static class GameServerOptions {
		public String Name = "Test";
		public String Ip = "0.0.0.0";
		public String PublicIp = "127.0.0.1";
		public int Port = 22102;
		public int PublicPort = 0;

		public String DispatchServerDatabaseUrl = "mongodb://localhost:27017";
		public String DispatchServerDatabaseCollection = "grasscutter";

		public int InventoryLimitWeapon = 2000;
		public int InventoryLimitRelic = 2000;
		public int InventoryLimitMaterial = 2000;
		public int InventoryLimitFurniture = 2000;
		public int InventoryLimitAll = 30000;
		public int MaxAvatarsInTeam = 4;
		public int MaxAvatarsInTeamMultiplayer = 4;
		public int MaxEntityLimit = 1000; // Max entity limit per world. // TODO: Enforce later.
		public boolean WatchGacha = false;
		public String ServerNickname = "Server";
		public int ServerAvatarId = 10000007;
		public int[] WelcomeEmotes = {2007, 1002, 4010};
		public String WelcomeMotd = "欢迎来到Leekcutter！";
		public String WelcomeMailContent = "Hi There!\r\n在一切之前，我想先跟大家说：欢迎来到Leekcutter。Leekcutter是基于Grasscutter构建的Genshin Impact Private Server，同时也将作为Grasscutter的备胎，并会与Grasscutter的代码保持同步。一般来说，你可以将Leekcutter和Grasscutter的关系理解为joyui和miui的关系，因此，如果你遇到了什么问题，Grasscutter的解决方案一般情况下都是可以直接套用到Leekcutter上的，所以Leekcutter也将不提供问题反馈渠道与交流社区，并鼓励用户们加入Grasscutter的社区以代替Leekcutter的交流社区。 \r\n\r\n链接：\r\n<type=\"browser\" text=\"Grasscutter的Discord服务器\" href=\"https://discord.gg/T5vZU6UyeG\"/> <type=\"browser\" text=\"Grasscutter的Github开源页面\" href=\"https://github.com/Grasscutters/Grasscutter\"/> <type=\"browser\" text=\"Leekcutter的Github开源页面\" href=\"https://github.com/Searchstars/Leekcutter\"/>";
		public Mail.MailItem[] WelcomeMailItems = {
				new Mail.MailItem(13509, 1, 1),
				new Mail.MailItem(201, 10000, 1),
		};

		public boolean EnableOfficialShop = true;

		public GameRates Game = new GameRates();

		public GameRates getGameRates() { return Game; }

		public static class GameRates {
			public float ADVENTURE_EXP_RATE = 1.0f;
			public float MORA_RATE = 1.0f;
			public float DOMAIN_DROP_RATE = 1.0f;
		}
	}
}
