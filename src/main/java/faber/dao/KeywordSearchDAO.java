package faber.dao;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import faber.connection.MySQLConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class KeywordSearchDAO {
    
    //<editor-fold defaultstate="collapsed" desc="GET ID KEYWORD">
    public Integer getIdKeyword (String keyword) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer result = -1;
        
        try {
            String sql = "SELECT * FROM KEYWORD_SEARCH WHERE KEYWORD=?";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, keyword);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("ID");
            }
        } catch (Exception ex) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return result;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="SAVE KEYWORD">
    public Integer saveKeyword (String keyword) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            String sql = "INSERT INTO KEYWORD_SEARCH (KEYWORD, TIME_SAVE) VALUES (?,now())";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, keyword);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                return Integer.parseInt(rs.getLong(1) + "");
            }
            
            return -1;
        } catch (SQLException | NumberFormatException ex) {
            return -1;
        } finally {
            try {
                
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    //</editor-fold>
}
