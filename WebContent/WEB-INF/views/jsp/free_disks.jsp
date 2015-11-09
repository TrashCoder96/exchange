<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Free disks</title>
</head>
<body>

<a href="main_menu">Main menu</a>

<br/>
<form method="post" action="free_disks">
 <input type="text" name="str" />
 <input type="hidden" name="count" value="50"/>
 <input type="hidden" name="position" value="0" />
 <br/>
 <input type="submit" value="Find" />
</form>
<br/>

<table border="1">
<c:forEach var="free_disk" items="${free_disks}">
<tr>
 
 <td>
   ${free_disk.disk_id}
 </td>

 <td>
	${free_disk.disk_name}
 </td>
 
 <td>
    ${free_disk.owner.email}
 </td>
 
 <td>
    <form method="post" action="takeDisk">
      <input type="hidden" name="disk_id" value="${free_disk.disk_id}" />
      <input type="submit" value="Take disk" />
    </form>
 </td>
 
 </tr>
 </c:forEach>

 
</table>

</body>
</html>