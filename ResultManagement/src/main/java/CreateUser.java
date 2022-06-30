import java.sql.PreparedStatement; 
import java.sql.SQLException;

public class CreateUser {

    public static boolean createLogin( Login loginAccess , String loginType, String userName , String password ){
        DataBaseAccess dbAccess = DataBaseAccess.getAccessobject();
        if( dbAccess.openConnection() )  {
            PreparedStatement pstmt = null ; 
            try{
                dbAccess.setPath( loginType ) ;
                
                String insertQurey = "INSERT INTO " + loginType + "_LOGIN( LOGIN_ID, PASSWORD ) " +
                                     "VALUES(?,?)";
                
                pstmt = dbAccess.getConnectedPreparedStatement( insertQurey );

                pstmt.setString(1, userName );
                pstmt.setString(2, loginAccess.encryptPassword( password ));
                pstmt.executeUpdate() ;
                return true ;
                

            }catch( SQLException ex ){
                ex.printStackTrace();
                return false ;
            }
            finally{
                if( pstmt != null ){
                    try{
                        pstmt.close();
                    }
                    catch( SQLException ex ){}
                    dbAccess.closeConnection() ;
                }   
            }
        }
        else{
            return false ;
        }
    }

    public static String userLogin( Login loginAccess  , String loginType, String userName , String password ){
        String loginStatus = loginAccess.loginValidation( userName , password , loginType );
        return loginStatus ;
    }

}