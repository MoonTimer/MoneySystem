package me.moontimer.moneysystem.listener;

import me.moontimer.moneysystem.MoneySystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        MoneySystem.getInstance().getMoneyManager().createPlayer(String.valueOf(event.getPlayer().getUniqueId()));
    }
}
