package provider;

import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceProvider {

    private static MariaDbDataSource dataSource;

    public static DataSource getDataSource() throws SQLException {

        if (dataSource == null) {
            initDataSource();
        }
        return dataSource;
    }

    private static void initDataSource() throws SQLException {
        Properties jdbcProperties = DataSourceProvider.loadProperties();
        if(dataSource == null){
            dataSource = new MariaDbDataSource();
            dataSource.setUrl(jdbcProperties.getProperty("jdbc.url"));
            dataSource.setUser(jdbcProperties.getProperty("jdbc.user"));
            dataSource.setPassword(jdbcProperties.getProperty("jdbc.password"));
        }

    }

    private static Properties loadProperties() {
        try (InputStream input = DataSourceProvider.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            if (input == null) {
                throw new IllegalStateException("Properties file not found.");
            }
            Properties configuration = new Properties();
            configuration.load(input);
            return configuration;
        } catch (IOException e) {
            throw new RuntimeException("Problem when reading the properties file.", e);
        }
    }

}