<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ include file="/jsp/util/titulo.jsp"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet"	href="<bean:message key="caminho.css"/>popup.css"	type="text/css" />
	
   <link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jqgrid/jquery-ui-1.8.2.custom.css" />
  <link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jqgrid/ui.jqgrid.css" />
<link rel="stylesheet"	href="<bean:message key="caminho.css"/>EstilosCompesa.css"	type="text/css" />
	
<!--

function fechar(){
		window.close();
-->

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" 
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jqgrid/jquery.js"></script>
    <script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jqgrid/grid.locale-pt-br.js"></script>
    <script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jqgrid/jquery.jqGrid.src.js"></script>
	
<script type="text/javascript" src="<bean:message key="caminho.js"/>popup.js"></script>
<script language="JavaScript">
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

   	var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.idImovelRegistroAtendimento.value = codigoRegistro;
      form.matriculaImovelRegistroAtendimento.value = descricaoRegistro;
      form.matriculaImovelRegistroAtendimento.style.color = "#000000";
	  form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelRegistroAtendimentoAction&indicadorNovo=OK'
	  form.submit();
    }
    
}

function limparForm(){
   	var form = document.forms[0];
	form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelRegistroAtendimentoAction&limparForm=OK'
	form.submit();
}

function expandirConsulta(){
	centerPopup();
	loadPopup();
}


function habilitaMatricula() {
	var form = document.forms[0];
	
	if (form.idImovelRegistroAtendimento.value != null && form.matriculaImovelRegistroAtendimento.value != null && 
		form.matriculaImovelRegistroAtendimento.value != "" && form.matriculaImovelRegistroAtendimento.value != "IMÓVEL INEXISTENTE"){
	
		form.idImovelRegistroAtendimento.disabled = true;
	} else {
		form.idImovelRegistroAtendimento.disabled = false;
	}
}

function pesquisarImovel() {
	var form = document.forms[0];
 	
 	if (form.idImovelRegistroAtendimento.disabled ) {
 		alert("Para realizar uma pesquisa de imóvel é necessário apagar o imóvel atual.")
	} else {
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800)
	}
}
	
	function centralizaMenuAbas(){
		document.getElementById("Layer1").style.left = "460px";
		document.getElementById("Layer1").style.top = "85px";
	}
</script>


</head>
<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('idImovelRegistroAtendimento'); centralizaMenuAbas();">


<html:form action="/exibirConsultarImovelAction.do"
	name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm" method="post"
	onsubmit="return validateConsultarImovelActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=10" />


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="150" valign="top" class="leftcoltext">
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

		<td width="675" valign="top" class="centercoltext">
		<p>&nbsp;</p>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">&nbsp;</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>

		<!-- Início do Corpo - Fernanda Paiva  07/02/2006  -->
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
										<td align="center" width="96%"><strong>Dados do Imóvel<logic:present name="imovelExcluido" scope="request"><font color="#ff0000"> (Excluído)</font></logic:present></strong></td>
									</tr>
								</table>
							</td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td bordercolor="#000000" width="25%"><strong>Im&oacute;vel:<font
										color="#FF0000">*</font></strong></td>
									<td width="75%" colspan="3"><html:text
										property="idImovelRegistroAtendimento" maxlength="9" size="9"
										onkeypress="validaEnterComMensagem(event, 'consultarImovelWizardAction.do?action=exibirConsultarImovelRegistroAtendimentoAction&indicadorNovo=OK','idImovelRegistroAtendimento','Im&oacute;vel');return isCampoNumerico(event); " 
										/> 
									<a
										href="javascript:pesquisarImovel();">
									<img width="23" height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Imóvel"/></a> <logic:present
										name="idImovelRegistroAtendimentoNaoEncontrado" scope="request">
										<html:text property="matriculaImovelRegistroAtendimento" size="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											title="Localidade.Setor.Quadra.Lote.Sublote" />

									</logic:present> <logic:notPresent
										name="idImovelRegistroAtendimentoNaoEncontrado" scope="request">
										<logic:present name="valorMatriculaImovelRegistroAtendimento"
											scope="request">
											<html:text property="matriculaImovelRegistroAtendimento"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:present>
										<logic:notPresent name="valorMatriculaImovelRegistroAtendimento"
											scope="request">
											<html:text property="matriculaImovelRegistroAtendimento"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:notPresent>
									</logic:notPresent> <a href="javascript:limparForm();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>

								</tr>
								<tr>
									<td height="10">
									<div class="style9"><strong>Situação de Água:</strong></div>
									</td>
									<td><html:text property="situacaoAguaRegistroAtendimento"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
									<td width="90"><strong>Situação de Esgoto:</strong></td>
									<td width="120"><html:text
										property="situacaoEsgotoRegistroAtendimento" readonly="true"
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
				<td colspan="3" height="10"></td>
			</tr>

				<tr>
					<td align="center">

					<table width="100%" border="0" bgcolor="#90c7fc">
						
						<tr bgcolor="#79bbfd">
							<td height="18" colspan="6" align="center">
								<strong>Dados Gerais do Registros de Atendimento</strong></td>
						</tr>
						
						<tr>
							
							<td bgcolor="#90c7fc" align="center" width="10%">
								<div align="center"><strong>Número do RA</strong></div>
							</td>
							
							<td bgcolor="#90c7fc" width="40%">
								<div align="center"><strong>Especificação</strong></div>
							</td>
							
							<td bgcolor="#90c7fc" width="10%">
								<div align="center"><strong>Data de Atendimento</strong></div>
							</td>

							<td bgcolor="#90c7fc" width="10%">
								<div align="center"><strong>Data de Encerramento</strong></div>
							</td>
							
							<td bgcolor="#90c7fc" width="15%">
								<div align="center"><strong>Situação</strong></div>
							</td>

							<td bgcolor="#90c7fc" width="15%">
								<div align="center"><strong>Motivo Encerramento</strong></div>
							</td>					
							
						</tr>
						
						<tr bordercolor="#000000">
						
						<logic:present name="colecaoConsultarImovelRegistroAtendimentoHelper">
							<%	int cont = 0;	%>
							<logic:iterate name="colecaoConsultarImovelRegistroAtendimentoHelper"
								id="consultarImovelRegistroAtendimentoHelper">
								<%	cont = cont + 1;
								if (cont % 2 == 0) {	%>
									<tr bgcolor="#cbe5fe">
							<%	} else {	%>
									<tr bgcolor="#FFFFFF">
							<%	}	%>

									<td align="center">
										<a href="javascript:abrirPopup('exibirConsultarRegistroAtendimentoPopupAction.do?numeroRA='+${consultarImovelRegistroAtendimentoHelper.idRegistroAtendimento}, 400, 800);">
												${consultarImovelRegistroAtendimentoHelper.idRegistroAtendimento}
										</a>
									</td>

									<td align="left">
    									<a title="Protocolo : ${consultarImovelRegistroAtendimentoHelper.numeroProtocolo}">
											${consultarImovelRegistroAtendimentoHelper.especificacao}
										</a>									
									</td>
									
									<td align="center">${consultarImovelRegistroAtendimentoHelper.dataAtendimento}</td>
									<td align="center">${consultarImovelRegistroAtendimentoHelper.dataEncerramento}</td>
									<td align="center">${consultarImovelRegistroAtendimentoHelper.situacao}</td>
									<td align="center">${consultarImovelRegistroAtendimentoHelper.motivoEncerramento}</td>
								</tr>
								
							</logic:iterate>
						</logic:present>
					</table>
				</tr>
			</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td>
					<input type="button" 
       					name="ButtonReset" 
       					class="bottonRightCol"
						value="Expandir Consulta"
						onClick="expandirConsulta();">
				</td>
			</tr>
		</table>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">
				<div align="right"><jsp:include
					page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=10" /></div>
				</td>
			</tr>
		</table>
		
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
<%@ include file="/jsp/cadastro/imovel/imovel_consultar_registro_atendimento_popup.jsp"%>	
		
</body>
</html>
