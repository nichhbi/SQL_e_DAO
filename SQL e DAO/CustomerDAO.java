import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/sakila";
    private static final String USER = "root";
    private static final String PASS = "admin.33!";

    private static final String QUERY_ALL_CATEGORIES_RENTED = 
        "SELECT c.first_name, c.last_name, COUNT(DISTINCT fc.category_id) AS category_count " +
        "FROM customer c " +
        "JOIN rental r ON c.customer_id = r.customer_id " +
        "JOIN inventory i ON r.inventory_id = i.inventory_id " +
        "JOIN film_category fc ON i.film_id = fc.film_id " +
        "GROUP BY c.customer_id " +
        "HAVING COUNT(DISTINCT fc.category_id) = (SELECT COUNT(*) FROM category);";

    private static final String QUERY_ABOVE_AVERAGE_RENTALS = 
        "SELECT c.first_name, c.last_name, COUNT(r.rental_id) AS rental_count " +
        "FROM customer c " +
        "JOIN rental r ON c.customer_id = r.customer_id " +
        "GROUP BY c.customer_id " +
        "HAVING COUNT(r.rental_id) > ( " +
        "   SELECT AVG(rental_count) " +
        "   FROM ( " +
        "      SELECT customer_id, COUNT(rental_id) AS rental_count " +
        "      FROM rental " +
        "      GROUP BY customer_id " +
        "   ) AS customer_rentals " +
        ") " +
        "ORDER BY rental_count DESC;";

    public List<Customer> getCustomersWithAllCategoriesRented() {
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY_ALL_CATEGORIES_RENTED);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int categoryCount = rs.getInt("category_count");

                customers.add(new Customer(firstName, lastName, categoryCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> getCustomersWithAboveAverageRentals() {
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY_ABOVE_AVERAGE_RENTALS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int rentalCount = rs.getInt("rental_count");

                customers.add(new Customer(firstName, lastName, rentalCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
