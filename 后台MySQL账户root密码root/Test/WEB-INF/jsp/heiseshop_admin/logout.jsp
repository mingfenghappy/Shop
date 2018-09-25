<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%  
  session.removeAttribute("user");  
  //response.sendRedirect("/Test/admin/index");  
  out.println("<script>window.parent.parent.location.href ='/Test/admin/login.html'</script>");  
%> 
</body>
</html>