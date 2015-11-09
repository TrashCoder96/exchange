<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="own_disks">Own disks</a>
<br/>
<a href="free_disks">Free disks</a>
<br/>
<a href="taken_disks">Taken disks</a>
<br/>
<a href="user_taken_disks">Users</a>
<br/>
<a href="<c:url value="j_spring_security_logout" />" >Logout</a>

</body>
</html>