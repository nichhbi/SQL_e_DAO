import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();

        System.out.println("Customers with rentals in all categories:");
        List<Customer> allCategoriesCustomers = customerDAO.getCustomersWithAllCategoriesRented();
        for (Customer customer : allCategoriesCustomers) {
            System.out.println(customer);
        }

        System.out.println("\nCustomers with above-average rentals:");
        List<Customer> aboveAverageRentalCustomers = customerDAO.getCustomersWithAboveAverageRentals();
        for (Customer customer : aboveAverageRentalCustomers) {
            System.out.println(customer);
        }
    }
}
