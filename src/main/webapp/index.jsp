<%@ page import="uk.bl.wap.memento.*" %>
<%
    String jspUrl = request.getRequestURL().toString();
    String error = null;
    MementoSearchBean msb = null;
    String url = request.getParameter("url");
    if ( url == null ) {
    	url = "";
    } else {
    	//msb = new MementoSearchBean();
    	//msb.setUrl(url);
    }
%>
<html>
<head>
    <title>Blue Box: </title>
</head>
<body>
    <img src="${pageContext.request.contextPath}/images/mementologo.png" />
    <form method="GET" action="<%= jspUrl %>">
    <input type="text" name="url" value="<%= url %>"/>
    <input type="submit" value="Engage The TimeGate"/>
    </form>
    
	<p>    
    <a href="${pageContext.request.contextPath}/timegate/query?url=<%= url %>">JSON</a>
    </p>
   
   <% if( msb != null ) { %>
   <ul>
   <% for( MementoBean m : msb.getMementos() ) { %>
     <li><%= m.getUrl() %></li>
   <% } %>
   </ul> 
   <% } %>
   
   
    
	<p>
	   <a href="http://webcitation.org/archive?url=<%= url %>">Click here to use WebCite® to archive <%= url %></a>.
   
	</p>    
     <p>
      Bookmarklet:&#160;<a href="javascript:void(location.href='<%= jspUrl %>?url='+escape(location.href))">[b]</a>
     </p>
     <p>
      For more information on Memento, see&#160;<a href="http://www.mementoweb.org/">www.mementoweb.org</a>.
     </p>
     <p>
     This web interface uses the Memento aggregate TimeGate hosted by&#160;<a href="http://lanl.gov">lanl.gov</a>.<br/>
     This interface was created by the&#160;<a href="http://www.webarchive.org.uk/">UK Web Archive</a>.<br/>
     The source code is&#160;<a href="https://github.com/ukwa/mementoweb-webclient">hosted on GitHub</a>.
     </p>
     
</body>
</html>