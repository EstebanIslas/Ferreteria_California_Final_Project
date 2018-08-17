package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    private final String bd = "ferre_cali";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql//localhost:3306/" + bd;
    
    private Connection con = null;

    public Connection getConexion() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 000 " + ex.getMessage());
        }

        return con;

    }
    

}
