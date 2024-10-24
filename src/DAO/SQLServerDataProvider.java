package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLServerDataProvider {
    private Connection connection = null;
    private final String strServer = "VAN"; // Hoặc địa chỉ IP
    private final String strDatabase = "QLTRUONGMAMNON";
    private final String strUser = "sa";
    private final String strPass = "123";

    public void open() {
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driver);
            String connectionURL = "jdbc:sqlserver://" + strServer + ":1433;databaseName=" + strDatabase +
                                    ";user=" + strUser + ";password=" + strPass +
                                    ";encrypt=true;trustServerCertificate=true";
            connection = DriverManager.getConnection(connectionURL);
            if (connection != null) {
                System.out.println("Kết nối thành công");
            } else {
                System.out.println("Kết nối thất bại!!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            Statement sm = connection.createStatement();
            rs = sm.executeQuery(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        int n = -1;
        try {
            Statement sm = connection.createStatement();
            n = sm.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        
        // Không thực hiện truy vấn nào ở đây

        provider.close();
    }
}
