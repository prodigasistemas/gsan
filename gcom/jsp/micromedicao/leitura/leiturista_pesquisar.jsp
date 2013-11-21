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
<html:javascript staticJavascript="false"  formName="PesquisarLeituristaActionForm" />
<script>
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	   var form = document.forms[0];
//alert('tipoconsulta=' + tipoConsulta);
	   if (tipoConsulta == 'funcionario') {
	      form.idFuncionario.value = codigoRegistro;
	      form.nomeFuncionario.value = descricaoRegistro;
	    } else if (tipoConsulta == 'cliente') {
	      form.idCliente.value = codigoRegistro;
	      form.nomeCliente.value = descricaoRegistro;
	    } 
	    
   }

   function validarForm(form){
		if(validatePesquisarLeituristaActionForm(form)){
    		form.submit();
		} 

	}
	
	function limparFuncionario(form){
	    form.idFuncionario.value = '';
	    form.nomeFuncionario.value = '';
	}
	
	function limparCliente(form){
	    form.idCliente.value= '';
	    form.nomeCliente.value = '';
	}
	
	function limparDescricao(campo){
	    campo.value = '';
	}

	function limparForm(){
	    var form = document.forms[0];
	    limparFuncionario(form);
	    limparCliente(form);
	    form.empresa.options[0].selected = true;
	    form.DDDTelefone.value = '';
	    form.numeroTelefone.value = '';
	}	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(600, 400);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarLeituristaAction"
	name="PesquisarLeituristaActionForm"
	type="gcom.gui.micromedicao.leitura.PesquisarLeituristaActionForm"
	method="post"
	onsubmit="return validatePesquisarLeituristaActionForm(this);">

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
					<td class="parabg">Pesquisar Leiturista</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr> 
          <td colspan="2">Preencha os campos para pesquisar um leiturista:</td>
          <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=leituristaPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
        </tr>
       </table>
        
       <table width="100%" border="0">
        <tr>
			<td><strong>Empresa:</strong></td>
			<td><div align="left"> <span class="style2">
				  <html:select property="empresa" tabindex="1" >
					<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>				  
					<html:options collection="colecaoEmpresa"
						labelProperty="descricao" property="id" />
				  </html:select>
				  </span></div>
			</td>
		</tr>
        
        <tr>
			<td width="130"><strong>Funcionário:</strong></td>
			<td colspan="2"><html:text maxlength="9" property="idFuncionario"
				tabindex="1" size="9"
				onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarLeituristaAction.do?objetoConsulta=1', 'idFuncionario', 'Funcionário');" />
			<a href="javascript:redirecionarSubmit('exibirFuncionarioPesquisar.do?caminhoRetornoTelaPesquisaFuncionario=exibirPesquisarLeituristaAction');">
			<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Funcionário" /></a> 
			<logic:present
				name="idFuncionarioEncontrado" scope="request">
				<html:text maxlength="30" property="nomeFuncionario"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000"
					size="40" />
			</logic:present> 
			<logic:notPresent
				name="idFuncionarioEncontrado" scope="request">
				<html:text maxlength="30" property="nomeFuncionario"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000"
					size="40" />
			</logic:notPresent> 
			<a href="javascript:limparFuncionario(document.PesquisarLeituristaActionForm);"> 
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /></a></td>
		</tr>
                  
		<tr>
			<td class="style3"><strong>Cliente:</strong></td>
			<td colspan="3">
				<html:text maxlength="9" property="idCliente"
					tabindex="1" size="9"
					onkeyup="javascript:verificaNumeroInteiro(this);limparDescricao(document.PesquisarLeituristaActionForm.nomeCliente);"
					onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarLeituristaAction.do?objetoConsulta=1', 'idCliente', 'Cliente');" />
			<a href="javascript:redirecionarSubmit('exibirPesquisarClienteAction.do?caminhoRetornoTelaPesquisaCliente=exibirPesquisarLeituristaAction', 275, 480);">
			<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Cliente" /></a> 
				<logic:present name="idClienteEncontrado">	
	 				<html:text property="nomeCliente" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
			    </logic:present>
				<logic:notPresent name="idClienteEncontrado">
					<html:text property="nomeCliente" size="35" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:notPresent>
				<a href="javascript:limparCliente(document.PesquisarLeituristaActionForm);"> 
					<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />
				</a>
			</td>
		</tr>
		<tr>
			<td class="style3">
				<strong>DDD:</strong>
			</td>
			<td colspan="3">
				<html:text property="DDDTelefone" size="3" maxlength="3" /> 
			</td>
		</tr>
						
		<tr>
			<td class="style3">
				<strong>Telefone:</strong>
			</td>
			<td colspan="3">
				<html:text property="numeroTelefone" size="10" maxlength="10" /> 
			</td>
		</tr>				
		<tr>
			<td>
			
			<logic:present name="caminhoRetornoTelaPesquisaLeiturista">
				<input type="button" name="Button1"
				class="bottonRightCol" value="Voltar" 
				onclick="history.back();">
			</logic:present>

				<input name="Button2" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="document.forms[0].reset();limparForm();" >
            </td>
            <td  align="right">
				<input type="button" name="Button3"
				class="bottonRightCol" value="Pesquisar" tabindex="4"
				onClick="validarForm(document.PesquisarLeituristaActionForm)"/>
			</td>
		</tr>

      </table>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
