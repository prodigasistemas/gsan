<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

<%@ page import="gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="generator"
	content="Web page layout created by Xara Webstyle 4 - http://www.xara.com/webstyle/" />
<link rel="stylesheet"
	href="<bean:message key='caminho.css'/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key='caminho.js'/>util.js"></script>

<script language="JavaScript">
window.onmousemove = dimensaoPagina;
function dimensaoPagina(){
	resizePageSemLink(760, 580);
}

function resizePageSemLink(largura, altura){

	   //Para abrir o popup centralizado
		var height = window.screen.height - 160;
		var width = window.screen.width;
		var top = 50;
		var left = (width - largura)/2;
		resizeNow(largura, altura, top, left);
  }

  function resizeNow(largura, altura, top, left){
       window.resizeTo(largura, altura);
       window.moveTo(left , top);
  }
</script>
</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(760, 580);">
<form>
<table width="720" border="0" cellpadding="0" cellspacing="5">
	<tr>
		<td width="710" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="710" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img
					src="<bean:message key='caminho.imagens'/>parahead_left.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
					border="0" /></td>
				<td class="parabg">Consultar Itens do Movimento dos Arrecadadores</td>
				<td width="11"><img
					src="<bean:message key='caminho.imagens'/>parahead_right.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
					border="0" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>

		<table width="710" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="2">

				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td>
						<table width="100%" bgcolor="#90c7fc">
							<tr bgcolor="#90c7fc">
								
								<td width="8%">
								<div align="center"><strong>Registro</strong>
								</td>

								<td width="10%">
								<div align="center"><strong>Mat. Imóvel</strong></div>
								</td>

								<td width="12%">
								<div align="center"><strong>Ident. Imóvel/Cliente</strong></div>
								</td>
								
								<td width="15%">
								<div align="center"><strong>Tipo de Pag.</strong></div>
								</td>

								<td width="20%">
								<div align="center"><strong>Ocorrência</strong></div>
								</td>

								<td width="10%">
								<div align="center"><strong>Ind. Aceitação</strong></div>
								</td>
								
								<td width="12%">
								<div align="center"><strong>Vl no Movimento</strong></div>
								</td>
								
								<td width="13%">
								<div align="center"><strong>Vl dos Pagamentos</strong></div>
								</td>
							
						</table>
						</td>
					</tr>
				</table>

				<logic:present name="colecaoArrecadadorMovimentoItemHelper"
					scope="request">

					<div style="width: 100%; height: 400; overflow: auto;">

					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td><%String cor = "#FFFFFF";%>

							<table width="100%" align="center" bgcolor="#99CCFF">

								<logic:iterate name="colecaoArrecadadorMovimentoItemHelper"
									id="arrecadadorMovimentoItemHelper"
									type="ArrecadadorMovimentoItemHelper">


									<%if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
									<tr bgcolor="#FFFFFF">
										<%} else {
							cor = "#FFFFFF";%>
									<tr bgcolor="#cbe5fe">
										<%}%>

										<td align="center" width="8%" height="25">
											<logic:present name="arrecadadorMovimentoItemHelper" property="codigoRegistro">
												<bean:write name="arrecadadorMovimentoItemHelper" property="codigoRegistro" />
											</logic:present> 
											<logic:notPresent name="arrecadadorMovimentoItemHelper"	property="codigoRegistro">
												&nbsp;
											</logic:notPresent>
										</td>
										
										<td width="10%">
											<div align="center">
												<logic:present name="arrecadadorMovimentoItemHelper" property="matriculaImovel">
													<bean:write name="arrecadadorMovimentoItemHelper" property="matriculaImovel" />
												</logic:present> 
												<logic:notPresent name="arrecadadorMovimentoItemHelper" property="matriculaImovel">
													&nbsp;
												</logic:notPresent>
											</div>
										</td>

										<td width="12%">
											<div align="center">
												<logic:present name="arrecadadorMovimentoItemHelper" property="identificacao">
													<bean:write name="arrecadadorMovimentoItemHelper" property="identificacao" />
												</logic:present> 
												<logic:notPresent name="arrecadadorMovimentoItemHelper" property="identificacao">
													&nbsp;
												</logic:notPresent>
											</div>
										</td>


										
										<td width="15%" align="left">
											<div align="left">
												<logic:present name="arrecadadorMovimentoItemHelper" property="tipoPagamento">
													<bean:write name="arrecadadorMovimentoItemHelper" property="tipoPagamento" />
											    </logic:present> 
											    <logic:notPresent name="arrecadadorMovimentoItemHelper" property="tipoPagamento">
													&nbsp;
												</logic:notPresent>
											</div>
										</td>
										
										<td width="20%">
											<div align="left">
												<logic:present name="arrecadadorMovimentoItemHelper" property="ocorrencia">
													<html:link
														href="/gsan/exibirApresentarDadosConteudoRegistroMovimentoArrecadadorAction.do"
														paramId="arrecadadorMovimentoItemID" paramProperty="id"
														paramName="arrecadadorMovimentoItemHelper"
														title="<%="" + arrecadadorMovimentoItemHelper.getOcorrencia()%>">
														<bean:write name="arrecadadorMovimentoItemHelper"
															property="ocorrencia" />
													</html:link>
												</logic:present> 
												<logic:notPresent name="arrecadadorMovimentoItemHelper" property="ocorrencia">
												&nbsp;
												</logic:notPresent>
											</div>
										</td>
										
										<td width="10%">
											<div align="left">
												<logic:present name="arrecadadorMovimentoItemHelper" property="descricaoIndicadorAceitacao">
													<bean:write name="arrecadadorMovimentoItemHelper" property="descricaoIndicadorAceitacao" />
												</logic:present> 
												<logic:notPresent name="arrecadadorMovimentoItemHelper" property="descricaoIndicadorAceitacao">
														&nbsp;
												</logic:notPresent>
											</div>
										</td>
										
										<td width="12%">
											<div align="right">
												<logic:present name="arrecadadorMovimentoItemHelper" property="vlMovimento">
													<bean:write name="arrecadadorMovimentoItemHelper" property="vlMovimento" />
												</logic:present> 
												<logic:notPresent name="arrecadadorMovimentoItemHelper" property="vlMovimento">
														&nbsp;
												</logic:notPresent>
											</div>
										</td>
										
										<td width="13%">
											<div align="right">
												<logic:present name="arrecadadorMovimentoItemHelper" property="vlPagamento">
													<bean:write name="arrecadadorMovimentoItemHelper" property="vlPagamento" />
												</logic:present> 
												<logic:notPresent name="arrecadadorMovimentoItemHelper" property="vlPagamento">
														&nbsp;
												</logic:notPresent>
											</div>
										</td>
									</tr>
								</logic:iterate>
								
								<logic:notEmpty name="colecaoArrecadadorMovimentoItemHelper">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
								cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td bgcolor="#90c7fc" colspan="6">
										<div align="center" class="style9">
											<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<strong>Total</strong> </font>
										</div>
									</td>
									<td>
										<div align="right" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										<%=session.getAttribute("valorDadosMovimento")%> </font></div>
									</td>
									<td>
										<div align="right" class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										<%=session.getAttribute("valorDadosPagamento")%> </font></div>
									</td>
								</tr>
							</logic:notEmpty>
							</table>
							</td>
						</tr>
					</table>
					</div>

				</logic:present></td>
			</tr>

			<tr>
				<td align="left"><input type="button" name="fechar" class="bottonRightCol"
					value="Voltar" tabindex="2" style="width: 70px;"
					onclick="javascript:history.back();">&nbsp;
				<input type="button" name="fechar" class="bottonRightCol"
					value="Fechar" tabindex="2" style="width: 70px;"
					onclick="javascript:window.close();"></td>
				<td align="right" valign="top">
					<a href="javascript:toggleBox('demodiv',1);">
                       	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Itens do Movimento dos Arrecadadores"/>
					</a>
				</td> 
			</tr>
			
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>

		</td>
	</tr>

</table>
	 <jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioMovimentoArrecadadoresItensAction.do"/>
</body>
</form>
</html:html>


