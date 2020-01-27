package xyz.ariesfish.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component("connectionUtils")
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    @Autowired
    private DataSource dataSource;

    public Connection getThreadConnection() {
        try {
            // first get from ThreadLocal
            Connection conn = tl.get();

            // has connection?
            if (conn == null) {
                // get from data source and then save into ThreadLocal
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // unbind thread and connection
    public void removeConnection() {
        tl.remove();
    }
}
