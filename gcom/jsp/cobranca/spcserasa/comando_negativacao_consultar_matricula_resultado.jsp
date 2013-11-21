<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script>
<!--

function submeterForm(id){
 	window.open("exibirConsultarDadosInclusoesAction.do?idSelecionado=" + id,"mywindow","menubar=0,resizable=1,width=770,height=520")
}	

-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="150" valign="top" class="leftcoltext">
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
		<td width="613" valign="top" class="centercoltext">
			<!--Início Tabela Reference a Páginação da Tela de Processo-->
              <table>

                <tr> 
                  <td></td>
                </tr>
              </table>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                  <td class="parabg">Consulta Comandos de Negativa&ccedil;&atilde;o - Por Matr&iacute;cula de Im&oacute;veis </td>

                  <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
                </tr>
              </table> 
              <!--Fim Tabela Reference a Páginação da Tela de Processo-->
              <p>&nbsp;</p>
              <table width="100%" border="0">
                <tr> 
                  <td colspan="2"><strong>Comandos de Negativa&ccedil;&atilde;o Por Matr&iacute;cula de Im&oacute;veis:</strong></td>

                </tr>
                <tr> 
                  <td colspan="2"><strong></strong> <div align="left"> 
                      <table width="100%" align="center" bgcolor="#99CCFF">
                      <!--corpo da segunda tabela-->
                      <tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
                        <td width="30%" height="36"><div align="center"><strong>Identifica&ccedil;&atilde;o da CI </strong></div></td>
                        <td width="13%"><div align="center"><strong>Negativador</strong></div></td>
                        <td> <div align="center"><strong>Data e Hora de Gera&ccedil;&atilde;o do Comando</strong></div><div align="center"></div> <div align="center"></div></td>
                        <td><div align="center"><strong>Data e Hora de Execu&ccedil;&atilde;o do Comando</strong></div></td>
                        <td width="14%"><div align="center"><strong>Usu&aacute;rio Respons&aacute;vel</strong></div><div align="center"></div></td>
						<td><div align="center"><strong>Ao Menos uma Conta em Nome do Cliente</strong></div><div align="center"></div></td>
                      </tr>
                        
                        <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />
                        

                        <!-- Colocar aqui o inicio da tag de paginação  --> 
                     	<logic:present name="collectionNegativacaoComando">
                     	<%int cont = 1;%>
					 	<logic:iterate name="collectionNegativacaoComando" id="negativacaoComando">
							<pg:item>
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
	                      		<tr bgcolor="#cbe5fe"> 
	                      	<%} else { %>
	                      		<tr bgcolor="#FFFFFF"> 
	                      	<%} %>
	                      			<td bordercolor="#90c7fc">
				                      	<a href="javascript:submeterForm(<bean:write name="negativacaoComando" property="id"/>);">			
											<bean:write name="negativacaoComando" property="descricaoComunicacaoInterna"/>&nbsp;															
										</a>
	                        		</td>
			                        <td bordercolor="#90c7fc" >
			                        	<bean:write name="negativacaoComando" property="negativador.cliente.nome"/>
			                        </td>
			                        <td width="14%" bordercolor="#90c7fc" >
			                        	<bean:write name="negativacaoComando" property="dataHoraComando"/>
			                        </td>
			                        <td width="14%" bordercolor="#90c7fc">
			                        	<bean:write name="negativacaoComando" property="dataHoraRealizacao"/>
			                        </td>
			                        <td bordercolor="#90c7fc">
			                        	<bean:write name="negativacaoComando" property="usuario.nomeUsuario"/>
			                        </td>
			                        <td bordercolor="#90c7fc" align="center">
			                        	<logic:equal name="negativacaoComando" property="indicadorContaNomeCliente" value="1">
			                        	sim
			                        	</logic:equal>
			                        	<logic:equal name="negativacaoComando" property="indicadorContaNomeCliente" value="2">
			                        	não
			                        	</logic:equal>
			                        </td>
		                        </tr>
								</pg:item>
							</logic:iterate>
							</logic:present>
       

                    </table>
                     <!-- 	Colocar aqui o fim da tag de paginação --> 
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
                  <td colspan="2"><strong></strong> <div align="left"><strong><font color="#FF0000"> 
                      </font></strong> </div></td>
                </tr>
                <tr> 
                  <td width="233"><strong><font color="#FF0000">

                    <input name="button" type="button"
							class="bottonRightCol" value="Novo Filtro"
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarComandoNegativacaoAction.do?desfazer=S"/>'"
							align="left" style="width: 80px;">
                  </font></strong></td>
                  <td align="right"><div align="right"><strong> </strong></div>
                  <div align="left"><strong></strong></div>
                    <div align="left"> </div></td>
                </tr>
              </table>
             
      
            
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</div>
</body>


<%@ include file="/jsp/util/telaespera.jsp"%>

<script>

</script>



</html:html>