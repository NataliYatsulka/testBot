import java.sql.*;

public class ConnectionDB {
    private static final String url = "jdbc:mysql://localhost:3306/cities";
    private static final String user = "root";
    private static final String password = "Init123$";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static int connectDb() throws ClassNotFoundException {
        //Class.forName("com.mysql.jdbc.Driver");
        String query = "select count(*) from country";
        int count = 0;

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt(1);
                System.out.println("Total number of books in the table : " + count);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return count;
    }

}
