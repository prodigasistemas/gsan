<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="java.util.Collection" %>

<table width="600" border="0" bgcolor="#90c7fc">
     <tr  bgcolor="#90c7fc">
       <td width="119" ><div align="center"><strong> Parcela / No. Presta&ccedil;&otilde;es</strong></div></td>
       <td width="126" ><div align="center"><strong>Valor 
         da Parcela</strong></div></td>
       <td width="127" ><div align="center">
           <blockquote>
             <p><strong>Valor 
               Pago</strong></p>
           </blockquote>
       </div></td>
       <td width="148" ><div align="center"><strong>Data Pagamento</strong></div></td>
        <td width="100" ><div align="center"><strong>Itens Pagos</strong></div></td>
     </tr>
    
      <logic:notEmpty name="collParcelasPagas">
       <% String corParcelasPagas = "#FFFFFF";
       Collection collParcelasPagas = (Collection)session.getAttribute("collParcelasPagas");
       %>
		<logic:iterate name="collParcelasPagas" id="parcela">
             <tr bgcolor="<%= corParcelasPagas%>">
               <td><div align="center"><c:out value="${parcela.numero}"></c:out>/<%= collParcelasPagas.size() %></div></td>
               <td><div align="center"><fmt:formatNumber value="${parcela.valor}" minFractionDigits="2" maxFractionDigits="2" type="number"/></div></td>
            	<td><div align="center"><fmt:formatNumber value="${parcela.valorPagamento}" minFractionDigits="2" maxFractionDigits="2" type="number"/></div></td>
               <td><div align="center"><c:out value="${parcela.dataPagamentoFormatada}"></c:out></div></td>
           		<td><div align="center"><c:out value="${parcela.itensPagos}"></c:out></div></td>
             </tr>
             <%
               	if(corParcelasPagas.equals("#FFFFFF")){
               		corParcelasPagas = "#cbe5fe";
               	}else{
               		corParcelasPagas = "#FFFFFF";
               	}
               %>
		</logic:iterate>
	 </logic:notEmpty>
   </table>