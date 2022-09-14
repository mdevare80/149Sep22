package de.lukasp1505.mchristmas.commands.admins;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.lukasp1505.mchristmas.main.Main;

public class EventMenu implements CommandExecutor {
	
	public static int TaskID;
	public static boolean event_activ;
	public static Location eventLocation;
	private static String eventName = "Unknown";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("christmas.admin")) {
				if(args.length == 1) {
					if(event_activ == false) {
						eventLocation = player.getLocation();
												   
						for(int i = 1; i < args.length; ++i) {
							eventName = eventName + args[i] + " ";
						}
	                
						Bukkit.broadcastMessage(Main.chatPrefix + "§2" + player.getName() + " hat ein " + eventName + "-Event gestartet!");
						Bukkit.broadcastMessage(Main.chatPrefix + "§2Das " + eventName + "-Event startet in 60 Sekunden...");
						Bukkit.broadcastMessage(Main.chatPrefix + "§2Benutze /event um zum Event zugelangen!");
						event_activ = true;
						TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
						
						int countdown = 60;
						
						@Override
						public void run() {
							if(countdown <= 0) {
								Bukkit.broadcastMessage(Main.chatPrefix + "§cDas " + eventName + "-Event kann nun nicht mehr beigetreten werden!");
								Bukkit.getScheduler().cancelTask(TaskID);
								event_activ = false;
								return;
							} else
								countdown --;
							if(countdown == 50) {
								Bukkit.broadcastMessage(Main.chatPrefix + "§2Das " + eventName + "-Event startet in " + countdown + " Sekunden...");
							}
							if(countdown == 40) {
								Bukkit.broadcastMessage(Main.chatPrefix + "§2Das " + eventName + "-Event startet in " + countdown + " Sekunden...");
							}
							if(countdown == 30) {
								Bukkit.broadcastMessage(Main.chatPrefix + "§2Das " + eventName + "-Event startet in " + countdown + " Sekunden...");
							}
							if(countdown == 20) {
								Bukkit.broadcastMessage(Main.chatPrefix + "§2Das " + eventName + "-Event startet in " + countdown + " Sekunden...");
							}
							if(countdown == 10) {
								Bukkit.broadcastMessage(Main.chatPrefix + "§2Das " + eventName + "-Event startet in " + countdown + " Sekunden...");
							}
							if(countdown <= 5) {
								Bukkit.broadcastMessage(Main.chatPrefix + "§2Das " + eventName + "-Event startet in " + countdown + " Sekunden...");
							}
						}
						
					}, 0, 20);
					} else
						player.sendMessage(Main.chatPrefix + "§cEin Event ist bereits aktiv!");
				} else
					player.sendMessage(Main.chatPrefix + "§cBenutze /eventmenu <EVENTNAME>");
			} else
				player.sendMessage(Main.chatPrefix + "§cDu darfst diesen Befehl nicht ausführen!");
		} else
			sender.sendMessage(Main.chatPrefix + "Dieser Befehl kann nur als Spieler verwendet werden!");
		
		return false;
	}

}
