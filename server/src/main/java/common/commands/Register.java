package common.commands;

import common.CommandWithoutArg;
import utility.CreateServer;
import utility.SQLConnector;
import utility.ServerReceiver;
import utility.ServerSender;

import java.io.IOException;
import java.net.Socket;

public class Register implements CommandWithoutArg {
    @Override
    public String execute(String o, Socket socket, String user)  {
        ServerSender serverSender = new ServerSender();
        ServerReceiver serverReceiver = new ServerReceiver();
        serverSender.send(socket, "Введите логин", 1);
        String login = (String) (serverReceiver.receive(socket));
        serverSender.send(socket, "Введите пароль", 1);
        String password = CreateServer.PasswordCoder((String) (serverReceiver.receive(socket)));
        if (SQLConnector.addNewUser(login, password)) {
            return login;
        }
        else  return "false";
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public String getName() {
        return "register";
    }
}

