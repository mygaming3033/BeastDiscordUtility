package com.mygaming3033.beastdiscordutility;

import com.mygaming3033.beastdiscordutility.utils.Utils;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BeastDiscordUtility extends JavaPlugin {
    static BeastDiscordUtility INSTANCE;
    private RegisterListener registerListener;
    private Reminder reminder;
    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        reminder = new Reminder(this);
        registerListener = new RegisterListener(this);
        DiscordSRV.api.subscribe(registerListener);
        //getServer().getPluginManager().registerEvents(new RegisterListener(this),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static BeastDiscordUtility getInstance(){
        return INSTANCE;
    }

    public boolean isDiscordAccountLinked(Player p){
        String discordId = DiscordSRV.getPlugin().getAccountLinkManager().getDiscordId(p.getUniqueId());
        if (discordId == null) {
            return false;
        }
        return true;
    }
}

