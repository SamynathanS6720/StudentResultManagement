
// import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
// import java.util.List ;
// import java.util.LinkedList ;

public class StudentRelatedQuery extends InsertDeleteViewOperation {

    // private DataBaseAccess dBaseAccess = null ;
    // private PreparedStatement pstmt = null ;
    private ResultSet result = null ;

    public StudentRelatedQuery(){
        super.dbAccess = DataBaseAccess.getAccessobject();
        super.dbAccess.openConnection() ;
    }

    public String validateStudentId( Integer stdID ){
        
        if( super.dbAccess.openConnection() ){
            try{ 

                super.dbAccess.setPath( "STUDENT" ) ;
                String checkQuery = "SELECT STUDENT_NAME FROM STUDENT WHERE STUDENT_ID = " + stdID + ";" ;
                super.pstmt = super.dbAccess.getConnectedPreparedStatement( checkQuery ) ;
                this.result = super.pstmt.executeQuery() ;
                if( this.result.next() ){
                    return "true" ;
                }else {
                    return "false";
                }

            }catch( SQLException ex ){
                // ex.printStackTrace();
                return ex.getMessage() ; 
            }finally{
                super.dbAccess.closeConnection() ;
            }
        }
        else{
            return "Connection faild" ; 
        }
    }

    public boolean createStudentDetailsSpace( int std_Id ){
        
        if( super.dbAccess.openConnection() ){
            try{ 
                super.dbAccess.setPath( "STUDENT" ) ;

                String insertQuery = " BEGIN; " +
                                    //  "INSERT INTO COURSE. ( student_id) SELECT student_id FROM STUDENT; "+
                                     "INSERT INTO STUDENT_ID_PROOF ( student_id) SELECT student_id FROM STUDENT; "+
                                     "INSERT INTO MOBILE ( student_id) SELECT student_id FROM STUDENT; "+
                                     "INSERT INTO ADDRESS ( student_id) SELECT student_id FROM STUDENT; "+
                                     "INSERT INTO STUDENT_RESULT ( student_id) SELECT student_id FROM STUDENT; "+
                                     "COMMIT;" ;

                super.pstmt = super.dbAccess.getConnectedPreparedStatement( insertQuery ) ;
                super.pstmt.executeUpdate() ;
                return true ;
               
            }catch( SQLException ex ){
                // ex.printStackTrace();
                return false ; 
            }finally{
                super.dbAccess.closeConnection() ;
            }
        }else{
            return false ; 
        }
    }

}
