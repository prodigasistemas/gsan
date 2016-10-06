<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<%@ page import="gcom.cobranca.bean.OpcoesParcelamentoHelper" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="EfetuarParcelamentoDebitosActionForm" dynamicJavascript="false" />

<script language="JavaScript">
<!--
var bCancel = false; 

function validateEfetuarParcelamentoDebitosActionForm(form) {                                                                   
	if (bCancel) 
		return true; 
	else 
		return validateCaracterEspecial(form); 
} 

function caracteresespeciais () { 
	this.aa = new Array("valorEntradaInformado", "Valor da Entrada possui caracteres especiais.", new Function ("varName", " return this[varName];"));
} 

function enviar(){
	redirecionarSubmit('efetuarParcelamentoDebitosWizardAction.do?action=exibirEfetuarParcelamentoDebitosProcesso3Action&calculaOpcaoParcelamento=1');
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<div id="formDiv">
<html:form action="/efetuarParcelamentoDebitosWizardAction"
	name="EfetuarParcelamentoDebitosActionForm"
	type="gcom.gui.cobranca.EfetuarParcelamentoDebitosActionForm"
	method="post">

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_tela_espera.jsp?numeroPagina=3"/>	

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="numeroPagina" value="3"/>
<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="130" valign="top" class="leftcoltext">
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
		<td width="625" valign="top" class="centercoltext">
	        <table height="100%">
		        <tr><td></td></tr>
	      	</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        	<tr>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
		          <td class="parabg">Efetuar Parcelamento de Débitos</td>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
	    	    </tr>
		    </table>
			<!-- Início do Corpo - Roberta Costa -->
			<p>&nbsp;</p>
			
            <table width="100%" border="0">
				<tr> 
					<td colspan="2">Para efetuar o parcelamento de d&eacute;bitos informe o im&oacute;vel:</td>
				</tr>
				<tr>
				<td width="25%" >&nbsp;</td>
		        <logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cobrancaParcelamentoEfetuar-AbaNegociacao', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
				</tr>
				<tr> 
					<td colspan="2">
			            <table border="0">
							<tr> 
								<td>
									<strong>Matr&iacute;cula do Im&oacute;vel:</strong>
								</td>
								<td>
									<html:text property="matriculaImovel" size="10"
										readonly="true" style="background-color:#EFEFEF; border:0" />
								</td>
							</tr>
			            </table>
					</td>
				</tr>
				<tr> 
					<td colspan="2">
			            <table border="0" bgcolor="#99CCFF" width="100%">
							<tr bgcolor="#99CCFF">
								<td colspan="2" align="center"><strong>Dados do Imóvel</strong></td>
							</tr>
							<tr>
								<td>
									<table border="0" bgcolor="#cbe5fe" width="100%">
										<tr> 
											<td width="32%"><strong>Inscri&ccedil;&atilde;o do Im&oacute;vel:</strong></td>
											<td>
												<html:text property="inscricaoImovel" size="25"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr> 
											<td><strong>Cliente Usu&aacute;rio:</strong></td>
											<td>
												<html:text property="nomeCliente" size="45"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr> 
											<td ><strong>CPF ou CNPJ:</strong></td>
											<td >
												<html:text property="cpfCnpj" size="45"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr> 
											<td ><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
											<td >
												<html:text property="situacaoAgua" size="45"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr> 
											<td ><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de Esgoto:</strong></td>
											<td >
												<html:text property="situacaoEsgoto" size="45"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr> 
											<td><strong>Perfil do Imóvel:</strong></td>
											<td>
												<html:text property="descricaoPerfilImovel" size="45"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><br>
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td align="center">
									<strong>Endere&ccedil;o do Im&oacute;vel</strong>
								</td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td align="center" bgcolor="#FFFFFF" height="20">
									<span id="endereco">
										<logic:present name="EfetuarParcelamentoDebitosActionForm" property="endereco">
											<bean:write name="EfetuarParcelamentoDebitosActionForm" 
												property="endereco"/>
										</logic:present>
									</span>	
									<logic:notPresent name="EfetuarParcelamentoDebitosActionForm" property="endereco">
									&nbsp;
									</logic:notPresent>													
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="23"><br>
						<font color="#000000">
							<strong>Valor dos Descontos:</strong>
						</font>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" bgcolor="#99CCFF">
							<tr bgcolor="#90c7fc">
								<td align="center">
									<strong>Por Faixa de Referência da Conta</strong>
								</td>  
								<td align="center">
									<strong>Nos Acréscimos por Impontualidade</strong>
								</td>
								<td align="center">
									<strong>Por Antiguidade do Débito</strong>
								</td>
								<td align="center">
									<strong>Por Inatividade da Ligação de Água</strong>
								</td>
								<td align="center">
									<strong>Por Sanções Regulamentares</strong>
								</td>
								<td align="center">
									<strong>Por Tarifa Social</strong>
								</td>
								
							</tr>
							<tr bgcolor="#cbe5fe">
								<td align="right" bgcolor="#FFFFFF">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
											property="descontoFaixaReferenciaConta"/>
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
											property="descontoAcrescimosImpontualidade"/>
									<html:hidden property="percentualDescontoAcrescimosImpontualidade"/>			
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
											property="descontoAntiguidadeDebito"/>
									<html:hidden property="percentualDescontoAntiguidadeDebito"/>			
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
											property="descontoInatividadeLigacaoAgua"/>
									<html:hidden property="percentualDescontoInatividadeLigacaoAgua"/>			
									<html:hidden property="parcelamentoPerfilId"/>			
								</td>
								
								<td align="right" bgcolor="#FFFFFF">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
											property="descontoSancoesRDEspecial"/>
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
											property="descontoTarifaSocialRDEspecial"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr> 
					<td colspan="2">
						<table>
							<tr>  
								<td><strong>Valor Total dos Descontos:</strong></td>
								<td>
									<html:text property="valorTotalDescontos" size="20" readonly="true" 
										style="background-color:#EFEFEF; border:0; text-align:right"/>
								</td>
							</tr>
						</table>
					</td>			
				</tr>
				<tr><td colspan="2"><hr></td></tr>
				<tr>
					<td colspan="2" height="23">
						<table width="100%">
							<tr>  
								<td><strong>Opção de Pagamento à Vista:</strong></td>
								<logic:equal name="habilitaPagamentoAVista" value="1">
									<td align="right">
										
										<logic:equal name="EfetuarParcelamentoDebitosActionForm" property="indicadorContasRevisao" value="1">
											<input type="button" name="" value="Pagamento à Vista" class="bottonRightCol" 
											onclick="window.location.href='<html:rewrite page="/gerarRelatorioExtratoDebitoAction.do?parcelamento=1&indicadorContasRevisao=1&RD="/><bean:write name="EfetuarParcelamentoDebitosActionForm" property="resolucaoDiretoria"/>'"/>
										</logic:equal>
									
										<logic:notEqual name="EfetuarParcelamentoDebitosActionForm" property="indicadorContasRevisao" value="1">
											<input type="button" name="" value="Pagamento à Vista" class="bottonRightCol" 
											onclick="window.location.href='<html:rewrite page="/gerarRelatorioExtratoDebitoAction.do?parcelamento=1&indicadorContasRevisao=2&RD="/><bean:write name="EfetuarParcelamentoDebitosActionForm" property="resolucaoDiretoria"/>'"/>
										</logic:notEqual>
										
									</td>
								</logic:equal>
								
								<logic:notEqual name="habilitaPagamentoAVista" value="1">
									<td align="right">
										<input type="button" name="" value="Pagamento à Vista" class="bottonRightCol" 
										disabled="true"										
										/>
									</td>
								</logic:notEqual>
								
							</tr>
						</table>
					</td>			
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" bgcolor="#99CCFF">
							<tr bgcolor="#90c7fc">  
								<td align="center">
									<strong>Valor Total do Débito Atualizado</strong>
								</td>
								<td align="center">
									<strong>Valor Total dos Impostos</strong>
								</td>
								
								<td align="center">
									<strong>Valor do Desconto para Pagamento à Vista</strong>
								</td>
								<td align="center">
									<strong>Valor do Pagamento à Vista</strong>
								</td>
							</tr>
							<tr bgcolor="#cbe5fe"> 
							
							<logic:equal name="habilitaPagamentoAVista" value="1">
								<td align="right" bgcolor="#FFFFFF">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
											property="valorDebitoTotalAtualizado"/>
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
											property="valorTotalImpostos"/>
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
											property="valorDescontoPagamentoAVista"/>
								</td>
								<td align="right" bgcolor="#FFFFFF">
									<bean:write name="EfetuarParcelamentoDebitosActionForm" 
											property="valorPagamentoAVista"/>
								</td>
							</logic:equal>
							
							<logic:notEqual name="habilitaPagamentoAVista" value="1">
								<td align="right" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								<td align="right" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								<td align="right" bgcolor="#FFFFFF">
									&nbsp;
								</td>
							</logic:notEqual>
							
								
							</tr>
						</table>
					</td>
				</tr>
				<tr><td colspan="2"><hr></td></tr>
				<tr>
					<td colspan="2" height="23">
						<table width="100%">
							<tr>  
								<td><strong>Opção de Pagamento Parcelado:</strong></td>
							</tr>
						</table>
					</td>			
				</tr>
				<tr> 
					<td width="18%"><strong>Valor da Entrada:</strong></td>
					<td>
						<html:hidden property="valorEntradaInformadoAntes"/>
						<html:text property="valorEntradaInformado" maxlength="20" size="20" 
							onkeyup="formataValorMonetario(this,20)" style="text-align: right;"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name="" value="Calcular" class="bottonRightCol" onClick="enviar();"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="23">
						<font color="#000000">
							<strong>Para efetuar o parcelamento selecione a opção desejada:</strong>
						</font>
					</td>
				</tr>
				<%int cont = 0;%>
				<%int cor = 0;%>															
															
				
				<tr>
					<td colspan="2">
						<table width="100%" bgcolor="#99CCFF">
							<logic:empty name="colecaoOpcoesParcelamento" scope="session">
							<tr bgcolor="#90c7fc">  
								<td align="center" width="5%">
									&nbsp;
								</td>
								<td align="center">
									<strong>Parcelas</strong>
								</td>
								<td align="center">
									<strong>Valor da Entrada</strong>
								</td>
								<td align="center">
									<strong>Valor da Parcela</strong>
								</td>
								<td align="center">
									<strong>Taxa de Juros (%)XXX</strong>
								</td>
							</tr>
							</logic:empty>
							<logic:notEmpty name="colecaoOpcoesParcelamento" scope="session">
				 			<%if (((Collection) session.getAttribute("colecaoOpcoesParcelamento")).size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_OPCAO_PARCELAMENTO) {%>
							<tr bgcolor="#90c7fc">  
								<td align="center" width="5%">
									&nbsp;
								</td>
								<td align="center">
									<strong>Parcelas</strong>
								</td>
								<td align="center">
									<strong>Valor da Entrada</strong>
								</td>
								<td align="center">
									<strong>Valor da Parcela</strong>
								</td>
								<td align="center">
									<strong>Taxa de Juros (%)XXX</strong>
								</td>
							</tr>
							<logic:iterate name="colecaoOpcoesParcelamento" type="OpcoesParcelamentoHelper" id="opcoesParcelamento">
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
							<%} else {%>
							<tr bgcolor="#FFFFFF">
							<%}%>
								<td align="center" width="5%">
									<% if ((((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()) != null 
											&& ((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()
												.equals(((OpcoesParcelamentoHelper)opcoesParcelamento).getQuantidadePrestacao().toString())){ %>
										<input type="radio" name="indicadorQuantidadeParcelas" value="${opcoesParcelamento.quantidadePrestacao}" checked="true"/>
									<% } else { %>
										<input type="radio" name="indicadorQuantidadeParcelas" value="${opcoesParcelamento.quantidadePrestacao}"/>
									<% } %>
								</td>
								<td align="center">
									<bean:write name="opcoesParcelamento" property="quantidadePrestacao"/>
								</td>
								<td align="right">
									<bean:write name="opcoesParcelamento" property="valorEntradaMinima" formatKey="money.format"/>
								</td>
								<td align="right">
									<bean:write name="opcoesParcelamento" property="valorPrestacao" formatKey="money.format"/>	
								</td>
								<td align="right">
									<bean:write name="opcoesParcelamento" property="taxaJuros" formatKey="money.format"/>	
								</td>
							</tr>
							</logic:iterate>
							<% }else{ %>
							<tr bgcolor="#90c7fc">  
								<td align="center" width="5%">
									&nbsp;
								</td>
								<td align="center" width="12%">
									<strong>Parcelas</strong>
								</td>
								<td align="center" width="28%">
									<strong>Valor da Entrada</strong>
								</td>
								<td align="center" width="29%">
									<strong>Valor da Parcela</strong>
								</td>
								<td align="center">
									<strong>Taxa de Juros (%)XXX</strong>
								</td>
							</tr>
							<tr>
								<td height="100" colspan="6">
									<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%">
											<logic:iterate name="colecaoOpcoesParcelamento" type="OpcoesParcelamentoHelper" id="opcoesParcelamento">
											<%cont = cont + 1;
											if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
											<%} else {%>
											<tr bgcolor="#FFFFFF">
											<%}%>
											
											
											<logic:equal name="opcoesParcelamento" property="indicadorValorPrestacaoMaiorValorLimite" value="2">
												<td align="center" width="5%">
													<% if ((((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()) != null 
														&& ((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()
															.equals(((OpcoesParcelamentoHelper)opcoesParcelamento).getQuantidadePrestacao())){ %>
														<input type="radio" name="indicadorQuantidadeParcelas" 
															value="${opcoesParcelamento.quantidadePrestacao}" checked="true"/>
													<% } else { %>
														<input type="radio" name="indicadorQuantidadeParcelas" 
															value="${opcoesParcelamento.quantidadePrestacao}"/>
													<% } %>
												</td>
												<td align="center" width="12%">
													<bean:write name="opcoesParcelamento" property="quantidadePrestacao"/>
												</td>
												<td align="right" width="29%">
													<bean:write name="opcoesParcelamento" property="valorEntradaMinima" formatKey="money.format"/>
												</td>
												<td align="right" width="30%">
													<bean:write name="opcoesParcelamento" property="valorPrestacao" formatKey="money.format"/>	
												</td>
												<td align="right">
													<bean:write name="opcoesParcelamento" property="taxaJuros" formatKey="money.format"/>	
												</td>
											</logic:equal>
											
											
											<logic:equal name="opcoesParcelamento" property="indicadorValorPrestacaoMaiorValorLimite" value="1">
												<%cor = 1;%>		
											
												<td align="center" width="5%">
													<% if ((((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()) != null 
														&& ((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()
															.equals(((OpcoesParcelamentoHelper)opcoesParcelamento).getQuantidadePrestacao())){ %>
														<input type="radio" name="indicadorQuantidadeParcelas" 
															value="${opcoesParcelamento.quantidadePrestacao}" checked="true"/>
													<% } else { %>
														<input type="radio" name="indicadorQuantidadeParcelas" 
															value="${opcoesParcelamento.quantidadePrestacao}"/>
													<% } %>
												</td>
												<td align="center" width="12%">
													<font color="#ff0000"> 
														<bean:write name="opcoesParcelamento" property="quantidadePrestacao"/>
													</font>
												</td>
												<td align="right" width="29%">
													<font color="#ff0000"> 
														<bean:write name="opcoesParcelamento" property="valorEntradaMinima" formatKey="money.format"/>
													</font>
												</td>
												<td align="right" width="30%">
													<font color="#ff0000"> 
														<bean:write name="opcoesParcelamento" property="valorPrestacao" formatKey="money.format"/>	
													</font>
												</td>
												<td align="right">
													<font color="#ff0000"> 
														<bean:write name="opcoesParcelamento" property="taxaJuros" formatKey="money.format"/>	
													</font>
												</td>
											</logic:equal>
											
												
											</tr>
											</logic:iterate>
										</table>
			           				</div>
		           				</td>
	           				</tr>
							<% } %>
							</logic:notEmpty>
						</table>
					</td>
				</tr>
				
								<% if (cor == 1) {%>
									<tr>
										<td colspan="2">
											<font color="#FF0000">Valor da parcela maior que Tarifa Social / Tarifa Mínima</font>
										</td>
									</tr>
								<%}%>
								
								
								<logic:equal name="opcoesParcelamento" property="indicadorExisteParcelamentoEmAndamento" scope="session" value="1">
									<tr>
										<td colspan="2">
											<font color="#FF0000">Imovél com parcelamento ativo, permitido apenas pagar à vista com esta RD.</font>
										</td>
									</tr>
								</logic:equal>
				
				<tr><td><br></td></tr>
				<tr>
					<td colspan="2">
						<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar_tela_espera.jsp?numeroPagina=3"/>
					</td>
				</tr>			
			</table>
			<p>&nbsp;</p>
			<!-- Fim do Corpo - Roberta Costa -->
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>