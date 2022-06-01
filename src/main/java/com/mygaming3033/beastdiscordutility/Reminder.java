package com.mygaming3033.beastdiscordutility;

import com.mygaming3033.beastdiscordutility.utils.Utils;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Reminder extends BukkitRunnable {
    BeastDiscordUtility pl;
    public Reminder(BeastDiscordUtility pl) {
        this.pl=pl;
        runTaskTimer(pl, 20 * 20, 20 * pl.getConfig().getInt("Delay"));

    }

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()){
            String discordId = DiscordSRV.getPlugin().getAccountLinkManager().getDiscordId(p.getUniqueId());
            if (discordId == null) {
                Utils.sendMessage(p,pl.getConfig().getString("PlayerNotLinked"));
            }
        }

    }


}
