package me.border.trapsoup.command;

import me.border.trapsoup.TrapSoup;
import me.border.trapsoup.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class Soup implements CommandExecutor {

    private TrapSoup plugin;

    public Soup(TrapSoup plugin){
        this.plugin = plugin;

        plugin.getCommand("soup").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender,final Command cmd,final String label,final String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(Utils.ucs("notAPlayer"));
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("trapsoup.soup")) {
            if (args.length == 0) {
                potPlayer(p);
                return true;
            } else {
                p.sendMessage(Utils.ucs("Soup.correct-usage"));
            }
        } else {
            p.sendMessage(Utils.ucs("noPermission"));
        }

        return false;
    }

    public void potPlayer(Player p){
        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP, 1);

        boolean bool = false;
        for (int i = 0; i < p.getInventory().getSize(); i++){
            if (p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR || p.getInventory().getItem(i).getType() == null){
                p.getInventory().setItem(i, soup);
                bool = true;
            }
        }
        if (!bool){
            p.sendMessage(Utils.ucs("noInventorySpace"));
            return;
        }
        p.sendMessage(Utils.ucs("Soup.successful"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kb coins remove " + p.getName() + " 100");
    }
}

