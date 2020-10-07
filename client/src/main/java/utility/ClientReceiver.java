package utility;

import java.io.*;
import java.net.*;
import java.util.Map;
/**
 * Получатель
 */
public class ClientReceiver {
    public static Socket socket;
    /**
     * Получить
     * @return массивчик байтиков
     */
    public static Object receive() throws IOException, ClassNotFoundException, InterruptedException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object obj = objectInputStream.readObject();
        Map<Object, Integer> answer = (Map<Object, Integer>) obj;
            if (answer.entrySet().iterator().next().getValue() == 0) {
                return (answer.entrySet().iterator().next().getKey());
            } else if (answer.entrySet().iterator().next().getValue() == 1) {
                System.out.println(answer.entrySet().iterator().next().getKey());
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String s = reader.readLine();
                ClientSender.send(s);
                return ClientReceiver.receive();
            }
       return null;
    }
}
