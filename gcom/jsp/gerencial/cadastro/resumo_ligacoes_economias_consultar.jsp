<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Util" %>

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
  action="/exibirResultadoConsultaResumoLigacoesEconomiaAction"
  name="ResumoLigacoesEconomiasActionForm"
  type="gcom.gui.gerencial.cadastro.ResumoLigacoesEconomiasActionForm"
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
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                <td class="parabg">Consultar Resumo das Ligações/Economias</td>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
            <p>&nbsp;</p>
  <!-- include com a tabela Dados da Geração da Consulta -->
			<%@ include file="/jsp/gerencial/tabela_dados_geracao_consulta.jsp"%>
  <!-- fim include Dados da Geração da Consulta -->
  
            <table width="100%" cellpadding="0" cellspacing="0">
            
              <tr>
                <td height="0">
                  <table width="100%" bgcolor="#99CCFF">
                    <!--header da tabela interna -->
                    <tr bordercolor="#000000">
                      <td bgcolor="#1888f8" align="center" colspan="9"><div align="center"><strong>Dados da Consulta do Resumo das Ligações/Economias</strong></div></td>
                    </tr>
                    <tr bordercolor="#FFFFFF" bgcolor="#90c7fc"> 
                        <td rowspan="2" width="16%"><div align="center"><strong>Situa&ccedil;&atilde;o 
                            de &Aacute;gua</strong></div></td>
                        <td rowspan="2" width="16%"><div align="center"><strong>Situa&ccedil;&atilde;o 
                            de Esgoto</strong></div></td>
                        <td rowspan="2" width="15%"><div align="center"><strong>Categoria</strong></div></td>
                        <td colspan="3"><div align="center"><strong>Liga&ccedil;&otilde;es</strong></div></td>

                        <td colspan="3"><div align="center"><strong> Economias</strong></div></td>
                      </tr>
                      <tr bgcolor="#cbe5fe">
                      	<td width="8%"><strong> C/ Hidr.</strong></td>
                      	<td width="9%"><strong>S/ Hidr.</strong></td>
                      	<td width="8%"><strong>Total</strong></td>
                      	<td width="9%"><strong>C/ Hidr.</strong></td>
                      	<td width="9%"><strong>S/ Hidr.</strong></td>
                      	<td width="10%"><strong>Total</strong></td>
                      </tr>
                  </table></td>
              </tr>
          <logic:present name="colecaoResumoLigacoesEconomias">
          	<tr>
                <td width="614" height="398"> <div style="width: 100%; height: 100%; overflow: auto; "> 
                    <table width="100%" align="center" bgcolor="#99CCFF">
                      <!--corpo da segunda tabela-->
                      <%int cont=0;%>
                      <logic:notEmpty name="colecaoResumoLigacoesEconomias">
                        <logic:iterate name="colecaoResumoLigacoesEconomias" id="resumoLigacoesEconomiasAgua">
                          <%
                               cont = cont+1;
                            if (cont%2==0){%>
                              <tr bgcolor="#FFFFFF">
                            <%}else{ %>
                              <tr bgcolor="#cbe5fe">
                          <%}%>
                              <td width="17%">
                                <div align="left">
                                   ${resumoLigacoesEconomiasAgua.descricaoSituacaoLigacaoAgua}
                                </div>
                              </td>
                              <td width="17%">
			        			<div align="left">
                                   ${resumoLigacoesEconomiasAgua.descricaoSituacaoLigacaoEsgoto}
								</div>
                              </td>
                              <td width="16%">
                                 <div align="left">
                                   ${resumoLigacoesEconomiasAgua.descricaoCategoria}
                                 </div>
                              </td>
                              <td width="9%">
                                <div align="right">
                                   ${resumoLigacoesEconomiasAgua.quantidadeLigacoesComHidrometro}
                                </div>
                              </td>
                              <td width="9%">
			        			<div align="right">
                                   ${resumoLigacoesEconomiasAgua.quantidadeLigacoesSemHidrometro}
								</div>
                              </td>
                              <td width="9%">
                                 <div align="right">
                                   ${resumoLigacoesEconomiasAgua.totalLigacoes}
                                 </div>
                              </td>
                              <td width="9%">
                                <div align="right">
                                   ${resumoLigacoesEconomiasAgua.quantidadeEconomiaComHidrometro}
                                </div>
                              </td>
                              <td width="10%">
			        			<div align="right">
                                   ${resumoLigacoesEconomiasAgua.quantidadeEconomiaSemHidrometro}
								</div>
                              </td>
                              <td width="8%">
                                 <div align="right">
                                   ${resumoLigacoesEconomiasAgua.totalEconomia}
                                 </div>
                              </td>
                            </tr>
                        </logic:iterate>
                      </logic:notEmpty>
                   </table>
                  </div>
                  </table>
                   <br>
               <p>
              <table>
                 <tr>
   	               <td>&nbsp;</td>
                   <td width="600" align="right">&nbsp;</td>
                   <td align="right"><a href="javascript:abrirPopupRelatorio('gerarRelatorioResumoAnormalidadeLeituraAction.do');"><img  border="0" src="<bean:message key="caminho.imagens"/>print.gif"></a></td>
                 </tr>
                 <tr>
   	               <td><input type="button" name="Button" class="bottonRightCol" value="Voltar" onclick="javascript:window.location.href='exibirInformarDadosGeracaoRelatorioConsultaAction.do?tipoResumo=LIGACAO_EC0NOMIA';" /></td>
                   <td width="600" align="right">&nbsp;</td>
                 </tr>
              </table>
           </td>
        </tr>
		</logic:present>
   </table>
<%@ include file="/jsp/util/rodape.jsp"%>
</body>

</html:form>
</html:html>
