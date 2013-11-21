<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" 
	formName="InformarSubdivisoesDeRotaActionForm" dynamicJavascript="true"/>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	var quadraFinalAnterior = parseInt("${quadraFinalAnterior}", 10);
	
	function concluir() {
		var form = document.forms[0];
		
		var rotas = form.rotas.value;
		var colecaoRotas = rotas.split("|");
		
		for (var i = 0; i < colecaoRotas.length; i++) {
			var codigoRota = colecaoRotas[i];
			if (codigoRota != "") {
				var quadraInicial = document.getElementById('quadra_ini_' + codigoRota).value;
				var quadraFinal = document.getElementById('quadra_fim_' + codigoRota).value;
				
				if (quadraInicial == "") {
					alert("Informe a Quadra inicial da Rota " + codigoRota + ".");
					return false;
				}
				
				if (quadraFinal == "") {
					alert("Informe a Quadra final da Rota " + codigoRota + ".");
					return false;
				}
				
				if (isNaN(quadraInicial) || parseInt(quadraInicial) < 0) {
					alert("Quadra inicial da Rota " + codigoRota + " deve conter números positivos.");
					return false;
				}
				
				if (isNaN(quadraFinal) || parseInt(quadraFinal) < 0) {
					alert("Quadra final da Rota " + codigoRota + " deve conter números positivos.");
					return false;
				}
				
				if (parseInt(quadraFinal) < parseInt(quadraInicial)) {
					alert("Quadra inicial da Rota " + codigoRota + " deve ser menor ou igual que quadra final.");
					return false;
				}
			}
		}
		
		form.action = "alterarSubdivisoesDeRotaAction.do";
		form.submit();
	}

	function adicionarSubrota() {
		var form = document.forms[0];
		var quadraInicial = parseInt(form.quadraInicial.value.toString(), 10);
		var quadraFinal = parseInt(form.quadraFinal.value.toString(), 10);
		
		if (isNaN(quadraInicial) || quadraInicial < 0) {
			alert("Quadra inicial deve conter números positivos.");
			return false;
		}
		
		if (isNaN(quadraFinal) || quadraFinal < 0) {
			alert("Quadra final deve conter números positivos.");
			return false;
		}
		
		if (quadraFinal < quadraInicial) {
			alert("Quadra inicial deve ser menor ou igual que quadra final.");
			return false;
		}
		
		form.action = "exibirAlterarSubdivisoesDeRotaAction.do";
		form.opcao.value = "adicionarSubrota";
		form.submit();
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');" >

<html:form action="/alterarSubdivisoesDeRotaAction" method="post"
	name="InformarSubdivisoesDeRotaActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InformarSubdivisoesDeRotaActionForm">
	
	<input type="hidden" name="opcao" value="" />
	<input type="hidden" name="index" value="" />
	<input type="hidden" name="rotas" id="rotas" value="" />
	
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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Alterar Subdivisões de Rota</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">
					<font><strong>Informe os dados abaixo:</strong></font>
					</td>
				</tr>
				
				<tr><td colspan="2">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td><div align="center"><strong>Rota</strong></div></td>
							<td><div align="center"><strong>Quadra<br/>Inicial</strong></div></td>
							<td><div align="center"><strong>Quadra<br/>Final</strong></div></td>
							<td colspan="2"><div align="center"><strong>Leiturista</strong></div></td>
						</tr>
						<% int indexRow = 0; %>
						<logic:present name="subdivisoesDeRota" scope="session" >
							<logic:iterate name="subdivisoesDeRota" id="subrota">
								<script>
									document.getElementById('rotas').value += "${subrota.codigoRota}" + "|";
								</script>
								<tr bgcolor="<%= ((indexRow++ % 2) == 0) ? "#cbe5fe" : "#90c7fc" %>">
									<td align="left" style="padding-left: 4px">
										<strong>${subrota.codigoRota}</strong>
									</td>
									<td align="center">
										<input type="text" size="4" maxlength="4" name="quadra_ini_${subrota.codigoRota}" id="quadra_ini_${subrota.codigoRota}" value="${subrota.quadraInicial}" onkeypress="return isCampoNumerico(event);">
									</td>
									<td align="center">
										<input type="text" size="4" maxlength="4" name="quadra_fim_${subrota.codigoRota}" id="quadra_fim_${subrota.codigoRota}" value="${subrota.quadraFinal}" onkeypress="return isCampoNumerico(event);">
									</td>
									<td align="left" colspan="2">
										<select name="leiturista_${subrota.codigoRota}" style="width: 310px;">
											<logic:iterate name="colecaoLeiturista" id="leiturista">
												<c:if test="${leiturista.id == subrota.idLeiturista}">
													<option value="${leiturista.id}" selected>${leiturista.cliente.nome}</option>
												</c:if>
												<c:if test="${leiturista.cliente.nome != subrota.nomeLeiturista}">
													<option value="${leiturista.id}">${leiturista.cliente.nome}</option>
												</c:if>
											</logic:iterate>
										</select>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<tr><td colspan="5"><div id="divNovasRotas"></div></td></tr>
						<c:if test="${editable == false}">
							<tr bgcolor="#cbe5fe">
								<td colspan="5" height="30" valign="bottom"><strong>Informe os dados das novas Rotas:</strong></td>
							</tr>
							
							<tr bgcolor="<%= ((indexRow++ % 2) == 0) ? "#cbe5fe" : "#90c7fc" %>">
								<td align="left" style="padding-left: 4px"></td>
								<td align="center">
									<html:text property="quadraInicial" size="4" maxlength="4" onkeypress="return isCampoNumerico(event);" />
								</td>
								<td align="center">
									<html:text property="quadraFinal" size="4" maxlength="4" onkeypress="return isCampoNumerico(event);" />
								</td>
								<td align="left" width="315">
									<html:select property="idLeiturista" style="width: 310px;">
										<html:options collection="colecaoLeiturista" labelProperty="cliente.nome" property="id" />
									</html:select>
								</td>
								<td align="center">
									<img border="0" src="<bean:message key="caminho.imagens"/>mais.gif" width="28"
										title="Clique para inserir uma nova Rota." style="cursor: pointer;"
										onclick="adicionarSubrota();" />
								</td>
							</tr>
						</c:if>
					</table>
				</td></tr>
				
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>

				<tr>
					<td height="24"><input type="button" class="bottonRightCol"
						value="Cancelar"
						onclick="window.location.href='/gsan/exibirInformarSubdivisoesDeRotaAction.do?menu=sim';" />
					</td>
					
					<td align="right"><input type="button" name="Button"
							class="bottonRightCol" value="Concluir"
							onClick="javascript:concluir()" /></td>
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