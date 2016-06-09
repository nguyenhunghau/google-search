package faber.business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import faber.dao.KeywordSearchDAO;
import faber.dao.ResultDAO;
import faber.dto.ResultDTO;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class GoogleSearch {

    KeywordSearchDAO keywordSearch = new KeywordSearchDAO();
    ResultDAO resultDao = new ResultDAO();

    //<editor-fold defaultstate="collapsed" desc="IMPORT">
    /**
     * Get Id of keyword
     *
     * @param keyword
     * @return
     */
    public Integer getIdKeyword(String keyword) {
        return keywordSearch.getIdKeyword(keyword);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SAVE RESULT SEARCH">
    /**
     * Save result search
     *
     * @param keryword
     * @param resultSearch
     * @return
     */
    public Boolean saveResultSearch(String keryword, String resultSearch) {
        List<ResultDTO> listResult = new Gson().fromJson(resultSearch, new TypeToken<List<ResultDTO>>() {
        }.getType());
        Integer idKeyword = keywordSearch.saveKeyword(keryword);
        if (idKeyword != -1) {
            return resultDao.saveResultSearch(listResult, idKeyword);
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="IMPORT">
    /**
     * Get result search
     *
     * @param idKeyword
     * @return
     */
    public String getResultSearch(int idKeyword) {
        List<ResultDTO> listResult = resultDao.getListResultSearch(idKeyword);
        return new Gson().toJson(listResult);
    }
    //</editor-fold>

}
