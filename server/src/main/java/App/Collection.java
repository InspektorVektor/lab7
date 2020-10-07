package App;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.*;

public class Collection implements Serializable {

    private static Set<Person> people =  Collections.synchronizedSet(new HashSet<>());

    public static Set<Person> getPeople() {
        return people;
    }

    public static void setPeople(HashSet<Person> people) {
        Collection.people = people;
    }

    private String path;


    /**
     * Конструктор коллекии
     */
    public Collection(){
        this.people = new HashSet<>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }





    /**
     * Метод поиска элемента по id
     * @param id id
     * @return элемент коллекции
     */
    public static Person searchById(int id) {
        for (Person r : people) {
            if (r.getId().equals(id))
                return r;
        }
        return null;
    }


}
