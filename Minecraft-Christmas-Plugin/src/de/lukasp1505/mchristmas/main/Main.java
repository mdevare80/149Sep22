package de.lukasp1505.mchristmas.main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	public static String chatPrefix = "�7[�fMinecraft Christmas�7]�f ";
	
	public void onEnable() {
		plugin = this;
		PluginManager pluginManager = Bukkit.getPluginManager();
		
		//Commands.Admins
		getCommand("eventmenu").setExecutor(new de.lukasp1505.mchristmas.commands.admins.EventMenu());
		getCommand("resetevent").setExecutor(new de.lukasp1505.mchristmas.commands.admins.ResetEvent());
		getCommand("megaphone").setExecutor(new de.lukasp1505.mchristmas.commands.admins.MegaPhone());


		//Commands.Global
		getCommand("event").setExecutor(new de.lukasp1505.mchristmas.commands.global.Event());
		getCommand("info").setExecutor(new de.lukasp1505.mchristmas.commands.global.Info());
		getCommand("sendpos").setExecutor(new de.lukasp1505.mchristmas.commands.global.SendPos());

		
		//Listeners
		pluginManager.registerEvents(new de.lukasp1505.mchristmas.engines.ElytraEngine(), this);
		
	}
	
	public static Main getPlugin() {
		return plugin;
	}

//after disable NOTE: the after disable will onli work once the event is over and prices are distributed :P
public void onDisable()
{
plugin=this;
PluginManager pluginManager = Bukkit.getPluginManager();

//Commands.Admins
getCommand("eventmenu").setExecutor(new de.lukasp1505.mchristmas.commands.admins.EventMenu());
getCommand("resetevent").setExecutor(new de.lukasp1505.mchristmas.commands.admins.ResetEvent());
getCommand("megaphone").setExecutor(new de.lukasp1505.mchristmas.commands.admins.MegaPhone());

//Commands.Global
getCommand("event").setExecutor(new de.lukasp1505.mchristmas.commands.global.Notallowed());
getCommand("info").setExecutor(new de.lukasp1505.mchristmas.commands.global.Notallowed());
getCommand("sendpos").setExecutor(new de.lukasp1505.mchristmas.commands.global.Notallowed());

//lookers
              
             pluginManager.registerEvents(new de.lukasp1505.mchristmas.engines.ElytraEngine(), this);
			 pluginManager.registerEvents(new de.lukasp1505.mchristmas.engines.Ttsallow(), this);
			 pluginManager.registerEvents(new de.lukasp1505.mchristmas.engines.Pweather(), this);
			 pluginManager.registerEvents(new de.lukasp1505.mchristmas.engines.Ptime(), this);
			 pluginManager.registerEvents(new de.lukasp1505.mchristmas.engines.Daily_quest_Scroll(), this);
}
}