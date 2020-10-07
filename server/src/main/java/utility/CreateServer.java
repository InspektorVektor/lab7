package utility;

import App.Collection;
import common.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * В начале был сервер...
 */
public class CreateServer {
    public static ServerSocket server;
    public static boolean create(int port) {

        Add add = new Add();
        Add_if_max add_if_max = new Add_if_max();
        Clear clear = new Clear();
        Exit exit = new Exit();
        Info info = new Info();
        Max_by_height max_byDistance = new Max_by_height();
        Filter_by_nationality filter_by_nationality = new Filter_by_nationality();
        Filter_greater_than_birthday filter_greater_than_birthday = new Filter_greater_than_birthday();
        Remove_by_id remove_byId = new Remove_by_id();
        Remove_greater remove_greater = new Remove_greater();
        Show show = new Show();
        Updater update = new Updater();
        Login login =new Login();
        Register register = new Register();
        Collection collection = new Collection();
        try {
            server = new ServerSocket(port);
            System.out.println("Сервер запущен");
            new InputString().start();
            return true;
        } catch (BindException e) {
            System.out.println("Данный порт уже занят,возможно сервер уже запущен!\n Принудительно завершаю работу.");
            System.exit(0);
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public static String PasswordCoder(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {

        }
        return null;
    }
}
