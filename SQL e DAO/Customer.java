public class Customer {
    private String firstName;
    private String lastName;
    private int count; // Questo campo sarà utilizzato per rappresentare sia il numero di categorie noleggiate sia il numero di noleggi

    public Customer(String firstName, String lastName, int count) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.count = count;
    }

    // Getter e setter
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", count=" + count +
                '}';
    }
}
