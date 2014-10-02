<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	formName="FiltrarInformarLeituraFiscalizacaoActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){

		if(testarCampoValorZero(document.FiltrarInformarLeituraFiscalizacaoActionForm.matricula, 'Matrícula') && 
			testarCampoValorZero(document.FiltrarInformarLeituraFiscalizacaoActionForm.medicaoTipo, 'Tipo de Medição') && 
			testarCampoValorZero(document.FiltrarInformarLeituraFiscalizacaoActionForm.mesAnoReferencia, 'Mês/Ano de Referência')) {

			if(validateFiltrarInformarLeituraFiscalizacaoActionForm(form)){
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];
   
    	if (tipoConsulta == 'imovel') {
	    	form.matricula.value = codigoRegistro;
	      	form.action='exibirFiltrarInformarLeituraFiscalizacaoAction.do';
	      	form.submit();
	    }
  	}

	function limparIncricaoImovel(){
		var form = document.forms[0];
		form.inscricaoImovel.value = "";
	}
	

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form action="/filtrarInformarLeituraFiscalizacaoAction.do"
	name="FiltrarInformarLeituraFiscalizacaoActionForm"
	type="gcom.gui.micromedicao.leitura.FiltrarInformarLeituraFiscalizacaoActionForm"
	method="post">

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
			<td width="600" valign="top" class="centercoltext">
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
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Informar Leitura de Fiscalização</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="2">Para pesquisar o imovel e informar a
					leitura de fiscalização, informe os dados abaixo:.</td>
				</tr>
				<tr>
					<td bordercolor="#000000" width="15%"><strong>Matrícula do
					Im&oacute;vel:<font color="#FF0000">*</font></strong></td>
					<td width="75%" colspan="3"><html:text property="matricula"
						maxlength="9" size="9"
						onkeyup="javascript:limparIncricaoImovel();"
						onkeypress="validaEnterComMensagem(event, 'exibirFiltrarInformarLeituraFiscalizacaoAction.do','matricula','imovel');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" /></a>
					<logic:present name="matriculaNaoEncontrado" scope="request">
						<html:text property="inscricaoImovel" size="20" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="matriculaNaoEncontrado"
						scope="request">
						<html:text property="inscricaoImovel" size="20" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparIncricaoImovel();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Tipo de Medição:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="medicaoTipo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoMedicaoTipo"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<tr>
					<td><strong>Mês e Ano de Referência:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="mesAnoReferencia" size="7"
						maxlength="7" onkeyup="javascript:mascaraAnoMes(this,event);" />
					mm/aaaa</td>
				</tr>

				<tr colspan="1" align="center">
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="center">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Pesquisar"
						onClick="javascript:validarForm(document.forms[0]);"></td>
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

