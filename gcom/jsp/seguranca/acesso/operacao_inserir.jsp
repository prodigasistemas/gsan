<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.seguranca.transacao.Tabela" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirOperacaoActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
<!-- Begin

	function validarForm(form){

		if(testarCampoValorZero(document.InserirOperacaoActionForm.descricao, 'Descrição') && 
			testarCampoValorZero(document.InserirOperacaoActionForm.descricaoAbreviada, 'Descrição Abreviada') && 
			testarCampoValorZero(document.InserirOperacaoActionForm.caminhoUrl, 'Caminho URL')){

			if(validateInserirOperacaoActionForm(form) && verificarArgumentoPesquisa()){
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
					abrirPopup(url + "?" + "menu=sim&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto , altura, largura);
				}
			}
		}
	}


	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];

    	if (tipoConsulta == 'operacao') {
      		form.idOperacaoPesquisa.value = codigoRegistro;
      		form.action='exibirInserirOperacaoAction.do';
	  		form.submit();
    	}
    
     	if (tipoConsulta == 'funcionalidade') {
      		form.idFuncionalidade.value = codigoRegistro;
      		form.action='exibirInserirOperacaoAction.do';
	  		form.submit();
    	}
     	
     	if (tipoConsulta == 'TabelaColuna') {
      		form.idArgumentoPesquisa.value = codigoRegistro;
      		form.action='exibirInserirOperacaoAction.do';
	  		form.submit();
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

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirOperacaoAction.do" method="post"
	name="InserirOperacaoActionForm"
	type="gcom.gui.seguranca.acesso.InserirOperacaoActionForm"
	onsubmit="return validarForm(this);">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="140" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Operação</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>

			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar a operação, informe os dados abaixo:</td>
				</tr>

				<tr>
					<td><strong>Descri&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="descricao" size="60" maxlength="60"
						style="text-transform: none;" /></td>
				</tr>

				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="descricaoAbreviada" size="10"
						maxlength="10" style="text-transform: none;" /></td>
				</tr>

				<tr>
					<td><strong>Caminho URL:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="caminhoUrl" size="60" maxlength="100"
						style="text-transform: none;" /></td>
				</tr>

				<tr>
					<td><strong>Funcionalidade:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text property="idFuncionalidade" size="9"
						maxlength="9" tabindex="2"
						onkeyup="javascript:limparDescricaoFuncionalidade();"
						onkeypress="return validaEnterSemUpperCase(event, 'exibirInserirOperacaoAction.do?objetoConsulta=1', 'idFuncionalidade');" />





					<a
						href="javascript:chamarPopup('exibirPesquisarFuncionalidadeAction.do', '', null, null, 600, 500, '','');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Funcionalidade" /> </a> <!--  <img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Funcionalidade"
						onclick="javascript:abrirPopup('exibirPesquisarFuncionalidadeAction.do', 400, 800);" />-->
					<logic:present name="funcionalidadeNaoEncontrada">
						<logic:equal name="funcionalidadeNaoEncontrada" value="exception">
							<html:text property="descricaoFuncionalidade" size="45"
								maxlength="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="funcionalidadeNaoEncontrada"
							value="exception">
							<html:text property="descricaoFuncionalidade" size="45"
								maxlength="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="funcionalidadeNaoEncontrada">
						<logic:empty name="InserirOperacaoActionForm"
							property="idFuncionalidade">
							<html:text property="descricaoFuncionalidade" value="" size="45"
								maxlength="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirOperacaoActionForm"
							property="idFuncionalidade">
							<html:text property="descricaoFuncionalidade" size="45"
								maxlength="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparFuncionalidade();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					<!--  	<img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar"
						onclick="javascript:limparFuncionalidade();" />--></td>
				</tr>

				<tr>
					<td><strong>Tipo da Operação:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idTipoOperacao"
						onchange="javascript:redirecionarSubmitSemUpperCase('exibirInserirOperacaoAction.do');">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoOperacaoTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Argumento de Pesquisa:</strong></td>

					<logic:equal name="habilitarArgumentoPesquisa" value="true">
						<td colspan="3"><html:text property="idArgumentoPesquisa" size="9"
							maxlength="9" tabindex="2"
							onkeyup="javascript:limparDescricaoArgumentoPesquisa();"
							onkeypress="return validaEnterSemUpperCase(event, 'exibirInserirOperacaoAction.do?objetoConsulta=1', 'idArgumentoPesquisa');" />

						<a
							href="javascript:chamarPopup('exibirPesquisarColunaTabelaAction.do', '', null, null, 400, 800, '','');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Tabela" /> </a> <!-- <img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Tabela"
							onclick="javascript:abrirPopup('exibirPesquisarColunaTabelaAction.do', 400, 800);"
							style="cursor: hand;" /> --> <logic:present
							name="argumentoPesquisaNaoEncontrado">
							<logic:equal name="argumentoPesquisaNaoEncontrado"
								value="exception">
								<html:text property="descricaoArgumentoPesquisa" size="45"
									maxlength="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="argumentoPesquisaNaoEncontrado"
								value="exception">
								<html:text property="descricaoArgumentoPesquisa" size="45"
									maxlength="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> <logic:notPresent
							name="argumentoPesquisaNaoEncontrado">
							<logic:empty name="InserirOperacaoActionForm"
								property="idArgumentoPesquisa">
								<html:text property="descricaoArgumentoPesquisa" value=""
									size="45" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirOperacaoActionForm"
								property="idArgumentoPesquisa">
								<html:text property="descricaoArgumentoPesquisa" size="45"
									maxlength="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> <a
							href="javascript:limparArgumentoPesquisa();"> <img border="0"
							title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
						<!--  <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar"
							onclick="javascript:limparArgumentoPesquisa();" />--></td>
					</logic:equal>

					<logic:equal name="habilitarArgumentoPesquisa" value="false">
						<td colspan="3"><html:text property="idArgumentoPesquisa" size="9"
							maxlength="9" disabled="true" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" /> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Argumento de Pesquisa" /> <html:text
							property="descricaoArgumentoPesquisa" size="45" maxlength="45"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" /> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></td>
					</logic:equal>
				</tr>

				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>

				<tr>
					<td><strong>Tabelas:</strong></td>
					<td align="right"><logic:equal name="habilitarOperacaoPesquisa"
						value="true">
						<input name="Button" type="button" class="bottonRightCol"
							value="Adicionar" align="right"
							onclick="javascript:abrirPopup('exibirAdicionarOperacaoTabelaAction.do?retornarTela=exibirInserirOperacaoAction.do&limpaForm=true');" />
					</logic:equal> <logic:equal name="habilitarOperacaoPesquisa"
						value="false">
						<input name="Button" type="button" class="bottonRightCol"
							value="Adicionar" align="right" disabled />
					</logic:equal></td>
				</tr>


				<tr>
					<td colspan="2">
					<table width="100%" bgcolor="#99CCFF">
						<tr bordercolor="#000000">
							<td align="center" width="10%" bgcolor="#90c7fc"><strong>Remover</strong></td>
							<td align="center" width="40%" bgcolor="#90c7fc"><strong>Tabela</strong></td>
						</tr>

						<%--Esquema de paginação--%>

						<logic:present name="colecaoOperacaoTabela" scope="session">
							<%int cont = 0;%>
							<logic:iterate name="colecaoOperacaoTabela" scope="session"
								id="tabela" type="Tabela">

								<%cont = cont + 1;
			if (cont % 2 == 0) {%>
								<tr bgcolor="#FFFFFF">
									<%} else {

			%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td width="10%">
									<div align="center"><font color="#333333"> <img width="14"
										height="14" border="0"
										src="<bean:message key="caminho.imagens"/>Error.gif"
										onclick="javascript:if(confirm('Confirma exclusão?')){redirecionarSubmit('removerOperacaoTabelaAction.do?telaRetorno=inserirOperacao&idTabelaExcluir=<bean:write name="tabela" property="id"/>');}" />
									</font></div>
									</td>
									<td>
									<div align="left"><bean:write name="tabela"
										property="descricao" /></div>
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
						<td colspan="3"><html:text property="idOperacaoPesquisa" size="9"
							maxlength="9" tabindex="2"
							onkeyup="javascript:limparDescricaoOperacaoPesquisa();"
							onkeypress="return validaEnterSemUpperCase(event, 'exibirInserirOperacaoAction.do?objetoConsulta=1', 'idOperacaoPesquisa');" />

						<a
							href="javascript:chamarPopup('exibirPesquisarOperacaoAction.do', '', null, null, 400, 800, '','');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Operação" /> </a> <!--  <img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Tabela"
							onclick="javascript:abrirPopup('exibirPesquisarOperacaoAction.do', 400, 800);"
							style="cursor: hand;" /> --><logic:present
							name="operacaoPesquisaNaoEncontrado">
							<logic:equal name="operacaoPesquisaNaoEncontrado"
								value="exception">
								<html:text property="descricaoOperacaoPesquisa" size="45"
									maxlength="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="operacaoPesquisaNaoEncontrado"
								value="exception">
								<html:text property="descricaoOperacaoPesquisa" size="45"
									maxlength="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> <logic:notPresent
							name="operacaoPesquisaNaoEncontrado">
							<logic:empty name="InserirOperacaoActionForm"
								property="idOperacaoPesquisa">
								<html:text property="descricaoOperacaoPesquisa" value=""
									size="45" maxlength="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirOperacaoActionForm"
								property="idOperacaoPesquisa">
								<html:text property="descricaoOperacaoPesquisa" size="45"
									maxlength="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> <a href="javascript:limparOperacaoPesquisa();">
						<img border="0" title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>

						<!-- <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar"
							onclick="javascript:limparOperacaoPesquisa();" /> --></td>
					</logic:equal>

					<logic:equal name="habilitarOperacaoPesquisa" value="false">
						<td colspan="3"><html:text property="idOperacaoPesquisa" size="9"
							maxlength="9" tabindex="2" disabled="true" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" /> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Operação de Pesquisa" /> <html:text
							property="descricaoOperacaoPesquisa" size="45" maxlength="45"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" /> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></td>
					</logic:equal>
				</tr>

				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>Campos
					obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirOperacaoAction.do?desfazer=S"/>'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='<html:rewrite page="/"/>'"></td>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Inserir" align="right"
						onclick="validarForm(document.InserirOperacaoActionForm)"></td>
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

