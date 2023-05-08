package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConfig {
    public static final String url = "jdbc:mysql://localhost:3308/crm_app";
    public static final String username = "root";
    public static final String password = "1234";
    public static Connection getConnection(){
        try{
            // Chỉ định driver sử dụng
            // Class.forName keyword: mysql driver connector class name nhớ thêm .cj để không lỗi
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,username,password);
        }catch (Exception error){
            System.out.println("Error connection database: " + error.getMessage() );
        }
        return null;
    }
}
