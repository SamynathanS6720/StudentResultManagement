
import java.sql.PreparedStatement; 
import java.sql.SQLException;
import java.sql.ResultSet; 
import java.util.List;
import java.util.LinkedList; 

public class InsertDeleteViewOperation  {
    
    protected DataBaseAccess dbAccess = null ;
    protected PreparedStatement pstmt = null ;
    protected ResultSet result = null ;
    
    protected String insertData( String insertType , String insertName ){

        this.dbAccess = DataBaseAccess.getAccessobject();
        String messege = null ;
        if( this.dbAccess.openConnection() )  {
            this.pstmt = null ; 
            ResultSet result = null ;

            try{ 
                this.dbAccess.setPath( insertType ) ;

                String insertQuery = "INSERT INTO " + insertType + " ( "+ insertType +"_name )" +
                                    "VALUES ( ? ) RETURNING " + insertType + "_id ;" ;

                this.pstmt = this.dbAccess.getConnectedPreparedStatement( insertQuery ) ;
                this.pstmt.setString( 1, insertName );
                result = this.pstmt.executeQuery() ;

                if( result.next() ){
                    return String.valueOf( result.getInt(1) );
                }
                else{
                    messege = "Failed " ; 
                    return messege ;
                }
            }catch( SQLException ex ){
                messege = ex.getMessage() ;
                return messege ; 
            }
            finally{
                this.dbAccess.closeConnection() ;
            }
        }else{
            messege = "Connection Failed " ; 
            return messege ;
        }

    }

    protected String deleteData( String insertType , String deleteID ) {
        String messege  ;

        this.dbAccess = DataBaseAccess.getAccessobject();
        if( this.dbAccess.openConnection() ){
            this.pstmt = null ; 
            try{ 
                this.dbAccess.setPath( "SUBJECT" ) ;

                String DeleteQuery = "DELETE FROM SUBJECT WHERE " + insertType + "_ID = '" + deleteID + "' " ;

                this.pstmt = this.dbAccess.getConnectedPreparedStatement( DeleteQuery ) ;
                this.pstmt.executeUpdate() ;
                messege = "Deleted successfully " ;
                return messege ;
            }catch( SQLException ex ){
                messege = ex.getMessage() ;
                return messege ; 
            }finally{
                this.dbAccess.closeConnection() ;
            }
        }else{
            messege = "Connection Failed " ; 
            return messege ;
        }

    }

    protected List< String > viewList( String tableType ) {
        List < String > subjectList = new LinkedList<String>() ; 
        this.dbAccess = DataBaseAccess.getAccessobject();
        if( this.dbAccess.openConnection() ){
            try{ 
                this.dbAccess.setPath( tableType ) ;
            
                String selectOuery = "SELECT " + tableType + "_ID," + tableType +  "_NAME FROM " + tableType + " ;" ;

                this.pstmt = this.dbAccess.getConnectedPreparedStatement( selectOuery ) ;

                this.result = this.pstmt.executeQuery() ;

                while( result.next() ) {
                    int idIndex = 1 ;
                    int nameIndex = 2 ;
                    subjectList.add( String.valueOf( result.getInt( idIndex ) ) );
                    subjectList.add( result.getString( nameIndex ) ) ; 
                }
                return subjectList ;

            }catch( SQLException ex ){
                return null ;
            }finally{
                try{
                    if( result != null ){
                        result.close() ;
                    }
                }catch( SQLException ex ){}
                finally{
                    this.dbAccess.closeConnection() ;
                }
            }
        }
        return null ;
    }


}
