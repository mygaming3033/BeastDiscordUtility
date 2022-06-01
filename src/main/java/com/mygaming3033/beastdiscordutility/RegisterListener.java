package com.mygaming3033.beastdiscordutility;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.AccountLinkedEvent;
import github.scarsz.discordsrv.api.events.DiscordConsoleCommandPostProcessEvent;
import github.scarsz.discordsrv.dependencies.jda.api.hooks.ListenerAdapter;
import github.scarsz.discordsrv.objects.managers.AccountLinkManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class RegisterListener{
    BeastDiscordUtility pl;

    public RegisterListener(BeastDiscordUtility pl) {
        this.pl = pl;
    }

    @Subscribe
    public void onLinkEvent(AccountLinkedEvent e){
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String name = e.getUser().getName();


        new BukkitRunnable() {
            @Override
            public void run() {
                for(String cmd : pl.getConfig().getStringList("Reward")){
                    cmd = cmd.replaceAll("%player%",name);
                    Bukkit.dispatchCommand(console,cmd);
                }
            }
        }.runTask(pl);

    }
}
