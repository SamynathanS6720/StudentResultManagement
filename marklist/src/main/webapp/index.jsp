<!DOCTYPE html>   
<html>   
<head>  
<meta name="viewport" content="width=device-width, initial-scale=1">  
<meta http-equiv = "refresh" content = "" />
<title> Login Page </title>  
<style>   
Body {  
  font-family: Calibri, Helvetica, sans-serif;
  align-items: center;  
  background-color: rgb(202, 216, 202);      
}

.row {
  margin-left:-5px;
  margin-right:-5px;
}
  
.column {
  float: left;
  width: 50%;
  padding: 5px;
}

/* Clearfix (clear floats) */
.row::after {
  content: "";
  clear: both;
  display: table;
}

.header h1{
  color: white;
}

.header{
  width: 100%;   
  margin: 5px 5px;  
  padding: 10px 10px;  
  align-items: center;  
  background-color: green;  
}


button {   
        
       background-color: #4CAF50;   
       width: 100%;  
        color: white;   
        padding: 12px 20px;   
        margin: 10px 10px;   
        border: none;   
        cursor: pointer; 
        border-radius: 50px; 
         }   

 input[type=text], input[type=password] {   
        width: 100%;   
        margin: 5px 5px;  
        padding: 10px 10px;   
        display: inline-table ;   
        border: 2px solid green;  
        border-radius: 50px; 
        box-sizing: border-box;   
    }  
 button:hover {   
        opacity: 0.7;   
        border-radius: 50px; 
    }   
  .cancelbtn {   
        width: 100%;   
        padding: 10px 18px;  
        margin: 10px 5px;  
    }   
    .title{
        color: rgb(48, 53, 59);
        background-origin: padding-box;
    }
     
 .container1 { 
        opacity: 0.7;
        width: 150px ;   
        margin: 0px 10px;  
        padding: 12px 100px;    
        background-color: rgb(230, 237, 240);
        border-radius: 40px; 
        position: absolute;
        left: 40%;
    }   
    /* .container2 { 
        opacity: 0.7;
        width: 150px ;   
        margin: 0px 10px;  
        padding: 12px 100px;    
        background-color: rgb(230, 237, 240);
        border-radius: 40px; 
        position: absolute ;
        left: 50%;
    }   */
</style>   
</head>    
<body>   
  <div class="header">
    <center> <h1 class="title"> Login Form </h1> </center>   
  </div> 
    <form action="STAFF" method="post"> 
      <div class = "row">
        <div class = "column">
        <div class="container1">
          <h2>Staff Login</h2>    
            <label>Username  </label>   
            <input type="text" placeholder="Enter Username" name="username" required>  
            <label>Password  </label>   
            <input type="password" placeholder="Enter Password" name="password" required>  
            <button type="submit" name="usertype" value="STAFF">Login</button>   
            <!-- <input type="checkbox" checked="checked"> Remember me    -->
            <!-- <button type="button" class="cancelbtn"> Cancel</button> <br>   -->
             <!-- <a href="#"> Forgot password? </a>    -->
        </div>  
      </div>
        <!-- <div class = "column">
          <div class="container2">   
            <h2>Student Login</h2>
              <label>Username  </label>   
              <input type="text" placeholder="Enter Username" name="username" >  
              <label>Password  </label>   
              <input type="password" placeholder="Enter Password" name="password" >  
              <button type="submit" name="usertype" value="STUDENT">Login</button>   
              <input type="checkbox" checked="checked"> Remember me   
              <button type="button" class="cancelbtn"> Cancel</button> <br>   -->
              <!-- <a href="#"> Forgot password? </a>    -->
          </div>  -->
        </div>
      </div>
    </form>     
</body>     
</html>  