<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
	
	
	<script language="JavaScript">
	
		function apertouCancelar(){
		    document.forms[0].reset();
			window.location.href = "/gsan/telaPrincipal.do";
		}
		
		function validarForm(){
			var form = document.forms[0];
			var mesAno = form.mesAno.value;
			var mes = mesAno.substring(0,2);
			var barra = mesAno.substring(2,3);
			var dataValida = true;
						
			if(mesAno.length < 7){
				alert('Mês/ano inválido');
				dataValida = false;
			}else if(mes > 12){
				alert('Mês/ano inválido');
				dataValida = false;
			}else if(barra != "/"){
				alert('Mês/ano inválido');
				dataValida = false;
			}
			
			if(dataValida) {
				toggleBox('demodiv',1);
				form.action = form.action + "&mesAno="+mesAno;
			}
			
		}
		
		function verificaData(event){
			toggleBox('demodiv',0);
			
          	var char = null;
          		if (event.which == null){
				     char= String.fromCharCode(event.keyCode);    // IE
				}else if (event.which != 0 && event.charCode != 0){
				     char= String.fromCharCode(event.which);
				 }   
				 
				if(char != '/'){
					return isCampoNumerico(event);
				}
			
          }
          
	</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirRelatorioAnalisePerdasCreditoAction"
	name="GerarRelatorioAnalisePerdasCreditosActionForm"
	type="gcom.gui.relatorio.cobranca.GerarRelatorioAnalisePerdasCreditosActionForm"
	method="post" onsubmit="validarForm(); return false;">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
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
					<table>
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0" src="imagens/parahead_left.gif"
								alt="" /></td>
							<td class="parabg">Gerar Relatório de Análise de Perdas com Crédito</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" alt="" /></td>
						</tr>
					</table>
					
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para gerar o relatório informe os dados abaixo:</td>
						</tr>
						
						<tr>
							<td width="150"><strong>Mês/Ano de Refência:</strong></td>
							<td colspan="2"><html:text onkeypress="return verificaData(event);" property="mesAno" size="7"  maxlength="7" /> mm/aaaa</td>
						</tr>
						<tr>
							<td><strong> <font color="#FF0000"></font></strong></td>
							<td align="right">
							<div align="left"><strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<div align="left"><input type="button" name="cancelar" onclick="javascritp: apertouCancelar();"
								class="bottonRightCol" value="Cancelar"></div>
							</td>
							<td>
								<input type="button" class="bottonRightCol" onclick="javascript: validarForm();" 
												value="Gerar Resumo" alt="" width="28"
												height="26" />
							</td>
						</tr>
					</table>
	
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAnalisePerdasCreditoAction.do"/> 
	<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
	</body>	
	
</html:html>

