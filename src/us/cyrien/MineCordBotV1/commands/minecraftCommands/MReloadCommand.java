package us.cyrien.MineCordBotV1.commands.minecraftCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import us.cyrien.MineCordBotV1.main.MineCordBot;

import java.io.IOException;

public class MReloadCommand implements CommandExecutor {

    private MineCordBot mcb;

    public MReloadCommand(MineCordBot mcb) {
        this.mcb = mcb;
    }

    private boolean preCommand(CommandSender commandSender, Command command, String[] args) {
        if (!command.getName().equalsIgnoreCase("minecordbot"))
            return usage(commandSender);
        if (!commandSender.hasPermission("minecordbot.reload"))
            return noPerm(commandSender);
        return !(args == null || args.length < 1 || args.length > 2) || usage(commandSender);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!preCommand(commandSender, command, args))
            return true;
        try {
            mcb.getMcbConfig().reload();
        } catch (IOException e) {
            e.printStackTrace();
        }
        commandSender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "MineCordBot Reloaded!");
        return true;
    }

    private boolean usage(CommandSender cs) {
        cs.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l=== MineCordBot Usage ==="));
        cs.sendMessage("/minecordbot reload");
        return false;
    }

    private boolean noPerm(CommandSender cs) {
        cs.sendMessage(ChatColor.RED + "You do not have permission to reload MineCordBot");
        return false;
    }

}
