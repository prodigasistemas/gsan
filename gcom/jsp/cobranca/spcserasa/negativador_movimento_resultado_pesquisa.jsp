<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarNegativadorMovimentoActionForm" dynamicJavascript="false" />
</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(790, 410);">

<table width="750" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="750" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		
		
		
		
		
		 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="11"><img src="imagens/parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Pesquisa de Movimento de Negativadores</td>
          <td width="11"><img src="imagens/parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" cellpadding="2" cellspacing="0" class="centercoltext">
        <tr align="left">
          <td width="8%"  class="linhaBaixoTD"><div align="center"><span class="style1"><strong>Negativador:</strong><strong></strong></span></div></td>
          <td width="92%" class="linhaBaixoTD"><div align="left"><span class="style1">
             
             	
             	<html:text name="negativador" property="cliente.nome" readonly="true" size="50" />
             
          </span></div></td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="2" cellspacing="0" class="centercoltext">
        <tr align="left" bgcolor="#90c7fc"> 
          <td width="16%"><div align="center"><strong>Tipo do Movimento</strong></div></td>
          <td width="43%"><div align="center"><strong>N&uacute;mero Sequencial do Arquivo (NSA)</strong></div></td>
          <td width="41%"><div align="center"><strong>Data de Processamento </strong></div></td>
        </tr>
         
    <%--Esquema de paginação--%>
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />

							<logic:present name="collNegativadorMovimento">
								<%int cont = 1;%>
								<logic:iterate name="collNegativadorMovimento" id="negativadorMovimento">
									<pg:item>
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											
											<td width="20">
												<div align="center">
													
												
												
													<a
													href="javascript:enviarDadosQuatroParametros('<bean:write name="negativadorMovimento" property="id"/>', '<bean:write name="negativadorMovimento" property="negativador.cliente.descricao"/>', '<bean:write name="negativadorMovimento" property="codigoMovimento"/>', 'negativadorMovimento');">
													  <logic:equal  name="negativadorMovimento"  property="codigoMovimento"  value="1"> Inclusão		
													    </logic:equal>	
														<logic:equal name="negativadorMovimento" property="codigoMovimento"  value="2"> Exclusão		
													    </logic:equal> </a>
											 
													
												</div>
											</td>											
										
											<td width="40">
											<div align="center">
												<bean:write name="negativadorMovimento" property="numeroSequencialEnvio"/>
											<div align="center">	
											</td>
											<td width="40">
											<div align="center">
												<bean:write name="negativadorMovimento" property="dataProcessamentoEnvio" format="dd/MM/yyyy"/>
											<div align="center">	
											</td>
											
											
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
       
      

        <tr bordercolor="#cbe5fe">
          <td colspan="6">&nbsp;</td>
        </tr>
        
        </table>
        
        
       	<table width="100%">
				<tr>

					<td>
				
		
					<input name="button" type="button"
						class="bottonRightCol" value="Voltar Pesquisa"
						onclick="window.location.href='<html:rewrite page="/exibirPesquisarNegativadorMovimentoAction.do"/>'"
						align="left" style="width: 120px;">
					
						
						</td>
				
				</tr>
			</table>



			<table width="100%" border="0">

				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
					<%-- Fim do esquema de paginação --%>
				</tr>

			</table>
      
		
		
      </td>
	</tr>

</table>



</body>

</html:html>
