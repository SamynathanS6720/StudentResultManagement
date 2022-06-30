
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

public class StaffLoginServlet extends HttpServlet {
    
    private static Login loginAccess = new LoginValidationAndEnciption() ;

    public void doPost( HttpServletRequest req , HttpServletResponse res ) throws IOException, ServletException {
        
        // HttpSession session = req.getSession();

        String usertype =  "STAFF";
        String userName =  req.getParameter("username") ;
        String password =  req.getParameter("password") ;

        HttpSession session = req.getSession();
        
        session.setAttribute("username", userName);

        boolean passwordStatus = UserControl.userLogin( loginAccess  , usertype ,  userName, password).equals("true") ;
        
        if( passwordStatus ){
            InsertDeleteViewOperationinDB operation = new InsertDeleteViewOperationinDB() ;
            SubjectsControlQuery subjectFuntion = new SubjectsControlQuery() ;

            req.setAttribute( "subjectFuntion" ,  subjectFuntion);
            req.setAttribute("commenoperation", operation );

            RequestDispatcher rd = req.getRequestDispatcher("StaffFunctions.jsp");
            rd.forward( req , res );
        }
        else{
            String errorMassege = "Invalid Password";
            req.setAttribute("error",  errorMassege );
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward( req , res );
        }
    
    }
}
