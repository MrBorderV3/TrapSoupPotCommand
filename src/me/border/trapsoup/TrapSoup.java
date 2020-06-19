package me.border.trapsoup;

import me.border.trapsoup.command.Pot;
import me.border.trapsoup.command.Soup;
import me.border.trapsoup.util.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class TrapSoup extends JavaPlugin {

    @Override
    public void onEnable(){
        new Utils(this);
        new Pot(this);
        new Soup(this);
    }
}
