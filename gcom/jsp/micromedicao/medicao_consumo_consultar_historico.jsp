<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.cadastro.cliente.ClienteTipo" %>
<%@ page import="gcom.gui.*" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="LeituraConsumoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
</head>

<body leftmargin="5" topmargin="5">

<html:form
    action="/exibirConsultarHistoricoMedicaoConsumoAction.do"
    name="LeituraConsumoActionForm"
    type="gcom.gui.micromedicao.LeituraConsumoActionForm"
    method="post"
>


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
      </div></td>
    <td width="625" valign="top" bgcolor="#003399" class="centercoltext"> 
      <table height="100%">

        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">

        <tr>
          <td width="11"><img src="imagens/parahead_left.gif" border="0" /></td>
          <td class="parabg">
          	Consultar Hist&oacute;rico de Medi&ccedil;&atilde;o 
            e Consumo
          </td>

          <td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
        </tr>
      </table>
      <table width="100%" border="0">

        
        <tr> 
          <td height="31"><table width="100%" bgcolor="#99CCFF">
		       <tr> 
		          <td bgcolor="#79bbfd" height="20" colspan="4"><div align="center" class="style11">
		          	<strong>Dados do Im&oacute;vel </strong></div></td>
		       </tr>
              <!--header da tabela interna -->
              <tr bordercolor="#FFFFFF" bgcolor="#99CCFF"> 
                <td  width="24%"> <div align="center" class="style9">
                	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
                	<strong>Inscri&ccedil;&atilde;o</strong></font></div></td>

                <td width="17%"> <div align="center" class="style9">
	                <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
	                <strong>Matr&iacute;cula Im&oacute;vel </strong></font></div></td>
                <td width="27%"> <div align="center" class="style9">
                	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
                	<strong>Tipo de Medi&ccedil;&atilde;o</strong></font></div></td>
                <td width="32%"> <div align="center" class="style9">
                	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
                	<strong>Matr&iacute;cula do Im&oacute;vel Condom&iacute;nio</strong></font></div></td>

              </tr>

              <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
                <td height="17"> <div align="center">
                	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
                	${sessionScope.leituraConsumoActionForm.inscricaoImovel}<br>
                  </font></div>
                </td>
                <td> <div align="center">
                	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
                	${sessionScope.leituraConsumoActionForm.imovel} &nbsp;</font></div>
                </td>
                <td> <div align="center">
                	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
                	${sessionScope.leituraConsumoActionForm.tipoMedicao} &nbsp;</font></div>
                </td>
                <td> <div align="center">
                	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
                	${sessionScope.leituraConsumoActionForm.imovelCondominio} &nbsp;</font></div>
                </td>
              </tr>
		        <tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
		              <td width="90%" bordercolor="#FFFFFF" bgcolor="#99CCFF" colspan="4"> 
		                <div align="center" class="style7">
		                	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">	
		                	<strong>Endere&ccedil;o 
		                  	do Im&oacute;vel</strong></font></div>
		              </td>
		            </tr>
		            <tr bgcolor="#FFFFFF"> 
		                <td width="90%" colspan="4">
		                	<div align="center">
		                		<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
		                		${sessionScope.leituraConsumoActionForm.enderecoFormatado} &nbsp;
		                		</font>
		                	</div>
		                </td>
		         </tr>
            </table></td>
        </tr>
	    
      </table>
   <br>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr bordercolor="#000000"> 
          <td colspan="8" bgcolor="#79bbfd" height="20"><div align="center"><strong>Hist&oacute;rico 
              de Medi&ccedil;&atilde;o</strong></div></td>
        </tr>
        <tr bordercolor="#000000"> 
          <td bgcolor="#90c7fc" width="10%" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong> M&ecirc;s e Ano </strong></font>
          </td>

          <td bgcolor="#90c7fc" width="13%" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Dt. Leitura Informada</strong></font>
          </td>
          <td bgcolor="#90c7fc" width="10%" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Leitura. Informada</strong></font>
          </td>
          <td bgcolor="#90c7fc" width="13%" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Dt. Leitura Faturada</strong></font>
          </td>
          <td bgcolor="#90c7fc" width="12%" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Leitura Faturada</strong></font></td>
          <td bgcolor="#90c7fc" width="15%" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Anormalidade Informada </strong></font>
          </td>
          <td bgcolor="#90c7fc" width="12%" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Anormalidade Faturada</strong></font>
          </td>

          <td bgcolor="#90c7fc" width="17%" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Sit. Leit. Atual </strong></font>
          </td>
        </tr>
        <tr bordercolor="#90c7fc"> 
          <td colspan="8">
	        <div style="width: 100%; height: 100%; overflow: auto;">
    	     <table width="100%" align="left" bgcolor="#90c7fc" >
         		<!--corpo da segunda tabela-->
			   <%int cont=0;%>
			    <logic:notEmpty name="medicoesHistoricos">
				  
					<logic:iterate name="medicoesHistoricos" id="medicaoHistorico">
			              <%
		                      cont = cont+1;
		                   if (cont%2==0){%>
		                     <tr bgcolor="#cbe5fe">
		                   <%}else{ %>
							 <tr bgcolor="#FFFFFF">		                     
		                   <%}%>
				          <td width="10%" align="center">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          	 ${medicaoHistorico.mesAno} &nbsp;</font>
				          </td>
				          <td width="13%" align="center">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
					          	<bean:write name="medicaoHistorico" property="dataLeituraAtualInformada"
					          	formatKey="date.format"/>
					        </font>
				          </td>
				          <td width="11%" align="center">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          	${medicaoHistorico.leituraAtualInformada} &nbsp;</font>
				          </td>
				          <td width="13%" align="center">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          		<bean:write name="medicaoHistorico" property="dataLeituraAtualFaturamento"
				          		formatKey="date.format"/>
				          	</font>
				          </td>
				
				          <td width="11%" align="center">
		  		          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
					        ${medicaoHistorico.leituraAtualFaturamento} &nbsp;</font>
					      </td>
				          <td width="15%">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          	${medicaoHistorico.leituraAnormalidadeInformada.descricao} &nbsp;</font>
				          </td>
				          <td width="14%">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          	${medicaoHistorico.leituraAnormalidadeFaturamento.descricao} &nbsp;
				          	</font>
				          </td>
				          <td width="14%" >
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
					          ${medicaoHistorico.leituraSituacaoAtual.descricao} &nbsp;</font>
					      </td>
				        </tr>
			      	</logic:iterate>
		      </logic:notEmpty>
		     </table>
		    </div>
		   </td>
        </tr>
      </table>
      <br>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="4" bgcolor="#cbe5fe" colspan="6"></td>
		</tr>
        <tr bordercolor="#000000" > 
          <td colspan="6" bgcolor="#79bbfd" height="20"> <div align="center">
          	<strong>Hist&oacute;rico 
              de Consumo</strong></div>
          </td>

        </tr>
        <tr bordercolor="#000000"> 
          <td width="16%" bgcolor="#90c7fc"><div align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
          	<strong> M&ecirc;s 
              e Ano </strong></font></div>
          </td>
          <td width="15%" bgcolor="#90c7fc" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
          	<strong>Consumo Medido</strong></font>
          </td>
          <td width="17%" bgcolor="#90c7fc" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
          	<strong>Consumo Faturado</strong></font>
          </td>
          <td width="13%" bgcolor="#90c7fc" align="center">
	          <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
    	      <strong>Anormalidade Consumo</strong></font>
    	  </td>
    	  <td width="10%" bgcolor="#90c7fc" align="center">
	          <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
    	      <strong>Dias de Consumo</strong></font>
    	  </td>
          <td width="29%" bgcolor="#90c7fc" align="center">
          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
          	<strong>Tipo de 
              Consumo</strong></font>
          </td>
        </tr>
        <tr bordercolor="#90c7fc"> 
           <td colspan="6">
	        <div style="width: 100%; height: 100%; overflow: auto;">
    	     <table width="100%" align="left" bgcolor="#90c7fc" >
         		<!--corpo da segunda tabela-->
			   <%int cont2=0;%>
			    <logic:notEmpty name="imoveisMicromedicao">
				  
					<logic:iterate name="imoveisMicromedicao" id="imovelMicromedicao">
			              <%
		                      cont2 = cont2+1;
		                   if (cont2%2==0){%>
			                   <tr bgcolor="#cbe5fe">
		                   <%}else{ %>
								<tr bgcolor="#FFFFFF">		                     
		                   <%}%>
				          <td width="16%" align="center">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          	${imovelMicromedicao.consumoHistorico.mesAno} &nbsp;</font>
				          </td>
				          <td width="15%" align="center">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          	${imovelMicromedicao.medicaoHistorico.numeroConsumoMes} &nbsp;</font>
				          </td>
				          <td width="17%" align="center">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          	${imovelMicromedicao.consumoHistorico.numeroConsumoFaturadoMes} &nbsp;</font>
				          </td>
				          <td width="13%" align="center">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          	${imovelMicromedicao.consumoHistorico.consumoAnormalidade.descricaoAbreviada} &nbsp;</font>
				          </td>
				          <td width="10%" align="center">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          	 ${imovelMicromedicao.qtdDias}&nbsp;</font>
				          </td>
				          <td width="29%">
				          	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
				          	${imovelMicromedicao.consumoHistorico.consumoTipo.descricao} &nbsp;</font>
				          </td>
	          			</tr>
			      </logic:iterate>
		      </logic:notEmpty>
		     </table>
		    </div>
		   </td>
        </tr>
        <tr>
        	<td height="4"></td>
        </tr>
        <tr>
			<td align="left" colspan="8">
        	  <input type="button" value="Voltar" class="bottonRightCol" onclick="javascript:history.back();"/>
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
