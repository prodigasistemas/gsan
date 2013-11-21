<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

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
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarNumeracaoManualRegistroAtendimentoActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--


function limparDescricao(){

	var form = document.forms[0];
	
	form.descricaoUnidadeOrganizacional.value = "";
}

function limpar(){

	var form = document.forms[0];
	
	form.idUnidadeOrganizacional.value = "";
	form.descricaoUnidadeOrganizacional.value = "";
	
	form.idUnidadeOrganizacional.focus();
}

function validarForm(form){

	if (validateGerarNumeracaoManualRegistroAtendimentoActionForm(form) &&
	    verificarQuantidade(form)){
		toggleBox('demodiv',1);
	}
	
}

function verificarQuantidade(form){

	var limite = document.getElementById("qtdLimite").value;
	var retorno = true;
	
	if (form.quantidade.value > limite){
		alert("Quantidade informada ultrapassa o valor limite que é de " + limite);
		form.quantidade.focus();
		retorno = false;
	}
	
	return retorno;
}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
    
    if (tipoConsulta == 'unidadeOrganizacional') {
    	form.idUnidadeOrganizacional.value = codigoRegistro;
    	form.descricaoUnidadeOrganizacional.value = descricaoRegistro;
    	form.descricaoUnidadeOrganizacional.style.color = "#000000";
    }
}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<input type="hidden" name="qtdLimite" id="qtdLimite" value="<%="" + ConstantesSistema.LIMITE_QUANTIDADE_GERACAO_MANUAL %>">

<html:form action="/gerarNumeracaoManualRegistroAtendimentoAction" method="post">

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
					<td class="parabg">Gerar Relação de Numeração Manual</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">



				<tr>
					<td width="100%" colspan=2>
					<table>
						<tr>
							<td width="512px">Para gerar a relação de numeração manual, informe os dados abaixo:</td>
							<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroNumeracaoRAManualGerar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
							</logic:present>
							<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
							</logic:notPresent>							
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td width="145"><strong>Último Valor Gerado:</strong></td>
					<td width="470"><html:text property="ultimoValorGerado" size="12"
					maxlength="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
				<tr>
					<td><strong>Quantidade Desejada:<SPAN STYLE="color: #FF0000">*</SPAN></strong></td>
					<td><html:text property="quantidade" size="5" maxlength="2" tabindex="1" /></td>
				</tr>
				<tr>
		      		<td width="145"><strong>Unidade Organizacional:<SPAN STYLE="color: #FF0000">*</SPAN></strong></td>
		        	<td width="470">
					<html:text property="idUnidadeOrganizacional" size="5" maxlength="4" tabindex="2" onkeypress="limparDescricao();validaEnterComMensagem(event, 'exibirGerarNumeracaoManualRegistroAtendimentoAction.do?pesquisarUnidade=OK', 'idUnidadeOrganizacional', 'Unidade Organizacional');"/>
					<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 410, 790);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0"></a>
		
					<logic:present name="corUnidade">
		
						<logic:equal name="corUnidade" value="exception">
							<html:text property="descricaoUnidadeOrganizacional" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:equal>
		
						<logic:notEqual name="corUnidade" value="exception">
							<html:text property="descricaoUnidadeOrganizacional" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEqual>
		
					</logic:present>
		
					<logic:notPresent name="corUnidade">
		
						<logic:empty name="GerarNumeracaoManualRegistroAtendimentoActionForm" property="idUnidadeOrganizacional">
							<html:text property="descricaoUnidadeOrganizacional" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:empty>
						<logic:notEmpty name="GerarNumeracaoManualRegistroAtendimentoActionForm" property="idUnidadeOrganizacional">
							<html:text property="descricaoUnidadeOrganizacional" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEmpty>
						
		
					</logic:notPresent>
		        	
		        	<a href="javascript:limpar();">
		        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0"></a>
		       		</td>
			  	</tr>
			</table>  	
			
			<table width="100%" border="0">
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirGerarNumeracaoManualRegistroAtendimentoAction.do?desfazer=S"/>'" tabindex="3" style="width: 70px;">
						<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='<html:rewrite page="/telaPrincipal.do"/>'" tabindex="4" style="width: 70px;">
					</td>
					<td align="right">
						<gsan:controleAcessoBotao name="gerar" value="Gerar" onclick="validarForm(document.forms[0]);" url="gerarNumeracaoManualRegistroAtendimentoAction.do" tabindex="5"/>	
					</td>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarNumeracaoManualRegistroAtendimentoAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>


