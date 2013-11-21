<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarOperacaoActionForm" />

<script language="JavaScript">

 
function validarForm(form){

  if(testarCampoValorZero(document.FiltrarOperacaoActionForm.idOperacao, 'Código da Operação') && testarCampoValorZero(document.FiltrarOperacaoActionForm.descricaoOperacao, 'Descrição') && testarCampoValorZero(document.FiltrarOperacaoActionForm.idFuncionalidade, 'Código da Funcionalidade')){
    if(validateFiltrarOperacaoActionForm(form)){
      submeterFormPadrao(form);
    }
  }  
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
					abrirPopup(url + "?" + "menu=sim&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'funcionalidade') {
      form.idFuncionalidade.value = codigoRegistro;
      //form.descricaoFuncionalidade.value = descricaoRegistro;
      form.action='exibirFiltrarOperacaoAction.do';
      form.submit();
    }
}

function limpar(form){
  form.idOperacao.value = "";	
  form.descricaoOperacao.value = "";
  form.idTipoOperacao.value = "-1";
  limparFuncionalidade();
  setarFoco('idOperacao');
}

function limparFuncionalidade(){
 	var form = document.forms[0];
 	
 	form.idFuncionalidade.value = "";
 	form.descricaoFuncionalidade.value = ""; 
 	setarFoco('idFuncionalidade');		
 }

function limparDescricaoFuncionalidade(){
 	var form = document.forms[0];
 	form.descricaoFuncionalidade.value = ""; 		
}
 
function verificarChecado(valor){
	form = document.forms[0];
	if(valor == "1"){
	 	form.indicadorAtualizar.checked = true;
	 }else{
	 	form.indicadorAtualizar.checked = false;
	}
}
	
function setaFocus(){
	var form = document.FiltrarOperacaoActionForm;
	form.idOperacao.focus();
}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="verificarChecado('${sessionScope.indicadorAtualizar}'); setaFocus();">

<html:form action="/filtrarOperacaoAction"
	name="FiltrarOperacaoActionForm"
	type="gcom.gui.seguranca.acesso.FiltrarOperacaoActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="140" valign="top" class="leftcoltext">
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
					<td class="parabg">Filtrar Operação</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td width="80%">Para filtrar a(s) operação(ões) no sistema,
							informe os dados abaixo:</td>
							<td align="right"><input type="checkbox"
								name="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
							<p>&nbsp;</p>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td><strong>Código da Operação:</strong></td>
					<td><html:text property="idOperacao" size="9" maxlength="9"
						tabindex="1" /></td>
				</tr>

				<tr>
					<td><strong>Descrição da Operação:</strong></td>
					<td><html:text property="descricaoOperacao" size="60"
						maxlength="60" tabindex="2" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>

				<tr>
					<td><strong>Tipo da Operação:</strong></td>
					<td><html:select property="idTipoOperacao"
						onchange="javascript:setarFoco('idFuncionalidade')">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoOperacaoTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Funcionalidade:</strong></td>
					<td colspan="3"><html:text property="idFuncionalidade" size="9"
						maxlength="9" tabindex="2"
						onkeyup="javascript:limparDescricaoFuncionalidade();"
						onkeypress="return validaEnter(event, 'exibirFiltrarOperacaoAction.do', 'idFuncionalidade');" />

					<a
						href="javascript:chamarPopup('exibirPesquisarFuncionalidadeAction.do', '', null, null, 400, 800, '','');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Funcionalidade" /> </a> 
						
						<!-- <img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Tabela"
						onclick="javascript:abrirPopup('exibirPesquisarFuncionalidadeAction.do', 400, 800);"
						style="cursor: hand;" />
						-->
						
						 <logic:present name="funcionalidadeNaoEncontrada">
						<logic:equal name="funcionalidadeNaoEncontrada" value="exception">
							<html:text property="descricaoFuncionalidade" size="45"
								maxlength="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="funcionalidadeNaoEncontrada"
							value="exception">
							<html:text property="descricaoFuncionalidade" size="45"
								maxlength="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="funcionalidadeNaoEncontrada">
						<logic:empty name="FiltrarOperacaoActionForm"
							property="idFuncionalidade">
							<html:text property="descricaoFuncionalidade" value="" size="45"
								maxlength="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarOperacaoActionForm"
							property="idFuncionalidade">
							<html:text property="descricaoFuncionalidade" size="45"
								maxlength="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparFuncionalidade();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>

					<!--  <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar"
						onclick="javascript:limparFuncionalidade();" />--></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><input name="button" type="button" class="bottonRightCol"
						value="Limpar"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarOperacaoAction.do?desfazer=S"/>'">&nbsp;
					</td>

					<td align="right"><input name="botaoFiltrar" type="button"
						class="bottonRightCol" align="right" value="Filtrar"
						onclick="validarForm(document.forms[0]);" tabindex="19"
						style="width: 70px;"></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
