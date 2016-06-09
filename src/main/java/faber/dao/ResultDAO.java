package faber.dao;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import faber.connection.MySQLConnect;
import faber.dto.ResultDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class ResultDAO {

    //<editor-fold defaultstate="collapsed" desc="GET LIST RESULT SEARCH FROM IDKEYWORD">
    public List<ResultDTO> getListResultSearch(Integer idKeyword) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ResultDTO> listResultSearch = new ArrayList<ResultDTO>();

        try {
            String sql = "SELECT * FROM RESULT WHERE ID_KEYWORD=?";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, idKeyword);
            rs = ps.executeQuery();
            while (rs.next()) {
                ResultDTO result = new ResultDTO();
                result.setTitle(rs.getString("TITLE"));
                result.setUrl(rs.getString("URL"));
                result.setDescription(rs.getString("DESCRIPTION"));
                listResultSearch.add(result);
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
        return listResultSearch;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SAVE RESULT SEARCH FROM GOOGLE">
    public Boolean saveResultSearch(List<ResultDTO> listResult, Integer idKeyword) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO RESULT (ID_KEYWORD,TITLE,URL,DESCRIPTION) VALUES (?,?,?,?)";
            con = (Connection) MySQLConnect.getConnection();
            ps = (PreparedStatement) con.prepareStatement(sql);
            for (ResultDTO item : listResult) {
                ps.setInt(1, idKeyword);
                ps.setString(2, item.getTitle());
                ps.setString(3, item.getUrl());
                ps.setString(4, item.getDescription());
                ps.addBatch();
            }
            int[] n = ps.executeBatch();
            if (n.length > 0) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
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
