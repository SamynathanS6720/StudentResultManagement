
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
// import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

public class ToperInEachSubjectServlet extends HttpServlet {
    public void service( HttpServletRequest req , HttpServletResponse res ) throws IOException, ServletException {

        // SubjectQuerys subjectFuntion1 = ( SubjectQuerys ) req.getAttribute( "subjectFuntion" );
        String title = "Topers In Each SubjectS" ;

        req.setAttribute("title", title);

        List<String> headingList = new ArrayList<String>() {
            {
                add("Subject ID");
                add("Subject Name");
                add("Student ID");  
                add("Student Name"); 
                add("Marks");
            }
        }; 

        req.setAttribute("tableHeading", headingList);


        SubjectQuerys subjectFuntion = new SubjectQuerys() ;

        List< String > subjectList = subjectFuntion.getTopScorereinEverySubjects();
               
        req.setAttribute("OverallAvgMarkList", subjectList);

        req.getRequestDispatcher("/TableTemplet.jsp").forward(req, res);
    }
}