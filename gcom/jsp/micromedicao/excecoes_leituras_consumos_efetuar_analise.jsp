<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script>
<!--
function facilitador(objeto){
	if (objeto.value == "0"){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
	
	capturarSelecao();
}

function gerarRelatorio(){
	var form = document.forms[0];
	
	if (document.getElementById("current") != null){
		paginaCorrente = document.getElementById("current").innerHTML;
	}
	else{
		paginaCorrente = "1";
	}
	
	idRegistrosImovel = obterValorCheckboxMarcado();
	
	if (idRegistrosImovel.length > 0){
		form.action = "gerarRelatorioAnaliseConsumoAction.do?idRegistrosImovel=" + idRegistrosImovel +
		"&paginaCorrente=" + paginaCorrente;
	}
	else{
		form.action = "gerarRelatorioAnaliseConsumoAction.do?paginaCorrente=" + paginaCorrente;
	}
	
	form.submit();
}


function alterarLinkPaginacao(objetoLink, linkDefault, idRegistrosImovel){
	
	if (document.getElementById("current") != null){
		paginaCorrente = document.getElementById("current").innerHTML;
	}
	else{
		paginaCorrente = "1";
	}
	
	if (objetoLink != null){
				
		linkDefaultPartido = linkDefault.split("&");
		 
		if (linkDefaultPartido.length > 1){
			linkFinal = linkDefaultPartido[0] + "&idRegistrosImovel=" 
			+ idRegistrosImovel + "&paginaCorrente=" + paginaCorrente + "');";
		}
		else{
			linkFinal = linkDefault.substring(0, linkDefault.length - 3) + "&idRegistrosImovel=" 
			+ idRegistrosImovel + "&paginaCorrente=" + paginaCorrente + "');";
		}
		
		objetoLink.href = linkFinal;	
	}
}

function linkPaginacaoDefault(objetoLink, linkDefault){
	
	if (document.getElementById("current") != null){
		paginaCorrente = document.getElementById("current").innerHTML;
	}
	else{
		paginaCorrente = "1";
	}
	
	if (objetoLink != null){
				
		linkDefaultPartido = linkDefault.split("&");
		 
		if (linkDefaultPartido.length > 1){
			linkFinal = linkDefaultPartido[0] + "&paginaCorrente=" + paginaCorrente + "');";
		}
		else{
			linkFinal = linkDefaultPartido[0].substring(0, linkDefaultPartido[0].length - 3)  + 
			"&paginaCorrente=" + paginaCorrente + "');";
		}
		
		objetoLink.href = linkFinal;	
	}
}

function linkAnalise(idImovel, idMedicaoTipo, linkAnalise){
	
	if (document.getElementById("current") != null){
		paginaCorrente = document.getElementById("current").innerHTML;
	}
	else{
		paginaCorrente = "1";
	}
	
	idRegistrosImovel = obterValorCheckboxMarcado();
	
	linkFinal = document.forms[0].caminhoReload.value;
    linkFinal = linkFinal + "?idRegistroAtualizacao=" + idImovel + "&medicaoTipo=" + idMedicaoTipo;
    linkFinal = linkFinal + "&linkAnalise=" + linkAnalise;
	
	if (idRegistrosImovel.length > 0){
       	linkFinal = linkFinal + "&idRegistrosImovel=" + idRegistrosImovel + "&paginaCorrente=" + paginaCorrente;
    }
    else{
    	linkFinal = linkFinal + "&paginaCorrente=" + paginaCorrente;
    }
    
    window.location.href = linkFinal;
}

function capturarSelecao(){
	idRegistrosImovel = obterValorCheckboxMarcado();
	
	//NÚMEROS
	continua = 0;
	for(x = 1; continua < 2; x++){
			
			if (document.getElementById("number" + x) != null){
				
				linkDefault = document.getElementById("number" + x) + "";
       			var objetoLink = document.getElementById("number" + x);
       			
       			if (idRegistrosImovel.length > 0){
       				alterarLinkPaginacao(objetoLink, linkDefault, idRegistrosImovel);	
       			}
       			else{
       				linkPaginacaoDefault(objetoLink, linkDefault);
       			}
			}
			else{
				continua++;
			}
	}
		
	//PRÓXIMOS E ANTERIORES
	for(indice = 0; indice < 4; indice++){
		
			switch (indice) { 
    			case 0: 
       
       			linkDefault = document.getElementById("numberNext") + "";
       			var objetoLink = document.getElementById("numberNext"); 		
       			
       			break; 
    			case 1:
    			
    			linkDefault = document.getElementById("next") + "";
    			var objetoLink = document.getElementById("next");		 
       
       			break;
       			case 2: 
       			
       			linkDefault = document.getElementById("previous") + "";
       			var objetoLink = document.getElementById("previous");
       			
       			break; 
    			default:
    			
    			linkDefault = document.getElementById("numberPrevious") + "";
       			var objetoLink = document.getElementById("numberPrevious");
			} 
			
			if (idRegistrosImovel.length > 0){
       			alterarLinkPaginacao(objetoLink, linkDefault, idRegistrosImovel);	
       		}
       		else{
       			linkPaginacaoDefault(objetoLink, linkDefault);
       		}
	}
	
}
-->
</script>
</head>

<logic:present name="telaAnalise">
	<body leftmargin="5" topmargin="5" onload="window.location.href='${requestScope.telaAnalise}';">
</logic:present>

<logic:notPresent name="telaAnalise">
	<body leftmargin="5" topmargin="5">
</logic:notPresent>

<html:form
  action="/removerImovelAction"
  name="LeituraConsumoActionForm"
  type="gcom.gui.micromedicao.LeituraConsumoActionForm"
  method="post"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
   <td width="130"  valign="top" class="leftcoltext">
<input type="hidden" name="caminhoReload" value="/gsan/exibirManterAnaliseExcecoesConsumosAction.do" />
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

           <table height="100%">
            <tr>
             <td></td>
            </tr>
           </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
          		<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
			    <td class="parabg">Dados para Análise da Medição e Consumo</td>
			    <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
		      </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
	     		<tr>
                 	<td colspan="4" height="23"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Imoveis:</strong></font></td>
              	</tr>
             	<tr>
                	<td colspan="4" bgcolor="#000000" height="2"></td>
             	</tr>
              	<tr>
               		<td>
		                <table width="100%" bgcolor="#90c7fc">
		                  <tr bordercolor="#000000">
		                    <td width="6%" bgcolor="#90c7fc" align="center"><strong><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><a
									href="javascript:facilitador(this);" id="0">Todos</a></font></strong></td>
		                    <td width="21%" bgcolor="#90c7fc" align="center"><strong><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">Inscrição</font></strong></td>
		                    <td width="10%" bgcolor="#90c7fc" align="center"><strong><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">Matrícula</font></strong></td>
		                    <td width="12%" bgcolor="#90c7fc" align="center"><strong><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">Perfil</font></strong></td>
		                    <td width="13%" bgcolor="#90c7fc" align="center"><strong><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">Tipo Medição</font></strong></td>
		                    <td width="10%" bgcolor="#90c7fc" align="center"><strong><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">Consumo Faturado</font></strong></td>
		                	<td width="14%" bgcolor="#90c7fc" align="center"><strong><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">Anorm. L. Faturada</font></strong></td>
			                <td width="14%" bgcolor="#90c7fc" align="center"><strong><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">Anorm. Consumo</font></strong></td>
		                  </tr>
				 		</table>
					</td>
				</tr>
               	<tr>
                 	<td> <%--<div style="width: 100%; height: 100%; overflow: auto;">--%>
                    	<table width="100%" bgcolor="#99CCFF">
                      <%--Esquema de paginação--%>
                    <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
                      
                      <%int cont=0;%>
                      
                      <logic:present name="colecaoImovelMedicao">
                      <logic:iterate name="colecaoImovelMedicao" id="imovelMicromedicao">
                        <pg:item>
                          <%
                              cont = cont+1;
                              if (cont%2==0){%>
                                      <tr bgcolor="#cbe5fe">
                           <%}else{ %>
                                      <tr bgcolor="#FFFFFF">                                      
                           <%}%>
                           
                           
                        <td width="6%">
							
							<logic:present name="selecionados">
							
								<% boolean selecionado = false; %>
								
								<logic:iterate name="selecionados" id="imovelSelecionado">
										
									<logic:equal name="imovelSelecionado" value="${imovelMicromedicao.imovel.id}">
									
										<div align="center">
										<html:checkbox property="idRegistrosImovel"
										value="${imovelMicromedicao.imovel.id}" onclick="capturarSelecao();"/></div>		
										
										<SCRIPT>
										
											if (eval(<%=cont - 1%>) == 0){
												eval("document.forms[0].idRegistrosImovel.checked=true");
											}
											else{
												eval("document.forms[0].idRegistrosImovel[<%=cont - 1%>].checked=true");
											}
											
										</SCRIPT>
										
										<% selecionado = true; %>
										
									</logic:equal>
									
								</logic:iterate>
								
								<%if (!selecionado){ %>
								
									<div align="center">
									<html:checkbox property="idRegistrosImovel"
									value="${imovelMicromedicao.imovel.id}" onclick="capturarSelecao();"/></div>
								
								<%} %>
								
							</logic:present>
							
							<logic:notPresent name="selecionados">
							
								<div align="center">
								<html:checkbox property="idRegistrosImovel"
								value="${imovelMicromedicao.imovel.id}" onclick="capturarSelecao();"/></div>
								
							</logic:notPresent>
								
						</td>   
	                           
						<td width="21%">
							<div align="center">
								<logic:present name="indicadorTipoApresentacao">
									<a href="javascript:linkAnalise(${imovelMicromedicao.imovel.id}, ${imovelMicromedicao.medicaoHistorico.medicaoTipo.id}, '/gsan/exibirDadosAnaliseMedicaoConsumoAction.do');">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">${imovelMicromedicao.imovel.inscricaoFormatada}</font>
									</a>
								</logic:present>
								<logic:notPresent name="indicadorTipoApresentacao">
									<a href="javascript:linkAnalise(${imovelMicromedicao.imovel.id}, ${imovelMicromedicao.medicaoHistorico.medicaoTipo.id}, '/gsan/exibirDadosAnaliseMedicaoConsumoResumoAction.do');">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">${imovelMicromedicao.imovel.inscricaoFormatada}</font>
									</a>
								</logic:notPresent>
							</div>
						</td>
						<td width="10%">
							<div align="center"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">${imovelMicromedicao.imovel.id}</font></div>
						</td>
						<td width="12%">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">${imovelMicromedicao.imovel.imovelPerfil.descricao}</font>
						</td>
						<td width="14%">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">${imovelMicromedicao.medicaoHistorico.medicaoTipo.descricao}</font>
						</td>
						<td width="9%">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">${imovelMicromedicao.consumoHistorico.numeroConsumoFaturadoMes}</font>
						</td>
						<td width="14%">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">${imovelMicromedicao.medicaoHistorico.leituraAnormalidadeFaturamento.descricao}</font>
						</td>
						<td width="14%">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">${imovelMicromedicao.consumoHistorico.consumoAnormalidade.descricao}</font>
						</td>                          
                        </tr>
                        </pg:item>
                      </logic:iterate>
                      </logic:present>
                    </table>
  	             <table width="100%">
					<tr>
                         <td colspan="2">
                               <input name="button" type="button" class="bottonRightCol" value="Voltar Filtro" onclick="window.location.href='<html:rewrite page="/exibirFiltrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos"/>'" align="left" style="width: 80px;">
        		         </td>
        		         
        		         <td align="right">
										<input type="button" name="" value="Relatório Análise de Consumo" class="bottonRightCol" 
										onclick="gerarRelatorio();"/>
						 </td>
        		         
        		          <logic:equal name="indicadorImovelCondominio" value="1" scope="session">
	        		         <td>
								   <div align="right"><a href="javascript:toggleBox('demodiv',1);">
								       <img border="0"
										src="<bean:message key="caminho.imagens"/>print.gif"
										title="Imprimir" /> </a></div>
							 </td>
        		         </logic:equal>
                	</tr>
                      </table>
			 <logic:present name="maiorMaximo">
              <%@ include file="/jsp/util/limite_pesquisa.jsp"%>
			 </logic:present>
            <table width="100%" border="0">
				<tr>
					<td align="center">
						<strong><%@ include file="/jsp/util/indice_pager_novo_parametro_url.jsp"%></strong>
					</td>
				</tr>
			</table>
          </pg:pager>
       </table>
       <p>&nbsp;</p>
    </td>
  </tr>
</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioFaturamentoLigacoesMedicaoIndividualizadaAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
	
</body>
</html:form>

<SCRIPT>

	capturarSelecao();

</SCRIPT>

</html:html>
