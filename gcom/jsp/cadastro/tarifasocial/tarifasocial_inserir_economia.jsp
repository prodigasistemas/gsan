<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.GerenciadorPaginas" %>
<%@ page import="gcom.util.Pagina" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirTarifaSocialActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script>
<!--

    var bCancel = false;

    function validateInserirTarifaSocialActionForm(form) {

      return true;

   }
-->

</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form
    action="/inserirTarifaSocialEconomiaAction"
    method="post"
>


<%@ include file="/jsp/util/etapas_processo.jsp"%>


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
          <td class="parabg">Inserir Tarifa Social</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

                      <table width="100%" border="0" dwcopytype="CopyTableRow">
                                      <tr>
                                        <td width="100%" height="17">Rela&ccedil;&atilde;o de clientes
                                          usu&aacute;rios por economia</td>
                                      </tr>
                                      <tr>
                                        <td height="200"> <div style="width: 100%; height: 100%; overflow: auto;">
                                          <table width="100%" >
                                            <tr>
                                              <td height="31"><table width="100%" bgcolor="#99CCFF">
                                                  <!--header da tabela interna -->
                                                  <tr bordercolor="#FFFFFF" bgcolor="#0066FF">
                                                    <td  width="43%" bgcolor="#0066FF">
                                                      <div align="center"><strong><font color="#FFFFFF">Cliente
                                                        Usu&aacute;rio</font></strong></div></td>
                                                    <td width="26%">
                                                      <div align="center"><strong><font color="#FFFFFF">Complemento
                                                        Endere&ccedil;o</font></strong></div></td>
                                                    <td width="16%">
                                                      <div align="center"><font color="#FFFFFF"><strong>CEP</strong></font></div></td>
                                                    <td width="15%">
                                                      <div align="center"><font color="#FFFFFF"><strong>N&uacute;m.
                                                        IPTU</strong></font></div></td>
                                                  </tr>
                                                  <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
                                                    <td height="17"><a href="tarifa_social_inserir_popup.htm">Carlos Alberto da Silva</a></td>
                                                    <td> <div align="center">Apt. 101</div></td>
                                                    <td> <div align="center">52021-000</div></td>
                                                    <td> <div align="center">5545544-8</div></td>
                                                  </tr>
                                                </table></td>
                                            </tr>
                                            <tr>
                                              <td height="44"> <table width="100%" bgcolor="#99CCFF">

                                                  <!--header da tabela interna -->
                                                  <tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">
                                                    <td  width="9%">
                                                      <div align="center"><font color="#333333">Remover</font></div></td>
                                                    <td width="23%">
                                                      <div align="center"><font color="#333333">Cart&atilde;o
                                                        do Prog.Social</font></div></td>
                                                    <td width="23%">
                                                      <div align="center"><font color="#333333">Tipo
                                                        Cart&atilde;o Prog.Social</font></div></td>
                                                    <td width="15%">
                                                      <div align="center"><font color="#333333">Renda
                                                        Familiar</font></div></td>
                                                    <td width="23%">
                                                      <div align="center"><font color="#333333">Tipo
                                                        Renda Familiar</font></div></td>
                                                  </tr>
                                                  <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
                                                    <td height="17"  > <div align="center"> <font color="#333333"><img src="imagens/Error.gif" width="14" height="14">
                                                        </font></div></td>
                                                    <td align="center"><font color="#333333">65316532165</font></td>
                                                    <td> <div align="center"><font color="#333333">Cart&atilde;o
                                                        do Cidad&atilde;o</font></div></td>
                                                    <td><div align="right"><font color="#333333">550,00</font></div></td>
                                                    <td> <div align="center"><font color="#333333">Comprovada</font></div></td>
                                                  </tr>
                                                </table></td>

                                            </tr>
                                            <tr>
                                              <td height="7"></td>
                                            </tr>
                                            <tr>
                                              <td height="31"><table width="100%" bgcolor="#99CCFF">
                                                  <!--header da tabela interna -->
                                                  <tr bordercolor="#FFFFFF" bgcolor="#0066FF">
                                                    <td  width="43%">
                                                      <div align="center"><font color="#FFFFFF"><strong>Cliente
                                                        Usu&aacute;rio</strong></font></div></td>
                                                    <td width="26%">
                                                      <div align="center"><font color="#FFFFFF"><strong>Complemento
                                                        Endere&ccedil;o</strong></font></div></td>
                                                    <td width="16%">
                                                      <div align="center"><strong><font color="#FFFFFF">CEP</font></strong></div></td>
                                                    <td width="15%">
                                                      <div align="center"><font color="#FFFFFF"><strong>N&uacute;m.
                                                        IPTU</strong></font></div></td>
                                                  </tr>
                                                  <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
                                                    <td height="17">Maria das Gra&ccedil;as Lima</td>
                                                    <td> <div align="center">Apt. 102</div></td>
                                                    <td> <div align="center">52487-000</div></td>
                                                    <td> <div align="center">542141-8</div></td>
                                                  </tr>
                                                </table></td>
                                            </tr>
                                            <tr>
                                              <td height="44"> <table width="100%" bgcolor="#99CCFF">
                                                  <!--header da tabela interna -->
                                                  <tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">
                                                    <td  width="9%">
                                                      <div align="center"><font color="#333333">Remover</font></div></td>
                                                    <td width="23%">
                                                      <div align="center"><font color="#333333">Cart&atilde;o
                                                        do Prog.Social</font></div></td>
                                                    <td width="23%">
                                                      <div align="center"><font color="#333333">Tipo
                                                        Cart&atilde;o Prog.Social</font></div></td>
                                                    <td width="15%">
                                                      <div align="center"><font color="#333333">Renda
                                                        Familiar</font></div></td>
                                                    <td width="23%">
                                                      <div align="center"><font color="#333333">Tipo
                                                        Renda Familiar</font></div></td>
                                                  </tr>
                                                  <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
                                                    <td height="17"  > <div align="center"> <font color="#333333"><img src="imagens/Error.gif" width="14" height="14">
                                                        </font></div></td>
                                                    <td> <div align="center"><font color="#333333">52541254155</font></div></td>
                                                    <td> <div align="center"><font color="#333333">Idoso</font></div></td>
                                                    <td><div align="right"><font color="#333333">635,14</font></div></td>
                                                    <td> <div align="center"><font color="#333333">N&atilde;o
                                                        Declarada</font></div></td>
                                                  </tr>
                                                </table></td>
                                            </tr>
                                            <tr>
                                              <td>&nbsp;</td>
                                            </tr>
                                          </table></td>
                                      </tr>
                                    </table>                                               </div>
                                                     <tr>
                                                      <td colspan="2">
							<div align="right">
								<%@ include file="/jsp/util/controle_processo_fixo.jsp"%>
							</div>
						      </td>
                                                     </tr>
  					</td>

					  </tr>

                                       </table>
                                    </td>

                                 </tr>


                              </table>

    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp" %>
</body>
</html:form>
</html:html>
