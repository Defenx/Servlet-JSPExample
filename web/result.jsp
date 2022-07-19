<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.company.ConstantsJSP" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.function.DoubleFunction" %>
<%@ page import="java.util.stream.Collectors" %>
<html>
<head>
<title>Result page</title>
</head>
<body>


    <%=request.getAttribute((ConstantsJSP.OPERATION_NAME))%>(
    <%=Arrays.stream((double[]) request.getAttribute(ConstantsJSP.STAT_NAME))
                .mapToObj(Double::toString)
                .collect(Collectors.joining(", "))
    %>
	) is <%=request.getAttribute((ConstantsJSP.RESULT_NAME))%>
	<br />
	<a href="<c:url value='/'/>">Main</a>
</body>
</html>