<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
	
function facilitador(objeto){
	if (objeto.value == "0"){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
}
function remover1(objeto){
	if (CheckboxNaoVazio(objeto)){
		if (confirm ('Confirma remoção?')) {
			document.forms[0].action = '/gsan/removerProcessoIniciadoAction.do';
			document.forms[0].submit();
		 }
	}
}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/removerProcessoIniciadoAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post">

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
					<td class="parabg">Consultar Processos Iniciados</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td><strong>M&ecirc;s/Ano de Refer&ecirc;ncia:
					${requestScope.mesAnoReferencia}</strong></td>
					<td>
					<div align="left"><strong> Data: <bean:write name="dataCorrente"
						formatKey="date.format" /> - Hora: <bean:write
						name="dataCorrente" formatKey="hour.format" /> </strong></div>
					</td>

				</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" align="center" cellpadding="0" cellspacing="0">


				<tr bordercolor="#90c7fc">
					<td colspan="5"><font color="#000000" style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Processos
					Iniciados</strong></font></td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" align="center" bgcolor="#90c7fc">
						<tr bordercolor="#000000">
							
							<!-- 
							<td width="4%" bgcolor="#90c7fc" align="center"><strong><a
								href="javascript:facilitador(this);">Todos</a></strong>
							</td>
							 -->
							<td width="40%" bgcolor="#90c7fc">
							<div align="left"><strong>Processo</strong></div>
							</td>
							<td bgcolor="#90c7fc">
							<div align="left"><strong>Grupo</strong></div>
							</td>
							<td width="12%" bgcolor="#90c7fc">
							<div align="center"><strong>Data</strong></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="center"><strong>Hora</strong></div>
							</td>

							<td width="13%" bgcolor="#90c7fc">
							<div align="left">
							<p><strong>Usu&aacute;rio</strong></p>
							</div>
							
							</td>
							<td width="22%" bgcolor="#90c7fc">
							<div align="left"><strong>Situa&ccedil;&atilde;o</strong></div>
							</td>
						</tr>
						<%int cont = 0;%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />


							<logic:iterate name="colecaoProcessosIniciados"
								id="processoIniciado">


								<pg:item>

									<%cont = cont + 1;
								if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {

								%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										
										<!--
										<td>
										<div align="center"><strong> <span class="style1"><strong> <html:checkbox
											property="idRegistrosRemocao" value="${processoIniciado.id}" />
										</strong></span> </strong></div>
										</td>
										-->
										
										<td>
										<div align="left"><html:link
											page="/exibirConsultarDadosProcessoIniciadoAction.do"
											paramName="processoIniciado" paramProperty="id"
											paramId="idRegistroAtualizacao">
											<bean:write name="processoIniciado"
												property="processo.descricaoProcesso" />
										</html:link></div>
										</td>
										<td>
										<div align="center"><bean:write name="processoIniciado"
											property="codigoGrupoProcesso" /></div>
										<td>
										<div align="center"><bean:write name="processoIniciado"
											property="dataHoraAgendamento" formatKey="date.format" /></div>
										</td>
										<td>
										<div align="center"><bean:write name="processoIniciado"
											property="dataHoraAgendamento" formatKey="hour.format" /></div>
										</td>
										<td>
										<div align="left"><bean:write name="processoIniciado"
											property="usuario.nomeUsuario" /></div>
										</td>
										
										<td>
										<div align="left"><bean:write name="processoIniciado"
											property="processoSituacao.descricao" /></div>
										</td>
									</tr>

								</pg:item>

							</logic:iterate>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">

				<tr>
				
					<td colspan="3" class="style1">
					<!--
					<input type="button"
						class="bottonRightCol" value="Remover"
						onclick="javascript:remover1(document.forms[0].idRegistrosRemocao);">
					-->
					<input name="voltar" type="button" class="bottonRightCol"
						value="Voltar Filtro"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarProcessoAction.do"/>'">
				   </td>
				   

					<td width="116" class="style1">
					<div align="right"><%--<input name="iniciar" type="button"
						class="bottonRightCol" value="Iniciar">--%>&nbsp;</div>
					</td>
				</tr>
				<tr>
					<td width="330">&nbsp;</td>
					<td width="157" align="right">&nbsp;</td>
					<td colspan="2" align="right">&nbsp;</td>
				</tr>


			</table>
			<table width="100%" border="0">
				<tr>
					<td align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></td>
				</tr>
			</table>




			</td>
		</tr>
		</pg:pager>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
