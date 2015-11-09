<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Taken disks from user</title>
</head>
<body>
<a href="main_menu">Main menu</a>
<br/>

<br/>

<table border="1">
<c:forEach var="taken_disk" items="${user_taken_disks}">
<tr>
 
 <td>
   ${taken_disk.disk_id}
 </td>

 <td>
	${taken_disk.disk_name}
 </td>
 
 <td>
    ${taken_disk.accounts[0].email}
 </td>
 
 <td>
    <form method="post" action="returnDisk">
      <input type="hidden" name="disk_id" value="${taken_disk.disk_id}" />
      <input type="submit" value="Return disk" />
    </form>
 </td>
 
 </tr>
 </c:forEach>

 
</table>

</body>
</html>