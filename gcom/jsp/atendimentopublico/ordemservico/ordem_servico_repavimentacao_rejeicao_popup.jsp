<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.gui.atendimentopublico.ordemservico.AtualizarOrdemProcessoRepavimentacaoPopUpActionForm"%>
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
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
    <html:javascript staticJavascript="false"  formName="ConjuntoTramitacaoRaActionForm"/>		
<script language="JavaScript">

  	
function DateValidations () { 
     this.aa = new Array("dataRejeicao", "Data da Rejeição não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
} 	 	
	
function validateAlterarOrdemProcessoRepavimentacaoPopUpActionForm(){
	var form = document.forms[0];       
    
    if (form.dataRejeicao.value != '' && form.idMotivoRejeicao.value != '-1'){
		if(validaData(form.dataRejeicao)){
		
			if(form.idMotivoObrigatorio != null && form.idMotivoRejeicao.value == form.idMotivoObrigatorio.value && form.descricaoRejeicao.value == ''){
	  			alert('Informe a Descrição da Rejeição.');
	  		}else{
				if (comparaData(form.dataRejeicao.value, "<", form.dataAtual.value )){
	  				submeterFormPadrao(form);
	  			}else{
	  				alert('Data do Retorno maior que a data corrente.');			
	  			}	
  			}
  		}
  	}else{
  		if(form.dataRejeicao.value == ''){
  			alert('Informe a Data da Rejeição.');
  		}
  		
  		if(form.idMotivoRejeicao.value == '-1'){
  			alert('Informe o Motivo da Rejeição.');
  		}
  		
  		if(form.idMotivoRejeicao.value == '1' && form.descricaoRejeicao.value == ''){
  			alert('Informe a Descrição da Rejeição.');
  		}
  	}
}

function voltar(){
	var form = document.forms[0]; 
	
	form.action = 'exibirAtualizarOrdemProcessoRepavimentacaoPopupAction.do';
	form.submit();
}
 		
</script>

</head>

<logic:notPresent name="fecharPopup">
	<body leftmargin="5" topmargin="5"
			onload="javascript:setarFoco('${requestScope.nomeCampo}');resizePageSemLink(640, 420);">
</logic:notPresent>
<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirManterOrdemProcessoRepavimentacaoAction.do?retornaDoPopup=1&page.offset='+ document.forms[0].manterPaginaAux.value);window.close()">
</logic:present>

<html:form action="/rejeitarOrdemProcessoRepavimentacaoPopupAction.do"
	name="AtualizarOrdemProcessoRepavimentacaoPopUpActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarOrdemProcessoRepavimentacaoPopUpActionForm"
	method="post">
	
	<html:hidden property="manterPaginaAux" />
	<html:hidden property="dataAtual" />
	
	<html:hidden property="idMotivoObrigatorio" value="${requestScope.idMotivoObrigatorio}"/>

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
					<td class="parabg">Atualizar Processo de Repavimentação - Rejeição</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para rejeição do serviço de repavimentação, informe os dados abaixo:</td>
				</tr>							
			</table>
			<table width="100%" border="0">
				<tr>		
					<td width="25%"><strong>Data da Rejeição:</strong><font color="#ff0000">*</font></td>
					<td width="30%">
						<html:text property="dataRejeicao" 	
					 			   tabindex="11" size="10" 
					 		 	   maxlength="10" onkeyup="mascaraData(this, event)" />
					  	<a href="javascript:abrirCalendario('AtualizarOrdemProcessoRepavimentacaoPopUpActionForm', 'dataRejeicao');" >
					    	<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>  
					</td>
				</tr>
				
				<tr>
					<td width="120" ><strong>Motivo da Rejeição:</strong><font color="#ff0000">*</font></td>
					<td colspan="2">
						<html:select property="idMotivoRejeicao" 
									 tabindex="1" style="width: 280px;" >
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoMotivoRejeicao"
										  labelProperty="descricao" 
										  property="id" />
						</html:select> 
						<font size="1">&nbsp; </font>
					</td>
				</tr>
				
				<tr>
					<td width="15%"><strong>Descrição da Rejeição:<font color="#FF0000"></font></strong></td>
					<td width="85%" colspan="2">
						<html:textarea property="descricaoRejeicao" cols="40" rows="5" /><br>		
					</td>
				</tr>

				<tr>
					<td width="25%">&nbsp;</td>
				</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
						<input name="Button" type="button" 
							   class="bottonRightCol" value="Voltar" 
							   align="left" onclick="window.close();" >
						
                    </td>
                    
                    <td colspan="1" align="right">
						<input  type="button" 
								class="bottonRightCol" 
							    value="Rejeitar" 
							    onClick="javascript:validateAlterarOrdemProcessoRepavimentacaoPopUpActionForm();">
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