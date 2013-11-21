<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InformarTelefoneActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"><!--<!--

function validarForm(){

	var form = document.forms[0];
	if(validateInformarTelefoneActionForm(form) && validateCamposObrigatorios()){
		
		submeterFormPadrao(form);
	}
}

function limparMunicipio(){
	var form = document.forms[0];
	form.municipio.value = "";
	form.municipioId.value = "";
	habilitaDesabilitaDDD();
}

function validateCamposObrigatorios(){
	var form = document.forms[0];

	if(!form.telefonePrincipal[0].checked && !form.telefonePrincipal[1].checked){
		alert('Informe o indicador de Telefone Principal.');		
		return false;
	}
	 
    return true;			
}


 function habilitaDesabilitaDDD(){
	 var form = document.forms[0];

	 if ( form.municipioId.value != null && form.municipioId.value != ''  
				 && form.municipio.value != null && form.municipio.value != '' ) {
		 
		form.ddd.disabled = true;
	 } else {
		form.ddd.disabled = false;
	 }
	 
 }

function limparForm(){

	var form = document.forms[0];

	form.tipoTelefone.value = "-1";
	form.telefonePrincipal.value = "";
    form.municipioId.value = "";
    form.municipio.value;
    form.ddd.value = "";
    form.numeroTelefone.value = "";
    form.ramal.value = "";
    form.nomeContato.value = "";
	
}

function fecharPop(clienteFone){

	if(clienteFone!=''){
		opener.reload();
		self.close();
	}
}
 
</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(585, 435); habilitaDesabilitaDDD();fecharPop('${sessionScope.clienteFoneAdicionar}');" >
<html:form action="/informarTelefoneAction.do" method="post">
	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="550" valign="top" class="centercoltext">
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
					<td class="parabg">Informar Telefone</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha os campos para informar um telefone</td>
				</tr>

				<tr>
					<td width="40%">
							<strong>Tipo Telefone: <font color="#FF0000">*</font></strong>
					</td>
					<td width="60%">
						<html:select property="tipoTelefone" tabindex="1" style="width:200px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="tiposTelefone" labelProperty="descricao" property="id" />
						</html:select>						
					</td>
				</tr>

				<tr>
					<td ><strong>Telefone Principal: <font color="#FF0000">*</font></strong></td>

					<td>
					    <strong>
					        <html:radio property="telefonePrincipal" value="1"/>					       
							Sim 
							<html:radio property="telefonePrincipal" value="2"/>
							Não  
					    </strong>
					</td>
				</tr>
				
				<tr>
				    <td ><strong>Munic&iacute;pio:</strong></td>
						<td width="76%">
							<html:text maxlength="4" property="municipioId" size="4" tabindex="2"
								onkeypress="validaEnterComMensagem(event, 'exibirInformarTelefoneAction.do?objetoConsulta=1','municipioId','Município');
							            return isCampoNumerico(event);"/>
								
							<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do?caminhoRetornoTelaPesquisaMunicipio=exibirInformarTelefoneAction',400,800);">
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Municipio" /></a>
									
							<logic:equal name="municipioEncontrado" scope="request" value="true">
								<html:text maxlength="30" property="municipio"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="30" />
							</logic:equal> 
							<logic:notEqual name="municipioEncontrado" scope="request" value="true">
								<html:text maxlength="30" property="municipio"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="30" />
							</logic:notEqual> 
							<a href="javascript:limparMunicipio();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"></a>
						</td>
				</tr>
				
				<tr>
					<td >
						<strong>DDD: <font color="#FF0000">*</font></strong>
					</td>
					<td>
					    <html:text maxlength="2" property="ddd" size="2" tabindex="3"
                                   onkeypress="validaEnterComMensagem(event, 'exibirInformarTelefoneAction.do?objetoConsulta=2','ddd','Município');
							            return isCampoNumerico(event);"/>
					</td>
				</tr>
				
				<tr>
						<td>
							<strong>N&uacute;mero do Telefone: <font color="#FF0000">*</font></strong>
						</td>
						<td>
							<html:text maxlength="9" property="numeroTelefone" size="9" tabindex="4" onkeypress="return isCampoNumerico(event);"/>
						</td>
					</tr>
					<tr>
						<td>
							<strong>Ramal:</strong>
						</td>
						<td>
							<html:text maxlength="4" property="ramal" size="4" tabindex="5" onkeypress="return isCampoNumerico(event);"/>
						</td>
					</tr>
					<tr>
						<td>
							<strong>Nome do Contato:</strong>
						</td>
						<td>
							<html:text maxlength="50" property="nomeContato" size="50" tabindex="5"/>
						</td>
					</tr>
				
				

				<tr>	
					<td align="left" height="24" colspan="2">
					    <input type="button" name="ButtonVoltar" class="bottonRightCol" value="Voltar"
						    onClick="window.history.go(-1)">
						<input type="button" name="Button" class="bottonRightCol"
							value="Limpar" onClick="javascript:limparForm()" />
					</td>
					
					<td>&nbsp;</td>
					
					<td align="right" height="24"><input type="button" name="Button" class="bottonRightCol"
							value="Adicionar" onClick="javascript:validarForm()" />
					</td>
					
					<td>&nbsp;</td>
				</tr>
				

			</table>
			</td>
		</tr>
		
		
	</table>
	<p>&nbsp;</p>
	
	
</html:form>
</body>
</html:html>