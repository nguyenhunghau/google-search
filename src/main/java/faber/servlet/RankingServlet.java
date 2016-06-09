package faber.servlet;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import faber.business.Ranking;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class RankingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        PrintWriter out = response.getWriter();
        Ranking ranking = new Ranking();

        try {
            if (path.equals("/handleUrl")) {
                String json = request.getParameter("json");
                JSONObject jsonObject = new JSONObject(json);
                String url = jsonObject.getString("url");
                String type = jsonObject.getString("type");
                String result = ranking.handleUrl(url, type);
                out.print(result);
            }
        } catch (Exception e) {

        }
    }

}
