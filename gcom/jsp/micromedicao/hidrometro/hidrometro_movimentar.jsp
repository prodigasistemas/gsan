<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.micromedicao.hidrometro.Hidrometro"%>
<%@ page import="java.util.Collection"%>

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
<script language="JavaScript">
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

function checkboxNaoVazio(campo) {

	form = document.forms[0];
	retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	}

	if (!retorno) {
		alert('Nenhum hidrômetro foi selecionado para movimentação.');
	} else {
        submeterFormPadrao(form);
	}

}
//-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/movimentarHidrometroAction"
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
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Movimentar Hidrômetro</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="590" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="4" height="23"><font color="#000000"
						style="font-size: 10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Hidrômetro(s)
					encontrado(s):</strong></font></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="2"></td>
				</tr>
				<logic:notEmpty name="colecaoHidrometro" scope="session">
					<%if (((Collection) session
								.getAttribute("colecaoHidrometro")).size() > 11) {%>
					<tr>
						<td>
						<table width="100%" bgcolor="#90c7fc" border="0" cellpadding="0">
							<tr>
								<td align="center" width="*"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></td>
								<td align="center" width="11%"><strong>N&uacute;mero</strong></td>
								<td align="center" width="10%"><strong>Data de
								Aquisi&ccedil;&atilde;o</strong></td>
								<td align="center" width="12%"><strong>Ano de
								Fabrica&ccedil;&atilde;o</strong></td>
								<td align="center" width="17%"><strong>Marca</strong></td>
								<td align="center" width="12%"><strong>Capacidade</strong></td>
								<td align="center" width="13%"><strong>Situa&ccedil;&atilde;o</strong></td>
								<td align="center" width="17%"><strong>Armazenagem</strong></td>
							</tr>
						</table>
						</td>
					</tr>
					<%}

					%>
				</logic:notEmpty>
				<tr>
					<td height="330">
					<div style="width: 100%; height: 100%; overflow: auto;">
					<table width="100%" bgcolor="#99CCFF">
						<%int cont = 0;%>
						<logic:notEmpty name="colecaoHidrometro" scope="session">
							<%if (((Collection) session
								.getAttribute("colecaoHidrometro")).size() <= 11) {%>
							<tr>
								<td align="center"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></td>
								<td align="center"><strong>N&uacute;mero</strong></td>
								<td align="center"><strong>Data de Aquisi&ccedil;&atilde;o</strong></td>
								<td align="center"><strong>Ano de Fabrica&ccedil;&atilde;o</strong></td>
								<td align="center"><strong>Marca</strong></td>
								<td align="center"><strong>Capacidade</strong></td>
								<td align="center"><strong>Situa&ccedil;&atilde;o</strong></td>
								<td align="center"><strong>Armazenagem</strong></td>
							</tr>
							<%}

					%>
						</logic:notEmpty>
						<logic:iterate name="colecaoHidrometro" id="hidrometro"
							type="Hidrometro">
							<%cont = cont + 1;
						if (cont % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
								<%} else {

						%>
							<tr bgcolor="#FFFFFF">
								<%}%>
								<td width="*" align="center"><html:checkbox
									property="idRegistrosRemocao"
									value="<%="" + hidrometro.getId()%>" /></td>
								<td width="11%" align="right"><span
									style="font-size: 10px; font-color:#000000; font-face:verdana">
								<bean:write name="hidrometro" property="numero" /> </span></td>
								<td width="10%" align="center"><span
									style="font-size: 10px; font-color:#000000; font-face:verdana">
								<bean:write name="hidrometro" property="dataAquisicao"
									formatKey="date.format" /> </span></td>
								<td width="12%" align="center"><span
									style="font-size: 10px; font-color:#000000; font-face:verdana">
								<bean:write name="hidrometro" property="anoFabricacao" /> </span>
								</td>
								<td width="18%"><span
									style="font-size: 10px; font-color:#000000; font-face:verdana">
								<bean:write name="hidrometro"
									property="hidrometroMarca.descricao" /> </span></td>
								<td width="12%"><span
									style="font-size: 10px; font-color:#000000; font-face:verdana">
								<bean:write name="hidrometro"
									property="hidrometroCapacidade.descricao" /> </span></td>
								<td width="13%"><span
									style="font-size: 10px; font-color:#000000; font-face:verdana">
								<bean:write name="hidrometro"
									property="hidrometroSituacao.descricao" /> </span></td>
								<td width="14%">
								<logic:notEmpty name="hidrometro" property="hidrometroLocalArmazenagem">
									<span
										style="font-size: 10px; font-color:#000000; font-face:verdana">
									<bean:write name="hidrometro"
										property="hidrometroLocalArmazenagem.descricaoAbreviada" /></span>
								</logic:notEmpty>
								<logic:empty name="hidrometro" property="hidrometroLocalArmazenagem">&nbsp;</logic:empty>
								</td>
							</tr>
						</logic:iterate>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td><br>
					<table width="100%">
						<tr>
							<td><gsan:controleAcessoBotao name="Button" value="Movimentar"
								onclick="javascript:checkboxNaoVazio(idRegistrosRemocao);"
								url="movimentarHidrometroAction.do" /> <!-- 
									<input type="button" class="bottonRightCol" value="Movimentar" onclick="checkboxNaoVazio(idRegistrosRemocao)">
									 --><input type="button" class="bottonRightCol"
								value="Voltar Filtro"
								onclick="window.location.href='/gsan/exibirFiltrarHidrometroAction.do?tela=movimentarHidrometro'">
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
