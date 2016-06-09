package faber.business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import com.google.gson.Gson;
import java.net.IDN;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class Ranking {

    //<editor-fold defaultstate="collapsed" desc="HANLDE URL">
    /**
     * Hanlde url
     * @param url
     * @param type
     * @return
     */
    public String handleUrl(String url, String type) {
        Map<String, String> mapResult = new HashMap<String, String>();
        String result = "";
        try {
            if (type.equals("1")) {
                String domain = getDomainName(URLDecoder.decode(url, "UTF-8"));
                String domainCode = IDN.toASCII(domain);
                mapResult.put("url", domain);
                mapResult.put("urlCode", domainCode);
            } else {
                String urlCode = formatUrl(URLDecoder.decode(url, "UTF-8"));
                if (urlCode.length() < 63) {
                    urlCode = IDN.toASCII(urlCode);
                }
                mapResult.put("url", URLDecoder.decode(url, "UTF-8"));
                mapResult.put("urlCode", urlCode);
            }
            result = new Gson().toJson(mapResult);
        } catch (Exception e) {
        }
        return result;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="IMPORT">
    /**
     * Get domain from a url
     * @param url
     * @return
     */
    private String getDomainName(String url) {

        String domainName = url;
        int index = domainName.indexOf("://");
        if (index != -1) {
            // keep everything after the "://"
            domainName = domainName.substring(index + 3);
        }
        index = domainName.indexOf('/');

        if (index != -1) {
            // keep everything before the '/'
            domainName = domainName.substring(0, index);
        }
        domainName = domainName.replaceFirst("^www.*?\\.", "");

        return domainName;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="FORMAT URL">
    /**
     * Change url with correct format
     *
     * @param url
     * @return
     */
    public String formatUrl(String url) {
        String result = url;
        while (result.matches(".*[#/?]$")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
    //</editor-fold>
}
