package us.cyrien.MineCordBotV1.commands.discordCommands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import us.cyrien.MineCordBotV1.commands.DiscordCommand;
import us.cyrien.MineCordBotV1.main.MineCordBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ListCommand extends DiscordCommand {

    public ListCommand(MineCordBot mcb) {
        super(mcb, "List");
        usage = getLanguage().getTranslatedMessage("mcb.commands.list.usage");
        description = getLanguage().getTranslatedMessage("mcb.commands.list.description");
        commandType = INFO;
        commandPermissionLevel = LEVEL_0;
    }

    @Override
    public boolean checkArguments(MessageReceivedEvent e, String[] args) {
        return true;
    }

    @Override
    public boolean hasPermission(String[] args) {
        if (args.length != 1) {
            return getSender().getPermissionLevel() >= commandPermissionLevel;
        } else
            return true;
    }

    @Override
    public void execute(MessageReceivedEvent e, String[] args) {
        EmbedBuilder eb = new EmbedBuilder();
        e.getTextChannel().sendMessage(generateList(eb)).queue();
    }

    private MessageEmbed generateList(EmbedBuilder eb) {
        StringBuilder out;
        if (Bukkit.getServer().getOnlinePlayers() == null || Bukkit.getServer().getOnlinePlayers().size() == 0) {
            eb.setTitle("There are no players online...");
        } else {
            out = new StringBuilder();
            int counter = 1;
            for (Player p : Bukkit.getOnlinePlayers())
                out.append(counter++).append(". ").append(p.getName()).append("\n");
            eb.addField("Online Players on Minecraft", out.toString(), false);
        }
        return eb.build();
    }

    @Override
    public void executed(MessageReceivedEvent e) {
        logCommand(e);
    }

    @Override
    public void logCommand(MessageReceivedEvent e) {
        getLogger().info(getSender().getName() + " Issued a list command.");
    }
}
