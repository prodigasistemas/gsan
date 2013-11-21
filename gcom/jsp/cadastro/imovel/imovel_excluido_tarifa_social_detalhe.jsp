<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
</head>

<body leftmargin="5" topmargin="5">

<html:form
  action="/removerImovelAction"
  name="ImovelOutrosCriteriosActionForm"
  type="gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm"
  method="post"
>

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
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Consultar Dados de Imóvel Excluído da Tarifa Social</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
       </table>
            <table width="100%" cellpadding="0" cellspacing="0">
              <tr>
                <td colspan="4" bgcolor="#000000" height="2"></td>
              </tr>
		      <tr>
                <td colspan="4" bgcolor="#cbe5fe" height="2"><strong>Dados do Usuário</strong></td>
              </tr>
              <tr colspan="4">
              	<td>
                  <table width="100%" bgcolor="#90c7fc">
	              	<tr bordercolor="#000000">
	                    <td width="172" bgcolor="#90c7fc" align="center"><strong>Nome</strong></td>
	                    <td width="105" bordercolor="#000000" bgcolor="#90c7fc" align="center" ><div align="center"><strong>CPF</strong></div></td>
	                    <td width="90" bgcolor="#90c7fc" align="center"><strong>RG</strong></td>
	                    <td width="90" bgcolor="#90c7fc" align="center"><strong>Data Emissão</strong></td>
	                    <td width="90" bgcolor="#90c7fc" align="center"><strong>Orgão Expedidor</strong></td>
	                    <td width="62" bgcolor="#90c7fc" align="center"><strong>Estado</strong></td>
	                  </tr>
	                  <tr bgcolor="#cbe5fe">
	                  	<td align="center">${requestScope.imovel.clienteNome}</td>
	                  	<td align="center">${requestScope.imovel.cpfFormatado}</td>
	                  	<td align="center">${requestScope.imovel.clienteRg}</td>
	                  	<td align="center"><bean:write name="imovel" property="clienteDataEmissaoOrgaoRg" scope="request" formatKey="date.format"/></td>
	                  	<td align="center">${requestScope.imovel.clienteEmissaoOrgaoRg}</td>
	                  	<td align="center">${requestScope.imovel.clienteUf}</td>
	                  </tr>
                  </table>
                </td>
              </tr>
              <tr>
              	<td colspan="4"><hr></td>
              </tr>
              <tr>
              	<td colspan="4"><strong>Dados do(s) Proprietário(s):</strong></td>
              </tr>
              <tr>
               <td colspan="4">
                <table width="100%" bgcolor="#90c7fc">
                  <tr bordercolor="#000000">
                    <td width="172" bgcolor="#90c7fc" align="center"><strong>Nome</strong></td>
                    <td width="105" bordercolor="#000000" bgcolor="#90c7fc" align="center" ><div align="center"><strong>CPF</strong></div></td>
                    <td width="90" bgcolor="#90c7fc" align="center"><strong>RG</strong></td>
                    <td width="90" bgcolor="#90c7fc" align="center"><strong>Data Emissão</strong></td>
                    <td width="90" bgcolor="#90c7fc" align="center"><strong>Orgão Expedidor</strong></td>
                    <td width="62" bgcolor="#90c7fc" align="center"><strong>Estado</strong></td>
                  </tr>
		 </table>
		</td>
	       </tr>
               <tr>
                 <td><div style="width: 100%; height: 50; overflow: auto;">
                    <table width="100%" bgcolor="#99CCFF">
                      <%int cont=0;%>
                      <logic:iterate name="colecaoClienteProprietario" id="cliente">
                          <%
                              cont = cont+1;
                              if (cont%2==0){%>
                                      <tr bgcolor="#FFFFFF">
                           <%}else{ %>
                                      <tr bgcolor="#cbe5fe">
                           <%}%>
                          <td width="172" align="center">
                            <div align="center">
                            	${cliente.clienteNome}
                            </div>
                          </td>
                          <td width="105" align="center">
                            <div align="center">
                            	${cliente.cpfFormatado}
                            </div>
                          </td>
                          <td width="90" align="center">
                               ${cliente.clienteRg}
                          </td>
                          <td width="90" align="center">
                            <div align="center">
	                            <bean:write name="cliente" property="clienteDataEmissaoOrgaoRg" formatKey="date.format"/>
                            </div>
                          </td>
                           <td width="90" align="center">
                            <div align="center">
                            	${cliente.clienteEmissaoOrgaoRg}
                            </div>
                          </td>
                           <td width="62" align="center">
                            <div align="center">
                            	${cliente.clienteUf}
                            </div>
                        </tr>
                      </logic:iterate>
                    </table>
                    </div>
                 </td>
                 </tr>
	       <tr>
	       		<td>
	       			<hr>
	       		</td>
	       </tr>
                 <tr>
                 	<td>
	  	             <table width="100%">
  		             	<tr>
  	    	         	  <td colspan="2"><strong>Dados da Tarifa Social:</strong></td>
  	        	     	</tr>
	  	             	<tr>
	  	             		<td>Data de Implantação:</td>
	  	             		<td><input type="text" size="15" name="dtImplantacao" style="background-color:#EFEFEF; border:0; color: #000000" value="<bean:write name="imovel" property="dataImplantacao" scope="request" formatKey="date.format"/>"></td>
	  	             	</tr>
	  	             	<tr>
	  	             		<td>Data de Exclusão:</td>
	  	             		<td><input type="text" size="15" name="dtExclusao" style="background-color:#EFEFEF; border:0; color: #000000" value="<bean:write name="imovel" property="dataExclusao" scope="request" formatKey="date.format"/>"></td>
	  	             	</tr>
	  	             	<tr>
	  	             		<td>Motivo da Exclusão:</td>
	  	             		<td><input type="text" size="50" name="motivoExclusao" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.imovel.motivoExclusao}"></td>
	  	             	</tr>
	  	             	<tr>
	  	             		<td>Data do Último Recadastramento:</td>
	  	             		<td><input type="text" size="15" name="dtRecadastramento" style="background-color:#EFEFEF; border:0; color: #000000" value="<bean:write name="imovel" property="ultimoRecadastramento" scope="request" formatKey="date.format"/>"></td>
	  	             	</tr>
	  	             	<tr>
	  	             		<td>Número de Recadastramentos:</td>
	  	             		<td><input type="text" size="5" name="nmRecadastramento" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.tarifaEconomia.numeroRecadastramento}"></td>
	  	             	</tr>
	  	             	<tr>
	  	             		<td>Área construída do imóvel:</td>
	  	             		<td><input type="text" size="20" name="AreaConstruida" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.tarifaEconomia.areaConstruida}"></td>
	  	             	</tr>
	  	             	<tr>
	  	             		<td>Número de IPTU:</td>
	  	             		<td><input type="text" size="20" name="nmIptu" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.tarifaEconomia.numeroIptu}"></td>
	  	             	</tr>
	  	             	<tr>
	  	             		<td>Contrato Companhia de Energia:</td>
	  	             		<td><input type="text" size="20" name="contratoCelpe" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.tarifaEconomia.numeroCelpe}"></td>
	  	             	</tr>
	  	             	<tr>
	  	             		<td>Consumo Médio Companhia de Energia:</td>
	  	             		<td><input type="text" size="10" name="consumoCelpe" style="background-color:#EFEFEF; border:0; color: #000000" value="${requestScope.tarifaEconomia.consumoCelpe}"></td>
	  	             	</tr>
					</table>
				   </td>
				</tr>
				<tr>
					<td colspan="4"><hr></td>
				</tr>
				<tr>
					<td colspan="4"><strong>Cartão do Programa Social:</strong></td>
				</tr>
				<tr>
				<td>
    	            <table width="100%" bgcolor="#99CCFF">
						<tr>
							<td bgcolor="#90c7fc" align="center"><strong>Número</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>Tipo</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>Data de Validade</strong></td>
							<td bgcolor="#90c7fc" align="center"><strong>Número de Parcelas</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td align="center">${requestScope.tarifaEconomia.numeroCartaoTarifaSocial}</td>
							<td align="center">${requestScope.tarifaEconomia.tipoCartaoTarifaSocial}</td>
							<td align="center"><bean:write name="tarifaEconomia" property="validadeCartao" scope="request" formatKey="date.format"/></td>
							<td align="center">${requestScope.tarifaEconomia.numeroMesesAdesao}</td>
						</tr>
    	            </table>
    	          </td>
               </tr>
               <tr>
                 <td>
					<table width="100%" bgcolor="#cbe5fe">
						<tr>
	                     <td align="right">
	                        <input name="button" type="button" class="bottonRightCol" value="Voltar" onclick="history.back()" align="left" style="width: 80px;">
	        	   		 </td>
	                   </tr>
	                </table>
                </td>
                </tr>
       </table>
       <p>&nbsp;</p>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
