import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
// import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

public class AboveAverageList extends HttpServlet {
    public void service( HttpServletRequest req , HttpServletResponse res ) throws IOException, ServletException {

        // SubjectQuerys subjectFuntion1 = ( SubjectQuerys ) req.getAttribute( "subjectFuntion" );
        String title = "Above Average List" ;

        req.setAttribute("title", title);

        List<String> headingList = new ArrayList<String>() {
            {
                add("Student ID");
                add("Student Name");  
                add("Subject ID");
                add("Subject Name"); 
                add("Marks");
            }
        }; 

        req.setAttribute("tableHeading", headingList);


        SubjectQuerys subjectFuntion = new SubjectQuerys() ;

        List< String > subjectList = subjectFuntion.getaboveAvgScorerinEverySubjects();
               
        req.setAttribute("OverallAvgMarkList", subjectList);

        req.getRequestDispatcher("/TableTemplet.jsp").forward(req, res);
    }
}

