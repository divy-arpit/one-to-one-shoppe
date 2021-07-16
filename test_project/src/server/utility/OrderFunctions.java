package server.utility;

import server.core.Strings;
import server.models.OrderedItem;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class contains list of functions which can be used to operate on the ordered_list table
 */
public class OrderFunctions {

    public ArrayList<OrderedItem> getOrderedItems(Statement st) throws SQLException {
        String tableName = Strings.orderTableName;
        String query = "select * from " + tableName;
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<OrderedItem> list = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            int quantity = resultSet.getInt(2);
            OrderedItem orderedItem = new OrderedItem(name, quantity);
            list.add(orderedItem);
        }
        return list;
    }

    public String orderItem(Connection con, String itemName, int quantity) throws SQLException {
        String response = checkAvailable(con, itemName, quantity);
        if (response.equals("not-available")) {
            return "Item not available.";
        } else if (response.equals("less-quantity")) {
            return "We do not have that much quantity.";
        }
        String tableName = Strings.orderTableName;
        String query = "insert into " + tableName + " (item_name,item_quantity) values " + "(?,?);";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, itemName);
        statement.setInt(2, quantity);
        int affectedRows = statement.executeUpdate();
        if (affectedRows > 0) {
            return "Order Placed";

        } else {
            return "Some error occurred";

        }
    }

    private String checkAvailable(Connection con, String itemName, int quantity) throws SQLException {
        String tableName = Strings.productTableName;
        String query = "select * from " + tableName + " where productName= " + "'" + itemName + "'";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int count = 0;
        while (resultSet.next()) {
            int quantityAvailable = resultSet.getInt(4);
            if (quantityAvailable < quantity) {
                return "less-quantity";
            }
            count++;
        }
        if (count > 0) {
            return "available";
        } else {
            return "not-available";
        }

    }
}
