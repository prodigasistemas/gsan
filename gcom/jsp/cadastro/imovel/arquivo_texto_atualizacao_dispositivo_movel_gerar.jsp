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

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm" />

<script LANGUAGE="JavaScript">
	
	function validarForm(form){
		if(form.idLeiturista.value == null || form.idLeiturista.value == "") {		   		    		 
			alert("Informe o Agente Comercial")
		}else{
			form.action = "/gsan/gerarArquivoTextoAtualizacaoCadastralDispositivoMovelAction.do";
			submeterFormPadrao(form);	
	    }
	}
	
	function limparLeiturista() {
	 var form = document.forms[0]; 
    	form.idLeiturista.value = "";
    	form.nomeLeiturista.value = "";
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0]; 
		if (tipoConsulta == 'leiturista') {
	    	form.idLeiturista.value = codigoRegistro;
	    	form.nomeLeiturista.value = descricaoRegistro;
	    	form.nomeLeiturista.style.color = "#000000";
	    } 
   }
  function voltar(){
	var formulario = document.forms[0]; 
	formulario.action =  'exibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction.do'
	formulario.submit();
  }
</script>


</head>


<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');habilitaDesabilita();">

<div id="formDiv">
<html:form action="/gerarArquivoTextoAtualizacaoCadastralDispositivoMovelAction"
	name="GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm"
	type="gcom.gui.cadastro.imovel.GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" id="existeColecaoImovel" value="${requestScope.existeColecaoImovel}"/>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="140" valign="top" class="leftcoltext">
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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Gerar Aquivo Texto - Atualização Cadastral</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="2">Para gerar o arquivo texto, informe os dados abaixo:</td>
      </tr>

  
	  <tr>
		<td width="25%"><strong>Quantidade de Imóveis:</strong></td>
		<td><html:text tabindex="10" maxlength="8" readonly="true"
			property="tamanhoColecaoImovel" size="8"/> 
		</td>
	  </tr>
	  <tr>
		  <td width="25%"><strong>Descrição do Arquivo:</strong></td>
		  <td>
			<logic:present name="informarDescricao">
				<html:text maxlength="50" property="descricaoArquivo" size="50" 
					tabindex="13"/> 
			</logic:present>
			<logic:notPresent name="informarDescricao">
				<html:text maxlength="50" property="descricaoArquivo" size="50" 
					tabindex="13" readonly="true"/> 
			</logic:notPresent>
		  </td>
	  </tr>
	  <tr>
		<td height="10" width="145"><strong>Agente Comercial:<font color="#FF0000">*</font></strong></td>
		<td colspan="2" align="left"><html:select property="idLeiturista"
			tabindex="4">
			<html:option value="">&nbsp;</html:option>
			<html:options collection="colecaoLeiturista"
				labelProperty="descricao" property="id" />
		</html:select></td>
	  </tr>
      <tr>
         <td colspan="2"></td>
     </tr>
	 
      <tr>
         <td colspan="2"></td>
     </tr>
   

     
	 <tr>
	   <td align="left" width="25%">					
	    <input name="Button322222" type="button"
		class="bottonRightCol" value="Voltar"
		onClick="javascript:voltar()" />
	   </td>
	   <td align="right">
	     <table border="0" width="100%">
		   <tr>
			<td align="right">					
			 <input name="Button322222" type="button"
				class="bottonRightCol" value="Gerar"
				onClick="javascript:validarForm(document.forms[0]);" />
			</td>
		  </tr>
		</table>
	   </td>
	 </tr>
	 
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>



<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>

</body>
</html:html>
