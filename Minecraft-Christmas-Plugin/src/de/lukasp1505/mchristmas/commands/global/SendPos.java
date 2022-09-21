package de.lukasp1505.mchristmas.commands.global;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.lukasp1505.mchristmas.main.Main;

public class SendPos implements CommandExecutor {

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	   
	   if(sender instanceof Player) {
		   Player player = ((Player) sender);
		   
		   if(args.length == 1) {
				   Player target = Bukkit.getPlayer(args[0]);
				   
				   if(Bukkit.getOnlinePlayers().contains(target)) {
					   if((target.getName().equals(player.getName()))) {
						   target.sendMessage(Main.chatPrefix + "§6" + player.getName() + " hat dir seinen Standort gesendet: §b" + Math.round(player.getLocation().getX()) + "§7, §b" + Math.round(player.getLocation().getY()) + "§7, §b" + Math.round(player.getLocation().getZ()));
			                 
			               player.sendMessage(Main.chatPrefix + "§6Du hast deinen Standort an §b" + target.getName() + " §6gesendet!");
					   } else
						   target.sendMessage(Main.chatPrefix + "§cDer Empfänger ist nicht zulässig!");
				   } else
					   target.sendMessage(Main.chatPrefix + "§cDer Empfänger ist nicht online!");
			   } else
				   player.sendMessage(Main.chatPrefix + "§cBenutze /sendpos <Spieler> !");
	   }
	   
      return false;
   }
}