<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<style>
Body {  
  font-family: Calibri, Helvetica, sans-serif;
  align-items: center;  
  background-color: rgb(202, 216, 202);      
}


h1{
  width: 100%;   
  margin: 5px 5px;  
  padding: 10px 10px;  
  align-items: center; 
  color: white;
  background-color: green;
  border-radius: 3px;
}


div {
  background-color: lightgrey;
  width: 700px;
  border: 3px solid green;
  border-radius: 3px;
  padding: 50px;
  margin: 20px;
  position: absolute;
  left: 20%;
}

#Subject {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  border-radius: 3px;
  width: 100%;
}

#Subject td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#Subject tr:nth-child(even){background-color: #f2f2f2;}

/* #Subject tr:hover {background-color: #ddd;} */

#Subject th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>
</head>
<body>

  <% String title = (String) request.getAttribute("title"); %>
    <center><h1><%= title%></h1></center> 

  <div class= "container">
  <table id="Subject">
  <%
  List tableHeading = (List)request.getAttribute("tableHeading");
  int i = 0 ;
  %>
  <tr>
    <%
    while( i < tableHeading.size() ){
      %>
    <th><%=tableHeading.get(i++)%></th>
    <%}%>
  </tr> 
  
  <%
  List table=(List)request.getAttribute("OverallAvgMarkList");
  i = 0 ;
  while( i < table.size() ){
  %>
  <tr>
    <%
    for( int j = 0 ; j < tableHeading.size() ; j++ ){
      %>
      <td><%=table.get(i++)%></td>
      <%}%> 
  </tr>
  <%}%> 

 
</table>
</div>
</body>
</html>


