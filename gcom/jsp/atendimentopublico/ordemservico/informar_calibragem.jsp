<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<!-- <title>GCOM - Sistema de Gest&atilde;o Comercial</title> -->
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false" formName="InformarCalibragemActionForm" dynamicJavascript="false" />
<script language="JavaScript">

var bCancel = false;

function validarForm(form){
    form.action = "atualizarInformarCalibragemAction.do";
	form.submit();
}

function carregarGridProgramacaoCalibragem(){
 	var form = document.forms[0];
	if ( form.idPriorizacaoTipo.value != null ) {
    	form.action = "exibirInformarCalibragemAction.do?priorizacaoTipo=" + form.idPriorizacaoTipo.value;
	    form.submit();
	}
}

</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirInformarCalibragemAction"
	name="InformarCalibragemActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InformarCalibragemActionForm"
	method="post"
	onsubmit="return informarCalibragemActionForm(this);">

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
					<td width="620" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11">
								<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Informar Calibragem</td>
							<td width="11">
								<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					<table bordercolor="#000000" width="100%" cellspacing="0">
						<tr height="30">
						<td colspan="2">
							<p>Para atualizar uma calibragem selecione o tipo de priorização abaixo:</p>
						</td>
						</tr>
						<tr height="30">
							<td width="20%"><strong>Tipo de Prioriza&ccedil;&atilde;o:</strong></td>
							<td colspan="1" align="left">
								<html:select property="idPriorizacaoTipo" onchange="carregarGridProgramacaoCalibragem();">
									<html:options collection="colecaoPriorizacaoTipo" labelProperty="descricaoPriorizacao" property="id" />
								</html:select>
							</td>
						</tr>
	       		 	</table>
	       		 	<br>
	       		 	<table width="100%" cellpadding="0" cellspacing="0" border="0">
	       		 		<tr>
	       		 		<td>
	       		 			<table bgcolor="#99CCFF" border="0">
								<tr bordercolor="#000000">
									<td width="215" bgcolor="#90c7fc" align="center"><strong>Grau Import&acirc;ncia</strong></td>
									<td width="300" bgcolor="#90c7fc" align="center"><strong>Faixa In&iacute;cio</strong></td>
									<td width="300" bgcolor="#90c7fc" align="center"><strong>Faixa Fim</strong></td>
									<td width="300" bgcolor="#90c7fc" align="center"><strong>Peso</strong></td>
									<td width="300" bgcolor="#90c7fc" align="center"><strong>Fator</strong></td>
								</tr>
							</table>
						</td>
						</tr>
						<tr>
						<td>
							<table bgcolor="#99CCFF" border="0">
	       		 				<logic:present name="colecaoProgramaCalibragem">
	       		 				<%int cont = 0;%>
	       		 				<logic:iterate name="colecaoProgramaCalibragem" id="programaCalibragem">
		       		 			<%cont = cont + 1;
								if (cont % 2 == 0) {%>	
		       		 				<tr bgcolor="#cbe5fe">
		       		 			<%} else {%>
		       		 				</tr>
	                          		<tr bgcolor="#FFFFFF">
	                          	<%}%>
	                            	<td width="10%">
	                            	<div align="center">
	                            		${programaCalibragem.grauImportancia}
	                            	</div>
	                            	</td>
	                            	<td width="10%">
	                            	<div align="center">
	                            		${programaCalibragem.faixaInicial}
	                            	</div>
	                            	</td>
	                            	<td width="10%">
	                            	<div align="center">
	                            		${programaCalibragem.faixaFinal}
	                            	</div>
	                            	</td>
	                            	<td width="10%">
	                            	<div align="center">
	                            		<input type="text" onkeypress="javascript:return isCampoNumerico(event);" name="peso_${programaCalibragem.id}" id="peso_${programaCalibragem.id}" maxlength="2" size="7" value="${programaCalibragem.peso}"/>
	                            	</div>
	                            	</td>
	                            	<td width="10%">
	                            	<div align="center">
	                            		<input type="text" onkeypress="javascript:return isCampoNumerico(event);" name="fator_${programaCalibragem.id}" id="fator_${programaCalibragem.id}" maxlength="2" size="7" value="${programaCalibragem.fator}"/>
	                            	</div>
	                            	</td>
	                            	</tr>
	                            </logic:iterate>
	                    		</logic:present>
	                    		</td>
	                    		</tr>
	       		 			</table>
	       		 		</td>
	       		 		</tr>
	       		 		<tr height="50">
							<td>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
								<td align="left">
									<input name="button" type="button"
												class="bottonRightCol" value="Cancelar" 
											   	onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
								</td>
								<td align="right">
									<input name="button" type="button"
											class="bottonRightCol" value="Atualizar" onclick="validarForm(document.forms[0]);">
								</td>
								</tr>
							</table>
							</td>
						</tr>
	       		 	</table>
				</div>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>

</html:form>
</html:html>
