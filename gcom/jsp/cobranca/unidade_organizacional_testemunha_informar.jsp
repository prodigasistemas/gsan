<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.cobranca.UnidadeOrganizacionalTestemunha"%>

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
	formName="InformarUnidadeOrganizacionalTestemunhaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin
	
	function limparTestemunha() {
		var form = document.forms[0];
		form.idTestemunha.value = "";
		form.loginTestemunha.value = "";
		form.nomeTestemunha.value = "";	
	}
	
	function limparTestemunhaTecla() {
		var form = document.forms[0];
		form.idTestemunha.value = "";
		form.nomeTestemunha.value = "";	
	}
	
	function limparUnidadeOrganizacional() {
		var form = document.forms[0];
		form.idUnidadeOrganizacional.value = "";
		form.nomeUnidadeOrganizacional.value = "";
	}
	
	function limparUnidadeOrganizacionalTecla() {
		var form = document.forms[0];
		form.nomeUnidadeOrganizacional.value = "";	
	}
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'usuario') {
			form.idTestemunha.value = codigoRegistro;
			form.loginTestemunha.value = "";
			form.action= 'exibirInformarUnidadeOrganizacionalTestemunhaAction.do';
			form.submit();
    	} else if (tipoConsulta == 'unidadeOrganizacional') { 
    		form.idUnidadeOrganizacional.value = codigoRegistro;
			form.nomeUnidadeOrganizacional.value = descricaoRegistro;
			form.nomeUnidadeOrganizacional.style.color = "#000000";
    	}
    
    }
    
    function remover(id){
		var form = document.forms[0];
		if (confirm("Deseja realmente remover esta unidade organizacional testemunha?")) {
		    form.action='exibirInformarUnidadeOrganizacionalTestemunhaAction.do?removerUnidadeOrganizacionalTestemunha='+id;
		    form.submit();
 		}
	}
	
	function confirmacao() {
		if (confirm("Você alterou dados da consulta inicial e perderá todas as alterações efetuadas. Deseja Continuar?")) {
			consultar();
		}
	}
	
	function consultar() {
		var form = document.forms[0];
			if(validateInformarUnidadeOrganizacionalTestemunhaActionForm(form)){			
		    	form.action='exibirInformarUnidadeOrganizacionalTestemunhaAction.do?consultar=sim';
			    form.submit();
			}
	}
	
	function adicionar(){
		var form = document.forms[0];
		
		if(validateInformarUnidadeOrganizacionalTestemunhaActionForm(form)){
			if (form.loginTestemunha.value == '') {
				alert("Informe Testemunha");
			} else {
	    		form.action='exibirInformarUnidadeOrganizacionalTestemunhaAction.do?adicionar=sim';
			    form.submit();
		    }
		}
	}
	
	function informar(){
		var form = document.forms[0];
		form.action = 'informarUnidadeOrganizacionalTestemunhaAction.do'
	    form.submit();
	}
	
	function desabilitarCampos() {
		var form = document.forms[0];
		form.botaoAdicionar.disabled = true;
		form.idTestemunha.value = "";
		form.loginTestemunha.value = "";
		form.nomeTestemunha.value = "";
		form.loginTestemunha.readOnly = true;
	}
	
	function pesquisarTestemunha(event) {
		var form = document.forms[0];
	
		if (form.botaoAdicionar.disabled) {
			validaEnterStringSemUpperCase(event, 'exibirInformarUnidadeOrganizacionalTestemunhaAction.do?desabilitaCampos=sim', 'loginTestemunha');
		} else {
			validaEnterStringSemUpperCase(event, 'exibirInformarUnidadeOrganizacionalTestemunhaAction.do', 'loginTestemunha');
		}	
	}
	
	function pesquisarTestemunhaLupa() {
		var form = document.forms[0];
		
		abrirPopup('exibirUsuarioPesquisar.do?idUnidadeOrganizacional=' + form.idUnidadeOrganizacional.value, 250, 495);
	}
	
	function pesquisarUnidadeOrganizacional(event) {
		var form = document.forms[0];
	
		if (form.botaoAdicionar.disabled) {
			validaEnterSemUpperCase(event, 'exibirInformarUnidadeOrganizacionalTestemunhaAction.do?desabilitaCampos=sim', 'idUnidadeOrganizacional');
		} else {
			validaEnterSemUpperCase(event, 'exibirInformarUnidadeOrganizacionalTestemunhaAction.do', 'idUnidadeOrganizacional');
		}	
	}
	
	function controleDesabilitarCampos(desabilitaCampos) {
		if (desabilitaCampos != null && desabilitaCampos != '') {
			desabilitarCampos();
		}
	}

-->
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');controleDesabilitarCampos('${requestScope.desabilitaCampos}')">

<html:form action="/informarUnidadeOrganizacionalTestemunhaAction"
	name="InformarUnidadeOrganizacionalTestemunhaActionForm"
	type="gcom.gui.cobranca.InformarUnidadeOrganizacionalTestemunhaActionForm"
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
					<td class="parabg">Informar Unidade Organizacional Testemunha</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para informar a unidade organizacional testemunha,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="20%"><strong>Unidade Organizacional:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="9" property="idUnidadeOrganizacional"
							size="9" tabindex="1"
							onkeyup="pesquisarUnidadeOrganizacional(event); limparUnidadeOrganizacionalTecla();" onchange="desabilitarCampos()" /> <a
							href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limpaForm=S', 495, 300);"><img
							src="<bean:message key='caminho.imagens'/>pesquisa.gif"
							width="23" height="21" alt="Pesquisar" border="0"></a> 
							<logic:present name="unidadeOrganizacionalInexistente" scope="request">
							<html:text property="nomeUnidadeOrganizacional" size="40" maxlength="40" readonly="true"
								style="border: 0pt none ; background-color: color: #ff0000" />
						</logic:present> <logic:notPresent name="unidadeOrganizacionalInexistente"
						scope="request">
							<html:text property="nomeUnidadeOrganizacional" size="40" maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> <a href="javascript:limparUnidadeOrganizacional();"> <img
							border="0" src="imagens/limparcampo.gif" height="21" width="23">
						</a>
					</td>
				</tr>
				<tr>
					<td><strong>Testemunha:</strong></td>
					<html:hidden property="idTestemunha" />
					<td><strong> <logic:present name="colecaoUnidadeOrganizacionalTestemunha">
						<html:text property="loginTestemunha" size="11" maxlength="11"
							style="text-transform: none;"
							onkeyup="javascript:pesquisarTestemunha(event); limparTestemunhaTecla();" />
					</logic:present> <logic:notPresent
						name="colecaoUnidadeOrganizacionalTestemunha">
						<html:text property="loginTestemunha" size="11" maxlength="11"
							style="text-transform: none;"
							onkeyup="javascript:return pesquisarTestemunha(event); limparTestemunhaTecla();"
							readonly="true" />
					</logic:notPresent> <a
						href="javascript:pesquisarTestemunhaLupa();">
					<img border="0" src="imagens/pesquisa.gif" height="21" width="23" /></a>

					<logic:present name="testemunhaInexistente" scope="request">
						<html:text property="nomeTestemunha" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> <logic:notPresent name="testemunhaInexistente"
						scope="request">
						<html:text property="nomeTestemunha" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
					</logic:notPresent> <a href="javascript:limparTestemunha();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> </strong></td>
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="right"><logic:present
						name="colecaoUnidadeOrganizacionalTestemunha">
						<input type="button" name="Button" value="Consultar"
							onclick="javascript:confirmacao();" class="bottonRightCol" />
					</logic:present> <logic:notPresent
						name="colecaoUnidadeOrganizacionalTestemunha">
						<input type="button" name="Button" value="Consultar"
							onclick="javascript:consultar();" class="bottonRightCol" />
					</logic:notPresent></td>
				</tr>

				<tr>
					<td height="18" colspan="2">
					<hr>
					</td>
				</tr>

				<tr>
					<td width="100%" colspan="2">
					<table width="100%">
						<tr>
							<td width="60%">Unidade(s) Organizacional(is) Testemunha(s):</td>
							<td width="40%" align="right"><logic:present
								name="colecaoUnidadeOrganizacionalTestemunha">
								<input type="button" name="botaoAdicionar" value="Adicionar"
									id="botaoAdicionar" class="bottonRightCol"
									onclick="javascript:adicionar();" />
							</logic:present> <logic:notPresent
								name="colecaoUnidadeOrganizacionalTestemunha">
								<input type="button" name="botaoAdicionar" value="Adicionar"
									id="botaoAdicionar" class="bottonRightCol" disabled />
							</logic:notPresent></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td width="100%" colspan="2">
					<div align="center">
					<table width="100%" align="center" cellpadding="0" cellspacing="0">
						<!--corpo da segunda tabela-->
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td width="10%">
							<div align="center"><strong>Remover</strong></div>
							</td>
							<td width="25%">
							<div align="center"><strong>Unidade Organizacional</strong></div>
							</td>
							<td width="25%">
							<div align="center"><strong>Testemunha</strong></div>
							</td>
							<td width="20%">
							<div align="center"><strong>Data Relação Início</strong></div>
							</td>
							<td width="20%">
							<div align="center"><strong>Data Relação Fim</strong></div>
							</td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td colspan="5" width="100%">
							<div style="width: 100%; height: 200; overflow: auto;">
							<table width="100%" bgcolor="#90c7fc">
								<c:set var="count" value="0" />
								<logic:present name="colecaoUnidadeOrganizacionalTestemunha">
									<logic:iterate name="colecaoUnidadeOrganizacionalTestemunha"
										id="unidadeOrganizacionalTestemunha" type="UnidadeOrganizacionalTestemunha">
										<c:set var="count" value="${count+1}" />
										<c:choose>
											<c:when test="${count%2 == '1'}">
												<tr bgcolor="#FFFFFF">
											</c:when>
											<c:otherwise>
												<tr bgcolor="#cbe5fe">
											</c:otherwise>
										</c:choose>
										<td bordercolor="#90c7fc" width="10%">
										<div align="center"><logic:notPresent
											name="unidadeOrganizacionalTestemunha" property="dataFimRelacao">
											<img src="<bean:message key='caminho.imagens'/>Error.gif"
												width="14" height="14" style="cursor:hand;" name="imagem"
												onclick="remover('${count}');" alt="Remover">
										</logic:notPresent>&nbsp;</div>
										</td>

										<td bordercolor="#90c7fc" align="left" width="25%"><logic:present
											name="unidadeOrganizacionalTestemunha" property="unidadeOrganizacional">
											<bean:write name="unidadeOrganizacionalTestemunha"
												property="unidadeOrganizacional.descricao" />
										</logic:present>&nbsp;</td>

										<td bordercolor="#90c7fc" align="left" width="25%"><logic:present
											name="unidadeOrganizacionalTestemunha" property="usuario">
											<bean:write name="unidadeOrganizacionalTestemunha"
												property="usuario.nomeUsuario" />
										</logic:present>&nbsp;</td>

										<td bordercolor="#90c7fc" align="center" width="20%"><logic:present
											name="unidadeOrganizacionalTestemunha" property="dataInicioRelacao">
											<bean:write name="unidadeOrganizacionalTestemunha"
												property="dataInicioRelacao" formatKey="date.format" />
										</logic:present>&nbsp;</td>

										<td bordercolor="#90c7fc" align="center" width="20%"><logic:present
											name="unidadeOrganizacionalTestemunha" property="dataFimRelacao">
											<bean:write name="unidadeOrganizacionalTestemunha"
												property="dataFimRelacao" formatKey="date.format" />
										</logic:present>&nbsp;</td>

										</tr>

									</logic:iterate>
								</logic:present>
							</table>
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="2">
					<table width="100%">
						<tr>
							<td width="60%"><font color="#FF0000"> <input type="button"
								name="ButtonReset" class="bottonRightCol" value="Desfazer"
								onClick="javascript:window.location.href='/gsan/exibirInformarUnidadeOrganizacionalTestemunhaAction.do?menu=sim'">
							<input type="button" name="ButtonCancelar" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</font></td>
							<td align="right"><input type="button" name="Button"
								value="Informar" onclick="javascript:informar();"
								class="bottonRightCol" /></td>
						</tr>
					</table>
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
