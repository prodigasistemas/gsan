<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page isELIgnored="false" %>
<%@ page import="gcom.util.Pagina" %>
<%@ page import="gcom.cadastro.cliente.ClienteImovel" %>
<%@ page import="gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

	<SCRIPT LANGUAGE="JavaScript">
	<!--
	function validateInserirTarifaSocialActionForm(){
		return true;
	}
	//-->
	</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/inserirTarifaSocialWizardAction" method="post">


<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
<input type="hidden" name="numeroPagina" value="2"/>
  <tr>
    <td width="115" valign="top" class="leftcoltext">
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
    <td valign="top" class="centercoltext">

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
          <td width="100%" height="17"><b>Rela&ccedil;&atilde;o de clientes
           usu&aacute;rios por economia</b></td>
      </tr>
	  <tr>
          <td height="200"> <div style="width: 100%; height: 100%; overflow: auto;">

		  <logic:iterate id="clienteImovelEconomia" name="colecaoClienteImovelEconomia">
                    
				<table width="100%" >
				<tr>
                    <td height="82">
						
						<table width="100%" bgcolor="#99CCFF">
                            <tr bordercolor="#FFFFFF" bgcolor="#0066FF">
								<td height="0" colspan="2" bgcolor="#99CCFF"> <div align="center"><strong>Cliente
									Usuário</strong></div>
								</td>
								<td width="26%" height="0" bgcolor="#99CCFF"> <div align="center"><strong>Complemento
									Endereço</strong></div>
								</td>
								<td height="0" bgcolor="#99CCFF"> <div align="center"><strong>CPF</strong></div></td>
                            </tr>
                            <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
								<td colspan="2" ><div align="left">
									<a href="javascript:abrirPopup('exibirInserirTarifaSocialDadosCartaoMultiplasEconomias.do?clienteImovelEconomia=${clienteImovelEconomia.id}', 430, 470);"><bean:write name="clienteImovelEconomia" property="cliente.nome"/></a></div>
								</td>
								<td> 
									<div align="left"><bean:write name="clienteImovelEconomia" property="imovelEconomia.complementoEndereco"/></div>
								</td>
								<td><div align="right">
								
									<logic:present name="clienteImovelEconomia" property="cliente.cpf">
										<bean:write name="clienteImovelEconomia" property="cliente.cpfFormatado"/>
									</logic:present>
								
									<logic:present name="clienteImovelEconomia" property="cliente.cpf">
										&nbsp;
									</logic:present>
				
								</div></td>
                            </tr>
                            <tr bordercolor="#FFFFFF" bgcolor="#0066FF">
								<td width="23%" height="0" bgcolor="#99CCFF"> <div align="center"><strong>RG</strong></div>
								</td>
								<td width="20%" bgcolor="#99CCFF"><div align="center"><strong>Data
									Emiss&atilde;o </strong></div>
								</td>
								<td bgcolor="#99CCFF"> <div align="center" bgcolor="#99CCFF"><strong><strong>&Oacute;rg&atilde;o
									Expedidor </strong></strong></div>
								</td>
								<td bgcolor="#99CCFF"> <div align="center"></div>
									<div align="center"><strong>UF</strong></div>
								</td>
                            </tr>
                            <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
								<td>
									<div align="right">
									
									<logic:present name="clienteImovelEconomia" property="cliente.rg">
										<bean:write name="clienteImovelEconomia" property="cliente.rg"/>
									</logic:present>
								
									<logic:present name="clienteImovelEconomia" property="cliente.rg">
										&nbsp;
									</logic:present>
									
									</div>
								</td>
								<td>
									<div align="center">
									
									<logic:present name="clienteImovelEconomia" property="cliente.dataEmissaoRg">
										<bean:write name="clienteImovelEconomia" property="cliente.dataEmissaoRg" formatKey="date.format"/>
									</logic:present>
								
									<logic:present name="clienteImovelEconomia" property="cliente.dataEmissaoRg">
										&nbsp;
									</logic:present>			
									
									</div>
								</td>
								<td><div align="left">
								
									<logic:present name="clienteImovelEconomia" property="cliente.orgaoExpedidorRg">
										<bean:write name="clienteImovelEconomia" property="cliente.orgaoExpedidorRg.descricaoAbreviada"/>
									</logic:present>
								
									<logic:present name="clienteImovelEconomia" property="cliente.orgaoExpedidorRg">
										&nbsp;
									</logic:present>
								</div>
								</td>
								<td><div align="left"></div>
									<div align="center">
									
									<logic:present name="clienteImovelEconomia" property="cliente.unidadeFederacao">
										<bean:write name="clienteImovelEconomia" property="cliente.unidadeFederacao.sigla"/>
									</logic:present>
								
									<logic:present name="clienteImovelEconomia" property="cliente.unidadeFederacao">
										&nbsp;
									</logic:present>
								
									</div>
								</td>
                            </tr>
                          </table>
					</td>
				</tr>

			<%-- Inicio iterate interno --%>

				<logic:present name="clienteImovelEconomia" property="imovelEconomia.tarifaSocialDadoEconomias">

				<logic:iterate id="tarifaSocialDadoEconomia" name="clienteImovelEconomia" property="imovelEconomia.tarifaSocialDadoEconomias">
              
				<tr>
                    <td height="44"> 
					
						<table width="100%" bgcolor="#99CCFF">
                            <tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">
                              
							  <logic:notPresent name="tarifaSocialDadoEconomia" property="id">
								<td  width="9%" bgcolor="#99CCFF"> <div align="center"><strong>Remover</strong></div></td> 
							  </logic:notPresent> 
                              
							  <td width="23%" bgcolor="#99CCFF"> <div align="center"><strong>Cart&atilde;o
                                  do Prog.Social</strong></div>
							  </td>
                              <td width="23%" bgcolor="#99CCFF"> <div align="center"><strong>Tipo
                                  Cart&atilde;o Prog.Social</strong></div>
							  </td>
                              <td width="15%" bgcolor="#99CCFF"> <div align="center"><strong>Renda
                                  Familiar</strong></div>
							  </td>
                              <td width="23%" bgcolor="#99CCFF"> <div align="center"><strong>Tipo
                                  Renda Familiar</strong></div>
						      </td>
                            </tr>
                            <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">

							<logic:notPresent name="tarifaSocialDadoEconomia" property="id">
                              <td height="17" align="center"> 
									<html:link href="removerInserirTarifaSocialColecaoAction.do" paramId="remover" paramProperty="ultimaAlteracao" paramName="tarifaSocialDadoEconomia" title="remover">
									<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></html:link>
							  </td>
							</logic:notPresent>

                              <td align="center"><font color="#333333"><bean:write name="tarifaSocialDadoEconomia" 	property="numeroCartaoProgramaSocial"/></font>
							  </td>
							  <td align="left">

								<logic:notEmpty name="tarifaSocialDadoEconomia" property="tarifaSocialCartaoTipo">
									<font color="#333333"><bean:write name="tarifaSocialDadoEconomia" property="tarifaSocialCartaoTipo.descricao"/></font>
								</logic:notEmpty>
								<logic:empty name="tarifaSocialDadoEconomia" property="tarifaSocialCartaoTipo">
	  								&nbsp;
								</logic:empty>
							
							  </td>
                              <td><div align="right"><font color="#333333">
								<logic:notEmpty name="tarifaSocialDadoEconomia" property="valorRendaFamiliar">
									<bean:write name="tarifaSocialDadoEconomia" property="valorRendaFamiliar" format="0.00"/>
								</logic:notEmpty>
								</font></div>
							  </td>
							  <td >
			      
								<logic:notEmpty name="tarifaSocialDadoEconomia" property="rendaTipo">
                                       <div align="left"><font color="#333333"><bean:write name="tarifaSocialDadoEconomia" property="rendaTipo.descricao"/></font></div>
								</logic:notEmpty>
								<logic:empty name="tarifaSocialDadoEconomia" property="rendaTipo">
                              		&nbsp;
								</logic:empty>
							</td>

                            </tr>
                        </table>

 			        </td>
			</logic:iterate>

			</logic:present>

		</logic:iterate>

                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                </table>
				</div>
			</td>
          </tr>
          <tr>
				<td align="left">
					<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2"/>
                </td>
          </tr>
          </table>

	<p>&nbsp;</p>
	</td>
</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>

