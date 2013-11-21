<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarCapacidadeHidrometroActionForm" />

<script language="JavaScript">
  
	function validaForm() {
	  	var form = document.forms[0];
	  	form.action = "/gsan/atualizarCapacidadeHidrometroAction.do";
		if(validateAtualizarCapacidadeHidrometroActionForm(form)) {
	     		  		
				submeterFormPadrao(form);   		  
   	      	
   	    }
	}
	 
 
	function limparForm() {
		var form = document.AtualizarCapacidadeHidrometroActionForm;
		form.identificador.value = "";
		form.descricao.value = "";
	    form.numMinimo.value = "";
	    form.numMaximo.value = "";
		form.numOrdem.value = "";
	    form.codigo.value = "";
	    form.indicador.value = "";
			
	}
	
	
	function reload() {
		var form = document.AtualizarCapacidadeHidrometroActionForm;
		form.action = "/gsan/exibirAtualizarCapacidadeHidrometroAction.do";
		form.submit();
	}  
	

</script>

</head>

<body leftmargin="5" topmargin="5";">

<html:form action="/atualizarCapacidadeHidrometroAction"
	name="AtualizarCapacidadeHidrometroActionForm"
	type="gcom.gui.micromedicao.hidrometro.AtualizarCapacidadeHidrometroActionForm"
	method="post"
	onsubmit="return validateAtualizarCapacidadeHidrometroActionForm(this);">

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

			<!-- centercoltext -->

			<td width="625" valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Capacidade de Hidrômetro</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">Para Atualizar uma capacidade de
					hidrômetro, informe os dados abaixo:</td>
				</tr>


				<!-- Descricao -->

				<tr>
					<td><strong>Identicador da capacidade de Hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="identificador" size="2"
						maxlength="2" tabindex="2" disabled="true" /></td>
					<td></td>
				</tr>
				<tr>
					<td><strong>Descrição:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" size="50"
						maxlength="20" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Descrição Abreviada:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="abreviatura" size="6"
						maxlength="6" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Número mínimo de dígitos de leitura do hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="numMinimo" size="1"
						maxlength="1" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Número máximo de dígitos de leitura do hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="numMaximo" size="1"
						maxlength="1" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Número de ordem da capacidade do hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="numOrdem" size="2"
						maxlength="2" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Código da Capacidade do hidrômetro:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="codigo" size="1" maxlength="1"
						tabindex="2" /></td>
				</tr>
				
				<tr>
			        <td><strong>Indicador de uso:</strong></td>
			        <td>
						<html:radio property="indicadoruso" value="1"/><strong>Ativo
						<html:radio property="indicadoruso" value="2"/>Inativo</strong>
					</td>
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><font color="#FF0000"> <logic:present
						name="inserir" scope="request">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarCapacidadeHidrometro.do?menu=sim'">
					</logic:present><logic:notPresent name="inserir" scope="request">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar" onClick="javascript:history.back();">
					</logic:notPresent><input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td align="right"><gsan:controleAcessoBotao name="Button"
						value="Atualizar"
						onclick="javascript:validaForm(document.forms[0]);"
						url="atualizarCapacidadeHidrometroAction.do" /> <%-- <input type="button" name="Button" class="bottonRightCol" value="Atualizar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</html:html>
