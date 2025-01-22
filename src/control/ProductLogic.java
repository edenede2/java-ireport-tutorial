package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
// import net.ucanaccess.jdbc.UcanaccessDriver;

import entity.Consts;
import entity.Product;

public class ProductLogic {
	private static ProductLogic instance;
	
	private ProductLogic() {}
	
	public static ProductLogic getInstance() {
		if (instance == null)
			instance = new ProductLogic();
		return instance;
	}
	
	public ArrayList<Product> getProducts() {
		ArrayList<Product> result = new ArrayList<>();
	
		// No need for Class.forName(...) if the JDBC driver implements JDBC 4 auto-registration
		try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
			 PreparedStatement stmt = conn.prepareStatement(Consts.SEL_PRODUCTS);
			 ResultSet rs = stmt.executeQuery()) {
	
			System.out.println("Connected");
	
			while (rs.next()) {
				System.out.println("Found product");
				int i = 1;
				result.add(new Product(rs.getInt(i++), rs.getString(i)));
			}
	
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			e.printStackTrace();
		}
	
		return result;
	}
}
