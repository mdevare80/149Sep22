package de.lukasp1505.mchristmas.commands.global;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.lukasp1505.mchristmas.main.Main;

public class Info implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 0) {
				player.sendMessage("§6========== §bPLUGIN INFO§6 ==========");
				player.sendMessage("§6");
				player.sendMessage("§6=> Author: §bLukasP1505");
				player.sendMessage("§6=> Help: §b/help");
				player.sendMessage("§6=> Bug-Report: §bDiscord");
				player.sendMessage("§6=> §bDieses Plugin benutzt Funktionen vom Event Plugin von LukasP1505");
				player.sendMessage("§6");
				player.sendMessage("§6=> §bhttps://github.com/lukasp1505/Minecraft-Christmas-Plugin");
				player.sendMessage("§6");

			} else
				player.sendMessage(Main.chatPrefix + "§cBenutze /info !");
		} else
			sender.sendMessage(Main.chatPrefix + "Dieser Befehl kann nur als Spieler verwendet werden!");
		
		return false;
	}

}