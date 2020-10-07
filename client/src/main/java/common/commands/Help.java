package common.commands;

import common.CommandWithoutArg;
import common.Commandable;
import utility.Invoker;

import java.net.Socket;

/**
 * Команда "ПОМОЩЬ"
 */
public class Help implements CommandWithoutArg {

    @Override
    public String execute(String par1, Socket socket, String user) {
        for (Commandable command : Invoker.getCommandCollection().values()) {
            System.out.println(command.getInfo());
        }
        return null;
    }
    @Override
    public String getName() {
        return "help";
    }
    @Override
    public String getInfo() {
        return "help : вывести список всех команд и кратенько рассказать, что они делают";
    }
}
