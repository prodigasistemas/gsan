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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FimRelacaoClienteImovelActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
</head>

<body leftmargin="0" topmargin="0" >

<html:form
   action="/manterFimRelacaoClienteImovelAction"
   name="FimRelacaoClienteImovelActionForm"
   type="gcom.gui.cadastro.imovel.FimRelacaoClienteImovelActionForm"
   method="post"
   onsubmit="return validateFimRelacaoClienteImovelActionForm(this);"
   >
<table width="600" border="0" cellpadding="0" cellspacing="5">
<tr>
    <td width="600" valign="top" class="centercoltext">
      <table height="100%">
      <tr>
		  <td></td>
      </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Fim de relação cliente imóvel</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
      </tr>
      </table>
      <table width="100%" border="0">
      <tr>
    	  <td>&nbsp;</td>
      </tr>
      <tr>
          <td colspan="2">Clientes selecionados para remoção.</td>
      </tr>
      <tr>
          <td>
                <table width="100%" cellpadding="0" cellspacing="0">
                  <tr>
                      <td height="0">
                          <div style="width: 100%; height: 100%; overflow: auto;">
                             <table width="100%" bgcolor="#99CCFF">
                                 <!--header da tabela interna -->
                                  <tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
                                  	  <td width="15%"> <div align="center">Código</div></td>	
                                      <td width="35%"> <div align="center">Nome</div></td>
                                      <td width="18%">Tipo<div align="center"></div></td>
                                      <td width="25%">CPF/CNPJ<div align="center"></div></td>
                                 </tr>
                             </table>
                          </div>
                      </td>
                  </tr>
                  <tr>
                      <td>
			     <div style="width: 100%; height: 100%; overflow: auto;">
                              <table width="100%" align="center" bgcolor="#99CCFF">
                              <%int cont=0;%>
                             <logic:iterate name="colecaoClientesImoveisEconomiaFimRelacao" id="clienteImovelEconomia">
                             <%
                             cont = cont+1;
                             if (cont%2==0){%>
                              <tr bgcolor="#cbe5fe">
                             <%}else{ %>
                              <tr bgcolor="#FFFFFF">
                             <%}%>
								  <td width="15%" align="center"><bean:write name="clienteImovelEconomia" property="cliente.id"/></td>	
                                  <td width="35%"><bean:write name="clienteImovelEconomia" property="cliente.nome"/></td>
                                  <td width="18%"> <bean:write name="clienteImovelEconomia" property="clienteRelacaoTipo.descricao"/></td>
                                  <td width="25%" align="right">
                                  <logic:notEmpty name="clienteImovelEconomia" property="cliente.cpf">
                                   <bean:write name="clienteImovelEconomia" property="cliente.cpfFormatado"/>
                                  </logic:notEmpty>
                                  <logic:notEmpty name="clienteImovelEconomia" property="cliente.cnpj">
                                   <bean:write name="clienteImovelEconomia" property="cliente.cnpjFormatado"/>
                                  </logic:notEmpty>
                                 </td>
                              </tr>
                               </logic:iterate>
                              </table>
                          </div>
			</td>
                  </tr>
                 </table>
		<tr>
                  <td colspan="2">
                      <table width="100%">
                      <tr>
                          <td width="10%"><strong>Data Término Relação<font color="#FF0000">*</font></strong></td>
                          <td width="14%"><html:text property="dataTerminoRelacao" size="10" maxlength="10" onkeypress="mascaraData(this, event);"/>
                          <a href="javascript:abrirCalendario('FimRelacaoClienteImovelActionForm', 'dataTerminoRelacao')"><img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
                          dd/mm/aaaa
                         </td>
                      </tr>
		      <tr>
                      <td width="10%"><strong>Motivo<font color="#FF0000">*</font></strong></td>
                      <td width="56%">
                        <html:select property="idMotivo">
                        <html:option value="-1">&nbsp;</html:option>
                          <html:options collection="clienteImoveisFimRelacaoMotivo" labelProperty="descricao" property="id"/>
                        </html:select><font size="1">&nbsp; </font>
                          </td>
                      </tr>
					  <tr>
                          <td>&nbsp;</td>
                          <td><strong><font color="#FF0000">*</font></strong> Campos
                          obrigat&oacute;rios</td>
                      </tr>
                      <tr>
                           <td>
                           <input type="button" class="bottonRightCol" value="Voltar" onclick="javascript:history.back();"/>
                           </td>
		     			  <td align="right">
		                    <html:submit styleClass="bottonRightCol" value="Confirmar" property="Button" />
                          </td>
                      </tr>
                      </table>
	              </td>
              </tr>
      </table>
   </td>
</tr>
</table>
</html:form>
</body>
</html:html>
