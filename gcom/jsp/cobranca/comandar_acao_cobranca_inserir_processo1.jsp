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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoAcaoCobrancaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">

function selecionar(){

		var formulario = document.forms[0]; 
	    var i
    	for (i=0;i<formulario.tipoComando.length;i++){
       		if (formulario.tipoComando[i].checked)
	          break; 
	    }
    	formulario.tipoComando[i].value
 		if(formulario.tipoComando[i].value == 'Cronograma'){
    		formulario.action =  '/gsan/exibirInserirComandoAcaoCobrancaCronogramaAction.do?limparForm=OK&menu=sim'
    		formulario.submit();
		}else if(formulario.tipoComando[i].value == 'Eventual'){
    		formulario.action =  'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?limparForm=OK&menu=sim'
    		formulario.submit();
		}
}
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirInserirComandoAcaoCobrancaAction.do"
	name="InserirComandoAcaoCobrancaActionForm"
	type="gcom.gui.cobranca.InserirComandoAcaoCobrancaActionForm"
	method="post"
	onsubmit="return validateInserirComandoAcaoCobrancaActionForm(this);">

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
					Cobran&ccedil;a - Tipo de Comando	</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0" cellpadding="0"cellspacing="3">
				<tr>
					<td colspan="3">Para inserir o comando de a&ccedil;&atilde;o de
					cobran&ccedil;a, informe o tipo do comando:</td>
				</tr>
				<tr>
					<td width="18%"><strong>Tipo do Comando:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><input type="radio"
						name="tipoComando" value="Cronograma" checked> <strong>Cronograma
					<input type="radio" name="tipoComando" 
						value="Eventual"> Eventual</strong></td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
						color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>
				</tr>
				<tr>
					<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
						color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>
				</tr>
				<tr>
					<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
						color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>
				</tr>
				<tr>
					<td colspan="3" align="right">
						<table border="0" width="100%">
							<tr>
								<td align="right"  width="60%">&nbsp;</td>
								<td align="right"  width="5%">	
								  <gsan:controleAcessoBotao name="Button3222" value="Avançar" onclick="selecionar();" url="inserirComandoAcaoCobrancaCronogramaAction.do"/>			
								  <%-- <input name="Button3222" type="button" class="bottonRightCol" value="Avançar" onclick="selecionar();" /> --%>
								</td>
								<td align="right" width="2%">
									<img src="imagens/avancar.gif" width="15"  border="0"/>
								</td>
								<td align="right"  width="33%">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" width="100%" height="1px" bgcolor="#000000"></td>
				</tr>				
				<tr>
					<td colspan="2"><font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirInserirComandoAcaoCobrancaAction.do?menu=sim';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></td>
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
