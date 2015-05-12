<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"
	isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.faturamento.conta.Conta"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@ page import="gcom.cadastro.cliente.ClienteImovel"%>
<%@ page import="gcom.gui.*"%>
<%@ page import="gcom.util.ConstantesSistema" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ConsultarImovelActionForm"
	dynamicJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!--
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
        	
      form.idImovelDebitos.value = codigoRegistro;
	  form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDebitosAction&indicadorNovo=OK&limparForm=S'
	  form.submit();
    }else if (tipoConsulta == 'manterCliente') {
            
  	  form.action = 'exibirAtualizarClienteAction.do?idRegistroAtualizacao=' + codigoRegistro + '&linkSucesso=exibirConsultarImovelAction.do?menu=sim';
  	  form.submit();
    }else if (tipoConsulta == 'manterImovel') {
        
      form.action = 'filtrarImovelAction.do?idImovel=' + codigoRegistro + '&atualizarFiltro=1&linkSucesso=exibirConsultarImovelAction.do?menu=sim';
      form.submit();
    }
    
}

function limparForm(){
   	var form = document.forms[0];
	form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDebitosAction&limparForm=OK'
	form.submit();
}

function verificarExibicaoRelatorio() {
	var form = document.forms[0];
	
	 if (form.idImovelDebitos.value.length > 0 && form.matriculaImovelDebitos.value.length > 0) {
		toggleBox('demodiv',1);
	} else {
		alert('Informe Im&oacute;vel');
	}
	
}

function limparImovelTecla() {

	var form = document.forms[0];
	
	form.matriculaImovelDebitos.value = "";
	form.situacaoAguaDebitos.value = "";
	form.situacaoEsgotoDebitos.value = "";

}

function gerarExtratoDebito() {

	var form = document.forms[0];
	
		if (form.matriculaImovelDebitos.value != null && form.matriculaImovelDebitos.value != "") {
			form.action = 'gerarRelatorioExtratoDebitoAction.do?consultarDebitoImovel=1';
			form.submit();
		} else {
			alert("Pesquise o im&oacute;vel");
		}
	

}


function mostrarPopUp(idImovel){
	
	if(idImovel != null && idImovel != "" 
		&& '${sessionScope.abrirPopupPROMAIS}' != null && '${sessionScope.abrirPopupPROMAIS}' == 'TRUE' ){				

		var strParametros="dialogHeight: " + 750 + "px; dialogWidth: " + 750 + "px; Help:no; Status:no; Center:yes;";

		window.showModalDialog('exibirAtualizarDadosClientesPopupAction.do?atualizaImovel='+idImovel, "", strParametros);
	}	
}

function redirecionarManterImovel(idImovel){
	urlRedirect = "exibirAtualizarImovelAction.do?promais=sim&idRegistroAtualizacao="+idImovel;
	redirecionarSubmit(urlRedirect);
}

function redirecionarManterCliente(idcliente){
	urlRedirect = "exibirAtualizarClienteAction.do?promais=sim&idRegistroAtualizacao="+idcliente+"&idImovel="+document.forms[0].idImovelDebitos.value;
	redirecionarSubmit(urlRedirect);
}


function habilitaMatricula() {
	var form = document.forms[0];
	
	if (form.idImovelDebitos.value != null && form.matriculaImovelDebitos.value != null && form.matriculaImovelDebitos.value != "" &&
	form.matriculaImovelDebitos.value != "IM&oacute;VEL INEXISTENTE"){
	
		form.idImovelDebitos.disabled = true;
	} else {
		form.idImovelDebitos.disabled = false;
	}
}

function pesquisarImovel() {
	var form = document.forms[0];
 	
 	if (form.idImovelDebitos.disabled ) {
 		alert("Para realizar uma pesquisa de im&oacute;vel &eacute; necess&aacute;rio apagar o im&oacute;vel atual.")
	} else {
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800)
	}
}



//-->
</script>
<style>
#demoDiv{
	position: absolute; 
	top: 515px; 
	left: 56px; 
	visibility:hidden
}
</style>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:habilitaMatricula();setarFoco('idImovelDebitos');mostrarPopUp(document.forms[0].idImovelDebitos.value);">
<html:form action="/exibirConsultarImovelAction.do"
	name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm" method="post"
	onsubmit="return validateConsultarImovelActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=5" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	

	<table width="770" border="0" cellspacing="5" cellpadding="0">
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

			<td valign="top" class="centercoltext"><!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">&nbsp;</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
						    <td>
							    <table width="100%" align="center" bgcolor="#99CCFF" border="0">
								    <tr>
									    <td align="left" width="4%">
											<img border="0" width="25" height="25"
												src="<bean:message key="caminho.imagens"/>informacao.gif"
												onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); "/>
									    </td>						    						
										<td align="center" width="96%"><strong>Dados do Im&oacute;vel<logic:present name="imovelExcluido" scope="request"><font color="#ff0000"> (Exclu&iacute;do)</font></logic:present></strong></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td bordercolor="#000000" width="25%">
										<strong>Im&oacute;vel:<font color="#FF0000">*</font></strong>
									</td>
									
									<td width="75%" colspan="3">
										<html:text property="idImovelDebitos" 
												   maxlength="9" 
												   size="9"
												   onkeypress="validaEnterComMensagem(event, 'consultarImovelWizardAction.do?action=exibirConsultarImovelDebitosAction&indicadorNovo=OK&limparForm=S','idImovelDebitos','Im&oacute;vel');return isCampoNumerico(event);" 
												   onkeyup="limparImovelTecla();"
												   />
										<a href="javascript:pesquisarImovel();">
									<img width="23" height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Im&oacute;vel"/></a>
										<logic:present
										name="idImovelDebitosNaoEncontrado" scope="request">
										<html:text property="matriculaImovelDebitos" size="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											title="Localidade.Setor.Quadra.Lote.Sublote"/>

									</logic:present> <logic:notPresent
										name="idImovelDebitosNaoEncontrado" scope="request">
										<logic:present name="valorMatriculaImovelDebitos"
											scope="request">
											<html:text property="matriculaImovelDebitos" size="40"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:present>
										<logic:notPresent name="valorMatriculaImovelDebitos"
											scope="request">
											<html:text property="matriculaImovelDebitos" size="40"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:notPresent>
									</logic:notPresent> <a href="javascript:limparForm();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>

								</tr>
								<tr>
									<td height="10">
									<div class="style9"><strong>Situa&ccedil;&atilde;o de &Aacute;gua:</strong></div>
									</td>
									<td><html:text property="situacaoAguaDebitos" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
									<td width="146"><strong>Situa&ccedil;&atilde;o de Esgoto:</strong></td>
									<td width="123"><html:text property="situacaoEsgotoDebitos"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				
<tr>
					<td colspan="4">
					<table width="100%" border="0">
						<tr>
							<td colspan="4">
							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
								<tr bordercolor="#79bbfd">
									<td colspan="6" align="center" bgcolor="#79bbfd"><strong>Clientes</strong></td>
								</tr>
								<tr bordercolor="#000000">
									<td width="25%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Nome do
									Cliente</strong> </font></div>
									</td>
									<td width="17%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo da
									Rela&ccedil;&atilde;o</strong> </font></div>
									</td>
									<td width="19%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
									In&iacute;cio Rela&ccedil;&atilde;o</strong></font></div>
									</td>
									<td width="15%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Telefone</strong></font>
									</div>
									</td>
									<td width="15%" bgcolor="#90c7fc" align="center"><font
										color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>CPF/CNPJ</strong>
									</font></td>
									<td width="9%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Ativo</strong></font>
									</div>
									</td>
								</tr>
								<tr>
									<td width="100%" colspan="6">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%" align="left" bgcolor="#99CCFF">
										<!--corpo da segunda tabela-->
										<%int cont = 0;%>
										<logic:notEmpty name="imovelClientes">
											<logic:iterate name="imovelClientes" id="imovelCliente"
												type="ClienteImovel">
												<%cont = cont + 1;
			if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>
													<td width="25%" bordercolor="#90c7fc" align="left">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelCliente" property="cliente">
														<a
															href="javascript:abrirPopup('exibirConsultarClienteAction.do?desabilitarPesquisaCliente=SIM&codigoCliente='+<bean:write name="imovelCliente" property="cliente.id" />, 500, 800);">
														<bean:write name="imovelCliente" property="cliente.nome" />
														</a>
													</logic:present> </font></div>
													</td>
													<td width="17%" align="left">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:present
														name="imovelCliente" property="clienteRelacaoTipo">
														<bean:write name="imovelCliente"
															property="clienteRelacaoTipo.descricao" />
													</logic:present> </font></div>
													</td>
													<td width="19%" align="center">
													<div class="style9"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
													<bean:write name="imovelCliente"
														property="dataInicioRelacao" formatKey="date.format" /></td>

													<td width="15%" align="right"><logic:notEmpty
														name="imovelCliente" property="cliente">
														<bean:define name="imovelCliente" property="cliente"
															id="cliente" />
														<logic:notEmpty name="cliente" property="clienteFones">
															<bean:define name="cliente" property="clienteFones"
																id="clienteFones" />
															<logic:iterate name="clienteFones" id="clienteFone">
																<div class="style9">
																<div align="center"><font color="#000000"
																	style="font-size:9px"
																	face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
																<bean:write name="clienteFone" property="dddTelefone" />
																</div>
															</logic:iterate>
														</logic:notEmpty>
													</logic:notEmpty></td>
													<td width="15%" align="right"><font color="#000000"
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
														name="imovelCliente" property="cliente.cpf">
														<bean:write name="imovelCliente"
															property="cliente.cpfFormatado" />
													</logic:notEmpty> <logic:notEmpty name="imovelCliente"
														property="cliente.cnpj">
														<bean:write name="imovelCliente"
															property="cliente.cnpjFormatado" />
													</logic:notEmpty> </font></td>
													
													<td width="9%" align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
														${ (imovelCliente.cliente.indicadorUso != null) ? (imovelCliente.cliente.indicadorUso == 1 ? "SIM" : "N&atilde;O" ) : "" }
													</font></td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
									</div>
									</td>
								</tr>

							</table>
							</td>
						</tr>
				
				</table>
				

				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<%String cor = "#cbe5fe";%>
						<%cor = "#cbe5fe";%>
						<tr bordercolor="#79bbfd">
							<td colspan="9" align="center" bgcolor="#79bbfd">
							<strong>Contas</strong>
							</td>
						</tr>
						<logic:notEmpty name="colecaoContaValores" scope="session">
							<%if ( ((Integer)session.getAttribute("totalContas"))  <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<tr bordercolor="#000000">
								<td width="9%" bgcolor="#90c7fc" align="center">
						<% } else { %>
							<tr bordercolor="#000000">
								<td width="20%" bgcolor="#90c7fc">
						<% } %>
									<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<strong>M&ecirc;s/Ano</strong>
										</font>
									</div>
								</td>
								
								<td width="12%" bgcolor="#90c7fc">
									<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<strong>Vencimento</strong>
										</font>
									</div>
								</td>
								
								<td width="8%" bgcolor="#90c7fc">
									<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<strong>Valor de &Aacute;gua </strong> 
										</font>
									</div>
								</td>
								
								<td width="8%" bgcolor="#90c7fc">
									<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<strong>Valor de Esgoto</strong> 
										</font>
									</div>
								</td>
								
								<td width="8%" bgcolor="#90c7fc">
									<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<strong>Valor dos <br> D&eacute;bitos</strong> 
										</font>
									</div>
								</td>
								
								<td width="10%" bgcolor="#90c7fc">
									<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<strong>Valor dos Creditos</strong> 
										</font>
									</div>
								</td>
								
								<td width="10%" bgcolor="#90c7fc">
								  <div align="center" class="style9">
								    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								      <strong>Valor dos	Impostos</strong> 
								    </font>
								  </div>
								</td>
								
								<td width="8%" bgcolor="#90c7fc">
									<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<strong>Valor da Conta</strong> 
										</font>
									</div>
								</td>
								
								<td width="7%" bgcolor="#90c7fc">
									<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<strong>Acr&eacute;sc. Impont.</strong>
											<strong></strong> 
										</font>
									</div>
								</td>
								
								<td width="8%" bgcolor="#90c7fc">
									<div align="center" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<strong>Sit.</strong>
										</font>
									</div>
								</td>
							</tr>
							
							<tr>
								<td height="auto" colspan="10">
								<div style="width: 100%; max-height: 100px; overflow: auto;">
								<table width="100%">
									<logic:present name="colecaoContaValores">
										<logic:iterate name="colecaoContaValores"
											id="contavaloreshelper">
											<%if (cor.equalsIgnoreCase("#cbe5fe")) { cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>
												
												<td width="9%" align="left">
													<div align="left" class="style9">
														<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta"/>
																		<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																		<bean:define name="contavaloreshelper" property="conta" id="conta" /> <bean:write name="conta" property="formatarAnoMesParaMesAno"/>
																	</a>
																</logic:notEmpty>
															</font>
														</logic:empty>
		
														<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" />
																		<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																		<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																		<font color="#ff0000">
																			<bean:write name="conta" property="formatarAnoMesParaMesAno" />
																		</font> 
																	</a>
																</logic:notEmpty> 
															</font>
														</logic:notEmpty>
													</div>
												</td>
												
												<td width="12%" align="left">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="left" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta" id="conta" />
																	<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="left" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta" id="conta" />
																	<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorAgua" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorAgua" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorEsgoto" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorEsgoto" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<logic:notEqual name="contavaloreshelper" property="conta.debitos" value="0">
																		<a href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" />
																			<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			<bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" /> 
																		</a>
																	</logic:notEqual>
														
																	<logic:equal name="contavaloreshelper" property="conta.debitos" value="0">
																		<bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" />
																	</logic:equal>
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>

													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<logic:notEqual name="contavaloreshelper" property="conta.debitos" value="0">
																		<a href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" />
																			<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			<font color="#ff0000">
																				<bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" /> 
																			</font>
																		</a>
																	</logic:notEqual>
																	<logic:equal name="contavaloreshelper" property="conta.debitos" value="0">
																		<bean:write name="contavaloreshelper" property="conta.debitos" formatKey="money.format" />
																	</logic:equal>
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>

												<td width="10%" align="right">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<logic:notEqual name="contavaloreshelper" property="conta.valorCreditos" value="0">
																		<a href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			<bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" />
																		</a>
																	</logic:notEqual>
																	<logic:equal name="contavaloreshelper" property="conta.valorCreditos" value="0">
																		<bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" />
																	</logic:equal>
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>

													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta"> 
																	<logic:notEqual name="contavaloreshelper" property="conta.valorCreditos" value="0">
																		<a href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contavaloreshelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			<font color="#ff0000">
																				<bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" />
																			</font>
																		</a>
																	</logic:notEqual>
																	<logic:equal name="contavaloreshelper" property="conta.valorCreditos" value="0">
																		<bean:write name="contavaloreshelper" property="conta.valorCreditos" formatKey="money.format" />
																	</logic:equal>
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="10%" align="right">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
														  <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														    <logic:notEmpty	name="contavaloreshelper" property="conta">
																<bean:define name="contavaloreshelper" property="conta"	id="conta" />
																<bean:write name="conta" property="valorImposto" formatKey="money.format" />
															</logic:notEmpty> 
														  </font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
														  <font color="#ff0000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														    <logic:notEmpty	name="contavaloreshelper" property="conta">
															  <bean:define name="contavaloreshelper" property="conta"	id="conta" />
															  <bean:write name="conta" property="valorImposto" formatKey="money.format" />
														    </logic:notEmpty> 
														  </font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorTotal" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorTotal" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEqual name="contavaloreshelper" property="valorTotalContaValores" value="0">
																	<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																		<bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" />
																	</a>
																</logic:notEqual> 
																
																<logic:equal name="contavaloreshelper" property="valorTotalContaValores" value="0">
																	<bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" />
																</logic:equal> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEqual name="contavaloreshelper" property="valorTotalContaValores" value="0">
																	<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contavaloreshelper" property="valorMulta" />&juros=<bean:write name="contavaloreshelper" property="valorJurosMora" />&atualizacao=<bean:write name="contavaloreshelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																		<font color="#ff0000">
																			<bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" />
																		</font>
																	</a>
																</logic:notEqual> 
																<logic:equal name="contavaloreshelper" property="valorTotalContaValores" value="0">
																	<bean:write name="contavaloreshelper" property="valorTotalContaValores" formatKey="money.format" />
																</logic:equal> 
															</font>
														</div>
													</logic:notEmpty>
												</td>

												<td width="7%" align="left">
													<logic:empty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="left" class="style9" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta" id="conta" />
																	<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" />
																	<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contavaloreshelper" property="conta.contaMotivoRevisao">
														<div align="left" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}" > 
																<logic:notEmpty name="contavaloreshelper" property="conta">
																	<bean:define name="contavaloreshelper" property="conta" id="conta" />
																	<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" />
																	<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
											</tr>
										</logic:iterate>
										
										
										<logic:notEmpty name="colecaoContaValores">
											<%if (cor.equalsIgnoreCase("#cbe5fe")) { cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else { cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>
												<td bgcolor="#90c7fc" align="center">
													<div class="style9" align="center">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<strong>Total</strong>
														</font>
													</div>
												</td>
												
												<td align="left">
													<%=((Collection) session.getAttribute("colecaoContaValores")).size()%> &nbsp; doc(s)
												</td>
												
												<td align="right">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<%=session.getAttribute("valorAgua")%>
														</font>
													</div>
												</td>
												
												<td align="rigth">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<%=session.getAttribute("valorEsgoto")%>
														</font>
													</div>
												</td>
												
												<td align="right">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<%=session.getAttribute("valorDebito")%>
														</font>
													</div>
												</td>
												
												<td align="right">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<%=session.getAttribute("valorCredito")%>
														</font>
													</div>
												</td>
												
												<td align="right">
												  <div align="right">
												    <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												      <%=session.getAttribute("valorImposto")%>
												    </font>
												  </div>
												</td>
												
												<td align="right">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<%=session.getAttribute("valorConta")%>
														</font>
													</div>
												</td>
												
												<td align="right">
													<div align="right" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<%=session.getAttribute("valorAcrescimo")%>
														</font>
													</div>
												</td>
												
												<td align="left">
													<div align="left">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														</font>
													</div>
												</td>
											</tr>
										</logic:notEmpty>
									</logic:present>
									
									<logic:present name="colecaoContaValoresPreteritos">
									<tr>
										<td colspan="8">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
													<strong>----------------- Contas de clientes anteriores -----------------</strong>
												</font>
											</div>
										</td>
									</tr>
									
									<logic:iterate name="colecaoContaValoresPreteritos" id="contaValoresPreteritosHelper">
											<%if (cor.equalsIgnoreCase("#cbe5fe")) { cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else { cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>
												<td width="9%" align="left">
													<div align="left" class="style9">
														<logic:empty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																		<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" /> 
																		<bean:write name="conta" property="formatarAnoMesParaMesAno" />
																	</a>
																</logic:notEmpty> 
															</font>
														</logic:empty>
		
														<logic:notEmpty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																		<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" /> 
																		<font color="#ff0000">
																			<bean:write name="conta" property="formatarAnoMesParaMesAno" />
																		</font> 
																	</a>
																</logic:notEmpty> 
															</font>
														</logic:notEmpty>
													</div>
												</td>
												
												<td width="12%" align="left">
													<logic:empty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="left" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" />
																	<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="left" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" />
																	<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorAgua" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorAgua" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorEsgoto" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorEsgoto" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<logic:notEqual name="contaValoresPreteritosHelper" property="conta.debitos" value="0">
																		<a href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			<bean:write name="contaValoresPreteritosHelper" property="conta.debitos" formatKey="money.format" /> 
																		</a>
																	</logic:notEqual>
																	
																	<logic:equal name="contaValoresPreteritosHelper" property="conta.debitos" value="0">
																		<bean:write name="contaValoresPreteritosHelper" property="conta.debitos" formatKey="money.format" />
																	</logic:equal>
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>

													<logic:notEmpty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<logic:notEqual name="contaValoresPreteritosHelper" property="conta.debitos" value="0">
																		<a href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			<font color="#ff0000">
																				<bean:write name="contaValoresPreteritosHelper" property="conta.debitos" formatKey="money.format" /> 
																			</font>
																		</a>
																	</logic:notEqual>
															
																	<logic:equal name="contaValoresPreteritosHelper" property="conta.debitos" value="0">
																		<bean:write name="contaValoresPreteritosHelper" property="conta.debitos" formatKey="money.format" />
																	</logic:equal>
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="10%" align="right">
													<logic:empty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<logic:notEqual name="contaValoresPreteritosHelper" property="conta.valorCreditos" value="0">
																		<a href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contaValoresPreteritosHelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			<bean:write name="contaValoresPreteritosHelper" property="conta.valorCreditos" formatKey="money.format" />
																		</a>
																	</logic:notEqual>
																	
																	<logic:equal name="contaValoresPreteritosHelper" property="conta.valorCreditos" value="0">
																		<bean:write name="contaValoresPreteritosHelper" property="conta.valorCreditos" formatKey="money.format" />
																	</logic:equal>
																</logic:notEmpty>
															</font>
														</div>
													</logic:empty>

													<logic:notEmpty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<logic:notEqual name="contaValoresPreteritosHelper" property="conta.valorCreditos" value="0">
																		<a href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contaValoresPreteritosHelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																			<font color="#ff0000">
																				<bean:write name="contaValoresPreteritosHelper" property="conta.valorCreditos" formatKey="money.format" />
																			</font>
																		</a>
																	</logic:notEqual>
																	<logic:equal name="contaValoresPreteritosHelper"property="conta.valorCreditos" value="0">
																		<bean:write name="contaValoresPreteritosHelper"property="conta.valorCreditos" formatKey="money.format" />
																	</logic:equal>
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="10%" align="right">
													<logic:empty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
														  <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														    <logic:notEmpty	name="contaValoresPreteritosHelper" property="conta">
																<bean:define name="contaValoresPreteritosHelper" property="conta"	id="conta" />
																<bean:write name="conta" property="valorImposto" formatKey="money.format" />
															</logic:notEmpty> 
														  </font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
														  <font color="#ff0000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														    <logic:notEmpty	name="contaValoresPreteritosHelper" property="conta">
															  <bean:define name="contaValoresPreteritosHelper" property="conta"	id="conta" />
															  <bean:write name="conta" property="valorImposto" formatKey="money.format" />
														    </logic:notEmpty> 
														  </font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorTotal" formatKey="money.format" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>
													<logic:notEmpty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" />
																	<bean:write name="conta" property="valorTotal" formatKey="money.format" />
																</logic:notEmpty>
															</font>
														</div>
													</logic:notEmpty>
												</td>
												
												<td width="8%" align="right">
													<logic:empty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEqual name="contaValoresPreteritosHelper" property="valorTotalContaValores" value="0">
																	<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contaValoresPreteritosHelper" property="valorMulta" />&juros=<bean:write name="contaValoresPreteritosHelper" property="valorJurosMora" />&atualizacao=<bean:write name="contaValoresPreteritosHelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																		<bean:write name="contaValoresPreteritosHelper" property="valorTotalContaValores" formatKey="money.format" />
																	</a>
																</logic:notEqual> 
																<logic:equal name="contaValoresPreteritosHelper" property="valorTotalContaValores" value="0">
																	<bean:write name="contaValoresPreteritosHelper" property="valorTotalContaValores" formatKey="money.format" />
																</logic:equal> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="right" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEqual name="contaValoresPreteritosHelper" property="valorTotalContaValores" value="0">
																	<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contaValoresPreteritosHelper" property="valorMulta" />&juros=<bean:write name="contaValoresPreteritosHelper" property="valorJurosMora" />&atualizacao=<bean:write name="contaValoresPreteritosHelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
																		<font color="#ff0000">
																			<bean:write name="contaValoresPreteritosHelper" property="valorTotalContaValores" formatKey="money.format" />
																		</font>
																	</a>
																</logic:notEqual> 
																<logic:equal name="contaValoresPreteritosHelper" property="valorTotalContaValores" value="0">
																	<bean:write name="contaValoresPreteritosHelper" property="valorTotalContaValores" formatKey="money.format" />
																</logic:equal> 
															</font>
														</div>
													</logic:notEmpty>
												</td>

												<td width="7%" align="left">
													<logic:empty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="left" class="style9" title="${contaValoresPreteritosHelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" />
																	<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" />
																	<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:empty>
	
													<logic:notEmpty name="contaValoresPreteritosHelper" property="conta.contaMotivoRevisao">
														<div align="left" class="style9">
															<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif" title="${contaValoresPreteritosHelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}" >
																<logic:notEmpty name="contaValoresPreteritosHelper" property="conta">
																	<bean:define name="contaValoresPreteritosHelper" property="conta" id="conta" />
																	<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" />
																	<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" />
																</logic:notEmpty> 
															</font>
														</div>
													</logic:notEmpty>
												</td>
											  </tr>
											</logic:iterate>
											
									</logic:present>
								</table>
								</div>
								</td>
							</tr>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>				
				
				<!-- Inicio da tabela de Situa��o de Cobranca - Vivianne Sousa -->
				  <tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					
						<tr bordercolor="#79bbfd">
							<td colspan="4" bgcolor="#79bbfd" align="center"><strong>Situa&ccedil;&otilde;es de Cobran&ccedil;a </strong></td>
						</tr>
						<tr bordercolor="#000000">
							<td width="15%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>C&oacute;digo</strong> </font></div>
							</td>
							<td width="60%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Descri&ccedil;&atilde;o</strong> </font></div>
							</td>
							<td width="25%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Data de Implanta&ccedil;&atilde;o</strong> </font></div>
							
						</tr>
						
						<logic:notEmpty name="colecaoCobrancaSituacao" scope="session">
						<%if (((Collection) session.getAttribute("colecaoCobrancaSituacao")).size() <= 3) {%>
					
						<%cor = "#cbe5fe";%>
						<logic:present name="colecaoCobrancaSituacao">
							<logic:iterate name="colecaoCobrancaSituacao" id="imovelCobrancaSituacao">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td>
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
											<logic:notEmpty	name="imovelCobrancaSituacao" property="cobrancaSituacao">
												<bean:define name="imovelCobrancaSituacao"	property="cobrancaSituacao" id="cobrancaSituacao" />
												<bean:write name="cobrancaSituacao" property="id" />
											</logic:notEmpty> 
										</font></div>
									</td>
									<td>
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEmpty	name="imovelCobrancaSituacao" property="cobrancaSituacao">
												<bean:define name="imovelCobrancaSituacao"	property="cobrancaSituacao" id="cobrancaSituacao" />
												<bean:write name="cobrancaSituacao" property="descricao" />
											</logic:notEmpty> 
										</font></div>
									</td>
									<td>
										<div align="left'''" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												<bean:write name="imovelCobrancaSituacao" property="dataImplantacaoCobranca" formatKey="date.format" />
										</font></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
						
						
						<%} else {%>
						<tr>
							<td height="45" colspan="6">
								<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">
												
										<%cor = "#cbe5fe";%>
										<logic:present name="colecaoCobrancaSituacao">
											<logic:iterate name="colecaoCobrancaSituacao" id="imovelCobrancaSituacao">
												<%if (cor.equalsIgnoreCase("#cbe5fe")) {
													cor = "#FFFFFF";%>
												<tr bgcolor="#FFFFFF">
													<%} else {
													cor = "#cbe5fe";%>
												<tr bgcolor="#cbe5fe">
													<%}%>
				
													<td width="15%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
															<logic:notEmpty	name="imovelCobrancaSituacao" property="cobrancaSituacao">
																<bean:define name="imovelCobrancaSituacao"	property="cobrancaSituacao" id="cobrancaSituacao" />
																<bean:write name="cobrancaSituacao" property="id" />
															</logic:notEmpty> 
														</font></div>
													</td>
													<td width="61%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty	name="imovelCobrancaSituacao" property="cobrancaSituacao">
																<bean:define name="imovelCobrancaSituacao"	property="cobrancaSituacao" id="cobrancaSituacao" />
																<bean:write name="cobrancaSituacao" property="descricao" />
															</logic:notEmpty> 
														</font></div>
													</td>
													<td width="24%">
														<div align="left'''" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																<bean:write name="imovelCobrancaSituacao" property="dataImplantacaoCobranca" formatKey="date.format" />
														</font></div>
													</td>
												</tr>
											</logic:iterate>
										</logic:present>		 							
			
			
									</table>
								</div>
							</td>						
						</tr>						
						<%}%>
						
						</logic:notEmpty>
							
					</table>
					</td>
				</tr>
				<!-- Fim da tabela de Situa��o de Cobranca - Vivianne Sousa -->
				
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>

				<!-- Inicio da tabela de Hist�rico de Retorno de Negativa��es - Vivianne Sousa -->
				  <tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					
						<tr bordercolor="#79bbfd">
							<td colspan="4" bgcolor="#79bbfd" align="center"><strong>Hist&oacute;rico de Retorno de Negativa&ccedil;&atilde;es</strong></td>
						</tr>
						<tr bordercolor="#000000">
							<td width="10%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Data</strong> </font></div>
							</td>
							<td width="40%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Motivo</strong> </font></div>
							</td>
							<td width="35%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Negativador</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc">
							<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Situa&ccedil;&atilde;o</strong> </font></div>
							</td>
						</tr>
						
						<logic:notEmpty name="colecaoDadosNegativacaoRetorno" scope="session">
						<%if (((Collection) session.getAttribute("colecaoDadosNegativacaoRetorno")).size() <= 3) {%>
					
						<%cor = "#cbe5fe";%>
						<logic:present name="colecaoDadosNegativacaoRetorno">
							<logic:iterate name="colecaoDadosNegativacaoRetorno" id="dadosNegativacaoRetornoHelper">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td width="10%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
											<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="dataRetorno">
												<bean:write name="dadosNegativacaoRetornoHelper" property="dataRetorno" formatKey="date.format" />
											</logic:notEmpty> 
										</font></div>
									</td>
									<td width="40%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="descricaoRetornocodigo">
												<bean:write name="dadosNegativacaoRetornoHelper" property="descricaoRetornocodigo" />
											</logic:notEmpty>
										</font></div>
									</td>
									
									<td width="35%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="nomeCliente">
												<bean:write name="dadosNegativacaoRetornoHelper" property="nomeCliente" />
											</logic:notEmpty>
										</font></div>
									</td>
									<td width="15%">
										<div align="left" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write name="dadosNegativacaoRetornoHelper" property="indicadorCorrecaoFormatado" />
										</font></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
						
						
						<%} else {%>
						<tr>
							<td height="60" colspan="6">
								<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">
												
										<%cor = "#cbe5fe";%>
										<logic:present name="colecaoDadosNegativacaoRetorno">
											<logic:iterate name="colecaoDadosNegativacaoRetorno" id="dadosNegativacaoRetornoHelper">
												<%if (cor.equalsIgnoreCase("#cbe5fe")) {
													cor = "#FFFFFF";%>
												<tr bgcolor="#FFFFFF">
													<%} else {
													cor = "#cbe5fe";%>
												<tr bgcolor="#cbe5fe">
													<%}%>
				
													<td width="10%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
															<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="dataRetorno">
																<bean:write name="dadosNegativacaoRetornoHelper" property="dataRetorno" formatKey="date.format" />
															</logic:notEmpty> 
														</font></div>
													</td>
													<td width="40%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="descricaoRetornocodigo">
																<bean:write name="dadosNegativacaoRetornoHelper" property="descricaoRetornocodigo" />
															</logic:notEmpty>
														</font></div>
													</td>
													
													<td width="35%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty	name="dadosNegativacaoRetornoHelper" property="nomeCliente">
																<bean:write name="dadosNegativacaoRetornoHelper" property="nomeCliente" />
															</logic:notEmpty>
														</font></div>
													</td>
													<td width="15%">
														<div align="left" class="style9">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:write name="dadosNegativacaoRetornoHelper" property="indicadorCorrecaoFormatado" />
														</font></div>
													</td>
												</tr>
											</logic:iterate>
										</logic:present>		 							
			
			
									</table>
								</div>
							</td>						
						</tr>						
						<%}%>
						
						</logic:notEmpty>
							
					</table>
					</td>
				</tr>
				<!-- Fim da tabela de Hist�rico de Retorno de Negativa��es - Vivianne Sousa -->

				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#79bbfd">
							<td colspan="5" bgcolor="#79bbfd" align="center"><strong>D&eacute;bitos
							A Cobrar</strong></td>
						</tr>
						<tr bordercolor="#000000">
							<td width="50%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do
							D&eacute;bito</strong> </font></div>
							</td>
							<td width="13%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
							Refer&ecirc;ncia</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
							Cobran&ccedil;a</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcelas a
							cobrar</strong> </font></div>
							</td>
							<td width="17%" bgcolor="#90c7fc" height="20">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor a
							cobrar</strong> </font></div>
							</td>
						</tr>
						<%cor = "#cbe5fe";%>
						<logic:present name="colecaoDebitoACobrar">
							<logic:iterate name="colecaoDebitoACobrar" id="debitoacobrar">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td>
									<div align="left" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="debitoacobrar" property="imovel">
										<a
											href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoacobrar" property="id" />', 570, 720);">
										<bean:define name="debitoacobrar" property="debitoTipo"
											id="debitoTipo" /> <logic:notEmpty name="debitoTipo"
											property="descricao">
											<bean:write name="debitoTipo" property="descricao" />
										</logic:notEmpty> </a>
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="debitoacobrar" property="anoMesReferenciaDebito">
										<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesReferenciaDebito().toString()) %>
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="debitoacobrar" property="anoMesCobrancaDebito">
										<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesCobrancaDebito().toString()) %>
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="debitoacobrar" property="parcelasRestanteComBonus">
										<bean:write name="debitoacobrar" property="parcelasRestanteComBonus" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="debitoacobrar" property="valorTotalComBonus">
										<bean:write name="debitoacobrar" property="valorTotalComBonus"
											formatKey="money.format" />
									</logic:notEmpty> </font></div>
									</td>
								</tr>
							</logic:iterate>
							<logic:notEmpty name="colecaoDebitoACobrar">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
									</font></div>
									</td>
									<td>
									<%=((Collection) session.getAttribute("colecaoDebitoACobrar")).size()%>
									&nbsp;
									doc(s)
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebitoACobrar")%>
									</font></div>
									</td>
								</tr>
							</logic:notEmpty>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>				
				
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#79bbfd">
							<td colspan="5" bgcolor="#79bbfd" align="center"><strong>Cr&eacute;ditos
							A Realizar</strong></td>
						</tr>
						<tr bordercolor="#000000">
							<td width="53%" bgcolor="#90c7fc" height="20">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do
							Cr&eacute;dito</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
							Refer&ecirc;ncia</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
							Cobran&ccedil;a</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcelas a
							creditar</strong> </font></div>
							</td>
							<td width="17%" bgcolor="#90c7fc" height="20">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor a
							creditar</strong> </font></div>
							</td>
						</tr>
						<%cor = "#cbe5fe";%>
						<logic:present name="colecaoCreditoARealizar">
							<logic:iterate name="colecaoCreditoARealizar"
								id="creditoarealizar">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td><logic:notEmpty name="creditoarealizar"
										property="creditoTipo">
										<div align="left" class="style9"><font color="#000000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <a
											href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="creditoarealizar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&creditoID=<bean:write name="creditoarealizar" property="id" />', 570, 720);">
										<bean:define name="creditoarealizar" property="creditoTipo"
											id="creditoTipo" /> <logic:notEmpty name="creditoTipo"
											property="descricao">
											<bean:write name="creditoTipo" property="descricao" />
										</logic:notEmpty> </a> </font></div>
									</logic:notEmpty></td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="creditoarealizar" property="anoMesReferenciaCredito">
										<bean:write name="creditoarealizar"
											property="formatarAnoMesReferenciaCredito" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="creditoarealizar" property="anoMesCobrancaCredito">
										<bean:write name="creditoarealizar"
											property="formatarAnoMesCobrancaCredito" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="creditoarealizar" property="parcelasRestanteComBonus">
										<bean:write name="creditoarealizar"
											property="parcelasRestanteComBonus" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="creditoarealizar" property="valorTotalComBonus">
										<bean:write name="creditoarealizar" property="valorTotalComBonus"
											formatKey="money.format" />
									</logic:notEmpty> </font></div>
									</td>
								</tr>
							</logic:iterate>
							<logic:notEmpty name="colecaoCreditoARealizar">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
									</font></div>
									</td>
									<td>
									<%=((Collection) session.getAttribute("colecaoCreditoARealizar")).size()%>
									&nbsp;
									doc(s)
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorCreditoARealizar")%>
									</font></div>
									</td>
								</tr>
							</logic:notEmpty>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>				
				
				<tr>
					<td colspan="5">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#79bbfd">
							<td colspan="5" bgcolor="#79bbfd" align="center"><strong>Guias de Pagamento</strong></td>
						</tr>
						<tr bordercolor="#000000">
							<td width="36%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do D&eacute;bito</strong> </font></div></td>
							<td width="11%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Presta&ccedil;&atilde;o</strong> </font></div></td>
							<td width="13%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de Emiss&atilde;o</strong> </font></div></td>
							<td width="13%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de Vencimento</strong> </font></div></td>
							<td width="27%" bgcolor="#90c7fc"><div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da Guia de Pagamento</strong> </font></div></td>
						</tr>
						<%cor = "#cbe5fe";%>
						<logic:present name="colecaoGuiaPagamentoValores">
							<logic:iterate name="colecaoGuiaPagamentoValores"
								id="guiapagamentohelper">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
							cor = "#FFFFFF";
									%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td>
									<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <a
										href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" /><bean:write name="guiaPagamento" property="id" />', 600, 800);"><bean:define name="guiaPagamento" property="debitoTipo"	id="debitoTipo" /> <logic:notEmpty name="debitoTipo" property="descricao"><bean:write name="debitoTipo" property="descricao" /></logic:notEmpty> </a> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="guiapagamentohelper" property="guiaPagamento">
										<bean:define name="guiapagamentohelper"
											property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="prestacaoFormatada"/>
									</logic:notEmpty> </font></div>
									</td>
									
									
									<td>
									<div align="center" class="style9"><font color="#000000"	style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										<logic:notEmpty	name="guiapagamentohelper" property="guiaPagamento"> 
										<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="dataEmissao"	formatKey="date.format" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="guiapagamentohelper" property="guiaPagamento">
										<bean:define name="guiapagamentohelper"
											property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="dataVencimento"
											formatKey="date.format" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="guiapagamentohelper" property="guiaPagamento">
										<bean:define name="guiapagamentohelper"
											property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="valorDebito"
											formatKey="money.format" />
									</logic:notEmpty> </font></div>
									</td>
								</tr>
							</logic:iterate>
							<logic:notEmpty name="colecaoGuiaPagamentoValores">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
									</font></div>
									</td>
									<td><%=((Collection) session.getAttribute("colecaoGuiaPagamentoValores")).size()%>
									&nbsp;
									doc(s)</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorGuiaPagamento")%>
									</font></div>
									</td>
								</tr>
							</logic:notEmpty>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>	
				
				<tr>
					<td colspan="4">
						<table  width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr bordercolor="#79bbfd">
								<td colspan="5" bgcolor="#79bbfd" align="center"><strong>D&eacute;bitos Pret&eacute;ritos</strong></td>
							</tr>
							<tr  bordercolor="#000000">
								<td width="50%" bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
									<strong>Quantidade de contas</strong> </font></div>
								</td>
								<td width="50%" bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
									<strong>Valor Total</strong> </font></div>
								</td>
							</tr>
							<%if((session.getAttribute("quantidadeDebitosPreteritos")!= null) || (session.getAttribute("valorDebitosPreteritos") != null)){ %>
							<tr bgcolor="#FFFFFF">
								<td>
									<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("quantidadeDebitosPreteritos")!=null?session.getAttribute("quantidadeDebitosPreteritos"):""%>
								</font></div>
								</td>
								<td>
								<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebitosPreteritos")!=null?session.getAttribute("valorDebitosPreteritos"):""%>
								</font></div>
								</td>
							</tr>
							<%} %>
						</table>
					</td>
				</tr>
						
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
					
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#000000">
							<td width="25%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Valor Total dos D&eacute;bitos</strong> </font></div>
							</td>
							<td width="25%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Valor Total dos D&eacute;bitos Atualizado</strong> </font></div>
							</td>
							<td width="25%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Valor do Desconto para Pagamento &aacute; Vista</strong> </font></div>
							</td>
							<td width="25%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Valor do Pagamento &aacute; Vista</strong> </font></div>
							</td>
						</tr>
						<%if((session.getAttribute("valorTotalSemAcrescimo")!= null) || (session.getAttribute("valorTotalComAcrescimo") != null)){ %>
							<tr bgcolor="#FFFFFF">
								<td>
								<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorTotalSemAcrescimo")!= null?session.getAttribute("valorTotalSemAcrescimo"):""%>
								</font></div>
								</td>
								<td>
								<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorTotalComAcrescimo")!=null?session.getAttribute("valorTotalComAcrescimo"):""%>
								</font></div>
								</td>
								
								<td>
								<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorTotalDescontoPagamentoAVista")!=null?session.getAttribute("valorTotalDescontoPagamentoAVista"):""%>
								</font></div>
								</td>
								
								<td>
								<div align="right" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorPagamentoAVista")!=null?session.getAttribute("valorPagamentoAVista"):""%>
								</font></div>
								</td>
							</tr>
						<%} %>
					</table>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						  <div align="right">
						   <a href="javascript:verificarExibicaoRelatorio();">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir D&eacute;bitos" /> </a>
						  </div>
					</td>
				</tr>
				
				<tr>
					<td height="23">
						<table width="100%">
							<tr>  
								<logic:empty name="colecaoContaValores">
									<td align="right">
										<input type="button" name="" value="Imprimir Extrato de D&eacute;bito" class="bottonRightCol" disabled="true"/>
									</td>
								</logic:empty>
								<logic:notEmpty name="colecaoContaValores">
									<logic:equal name="ConsultarImovelActionForm" property="indicadorEmissaoExtratoNaConsulta" value="<%=ConstantesSistema.SIM.toString()%>" >
										<td align="right">
											<input type="button" name="" value="Imprimir Extrato de D&eacute;bito" class="bottonRightCol" onclick="gerarExtratoDebito()" >
										</td>
									</logic:equal>
									
									<logic:notEqual name="ConsultarImovelActionForm" property="indicadorEmissaoExtratoNaConsulta" value="<%= ConstantesSistema.SIM.toString()%>" >
										<td align="right">
											<input type="button" name="" value="Imprimir Extrato de D&eacute;bito" class="bottonRightCol" disabled="true" >
										</td>
									</logic:notEqual>
								</logic:notEmpty>
							</tr>
						</table>
					</td>			
				</tr>
			
				
			</table>
			
		
			
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=5" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>		
		
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDebitosConsultarAction.do" />		
	<%@ include file="/jsp/util/rodape.jsp"%>
    <%@ include file="/jsp/util/tooltip.jsp" %>	
	<p>&nbsp;</p>
	<!-- Fim do Corpo - Fernanda Paiva -->
</html:form>
</body>
</html:html>
