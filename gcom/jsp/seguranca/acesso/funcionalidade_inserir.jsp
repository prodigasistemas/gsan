<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.seguranca.acesso.Funcionalidade"%>
<%@page import="gcom.gui.GcomAction"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirFuncionalidadeActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
	
		if(testarCampoValorZero(document.InserirFuncionalidadeActionForm.numeroOrdemMenu, 'Número ordem menu') &&
		testarCampoValorZero(document.InserirFuncionalidadeActionForm.descricao, 'Descrição') && 
		testarCampoValorZero(document.InserirFuncionalidadeActionForm.descricaoAbreviada, 'Descrição Abreviada') && 
		testarCampoValorZero(document.InserirFuncionalidadeActionForm.caminhoMenu, 'Caminho Menu') && 
		testarCampoValorZero(document.InserirFuncionalidadeActionForm.caminhoUrl, 'Caminho URL') && 
		testarCampoValorZero(document.InserirFuncionalidadeActionForm.modulo, 'Módulo')) {
	
			if(validateInserirFuncionalidadeActionForm(form) && verificaFuncionalidadeCategoriaObrigatorio()){
	    		form.submit();
			}
		}
	}
	
	
	function verificaFuncionalidadeCategoriaObrigatorio(){
		var form =  document.forms[0];
		retorno = false;
		
		if(form.indicadorPontoEntrada[0].checked == true){
			
			if(form.idFuncionalidadeCategoria.value == null || form.idFuncionalidadeCategoria.value == ''){
			
				alert('Informe Categoria da Funcionalidade');
			
			}else{

				return true;
			}
		
		}else{
			return true;
		}
	
	}
	
	
	function reload(){
		var form =  document.forms[0];
		
		
		form.action = "/gsan/exibirInserirFuncionalidadeAction.do";
		form.submit();
	
	}
	
	
	function pesquisaIndisponivel(){
		
		alert('Pesquisa Indisponível!');
	
	}
	
	function limparFuncionalidadeCategoriaTecla(){
		var form =  document.forms[0];
		form.nomeFuncionalidadeCategoria.value = '';
	}
	
	function limparFuncionalidadeCategoria(){
		var form =  document.forms[0];
		
		form.idFuncionalidadeCategoria.value = '';
		form.nomeFuncionalidadeCategoria.value = '';
	}
	
	function recuperarDadosPopup(codigo, descricao, tipoConsulta) {

		var form = document.forms[0];
		if (tipoConsulta == 'categoriaFuncionalidade') {
		     form.idFuncionalidadeCategoria.value = codigo;
		 	 form.nomeFuncionalidadeCategoria.value = descricao;
	  	     form.nomeFuncionalidadeCategoria.style.color = "#000000";
		}
	}

</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/inserirFuncionalidadeAction.do" method="post"
	name="InserirFuncionalidadeActionForm"
	type="gcom.gui.seguranca.acesso.InserirFuncionalidadeActionForm"
	onsubmit="return validateInserirFuncionalidadeActionForm(this);">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			</div>
			</td>
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Funcionalidade</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar a funcionalidade, informe os dados
					abaixo:</td>
				</tr>
				<tr>
					<td><strong>Número ordem menu:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="numeroOrdemMenu" size="12"
						maxlength="12" style="text-transform: none;" /> </strong></td>
				</tr>
				<tr>
					<td width="162"><strong>Descri&ccedil;&atilde;o:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="descricao" size="60"
						maxlength="60" style="text-transform: none;" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="descricaoAbreviada" size="10"
						maxlength="10" style="text-transform: none;" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Caminho Menu:<font color="#FF0000">*</font></strong></td>
					<td><strong> </strong> <html:text property="caminhoMenu" size="60"
						maxlength="300" style="text-transform: none;" /></td>
				</tr>
				<tr>
					<td><strong>Caminho URL:<font color="#FF0000">*</font></strong></td>
					<td><strong> </strong> <html:text property="caminhoUrl" size="60"
						maxlength="300" style="text-transform: none;" /></td>
				</tr>

				<tr>
					<td><strong>Módulo:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="modulo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoModulo"
							labelProperty="descricaoModulo" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Nova Janela:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorNovaJanela" value="1" />
					<strong>Sim <html:radio property="indicadorNovaJanela" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>
				<tr>
					<td><strong>Indicador de Uso OLAP:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorOlap" value="1" /> <strong>Ativo
					<html:radio property="indicadorOlap" value="2" /> Inativo</strong>
					</strong></td>

				</tr>

				<tr>
					<td><strong>Ponto de Entrada:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorPontoEntrada" value="1"
						onclick="javascript:reload();" /> <strong>Sim <html:radio
						property="indicadorPontoEntrada" value="2"
						onclick="javascript:reload();" /> N&atilde;o</strong> </strong></td>

				</tr>

				<logic:present name="funcionalidadeCategoria" scope="request">
					<tr>
						<td width="30%"><strong>Categoria da Funcionalidade:<font
							color="#FF0000">*</font></strong></td>
						<td><html:text maxlength="9" property="idFuncionalidadeCategoria"
							size="5" tabindex="6"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirFuncionalidadeAction.do', 'idFuncionalidadeCategoria', 'Funcionalidade Categoria'); limparFuncionalidadeCategoriaTecla();" />
							<a href="javascript:abrirPopup('exibirPesquisarCategoriaFuncionalidadeAction.do', 250, 495);"><img
							src="<bean:message key='caminho.imagens'/>pesquisa.gif"
							width="23" height="21" alt="Pesquisar" border="0">
							</a> 
						<!-- <logic:present
							name="funcionalidadeCategoriaInexistente" scope="request">
							<html:text property="nomeFuncionalidadeCategoria" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						 </logic:present> <logic:notPresent name="empresaInexistente"
							scope="request">
							<html:text property="nomeFuncionalidadeCategoria" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>--> 
							<logic:present name="idFuncionalidadeCategoria" scope="request">
							
								<html:text property="nomeFuncionalidadeCategoria" 
									size="45"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							
							</logic:present> 

							<logic:notPresent name="idFuncionalidadeCategoria" scope="request">
							
								<html:text property="nomeFuncionalidadeCategoria" 
									size="45"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />

							</logic:notPresent>
							<a href="javascript:limparFuncionalidadeCategoria();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
						</td>
					</tr>



				</logic:present>

				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>

				<tr>
					<td><strong> Depend&ecirc;ncias:</strong></td>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Adicionar" align="right"
						onclick="javascript:abrirPopup('exibirAdicionarFuncionalidadeDependenciaAction.do?funcionalidade=inserir');" />


					</td>
				</tr>


				<tr>
					<td colspan="2">
					<table width="100%" bgcolor="#99CCFF">


						<tr bordercolor="#000000">
							<td align="center" width="10%" bgcolor="#90c7fc"><strong>Remover</strong></td>
							<td width="40%" bgcolor="#90c7fc"><strong>Funcionalidade</strong></td>
						</tr>
						<%--Esquema de paginação--%>

						<logic:present name="colecaoFuncionalidadeTela" scope="session">
							<%int cont = 0;%>
							<logic:iterate id="funcionalidade"
								name="colecaoFuncionalidadeTela">
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
										onclick="javascript:if(confirm('Confirma remoção?')){redirecionarSubmitSemUpperCase('removerAdicionarFuncionalidadeDependenciaAction.do?timestamp=<%=""+GcomAction.obterTimestampIdObjeto((Funcionalidade)funcionalidade)%>');}" />
									</font></div>
									</td>
									<td>
									<div align="center"><bean:write name="funcionalidade"
										property="descricao" /></div>
									</td>
								</tr>
							</logic:iterate>

						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirFuncionalidadeAction.do?desfazer=S"/>'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='<html:rewrite page="/"/>'"></td>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Inserir" align="right"
						onClick="javascript:validarForm(document.forms[0]);"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>

