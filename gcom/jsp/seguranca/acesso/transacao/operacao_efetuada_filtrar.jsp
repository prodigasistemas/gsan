<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<html:javascript staticJavascript="false"  formName="FiltrarOperacaoEfetuadaActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script>

 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
 	if ('funcionalidade' == tipoConsulta) {
	 	document.forms[0].idFuncionalidade.value = codigoRegistro;
	 	document.forms[0].nomeFuncionalidade.value = descricaoRegistro;
	 	document.forms[0].action = 'ExibirFiltrarOperacaoEfetuadaAction.do';
 		document.forms[0].submit();
 	} else if ('usuario' == tipoConsulta) {
	 	document.forms[0].idUsuario.value = codigoRegistro;
	 	document.forms[0].nomeUsuario.value = descricaoRegistro;
 	} else { 	
	 	document.forms[0].valorArgumentoPesquisa.value = codigoRegistro;
// 		document.forms[0].nomeArgumentoPesquisa.value = descricaoRegistro;
 	}
 }


 function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta){
 	document.forms[0].valorArgumentoPesquisa.value = codigoRegistro;
 	document.forms[0].nomeArgumentoPesquisa.value = descricaoRegistro1;
 }


 function chamarFiltrar() {
 
 	document.forms[0].valorArgumentoPesquisa.value = trim(document.forms[0].valorArgumentoPesquisa.value);
 
	if (document.forms[0].valorArgumentoPesquisa.value != '' && isNaN(document.forms[0].valorArgumentoPesquisa.value)) {
		alert('O valor do Argumento de Pesquisa de conter apenas Numeros.');
	}

	if (document.forms[0].dataInicial.value != '' && document.forms[0].dataFinal.value == '') {
		 document.forms[0].dataFinal.value = document.forms[0].dataInicial.value;
	}
	 
	if (document.forms[0].horaInicial.value != '' && document.forms[0].horaFinal.value == '') {
		 document.forms[0].horaFinal.value = document.forms[0].horaInicial.value;
	}
	
/*	if (document.forms[0].idFuncionalidade.value == '' && 
		document.forms[0].idUsuario.value == ''){
		alert('É necessário preencher funcionalidade ou usuário');	
		return;
	} else {
*/	
		if (!(testarCampoValorZero(document.forms[0].idFuncionalidade, 'Funcionalidade') &&
			testarCampoValorZero(document.forms[0].idUsuario, 'Usuário') &&
			validateCaracterEspecial(document.forms[0]) && validateLong(document.forms[0])
			&& validateDate(document.forms[0]))){
           return;
        }
	
//	}
	if (document.forms[0].idFuncionalidade.value != '') {
	   	if (!verificarOperacoes()){
			return;
		}
	} else {
   	    var resposta = confirm('Confirma a pesquisa sobre todas as operações?');
		if (!resposta){
			return false;
		}
	}
	document.forms[0].submit();
 }
 
    function caracteresespeciais () {
     this.aa = new Array("idFuncionalidade", "Funcionalidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idUsuario", "Usuário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }


    function IntegerValidations () {
     this.aa = new Array("idFuncionalidade", "Funcionalidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function DateValidations () { 
     this.aa = new Array("dataInicial", "Data Inicial inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ab = new Array("dataFinal", "Data Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }
 
 function limparFuncionalidade() {
    var form = document.forms[0]; 
 	form.nomeFuncionalidade.value = '';
 	form.idFuncionalidade.value = '';
 	form.funcionalidadeLimpa.value='1';                  	
 	limparArgumentoPesquisa();
 	if (form.operacoes != null) {
	 	form.action = 'ExibirFiltrarOperacaoEfetuadaAction.do';
	 	form.submit();
	}
	form.operacoes = null;
 }
 
 function limparUsuario() {
 	document.forms[0].nomeUsuario.value = '';
 	document.forms[0].idUsuario.value = '';
 	document.forms[0].usuarioLimpo.value='1';                  
 }
 function limparTabela() {
 	document.forms[0].nomeTabela.value = '';
 	document.forms[0].idTabela.value = '';
 	document.forms[0].action = 'ExibirFiltrarOperacaoEfetuadaAction.do';
 	document.forms[0].submit();
 }
 
 function dataEstahLimpa(){
 	var dataInicial = document.forms[0].dataInicial.value;
 	var dataFinal = document.forms[0].dataFinal.value;
 	if (dataInicial == '' || dataFinal == ''){
 	    document.forms[0].dataLimpa.value = '1';
 	} else {
    	document.forms[0].dataLimpa.value = '0';
 	}
 }
 
 function horaEstahLimpa(){
 	var horaInicial = document.forms[0].horaInicial.value;
 	var horaFinal = document.forms[0].horaFinal.value;
 	if (horaInicial == '' || horaFinal == ''){
 	    document.forms[0].horaLimpa.value = '1';
 	} else {
    	document.forms[0].horaLimpa.value = '0';
 	}
 }

 function argumentoPesquisaEstahLimpo() {
 	if (document.forms[0].valorArgumentoPesquisa.value == ''){
      document.forms[0].argumentoLimpo.value = '1';
 	} else {
      document.forms[0].argumentoLimpo.value = '0';
    }
 }
 
 function limparArgumentoPesquisa(){
   document.forms[0].valorArgumentoPesquisa.value = '';
   document.forms[0].argumentoLimpo.value = '1';
 }

 function verificarOperacoes(){
   var form = document.forms[0];
   if (form.operacoes == null || form.operacoes.length == 0) {
	   if (document.forms[0].valorArgumentoPesquisa.value == ''){
	       alert('Operação não disponível');
    	   return false;	   	   
	   } 
   }
   if (form.operacoes.value == '' && form.operacoes.options != null){
       for(i = 0; i < form.operacoes.options.length; i++){
         form.operacoes.options[i].selected = true;
       }
   }
   return true;   
 }

  function limparFiltrar(){
    limparArgumentoPesquisa();
    limparUsuario();
 	document.forms[0].dataInicial.value = '';
 	document.forms[0].dataFinal.value = '';
 	document.forms[0].horaInicial.value = '';
 	document.forms[0].horaFinal.value = '';
 	document.forms[0].dataLimpa.value = '1';
 	document.forms[0].horaLimpa.value = '1';
 	document.forms[0].unidadeNegocio.value= '-1'; 	
    limparFuncionalidade();
  }

  function salvarNomeOperacaoUnica(){
    
    var oper = document.forms[0].operacoes;
    if (oper != null && oper.selectedIndex != -1){
      document.forms[0].nomeOperacaoSelecionada.value = oper.options[oper.selectedIndex].text;
    }
  
  }
  
  </script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/FiltrarOperacaoEfetuadaAction" method="post" onsubmit="return validateFiltrarOperacaoEfetuadaActionForm(this);" >

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="150" valign="top" class="leftcoltext">
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
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Filtrar Operação</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
            <table border="0" width="100%">
                <tbody><tr> 
                  <td colspan="2">Para filtrar a(s) operação(ões), 
                    informe os dados abaixo:</td>

                </tr>
                <tr> 
                  <td width="26%"><strong>Funcionalidade:</strong></td>
                  <td width="74%">
					<html:text maxlength="9" 
						name="FiltrarOperacaoEfetuadaActionForm" 
						property="idFuncionalidade" 
						size="9" 
						onkeypress="javascript: validaEnter(event, 'ExibirFiltrarOperacaoEfetuadaAction.do', 'idFuncionalidade'); return isCampoNumerico(event); "/>
					
						<input type="hidden" name="funcionalidadeLimpa" value="0">
					 	<img onclick="abrirPopup('exibirPesquisarFuncionalidadeAction.do?registraTransacao=true',250, 495);" 
					 		width="23" 
					 		height="21" 
					 		src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
					 		border="0"
					 		title="Pesquisar Funcionalidade" />	
						
						
						

   		      		<logic:equal property="funcionalidadeNaoEncontrada" name="FiltrarOperacaoEfetuadaActionForm" value="true">
						<input type="text" name="nomeFuncionalidade" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="atencao.funcionalidade.inexistente"/>"/>
                    </logic:equal>
   		      		<logic:equal property="funcionalidadeNaoEncontrada" name="FiltrarOperacaoEfetuadaActionForm" value="false">
						<html:text name="FiltrarOperacaoEfetuadaActionForm" property="nomeFuncionalidade" size="42" maxlength="42" readonly="true" style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
                    </logic:equal>
                     
                    <a href="javascript:limparFuncionalidade();">
                    	<img  border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar"> 
                    </a>
                  </td>
                </tr>
                <tr> 
                  <td width="26%"><strong>Operações:</strong></td>
                  <td width="74%">
                  <input type="hidden" name="nomeOperacaoSelecionada" value="<%=session.getAttribute("nomeOperacaoSelecionada") == null ? "" : session.getAttribute("nomeOperacaoSelecionada")%>">
                  <logic:present name="operacoes">
					<html:select property="operacoes" tabindex="4"
						style="width:200px;" multiple="" onchange="javascript:salvarNomeOperacaoUnica()">
						<html:options collection="operacoes"
							labelProperty="descricao" property="id" />
					</html:select>
					</logic:present>
					<logic:notPresent name="operacoes">
						<select><option></option></select>
					</logic:notPresent>
                  </td>
                </tr>
                <tr> 
                  <td><strong>Usuário:</strong></td>
                  <td>
				    <input type="hidden" name="usuarioLimpo" value="0">
					<html:text maxlength="11"  name="FiltrarOperacaoEfetuadaActionForm" property="idUsuario" size="11"
					onkeypress="javascript: validaEnterStringSemUpperCase(event, 'ExibirFiltrarOperacaoEfetuadaAction.do', 'idUsuario');
					 return isCampoNumerico(event);" style="text-transform: none;"/>
                    <img onclick="abrirPopup('exibirUsuarioPesquisar.do', 250, 495);" width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
                     title="Pesquisar Usu&aacute;rio" />
   		      		<logic:equal property="usuarioNaoEncontrada" name="FiltrarOperacaoEfetuadaActionForm" value="true">
						<input type="text" name="nomeUsuario" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="pesquisa.usuario.inexistente"/>"/>
                    </logic:equal>
   		      		<logic:equal property="usuarioNaoEncontrada" name="FiltrarOperacaoEfetuadaActionForm" value="false">
						<html:text name="FiltrarOperacaoEfetuadaActionForm" property="nomeUsuario" size="40" maxlength="50" readonly="true" style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
                    </logic:equal>
                     
                    <a href="javascript:limparUsuario();">
                    	<img  border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar" /> 
                    </a>
                    </td>
                </tr>
	                <tr> 
	                  <td><strong>Período de Realização:</strong></td>
                  <td> 
                    <input type="hidden" name="dataLimpa" value="0">
                  	<html:text name="FiltrarOperacaoEfetuadaActionForm" onkeyup="mascaraData(this, event);/*replicarCampo(document.forms[0].dataFinal,this);*/"
                  	 onblur="javascript:dataEstahLimpa()"  property="dataInicial" size="10" maxlength="10"
                  	 onkeypress="javascript:return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendario('FiltrarOperacaoEfetuadaActionForm', 'dataInicial')"><img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
                    <strong>a</strong> 
                    <html:text name="FiltrarOperacaoEfetuadaActionForm" onkeyup="mascaraData(this, event);" property="dataFinal" size="10" maxlength="10" 
                     onkeypress="javascript:return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendario('FiltrarOperacaoEfetuadaActionForm', 'dataFinal')"><img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a> dd/mm/aaaa</td>

                </tr>
                <tr> 
                  <td><strong>Horário de Realização:</strong></td>
                  <td> 
                    <input type="hidden" name="horaLimpa" value="0">                  
                  <html:text name="FiltrarOperacaoEfetuadaActionForm" property="horaInicial" onkeyup="mascaraHora(this, event);replicarCampo(document.forms[0].horaFinal,this);" 
                    onblur="javascript:horaEstahLimpa()" size="10" maxlength="5"
                     onkeypress="javascript:return isCampoNumerico(event);"/>
                    <strong> a</strong> 
                    <html:text name="FiltrarOperacaoEfetuadaActionForm" onkeyup="mascaraHora(this, event);" property="horaFinal" size="10" maxlength="5"
                     onkeypress="javascript:return isCampoNumerico(event);"/>
                    hh:mm </td>
                </tr>
                
                
                <tr>
					<td width="120"><strong>Unidade de Negócio:</strong></td>
					<td colspan="2"><html:select property="unidadeNegocio">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoUnidadeNegocio"
							labelProperty="nome" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
                
                
                <tr>
                  <td colspan="2"><hr></td>
                </tr>
                <tr>
                  <td><strong>Pesquisar Por:</strong></td>
                  <td>
                  	  <input type="hidden" name="argumentoLimpo" value="0">
                  	  <html:hidden property="idArgumentoPesquisa" />
					  <html:text name="FiltrarOperacaoEfetuadaActionForm" property="nomeArgumentoPesquisa" 
					  		readonly="true" size="30" maxlength="30" readonly="true" style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
 					<html:text maxlength="9" name="FiltrarOperacaoEfetuadaActionForm" property="valorArgumentoPesquisa"
						size="9" 
						onkeypress="javascript: return isCampoNumerico(event);
						return validaEnter(event, 'ExibirFiltrarOperacaoEfetuadaAction.do', 'valorArgumentoPesquisa');"
						onchange="javascript:argumentoPesquisaEstahLimpo()" />

                    <!-- <img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" style="cursor:hand;"
						onclick="abrirPopup('<bean:write name="FiltrarOperacaoEfetuadaActionForm" property="url" />', 495, 300);"
						alt="Pesquisar">  
					-->

                    <a href="javascript:limparArgumentoPesquisa();">
                    	<img  border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar" /> 
                    </a>
	                  	 
                  </td>
               </tr>
		<tr>
		  <td colspan="2">&nbsp;</td>
		</tr>
                <tr> 
                  <td class="style1"><input type="button" class="bottonRightCol" value="Limpar" onclick="javascript:limparFiltrar();"/>
                  <p>&nbsp;</p></td>
                  <td class="style1"> <table>
                      <tbody><tr> 
                        
                        <td align="right" width="500"><input type="button" class="bottonRightCol" value="Filtrar" onclick="javascript:chamarFiltrar();"/></td>
                        <td>&nbsp;</td>
                      </tr>
                    </tbody></table></td>
                </tr>
            </tbody></table>
      <p>&nbsp;</p>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>
