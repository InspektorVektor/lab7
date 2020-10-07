package common.commands;

import common.CommandWithoutArg;

import java.net.Socket;

public class Register implements CommandWithoutArg {
    @Override
    public String execute(String par1, Socket socket, String user) {
        return null;
    }

    @Override
    public String getInfo() {
        return "register";
    }
    @Override
    public String getName() {
        return "register";
    }
}
