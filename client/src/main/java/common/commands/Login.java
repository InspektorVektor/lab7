package common.commands;

import common.CommandWithoutArg;
import common.Commandable;

import java.net.Socket;

public class Login implements CommandWithoutArg {
    @Override
    public String execute(String par1, Socket socket, String user) {
        return null;
    }

    @Override
    public String getInfo() {
        return "login";
    }
    @Override
    public String getName() {
        return "login";
    }
}
