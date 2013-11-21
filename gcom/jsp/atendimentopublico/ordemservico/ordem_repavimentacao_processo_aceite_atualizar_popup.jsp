<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
    <html:javascript staticJavascript="false"  formName="ConjuntoTramitacaoRaActionForm"/>		
<script language="JavaScript">

 
function desfazer(){
var form = document.forms[0];
    form.dataAceite.value = ""; 
    form.indicadorSituacaoAceite[0].checked = false;
    form.indicadorSituacaoAceite[1].checked = false;
    form.motivo.value = "";		
}
  	
function DateValidations () { 
     this.aa = new Array("dataAceite", "Data de Aceite não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
} 	
	
 function validateFiltrarOrdemRepavimentacaoProcessoAceiteActionForm() { 
     var form = document.forms[0];       

     if ( form.dataAceite.value != null && form.dataAceite.value != "" ) {
      	 
     	 if(validateDate(form) && validateCaracterEspecial(form)){
     		 form.action = 'atualizarOrdemRepavimentacaoProcessoAceitePopUpAction.do?page.offset='+ document.forms[0].manterPaginaAux.value; 
  			 submeterFormPadrao(form);
		 }
     }else {
    	alert("Campos obrigatórios não informados");
  	}   
 } 	
 
 function caracteresespeciais () {

     this.aa = new Array("motivo", "Motivo possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 }
 
		
</script>

</head>

<logic:notPresent name="fecharPopup" scope="request">
	<body leftmargin="5" topmargin="5"
			onload="javascript:setarFoco('${requestScope.nomeCampo}');resizePageSemLink(700, 400);">
</logic:notPresent>
<logic:present name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirManterOrdemRepavimentacaoProcessoAceiteAction.do');window.close()">
</logic:present>

<html:form action="/atualizarOrdemRepavimentacaoProcessoAceitePopUpAction.do"
	name="FiltrarOrdemRepavimentacaoProcessoAceiteActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarOrdemRepavimentacaoProcessoAceiteActionForm"
	method="post">
	
		<html:hidden property="manterPaginaAux" />
		
	<table width="600" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Para efetuar o aceite da(s) ordem(ns) de repavimentação, informe os dados abaixo: </td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Dados do Aceite da(s) Ordem(ns) de Repavimentação</td>
				</tr>							
			</table>
			<table width="100%" border="0">
				
				<tr>
					<td><strong>Situação do Aceite:</strong><font color="#ff0000">*</font></td>
					<td colspan="2">
					<html:radio property="indicadorSituacaoAceite" tabindex="2" value="1" /><strong>Aceita</strong>
					<html:radio property="indicadorSituacaoAceite" tabindex="3" value="2" /><strong>Não Aceita</strong>
				</tr>
				
				<tr>		
					<td><strong>Data do Aceite:</strong><font color="#ff0000">*</font></td>
					<td width="66%">
					 <html:text property="dataAceite" 	
					 			tabindex="3" 
					 			size="10" 
					 			maxlength="10" 
					 			onkeyup="mascaraData(this, event)" 
					 			onkeypress="return isCampoNumerico(event);"/>
					  <a	href="javascript:abrirCalendario('FiltrarOrdemRepavimentacaoProcessoAceiteActionForm', 'dataAceite');" >
					    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/>
					  </a>  

					</td>
				</tr>
				
				<tr>		
					<td><strong>Motivo:</strong></td>
					<td width="66%">
						<html:textarea
										property="motivo" 
										cols="40" 
										rows="5"/>
					  			  

					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="6">
					<div align="right"></div>
					</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol" value="Voltar" align="left" onclick="window.close();" >
                    	<input type="button" class="bottonRightCol" value="Limpar"onClick="javascript:desfazer();">
                    </td>
					<td width="66%" align="right">							
						<input type="button" class="bottonRightCol" value="Atualizar" onClick="javascript:validateFiltrarOrdemRepavimentacaoProcessoAceiteActionForm();">
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>