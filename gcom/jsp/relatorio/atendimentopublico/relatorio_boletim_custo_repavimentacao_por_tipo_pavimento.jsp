<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
		
	function validarForm(){

		var form = document.forms[0];
		
		if(validateGerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm(form)){
			
			toggleBox('demodiv',1);	
		}
	}

  	function limpar(){
  		var form = document.forms[0];
		
		form.idUnidadeRepavimentadora.value = "-1";
		form.indicadorTipoBoletim[0].checked = true;
		form.mesAnoReferenciaGeracao.value = "";
  	}

		  	
</script>

</head>

<body leftmargin="5" topmargin="5" >

<div id="formDiv"><html:form action="/gerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoAction.do"
	name="GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm"
	type="gcom.gui.relatorio.atendimentopublico.ordemservico.GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
					<td class="parabg">Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td style="width: 100%" colspan="3">Para filtrar o boletim de custo de repavimentação por tipo de pavimento, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td style="width: 140px;">
						<strong>Unidade Repavimentadora:<font color="#FF0000">*</font></strong>
					</td>

					<td colspan="2">
						<strong> 
						<html:select property="idUnidadeRepavimentadora" 
									 style="width: 100px;" 
									 tabindex="1">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoUnidadeRepavimentadora" 
										   scope="request">
							   <html:options collection="colecaoUnidadeRepavimentadora"
											 labelProperty="descricao" 
											 property="id"/>
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td style="width: 140px;">
						<strong>Mês e Ano de Referência para Geração:<font color="#FF0000">*</font></strong>
					</td>

					<td colspan="2">
						<html:text  property="mesAnoReferenciaGeracao" 
									size="7" 
									maxlength="7" 
									tabindex="2"
									onkeyup="mascaraAnoMes(this, event);"
									onkeypress="return isCampoNumerico(event);" /> (mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td style="width: 140px;"><strong>Tipo do Boletim:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<html:radio property="indicadorTipoBoletim" value="1" tabindex="3" /> <strong>Analítico</strong>
						<html:radio property="indicadorTipoBoletim" value="2" tabindex="4" /> <strong>Sintético</strong>
					</td>
				</tr>
				
				<tr>
					<td align="center" colspan="2" style="width: 140px;"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
							          	
				<tr>
					<td height="24" style="width: 140px;">
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
							   onClick="javascript:validarForm()" />
					</td>
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoAction.do" />	
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
