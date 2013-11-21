<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.seguranca.transacao.Tabela"%>
<%@ page import="gcom.seguranca.transacao.TabelaColuna"%>
<%@ page import="gcom.seguranca.acesso.OperacaoTabela"%>
<%@ page import="gcom.seguranca.acesso.Operacao"%>


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
function validarForm(form){
	for (i=0; i < form.elements.length; i++) {
	    if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
    		if(form.elements[i].value == ''){	
    			alert('Descrição não pode ser vazia');
				return;
    		}
		}
	}
	document.forms[0].action = "/gsan/atualizarTabelaColunaAction.do";
	document.forms[0].submit();	
}

//-->
</SCRIPT>

</head>

<logic:present name="atualizouTabelaColuna">
	<body leftmargin="5" topmargin="5" onload="javascript:alert('Tabela / Coluna alteradas com sucesso.');">
</logic:present>
<logic:notPresent name="atualizouTabelaColuna">
	<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
 </logic:notPresent>


<html:form action="/exibirManterContaAction" method="post">

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
					<td class="parabg">Manter Tabelas e Colunas</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td>Para manter a(s) tabela(s) e coluna(s), informe os dados abaixo:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>					
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td>

					<table width="100%" bgcolor="#99CCFF">
					
						<logic:iterate name="colecaoTabela" id="tabela" type="Tabela">
							<tr bgcolor="#79bbfd">
							   <td colspan="2">
							     <Strong>Descrição da Tabela
							     (<bean:write name="tabela" property="nomeTabela" />): </Strong>
							     <html:text name="tabela" property="descricao" size="50" maxlength="50" tabindex="1" style="color: #000000"
									onkeyup="limpar(10);"/>						     
							   </td>
							</tr>
							<tr bgcolor="#99CCFF">
								<td align="center" width="10%"><strong>Nome do campo</strong></td>
								<td width="60%">
								<div align="center"><strong>Descrição do campo</strong></div></td>
							</tr>
							<bean:define name="tabela" property="tabelaColunas" id="colecaoColunas" /> 
							<logic:iterate name="colecaoColunas" id="coluna" type="TabelaColuna">
							<%
								
								String nomesColunasSelecionadas = (String) session.getAttribute("nomesColunasSelecionadas");
							
								if (nomesColunasSelecionadas.indexOf("$" + coluna.getColuna() + "$") != -1){
							
							%>						
								<tr bgcolor="#cbe5fe">
									<td align="center" width="10%"><bean:write name="coluna" property="coluna" /></td>
									<td width="60%">
									<div align="center">
									<input type=text name="descricaoColuna<%=((TabelaColuna) coluna).getId()%>"
										id="descricaoColuna<%=((TabelaColuna) coluna).getId()%>"
								 		value="<%=((TabelaColuna) coluna).getDescricaoColuna()%>" size="30" 
										maxlength="30" /></div></td>
								</tr>								
							<%
								}							
							%>								
							</logic:iterate>
						</logic:iterate>
						<tr>
							<td colspan="2" height="5">
								<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
								<input type="button" name="ButtonVoltar" class="bottonRightCol" value="Voltar"
								onClick="javascript:document.forms[0].target='';window.location.href='/gsan/exibirFiltrarTabelaColunaAction.do'">
								
								 <gsan:controleAcessoBotao	name="Button" value="Atualizar"
								onclick="validarForm(document.forms[0]);" url="atualizarTabelaColunaAction.do" style="width: 140px"/>
								
							</td>
						</tr>						
						<tr>
							<td colspan="2" height="5"><strong>
							Manter ordem de exibição desta tabela nas operações:</strong></td>
						</tr>

						<logic:iterate name="colecaoOperTabela" id="operTabela" type="OperacaoTabela">
						<tr bgcolor="#cbe5fe">
							<td colspan="2" height="5">
								<bean:define name="operTabela" property="operacao" id="operacao" /> 							
								<a href="/gsan/exibirManterOperacaoOrdemExibicaoAction.do?idOperacao=<%=((Operacao) operacao).getId()%>">								
								<bean:write name="operacao" property="descricao" /> </a>
								&nbsp;
								<logic:present name="operacao" property="argumentoPesquisa">*</logic:present>
								
							</td>		
						<tr>

						</logic:iterate>
						
					</table>
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
			