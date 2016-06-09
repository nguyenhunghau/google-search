package faber.servlet;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import faber.business.GoogleSearch;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
@WebServlet(name = "GoogleSearchServlet", urlPatterns = {"/GoogleSearchServlet"})
public class GoogleSearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        PrintWriter out = response.getWriter();
        GoogleSearch googleSearch = new GoogleSearch();

        try {
            if (path.equals("/checkKeyword")) {
                String keyword = request.getParameter("keyword");
                int idKeyword = googleSearch.getIdKeyword(keyword);
                out.print(idKeyword);
            }
            if (path.equals("/saveResultSearch")) {
                String json = request.getParameter("json");
                JSONObject jsonObj = new JSONObject(json);
                String keyword = jsonObj.getString("keyword");
                String resultSearch = jsonObj.getString("resultSearch");
                out.print(googleSearch.saveResultSearch(keyword, resultSearch));
            }
            if (path.equals("/getResultSearch")) {
                String idKeyword = request.getParameter("keyword");
                String resultSearch = googleSearch.getResultSearch(Integer.valueOf(idKeyword));
                out.print(resultSearch);
            }
        } catch (JSONException | NumberFormatException e) {

        }
    }

}
