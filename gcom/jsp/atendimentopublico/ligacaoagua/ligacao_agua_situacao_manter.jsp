<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirLigacaoAguaSituacaoActionForm" />
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

function remover(objeto){
	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "removerLigacaoAguaSituacaoAction.do"
			document.forms[0].submit();
		 }
	}
}

</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/removerLigacaoAguaSituacaoAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

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
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Situa&ccedil;&atilde;o de Liga&ccedil;&atilde;o de &Aacute;gua</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="11"><font color="#000000" style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Situa&ccedil;&atilde;o(&otilde;es) de Liga&ccedil;&atilde;o de &Aacute;gua encontrada(s):</strong> </font></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#000000">
							
          <td width="50" bgcolor="#90c7fc" align="center"><strong><a
								href="javascript:facilitador(this);">Todos</a></strong></td>
							
          <td width="54" bordercolor="#000000" bgcolor="#90c7fc"
								align="center"> 
            <div align="center"><strong>C&oacute;digo</strong></div>
							</td>
							
          <td width="219" bgcolor="#90c7fc" align="center"><strong>Descri&ccedil;&atilde;o</strong></td>
							
          <td width="70" bgcolor="#90c7fc" align="center"><strong>Cons. M&iacute;n.</strong></td>
							
          <td width="56" bgcolor="#90c7fc" align="center"><strong>Ind. Fat.</strong></td>
							
          <td width="56" bgcolor="#90c7fc" align="center"><strong>Ind. Rede</strong></td>
							
          <td width="56" bgcolor="#90c7fc" align="center"><strong>Ind. Lig.</strong></td>
          
          <td width="56" bgcolor="#90c7fc" align="center"><strong>Ind. Ativa</strong></td>
           
          <td width="56" bgcolor="#90c7fc" align="center"><strong>Ind. Deslig.</strong></td>
          
          <td width="56" bgcolor="#90c7fc" align="center"><strong>Ind. Cadast.</strong></td>
          
          <td width="56" bgcolor="#90c7fc" align="center"><strong>Ind. Análize</strong></td>
							
          <td width="45" bgcolor="#90c7fc" align="center"><strong>Ind. Uso</strong></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF" border="0">
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
							
							
							<logic:present name="colecaoLigacaoAguaSituacao">
								<%int cont = 0;%>
								<logic:iterate name="colecaoLigacaoAguaSituacao" id="ligacaoAguaSituacao"
									scope="request">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="6%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao" value="${ligacaoAguaSituacao.id}">
	            								</div>
											</td>

											<td width="7%" align="center">${ligacaoAguaSituacao.id}</td>

											<td width="26.5%" align="center">
												<html:link
													page="/exibirAtualizarLigacaoAguaSituacaoAction.do"
													paramName="ligacaoAguaSituacao" 
													paramProperty="id"
													paramId="idRegistroAtualizacao">${ligacaoAguaSituacao.descricao}
													</html:link>
											</td>

											<html:link href="/gsan/exibirManterLigacaoAguaSituacaoAction.do"
												paramId="codigo" paramProperty="id" paramName="ligacaoAguaSituacao">
											</html:link>
														
										
										<td width="8%" align="center">
											<bean:write name="ligacaoAguaSituacao" 
												property="consumoMinimoFaturamento" 
												 format='#,###.###' 
												 formatKey='number.format'/>
											
										
										</td>
										
											
										<td width="7%" align="center"><logic:equal
														name="ligacaoAguaSituacao"
														property="indicadorFaturamentoSituacao" value="1">
											SIM
										</logic:equal> <logic:equal name="ligacaoAguaSituacao"
														property="indicadorFaturamentoSituacao" value="2">
											N&Atilde;O
										</logic:equal></td>
										
										<td width="7%" align="center"><logic:equal
														name="ligacaoAguaSituacao"
														property="indicadorExistenciaRede" value="1">
											SIM
										</logic:equal> <logic:equal name="ligacaoAguaSituacao"
														property="indicadorExistenciaRede" value="2">
											N&Atilde;O
										</logic:equal></td>
										
										<td width="7%" align="center"><logic:equal
														name="ligacaoAguaSituacao"
														property="indicadorExistenciaLigacao" value="1">
											SIM
										</logic:equal> <logic:equal name="ligacaoAguaSituacao"
														property="indicadorExistenciaLigacao" value="2">
											N&Atilde;O
										</logic:equal></td>
										
										<td width="7%" align="center"><logic:equal
														name="ligacaoAguaSituacao"
														property="indicadorAguaAtiva" value="1">
											SIM
										</logic:equal> <logic:equal name="ligacaoAguaSituacao"
														property="indicadorAguaAtiva" value="2">
											N&Atilde;O
										</logic:equal></td>
										
										<td width="7%" align="center"><logic:equal
														name="ligacaoAguaSituacao"
														property="indicadorAguaDesligada" value="1">
											SIM
										</logic:equal> <logic:equal name="ligacaoAguaSituacao"
														property="indicadorAguaDesligada" value="2">
											N&Atilde;O
										</logic:equal></td>
										
										<td width="7%" align="center"><logic:equal
														name="ligacaoAguaSituacao"
														property="indicadorAguaCadastrada" value="1">
											SIM
										</logic:equal> <logic:equal name="ligacaoAguaSituacao"
														property="indicadorAguaCadastrada" value="2">
											N&Atilde;O
										</logic:equal></td>
										
										<td width="7%" align="center"><logic:equal
														name="ligacaoAguaSituacao"
														property="indicadorAnalizeAgua" value="1">
											SIM
										</logic:equal> <logic:equal name="ligacaoAguaSituacao"
														property="indicadorAnalizeAgua" value="2">
											N&Atilde;O
										</logic:equal></td>
										
										<td width="7%" align="center"><logic:equal
														name="ligacaoAguaSituacao"
														property="indicadorUso" value="1">
											&nbsp;&nbsp;&nbsp;Ativo&nbsp;&nbsp;&nbsp;
										</logic:equal> <logic:equal name="ligacaoAguaSituacao"
														property="indicadorUso" value="2">
										&nbsp;&nbsp;&nbsp;Inativo&nbsp;&nbsp;&nbsp;
										</logic:equal></td>
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
						type="button" class="bottonRightCol" value="Remover" align="left"
						onclick="remover(document.ManutencaoRegistroActionForm.idRegistrosRemocao);" />
					<input name="button" type="button" class="bottonRightCol"
						tabindex="2" value="Voltar Filtro"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarLigacaoAguaSituacaoAction.do"/>'">
					</font></td>
                   <td align="right" valign="top">
						<a href="javascript:toggleBox('demodiv',1);">
                        	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Situações de Ligação de Água"/>
						</a>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioLigacaoAguaSituacaoManterAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
