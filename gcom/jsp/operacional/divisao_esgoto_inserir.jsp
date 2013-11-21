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
	formName="InserirDivisaoEsgotoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirDivisaoEsgotoActionForm(form)){
				submeterFormPadrao(form);
			}	
	}

	function limparForm() {
		var form = document.InserirDivisaoEsgotoActionForm;
		
		form.descricao.value = "";
		form.unidadeOrganizacionalId.value = "";
     	form.unidadeOrganizacionalDescricao.value = "";
		
	}
	
	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
	    var form = document.forms[0];
		if (tipoConsulta == 'unidadeOrganizacional') {
     		form.unidadeOrganizacionalId.value = codigoRegistro;
	    	form.unidadeOrganizacionalDescricao.value = descricaoRegistro;
	    	form.unidadeOrganizacionalDescricao.style.color = '#000000';
	     }
	}
	
	function limparUnidade(){
		var form = document.forms[0];

   		form.unidadeOrganizacional.value = "";
     	form.unidadeOrganizacionalDescricao.value = "";
	}
	function limpar(tipo){
		var form = document.forms[0];
		switch (tipo){
			case 1:
			   form.unidadeOrganizacionalId.value = "";
		  	   form.unidadeOrganizacionalDescricao.value = "";
           	   form.unidadeOrganizacionalDescricao.style.color = "#000000";

  		       //Coloca o foco no objeto selecionado
		       form.unidadeOrganizacionalId.focus();
		       break;
		   case 2:
		   form.unidadeOrganizacionalDescricao.value = "";
           form.unidadeOrganizacionalDescricao.style.color = "#000000";

		   //Coloca o foco no objeto selecionado
		   form.unidadeOrganizacionalId.focus();
		   break;
		}
	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">

<html:form action="/inserirDivisaoEsgotoAction.do"
	name="InserirDivisaoEsgotoActionForm"
	type="gcom.gui.operacional.InserirDivisaoEsgotoActionForm"
	method="post"
	onsubmit="return validateInserirDivisaoEsgotoActionForm(this);">

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
					<td class="parabg">Inserir Divisão de Esgoto</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para adicionar uma divisão de esgoto, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Descrição: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" maxlength="30"
						size="30" />
						<br>
					</td>
				</tr>

					<tr>
					<td><strong>Unidade Organizacional:<font color="#FF0000">*</font> </strong></td>
					<td colspan="3"><html:text property="unidadeOrganizacionalId"
						size="5" maxlength="5" tabindex="1"
						onkeypress="limpar(2);validaEnterComMensagem(event, 'exibirInserirDivisaoEsgotoAction.do?objetoConsulta=1', 'unidadeOrganizacionalId','Unidade Organizacional');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do?indicadorUsoTodos=1', 250, 495);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Unidade Organizacional" /></a> <logic:present
						name="corUnidadeOrganizacional">

						<logic:equal name="corUnidadeOrganizacional" value="exception">
							<html:text property="unidadeOrganizacionalDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corUnidadeOrganizacional" value="exception">
							<html:text property="unidadeOrganizacionalDescricao" size="42"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corUnidadeOrganizacional">

						<logic:empty name="InserirDivisaoEsgotoActionForm"
							property="unidadeOrganizacionalId">
							<html:text property="unidadeOrganizacionalDescricao" size="40"
								value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirDivisaoEsgotoActionForm"
							property="unidadeOrganizacionalId">
							<html:text property="unidadeOrganizacionalDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limpar(1);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>







				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="javascript:limparForm();">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td width="53" height="24" align="right"><input type="button"
						name="Button2" class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm(document.forms[0])" /></td>
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
