package common.commands;

import common.Commandable;
import readers.ConsoleSourceReader;
import readers.FileSourceReader;
import utility.ClientReceiver;
import utility.ClientSender;
import utility.Invoker;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExecuteScript implements Commandable {


    protected static ArrayList<String> usedFiles = new ArrayList<>();
    static boolean fileWork = true;

    /**
     * Метод для выполнения скрипта
     *
     * @param s2 имя файла
     * @return fileWork
     */

    public void execute(String s2) {
        System.out.println(1);
        if (s2 == null | s2.equals("")) {
            ConsoleSourceReader consoleSourceReader = new ConsoleSourceReader();
            while (s2 == null | s2.equals("")) {
                System.out.println("кажется, вы забыли ввести расположение файла. Где он лежит?");
                s2 = consoleSourceReader.getLine();
            }
            System.out.println("спасибо, но в следующий раз введите его в той же строке, что и команду" + "\n");
        }
        Map<String, Commandable> commands = Invoker.getCommandCollection();
        if (theSameExist(s2)) {
            System.out.println("\n" + "-ать с рекурсией не надо игр-" + "\n");
            System.out.println("Рекурсивное чтение файла было завершено во избежание разрыва пространственно-временного континуума.");
        } else {
            usedFiles.add(s2);
            try {
                FileSourceReader fileSourceReader = new FileSourceReader(s2);
                String line;
                line = fileSourceReader.getLine();
                while (fileWork && line != null) {
                    String[] s = line.split(" ", 2);
                    System.out.println("Пытаемся выполнить команду \" " + line + " \"");
                    Commandable command = commands.get(s[0].toLowerCase());
                    if (s[0].toLowerCase().equals("add")) {
                        History.addInArray(s[0]);
                        try {
                            String[] array = {
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                            };
                            String string = Add.makeString(array);
                            if (string.equals("mistake")) {
                                System.out.println("Команда add не была выполнена");
                                line = fileSourceReader.getLine();
                                continue;
                            }
                            Map<Commandable, String> commandparamMap = new HashMap<>();
                            commandparamMap.put(command, string);
                            ClientSender.send(commandparamMap);
                            try {
                                ClientReceiver.receive();
                            } catch (SocketTimeoutException e) {
                                System.out.println("Сервер не отвечает или занят,попробуйте ещё раз и убедитесь,что сервер работает.");
                            }

                        } catch (Exception e) {
                            System.out.println("Невозможно создать элемент.");
                            return;
                        }

                    } else if (s[0].toLowerCase().equals("update")) {
                        History.addInArray(s[0]);
                        String ID;
                        if (s.length == 2) ID = s[1].trim();
                        else ID = null;
                        if (ID == null || ID.equals("")) {
                            System.out.println("Вы не ввели ID для апдейтинга.");
                            return;
                        }
                        Map<Commandable, String> commandparamMap = new HashMap<>();
                        commandparamMap.put(command, ID);
                        ClientSender.send(commandparamMap);
                        String a = (String)ClientReceiver.receive();
                        if (a.startsWith("Состояние элемента сейчас:")) {
                            String[] array = {
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                                    fileSourceReader.getLine(),
                            };
                            String s1 = Add.makeString(array);
                            commandparamMap.put(command, s1);
                            ClientSender.send(s1);
                            System.out.println(ClientReceiver.receive());
                        }
                    } else {
                        Map<Commandable, String> commandparamMap = new HashMap<>();
                        commandparamMap = Invoker.execute(line);

                        if (commandparamMap != null) {
                            ClientSender.send(commandparamMap);
                            try {
                                System.out.println(ClientReceiver.receive());
                            } catch (SocketTimeoutException e) {
                                System.out.println("Сервер не отвечает или занят,попробуйте ещё раз и убедитесь,что сервер работает.");
                            }
                        }
                    }
                    line = fileSourceReader.getLine();
                }
                fileSourceReader.close();
                usedFiles.clear();
                System.out.println("Завершение скрипта");
            } catch (IOException e) {
                System.out.println("ошибка чтения файла");
                usedFiles.clear();
            } catch (Exception e) {
                System.out.println("непредвиденный конец файла");
                usedFiles.clear();
            }
        }
    }

    /**
     * Поиск передаваемого файла среди уже запущенных
     *
     * @param s2 путь файла
     * @return true - если найден
     */
    private static boolean theSameExist(String s2) {
        for (String s : usedFiles) if (s.equals(s2)) return true;
        return false;
    }


    @Override
    public String execute(String par1, Socket socket, String user) {
        return null;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getInfo() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла.В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}