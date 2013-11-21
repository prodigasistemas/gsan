<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>

EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarLeituraAnormalidadeActionForm"
	dynamicJavascript="false" />

<script>

 var bCancel = false;

    function validatePesquisarLeituraAnormalidadeActionForm(form) {
        if (bCancel) 
      return true;
      else 
       return validateCaracterEspecial(form);
   }

    function caracteresespeciais () {
     this.aa = new Array("descricao", "A descrição da anormalidade de leitura possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

	function validarForm(form){

		if(validatePesquisarLeituraAnormalidadeActionForm(form)){
    		form.submit();
		}
	}
	
	function limpar(){
		var form = document.forms[0];
		
		form.descricao.value = "";
		
		form.anormalidadeRelativaHidrometro.value = "<%=ConstantesSistema.TODOS.toString()%>";
		form.anormalidadeSemHidrometro.value = "<%=ConstantesSistema.TODOS.toString()%>";
		form.anormalidadeRestritoSistema.value = "<%=ConstantesSistema.TODOS.toString()%>";
		form.anormalidadePerdaTarifaSocial.value = "<%=ConstantesSistema.TODOS.toString()%>";
		form.anormalidadeOrdemServicoAutomatica.value = "<%=ConstantesSistema.TODOS.toString()%>";
	
		form.tipoServico.value = "<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>";
		form.consumoCobradoLeituraNaoInformada.value = "<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>";
		form.consumoCobradoLeituraInformada.value = "<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>";
		form.leituraFaturamentoLeituraNaoInformada.value = "<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>";
		form.leituraFaturamentoLeituraInformada.value = "<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>";
		
	}
	
	function desmarcarTodosRadios(campo) {
	   for ( i = 0; i < document.forms[0].elements[campo].length; i++ ) {
	      document.forms[0].elements[campo][i].checked = false;
	   }
	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(530, 595);">
<html:form action="/pesquisarLeituraAnormalidadeAction"
	method="post"
	onsubmit="return validatePesquisarLeituraAnormalidadeActionForm(this);">
	<table width="500" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="495" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Anormalidade de Leitura</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar uma anormalidade
					de leitura:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}micromedicaoLeituraAnormalidadePesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
				
			<table width="100%" border="0">
				<tr>
					<td width="52%"><strong>Descri&ccedil;&atilde;o da Anormalidade de
					Leitura:</strong></td>
					<td width="48%" colspan="3"><html:text property="descricao"
						size="25" maxlength="25" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td><strong>Anormalidade Relativa a Hidr&ocirc;metro:</strong></td>
									<td align="right">
									<div align="left"><strong> <label> <html:radio
										property="anormalidadeRelativaHidrometro"
										value="<%=ConstantesSistema.SIM.toString()%>" /> Sim</label>
									<label> <html:radio property="anormalidadeRelativaHidrometro"
										value="<%=ConstantesSistema.NAO.toString()%>" /> N&atilde;o</label>
									<label> <html:radio property="anormalidadeRelativaHidrometro"
										value="<%=ConstantesSistema.TODOS.toString()%>" />Todos</label>
									</strong></div>
									</td>
								</tr>
								<tr>
									<td><strong>Anormalidade Aceita para Liga&ccedil;&atilde;o sem
									Hidr&ocirc;metro:</strong></td>
									<td align="right">
									<div align="left"><strong> <label> <html:radio
										property="anormalidadeSemHidrometro"
										value="<%=ConstantesSistema.SIM.toString()%>" /> Sim</label>
									<label> <html:radio property="anormalidadeSemHidrometro"
										value="<%=ConstantesSistema.NAO.toString()%>" /> N&atilde;o</label>
										<label> <html:radio property="anormalidadeSemHidrometro"
										value="<%=ConstantesSistema.TODOS.toString()%>" /> Todos</label>
									</strong></div>
									</td>
								</tr>
								<tr>
									<td><strong>Anormalidade de Uso Restrito do Sistema:</strong></td>
									<td align="right">
									<div align="left"><strong> <label> <html:radio
										property="anormalidadeRestritoSistema"
										value="<%=ConstantesSistema.SIM.toString()%>" /> Sim</label>
									<label> <html:radio property="anormalidadeRestritoSistema"
										value="<%=ConstantesSistema.NAO.toString()%>" /> N&atilde;o</label>
										<label> <html:radio property="anormalidadeRestritoSistema"
										value="<%=ConstantesSistema.TODOS.toString()%>" /> Todos</label>
									</strong></div>
									</td>
								</tr>
								<tr>
									<td><strong>Anormalidade Acarreta Perda Tarifa Social:</strong></td>
									<td align="right">
									<div align="left"><strong> <label> <html:radio
										property="anormalidadePerdaTarifaSocial"
										value="<%=ConstantesSistema.SIM.toString()%>" /> Sim</label>
									<label> <html:radio property="anormalidadePerdaTarifaSocial"
										value="<%=ConstantesSistema.NAO.toString()%>" /> N&atilde;o</label>
										<label><html:radio property="anormalidadePerdaTarifaSocial"
										value="<%=ConstantesSistema.TODOS.toString()%>" /> Todos</label>
									</strong></div>
									</td>
								</tr>
								<tr>
									<td><strong>Anormalidade Emite OS Autom&aacute;tica:</strong></td>
									<td align="right">
									<div align="left"><strong> <label> <html:radio
										property="anormalidadeOrdemServicoAutomatica"
										value="<%=ConstantesSistema.SIM.toString()%>" /> Sim</label>
									<label> <html:radio
										property="anormalidadeOrdemServicoAutomatica"
										value="<%=ConstantesSistema.NAO.toString()%>" /> N&atilde;o</label>
										<label><html:radio
										property="anormalidadeOrdemServicoAutomatica"
										value="<%=ConstantesSistema.TODOS.toString()%>" /> Todos</label>
									</strong></div>
									</td>
								<tr>
									<td><strong>Tipo de Servi&ccedil;o da OS Autom&aacute;tica: </strong></td>
									<td align="right">
									<div align="left"><html:select property="tipoServico">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									</html:select></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td><strong>Consumo a Ser Cobrado (leitura n&atilde;o
									informada):</strong></td>
									<td align="right">
									<div align="left"><html:select
										property="consumoCobradoLeituraNaoInformada">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="leiturasAnormalidadesConsumo"
											labelProperty="descricaoConsumoACobrar" property="id" />
									</html:select></div>
									</td>
								</tr>
								<tr>
									<td><strong>Consumo a Ser Cobrado (leitura informada):</strong></td>
									<td align="right">
									<div align="left"><html:select
										property="consumoCobradoLeituraInformada">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="leiturasAnormalidadesConsumo"
											labelProperty="descricaoConsumoACobrar" property="id" />
									</html:select></div>
									</td>
								</tr>
								<tr>
									<td><strong>Leitura para faturamento (leitura n&atilde;o
									informada): </strong></td>
									<td align="right">
									<div align="left"><html:select
										property="leituraFaturamentoLeituraNaoInformada">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="leiturasAnormalidadesLeitura"
											labelProperty="descricaoFaturamento" property="id" />
									</html:select></div>
									</td>
								</tr>
								<tr>
									<td><strong>Leitura para faturamento (leitura informada):</strong></td>
									<td align="right">
									<div align="left"><html:select
										property="leituraFaturamentoLeituraInformada">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="leiturasAnormalidadesLeitura"
											labelProperty="descricaoFaturamento" property="id" />
									</html:select></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="24"><input type="button" name="Button" class="bottonRightCol"
						value="Limpar" onClick="javascript:limpar();" /></td>
					<td align="right"><input type="button" name="Button" class="bottonRightCol"
						value="Pesquisar" onClick="javascript:validarForm(document.forms[0])" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
