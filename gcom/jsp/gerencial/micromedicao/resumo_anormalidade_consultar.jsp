<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.gerencial.micromedicao.ResumoAnormalidadeConsultaHelper" %>
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
  action="/resultadoResumoAnormalidadeAction"
  name="ResumoAnormalidadeActionForm"
  type="gcom.gui.gerencial.micromedicao.ResumoAnormalidadeActionForm"
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
                <td class="parabg">Consultar Resumo das Anormalidades de Leitura</td>
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
                      <td bgcolor="#99CCFF" align="center" colspan="3"><div align="center"><strong>Tipo de Medição: Ligação de Água</strong></div></td>
                    </tr>
                    <tr>
                    	<td bgcolor="#90c7fc" width="74%"><div align="center"><strong>Anormalidade</strong></div></td>
                    	<td bgcolor="#90c7fc" width="12%"><div align="center"><strong>Medições</strong></div></td>
                    	<td bgcolor="#90c7fc" width="25%"><div align="center"><strong>Percentual</strong></div></td>
                    </tr>
                  </table></td>
              </tr>
          <logic:present name="colecaoResumoAnormalidadeAgua">
              <tr>
                <td height="126">
                  <div style="width: 100%; height: 100%; overflow: auto;">
                    <table width="100%" align="center" bgcolor="#99CCFF">
                      <!--corpo da segunda tabela-->
                      <%int cont=0;%>
                      <logic:notEmpty name="colecaoResumoAnormalidadeAgua">
                        <logic:iterate name="colecaoResumoAnormalidadeAgua" id="resumoAnormalidadeAgua" type="ResumoAnormalidadeConsultaHelper">
                          <%
                               cont = cont+1;
                            if (cont%2==0){%>
                              <tr bgcolor="#FFFFFF">
                            <%}else{ %>
                              <tr bgcolor="#cbe5fe">
                          <%}%>
                              <td width="76%">
                                <div align="left">
                                   <bean:write name="resumoAnormalidadeAgua" property="descricaoLeituraAnormalidadeFaturada"/>
                                </div>
                              </td>
                              <td width="12%">
			        			<div align="right">
                                   <bean:write name="resumoAnormalidadeAgua" property="quantidadeMedicao"/>
								</div>
                              </td>
                              <td width="12%">
                                 <div align="right">
                                   <%=Util.calcularPercentual(resumoAnormalidadeAgua.getQuantidadeMedicao().toString(), (String)request.getAttribute("somatorioAnormalidadeAgua"))%>
                                 </div>
                              </td>
                            </tr>
                        </logic:iterate>
                      </logic:notEmpty>
                   </table>
                   </div>
                  </table>
                  <table width="100%" cellpadding="0" cellspacing="0">
	                  <tr bgcolor="#90c7fc">
                        	<td width="65%"><strong>Total:</strong></td>
                        	<td align="right" width="20%"><strong>${somatorioAnormalidadeAgua}</strong></td>
                        	<td width="20%">&nbsp;</td>
                        </tr>
                  </table>
                   <br>
            <table width="100%" cellpadding="0" cellspacing="0">
               <tr>
                <td height="0">
                  <table width="100%" bgcolor="#99CCFF">
                    <!--header da tabela interna -->
                    <tr bordercolor="#000000">
                      <td bgcolor="#90c7fc" align="center" colspan="3"><div align="center"><strong>Tipo de Medição: Ligação de Poço</strong></div></td>
                    </tr>
                    <tr>
                    	<td bgcolor="#90c7fc" width="74%"><div align="center"><strong>Anormalidade</strong></div></td>
                    	<td bgcolor="#90c7fc" width="12%"><div align="center"><strong>Medições</strong></div></td>
                    	<td bgcolor="#90c7fc" width="25%"><div align="center"><strong>Percentual</strong></div></td>
                    </tr>
                  </table></td>
              </tr>
          <logic:present name="colecaoResumoAnormalidadePoco">
              <tr>
                <td height="126">
                  <div style="width: 100%; height: 100%; overflow: auto;">
                    <table width="100%" align="center" bgcolor="#99CCFF">
                      <!--corpo da segunda tabela-->
                      <%int cont2=0;%>
                      <logic:notEmpty name="colecaoResumoAnormalidadePoco">
                        <logic:iterate name="colecaoResumoAnormalidadePoco" id="resumoAnormalidadeEsgoto" type="ResumoAnormalidadeConsultaHelper">
                          <%
                               cont2 = cont2+1;
                            if (cont2%2==0){%>
                              <tr bgcolor="#FFFFFF">
                            <%}else{ %>
                              <tr bgcolor="#cbe5fe">
                          <%}%>
                              <td width="76%">
                                <div align="left">
                                   <bean:write name="resumoAnormalidadeEsgoto" property="descricaoLeituraAnormalidadeFaturada"/>
                                </div>
                              </td>
                              <td width="12%">
			        			<div align="right">
                                   <bean:write name="resumoAnormalidadeEsgoto" property="quantidadeMedicao"/>
								</div>
                              </td>
                              <td width="12%" align="right">
                                 <div align="right">
                                    <%=Util.calcularPercentual(resumoAnormalidadeEsgoto.getQuantidadeMedicao().toString(), (String)request.getAttribute("somatorioAnormalidadePoco"))%>
                                 </div>
                              </td>
                            </tr>
                        </logic:iterate>
                      </logic:notEmpty>
                    </table>
                </div>
    	       </td>
              </tr>
            </logic:present>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                 <tr bgcolor="#90c7fc">
                      	<td width="67%"><strong>Total:</strong></td>
                      	<td align="right" width="20%"><strong>${somatorioAnormalidadePoco}</strong></td>
                      	<td width="15%">&nbsp;</td>

                 </tr>
            </table>
            <br>
            <p>
              <table >
                 <tr>
   	               <td>&nbsp;</td>
                   <td width="100%" align="right">&nbsp;</td>
                   <td align="right"><a href="javascript:abrirPopupRelatorio('gerarRelatorioResumoAnormalidadeLeituraAction.do');"><img  border="0" src="<bean:message key="caminho.imagens"/>print.gif"></a></td>
                 </tr>
                 <tr>
   	               <td><input type="button" name="Button" class="bottonRightCol" value="Voltar" onclick="javascript:window.location.href='exibirInformarDadosGeracaoRelatorioConsultaAction.do?tipoResumo=ANORMALIDADE';" /></td>
                   <td width="100%" align="right">&nbsp;</td>
                   <td><input type="button" name="Button" class="bottonRightCol" value="Resumo das Anormalidades de Consumo" onclick="javascript:window.location.href='/gsan/consultarResumoAnormalidadeConsumoAction.do';" /></td>
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
