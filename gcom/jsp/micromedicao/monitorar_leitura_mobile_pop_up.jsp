<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="MonitorarLeituraMobileActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">


function validaForm(form){
	document.forms[0].action ='exibirMonitorarLeituraMobilePopupAction.do';
	document.forms[0].submit();
}

function controlarDivConsumos( div ){
  if ( div.style.display == 'block' ){
	div.style.display = 'none';
  } else {
  	div.style.display = 'block';
  }

}
  
	 


</script>


</head>

<body leftmargin="5" topmargin="5" onload="toggleBox('demodiv',0);">
<html:form action="/monitorarLeituraMobilePopupAction"
	name="ConsultarArquivoTextoLeituraActionForm"
	type="gcom.gui.micromedicao.ConsultarArquivoTextoLeituraActionForm"
	method="post">
	
	<table width="780" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="100%" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<p>&nbsp;</p>
						<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Monitorar Leituras Transmitidas</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">Para listar as leituras já realizadas, 
					informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>		
				
				<tr>
					<td width="18%"><strong>Mês de Referência:</strong></td>
					<td>
						<input type="text" readonly="readonly" style="background-color:#EFEFEF;  
						border:0; text-align:right; color: #000000;" 
						value="<bean:write name="anoMes" scope="session" />" size="7">							
					</td>	

					<td align="right"><strong>Código da Rota:</strong>
						<input type="text" readonly="readonly" style="background-color:#EFEFEF;  
						border:0; text-align:right; color: #000000;" 
						value="<bean:write name="cdRota" scope="session" />" size="7">								
					</td>		
					<td width="10%">&nbsp;</td>
							
				</tr>	
				<tr>
					<td><strong>Leiturista:</strong></td>
					<td colspan="2">
						<input type="text" readonly="readonly" style="background-color:#EFEFEF;  
						border:0; color: #000000;" 
						value="<bean:write name="nomeLeiturista" 
						scope="session" />" size="65">								
					</td>	
				</tr>
				<tr>
						<td><strong>Im&oacute;vel Impresso:</strong></td>
						<td colspan="2">
							<html:radio property="contaImpressa"
								value="1"  />
							Sim
							<html:radio property="contaImpressa"
								value="2" />
							N&atilde;o
							<html:radio property="contaImpressa"
								value="" />
							Todos
						</td>
					</tr>
				<tr>
				
				<tr>
						<td><strong>Tipo Medicão:</strong></td>
						<td colspan="2">
							<html:radio property="tipoMedicao"
								value="1"  />
							Medidos
							<html:radio property="tipoMedicao"
								value="2" />
							Não Medidos
							<html:radio property="tipoMedicao"
								value="" />
							Todos
						</td>
					</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="4" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>		
				<tr>
					<td align="left">
						<input type="button"
							onclick="window.close()" class="bottonRightCol" value="Fechar"
							style="width: 70px;">
					</td>
					<td align="right" colspan="4">
						<input type="button" class="bottonRightCol" value="Listar"
							onClick="validaForm(document.forms[0]);">	
					</td>
				</tr>
				<tr>
				<td colspan="4">		
				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
					cellpadding="0" cellspacing="0">
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center">
						<div style="height:250px;overflow:auto">
						<table>	
							<tr bordercolor="#000000" bgcolor="#90c7fc">
							
								<c:if test="${sessionScope.temPermissao!=null && sessionScope.temPermissao}" >												
									<td bgcolor="#90c7fc">
										&nbsp;
									</td>
								</c:if>
								<td width="20%" bgcolor="#90c7fc">
								<div align="center"><strong>Inscrição</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Matrícula</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Sequencial de Rota</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Leitura Anterior</strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>Leitura Atual</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Anormalidade</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Data e Hora da Leitura</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Data e Hora de Recebimento</strong></div>
								</td>
								<td width="5%" bgcolor="#90c7fc">
								<div align="center"><strong>Impressão</strong></div>
								</td>
						
								<%int cont = 0;%>
								
								<logic:iterate name="colecao" id="helper" scope="session">

								<%cont = cont + 1; if (cont % 2 == 0) {%>
								
									<tr bgcolor="#cbe5fe">
										<%} else {%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										
										<c:if test="${sessionScope.temPermissao!=null && sessionScope.temPermissao}" >												
											<td>
						                        <img border="0" src="imagens/nolines_plus.gif" onclick="javascript:controlarDivConsumos( document.getElementById('divConsumos${helper.idImovel}') );" />
											</td>
											
										</c:if>

										<td align="center" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${helper.dadosCliente}' ); ">
											${helper.inscricao}
										</td>									
										
										<td align="center">
											${helper.idImovel}
										</td>
										<td align="center">
											${helper.sequencialRota}
										</td >
										<td align="center">
											${helper.leituraAnterior}
										</td>
										
										<td align="center">
											${helper.leituraAtual}
										</td >						
												 
										<td align="center">
											${helper.idAnormalidade}
										</td>										
										<td align="center">
											${helper.dtLeitura}
										</td>
										
										<td align="center">
											${helper.dtRecebimento}
										</td>
										
										<td align="center"><a title="${helper.motivoNaoEmissao}">${helper.icEmissaoConta}</td>
									</tr>											
									
										<%if (cont % 2 == 0) {%>								
											<tr bgcolor="#cbe5fe" style="border-width: 0">
										<%} else {%>
											<tr bgcolor="#FFFFFF">
										<%}%>

						            		<td colspan="10">
						            			<div id="divConsumos${helper.idImovel}" style="display: none;">
						            				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
														cellpadding="0" cellspacing="0">						            				
						            					
						            					<%int cont2 = cont % 2; %>
						            					
						            					<%cont2++; if (cont2 % 2 == 0) {%>								
														<tr bgcolor="#cbe5fe" style="border-width: 0">
															<%} else {%>
														<tr bgcolor="#FFFFFF">
															<%}%>
						            					
						            						<td align="center" colspan="10"><strong>&Uacute;ltimos Consumos</strong></td>
						            					</tr>
														
														
						            					<%if (cont2 % 2 == 0) {%>								
														<tr bgcolor="#cbe5fe" style="border-width: 0">
															<%} else {%>
														<tr bgcolor="#FFFFFF">
															<%}%>
						            						<td width="34%" align="center"><strong>Refer&ecirc;ncia</strong></td>
						            						<td width="33%" align="center"><strong>Consumo</strong></td>
						            						<td width="33%" align="center"><strong>Anormalidade</strong></td>
						            					</tr>
						            					
						            					<logic:present name="helper" property="colConsumos">
														<logic:notEmpty name="helper" property="colConsumos">
						            					<logic:iterate property="colConsumos" id="consumo" name="helper">
						            						<%cont2++; if (cont2 % 2 == 0) {%>								
																<tr bgcolor="#cbe5fe" style="border-width: 0">
															<%} else {%>
																<tr bgcolor="#FFFFFF">
															<%}%>
															
						            							<td align="center">${consumo.referenciaFaturamentoFormatado}</td>
						            							<td align="center">${consumo.numeroConsumoFaturadoMes}</td>
						            							<td align="center">${consumo.consumoAnormalidade.id}</td>
															</tr>
						            					
						            					</logic:iterate>
						            					</logic:notEmpty>
						            					</logic:present>
						            				</table>						            		
						            			</div>
						                	</td>
						                	</tr>
									</tr>										
								</logic:iterate>
								</table>
								</div>		
					</td>
				  </tr>
					<tr>
						<td valign="top">
                        	<strong>Quantidade: ${sessionScope.quantidade}</strong>
                        	<a href="javascript:toggleBox('demodiv',1);">
                            	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Leituras Transmitidas"/></a>
	                     </td>
					</tr>				  
				</table>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioMonitorarLeituraMobileAction.do"/>
<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</body>
</html:html>

