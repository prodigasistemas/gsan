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
	formName="FiltrarUnidadeNegocioActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(){
			var form = document.forms[0];	
			
			if(validateFiltrarUnidadeNegocioActionForm(form)){    		
		  			submeterFormPadrao(form);
			}
	}


    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.atualizar.checked = true;
		 }else{
		 	form.atualizar.checked = false;
		}
	}
	

	
	function limparForm() {
		var form = document.FiltrarUnidadeNegocioActionForm;
		form.id.value = "";
		form.nome.value = "";
		form.nomeAbreviado.value = "";
		form.numeroCnpj.value = "";
		form.idCliente.value = "";
		form.nomeCliente.value = "";
		form.idGerenciaRegional.value = "";
		form.nomeGerenciaRegional.value = "";
	}
	
	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	    if (tipoConsulta == 'cliente') {
	    	form.idCliente.value = codigoRegistro;
	      	form.action='exibirFiltrarUnidadeNegocioAction.do';
	      	form.submit();
	    }
	    if (tipoConsulta == 'gerenciaRegional') {
	    	form.idGerenciaRegional.value = codigoRegistro;
	      	form.action='exibirFiltrarUnidadeNegocioAction.do';
	      	form.submit();
	    }
	}
	
		function limparCliente() {
		var form = document.forms[0];
		form.idCliente.value = "";
		form.nomeCliente.value = "";
	
	}
	
	
	function limparGerenciaRegional() {
		var form = document.forms[0];
		form.idGerenciaRegional.value = "";
		form.nomeGerenciaRegional.value = "";
	
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/filtrarUnidadeNegocioAction"
	name="FiltrarUnidadeNegocioActionForm"
	type="gcom.gui.cadastro.localidade.FiltrarUnidadeNegocioActionForm"
	method="post"
	onsubmit="return validateFiltrarUnidadeNegocioActionForm(this);">


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
					<td class="parabg">Filtrar Unidade de Negócio</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para filtrar a(s) unidade(s) de negócio, informe o
					dado abaixo:</td>
					<td width="100" align="right" colspan="2"><html:checkbox property="atualizar" value="1" />
					<strong>Atualizar</strong></td>
				</tr>

				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:text property="id" size="3" maxlength="3" tabindex="1"/><font
						size="1">&nbsp;(somente números)</font> <br>
					<font color="red"><html:errors property="id" /></font></td>
				</tr>
				<tr>
					<td><strong>Nome:</strong></td>
					<td colspan="2"><span class="style2"> <html:text property="nome"
						size="50" maxlength="50" tabindex="2"/> </span></td>
					<td width="15%">&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="3"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="4"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Nome Abreviado:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="nomeAbreviado" size="4" maxlength="5" /> </span></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>CNPJ:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numeroCnpj" size="14" maxlength="14" tabindex="6"/> </span></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td HEIGHT="30" WIDTH="120"><strong>Gerente da Unidade de Neg&oacute;cio:</strong></td>
					<td title="Pesquisar Gerente da Unidade de Negócio" colspan="3"><html:text property="idCliente" size="10"
						maxlength="9" tabindex="7"
						onkeypress="validaEnterComMensagem(event, 'exibirFiltrarUnidadeNegocioAction.do', 'idCliente', 'Cliente');" />
					<a
						href="javascript:chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 800, 490, '',document.forms[0].idCliente);">
					<img  src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
						width="23" height="21" alt="Pesquisar" border="0"></a> <logic:notPresent
						name="corCliente" scope="request">

						<html:text property="nomeCliente" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="37" maxlength="40" />

					</logic:notPresent> <logic:present name="corCliente"
						scope="request">

						<html:text property="nomeCliente" size="30" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />

					</logic:present> <a href="javascript:limparCliente();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>

				<tr>
					<td HEIGHT="30" WIDTH="100"><strong>Gerencial Regional:</strong></td>
					<td colspan="3"><html:text property="idGerenciaRegional" size="10"
						maxlength="9" tabindex="8"
						onkeypress="validaEnterComMensagem(event, 'exibirFiltrarUnidadeNegocioAction.do', 'idGerenciaRegional', 'Cliente');" />
					<a
						href="javascript:chamarPopup('exibirPesquisarGerenciaRegionalAction.do', 'cliente', null, null, 800, 490, '',document.forms[0].idGerenciaRegional);">
					<img title="Pesquisar Gerencial Regional" src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
						width="23" height="21" alt="Pesquisar" border="0"></a> <logic:notPresent
						name="corGerenciaRegional" scope="request">

						<html:text property="nomeGerenciaRegional" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="37" maxlength="40" />

					</logic:notPresent> <logic:present name="corGerenciaRegional"
						scope="request">

						<html:text property="nomeGerenciaRegional" size="30"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />

					</logic:present> <a href="javascript:limparGerenciaRegional();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>

				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="9" value="1" /><strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="10" value="2" /><strong>Inativo</strong>
					<html:radio property="indicadorUso" tabindex="11" value="" /><strong>Todos</strong>
					</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarUnidadeNegocioAction.do?menu=sim'"
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
