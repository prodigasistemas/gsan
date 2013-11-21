<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.util.ConstantesSistema"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	<html:javascript staticJavascript="false"  formName="FiltrarHidrometroMarcaActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	
<script language="JavaScript">
  
    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	function setaFocus(){
		var form = document.FiltrarHidrometroMarcaActionForm;
		form.descricaoMarcaHidrometro.focus();
	}
	
	function verificarValorAtualizar(){
		var form = document.forms[0]
       	
       	if (form.indicadorAtualizar.checked == true) {
       		form.indicadorAtualizar.value = '1';
       	} else {
       		form.indicadorAtualizar.value = '';
       	}
       	
	}
	
	function validarForm() {
      var form = document.forms[0];
      
      form.action = 'filtrarHidrometroMarcaAction.do';
	  if(validateFiltrarHidrometroMarcaActionForm(form)){	
	  		submeterFormPadrao(form); 
   	  	} 
	 }
	
	
</script>
</head>



<body leftmargin="5" topmargin="5" onload="verificarChecado('${indicadorAtualizar}');setaFocus();">

<html:form action="/filtrarHidrometroMarcaAction"
	name="FiltrarHidrometroMarcaActionForm"
	type="gcom.gui.micromedicao.hidrometro.FiltrarHidrometroMarcaActionForm"
	method="post"
	onsubmit="return FiltrarHidrometroMarcaActionForm(this);">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Filtrar Marca de Hidrômetro</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>
					<td width="100%" colspan="3">
					<table width="100%">
						<tr>
							<td width="80%">Para filtrar a(s) marca(s) de hidrômetro, informe os dados abaixo:</td>
							<td align="right"><input type="checkbox" name="indicadorAtualizar" value="1"
								onclick="javascript:verificarValorAtualizar();" /><strong>Atualizar</strong>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>Descrição:</strong></td>
					<td><html:text property="descricaoMarcaHidrometro" size="20" maxlength="20"
						tabindex="1" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa"	tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto
						<html:radio	property="tipoPesquisa" tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
					</td>
				</tr>
				
				<tr>
					 <td  width="30%"class="style3"><strong>Descrição Abreviada:</strong></td>
					 <td  width="70%" colspan="2"><html:text property="descricaoAbreviada" size="3" maxlength="3"
						tabindex="1" /></td>
				</tr>
				
				<tr>
					 <td  width="30%"class="style3"><strong>Validade da Revisão:</strong></td>
					 <td  width="70%" colspan="2"><html:text property="validadeRevisao" size="3" maxlength="3"
						tabindex="1" /></td>
				</tr>
				
				<tr>
					<td><strong>Situação:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1"/>Ativo
						<html:radio	property="indicadorUso" tabindex="3" value="2"/>Inativo
						<html:radio	property="indicadorUso" tabindex="4" value=""/>Todos
					</td>
				</tr>
				
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarHidrometroMarcaAction.do?menu=sim'">
					</td>
					<td align="right"><INPUT type="button" onclick="javascript:validarForm();" 
						class="bottonRightCol" 	value="Filtrar" tabindex="9" style="width: 70px;"></td>
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

