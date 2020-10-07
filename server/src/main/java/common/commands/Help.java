package common.commands;

import common.CommandWithoutArg;
import common.Commandable;

import java.net.Socket;

/**
 * Команда "ПОМОЩЬ"
 */
public class Help implements CommandWithoutArg {

    @Override
    public String execute(String par1, Socket socket, String user) {
//        System.out.println("\n \n \n");
//        for (Commandable command : getCommandCollection().values()) {
//            System.out.println(command.getInfo());
//        }
//        System.out.println("\n \n \n");
        return null;
    }

    @Override
    public String getInfo() {
        return "help : вывести список всех команд и кратенько рассказать, что они делают";
    }

    @Override
    public String getName() {
        return "help";
    }
}
