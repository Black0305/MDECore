package com.mattdahepic.mdecore.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractHelpLogic implements ICommandLogic {
    public abstract AbstractCommand getBaseCommand ();

    @Override
    public String getCommandName () {
        return "help";
    }
    @Override
    public int getPermissionLevel () {
        return -1;
    }
    @Override
    public String getCommandSyntax () {
        return String.format("/"+getBaseCommand().getCommandName()+" help <command>");
    }
    @Override
    public void handleCommand (ICommandSender sender, String[] args) throws CommandException {
        switch (args.length) {
            case 1: //list all commands
                StringBuilder output = new StringBuilder("Available commands are: ");
                List<String> commandList = new ArrayList<String>(getBaseCommand().getCommandList());
                Collections.sort(commandList,String.CASE_INSENSITIVE_ORDER);

                String baseCommand = "/"+getBaseCommand().getCommandName()+" ";

                int commands = 0;
                for (int i = 0; i < commandList.size() - 1; i++) { //all commands except last one
                    String name = commandList.get(i);
                    if (getBaseCommand().canUseCommand(sender,getBaseCommand().getCommandPermission(name),getBaseCommand(),name)) {
                        output.append(baseCommand+EnumChatFormatting.YELLOW+commandList.get(i)+EnumChatFormatting.WHITE+", ");
                        commands++;
                    }
                }
                if (commands > 0) output.delete(output.length() - 2, output.length()); //delete final comma
                String name = commandList.get(commandList.size() - 1);
                if (getBaseCommand().canUseCommand(sender,getBaseCommand().getCommandPermission(name),getBaseCommand(),name)) { //final command and formatting
                    if (commands > 0) output.append(" and ");
                    output.append(baseCommand+EnumChatFormatting.YELLOW+name+EnumChatFormatting.WHITE+".");
                }
                sender.addChatMessage(new ChatComponentText(output.toString()));
                break;
        }
    }
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 2) {
            return CommandBase.getListOfStringsMatchingLastWord(args, getBaseCommand().getCommandList()); //get all possible commands to "/mde help" on
        }
        return null;
    }
}