<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.micromedicao.ResumoAnormalidadeLeitura"%>
<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function abrirPopupDetalhe(tipo){

	abrirPopup('exibirResumoAnaliseFaturamentoDetalheAction.do?tipo='+tipo,470,532);
	
}
</script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/resultadoResumoAnaliseFaturamentoAction"
	name="ResumoAnaliseFaturamentoActionForm"
	type="gcom.gui.gerencial.faturamento.ResumoAnaliseFaturamentoActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="135" valign="top" class="leftcoltext">

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


			<td width="625" valign="top" class="centercoltext"><!--In&iacute;cio Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Análise do Faturamento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
			<p>&nbsp;</p>
			<!-- include com a tabela Dados da Geração da Consulta --> <%@ include
				file="/jsp/gerencial/tabela_dados_geracao_consulta.jsp"%> 
			<!-- fim include Dados da Geração da Consulta -->

			<!-- fim include Dados da Geração da Consulta -->
            <table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td height="0">
						<table width="100%" bgcolor="#99CCFF">
                    		<!--header da tabela interna -->
		                    <tr bordercolor="#000000">
        		            	<td bgcolor="#99CCFF" align="center" colspan="3">
        		            		<strong>Resumo da Análise de Faturamento</strong>
        		            	</td>
                		    </tr>
                		    <tr bgcolor="#cbe5fe">
			                    <td width="100%" align="center">
			                    	<table width="100%" border="0">
			                        	<tr>
				                        	<td height="10" colspan="2"><strong>Quantidade de Contas: </strong></td>
						                  	<td>                              
						                  		<div align="left">
						                  			<strong>
						                  				<input name="quantidadeContas" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right; color: #000000;" value="<bean:write name="ResumoAnaliseFaturamentoActionForm" property="quantidadeContas" />" size="15" maxlength="15">
						                  			</strong>
				                          		</div>
			                          		</td>
			                          	</tr>
				                        <tr>
				                        	<td height="10" colspan="2"><strong>Quantidade de Economias: </strong></td>
					                        <td>
					                        	<strong>
													<input name="quantidadeEconomia" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right; color: #000000; " value="<bean:write name="ResumoAnaliseFaturamentoActionForm" property="quantidadeEconomia" />" size="15" maxlength="15">
					                          	</strong> 
					                        </td>
				                        </tr>
			                        	<tr>
			                          		<td height="10" colspan="2"><strong>&Aacute;gua</strong></td>
			                          		<td>&nbsp;</td>
			                        	</tr>
			                        	<tr>
			                          		<td width="25" height="10">&nbsp;</td>
			                          		<td width="187">Volume Consumido</td>
			                          		<td>
			                           			<strong>
			                          				<input name="consumoAgua" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right; color: #000000;" value="<bean:write name="ResumoAnaliseFaturamentoActionForm" property="consumoAgua" />" size="15" maxlength="15">
			                          			</strong> 
			                          		</td>
			                          	</tr>
			                        	<tr>
			                          		<td height="10">&nbsp;</td>
			                          		<td height="10">Valor Faturado</td>
			                          		<td>
			                          			<strong>
			                          				<input name="valorAgua" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right; color: #000000;" value="<bean:write name="ResumoAnaliseFaturamentoActionForm" property="valorAgua" formatKey="money.format" />" size="15" maxlength="15">
			                          			</strong>
			                          		</td>
				                        </tr>
				                        <tr>
				                        	<td height="10" colspan="2"><strong>Esgoto</strong></td>
				                          	<td>&nbsp;</td>
				                        </tr>
				                        <tr>
					                        <td height="10">&nbsp;</td>
						                    <td height="10">Volume Coletado </td>
			                          		<td>
			                          			<strong>                         
			                          				<input name="consumoEsgoto" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right; color: #000000;" value="<bean:write name="ResumoAnaliseFaturamentoActionForm" property="consumoEsgoto" />" size="15" maxlength="15">
			                          			</strong>
			                          		</td>
			                          	</tr>
			                        	<tr>
			                          		<td height="10">&nbsp;</td>
						                    <td height="10">Valor Faturado</td>
			                          		<td>
			                          			<strong>
			                          				<input name="valorEsgoto" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right; color: #000000;" value="<bean:write name="ResumoAnaliseFaturamentoActionForm" property="valorEsgoto" formatKey="money.format" />" size="15" maxlength="15">
			                          			</strong>
			                          		</td>
				                        </tr>
				                        <tr>
				                            <td height="10" colspan="2"><strong>D&eacute;bitos Cobrados: </strong></td>
			                          		<td>
			                          			<strong>
			                          				<html:link href="" onclick="abrirPopupDetalhe('DEBITO');" 
				                          			style="background-color:#EFEFEF; border:0; text-align:right; 
				                          			color: #000000;">
				                          			<!--<bean:write name="ResumoAnaliseFaturamentoActionForm" property="valorDebitos" 
				                          			formatKey="money.format" />-->
				                          			<input name="valorDebitos" type="text" readonly="readonly" style="background-color:#EFEFEF;  border:0; text-align:right; color: #000000; font-weight:bold;" value="<bean:write name="ResumoAnaliseFaturamentoActionForm" property="valorDebitos" formatKey="money.format" />" size="13">
				                          			</html:link>
			                            		</strong>
			                            	</td>
				                        </tr>
				                        <tr>
				                        	<td height="10" colspan="2"><strong>Cr&eacute;ditos Realizados: </strong></td>
			                          		<td>
			                          			<strong>
			                          			<html:link href="" onclick="abrirPopupDetalhe('CREDITO');" 
				                          			style="background-color:#EFEFEF; border:0; text-align:right; 
				                          			color: #000000;">
				                          			<!--<bean:write name="ResumoAnaliseFaturamentoActionForm" property="valorCreditos" 
				                          			formatKey="money.format"/>-->
				                          			<input name="valorCreditos" type="text" readonly="readonly" style="background-color:#EFEFEF; border:0; text-align:right; color: #000000; font-weight:bold;" value="<bean:write name="ResumoAnaliseFaturamentoActionForm" property="valorCreditos" formatKey="money.format" />" size="13">
				                          		</html:link>
			                          			</strong>
		                          			</td> 
				                        </tr>
				                        <tr>
				                        	<td height="10" colspan="2"><strong> Total Impostos: </strong></td>
					                        <td>
						                        <strong>
						                            <input name="valorImpostos" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right; color: #000000;" value="<bean:write name="ResumoAnaliseFaturamentoActionForm" property="valorImpostos" formatKey="money.format" />" size="15" maxlength="15">
					                           </strong>
			                          		</td>
				                        </tr>
				                        <tr>
				                        	<td height="10" colspan="2"><strong> Total Cobrado: </strong></td>
					                        <td>
						                        <strong>
						                            <input name="valorTotal" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right; color: #000000;" value="<bean:write name="ResumoAnaliseFaturamentoActionForm" property="valorTotal" formatKey="money.format" />" size="15" maxlength="15">
					                           </strong>
			                          		</td>
				                        </tr>                   
				                    </table>
				                </td>
			                 </tr>
		                 </table>
                 	</td>
				</tr>
                <tr>
                   <td height="12">
				   </td>
                </tr>
            </table>
            <p>
              <table>
                 <tr>
                 	<td>
                 		<input name="button" type="button" class="bottonRightCol" value="Voltar" onclick="window.location.href='<html:rewrite page="/exibirInformarDadosGeracaoRelatorioConsultaAction.do"/>'" align="left" style="width: 80px;">
                 	</td>
                 	
					<td align="left" width="100%">
						  <div align="right">
							   <a href="javascript:toggleBox('demodiv',1);">
									<img border="0" src="<bean:message key="caminho.imagens"/>print.gif"
										title="Imprimir Análise Faturamento" /> 
								</a>
						  </div>
					</td>
                  
                   <td>&nbsp;</td>
                 </tr>
              </table>
           </td>
        </tr>
   </table>
   </td>
  </tr>
</table>
	
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAnaliseFaturamentoAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
    <%@ include file="/jsp/util/tooltip.jsp" %>	
    
</body>

</html:form>
</html:html>
