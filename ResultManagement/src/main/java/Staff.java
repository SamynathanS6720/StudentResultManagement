
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.NumberFormatException ;
import java.util.List;
import java.util.LinkedList ;

public class Staff {

    private static CommenOperations operation = new CommenOperations() ;
    private static SubjectQuerys subjectFuntion = new SubjectQuerys() ;
    private static StudentQuery studentfuntions = new StudentQuery() ;

    public static boolean staffFunctions( BufferedReader reader , Login loginAccess ) throws IOException {

        boolean flag = true ;
        do{ 
            System.out.println( "\nEnter Option " + 
                                "\n1.Course Details" +                                
                                "\n2.Student Details " +
                                "\n3.Subject Details " +
                                "\n4.Logout" ) ;
            switch( reader.readLine() ){
                    case "1" :
                        Staff.accessCourseDetails( reader , loginAccess ) ;
                        break ;
                    case "2" :
                        Staff.accessStudentDetails( reader ,  loginAccess ) ;
                        break ;
                    case "3" :
                        Staff.accessSubjectDetails( reader ) ;
                        break ;
                    case "4" :
                        flag = false ;
                        break ;
                    default :
                        System.out.println("\nEnter Correct option " );
                        break ; 
            }
        }while( flag );

        return true ;
    }

    public static boolean accessCourseDetails( BufferedReader reader , Login loginAccess ) throws IOException {
        
        String messege ;
        String tableType = "COURSE" ; 

        System.out.println( "\nEnter Option : "+
                            "\n1.Create New Course "+
                            "\n2.View Student List in Course "+ 
                            "\n3.Exit" ) ;
        switch( reader.readLine() ){
            case "1" : 
                System.out.println( "Enter Course Name " );
                String courseName = reader.readLine() ;
                int course_Id ;
                messege = operation.insertData( tableType , courseName ) ;

                try{ 
                    course_Id = Integer.parseInt( messege ) ;
                    System.out.println( "\\Course Added Successfully " +
                                        "\nCourse Id  :  " + course_Id  +
                                        "\nCourse Name :  " + courseName ) ;
                }catch( NumberFormatException ex  ){
                    System.out.println( messege );
                }
                break;
            case "2" :

                break;
            case "3" :

                break ;
        }

        return true ;
    }

    public static boolean accessStudentDetails( BufferedReader reader , Login loginAccess ) throws IOException {
        
        String messege ;
        String userType = "STUDENT" ; 

        System.out.println( "\nEnter Option : "+
                            "\n1.Create New Student "+
                            "\n2.Remove Student " +
                            "\n3.View Student List" +
                            "\n5.Add Student Accodamic Details" +
                            "\n7.Get Student Accodamic Details " +
                            "\n8.View all Student Accodamic Details " ) ;
        
        switch( reader.readLine() ){
            
            case "1" :                
                System.out.println( "\nEnter Student Name : " ) ;
                String stdName = reader.readLine() ;
                int std_Id ;
                messege = studentfuntions.insertData( userType , stdName ) ;
                
                try{ 
                    std_Id = Integer.parseInt( messege ) ;
                }catch( NumberFormatException ex  ){
                    System.out.println( messege );
                    break ;
                }
                
                String userID = stdName + std_Id ; 

                System.out.println( "\nCreate password : " ) ;
                String stdPassword = String.valueOf( System.console().readPassword() ) ;
                System.out.println( "Select Course ");
                System.out.println( studentfuntions.viewList( "Course")  ) ;
    

                if( CreateUser.createLogin( loginAccess , userType , userID , stdPassword ) )
                {
                    studentfuntions.createStudentDetailsSpace( std_Id ) ;
                }
                System.out.println( "\nStudent Added Successfully " +
                                    "\nStudent Id  :  " + std_Id  +
                                    "\nLogin ID : " + userID + 
                                    "\nStudent Name :  " + stdName );
                break ;
            
            case "2" :
                System.out.println( "\nEnter StudentId to remove : " ) ;
                String subjectID = reader.readLine() ;
                System.out.println( studentfuntions.deleteData( userType , subjectID ) );
                break ;
            
            case "3" :
                List< String > subList = studentfuntions.viewList( userType );

                if( subList.isEmpty() ){
                    System.out.println( "No Students Available ");
                    break ;
                }
                System.out.println( "\nStudents_Id \t : \t Students_Name \n"  );
                for( int i = 0 ; i < subList.size() ; i++ ){
                    System.out.println("\n" + subList.get( i ) +"\t : \t" + subList.get(i+1) );
                    i++ ;
                }
                
                break ;
            
            case "4" :
                
            
            case "5" :
                    System.out.println( "Enter Student Id Number   : "  ) ;
                    Integer student_Id ;
                    try{ 
                        student_Id = Integer.parseInt( reader.readLine() ) ;
                    }catch( NumberFormatException ex  ){
                        System.out.println( "Invalid Student ID " );
                        break ;
                    }
                    List< Double > marks = Staff.setStudentAccodamicDetails(reader, student_Id );
                    if( marks == null ){
                        System.out.println( "Failed to Save" ); 
                        break ;
                    }
                    else{
                        if( subjectFuntion.insertMarks( marks , student_Id ) ){
                            System.out.println( "Details Saved Successfully " );  
                        }
                        else{
                            System.out.println( "failed to Save Details" );  
                        }
                    }
                break ;
            
            case "6" :

                break ;
            
            default :
                System.out.println( "Enter Correct Option " ) ;
                break ; 
        }

        return false ;
    }

    public static boolean accessSubjectDetails( BufferedReader reader ) throws IOException {
        
        
        String tableType = "Subject" ;
        String messege ;

        System.out.println( "Enter Option : " +
                            "\n1.Create New Subject" +
                            "\n2.Remove Subject" + 
                            "\n3.View Subject List" + 
                            "\n4.Get Student Marks in all subjects" +
                            "\n5.Get avg marks of each subject" + 
                            "\n6.view Top Scorer in each Subjects" +
                            "\n7.View Student List in rank order " + 
                            "\n8.View the Student scored above Average in each subject" +
                            "\n9.Get the Student count scored above Average in each subject"+
                            "\n10.Exit ;") ;
        switch( reader.readLine() ){
            
            case "1" :
                System.out.println("\nEnter Subject Name : " ) ; 
                String subjectName = reader.readLine() ;
                int sub_ID ;
                messege = subjectFuntion.insertData( tableType , subjectName ) ;
                try{ 
                    sub_ID = Integer.parseInt( messege ) ;
                    System.out.println( "\\Subject Added Successfully " +
                                        "\nSubject Id  :  " + sub_ID  +
                                        "\nSubject Name :  " + subjectName) ;
                }catch( NumberFormatException ex  ){
                    System.out.println( messege );
                }
                break ;
            
            case "2" :
                System.out.println( "\nEnter StudentId to remove : " ) ;
                String subjectID = reader.readLine() ;
                System.out.println( subjectFuntion.deleteData( tableType , subjectID ) );
                break ;
            
            case "3" : 
                List< String > subList = subjectFuntion.viewList( tableType );
                if( subList.isEmpty() ){
                    System.out.println( "No Subjects Available ");
                    break ;
                }
                System.out.println( "\nSubject_Id    :     Subject_Name \n"  );
                for( int i = 0 ; i < subList.size() ; i++ ){
                    System.out.println("\n" + subList.get( i ) +"\t : \t" + 
                                              subList.get(i+1) );
                    i++ ;
                }
                break ;

            case "4" :
                System.out.println( "Enter Student Id Number   : "  ) ;
                Integer studentId ;
                try{ 
                    studentId = Integer.parseInt( reader.readLine() ) ;
                }catch( NumberFormatException ex  ){
                    System.out.println( "Invalid Student ID " );
                    break ;
                }
                List< String > marklist = subjectFuntion.getStudentsMark( studentId );
                if( marklist.isEmpty() ){
                    System.out.println( "No Marks Available ");
                    break ;
                }
                System.out.println( "\nSubject_Id    :    Subject_Name    :   Average marks \n"  );
                for( int i = 0 ; i < marklist.size() ; i++ ){
                    System.out.println("\n" + marklist.get( i ) +"\t : \t" + 
                                              marklist.get(i+1) + "\t : \t " + 
                                              marklist.get(i+2) );
                    i = i+2 ;
                }
                break ;
            
            case "5" :
                List< String > subjectList = subjectFuntion.getAvgMarksOfEverySubjects();
                if( subjectList.isEmpty() ){
                    System.out.println( "No Subjects Available ");
                    break ;
                }
                System.out.println( "\nSubject_Id    :    Subject_Name    :   Average marks \n"  );
                for( int i = 0 ; i < subjectList.size() ; i++ ){
                    System.out.println("\n" + subjectList.get( i ) +"\t : \t" + 
                                              subjectList.get(i+1) + "\t : \t " + 
                                              subjectList.get( i + 2 ) );
                    i = i+2 ;
                }
                break ;
            
            case "6" :
                List< String > toperList = subjectFuntion.getTopScorereinEverySubjects();

                if( toperList.isEmpty() ){
                    System.out.println( "No Subjects Available ");
                    break ;
                }
                System.out.println( "\nStudents_Id    :    Students_Name Subject_Id    :    Subject_Name   :  Top Marks \n"  );
                int i = 0 ;
                while( i < toperList.size() ){
                    System.out.println("\n" + toperList.get( i++ ) + "    :    " + 
                                              toperList.get( i++ ) + "    :    " + 
                                              toperList.get( i++ ) + "    :    " + 
                                              toperList.get( i++ ) + "    :    " +
                                              toperList.get( i++ ) );
                }

                break ;
            case "7" :
                List< String > RankList = subjectFuntion.getStudentListInRankOrder();

                if( RankList.isEmpty() ){
                    System.out.println( "No Students Available ");
                    break ;
                }
                System.out.println( "\nRank   :    Students_Id    :    Students_Name Subject_Id    :    Subject_Name   :  Top Marks \n"  );
                i = 0 ;
                int j = 1 ;
                while( i < RankList.size() ){
                    System.out.println("\n" + j++ + "    :    " + 
                                            RankList.get( i++ ) + "    :    " + 
                                            RankList.get( i++ ) + "    :    " + 
                                            RankList.get( i++ ) );
                }
            case "8" :
                List< String > stdList = subjectFuntion.getaboveAvgScorerinEverySubjects();

                if( stdList.isEmpty() ){
                    System.out.println( "No Students Available ");
                    break ;
                }
                System.out.println( "\nRank   :    Students_Id    :    Students_Name Subject_Id    :    Subject_Name   :  Top Marks \n"  );
                i = 0 ;
                j = 1 ;
                while( i < stdList.size() ){
                    System.out.println("\n" + j++ + "    :    " + 
                                            stdList.get( i++ ) + "    :    " + 
                                            stdList.get( i++ ) + "    :    " +
                                            stdList.get( i++ ) + "    :    " + 
                                            stdList.get( i++ ) );
                }
                break ;
                
            case "9" :
                List< String > subjectCountList = subjectFuntion.getStdCuntAboveAvgMarks();

                if( subjectCountList.isEmpty() ){
                    System.out.println( "No Students Available ");
                    break ;
                }
                System.out.println( "\nRank   :    Students_Id    :    Students_Name Subject_Id    :    Subject_Name   :  Top Marks \n"  );
                i = 0 ;
                j = 1 ;
                while( i < subjectCountList.size() ){
                    System.out.println("\n" + j++ + "    :    " + 
                                            subjectCountList.get( i++ ) + "    :    " + 
                                            subjectCountList.get( i++ ) + "    :    " + 
                                            subjectCountList.get( i++ ) );
                }
                break ;
            default :
                System.out.println( "Enter Correct Option " ) ;
                break ;
        }
        return false ;
    }

    public static List< Double > setStudentAccodamicDetails ( BufferedReader reader , Integer std_id ) throws IOException{  

        List< String > subList = subjectFuntion.viewList( "Subject" );

        String studentStatus =  studentfuntions.validateStudentId(  std_id ) ;
        if( studentStatus.equals( "true" )) {
            List< Double > marks = new LinkedList< Double >() ;
            for( int i = 1 ; i < subList.size() ; i=i+2 ){
                System.out.println( "Enter " + subList.get( i ) + " marks  : " );
                try{
                    // String mark = reader.readLine() ;
                    marks.add( Double.parseDouble( reader.readLine() ) );

                }catch( NumberFormatException ex ){
                    System.out.println( " mark must in number format re-enter mark " );
                    i = i-2;
                }
            }
            if( Staff.finalConfermation() ){
                return marks ;
            }else{
                return null ;
            }
        }
        else if( studentStatus.equals( "false" )) {
            System.out.println( " Student Id not Available " );
            return null ;
        }
        else{
            System.out.println( studentStatus ); 
            return null ;
        }

    }

    public static boolean finalConfermation(){
        boolean flag = true  ;
            System.out.println( "Enter " +
                                "\n1.Save and exit  " + 
                                "\n2,don't Save and exit " ) ;
            switch( System.console().readLine() ){
                case "1" :
                    break ;
                case "2" :
                    flag = false ;
                    break ;
                default :
                    System.out.println( "Enter correct option...! " ) ;
                    finalConfermation() ;
                    break ;
            }
        return flag ;
    }
}
