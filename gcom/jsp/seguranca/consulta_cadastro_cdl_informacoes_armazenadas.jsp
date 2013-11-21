<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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
	formName="ConsultaCadastroCdlInformacoesArmazenadasActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

function limparForm() {
	var form = document.ConsultaCadastroCdlInformacoesArmazenadasForm;
	form.loginUsuario.value = "";
	form.codigoCliente.value = "";
	form.cpfCliente.value = "";
	form.cnpjCliente.value = "";
	form.nomeCliente.value = "";
	form.logradouroCliente.value = "";
	form.numeroImovelCliente.value = "";
	form.bairroCliente.value = "";
	form.complementoEnderecoCliente.value = "";
	form.cidadeCliente.value = "";
	form.cepCliente.value = "":
	form.codigoDdd.value = "";
	form.telefoneCliente.value = "";
}

</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:limparForm();setarFoco('${requestScope.nomeCampo}');">


<html:form action="/exibirConsultaCadastroCdlInformacoesArmazenadasAction.do"
	name="ConsultaCadastroCdlInformacoesArmazenadasActionForm"
	type="gcom.seguranca.ConsultaCadastroCdlInformacoesArmazenadasActionForm" method="post"
	>


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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

			<td width="600" valign="top" class="centercoltext">

			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consulta informações armazenadas no cadastro da Receita Federal</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

    		<table width="80%" border="0">
				
				<tr>
					<td><strong>Login:</strong></td>
					
					<td>
						<html:text maxlength="11"
							property="loginUsuario" 
							size="12" 
							tabindex="1" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							/>
					</td>
				</tr>
				
				<tr>
					<td><strong>Cliente:</strong> </td>
					<td colspan='3' >
						<html:text maxlength="10"
							property="codigoCliente" 
							size="12" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
						
						<html:text maxlength="50"
							property="nomeCliente" 
							size="50" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />							
					</td>
				</tr>
				
				<tr>
					<td><strong>CPF:</strong></td>
					<td width="20">
						<html:text maxlength="11"
							property="cpfCliente" 
							size="14" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
						
						<strong>CNPJ:</strong>
						
						<html:text maxlength="14"
							property="cnpjCliente" 
							size="17" 
							tabindex="4" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
							
					</td>
				</tr>
	
				<tr>
					<td><strong>Logradouro:</strong>
					</td>
					
					<td>
						<html:text maxlength="50"
							property="logradouroCliente" 
							size="50" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
					
					<td><strong>Número:</strong>
					</td>
					
					<td>
						<html:text maxlength="10"
							property="numeroImovelCliente" 
							size="12" 
							tabindex="7" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
				</tr>
	
				<tr>
					<td ><strong>Bairro:
						</strong>
					</td>
					
					<td>
						<html:text maxlength="50"
							property="bairroCliente" 
							size="50" 
							tabindex="8" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
					
					<td><strong>Comp.:</strong>
					</td>
					
					<td>
						<html:text maxlength="10"
							property="complementoEnderecoCliente" 
							size="12" 
							tabindex="9" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
				</tr>
				
				<tr>
					<td><strong>Cidade:
						</strong>
					</td>
					
					<td>
						<html:text maxlength="50"
							property="cidadeCliente" 
							size="50" 
							tabindex="10"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>
					
					<td><strong>CEP:</strong>
					</td>
					
					<td>
						<html:text maxlength="10"
							property="cepCliente" 
							size="12" 
							tabindex="11"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Telefone:
						</strong>
					</td>
					
					<td>
						<html:text maxlength="3"
							property="codigoDdd" 
							size="4" 
							tabindex="12" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					
						<html:text maxlength="10"
							property="telefoneCliente" 
							size="12" 
							tabindex="13" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
				</tr>	

				<tr>
					<td colspan="2">
						<input name="Button" 
						type="button"
						class="bottonRightCol" 
						value="Voltar" 
						align="left"
						onclick="window.location.href='/gsan/filtrarConsultaCadastroCdlAction.do'">&nbsp; 
					&nbsp;
						
					</td>
					
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>
