public abstract class MySQL {
    protected static String dbHost = "localhost";
    protected static String dbName = "hr";
    protected static String dbUser = "consoleUser";
    protected static String dbPass = "qwe123$!";
    protected static String useSSL = "false";
    protected static String procBod = "true";

    protected static Connection connection = null;

    protected static final int GET_BY_ID = 10;
    protected static final int GET_BY_ID_AND_ENTITY_TYPE = 11;

    protected static final int GET_COLLECTION = 20;
    protected static final int GET_COLLECTION_AND_ENTITY_TYPE = 21;
    protected static final int INSERT = 10;
    protected static final int UPDATE = 20;
    protected static final int DELETE = 30;

    protected static void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL Driver not Found!" + ex);
        }

        logger.info("MySQL Driver Registered");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" + dbName + "?useSSL=" + useSSL + "&noAccessToProcedureBodies=" + procBod, dbUser, dbPass);
        } catch (SQLException ex) {
            System.out.println("Connection failed!" + ex);
        }

        if (connection != null) {
            System.out.println("Successfully connected to MySQL database");
        } else {
            System.out.println("Connection failed!");
        }


    }
