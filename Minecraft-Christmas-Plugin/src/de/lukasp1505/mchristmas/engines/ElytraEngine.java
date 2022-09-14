package de.lukasp1505.mchristmas.engines;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ElytraEngine implements Listener{

    ArrayList<Player> allowedPlayers = new ArrayList<>();
    Map<String, Long> delay = new HashMap<>();
    Map<String, Long> delayMessage = new HashMap<>();
    
    int setDelay = 4;
    float velocity = 1.5F;
    int fuelId = 385;
    int fuelData = 0;
    int fuelCount = 1;
    List<String> fuelLore;
    
    Particle particleElytra = Particle.valueOf("CLOUD");
    Particle particlePower = Particle.valueOf("FLAME");
    Sound soundPower = Sound.valueOf("ITEM_FIRECHARGE_USE");
    Sound soundReload = Sound.valueOf("BLOCK_FIRE_EXTINGUISH");
    
    float soundVolumePower = 1.0F;
    float soundVolumePowerReload = 1.0F;
    float soundVolumeElytra = 1.0F;
    float soundPichPower = 1.0F;
    float soundPichPowerReload = 1.0F;
    float soundPichElytra = 1.0F;
    int particleCountPower = 16;
    float particlePowerDx = 0.1F;
    float particlePowerDy = 0.1F;
    float particlePowerDz = 0.1F;
    float particlePowerSpeed = 0.15F;
    int particleCountElytra = 3;
    float particleFlyDx = 0.1F;
    float particleFlyDy = 0.1F;
    float particleFlyDz = 0.1F;
    float particleFlySpeed = 0.1F;
    
    public void power(Player player){
        this.delay.put(player.getName(), System.currentTimeMillis() + (this.setDelay * 1000L));
        Vector pv = player.getLocation().getDirection();
        Vector v = pv.multiply(velocity);
        player.setVelocity(v);
        player.getWorld().playSound(player.getLocation(), this.soundPower, this.soundVolumePower, this.soundPichPower);
        player.getWorld().spawnParticle(this.particlePower, player.getLocation(), this.particleCountPower, this.particlePowerDx, this.particlePowerDy, this.particlePowerDz, this.particlePowerSpeed);
    }
    
    @EventHandler
    public void onMove(PlayerMoveEvent move) {
        Player player = move.getPlayer();
        ItemStack chestItem = player.getInventory().getChestplate();
        if (player.isGliding() && !player.isFlying()) {
                    if (player.hasPermission("powerelytra.player.usepower")){
                        if (this.delay.get(player.getName()) == null || this.delay.get(player.getName()) <= System.currentTimeMillis()) {
                            if (player.isSneaking()){
                                ItemStack fuel = new ItemStack(this.fuelId, this.fuelCount, (short) this.fuelData);
                                            ItemStack[] items = player.getInventory().getContents();
                                            int totalCount = 0;
                                            int delete = this.fuelCount;
                                            for (int i = 0; i < player.getInventory().getSize(); i++) {
                                                ItemStack item = player.getInventory().getItem(i);
                                                if(fuel.isSimilar(item)){
                                                    totalCount += item.getAmount();
                                                }
                                            }
                                            if (totalCount >= this.fuelCount){
                                                for (int i = 0; i < player.getInventory().getSize(); i++) {
                                                    ItemStack item = player.getInventory().getItem(i);
                                                    if(fuel.isSimilar(item)){
                                                        if (delete > 0){
                                                            if (item.getAmount() <= delete){
                                                                delete -= item.getAmount();
                                                                player.getInventory().setItem(i, null);
                                                            }else{
                                                                    item.setAmount(item.getAmount() - delete);
                                                                    break;
                                                                }
                                                        }else{break;}
                                                    }
                                                }
                                                power(player);
                                            }
                                        }
                                }
                            }
                            if (this.delay.get(player.getName()) != null && this.setDelay != 0){
                                if (this.delay.get(player.getName())/1000 == System.currentTimeMillis()/1000){
                                        player.getWorld().playSound(player.getLocation(), this.soundReload, this.soundVolumeElytra, this.soundPichElytra);
                                    this.delay.remove(player.getName());
                                }
                            }
                        }else {
                                    player.getWorld().spawnParticle(this.particleElytra, player.getLocation(), this.particleCountElytra, this.particleFlyDx, this.particleFlyDy, this.particleFlyDz, this.particleFlySpeed);
                            }
                    }
    
    @EventHandler
    public void playerGlideEvent(EntityToggleGlideEvent glide){
        Entity entity = glide.getEntity();
        if (entity.getType().equals(EntityType.PLAYER)){
            Player player = (Player) entity;
                if(!(allowedPlayers.contains(player))){
                    glide.setCancelled(true);
                }
        }
    }
    
    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent quit){
        Player player = quit.getPlayer();
        this.delay.remove(player.getName());
        this.delayMessage.remove(player.getName());
    }
    
}