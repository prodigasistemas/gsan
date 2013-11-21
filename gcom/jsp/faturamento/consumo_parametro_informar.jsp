<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InformarConsumoParametroActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function consultarConsumoParametro(form){		if(testarCampoValorZero(document.InformarConsumoParametroActionForm.mesAnoReferencia, 'Mês e Ano de Referência') && 			testarCampoValorZero(document.InformarConsumoParametroActionForm.idCategoria, 'Categoria') && 			testarCampoValorZero(document.InformarConsumoParametroActionForm.idSubCategoria, 'Subcategoria')) {		
			if(validateInformarConsumoParametroActionForm(form)){
					redirecionarSubmit("exibirInformarConsumoParametroAction.do?consultar=sim");
			}		}	}
	
	function confirmarConsultaConsumoParametro(form){
			if(testarCampoValorZero(document.InformarConsumoParametroActionForm.mesAnoReferencia, 'Mês e Ano de Referência') && 
				testarCampoValorZero(document.InformarConsumoParametroActionForm.idCategoria, 'Categoria') && 
				testarCampoValorZero(document.InformarConsumoParametroActionForm.idSubCategoria, 'Subcategoria')) {
				
				var where_to= confirm("Os dados informados serão perdidos. Deseja Continuar ?");
				
				if(validateInformarConsumoParametroActionForm(form)){
					if (where_to== true) {
						redirecionarSubmit("exibirInformarConsumoParametroAction.do?consultar=sim");
					}
				}
			}
	}			

	function validarForm(form){		if(testarCampoValorZero(document.InformarConsumoParametroActionForm.mesAnoReferencia, 'Mês e Ano de Referência') && 			testarCampoValorZero(document.InformarConsumoParametroActionForm.idCategoria, 'Categoria') && 			testarCampoValorZero(document.InformarConsumoParametroActionForm.idSubCategoria, 'Subcategoria')) {
			if(validateInformarConsumoParametroActionForm(form)){	    		form.submit();			}		}	}
	
	function desabilitaBotoes(){
		var form = document.InformarConsumoParametroActionForm;
	
		form.Adicionar.disabled=true;
		form.Informar.disabled=true;
	}		
	
/* Remove uma area e consumo */	
	function remover(id){
		var form = document.InformarConsumoParametroActionForm;
		var where_to= confirm("Deseja realmente remover este parâmetro e consumo ?");
		if (where_to== true) {
		    form.action='exibirInformarConsumoParametroAction.do?remover='+id;
		    form.submit();
 		}
	}		function reload() {	   	redirecionarSubmit("exibirInformarConsumoParametroAction.do?reload=sim");	}
 
	function atualizarComponente(id) {
		abrirPopup('exibirAdicionarConsumoParametroAction.do?atualizarComponente=sim&atualizaComponente=' + id +'&mesAnoReferencia=' +  document.forms[0].mesAnoReferencia.value + '&idCategoria=' + document.forms[0].idCategoria.value + '&idSubCategoria='+document.forms[0].idSubCategoria.value +'');
	} 

	function bloqueiaCampos(){
		var form = document.forms[0];
		
		if ((form.tipoParametro[0].checked == false && form.tipoParametro[1].checked == false)
			|| (form.mesAnoReferencia.value == null || form.mesAnoReferencia.value == '')
			|| (form.idCategoria.value == null || form.idCategoria.value == '' || form.idCategoria.value == '-1')) {
			desabilitaBotoes();
		}
	}
	
	function adicionar(){	
		var url = 'exibirAdicionarConsumoParametroAction.do?mesAnoReferencia=';
		
		if (document.forms[0].mesAnoReferencia.value == null
			|| document.forms[0].mesAnoReferencia.value == '') {
			alert('Informe o Mês e Ano de Referência.');
		} else {
			url = url + document.forms[0].mesAnoReferencia.value;
			
			if (document.forms[0].idCategoria.value == null
				|| document.forms[0].idCategoria.value == ''
				|| document.forms[0].idCategoria.value == '-1') {
				alert('Informe a Categoria.');
			} else {
				url = url + '&idCategoria=' + document.forms[0].idCategoria.value;
				
				if (document.forms[0].idSubCategoria.value == null
					|| document.forms[0].idSubCategoria.value == ''
					|| document.forms[0].idSubCategoria.value == -1) {
					
					url = url + '&adicionar=sim';
				} else {
					url = url + '&idSubCategoria=' + document.forms[0].idSubCategoria.value + '&adicionar=sim';
				}
			}
		}
		
		abrirPopup(url);
	}
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.mesAnoReferencia}');bloqueiaCampos();">
<html:form action="/informarConsumoParametroAction"
	name="InformarConsumoParametroActionForm"
	onsubmit="return validateInformarConsumoParametroActionForm(this);"
	type="gcom.gui.faturamento.InformarConsumoParametroActionForm" method="post">

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
					<td class="parabg">Informar Consumo por Parâmetros</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%">
				<tr>
					<td colspan="3">Para informar o consumo por parâmtros, informe os dados
					abaixo:</td>
					<td align="right"></td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td><strong>Tipo de Parâmetro:</strong><font color="#FF0000">*</font></td>
					<td colspan="3">
						<html:radio property="tipoParametro" tabindex="6"
							value="1" onchange="desabilitaBotoes()" /><strong>Pontos de Utilização</strong> 
						<html:radio property="tipoParametro" tabindex="7"
							 value="2" onchange="desabilitaBotoes()" /><strong>Número de Moradores</strong></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Mês e Ano de Referência:</strong><font color="#FF0000">*</font></td>

					<td><html:text property="mesAnoReferencia" size="7" maxlength="7"
						onkeyup="mascaraAnoMes(mesAnoReferencia, event);desabilitaBotoes();"/></td>
				</tr>

				<tr>
					<td><strong>Categoria:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idCategoria"
						onchange="javascript:reload();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoCategoria"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<tr>
					<td><strong>Subcategoria:<font color="#FF0000"></font></strong></td>
					<td><html:select property="idSubCategoria">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSubcategoria"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<tr>
					<td colspan="4" class="style1">
						<div align="right">
							<logic:empty name="colecaoConsumoMinimoParametro" scope="session">
								<input type="button" name="adicionar2"
									class="bottonRightCol" value="Consultar"
									onClick="javascript:consultarConsumoParametro(document.forms[0]);">
							</logic:empty>
							
							<logic:notEmpty name="colecaoConsumoMinimoParametro" scope="session">
								<input type="button" name="adicionar2"
									class="bottonRightCol" value="Consultar"
									onClick="javascript:confirmarConsultaConsumoParametro(document.forms[0]);">
							</logic:notEmpty>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1" colspan="2"><strong>Consumos e Parâmetros:<font
						color="#FF0000">*</font></strong></td>
					<logic:present name="adicionar" scope="request">
						<td colspan="2" class="style1">
							<div align="right"><input type="button" name="Adicionar"
								class="bottonRightCol" value="Adicionar"
								onClick="javascript:adicionar();"></div>
						</td>
					</logic:present>
					
					<logic:notPresent name="adicionar" scope="request">
						<td colspan="2" class="style1">
							<div align="right"><input type="button" name="Adicionar"
								class="bottonRightCol" value="Adicionar" disabled="true"
								onClick="javascript:adicionar();"></div>
						</td>
					</logic:notPresent>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
							<td width="9%">
							<div align="center" class="style9"><strong>Remover</strong></div>
							</td>
							<td width="34%">
							<div align="center" class="style9"><strong>Parâmetro Máximo</strong></div>
							</td>
							<td width="19%">
							<div align="center" class="style9"><strong>Consumo</strong></div>
							</td>
						</tr>
						<%String cor = "#FFFFFF";%>
						<logic:present name="colecaoConsumoMinimoParametro" scope="session">
							<%int cont = 0;%>
							<logic:iterate name="colecaoConsumoMinimoParametro"
								id="consumoMinimoParametro">
								<c:set var="count" value="${count+1}" />
								<c:choose>
									<c:when test="${count%2 == '1'}">
										<tr bgcolor="#FFFFFF">
									</c:when>
									<c:otherwise>
										<tr bgcolor="#cbe5fe">
									</c:otherwise>
								</c:choose>
								<td>
								<div align="center" class="style9"><img
									src="<bean:message key='caminho.imagens'/>Error.gif" width="14"
									height="14" style="cursor:hand;" onclick="remover('${count}');"></div>
								</td>
								<td>

								<div align="center" class="style9"><a
									href="javascript:atualizarComponente('${count}');"><bean:write
									name="consumoMinimoParametro" property="numeroParametroFinal"
									formatKey="money.format" /></a></div>
								</td>
								<td>
								<div align="center" class="style9"><bean:write
									name="consumoMinimoParametro" property="numeroConsumo"
									 /></div>
								</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="style1"></td>
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td colspan="3"><strong> <font color="#ff0000"> <!-- <input
						name="Submit22" class="bottonRightCol" value="Desfazer"
						type="button"
						onclick="window.location.href='/gsan/exibirInserirConsumoTarifaAction.do?menu=sim';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong> --></td>

					<td class="style1" align="right"><logic:present name="informar"
						scope="request">
						<input type="button" class="bottonRightCol" value="Informar" name="Informar"
							onclick="javascript:if(validateInformarConsumoParametroActionForm(document.forms[0])){submeterFormPadrao(document.forms[0]);}">
					</logic:present> <logic:notPresent name="informar" scope="request">
						<input type="button" class="bottonRightCol" value="Informar" name="Informar"
							disabled="true"
							onclick="javascript:if(validateInformarConsumoParametroActionForm(document.forms[0])){submeterFormPadrao(document.forms[0]);}">
					</logic:notPresent></td>
				</tr>
			</table>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>

<script language="JavaScript">

</script>

</html:html>
