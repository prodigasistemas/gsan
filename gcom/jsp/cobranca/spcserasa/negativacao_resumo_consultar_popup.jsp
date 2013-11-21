<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarResumoNegativacaoPopupAction.do"
	name="InformarDadosConsultaNegativacaoActionForm"
	type="gcom.gui.cobranca.spcserasa.InformarDadosConsultaNegativacaoActionForm"
	method="post">
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="560" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Resumo da Negativação</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table>
			  <tr>
			    <td></td>
			  </tr>
              <tr>
			    <td>
		          <strong>Negativador:</strong>
                </td>
			    <td>
			      <html:text property="nomeNegativador" size="45" readonly="true" disabled="true" 
			                 style="background-color:#EFEFEF; border:0;"/>
			    </td>	
              </tr>	
              <tr>
                <td>
                  <strong>Período do envio da Negativação:</strong>
                </td>
                <td>
                  <html:text property="periodoEnvioNegativacaoInicio" size="8" readonly="true" disabled="true"
                             style="background-color:#EFEFEF; border:0; " />
                  <strong>a</strong>
                  <html:text property="periodoEnvioNegativacaoFim" size="8" readonly="true" disabled="true"
                             style="background-color:#EFEFEF; border:0; " />
                </td>
              </tr>		
              <tr> 
                <td colspan="2">
                  <div align="center">
                    <hr>
                  </div>
                </td>
              </tr>    
              <tr>
			    <td>
		          <strong>Situação da Negativação:</strong>
                </td>
			    <td>
                  <bean:write scope="session"
							  name="situacaoNegativacao"/>
			    </td>	
              </tr>	
              <tr> 
                <td colspan="2">
                  <div align="center">
                    <hr>
                  </div>
                </td>
              </tr>    
            </table>
			<table bgcolor="#90c7fc" border="0" width="100%">
			  <tr>
			    <td bgcolor="#79bbfd" align="center" colspan="3" ><strong>Situação do Débito da Negativação</strong></td>
			  </tr>

				<tr>
					<td bgcolor="#cbe5fe" align="center" rowspan="2" width="25%">
					  <strong>
					    <bean:write scope="session" name="descricaoIncluidas"/> 
					  </strong>
					</td>
					<td bgcolor="#79bbfd" align="center"><strong> Valor</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Percentual </strong></td>
				</tr>
						<tr>
						  
								<td width="17%" align="center" bgcolor="#FFFFFF"><bean:write
								    scope="session"
									name="somatorioValorDebito"/>
								</td>
									
								<td width="17%" align="center" bgcolor="#FFFFFF">
								  100,00
								</td>
						</tr>
			  <tr>
			    <td bgcolor="#79bbfd" align="center" colspan="3" ><strong>Situação Parcial do Débito da Negativação</strong></td>
			  </tr>
			</table>
			
			
			      <table width="100%" bgcolor="#90c7fc" border="0"> 
				    <logic:iterate name="colecaoResumoNegativacaoDetalhePopUp" id="resumoNegativacao">
				      <tr>
					    <td rowspan="2" bgcolor="#cbe5fe" width="25%">
					      <strong><bean:write name="resumoNegativacao"property="descricao"/></strong>
					   	</td>
					  </tr>
					  <tr>
					   	<td bgcolor="#FFFFFF" width="17%" align="center">
					   		<bean:write name="resumoNegativacao"
					   		            property="valorDinamico" formatKey="money.format"/>
					   	</td>
					   	<td bgcolor="#FFFFFF" width="17%" align="center">
					   	  <bean:write name="resumoNegativacao"
					   		          property="percentualValor" formatKey="money.format"/>
					   	</td>
					  </tr>											
				    </logic:iterate>  
				</table>
				
			<table width="100%" bgcolor="#90c7fc" border="0">
				<tr>
					<td bgcolor="#cbe5fe" align="center" rowspan="2" width="25%">
					  
					</td>
					<td bgcolor="#79bbfd" align="center"><strong> Valor</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Quantidade </strong></td>
				</tr>
				<tr>
					<td width="17%" align="center" bgcolor="#FFFFFF">
						<bean:write scope="session" name="somatorioValor"/>
					</td>
					<td width="17%" align="center" bgcolor="#FFFFFF">
						<bean:write scope="session" name="somatorioTotalQuantidade"/>
					</td>
				</tr>
			</table> 
			
			<table bgcolor="#90c7fc" border="0" width="100%">
				<tr>
					<td bgcolor="#79bbfd" align="center" colspan="3" ><strong>Situação de &Aacute;gua</strong></td>
				</tr>
			</table>
			<table width="100%" bgcolor="#90c7fc" border="0"> 
				<logic:iterate name="colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp" id="resumoNegativacaoLigacaoAguaSituacao">
				<tr>
					<td rowspan="2" bgcolor="#cbe5fe" width="25%">
						<strong><bean:write name="resumoNegativacaoLigacaoAguaSituacao" property="descricao"/></strong>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF" width="17%" align="center">
						<bean:write name="resumoNegativacaoLigacaoAguaSituacao" property="valorDinamico" formatKey="money.format"/>
					</td>
					<td bgcolor="#FFFFFF" width="17%" align="center">
						<bean:write name="resumoNegativacaoLigacaoAguaSituacao" property="quantidadeInclusao" formatKey="money.format"/>
					</td>
				</tr>											
			    </logic:iterate>  
			</table> 
			
			<table border="0" width="100%">
				<tr>
					<td colspan="3" align="right">
						<input name="Button" type="button" class="bottonRightCol" value="Imprimir" onClick="javascript:window.print();">&nbsp;&nbsp;&nbsp;
						<input name="Button" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:fechar();">
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<body>
</html:html>
