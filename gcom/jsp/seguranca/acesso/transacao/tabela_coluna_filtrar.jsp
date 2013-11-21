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
<html:javascript staticJavascript="false"  formName="FiltrarTabelaColunaActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script>

 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
 	if ('funcionalidade' == tipoConsulta) {
	 	document.forms[0].idFuncionalidade.value = codigoRegistro;
	 	document.forms[0].nomeFuncionalidade.value = descricaoRegistro;
	 	document.forms[0].action = 'exibirFiltrarTabelaColunaAction.do';
 		document.forms[0].submit();
 	} else if ('tabela' == tipoConsulta) {
	 	document.forms[0].idTabela.value = codigoRegistro;
	 	document.forms[0].nomeTabela.value = descricaoRegistro;
	 	document.forms[0].action = 'exibirFiltrarTabelaColunaAction.do';
 		document.forms[0].submit(); 	
 	} else {
	 	document.forms[0].valorArgumentoPesquisa.value = codigoRegistro;
 	}
 }

 function chamarFiltrar() {
/* 
	if (document.forms[0].idFuncionalidade.value == '' && 
		document.forms[0].idTabela.value == ''){
		alert('É necessário preencher funcionalidade ou tabela');	
		return;
	} else {
	
		if (!(testarCampoValorZero(document.forms[0].idFuncionalidade, 'Funcionalidade') &&			
			validateCaracterEspecial(document.forms[0]) && validateLong(document.forms[0]))){
           return;
        }
	
	}
	if (document.forms[0].idFuncionalidade.value != '' && !verificarOperacoes()){
		return;
	}
	*/
	if (!(testarCampoValorZero(document.forms[0].idTabela, 'Tabela') &&			
		validateCaracterEspecial(document.forms[0]) && validateLong(document.forms[0]))){
          return;
    }
	document.forms[0].action = 'exibirManterTabelaColunaAction.do';
	document.forms[0].submit();
 }
 
 function caracteresespeciais () {
   this.aa = new Array("idTabela", "Tabela deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
 }


 function IntegerValidations () {
   this.aa = new Array("idTabela", "Tabela deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
 }

 function limparFuncionalidade() {
    var form = document.forms[0]; 
 	form.nomeFuncionalidade.value = '';
 	form.idFuncionalidade.value = '';
 	if (form.operacoes != null) {
	 	form.action = 'exibirFiltrarTabelaColunaAction.do';
	 	form.submit();
	}
	form.operacoes = null;
 }
  function limparTabela() {
    var form = document.forms[0]; 
 	form.nomeTabela.value = '';
 	form.idTabela.value = '';
  } 
 function verificarOperacoes(){
   var form = document.forms[0];
   if (form.operacoes == null || form.operacoes.length == 0) {
       alert('Falta carregar operações');
       return false;	   
   }
   if (form.operacoes.value == '' && form.operacoes.options != null){
       for(i = 0; i < form.operacoes.options.length; i++){
         form.operacoes.options[i].selected = true;
       }
   }
   return true;   
 }

  function limparFiltrar(){
    limparTabela();
  }

  </script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirFiltrarTabelaColunaAction" method="post" onsubmit="return validateFiltrarTabelaColunaActionForm(this);" >

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
          <td class="parabg">Filtrar Tabelas e Colunas</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
            <table border="0" width="100%">
                <tbody><tr> 
                  <td colspan="2">Para filtrar as tabelas e colunas, 
                    informe os dados abaixo:</td>

                </tr>
<!-- 
                <tr> 
                  <td width="26%"><strong>Funcionalidade:</strong></td>
                  <td width="74%">
					<html:text maxlength="9" name="FiltrarTabelaColunaActionForm" property="idFuncionalidade" size="9" onkeypress="javascript:return validaEnter(event, 'exibirFiltrarTabelaColunaAction.do', 'idFuncionalidade');"/>
                    <img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" style="cursor:hand;"
						onclick="abrirPopup('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=funcionalidade', 495, 300);"
						alt="Pesquisar"> 

   		      		<logic:equal property="funcionalidadeNaoEncontrada" name="FiltrarTabelaColunaActionForm" value="true">
						<input type="text" name="nomeFuncionalidade" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="atencao.funcionalidade.inexistente"/>"/>
                    </logic:equal>
   		      		<logic:equal property="funcionalidadeNaoEncontrada" name="FiltrarTabelaColunaActionForm" value="false">
						<html:text name="FiltrarTabelaColunaActionForm" property="nomeFuncionalidade" size="42" maxlength="42" readonly="true" style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
                    </logic:equal>
                     
                    <a href="javascript:limparFuncionalidade();">
                    	<img  border="0" src="imagens/limparcampo.gif" height="21" width="23"> 
                    </a>
                  </td>
                </tr>
                <tr> 
                  <td width="26%"><strong>Operações:</strong></td>
                  <td width="74%">
                  <input type="hidden" name="nomeOperacaoSelecionada" value="<%=session.getAttribute("nomeOperacaoSelecionada") == null ? "" : session.getAttribute("nomeOperacaoSelecionada")%>">
                  <logic:present name="operacoes">
					<html:select property="operacoes" tabindex="4"
						style="width:300px;" multiple="" onchange="javascript:salvarNomeOperacaoUnica()">
						<html:options collection="operacoes"
							labelProperty="descricao" property="id" />
					</html:select>
					</logic:present>
					<logic:notPresent name="operacoes">
						<select><option></option></select>
					</logic:notPresent>
                  </td>
                </tr>
 -->                
                <tr> 
                  <td width="26%"><strong>Tabela:</strong></td>
                  <td width="74%">
					<html:text maxlength="9" name="FiltrarTabelaColunaActionForm" property="idTabela" size="9" onkeypress="javascript:return validaEnter(event, 'exibirFiltrarTabelaColunaAction.do', 'idTabela');"/>
                    <img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" style="cursor:hand;"
						onclick="abrirPopup('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=tabela', 495, 300);"
						alt="Pesquisar"> 

   		      		<logic:equal property="tabelaNaoEncontrada" name="FiltrarTabelaColunaActionForm" value="true">
						<html:text name="FiltrarTabelaColunaActionForm" property="nomeTabela" size="42" maxlength="42" readonly="true" style="border: 0pt none ; background-color: rgb(239, 239, 239);color:rgb(256,0,0);" />
                    </logic:equal>
   		      		<logic:equal property="tabelaNaoEncontrada" name="FiltrarTabelaColunaActionForm" value="false">
						<html:text name="FiltrarTabelaColunaActionForm" property="nomeTabela" size="42" maxlength="42" readonly="true" style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
                    </logic:equal>
                     
                    <a href="javascript:limparTabela();">
                    	<img  border="0" src="imagens/limparcampo.gif" height="21" width="23"> 
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
