<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de
Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">


</script>
<script type="text/javascript">
	function validarForm() {		
		var form = document.forms[0];  		
	    var idsMotivoEncerramento = form.idsMotivoEncerramento;
	    if (idsMotivoEncerramento.options[0].selected == true) {
	      alert("Motivo de Encerramento selecionado inválido!");
	    }else{
	    	if($("select[@name='idsMotivoEncerramento'] option").is(':selected')){
		    	form.submit();
	    	}
	    	else{
		    	alert("Selecione pelo menos um Motivo de Encerramento");	    		
	    	}
	    	
		}	  
	}
</script>


</head>


<logic:present name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirAtualizarTipoServicoAction.do');window.close()">
</logic:present>
<html:form action="/exibirPesquisarMotivoDeEncerramentoAction"
	name="PesquisarMotivoDeEncerramentoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.PesquisarMotivoDeEncerramentoActionForm"
	method="post">
	<table width="600" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="576" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="14"><img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Adicionar Motivo de Encerramento do Tipo de
					Serviço</td>
					<td width="14"><img src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td><strong style="width: 200px; height:100px;">Motivo de
					Encerramento: </strong></td>
					<td><html:select property="idsMotivoEncerramento"
						style="width: 350px; height:100px;" multiple="true">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoAtendimentoMotivoEncerramento"
							scope="session">
							<html:options collection="colecaoAtendimentoMotivoEncerramento"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
					<td style="width: 50px; height:50px;">&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 100px; height:50px;">&nbsp;</td>
					<td style="width: 100px; height:50px;">&nbsp;</td>
					<td width="50px;" height="27">
					<div align="right"><input name="Button3" type="button"
						class="bottonRightCol" value="Inserir"
						onclick="javascript:validarForm();"></div>
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
