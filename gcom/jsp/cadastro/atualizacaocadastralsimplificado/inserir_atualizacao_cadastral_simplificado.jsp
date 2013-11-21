<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema"%>

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
	
<script type="text/javascript">
	function validarForm(form){
		var mensagens = "";
		
		if (form.comentario.value == "")
			mensagens += "O campo 'Comentário' precisa ser preenchido!\n";
		if (form.arquivo.value == "")
			mensagens += "O campo 'Arquivo Texto' precisa ser preenchido!";
		
		if (mensagens == "") {
			form.action = 'inserirAtualizacaoCadastralSimplificadoAction.do';
			submitForm(form);
		} else {
			alert(mensagens);			
		}
		
	}
</script>

</head>

<body leftmargin="5" topmargin="5" onload="document.forms[0].comentario.focus();">

<div id="formDiv">

<form action="inserirAtualizacaoCadastralSimplificadoAction.do"
	method="post"
	enctype="multipart/form-data" name="inserirAtualizacaoCadastralSimplificadoActionForm">

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

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
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
				<td class="parabg">Atualização Cadastral Simplificado</td>
				<td width="11" valign="top"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">Para importar o arquivo texto, informe os
				dados abaixo:</td>
			</tr>
			<tr>
				<td><strong>Comentário:<span style="color: red">*</span></strong></td>
				<td><input name="comentario" size="50" maxlength="50" /></td>
			</tr>
			<tr>
				<td><strong>Arquivo Texto:<span style="color: red">*</span></strong></td>
				<td><input type="file" style="textbox" name="arquivo"
					size="50" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="left"><span style="color: red">*</span> Campo
				Obrigat&oacute;rio</td>
			</tr>
		</table>

		<table width="100%">
			
			<tr>
				<td>&nbsp;</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

			<tr>
				<td align="left"><input name="Button" type="button"
					class="bottonRightCol" value="Desfazer" align="left"
					onclick="window.location.href='<html:rewrite page="/exibirInserirAtualizacaoCadastralSimplificadoAction.do?menu=sim"/>'">
				&nbsp;<input type="button" name="ButtonCancelar"
					class="bottonRightCol" value="Cancelar"
					onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
				<td align="right"><input type="button" name="Button" class="bottonRightCol"
					value="Atualizar" onclick="validarForm(document.forms[0]);" />
				</td>
			</tr>
		</table>
			<p>&nbsp;</p>
			<logic:notEmpty name="arquivos">
			<table width="100%" border="0" bgcolor="#90c7fc">
			<tbody>
			<tr align="left" bgcolor="#90c7fc">
				<td width="10%"  align="center"><strong>Arquivo</strong></td>
				<td width="60%" align="center"><strong>Comentário</strong></td>
				<td width="15%" align="center"><strong>Usuário</strong></td>			
				<td width="15%" align="center"><strong>Data</strong></td>
			</tr>
			<%--Esquema de paginação--%>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
				<pg:param name="pg" />
				<pg:param name="q" />
				<%String cor = "#cbe5fe";%>
				<logic:iterate name="arquivos" id="arquivo">
					<pg:item>
						<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
									<%} else {
					cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
									<td width="10%">
										<div align="left">
											<html:link title="Baixar o arquivo importado" page="/retornarAquivoTxtAtualizacaoCadastralSimplificadoAction.do" paramName="arquivo" paramProperty="id" paramId="id">
												<bean:write name="arquivo" property="nome"/>
											</html:link>
										</div>
									</td>
											
									<td width="50%">
										<bean:write name="arquivo" property="comentario" />
									</td>
									
									<td width="15%">
										<bean:write name="arquivo" property="usuario.login" />
									</td>														
									<td width="15%">
										<bean:write name="arquivo" property="ultimaAlteracao" formatKey="datehour.format" />
									</td>
									<td width="10%">
										<html:link title="Ver detalhes da importação" page="/exibirDetalhesAtualizacaoCadastralSimplificadoAction.do" paramName="arquivo" paramProperty="id" paramId="id">
											detalhes
										</html:link>
									</td>														
						</tr>
					</pg:item>
				</logic:iterate>
				</tbody>
		</table>
		<table width="100%" border="0">
			<tr>
				<td>
				<div align="center"><strong><%@ include
				file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
				</td>
			</tr>
		</table>
		</pg:pager>
			<p>&nbsp;</p>
		</logic:notEmpty>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
