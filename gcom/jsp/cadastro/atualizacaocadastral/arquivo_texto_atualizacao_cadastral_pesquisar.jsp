<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="PesquisarArquivoTextoAtualizacaoCadastralActionForm" />
<script>

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	   var form = document.forms[0];
	   if (tipoConsulta == 'leiturista') {
	      form.idLeiturista.value = codigoRegistro;
	      form.nomeLeiturista.value = descricaoRegistro;
	    }
	    
   }
   function validarForm(form){
    		form.submit();
	}
	
	function limparLeiturista(){
	var form = document.forms[0];
	    form.idLeiturista.value = '';
	    form.nomeLeiturista.value = '';
	}
	
	function limparDescricao(campo){
	    campo.value = '';
	}

	function limparForm(){
	    var form = document.forms[0];
	    limparLeiturista();
	    form.idSituacaoTransmissao.options[0].selected = true;
	    form.descricao.value = '';
	}	
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(600, 330);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarArquivoTextoAtualizacaoCadastralAction"
	name="PesquisarArquivoTextoAtualizacaoCadastralActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.PesquisarArquivoTextoAtualizacaoCadastralActionForm"
	method="post">

	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="520" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
				
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Pesquisar Arquivo Texto para Atualização Cadastral</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr> 
          <td colspan="2">Preencha os campos para pesquisar um arquivo texto:</td>
          <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=leituristaPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
        </tr>
       </table>
        
       <table width="100%" border="0">
       	<tr>
			<td class="style3">
				<strong>Descriçaõ do Arquivo:</strong>
			</td>
			<td colspan="3">
				<html:text property="descricao" size="50" maxlength="50" /> 
			</td>
		</tr>
		<tr>
			<td width="120"><strong>Agente Cadastral:</strong></td>
			<td colspan="3"><html:text maxlength="9" property="idLeiturista"
				tabindex="1" size="6"
				onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarArquivoTextoAtualizacaoCadastralAction.do?objetoConsulta=1', 'idLeiturista', 'leiturista');" />
			<a href="javascript:limparLeiturista();redirecionarSubmit('exibirPesquisarLeituristaAction.do?caminhoRetornoTelaPesquisaLeiturista=exibirPesquisarArquivoTextoAtualizacaoCadastralAction');">
			<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Agente Cadastral" /></a> 
			<logic:present
				name="idLeituristaEncontrado" scope="request">
				<html:text maxlength="30" property="nomeLeiturista"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000"
					size="30" />
			</logic:present> 
			<logic:notPresent
				name="idLeituristaEncontrado" scope="request">
				<html:text maxlength="30" property="nomeLeiturista"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000"
					size="30" />
			</logic:notPresent> 
			<a href="javascript:limparLeiturista();"> 
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /></a></td>
		</tr>  
        <tr>
			<td><strong>Situação da Transmissão do Arquivo:</strong></td>
			<td><div align="left"> <span class="style2">
				  <html:select property="idSituacaoTransmissao" tabindex="1" >
					<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>				  
					<html:options collection="colecaoSituacaoTransmissaoLeitura"
						labelProperty="descricaoSituacao" property="id" />
				  </html:select>
				  </span></div>
			</td>
		</tr>
                     							
		<tr>
			<td>
			
			<logic:present name="caminhoRetornoTelaPesquisaArquivoTextoAtualizacaoCadastral">
				<input type="button" name="Button1"
				class="bottonRightCol" value="Voltar" 
				onclick="history.back();">
			</logic:present>

				<input name="Button2" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="document.forms[0].reset();limparForm();" >
            </td>
            <td  align="right">
				<input type="button" name="Button3"
				class="bottonRightCol" value="Pesquisar" tabindex="4"
				onClick="validarForm(document.PesquisarArquivoTextoAtualizacaoCadastralActionForm)"/>
			</td>
		</tr>

      </table>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
