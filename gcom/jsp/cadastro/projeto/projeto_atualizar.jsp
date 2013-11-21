<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<html:javascript staticJavascript="false" formName="AtualizarProjetoForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(){
	var form = document.forms[0];
	if (validateAtualizarProjetoForm(form)){
		submeterFormPadrao(form);
	}
}

function limparForm(tipo){

      var form = document.forms[0];
      
	  if(tipo == 'OrgaoFinanciador')
	  { 
	    form.idOrgaoFinanciador.value = "";
	  	form.nomeOrgaoFinanciador.value = "";
	  }
}

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
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
	if (tipoConsulta == 'cliente') {
        form.idOrgaoFinanciador.value = codigoRegistro;
        form.nomeOrgaoFinanciador.value = descricaoRegistro;
     }
}	

</script>

</head>

<body leftmargin="5" topmargin="5" onload="">

<html:form action="/atualizarProjetoAction.do"
	name="AtualizarProjetoForm"
	type="gcom.gui.cadastro.projeto.AtualizarProjetoForm"
	method="post">

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
	
				<td valign="top" class="centercoltext">
				<table>
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
						<td width="11"><img border="0" src="imagens/parahead_left.gif" />
						</td>
						<td class="parabg">Atualizar Projeto</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para atualizar um Projeto, informe os
						dados abaixo:</td>
					</tr>
					
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				
					<tr>
						<td><strong>Nome:<font color="#FF0000">*</font></strong></td>
						<td><html:text property="nome" tabindex="1" size="55" maxlength="50"/></td>
					</tr>
					
					<tr>
						<td height="30"><strong>Nome Abreviado:</strong></td>
						<td colspan="2">
							<html:text property="nomeAbreviado" tabindex="2" size="15" maxlength="10"/>
						</td>	
					</tr>
					
					<tr>
					<td><strong>Órgão Financiador:</strong></td>
					<td colspan="3"><strong> <html:text property="idOrgaoFinanciador" size="10"
						maxlength="10"
						tabindex="3"
						onkeypress="javascript:return validaEnter(event, 'exibirAtualizarProjetoAction.do?pesquisarCliente=sim', 'idOrgaoFinanciador');" />
					</strong> <a
						href="javascript:chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',document.forms[0].idOrgaoFinanciador.value);"
						alt="Pesquisar Órgão Financiador"> <img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					
					<logic:present name="existeCliente">
						<logic:equal name="existeCliente" value="exception">
							<html:text property="nomeOrgaoFinanciador" size="35" maxlength="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="existeCliente" value="exception">
							<html:text property="nomeOrgaoFinanciador" size="35" maxlength="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="existeCliente">
						<logic:empty name="AtualizarProjetoForm"
							property="idOrgaoFinanciador">
							<html:text property="nomeOrgaoFinanciador" size="35" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="AtualizarProjetoForm"
							property="idOrgaoFinanciador">
							<html:text property="nomeOrgaoFinanciador" size="35" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparForm('OrgaoFinanciador');"> <img
						border="0"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						style="cursor: hand;" /> </a></td>
					</tr>
					<tr>
					<td><strong>Data do Início do Projeto:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text maxlength="10"
						property="dataInicio" size="10" tabindex="4"
						onkeyup="mascaraData(this, event);" />
					<a
						href="javascript:abrirCalendario('AtualizarProjetoForm', 'dataInicio');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					</strong>
					</td>
					</tr>
					<tr>
					<td><strong>Data do Fim do Projeto:</strong></td>
					<td><strong> <html:text maxlength="10"
						property="dataFim" size="10" tabindex="5"
						onkeyup="mascaraData(this, event);" />
					<a
						href="javascript:abrirCalendario('AtualizarProjetoForm', 'dataFim');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					</strong>
					</td>
					</tr>
					
					
					<tr>
						<td width="30%"><strong>Valor Financiamento:</strong></td>
						<td><html:text property="valorFinanciamento" onkeyup="formataValorMonetario(this, 12)" size="15" tabindex="6" /></td>
					</tr>
					<tr>
						<td width="30%"><strong>Observação:</strong></td>
						<td width="75%">
							<html:textarea property="observacao" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].observacao, 300, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
							<strong><span id="utilizado">0</span>/<span id="limite">300</span></strong>				
						</td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td align="left"><font color="#FF0000">*</font>Campos Obrigatórios</td>
					</tr>
					
					<tr>
						<td colspan="2"><input name="Button" type="button"
							class="bottonRightCol" value="Desfazer" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarProjetoAction.do?menu=sim"/>'"> 
							<input name="Button"
							type="button" class="bottonRightCol" value="Cancelar" align="left"
							onclick="window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td width="12">&nbsp;</td>
						<td width="53" height="24" align="right"><input type="button"
							name="Button2" class="bottonRightCol" value="Atualizar"
							onClick="javascript:validarForm();" /></td>
						<td width="12">&nbsp;</td>
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