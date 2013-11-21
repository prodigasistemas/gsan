<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="java.util.Collection" %>

<table>
	<tr>
		<td colspan="4">
		<div align="left">
		<div align="left">
		<table width="600" border="0" bgcolor="#90c7fc">
			<tr bgcolor="#90c7fc">
				<td width="50" >
				<div align="center"></div>
				</td>
				<td width="50" >
				<div align="center"><strong>Valor</strong></div>
				<div align="center"></div>
				</td>
				<td width="100" >
				<div align="center"><strong>Acr&eacute;scimos por Impontualidade</strong></div>
				</td>
				<td width="100" >
				<div align="center"><strong>D&eacute;bito Com Acr&eacute;scimo</strong></div>
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td>
				<div align="left"><strong>Contas</strong></div>
				</td>
				<td>
				<div align="right">
					<c:out value="${valorConta}"></c:out>
				</td>
				<td>
				<div align="right">
					<c:out value="${valorAcrescimo}"></c:out>
				</div>
				</td>
				<td>
					<div align="right">
						<c:out value="${valorContaAcrescimo}"></c:out>
					</div>
				</td>
			</tr>
			<tr>
				<td bgcolor="#cbe5fe">
				<div align="left"><strong>Débitos A Cobrar</strong></div>
				</td>
				<td bgcolor="#cbe5fe" align="right">
					<fmt:formatNumber value="${valorDebitoACobrar}" 
						minFractionDigits="2" 
						maxFractionDigits="2" 
						type="number" /> 
				</td>
				<td bgcolor="#cbe5fe" align="right">
					<fmt:formatNumber value="0.0" 
						minFractionDigits="2" 
						maxFractionDigits="2" 
						type="number" /> 
				</td>
				<td bgcolor="#cbe5fe" align="right">
					<fmt:formatNumber value="${valorDebitoACobrar}" 
						minFractionDigits="2" 
						maxFractionDigits="2" 
						type="number" /> 
				</td>
			</tr>
			<tr bgcolor="#cbe5fe">
				<td >
				<div align="left"><strong>Total Geral</strong></div>
				</td>
				<td >
				<div align="right" id="valorContaTotal">
					<c:out value="${valorTotalDebito}"></c:out>
				</div>
				</td>
				<td >
				<div align="right" id="valorAcrescimoTotal">
					<c:out value="${valorTotalAcrescimo}"></c:out>
				</div>
				</td>
				<td >
					<div align="right" id="valorContaAcrescimoTotal">
						<c:out value="${valorTotalDebitoAtualizado}"></c:out>
					</div>
				</td>
			</tr>
		</table>
		</div>
		</div>
		</td>
	</tr>
</table>
<hr>
 <div align="left">
     <div align="left">
       <table>
       	<tr>
       		<td>
       			<strong>Taxa de Juros (%):</strong>
       		</td>
       		<td>
       			<input name="inscricaoImovel2" type="text" value="<fmt:formatNumber value="${contratoManter.taxaJuros}" minFractionDigits="2" maxFractionDigits="2" type="number"/>" disabled="disabled" size="6" maxlength="6">
       		</td>
       	</tr>
       	<tr>
       		<td>
       			 <strong>Valor dos Juros de Parcelamento</strong><strong>:</strong>
       		</td>
       		<td>
       			<input name="inscricaoImovel3" value="<fmt:formatNumber value="${contratoManter.valorJurosParcelamento}" minFractionDigits="2" maxFractionDigits="2" type="number"/>" type="text" disabled="disabled" size="20" maxlength="20">
       		</td>
       	</tr>
       	<tr>
       		<td>
       			<strong>Valor Parcelado</strong><strong>:</strong>
       		</td>
       		<td>
       			<input name="inscricaoImovel" value="<fmt:formatNumber value="${contratoManter.valorTotalParcelado}" minFractionDigits="2" maxFractionDigits="2" type="number"/>" type="text" disabled="disabled" size="20" maxlength="20">
       		</td>
       	</tr>
       </table>
     </div>
   </div>  
  <hr> 
   <br />
   
   <table width="600" border="0" bgcolor="#90c7fc">
                    <tr bgcolor="#90c7fc">
                      <td width="20" ><div align="center"><strong>Parcela / No. Presta&ccedil;&otilde;es</strong></div></td>
                      <td width="50" ><div align="center"><strong>Data de Vencimento</strong></div></td>
                      <td width="50" ><div align="center"><strong>Valor da Parcela</strong></div></td>
                      <td width="50" ><div align="center"><strong>Situa&ccedil;&atilde;o</strong></div></td>
                    </tr>
                  <logic:notEmpty name="collParcelas">
                  <% String corCollParcelas = "#FFFFFF";
                  Collection collParcelas = (Collection)session.getAttribute("collParcelas");
                  	%>
					<logic:iterate name="collParcelas" id="parcela">
	                    <tr bgcolor="<%= corCollParcelas%>">
	                      <td><div align="center"><c:out value="${parcela.numero}"></c:out>/<%=collParcelas.size() %></div></td>
	                      <td><div align="center"><c:out value="${parcela.dataVencimentoFormatada}"></c:out></div></td>
	                      <td><div align="center">
	                      	<fmt:formatNumber value="${parcela.valor}" minFractionDigits="2" maxFractionDigits="2" type="number"/>
                      		</div></td>
	                      <td><div align="center">
	                      		<c:if test="${parcela.prestacaoPaga == true }">
		                      		Paga
	                      		</c:if>
                     		</div></td>
	                    </tr>
	                    <%
	                    	if(corCollParcelas.equals("#FFFFFF")){
	                    		 corCollParcelas = "#cbe5fe";
	                    	}else{
	                    		 corCollParcelas = "#FFFFFF";
	                    	}
	                    %>
					</logic:iterate>
				 </logic:notEmpty>
                </table>               