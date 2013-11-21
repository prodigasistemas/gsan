<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.seguranca.transacao.Tabela" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarOperacaoActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- Begin

function validarForm(form){

if(testarCampoValorZero(document.AtualizarOperacaoActionForm.descricao, 'Descrição') && testarCampoValorZero(document.AtualizarOperacaoActionForm.descricaoAbreviada, 'Descrição Abreviada') && testarCampoValorZero(document.AtualizarOperacaoActionForm.caminhoUrl, 'Caminho URL')){

if(validateAtualizarOperacaoActionForm(form) && verificarArgumentoPesquisa()){
    form.submit();
}
}
}


	//Chama Popup
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
					abrirPopup(url + "?" + "menu=sim&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

   
    if (tipoConsulta == 'operacao') {
      form.idOperacaoPesquisa.value = codigoRegistro;
      form.action='exibirAtualizarOperacaoAction.do?objetoConsulta=operacao';
	  form.submit();
//      form.descricaoOperacaoPesquisa.value = descricaoRegistro;
    }
    
     if (tipoConsulta == 'funcionalidade') {
      form.idFuncionalidade.value = codigoRegistro;
      form.action='exibirAtualizarOperacaoAction.do';
	  form.submit();
//      form.descricaoFuncionalidade.value = descricaoRegistro;
    }
    
     if (tipoConsulta == 'TabelaColuna') {
      form.idArgumentoPesquisa.value = codigoRegistro;
      form.descricaoArgumentoPesquisa.value = descricaoRegistro;
//      form.action='exibirAtualizarOperacaoAction.do?objetoConsulta=TabelaColuna';
//	  form.submit();
      
    }
    
   
 }

function limparOperacaoPesquisa(){
 	var form = document.forms[0];
 	
 	form.idOperacaoPesquisa.value = "";
 	form.descricaoOperacaoPesquisa.value = ""; 		
 }

function limparArgumentoPesquisa(){
 	var form = document.forms[0];
 	
 	form.idArgumentoPesquisa.value = "";
 	form.descricaoArgumentoPesquisa.value = ""; 		
 }
 
 function limparFuncionalidade(){
 	var form = document.forms[0];
 	
 	form.idFuncionalidade.value = "";
 	form.descricaoFuncionalidade.value = ""; 		
 }
 
 
function limparDescricaoOperacaoPesquisa(){
 	var form = document.forms[0];
 	form.descricaoOperacaoPesquisa.value = ""; 		
 }

function limparDescricaoArgumentoPesquisa(){
 	var form = document.forms[0];
 	form.descricaoArgumentoPesquisa.value = ""; 		
 }
 
 function limparDescricaoFuncionalidade(){
 	var form = document.forms[0];
 	form.descricaoFuncionalidade.value = ""; 		
 }
 
 
function desfazer(){
 form = document.forms[0];
 form.idTipoOperacao.value = "-1";
 form.reset();
}

function verificarArgumentoPesquisa(){
  form = document.forms[0];

  if(form.idArgumentoPesquisa.disabled == false && form.idArgumentoPesquisa.value == ""){
    alert('Argumento de pesquisa não informado.');
    return false;
  }else{
    return true;
  }
}

function verificarOperacaoPesquisa(){
  form = document.forms[0];

  if(form.idOperacaoPesquisa.disabled == false && form.idOperacaoPesquisa.value == ""){
    alert('Operação de pesquisa não informado.');
    return false;
  }else{
    return true;
  }
}
-->
</script>



</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/atualizarOperacaoAction" 
		   method="post"
		   name="AtualizarOperacaoActionForm"
		   type="gcom.gui.seguranca.acesso.AtualizarOperacaoActionForm"
		   onsubmit="return validateAtualizarOperacaoActionForm(this);">
		   
<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
	<td width="190" valign="top" class="leftcoltext">
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
	  <table>
		<tr>
		  <td></td>
		</tr>
	  </table>
 
	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		  <td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
		  <td class="parabg">Atualizar Operação</td>
		  <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif" /></td>
		</tr>
	  </table>
	  
	  <p>&nbsp;</p>
	  
	  <table width="100%" border="0">
		<tr>
		  <td colspan="2">Para atualizar a operação, informe os dados	abaixo:</td>
		</tr>
		<tr> 
          <td><strong>Código:</strong></td>
          <td>
			<html:hidden property="idOperacao"/>
			<bean:write name="AtualizarOperacaoActionForm" property="idOperacao"/>
		  </td>
        </tr>
		<tr>
		  <td><strong>Descri&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
		  <td>
		    <html:text property="descricao" size="60" maxlength="60" style="text-transform: none;"/> 
		  </td>
		</tr>
		
		<tr>
		  <td><strong>Descri&ccedil;&atilde;o Abreviada:<font color="#FF0000">*</font></strong></td>
		  <td>
		    <html:text property="descricaoAbreviada" size="10" maxlength="10" style="text-transform: none;"/> 
		  </td>
		</tr>
				
		<tr>
		  <td><strong>Caminho URL:<font color="#FF0000">*</font></strong></td>
		  <td>
		    <html:text property="caminhoUrl" size="60" maxlength="100" style="text-transform: none;"/>
		  </td>
		</tr>
		
		<tr>
		  <td><strong>Funcionalidade:<font color="#FF0000">*</font></strong></td>
		  <td colspan="3">
			<html:text property="idFuncionalidade" size="9" maxlength="9" tabindex="2" onkeyup="javascript:limparDescricaoFuncionalidade();" onkeypress="return validaEnterSemUpperCase(event, 'exibirAtualizarOperacaoAction.do?objetoConsulta=1', 'idFuncionalidade');" />
			<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Tabela" onclick="javascript:abrirPopup('exibirPesquisarFuncionalidadeAction.do', 400, 800);" style="cursor: hand;"/>
						
			<logic:present name="funcionalidadeNaoEncontrada">
			  <logic:equal name="funcionalidadeNaoEncontrada" value="exception">
			    <html:text property="descricaoFuncionalidade" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
					
			  <logic:notEqual name="funcionalidadeNaoEncontrada" value="exception">
				<html:text property="descricaoFuncionalidade" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
							
			<logic:notPresent name="funcionalidadeNaoEncontrada">
			  <logic:empty name="AtualizarOperacaoActionForm" property="idFuncionalidade">
			    <html:text property="descricaoFuncionalidade" value="" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  
			  <logic:notEmpty name="AtualizarOperacaoActionForm" property="idFuncionalidade">
				<html:text property="descricaoFuncionalidade" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
	
		    <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" onclick="javascript:limparFuncionalidade();" />
		  </td>
		</tr>
		
		
		  <tr>
			<td><strong>Tipo da Operação:<font color="#FF0000">*</font></strong></td>
			<td>
			  <html:select property="idTipoOperacao" onchange="javascript:redirecionarSubmitSemUpperCase('exibirAtualizarOperacaoAction.do');">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoOperacaoTipo" labelProperty="descricao" property="id" />
			  </html:select> 
			</td>
		  </tr>

		  <tr>
			<td><strong>Argumento de Pesquisa:</strong></td>
			 
				<td colspan="3">
				  <html:text property="idArgumentoPesquisa" size="9" maxlength="9" tabindex="2" onkeyup="javascript:limparDescricaoArgumentoPesquisa();" onkeypress="return validaEnterSemUpperCase(event, 'exibirAtualizarOperacaoAction.do?objetoConsulta=1', 'idArgumentoPesquisa');" />
				<a	href="javascript:chamarPopup('exibirPesquisarColunaTabelaAction.do', 'TabelaColuna', null, null, 400, 800, '','');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Tabela" /> </a>
			
					  <html:text property="descricaoArgumentoPesquisa" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			      <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" onclick="javascript:limparArgumentoPesquisa();"/>
				</td>
		
		  </tr>

		  <tr>
			<td colspan="4">
			  <hr>
			</td>
		  </tr>

		  <tr>
			<td><strong>Tabelas:</strong></td>
			<td align="right" colspan="4">
			  <logic:equal name="habilitarOperacaoPesquisa" value="true">
			    <input name="Button" type="button" class="bottonRightCol" value="Adicionar" align="right"	onclick="javascript:abrirPopup('exibirAdicionarOperacaoTabelaAction.do?retornarTela=exibirAtualizarOperacaoAction.do&limpaForm=true');" />
			  </logic:equal>
			  
			  <logic:equal name="habilitarOperacaoPesquisa" value="false">
			    <input name="Button" type="button" class="bottonRightCol" value="Adicionar" align="right" disabled/>
			  </logic:equal>
			</td>
		  </tr>


		  <tr>
			<td colspan="4">
			  <table width="100%" bgcolor="#99CCFF">
				<tr bordercolor="#000000">
				  <td align="center" width="10%" bgcolor="#90c7fc"><strong>Remover</strong></td>
				  <td align="center" width="40%" bgcolor="#90c7fc"><strong>Tabela</strong></td>
				</tr>
				
				<%--Esquema de paginação--%>

				<logic:present name="colecaoOperacaoTabela" scope="session">
				  <%int cont = 0;%>
				  <logic:iterate name="colecaoOperacaoTabela" scope="session" id="tabela" type="Tabela">

				  <%cont = cont + 1;
				  if (cont % 2 == 0) {%>
				  <tr bgcolor="#FFFFFF">
				  <%} else { %>
				  <tr bgcolor="#cbe5fe">
				  <%}%>
					<td width="10%">
					  <div align="center">
					    <font color="#333333"> 
					      <img width="14" height="14" border="0" src="<bean:message key="caminho.imagens"/>Error.gif" onclick="javascript:if(confirm('Confirma exclusão?')){redirecionarSubmit('removerOperacaoTabelaAction.do?telaRetorno=atualizarOperacao&idTabelaExcluir=<bean:write name="tabela" property="id"/>');}" />
						</font>
					  </div>
					</td>
					<td>  
					  <div align="left">
				        <bean:write name="tabela" property="descricao"/>
					  </div>
					</td>
				  </tr>

				</logic:iterate>
			  </logic:present>
			</table>
		  </td>
		</tr>
		
		<tr>
		  <td><strong>Operação de Pesquisa:</strong></td>
		    <logic:equal name="habilitarOperacaoPesquisa" value="true">
			  <td colspan="3">
				<html:text property="idOperacaoPesquisa" size="9" maxlength="9" tabindex="2" onkeyup="javascript:limparDescricaoOperacaoPesquisa();"  onkeypress="return validaEnterSemUpperCase(event, 'exibirAtualizarOperacaoAction.do?objetoConsulta=1', 'idOperacaoPesquisa');" />
				<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Tabela" onclick="javascript:abrirPopup('exibirPesquisarOperacaoAction.do', 400, 800);" style="cursor: hand;"/>
							
				<logic:present name="operacaoPesquisaNaoEncontrado">
				  <logic:equal name="operacaoPesquisaNaoEncontrado" value="exception">
				    <html:text property="descricaoOperacaoPesquisa" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				  </logic:equal>
						
				  <logic:notEqual name="operacaoPesquisaNaoEncontrado" value="exception">
					<html:text property="descricaoOperacaoPesquisa" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				  </logic:notEqual>
				</logic:present> 
								
				<logic:notPresent name="operacaoPesquisaNaoEncontrado">
				  <logic:empty name="AtualizarOperacaoActionForm" property="idOperacaoPesquisa">
				    <html:text property="descricaoOperacaoPesquisa" value="" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				  </logic:empty>
				  <logic:notEmpty name="AtualizarOperacaoActionForm" property="idOperacaoPesquisa">
					<html:text property="descricaoOperacaoPesquisa" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				  </logic:notEmpty>
				</logic:notPresent> 
		
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" onclick="javascript:limparOperacaoPesquisa();" />
			  </td>
		  </logic:equal>	  
		  
		  <logic:equal name="habilitarOperacaoPesquisa" value="false">
		    <td colspan="3">
		      <html:text property="idOperacaoPesquisa" size="9" maxlength="9" tabindex="2" disabled="true" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
		      <img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Operação de Pesquisa" /> 
	          <html:text property="descricaoOperacaoPesquisa" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
	          <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
	        </td>  
		  </logic:equal>
		</tr>

		<tr>
		  <td><strong> <font color="#FF0000"></font></strong></td>
		  <td align="right">
			<div align="left"><strong><font color="#FF0000">*</font></strong>Campos obrigat&oacute;rios</div>
		  </td>
		</tr>
	
	
	    <tr>
   		  <td colspan="3">
		    <logic:present name="voltar">
			  <logic:equal name="voltar" value="filtrar">
				<input name="Button" type="button" class="bottonRightCol" value="Voltar" align="left" onclick="window.location.href='<html:rewrite page="/exibirFiltrarOperacaoAction.do?desfazer=N"/>'">
			  </logic:equal>
			  <logic:equal name="voltar" value="manter">
				<input name="Button" type="button" class="bottonRightCol" value="Voltar" align="left" onclick="window.location.href='<html:rewrite page="/exibirManterOperacaoAction.do"/>'">
			  </logic:equal>
		    </logic:present>
		    
		    <logic:notPresent name="voltar">
			  <input name="Button" type="button" class="bottonRightCol" value="Voltar" align="left" onclick="window.location.href='<html:rewrite page="/exibirManterOperacaoAction.do"/>'">
		    </logic:notPresent>
   		
   		    <input name="Button" type="button" class="bottonRightCol" value="Desfazer" align="left" onclick="window.location.href='<html:rewrite page="/exibirAtualizarOperacaoAction.do?desfazer=S"/>'">
		    <input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left" onclick="window.location.href='/gsan/telaPrincipal.do'">
		  </td>						
   
          <td align="right">
            <gsan:controleAcessoBotao name="Botao" value="Atualizar" onclick="validarForm(document.forms[0]);" url="atualizarOperacaoAction.do" tabindex="13"/>
          </td>
        </tr>
   	
		
	  </table>
	  <p>&nbsp;</p>
	</td>  
  </tr>   
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

