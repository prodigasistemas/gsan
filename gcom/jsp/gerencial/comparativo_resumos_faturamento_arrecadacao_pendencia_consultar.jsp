<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>

</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/gerarRelatorioConsultarComparativoResumosFaturamentoArrecadacaoPendenciaAction"
  name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm"
  type="gcom.gui.gerencial.ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm"
  method="post"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

 <table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
	  <td width="140" valign="top" class="leftcoltext">
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
		<!--Início Tabela Reference a Páginação da Tela de Processo-->
		<table>
		  <tr>
			<td></td>
		  </tr>
		</table>
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
			<td class="parabg">Consultar Comparativo entre os Resumos</td>
			<td width="11" valign="top"><img border="0"	src="imagens/parahead_right.gif" /></td>
		  </tr>
		</table>
      
      <p>&nbsp;</p>
      <!-- include com a tabela Dados da Geração da Consulta -->
	  <%@ include file="/jsp/gerencial/tabela_dados_geracao_consulta.jsp"%> 
      <!-- fim include Dados da Geração da Consulta -->
   
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
          <td height="0">
            <table width="100%" bgcolor="#99CCFF">
              <tr>
                <td>
                  <table width="100%" border="0" align="center" bgcolor="#99CCFF">
                
                    <tr>
                      <td><div align="center"><strong>Dados do Comparativo </strong></div></td>
                    </tr>

	                <tr bgcolor="#cbe5fe">
	                  <td width="100%" align="center">
	                    <table width="100%" border="0">
                            <tr>
	                          <td height="10" colspan="3"><strong>Faturamento: <span class="style1"></span></strong></td>	                          
	                        </tr>
	                        
	                        <tr>
	                          <td height="10">&nbsp;</td>
	                          <td height="10" colspan="2">Quantidade de Contas</td>
	                          <td> 
	                            <strong>
	                              <b>
	                                <input name="quantidadeContasFaturamento" type="text" disabled style="background-color:#EFEFEF; border:0; text-align:right;" value="<bean:write name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm" property="quantidadeContasFaturamento" formatKey="number.format" />" size="16" maxlength="15">
	                              </b>
	                            </strong>
	                          </td>
	                        </tr>
	                        
	                        <tr>
	                          <td height="10">&nbsp;</td>
	                          <td height="10" colspan="2">Valor </td>
	                          <td>
	                            <strong>
	                              <b>
	                                <input name="valorFaturamento" type="text" disabled style="background-color:#EFEFEF; border:0; text-align:right;" value="<bean:write name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm" property="valorFaturamento" formatKey="money.format"/>" size="16" maxlength="15">
	                              </b> 
	                            </strong> 
	                          </td>
	                        </tr>
	                        
	                        <tr>
	                          <td height="10" colspan="3"><strong>Arrecada&ccedil;&atilde;o:</strong></td>
	                          <td>&nbsp;</td>
	                        </tr>
	                        
	                        <tr>
	                          <td width="18" height="10">&nbsp;</td>
	                          <td colspan="2">Quantidade de Contas</td>
	                          <td>
	                            <strong>
	                              <b>
	                                <input name="quantidadeContasArrecadacao" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right;" value="<bean:write name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm" property="quantidadeContasArrecadacao" formatKey="number.format"/>" size="16" maxlength="15">
	                              </b>
	                            </strong> 
	                          </td>
	                        </tr>
	                        
	                        <tr>
	                          <td height="10">&nbsp;</td>
	                          <td colspan="2">Valor</td>
	                          <td>
	                            <strong>
	                              <b>
	                                <input name="valorArrecadacao" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right;" value="<bean:write name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm" property="valorArrecadacao" formatKey="money.format"/>" size="16" maxlength="15">
	                              </b>
	                            </strong>
	                          </td>
	                        </tr>
	                        
	                        <tr>
	                          <td height="10" colspan="3"><strong>Percentuais:</strong></td>
	                          <td>&nbsp;</td>
	                        </tr>
	                        
	                        <tr>
	                          <td height="10">&nbsp;</td>
	                          <td colspan="2">Quantidade de Contas</td>
	                          <td>
	                            <strong> 
	                              <b>
	                                <input name="quantidadeContasPercentual" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right;" value="<bean:write name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm" property="quantidadeContasPercentual" formatKey="money.format"/>" size="16" maxlength="6">
	                              </b>
	                            </strong> 
	                          </td>
	                        </tr>
	                        
	                        <tr>
	                          <td height="10">&nbsp;</td>
	                          <td colspan="2">Valor</td>
	                          <td>
	                            <strong> 
	                              <b>
	                                <input name="valorPercentual" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right;" value="<bean:write name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm" property="valorPercentual" formatKey="money.format"/>" size="16" maxlength="6">
	                              </b>
	                            </strong>
	                          </td>
	                        </tr>
	
	                        <tr>
	                          <td height="10" colspan="3"><strong>Pend&ecirc;ncia:</strong></td>
	                          <td>&nbsp; </td>
	                        </tr>
	                        
	                        <tr>
	                          <td height="10"><strong> </strong></td>
	                          <td colspan="2">Quantidade de Contas</td>
	                          <td> 
	                            <strong>                         
	                              <b>
	                                <input name="quantidadeContasPendencia" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right;" value="<bean:write name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm" property="quantidadeContasPendencia" formatKey="number.format"/>" size="16" maxlength="10">
	                              </b>
	                            </strong>
	                          </td>
	                        </tr>
	                        
	                        <tr>
	                          <td height="10"><strong> </strong></td>
	                          <td colspan="2">Valor </td>
	                          <td>
	                            <strong>
	                              <b>
	                                <input name="valorPendencia" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right;" value="<bean:write name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm" property="valorPendencia" formatKey="money.format"/>" size="16" maxlength="16">
	                              </b>
	                            </strong> 
	                          </td>
	                        </tr>
	                        
	                        <tr>
	                          <td height="10">&nbsp;</td>
	                          <td height="10" colspan="2"><strong>N&ordm; de Vezes do Faturamento: </strong></td>
	                          <td>
	                            <strong>
	                              <b></b>
	                            </strong>
	                          </td>
	                        </tr>
	                        
	                        <tr>
	                          <td height="10">&nbsp;</td>
	                          <td width="14" height="10">&nbsp;</td>
	                          <td width="147">Quantidade de Contas</td>
	                          <td>
	                            <strong>
	                              <b>
	                                <input name="quantidadeContasNumeroVezesFaturamento" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right;" value="<bean:write name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm" property="quantidadeContasNumeroVezesFaturamento" />" size="16" maxlength="6">
	                              </b>
	                            </strong>
	                          </td> 
	                        </tr>
	                        
	                        <tr>
	                          <td height="10"><strong> </strong></td>
	                          <td height="10">&nbsp;</td>
	                          <td height="10">Valor</td>
	                          <td>
	                            <strong>
	                              <b>
	                                <input name="valorNumeroVezesFaturamento" type="text"  disabled style="background-color:#EFEFEF; border:0; text-align:right;" value="<bean:write name="ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm" property="valorNumeroVezesFaturamento" />" size="16" maxlength="6">
	                              </b>
	                            </strong>
	                          </td>
	                        </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table> 
      <p>
      <table>
        <tr>
          <td align="left">
            <input type="button" name="Button" class="bottonRightCol" value="Voltar Filtro" onclick="javascript:window.location.href='exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&tipoResumo=COMPARATIVORESUMOS';"/>
          </td>          
        </tr>
      </table>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
