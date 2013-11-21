<pg:index>
  <font size="2" face="Arial, Helvetica, sans-serif"><div align="center">Páginas de Resultado:
  <pg:prev>&nbsp;<a href="<%= pageUrl %>">[&lt;&lt; Anterior]</a></pg:prev>
  <pg:pages><%
    if (pageNumber.intValue() < 10) {
      %>&nbsp;<%
    }
    if (pageNumber == currentPageNumber) {
      %><b><%= pageNumber %></b><%
    } else {
      %><a href="<%= pageUrl %>"><%= pageNumber %></a><%
    }
  %>
  </pg:pages>
  <pg:next>&nbsp;<a href="<%= pageUrl %>">[Próximo &gt;&gt;]</a></pg:next>
  <br></div></font>
</pg:index>
