package me.moontimer.moneysystem.api;


import java.sql.*;
import java.util.UUID;

public class MoneyManager {
    private final Connection connection;

    public MoneyManager(Connection connection) {
        this.connection = connection;
    }

    public void createPlayer(String playerUUID) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT IGNORE INTO player_money (uuid, amount) VALUES (?, ?);")) {
            statement.setString(1, playerUUID);
            statement.setDouble(2, 1000.0);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getMoney(String playerUUID) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT amount FROM player_money WHERE uuid = ?;")) {
            statement.setString(1, playerUUID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public void setMoney(String playerUUID, double amount) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE player_money SET amount = ? WHERE uuid = ?;")) {
            statement.setDouble(1, amount);
            statement.setString(2, playerUUID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMoney(String playerUUID, double amount) {
        double currentBalance = getMoney(playerUUID);
        setMoney(playerUUID, currentBalance + amount);
    }

    public void removeMoney(String playerUUID, double amount) {
        double currentBalance = getMoney(playerUUID);
        setMoney(playerUUID, currentBalance - amount);
    }

    public boolean hasEnoughMoney(String playerUUID, double amount) {
        return getMoney(playerUUID) >= amount;
    }
}
