
interface Login {
    String encryptPassword( String password );
    String loginValidation( String username , String enteredpassword  , String loginType );
}
