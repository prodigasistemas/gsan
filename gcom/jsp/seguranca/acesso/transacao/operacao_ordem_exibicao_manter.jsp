<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.seguranca.acesso.OperacaoOrdemExibicao"%>
<%@ page import="gcom.seguranca.transacao.TabelaColuna"%>


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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
function submeter(){
	document.forms[0].action = "/gsan/atualizarOperacaoOrdemExibicaoAction.do?ordenar=Sim";
	document.forms[0].submit();
}

function validarForm(form){
	for (i=0; i < form.elements.length; i++) {
	    if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
    		if(!testarCampoValorInteiroComZero(form.elements[i],'Ordem')){	
				return;
    		}
		}
	}
	document.forms[0].action = "/gsan/atualizarOperacaoOrdemExibicaoAction.do";
	document.forms[0].submit();	
}
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirManterOperacaoOrdemExibicaoAction" method="post">

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
					<td class="parabg">Manter Ordem de Exibição das Colunas nas Operações</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td>Para manter a ordem das colunas, informe os dados abaixo:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>					
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td>

					<table width="100%" bgcolor="#99CCFF">
					
						<tr bgcolor="#79bbfd">
						   <td colspan="3">
						     <Strong>Operação 
						     (<bean:write name="operacao" property="descricao" />): </Strong>
						   </td>
						</tr>
						<tr bgcolor="#99CCFF">
							<td align="center" width="30%"><strong>Tabela</strong></td>
							<td align="center" width="40%"><strong>Campo</strong></td>
							<td width="30%">
							<div align="center"><strong><a href="javascript:submeter()">Ordem</a></strong></div></td>
						</tr>

						<logic:iterate name="colecaoOrdens" id="ordem" type="OperacaoOrdemExibicao">
							<bean:define name="ordem" property="tabelaColuna" id="coluna" /> 
							<bean:define name="coluna" property="tabela" id="tabela" /> 						
							<tr bgcolor="#cbe5fe">
								<td align="center" width="30%">
									<bean:write name="tabela" property="descricao" /></td>
								<td align="center" width="40%">
									<bean:write name="coluna" property="descricaoColuna" /></td>
								<td width="30%">
								<div align="center"><input type=text name="ordem<%=((TabelaColuna) coluna).getId()%>"
								 id="ordem<%=((TabelaColuna) coluna).getId()%>"
								 value="<%=((OperacaoOrdemExibicao) ordem).getNumeroOrdem()%>" size="5" 
								maxlength="5" tabindex="1" /></div></td>
							</tr>								
						</logic:iterate>
												
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" height="5">
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
						<input type="button" name="ButtonVoltar" class="bottonRightCol" value="Voltar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/exibirManterTabelaColunaAction.do'">
						
						 <gsan:controleAcessoBotao	name="Button" value="Atualizar"
						onclick="validarForm(document.forms[0]);" url="atualizarOperacaoOrdemExibicaoAction.do" style="width: 140px"/>
						
						</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>


</body>
</html:form>
</html:html>
			