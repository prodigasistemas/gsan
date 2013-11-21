<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarParcelamentoPerfilActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--
  
  function abrirPopUPRegistro(idRegistro){
  
		abrirPopup('consultarNegativadorMovimentoRegGenericoAction.do?idRegistro='+idRegistro, 410, 790);
  }
  
  function voltar(){
	  var form = document.forms[0];
	  
	  form.action = '/gsan/exibirConsultarNegativadorMovimentoAction.do';
	  form.submit();	
  }
  function atualizar(){
	  var form = document.forms[0];
	  form.action = '/gsan/exibirConsultarNegativadorMovimentoDadosAction.do?reload=1&atualizar=1';
	  form.submit();	
  }
  
-->
</script>
</head>
<body leftmargin="0" topmargin="0"
	onload="setarFoco('${requestScope.nomeCampo}');submit();">
<html:form action="/exibirConsultarNegativadorMovimentoDadosAction"
	name="ConsultarNegativadorMovimentoActionForm"
	type="gcom.gui.cobranca.spcserasa.ConsultarNegativadorMovimentoActionForm"
	method="post" >

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	



	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="120" valign="top" class="leftcoltext">
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
			<td valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>

			</table>
		
			    <!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                <td class="parabg">Consultar Dados do Movimento do Negativador</td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
<p>&nbsp;</p>
            <table width="100%" border="0" >
              <tr> 
                <td colspan="2"><div align="center"><STRONG>Dados do Movimento</STRONG></div></td>
              </tr>
              <tr> 
                <td width="262"><strong>Negativador:<strong><font color="#FF0000"></font></strong></strong></td>
                <td><strong><b><span class="style4">
                   <html:text property="negativador" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0"/>
                </span></b></strong></td>
              </tr>
              <tr> 
                <td width="262"><strong>Tipo do Movimento:</strong></td>
                <td><strong><b><span class="style4">
                 <logic:equal  name="negativadorMovimento"  property="codigoMovimento"  value="1">				
                      <html:text property="negativadorMovimento" value="Inclusão" size="9" maxlength="9" readonly="true" style="background-color:#EFEFEF; border:0"/>
                  </logic:equal>	 
                  
                  <logic:equal  name="negativadorMovimento"  property="codigoMovimento"  value="2">				
                      <html:text property="negativadorMovimento" value="Exclusão" size="9" maxlength="9" readonly="true" style="background-color:#EFEFEF; border:0"/>
                  </logic:equal>
                  
                </span></b></strong></td>
              </tr>
              <tr>
                <td><strong>N&uacute;mero Sequencial do Arquivo (NSA):</strong></td>
                <td><strong><b><span class="style4">                  
                  <html:text property="numeroSequencialEnvio" size="9" maxlength="9" readonly="true" style="background-color:#EFEFEF; border:0"/>
                </span></b></strong></td>
              </tr>
              <tr>
                <td><strong>Total de Registros Enviados:</strong></td>
                <td><strong><b><span class="style4">
                   <html:text property="numeroRegistrosEnvio" size="9" maxlength="9" readonly="true" style="background-color:#EFEFEF; border:0"/>               
                </span></b></strong></td>
              </tr>
              <tr> 
                <td><strong>Valor Total do D&eacute;bito:</strong></td>
                <td><strong><b><span class="style4">
                   <html:text property="valorTotalEnvio" size="20" maxlength="20" readonly="true" style="background-color:#EFEFEF; border:0"/>                     
                </span></b></strong></td>
              </tr>
              <tr>
                <td height="25"><strong>Data e Hora de Processamento do Envio:</strong></td>
                <td><strong><b><span class="style4">
                   <html:text property="dataEnvio" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0"/>                 
                  </span></b></strong><strong>-</strong><strong><b><span class="style4">
                  <html:text property="horaEnvio" size="8" maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0"/>                  
                </span></b></strong></td>
              </tr>
              <tr>
                <td height="25"><strong>Situa&ccedil;&atilde;o do Movimento:</strong></td>
                <td><strong><b><span class="style4">                
                  <html:text property="situacaoMovimento" size="20" maxlength="20" readonly="true" style="background-color:#EFEFEF; border:0"/>                  
                </span></b></strong></td>
              </tr>
              <tr>
                <td height="25"><strong>Total de Registros Aceitos:</strong></td>
                <td><strong><strong><strong><strong><strong><strong><b><span class="style4">                
                 <html:text property="totalRegistrosAceitos" size="9" maxlength="9" readonly="true" style="background-color:#EFEFEF; border:0"/>   
                </span></b></strong></strong></strong></strong></strong></strong></td>
              </tr>
              <tr>
                <td height="25"><strong>Total de Registros Não Aceitos:</strong></td>
                <td><strong><strong><strong><strong><strong><strong><b><span class="style4">
                  <input type="text" value="${ConsultarNegativadorMovimentoActionForm.numeroRegistrosEnvio - ConsultarNegativadorMovimentoActionForm.totalRegistrosAceitos}" size="9" maxlength="9" readonly="true" style="background-color:#EFEFEF; border:0" />
                </span></b></strong></strong></strong></strong></strong></strong></td>
              </tr>
              <tr>
                <td height="25"><strong>Data e Hora de Processamento do Retorno:</strong></td>
                <td><strong><b><span class="style4">
                 <html:text property="dataRetorno" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0"/>   
                 </span></b></strong><strong>-</strong><strong><b><span class="style4">
                  <html:text property="horaRetorno" size="8" maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0"/>                  
                </span></b></strong></td>
              </tr>
              
              <tr> 
                <td ><strong>Corrigir:<strong><font color="#FF0000"></font></strong></strong></td>
                <td>
            	 <html:select property="indicadorCorrecao">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				   <html:option value="<%=""+ConstantesSistema.SIM%>">CORRIGIR</html:option>
				   <html:option value="<%=""+ConstantesSistema.NAO%>">NÃO CORRIGIR</html:option>
				</html:select>
         		 </td>
              </tr>
              <tr>
                <td colspan="2"><hr></td>
              </tr>
              <tr>
                <td colspan="2"><table width="100%" border="0">
                  <tr bordercolor="#90c7fc">
                    <td colspan="8" bgcolor="#90c7fc"><div align="center"><strong>Registros do Movimento</strong></div></td>
                  </tr>
                  <tr bordercolor="#000000">
                    <td width="10%" bgcolor="#90c7fc"><div align="center"><strong>Selecionar </strong></div></td>
                    <td width="15%" bgcolor="#90c7fc"><div align="center"><strong>N&uacute;mero do Registro </strong></div></td>
                    <td width="20%" bgcolor="#90c7fc"><div align="center"><strong>Tipo do Registro</strong> </div></td>
                    <td width="20%" bgcolor="#90c7fc"><div align="center"><strong>Matrícula do Imóvel</strong> </div></td>
                    <td width="15%" bgcolor="#90c7fc"><div align="center"><strong>Indicador</strong><strong> de Aceito </strong></div></td>
                    <td width="20%" bgcolor="#90c7fc"><div align="center"><strong>Indicador</strong><strong> de Corre&ccedil;&atilde;o </strong><strong> </strong></div></td>
                    </tr>                    
                    <%--Esquema de paginação--%>
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />

							<logic:present name="collNegativadorMovimentoReg">
								<%int cont = 1;%>
								<logic:iterate name="collNegativadorMovimentoReg" id="negativadorMovimentoReg">
									<pg:item>
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td >
												<div align="center">
												 <input type="checkbox" name="idRegistrosCorrecao" value="${negativadorMovimentoReg.id}" />		
								                </div>
											</td>
																						
											<td >
												<div align="center">
											
														
														<a href="javascript:abrirPopUPRegistro('<bean:write name="negativadorMovimentoReg" property="id"/>');
														">
														<bean:write name="negativadorMovimentoReg" property="numeroRegistro"/>
														</a>
													   &nbsp;		
								                </div>
											</td>
											
											<td align="center">
												<bean:write name="negativadorMovimentoReg" property="negativadorRegistroTipo.codigoRegistro"/> -
												<bean:write name="negativadorMovimentoReg" property="negativadorRegistroTipo.descricaoRegistroTipo"/>
											</td>
											
											<td align="center">
												<logic:empty name="negativadorMovimentoReg" property="imovel" >
												&nbsp;	
												</logic:empty>
												<logic:notEmpty name="negativadorMovimentoReg" property="imovel" >
													<bean:write name="negativadorMovimentoReg" property="imovel.id"/> 
												</logic:notEmpty>
											
												
											</td>
											
											<td align="center">		
												&nbsp;										 
												<logic:equal  name="negativadorMovimentoReg"  property="indicadorAceito"  value="1">				
                      								SIM
                  								</logic:equal>	
												<logic:equal  name="negativadorMovimentoReg"  property="indicadorAceito"  value="2">				
                      								NAO
                  								</logic:equal>							
											</td>
											
											<td align="center">	
											&nbsp;													 
												<logic:equal  name="negativadorMovimentoReg"  property="indicadorCorrecao"  value="1">				
                      							   SIM
                  								</logic:equal>	
												<logic:equal  name="negativadorMovimentoReg"  property="indicadorCorrecao"  value="2">				
                      								NAO
                  								</logic:equal>							
											</td>
											</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
                    
                                
                </table>
                
                <table width="100%" border="0">

				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
				</tr>
<%-- Fim do esquema de paginação --%>
			</table>
                
                
                
                
                </td>
              </tr>
              <tr> 
                <td colspan="2">&nbsp;</td>
              </tr>
              
              
              
              
              
              
              <tr> 
                <td> 
                  <div align="left"><strong><font color="#FF0000">
                   <input type="submit" name="Submit223" class="bottonRightCol" value="Voltar" onclick="javascript:voltar();">
                  </font></strong></div></td>
                   <td > 
                  <div align="right"><strong><font color="#FF0000">
                   <input type="submit" name="Submit223" class="bottonRightCol" value="Atualizar" onclick="javascript:atualizar();">
                  </font></strong></div></td>
              </tr>
              
              
            </table>
			
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>




</html:form>

</body>
</html:html>
