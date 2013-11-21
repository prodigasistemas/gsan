<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.util.Util, gcom.cobranca.CobrancaCriterio"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript">
<!-- Begin -->
	
</script>

</head>

<body leftmargin="0" topmargin="0">

<form method="post">

<table width="764" border="0" cellpadding="0" cellspacing="5">
	<tr>
		<td width="754" valign="top" class="centercoltext">
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
				<td class="parabg">Consultar<strong> </strong><font size="-1">Crit&eacute;rio
				da A&ccedil;&atilde;o de Cobran&ccedil;a</font></td>
				<td width="11"><img src="imagens/parahead_right.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
					border="0" /></td>
			</tr>
		</table>
		<table width="100%" border="0">
			<logic:iterate name="colecaoCobrancaCriterio" id="cobrancaCriterio"
				type="CobrancaCriterio">
				<tr>
					<td colspan="4">
					<p>&nbsp;</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Descri&ccedil;&atilde;o do
					Crit&eacute;rio:</strong></td>
					<td colspan="3" class="style1"><logic:present
						name="cobrancaCriterio" property="descricaoCobrancaCriterio">
						<input name="inscricaoImovel2" type="text" disabled
							style="background-color:#EFEFEF; border:0" size="30"
							maxlength="30"
							value="<bean:write name="cobrancaCriterio"
						property="descricaoCobrancaCriterio" />">
					</logic:present> <logic:notPresent name="cobrancaCriterio"
						property="descricaoCobrancaCriterio">
						<input name="inscricaoImovel2" type="text" disabled
							style="background-color:#EFEFEF; border:0" size="30"
							maxlength="30" value="">
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Data de In&iacute;cio de Vig&ecirc;ncia:</strong></td>
					<td colspan="3"><span class="style1"> <logic:present
						name="cobrancaCriterio" property="dataInicioVigencia">
						<input name="inscricaoImovel2" type="text" disabled
							style="background-color:#EFEFEF; border:0" size="10"
							maxlength="10"
							value="<bean:write name="cobrancaCriterio" format="dd/MM/yyyy"
						property="dataInicioVigencia" />">
					</logic:present> <logic:notPresent name="cobrancaCriterio"
						property="dataInicioVigencia">
						<input name="inscricaoImovel2" type="text" disabled
							style="background-color:#EFEFEF; border:0" size="10"
							maxlength="10" value="">
					</logic:notPresent> </span></td>
				</tr>
				<tr>
					<td><strong>N&uacute;mero de Anos para Considerar Conta Antiga:</strong></td>
					<td colspan="3"><span class="style1"> <logic:present
						name="cobrancaCriterio" property="numeroContaAntiga">
						<input name="inscricaoImovel2" type="text" disabled
							style="background-color:#EFEFEF; border:0" size="3" maxlength="3"
							value="<bean:write name="cobrancaCriterio"
						property="numeroContaAntiga" />">
					</logic:present> <logic:notPresent name="cobrancaCriterio"
						property="numeroContaAntiga">
						<input name="inscricaoImovel2" type="text" disabled
							style="background-color:#EFEFEF; border:0" size="3" maxlength="3"
							value="">
					</logic:notPresent> </span></td>
				</tr>
				<tr>
					<td colspan="4" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Indicadores de Sele&ccedil;&atilde;o</strong></td>
					<td colspan="3" class="style1">&nbsp;</td>
				</tr>
				<tr>
					<td class="style1"><strong>Im&oacute;vel com Situa&ccedil;&atilde;o
					Especial de Cobran&ccedil;a:</strong></td>
					<td colspan="3" class="style1">
					<logic:equal name="cobrancaCriterio"
						property="indicadorEmissaoImovelParalisacao" value="1">
						<input type="radio"
							name="indicadorEmissaoImovel" value="rádio" enabled checked>
							<strong> Sim 
						<input type="radio"
							name="indicadorEmissaoImovel" value="rádio" disabled>
						N&atilde;o</strong>					
					</logic:equal>
					<logic:notEqual name="cobrancaCriterio"
						property="indicadorEmissaoImovelParalisacao" value="1"> 
						<input type="radio"
							name="indicadorEmissaoImovel" value="rádio" disabled>
						<strong> Sim 
						<input type="radio"
						name="indicadorEmissaoImovel" value="rádio" enabled checked>
						N&atilde;o</strong>					
					</logic:notEqual>
						
				</td>
				</tr>
				<tr>
					<td class="style1"><strong>Im&oacute;vel com Situa&ccedil;&atilde;o
					Cobran&ccedil;a:</strong></td>
					<td colspan="3" class="style1">
					<logic:equal name="cobrancaCriterio"
						property="indicadorEmissaoImovelSituacaoCobranca" value="1">
						<input type="radio"
							name="indicador" value="rádio" enabled checked>
						<strong>Sim 
						<input type="radio"
							name="indicador" value="rádio" disabled>
						N&atilde;o</strong>
					</logic:equal>
					<logic:notEqual name="cobrancaCriterio"
						property="indicadorEmissaoImovelSituacaoCobranca" value="1">
						<input type="radio"
							name="indicador" value="rádio" disabled>
						<strong>Sim 
						<input type="radio"
							name="indicador" value="rádio" enabled checked>
						N&atilde;o</strong>
					</logic:notEqual>					
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Contas em Revis&atilde;o:</strong></td>
					<td colspan="3" class="style1">
					<logic:equal name="cobrancaCriterio"
						property="indicadorEmissaoContaRevisao" value="1">
						<input type="radio"
							name="indicadorEmissao" value="rádio" enabled checked>
						<strong>Sim <input type="radio"
							name="indicadorEmissao" value="rádio" disabled>
						N&atilde;o</strong>
					</logic:equal>
					<logic:notEqual name="cobrancaCriterio"
						property="indicadorEmissaoContaRevisao" value="1">
						<input type="radio"
							name="indicadorEmissao" value="rádio" disabled>
						<strong>Sim <input type="radio"
							name="indicadorEmissao" value="rádio" enabled checked>
						N&atilde;o</strong>
					</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Im&oacute;vel com D&eacute;bito
					s&oacute; na Conta do M&ecirc;s:</strong></td>
					<td colspan="3" class="style1">
					<logic:equal name="cobrancaCriterio"
						property="indicadorEmissaoDebitoContaMes" value="1">
						<input type="radio"
							name="indicadorEmissaoDebito" value="rádio" enabled checked>
						<strong>Sim <input type="radio"
							name="indicadorEmissaoDebito" value="rádio" disabled>
						N&atilde;o</strong>
					</logic:equal>													
					<logic:notEqual name="cobrancaCriterio"
						property="indicadorEmissaoDebitoContaMes" value="1">
						<input type="radio"
							name="indicadorEmissaoDebito" value="rádio" disabled>
						<strong>Sim <input type="radio"
							name="indicadorEmissaoDebito" value="rádio" enabled checked>
						N&atilde;o</strong>
					</logic:notEqual>													
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Inquilino com D&eacute;bito s&oacute; na
					Conta do M&ecirc;s:</strong></td>
					<td colspan="3" class="style1">
					<logic:equal name="cobrancaCriterio"
						property="indicadorEmissaoInquilinoDebitoContaMes" value="1">
							<input type="radio"
								name="indicadorEmissaoInquilino" value="rádio"  enabled checked>
							<strong>Sim <input type="radio"
								name="indicadorEmissaoInquilino" value="rádio" disabled>
							N&atilde;o</strong>
					</logic:equal>					
					<logic:notEqual name="cobrancaCriterio"
						property="indicadorEmissaoInquilinoDebitoContaMes" value="1">
							<input type="radio"
								name="indicadorEmissaoInquilino" value="rádio" disabled>
							<strong>Sim <input type="radio"
								name="indicadorEmissaoInquilino" value="rádio"  enabled checked>
							N&atilde;o</strong>
					</logic:notEqual>					
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Im&oacute;vel com D&eacute;bito
					s&oacute; de Contas Antigas:</strong></td>
					<td colspan="3" class="style1">
					<logic:equal name="cobrancaCriterio"
						property="indicadorEmissaoDebitoContaAntiga" value="1">
						<input type="radio"
							name="indicadorEmissaoDebitoConta" value="rádio" enabled checked>
						<strong>Sim <input type="radio"
							name="indicadorEmissaoDebitoConta" value="rádio" disabled>
						N&atilde;o</strong>
					</logic:equal>
					<logic:notEqual name="cobrancaCriterio"
						property="indicadorEmissaoDebitoContaAntiga" value="1">
						<input type="radio"
							name="indicadorEmissaoDebitoConta" value="rádio" disabled>
						<strong>Sim <input type="radio"
							name="indicadorEmissaoDebitoConta" value="rádio" enabled checked>
						N&atilde;o</strong>
					</logic:notEqual>
					
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
			</logic:iterate>
			<tr>
				<td colspan="4">
				<table width="100%" border="0" bordercolor="#000000">
					<tr bordercolor="#90c7fc">
						<td colspan="9" bgcolor="#90c7fc"><strong>Linhas do
						Crit&eacute;rio </strong></td>
					</tr>
					<tr bordercolor="#000000">
						<td width="8%" bgcolor="#90c7fc">
						<div align="center"></div>
						<div align="center"><strong>Perfil do Im&oacute;vel </strong></div>
						</td>
						<td width="9%" bgcolor="#90c7fc">
						<div align="center"><strong>Categoria</strong></div>
						</td>
						<td width="10%" bgcolor="#90c7fc">
						<div align="center"><strong>Valor M&iacute;nimo do D&eacute;bito</strong></div>
						</td>
						<td width="7%" bgcolor="#90c7fc">
						<div align="center"><strong>Qtde. M&iacute;nima de Contas</strong></div>
						</td>
						<td width="7%" bgcolor="#90c7fc">
						<div align="center"><strong>Valor M&aacute;ximo do D&eacute;bito</strong></div>
						</td>
						<td width="7%" bgcolor="#90c7fc">
						<div align="center"><strong>Qtde. M&aacute;xima de Contas</strong></div>
						</td>
						<td width="18%" bgcolor="#90c7fc">
						<div align="center"><strong>Valor M&iacute;nimo do D&eacute;bito
						para Clientes com D&eacute;bito Autom&aacute;tico</strong></div>
						</td>
						<td width="16%" bgcolor="#90c7fc">
						<div align="center"><strong>Qtde. M&iacute;nima de Contas para
						Clientes com D&eacute;bito Autom&aacute;tico</strong></div>
						</td>
						<td width="18%" bgcolor="#90c7fc">
						<div align="center"><strong>Valor M&iacute;nimo da Conta do
						M&ecirc;s</strong></div>
						</td>
					</tr>
					<%String cor = "#FFFFFF";%>

					<!--Fim Tabela Reference a Páginação da Tela de Processo-->
					<%--Esquema de paginação--%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
						export="currentPageNumber=pageNumber;pageOffset"				
						maxPageItems="10" items="${sessionScope.totalRegistros}">

						<pg:param name="pg" />
						<pg:param name="q" />

						<logic:iterate name="colecaoCobrancaCriterioLinha"
							id="cobrancaCriterioLinha">
							<pg:item>
								<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%} else {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%}%>
								<tr bordercolor="#90c7fc">
									<td>
									<div align="center"><bean:define name="cobrancaCriterioLinha"
										property="imovelPerfil" id="imovelPerfil" /> <bean:write
										name="imovelPerfil" property="descricao" /></div>
									</td>
									
									<td>
									<div align="center"><bean:define name="cobrancaCriterioLinha"
										property="categoria" id="categoria" /> <bean:write
										name="categoria" property="descricaoAbreviada" /></div>
									</td>

									<td>
									<div align="center"><bean:write name="cobrancaCriterioLinha"
										property="valorMinimoDebito" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="cobrancaCriterioLinha"
										property="quantidadeMinimaContas" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="cobrancaCriterioLinha"
										property="valorMaximoDebito" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="cobrancaCriterioLinha"
										property="quantidadeMaximaContas" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="cobrancaCriterioLinha"
										property="valorMinimoDebitoDebitoAutomatico" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="cobrancaCriterioLinha"
										property="quantidadeMinimaContasDebitoAutomatico" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="cobrancaCriterioLinha"
										property="valorMinimoContaMes" /></div>
									</td>
								</tr>
							</pg:item>

						</logic:iterate>
				</table>
				</td>
			</tr>
				<tr>
					<td><strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
					</td>
				</tr>
			<tr>
				<td height="17">
				<div align="left"><input type="button" name="Submit222"
					class="bottonRightCol" value="Voltar"
					onClick="javascript:window.history.back();"></div>
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			</pg:pager>
			<%-- Fim do esquema de paginação --%>		
			
			</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
</form>
</body>
</html:html>

