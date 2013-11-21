<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>	
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InserirContratoNegativadorActionForm" dynamicJavascript="false" />

<script language="JavaScript">
 
function desfazer(){

var form = document.forms[0];

        form.idNegativador.value = "-1"; 
	    form.numeroContrato.value = "";
		form.descricaoEmailEnvioArquivo.value = "";
		
		form.codigoConvenio.value = "";
		form.valorContrato.value = "";
		form.valorTarifaInclusao.value = "";
		form.numeroPrazoInclusao.value = "";
		form.dataContratoInicio.value = "";
		form.dataContratoFim.value = "";
		

}


  function validateInserirContratoNegativadorActionForm() {                                                                   
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
     this.ai = new Array("idNegativador","Informe o Negativador.", new Function ("varName", " return this[varName];"));
    } 

    function email () {     
     this.aa = new Array("descricaoEmailEnvioArquivo", "E-mail é um endereço inválido.", new Function ("varName", " return this[varName];"));
   
    } 
  

    function DateValidations () { 
     this.aa = new Array("dataContratoInicio", "Data de Cadastramento Inicial não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ab = new Array("dataContratoFim", "Data de Cadastramento Final não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
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

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirContratoNegativadorAction"
	name="InserirContratoNegativadorActionForm"
	type="gcom.gui.cobranca.spcserasa.InserirContratoNegativadorActionForm"
	method="post"  >
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<table width="770" border="0" cellspacing="5" cellpadding="0">
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">
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
                <td class="parabg">Inserir Contrato do Negativador</td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>  
            
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->      
            
	         <p>&nbsp;</p>
	
	       <table width="100%" border="0">
              <tr> 
                <td colspan="2">Para adicionar o contrato do negativador, informe
                  os dados abaixo:</td>
              </tr>
              
              
           
              <tr>
                <td><strong>Negativador:<font color="#FF0000">*</font></strong></td>
                <td>   
                	<logic:present name="colecaoNegativador">  
                   	   <html:select property="idNegativador" tabindex="7">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoNegativador">
								<html:options collection="colecaoNegativador" labelProperty="cliente.nome" property="id"/>
							</logic:present>
						</html:select>
                		</logic:present>              		
                   
                  </td>
              </tr>
            
			  <tr>
                <td><strong>N&uacute;mero do Contrato:<font color="#FF0000">*</font></strong></td>
                <td><html:text property="numeroContrato" name="InserirContratoNegativadorActionForm"  size="15" maxlength="10"/>
                </td>
              </tr>
              
               <tr>
                <td><strong>E-mail para envio do arquivo:</strong></td>
                <td><html:text property="descricaoEmailEnvioArquivo" size="45" maxlength="40"   />
               
                </td>
              </tr>
              
              
               <tr>
                <td><strong>C&oacute;digo do Conv&ecirc;nio:</strong></td>
                <td align="right"><div align="left"><strong>
                    <html:text property="codigoConvenio" size="25" maxlength="20"/>                    
                  </strong></div>
                </td>
              </tr>
			  <tr> 
                <td><strong>Valor do Contrato:<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                  <html:text property="valorContrato" size="15" maxlength="15"  onkeyup="formataValorMonetario(this, 14)"/>   
				   </strong>                    
                  </div></td>
              </tr>
	      <tr> 
                <td><strong>Valor da Tarifa para inclusão:<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                <html:text property="valorTarifaInclusao" size="15" maxlength="15"  onkeyup="formataValorMonetario(this, 14)" />  
                  
                </strong>                    
                  </div></td>
              </tr>
			  <tr> 
                <td><strong>Prazo para Negativação (em dias):<font color="#FF0000">*</font></strong></td>
                <td align="right"> <div align="left"><strong>
                     <html:text property="numeroPrazoInclusao" size="6" maxlength="3"/>                  
                </strong>                    
                  </div></td>
              </tr>
              <tr>
                <td><strong>Data de Início do Contrato:<font color="#FF0000">*</font></strong></td>
                <td align="right">
                  <div align="left"><strong>      
                   <html:text property="dataContratoInicio" size="10" maxlength="10"
						tabindex="8" onkeyup="mascaraData(this, event);" /> 
						
					<a href="javascript:abrirCalendario('InserirContratoNegativadorActionForm', 'dataContratoInicio')">	
						
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa
					
				 </strong>                    
                  </div>	
                  
                  
                  
                </td>
              </tr>
              <tr> 
                <td><strong>Data de Fim do Contrato:<font color="#FF0000">*</font></strong></td>              
   
                  
                 <td align="right"> <div align="left"><strong>
                 <html:text property="dataContratoFim" size="10" maxlength="10"
						tabindex="8" onkeyup="mascaraData(this, event);" /> 
				  <a href="javascript:abrirCalendario('InserirContratoNegativadorActionForm', 'dataContratoFim')">			
						
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa
					
				 </strong>                    
                  </div>	
					</td>  
                  
                  
                  
                  
              </tr>
			  <tr> 
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr>
             
	
	         </table>
	
	
			<table width="100%" border="0">			
				
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="javascript:desfazer();"> <input
						name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right" height="24">
					<input type="button" name="botaoInserir" class="bottonRightCol" value="Inserir" onclick="javascript:validateInserirContratoNegativadorActionForm();"  /></td>
			
				</tr>
				
					<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			
			
			
			
			
			
			
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
