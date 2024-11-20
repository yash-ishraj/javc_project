import java.sql.*;

public class create {
    public static void main(String[] args) {
        // Database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/myfirstdb"; // Change 'your_database' to your actual DB name
        String username = "root";  // Replace with your MySQL username
        String password = "9835397556";  // Replace with your MySQL password

        // JDBC Connection and Statement
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Load MySQL JDBC Driver (if not using Maven/Gradle)
            // This step may be optional with modern JDBC versions
            Class.forName("com.mysql.cj.jdbc.Driver");  // For newer versions (8.0+)

            // Step 2: Establish the connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully!");

            // Step 3: Create a statement object
            stmt = conn.createStatement();

            // Step 4: Execute a query (SELECT statement)
            String query = "SELECT * FROM student"; // Replace 'your_table' with an actual table name
            rs = stmt.executeQuery(query);

            // Step 5: Process the result set
            while (rs.next()) {
                // Assuming the table has two columns: id (int) and name (String)
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (SQLException se) {
            // Handle SQL errors
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Handle class not found errors
            e.printStackTrace();
        } finally {
            // Step 6: Clean up resources (close connection, statement, result set)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
