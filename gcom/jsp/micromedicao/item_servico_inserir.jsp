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
	formName="InserirItemServicoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">


	function validarForm(formulario) {
		
			if (validateInserirItemServicoActionForm(formulario)){
				submeterFormPadrao(formulario);
			}
		
	}
  	
  	function limparForm() {
		var form = document.forms[0];
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		form.codigoConstanteCalculo.value = "";
		form.codigoItem.value = "";
	}	

</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">


<html:form action="/inserirItemServicoAction.do"
	name="InserirItemServicoActionForm"
	type="gcom.gui.micromedicao.InserirItemServicoActionForm" method="post"
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

			<td width="625" valign="top" class="centercoltext">

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
					<td class="parabg">Inserir Item de Contrato</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar o item de contrato, informe os dados
					abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClienteRamoAtividadeInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>				
    			</tr>
    		</table>
    
    		<table width="100%" border="0">
				
				<tr>
					<td><strong>Descrição:
						<font color="#FF0000">*</font></strong>
					</td>
					
					<td align="right">
					<div align="left">
						<html:text maxlength="100"
							property="descricao" 
							size="50" 
							tabindex="1" 
							/></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Descrição Abreviada:
						<font color="#FF0000">*</font></strong>
					</td>
					
					<td align="right">
					<div align="left">
						<html:text maxlength="30"
							property="descricaoAbreviada" 
							size="32" 
							tabindex="2" /></div>
					</td>
				</tr>
				 
				<tr>
					<td><strong>Código Constante de Cálculo:
						</strong>
					</td>
					
					<td align="right">
					<div align="left">
						<html:text maxlength="4"
							property="codigoConstanteCalculo" 
							size="5" 
							tabindex="3" 
							onkeypress="return isCampoNumerico(event);"/></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Código do Item:
						</strong>
					</td>
					
					<td align="right">
					<div align="left">
						<html:text maxlength="11"
							property="codigoItem" 
							size="12" 
							tabindex="4" 
							onkeypress="return isCampoNumerico(event);"/></div>
					</td>
				</tr>
				 
				<tr>
					<td>&nbsp;</td>
					<td align="right">
						<div align="left">
							<strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input name="Button" 
						type="button"
						class="bottonRightCol" 
						value="Limpar" 
						align="left"
						onclick="javascript:limparForm();">&nbsp; 
					&nbsp;
						<input type="button" 
								name="Button"
								class="bottonRightCol" 
								value="Cancelar" 
								tabindex="6"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
								style="width: 80px" />
					</td>
					
					<td align="right">
						<gsan:controleAcessoBotao name="Button" 
							value="Inserir"
							onclick="javascript:validarForm(document.forms[0]);"
							url="inserirItemServicoAction.do" />
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
