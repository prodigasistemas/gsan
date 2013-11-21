<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.operacional.FonteCaptacao"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  
	formName="AtualizarSistemaAbastecimentoActionForm" 
	dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">
	var bCancel = false; 

   	function validateAtualizarSistemaAbastecimentoActionForm(form) {
	   	if (bCancel) 
   			return true; 
       	else 
   	 		return validateCaracterEspecial(form) && validateLong(form); 
	}

    function caracteresespeciais () { 
		this.ab = new Array("fonteCaptacaoId", "Fonte de Captação possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.ab = new Array("fonteCaptacaoId", "Fonte de Captação deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

	function validarForm(){
		
		var form = document.forms[0];
		
		if(form.descricao.value == ""){
			alert("Informe Descrição");
			form.descricao.focus();
		}else if(form.descricaoAbreviada.value == ""){
		
			alert("Informa Descrição Abreviada");
			form.descricaoAbreviada.focus();
		
		}else if (validateAtualizarSistemaAbastecimentoActionForm(form)){
			submeterFormPadrao(form);
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];
		
		if (tipoConsulta == 'fonteCaptacaoId') {
			form.fonteCaptacaoId.value = codigoRegistro;
		  	form.fonteCaptacaoDescricao.value = descricaoRegistro;
		  	form.fonteCaptacaoDescricao.style.color = "#000000";
		}
		
		if (tipoConsulta == 'tipoCaptacao') {
			form.tipoCaptacao.value = codigoRegistro;
		  	form.descricaoTipoCaptacao.value = descricaoRegistro;
		  	form.descricaoTipoCaptacao.style.color = "#000000";
		}
		
	}
  	
  	function limparFonteCaptacao(){
  		var form = document.forms[0];
  	
  		form.fonteCaptacaoId.value = "";
  		form.fonteCaptacaoDescricao.value = "";
  	}
  	
  	function limparTipoCaptacao(){
  		var form = document.forms[0];
  		
  		form.tipoCaptacao.value = "";
  		form.descricaoTipoCaptacao.value = "";
  	}
	
</SCRIPT>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarSistemaAbastecimentoAction" method="post">
	
	<html:hidden name="AtualizarSistemaAbastecimentoActionForm" property="id" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp" %>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
  		<tr>
    		<td width="130" valign="top" class="leftcoltext">
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

	      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        	<tr>
	          		<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
	          		<td class="parabg">Atualizar Sistema de Abastecimento</td>
	          		<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
	        	</tr>
	      	</table>
	      	
      		<p>&nbsp;</p>

	      	<table width="100%" border="0">
		      	<tr>
		      		<td colspan="2">Para alterar o Sistema de Abastecimento, informe os dados abaixo:</td>
		      		<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroSistemaAbastecimentoAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
		      	</tr>
	      	</table>
    
      		<table width="100%" border="0">
		      
		      <tr>
		      	<td height="20"><strong>Descrição:
		      		<font color="#FF0000">*</font></strong>
		      	</td>
		        
		        <td align="right">
					<div align="left">
					<html:text maxlength="20" 
						property="descricao" 
						size="55" 
						tabindex="1"/>

					</div>
				</td>
      		  </tr>
      		  
      		  <tr>
		      	<td height="20"><strong>Descrição Abreviada:
		      		<font color="#FF0000">*</font></strong>
		      	</td>
		        
		        <td align="right">
					<div align="left">
					<html:text maxlength="6" 
						property="descricaoAbreviada" 
						size="7" 
						tabindex="2"/>

					</div>
				</td>
      		  </tr>
      			  		
	  		<tr>
        		<td><strong>Indicador de uso:</strong></td>
        		<td>
					<html:radio property="indicadorUso" value="1"/><strong>Ativo
					<html:radio property="indicadorUso" value="2"/>Inativo</strong>
				</td>
      		</tr>
      		
      		<tr>
					<td><strong>Tipo de Captação:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="3"
							property="tipoCaptacao" 
							size="3"
							onkeyup="somente_numero(this);"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarSistemaAbastecimentoAction.do?idTipoCaptacao=' + document.forms[0].tipoCaptacao.value,'tipoCaptacao','Tipo de Captação');"/>

						<a href="javascript:abrirPopup('exibirPesquisarTabelaAuxiliarAction.do?tela=tipoCaptacao',275,480);">							
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Captação" /></a>
								

						<logic:present name="tipoCaptacaoEncontrado" scope="request">
							<html:text property="descricaoTipoCaptacao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="tipoCaptacaoEncontrado" scope="request">
							<html:text property="descricaoTipoCaptacao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparTipoCaptacao();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
      
			<tr>
					<td><strong>Fonte de Captação:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="4"
							property="fonteCaptacaoId" 
							size="3"
							onkeyup="somente_numero(this);"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarSistemaAbastecimentoAction.do?idFonteCaptacao='+ document.forms[0].fonteCaptacaoId.value,'fonteCaptacaoId','Fonte de Captação');"/>

						<a href="javascript:abrirPopup('exibirPesquisarFonteCaptacaoAction.do?
									idTipoCaptacao='+document.forms[0].tipoCaptacao.value+'&tipo=fonteCaptacao&indicadorUsoTodos=1',275,480);">						
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Fonte de Captação" /></a>
								

						<logic:present name="fonteCaptacaoEncontrada" scope="request">
							<html:text property="fonteCaptacaoDescricao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="fonteCaptacaoEncontrada" scope="request">
							<html:text property="fonteCaptacaoDescricao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparFonteCaptacao();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
			</tr>

	      	<tr>
	      		<td><strong> <font color="#FF0000"></font></strong></td>
	        	<td align="right">
	        		<div align="left"><strong><font color="#FF0000">*</font></strong>
	                    Campos obrigat&oacute;rios</div>
	            </td>
	      	</tr>					
	      	      
      		<tr>
      			<td colspan="2" widt="100%">
      			<table width="100%">
					<tr>
						<tr>
							<td width="40%" align="left">
								<input type="button"
									name="ButtonCancelar" 
									class="bottonRightCol" 
									value="Voltar"
									onClick="window.history.go(-1)"> 
								<input type="button"
									name="ButtonReset" 
									class="bottonRightCol" 
									value="Desfazer"
									onClick="window.location.href='/gsan/exibirAtualizarSistemaAbastecimentoAction.do?idRegistroAtualizacao=<bean:write name="AtualizarSistemaAbastecimentoActionForm" property="id" />'"> 
								<input type="button"
									name="ButtonCancelar" 
									class="bottonRightCol" 
									value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right">
								<input type="button"
									onClick="javascript:validarForm();"
									name="botaoAtualizar" 
									class="bottonRightCol" 
									value="Atualizar">
							</td>
						</tr>
	      			
      			</table>
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
