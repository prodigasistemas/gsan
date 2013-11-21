<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarDivisaoEsgotoActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">


	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
	    var form = document.forms[0];
		if (tipoConsulta == 'unidadeOrganizacional') {
     		form.unidadeOrganizacionalId.value = codigoRegistro;
	    	form.unidadeOrganizacionalDescricao.value = descricaoRegistro;
	    	form.unidadeOrganizacionalDescricao.style.color = '#000000';
	     }
	}
	     
	     
	function validarForm(formulario){	
			if(validateFiltrarDivisaoEsgotoActionForm(formulario)){    		
	    		if(validateInteger(formulario)){	     
		  			submeterFormPadrao(formulario);
		  		}
		}
	}
	
	function IntegerValidations () {
		this.aa = new Array("id", "O campo Código deve conter apenas números.", new Function ("varName", " return this[varName];"));
	}

    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	
	function limparForm() {
		var form = document.AtualizarDivisaoEsgotoActionForm;
		form.descricao.value = "";
		form.unidadeOrganizacional.value = "";
		form.indicadorUso.value = "";
	
	}
	function IntegerValidations () {
		this.aa = new Array("id", "O campo Código deve conter apenas números.", new Function ("varName", " return this[varName];"));
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

<body leftmargin="5" topmargin="5">
<html:form action="/filtrarDivisaoEsgotoAction"
	name="FiltrarDivisaoEsgotoActionForm"
	type="gcom.gui.operacional.FiltrarDivisaoEsgotoActionForm"
	method="post"
	onsubmit="return validateFiltrarDivisaoEsgotoActionForm(this);">


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
			<td width="615" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Divisão de Esgoto</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para filtrar a(s) divisão(ões) de esgoto, informe o
					dado abaixo:</td>
					<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>

				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:text property="id" size="5" maxlength="5" /><font
						size="1">&nbsp;(somente números)</font> <br>
					<font color="red"><html:errors property="id" /></font></td>
				</tr>

				<tr>
					<td><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="30" maxlength="30" /> </span></td>
					<td width="15%">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Unidade Organizacional: </strong></td>
					<td colspan="3"><html:text property="unidadeOrganizacionalId"
						size="5" maxlength="5" tabindex="1"
						onkeypress="limpar(2);validaEnterComMensagem(event, 'exibirFiltrarDivisaoEsgotoAction.do?objetoConsulta=1', 'unidadeOrganizacionalId','Unidade Organizacional');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do?indicadorUsoTodos=1', 250, 495);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Unidade Organizacional" /></a> <logic:present
						name="corUnidadeOrganizacional">

						<logic:equal name="corUnidadeOrganizacional" value="exception">
							<html:text property="unidadeOrganizacionalDescricao" size="42"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corUnidadeOrganizacional" value="exception">
							<html:text property="unidadeOrganizacionalDescricao" size="42"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corUnidadeOrganizacional">

						<logic:empty name="FiltrarDivisaoEsgotoActionForm"
							property="unidadeOrganizacionalId">
							<html:text property="unidadeOrganizacionalDescricao" size="45"
								value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarDivisaoEsgotoActionForm"
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
					<td><strong>Indicador de Uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="5" value="1" /><strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="7" value="2" /><strong>Inativo</strong>
					<html:radio property="indicadorUso" tabindex="8" value="" /><strong>Todos</strong>
					</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarDivisaoEsgotoAction.do?menu=sim'"
						tabindex="8"></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td width="65" align="right"><input name="Button2" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm(document.forms[0]);" tabindex="9" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
