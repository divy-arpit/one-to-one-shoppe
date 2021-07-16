package server.utility;

import server.models.Product;
import server.core.Strings;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class contains functions which can be used to operate on the list of products table
 */
public class ProductFunctions {
    String tableName = Strings.productTableName;
    private ArrayList<Product> productList;

    //! Get list of items
    public ArrayList<Product> getList(Statement st) throws SQLException {
        String tableName = Strings.productTableName;
        String query = "select * from " + tableName;
        ResultSet resultSet = st.executeQuery(query);
        productList = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String s = resultSet.getString(2);
            int mrp = resultSet.getInt(3);
            int quantity = resultSet.getInt(4);
            Product product = new Product(id, s, mrp, quantity);
            productList.add(product);
        }
        return productList;
    }

    //! add item to the list
    public void addItem(@NotNull Connection con, String item, int mrp, int quantity) throws SQLException {

        String query = "insert into " + tableName + " (productName,mrp,quantity) values(?,?,?);";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, item);
        st.setInt(2, mrp);
        st.setInt(3, quantity);
        int affectedRows = st.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Item added");
        } else {
            System.out.println("An unknown problem occurred");
        }
    }

    //! delete item from list
    public void deleteItem(@NotNull Statement st, String item) throws SQLException {
        String query = "delete from " + tableName + " where productName='" + item + "'";
        int x = st.executeUpdate(query);
        if (x == 0) {
            System.out.println("No item with this index");
        } else {
            System.out.println("Item removed");
        }
    }
}
