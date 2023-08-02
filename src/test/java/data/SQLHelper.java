package data;

import lombok.SneakyThrows;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        var SQLCode = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var connection = getConnection();
        var code = runner.query(connection, SQLCode, new ScalarHandler<String>());
        return code;
    }
    @SneakyThrows
    public static String getCreditPaymentStatus() {
        var SQLCode = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var connection = getConnection();
        var code = runner.query(connection, SQLCode, new ScalarHandler<String>());
        return code;
    }

    @SneakyThrows
    public static String getPaymentIdFromDB() {
        var SQLId = "SELECT payment_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        var connection = getConnection();
        var id = runner.query(connection, SQLId, new ScalarHandler<String>());
        return id;
    }
    @SneakyThrows
    public static String getPaymentInfoFromDB() {
        var SQLId = "SELECT transaction_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var connection = getConnection();
        var id = runner.query(connection, SQLId, new ScalarHandler<String>());
        return id;
    }

    @SneakyThrows                   /////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static String getCreditRequestIdFromDB() {
        var SQLId = "SELECT transaction_id FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var connection = getConnection();
        var id = runner.query(connection, SQLId, new ScalarHandler<String>());
        return id;
    }

    @SneakyThrows                   /////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static String getCreditRequestInfoFromDB() {
        var SQLId = "SELECT credit_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var connection = getConnection();
        var id = runner.query(connection, SQLId, new ScalarHandler<String>());
        return id;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConnection();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }

}

