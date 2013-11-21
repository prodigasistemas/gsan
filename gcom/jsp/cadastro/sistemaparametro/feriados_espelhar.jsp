<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="EspelharFeriadosActionForm" />
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">
	 
	 function validarForm() {
       var form = document.forms[0];

	   if(validateEspelharFeriadosActionForm(form)){
	     if ( confirm( "Todos os fériados cadastrados previamente para o ano de destino, e tipo selecionados serão excluidos. Deseja continuar ?" ) ){
		 	submeterFormPadrao(form); 
		 }
   	   }  		  
	 }
	 
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/espelharFeriadosAction"
	name="EspelharFeriadosActionForm"
	type="gcom.gui.cadastro.sistemaparametro.EspelharFeriadosActionForm"
	method="post"
	onsubmit="return validateEspelharFeriadosActionForm(this);">


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
					<td class="parabg">Espelho dos feriados existentes</td>
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
							<td width="80%">Para espelhar o(s) feriado(s), informe os dados
							abaixo:</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td width="30%" class="style3"><strong>Tipo do Feriado:<font color="#FF0000">*</font></strong></td>
					<td width="70%" colspan="2" >  
						<html:radio  property="indicadorTipoFeriado" onkeyup="javascript:bloquearMunicipio();" 
				 			value="1" tabindex="1" /><strong>Nacional
						<html:radio  property="indicadorTipoFeriado" onkeyup="javascript:bloquearMunicipio();" 
							value="2" tabindex="2" />Municipal
						<html:radio  property="indicadorTipoFeriado" onkeyup="javascript:bloquearMunicipio();" 
							value="3" tabindex="3" /><strong>Todos </strong>
					</td>
				</tr>
				
				<tr> 
	           		<td width="30%" class="style3"><strong>Ano de origem:<font color="#FF0000">*</font></strong></td>
					<td width="70%" colspan="2">
						<html:text	property="anoOrigemFeriado" size="4" maxlength="4" tabindex="4"/> 
					</td>
	           	</tr>	           	
				<tr> 
	           		<td width="30%" class="style3"><strong>Ano de destino:<font color="#FF0000">*</font></strong></td>
					<td width="70%" colspan="2">
						<html:text	property="anoDestinoFeriado" size="4" maxlength="4" tabindex="5"/> 
					</td>
	           	</tr>
	           	
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>	           	
	           	
				<tr>
					<td>
						<input name="Button" type="button" class="bottonRightCol" value="Voltar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarFeriadoAction.do?menu=sim'" tabindex="8">
						<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirEspelharFeriadosAction.do?menu=sim'" tabindex="9">
					</td>					
					
					<td align="right" colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Espelhar feriados" align="right" tabindex="10" onclick="javascript:validarForm();">
					</td>
					
					<td align="right"></td>
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

