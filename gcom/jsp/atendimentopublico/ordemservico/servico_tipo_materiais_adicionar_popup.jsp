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
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script>

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
  		var form = document.forms[0];
    	if (tipoConsulta == 'material') {
	 	  	form.idMaterial.value = codigoRegistro;
	 	  	form.descricaoMaterial.value = descricaoRegistro; 
 		}	
  	}	

	function whenOnload() {
		if (${requestScope.close != null}) {
			var form = document.forms[0];
			var a = form.idMaterial.value;
			var b = form.materialDescricao.value;
			var c = form.materialQuantidadePadrao.value;
			enviarDadosQuatroParametros(a, b, c, 'material'); 
		}
	}
	
	function validaObrigatorio(idMaterial){
		if(idMaterial.value == "")
			return false;
		else
			return true;
	}

	function validarForm() {
  		var form = document.forms[0];
		
		if(validaCampoInteiro(form.idMaterial)
			&& validaCampoInteiro(form.quantidadePadrao)
			&& validaObrigatorio(form.idMaterial)){
			
			if(testarCampoValorZero(form.idMaterial, 'Material') 
				&& testarCampoValorZero(form.quantidadePadrao, 'Quantidade Padrão')){
				form.method.value = "inserir";
				form.submit();
			}
			
		}else{
			if(!validaCampoInteiro(form.idMaterial)
				&& !validaCampoInteiro(form.quantidadePadrao)){
				alert('Material deve somente conter números positivos. \n Quantidade Padrão deve somente conter números positivos.');
			}else if(!validaCampoInteiro(form.idMaterial)){
				alert('Material deve somente conter números positivos.');
			}else if(!validaCampoInteiro(form.quantidadePadrao)){
				alert('Quantidade Padrão deve somente conter números positivos.');
			}else if(!validaObrigatorio(form.idMaterial)){
				alert('Material é um campo obrigatório.');
			}
		}
	}
	
	function popupMaterial() {
		redirecionarSubmit('exibirPesquisarMaterialAction.do?caminhoRetornoTelaPesquisaMaterial=exibirPesquisarServicoTipoMaterialAction');	
	}

	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];
    	form.idMaterial.value = "";
	    form.descricaoMaterial.value = "";
	    form.abreviaturaAtividade.value = "";
	    form.action = 'exibirPesquisarAtividadeAction.do';
		form.submit();
	}  
	
	function limpar() {
		var form = document.forms[0];
    	form.idMaterial.value = "";
	    form.descricaoMaterial.value = "";
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

<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(620, 300);setarFoco('${requestScope.nomeCampo}');whenOnload();">
<html:form
  action="/exibirPesquisarServicoTipoMaterialAction"
  method="post"
>
<input type="hidden" name="materialDescricao" value="${PesquisarServicoTipoMaterialActionForm.descricaoMaterial}" />
<input type="hidden" name="materialQuantidadePadrao" value="${PesquisarServicoTipoMaterialActionForm.quantidadePadrao}" />
<input type="hidden" name="method" value="${"PesquisarServicoTipoMaterialActionForm.method"}" />

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
          			<td width="14"><img src="imagens/parahead_left.gif" border="0" /></td>
          			<td class="parabg">Adicionar Materiais do Tipo de Servi&ccedil;o </td>
          			<td width="14"><img src="imagens/parahead_right.gif" border="0" /></td>
        		</tr>
      		</table>
      		<p>&nbsp;</p>
      		<table width="100%" border="0">
        		<tr> 
          			<td colspan="3">Preencha os campos para inserir um material do tipo de servi&ccedil;o:</td>
        		</tr>
        		<tr>
          			<td width="25%" height="24"><strong>Material:<span class="style3"><font color="#FF0000">*</font></span></strong></td>
          			<td colspan="2">
          				<strong>
          				<html:text property="idMaterial" 
          					   	   size="4" 
          					       maxlength="4"
							       onkeyup="validaEnterComMensagem(event, 'exibirPesquisarServicoTipoMaterialAction.do', 'idMaterial', 'Material');" />
						<a href="javascript:popupMaterial();">	       
						<img src="imagens/pesquisa.gif" 
							 width="23" 
							 height="21"
							 border="0"
							 title="Pesquisar">
						</a>	 
                  		<c:if test="${not empty material}">	 		                  			 
          					<html:text property="descricaoMaterial" readonly="true" style="background-color:#EFEFEF; border:0; color:#000000" size="40" maxlength="40" />
                  		</c:if>
                  		<c:if test="${empty material}">	 		                  			 
          					<html:text property="descricaoMaterial" readonly="true" style="background-color:#EFEFEF; border:0; color:#ff0000" size="40" maxlength="40" />
                  		</c:if>
                  		<a href="javascript:limpar();">
          					<img src="imagens/limparcampo.gif" width="23" height="21" border="0" title="Apagar">
          				</a>
          				</strong>
          			</td>
		       	</tr>
        		<tr>
          			<td height="24"><span class="style3"><strong>Quantidade Padr&atilde;o:</strong></span></td>
          			<td height="24" colspan="2">
          				<strong><span class="style2">
            			<html:text property="quantidadePadrao" size="6" maxlength="4" />
            			</span>
          				</strong>
          			</td>
        		</tr>
        		<tr> 
          			<td height="24"><strong> </strong></td>
          			<td colspan="2"><strong><font color="#FF0000">*</font></strong> Campos 
            		obrigat&oacute;rios</td>
        		</tr>
        		<tr> 
          			<td height="27" colspan="2"> 
            		</td>
          			<td width="60%" height="27">
          				<div align="right">
            			<input name="Button3" 
            				   type="button" 
            				   class="bottonRightCol" 
            				   value="Inserir"
            				   onclick="javascript:validarForm();">
          				</div>
          			</td>
        		</tr>
      		</table>
      		<p>&nbsp;</p>
      	</td>
  	</tr>
</table>
</body>
</html:form>
</html:html>
