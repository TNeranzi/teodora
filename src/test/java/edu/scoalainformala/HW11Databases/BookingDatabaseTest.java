package edu.scoalainformala.HW11Databases;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static edu.scoalainformala.HW11Databases.BookingDatabase.*;

public class BookingDatabaseTest {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/booking_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "R3dMistr3ss";

    @Test
    public void testInsertData() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            insertAccommodation(connection, 2, "Apartment", "Double", 4, "Modern apartment");
            insertRoomFair(connection, 2, 150.0, "Winter");
            insertAccommodationRoomFairRelation(connection, 2, 2, 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrintRoomPrices() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD))  {
            printRoomPrices(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void printRoomPrices(Connection connection) throws SQLException {
        String sql = "SELECT a.id, a.type, a.bed_type, r.value, r.season " +
                "FROM accommodation a " +
                "JOIN accommodation_room_fair_relation arfr ON a.id = arfr.accommodation_id " +
                "JOIN room_fair r ON arfr.room_fair_id = r.id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int accommodationId = resultSet.getInt("id");
                String accommodationType = resultSet.getString("type");
                String bedType = resultSet.getString("bed_type");
                double roomValue = resultSet.getDouble("value");
                String roomSeason = resultSet.getString("season");

                System.out.printf("Room: %d, Type: %s, Bed Type: %s, Room Price: %.2f, Season: %s%n",
                        accommodationId, accommodationType, bedType, roomValue, roomSeason);
            }
        }
    }
}