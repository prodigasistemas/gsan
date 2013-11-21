<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<%-- Novos Javascripts --%>

<script language="JavaScript">
   	function fechar() {
   		window.close();
   	}
</script>
</head>

<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(600, 400);">	

	<html:form action="/exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do"
		name="AcompanharRoteiroProgramacaoOrdemServicoActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.AcompanharRoteiroProgramacaoOrdemServicoActionForm"
		method="post">
	
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr> 
	    	<td width="560" valign="top" class="centercoltext">
	    		<table height="100%">
	        		<tr> 
	          			<td></td>
	        		</tr>
	      		</table>
	      		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        		<tr> 
	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
	          			</td>
	          			<td class="parabg">Alertas da Ordem de Servi&ccedil;o Programada</td>
	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
	          			</td>
	        		</tr>
	      		</table>
	      		<p>&nbsp;</p>
	      		<table width="100%" border="0">

					<tr>
						<td>
							<strong> Ordem de Servi&ccedil;o:</strong>
						</td>

						<td colspan="2">
							<strong> 
								<html:text property="idOrdemServico" 
									readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									size="9"
									maxlength="9" />

								<html:text property="descricaoOrdemServico" 
									readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									size="50"
									maxlength="50" />

							</strong>
						</td>
					</tr>
					
			        <tr> 
			          	<td height="24" colspan="3">
			          	
			          		<table width="100%" align="center" bgcolor="#99CCFF">
			              		<!--corpo da segunda tabela-->
			              		<tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
			                		<td width="55%">
			                			<div align="center"><strong>Alerta</strong></div>
			                		</td>
			                		<td width="45%">
			                			<div align="center"><strong>Equipe</strong></div>
			                		</td>
			              		</tr>

			              		<tr bgcolor="#cbe5fe">
			                		<td height="19" bordercolor="#90c7fc" >
			                			<div align="left">Perfil do servi&ccedil;o 
			                			incompat&iacute;vel com o perfil da equipe</div>
			                		</td>
			                		
			                		<td bordercolor="#90c7fc">
			                			<strong> 
											<html:text property="alertaEquipeServico" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="35"
												maxlength="35" />
			                    		</strong>
			                    	</td>
			              		</tr>
			             	 	<c:if test="${not empty requestScope.colecaoAlertaEquipeLogradouro}">
									<tr bgcolor="#FFFFFF">
				                		<td bordercolor="#90c7fc" >
				                			<div align="left">H&aacute; equipes com servi&ccedil;os no logradouro</div>
				                		</td>
	
									    <td>
									    	<html:select property="alertaEquipeLogradouro" tabindex="2">
									      	
									      	<logic:present name="colecaoAlertaEquipeLogradouro">
									       		<c:forEach items="${requestScope.colecaoAlertaEquipeLogradouro}" var="equipe">
									        		<option value="${equipe}">${equipe}</option>
									       		</c:forEach>
									      	</logic:present>
									     	</html:select>
									   	</td>
									</tr>
								</c:if>
			            	</table>
						</td>
					</tr>
					
	        		<tr> 
	          			<td height="27" colspan="5"> 
	          				<div align="right"> 
	              				<input name="Button2" 
	              					type="button" 
	              					class="bottonRightCol" 
	              					value="Fechar" 
	              					onClick="javascript:fechar();">
	            			</div>
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