package me.supermaxman.xestats;

import me.supermaxman.xestats.executors.StatsMainExecutor;
import me.supermaxman.xestats.utils.MySQL.QueryManager;
import me.supermaxman.xestats.utils.MySQL.StatsUser;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

public class XeStats extends JavaPlugin {

    //Required
    public static Logger log;
    public static FileConfiguration conf;
    public static QueryManager qm;
    private final XeStatsListener Listener = new XeStatsListener(this);
    //    public static Permission permission = null;
    public static Chat chat = null;
    public static XeStats XE;

    public static final HashMap<String, StatsUser> statsUserMap = new HashMap<String, StatsUser>();

    @Override
    public void onDisable() {
        qm.closeConn(); //Close db connection
        log.info("Disabled.");

    }


    @Override
    public void onEnable() {
        XE = this;
        log = getLogger();
        conf = getConfig();
        if (!setupChat()) {
            log.severe("Vault fail!");
            this.setEnabled(false);
            return;
        }
        setupConfig();
        qm = new QueryManager();
        getServer().getPluginManager().registerEvents(Listener, this);
        log.info("All systems go! Version:" + this.getDescription().getVersion());

        getCommand("stats").setExecutor(new StatsMainExecutor(this));
    }


    void setupConfig() {
        if (!conf.contains("xs.config.database")) {
            conf.set("xs.config.database", "jdbc:mysql://localhost:3306/minecraft");
        }
        if (!conf.contains("xs.config.user")) {
            conf.set("xs.config.user", "notRoot");
        }
        if (!conf.contains("xs.config.password")) {
            conf.set("xs.config.password", "YourAwesomePassword");
        }
        if (!conf.contains("xs.config.databasename")) {
            conf.set("xs.config.databasename", "pvp_database");
        }
        saveConfig();
    }

//    private boolean setupPermissions() {
//        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
//        if (permissionProvider != null) {
//            permission = permissionProvider.getProvider();
//            return true;
//        }
//        return false;
//    }
//

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatRegisteredServiceProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatRegisteredServiceProvider != null) {
            chat = chatRegisteredServiceProvider.getProvider();
            return true;
        }
        return false;
    }
}
