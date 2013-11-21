<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarResolucaoDiretoriaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

	function limparForm(form) {
	
		form.numero.value = "";
		form.assunto.value = "";
		form.dataInicio.value = "";
		form.dataFim.value = "";
		form.indicadorParcelamentoUnico.value = "3";
		form.indicadorUtilizacaoLivre.value = "3";
		form.indicadorDescontoSancoes.value = "3";
	
	}

	function validarForm(form){
				
		if(testarCampoValorZero(document.FiltrarResolucaoDiretoriaActionForm.numero, 'Número da RD')
		&& testarCampoValorZero(document.FiltrarResolucaoDiretoriaActionForm.assunto, 'Assunto da RD')) { 		
			if(validateFiltrarResolucaoDiretoriaActionForm(form)){			
   				form.submit();			
			}
		}	
	}
	
	function habilitacaoIdParcelasEmAtraso(indicadorParcelasEmAtraso){

		var form = document.forms[0];
			if (indicadorParcelasEmAtraso[1].checked ||
			indicadorParcelasEmAtraso[2].checked){
				form.idParcelasEmAtraso.disabled = true;
				form.idParcelasEmAtraso.value = ""; 
			}else{
				form.idParcelasEmAtraso.disabled = false;
			}
   	}
   	
   	function habilitacaoIdParcelamentoEmAndamento(indicadorParcelamentoEmAndamento){

		var form = document.forms[0];
			if (indicadorParcelamentoEmAndamento[1].checked ||
			indicadorParcelamentoEmAndamento[2].checked){
				form.idParcelamentoEmAndamento.disabled = true;
				form.idParcelamentoEmAndamento.value = ""; 
			}else{
				form.idParcelamentoEmAndamento.disabled = false;
			}
   	}

-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/filtrarResolucaoDiretoriaAction"
	name="FiltrarResolucaoDiretoriaActionForm"
	type="gcom.gui.cobranca.FiltrarResolucaoDiretoriaActionForm"
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
					<td class="parabg">Filtrar Resolu&ccedil;&atilde;o de Diretoria</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para manter a(s)
					resolu&ccedil;&atilde;o(&otilde;es) de diretoria, informe os dados
					abaixo:</td>
					<td><html:checkbox property="atualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>
				<tr>
					<td><strong>N&uacute;mero RD:</strong></td>
					<td><html:text property="numero" size="15" maxlength="15" /></td>
				</tr>
				<tr>
					<td><strong>Assunto RD:</strong></td>
					<td><html:text property="assunto" size="50" maxlength="50" /></td>
				</tr>
				<tr>
					<td><strong>Data In&iacute;cio Vig&ecirc;ncia RD<font
						color="#000000">:</font></strong></td>
					<td height="25"><html:text property="dataInicio" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('FiltrarResolucaoDiretoriaActionForm', 'dataInicio')" />
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td height="25"><strong>Data T&eacute;rmino Vig&ecirc;ncia RD:</strong></td>
					<td height="25"><html:text property="dataFim" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('FiltrarResolucaoDiretoriaActionForm', 'dataFim')" />
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td><strong>Parcelamento &Uacute;nico? </strong></td>
					<td><html:radio property="indicadorParcelamentoUnico" tabindex="2" value="<%=ConstantesSistema.SIM.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorParcelamentoUnico" tabindex="3" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>N&atilde;o</strong>
					<html:radio property="indicadorParcelamentoUnico" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Utiliza&ccedil;&atilde;o Livre? </strong></td>
					<td><html:radio property="indicadorUtilizacaoLivre" tabindex="5" value="<%=ConstantesSistema.SIM.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorUtilizacaoLivre" tabindex="6" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>N&atilde;o</strong>
					<html:radio property="indicadorUtilizacaoLivre" tabindex="7" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Descontos e San&ccedil;&otilde;es? </strong></td>
					<td><html:radio property="indicadorDescontoSancoes" tabindex="8" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorDescontoSancoes" tabindex="9" value="2" /><strong>N&atilde;o</strong>
					<html:radio property="indicadorDescontoSancoes" tabindex="10" value="3" /><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Negociação só a Vista: </strong></td>
					<td><html:radio property="indicadorNegociacaoSoAVista" tabindex="11" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorNegociacaoSoAVista" tabindex="12" value="2" /><strong>N&atilde;o</strong>
					<html:radio property="indicadorNegociacaoSoAVista" tabindex="13" value="3" /><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Desconto só em Conta para Pagamento a Vista: </strong></td>
					<td><html:radio property="indicadorDescontoSoEmContaAVista" tabindex="14" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorDescontoSoEmContaAVista" tabindex="15" value="2" /><strong>N&atilde;o</strong>
					<html:radio property="indicadorDescontoSoEmContaAVista" tabindex="16" value="3" /><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><strong>Indicador de Parcelamento para Loja Virtual: </strong></td>
					<td><html:radio property="indicadorParcelamentoLojaVirtual" tabindex="17" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorParcelamentoLojaVirtual" tabindex="18" value="2" /><strong>N&atilde;o</strong>
					<html:radio property="indicadorParcelamentoLojaVirtual" tabindex="19" value="3" /><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><strong>Indicador de Parcelas em Atraso: </strong></td>
					<td><html:radio property="indicadorParcelasEmAtraso" tabindex="20" value="<%=ConstantesSistema.SIM.toString()%>" onclick="habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);"/><strong>Sim</strong>
					<html:radio property="indicadorParcelasEmAtraso" tabindex="21" value="<%=ConstantesSistema.NAO.toString()%>" onclick="habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);"/><strong>N&atilde;o</strong>
					<html:radio property="indicadorParcelasEmAtraso" tabindex="22" value="<%=ConstantesSistema.TODOS.toString()%>" onclick="habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);"/><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="218"><strong>RD Parcelas em Atraso:</strong></td>
					<td width="393">
						<html:text property="idParcelasEmAtraso" size="10" tabindex="23" maxlength="10" onkeyup="verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Parcelamento em Andamento:</strong></td>
					<td><html:radio property="indicadorParcelamentoEmAndamento" tabindex="24" value="<%=ConstantesSistema.SIM.toString()%>" onclick="habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);"/><strong>Sim</strong>
					<html:radio property="indicadorParcelamentoEmAndamento" tabindex="25" value="<%=ConstantesSistema.NAO.toString()%>" onclick="habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);"/><strong>N&atilde;o</strong>
					<html:radio property="indicadorParcelamentoEmAndamento" tabindex="26" value="<%=ConstantesSistema.TODOS.toString()%>" onclick="habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);"/><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="218"><strong>RD Parcelamento em Andamento:</strong></td>
					<td width="393">
						<html:text property="idParcelamentoEmAndamento" size="10" tabindex="27" maxlength="10" onkeyup="verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td><input type="button" name="ButtonReset" class="bottonRightCol"
						value="Limpar" onclick="javascript:limparForm(document.forms[0]);">
						
					<td colspan="2" align="right">
 					  <gsan:controleAcessoBotao name="Button" value="Filtrar" onclick="javascript:validarForm(document.forms[0]);" url="filtrarResolucaoDiretoriaAction.do"/>
					  <%-- <input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
	<script language="JavaScript">
	<!--
	habilitacaoIdParcelasEmAtraso(document.forms[0].indicadorParcelasEmAtraso);
	habilitacaoIdParcelamentoEmAndamento(document.forms[0].indicadorParcelamentoEmAndamento);
	//-->
	</script>
</body>
</html:html>
