package com.mygaming3033.beastdiscordutility.utils;
import com.mygaming3033.beastdiscordutility.BeastDiscordUtility;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    private static BeastDiscordUtility pl;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private final static Pattern PATTERN = Pattern.compile("&#[a-fA-F0-9]{6}");

    public Utils(BeastDiscordUtility pl) {

        this.pl = pl;

    }

    public static String getPrefix() {

        return BeastDiscordUtility.getInstance().getConfig().getString("Prefix");

    }

    public static  void  sendMessage(Player p , String message){
        p.sendMessage(setPlaceholders(p,message));
    }

    public static void sendMessage(CommandSender sender, String message){

        message = message.replaceAll("%prefix%", getPrefix());
        message = message.replaceAll("%player%", sender.getName());
        message = setHexColors(message);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',message));

    }



    public static void sendLog(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        Bukkit.getServer().getConsoleSender().sendMessage("["+ BeastDiscordUtility.getInstance().getDescription().getName()+"] " + s);
    }

    public static void warningLog(String message) {
        Bukkit.getServer().getLogger().warning(ANSI_RED + "[" + BeastDiscordUtility.getInstance().getDescription().getName() + "] " + message + ANSI_RESET);
    }


    public static String setHexColors(String s) {
        if (!MinecraftVersion.isAtLeastVersion(MinecraftVersion.MC1_16_R1)) return s;
        Matcher matcher = PATTERN.matcher(s);
        while (matcher.find()) {
            String c = s.substring(matcher.start(), matcher.end());

            s = s.replace(c, "" + ChatColor.of(c.replace("&", "")));
            matcher = PATTERN.matcher(s);
        }
        return s;
    }

    public static String setColor(String s) {
        s = setHexColors(s);
        return ChatColor.translateAlternateColorCodes('&', s);
    }


    public static String setPlaceholders(Player p, String s) {
        s = s.replaceAll("%prefix%", getPrefix());
        s = s.replaceAll("%player%", p.getName());
        s = s.replaceAll("%online_players%",String.valueOf(Bukkit.getOnlinePlayers().size()));

        s = setColor(s);
        return s;
    }
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException efr) {
            return false;
        }
        return true;
    }

    public static boolean isInt(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException efr) {
            return false;
        }
        return true;
    }



    public static void setSlots(Player p){


       /* PlayerInventory inv = p.getInventory();

        for(String s : pl.getConfig().getConfigurationSection("GlassPanels.Items").getKeys(false)){

            int slot = pl.getConfig().getInt("GlassPanels.Items."+s+".Slot");

            //GlassPanels.Items.cuna.Material
            Material m = Material.matchMaterial(pl.getConfig().getString("GlassPanels.Items."+s+".Material"));
            ItemStack item = new ItemStack(m,1);

            ItemMeta meta = item.getItemMeta();
            //Setting Name
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',pl.getConfig().getString("GlassPanels.Items."+s+".Name")));

            //Adding Lore
            ArrayList<String> list = new ArrayList<>();
            for(String l : pl.getConfig().getStringList("GlassPanels.Items."+s+".Lore")){

                list.add(ChatColor.translateAlternateColorCodes('&',l));

            }
            meta.setLore(list);


            boolean b = pl.getConfig().getBoolean("GlassPanels.Items."+s+".Glow");
            if(b == true) {
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                meta.addEnchant(Enchantment.DURABILITY,1,true);
            }
            item.setItemMeta(meta);

            inv.setItem(slot,item);

        }*/

    }




}
