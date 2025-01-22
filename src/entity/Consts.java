package entity;

import java.net.URLDecoder;

/**
 * Constants class containing database connection strings, file paths,
 * and SQL queries used throughout the application.
 * This class cannot be instantiated.
 */
public final class Consts {
	private Consts() {
		throw new AssertionError();
	}

	protected static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
	public static final String DATA_DIR = getDataDirPath();
    public static final String SEL_PRODUCTS = "SELECT * FROM TblProducts";
 
    /**
	 * Find the correct path of the DB file
     * @return the path of the DB file (from eclipse or with runnable file)
	 */
	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));
				System.out.println(decoded + "/connection/Tirgul05_north2000.accdb");
				return decoded + "/connection/Tirgul05_north2000.accdb";
			} else {
				decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
				System.out.println(decoded);
				return decoded + "src/connection/Tirgul05_north2000.accdb";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "src/connection/Tirgul05_north2000.accdb"; 
		}
	}
	
	/**
     * Gets the data directory path based on runtime environment
     * @return Path to data directory
     */
	private static String getDataDirPath() {
        try {
            String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decoded = URLDecoder.decode(path, "UTF-8");
            if (decoded.contains(".jar")) {  
                decoded = decoded.substring(0, decoded.lastIndexOf('/'));
                return decoded + "/connection";
            } else {
                decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
                return decoded + "src/connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "src/connection"; 
        }
    }
}

