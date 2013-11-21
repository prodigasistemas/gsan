<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ConsultarTransferenciasDebitoActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

	function limparForm(form) {
	
		form.idImovelOrigem.value = "";
		form.inscricaoImovelOrigem.value = "";
		form.idImovelDestino.value = "";
		form.inscricaoImovelDestino.value = "";
		form.idUsuario.value = "";
		form.loginUsuario.value = "";
		form.nomeUsuario.value = "";
		form.dataInicio.value = "";
		form.dataFim.value = "";
	
	}
	
	function pesquisarImovelOrigem() {
		var form = document.forms[0];
		
		form.tipoPesquisa.value = 'origem';
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
	}
	
	function pesquisarImovelDestino() {
		var form = document.forms[0];
		
		form.tipoPesquisa.value = 'destino';
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
	}
	
	function recuperarDadosPopup(codigoRegistro,descricaoRegistro,tipoConsulta){

       var form = document.ConsultarTransferenciasDebitoActionForm;

       if (tipoConsulta == 'imovel') {
       
       		if (form.tipoPesquisa.value == 'origem') {
      			form.idImovelOrigem.value = codigoRegistro;
			    form.inscricaoImovelOrigem.value = descricaoRegistro;
    	        form.inscricaoImovelOrigem.style.color = "#000000";
            } else if (form.tipoPesquisa.value == 'destino') {
            	form.idImovelDestino.value = codigoRegistro;
			    form.inscricaoImovelDestino.value = descricaoRegistro;
    	        form.inscricaoImovelDestino.style.color = "#000000";
            }
       } else if (tipoConsulta == 'usuario') {
			form.idUsuario.value = codigoRegistro;
			form.loginUsuario.value = "";
			form.action= 'exibirConsultarTransferenciasDebitoAction.do';
			form.submit();
    	}
       
    }
	
	function limparUsuario() {
		var form = document.forms[0];
		form.idUsuario.value = "";
		form.loginUsuario.value = "";
		form.nomeUsuario.value = "";	
	}
	
	function limparUsuarioTecla() {
		var form = document.forms[0];
		form.idUsuario.value = "";
		form.nomeUsuario.value = "";	
	}
	
	function limparImovelOrigem(){
		var form = document.forms[0];
		
		form.idImovelOrigem.value = "";
		form.inscricaoImovelOrigem.value = "";
	}

	function limparImovelOrigemTecla() {
		var form = document.forms[0];
	
		form.inscricaoImovelOrigem.value = "";
	}
	
	function limparImovelDestino(){
		var form = document.forms[0];
		
		form.idImovelDestino.value = "";
		form.inscricaoImovelDestino.value = "";
	}

	function limparImovelDestinoTecla() {
		var form = document.forms[0];
	
		form.inscricaoImovelDestino.value = "";
	}
	

	function validarForm(form){
				
		if(validateConsultarTransferenciasDebitoActionForm(form)){			
			form.submit();			
		}
	}
	
	function replicaDados(campoOrigem, campoDestino){
		campoDestino.value = campoOrigem.value;
	}

-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/consultarTransferenciasDebitoAction"
	name="ConsultarTransferenciasDebitoActionForm"
	type="gcom.gui.cobranca.ConsultarTransferenciasDebitoActionForm"
	method="post">

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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar Transferências</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para consultar a(s) transferências, informe os dados
					abaixo:</td>
				</tr>
				<html:hidden property="tipoPesquisa" />
				<tr>
					<td><strong>Imóvel Origem:</strong></td>
					<td><html:text property="idImovelOrigem" size="9" maxlength="9"
						onkeyup="javascript:limparImovelOrigemTecla();"
						onkeypress="validaEnterComMensagem(event, 'exibirConsultarTransferenciasDebitoAction.do', 
							'idImovelOrigem', 'Imóvel');" />
					<a
						href="javascript:pesquisarImovelOrigem();">
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="imovelOrigemInexistente">
						<html:text property="inscricaoImovelOrigem" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="imovelOrigemInexistente">
						<html:text property="inscricaoImovelOrigem" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovelOrigem();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Imóvel Origem" /> </a></td>
				</tr>
				<tr>
					<td><strong>Imóvel Destino:</strong></td>
					<td><html:text property="idImovelDestino" size="9" maxlength="9"
						onkeyup="javascript:limparImovelDestinoTecla();"
						onkeypress="validaEnterComMensagem(event, 'exibirConsultarTransferenciasDebitoAction.do', 
							'idImovelDestino', 'Imóvel');" />
					<a
						href="javascript:pesquisarImovelDestino();">
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="imovelDestinoInexistente">
						<html:text property="inscricaoImovelDestino" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="imovelDestinoInexistente">
						<html:text property="inscricaoImovelDestino" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovelDestino();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Imóvel Destino" /> </a></td>
				</tr>
				<tr>
					<td><strong>Data Transferência:</strong></td>
					<td height="25"><html:text property="dataInicio" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);replicaDados(document.forms[0].dataInicio, document.forms[0].dataFim);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" 
						onclick="javascript:abrirCalendarioReplicando('ConsultarTransferenciasDebitoActionForm', 'dataInicio','dataFim');" />
					 a <html:text property="dataFim" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('ConsultarTransferenciasDebitoActionForm', 'dataFim')" />
					(dd/mm/aaaa)</td>
				</tr>
				
				<tr>
					<td><strong>Usuário:</strong></td>
					<html:hidden property="idUsuario" />
					<td><strong>
						<html:text property="loginUsuario" size="11" maxlength="11"
							style="text-transform: none;"
							onkeyup="javascript:return validaEnterStringSemUpperCase(event, 'exibirConsultarTransferenciasDebitoAction.do', 'loginUsuario');" /> 
					<a
						href="javascript:abrirPopup('exibirUsuarioPesquisar.do', 250, 495);">
					<img border="0" src="imagens/pesquisa.gif" height="21" width="23" /></a>

					<logic:present name="usuarioInexistente" scope="request">
						<html:text property="nomeUsuario" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> <logic:notPresent name="usuarioInexistente"
						scope="request">
						<html:text property="nomeUsuario" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
					</logic:notPresent> <a href="javascript:limparUsuario();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> </strong></td>
				</tr>
				
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td><input type="button" name="ButtonReset" class="bottonRightCol"
						value="Limpar" onclick="javascript:limparForm(document.forms[0]);">
						
					<td colspan="2" align="right">
 					  <gsan:controleAcessoBotao name="Button" value="Consultar" onclick="javascript:validarForm(document.forms[0]);" url="consultarTransferenciasDebitoAction.do"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
