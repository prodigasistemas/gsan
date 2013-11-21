<pg:index>

    <pg:first unless="indexed">
      <a id="numberPrevious" href="javascript:window.location.replace(document.forms[0].caminhoReload.value + '?page.offset=<%=pageNumber%>');">[<%= pageNumber %>]</a>
    </pg:first>

    <pg:skip pages="-10">
      <a id="previous" href="javascript:window.location.replace(document.forms[0].caminhoReload.value + '?page.offset=<%=pageNumber%>');">[Anteriores]</a>
    </pg:skip>

    <pg:pages>
    <%
    	if (pageNumber == currentPageNumber) { 
        %><span id="current" style="font-weight: bold;"><%= pageNumber %></span><%
      } else { 
        %><a id="number<%=pageNumber%>" href="javascript:window.location.replace(document.forms[0].caminhoReload.value + '?page.offset=<%=pageNumber%>');"><%= pageNumber %></a><%
      }
    %>
           
    </pg:pages>

    <pg:skip pages="10">
      <a id="next" href="javascript:window.location.replace(document.forms[0].caminhoReload.value + '?page.offset=<%=pageNumber%>');">[Próximos]</a>
    </pg:skip>

	
    <pg:last unless="indexed">
      <a id="numberNext" href="javascript:window.location.replace(document.forms[0].caminhoReload.value + '?page.offset=<%=pageNumber%>');">[<%= pageNumber %>]</a>
    </pg:last>

  </pg:index>  