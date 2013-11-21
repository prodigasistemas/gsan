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
<script language="JavaScript">
<!-- Begin
    function DateValidations () {
      this.aa = new Array("dataTerminoRelacao", "Data Término Relação inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }


function confirmar(){
	var form = document.forms[0];
	if(validateDate(form)){
	    form.action='manterImovelFimRelacaoClienteImovelAction.do';
		form.submit();
	}
}  
  
-->
</script>


<logic:present name="reloadPage">
 <body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('atualizarImovelWizardAction.do?action=exibirAtualizarImovelClienteAction'); window.close();">
</logic:present>

<logic:notPresent name="reloadPage">
 <body leftmargin="0" topmargin="0" onload="resizePageSemLink(640,300);">
</logic:notPresent>

<html:form
   action="/manterImovelFimRelacaoClienteImovelAction"
   name="FimRelacaoClienteImovelActionForm"
   type="gcom.gui.cadastro.imovel.FimRelacaoClienteImovelActionForm"
   method="post"
   onsubmit=""
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
								<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
                                      <td width="50%"><div align="center"><strong>Nome</strong></div></td>
                                      <td width="18%"><strong>Tipo</strong><div align="center"></div></td>
                                      <td width="25%"><strong>CPF/CNPJ</strong><div align="center"></div></td>
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
                              <logic:present name="colecaoClientesImoveisFimRelacao"> 
                             <logic:iterate name="colecaoClientesImoveisFimRelacao" id="clienteImovel">
                             <%
                             cont = cont+1;
                             if (cont%2==0){%>
                              <tr bgcolor="#cbe5fe">
                             <%}else{ %>
                              <tr bgcolor="#FFFFFF">
                             <%}%>

                                  <td width="50%"><bean:write name="clienteImovel" property="cliente.nome"/></td>
                                  <td width="18%"> <bean:write name="clienteImovel" property="clienteRelacaoTipo.descricao"/></td>
                                  <td width="25%">
                                  <logic:notEmpty name="clienteImovel" property="cliente.cpf">
                                   <bean:write name="clienteImovel" property="cliente.cpfFormatado"/>
                                  </logic:notEmpty>
                                  <logic:notEmpty name="clienteImovel" property="cliente.cnpj">
                                   <bean:write name="clienteImovel" property="cliente.cnpjFormatado"/>
                                  </logic:notEmpty>
                                 </td>
                              </tr>
                               </logic:iterate>
                               </logic:present>
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
                          <a href="javascript:abrirCalendario('FimRelacaoClienteImovelActionForm', 'dataTerminoRelacao')">
                            <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/>
                          </a>
                          <font size="1">(dd/mm/aaaa) </font>
                         </td>
                      </tr>
		      <tr>
                      <td width="10%"><strong>Motivo</strong></td>
                      <td width="56%">
                        <html:select property="idMotivo">
                          <html:options collection="clienteImoveisFimRelacaoMotivo" labelProperty="descricao" property="id"/>
                        </html:select>
                          </td>
                      </tr>
					  <tr>
                          <td>&nbsp;</td>
                          <td><strong><font color="#FF0000">*</font></strong> Campos
                          obrigat&oacute;rios</td>
                      </tr>
                      <tr>
                           <td><strong></strong></td>
     			  <td align="right" >
     			  <input type="button"
						class="bottonRightCol" style="bottonRightCol" value="Confirmar"
						onclick="javascript:confirmar();" />
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
