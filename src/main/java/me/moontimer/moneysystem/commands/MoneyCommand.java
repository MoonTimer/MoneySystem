package me.moontimer.moneysystem.commands;

import me.moontimer.moneysystem.MoneySystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        switch (args.length) {
            case 0:
                player.sendMessage("§4§lGrieferWeit §8» §7Du hast zurzeit §e" + MoneySystem.getInstance().getMoneyManager().getMoney(String.valueOf(player.getUniqueId())) + "€");
                break;
            case 3:
                if (!player.hasPermission("money.change")) {
                    if (args[0].equalsIgnoreCase("pay")) {
                        MoneySystem.getInstance().getMoneyManager().removeMoney(String.valueOf(player.getUniqueId()), Integer.parseInt(args[2]));
                        MoneySystem.getInstance().getMoneyManager().addMoney(String.valueOf(Bukkit.getPlayer(args[1]).getUniqueId()), Integer.parseInt(args[2]));
                        player.sendMessage("§4§lGrieferWeit §8» §7Du hast dem Spieler §e" + args[2] + "€ §8überwiesen");
                        Bukkit.getPlayer(args[1]).sendMessage("§4§lGrieferWeit §8» §7Dir wurden §e" + args[2] + "€ §8 von §a" + player.getName() + " §8überwiesen");
                    }
                    player.sendMessage("§4§lGrieferWeit §8» §7Du hast keine Rechte auf diesen Befehl");
                    return true;
                }
                switch (args[0].toLowerCase()) {
                    case "add":
                        MoneySystem.getInstance().getMoneyManager().addMoney(String.valueOf(Bukkit.getPlayer(args[1]).getUniqueId()), Integer.parseInt(args[2]));
                        player.sendMessage("§4§lGrieferWeit §8» §7Du hast den Spieler §e" + args[2] + "€ §8gegeben");
                        break;
                    case "remove":
                        MoneySystem.getInstance().getMoneyManager().removeMoney(String.valueOf(Bukkit.getPlayer(args[1]).getUniqueId()), Integer.parseInt(args[2]));
                        player.sendMessage("§4§lGrieferWeit §8» §7Du hast den Spieler §e" + args[2] + "€ §8entfernt");
                        break;
                    case "set":
                        MoneySystem.getInstance().getMoneyManager().setMoney(String.valueOf(Bukkit.getPlayer(args[1]).getUniqueId()), Integer.parseInt(args[2]));
                        player.sendMessage("§4§lGrieferWeit §8» §7Du hast den Spieler auf §e" + MoneySystem.getInstance().getMoneyManager().getMoney(String.valueOf(player.getUniqueId())) + "€ §8gesetzt");
                        break;
                    default:
                        player.sendMessage("§7[§2Lemon§eVace§7] §cFalscher Syntax §8Nur /money (add|remove|set)");
                        break;
                }
        }
        return false;
    }
}
