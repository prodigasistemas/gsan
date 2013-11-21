<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	
	/* Valida Form */
	function validaForm(tipoConsumo) {
		
		var form = document.forms[0];
		
		if(validaLimite()){
			var lista = new Array();
	
			lista[0] = tipoConsumo;
			lista[1] = form.limiteInferiorFaixa.value;
			lista[2] = form.limiteSuperiorFaixa.value;
	
			window.opener.recuperarDadosPopup('', lista, 'consumoFaixa');
			window.close();
		}
	}
	
	function validaLimite(){
		
		var form = document.forms[0];
		var msg = "";
		
		if(form.limiteInferiorFaixa.value == ""){
			msg = "Informe Limite Inferior";
			form.limiteInferiorFaixa.focus();
		}else {
			var valorCampo = form.limiteInferiorFaixa.value;
			
			if (valorCampo.length > 0 && (isNaN(valorCampo) || valorCampo.indexOf(',') != -1 ||
				valorCampo.indexOf('.') != -1)){

				msg = "Limite Inferior deve somente conter números positivos.";
				form.limiteInferiorFaixa.focus();
			}		
			

		}
		
		if(form.limiteSuperiorFaixa.value == ""){
			msg = "Informe Limite Superior";
			if(msg == ""){
				msg = "Informe Limite Inferior";
				form.limiteSuperiorFaixa.focus();
			}else{
				msg = msg+"\n"+"Informe Limite Superior";
			}			
		}else{
		
			
			var valorCampo = form.limiteSuperiorFaixa.value;
			
			if (valorCampo.length > 0 && (isNaN(valorCampo) || valorCampo.indexOf(',') != -1 ||
				valorCampo.indexOf('.') != -1)){
				
				if(msg == ""){
					msg = "Limite Superior deve somente conter números positivos.";
					form.limiteSuperiorFaixa.focus();
				}else{
					msg = msg+"\n"+"Limite Superior deve somente conter números positivos.";
				}	
			}
			
			if( (valorCampo*1) < (form.limiteInferiorFaixa.value*1) ){
				if(msg == ""){
					msg = "Faixa inválida";
					form.limiteSuperiorFaixa.focus();
				}else{
					msg = msg+"\n"+"Faixa inválida.";
				}
			}
		}
		
		if(msg != ""){
			alert(msg);
			return false;
		}else{
			return true;
		}
		
	
	}
</script>
</head>
	
<body leftmargin="5" topmargin="5" onload="javascript:resizePageSemLink(500, 300);setarFoco('${requestScope.limiteInferiorFaixa}');">

<html:form action="/emissaoHistogramaAguaDadosInformarAction.do"
	name="EmissaoHistogramaAguaDadosInformarActionForm"
	type="gcom.gui.faturamento.EmissaoHistogramaAguaDadosInformarActionForm"
	method="post">

	<table width="550" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="550" valign="top" class="centercoltext">
			
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
						<td class="parabg">Adicionar Faixa no Consumo para 
							<bean:write property="tituloFaixaConsumo" 
								name="EmissaoHistogramaAguaDadosInformarActionForm"/> 

						</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Preencha os campos para inserir uma faixa no consumo para 
						<bean:write property="subTituloFaixaConsumo" 
							name="EmissaoHistogramaAguaDadosInformarActionForm"/> 
					</td>
					</tr>

					<tr>
						<td width="28%" height="24">
							<strong>Limite Inferior  da Faixa:<font color="#FF0000">*</font>:</strong>
						</td>
						<td>
							<html:text maxlength="6"
								property="limiteInferiorFaixa" 
								size="6" />
						</td>
					</tr>
	
					<tr>
						<td width="28%" height="24">
							<strong>Limite  Superior da Faixa:<font color="#FF0000">*</font>:</strong>
						</td>
						<td>
							<html:text maxlength="6"
								property="limiteSuperiorFaixa" 
								size="6" />
						</td>
					</tr>

					<tr>
						<td> </td>
						<td align="right">
							<div align="left">
								<strong><font color="#FF0000">*</font></strong>
								Campos obrigat&oacute;rios
							</div>
						</td>
					</tr>
					
					<tr>
						<td colspan="1">
							<div align="left">
								<input name="Button" 
									type="button"
									class="bottonRightCol" 
									value="Inserir"
									onClick="javascript:validaForm('<bean:write property="tipoConsumo" name="EmissaoHistogramaAguaDadosInformarActionForm" />');">
								
								<input name="Button2" 
									type="button" 
									class="bottonRightCol"
									value="Fechar"
									onClick="javascript:window.close();"></div>
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
