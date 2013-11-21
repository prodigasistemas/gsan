<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema" %>
<%@ page import="java.util.Date"%>
<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<title>Compesa - SGCQ</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script>
<!--
function concluir(){
	document.forms[0].submit();
}
function reexibir(){
	document.forms[0].acao.value = 'reexibir';
	document.forms[0].submit();
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/MostrarDadosTarifaSocialAction" method="post">
<html:hidden property="acao" value="processar" />
<html:hidden property="idRegistroAtualizacao" />

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
                  <td width="14" valign="top"><div align="center"><img src="imagens/parahead_left.gif" border="0" /></div></td>
                  <td valign="middle" class="parabg">Atualizar Dados da Tarifa Social</td>
                  <td class="parabg">&nbsp;</td>
                  <td class="parabgsimples" valign="top" border="0" >&nbsp;</td>
                  <td class="parabgsimples" valign="top" border="0" >&nbsp;</td>
                  <td class="parabgsimples" valign="top" border="0" > <div align="justify"> 
                      <h1>&nbsp;</h1>
                    </div></td>
                  <td width="14" valign="top" > <div align="center"><img src="imagens/parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></div></td>
                </tr>
              </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
              
              <table width="100%" border="0" dwcopytype="CopyTableRow">
                <tr> 
                  <td width="100%" height="17">Rela&ccedil;&atilde;o de clientes 
                    usu&aacute;rios por im&oacute;vel ou economia</td>
                </tr>
                <tr> 
                  <td height="200"> <div style="width: 100%; height: 100%; overflow: auto;"> 
                    <table width="100%" >
                <!-- inicio de um  -->
<logic:iterate name="MostrarDadosTarifaSocialActionForm" property="collObjeto" id="OTDClienteEconomia">

					<bean:define id="tarifaSocialDadoEconomia" name="OTDClienteEconomia" property="tarifaSocialDadoEconomia" />
					<bean:define id="cliente" name="OTDClienteEconomia" property="cliente" />
					
                    <logic:present name="tarifaSocialDadoEconomia" property="tarifaSocialCartaoTipo" >
                    	<bean:define id="tarifaSocialCartaoTipo" name="tarifaSocialDadoEconomia" property="tarifaSocialCartaoTipo" />
					</logic:present> 

                    <logic:present name="tarifaSocialDadoEconomia" property="rendaTipo" >
                  		<bean:define id="rendaTipo" name="tarifaSocialDadoEconomia" property="rendaTipo" /> 
                    	<bean:define id="rendaTipoDescricao" name="rendaTipo" property="descricao" />
					</logic:present>

                      <tr> 
                        <td height="82">
							<table width="100%" bgcolor="#99CCFF">
                            <!--header da tabela interna -->
                            <tr bordercolor="#FFFFFF" bgcolor="#99CCFF"> 
                              <td height="0" colspan="2" bgcolor="#99CCFF"> <div align="center"><strong>Cliente 
                                  Usu&aacute;rio</strong></div></td>
                              <td width="26%" height="0"> <div align="center"><strong>Complemento 
                                  Endere&ccedil;o</strong></div></td>
                              <td height="0"> <div align="center"><strong>CPF</strong></div></td>
                            </tr>
                            <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
                              <td colspan="2" ><div align="left">
								<a href="javascript:abrirPopup('AtualizarDadosTarifaSocialAction.do?id=<bean:write name="tarifaSocialDadoEconomia" property="id"/>', 600, 470);">

									<bean:write name="OTDClienteEconomia" property="cliente.nome"/>

			                    </a>
                               </div></td>
                              <td> <div align="left"><bean:write name="OTDClienteEconomia" property="complemento" />&nbsp;</div></td>
                              
                              
                              <td> <div align="right"><bean:write name="cliente" property="cpf"/></div></td>

                              
                            </tr>
                            <tr bordercolor="#FFFFFF" bgcolor="#99CCFF"> 
                              <td width="23%" height="0" bgcolor="#99CCFF"> <div align="center"><strong>RG</strong></div></td>
                              <td width="20%" bgcolor="#99CCFF"><div align="center"><strong>Data 
                                  Emiss&atilde;o </strong></div></td>
                              <td> <div align="center"><strong><strong>&Oacute;rg&atilde;o 
                                  Expedidor </strong></strong></div></td>
                              <td > <div align="center"></div>
                                <div align="center"><strong>UF</strong></div></td>
                            </tr>
                            <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
                              <td ><div align="right"><bean:write name="cliente" property="rg"/></div></td>
                              <td ><div align="center"><%= Util.formatarData(((gcom.cadastro.cliente.Cliente)cliente).getDataEmissaoRg())%></div></td>
                              <td><div align="left"><logic:present name="cliente" property="orgaoExpedidorRg" ><bean:write name="cliente" property="orgaoExpedidorRg.descricao"/></logic:present></div></td>
                              <td><div align="left"></div>
                                <div align="left"><logic:present  name="cliente" property="unidadeFederacao" ><bean:write name="cliente" property="unidadeFederacao.descricao" /></logic:present></div></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr> 
                        <td height="44"> <table width="100%" bgcolor="#99CCFF">
                            <!--header da tabela interna -->
                            <tr  bgcolor="#99CCFF"> 
                              <td width="23%" bgcolor="#99CCFF"> <div align="center"><strong>Cart&atilde;o 
                                  do Prog.Social</strong></div></td>
                              <td width="23%" bgcolor="#99CCFF"> <div align="center"><strong>Tipo 
                                  Cart&atilde;o Prog.Social</strong></div></td>
                              <td width="15%" bgcolor="#99CCFF"> <div align="center"><strong>Renda 
                                  Familiar</strong></div></td>
                              <td width="23%" bgcolor="#99CCFF"> <div align="center"><strong>Tipo 
                                  Renda Familiar</strong></div></td>
                            </tr>
                            <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
                              <td height="17" align="center"><font color="#333333"><logic:present name="tarifaSocialDadoEconomia" property="numeroCartaoProgramaSocial" ><bean:write name="tarifaSocialDadoEconomia" property="numeroCartaoProgramaSocial"/></logic:present></font></td>
                              <td><div align="left"><font color="#333333"><logic:present name="tarifaSocialCartaoTipo" ><bean:write name="tarifaSocialCartaoTipo" property="descricao"/></logic:present></font></div></td>
                              <td><div align="right"><font color="#333333"><bean:write name="tarifaSocialDadoEconomia" property="valorRendaFamiliar"/></font></div></td>
                              <td><div align="left"><font color="#333333"><logic:present name="rendaTipoDescricao" ><bean:write name="rendaTipoDescricao" /></logic:present></font></div></td>
                            </tr>
                          </table>
						  </td>
                      </tr>
                  
</logic:iterate>
                      <!--  Fim de um -->
                      <tr> 
                        <td>&nbsp;</td>
                      </tr>
                    </table></td>
                </tr>
              </table>
		   <table> <tr> <td width="100%">
          <table width="30%" border="0" align="right" cellpadding="0" cellspacing="2">
              <tr> 
                        <td width="61" align="left"></td>
                        <td width="416" align="left"><input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'"></td>
                <td width="12"></td>
                        <td width="61"><input name="Button2" type="button" class="bottonRightCol" value="Concluir" onClick="javascript:concluir();"></td>
                        <td width="82">&nbsp;</td>
              </tr>	  
			</table>
			</td>
			</tr>
</table>	
            <p>&nbsp;</p></tr>
       </table>
       <p>&nbsp;</p>

<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
