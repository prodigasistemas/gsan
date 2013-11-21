<%--<pg:index>
  <font size="2" face="Arial, Helvetica, sans-serif"><div align="center">Páginas de Resultado:
  <pg:prev>&nbsp;<a href="javascript:window.location.search='?pageOffset=<%=pageNumber%>';window.location.replace();">[&lt;&lt; Anterior]</a></pg:prev>
  <pg:pages><%
    if (pageNumber.intValue() < 10) {
      %>&nbsp;<%
    }
    if (pageNumber == currentPageNumber) {
      %><b><%= pageNumber %></b><%
    } else {
      %><a href="javascript:window.location.search='?pageOffset=<%=pageNumber%>';window.location.replace();"><%= pageNumber %></a><%
    }
  %>
  </pg:pages>
  <pg:next>&nbsp;<a href="javascript:window.location.search='?pageOffset=<%=pageNumber%>';window.location.replace();">[Próximo &gt;&gt;]</a></pg:next>
  <br></div></font>
</pg:index>
--%>
<%--
<%
Integer paginaCorrente = (Integer)request.getAttribute("page.offset");
Integer maximoPaginas = (Integer)request.getAttribute("maximoPaginas");
pageContext.setAttribute("pageNumber", paginaCorrente); 

%>

 <pg:index>

    <pg:first>
      <a href="javascript:window.location.search='?page.offset=<%=pageNumber%>';window.location.replace();">[ (<%= pageNumber %>) |&lt; Primeiro ]</a>
    </pg:first>

    <% if ((paginaCorrente - 1) > 1) { %>
    <a href="javascript:window.location.search='?page.offset=<%=(paginaCorrente - 1)%>';window.location.replace();">[ (<%= (paginaCorrente - 1) %>) &lt;&lt; Anterior ]</a>
    <% } %>

    <pg:pages>
    <% 
    
    if (paginaCorrente!=null) {
    	//out.write("!@"+paginaCorrente+"@!");
    	if (paginaCorrente.equals(pageNumber)) { %>
    	    <b><%= pageNumber %></b><%
    	} else { %>
    		<a href="javascript:window.location.search='?page.offset=<%=pageNumber%>';window.location.replace();"><%= pageNumber %></a> 
    	<%
    	}
    } else { %>
	<a href="javascript:window.location.search='?page.offset=<%=pageNumber%>';window.location.replace();"><%= pageNumber %></a> 
	<%
	}	
    	%>

    </pg:pages>


    <% if ((paginaCorrente+1) < maximoPaginas) { %>
    <a href="javascript:window.location.search='?page.offset=<%=(paginaCorrente+1)%>';window.location.replace();">[ Próximo &gt;&gt; (<%=(paginaCorrente+1)%>) ]</a>
    <% } %>


    <pg:last >
      <a href="javascript:window.location.search='?page.offset=<%=pageNumber%>';window.location.replace();">[ Último &gt;| (<%= pageNumber %>) ]</a>
    </pg:last>

  </pg:index>
  
--%>
<pg:index>

    <pg:first unless="indexed">
      <a href="javascript:window.location.search='?page.offset=<%=pageNumber%>';window.location.replace;">[<%= pageNumber %>]</a>
    </pg:first>

    <pg:skip pages="-10">
      <a href="javascript:window.location.search='?page.offset=<%=pageNumber%>';window.location.replace;">[Anteriores]</a>
    </pg:skip>

    <pg:pages>
    <%
    	if (pageNumber == currentPageNumber) { 
        %><b><%= pageNumber %></b><%
      } else { 
        %><a href="javascript:window.location.search='?page.offset=<%=pageNumber%>';window.location.replace;"><%= pageNumber %></a><%
      }
    %>
           
    </pg:pages>

    <pg:skip pages="10">
      <a href="javascript:window.location.search='?page.offset=<%=pageNumber%>';window.location.replace;">[Próximos]</a>
    </pg:skip>

	
    <pg:last unless="indexed">
      <a href="javascript:window.location.search='?page.offset=<%=pageNumber%>';window.location.replace;">[<%= pageNumber %>]</a>
    </pg:last>

  </pg:index>  