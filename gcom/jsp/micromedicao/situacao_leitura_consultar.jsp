<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">


</script>

</head>

<body leftmargin="0" topmargin="0"
		onload="window.focus();resizePageSemLink(800,535);">

<html:form action="/exibirConsultarSituacaoLeituraPopupAction" 
	name="ConsultarArquivoTextoLeituraActionForm"
	type="gcom.gui.micromedicao.ConsultarArquivoTextoLeituraActionForm"
	method="post">
	
	<table width="702" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="100%" valign="top" class="centercoltext">
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
					<td class="parabg">Situação de Leitura</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
					<td width="10%" bgcolor="#90c7fc">
					<strong>Descrição</strong>				
					</td>
					<td width="10%" bgcolor="#90c7fc">
					<strong>Enviados</strong>
					</td>	
					<td width="10%" bgcolor="#90c7fc">
					<strong>Recebidos</strong>
					</td>	
					<td width="10%" bgcolor="#90c7fc">
					<strong>Diferença</strong>
					</td>				
				</tr>
				<tr bgcolor="#FFFFFF" class="styleFontePequena">
					<td colspan="1">
					Imóveis Medidos
					</td>
					<td colspan="1" >
					<bean:write name="helper" property="medidosEnviados" scope="request"/>
					</td>
					<td colspan="1">
					<bean:write name="helper" property="medidosRecebidos" scope="request"/>
					</td>
					<td colspan="1">
					<bean:write name="helper" property="diferencaMedidosEnvRec" scope="request"/>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF" class="styleFontePequena">
					<td colspan="1">
					Imóveis Não Medidos
					</td>
					<td colspan="1">
					<bean:write name="helper" property="naoMedidosEnviados" scope="request"/>
					</td>
					<td colspan="1">
					<bean:write name="helper" property="naoMedidosRecebidos" scope="request"/>
					</td>
					<td colspan="1">
					<bean:write name="helper" property="diferencanaoMedidosEnvRec" scope="request"/>
					</td>
				</tr>
				<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
					<td width="10%" bgcolor="#90c7fc">
					</td>	
					<td width="10%" bgcolor="#90c7fc">
					<strong>Impressos</strong>
					</td>	
					<td width="10%" bgcolor="#90c7fc">
					<strong>Não Impressos</strong>
					</td>		
					<td width="10%" bgcolor="#90c7fc">
					</td>		
				</tr>
				<tr bgcolor="#FFFFFF" class="styleFontePequena">
					<td colspan="1">
					Imóveis Medidos
					</td>
					<td colspan="1">
					<bean:write name="helper" property="medidosImpressos" scope="request"/>
					</td>
					<td colspan="2">
					<bean:write name="helper" property="medidosNaoImpressos" scope="request"/>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF" class="styleFontePequena">
					<td colspan="1">
					Imóveis Não Medidos
					</td>
					<td colspan="1">
					<bean:write name="helper" property="naoMedidosImpressos" scope="request"/>
					</td>
					<td colspan="2">
					<bean:write name="helper" property="naoMedidosNaoImpressos" scope="request"/>
					</td>
				</tr>
				<logic:notEmpty name="helper" property="motivoFinalizacao" scope="request">
					<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
						<td width="10%" bgcolor="#90c7fc">
						<strong>Motivo Finalização</strong>				
						</td>
					</tr>
					<tr bgcolor="#FFFFFF" class="styleFontePequena">
						<td colspan="4">
						<bean:write name="helper" property="motivoFinalizacao" scope="request"/>
						</td>
					</tr>
				</logic:notEmpty>
				
		<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
			<td width="20%" bgcolor="#90c7fc">
				<strong>Anormalidades Informadas</strong>				
			</td>
			<td colspan="4" bgcolor="#90c7fc">
				<strong>Quantidade</strong>				
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="styleFontePequena">
			<td>&nbsp;</td>					
			
			<td colspan="3">
				<bean:write name="helper" property="anormalidades" scope="request"/>
			</td>
		</tr>
											
		<tr>
			<td colspan="4">
				<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">

					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Im&oacute;veis N&atilde;o Recebidos</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
			</td>
		</tr>
				
		<tr>
			<td colspan="4">
				<logic:notEmpty name="colImoveisFaltandoHelper" scope="request">				
					<table width="100%" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
							<td width="10%" bgcolor="#90c7fc">
								<strong>Matr&iacute;cula</strong>				
							</td>
							<td align="center" width="10%">
								<strong>Medido</strong>
							</td>							
							<td width="80%" bgcolor="#90c7fc">
								<strong>Endere&ccedil;o</strong>				
							</td>			
						</tr>
					</table>		
					<div style="height: 200px;overflow: auto;">
						<table width="100%" bgcolor="#99CCFF" border="0">
							<%  int cont = 0;%>										
							<logic:iterate name="colImoveisFaltandoHelper" id="imovelFaltanto">							
							<%	cont = cont + 1;
								if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
							<%	} else {	%>
								<tr bgcolor="#FFFFFF">
							<%	}	%>
									<td align="center" width="10%">
										<bean:write name="imovelFaltanto" property="matricula"/>								
									</td>
									
									<td align="center" width="10%">
										<bean:write name="imovelFaltanto" property="medido"/>								
									</td>									
					
									<td align="center" width="80%">
										<bean:write name="imovelFaltanto" property="endereco"/>
									</td>
								</tr>											
							</logic:iterate>									
						</table>					
					</div>					
				</logic:notEmpty>					
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
