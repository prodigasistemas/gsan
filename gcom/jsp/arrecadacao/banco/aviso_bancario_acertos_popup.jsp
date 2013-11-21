<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript"src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!--
function enviar(){

	if (trim(document.forms[0].idContaBancaria.value) == '') { 
		alert('Informe Conta Bancaria.');
		return;
	}
	if (trim(document.forms[0].tipoAcerto.value) == '') { 
		alert('Informe Tipo do Acerto.');
		return;
	}
	if (trim(document.forms[0].acertar.value) == '') { 
		alert('Informe Acertar.');
		return;
	}
	if (trim(document.forms[0].dataAcerto.value) == '') { 
		alert('Informe Data do Acerto.');
		return;
	}	
	if (trim(document.forms[0].valorAcerto.value) == '') { 
		alert('Informe Valor do Acerto.');
		return;
	}
	if (verificaDataMensagemPersonalizada(document.forms[0].dataAcerto,'Data do Acerto inválida.'))
	{	
		opener.recuperarDadosAcertos(
		document.forms[0].idContaBancaria.value,  
		document.forms[0].tipoAcerto.value, 
		document.forms[0].acertar.value,  
		document.forms[0].dataAcerto.value, 
		document.forms[0].valorAcerto.value);
		self.close();
   	}
   	
}
/*function fecharPopup(){
		document.forms[0].valorAcerto.value);
	opener.recuperarDadosAcertos(
		document.forms[0].idContaBancaria.value,  
		document.forms[0].tipoAcerto.value,  
		document.forms[0].dataAcerto.value, 
		document.forms[0].valorAcerto.value);
		document.forms[0].action = 'ExibirAtualizarAvisoBancarioAction.do';
		document.forms[0].submit();
	self.close();
}*/

function limparContaBancaria() {
	document.forms[0].idContaBancaria.value = '';
	document.forms[0].nomeBanco.value = '';
	document.forms[0].nomeAgencia.value = '';
	document.forms[0].numeroContaBancaria.value = '';
}

function preencherContaBancaria(idContaBancaria , nomeBanco, nomeAgencia, numeroContaBancaria ) {
	document.forms[0].idContaBancaria.value = idContaBancaria;
	document.forms[0].nomeBanco.value = nomeBanco;
	document.forms[0].nomeAgencia.value = nomeAgencia;
	document.forms[0].numeroContaBancaria.value = idContaBancaria;
}
 
function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3 ,tipoConsulta) {
    if (tipoConsulta == 'contaBancaria') {
      document.forms[0].nomeBanco.value = descricaoRegistro1;
      document.forms[0].nomeAgencia.value = descricaoRegistro2;
      document.forms[0].numeroContaBancaria.value = descricaoRegistro3;
      document.forms[0].idContaBancaria.value = codigoRegistro;
    }
}

//-->
</script>
</head>

<body leftmargin="0" topmargin="0" onload="window.focus();">
<form name="form1" >
<%--<html:form action="/ExibirPesquisarAvisoAcertoAction.do"
	name="AvisoBancarioActionForm"
	type="gcom.gui.arrecadacao.banco.AvisoBancarioActionForm"
	method="post">--%>
<input type="hidden" name="acao">
<table width="543" border="0" cellpadding="0" cellspacing="5">
  <tr> 
    <td width="533" valign="top" class="centercoltext"> <table height="100%">

        <tr> 
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="12"><img src="imagens/parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td width="*" class="parabg">Adicionar Acertos do Aviso Banc&aacute;rio </td>
          <td width="12"><img src="imagens/parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>

        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr> 
          <td colspan="4">Preencha os campos para inserir um acerto no aviso banc&aacute;rio:</td>
        </tr>
        <tr> 
          <td width="29%" height="24"><strong>Conta Banc&aacute;ria:<font color="#FF0000">*</font></strong></td>

          <td colspan="3">
            <logic:present scope="request" name="idContaBancaria">
          	  <input type="hidden" name="idContaBancaria" value="${requestScope.idContaBancaria}">	
          	  <input name="nomeBanco" type="text" size="3" value="${requestScope.nomeBanco}" maxlength="3" disabled style="background-color:#EFEFEF; border:0; font-color: #000000"/> 
              <input name="nomeAgencia" type="text" size="9" value="${requestScope.nomeAgencia}" maxlength="9" disabled style="background-color:#EFEFEF; border:0; font-color: #000000"/> 
              <input name="numeroContaBancaria" type="text" size="20"  value="${requestScope.numeroContaBancaria}" maxlength="20" disabled style="background-color:#EFEFEF; border:0; font-color: #000000"/> 
            </logic:present>
          
            <logic:notPresent scope="request" name="idContaBancaria">
          	  <input type="hidden" name="idContaBancaria">	
          	  <input name="nomeBanco" type="text" size="3" maxlength="3" disabled style="background-color:#EFEFEF; border:0; font-color: #000000"/> 
              <input name="nomeAgencia" type="text" size="9" maxlength="9" disabled style="background-color:#EFEFEF; border:0; font-color: #000000"/> 
              <input name="numeroContaBancaria" type="text" size="20" maxlength="20" disabled style="background-color:#EFEFEF; border:0; font-color: #000000"/> 
            </logic:notPresent>                  
           
            <a href="javascript:abrirPopupDeNome('contaBancariaPesquisarAction.do?limpar=sim', 285, 565,'numeroBancario','no');" >
				<img  border="0" src="imagens/pesquisa.gif" width="23" height="21" ></a>
            <a href="javascript:limparContaBancaria();">
            	<img border="0" src="imagens/limparcampo.gif" width="23" height="21">
			</a>
          </td>
        </tr>
        <tr> 
          <td height="24"><strong>Acertar:<font color="#FF0000">*</font></strong></td>
          <td colspan="3"><span class="style1"><strong> 
            <input type="radio" onclick="javascript:document.forms[0].acertar.value = 1;" checked name="radio1" value="1">
            <strong>Arrecadação 
            <input type="radio" onclick="javascript:document.forms[0].acertar.value = 2;" name="radio1" value="2">
            <input type="hidden" name="acertar" value="1">
            Devolução</strong></strong></span></td>
        </tr>
        <tr> 
          <td height="24"><strong>Tipo do Acerto:<font color="#FF0000">*</font></strong></td>
          <td colspan="3"><span class="style1"><strong> 
            <input type="radio" onclick="javascript:document.forms[0].tipoAcerto.value = 1;" checked name="radio" value="1">
            <strong>Cr&eacute;dito 
            <input type="radio" onclick="javascript:document.forms[0].tipoAcerto.value = 2;" name="radio" value="2">
            <input type="hidden" name="tipoAcerto" value="1">
            D&eacute;bito</strong><strong></strong></strong></span> </td>
        </tr>
        <tr> 
          <td height="24"><strong>Data do Acerto:<font color="#FF0000">*</font></strong></td>
          <td colspan="3"><input onkeyup="mascaraData(this, event);" name="dataAcerto" type="text" size="10" maxlength="10"> 
			  <a href="javascript:abrirCalendario('form1', 'dataAcerto')">
				<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
				width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
			 </a></td>
        </tr>
        <tr> 
          <td height="24"><strong>Valor do Acerto:<font color="#FF0000">*</font></strong></td>
          <td colspan="3"><input name="valorAcerto" type="text"  onkeyup="formataValorMonetario(this, 14)" style="text-align:right;" size="14" maxlength="14"> 
          </td>
        </tr>
        <tr>
          <td height="24">&nbsp;</td>
          <td colspan="3"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>

        </tr>
        <tr> 
          <td height="27" colspan="4"> <div align="right"> 
          	
              <input name="Button" type="button" class="bottonRightCol" value="Inserir" onClick="javascript:enviar(document.forms[0]);">
              <input name="Button2" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:window.close();">
            </div></td>
        </tr>
      </table>
      <p>&nbsp;</p></td>

  </tr>
</table>
</form>
<%--<logic:present name="fecharPopup" scope="request">
<script language="JavaScript">
	fecharPopup();
</script>
</logic:present>--%>
</body>
</html:html>