<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarArrecadadorActionForm" />


<html:html>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--

	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
		if (tipoConsulta == 'cliente') {
	        form.idCliente.value = codigoRegistro;
	        form.nomeCliente.value = descricaoRegistro;
	     }
	     if(tipoConsulta == 'imovel'){
	        form.idImovel.value = codigoRegistro;
	        form.inscricaoImovel.value = descricaoRegistro;
    	}
	}	
	
	// Chama Popup
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}

	function limparForm(tipo){
      var form = document.forms[0];
	    if(tipo == 'imovel')
	    {
	        form.idImovel.value = "";
	        form.inscricaoImovel.value = "";
		}
		if(tipo == 'cliente')
	    { 
	        form.idCliente.value = "";
	        form.nomeCliente.value = "";
		}
	}	

	function validarForm(formulario){
		if(validateFiltrarArrecadadorActionForm(formulario)){
    		submeterFormPadrao(formulario);
		}
	}
	
	
	function validarForm(formulario){
	if(testarCampoValorZero(document.FiltrarArrecadadorActionForm.idImovel, 'idImovel') && testarCampoValorZero(document.FiltrarArrecadadorActionForm.idCliente, 'idCliente')&& testarCampoValorZero(document.FiltrarArrecadadorActionForm.idAgente, 'idAgente')) {
		if(validateFiltrarArrecadadorActionForm(formulario)){
   			if(validateInteger(formulario)){
   				submeterFormPadrao(formulario);
			}
		}
	}
}

	function IntegerValidations () {
		this.aa = new Array("idCliente", "O campo Cliente contém caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("idImovel", "O campo Imóvel contém caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("idAgente", "O campo Código do Agente contém caracteres especiais.", new Function ("varName", "return this[varName];"));
	}
	
	
	
	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	
//-->
</SCRIPT>
</head>

<body leftmargin="5" topmargin="5"
	onLoad="verificarChecado('${indicadorAtualizar}');">

<html:form action="/filtrarArrecadadorAction"
	name="FiltrarArrecadadorActionForm"
	type="gcom.gui.arrecadacao.FiltrarArrecadadorActionForm" method="post"
	onsubmit="">

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
					<td class="parabg">Filtrar Arrecadador</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>
					<td width="100%" colspan="3">
					<table width="100%">
						<tr>
							<td width="80%">Para filtar o(s) arrecadador(es), informe os
							dados abaixo:</td>
							<td align="right"><input type="checkbox"
								name="indicadorAtualizar" value="1"
								onclick="" /><strong>Atualizar</strong>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td width="139" height="22"><strong>Código do Agente:<font
						color="#FF0000"></font></strong></td>

					<td width="406"><strong> <html:text property="idAgente" size="4"
						maxlength="3"/>
					</strong></td>
				</tr>
				<tr>
					<td><strong>Cliente:<font color="#FF0000"></font></strong></td>
					<td><strong> <html:text property="idCliente" size="10"
						maxlength="10" 
						onkeypress="javascript:return validaEnter(event, 'exibirFiltrarArrecadadorAction.do?objetoConsulta=1', 'idCliente');" />
					</strong> <a
						href="javascript:chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',document.forms[0].idCliente.value);"
						alt="Pesquisar Cliente"> <img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="existeCliente">
						<logic:equal name="existeCliente" value="exception">
							<html:text property="nomeCliente" size="30" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="existeCliente" value="exception">
							<html:text property="nomeCliente" size="30" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="existeCliente">
						<logic:empty name="FiltrarArrecadadorActionForm"
							property="idCliente">
							<html:text property="nomeCliente" size="30" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarArrecadadorActionForm"
							property="idCliente">
							<html:text property="nomeCliente" size="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparForm('cliente');"> <img
						border="0"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						style="cursor: hand;" /> </a></td>
				</tr>
				<tr>
					<td><strong>Imóvel:</strong></td>
					<td><strong><html:text property="idImovel" size="10" maxlength="10"
						
						onkeypress="javascript:return validaEnter(event, 'exibirFiltrarArrecadadorAction.do?objetoConsulta=2', 'idImovel');" />
					<a
						href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].idImovel.value);"
						alt="Pesquisar Imóvel"> <img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a></strong>
					<logic:present name="existeImovel">
						<logic:equal name="existeImovel" value="exception">
							<html:text property="inscricaoImovel" size="30" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="existeImovel" value="exception">
							<html:text property="inscricaoImovel" size="30" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="existeImovel">
						<logic:empty name="FiltrarArrecadadorActionForm"
							property="idImovel">
							<html:text property="inscricaoImovel" size="30" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarArrecadadorActionForm"
							property="idImovel">
							<html:text property="inscricaoImovel" size="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparForm('imovel');"> <img
						border="0"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						style="cursor: hand;" /> </a></td>
				</tr>

				<tr>
					<td><strong>Inscrição Estadual:</strong></td>
					<td><strong> <html:text property="inscricaoEstadual" size="20"
						maxlength="20" />
					</strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1"/><strong>Ativo</strong>
						<html:radio	property="indicadorUso" tabindex="3" value="2"/><strong>Inativo</strong>
						<html:radio	property="indicadorUso" tabindex="4" value=""/><strong>Todos</strong>
					</td>
				</tr>

				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarArrecadadorAction.do?menu=sim'"
						tabindex="8"></td>
					<td align="right" colspan="2">&nbsp;</td>
					<td width="65" align="right"><input name="Button2" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm(document.forms[0]);" tabindex="9"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>

