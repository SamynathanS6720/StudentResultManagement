
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

public class MainServlet extends HttpServlet {

    public void doPost( HttpServletRequest req , HttpServletResponse res ) throws IOException, ServletException {
         
        String usertype = req.getParameter("usertype");

        HttpSession session = req.getSession();
        session.setAttribute("user", usertype);

        res.getWriter().println(usertype );

        if ("STAFF".equals(usertype)) {
            RequestDispatcher rd = req.getRequestDispatcher("STAFF");
            rd.forward( req, res);
        } else if ("STUDENT".equals(usertype)) {
            RequestDispatcher rd = req.getRequestDispatcher("STUDENT");
            rd.forward( req, res);
        }
    }
}

