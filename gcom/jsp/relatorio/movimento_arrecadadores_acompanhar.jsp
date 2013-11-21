<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AcompanharMovimentoArrecadadoresActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

	function limparArrecadador() {
		var form = document.forms[0];
	
		form.idArrecadador.value = "";
		form.nomeArrecadador.value = "";
	}
	
	function limparArrecadadorTecla() {
		var form = document.forms[0];

		form.nomeArrecadador.value = "";
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];

      	if (tipoConsulta == 'arrecadador') {
        	form.idArrecadador.value = codigoRegistro;
        	form.nomeArrecadador.value = descricaoRegistro;
        	form.nomeArrecadador.style.color = "#000000";
      	}
      	
     }

	function validarForm(form){
				
		if(testarCampoValorZero(document.forms[0].mesAnoReferencia, 'Mês/Ano Referência')
		&& testarCampoValorZero(document.forms[0].idArrecadador, 'Arrecadador')) { 		
			if(validateAcompanharMovimentoArrecadadoresActionForm(form)){			
   				form.submit();			
			}
		}	
	}

-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/gerarRelatorioAcompanhamentoMovimentoArrecadadoresAction"
	name="AcompanharMovimentoArrecadadoresActionForm"
	type="gcom.gui.relatorio.AcompanharMovimentoArrecadadoresActionForm"
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Acompanhamento do Movimento dos Arrecadadores</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para acompanhar o
					movimento dos arrecadadores, informe os dados
					abaixo:</td>
				</tr>
				<tr>
					<td><strong>Mês/Ano Referência:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="mesAnoReferencia" size="7" maxlength="7" onkeyup="mascaraAnoMes(this, event);" /></td>
				</tr>
				<tr>
					<td width="130"><strong>Arrecadador:</strong></td>
					<td colspan="2"><html:text maxlength="4" property="idArrecadador"
						tabindex="1" size="4" onkeyup="javascript:limparArrecadadorTecla();"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirAcompanharMovimentoArrecadadoresAction.do', 'idArrecadador', 'Arrecadador');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarArrecadadorAction.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Arrecadador" /></a> <logic:present
						name="idArrecadadorNaoEncontrado" scope="request">
						<html:text maxlength="40" property="nomeArrecadador" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" />
					</logic:present> <logic:notPresent name="idArrecadadorNaoEncontrado"
						scope="request">
						<html:text maxlength="30" property="nomeArrecadador" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" />
					</logic:notPresent> <a href="javascript:limparArrecadador();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Forma de Arrecadação:</strong></td>
					<td><html:select property="idFormaArrecadacao" tabindex="3" style="width:300">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoArrecadacaoForma"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
							<td><strong> <font color="#FF0000"></font> </strong></td>
							<td colspan="3" align="right">
							<div align="left"><strong> <font color="#FF0000">*</font> </strong>
							Campos obrigat&oacute;rios</div>
							</td>
				</tr>
				<tr>
					<td><input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirAcompanharMovimentoArrecadadoresAction.do?menu=sim';">
					<td colspan="2" align="right">
 					  <gsan:controleAcessoBotao name="Button" value="Gerar" onclick="javascript:validarForm(document.forms[0]);" url="gerarRelatorioAcompanhamentoMovimentoArrecadadoresAction.do" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
