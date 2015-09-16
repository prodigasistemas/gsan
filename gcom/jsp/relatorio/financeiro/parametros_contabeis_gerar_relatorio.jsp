<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioParametrosContabeisActionForm" />
<script>

	function validaMesAno(form){
		var referenciaContabil = form.referenciaContabil.value;
		
		if ( referenciaContabil != null && referenciaContabil != "" ){		
			if (referenciaContabil.length < 7 || referenciaContabil.substring(2,3) != "/" ||
			    referenciaContabil.substring(0,2) < "01" || referenciaContabil.substring(0,2) > "12") {
				alert("Mês/Ano inválido.");
				return false;
			}
		}
		return true;
	}

	function validarForm() {
		var form = document.forms[0];
		if(form.selecaoRelatorio[0].checked == false 
				&&   form.selecaoRelatorio[1].checked == false &&  form.selecaoRelatorio[2].checked == false){
			alert('Informe Tipo do Relatório');
		}else{
				toggleBox('demodiv',1);

		}	
	}
	
	function limpar(){
	  var form = document.forms[0];
	  
	  form.referenciaContabil.value = '';
	  form.selecaoRelatorio[0].checked = false;
	  form.selecaoRelatorio[1].checked = false;
	//	form.action = 'exibirGerarRelatorioParametrosContabeisAction.do?menu=sim';
	//  form.submit();
	}

</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form 
	action="/gerarRelatorioParametrosContabeisAction.do"
	name="GerarRelatorioParametrosContabeisActionForm"
	type="gcom.gui.relatorio.financeiro.GerarRelatorioParametrosContabeisActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Relátorio dos Par&acirc;metros Cont&aacute;beis </td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relat&oacute;rio dos par&acirc;metros cont&aacute;beis, informe os dados abaixo:</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>					
				
				<tr>
					<td><strong>Tipo do Relat&oacute;rio:<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="selecaoRelatorio" value="F" />Faturamento
					<html:radio property="selecaoRelatorio" value="A" /> Arrecada&ccedil;&atilde;o
					<html:radio property="selecaoRelatorio" value="C" /> Contas a Receber</td>
				</tr>
				
				<tr>
					<td width="26%"><strong>Mês/Ano de Referência:</strong></td>
					<td colspan="2"><html:text property="referenciaContabil" size="7" maxlength="7" onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);"/>
					<strong>&nbsp; </strong>mm/aaaa</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>				
								
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="left"><font color="#FF0000">*</font> Campo
					Obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
					<td colspan="2"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limpar();"/>
					</td>
				
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar" 
							onClick="javascript:validarForm();" />
					</td>
				</tr>				
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioParametrosContabeisAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form>
</body>
</html:html>
