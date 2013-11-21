<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<link rel="stylesheet"	href="<bean:message key="caminho.css"/>popup.css"	type="text/css">
		<link rel="stylesheet"
			href="<bean:message key="caminho.css"/>EstilosCompesa.css"
			type="text/css">
		<script language="JavaScript"
			src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<script language="JavaScript"
			src="<bean:message key="caminho.js"/>util.js">
		</script>
		<script language="JavaScript">
			<!--
				function atualizar(obj){
					var form = document.forms[0];
					if(obj.value == 0){
						form.inicioParcelas.disabled = false;
					}
					else{
						form.inicioParcelas.disabled = true;
						form.inicioParcelas.value = "";
					}
				}
			-->
		
		</script>
		<script language="JavaScript"
			src="<bean:message key="caminho.js"/>Calendario.js"></script>
			<style type="text/css">
				<!--
				.desabilitado { background-color:#EFEFEF; border:0 }
				-->
				</style>
	</head>

	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(790, 590);">
		<html:form action="gerarRelatorioEmitirComprovantePagContratoParcelamentoAction.do"
				   name="EmitirComprovantePagamentoContratoParcelamentoClienteActionForm"
				   type="gcom.gui.cobranca.contratoparcelamento.EmitirComprovantePagamentoContratoParcelamentoClienteActionForm"
				   method="POST">
			<table width="662" border="0" cellpadding="0" cellspacing="5">
			  <tr> 
			    <td width="652" valign="top" class="centercoltext"> <table height="100%">
			        <tr> 
			          <td></td>
			        </tr>
			      </table>
			      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			        <tr> 
			          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" border="0" /></td>
			          <td class="parabg">Emitir Comprovante de Pagamento de Contrato de Parcelamento por Cliente<strong></strong></td>
			          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" border="0" /></td>
			        </tr>
			      </table>
			      <table width="100%" border="0">
			        <tr> 
			          <td height="148"><p>&nbsp;</p>
			            <table width="100%" border="0" >
			              <tr>
			                 <td height="18" colspan="2" bgcolor="#0099FF"><div align="center"><span class="style2"> Dados    do Contrato </span><strong></strong></div></td>
			              </tr>
			              <tr>
							<td><strong>N&uacute;mero do Contrato:</strong></td>
			              <td><strong>
			                  <html:text
			                        styleClass="desabilitado"
			                        size="10"
			                        maxlength="10"
			                        property="idNumeroContrato"
			                        readonly="true"/>
			                </strong></td>
			              </tr>
			              <tr>
			                <td width="30%"><strong>Cliente:</strong></td>
			                <td width="70%">
				                <strong>
					                <strong>
					                  <html:text
						                   property="idCliente"
						                   readonly="true"
						                   styleClass="desabilitado" 
						                   size="9" 
						                   maxlength="9"/>
						            </strong>
						            <html:text
					                   property="nomeCNPJ"
					                   readonly="true"
					                   styleClass="desabilitado" 
					                   size="50" 
					                   maxlength="50"/>
				                    <strong>	
		    	    			        <html:text
						                   property="tipoRelacao"
						                   readonly="true"
						                   styleClass="desabilitado" 
						                   size="14" 
						                   maxlength="14"/>
			                   		</strong>
		                      	</strong>
	                        </td>
			              </tr>
			              <tr>
			                <td><strong>Data Implanta&ccedil;&atilde;o do Contrato:</strong></td>
			                <td><strong>
			                  <html:text
			                  	 property="dataImplantacaoContrato"
			                  	 readonly="true"
			                  	 styleClass="desabilitado"
			                  	 size="10"
			                  	 maxlength="10"/>
			                </strong></td>
			              </tr>
			              <tr>
			                <td colspan="2"><hr></td>
			              </tr>
			              <tr>
			                <td height="18" colspan="2" bgcolor="#0099FF"><div align="center"><span class="style2"> Dados    do Pagamento</span><strong></strong></div></td>
			              </tr>
			              <tr>
			                <td height="52" colspan="2">
			                	<table width="704" border="1">
				                  <tr>
				                    <td width="97" bordercolor="#000000" bgcolor="#90c7fc"><div align="center"><strong> No. Parcela</strong></div></td>
				                    <td width="140" bordercolor="#000000" bgcolor="#90c7fc"><div align="center"><strong>Valor 
				                      da Parcela</strong></div></td>
				                    <td width="115" bordercolor="#000000" bgcolor="#90c7fc"><div align="center">
				                      <blockquote>
				                        <p><strong>Valor 
				                          Pago</strong></p>
				                      </blockquote>
				                    </div></td>
				                    <td width="140" bordercolor="#000000" bgcolor="#90c7fc"><div align="center"><strong>Data Pagamento</strong></div></td>
				                    <td width="178" bordercolor="#000000" bgcolor="#90c7fc"><div align="center"><strong>Itens Pagos</strong></div></td>
				                  </tr>
				                  <logic:present name="parcelas" scope="session">
	                                  <c:forEach  items="${sessionScope.parcelas}" var="parcela">
						                  <tr>
						                    <td height="19"><div align="center"><c:out value="${parcela.numero}/${parcela.contratoParcelamento.numeroPrestacoes}"/></div></td>
						                    <td><div align="center"><c:out value="${parcela.valor}"/></div></td>
						                    <td><div align="center"><c:out value="${parcela.valorPagamento}"/></div></td>
						                    <td><div align="center">    <fmt:formatDate value="${parcela.dataPagamento}" pattern="dd/MM/yyyy" /></div></td>
						                    <td><div align="center">${parcela.itensPagos}</div></td>
						                  </tr>
					                  </c:forEach>
				                  </logic:present>
				     
				                </table>
				             </td>
			              </tr>
			              <tr>
			                <td colspan="2"><hr></td>
			              </tr>
			              <tr>
			                <td height="18" colspan="2" bgcolor="#0099FF"><div align="center"><span class="style2"> Dados para Emiss&atilde;o do Comprovante de Pagamento</span> </div></td>
			              </tr>
			              <tr>
			                <td><strong>Ocultar N&uacute;mero da Parcela:<strong><font color="#FF0000">*</font></strong> </strong></td>
			                <td><strong>
			                  <label>
			                 	 <html:radio property="ocultarParcela" value="1" onclick="javascript: atualizar(this);"/>
				                   Sim
				              </label>
			                  <label>
			                  	<html:radio property="ocultarParcela" value="0" onclick="javascript: atualizar(this);"/>
			                    N&atilde;o
			                  </label>
			                </strong></td>
			              </tr>
			              <tr>
			                <td><strong>Parcela para Emiss&atilde;o: </strong></td>
			                <td>
			                	<html:select property="parcelaEmissao">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">Todas</html:option>
									<logic:present name="quantidadeParcelas" scope="session">	
										<html:options collection="parcelas" property="numero" labelProperty="numero"/>
									</logic:present>
								</html:select>
							</td>
			              </tr>
			              <tr>
			                <td><strong>Iniciar Numera&ccedil;&atilde;o das Parcelas a partir de: </strong></td>
			                <td><strong>
				                  <html:text 
				                  	property="inicioParcelas" 
				                  	size="3" 
				                  	maxlength="3"
        	                  	    onkeypress="return isCampoNumerico(event);"
				                  	/>
			                </strong></td>
			              </tr>
			              <tr>
			                <td height="19" colspan="2"><hr></td>
			              </tr>
			            </table></td>
			        </tr>
			      </table>
			      <table width="100%" border="0">
			        <tr>
			          <td><input name="Button23" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:window.close();"></td>
			          <td><div align="right">
			            <input name="Button" type="button" class="bottonRightCol" value="Emitir" onClick="toggleBox('demodiv',1);">
			            </div></td>
			        </tr>
			      </table>      <p>&nbsp;</p></td>
			  </tr>
			</table>
		<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioEmitirComprovantePagContratoParcelamentoAction.do" />
		</html:form>	
	</body>
</html:html>
