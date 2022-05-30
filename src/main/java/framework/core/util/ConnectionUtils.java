package framework.core.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtils {
    public static ConnectionUtils _INSTANCE = new ConnectionUtils();
    public static Connection CONNECTION;

    public static ConnectionUtils getInstance(){
        return _INSTANCE;
    }

    private ConnectionUtils(){};

    public Connection getConnection(){
        try {
            if(CONNECTION != null && !CONNECTION.isClosed()) {
                return CONNECTION;
            }

            Class.forName(PropertiesReader.getInstance().getProperty("persistence.jdbc.driver"));
            return CONNECTION = DriverManager.getConnection(PropertiesReader.getInstance().getProperty("persistence.jdbc.url"),
                    PropertiesReader.getInstance().getProperty("persistence.jdbc.user"),
                    PropertiesReader.getInstance().getProperty("persistence.jdbc.password"));
        }
        catch (Exception ex){
            throw new RuntimeException("Erro ao conectar no banco de dados", ex);
        }
    }

    public void closeSilenlty(Connection c){
        try {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
        catch(Exception silent){}
    }
}
