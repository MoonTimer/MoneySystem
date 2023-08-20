package me.moontimer.moneysystem.api;

public class MoneyAPI {

    private final MoneyManager moneyManager;

    public MoneyAPI(MoneyManager moneyManager) {
        this.moneyManager = moneyManager;
    }

    public double getBalance(String uuid) {
        return moneyManager.getMoney(uuid);
    }

    public void setBalance(String uuid, double amount) {
        moneyManager.setMoney(uuid, amount);
    }

    public void addBalance(String uuid, double amount) {
        moneyManager.addMoney(uuid, amount);
    }

    public void removeBalance(String uuid, double amount) {
        moneyManager.removeMoney(uuid, amount);
    }

    public boolean hasMoney(String uuid, double amount) {
        return moneyManager.hasEnoughMoney(uuid, amount);
    }
}
