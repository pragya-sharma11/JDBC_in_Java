package googlePlayGame.cityHelper;


import googlePlayGame.city.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityHelper {


    public static final String NAME_COLUMN="name";
    public static final String  TRAVERSED_COLUMN="traversed";
    public static final String KM_REQ="kilometers_required";
    private static final String INSERT_QUERY=
            "INSERT INTO Cities (name, traversed, kilometers_required)"+
                    " VALUES(?, ?, ?)";
                     //      1, 2, 3 ----> parameters index!!!!

    private static final String READ_ALL_QUERY=
            "SELECT * FROM Cities";

    private static final String UPDATE_QUERY=
            " UPDATE Cities SET kilometers_required= ? where name= ? ";

    private static final String DELETE_QUERY=
            "DELETE FROM Cities WHERE name=?";

    //create
    public void addCityToDatabase(City city, Connection connection)
    throws SQLException {
        String name=city.getName();
        boolean isTraversed=city.getTraversed();
        int killometersRequired=city.getKilometersRequired();
        PreparedStatement insertQuery= connection.prepareStatement(INSERT_QUERY);
        insertQuery.setObject(1,name);
        insertQuery.setBoolean(2,isTraversed);
        insertQuery.setObject(3,killometersRequired);
        insertQuery.execute();

    }

    //Read
    public void readAllCitiesFromDatabase(Connection connection)throws SQLException{
        PreparedStatement readStatement=
                connection.prepareStatement(READ_ALL_QUERY);
        ResultSet resultSet= readStatement.executeQuery();
        while (resultSet.next()){
            String cityName=resultSet.getString(NAME_COLUMN);
            boolean isTraversed=resultSet.getBoolean(TRAVERSED_COLUMN);
            int kmRequired=resultSet.getInt(KM_REQ);
            City city=new City(cityName, isTraversed, kmRequired);
            System.out.println(city.toString());
            System.out.println("-----------------------------------------");

        }
    }

    //update
    public void  updateCity(Connection connection, City city)throws  SQLException{
        PreparedStatement updateStatement=
                connection.prepareStatement(UPDATE_QUERY);
        updateStatement.setInt(1, city.getKilometersRequired());
        updateStatement.setString(2, city.getName());
        updateStatement.executeUpdate();
    }

    //Delete
    public void deleteCity(Connection connection, City city)throws SQLException{
        PreparedStatement deleteStatement=
                connection.prepareStatement(DELETE_QUERY);
        deleteStatement.setString(1,city.getName());
        deleteStatement.executeUpdate();


    }

}
