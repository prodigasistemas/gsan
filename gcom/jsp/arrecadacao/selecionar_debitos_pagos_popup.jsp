<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.cobranca.DocumentoTipo" %>

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
	function selecionar(){
		var form = document.forms[0];
		
		form.action = 'selecionarDebitosPagosPopupAction.do';
		form.submit();
	}
	 
	 function habilitarPesquisaConta(){
	    var form = document.forms[0];
	 	var CONTA = document.getElementById("DOCUMENTO_TIPO_CONTA").value;
	 	
	    if(form.idTipoDocumento.value == CONTA){
	    	
   			redirecionarSubmit('exibirPesquisarContaAction.do?idImovel='+form.idImovel.value+ '&limpaForm=1&situacaoConta=nri&caminhoRetornoTelaPesquisaConta=exibirSelecionarDebitosPagosPopupAction');
	       
	    }
	 }
 
	 function habilitarPesquisaGuiaPagamento(form){
	 	var GUIA_PAGAMENTO = document.getElementById("DOCUMENTO_TIPO_GUIA_PAGAMENTO").value;
	 	
	    if(form.idTipoDocumento.value == GUIA_PAGAMENTO){

   			redirecionarSubmit('exibirPesquisarGuiaPagamentoAction.do?idImovel='+form.idImovel.value+ '&limpaForm=1&caminhoRetornoTelaPesquisaGuiaPagamento=exibirSelecionarDebitosPagosPopupAction');
		   
	    }
	   
	}
 
	 function habilitarPesquisaDebitoACobrar(form){
	 	var DEBITO_A_COBRAR = document.getElementById("DOCUMENTO_TIPO_DEBITO_A_COBRAR").value;
	 	
	    if(form.idTipoDocumento.value == DEBITO_A_COBRAR){
	    
	   		redirecionarSubmit('exibirPesquisarDebitoACobrarAction.do?idImovel='+form.idImovel.value+ '&caminhoRetornoTelaPesquisaDebitoACobrar=exibirSelecionarDebitosPagosPopupAction');
	       
	    }
	    
	 }
	
	function limparReferenciaConta(){
	 	var form = document.forms[0];
	 	
	 	form.referenciaConta.value = "";
	 	form.descricaoReferenciaConta.value = ""; 		
	}
 
	function limparGuiaPagamento(){
	 	var form = document.forms[0];
	 	
	 	form.idGuiaPagamento.value = "";
	 	form.descricaoGuiaPagamento.value = ""; 		
	}
	 
	function limparDebitoACobrar(){
	 	var form = document.forms[0];
	 	
	 	form.idDebitoACobrar.value = "";
	 	form.descricaoDebitoACobrar.value = ""; 		
	}
	
	//[SB0003] ? Habilitar/Desabilitar Campos
	function habilitacaoCampos(){
		var form = document.forms[0];
		 
	 	var CONTA = document.getElementById("DOCUMENTO_TIPO_CONTA").value;
	 	var GUIA_PAGAMENTO = document.getElementById("DOCUMENTO_TIPO_GUIA_PAGAMENTO").value;
	 	var DEBITO_A_COBRAR = document.getElementById("DOCUMENTO_TIPO_DEBITO_A_COBRAR").value;
	 	
	 	var tipoDocumentoSelected = form.idTipoDocumento.value;
	 	
	 	if (tipoDocumentoSelected == CONTA ){
	 		
	 		form.referenciaConta.disabled = false;
	 		
	 		form.idGuiaPagamento.value = "";
	 		form.descricaoGuiaPagamento.value = "";
	 		form.idGuiaPagamento.disabled = true;
	 		
	 		form.idDebitoACobrar.value = "";
	 		form.descricaoDebitoACobrar.value = "";
	 		form.idDebitoACobrar.disabled = true;
	 		
	 	} else if(tipoDocumentoSelected == GUIA_PAGAMENTO){
		 	
	 		form.referenciaConta.value = "";
	 		form.descricaoReferenciaConta.value = "";
	 		form.referenciaConta.disabled = true;
	 		
	 		form.idGuiaPagamento.disabled = false;
	 		
	 		form.idDebitoACobrar.value = "";
	 		form.descricaoDebitoACobrar.value = "";
	 		form.idDebitoACobrar.disabled = true;
	 		
	 	} else if(tipoDocumentoSelected == DEBITO_A_COBRAR){
	 	
	 		form.referenciaConta.value = "";
	 		form.descricaoReferenciaConta.value = "";
	 		form.referenciaConta.disabled = true;
	 		
	 		form.idGuiaPagamento.value = "";
	 		form.descricaoGuiaPagamento.value = "";
	 		form.idGuiaPagamento.disabled = true;
	 		
	 		form.idDebitoACobrar.disabled = false;
	 		
	 	} else {
	 	
	 		form.referenciaConta.value = "";
	 		form.descricaoReferenciaConta.value = "";
	 		form.referenciaConta.disabled = true;
	 		
	 		form.idGuiaPagamento.value = "";
	 		form.descricaoGuiaPagamento.value = "";
	 		form.idGuiaPagamento.disabled = true;
	 		
	 		form.idDebitoACobrar.value = "";
	 		form.descricaoDebitoACobrar.value = "";
	 		form.idDebitoACobrar.disabled = true;
	 		
	 	}
	 }
    
    function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) {
        
        var form = document.InserirGuiaDevolucaoActionForm;
        
    	if (tipoConsulta == 'guiaPagamento') {
    		form.idGuiaPagamento.value = codigoRegistro;
			form.descricaoGuiaPagamento.value = descricaoRegistro3;
			form.descricaoGuiaPagamento.style.color = "#000000";
		}
		
		if (tipoConsulta == 'debitoACobrar') {
    		form.idDebitoACobrar.value = codigoRegistro;
			form.descricaoDebitoACobrar.value = descricaoRegistro3;
			form.descricaoDebitoACobrar.style.color = "#000000";
		}
    }
    
	function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, tipoConsulta) { 
		var form = document.forms[0];
		
	 	if (tipoConsulta == 'conta') {
	 	    form.referenciaConta.value = descricaoRegistro1;
			form.descricaoReferenciaConta.value = descricaoRegistro2;
		}
	}
 
</script>

</head>

<logic:notPresent name="fecharPopup" scope="request">
	<body leftmargin="5" topmargin="5"
			onload="javascript:resizePageSemLink(620, 430);habilitacaoCampos();">
</logic:notPresent>
<logic:present name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('informarAcertoDocumentosNaoAceitosWizardAction.do?destino=2&action=exibirInformarAcertoDocumentosNaoAceitosDebitosAction&retornoPopup=SIM');window.close()">
</logic:present>

<html:form action="/selecionarDebitosPagosPopupAction"
	name="SelecionarDebitosPagosPopupActionForm"
	type="gcom.gui.arrecadacao.SelecionarDebitosPagosPopupActionForm"
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
					<td class="parabg">Selecionar Débitos Pagos</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr> 
          <td colspan="2">Preencha os campos para selecionar um débito pago:</td>
        </tr>
       </table>
        
       <table width="100%" border="0">

			<tr>
			  <td width="160"><strong>Imóvel:</strong></td>
			  <td width="82%" >
				<input type="hidden" id="DOCUMENTO_TIPO_CONTA" value="<%=DocumentoTipo.CONTA%>"/>
				<input type="hidden" id="DOCUMENTO_TIPO_GUIA_PAGAMENTO" value="<%=DocumentoTipo.GUIA_PAGAMENTO%>"/>
				<input type="hidden" id="DOCUMENTO_TIPO_DEBITO_A_COBRAR" value="<%=DocumentoTipo.DEBITO_A_COBRAR%>"/>
			  
			    <html:text 
			    	maxlength="9" 
			    	tabindex="1" 
			    	property="idImovel" 
			    	size="9" 
				    readonly="true" 
				    style="background-color:#EFEFEF; border:0; color: #000000" 
				    /> 
			    
			    <html:text 
			    	property="descricaoImovel" 
			    	size="50" 
			    	maxlength="50" 
			    	readonly="true" 
			    	style="background-color:#EFEFEF; border:0; color: #000000" 
			    	/>
			    	
			  </td>
			  
			</tr> 		
			
	        <tr> 
	          <td colspan="4">
	          	<hr>
	          </td>
	        </tr>
	        
			<tr>
		          <td>
		            <strong>Tipo do Documento:<font color="#FF0000">*</font></strong>
		          </td>
		          <td>
		            <html:select property="idTipoDocumento" onchange="habilitacaoCampos();">
		              <html:option value=""></html:option> 
					  <html:options collection="colecaoDocumentoTipo" labelProperty="descricaoDocumentoTipo" property="id"/>
		            </html:select>
		          </td>
	        </tr>
	        
        <tr>
		  <td><strong>Referência da Conta:</strong></td>
		  <td>
		    <strong> 
		      <html:text property="referenciaConta" size="7" maxlength="7" 
			      onkeyup="document.forms[0].descricaoReferenciaConta.value=''"
			      onkeypress="javascript:mascaraAnoMes(this,event);return validaEnterString(event, '/gsan/exibirSelecionarDebitosPagosPopupAction.do', 'referenciaConta');"/>
		    </strong>mm/aaaa 
		    
		    	<a href="javascript:habilitarPesquisaConta();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Referência da Conta" /></a> 	
		    
		    <logic:present name="referenciaContaNaoEncontrada">
		  	  <logic:equal name="referenciaContaNaoEncontrada" value="exception">
			    <html:text property="descricaoReferenciaConta" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="referenciaContaNaoEncontrada" value="exception">
			    <html:text property="descricaoReferenciaConta" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="referenciaContaNaoEncontrada" >
			  <logic:empty name="SelecionarDebitosPagosPopupActionForm" property="referenciaConta">
		 	    <html:text property="descricaoReferenciaConta" value="" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="SelecionarDebitosPagosPopupActionForm" property="referenciaConta">
 			    <html:text property="descricaoReferenciaConta" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
    		    <a href="javascript:limparReferenciaConta();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
		  </td>
		</tr>
        
        <tr>
			  <td><strong> Débito a Cobrar:</strong></td>
			  <td width="82%" height="24">
				    <html:text maxlength="9" tabindex="1" property="idDebitoACobrar" size="9" 
					    onkeyup="document.forms[0].descricaoDebitoACobrar.value='';" 
					    onkeypress="validaEnter(event, '/gsan/exibirSelecionarDebitosPagosPopupAction.do','idDebitoACobrar');"/> 
				    
			    	<a href="javascript:habilitarPesquisaDebitoACobrar(document.forms[0]);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Débito a Cobrar" /></a> 				    
				    
				    <logic:present name="idDebitoACobrarNaoEncontrado">
				  	  <logic:equal name="idDebitoACobrarNaoEncontrado" value="exception">
					    <html:text property="descricaoDebitoACobrar" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
					  </logic:equal>
					  <logic:notEqual name="idDebitoACobrarNaoEncontrado" value="exception">
					    <html:text property="descricaoDebitoACobrar" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					  </logic:notEqual>
					</logic:present> 
					<logic:notPresent name="idDebitoACobrarNaoEncontrado">
					  <logic:empty name="SelecionarDebitosPagosPopupActionForm" property="idDebitoACobrar">
				 	    <html:text property="descricaoDebitoACobrar" value="" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					  </logic:empty>
					  <logic:notEmpty name="SelecionarDebitosPagosPopupActionForm" property="idDebitoACobrar">
		 			    <html:text property="descricaoDebitoACobrar" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					  </logic:notEmpty>
					</logic:notPresent> 
					
	    		    <a href="javascript:limparDebitoACobrar();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /> 
					</a>
				    
			  	</td>
			</tr>
        
	        <tr>
				  <td><strong>Guia de Pagamento:</strong></td>
				  <td width="82%" height="24">
				  
				    <html:text maxlength="9" tabindex="1"	property="idGuiaPagamento" size="9" 
					    onkeyup="document.forms[0].descricaoGuiaPagamento.value='';" 
					    onkeypress="validaEnter(event, '/gsan/exibirSelecionarDebitosPagosPopupAction.do','idGuiaPagamento');"/> 
				    
				    <a href="javascript:habilitarPesquisaGuiaPagamento(document.forms[0]);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Guia de Pagamento" /></a> 			    
				    
				    <logic:present name="idGuiaPagamentoNaoEncontrado">
				  	  <logic:equal name="idGuiaPagamentoNaoEncontrado" value="exception">
					    <html:text property="descricaoGuiaPagamento" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
					  </logic:equal>
					  <logic:notEqual name="idGuiaPagamentoNaoEncontrado" value="exception">
					    <html:text property="descricaoGuiaPagamento" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					  </logic:notEqual>
					</logic:present> 
					<logic:notPresent name="idGuiaPagamentoNaoEncontrado">
					  <logic:empty name="SelecionarDebitosPagosPopupActionForm" property="idGuiaPagamento">
				 	    <html:text property="descricaoGuiaPagamento" value="" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
					  </logic:empty>
					  <logic:notEmpty name="SelecionarDebitosPagosPopupActionForm" property="idGuiaPagamento">
		 			    <html:text property="descricaoGuiaPagamento" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					  </logic:notEmpty>
					</logic:notPresent> 
					
				    <a href="javascript:limparGuiaPagamento();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> 
					</a>
				    
				  </td>
			</tr>
			<tr>
				<td>

					<input type="button" name="Button1"
						class="bottonRightCol" value="Voltar" 
						onclick="window.close();">
	
	            </td>
	            <td  align="right">
	            
					<input type="button" name="Button3"
					class="bottonRightCol" value="Selecionar" tabindex="4"
					onclick="javascript:selecionar();"/>
					
				</td>
			</tr>

      </table>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
