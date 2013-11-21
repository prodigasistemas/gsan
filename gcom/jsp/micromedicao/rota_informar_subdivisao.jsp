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
		form.submit();
	}

	function adicionarSubrota() {
		var form = document.forms[0];
		
		if (validateInformarSubdivisoesDeRotaActionForm(form)) {
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
			
			if (quadraFinalAnterior != -1 && quadraInicial <= quadraFinalAnterior) {
				alert("Quadra inicial deve ser maior que a quadra final da rota anterior.");
				return false;
			}
			
			form.action = "exibirInformarSubdivisoesDeRotaAction.do";
			form.opcao.value = "adicionarSubrota";
			form.submit();
		}
	}
	
	function removerSubrota(index) {
		var form = document.forms[0];
		form.action = "exibirInformarSubdivisoesDeRotaAction.do";
		form.opcao.value = "removerSubrota";
		form.index.value = index;
		form.submit();
	}

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	
	function receberRota(idRota, descricao, codigoRota) {
		var form = document.forms[0];
		form.idRota.value = idRota;
		form.descricaoRota.style.color = "#000000";
		form.descricaoRota.value = descricao;
		
		form.action = "exibirInformarSubdivisoesDeRotaAction.do";
		form.opcao.value = "resetarSubrotas";
		form.submit();
	}
	
	function limparRota() {
		var form = document.forms[0];
		form.idRota.value = "";
		form.descricaoRota.value = "";
		
		form.action = "exibirInformarSubdivisoesDeRotaAction.do";
		form.opcao.value = "resetarSubrotas";
		form.submit();
	}
	
	function alterar() {
		var form = document.forms[0];
		form.action = "exibirAlterarSubdivisoesDeRotaAction.do";
		form.submit();
	}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');" >

<html:form action="/informarSubdivisoesDeRotaAction" method="post"
	name="InformarSubdivisoesDeRotaActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InformarSubdivisoesDeRotaActionForm">
	
	<input type="hidden" name="opcao" value="" />
	<input type="hidden" name="index" value="" />

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
					<td class="parabg">Informar Subdivisões de Rota</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para realizar a subdivisão de rotas, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td><strong>Id da Rota:</strong></td>
					<td>
						<html:text property="idRota" maxlength="4" size="4" readonly="true" />
						
						<a href="javascript: chamarPopup('exibirPesquisarInformarRotaLeituraAction.do${idEmpresaLeituristica}', 'idRota', null, null, 275, 480, '', document.forms[0].idRota);">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Rota" /></a> 
						
						<logic:present name="InformarSubdivisoesDeRotaActionForm" property="idRota" scope="session">
							<html:text property="descricaoRota" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="InformarSubdivisoesDeRotaActionForm" property="idRota" scope="session">
							<html:text property="descricaoRota" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparRota();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>
				
				<c:if test="${editable == false}">
				<tr>
					<td colspan="4">
					<font color="red"><strong>Esta rota já foi subdividida.</strong></font>
					</td>
				</tr>
				</c:if>
				
				<tr><td colspan="2">
					<table width="100%" align="center" bgcolor="#99CCFF">
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td width="5%"><div align="center"><strong>Rota</strong></div></td>
							<td width="20%"><div align="center"><strong>Quadra<br/>Inicial</strong></div></td>
							<td width="20%"><div align="center"><strong>Quadra<br/>Final</strong></div></td>
							<td width="10%"><div align="center"><strong>Qtde</strong></div></td>
							<td width="25%"><div align="center"><strong>Leiturista</strong></div></td>
							<td width="20%"><div align="center"><strong></strong></div></td>
						</tr>
						<% int indexRow = 0; %>
						<logic:present name="subdivisoesDeRota" scope="session" >
							<logic:iterate name="subdivisoesDeRota" id="subrota">
								<tr bgcolor="<%= ((indexRow++ % 2) == 0) ? "#cbe5fe" : "#90c7fc" %>">
									<td align="left" style="padding-left: 4px">
										${subrota.codigoRota}
									</td>
									<td align="center">
										${subrota.quadraInicial}
									</td>
									<td align="center">
										${subrota.quadraFinal}
									</td>
									<td align="center">
										${subrota.quantidadeQuadras}
									</td>
									<td align="left" style="padding-left: 4px" >
										${subrota.nomeLeiturista}
									</td>
									<td align="center">
										<c:if test="${editable == true}">
											<a href="javascript:removerSubrota(<%= (indexRow -1) %>);">
												<img src="<bean:message key="caminho.imagens"/>Error.gif" border="0" title="Remover" /> 
											</a> 
										</c:if>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<c:if test="${editable == true}">
							<tr bgcolor="<%= ((indexRow++ % 2) == 0) ? "#cbe5fe" : "#90c7fc" %>">
								<td align="left" style="padding-left: 4px"></td>
								<td align="center">
									<html:text property="quadraInicial" size="4" maxlength="4" />
								</td>
								<td align="center">
									<html:text property="quadraFinal" size="4" maxlength="4" />
								</td>
								<td align="center"></td>
								<td align="center">
									<html:select property="idLeiturista">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoLeiturista" labelProperty="cliente.nome" property="id" />
									</html:select>
								</td>
								<td align="center">
									<c:if test="${limiteSubdivisoes == null || limiteSubdivisoes == false}">
									<input type="button" name="Adicionar" class="bottonRightCol" value="Adicionar" onclick="javascript:adicionarSubrota();" />
									</c:if>
								
									<c:if test="${limiteSubdivisoes == true}">
									<input type="button" name="Adicionar" class="bottonRightCol" value="Adicionar" onclick="javascript:adicionarSubrota();" disabled="disabled" />
									</c:if>
								</td>
							</tr>
						</c:if>
					</table>
				</td></tr>
				
				<c:if test="${editable == true && limiteSubdivisoes == true}">
				<tr>
					<td colspan="4">
					Não é possível subdividir em mais de 10 rotas.
					</td>
				</tr>
				</c:if>
				
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
						value="Limpar"
						onclick="window.location.href='/gsan/exibirInformarSubdivisoesDeRotaAction.do?menu=sim';" />
						
						<c:if test="${editable == false}">
							&nbsp;&nbsp;
							<input type="button" name="Button" class="bottonRightCol" value="Editar"
							onClick="javascript:alterar()" />
						</c:if>
						
					</td>
					
					<c:if test="${editable == true}">
						<td align="right"><input type="button" name="Button"
							class="bottonRightCol" value="Concluir"
							onClick="javascript:concluir()" /></td>
					</c:if>
					<c:if test="${editable == false}">
						<td align="right"><input type="button" name="Button"
							class="bottonRightCol" value="Concluir" disabled="disabled"
							onClick="javascript:concluir()" /></td>
					</c:if>

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
