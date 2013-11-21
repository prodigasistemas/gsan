<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
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
	formName="FiltrarColetaMedidorEnergiaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">


function limparLocalidade() {
	var form = document.forms[0];
	
	form.idLocalidade.value = "";
    form.localidadeDescricao.value = "";
    form.codigoSetorComercial.value = "";
   	form.setorComercialDescricao.value = "";
   	form.rota.value = "";
}
	
function limparSetorComercial() {
   	var form = document.forms[0];
   	
   	form.codigoSetorComercial.value = "";
   	form.setorComercialDescricao.value = "";
   	form.rota.value = "";
}

function limparDescLocalidade(form) {
   	form.localidadeDescricao.value = "";
   	form.codigoSetorComercial.value = "";
}

function limparDescSetorComercial(form) {
   	form.setorComercialDescricao.value = "";
}

function limparRota(form){
	form.rota.value = "";
}
	
function validarForm(){
	var form = document.forms[0];
	
	if(validateFiltrarColetaMedidorEnergiaActionForm(form)){
		form.action = 'filtrarColetaMedidorEnergiaAction.do' ;
   		submeterFormPadrao(form);
	
	}
}
	
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {
    	limparDescLocalidade(form);
      	form.idLocalidade.value = codigoRegistro;
      	form.localidadeDescricao.value = descricaoRegistro;
      	form.localidadeDescricao.style.color = "#000000";
      	form.idLocalidade.focus();
    }

    if (tipoConsulta == 'setorComercial') {
    	limparSetorComercial(form);          
      	form.codigoSetorComercial.value = codigoRegistro;
      	form.setorComercialDescricao.value = descricaoRegistro;
      	form.setorComercialDescricao.style.color = "#000000";
    }

 }


</script>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarColetaMedidorEnergiaAction.do"
	name="FiltrarColetaMedidorEnergiaActionForm"
	type="gcom.gui.micromedicao.FiltrarColetaMedidorEnergiaActionForm"
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
			<td width="660" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Informar Medidor de Energia por Rota</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>

			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>

			<table width="100%" border="0">
			<tr>
				<td colspan="3">Para informar o Medidor de Energia por rota, informe os campos abaixo:</td>
				<td align="right"></td>
			</tr>
			</table>
			
			<table width="100%" border="0">
			
			<tr>
				<td><strong>Localidade:<font
						color="#FF0000">*</font></strong></td>
				
				<td>
					<html:text maxlength="3" 
						tabindex="1"
						property="idLocalidade" 
						size="3"
						onkeypress="javascript:limparDescLocalidade(document.FiltrarColetaMedidorEnergiaActionForm);
						limparDescSetorComercial(document.FiltrarColetaMedidorEnergiaActionForm);
						validaEnterComMensagem(event, 'exibirInformarMedidorEnergiaRotaAction.do?objetoConsulta=1', 'idLocalidade','Localidade');return isCampoNumerico(event);"/>
						
					<a
						href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade&indicadorUsoTodos=1', 400, 800);limparDescSetorComercial(document.FiltrarColetaMedidorEnergiaActionForm);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> 
							
					<logic:present name="FiltrarColetaMedidorEnergiaActionForm" property="localidadeNaoEncontrada" >
						<logic:equal name="FiltrarColetaMedidorEnergiaActionForm" property="localidadeNaoEncontrada" value="true">
							<html:text property="localidadeDescricao" 
									   size="30"
									   maxlength="30" 
									   readonly="true"
									   style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:equal>
						<logic:notEqual name="FiltrarColetaMedidorEnergiaActionForm" property="localidadeNaoEncontrada" value="true">
							<html:text property="localidadeDescricao" 
									   size="30"
									   maxlength="30" 
									   readonly="true"
									   style="background-color:#EFEFEF; border:0; color: #FF0000" />
						</logic:notEqual>
					</logic:present>
					
					<logic:notPresent name="FiltrarColetaMedidorEnergiaActionForm" property="localidadeNaoEncontrada">
						<html:text property="localidadeDescricao" 
								   size="30"
								   maxlength="30" 
								   readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent>
					
					<a href="javascript:limparLocalidade();"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" 
							title="Apagar" /></a>
				</td>
			</tr>

			<tr>
				<td><strong>Setor Comercial:<font
						color="#FF0000">*</font></strong></td>
				<td><html:text tabindex="3" maxlength="3"
					property="codigoSetorComercial" size="3"
					onkeypress="javascript:validaEnterDependenciaComMensagem(event, 'exibirInformarMedidorEnergiaRotaAction.do?objetoConsulta=2', this, document.forms[0].idLocalidade.value, 'Localidade','Setor Comercial');return isCampoNumerico(event);"/>
				<a
						href="javascript:limparDescSetorComercial(document.FiltrarColetaMedidorEnergiaActionForm);
						abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial&indicadorUsoTodos=1',document.forms[0].idLocalidade.value,'Localidade', 400, 800);">

					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> 
					
					<logic:present name="FiltrarColetaMedidorEnergiaActionForm" property="setorComercialNaoEncontrado">
						<logic:equal name="FiltrarColetaMedidorEnergiaActionForm" property="setorComercialNaoEncontrado" value="true"> 
							<html:text property="setorComercialDescricao" 
								 	   size="30"
									   maxlength="30" 
									   readonly="true"
									   style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:equal>
						<logic:notEqual name="FiltrarColetaMedidorEnergiaActionForm" property="setorComercialNaoEncontrado" value="true">
							<html:text property="setorComercialDescricao" 
									   size="30"
									   maxlength="30" 
									   readonly="true"
									   style="background-color:#EFEFEF; border:0; color: #FF0000" />
						</logic:notEqual>
						
					</logic:present> 

					<logic:notPresent name="FiltrarColetaMedidorEnergiaActionForm" property="setorComercialNaoEncontrado">
						<html:text property="setorComercialDescricao" 
							size="30"
							maxlength="30" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />
					</logic:notPresent>
					
					<a href="javascript:limparSetorComercial();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" /></a> 
				</td>
			</tr>
			
			<tr>
				<td><strong>Código da Rota:<font
						color="#FF0000">*</font></strong></td>
				<td><html:text property="rota" size="5" maxlength="5" tabindex="3" onkeypress="javascript:return isCampoNumerico(event);"/></td>
			</tr>

			<tr>
				<td><strong> <font color="#FF0000"></font></strong></td>
				<td align="right">
				<div align="left" colspan="2"><strong><font color="#FF0000">*</font></strong>

				Campos obrigat&oacute;rios</div>
				</td>
			</tr>
								
			<tr class="rigthcoltext">
								
				<td align="left" colspan="2"><input type="button" name="Button"
					class="bottonRightCol" value="Desfazer"
					onClick="window.location.href='/gsan/exibirInformarMedidorEnergiaRotaAction.do?menu=sim'" />
				<input type="button" name="Button" class="bottonRightCol"
					value="Cancelar"
					onClick="window.location.href='/gsan/telaPrincipal.do'" /></td>
				<td align="right" width="23">
					<div align="right">
						<input type="Button" name="Button"  class="bottonRightCol" 
						value="Filtrar" tabindex="8" onclick="javascript:validarForm();"/>
					</div>
				</td>
								
			</tr>
			</table>

			</td>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>

</html:form>
</body>
</html:html>