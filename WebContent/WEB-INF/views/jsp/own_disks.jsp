<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Own disks</title>
</head>
<body>

<a href="main_menu">Main menu</a>
<br/>
<br/>
<form method="post" action="own_disks">
 <input type="text" name="str" />
 <input type="hidden" name="count" value="50"/>
 <input type="hidden" name="position" value="0" />
 <br/>
 <input type="submit" value="Find" />
</form>
<br/>

<table border="1">
<c:forEach var="own_disk" items="${own_disks}">
<tr>
 
 <td>
   ${own_disk.disk_id}
 </td>

 <td>
	${own_disk.disk_name}
 </td>
 

 <td>
    <form method="post" action="removeDisk">
      <input type="hidden" name="disk_id" value="${own_disk.disk_id}" />
      <input type="submit" value="Remove disk" />
    </form>
 </td>
 
 
 <c:if test="${!own_disk.free}">
 <td>
    <form method="post" action="publishDisk">
      <input type="hidden" name="disk_id" value="${own_disk.disk_id}" />
      <input type="submit" value="Publish disk" />
    </form>
 </td>
 </c:if>
 
 <c:if test="${own_disk.free}">
 <td>
    <form method="post" action="unpublishDisk">
      <input type="hidden" name="disk_id" value="${own_disk.disk_id}" />
      <input type="submit" value="Unpublish disk" />
    </form>
 </td>
 </c:if>
 
 
 </tr>
 </c:forEach>

  <tr>
  <td>
   <form method="post" action="createDisk">
      <input type="text" name="disk_name" value="disk_name" />
      <input type="submit" value="Create disk" />
   </form>
  </td>
  <td>
  </td>
  <td>
  </td>
  </tr>
 
</table>

       
</body>
</html>