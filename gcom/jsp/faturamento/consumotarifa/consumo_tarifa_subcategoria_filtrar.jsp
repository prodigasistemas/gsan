<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarConsumoTarifaActionForm" />
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script>

 function chamarFiltrar(){
  	var form = document.FiltrarConsumoTarifaActionForm;
  	
  	if(validateFiltrarConsumoTarifaActionForm(form)){
	    form.action = 'filtrarConsumoTarifaSubCategoriaAction.do'
	   	form.submit();  	
	}
 }

 function chamarLimpar(){
  	var form = document.FiltrarConsumoTarifaActionForm;
 	for (var i=0;i < form.elements.length;i++){
		var elemento = form.elements[i];
		if (elemento.type == "text"){
			elemento.value = "";
		}
	}  	
 }  




function reload() {
	alert("reload");
   	redirecionarSubmit("exibirInserirConsumoTarifaSubCategoriaSubCategoriaAction.do");
      
 }



 function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
	//campoOrigem.focus();
}
  
function valorCheckAtualizar(){
    var form = document.forms[0];
    if(form.atualizarFiltro.checked == true){
	    form.atualizarFiltro.value = "1";
    }else{
	    form.atualizarFiltro.value = "";    
    }
}
  
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarConsumoTarifaSubCategoriaAction"
	name="FiltrarConsumoTarifaActionForm"
	onsubmit="return validateFiltrarConsumoTarifaActionForm(this);"
	type="gcom.gui.faturamento.consumotarifa.FiltrarConsumoTarifaActionForm"
	method="post">

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
					<td class="parabg">Filtrar Tarifa de Consumo Subcategoria</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%">
				<tr>
					<td>Para manter a(s) tarifa(s) de consumo, informe os
					dados abaixo:</td>
					<td align="right">
					<html:checkbox property="atualizarFiltro" value="1" 
					onclick="javacript:valorCheckAtualizar();"/><strong>Atualizar</strong>
					</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoTarifaConsumoFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>												
				</tr>
			</table>	
			<table  width="100%">
				<tr>
					<td width="151"><strong>Descrição da Tarifa:</strong></td>
					<td width="460"><html:text maxlength="30" property="descTarifa"
						size="30" /></td>
				</tr>
				<tr>
					<td height="27"><strong> Data de Vigência:</strong></td>
					<td><html:text maxlength="10" onkeyup="mascaraData(this, event); replicaDados(document.forms[0].dataVigencia, document.forms[0].dataVigenciaFim);" onfocus="replicaDados(document.forms[0].dataVigencia, document.forms[0].dataVigenciaFim);" property="dataVigencia" size="10" />
					<a
						href="javascript:abrirCalendario('FiltrarConsumoTarifaActionForm', 'dataVigencia')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> à <html:text maxlength="10" property="dataVigenciaFim" size="10" onkeypress="mascaraData(this, event);" />
					<a
						href="javascript:abrirCalendario('FiltrarConsumoTarifaActionForm', 'dataVigenciaFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>&nbsp; dd/mm/aaaa 
					
					
					
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td>
					<div align="left"><input name="btnlimpar" class="bottonRightCol"
					    
						value="Limpar" type="button" onclick="javascript:chamarLimpar();"></div>
					</td>

					<td>
					<div align="right"><input name="btnfiltrar" class="bottonRightCol" 
  					    onclick="javascript:chamarFiltrar()" 
						value="Filtrar" type="button"></div>
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
