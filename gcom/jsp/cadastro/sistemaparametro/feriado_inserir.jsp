<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="InserirFeriadoActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>	
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
<script language="JavaScript">


	 // Bloqueia Municipio
	 function bloquearMunicipio(){
		var form = document.InserirFeriadoActionForm;
		
		if(form.indicadorTipoFeriado[0].checked){
			form.idMunicipio.value="";
			form.descricaoMunicipio.value = "";
			form.idMunicipio.disabled = true;
	  		form.descricaoMunicipio.disabled = true;
		} else {
			form.idMunicipio.disabled = false;
	  		form.descricaoMunicipio.disabled = false;
		}
	}
	 
	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	    if (tipoConsulta == 'municipio') {
	    	form.idMunicipio.value = codigoRegistro;
	      	form.descricaoMunicipio.value = descricaoRegistro;
	    }
	}	
	
	// Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaMunicipio=" + tipo, altura, largura);
				}
			}
		}
	}
	 
	function limparMunicipio(){
		var form = document.forms[0];
		form.idMunicipio.value = "";
		form.descricaoMunicipio.value = "";
	}  
  
     function validarForm() {
      var form = document.forms[0];
	  if(validateInserirFeriadoActionForm(form)){	     
		  if (form.idMunicipio.disabled == false && (form.idMunicipio.value == null || form.idMunicipio.value == "")){
		  	alert('Informe Município');
		  } else {
		  	submeterFormPadrao(form); 
		  }
   	 }  		  
   	} 
   	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirFeriadoAction.do"
	name="InserirFeriadoActionForm" type="gcom.gui.cadastro.geografico.InserirFeriadoActionForm"
	method="post" onsubmit="return validateInserirFeriadoActionForm(this);">


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

			<td width="625" valign="top" class="centercoltext">

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
				<td class="parabg">Inserir Feriado Nacional ou Municipal</td>
				<td width="11" valign="top"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">Para inserir um feriado, informe os dados abaixo:</td>
			</tr>
				<tr>
					<td width="30%" class="style3"><strong>Tipo do Feriado:<font color="#FF0000">*</font></strong></td>
					<td width="70%" colspan="2" >  
						<html:radio onkeyup="javascript:bloquearMunicipio();" onclick="javascript:bloquearMunicipio();" property="indicadorTipoFeriado" value="1" tabindex="1" /><strong>Nacional
						<html:radio onkeyup="javascript:bloquearMunicipio();" onclick="javascript:bloquearMunicipio();" property="indicadorTipoFeriado" value="2" tabindex="2" />Municipal</strong>
					</td>
				</tr>
				
				<tr>
					<td width="30%" class="style3"><strong>Município:</strong></td>
					<td width="70%" colspan="2">
					
						<html:text property="idMunicipio" size="4" maxlength="4" tabindex="3"
							onkeypress="validaEnterComMensagem(event,'exibirInserirFeriadoAction.do','idMunicipio','Município');" />
					
						<a href="javascript:chamarPopup('exibirPesquisarMunicipioAction.do', 'municipio', null, null, 485, 370, '',document.forms[0].idMunicipio);">
							<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Municipio" border="0" height="21" width="23"></a> 
						
						
						<logic:present name="municipioEncontrado">
						
							<logic:equal name="municipioEncontrado" value="exception">
								<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="municipioEncontrado" value="exception">
								<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						
						</logic:present> 
					
						<logic:notPresent name="municipioEncontrado">
							<logic:empty name="InserirFeriadoActionForm" property="idMunicipio">
								<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirFeriadoActionForm" property="idMunicipio">
								<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
					
						<a 	href="javascript:limparMunicipio()"> <img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>
				
			
			 	<tr> 
	           		<td width="30%" class="style3"><strong>Data do Feriado:<font color="#FF0000">*</font></strong></td>
					<td width="70%" colspan="2">
						<html:text property="dataFeriado" size="10" maxlength="10" tabindex="4" onkeyup="mascaraData(this,event)"/>
						<a href="javascript:abrirCalendario('InserirFeriadoActionForm', 'dataFeriado')">
						<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
						dd/mm/aaaa
					</td>
	           	</tr>	
								
				<tr>
				   <td width="30%" class="style3"><strong>Descrição do Feriado:<font color="#FF0000">*</font></strong></td>
				   <td width="70%" colspan="2"><strong><b><span class="style2"> 
				  		<html:text property="descricaoFeriado" size="20" maxlength="20" tabindex="5"/> </span></b></strong></td>
				</tr>
            
           
				<table width="100%">
						<tr>
								<td height="19"><strong> <font color="#FF0000"></font></strong></td>
								
								<td align="right"><div align="left"><strong><font color="#FF0000">*</font></strong>
								Campos obrigat&oacute;rios</div>
								</td>
						</tr>
						<tr>
							  <td width="40%" align="left">				
								<input type="button" name="ButtonReset" class="bottonRightCol"
								value="Desfazer" onClick="window.location.href='/gsan/exibirInserirFeriadoAction.do?menu=sim'"> 
								
								<input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							  </td>
							  
							  <td align="right"><input type="button" name="Button" class="bottonRightCol"
									value="Inserir" onclick="validarForm();" tabindex="11"/>
							  </td>
						</tr>
				</table>
			
          </table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>

