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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="RegistrarMovimentoArredadadoresAction" />	
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

	   if(form.idNegativador.value != '' && form.uploadPicture.value != '') {		 
	  	 	if(confirm('Confirma registrar movimento de Retorno dos Negativadores ?')){		    	  
	    	   submitForm(form);
		   	}
		
	   }else{		   	     
		  if(form.idNegativador.value == ''){
		     mensagem = mensagem + '\n Informe Negativador.';
		  }
		  
		  if(form.uploadPicture.value == ''){
		     mensagem = mensagem + '\n Informe Nome do Arquivo.';
		  }		  
		  alert(mensagem);
	   }
	}

function limparPesquisaArrecadador(form) {
    form.idNegativador.value = "";
    form.uploadPicture.value = "";
}

-->
</script>

</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
	
<div id="formDiv">
	
<form action="/gsan/registrarNegativadorMovimentoRetornoAction.do"
	method="post" enctype="multipart/form-data"><%@ include
	file="/jsp/util/cabecalho.jsp"%> <%@ include file="/jsp/util/menu.jsp"%>

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
            <td class="parabg">Registrar Retorno do Movimento dos Negativadores</td>
            <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
          </tr>
        </table>
        <!--Fim Tabela Reference a Páginação da Tela de Processo-->
<p>&nbsp;</p>
         <table width="100%" border="0" >
             <tr> 
               <td colspan="2">Para registrar o retorno do movimento do arrecadador, informe 
                 os dados abaixo:</td>
             </tr>
             <tr> 
               <td width="24%">
               		<strong>Negativador:<strong><font color="#FF0000">*</font></strong></strong>
               </td>
               <td>
               		<select name="idNegativador">
						<option value="">&nbsp;</option>
						<c:forEach  items="${sessionScope.colecaoNegativador}"
									var="negativador">
							<option value="${negativador.id}">${negativador.cliente.nome}</option>
						</c:forEach>
				   </select>
			  </td>
             </tr>
             <tr> 
               <td class="style1"><strong>Nome do Arquivo:<strong><font color="#FF0000">*</font></strong></td>
               <td class="style1">
               		<input  type="file" 
               				style="textbox" 
               				name="uploadPicture"
							size="50" />
			  </td>
             </tr>
             <tr> 
               <td class="style1"><p>&nbsp;</p></td>
               <td class="style1"> <table>
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
           
</table>
</td>
</tr>

</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</form>

</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
