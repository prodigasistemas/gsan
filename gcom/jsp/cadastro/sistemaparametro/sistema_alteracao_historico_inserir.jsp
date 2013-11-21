<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirHistoricoAlteracaoSistemaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">
 
	function limparDescricaoFuncionalidade(){
		var form = document.forms[0];
		
		form.idFuncionalidade.value = "";
		form.descricaoFuncionalidade.value = "";
	}
	
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	
    	var form= document.forms[0];
    	//var form= document.InserirHistoricoAlteracaoSistemaActionForm;
    	
      	form.idFuncionalidade.value = codigoRegistro;  
      	form.descricaoFuncionalidade.value = descricaoRegistro;
      	form.descricaoFuncionalidade.style.color = "#000000";
    }	

	function validarForm(){
		var form = document.InserirHistoricoAlteracaoSistemaActionForm;
		
		if(validateInserirHistoricoAlteracaoSistemaActionForm(form)){
	       	submeterFormPadrao(form);
	    }
	}
	
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirHistoricoAlteracaoSistemaAction"
	name="InserirHistoricoAlteracaoSistemaActionForm"
	type="gcom.gui.cadastro.sistemaparametro.InserirHistoricoAlteracaoSistemaActionForm"
	method="post">

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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Histórico de Alterações do Sistema</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar Historico Alterações do Sistema,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td><strong>Funcionalidade:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="idFuncionalidade" size="4"
						maxlength="4" tabindex="1"
						onkeypress="validaEnterComMensagem(event, 'exibirInserirHistoricoAlteracaoSistemaAction.do', 'idFuncionalidade','Funcionalidade');" />

					<a
						href="javascript:abrirPopup('exibirPesquisarFuncionalidadeAction.do?menu=sim');">
					<img src="/gsan/imagens/pesquisa.gif"
						alt="Pesquisar Funcionalidade" border="0" height="21" width="23"></a>


					<logic:present name="funcionalidadeEncontrada">

						<logic:equal name="funcionalidadeEncontrada" value="exception">
							<html:text property="descricaoFuncionalidade" size="40"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="funcionalidadeEncontrada" value="exception">
							<html:text property="descricaoFuncionalidade" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="funcionalidadeEncontrada">
						<logic:empty name="InserirHistoricoAlteracaoSistemaActionForm"
							property="idFuncionalidade">
							<html:text property="descricaoFuncionalidade" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>

						<logic:notEmpty name="InserirHistoricoAlteracaoSistemaActionForm"
							property="idFuncionalidade">
							<html:text property="descricaoFuncionalidade" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparDescricaoFuncionalidade()"> <img border="0"
						title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>
				<tr>
					<td height="25"><strong>Data de Alteração:<font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="dataAlteracao" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <img
						border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('InserirHistoricoAlteracaoSistemaActionForm', 'dataAlteracao')" />
					dd/mm/aaaa</div>
					</td>
				</tr>
				<tr>
					<td><strong>Titulo de Alteração:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="tituloAlteracao" size="50" maxlength="50" /></td>
				</tr>
				<tr>
					<td><strong>Descrição:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="descricao" size="50" maxlength="50" /></td>
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td><font color="#FF0000"> <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td align="right"><gsan:controleAcessoBotao name="Button"
						value="Inserir"
						onclick="javascript:validarForm(document.forms[0]);"
						url="inserirHistoricoAlteracaoSistemaAction.do" /> <%-- <input type="button" name="Button" class="bottonRightCol" value="Inserir" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
