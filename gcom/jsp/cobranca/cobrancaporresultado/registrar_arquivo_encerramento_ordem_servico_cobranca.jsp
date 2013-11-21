<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">
<!--

	var bCancel = false; 
	
	function validateAtualizarAvisoBancarioAction(form) 
	{
	   if (bCancel) {
	      return true; 
	   }else 
	   {
	      return validateCaracterEspecial(form) && validateLong(form);
	   }
	}
	 
	function caracteresespeciais () {	  
	   this.ab = new Array("idNegativador", "Negativador possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}
		
	function validarForm(){
	   var form = document.forms[0];
	   var mensagem = '';
	   
  	   if(form.idEmpresa.value != '' && form.uploadPicture.value != '') {	
		   if(form.idEmpresa.value == ''){
		      mensagem = mensagem + '\n Informe a Empresa.';
		   }
		  
		   if(form.uploadPicture.value == ''){
		      mensagem = mensagem + '\n Informe Nome do Arquivo.';
		   }
		   
		   if (mensagem != '') {
			  alert(mensagem);
		   } else {
			  form.action = 'registrarArquivoTxtEncerramentoOSCobrancaAction.do';
			  submeterFormPadrao(form);
		   }
	   }
	}
	
	function pesquisarEmpresa() {
		var form = document.forms[0];

		if (form.idEmpresa.disabled == false)  {
			abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 495, 300);
		}
	}
	
	function limparEmpresa() {
		var form = document.forms[0];
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";	
	}
	
	function limparEmpresaTecla() {
		var form = document.forms[0];
		form.nomeEmpresa.value = "";	
	}

	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'empresa') {
			form.idEmpresa.value = codigoRegistro;
			form.nomeEmpresa.value = descricaoRegistro;
			form.nomeEmpresa.style.color = "#000000";
    	}
    	
   	}
-->
</script>

</head>
<body leftmargin="5" topmargin="5">
<form action="/gsan/exibirRegistrarArquivoTxtEncerramentoOSCobrancaAction.do"
	method="post" enctype="multipart/form-data">
	<%@ include	file="/jsp/util/cabecalho.jsp"%> 
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
	            <td class="parabg">Registrar Arquivo TXT Encerramento OS Cobrança</td>
	            <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
	          </tr>
	        </table>
	        <!--Fim Tabela Reference a Páginação da Tela de Processo-->
	<p>&nbsp;</p>
	         <table width="100%" border="0" >
	             <tr> 
	               <td colspan="2">Para registrar Arquivo TXT Encerramento OS Cobrança, informe 
	                 os dados abaixo:</td>
	             </tr>
	             <tr>
	             	<td colspan="2"> &nbsp;&nbsp;
	             	</td>
	             </tr>
				 <tr>
					<td width="30%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td><input type="text" maxlength="9" name="idEmpresa" size="10"
						tabindex="14" value="${requestScope.idEmpresa}"
						onkeyup="validaEnterComMensagem(event, 'exibirRegistrarArquivoTxtEncerramentoOSCobrancaAction.do?objetoConsulta=1', 'idEmpresa', 'Empresa'); limparEmpresaTecla();" />
					<a href="javascript:pesquisarEmpresa();"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:present
						name="empresaInexistente" scope="request">
						<input type="text" name="nomeEmpresa" size="40" maxlength="40"
							readonly="true" value="${requestScope.nomeEmpresa}"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="empresaInexistente"
						scope="request">
						<input type="text" name="nomeEmpresa" size="40" maxlength="40"
							readonly="true" value="${requestScope.nomeEmpresa}"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparEmpresa();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
					</td>
				 </tr>
	             <tr> 
		               <td class="style1"><strong>Arquivo de movimento da empresa de cobrança:<font color="#FF0000">*</font></strong></td>
		               <td class="style1">
		               		<input  type="file" 
		               				style="textbox" 
		               				name="uploadPicture" 
									size="40"  />
					  </td>
	             </tr>
	             <tr> 
	               <td class="style1"><p>&nbsp;</p></td>
	               <td class="style1"> 
	               <table>
	                  <tr> 
	                    <td width="500" align="right"><div align="left">
	                    	<strong><font color="#FF0000">*</font></strong> 
	                        Campos obrigat&oacute;rios </div>
	                    </td>
	                    <td>&nbsp;</td>
                  	 </tr>
	           	   </table>
		          </td>
		       </tr>
              <tr>
             	<td colspan="2"> &nbsp;&nbsp;
             	
             	</td>
              </tr>
	               
		   	 <tr> 
		        <td class="style1">
		        	<input  type="button"
							name="ButtonCancelar" 
							class="bottonRightCol" 
							value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
		       </td>
		       <td class="style1"> 
			       	<table>
			          <tr> 
			          	<td width="0" align="left">                 
			            </td>
			            <td>&nbsp;</td>
			            <td width="500" align="right">                 
				             	 <input name="Registrar" 
				             	 		type="submit" 
				             	 		class="bottonRightCol" 
				             	 		id="Registrar" 
				             	 		value="Registrar" 
				             	 		onclick="javascript:validarForm();">
				       		</td>
			            <td>&nbsp;</td>
			          </tr>
		         	</table>
		       </td>
		     </tr>
             <tr> <td colspan="2"> &nbsp;&nbsp; </td> </tr>
             <tr> <td colspan="2"> &nbsp;&nbsp; </td> </tr>
             <tr> <td colspan="2"> &nbsp;&nbsp; </td> </tr>
             <tr> <td colspan="2"> &nbsp;&nbsp; </td> </tr>
             <tr> <td colspan="2"> &nbsp;&nbsp; </td> </tr>
             <tr> <td colspan="2"> &nbsp;&nbsp; </td> </tr>
             <tr> <td colspan="2"> &nbsp;&nbsp; </td> </tr>
             <tr> <td colspan="2"> &nbsp;&nbsp; </td> </tr>
					           
		</table>
	</td>
</tr>
	
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</form>

<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
