<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServico"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="EncerrarOrdemServicoVencidaActionForm" staticJavascript="false" dynamicJavascript="true"/>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

	function validarForm(){
   		var form = document.forms[0];

    	if (form.tipoOrdem.selectedIndex == -1 || form.tipoOrdem.selectedIndex == 0) {
    		form.tipoOrdem.focus();
    		alert('Infome o Tipo da Ordem.');
    		return;
    	}
    	
    	if (form.quantidadeDias.value == '') {
	    	form.quantidadeDias.focus();
    		alert('Infome a Quantidade de Dias.');
    		return;
    	}

    	if (!validateCaracterEspecial(form)) { return false; }
    	if (!validateInteger(form)) { return false; }
		
		submeterFormPadrao(form);
    }
    
    function caracteresespeciais () { 
		this.aa = new Array("quantidadeDias", "Quantidade de Dias possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 
    
    function IntegerValidations () { 
		this.aa = new Array("quantidadeDias", "Quantidade de Dias deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function origemOrdemServicoInformado(){
	 	var form = document.forms[0];
	 	
		if (form.origemOrdemServico[0].checked){
			
		}else if (form.origemOrdemServico[1].checked){
			
		}else if (form.origemOrdemServico[2].checked){
			
		}else if (form.origemOrdemServico[3].checked){
			
		}else{
			form.origemOrdemServico[0].checked = true;
			
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');" >

	<html:form action="/encerrarOrdemServicoVencidaAction" method="post"
		name="EncerrarOrdemServicoVencidaActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.EncerrarOrdemServicoVencidaActionForm">
	
		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>
		
		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="130" valign="top" class="leftcoltext">
				<div align="center">
				<p align="left">&nbsp;</p>
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
				<td width="600" valign="top" class="centercoltext">
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
						<td class="parabg">Encerrar Ordem de Serviço Vencida</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para Encerrar as Ordens de Serviço Vencidas, informe os dados abaixo:</td>
					</tr>
					<tr>
						<td><strong>Tipo da Ordem:&nbsp;<font color="#FF0000">*</font></strong></td>
						<td align="right">
							<div align="left">
								<strong>
									<html:select property="tipoOrdem">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoTipoServico"
											property="id"
											labelProperty="descricao" />
									</html:select>
								</strong>
							</div>
						</td>
					</tr>
	
					<tr>
						<td><strong>Qtd. de Dias:&nbsp;<font color="#FF0000">*</font></strong></td>
						<td align="left"><html:text property="quantidadeDias" maxlength="5" size="5"></html:text></td>
					</tr>
					
					<tr>
						<td height="10" colspan="3"><hr></td>
					</tr>
					
					<tr>
						<td align="left" colspan="2">
							<strong><font color="#FF0000">*</font></strong>Campos obrigat&oacute;rios
						</td>
					</tr>
	
					<tr>
						<td height="24"><input type="button" class="bottonRightCol"
							value="Limpar"
							onclick="window.location.href='/gsan/exibirFiltrarAcompanhamentoOrdemServicoHidrometro.do?menu=sim';" />
						</td>
	
						<td align="right"><input type="button" name="Button"
							class="bottonRightCol" value="Encerrar"
							onClick="javascript:validarForm()" /></td>
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