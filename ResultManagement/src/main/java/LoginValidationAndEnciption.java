
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement; 
import java.sql.SQLException;

public class LoginValidationAndEnciption implements Login {

    public String loginValidation( String username , String enteredpassword  , String loginType ){
        DataBaseAccess dbAccess = DataBaseAccess.getAccessobject();
        if( dbAccess.openConnection() ) {
            Statement stmt = null ;
            PreparedStatement pstmt = null ; 
            ResultSet result = null ;
            try{
                stmt = dbAccess.getConnectedStatement() ;

                dbAccess.setPath( loginType ) ;
                
                String selectQurey = "SELECT  Login_id, password FROM " + loginType + "_login" + 
                                    " WHERE Login_id = '" + username +"';"; 

                pstmt = dbAccess.getConnectedPreparedStatement( selectQurey ) ;

                result = pstmt.executeQuery();
                
                String savedpassword ;
                if( result.next() ){
                    int passwordIndex = 2 ; 
                    savedpassword = result.getString( passwordIndex ) ;
                    String resultMessege ;
                    if( savedpassword.equals( encryptPassword( enteredpassword ) ) ) {  
                        resultMessege = "true" ; 
                        return resultMessege ;
                    }
                    else{
                        resultMessege = "false" ; 
                        return resultMessege ;
                    }
                }
                else{
                    String errorMessege = "UserName / possword incorrect " ;
                    return errorMessege ;
                }

            }catch( SQLException ex ){
                String errorMessege = ex.getClass().getName()+": "+ ex.getMessage() ;
                return errorMessege ;
            }finally{
                try{
                    if( stmt != null ){
                        stmt.close(); 
                    }
                    if( pstmt != null ){
                        pstmt.close();
                    }
                }catch( SQLException ex ){}
                dbAccess.closeConnection() ;
            }
        }
        else{
            String errorMessege = "Connection Failed" ;
            return errorMessege ;
        }
    }

    public String encryptPassword( String password ){
        
        char[] passwordArray = new char[ (int) password.length() ]; 
        for( int i = 0 ; i < password.length() ; i++ ){
            if( password.charAt(i) == 126 ){
                passwordArray[i] = (char)33 ;
            }
            else if( password.charAt(i) == 125 ){
                passwordArray[i] = (char)32 ;
            }
            else{
                passwordArray[i] = (char) ( password.charAt(i) + 2 ) ;
            }
        }
        String encryptedPassword = String.valueOf( passwordArray ) ;
        return encryptedPassword ;
    }

}


