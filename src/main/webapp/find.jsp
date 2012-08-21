<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%
    String url = request.getParameter("url");
    if ( url == null ) {
    	url = "";
    }
%>
<z:layout pageTitle="Blue Box">
    <p>Hello, JSP!</p>
</z:layout>