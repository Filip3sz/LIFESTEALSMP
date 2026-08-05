package pl.filipesz.lifestealsmp;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import pl.filipesz.lifestealsmp.cmd.*;
import pl.filipesz.lifestealsmp.listeners.*;
import pl.filipesz.lifestealsmp.structs.UserFile;
import pl.filipesz.lifestealsmp.systems.RefreshAPISystem;

@SuppressWarnings("ALL")
public class Main extends JavaPlugin {

    public static Main instance;

    public static Main getInstance() {
        return Main.instance;
    }

    private void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveConfig();
        UserFile.setup(this);

        Main.instance = this;

        Bukkit.setWhitelist(false);

        // IMPORTANT THINGS
        // START SYSTEMS
        RefreshAPISystem.start();

        // SET WORLD BORDER
        World world = Bukkit.getWorld("world");
        WorldBorder wb = world.getWorldBorder();
        wb.setCenter(0.0, 0.0);
        wb.setSize(4000.0);

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeListener());

        getCommand("items").setExecutor(new ItemsCMD());
        getCommand("player").setExecutor(new PlayerCMD());
        getCommand("rankreset").setExecutor(new RankResetCMD());
        getCommand("sklep").setExecutor(new ShopCMD());
        getCommand("spawn").setExecutor(new SpawnCMD());
        registerEvent(new BlockBreakListener());
        EntityDamageByEntityListener combatLog = new EntityDamageByEntityListener();
        registerEvent(combatLog);
        registerEvent(new InventoryClickListener());
        registerEvent(new PlayerDeathListener());
        registerEvent(new PlayerInteractListener());
        registerEvent(new PlayerItemConsumeListener());
        registerEvent(new PlayerJoinListener());
        registerEvent(new PlayerMoveListener());
        registerEvent(new PlayerQuitListener(combatLog.getCombatLog()));
        registerEvent(new PlayerRespawnListener());
    }
}



