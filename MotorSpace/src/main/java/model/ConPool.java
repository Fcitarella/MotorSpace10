package model;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Logger;


public class ConPool {
    private static DataSource dataSource;
    public static Connection getConnection() throws SQLException{
        if(dataSource==null) {
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties"));
        }catch (IOException io){
            io.printStackTrace();
            return null;
        }

            PoolProperties p = new PoolProperties();

            p.setUrl(prop.getProperty("connection.url"));
            p.setDriverClassName(prop.getProperty("setDriverClassName"));
            p.setUsername(prop.getProperty("database.user"));
            p.setPassword(prop.getProperty("database.password"));
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            dataSource=new DataSource();
            dataSource.setPoolProperties(p);

            }
        return dataSource.getConnection();
        }
    }

