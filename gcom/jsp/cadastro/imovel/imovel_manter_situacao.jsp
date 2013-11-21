<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

function validarForm(form){
	form.submit();
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerImovelSituacaoAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirmar remoção?')">

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar Situa&ccedil;&atilde;o de
					Im&oacute;vel</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>

				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Situa&ccedil;&otilde;es
					de Im&oacute;vel Cadastradas:</strong> </font></td>
				</tr>
				<tr>
					<td colspan="7" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc">
								<tr bordercolor="#FFFFFF">

									<td width="29%">
									<div align="center"><strong>Tipo da Situa&ccedil;&atilde;o do
									Im&oacute;vel</strong></div>
									</td>
									<td width="30%">
									<div align="center"><strong>Situa&ccedil;&atilde;o da
									Liga&ccedil;&atilde;o de &Aacute;gua</strong></div>
									</td>

									<td width="31%">
									<div align="center"><strong>Situa&ccedil;&atilde;o da
									Liga&ccedil;&atilde;o de Esgoto</strong></div>
									</td>
								</tr>
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">
									<pg:param name="pg" />
									<pg:param name="q" />
									<%int cont = 1;%>
									<logic:iterate name="colecaoImovelSituacao" id="imovelSituacao">
										<pg:item>
											<%cont = cont + 1;
								if (cont % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {%>
											<tr bgcolor="#cbe5fe">
												<%}%>
												<td width="29%">
												<div align="left"><bean:write name="imovelSituacao"
													property="imovelSituacaoTipo.descricaoImovelSituacaoTipo" />
												</div>
												</td>
												<td width="30%">
												<div align="left"><bean:write name="imovelSituacao"
													property="ligacaoAguaSituacao.descricao" /></div>
												</td>
												<td width="30%">
												<div align="left">${imovelSituacao.ligacaoEsgotoSituacao.descricao}&nbsp;</div>
												</td>
											</tr>
										</pg:item>
									</logic:iterate>
							</table>
							</td>
						</tr>
						<tr>
							<td>
							<table width="100%">
								<tr>
									<td align="center"><strong><%@ include
										file="/jsp/util/indice_pager_novo.jsp"%></strong></td>
								</tr>
								</pg:pager>
							</table>
							</td>
						</tr>
					</table>
				<tr>
					<td>
					<table width="100%">
						<tr>
							<td><font color="#FF0000">
							<gsan:controleAcessoBotao name="Button" value="Remover"
							  onclick="javascript:validarForm(document.forms[0]);" url="removerImovelSituacaoAction.do"/>
							<!--
							<html:submit property="buttonRemover"
								styleClass="bottonRightCol" value="Remover" /> --></font> <input
								name="button" type="button" class="bottonRightCol"
								value="Voltar Filtro"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarImovelSituacaoAction.do?limpar=S"/>'"
								align="left" style="width: 80px;"></td>
							<td align="right" valign="top"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
