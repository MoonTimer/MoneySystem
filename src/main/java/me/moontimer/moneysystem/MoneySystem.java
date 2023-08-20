package me.moontimer.moneysystem;

import me.moontimer.moneysystem.api.MoneyAPI;
import me.moontimer.moneysystem.api.MoneyManager;
import me.moontimer.moneysystem.mysql.MySQLConnector;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoneySystem extends JavaPlugin {

    private static MoneySystem instance;
    private MoneyManager moneyManager;
    private MySQLConnector mySQLConnector;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        mySQLConnector = new MySQLConnector("85.214.221.218", "3306", "grieferweit", "grieferweit", "1jkpug4mmmfwerr2cokka26y6kzgwuzm07a28hyh");
        moneyManager = new MoneyManager(mySQLConnector.getConnection());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public MoneyManager getMoneyManager() {
        return moneyManager;
    }

    public static MoneySystem getInstance() {
        return instance;
    }
}
