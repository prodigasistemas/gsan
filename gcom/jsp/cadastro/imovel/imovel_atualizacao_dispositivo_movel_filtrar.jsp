<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.cadastro.imovel.ImovelAtualizacaoCadastral"%>

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
 	
  	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
	
	function chamarPopup1(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}
	
	function limpar(tipo){
      var form =  document.forms[0];
	
			
		switch(tipo){
			case 1: //De localidade pra baixo
			
	            form.localidade.value = "";
				form.nomeLocalidade.value = "";				
				form.setorComercialCD.value = "";
			    form.setorComercialID.value = "";
			    form.nomeSetorComercial.value = "";
				
			case 2: //De setor para baixo
	
			   form.nomeSetorComercial.value = "";
			   form.setorComercialDestinoCD.value = "";
			   form.setorComercialDestinoID.value = "";		   
			 				   
		}
		//submeter();
		habilitaDesabilita();
		habilitaDesabilitaLocalidadeImovel();
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0]; 
	
		if (tipoConsulta == 'localidade') {
	      form.localidade.value = codigoRegistro;
	      form.nomeLocalidade.value = descricaoRegistro;
	      form.nomeLocalidade.style.color = "#000000"; 
		 		  
		}else if (tipoConsulta == 'imovel'){
		  form.idImovel.value = codigoRegistro;
	      form.inscricaoImovel.value = descricaoRegistro;
	      form.inscricaoImovel.style.color = "#000000"; 
		}

	}
	
    function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm;

			form.setorComercialID.value = idRegistro;
		  	form.setorComercialCD.value = codigoRegistro;
		  	form.nomeSetorComercial.value = descricaoRegistro;
		  	form.nomeSetorComercial.style.color = "#000000"; 


	}
	
   function limparBorracha(tipo){
	var form = document.forms[0];
			
		switch(tipo){
			case 1: //De localidara pra baixo
			    if(!form.localidade.disabled){
				form.localidade.value = "";
				form.nomeLocalidade.value = "";
			    form.setorComercialCD.value = "";
			    form.setorComercialID.value = "";
			    form.nomeSetorComercial.value = "";
			    form.quadraInicial.value = null;
			    form.idQuadraInicial.value = "";
			    form.quadraFinal.value = null;
			    form.idQuadraFinal.value = "";
			    form.codigoRota.value = "";
			    break;
			    }	
			case 2: //De setor para baixo
			  if(!form.setorComercialCD.disabled){
			     form.setorComercialCD.value = "";
			     form.setorComercialID.value = "";
			     form.nomeSetorComercial.value = "";
			     form.quadraInicial.value = "";
			     form.idQuadraInicial.value = "";
			     form.quadraFinal.value = "";
			     form.idQuadraFinal.value = "";
			     break;
			  }else{
			    break;
		      }	
			case 3:
			  form.idImovel.value = "";
			  form.inscricaoImovel.value = "";
			  break;
	    }
	    //submeter();
	    habilitaDesabilita();
	    habilitaDesabilitaLocalidadeImovel();
   }
      
   function pesquisarImovel(){
   		var form = document.forms[0];
		
		if ((form.localidade.value == null || form.localidade.value == "") &&
		   (form.codigoRota.value == null || form.codigoRota.value == "")) {
			abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
		}
   }
   
   function pesquisarLocalidade(){
      var form = document.forms[0];
	  var existeColecaoImovel = document.getElementById("existeColecaoImovel").value;	

	  if ((form.idImovel.value == null || form.idImovel.value == "")&&
	     (existeColecaoImovel == "")){
		    chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '');
	  }

   }
   
   function pesquisarSetorComercial(){
      var form = document.forms[0];
      var existeColecaoImovel = document.getElementById("existeColecaoImovel").value;
		
	  if ((form.idImovel.value == null || form.idImovel.value == "") &&
	     (existeColecaoImovel == "")){
		  chamarPopup1('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem','idLocalidade', form.localidade.value, 275, 480, 'Informe Localidade',form.setorComercialCD);
	  }

   }
   
   function habilitaDesabilita(){
	  var form = document.forms[0];
	  var existeColecaoImovel = document.getElementById("existeColecaoImovel").value;
	  
	  if(existeColecaoImovel != ""){
	        form.localidade.disabled = true;
			form.setorComercialCD.disabled = true;
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
			form.codigoRota.disabled = true;

	  }else{
		   if (form.codigoRota.value.length > 0){
			    form.quadraInicial.disabled = true;
			    form.quadraFinal.disabled = true;
			    form.idImovel.disabled = true;
		   }else if (form.idImovel.value.length > 0){
	   			form.localidade.disabled = true;
			    form.setorComercialCD.disabled = true;
			    form.quadraInicial.disabled = true;
			    form.quadraFinal.disabled = true;
			    form.codigoRota.disabled = true;
		   }else if(form.quadraInicial.value.length > 0 || form.quadraFinal.value.length > 0){
			 	form.codigoRota.disabled = true;		
			 	form.idImovel.disabled = true; 	  
		   }else{		   	  	
	   			form.localidade.disabled = false;
			    form.setorComercialCD.disabled = false;
			    form.quadraInicial.disabled = false;
			    form.quadraFinal.disabled = false;
			    form.codigoRota.disabled = false;
			    form.idImovel.disabled = false;
		   }
	  }    	
	}
	
   function habilitaDesabilitaLocalidadeImovel(){
	  var form = document.forms[0];
	  var existeColecaoImovel = document.getElementById("existeColecaoImovel").value;
	  
	  if(existeColecaoImovel != ""){
	        form.localidade.disabled = true;
			form.setorComercialCD.disabled = true;
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
			form.codigoRota.disabled = true;

	  }else{
	  	   if (form.localidade.value.length > 0){	  	        
			    form.idImovel.disabled = true;
		   }else if(form.idImovel.value.length > 0){
			    form.localidade.disabled = true;
				form.setorComercialCD.disabled = true;
				form.quadraInicial.disabled = true;
				form.quadraFinal.disabled = true;
				form.codigoRota.disabled = true;
		   }else{
			    form.localidade.disabled = false;
				form.setorComercialCD.disabled = false;
				form.quadraInicial.disabled = false;
				form.quadraFinal.disabled = false;
				form.codigoRota.disabled = false;
				form.idImovel.disabled = false;		     
		   }
	  }    
	}
	
	function limparQuadra(){
	  var form = document.forms[0];
	  if(form.quadraInicial.value.length <= 0){
	    form.quadraFinal.value = ""
	    //submeter();
	  }
	}
	
	function adicionarImovel(){
	  var form = document.forms[0];

	  if(form.idImovel.value == null || form.idImovel.value == ""){
	    alert("Informe o imóvel");
	  }else{
	    form.action = 'exibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction.do?adicionarImovel=sim';
	    form.submit();	
	  }
	}	
	
    function removerImovel(idImovel){
		var formulario = document.forms[0]; 
		  formulario.action =  'exibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction.do?idImovelRemover='+ idImovel;
		  formulario.submit();
    }
	
   function limparLocalidade(){
	  var form = document.forms[0];
	  if(form.localidade.value <= 0){
		  form.localidade.value = "";
		  form.nomeLocalidade.value = "";
	      form.setorComercialCD.value = "";
	      form.setorComercialID.value = "";
	      form.nomeSetorComercial.value = "";
	      form.quadraInicial.value = "";
	      form.idQuadraInicial.value = "";
	      form.quadraFinal.value = "";
	      form.idQuadraFinal.value = "";
	      form.codigoRota.value = "";
	  }
	}
	
	function validarInscricao(form){	      
	     return true;	 		
	}
	
	//function submeter(){
	//	var formulario = document.forms[0]; 
	//	  formulario.action =  'exibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction.do';
	//	  formulario.submit();
   // }
	
	function validarForm(form){
		var existeColecaoImovel = document.getElementById("existeColecaoImovel").value;
		 
		 if(validateGerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm(form) && validarInscricao(form)){
			  form.action = "/gsan/filtrarImovelAtualizacaoCadastralDispositivoMovelAction.do";
			  submeterFormPadrao(form);	
		 }
	}
</script>


</head>


<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');habilitaDesabilita();habilitaDesabilitaLocalidadeImovel();">

<div id="formDiv">
<html:form action="/filtrarImovelAtualizacaoCadastralDispositivoMovelAction"
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
          <td class="parabg">Filtrar Imóvel - Atualização Cadastral</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para filtrar os imóveis a serem atualizados, informe os dados abaixo:</td>
      </tr>
	  <tr>
		<td width="100"><strong>Localidade:</strong></td>
		  <td>
			<html:text tabindex="7" maxlength="3" property="localidade" size="5"
				onkeypress="validaEnter(event, 'exibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction.do?objetoConsulta=1', 'localidade');"
				onkeyup="javascript:habilitaDesabilitaLocalidadeImovel();limparLocalidade();"/>
			<a href="javascript:pesquisarLocalidade(); limpar(1);">
				<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar" /></a>
			<logic:present name="localidadeNaoEncontrada">
				<logic:equal name="localidadeNaoEncontrada" value="exception">
					<html:text property="nomeLocalidade" size="35" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:equal>

				<logic:notEqual name="localidadeNaoEncontrada" value="exception">
					<html:text property="nomeLocalidade" size="35" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEqual>
			</logic:present>
			
			<logic:notPresent name="localidadeNaoEncontrada">
				<logic:empty name="GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm"
					property="localidade">
					<html:text property="nomeLocalidade" value="" size="35" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:empty>

				<logic:notEmpty name="GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm"
					property="localidade">
					<html:text property="nomeLocalidade" size="35" readonly="true"
						style="background-color:#EFEFEF; border:0; color: 	#000000" />
				</logic:notEmpty>
			</logic:notPresent>
			
			<a href="javascript:limparBorracha(1);">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" />
			</a>
		  </td>
	  </tr>
	  
	   <tr>
		<td width="100"><strong>Setor Comercial:</strong></td>
		<td>
			<html:text tabindex="7" maxlength="3"
			property="setorComercialCD" size="5"
			onkeypress="validaEnterDependencia(event, 'exibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction.do?objetoConsulta=2', document.forms[0].setorComercialCD, document.forms[0].localidade.value, 'Localidade');"
			/>
			<a href="javascript:pesquisarSetorComercial();">
				<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /></a>
			
			<logic:present name="setorComercialNaoEncontrada">
				<logic:equal name="setorComercialNaoEncontrada" value="exception">
					<html:text property="nomeSetorComercial" size="35" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:equal>

				<logic:notEqual name="setorComercialNaoEncontrada" value="exception">
					<html:text property="nomeSetorComercial" size="35" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEqual>

			</logic:present>
			
			<logic:notPresent name="setorComercialNaoEncontrada">
				<logic:empty name="GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm" property="setorComercialCD">
					<html:text property="nomeSetorComercial" value="" size="35" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:empty>
				<logic:notEmpty name="GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm" property="setorComercialCD">
					<html:text property="nomeSetorComercial" size="35" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEmpty>
			</logic:notPresent>
			<a href="javascript:limparBorracha(2);">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
			</a>
			<html:hidden property="setorComercialID"/>
		</td>
	  </tr>	  
	  <tr>
		<td width="100"><strong>Quadra Inicial:</strong></td>
		<td width="432"><html:text tabindex="10" maxlength="4"
			property="quadraInicial" size="5"
			onkeypress="validaEnterDependencia(event, 'exibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction.do?objetoConsulta=3', document.forms[0].quadraInicial, document.forms[0].setorComercialCD.value, 'Setor Comercial.');"
			onkeyup="javascript:limparQuadra();habilitaDesabilita();"/> 
			<logic:present name="codigoQuadraInicialNaoEncontrada" scope="request">
				<span style="color:#ff0000" id="msgQuadra"><bean:write
					scope="request" name="msgQuadra" /></span>
			</logic:present> 
			<html:hidden property="idQuadraInicial" />
		</td>
	  </tr>
	  <tr>
		<td width="100"><strong>Quadra Final:</strong></td>
		<td><html:text maxlength="4" property="quadraFinal" size="5"
			onkeypress="validaEnterDependencia(event, 'exibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction.do?objetoConsulta=4', document.forms[0].quadraFinal, document.forms[0].quadraInicial.value, 'Quadra Inicial.');"
			tabindex="13" onkeyup="habilitaDesabilita();"/> 
			<logic:present name="codigoQuadraFinalNaoEncontrada" scope="request">
			<span style="color:#ff0000" id="msgQuadra"><bean:write
				scope="request" name="msgQuadra" /></span>
			</logic:present> 
			<html:hidden property="idQuadraFinal" />
		</td>
	  </tr>

	  <tr>
		<td width="100"><strong>Rota:<font color="#FF0000"></font></strong></td>
		<td align="left"><html:hidden property="idRota" />
		<html:text maxlength="4" size="5" property="codigoRota"  		
			onkeypress="validaEnterDependencia(event, 'exibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction.do?objetoConsulta=5',document.forms[0].codigoRota, document.forms[0].setorComercialCD.value, 'Setor Comercial.');"
			onkeyup="javascript:habilitaDesabilita();"/>  
			<logic:present name="codigoRotaNaoEncontrada" scope="request">
			<span style="color:#ff0000" id="msgRota"><bean:write
				scope="request" name="msgRota" /></span>
			</logic:present> </td>
	  </tr>
      <tr>
         <td colspan="2"><hr></td>
     </tr>
     <tr>
         <td colspan="2">

			<table width="100%" border="0">
	      	<tr>
	      		<td width="107"><strong>Imóveis</strong></td>	      		
	      		<td align="left"><html:text
					property="idImovel" maxlength="9" size="9"
					onkeypress="validaEnterComMensagem(event, 'exibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction.do?objetoConsulta=6','idImovel','Im&oacute;vel');" 
					onkeyup="javascript:habilitaDesabilitaLocalidadeImovel();limparLocalidade();"/>
				<a
					href="javascript:pesquisarImovel();">
				<img width="23" height="21"
					src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					border="0" /></a> 
				<logic:present name="existeImovel" scope="request">
					<html:text property="inscricaoImovel" size="35"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000" 
						title="Localidade.Setor.Quadra.Lote.Sublote"/>
				</logic:present>
				<logic:notPresent name="existeImovel" scope="request">
						<html:text property="inscricaoImovel" size="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" 
							title="Localidade.Setor.Quadra.Lote.Sublote"/>
				</logic:notPresent> 
				<a href="javascript:limparBorracha(3);"> <img
					src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" /></a></td>					
	      		<td align="right">
	      		    <input type="button" class="bottonRightCol" value="Adicionar" tabindex="3" id="botaoEndereco" onclick="javascript:adicionarImovel();">	      							      		
	      		</td>
	      	</tr>
		 	</table>
		 </td>
     </tr> 	     	
	<tr>
		<td colspan="2">

		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>

				<table width="100%" bgcolor="#99CCFF">
					<tr bgcolor="#99CCFF">

						<td align="center" width="20%"><strong>Remover</strong></td>
						<td>
						<div align="left" width="40%"><strong>Matrícula</strong></div>
						</td>
						<td>
						<div align="left" width="40%"><strong>Inscrição</strong></div>
						</td>

					</tr>
				</table>

				</td>
			</tr>

			<%int cont = 0;%>

			<logic:present name="colecaoImovel">
			<tr>
				<td>

				<div style="width: 100%; height: 100; overflow: auto;">

				<table width="100%" align="center" bgcolor="#99CCFF">

					<logic:iterate name="colecaoImovel" id="imovel"
						type="ImovelAtualizacaoCadastral">

						<%cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
						<%} else {%>
						<tr bgcolor="#FFFFFF">
						<%}%>
							<td align="center" width="20%" valign="middle">
								<a href="javascript:removerImovel(<%="" + imovel.getIdImovel().intValue()%>)">
									<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
								</a>
							</td>
							<td width="40%">							   
							    <bean:write name="imovel" property="idImovel" />
							</td>
							<td width="40%">
								<bean:write name="imovel" property="inscricaoFormatada" />							
							</td>
						</tr>
					</logic:iterate>
					</table>
					</div>
					</td>
					</tr>
				   </logic:present>
				</table>
			</td>
	 </tr>
     
	 <tr>
	   <td colspan="2" align="right">
	     <table border="0" width="100%">
		   <tr>
			<td align="right" width="50%">					
			 <input name="Button322222" type="button"
				class="bottonRightCol" value="Filtrar"
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
