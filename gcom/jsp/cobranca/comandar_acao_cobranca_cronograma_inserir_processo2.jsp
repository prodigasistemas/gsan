<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="InserirComandoAcaoCobrancaCronogramaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin

function concluir(){
	var formulario = document.forms[0]; 
	if(formulario.cobrancaGrupo.value == "" ){ 
    	var msg = '';
    	msg = 'Informe Grupo de Cobrança.\n';
	    msg = msg + 'Informe Ação de Cobrança.\n';
    	msg = msg + 'Informe Atividade de Cobrança.';
		alert(msg)
		formulario.cobrancaGrupo.focus();
	}else if(formulario.cobrancaAcao.value == "" ){
    	var msg = '';
	    msg = msg + 'Informe Ação de Cobrança.\n';
    	msg = msg + 'Informe Atividade de Cobrança.';
		alert(msg)
		formulario.cobrancaAcao.focus();
	}else if(formulario.cobrancaAtividade.value == "" ){
    	var msg = '';
    	msg = msg + 'Informe Atividade de Cobrança.';
		alert(msg)
		formulario.cobrancaAtividade.focus();
	}else{
		formulario.action =  '/gsan/inserirComandoAcaoCobrancaCronogramaAction.do'
		formulario.submit();
	}
}

function validarCobrancaGrupo(){
	var formulario = document.forms[0]; 
	formulario.action =  '/gsan/exibirInserirComandoAcaoCobrancaCronogramaAction.do?validarCobrancaGrupo=SIM'
	formulario.submit();
}
function validarCobrancaAcao(){
	var formulario = document.forms[0]; 
	formulario.action =  '/gsan/exibirInserirComandoAcaoCobrancaCronogramaAction.do?validarCobrancaAcao=SIM'
	formulario.submit();
}
function validarCobrancaAtividade(){
	var formulario = document.forms[0]; 
	formulario.action =  '/gsan/exibirInserirComandoAcaoCobrancaCronogramaAction.do?validarCobrancaAtividade=SIM'
	formulario.submit();
}

function voltar(){
	var formulario = document.forms[0]; 
	formulario.action =  'exibirInserirComandoAcaoCobrancaAction.do?menu=sim'
	formulario.submit();
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirInserirComandoAcaoCobrancaCronogramaAction.do"
	name="InserirComandoAcaoCobrancaCronogramaActionForm"
	type="gcom.gui.cobranca.InserirComandoAcaoCobrancaCronogramaActionForm"
	method="post"
	onsubmit="return validateInserirComandoAcaoCobrancaCronogramaActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">

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

			<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Comando de A&ccedil;&atilde;o de
					Cobran&ccedil;a - Grupo de Cobrança</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0" cellpadding="0"cellspacing="3">
				<tr>
					<td colspan="3">Para determinar a a&ccedil;&atilde;o de
					cobran&ccedil;a a ser comandada, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td><strong>Refer&ecirc;ncia da Cobran&ccedil;a:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:select
						name="InserirComandoAcaoCobrancaCronogramaActionForm"
						property="referenciaCobranca"
						onchange="validarCobrancaGrupo();">
						<logic:present scope="session" name="colecaoCobrancaGrupoCronogramaMensal">
							
							<html:options name="session" collection="colecaoCobrancaGrupoCronogramaMensal"
								labelProperty="mesAno" property="id" />
						</logic:present>
						</html:select>
					</td>
				</tr>
				<tr>
					<td width="33%"><strong>Grupo de Cobran&ccedil;a:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:select
						name="InserirComandoAcaoCobrancaCronogramaActionForm"
						property="cobrancaGrupo" onchange="validarCobrancaGrupo();">
						<html:option value="">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaGrupo">
							<html:options name="session" collection="colecaoCobrancaGrupo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td><strong>A&ccedil;&atilde;o de Cobran&ccedil;a:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:select
						name="InserirComandoAcaoCobrancaCronogramaActionForm"
						property="cobrancaAcao" onchange="validarCobrancaAcao();">
						<html:option value="">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaAcao">
							<html:options name="session" collection="colecaoCobrancaAcao"
								labelProperty="descricaoCobrancaAcao" property="id" />
						</logic:present>
								
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Atividade de Cobran&ccedil;a:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:select
						name="InserirComandoAcaoCobrancaCronogramaActionForm"
						property="cobrancaAtividade" onchange="validarCobrancaAtividade();">
						<html:option value="">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaAtividade">
							<html:options name="session" collection="colecaoCobrancaAtividade"
								labelProperty="descricaoCobrancaAtividade" property="id" />
						</logic:present>			
					</html:select></td>
				</tr>
				<tr>
					<td height="17"><strong></strong></td>
					<td colspan="2"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>

				
				<tr>
					<td colspan="3" align="right">
						<table border="0" width="100%">
							<tr>
								<td align="right"  width="40%">&nbsp;</td>
								<td align="right"  width="10%">							
									<img src="imagens/voltar.gif" width="15"
									height="24">
								</td>
								<td align="left" width="50%">					
									<input name="Button322222" type="button"
									class="bottonRightCol" value="Voltar"
									onClick="javascript:voltar()" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" width="100%" height="1px" bgcolor="#000000"></td>
				</tr>				
				<tr>
					<td colspan="2"><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirInserirComandoAcaoCobrancaAction.do?menu=sim';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
						
						
						
					<td align="right">
					  <gsan:controleAcessoBotao name="Button" value="Concluir" tabindex="5" onclick="concluir();" url="inserirComandoAcaoCobrancaCronogramaAction.do"/>			
					  <%-- <input type="button" name="Button" class="bottonRightCol" tabindex="5" value="Concluir" onclick="concluir();" />--%>
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
