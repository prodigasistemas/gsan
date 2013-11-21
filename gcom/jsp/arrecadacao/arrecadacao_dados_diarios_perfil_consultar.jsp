<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="java.util.Collection,java.util.Iterator,java.util.Map" %>
<%@ page import="java.math.BigDecimal,gcom.util.Util, gcom.cadastro.imovel.ImovelPerfil" %>
<%@ page import="gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarDadosDiariosArrecadacaoActionForm" dynamicJavascript="false" />
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin
function avancar(){
	document.forms[0].proximoPasso.value = 'avancar';
	enviar();
}

function verificarExibicaoRelatorio() {
	
	toggleBox('demodiv',1);
	
}
-->
</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/consultarDadosDiariosArrecadacaoWizardAction" 
	name="FiltrarDadosDiariosArrecadacaoActionForm"
	type="gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoActionForm"
	method="post">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_filtro.jsp?numeroPagina=5" />
		
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="5" />
		<tr>
			<td width="115" valign="top" class="leftcoltext">
				<div align="center">
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
	
				<%@ include file="/jsp/util/informacoes_usuario.jsp"%>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<%@ include file="/jsp/util/mensagens.jsp"%>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				</div>
			</td>
			<td valign="top" class="centercoltext">
				<table height="100%">
					<tr><td></td></tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Dados Diários</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				<!-- Início do Corpo - Fernanda Paiva -->
        	    <!--header da tabela interna -->
				<br>
                <table width="100%" bgcolor="#99CCFF" dwcopytype="CopyTableRow">
	                <% 
	                Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			   			mapDadosDiariosAnoMes = (Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>>)
			                session.getAttribute("mapDadosDiariosAnoMes");
	                
	                Collection<BigDecimal> colecaoValorTotal = (Collection<BigDecimal>) 
	                	session.getAttribute("colecaoValorTotal");
	                    
	                BigDecimal valorTotalPeriodo = (BigDecimal) session.getAttribute("valorTotalPeriodo");
	                
	                Iterator iter = colecaoValorTotal.iterator();
	                
	                String arrecadador = (String) session.getAttribute("arrecadador");
	                
	                BigDecimal percentual = new BigDecimal(0.0);
	                
	                for(Integer itemAnoMes : mapDadosDiariosAnoMes.keySet()){
						String cor = "#cbe5fe";	
						
						BigDecimal valorAnoMes = (BigDecimal) iter.next();
						
						BigDecimal valorMultiplicacaoPercentual = valorAnoMes.multiply(new BigDecimal("100.00"));
						
						percentual = valorMultiplicacaoPercentual.divide(
								valorTotalPeriodo,2,BigDecimal.ROUND_HALF_UP);
	
						%>
				       <% 
						Map<Integer, FiltrarDadosDiariosArrecadacaoHelper> 
	    	           	 	mapDadosProcessamento = (Map<Integer,FiltrarDadosDiariosArrecadacaoHelper>)
			               		 session.getAttribute("mapDadosProcessamento");
							
							FiltrarDadosDiariosArrecadacaoHelper helperProcessamento = mapDadosProcessamento.get(itemAnoMes);
		    	     	       		%>
	        	      	<% if ( helperProcessamento.getTipoProcessamento() != null 
								&& helperProcessamento.getTipoProcessamento().equals("provisorio") ){
						%>
							<tr>
	        	          	<td colspan=4><strong>Processamento Provisório: 
	        	          	 					<%=helperProcessamento.getUltimoProcessamentoMesInformado()%>
	        	          				 </strong>
							</td>
				                <td colspan="3" bgcolor="#90c7fc">
				                  	<div align="right">
				                  		<strong>M&ecirc;s/Ano: <%=Util.formatarAnoMesParaMesAno(itemAnoMes)%></strong>
				                  		
				                  	</div>
			                  	</td>
		    	    		</tr>
							<tr>
								<td colspan=7>
									<strong>Arrecadador:
										<logic:empty name="arrecadador">TODOS</logic:empty>
										<logic:notEmpty name="arrecadador"><%=arrecadador%></logic:notEmpty>
									</strong>
								</td>
							</tr>

		    	    		<logic:present name="exibirFaturamentoCobrado" scope="session">
		    	    		<tr>
		        	          	<td colspan=7>
		        	          		<strong>Faturamento Cobrado em Conta: 
	       	          	 					<%=helperProcessamento.getFaturamentoCobradoEmConta()%>
	       	          				 </strong>
								</td>
							</tr>
		    	            </logic:present>
						<%	
							} else {
						%>
							<tr>
			        	          	<td colspan=4><strong>Processamento Definitivo: 
			        	          	 					<%=helperProcessamento.getUltimoProcessamentoMesInformado()%>
			        	          				 </strong>
									</td>
					                <td colspan="3" bgcolor="#90c7fc">
					                  	<div align="right">
					                  		<strong>M&ecirc;s/Ano: <%=Util.formatarAnoMesParaMesAno(itemAnoMes)%></strong>
					                  		
					                  	</div>
					                </td>
				    	    </tr>
		    	    		<tr>
								<td colspan=7>
									<strong>Arrecadador:
										<logic:empty name="arrecadador">TODOS</logic:empty>
										<logic:notEmpty name="arrecadador"><%=arrecadador%></logic:notEmpty>
									</strong>
								</td>
							</tr>
		    	    		<tr>

				    	    <logic:present name="exibirFaturamentoCobrado" scope="session">
		    	    		<tr>
		        	          	<td colspan=7>
		        	          		<strong>Faturamento Cobrado em Conta: 
	       	          	 					<%=helperProcessamento.getFaturamentoCobradoEmConta()%>
	       	          				 </strong>
								</td>
							</tr>
							</logic:present>
						
		    	       <%} %>
				        	        <tr bgcolor="#cbe5fe"> 
					        	        <td colspan="7" align="center">
						        	        <table border="0" width="50%" align="center">
						        	        	<tr bgcolor="#cbe5fe">
								        	        <td align="rigth"><strong>Valor:</strong></td>
					    		              		<td align="left">
						    		              		<a href="javascript:abrirPopup('exibirConsultarDadosDiariosValoresDiariosAction.do?referencia=<%=itemAnoMes%>', 475, 800);">
						    		              			<strong><%= Util.formatarMoedaReal(valorAnoMes) %></strong>
						    		              		</a>
					    		              		</td>
						    	            	  	<td align="rigth"><strong>Percentual:</strong></td>
					    	    	    	      	<td align="left"> <strong><%= Util.formatarMoedaReal(percentual)%> %</strong></td>
						        		        </tr>
						        	        </table>
										</td>
				        	        </tr>
				        	        <tr bordercolor="#FFFFFF" bgcolor="#90c7fc""> 
					                	<td  width="38%"> <div align="center" class="style9">
					                	<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"><strong>Perfil</strong></font></div></td>
					                  	<td width="22%"> <div align="center" class="style9">
					                  	<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"><strong>Débitos</strong></font></div></td>
					                  	<td width="22%"> <div align="center" class="style9">
					                  	<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"><strong>Descontos</strong></font></div></td>
					                  	<td width="22%"> <div align="center" class="style9">
					                  	<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor Arrecadado</strong></font></div></td>
					                  	<td width="22%"> <div align="center" class="style9">
					                  	<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"><strong>Devoluções</strong></font></div></td>
					                  	<td width="22%"> <div align="center" class="style9">
					                  	<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"><strong>Arrecadação Líquida</strong></font></div></td>		                  	
					                  	<td width="20%"><div align="center">
					                  	<font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"><strong>% M&ecirc;s</strong></font></div></td>
			            		    </tr>

								<%
								
								for (FiltrarDadosDiariosArrecadacaoHelper helper : mapDadosDiariosAnoMes.get(itemAnoMes)){
								            		    
									ImovelPerfil imovelPerfil = (ImovelPerfil) helper.getItemAgrupado();
									
									boolean ehTODOS = imovelPerfil.getId().intValue() == -1;
									String negrito = ehTODOS ? "font-weight: bold;" : "";
									
									if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";
									%>
									<tr bgcolor="#FFFFFF">
									<%
									} else {
										cor = "#cbe5fe";
									%>
									<tr bgcolor="#cbe5fe">
									<%}%>
				                  <td height="17">
				                  <font color="#000000" style="font-size:9px;<%=negrito%>"
											face="Verdana, Arial, Helvetica, sans-serif">
				                  <%= imovelPerfil.getDescricao()%>
				                  </font>
				                  </td>
					              <td><div align="right">
					              <font color="#000000" style="font-size:9px;<%=negrito%>"
											face="Verdana, Arial, Helvetica, sans-serif">
									<%=Util.formatarMoedaReal(helper.getValorDebitos())%>
									</font></div></td>
					              <td><div align="right"><font color="#000000" style="font-size:9px;<%=negrito%>"
											face="Verdana, Arial, Helvetica, sans-serif">
									<%=Util.formatarMoedaReal(helper.getValorDescontos())%></font></div></td>			                  
					              <td><div align="right">
					              <font color="#000000" style="font-size:9px;<%=negrito%>"
											face="Verdana, Arial, Helvetica, sans-serif">
									<%=Util.formatarMoedaReal(helper.getValorArrecadacao())%></font></div></td>
					              <td><div align="right">
					              <font color="#000000" style="font-size:9px;<%=negrito%>"
											face="Verdana, Arial, Helvetica, sans-serif">
									<%=Util.formatarMoedaReal(helper.getValorDevolucoes())%></font></div></td>
					              <td><div align="right">
					              <font color="#000000" style="font-size:9px;<%=negrito%>"
											face="Verdana, Arial, Helvetica, sans-serif">
									<a href="javascript:abrirPopup('exibirConsultarDadosDiariosValoresDiariosAction.do?referencia=<%=itemAnoMes%>&idPerfil=<%=imovelPerfil.getId()%>', 475, 800);">
									<%=Util.formatarMoedaReal(helper.getValorArrecadacaoLiquida())%></font></div></td>
				                  <td nowrap="nowrap"><div align="right">
				                  <font color="#000000" style="font-size:9px;<%=negrito%>"
											face="Verdana, Arial, Helvetica, sans-serif">
									<%= Util.formatarMoedaReal(helper.getPercentual())%></font></div></td>
				                </tr>
								<% 
							}
							%>
							</table>
					        <p>&nbsp;</p>
					        <p>&nbsp;</p>
			                <table width="100%" bgcolor="#99CCFF" dwcopytype="CopyTableRow">
							<%
					}
	              %>
	            </table>
		        <p>&nbsp;</p>
	            <table width="100%" border="0">
                	<tr>
                        <td width="9%" height="17">&nbsp;</td>
	                    <td width="73%"><div align="right"><strong>Total do Per&iacute;odo:</strong></div>
	                    </td>
        		        <td width="18%" bgcolor="#90c7fc">
		                    <div align="right">
   		              			<strong><%= Util.formatarMoedaReal(valorTotalPeriodo) %></strong>
		                    </div>
                  		</td>
                	</tr>
              	</table>
	            <table width="100%" border="0">
					<tr>
						<td align="right">
							  <div align="right">
							   <a href="javascript:verificarExibicaoRelatorio();">
								<img border="0"
									src="<bean:message key="caminho.imagens"/>print.gif"
									title="Imprimir" /> </a>
							  </div>
						</td>
					</tr>
				</table>
              	<p></p>
              	<p>&nbsp;</p>
				<table width="100%" border="0" align="center">	               
	                <tr>
						<td colspan="3">
							<div align="right"><jsp:include
							page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=5" /></div>
						</td>
					</tr>
	              </table>
              
				<!-- Fim do Corpo - Fernanda Paiva  15/05/2006  -->
				<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDadosDiariosArrecadacaoPerfilAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>