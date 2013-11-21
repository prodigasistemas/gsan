<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarAtividadeActionForm"/>

<script>

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
  		var form = document.forms[0];
    	if (tipoConsulta == 'atividade') {
	 	  	form.idAtividade.value = codigoRegistro;
	 	  	form.descricaoAtividade.value = descricaoRegistro; 
 		}	
  	}	

	function whenOnload() {
		if (${requestScope.close != null}) {
			var form = document.forms[0];
			var a = form.idAtividade.value;
			var b = form.atividadeDescricao.value;
			var c = form.atividadeOrdemExecucao.value;
			enviarDadosQuatroParametros(a, b, c, 'atividade'); 
		}
	}
	
	function popupAtividade() {
		redirecionarSubmit('exibirPesquisarAtividadeAction.do?caminhoRetornoTelaPesquisaAtividade=exibirPesquisarServicoTipoAtividadeAction');
	}

	function validarForm() {
		var form = document.forms[0];
		if(validatePesquisarAtividadeActionForm(form)){
			form.method.value = "inserir";
    		form.submit();
		}
	}

	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];
    	form.idAtividade.value = "";
	    form.descricaoAtividade.value = "";
	    form.abreviaturaAtividade.value = "";
	    form.action = 'exibirPesquisarAtividadeAction.do';
		form.submit();
	}  
	
	function limpar() {
		var form = document.forms[0];
    	form.idAtividade.value = "";
	    form.descricaoAtividade.value = "";
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
  action="/exibirPesquisarServicoTipoAtividadeAction"
  method="post"
>
<input type="hidden" name="atividadeDescricao" value="${PesquisarServicoTipoAtividadeActionForm.descricaoAtividade}" />
<input type="hidden" name="atividadeOrdemExecucao" value="${PesquisarServicoTipoAtividadeActionForm.ordemExecucao}" />
<input type="hidden" name="method" value="${"PesquisarServicoTipoAtividadeActionForm.method"}" />
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
          			<td class="parabg">Adicionar Atividades do Tipo de Servi&ccedil;o </td>
          			<td width="14"><img src="imagens/parahead_right.gif" border="0" /></td>
        		</tr>
      		</table>
      		<p>&nbsp;</p>
      		<table width="100%" border="0">
        		<tr> 
          			<td colspan="3">Preencha os campos para inserir um atividades do tipo de servi&ccedil;o:</td>
        		</tr>
        		<tr>
          			<td width="25%" height="24"><strong>Atividade:<span class="style3"><font color="#FF0000">*</font></span></strong></td>
          			<td colspan="2">
          				<strong><span class="style2">
          				<html:text property="idAtividade" 
          					   	   size="6" 
          					       maxlength="6"
							       onkeyup="validaEnterComMensagem(event, 'exibirPesquisarServicoTipoAtividadeAction.do', 'idAtividade', 'Atividade');" />
						<a href="javascript:popupAtividade();">	       
						<img src="imagens/pesquisa.gif" 
							 width="23" 
							 height="21"
							 border="0"
							 title="Pesquisar">
						</a>	 
							 
                  		<c:if test="${not empty atividade}">	 		                  			 
          					<html:text property="descricaoAtividade" readonly="true" style="background-color:#EFEFEF; border:0; color:#000000" size="40" maxlength="40" />
                  		</c:if>
                  		<c:if test="${empty atividade}">	 		                  			 
          					<html:text property="descricaoAtividade" readonly="true" style="background-color:#EFEFEF; border:0; color:#ff0000" size="40" maxlength="40" />
                  		</c:if>
                  		<a href="javascript:limpar();">
          					<img src="imagens/limparcampo.gif" width="23" height="21" border="0" title="Apagar">
          				</a>
          			</td>
		       	</tr>
        		<tr>
          			<td height="24"><span class="style3"><strong>Ordem de Execu&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></span></td>
          			<td height="24" colspan="2">
          				<strong><span class="style2">
            			<html:text property="ordemExecucao" size="6" maxlength="4" />
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
