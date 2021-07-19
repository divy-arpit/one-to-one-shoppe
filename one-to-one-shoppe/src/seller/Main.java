package seller;

import server.core.Strings;
import server.models.OrderedItem;
import server.models.Product;
import server.utility.OrderFunctions;
import server.utility.PossibleChoices;
import server.utility.ProductFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Main class for the seller
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = Strings.url;
        String uname = Strings.uname;
        String password = Strings.password;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();
        Scanner in = new Scanner(System.in);

        //# objects of the function classes
        PossibleChoices possibleChoices = new PossibleChoices();
        ProductFunctions functions = new ProductFunctions();
        OrderFunctions orderFunctions = new OrderFunctions();

        //# All functions performed here
        while (true) {
            System.out.println("Choose what you want to do");
            String[] f = possibleChoices.listOfFunctionsForOwner();
            for (String s : f) {
                System.out.println(s);
            }
            int input = in.nextInt();
            switch (input) {
                case 1 -> {
                    ArrayList<Product> list = functions.getList(st);
                    if (list.size() == 0) {
                        System.out.println("Sorry but we have no products right now");
                    } else {
                        System.out.printf("%10s %10s %5s %10s %5s", "Item number", "Item Name", "Item Id", "Item MRP", "Quantity available");
                        System.out.println();
                        System.out.println("-----------------------------------------------------------------------------");
                        int count = 1;
                        for (Product product : list) {
                            System.out.format("%5s %11s %7s %10s %5s", count++, product.getName(), product.getId(), product.getMrp(), product.getQuantity());
                            System.out.println();
                        }
                        System.out.println("-----------------------------------------------------------------------------");
                    }

                }
                case 2 -> {
                    System.out.println("Enter item you want to add");
                    String s = in.nextLine();
                    s = in.nextLine();
                    System.out.println("Enter M.R.P. of the item");
                    int mrp = in.nextInt();
                    System.out.println("Enter quantity");
                    int quantity = in.nextInt();
                    functions.addItem(con, s, mrp, quantity);

                }
                case 3 -> {
                    System.out.println("Enter name of the product");
                    String product = in.nextLine();
                    product = in.nextLine();
                    functions.deleteItem(st, product);
                }
                case 4 -> {
                    ArrayList<OrderedItem> list = orderFunctions.getOrderedItems(st);
                    if (list.size() == 0) {
                        System.out.println("No orders yet");
                    } else {
                        System.out.printf("%5s %15s %5s", "Item number", "Item name", "Item quantity");
                        System.out.println();
                        System.out.println("------------------------------------------------------------------------------");
                        int count = 1;
                        for (OrderedItem orderedItem : list) {
                            System.out.printf("%5s %10s %5s", count++, orderedItem.getName(), orderedItem.getQuantity());
                            System.out.println();
                        }
                        System.out.println("-----------------------------------------------------------------------------");
                    }
                }
                case 5 -> {
                    System.out.println("Enter name of product you want to update");
                    String name = in.nextLine();
                    name = in.nextLine();
                    System.out.println("What is the new quantity");
                    int x = in.nextInt();
                    functions.updateItemQuantity(st, name, x);
                }
                case 6 -> System.exit(0);
                default -> System.out.println("Wrong operation");
            }
        }

    }
}
