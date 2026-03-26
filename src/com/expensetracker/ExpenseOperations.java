package com.expensetracker;

import java.sql.*;
import java.util.Scanner;

public class ExpenseOperations {

    public static void addExpense() {
        try {
            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            sc.nextLine(); // buffer clear

            System.out.print("Enter category: ");
            String category = sc.nextLine();

            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            String query = "INSERT INTO expenses(amount, category, date) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setDouble(1, amount);
            pst.setString(2, category);
            pst.setString(3, date);

            pst.executeUpdate();

            System.out.println("Expense added successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void viewExpenses() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM expenses");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getDouble("amount") + " | " +
                    rs.getString("category") + " | " +
                    rs.getDate("date")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void monthlySummary() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT SUM(amount) AS total FROM expenses WHERE MONTH(date) = MONTH(CURDATE()) AND YEAR(date) = YEAR(CURDATE())";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                System.out.println("Total expense this month: " + rs.getDouble("total"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void categoryWiseReport() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT category, SUM(amount) AS total FROM expenses GROUP BY category";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("category") + " : " + rs.getDouble("total"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void highestCategory() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT category, SUM(amount) AS total FROM expenses GROUP BY category ORDER BY total DESC LIMIT 1";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                System.out.println("Highest spending category: " + rs.getString("category"));
                System.out.println("Amount: " + rs.getDouble("total"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void deleteExpense() {
        try {
            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Expense ID to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM expenses WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Expense deleted successfully!");
            } else {
                System.out.println("Expense ID not found!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void updateExpense() {
        try {
            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Expense ID to update: ");
            int id = sc.nextInt();

            sc.nextLine(); // clear buffer

            System.out.print("Enter new amount: ");
            double amount = sc.nextDouble();

            sc.nextLine();

            System.out.print("Enter new category: ");
            String category = sc.nextLine();

            System.out.print("Enter new date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            String query = "UPDATE expenses SET amount=?, category=?, date=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setDouble(1, amount);
            pst.setString(2, category);
            pst.setString(3, date);
            pst.setInt(4, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Expense updated successfully!");
            } else {
                System.out.println("Expense ID not found!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }



}
