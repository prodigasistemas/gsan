<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.cadastro.geografico.BairroArea"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="GerarMovimentoExclusaoNegativacaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin
function gerarExclusaoNegativacao() {
	if (document.forms[0].opcao.value != '1' && document.forms[0].opcao.value != '2') {
		alert('Você deve selecionar uma opção!');
	} else {
		if (document.forms[0].opcao.value == '1'){
			for (i = 0; i < document.forms[0].negativadores.length; i++) 	{
				if (document.forms[0].negativadores.options[i].selected) {
						document.forms[0].submit();
						return;
				}
			}
			alert('Você deve selecionar Negativadores!');
		} else if (document.forms[0].opcao.value == '2') {
			if (document.forms[0].codigoMovimento.value == '') {
				alert('Você deve preencher um Movimento!');
			} else {
				document.forms[0].submit();
			}
		}	
	}
}

function selecionarOpcao(opcao){

	document.forms[0].gerar.disabled = true;
	document.forms[0].negativadores.disabled = false;

	if (opcao == '1'){
		document.forms[0].opcao.value = opcao;
		limparMovimento();

		if (document.forms[0].negativadores.length == 0) {
			carregarNegativador();
		} else {
			//document.forms[0].gerar.disabled = false;
		}
	} else if (opcao == '2'){
		document.forms[0].opcao.value = opcao;
		for (i = 0; i < document.forms[0].negativadores.length; i++) 	{
			document.forms[0].negativadores.options[i].selected=false;
		}
		document.forms[0].negativadores.disabled = true;
		if (document.forms[0].codigoMovimento.value == '') {
			document.forms[0].gerar.disabled = true;
		}
	}

	if (opcao == '') {
	//caso venha de um refresh pq consutou o negativador;
		if (document.forms[0].negativadores.length > 0) {
			//document.forms[0].gerar.disabled = false;
			document.forms[0].negativadores.disabled = false;
		} else {
			document.forms[0].gerar.disabled = true;
		}
	}
}

function selecionarNegativadores(){
	document.forms[0].gerar.disabled = false;
}

// consulta todos os negativadores
function carregarNegativador() {
	document.forms[0].action = 'exibirGerarMovimentoExclusaoNegativacaoAction.do';
	document.forms[0].submit();
}

// limpa o movimento
function limparMovimento() {
	document.forms[0].codigoMovimento.value = '';
	document.forms[0].descricaoMovimento.value = '';
	document.forms[0].gerar.disabled = true;
}

function pesquisarMovimentoArrecadador(){
	if (document.forms[0].opcao.value == '2') {
		abrirPopup('exibirPesquisarNegativadorMovimentoAction.do', 275, 665);
	}
}

function recuperarDadosQuatroParametros(id, descricao, codigoMovimento, tipoConsulta) { 
	document.forms[0].codigoMovimento.value = '';
	document.forms[0].descricaoMovimento.value = '';
	if (codigoMovimento  == '2' ) {
		document.forms[0].codigoMovimento.value = id;
		document.forms[0].descricaoMovimento.value = descricao;
		document.forms[0].gerar.disabled = false;
	} else {
		alert('Movimento informado não é de exlusão de negativação!');
	}
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:selecionarOpcao('');">
<html:form action="/gerarMovimentoExclusaoNegativacaoAction.do" name="GerarMovimentoExclusaoNegativacaoActionForm"
	type="gcom.gui.cadastro.geografico.BairroActionForm" method="post"
	onsubmit="return validateGerarMovimentoExclusaoNegativacaoActionForm(this);">

<input name="opcao" type="hidden"  value="<bean:write name="GerarMovimentoExclusaoNegativacaoActionForm" property="opcao" />" />
<input name="codigoMovimento" type="hidden" value="" />

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


			<td valign="top" class="centercoltext">

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
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                <td class="parabg">Gerar Movimento de Exclus&atilde;o de Negativa&ccedil;&atilde;o </td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>

              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->

<p>&nbsp;</p>
            <table width="100%" border="0">
              <tr> 
                <td colspan="2">Para gerar o movimento de exclus&atilde;o de negativa&ccedil;&atilde;o, 
                  informe os dados abaixo:</td>
              </tr>

              <tr> 
                <td colspan="2"><div align="left"><strong> 
                
                <input type="radio" name="opcao1"  
				  <logic:present property="opcao" name="GerarMovimentoExclusaoNegativacaoActionForm">
				  <logic:equal property="opcao" name="GerarMovimentoExclusaoNegativacaoActionForm" value="1">
					checked="checked"
				  </logic:equal>
	   			  </logic:present>
	   			  value="1" onclick="javascript:selecionarOpcao('1');">

                    Gerar Movimento de Exclus&atilde;o de Negativa&ccedil;&atilde;o </strong></div></td>
              </tr>
              <tr> 
                <td width="158" height="25"><strong>Negativador:<font color="#FF0000">*</font></strong></td>
                <td width="453" align="right"> <div align="left">
					
				<html:select property="negativadores" name="GerarMovimentoExclusaoNegativacaoActionForm" size="2"  style="width:250" multiple="multiple" onclick="javascript:selecionarNegativadores();">
				  <logic:present property="collNegativadores" name="GerarMovimentoExclusaoNegativacaoActionForm">
				  	<logic:iterate property="collNegativadores" name="GerarMovimentoExclusaoNegativacaoActionForm" id="collNegativadores">
						<option value="<bean:write name="collNegativadores" property="id"/>" ><bean:write name="collNegativadores" property="cliente.nome"/></option>
				  	</logic:iterate>
				  </logic:present>
				</html:select>
				  <logic:notPresent property="collNegativadores" name="GerarMovimentoExclusaoNegativacaoActionForm">
					Não existem negativações a serem excluídas.
				  </logic:notPresent>
				  <logic:present property="collNegativadores" name="GerarMovimentoExclusaoNegativacaoActionForm">
				  <logic:empty property="collNegativadores" name="GerarMovimentoExclusaoNegativacaoActionForm">
					Não existem negativações a serem excluídas.
				  </logic:empty>
				  </logic:present>
                </div></td>
              </tr>

              <tr> 
                <td colspan="2"><hr></td>
              </tr>
              <tr> 
                <td colspan="2"><strong> 

                <input type="radio" name="opcao1"  
				  <logic:present property="opcao" name="GerarMovimentoExclusaoNegativacaoActionForm">
				  <logic:equal property="opcao" name="GerarMovimentoExclusaoNegativacaoActionForm" value="2">
					checked="checked"
				  </logic:equal>
	   			  </logic:present>
	   			  value="2" onclick="javascript:selecionarOpcao('2');">

                  Regerar Arquivo TXT de Movimento de Exclus&atilde;o de Negativa&ccedil;&atilde;o</strong> 
                  <div align="left"></div></td>
              </tr>

              <tr> 
                <td><strong>Movimento:<font color="#FF0000">*</font></strong></td>
                <td align="right"><div align="left">
                  <input name="descricaoMovimento" type="text" size="50" maxlength="50" style="background-color:#EFEFEF; border:0; color: #000000"/>
                  <a href="javascript:pesquisarMovimentoArrecadador();">
                  <img border="0" src="imagens/pesquisa.gif" width="23" height="21"></a>
                  <strong></strong>
                  <a href="javascript:limparMovimento();">
                  <img border="0" src="imagens/limparcampo.gif" width="23" height="21">
                  </a>
                  </div></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="right"><div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td><strong> <font color="#FF0000"> 
                  <input type="button" name="Submit22" class="bottonRightCol" value="Desfazer"
                  onclick="window.location.href='/gsan/exibirGerarMovimentoExclusaoNegativacaoAction.do?menu=sim'">
                  <input type="button" name="Submit23" class="bottonRightCol" value="Cancelar"
                  onclick="window.location.href='/gsan/telaPrincipal.do'">
                  </font></strong></td>

                <td align="right"> <input type="button" name="gerar" onclick="javascript:gerarExclusaoNegativacao();" disabled="true" class="bottonRightCol" value="Gerar">                </td>
              </tr>
            </table>
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">











			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>
