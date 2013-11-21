<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<fmt:bundle basename="gcom.properties.application" />
<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
</head>

<body leftmargin="5" topmargin="5">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="115" valign="top" class="leftcoltext">

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


		<td valign="top" class="centercoltext">

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
				<td class="parabg">Consultar Hist&oacute;rico de
				Instala&ccedil;&atilde;o de Hidr&ocirc;metro</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		<table width="100%">
			<tr bgcolor="#79bbfd">
				<td >
				<div align="center" class="style11"><strong>Dados do Im&oacute;vel </strong></div>
				</td>
			</tr>
			
			<tr>
				<td height="31">
				<table width="100%" bgcolor="#90c7fc">
					<!--header da tabela interna -->
					<tr bgcolor="#90c7fc">
						<td width="24%">
							<div align="center"><strong>Inscri&ccedil;&atilde;o</strong></div>
						</td>
						<td width="17%">
							<div align="center"><strong>Matr&iacute;cula Im&oacute;vel</strong></div>
						</td>
						<td width="27%">
							<div align="center"><strong>Situa&ccedil;&atilde;o de &Aacute;gua</strong></div>
						</td>
						<td width="32%">
							<div align="center"><strong>Situa&ccedil;&atilde;o de Esgoto</strong></div>
						</td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td height="17">
						<div align="center">${requestScope.imovel.inscricaoFormatada}&nbsp;<br>
						</div>
						</td>
						<td>
						<div align="center">${requestScope.imovel.id}&nbsp;</div>
						</td>
						<td>
						<div align="center">${requestScope.imovel.ligacaoAguaSituacao.descricao}&nbsp;</div>
						</td>
						<td>
						<div align="center">${requestScope.imovel.ligacaoEsgotoSituacao.descricao}&nbsp;</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="22" class="style1">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td height="0">
						<table width="100%" bgcolor="#90c7fc">
							<!--header da tabela interna -->
							<tr bgcolor="#90c7fc">
								<td width="90%">
								<div align="center" class="style7"><strong>Endere&ccedil;o do
								Im&oacute;vel</strong></div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>
						<div style="width: 100%; height: 100%; overflow: auto;">
						<table width="100%" align="center" bgcolor="#90c7fc">
							<!--corpo da segunda tabela-->
							<tr bgcolor="#FFFFFF">
								<td width="90%">
								<div align="center">${requestScope.imovel.enderecoFormatado}&nbsp;</div>
								</td>
							</tr>
						</table>
						</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<c:if test="${!empty requestScope.hidrometrosInstalacaoHistorico}">
			<table width="100%">
				<tr>
					<td colspan="8" bgcolor="#79bbfd">
					<div align="center" class="style11"><strong>Hist&oacute;rico de
					Instala&ccedil;&atilde;o de Hidr&ocirc;metro</strong></div>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#90c7fc">

						<tr bgcolor="#90c7fc">
							<td width="13%">
							<div align="center"><strong>N&uacute;mero do Hidr&ocirc;metro</strong></div>
							</td>
							<td width="19%">
							<div align="center"><strong>Tipo de Medi&ccedil;&atilde;o</strong></div>
							</td>
							<td width="11%">
							<div align="center"><strong>Data de Instala&ccedil;&atilde;o</strong></div>
							</td>
							<td width="10%">
							<div align="center"><strong>Local de Instala&ccedil;&atilde;o</strong></div>
							</td>
							<td width="12%">
							<div align="center"><strong>Tipo de Prote&ccedil;&atilde;o</strong></div>
							</td>
							<td width="10%">
							<div align="center"><strong>Data de Retirada</strong></div>
							</td>
							<td width="16%">
							<div align="center"><strong>N&uacute;mero do Selo do HD</strong></div>
							</td>
							<td width="9%">
							<div align="center"><strong>Cavalete</strong></div>
							</td>
						</tr>
						<% String cor = "#cbe5fe";%>
						<c:forEach items="${requestScope.hidrometrosInstalacaoHistorico}"
							var="hidrometroInstalacaoHistorico">
                            <%    if (cor.equalsIgnoreCase("#cbe5fe")){   
                                cor = "#FFFFFF";%>
                                <tr bgcolor="#FFFFFF" height="18">   
                            <%} else{   
                                cor = "#cbe5fe";%>
                                <tr bgcolor="#cbe5fe" height="18">       
                            <%}%>
 										<td>
								<div align="center"><a
									href="consultarHistoricoInstalacaoHidrometroAction.do?codigoHidrometro=${hidrometroInstalacaoHistorico.hidrometro.numero}">${hidrometroInstalacaoHistorico.hidrometro.numero}&nbsp;</a>
								</div>
								</td>
								<td>
								<div align="center">${hidrometroInstalacaoHistorico.medicaoTipo.descricao}&nbsp;</div>
								</td>
								<td>
								<div align="center">
								<a href="javascript:abrirPopup('exibirPesquisarUsuarioEfetuouInstalacaoRetiradaAction.do?idUsuario=${hidrometroInstalacaoHistorico.usuarioInstalacao.id}&tipo=Instalação','','');">
								<bean:write
									name="hidrometroInstalacaoHistorico" property="dataInstalacao"
									formatKey="date.format" />&nbsp;</a></div>
								</td>
								<td>
								<div align="center">${hidrometroInstalacaoHistorico.hidrometroLocalInstalacao.descricaoAbreviada}&nbsp;</div>
								</td>
								<td>
								<div align="center">${hidrometroInstalacaoHistorico.hidrometroProtecao.descricao}&nbsp;</div>
								</td>
								<td>
								<div align="center">
								<a href="javascript:abrirPopup('exibirPesquisarUsuarioEfetuouInstalacaoRetiradaAction.do?idUsuario=${hidrometroInstalacaoHistorico.usuarioRetirada.id}&tipo=Retirada','','');">
								<bean:write
									name="hidrometroInstalacaoHistorico" property="dataRetirada"
									formatKey="date.format" />&nbsp;</a></div>
								</td>
								<td>
								<div align="center">${hidrometroInstalacaoHistorico.numeroSelo}&nbsp;</div>
								</td>
								<td>
								<div align="center"><c:if
									test="${hidrometroInstalacaoHistorico.indicadorExistenciaCavalete eq 1}">
						Sim	
					</c:if> <c:if
									test="${hidrometroInstalacaoHistorico.indicadorExistenciaCavalete eq 2}">
						Não
					</c:if></div>
								</td>
							</tr>
						</c:forEach>

					</table>
					</td>
				</tr>
			</table>
		</c:if>
		<table width="100%" border="0" cellpadding="1" cellspacing="0">
			<tr>
				<td align="left"><input class="bottonRightCol" type="button"
					name="Voltar" value="Voltar Consulta"
					onclick="javascript:window.location.href = 'exibirConsultarHistoricoInstalacaoHidrometroInformarAction.do'" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>

		</td>
	</tr>
</table>


<%@ include file="/jsp/util/rodape.jsp"%>



</body>
</html:html>
