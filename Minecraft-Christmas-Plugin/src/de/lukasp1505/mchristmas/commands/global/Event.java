package de.lukasp1505.mchristmas.commands.global;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.lukasp1505.mchristmas.commands.admins.EventMenu;
import de.lukasp1505.mchristmas.main.Main;

public class Event implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 0) {
				if(EventMenu.event_activ == true) {
				player.setGameMode(GameMode.SURVIVAL);
                player.teleport(EventMenu.eventLocation);
				player.sendMessage(Main.chatPrefix + "§2Du hast dich erfolgreich zum Event teleportiert!");
				player.setHealth(20);
				player.setFoodLevel(20);
				} else
					player.sendMessage(Main.chatPrefix + "§cEs ist kein Event aktiv!");
				} else
					player.sendMessage(Main.chatPrefix + "§cBenutze /event !");
		} else
			sender.sendMessage(Main.chatPrefix + "Dieser Befehl kann nur als Spieler verwendet werden!");
		
		return false;
	}

}