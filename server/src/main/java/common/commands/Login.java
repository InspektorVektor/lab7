package common.commands;

import common.CommandWithoutArg;
import common.Commandable;
import utility.CreateServer;
import utility.SQLConnector;
import utility.ServerReceiver;
import utility.ServerSender;

import java.net.Socket;

public class Login implements CommandWithoutArg {
    @Override
    public String execute(String par1, Socket socket, String user) {
        ServerSender serverSender = new ServerSender();
        ServerReceiver serverReceiver = new ServerReceiver();
        serverSender.send(socket, "Введите логин", 1);
        String login = (String) (serverReceiver.receive(socket));
        serverSender.send(socket, "Введите пароль", 1);
        String password = CreateServer.PasswordCoder((String) (serverReceiver.receive(socket)));
        if (SQLConnector.userExist(login, password)) {
            return login;
        } else return "false";
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public String getName() {
        return "login";
    }
}
