<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.atendimentopublico.registroatendimento.RAReiteracaoFone"%>
<%@ page import="gcom.atendimentopublico.bean.DadosRAReiteracaoHelper"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
	function fechar(){
  		window.close();
	}
</script>
</head>

<body leftmargin="5" topmargin="5">
<table width="570" border="0" cellspacing="5" cellpadding="0">
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
				<td class="parabg">SOLICITANTE DA REITERAÇÃO DO REGISTRO DE ATENDIMENTO</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>


		<logic:present name="helper" scope="request">
			<table width="100%" border="0">
			
				<tr>
					<td><strong>Protocolo:</strong></td>
					<td colspan="2">
						<html:text name="helper" 
							property="raReiteracao.numeroProtocoloAtendimento"
							size="50" 
							maxlength="50" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
			
				<tr>
					<td width="28%"><strong>Nome do Solicitante:</strong></td>
					<td colspan="2">
						<html:text name="helper" 
							property="nomeSolicitante"
							size="50" 
							maxlength="50" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Cliente Solicitante:</strong></td>
					<td>
						<html:text name="helper" 
							property="idClienteSolicitante"
							size="10" 
							maxlength="10" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</td>
					<td>
						<html:text name="helper" 
							property="nomeClienteSolicitante"
							size="40" 
							maxlength="40" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>Unidade Solicitante:</strong></td>
					<td>
						<html:text name="helper" 
							property="idUnidadeSolicitante"
							size="10" 
							maxlength="10" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</td>
					<td>
						<html:text name="helper" 
							property="nomeUnidadeSolicitante"
							size="40" 
							maxlength="40" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<!--  
				<tr>
					<td><strong>Endereço do Solicitante:</strong></td>
					<td colspan="2">
						<html:textarea name="helper" 
							property="enderecoSolicitante"
							readonly="true"
							style="background-color:#EFEFEF; border:0" cols="45"
							 />
					</td>
				</tr>
				
				<tr>
					<td><strong>Ponto de Referência:</strong></td>
					<td colspan="2">
						<html:text name="helper" 
							property="raReiteracao.pontoReferencia"
							size="50" 
							maxlength="50" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				-->
				<logic:present name="colecaoRAReiteracaoFone">
											
					<tr>
					
						<td><strong>Fone(s) do Solicitante:</strong></td>
					
						<td colspan="2">
							<table width="100%" align="center">
		
								<logic:iterate name="colecaoRAReiteracaoFone" id="fone" type="RAReiteracaoFone">
								<tr>
									
									<td colspan="2">
										<html:text name="fone" 
											property="telefoneFormatado"
											size="25" 
											maxlength="25" 
											readonly="true"
											style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
							</logic:iterate>
		
							</table>
					  	</td>
					</tr>
	
				</logic:present>
				
				<tr>
					<td><strong>Observação:</strong></td>
					<td colspan="2">
						<html:textarea name="helper" property="raReiteracao.observacao" cols="50" rows="4" 
						readonly="true"	style="background-color:#EFEFEF; border:0"/>	
					</td>
				</tr>
			</table>
		</logic:present>
				
		<table width="100%">
			<tr>
				<td colspan="1" align="left">
					<logic:present name="caminhoRetornoTelaConsulta" scope="request">
   						<input name="ButtonVoltar" 
   						type="button" 
   						class="bottonRightCol" 
   						value="Voltar" 
   						onclick="history.back();" style="width: 70px;">
	   				</logic:present>
				</td>
				<td colspan="1" align="right">
					<input name="Button" 
						type="button" 
						class="bottonRightCol" 
						value="Fechar"
						onClick="javascript:fechar();">
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html:html>