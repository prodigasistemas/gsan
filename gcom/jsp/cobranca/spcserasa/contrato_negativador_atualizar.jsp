<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

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


function verificaVigenciaContrato() {

	var form = document.AtualizarContratoNegativadorActionForm;		
	
	if(form.vigente.value == "false"){
		form.numeroContrato.readOnly = true;
		form.descricaoEmailEnvioArquivo.readOnly = true;
		form.codigoConvenio.readOnly = true;
		form.valorContrato.readOnly = true;
		form.valorTarifaInclusao.readOnly = true;
		form.numeroPrazoInclusao.readOnly = true;
		form.dataContratoInicio.readOnly = true;
		form.dataContratoFim.readOnly = true;
		form.dataContratoEncerramento.readOnly = true;		
		form.idContratoMotivoCancelamento.disabled = true;
		form.botaoAtualizar.disabled = true;	
		form.botaoDesfazer.disabled = true;
	} 
}


 function validateAtualizarContratoNegativadorActionForm() {                                                                   
     var form = document.forms[0];       
         
       	if(validateRequired(form) &&  validateDate(form)&& validateEmail(form)&& verificarEmail()){
    		submeterFormPadrao(form);
		}
       
   } 



 	 function required () {  
     this.aa = new Array("numeroContrato", "Informe número do contrato.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("valorContrato", "Informe o valor do contrato.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("valorTarifaInclusao", "Informe o valor da tarifa para inclusão.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("numeroPrazoInclusao", "Informe o prazo para negativação.", new Function ("varName", " return this[varName];"));   
     this.ag = new Array("dataContratoInicio","Informe a data de início do contrato.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("dataContratoFim","Informe a data de fim do contrato.", new Function ("varName", " return this[varName];"));
     } 

    function email () { 
     this.aa = new Array("descricaoEmailEnvioArquivo", "E-mail é um endereço inválido.", new Function ("varName", " return this[varName];"));
   
    } 
  

    function DateValidations () { 
     this.aa = new Array("dataContratoInicio", "Data de Cadastramento Inicial não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.aa = new Array("dataContratoFim", "Data de Cadastramento Final não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 


   function verificarEmail(){
	var form = document.forms[0];
 	  if(form.descricaoEmailEnvioArquivo.value != "" && !checkMail(form.descricaoEmailEnvioArquivo)){
     	  alert("E-mail é um endereço inválido."); 
       return false;    
	   }
   
   	return true;
	}


  

</script>
</head>
<body leftmargin="0" topmargin="0"
	onload="javascript:verificaVigenciaContrato();setarFoco('${requestScope.nomeCampo}');submit();">
<html:form action="/atualizarContratoNegativadorAction"
	name="AtualizarContratoNegativadorActionForm"
	type="gcom.gui.cobranca.spcserasa.AtualizarContratoNegativadorActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="time"/>
	<html:hidden property="vigente"/>

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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar Contrato do Negativador</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>


			<p>&nbsp;</p>
		
		
		
		    <table>
              <tr>
                <td></td>
              </tr>
            </table>
        
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->

            <table width="100%" border="0">
              <tr> 
                <td colspan="2">Para atualizar o contrato do negativador, informe
                  os dados abaixo:</td>
              </tr>
                 
              <tr>
                <td><strong>Negativador:</strong></td>
                <td><html:text property="negativadorCliente" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0"/> 
                </td>                 
              </tr>
			   <tr>
                <td><strong>Inclusões Enviadas:</strong></td>
				<td><html:text property="numeroInclusoesEnviadas" size="10" maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0"/> 
				</td>
              </tr>
			  <tr>
                <td><strong>Saldo das Inclusões:</strong></td>
				<td><html:text property="numeroInclusoesContratadas" size="10" maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0"/>
				</td>
			</tr>
               <tr>
                <td><strong>Exclusões Realizadas:</strong></td>
				<td><html:text property="numeroExclusoesEnviadas" size="10" maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0"/>
				</td>
                                                   
			  </tr>
                    <td colspan="4"><hr></td>
                  </tr>
			  
              <tr>
                <td><strong>N&uacute;mero do Contrato:<font color="#FF0000">*</font></strong></td>
                <td>
                <html:text property="numeroContrato" size="15" maxlength="10" />     
                </td>
              </tr>
              <tr>
                <td><strong>E-mail para envio do arquivo:</strong></td>
                <td align="right"><div align="left"><strong>
                 <html:text property="descricaoEmailEnvioArquivo" size="45" maxlength="40" />                   
                </strong>
                </div></td>
              </tr>
              <tr>
                <td><strong>C&oacute;digo do Conv&ecirc;nio:</strong></td>
                <td align="right"><div align="left"><strong>
                <html:text property="codigoConvenio" size="25" maxlength="20" />                    
                </strong></div></td>
              </tr>
			  <tr> 
                <td><strong>Valor do Contrato:<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                 <html:text property="valorContrato" size="20" maxlength="15" />				  
                </strong>                    
                  </div></td>
              </tr>
			  <tr> 
                <td><strong>Valor da Tarifa para inclusão:<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                  <html:text property="valorTarifaInclusao" size="20" maxlength="15" />		
                </strong>                    
                  </div></td>
              </tr>
			  <tr> 
                <td><strong>Prazo para Negativação (em dias):<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                  <html:text property="numeroPrazoInclusao" size="20" maxlength="15" />	                
                </strong>                    
                  </div></td>
              </tr>
              <tr>
                <td><strong>Data de In&iacute;cio do Contrato:<font color="#FF0000">*</font></strong></td>
                <td align="right">
                  <div align="left"><strong>
                     <html:text property="dataContratoInicio" size="14" maxlength="10" />	                       
                    <a href="javascript:abrirCalendario('AtualizarContratoNegativadorActionForm', 'dataContratoInicio')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
                  </strong> </div></td>
              </tr>
              <tr> 
                <td><strong>Data de Fim do Contrato:<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                 <html:text property="dataContratoFim" size="14" maxlength="10" />	  
                 <a href="javascript:abrirCalendario('AtualizarContratoNegativadorActionForm', 'dataContratoFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
                </strong>                    
                  </div></td>
              </tr>
              <tr>
                <td><strong>Data de Encerramento do Contrato:</strong></td>
                <td align="right">
                  <div align="left"><strong>
                     <html:text property="dataContratoEncerramento" size="14" maxlength="10" />
                     <a href="javascript:abrirCalendario('AtualizarContratoNegativadorActionForm', 'dataContratoEncerramento')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
                  </strong> </div>                </td>
              </tr>
              <tr>
                <td><strong>Motivo Cancelamento:</strong></td>
                <td align="right"><div align="left">
                    <logic:present name="colecaoContratoMotivoCancelamento">  
                   	   <html:select property="idContratoMotivoCancelamento" tabindex="7" >
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoContratoMotivoCancelamento">
								<html:options collection="colecaoContratoMotivoCancelamento" labelProperty="descricaoMotivoCancelContrato" property="id"/>
							</logic:present>
						</html:select>
                	
                		</logic:present>  
                   
                   
                   
                 </div></td>
              </tr>
              <tr> 
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr>
              
              <tr>


					<td><logic:present name="voltar">
						<logic:equal name="voltar" value="filtrar">
							<input name="Button" type="button" class="bottonRightCol"
								value="Voltar" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarContratoNegativadorAction.do?desfazer=N"/>'">
						</logic:equal>
						<logic:equal name="voltar" value="manter">
							<input name="Button" type="button" class="bottonRightCol"
								value="Voltar" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirManterContratoNegativadorAction.do"/>'">
						</logic:equal>
					</logic:present> <logic:notPresent name="voltar">
						<input name="Button" type="button" class="bottonRightCol"
							value="Voltar" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirManterContratoNegativadorAction.do"/>'">
					</logic:notPresent> 				
					<input name="botaoDesfazer" type="button" class="bottonRightCol"
							value="Desfazer" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirAtualizarContratoNegativadorAction.do?desfazer=S"/>'">
					<input name="Button" type="button"
						class="bottonRightCol" value="Cancelar" align="left"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right">					  
						<input type="button" name="botaoAtualizar" class="bottonRightCol" value="Atualizar" onclick="javascript:validateAtualizarContratoNegativadorActionForm();">					  
						
	               </td>
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
