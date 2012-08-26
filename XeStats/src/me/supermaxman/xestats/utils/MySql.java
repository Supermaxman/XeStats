package me.supermaxman.xestats.utils;

import me.supermaxman.xestats.XeStats;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


class MySql {

    private static final FileConfiguration config = XeStats.conf;
    private static final String url = config.getString("xs.config.database");
    private static final String user = config.getString("xs.config.user");
    private static final String pass = config.getString("xs.config.password");
    private static Connection conn = null;

    public static void initDB() throws SQLException {
        conn = DriverManager.getConnection(url, user, pass); //Creates the connection
    }

    public static void closeConn() throws SQLException {
        conn.close();
    }


//    public static void createTables() throws SQLException {
//        PreparedStatement Statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS `XBank` (  `id` int(11) NOT NULL auto_increment,  `User` varchar(50) NOT NULL,  `Balance` int(11) NOT NULL,  PRIMARY KEY  (`id`)) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
//        Statement.executeUpdate(); //Executes the query
//        Statement.close(); //Closes the query
//    }
//
//    public static void setBalance(Player p, int i) throws SQLException {
//        PreparedStatement Statement = conn.prepareStatement("UPDATE `XBank` SET Balance='" + i + "' WHERE User='" + p.getName() + "';");
//        Statement.executeUpdate(); //Executes the query
//        Statement.close(); //Closes the query
//    }
//
//    public static int getBalance(Player p) throws SQLException {
//        Statement state = conn.createStatement();
//        ResultSet rs = state.executeQuery("SELECT Balance FROM `XBank` WHERE User='" + p.getName() + "';");
//        int result = 0;
//        if (rs.next()) {
//            result = rs.getInt("Balance");
//        }
//        state.close();
//        return result;
//    }
//
//    public static void setBalanceOffline(OfflinePlayer target, int i) throws SQLException {
//        PreparedStatement Statement = conn.prepareStatement("UPDATE `XBank` SET Balance=" + i + " WHERE User='" + target.getName() + "';");
//        Statement.executeUpdate(); //Executes the query
//        Statement.close(); //Closes the query
//    }
//
//    private static final ArrayList<Player> seen = new ArrayList<Player>();
//
//    public static void createUser(Player player) throws SQLException {
//        if (seen.contains(player)) {
//            return;
//        } else {
//            seen.add(player);
//        } //Basic cache!
//        Statement state = conn.createStatement();
//        final ResultSet rs = state.executeQuery("SELECT * FROM `XBank` WHERE User='" + player.getName() + "';");
//        if (rs.first()) {
//            return;
//        } else {
//            PreparedStatement Statement1 = conn.prepareStatement("INSERT INTO `XBank` (`id`, `User`, `Balance`) VALUES (NULL, '" + player.getName() + "', '0');"); //Put your query in the quotes
//            Statement1.executeUpdate();
//            Statement1.close();
//            //INSERT INTO `XBank` (`id`, `User`, `Balance`) VALUES (NULL, 'TehRainbowGuy', '0');
//        }
//        state.close();
//    }
//
//    private static void createUserFromString(String player, int bal) throws SQLException {
//        Statement state = conn.createStatement();
//        final ResultSet rs = state.executeQuery("SELECT * FROM `XBank` WHERE User='" + player + "';");
//
//
//        if (rs.first()) {
//            return;
//        } else {
//            PreparedStatement Statement1 = conn.prepareStatement("INSERT INTO `XBank` (`id`, `User`, `Balance`) VALUES (NULL, '" + player + "', '" + bal + "');"); //Put your query in the quotes
//            Statement1.executeUpdate();
//            Statement1.close();
//        }
//        state.close();
//    }


}