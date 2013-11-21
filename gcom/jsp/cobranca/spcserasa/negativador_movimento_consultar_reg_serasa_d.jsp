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


<html:form action="/exibirConsultarDadosRegistroSERASAAction"
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
          <td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Consultar<strong> </strong>Registros do Movimento do Negativador </td>

          <td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
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
                  <html:text property="negativador" disabled="true" style="background-color:#EFEFEF; border:0" size="50" maxlength="50"/>
                </span></b></strong></td>
              </tr>
              <tr>
                <td><strong>Tipo do Movimento:</strong></td>

                <td colspan="2"><strong><b><span class="style4">
                  <html:text property="tipoMovimento" disabled="true" style="background-color:#EFEFEF; border:0" size="10" maxlength="10"/>
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
                <td colspan="2">
	                <html:text property="tipoRegistroCodigo" disabled="true" style="background-color:#EFEFEF; border:0" size="2" maxlength="2"/>
	                <html:text property="tipoRegistroDescricao" disabled="true" style="background-color:#EFEFEF; border:0" size="60" maxlength="60"/>
                </td>
              </tr>
              <tr>
                <td><strong>C&oacute;digo da Opera&ccedil;&atilde;o:</strong></td>

                <td colspan="2">
                	<html:text property="codigoOperacao" disabled="true" style="background-color:#EFEFEF; border:0" size="2" maxlength="2"/>
                	<html:text property="operacao" disabled="true" style="background-color:#EFEFEF; border:0" size="8" maxlength="8"/>
                </td>
              </tr>
              <tr>
                <td><strong>CNPJ da Contratante:</strong></td>
                <td colspan="2">
                <html:text property="cnpj" disabled="true" style="background-color:#EFEFEF; border:0" size="6" maxlength="6"/>
                  (filial e d&iacute;gito) </td>
              </tr>

              <tr>
                <td><strong>Data da Ocorr&ecirc;ncia:</strong></td>
                <td colspan="2">
                	<html:text property="dataOcorrencia" disabled="true" style="background-color:#EFEFEF; border:0" size="10" maxlength="10"/>
                  (AAAAMMDD) </td>
              </tr>
              <tr>
                <td><strong>Data do T&eacute;rmino do Contrato:</strong></td>

                <td colspan="2">
					<html:text property="dataTerminoContrato" disabled="true" style="background-color:#EFEFEF; border:0" size="10" maxlength="10"/>
                  (AAAAMMDD) </td>
              </tr>
              <tr>
                <td><strong>C&oacute;digo de Natureza da Opera&ccedil;&atilde;o:</strong></td>
                <td colspan="2">
                	<html:text property="codigoNaturezaOperacao" disabled="true" style="background-color:#EFEFEF; border:0" size="3" maxlength="3"/>
                </td>
              </tr>

              <tr>
                <td><strong>C&oacute;digo da Pra&ccedil;a Embratel:</strong></td>
                <td colspan="2">
					<html:text property="codigoPracaEmbratel" disabled="true" style="background-color:#EFEFEF; border:0" size="4" maxlength="4"/>
				</td>
              </tr>
              <tr>
                <td><strong>Tipo de Pessoa do Principal:</strong></td>
                <td colspan="2">
					<html:text property="tipoPessoaPrincipal" disabled="true" style="background-color:#EFEFEF; border:0" size="8" maxlength="8"/>
				</td>
              </tr>
              <tr>
                <td><strong>Tipo do Primeiro Documento do Principal:</strong></td>
                <td colspan="2">
                	<html:text property="tipoPrimeiroDocumentoPrincipal" disabled="true" style="background-color:#EFEFEF; border:0" size="8" maxlength="8"/>
                </td>
              </tr>
              <tr>

                <td><strong>CPF/CNPJ do Principal:</strong></td>
                <td colspan="2">
                	<html:text property="cpfCnpjPrincipal" disabled="true" style="background-color:#EFEFEF; border:0" size="15" maxlength="15"/>
                </td>
              </tr>
              <tr>
                <td><strong>Motivo da Baixa:</strong></td>
                <td colspan="2">
                	<html:text property="motivoBaixa" disabled="true" style="background-color:#EFEFEF; border:0" size="2" maxlength="2"/>
                </td>
              </tr>
              <tr>

                <td><strong>Tipo do Segundo Documento do Principal:</strong></td>
                <td colspan="2">
                	<html:text property="tipoSegundoDocumentoPrincipal" disabled="true" style="background-color:#EFEFEF; border:0" size="4" maxlength="4"/>
                </td>
              </tr>
              <tr>
                <td><strong>RG do Principal:</strong></td>
                <td colspan="2">
                	<html:text property="rgPrincipal" disabled="true" style="background-color:#EFEFEF; border:0" size="15" maxlength="15"/>
                </td>
              </tr>

              <tr>
                <td><strong>UF do RG do Principal:</strong></td>
                <td colspan="2">
                	<html:text property="ufDoRgPrincipal" disabled="true" style="background-color:#EFEFEF; border:0" size="2" maxlength="2"/>
               	</td>
              </tr>
              <tr>
                <td><strong>Tipo de Pessoa do Coobrigado:</strong></td>
                <td colspan="2">
                	<html:text property="tipoPessoaCoobrigado" disabled="true" style="background-color:#EFEFEF; border:0" size="8" maxlength="8"/>
                </td>

              </tr>
              <tr>
                <td><strong>Tipo do Primeiro Documento do Coobrigado:</strong></td>
                <td colspan="2">
                	<html:text property="tipoPrimeiroDocumentoCoobrigado" disabled="true" style="background-color:#EFEFEF; border:0" size="4" maxlength="4"/>
				</td>
              </tr>
              <tr>
                <td><strong>CPF/CNPJ  do Coobrigado:</strong></td>

                <td colspan="2">
					<html:text property="cpfCnpjCoobrigado" disabled="true" style="background-color:#EFEFEF; border:0" size="15" maxlength="15"/>
				</td>
              </tr>
              <tr>
                <td><strong>Tipo do Segundo Documento do Coobrigado:</strong></td>
                <td colspan="2">
                	<html:text property="tipoSegundoDocumentoCoobrigado" disabled="true" style="background-color:#EFEFEF; border:0" size="4" maxlength="4"/>
                </td>
              </tr>
              <tr>

                <td><strong>RG do Coobrigado:</strong></td>
                <td colspan="2">
                	<html:text property="rgCoobrigado" disabled="true" style="background-color:#EFEFEF; border:0" size="15" maxlength="15"/>
				</td>
              </tr>
              <tr>
                <td><strong>UF do RG do Coobrigado:</strong></td>
                <td colspan="2">
	                <html:text property="ufDoRgCoobrigado" disabled="true" style="background-color:#EFEFEF; border:0" size="2" maxlength="2"/>
                </td>
              </tr>
              <tr>

                <td><strong>Nome do Devedor:</strong></td>
                <td colspan="2">
                	<html:text property="nomeDevedor" disabled="true" style="background-color:#EFEFEF; border:0" size="70" maxlength="70"/>
                </td>
              </tr>
              <tr>
                <td><strong>Data do Nascimento:</strong></td>
                <td colspan="2">
                	<html:text property="dataNascimento" disabled="true" style="background-color:#EFEFEF; border:0" size="10" maxlength="10"/>
(AAAAMMDD) </td>
              </tr>

              <tr>
                <td><strong>Nome do Pai:</strong></td>
                <td colspan="2">
                	<html:text property="nomePai" disabled="true" style="background-color:#EFEFEF; border:0" size="70" maxlength="70"/>
                </td>
              </tr>
              <tr>
                <td><strong>Nome da M&atilde;e: </strong></td>
                <td colspan="2">
                	<html:text property="nomeMae" disabled="true" style="background-color:#EFEFEF; border:0" size="70" maxlength="70"/>
                </td>

              </tr>
              <tr>
                <td><strong>Endere&ccedil;o:</strong></td>
                <td colspan="2">
                	<html:text property="endereco" disabled="true" style="background-color:#EFEFEF; border:0" size="45" maxlength="45"/>
                </td>
              </tr>
              <tr>
                <td><strong>Bairro:</strong></td>

                <td colspan="2">
                	<html:text property="bairro" disabled="true" style="background-color:#EFEFEF; border:0" size="20" maxlength="20"/>
                </td>
              </tr>
              <tr>
                <td><strong>Munic&iacute;pio:</strong></td>
                <td colspan="2">
                	<html:text property="municipio" disabled="true" style="background-color:#EFEFEF; border:0" size="25" maxlength="25"/>
                </td>
              </tr>
              <tr>
                <td><strong>UF:</strong></td>

                <td colspan="2">
                	<html:text property="uf" disabled="true" style="background-color:#EFEFEF; border:0" size="2" maxlength="2"/>
                </td>
              </tr>
              <tr>
                <td><strong>CEP:</strong></td>
                <td colspan="2">
                	<html:text property="cep" disabled="true" style="background-color:#EFEFEF; border:0" size="8" maxlength="8"/>
                </td>
              </tr>
              <tr>
                <td><strong>Valor do D&eacute;bito:</strong></td>

                <td>
                	<html:text property="valorDebito" disabled="true" style="background-color:#EFEFEF; border:0" size="15" maxlength="15"/>
                </td>
                <td><div align="right">
                  	<input name="Button32224" type="button" class="bottonRightCol" value="Consultar Itens do D&eacute;bito" onClick="javascript:abrirPopup('exibirConsultarNegativadorMovimentoRegItemPopupAction.do', 410, 790);"         />
                </div></td>
              </tr>
              <tr>
                <td><strong>Contrato (Matr&iacute;cula do Im&oacute;vel):</strong></td>

                <td colspan="2">
                	<html:text property="contrato" disabled="true" style="background-color:#EFEFEF; border:0" size="16" maxlength="16"/>
                </td>
              </tr>
              <tr>
                <td><strong>Nosso N&uacute;mero:</strong></td>
                <td colspan="2">
                	<html:text property="nossoNumero" disabled="true" style="background-color:#EFEFEF; border:0" size="9" maxlength="9"/>
                </td>
              </tr>
              <tr>
                <td><strong>Complemento do Endere&ccedil;o:</strong></td>

                <td colspan="2">
                	<html:text property="complemento" disabled="true" style="background-color:#EFEFEF; border:0" size="25" maxlength="25"/>
                </td>
              </tr>
              <tr>
                <td><strong>DDD do Telefone do Devedor:</strong></td>
                <td colspan="2">
                	<html:text property="dddTelefoneDevedor" disabled="true" style="background-color:#EFEFEF; border:0" size="4" maxlength="4"/>
                </td>
              </tr>
              <tr>
                <td><strong>Telefone do Devedor:</strong></td>

                <td colspan="2">
                	<html:text property="telefoneDevedor" disabled="true" style="background-color:#EFEFEF; border:0" size="9" maxlength="9"/>
                </td>
              </tr>
              <tr> 
                <td width="37%"><strong>Data de Vencimento do D&eacute;bito Mais Antigo:</strong></td>
                <td colspan="2">
                	<html:text property="dataVencimentoDebitoMaisAntigo" disabled="true" style="background-color:#EFEFEF; border:0" size="8" maxlength="8"/>
                	
(AAAAMMDD) </td>
              </tr>
              <tr>

                <td><strong>Valor Total do Compromisso:</strong></td>
                <td colspan="2">
                	<html:text property="valorToralCompromisso" disabled="true" style="background-color:#EFEFEF; border:0" size="15" maxlength="15"/>
                </td>
              </tr>
              <tr>
                <td><strong>C&oacute;digo de Retorno:</strong></td>
                <td colspan="2">
                	<html:text property="codigoRetorno" disabled="true" style="background-color:#EFEFEF; border:0" size="60" maxlength="60"/>
                </td>
              </tr>

              <tr> 
                <td><strong>Sequencial do Registro:</strong></td>
                <td colspan="2">
                	<html:text property="sequencialRegistro" disabled="true" style="background-color:#EFEFEF; border:0" size="7" maxlength="7"/>
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
                <td colspan="2">
                	<html:text property="indicadorAceitacao" disabled="true" style="background-color:#EFEFEF; border:0" size="15" maxlength="15"/>
                </td>
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
