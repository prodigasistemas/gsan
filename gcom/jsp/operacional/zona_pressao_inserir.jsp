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
	formName="InserirZonaPressaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirZonaPressaoActionForm(form)){
       		submeterFormPadrao(form);
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];
		if (tipoConsulta == 'distrito') {
		     form.distritoOperacionalID.value = codigoRegistro;
		 	 form.distritoOperacionalDescricao.value = descricaoRegistro;
	  	     form.distritoOperacionalDescricao.style.color = "#000000";
		}
	}
	
	function redirecionarSubmitAtualizar(idZonaPressao) {
		urlRedirect = "/gsan/exibirAtualizarZonaPressaoAction.do?idRegistroInseridoAtualizar=" + idZonaPressao;
		redirecionarSubmit(urlRedirect);
	}
	
	
	function limpar(tipo){
		var form = document.forms[0];
		switch (tipo){
			case 1:
			   form.distritoOperacionalID.value = "";
		  	   form.distritoOperacionalDescricao.value = "";
           	   form.distritoOperacionalDescricao.style.color = "#000000";

  		       //Coloca o foco no objeto selecionado
		       form.distritoOperacionalID.focus();
		       break;
		   case 2:
		   form.distritoOperacionalDescricao.value = "";
           form.distritoOperacionalDescricao.style.color = "#000000";

		   //Coloca o foco no objeto selecionado
		   form.distritoOperacionalID.focus();
		   break;
		}
	}	
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">
<html:form action="/inserirZonaPressaoAction.do"
	name="InserirZonaPressaoActionForm"
	type="gcom.gui.operacional.InserirZonaPressaoActionForm"
	method="post"
	onsubmit="return validateInserirZonaPressaoActionForm(this);">

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
					<td width="11"><img border="0" src="imagens/parahead_left.gif" />
					</td>
					<td class="parabg">Inserir Zona de Press&atilde;o</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

<table width="100%" border="0">
				<tr>
					<td colspan="2">Para inserir a zona de press&atilde;o, informe os
					dados abaixo:</td>
				</tr>
				<tr>
					
    <td height="30" width="232"><strong>Descri&ccedil;&atilde;o: <font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" maxlength="30"
						size="30"/><br>
					</td>
				</tr>
				<tr>
					
    <td width="232"><strong>Descri&ccedil;&atilde;o Abreviada: </strong></td>
					
    <td width="549"><strong> <html:text property="descricaoAbreviada" size="6"
						maxlength="6"/> </strong></td>
				</tr>
				<tr>
					
    <td width="232"><strong>Distrito Operacional: <font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="distritoOperacionalID" size="5"
						maxlength="3" tabindex="15"
						onkeypress="validaEnterComMensagem(event, 'exibirInserirZonaPressaoAction.do?objetoConsulta=1', 'distritoOperacionalID','Distrito Operacional');"
						onkeyup="limpar(2);" /> <a
						href="javascript:abrirPopup('exibirPesquisarDistritoOperacionalAction.do?menu=sim',275,480);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Distrito Operacional" /></a> <logic:present
						name="corDistritoOperacional">

						<logic:equal name="corDistritoOperacional" value="exception">
							<html:text property="distritoOperacionalDescricao" size="42"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corDistritoOperacional" value="exception">
							<html:text property="distritoOperacionalDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corDistritoOperacional">

						<logic:empty name="InserirZonaPressaoActionForm"
							property="distritoOperacionalID">
							<html:text property="distritoOperacionalDescricao"
								value="" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirZonaPressaoActionForm"
							property="distritoOperacionalID">
							<html:text property="distritoOperacionalDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limpar(1);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>

				<tr>
					
    <td width="232">&nbsp;</td>
					
    <td align="left" width="549"><font color="#FF0000">*</font> Campos Obrigatórios</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirZonaPressaoAction.do?menu=sim"/>'"> <input name="Button"
						type="button" class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					
    <td width="192" height="24" align="right">
<input type="button"
						name="Button2" class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm();" /></td>
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
