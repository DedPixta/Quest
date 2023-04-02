package dev.makos.game.tombofmagic.repository;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class LiquibaseChecker {

    public static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
    public static final String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
    public static final String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";
    public static final String LIQUIBASE_CHANGELOG_PATH = "/liquibase/changelog.xml";

    public static void updateDataBase(Configuration configuration) {
        String url = configuration.getProperty(HIBERNATE_CONNECTION_URL);
        String user = configuration.getProperty(HIBERNATE_CONNECTION_USERNAME);
        String password = configuration.getProperty(HIBERNATE_CONNECTION_PASSWORD);

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Map<String, Object> config = new HashMap<>();
            try (connection) {
                Scope.child(config, () -> {
                    Database database = DatabaseFactory
                            .getInstance()
                            .findCorrectDatabaseImplementation(new JdbcConnection(connection));
                    Liquibase liquibase = new Liquibase(
                            "classpath:" + LIQUIBASE_CHANGELOG_PATH,
                            new ClassLoaderResourceAccessor(),
                            database
                    );
                    liquibase.update(new Contexts(), new LabelExpression());
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
