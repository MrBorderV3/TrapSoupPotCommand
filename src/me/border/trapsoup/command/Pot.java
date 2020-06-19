package me.border.trapsoup.command;

import me.border.trapsoup.TrapSoup;
import me.border.trapsoup.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class Pot implements CommandExecutor {

    private TrapSoup plugin;

    public Pot(TrapSoup plugin){
        this.plugin = plugin;

        plugin.getCommand("pot").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender,final Command cmd,final String label,final String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(Utils.ucs("notAPlayer"));
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("trapsoup.pot")) {
            if (args.length == 0) {
                potPlayer(p);
                return true;
            } else {
                p.sendMessage(Utils.ucs("Pot.correct-usage"));
            }
        } else {
            p.sendMessage(Utils.ucs("noPermission"));
        }

        return false;
    }

    public void potPlayer(Player p){
        Potion pot = new Potion(PotionType.INSTANT_HEAL, 2);
        pot.setSplash(true);

        boolean bool = false;
        for (int i = 0; i < p.getInventory().getSize(); i++){
            if (p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR || p.getInventory().getItem(i).getType() == null){
                p.getInventory().setItem(i, pot.toItemStack(1));
                bool = true;
            }
        }
        if (!bool){
            p.sendMessage(Utils.ucs("noInventorySpace"));
            return;
        }
        p.sendMessage(Utils.ucs("Pot.successful"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kb coins remove " + p.getName() + " 100");
    }
}

