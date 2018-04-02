package regression.com.common;


import org.json.JSONArray;

import java.sql.*;

import static jxl.biff.FormatRecord.logger;

/**
 * Created by mandeep.
 */

public class AzureDatabaseConnectionString {
    private String userName = "";
    private String password = "";

    Connection connection = null;
    private String dbUrl = "jdbc:sqlserver://hostName:1433;database=dabaseName;user={%s};password={%s};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;authentication=ActiveDirectoryPassword";

    public void getConnectionToTestDatabase() throws Exception {
        logger.info("Connect to Test database");
        try {
            logger.info("Connecting to " + dbUrl + " Database");
            connection = DriverManager.getConnection(dbUrl,userName,password);
            logger.info("Connecting to " + dbUrl + " Database");
            logger.info("Successfully connected to Test database");

            logger.info("Connect Schema");
            String schema = connection.getSchema();
            logger.info("Successful connection - Schema: " + schema);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            logger.info("Not able to connect to Test database");
        }
        logger.info("Test not able to connect to database");
    }

    /**
     * Just send '@param query' and return resultSet.
     *
     * @param query All sql query.
     * @return resultSet
     */
    public ResultSet doQuery(String query) {
        ResultSet resultSet = null;
        logger.info("=========================================");
        logger.info("Executing sql query: " + query);
        try {
            logger.info("Connect to DB....");
            getConnectionToTestDatabase();
            Statement statement = connection.createStatement();

            logger.info("Execute query");
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            logger.info("Result set is " + resultSet);
            logger.info("Close Database connection !");
            connection.close();
            logger.info("=========================================");

        } catch (SQLException sqle) {
            throw new RuntimeException("Error SELECT in database. ", sqle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;
    }

}
