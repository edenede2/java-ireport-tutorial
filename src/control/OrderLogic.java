package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;
import entity.Consts;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
// import net.ucanaccess.jdbc.UcanaccessDriver;

/**
 * Customer Orders Report controller.
 */
public class OrderLogic {
	/**Singleton instance.*/
    private static OrderLogic instance;
    
    /**
     * private constructor for singleton purposes.
     */
    private OrderLogic() {}
    
    /**
     * Singleton getter.
     * @return instance of this.
     */
    public static OrderLogic getInstance() {
        if (instance == null)
            instance = new OrderLogic();
        return instance;
    }
    
    /**
     * outputs report at runtime.
     * @return
     */
	public JFrame compileCustomerOrdersReport() {
        // try {
            // Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
            	JasperPrint print = JasperFillManager.fillReport(
            			getClass().getResourceAsStream("../boundary/RptCustomerOrders.jasper"),
                        new HashMap<String, Object>(), conn);
                JFrame frame = new JFrame("Customer Orders Report");
                frame.getContentPane().add(new JRViewer(print));
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.pack();
                return frame;
            } catch (SQLException | JRException | NullPointerException e) {
                e.printStackTrace();
            }
        // } catch (ClassNotFoundException e) {
        //     e.printStackTrace();
        // }
        return null;
    }
	
    /**
     * outputs report in runtime.
     * @return
     */
	public JFrame compileProductOrdersByCountryReport(int productID, String productName) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
            	HashMap<String, Object> params = new HashMap<>();
            	params.put("p1", productID);
            	params.put("productName", productName);
            	JasperPrint print = JasperFillManager.fillReport(
            			getClass().getResourceAsStream("../boundary/RptProductOrdersByCountry.jasper"),
                        params, conn);
                JFrame frame = new JFrame("Product " + productName + "'s Orders By Country");
                frame.getContentPane().add(new JRViewer(print));
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.pack();
                return frame;
            } catch (SQLException | JRException | NullPointerException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
