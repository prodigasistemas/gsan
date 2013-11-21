<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
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

function remover(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "removerRoteiroEmpresaAction.do"
			document.forms[0].submit();
		 }
	}
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerRoteiroEmpresaAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Manter Roteiro da Empresa</td>

					<td width="11"><img src="imagens/parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" align="center" cellpadding="0" cellspacing="0">
				<!--corpo da segunda tabela-->
				<tr>
					<td colspan="5" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Roteiros da
					Empresa Cadastradas:</strong></font></td>
				</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#99CCFF">
								<tr bordercolor="#000000" bgcolor="#90c7fc">
									<td bgcolor="#90c7fc">
									<div align="center"><strong><a
										href="javascript:facilitador(this);">Todos</a></strong></div>
									</td>
									<td bgcolor="#90c7fc">
									<div align="center"><strong>Empresa</strong></div>
									</td>
									<td bgcolor="#90c7fc">
									<div align="center"><strong>Roteiro da Empresa</strong></div>
									</td>
									<td bgcolor="#90c7fc">
									<div align="center"><strong>Localidade</strong></div>
									</td>
									<td bgcolor="#90c7fc">
									<div align="center"><strong>Setor Comercial</strong></div>
									</td>
									<td bgcolor="#90c7fc">
									<div align="center"><strong>Leiturista</strong></div>
									</td>
								</tr>
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">
									<pg:param name="pg" />
									<pg:param name="q" />
									<logic:present name="colecaoRoteiroEmpresa">
										<%int cont = 0;%>
										<logic:iterate name="colecaoRoteiroEmpresa"
											id="roteiroEmpresa" scope="request">
											<pg:item>
												<%cont = cont + 1;
									if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>
													<td width="7%">
													<div align="center"><input type="checkbox"
														name="idRegistrosRemocao"
														value="${roteiroEmpresa.idRoteiroEmpresa}"/></div>
													</td>
													<td width="21%" align="center"><html:link
														page="/exibirAtualizarRoteiroEmpresaAction.do"
														paramName="roteiroEmpresa" paramProperty="idRoteiroEmpresa"
														paramId="idRegistroAtualizacao">${roteiroEmpresa.nomeEmpresa}
													</html:link></td>
													<td width="12%" align="center">
														${roteiroEmpresa.idRoteiroEmpresa}
													</td>
													<td width="12%" align="center">
														${roteiroEmpresa.nomeLocalidade}
													</td>

													<td width="12%" align="center">
														${roteiroEmpresa.codigoSetorComercial}
													</td>									
													<td width="12%" align="center">
														${roteiroEmpresa.nomeLeiturista}
													</td>									
																	
													<html:link href="/gsan/exibirManterRoteiroEmpresaAction.do"
														paramId="codigo" paramProperty="idRoteiroEmpresa"
														paramName="roteiroEmpresa">


													</html:link>

												</tr>
											</pg:item>
										</logic:iterate>
									</logic:present>
							</table>
							</td>
						</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td colspan="2">
							<div align="center"><strong><%@ include
								file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
							</td>
						</tr>
						</pg:pager>
						<tr>
							<td width="233"><font color="#FF0000"> <input name="Button"
								type="button" class="bottonRightCol" value="Remover"
								align="left"
								onclick="remover(document.ManutencaoRegistroActionForm.idRegistrosRemocao);" />
							<input name="button" type="button" class="bottonRightCol"
								tabindex="2" value="Voltar Filtro"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarRoteiroEmpresaAction.do"/>'">
							</font></td>
							<td align="right"></td>
						</tr>
					</table>
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
