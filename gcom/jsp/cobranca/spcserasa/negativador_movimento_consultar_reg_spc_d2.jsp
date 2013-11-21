<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.util.ConstantesSistema"%>


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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="CriterioCobrancaActionForm" dynamicJavascript="false" />

<script language="JavaScript">

    function validarAtualizarForm(){   
   	  var form = document.forms[0];    	  
	  redirecionarSubmit('atualizarDadosRegistroAction.do');	  
	  chamarReload('exibirConsultarNegativadorMovimentoDadosAction.do');
	  window.close();
	}

 function verificarIndicadoCorrecao(){
	var form = document.forms[0];
    if(form.indicadorCorrecao.value != "-1" ){
        form.indicadorCorrecao.disabled = true;
        document.getElementById("buttonAtualizar").disabled = true;  
    }else{
        form.indicadorCorrecao.disabled = false;  
        document.getElementById("buttonAtualizar").disabled = false;   
    }
 }
 

</script>

</head>

<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(700, 500);setarFoco('${requestScope.nomeCampo}');verificarIndicadoCorrecao();">

<html:form action="/exibirConsultarDadosRegistroSPCAction"
	name="ConsultarDadosRegistroActionForm"
	type="gcom.gui.cobranca.spcserasa.ConsultarDadosRegistroActionForm"
	method="post">

	<table width="678" border="0" cellpadding="0" cellspacing="5">
  <tr> 
    <td width="668" valign="top" class="centercoltext"> <table height="100%">
        <tr> 
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="11"><img src="imagens/parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Consultar<strong> </strong>Registros do Movimento do Negativador </td>
          <td width="11"><img src="imagens/parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <table width="100%" border="0">
        <tr> 
          <td height="364"> 
            <table width="100%" border="0" dwcopytype="CopyTableRow">
              <tr> 
                <td colspan="3">&nbsp;</td>
              </tr>
              <tr>
                <td><strong>Negativador:</strong></td>
                <td colspan="2"><strong><b><span class="style4">
                  <html:text property="negativador" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0"/>
 
                </span></b></strong></td>
              </tr>
              <tr>
                <td><strong>Tipo do Movimento:</strong></td>
                <td colspan="2"><strong><b><span class="style4">
                  <html:text property="tipoMovimento" size="9" maxlength="9" readonly="true" style="background-color:#EFEFEF; border:0"/>                 
                </span></b></strong></td>
              </tr>
              <tr>
                <td colspan="3"><hr></td>
              </tr>
              <tr>
                <td colspan="3"><div align="center"><strong>Conte&uacute;do do Registro</strong></div></td>
              </tr>
             <tr>
                <td><strong>Tipo do Registro:</strong></td>
                <td colspan="2"><html:text property="tipoRegistroCodigo" size="2" maxlength="2" readonly="true" style="background-color:#EFEFEF; border:0"/>
                <html:text property="tipoRegistroDescricao" size="60" maxlength="60" readonly="true" style="background-color:#EFEFEF; border:0"/></td>
              </tr>
              <tr>
                <td><strong>Tipo Documento:</strong></td>
                <td colspan="2"><html:text property="tipoDocumentoCodigo" size="1" maxlength="1" readonly="true" style="background-color:#EFEFEF; border:0"/>               
               <html:text property="tipoDocumentoDescricao" size="4" maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0"/>
              </tr>
              <tr>
                <td><strong>CPF/CNPJ:</strong></td>
                <td colspan="2"><html:text property="cpfCnpj" size="15" maxlength="15" readonly="true" style="background-color:#EFEFEF; border:0"/>
               </td>
              </tr>
              <tr>
                <td><strong>C&oacute;digo de Opera&ccedil;&atilde;o:</strong></td>
                <td colspan="2"><html:text property="codigoOperacao" size="1" maxlength="1" readonly="true" style="background-color:#EFEFEF; border:0"/>
               				   <html:text property="operacao" size="8" maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0"/>
               </td>
              </tr>
              <tr>
                <td><strong>Comprador/Fiador/Avalista:</strong></td>
                <td colspan="2"><html:text property="compradorFiadorAvalista" size="1" maxlength="1" readonly="true" style="background-color:#EFEFEF; border:0"/>
                </td>
              </tr>
              <tr>
                <td><strong>Data de Vencimento do D&eacute;bito:</strong></td>
                <td colspan="2"><html:text property="dataVencimentoDebito" size="8" maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0"/>               
                  (DDMMAAAA) </td>
              </tr>
              <tr>
                <td><strong>Data de Registro:</strong></td>
                <td colspan="2"><html:text property="dataRegistro" size="8" maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0"/>               
                  (DDMMAAAA) </td>
              </tr>
              <tr>
                <td><strong>Valor do D&eacute;bito:</strong></td>
                <td><html:text property="valorDebito" size="13" maxlength="13" readonly="true" style="background-color:#EFEFEF; border:0"/> </td>
                <td><div align="right">
                  <input name="Button32224" type="button" class="bottonRightCol" value="Consultar Itens do D&eacute;bito" onClick="javascript:abrirPopup('exibirConsultarNegativadorMovimentoRegItemPopupAction.do', 410, 790);"         />
                </div></td>
              </tr>
              <tr>
                <td><strong>Contrato (Matr&iacute;cula do Im&oacute;vel):</strong></td>
                <td colspan="2"><html:text property="contrato" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0"/>   
                </td>
              </tr>
              <tr>
                <td><strong>Associado:</strong></td>
                <td colspan="2"><html:text property="associado" size="8" maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0"/> 
                </td>
              </tr>
              <tr>
                <td><strong>Natureza da Inclus&atilde;o:</strong></td>
                <td colspan="2"><html:text property="naturezaInclusao" size="2" maxlength="2" readonly="true" style="background-color:#EFEFEF; border:0"/> 
                </td>
              </tr>
              <tr> 
                <td width="37%"><strong>Motivo da Exclus&atilde;o:</strong></td>
                <td colspan="2"><html:text property="motivoExclusao" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0"/> 
                </td>
              </tr>
              <tr>
                <td><strong>C&oacute;digo de Retorno:</strong></td>
                <td colspan="2"><html:text property="codigoRetorno" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0"/>
               </td>
              </tr>
              <tr> 
                <td><strong>Sequencial do Registro:</strong></td>
                <td colspan="2"><html:text property="sequencialRegistro" size="15" maxlength="15" readonly="true" style="background-color:#EFEFEF; border:0"/>
                </td>
              </tr>
               <tr>
                <td colspan="3"><hr></td>
              </tr>
              <tr>
                <td colspan="3"><div align="center"><strong>Dados do Retorno  do Registro</strong></div></td>
              </tr>
              <tr>
                <td><strong>Indicador de Aceita&ccedil;&atilde;o:</strong></td>
                <td colspan="2"><html:text property="indicadorAceitacao" size="20" maxlength="20" readonly="true" style="background-color:#EFEFEF; border:0"/></td>
              </tr>
              <tr> 
                <td colspan="3"><table width="100%" border="0">
                  <tr bordercolor="#90c7fc">
                    <td colspan="6" bgcolor="#90c7fc"><strong>Ocorr&ecirc;ncias do Retorno</strong></td>
                  </tr>
                  <tr bordercolor="#000000">
                    <td width="23%" bgcolor="#90c7fc"><div align="center"><strong>C&oacute;digo</strong></div></td>
                    <td bgcolor="#90c7fc"><div align="center"><strong>Descri&ccedil;&atilde;o do Motivo de Retorno</strong> </div></td>
                    </tr>
                 
                

							<logic:present name="collNegativadorMovimentoRegRetMot">
								<%int cont = 1;%>
								<logic:iterate name="collNegativadorMovimentoRegRetMot" id="negativadorMovimentoRegRetMot">
									
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>

																						
											<td >
												<div align="center">
												<logic:notPresent name="acao" scope="session">
													
														<bean:write name="negativadorMovimentoRegRetMot" property="negativadorRetornoMotivo.codigoRetornoMotivo"/>														
													   &nbsp;		
																				
												</logic:notPresent></div>
											</td>					
											
											
											<td align="center">	
													<bean:write name="negativadorMovimentoRegRetMot" property="negativadorRetornoMotivo.descricaoRetornocodigo"/>				
											</td>
											</tr>
						
								</logic:iterate>
							</logic:present>
                 
                 

                </table>
                
                <table width="100%" border="0">


			</table>
                
                
                </td>
              </tr>
               <tr>
                <td colspan="3"><hr></td>
              </tr>
              
			  <tr>
                <td><strong>Indicador de Correção:</strong></td>
                <td colspan="2">                 
                  <html:select property="indicadorCorrecao" tabindex="7" >
                        <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>	 
						<html:option value="<%=""+ConstantesSistema.CORRIGIDO%>">CORRIGIDO</html:option>						
						<html:option value="<%=""+ConstantesSistema.NAO_CORRIGIDO%>">NÃO CORRIGIDO</html:option>						
					</html:select>			 
				</td>
              </tr> 
                
               <tr>
                <td colspan="3"><hr></td>
              </tr>
				
              
   
             <table width="100%">
				<tr>
					<td align="right"><strong><font color="#FF0000"> 					 
					     <input id ="buttonAtualizar" name="buttonAtualizar" type="button" class="bottonRightCol" value="Atualizar" onclick="javascript:validarAtualizarForm();" align="left">
					     <input  name="button" type="button" class="bottonRightCol" value="Fechar" onclick="window.close();" align="left">
					 </td>
				</tr>
			</table>
        
              
              
              <!-- <tr>
          <td height="15">&nbsp;</td>
          <td>&nbsp;</td>
        </tr> -->
            </table>
          </td>
        </tr>
      </table>
      <p>&nbsp;</p></td>
  </tr>
</table>
		     
            
		
		
	
</body>
</html:form>
</html:html>
