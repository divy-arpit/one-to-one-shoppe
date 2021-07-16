package server.utility;

/**
 * This class contains the list of functions which a seller or a customer can perform
 */
public class PossibleChoices {
    public String[] listOfFunctionsForOwner() {
        return new String[]{"1. Get List of Available items",
                "2. Add item to list",
                "3. Remove item from list",
                "4. List of ordered items",
                "5. Exit"};
    }

    public String[] listOfFunctionsForCustomer() {
        return new String[]{"1. Get List of Available items",
                "2.Get list of ordered Items",
                "3. Place Order",
                "4. Exit"};
    }
}
