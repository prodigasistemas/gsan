<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.gerencial.cobranca.bean.ResumoPendenciaAcumuladoHelper" %>
<%@ page import="gcom.util.Util" %>
<%@ page import="java.math.BigDecimal" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript"></script>

</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/resultadoResumoPendenciaAction"
  name="ResumoPendenciaActionForm"
  type="gcom.gui.gerencial.cobranca.ResumoPendenciaActionForm"
  method="post"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="135"  valign="top" class="leftcoltext">
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
		<td width="625" valign="top" class="centercoltext">
			<!--In&iacute;cio Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
         	<table>
            	<tr><td></td></tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
					<td class="parabg">Consultar Resumo da Pendência</td>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
				</tr>
            </table>
            <!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
            <p>&nbsp;</p>
			<!-- include com a tabela Dados da Geração da Consulta -->
			<%@ include file="/jsp/gerencial/tabela_dados_geracao_consulta.jsp"%>
			<!-- fim include Dados da Geração da Consulta -->
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td height="0">
						<table width="100%" bgcolor="#99CCFF">
                    		<!--header da tabela interna -->
		                    <%
		                    String cor = "#FFFFFF";
		                    String categoriaAnterior = "";
		                    Integer totalQtdLigacoes = 0;
		                    Integer totalQtdDocumento = 0;
		                    BigDecimal totalValorPendente = new BigDecimal("0.00");
		                    boolean primeiraVez = false;
		                    %>
		                    <logic:present name="resumoPendenciaAcumuladoHelper" scope="session">
							<logic:iterate name="resumoPendenciaAcumuladoHelper" 
								type="ResumoPendenciaAcumuladoHelper" id="resumoPendencia">
		                    <% 
		                    if( ! resumoPendencia.getCategoria().equals(categoriaAnterior) ){ 
		                    	if(!categoriaAnterior.equals("")){
		                    	%>
			                    <tr height="20" bgcolor="#90c7fc">
									<td width="10%"><strong>TOTAL DA PENDÊNCIA</strong></td>
									<td width="10%" align="center"><strong>
										<%= totalQtdLigacoes %></strong>
									</td>
									<td width="10%" align="center"><strong>
										<%= totalQtdDocumento %></strong>
									</td>
									<td width="10%" align="right"><strong>
										<%= Util.formatarMoedaReal(totalValorPendente) %></strong>
									</td>
								</tr>
		                    	<% 
		                    	}
			                    totalQtdLigacoes = 0;
			                    totalQtdDocumento = 0;
			                    totalValorPendente = new BigDecimal("0.00");

			                    if( resumoPendencia.getTipoCategoria().equals("PUBLICO") && !primeiraVez ){
		                    		primeiraVez = true;
		                    %>
		                    <logic:present name="existeParticular" scope="session">
			                    <tr height="20">
	        		            	<td bgcolor="#1888f8" colspan="4">
	        		            		<strong>Categoria: PARTICULAR</strong>
	        		            	</td>
	                		    </tr>
	                    		<tr height="20">
			                    	<td bgcolor="#90c7fc" width="30%" align="center"><strong>Situação Água/Esgoto</strong></td>
			                    	<td bgcolor="#90c7fc" width="10%" align="center"><strong>Ligações</strong></td>
			                    	<td bgcolor="#90c7fc" width="10%" align="center"><strong>Documentos</strong></td>
			                    	<td bgcolor="#90c7fc" width="20%" align="center"><strong>Valor Pendente</strong></td>
			                    </tr>
								<logic:present name="resumoPendenciaParticularPotencial" scope="session">
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>POTENCIAL</strong></td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularPotencial.quantidadeLigacoes}
									</td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularPotencial.quantidadeDocumento}
									</td>
									<td width="10%" align="right">
										<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaParticularPotencial")).getValorPendente()) %>
									</td>
								</tr>
								</logic:present>	
								<logic:present name="resumoPendenciaParticularFactivel" scope="session">
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>FACTÍVEL</strong></td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularFactivel.quantidadeLigacoes}
									</td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularFactivel.quantidadeDocumento}
									</td>
									<td width="10%" align="right">
										<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaParticularFactivel")).getValorPendente()) %>
									</td>
								</tr>	
								</logic:present>	
								<logic:present name="resumoPendenciaParticularLigadoAgua" scope="session">
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>LIGADO DE ÁGUA</strong></td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularLigadoAgua.quantidadeLigacoes}
									</td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularLigadoAgua.quantidadeDocumento}
									</td>
									<td width="10%" align="right">
										<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaParticularLigadoAgua")).getValorPendente()) %>
									</td>
								</tr>	
								</logic:present>	
								<logic:present name="resumoPendenciaParticularCortado" scope="session">
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>CORTADO</strong></td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularCortado.quantidadeLigacoes}
									</td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularCortado.quantidadeDocumento}
									</td>
									<td width="10%" align="right">
										<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaParticularCortado")).getValorPendente()) %>
									</td>
								</tr>	
								</logic:present>	
								<logic:present name="resumoPendenciaParticularLigadoSoEsgoto" scope="session">
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>LIGADO SÓ DE ESGOTO</strong></td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularLigadoSoEsgoto.quantidadeLigacoes}
									</td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularLigadoSoEsgoto.quantidadeDocumento}
									</td>
									<td width="10%" align="right">
										<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaParticularLigadoSoEsgoto")).getValorPendente()) %>
									</td>
								</tr>	
								</logic:present>	
								<logic:present name="resumoPendenciaParticularEsgotoForaUso" scope="session">
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>ESGOTO FORA DE USO</strong></td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularEsgotoForaUso.quantidadeLigacoes}
									</td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularEsgotoForaUso.quantidadeDocumento}
									</td>
									<td width="10%" align="right">
										<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaParticularEsgotoForaUso")).getValorPendente()) %>
									</td>
								</tr>	
								</logic:present>	
								<logic:present name="resumoPendenciaParticularEsgotoTamponado" scope="session">
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>ESGOTO TAMPONADO</strong></td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularEsgotoTamponado.quantidadeLigacoes}
									</td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularEsgotoTamponado.quantidadeDocumento}
									</td>
									<td width="10%" align="right">
										<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaParticularEsgotoTamponado")).getValorPendente()) %>
									</td>
								</tr>	
								</logic:present>	
								<logic:present name="resumoPendenciaParticularSuprimidoTotal" scope="session">
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>SUPRIMIDO TOTAL</strong></td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularSuprimidoTotal.quantidadeLigacoes}
									</td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularSuprimidoTotal.quantidadeDocumento}
									</td>
									<td width="10%" align="right">
										<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaParticularSuprimidoTotal")).getValorPendente()) %>
									</td>
								</tr>	
								</logic:present>	
								<logic:present name="resumoPendenciaParticularSuprimidoParcial" scope="session">
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>SUPRIMIDO PARCIAL</strong></td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularSuprimidoParcial.quantidadeLigacoes}
									</td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularSuprimidoParcial.quantidadeDocumento}
									</td>
									<td width="10%" align="right">
										<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaParticularSuprimidoParcial")).getValorPendente()) %>
									</td>
								</tr>	
								</logic:present>	
								<logic:present name="resumoPendenciaParticularSuprimidoAPedido" scope="session">
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>SUPRIMIDO A PEDIDO</strong></td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularSuprimidoAPedido.quantidadeLigacoes}
									</td>
									<td width="10%" align="center">
										${sessionScope.resumoPendenciaParticularSuprimidoAPedido.quantidadeDocumento}
									</td>
									<td width="10%" align="right">
										<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaParticularSuprimidoAPedido")).getValorPendente()) %>
									</td>
								</tr>	
								</logic:present>	
								<% if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";%>
								<tr height="20" bgcolor="#cbe5fe">
								<%} else {
								cor = "#FFFFFF";%>
								<tr height="20" bgcolor="#FFFFFF">
								<% }%>
									<td width="10%"><strong>TOTAL DA PENDÊNCIA</strong></td>
									<td width="10%" align="center"><strong>
										${sessionScope.resumoPendenciaParticularQuantidadeLigacoes}</strong>
									</td>
									<td width="10%" align="center"><strong>
										${sessionScope.resumoPendenciaParticularQuantidadeDocumento}
									</td>
									<td width="10%" align="right"><strong>
										<%= Util.formatarMoedaReal((BigDecimal)session.getAttribute("resumoPendenciaParticularValorPendente")) %>
									</td>
								</tr>
							</logic:present>	
							<% 
							}
							%>		                    	
		                    <tr height="20">
        		            	<td bgcolor="#1888f8" colspan="4">
        		            		<strong>Categoria: <%= resumoPendencia.getCategoria()%></strong>
        		            	</td>
                		    </tr>
                    		<tr height="20">
		                    	<td bgcolor="#90c7fc" width="30%" align="center"><strong>Situação Água/Esgoto</strong></td>
		                    	<td bgcolor="#90c7fc" width="10%" align="center"><strong>Ligações</strong></td>
		                    	<td bgcolor="#90c7fc" width="10%" align="center"><strong>Documentos</strong></td>
		                    	<td bgcolor="#90c7fc" width="20%" align="center"><strong>Valor Pendente</strong></td>
		                    </tr>
		                    <%
		                    	categoriaAnterior = resumoPendencia.getCategoria();
		                    }
		                    %>
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<%
							}
	                    	totalQtdLigacoes = totalQtdLigacoes + resumoPendencia.getQuantidadeLigacoes();
	                    	totalQtdDocumento = totalQtdDocumento + resumoPendencia.getQuantidadeDocumento();
	                    	totalValorPendente = totalValorPendente.add(resumoPendencia.getValorPendente());
							%>
								<td width="10%"><strong><%= resumoPendencia.getTipoSituacaoAguaEsgoto() %></strong></td>
								<td width="10%" align="center">
									<%= resumoPendencia.getQuantidadeLigacoes() %>
								</td>
								<td width="10%" align="center">
									<%= resumoPendencia.getQuantidadeDocumento() %>
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(resumoPendencia.getValorPendente()) %>
								</td>
							</tr>
						</logic:iterate>
						<% if( ! categoriaAnterior.equals("") ){ %>
				        <tr height="20" bgcolor="#90c7fc">
							<td width="10%"><strong>TOTAL DA PENDÊNCIA</strong></td>
							<td width="10%" align="center"><strong>
								<%= totalQtdLigacoes %></strong>
							</td>
							<td width="10%" align="center"><strong>
								<%= totalQtdDocumento %></strong>
							</td>
							<td width="10%" align="right"><strong>
								<%= Util.formatarMoedaReal(totalValorPendente) %></strong>
							</td>
						</tr>
						<% } %>
						</logic:present>
	                    <logic:present name="existePublico" scope="session">
		                    <tr height="20">
        		            	<td bgcolor="#1888f8" colspan="4">
        		            		<strong>Categoria: PÚBLICO</strong>
        		            	</td>
                		    </tr>
                    		<tr height="20">
		                    	<td bgcolor="#90c7fc" width="30%" align="center"><strong>Situação Água/Esgoto</strong></td>
		                    	<td bgcolor="#90c7fc" width="10%" align="center"><strong>Ligações</strong></td>
		                    	<td bgcolor="#90c7fc" width="10%" align="center"><strong>Documentos</strong></td>
		                    	<td bgcolor="#90c7fc" width="20%" align="center"><strong>Valor Pendente</strong></td>
		                    </tr>
							<logic:present name="resumoPendenciaPublicoPotencial" scope="session">
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<% }%>
								<td width="10%"><strong>POTENCIAL</strong></td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoPotencial.quantidadeLigacoes}
								</td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoPotencial.quantidadeDocumento}
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaPublicoPotencial")).getValorPendente()) %>
								</td>
							</tr>
							</logic:present>	
							<logic:present name="resumoPendenciaPublicoFactivel" scope="session">
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<% }%>
								<td width="10%"><strong>FACTÍVEL</strong></td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoFactivel.quantidadeLigacoes}
								</td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoFactivel.quantidadeDocumento}
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaPublicoFactivel")).getValorPendente()) %>
								</td>
							</tr>	
							</logic:present>	
							<logic:present name="resumoPendenciaPublicoLigadoAgua" scope="session">
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<% }%>
								<td width="10%"><strong>LIGADO DE ÁGUA</strong></td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoLigadoAgua.quantidadeLigacoes}
								</td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoLigadoAgua.quantidadeDocumento}
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaPublicoLigadoAgua")).getValorPendente()) %>
								</td>
							</tr>	
							</logic:present>	
							<logic:present name="resumoPendenciaPublicoCortado" scope="session">
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<% }%>
								<td width="10%"><strong>CORTADO</strong></td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoCortado.quantidadeLigacoes}
								</td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoCortado.quantidadeDocumento}
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaPublicoCortado")).getValorPendente()) %>
								</td>
							</tr>	
							</logic:present>	
							<logic:present name="resumoPendenciaPublicoLigadoSoEsgoto" scope="session">
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<% }%>
								<td width="10%"><strong>LIGADO SÓ DE ESGOTO</strong></td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoLigadoSoEsgoto.quantidadeLigacoes}
								</td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoLigadoSoEsgoto.quantidadeDocumento}
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaPublicoLigadoSoEsgoto")).getValorPendente()) %>
								</td>
							</tr>	
							</logic:present>	
							<logic:present name="resumoPendenciaPublicoEsgotoForaUso" scope="session">
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<% }%>
								<td width="10%"><strong>ESGOTO FORA DE USO</strong></td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoEsgotoForaUso.quantidadeLigacoes}
								</td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoEsgotoForaUso.quantidadeDocumento}
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaPublicoEsgotoForaUso")).getValorPendente()) %>
								</td>
							</tr>	
							</logic:present>	
							<logic:present name="resumoPendenciaPublicoEsgotoTamponado" scope="session">
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<% }%>
								<td width="10%"><strong>ESGOTO TAMPONADO</strong></td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoEsgotoTamponado.quantidadeLigacoes}
								</td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoEsgotoTamponado.quantidadeDocumento}
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaPublicoEsgotoTamponado")).getValorPendente()) %>
								</td>
							</tr>	
							</logic:present>	
							<logic:present name="resumoPendenciaPublicoSuprimidoTotal" scope="session">
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<% }%>
								<td width="10%"><strong>SUPRIMIDO TOTAL</strong></td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoSuprimidoTotal.quantidadeLigacoes}
								</td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoSuprimidoTotal.quantidadeDocumento}
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaPublicoSuprimidoTotal")).getValorPendente()) %>
								</td>
							</tr>	
							</logic:present>	
							<logic:present name="resumoPendenciaPublicoSuprimidoParcial" scope="session">
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<% }%>
								<td width="10%"><strong>SUPRIMIDO PARCIAL</strong></td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoSuprimidoParcial.quantidadeLigacoes}
								</td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoSuprimidoParcial.quantidadeDocumento}
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaPublicoSuprimidoParcial")).getValorPendente()) %>
								</td>
							</tr>	
							</logic:present>	
							<logic:present name="resumoPendenciaPublicoSuprimidoAPedido" scope="session">
							<% if (cor.equalsIgnoreCase("#FFFFFF")) {
							cor = "#cbe5fe";%>
							<tr height="20" bgcolor="#cbe5fe">
							<%} else {
							cor = "#FFFFFF";%>
							<tr height="20" bgcolor="#FFFFFF">
							<% }%>
								<td width="10%"><strong>SUPRIMIDO A PEDIDO</strong></td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoSuprimidoAPedido.quantidadeLigacoes}
								</td>
								<td width="10%" align="center">
									${sessionScope.resumoPendenciaPublicoSuprimidoAPedido.quantidadeDocumento}
								</td>
								<td width="10%" align="right">
									<%= Util.formatarMoedaReal(((ResumoPendenciaAcumuladoHelper)session.getAttribute("resumoPendenciaPublicoSuprimidoAPedido")).getValorPendente()) %>
								</td>
							</tr>	
							</logic:present>	
							<tr bgcolor="#90c7fc" height="20">
								<td width="10%"><strong>TOTAL DA PENDÊNCIA</strong></td>
								<td width="10%" align="center"><strong>
									${sessionScope.resumoPendenciaPublicoQuantidadeLigacoes}</strong>
								</td>
								<td width="10%" align="center"><strong>
									${sessionScope.resumoPendenciaPublicoQuantidadeDocumento}
								</td>
								<td width="10%" align="right"><strong>
									<%= Util.formatarMoedaReal((BigDecimal)session.getAttribute("resumoPendenciaPublicoValorPendente")) %>
								</td>
							</tr>
						</logic:present>	
						</table>
                 	</td>
				</tr>
            </table>
            <table width="100%" border="0">
	            <tr><td><br></td></tr>
                 <tr height="25" align="middle">
                   <td align="left">
	                   <input type="button" class="bottonRightCol" value="Voltar Filtro" onClick="history.back();">
                   </td>
                   <td align="right">
	                   <!--  Vai ser revisto
	                   <input type="button" class="bottonRightCol" value="Todos">&nbsp;
	                   <input type="button" class="bottonRightCol" value="Vencidos">&nbsp;
	                   <input type="button" class="bottonRightCol" value="A Vencer">&nbsp;
                  	   <img  border="0" src="<bean:message key="caminho.imagens"/>print.gif">
	                   -->
                   </td>
                 </tr>  
             </table>    
			<p>&nbsp;</p>
			<!-- Fim do Corpo - Roberta Costa -->
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
