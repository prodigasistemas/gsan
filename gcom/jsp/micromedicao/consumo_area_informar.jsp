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
	formName="InformarConsumoAreaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">



function consultarConsumoArea(form){
			if(testarCampoValorZero(document.InformarConsumoAreaActionForm.mesAnoReferencia, 'Mês e Ano de Referência') && 
			testarCampoValorZero(document.InformarConsumoAreaActionForm.categoria, 'Categoria') && 
			testarCampoValorZero(document.InformarConsumoAreaActionForm.subCategoria, 'Subcategoria')) {

			if(validateInformarConsumoAreaActionForm(form)){
					redirecionarSubmit("exibirInformarConsumoAreaAction.do?consultar=sim");
			}
		}

}


	function confirmarConsultaConsumoArea(form){
			if(testarCampoValorZero(document.InformarConsumoAreaActionForm.mesAnoReferencia, 'Mês e Ano de Referência') && 
			testarCampoValorZero(document.InformarConsumoAreaActionForm.categoria, 'Categoria') && 
			testarCampoValorZero(document.InformarConsumoAreaActionForm.subCategoria, 'Subcategoria')) {
			var where_to= confirm("Os dados informados serão perdidos. Deseja Continuar ?");
				if(validateInformarConsumoAreaActionForm(form)){
					if (where_to== true) {
						redirecionarSubmit("exibirInformarConsumoAreaAction.do?consultar=sim");
					}
				}
			}
	}			


function validarForm(form){

		if(testarCampoValorZero(document.InformarConsumoAreaActionForm.mesAnoReferencia, 'Mês e Ano de Referência') && 
			testarCampoValorZero(document.InformarConsumoAreaActionForm.categoria, 'Categoria') && 
			testarCampoValorZero(document.InformarConsumoAreaActionForm.subCategoria, 'Subcategoria')) {

			if(validateInformarConsumoAreaActionForm(form)){
	    		form.submit();
			}
		}
	}
	

	
	
function desabilitaBotoes(){
	var form = document.InformarConsumoAreaActionForm;

	form.Adicionar.disabled=true;
	form.Informar.disabled=true;

}		
	
	
/* Remove uma area e consumo */	
	function remover(id){
		var form = document.InformarConsumoAreaActionForm;
		var where_to= confirm("Deseja realmente remover esta área e consumo ?");
		if (where_to== true) {
		    form.action='exibirInformarConsumoAreaAction.do?remover='+id;
		    form.submit();
 		}
	}	


function reload() {
   	redirecionarSubmit("exibirInformarConsumoAreaAction.do?reload=sim");
      
 }
 
 
 
function atualizarComponente(id) {
		abrirPopup('exibirAdicionarConsumoAreaAction.do?atualizarComponente=sim&atualizaComponente=' + id +'&mesAnoReferencia=' +  document.forms[0].mesAnoReferencia.value + '&categoria=' + document.forms[0].categoria.value + '&subCategoria='+document.forms[0].subCategoria.value +'');
	} 
 
 
  
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.mesAnoReferencia}');">
<html:form action="/informarConsumoAreaAction"
	name="InformarConsumoAreaActionForm"
	onsubmit="return validateInformarConsumoAreaActionForm(this);"
	type="gcom.gui.faturamento.InformarConsumoAreaActionForm" method="post">

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
					<td class="parabg">Inserir Consumo por Área</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%">
				<tr>
					<td colspan="3">Para informar o consumo por área, informe os dados
					abaixo:</td>
					<td align="right"></td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td><strong>Mês e Ano de Referência:</strong><font color="#FF0000">*</font></td>

					<td><html:text property="mesAnoReferencia" size="7" maxlength="7"
						onkeyup="mascaraAnoMes(mesAnoReferencia, event);desabilitaBotoes();"/></td>
				</tr>

				<tr>
					<td><strong>Categoria:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="categoria"
						onchange="javascript:reload();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoCategoria"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<tr>
					<td><strong>Subcategoria:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="subCategoria">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSubcategoria"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<tr>
					<td colspan="2" class="style1"></td>
					<td colspan="2" class="style1">
					<div align="right">
					<logic:empty name="colecaoConsumoMinimoArea" scope="session">
					<input type="button" name="adicionar2"
						class="bottonRightCol" value="Consultar"
						onClick="javascript:consultarConsumoArea(document.forms[0]);"></logic:empty>
						<logic:notEmpty name="colecaoConsumoMinimoArea" scope="session">
					<input type="button" name="adicionar2"
						class="bottonRightCol" value="Consultar"
						onClick="javascript:confirmarConsultaConsumoArea(document.forms[0]);"></logic:notEmpty></div>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="style1">
					<hr>
					</td>
				</tr>
				<tr>
					<td class="style1" colspan="2"><strong>Consumos e Áreas:<font
						color="#FF0000">*</font></strong></td>
					<logic:present name="adicionar" scope="request">
						<td colspan="2" class="style1">
						<div align="right"><input type="button" name="Adicionar"
							class="bottonRightCol" value="Adicionar"
							onClick="javascript:abrirPopup('exibirAdicionarConsumoAreaAction.do?mesAnoReferencia='+ document.forms[0].mesAnoReferencia.value +'&categoria=' + document.forms[0].categoria.value + '&subCategoria=' + document.forms[0].subCategoria.value + '&adicionar=sim' );"></div>
						</td>

					</logic:present>
					<logic:notPresent name="adicionar" scope="request">
						<td colspan="2" class="style1">
						<div align="right"><input type="button" name="Adicionar"
							class="bottonRightCol" value="Adicionar" disabled="true"
							onClick="javascript:abrirPopup('exibirAdicionarConsumoAreaAction.do');"></div>
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
							<div align="center" class="style9"><strong>Área Máxima</strong></div>
							</td>
							<td width="19%">
							<div align="center" class="style9"><strong>Consumo</strong></div>
							</td>
						</tr>
						<%String cor = "#FFFFFF";%>
						<logic:present name="colecaoConsumoMinimoArea" scope="session">
							<%int cont = 0;%>
							<logic:iterate name="colecaoConsumoMinimoArea"
								id="consumoMinimoArea">
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
									name="consumoMinimoArea" property="numeroAreaFinal"
									formatKey="money.format" /></a></div>
								</td>
								<td>
								<div align="center" class="style9"><bean:write
									name="consumoMinimoArea" property="numeroConsumo"
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
							onclick="javascript:if(validateInformarConsumoAreaActionForm(document.forms[0])){submeterFormPadrao(document.forms[0]);}">
					</logic:present> <logic:notPresent name="informar" scope="request">
						<input type="button" class="bottonRightCol" value="Informar" name="Informar"
							disabled="true"
							onclick="javascript:if(validateInformarConsumoAreaActionForm(document.forms[0])){submeterFormPadrao(document.forms[0]);}">
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
