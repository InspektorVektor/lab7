package utility;

import App.*;

import java.sql.*;
import java.util.Set;

public class SQLConnector {
    private static final String url = "jdbc:postgresql://pg:5432/studs";
    private static final String user = "s285687";
    private static final String password = "mlo748";
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;

    public static Boolean ConnectionToDB() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException e) {
            throw e;

        }
    }
    public static Boolean addNewUser(String user, String password) {
        try {
            preparedStatement = connection.prepareStatement("insert into userdb values (?,?)");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean userExist(String user, String password) {

        try {
            preparedStatement = connection.prepareStatement("select *  from userdb d where exists( select * from userdb where d.login = ? and d.password= ?)");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void uploadAllPerson() {
        try {
            stmt = connection.createStatement();
            stmt.execute("TRUNCATE person");
            Set<Person> people = Collection.getPeople();
            people.forEach(x -> {
                try {
                    preparedStatement = connection.prepareStatement("INSERT into person values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setInt(1, x.getId());;
                    preparedStatement.setString(2, x.getName());
                    preparedStatement.setDouble(3, x.getCoordinates().getX());
                    preparedStatement.setDouble(4, x.getCoordinates().getY());
                    preparedStatement.setTimestamp(5, x.getCreationDate());
                    preparedStatement.setDouble(6, x.getHeight());
                    try {
                    preparedStatement.setTimestamp(7, x.getBirthday());
                    } catch (NullPointerException e) {
                        preparedStatement.setObject(7, null);
                    }
                        preparedStatement.setString(8, x.getHairColor().toString());
                    try {
                        preparedStatement.setString(9, x.getNationality().toString());
                    } catch (NullPointerException e) {
                        preparedStatement.setObject(9,  null);
                    }
                        preparedStatement.setDouble(10, x.getLocation().getX());
                        preparedStatement.setDouble(11, x.getLocation().getY());
                        preparedStatement.setInt(12, x.getLocation().getZ());
                        preparedStatement.setString(13, x.getLocation().getName());
                    preparedStatement.setString(14, x.getUser());
                    preparedStatement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadAllPersons() {
        try {
            try {
                Collection.getPeople().clear();
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select * from person");
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                Coordinates coordinates = new Coordinates(rs.getDouble(3), rs.getDouble(4));
                person.setCoordinates(coordinates);
                person.setCreationDate(rs.getTimestamp(5));
                person.setHeight(rs.getDouble(6));
              try {
                  person.setBirthday(rs.getTimestamp(7));
              }catch (Exception e){
                  person.setBirthday(null);
              }
                  person.setHairColor(Color.valueOf(rs.getString(8)));
                try {
                    person.setNationality(Country.valueOf(rs.getString(9)));
                }catch (Exception e){
                    person.setNationality(null);
                }
                    Location location = new Location();
                    location.setX(rs.getFloat(10));
                    location.setY(rs.getFloat(11));
                    location.setZ(rs.getInt(12));
                    location.setName(rs.getString(13));
                    person.setLocation(location);
                    person.setUser(rs.getString(14));
              Collection.getPeople().add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static Integer getNewId(){
        try {
            stmt = connection.createStatement();

            rs =stmt.executeQuery("SELECT nextval('id')");
            if (rs.next()) {
                return rs.getInt(1);
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}