
import java.util.LinkedList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet; 

public class SubjectQuerys extends CommenOperations {

    private ResultSet result = null ;

    public SubjectQuerys(){
        super.dbAccess = DataBaseAccess.getAccessobject();
        super.dbAccess.openConnection() ;
    }

    public  boolean insertMarks( List< Double > marks , Integer StudentId ) {
        List < String > subjectList = super.viewList( "SUBJECT" ) ; 

        if( super.dbAccess.openConnection() ){
            try{ 
                super.dbAccess.setPath( "SUBJECT" ) ;
            
                if( subjectList != null  ){
                    String insertQuery ; 
                    
                    insertQuery = "INSERT INTO MARKS ( student_id, SUBJECT_ID , MARKS ) " +
                                       "VALUES " ;
                    for( int i = 0 ; i < marks.size() ; i++ ){
                        insertQuery = insertQuery + "(" + StudentId + "," + subjectList.get( i*2 ) + "," + marks.get( i ) + ")" ;
                        if( ( i+1 ) < marks.size() )
                            insertQuery = insertQuery + "," ;
                    }
                    insertQuery = insertQuery + ";" ;
                                        
                    super.pstmt = super.dbAccess.getConnectedPreparedStatement( insertQuery ) ;

                    super.pstmt.executeUpdate() ;

                    return true ;
                }

            }catch( SQLException ex ){
                return false ;
            } finally{
                super.dbAccess.closeConnection() ;
            }
        }
        return false ;
    }

    public List< String > getStudentListInRankOrder( ) {
        List < String > marks = new LinkedList< String >() ;

        if( super.dbAccess.openConnection() ){
            try{ 

                    String selectQuery ; 
                    
                    selectQuery =  "select  student.student_id, student.student_name,  sum(marks) AS \"total\" " +
                                    "FROM subject.marks,  student.student " +
                                    "WHERE student.student_id = marks.student_Id " +
                                    "group by student.student_id " +
                                    "order by total desc ;" ;
                                        
                    super.pstmt = super.dbAccess.getConnectedPreparedStatement( selectQuery ) ;

                    this.result = super.pstmt.executeQuery() ;
                    int i = 1 ;

                    while( result.next() ) {
                        int studentnameIndex = 1 ,
                            subIDIndex = 2 ,
                            MarkIndex = 3  ;
                        marks.add(String.valueOf( i++ ));
                        marks.add( String.valueOf( result.getInt( studentnameIndex ) ) );
                        marks.add(  result.getString( subIDIndex ) ) ;
                        marks.add( String.valueOf( result.getDouble( MarkIndex ) ) ) ;
                    }

                    return marks ;

            }catch( SQLException ex ){
                return null ;
            } finally{
                super.dbAccess.closeConnection() ;
            }
        }
        return null ;
    }

    public List< String > getStudentsMark( Integer studentId ) {
        List < String > marks = new LinkedList< String >() ; 

        if( super.dbAccess.openConnection() ){
            try{ 

                    String selectQuery ; 
                    
                    selectQuery = "SELECT marks.subject_id , subject.subject_name, marks.marks " +
                                  "FROM subject.marks " +
                                  "left JOIN subject.subject ON marks.subject_id = subject.subject_id " +
                                  "where marks.student_id = ' " + studentId + " '; " ;
                                        
                    super.pstmt = super.dbAccess.getConnectedPreparedStatement( selectQuery ) ;

                    this.result = super.pstmt.executeQuery() ;

                    while( result.next() ) {
                        int studentID = 1 ,
                            subIDIndex = 2 ,
                            MarkIndex = 3 ;
                        marks.add( String.valueOf( result.getInt( studentID ) ) );
                        marks.add( result.getString( subIDIndex ) );
                        marks.add(String.valueOf( result.getDouble( MarkIndex ) ) ) ; 
                    }

                    return marks ;

            }catch( SQLException ex ){
                System.out.println( ex.getMessage() );
                ex.printStackTrace();
                return null ;
            } finally{
                super.dbAccess.closeConnection() ;
            }
        }
        return null ;
    }

    public List< String > getAvgMarksOfEverySubjects() {
        List < String > marks = new LinkedList< String >() ; 

        if( super.dbAccess.openConnection() ){
            try{ 

                    String selectQuery ; 
                    
                    selectQuery =   "SELECT MARKS.subject_id, SUBJECT.SUBJECT_Name, ROUND( AVG( MARKS )::numeric, 3  ) AS \"AVERAGE MARKS\" "+
                                    "FROM SUBJECT.MARKS " +
                                    "left JOIN subject.subject ON MARKS.SUBJECT_ID = SUBJECT.SUBJECT_ID " +
                                    "GROUP BY MARKS.SUBJECT_ID, subject.subject_name " +
                                    "order by marks.subject_id; " ;
                                        
                    super.pstmt = super.dbAccess.getConnectedPreparedStatement( selectQuery ) ;

                    this.result = super.pstmt.executeQuery() ;

                    while( this.result.next() ) {
                        int subIDIndex = 1 ,
                            subjectNameIndex = 2 ,
                            MarkIndex = 3 ;
                        marks.add( String.valueOf( result.getInt( subIDIndex ) ) );
                        marks.add( result.getString( subjectNameIndex )  );
                        marks.add(String.valueOf( result.getDouble( MarkIndex ) ) ) ; 
                    }

                    return marks ;

            }catch( SQLException ex ){
                ex.printStackTrace();
                return null ;
            } finally{
                super.dbAccess.closeConnection() ;
            }
        }else{
        return null ;
        }
    }

    public List< String > getTopScorereinEverySubjects() {
        List < String > topers = new LinkedList< String >() ; 

        if( super.dbAccess.openConnection() ){
            try{ 

                    String selectQuery ; 
                    
                    selectQuery =   "SELECT  marks.subject_Id , subject.subject_name ,  marks.student_id, student.student_name , marks.marks " +
                                    "FROM subject.marks " + 
                                    "JOIN subject.subject ON marks.subject_id = subject.subject_id " +
                                    "JOIN student.student ON marks.student_id = student.student_id " +
                                    "WHERE (marks.subject_id ,marks.marks) IN " +
                                    "(SELECT marks.subject_id, MAX(marks) " +
                                    "FROM subject.marks "+
                                    "GROUP BY marks.subject_id)"+
                                    "order by subject.subject_id;" ;

                    super.pstmt = super.dbAccess.getConnectedPreparedStatement( selectQuery ) ;

                    this.result = super.pstmt.executeQuery() ;

                    while( this.result.next() ) {
                        int subIDIndex = 1 ,
                            subjectNameIndex  = 2 ,
                            studentIDIndex  = 3 ,
                            studentnameIndex = 4 ,
                            MarkIndex = 5 ;
                        topers.add( String.valueOf( result.getInt( subIDIndex ) ) );
                        topers.add( result.getString( subjectNameIndex ) );
                        topers.add( String.valueOf( result.getInt( studentIDIndex ) ) );
                        topers.add( result.getString( studentnameIndex )  );
                        topers.add(String.valueOf( result.getDouble( MarkIndex ) ) ) ; 
                    }
                    
                    return topers ;

            }catch( SQLException ex ){
                ex.printStackTrace(); 
                return null ;
            } finally{
                super.dbAccess.closeConnection() ;
            }
        }else{
            return null ;
        }
    }

    public List< String > getStdCuntAboveAvgMarks() {
        List < String > marks = new LinkedList< String >() ; 

        if( super.dbAccess.openConnection() ){
            try{ 
                String selectQuery ;
                
                selectQuery =   "SELECT marks.student_id, student.student_name, subject.subject_id, subject.Subject_name , marks.marks FROM subject.marks " +
                                "INNER JOIN subject.subject ON marks.subject_id = subject.subject_id " +
                                "INNER JOIN student.student ON marks.student_id = student.student_id " +
                                "JOIN ( 	SELECT marks.subject_id, avg( marks.marks) AS \"avg_mark\" " +
                                        "FROM subject.marks " +
                                        "group by marks.subject_id " +
                                    ") avgtable " +
                                "ON marks.subject_id = avgtable.subject_id " +
                                "WHERE marks.marks > avgtable.avg_mark " +
                                "order by marks.student_id ; " ;
                                
            super.pstmt = super.dbAccess.getConnectedPreparedStatement( selectQuery ) ;

            this.result = super.pstmt.executeQuery() ;

            while( this.result.next() ) {
                int studentIDIndex  = 1 ,
                    studentnameIndex = 2 ,
                    subIDIndex = 3 ,
                    subjectNameIndex = 4 ,
                    markIndex = 5 ;
                marks.add( String.valueOf( result.getInt( studentIDIndex ) ) );
                marks.add( result.getString( studentnameIndex )  );
                marks.add( String.valueOf( result.getInt( subIDIndex ) ) );
                marks.add( result.getString( subjectNameIndex )  );
                marks.add(String.valueOf( result.getDouble( markIndex ) ) ) ; 
            }

                    return marks ;

            }catch( SQLException ex ){
                
                return null ;
            } finally{
                super.dbAccess.closeConnection() ;
            }
        }
        return null ;
    }

    public List< String > getaboveAvgScorerinEverySubjects() {
        List < String > topers = new LinkedList< String >() ; 

        if( super.dbAccess.openConnection() ){
            try{ 

                String selectQuery ; 
                    
                selectQuery = "SELECT  marks.subject_Id , subject.subject_name ,  marks.student_id, student.student_name , marks.marks " +
                              "FROM subject.marks " + 
                              "JOIN subject.subject ON marks.subject_id = subject.subject_id " +
                              "JOIN student.student ON marks.student_id = student.student_id " +
                              "WHERE ( marks.subject_Id , marks.marks ) = ANY ( " +
                                    "select  marks.subject_id,  max(marks) AS \"toper\" "+
                                    "FROM subject.marks " +  
                                    "group by marks.subject_id ) "+
                                    "order by subject.subject_id; " ;

                super.pstmt = super.dbAccess.getConnectedPreparedStatement( selectQuery ) ;

                this.result = super.pstmt.executeQuery() ;

                while( this.result.next() ) {
                    int subIDIndex = 1 ,
                        subjectNameIndex  = 2 ,
                        studentIDIndex  = 3 ,
                        studentnameIndex = 4 ,
                        MarkIndex = 5 ;
                    topers.add( String.valueOf( result.getInt( subIDIndex ) ) );
                    topers.add( result.getString( subjectNameIndex ) );
                    topers.add( String.valueOf( result.getInt( studentIDIndex  ) ) );
                    topers.add( result.getString( studentnameIndex  )  );
                    topers.add(String.valueOf( result.getDouble( MarkIndex ) ) ) ; 
                }
                
                    return topers ;

            }catch( SQLException ex ){
                ex.printStackTrace(); 
                return null ;
            } finally{
                super.dbAccess.closeConnection() ;
            }
        }else{
            return null ;
        }
    }
            
                    
}