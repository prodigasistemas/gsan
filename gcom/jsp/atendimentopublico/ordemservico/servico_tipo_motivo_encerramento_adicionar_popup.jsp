<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script>

	function recuperarDadosPopup(descricaoRegistro, tipoConsulta) {
  		var form = document.forms[0];
    	if (tipoConsulta == 'motivoEncerramento') {
	 	  	form.descricaoMotivoEncerramento.value = descricaoRegistro; 
 		}	
  	}	


	function validaForm() {

  		var form = document.forms[0];
  		
	    var motivosEncerramento = form.motivosEncerramento;
	  
	    if (motivosEncerramento.options[0].selected == true) {
	      alert("Motivo de Encerramento selecionado inválido!");
	    }else{
	    	if($("select[@name='motivosEncerramento'] option").is(':selected')){
			form.submit();
		}
		else{
		    	alert("Selecione pelo menos um Motivo de Encerramento");
		
			}
		}
	}
	
	
	function popupM() {
		redirecionarSubmit('ExibirPesquisarServicoTipoMotivoEncerramentoAction.do?caminhoRetornoTelaPesquisaMotivoEncerramento=exibirPesquisarServicoTipoMotivoEncerramentoAction');	
	}

	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];
	    form.descricaoMotivoEncerramento.value = "";
	    form.action = 'exibirPesquisarServicoTipoMotivoEncerramentoAction.do';
		form.submit();
	}  
	
	function limpar() {
		var form = document.forms[0];
	    form.descricaoMotivoEncerramento.value = "";
	}  
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado) {
		if(objetoRelacionado.disabled != true) {
			if (objeto == null || codigoObjeto == null) {
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}	 
	

</script>
</head>

<logic:present name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirInserirServicoTipoAction.do');window.close();">
</logic:present>
<logic:notPresent name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0"
	onload="form.motivosEncerramento.focus();resizePageSemLink(620, 300);">
</logic:notPresent>
				<!-- Teste -->

<html:form 
	action="/pesquisarServicoTipoMotivoEncerramentoAction.do"
	name="PesquisarServicoTipoMotivoEncerramentoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.PesquisarServicoTipoMotivoEncerramentoActionForm"
	method="post" 
>
<input type="hidden" name="method" value="${"PesquisarServicoTipoMotivoEncerramentoActionForm.method"}" />

<table width="586" border="0" cellpadding="0" cellspacing="5">
  	<tr> 
    	<td width="576" valign="top" class="centercoltext"> 
    		<table height="100%">
        		<tr> 
          			<td></td>
        		</tr>
      		</table>
      		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        		<tr> 
				<!-- Teste -->
				
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Adicionar Motivo do Encerramento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="5">Preencha o campo para inserir um motivo de encerramento:</td>
				</tr>
				<tr>
					<td><strong>Motivo de Encerramento:</strong>
					<td colspan="2">
					
						
						<html:select property="motivosEncerramento" multiple="true" size="5"
							style="width: 230px;">
							<logic:present name="collMotivosEncerramentos" >
								<html:option value="-1" disabled="disabled">&nbsp;</html:option>
								
								<logic:notEmpty name="collMotivosEncerramentos">
									<logic:iterate name="collMotivosEncerramentos" id="motivo">
										<option value="${motivo.id}">
											<c:out value="${motivo.descricao}"></c:out>
										</option>
									</logic:iterate>
								</logic:notEmpty>
							</logic:present>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="27" colspan="5">
					<div align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Inserir"
						onclick="javascript:validaForm();">
					</div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>