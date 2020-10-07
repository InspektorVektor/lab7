package common.commands;
import App.Collection;
import common.*;
import App.Person;
import utility.SQLConnector;

import java.net.Socket;

/**
 * Команда "МАКСИМАЛЬНЫЙ"
 */
public class Max_by_height implements CommandWithoutArg {
        private static final long serialVersionUID = 6529685098267757690L;

        /**
         * метод для вывода любого объект из коллекции, значение поля height которого является максимальным
         */
        @Override
        public String execute(String par1, Socket socket, String user) {
            SQLConnector.loadAllPersons();
                if (Collection.getPeople().size() > 0) {
                        double[] d = {0, 0};
                        Person maxPersonByHeight = Collection.getPeople().stream()
                                .max((p1, p2) -> {
                                        if (p1.getHeight() == null) d[0] = 0;
                                        else d[0] = p1.getHeight();
                                        if (p2.getHeight() == null) d[1] = 0;
                                        else d[1] = p2.getHeight();
                                        return Double.compare(d[0], d[1]);
                                })
                                .get();
                       return ("\n \n" + maxPersonByHeight + "\n \n");
                } else
                        return ("\nКоллекция пуста как моё сердце \n \n");
        }
        @Override
        public String getInfo() {
                return "max_by_height: вывести любой объект из коллекции, значение поля height которого является максимальным";
        }

    @Override
    public String getName() {
        return "max_by_height";
    }
}