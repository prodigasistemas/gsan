<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarMovimentoArrecadadorActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function validarForm(form){
		var formRed = "/gsan/pesquisarMovimentoArrecadadorAction.do";
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
		if(validatePesquisarMovimentoArrecadadorActionForm(form)){
			if (form.dataMovimentoInicio.value != "" && form.dataMovimentoFim.value != '') 
		 	{
				if (comparaData(form.dataMovimentoInicio.value, ">", form.dataMovimentoFim.value ))
				{
					alert('Data Final do Período de Geração do Movimento é anterior à Data Inicial do Período.');
				}
				else if(form.dataMovimentoInicio.value != "" && comparaData(form.dataMovimentoInicio.value, ">", DATA_ATUAL )){
					alert("Data Inicial do Período de Geração do Movimento posterior à Data corrente " + DATA_ATUAL + ".");
				}
				else if(form.dataMovimentoFim.value != "" && comparaData(form.dataMovimentoFim.value, ">", DATA_ATUAL )){
					alert("Data Final do Período de Geração do Movimento posterior à Data corrente " + DATA_ATUAL + ".");
				}else{
					redirecionarSubmit(formRed);
				}
			}
			else if(form.dataMovimentoInicio.value != "" && comparaData(form.dataMovimentoInicio.value, ">", DATA_ATUAL )){
				alert("Data Inicial do Período de Geração do Movimento posterior à Data corrente " + DATA_ATUAL + ".");
			}
			else if(form.dataMovimentoFim.value != "" && comparaData(form.dataMovimentoFim.value, ">", DATA_ATUAL )){
				alert("Data Final do Período de Geração do Movimento posterior à Data corrente " + DATA_ATUAL + ".");
			}
			else{
				redirecionarSubmit(formRed);
			}
		}
	}
	function limparArrecadador() {
		var form = document.PesquisarMovimentoArrecadadorActionForm;
		form.idBanco.value = "";
		form.arrecadadorNome.value = "";
	}
	function duplicaData(form) {
		form.dataMovimentoFim.value = form.dataMovimentoInicio.value;
	}
	function limparForm(){
		var form = document.forms[0];
		
		limparArrecadador();
		form.tipoRemessa[0].checked = false;
		form.tipoRemessa[1].checked = false;
		form.tipoRemessa[2].checked = true;	
		form.identificacaoServico[0].checked = false;
		form.identificacaoServico[1].checked = false;	
		form.identificacaoServico[2].checked = true;	
		form.numeroSequencialArquivo.value="";
		form.dataMovimentoInicio.value="";
		form.dataMovimentoFim.value="";
		
		form.idBanco.focus();
	
	}
	
</script>
</head>
<body onload="resizePageSemLink(665, 450);javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/pesquisarMovimentoArrecadadorAction"
	name="PesquisarMovimentoArrecadadorActionForm"
	onsubmit="return validatePesquisarMovimentoArrecadadorActionForm(this);"
	type="gcom.gui.arrecadacao.PesquisarMovimentoArrecadadorActionForm"
	method="post">

	<table width="630" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="620" valign="top" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img src="imagens/parahead_left.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
						border="0" /></td>
					<td class="parabg">Pesquisar Movimento do Arrecadador</td>
					<td width="11"><img src="imagens/parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>

				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar um movimento:</td>
				</tr>
				<tr>
					<td width="39%"><strong>Arrecadador:<strong><font color="#FF0000"></font></strong></strong></td>

					<td width="61%" colspan="3"> <html:text property="idBanco"
						size="3" maxlength="3"
						onkeyup="document.forms[0].arrecadadorNome.value='';"
						onkeypress="javascript:return validaEnter(event, 'exibirPesquisarMovimentoArrecadadorAction.do?objetoConsulta=1', 'idBanco');"/>
						<a
							href="javascript:abrirPopup('exibirPesquisarArrecadadorAction.do?caminhoRetornoTelaPesquisaArrecadador=exibirPesquisarMovimentoArrecadadorAction');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Arrecadador" /></a>
						<logic:present name="idArrecadadorNaoEncontrado">
							<logic:equal name="idArrecadadorNaoEncontrado" value="exception">
								<html:text property="arrecadadorNome" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:equal>
							<logic:notEqual name="idArrecadadorNaoEncontrado"
								value="exception">
<html:text property="arrecadadorNome" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"/>							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="idArrecadadorNaoEncontrado">
							<html:text property="arrecadadorNome" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"/>

						</logic:notPresent>
						<a
						href="javascript:limparArrecadador();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
						</td>
				</tr>
				<tr>
					<td height="0"><strong>Remessa:</strong></td>
					<td colspan="3"><span class="style1"> 
						<html:radio	property="tipoRemessa" value="1" /> <strong>Envio</strong> 
						<html:radio	property="tipoRemessa" value="2" /><strong> Retorno</strong>
						<html:radio	property="tipoRemessa" value="" /><strong> Todos</strong></span>
					</td>
				</tr>
				<tr>
				<tr>
					<td height="0"><strong>Identifica&ccedil;&atilde;o do
					Servi&ccedil;o:</strong></td>
					<td colspan="3"><strong> <html:radio
						property="identificacaoServico" value="CODIGO DE BARRAS" /> Código
					de Barras <html:radio property="identificacaoServico"
						value="DEBITO AUTOMATICO" /> Débito Automático
						<html:radio property="identificacaoServico"
						value="" /> Todos
					<%-- <input type="radio"	name="identificacaoServico" value="" checked /> Todos--%>
						</strong></td>
				</tr>
				<tr>
					<td height="0"><strong>N&uacute;mero Sequencial do Arquivo (NSA):</strong></td>
					<td colspan="3"><strong> <html:text
						property="numeroSequencialArquivo" size="9" maxlength="9" /> </strong>
					</td>

				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Gera&ccedil;&atilde;o do Movimento:</strong></td>
					<td colspan="3"><strong> <html:text property="dataMovimentoInicio"
						size="10" maxlength="10" onkeyup="mascaraData(this, event); duplicaData(document.forms[0]);"
						onfocus="duplicaData(document.forms[0]);" /> <a
						href="javascript:abrirCalendario('PesquisarMovimentoArrecadadorActionForm', 'dataMovimentoInicio')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <INPUT TYPE="hidden" ID="DATA_ATUAL"
						value="${requestScope.dataAtual}" /><html:text property="dataMovimentoFim" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('PesquisarMovimentoArrecadadorActionForm', 'dataMovimentoFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>

				</tr>
				
				
				<tr>
			      	<td HEIGHT="30" width="150"><strong>Ítens em Ocorrência:</td>
			        <td>
						<html:select property="movimentoItemOcorrencia" style="width: 200px;" tabindex="14">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:option value="<%=""+ConstantesSistema.COM_ITENS%>">COM ÍTENS EM OCORRÊNCIA</html:option>
							<html:option value="<%=""+ConstantesSistema.SEM_ITENS%>">SEM ÍTENS EM OCORRÊNCIA</html:option>
						</html:select>
					</td>
			    </tr>
				<tr>
			      	<td HEIGHT="30" width="150"><strong>Ítens não Aceitos:</td>
			        <td>
						<html:select property="movimentoItemAceito" style="width: 200px;" tabindex="15">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:option value="<%=""+ConstantesSistema.COM_ITENS%>">COM ÍTENS NÃO ACEITOS</html:option>
							<html:option value="<%=""+ConstantesSistema.SEM_ITENS%>">SEM ÍTENS NÃO ACEITOS</html:option>
						</html:select>
					</td>
			    </tr>			
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				
				
				<tr>
					<td colspan="3">
			          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="document.forms[0].reset();limparForm();"/>
							&nbsp;&nbsp;
			          	<logic:present name="caminhoRetornoTelaPesquisaMovimentoArrecadador">
			          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaMovimentoArrecadador}.do')"/>
			          	</logic:present>
			         </td>
			         <td align="right">
				         <input name="Button" type="button"
							class="bottonRightCol" value="Pesquisar"
							onClick="javascript:validarForm(document.forms[0]);">
					 </td>
				</tr>
				
			</table>

			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
