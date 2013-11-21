<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

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
	formName="entidadeBeneficenteActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	
<script type="text/javascript">
	function limparCliente() {
		document.getElementById('idCliente').value = '';
		document.getElementById('nomeCliente').value = '';
		document.getElementById('idCliente').focus();
	}
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    if (tipoConsulta == 'cliente') {
	    	document.getElementById('idCliente').value = codigoRegistro;
	    	document.getElementById('nomeCliente').value = descricaoRegistro;
	    	document.getElementById('nomeCliente').style.color = '#000000';
	    	document.getElementById('idTipoDebito').focus();
	    } else if (tipoConsulta == 'tipoDebito') {
	    	document.getElementById('idTipoDebito').value = codigoRegistro;
	    	document.getElementById('descricaoTipoDebito').value = descricaoRegistro;
	    	document.getElementById('descricaoTipoDebito').style.color = '#000000';
	    	document.getElementById('idEmpresa').focus();
	    }
	}
	function limparTipoDebito() {
		document.getElementById('idTipoDebito').value = '';
		document.getElementById('descricaoTipoDebito').value = '';
		document.getElementById('idTipoDebito').focus();
	}
	function validarForm(form){
		if(validateEntidadeBeneficenteActionForm(form))
	   		submeterFormPadrao(form);
	}
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form 
	action="/inserirEntidadeBeneficenteAction.do"
	name="entidadeBeneficenteActionForm"
	type="gcom.gui.cadastro.entidadebeneficente.EntidadeBeneficenteActionForm">

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Entidade Beneficente</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para adicionar uma entidade beneficente, informe os dados abaixo:</td>
				</tr>

				<tr>
					<td><strong>Cliente:<span style="color: red">*</span></strong></td>

					<td colspan="3">
					
						<html:text property="entidadeBeneficente.cliente.id" size="9"
							maxlength="9" tabindex="1"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirEntidadeBeneficenteAction.do?objetoConsulta=1&limparCampos=1', 'idCliente','Cliente');document.getElementById('nomeCliente').value = '';" styleId="idCliente"  />
							<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 800, 490, '',document.getElementById('idCliente'));">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0"></a> 
							
						<logic:notPresent name="existeCliente" scope="request">
	
							<html:text property="entidadeBeneficente.cliente.nome" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="40" maxlength="40" styleId="nomeCliente" />
	
						</logic:notPresent> 
						
						<logic:present name="existeCliente"	scope="request">
	
							<html:text property="entidadeBeneficente.cliente.nome" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" styleId="nomeCliente" />
	
						</logic:present> 
						
						<a href="javascript:limparCliente();"> <img
							border="0" title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
						</a>
					
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Tipo do D&eacute;bito:<span style="color: red">*</span></strong>
					</td>
					
					<td colspan="3">
						
						<html:text property="entidadeBeneficente.debitoTipo.id" 
							size="4" 
							maxlength="4"
							tabindex="2"
							onkeyup="validaEnter(event, 'exibirInserirEntidadeBeneficenteAction.do', 'idTipoDebito');document.getElementById('descricaoTipoDebito').value = '';"
							styleId="idTipoDebito" />
						
						<a href="javascript:abrirPopup('exibirPesquisarTipoDebitoAction.do', 'tipoDebito', null, null, 400, 800, '',document.getElementById('idTipoDebito'));">
							<img width="23" 
								height="21"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								alt="Pesquisar" 
								border="0" /></a>
						
						
						<logic:present name="existeDebitoTipo" scope="request">
							<html:text property="entidadeBeneficente.debitoTipo.descricao" 
								size="30" 
								maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								styleId="descricaoTipoDebito" />
						</logic:present> 
						
						<logic:notPresent name="existeDebitoTipo" scope="request">
							<html:text property="entidadeBeneficente.debitoTipo.descricao" 
								size="30" 
								maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								styleId="descricaoTipoDebito" />
						</logic:notPresent> 
												
						<a href="javascript:limparTipoDebito();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>

					</td>
				</tr>
				
				<tr>
					<td><strong>Empresa:<span style="color: red">*</span></strong></td>
					<td colspan="3"><html:select property="entidadeBeneficente.empresa.id" tabindex="3" styleId="idEmpresa">
						<option></option>
						<html:options name="request" collection="colecaoEmpresa"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
								

				<tr>
					<td>&nbsp;</td>
					<td align="left"><span style="color: red">*</span> Campo Obrigatório</td>
				</tr>
				<tr>
					<td colspan="2">
						<input name="Desfazer" type="button" class="bottonRightCol"
							value="Desfazer" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirInserirEntidadeBeneficenteAction.do?menu=sim"/>'"
							tabindex="5">
						<input name="Cancelar" type="button" class="bottonRightCol"
							value="Cancelar" align="left"
							onClick="window.location.href='<html:rewrite page="/telaPrincipal.do"/>'"
							tabindex="6">
					</td>
					<td width="53" height="24" align="right">
						<input type="button"
							name="Inserir" class="bottonRightCol" value="Inserir" onclick="validarForm(document.forms[0])" tabindex="4" />
					</td>
					<td width="12">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>
