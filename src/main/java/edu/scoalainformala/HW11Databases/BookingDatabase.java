package edu.scoalainformala.HW11Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BookingDatabase {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/booking_db";


    public static void main(String[] args) {
        //BookingDatabase bookingDatabase = new BookingDatabase();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();


        try (Connection connection = DriverManager.getConnection(DB_URL, username, password)) {
            insertAccommodation(connection, 1, "Hotel", "Single", 2, "Luxury hotel room");
            insertRoomFair(connection, 1, 100.0, "Summer");
            insertAccommodationRoomFairRelation(connection, 1, 1, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void insertAccommodation(Connection connection, int id, String type, String bedType, int maxGuests, String description) throws SQLException {
        String sql = "INSERT INTO accommodation (id, type, bed_type, max_guests, description) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, type);
            statement.setString(3, bedType);
            statement.setInt(4, maxGuests);
            statement.setString(5, description);
            statement.executeUpdate();
        }
    }

    static void insertRoomFair(Connection connection, int id, double value, String season) throws SQLException {
        String sql = "INSERT INTO room_fair (id, value, season) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setDouble(2, value);
            statement.setString(3, season);
            statement.executeUpdate();
        }
    }

    static void insertAccommodationRoomFairRelation(Connection connection, int id, int accommodationId, int roomFairId) throws SQLException {
        String sql = "INSERT INTO accommodation_room_fair_relation (id, accommodation_id, room_fair_id) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setInt(2, accommodationId);
            statement.setInt(3, roomFairId);
            statement.executeUpdate();
        }
    }
}