package de.lukasp1505.mchristmas.commands.admins;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.lukasp1505.mchristmas.main.Main;

public class MegaPhone implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("eventplugin.admin.megaphone")) {
				if(args.length >= 1) {					
						String message = "";					 
						for(int i = 0; i < args.length; i++)
						message += " " + args[i];
						Bukkit.broadcastMessage("§6§l---------------------------------------------");
						Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage(Main.chatPrefix + "§7Broadcast von " + player.getName());
						Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage("§c" + message);
						Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage("§6§l---------------------------------------------");
					} else
						player.sendMessage(Main.chatPrefix + "§cBitte benutze /m <Nachricht> oder /megaphone <Nachricht>!");
				} else
			player.sendMessage(Main.chatPrefix + "§cDu darfst diesen Befehl nicht ausführen!");
		} else
			sender.sendMessage(Main.chatPrefix + "Dieser Befehl kann nur als Spieler verwendet werden!");
		
		return false;
	}

}
