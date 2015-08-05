package br.com.tricoli.db;

import br.com.tricoli.core.configuration.Configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * It opens a connection with the SQLite3.
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class ConnSqlite3 implements Closeable{

    private static final Logger log = LoggerFactory.getLogger(ConnSqlite3.class);
    private static Configurations configs = new Configurations();

    private Connection con;

    /**
     * Constructs a new connection from the (@code db) property
     * of {@link br.com.tricoli.core.configuration.Configurations}
     */
    public ConnSqlite3() {
        open(configs.get("db"));
    }

    /**
     * Constructs a new connection from db param
     *
     * @param db The database path
     */
    public ConnSqlite3(String db) {
        open(db);
    }

    private void open(String db){
        log.debug("Create a new connection with the {}. [{}]", db, this.toString());
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlite:" + db);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @return The opened connection
     */
    public Connection getCon(){
        return con;
    }


    @PreDestroy
    public void close() {
        try {
            log.debug("Closing the connection [{}]", this.toString());
            con.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}
