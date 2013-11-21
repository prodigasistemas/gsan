<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<html:javascript staticJavascript="false"  formName="InformarTramiteAssociacaoSituacaoCobrancaActionForm" />

<script>
   function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	   var form = document.forms[0];

	   if (tipoConsulta == 'unidadeOrganizacional') {
	      form.idUnidadeAtendimento.value = codigoRegistro;
	      form.descricaoUnidadeAtendimento.value = descricaoRegistro;
	    }
   }
   
	function limpar(){
		var form = document.forms[0];
		form.idTipoSolicitacao.value = "-1";
		form.idTipoEspecificacao.value = "-1";
		form.idTipoEspecificacao.readonly = true;
		form.idTipoEspecificacao.disabled = true;
		
		limparUnidadeAtendimento();
	}

	function carregarEspecificacao() {
		var form = document.forms[0];
		
		if(form.idTipoSolicitacao.value != null && form.idTipoSolicitacao.value != '' && form.idTipoSolicitacao.value != '-1'){
			form.action = 'exibirInformarAssociacaoSituacaoCobrancaAction.do?carregarEspecificacao=SIM';
			form.submit();
		}
	}
	
	function limparUnidadeAtendimento() {
		var form = document.forms[0];
		
      	form.idUnidadeAtendimento.value = '';
	    form.descricaoUnidadeAtendimento.value = '';
	
		form.idUnidadeAtendimento.focus();
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	
	function adicionar(){
		var form = document.forms[0];
		
		form.action = 'informarAssociacaoSituacaoCobrancaAction.do';
		form.submit();
	}
</script>

</head>

<logic:notPresent name="fecharPopup" scope="request">
	<body leftmargin="5" topmargin="5"
			onload="javascript:resizePageSemLink(570, 370);">
</logic:notPresent>
<logic:present name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirInformarTramiteSituacaoCobrancaAction.do');window.close()">
</logic:present>

<html:form action="/informarAssociacaoSituacaoCobrancaAction.do"
	name="InformarTramiteAssociacaoSituacaoCobrancaActionForm"
	type="gcom.gui.atendimentopublico.InformarTramiteAssociacaoSituacaoCobrancaActionForm"
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
					<td class="parabg">Informar Associacao da Situação de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr> 
          <td colspan="2">Preencha os campos para informar uma associacao da Situação de Cobrança:</td>
        </tr>
       </table>
        
       <table width="100%" border="0">

			<tr>
				<td><strong>Situação de Cobrança:</strong></td>
				<td colspan="2"><span class="style2"> <html:text
					property="descricaoSituacaoCobranca" size="40"  tabindex="2" readonly="true" disabled="true"/> </span></td>
				<td>&nbsp;</td>
			</tr>		
			
			<tr>
				<td width="30%"><strong>Tipo de Solicitação:<font color="#FF0000">*</font></strong></td>
				<td><html:select property="idTipoSolicitacao" tabindex="3" onchange="javascript:carregarEspecificacao();" >
					<logic:notEmpty name="colecaoTipoSolicitacao">
						<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
						<html:options collection="colecaoTipoSolicitacao"
							labelProperty="descricao" property="id" />
					</logic:notEmpty>
				</html:select></td>
			</tr>
			<logic:notPresent name="colecaoTipoEspecificacao" scope="session">
				<tr>
					<td width="30%"><strong>Tipo de Especificação:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idTipoEspecificacao" tabindex="3" >
						<logic:notEmpty name="colecaoTipoEspecificacao">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoTipoEspecificacao"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
			</logic:notPresent>
			<logic:present name="colecaoTipoEspecificacao" scope="session">
				<tr>
					<td width="30%"><strong>Tipo de Especificação:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idTipoEspecificacao" tabindex="3" >
						<logic:notEmpty name="colecaoTipoEspecificacao">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoTipoEspecificacao"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
			</logic:present>
			
              <tr> 
                <td><strong>Unidade de Atendimento:<font color="#FF0000">*</font></strong></td>
                <td colspan="6"><span class="style2"><strong>
					<html:text property="idUnidadeAtendimento" 
							   size="4" 
							   maxlength="4"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirInformarAssociacaoSituacaoCobrancaAction.do?validaUnidadeAtendimento=true', 'idUnidadeAtendimento','Unidade Atendimento');return isCampoNumerico(event);"/>
					
					<a href="javascript:redirecionarSubmit('exibirPesquisarUnidadeOrganizacionalAction.do?caminhoRetornoTelaPesquisaUnidadeOrganizacional=exibirInformarAssociacaoSituacaoCobrancaAction');">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Unidade de Atendimento"></a>
						 
					<logic:present name="unidadeSuperiorEncontrada" scope="session">
						<html:text property="descricaoUnidadeAtendimento" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
					</logic:present> 

					<logic:notPresent name="unidadeSuperiorEncontrada" scope="session">
						<html:text property="descricaoUnidadeAtendimento" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					</logic:notPresent>
					
					<a href="javascript:limparUnidadeAtendimento();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  </strong></span></td>
              </tr>
	        <tr> 
	        
	          <td>&nbsp;</td>
	          <td colspan="3">&nbsp;</td>
	        </tr>
	
			<tr>
				<td>

					<input type="button" name="Button1"
						class="bottonRightCol" value="Voltar" 
						onclick="window.close();">
	
					<input name="Button2" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="javascript:limpar();" >
						
	            </td>
	            <td  align="right">
	            
					<input type="button" name="Button3"
					class="bottonRightCol" value="Adicionar" tabindex="4"
					onclick="javascript:adicionar();"/>
					
				</td>
			</tr>

      </table>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
