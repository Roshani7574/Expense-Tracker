package com.expensetracker;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Monthly Summary");
            System.out.println("4. Category-wise Report");
            System.out.println("5. Highest Spending Category");
            System.out.println("6. Delete Expense");
            System.out.println("7. Update Expense");
            System.out.println("8. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ExpenseOperations.addExpense();
                    break;
                case 2:
                    ExpenseOperations.viewExpenses();
                    break;
                case 3:
                    ExpenseOperations.monthlySummary();
                    break;
                case 4:
                    ExpenseOperations.categoryWiseReport();
                    break;
                case 5:
                    ExpenseOperations.highestCategory();
                    break;
                case 6:
                    ExpenseOperations.deleteExpense();
                    break;
                case 7:
                    ExpenseOperations.updateExpense();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }


    }
}
