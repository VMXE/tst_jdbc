

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;

public class BatchUpdate {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost/databasename", "guest", "guest");
             PreparedStatement psDDL = conn.prepareStatement(SQL_CREATE);
             PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT);
             PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE)) {

            // commit all or rollback all, if any errors
            conn.setAutoCommit(false); // default true

            psDDL.execute();
        
           /*
            // Run list of insert commands
            psInsert.setString(1, "mkyong");
            psInsert.setBigDecimal(2, new BigDecimal(10));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.addBatch();

            psInsert.setString(1, "kungfu");
            psInsert.setBigDecimal(2, new BigDecimal(20));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.addBatch();

            psInsert.setString(1, "james");
            psInsert.setBigDecimal(2, new BigDecimal(30));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.addBatch();

            int[] rows = psInsert.executeBatch();

            System.out.println(Arrays.toString(rows));

            // Run list of update commands
            psUpdate.setBigDecimal(1, new BigDecimal(999.99));
            psUpdate.setString(2, "mkyong");
            psUpdate.addBatch();

            psUpdate.setBigDecimal(1, new BigDecimal(888.88));
            psUpdate.setString(2, "james");
            psUpdate.addBatch();

            int[] rows2 = psUpdate.executeBatch();

            System.out.println(Arrays.toString(rows2));

            conn.commit();
*/
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static final String SQL_INSERT = "INSERT INTO singleton_claims2" + "(batch_id, claim_id, original_claim_id, bill_amount, deductible_amt, approved_units, value_code1, member_clientid, provider_clientid, bill_from_dt, bill_to_dt, diag_cd1, diagcd_description, proccd_dt) VALUES " +
            " (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?);";

    private static final String SQL_UPDATE = "UPDATE singleton_claims2 SET claim_id=?, original_claim_id=?, bill_amount=?, deductible_amt=?, approved_units=?, value_code1=?, member_clientid=?, provider_clientid=?, bill_from_dt=?, bill_to_dt=?, diag_cd1=?, diagcd_description=?, proccd_dt=?) WHERE claim_id=?";

    private static final String SQL_CREATE = "CREATE TABLE Singleton.Singleton_Claims" 
    	+ "("
    	+ "claim_id VARCHAR(15) NOT NULL,"
    	+ "original_claim_id VARCHAR(15),"
    	+ "bill_amount NUMERIC(10,2),"
    	+ "deductible_amt NUMERIC(10,2),"
    	+ "approved_units VARCHAR(5),"
    	+ "value_code1 VARCHAR(5),"
    	+ "member_clientid VARCHAR(15),"
    	+ "provider_clientid VARCHAR(15),"
    	+ "bill_from_dt TIMESTAMP,"
    	+ "bill_to_dt TIMESTAMP,"
    	+ "diag_cd1 VARCHAR(7),"
    	+ "diagcd_description VARCHAR(1000),"
    	+ "proccd_dt TIMESTAMP,"
    	+ "PRIMARY KEY(Batch_id,claim_id)"
    	+ ")";



}