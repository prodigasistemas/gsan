<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>


<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>


	function consultarNegativadorMovimentoDados(dado){
			form = document.forms[0];
			form.action  = '/gsan/exibirConsultarNegativadorMovimentoDadosAction.do?idRegistroAtualizacao=' + dado + ' ';
			form.submit()
	
	
	}
</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirFiltrarNegativadorMovimentoAction" method="post"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post">

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
			<td width="602" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
		
        <!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                <td class="parabg">Consultar Movimento de Negativador</td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
<p>&nbsp;</p>
            <table width="100%" border="0" >
              <tr> 
                <td colspan="4"> <table bordercolor="#000000" width="100%" cellspacing="0">
                  <tr>
                    <td width="615"><table width="100%" border="0">
                        <tr bordercolor="#90c7fc">
                          <td colspan="10" bgcolor="#90c7fc"><strong>Movimentos Encontrados </strong></td>
                        </tr>
                        <tr bordercolor="#000000">
                          <td width="23%" bgcolor="#90c7fc"><div align="center"><strong>Negativador</strong></div></td>
                          <td width="15%" bgcolor="#90c7fc"><div align="center"><strong>Tipo do Movimento</strong></div></td>
                          <td width="11%" bgcolor="#90c7fc"><div align="center"><strong>NSA</strong></div></td>
                          <td width="18%" bgcolor="#90c7fc"><div align="center"><strong>Data/Hora 
                            de Processamento </strong></div></td>
                          <td width="16%" bgcolor="#90c7fc"><div align="center"><strong>Qtde. 
                            Registros </strong></div></td>
                          <td width="17%" bgcolor="#90c7fc"><div align="center"><strong>Valor 
                            do D&eacute;bito </strong></div></td>
                        </tr>
                        
                        
                          <%--Esquema de paginação--%>
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />

						
							<logic:present name="collectionNegativadorMovimento">
								<%int cont = 1;%>
								<logic:iterate name="collectionNegativadorMovimento" id="negativadorMovimento">
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
												<logic:present name="negativadorMovimento" property="negativador.cliente" >
												   <bean:write name="negativadorMovimento" property="negativador.cliente.nome" />			
												</logic:present >							
												</div>
											</td>											
										
											<td width="40">
											<div align="center">
											     <logic:equal  name="negativadorMovimento"  property="codigoMovimento"  value="1"> Inclusão		
											     </logic:equal>	
												 <logic:equal name="negativadorMovimento" property="codigoMovimento"  value="2"> Exclusão		
												  </logic:equal> 												
											<div align="center">	
											</td>
											<td width="40">
											<div align="center">
											
											 <a href="javascript:consultarNegativadorMovimentoDados(<bean:write name="negativadorMovimento" property="id"/>);">
											
												<bean:write name="negativadorMovimento" property="numeroSequencialEnvio" />
												
											</a>
											<div align="center">	
											</td>
											<td width="40">
											<div align="center">
												<bean:write name="negativadorMovimento" property="dataProcessamentoEnvio" format="dd/MM/yyyy" />
											<div align="center">	
											</td>
											<td width="40">
											<div align="center">
												<bean:write name="negativadorMovimento" property="numeroRegistrosEnvio" />
											<div align="center">	
											</td>
											<td width="40">
											<div align="center">
												<bean:write name="negativadorMovimento" property="valorTotalEnvio" />
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
        
         
        	<table width="100%" border="0">

				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
				</tr>
<%-- Fim do esquema de paginação --%>
			</table>
      
        
            
			</td>
		</tr>
		
		
		 <tr> 
                <td colspan="4"><div align="left"><strong><font color="#FF0000">
                  <input type="submit" name="Submit223" class="bottonRightCol" value="Voltar Filtro" onClick="javascript:window.location.href='<html:rewrite page="/exibirFiltrarNegativadorMovimentoAction.do?desfazer='S'"/>'">
                </font></strong></div></td>
              </tr>
		
		
		
	</table>  
        
        
                             
        
	
	<!--  
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioPerfilParcelamentoManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
	
	-->
</body>
</html:form>
</html:html>
