<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.cadastro.cliente.ClienteImovel"%>
<%@ page isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<%@ page import="gcom.gui.GcomAction"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterImovelActionForm"
	dynamicJavascript="false" staticJavascript="false" page="3" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>	
<script language="JavaScript">
<!-- Begin

     var bCancel = false;

    function validateManterImovelActionForm(form) {
        if (bCancel)
      return true;
        else
       return testarCampoValorZero(document.ManterImovelActionForm.idCliente, 'Código') && validateCaracterEspecial(form) && validateLong(form)  && validateRequired(form) ;
   }

    function caracteresespeciais () {

     this.ad = new Array("idCliente", "Código deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

    }

    function IntegerValidations () {
     this.ac = new Array("idCliente", "Código deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function required () {
     this.ab = new Array("idClienteImovelUsuario", "Informe um cliente do tipo usúario.", new Function ("varName", " return this[varName];"));
    }


    function DateValidations () {
      this.aa = new Array("dataInicioClienteImovelRelacao", "Data Início Relação inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }

    function verificarAdicionar() {

      var form = document.forms[0];
      if (form.idCliente.value == ""){
		 alert("Informe Código.");
      }else if(form.tipoClienteImovel.value == "-1"){
		alert("Informe Tipo do Cliente.");
	  }else if (form.idCliente.value != '') {
        if(form.idCliente.value == '0'){
      	  alert("Código deve somente conter números positivos.");
		}else if(form.idCliente.value < '0'){
		  alert("Código deve somente conter números positivos.");
		}else{
		  if(!validateCaracterEspecial(form)){
    	        alert("Código deve somente conter números positivos.");
        	 }else if(!validateLong(form)){

		  }else{
			if(form.dataInicioClienteImovelRelacao.value  == ""){
				alert("Informe Data Início Relação.");
			}else{
			  	if(validateDate(form)){
		    	    form.action='atualizarImovelWizardAction.do?action=adicionarAtualizarImovelClienteAction';
			    	//form.submit();
			    	submitForm(form);
		    	}
			}		    
		    
		  }
	    }
      } 
  }

//Recebe os dados do(s) popup(s)

  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

     if (tipoConsulta == 'cliente') {
      form.idCliente.value = codigoRegistro;
      form.nomeCliente.value = descricaoRegistro;
      form.nomeCliente.style.color = "#000000";
    }
  }

function limparPesquisaImovel() {
    var form = document.forms[0];

    form.idCliente.value = "";
    form.nomeCliente.value = "";


  }
  
  
function verificarAberturaPopup(abertura){

	alert("vai abrir o popup??" + (abertura == 1));
	if(abertura == 1){
		abrirPopup('exibirManterImovelFimRelacaoClienteImovelAction.do', 475, 765);	
	}

}  
  
  
function validarNomeConta(valor){

	   var formulario = document.forms[0]; 
 	   formulario.nomeContaSelecionado.value = valor;
       formulario.action='atualizarImovelWizardAction.do?action=exibirAtualizarImovelClienteAction&indicadorNomeConta=SIM';
  	   formulario.submit(); 				

}
  
  
function verficarSelecao(objeto, tipoObjeto){

       var indice;
       var array = new Array(objeto.length);
///       var selecionado = "";
	   var formulario = document.forms[0]; 
		var cont = 0;
	   for(indice = 0; indice < formulario.elements.length; indice++){
//	   alert("formulario.elements[indice].type="+formulario.elements[indice].type+" - "+
  //     "formulario.elements[indice].checked="+formulario.elements[indice].checked);	   
		 if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) {
			//selecionado = formulario.elements[indice].value;
			cont = cont +1;
			///break;
		 }	
	   }
	   
	   //var qtdRadio = 0;
///	   for(indice = 0; indice < formulario.elements.length; indice++){
	//	 if (formulario.elements[indice].type == tipoObjeto) {
		//	qtdRadio = qtdRadio +1;
//		 }
	//   }

//		alert("selecionado="+cont);

       if (cont < 1) {

	       var array = new Array(objeto.length);
		   var formulario = document.forms[0]; 
	
		   for(indice = 0; indice < formulario.elements.length; indice++){
		   
		     var valor = formulario.elements[indice].value;
			 if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == false
			 && formulario.elements[indice].value.substring(0,7) == "USUARIO") {
				///selecionado = formulario.elements[indice].value;
				 formulario.elements[indice].checked = true;
 				 indice = formulario.elements.length;
 				 
 				 formulario.nomeContaSelecionado.value = valor;
 				 
    	         formulario.action='atualizarImovelWizardAction.do?action=exibirAtualizarImovelClienteAction&indicadorNomeConta=SIM';
	    	     formulario.submit(); 				
			 }
		   }
       
       }else {
       
       
       }
   }
  
  
	function removerCliente(url){
		if(confirm('Confirma remoção ?')){
	       var form = document.forms[0];
	    	form.action = url;
		    //form.submit()
		    submitForm(form);	
		}
	}

	function abrirPopManterCliente() {
		var form = document.forms[0];
		if (form.tipoClienteImovel.selectedIndex > 0) {
			var idClienteRelacaoTipo = form.tipoClienteImovel.value;
			abrirPopupModalDeNome('exibirInserirClienteAction.do?POPUP=true&PopUpInserirClienteRetorno=exibirAtualizarImovelClienteAction&desfazer=true&idClienteRelacaoTipo=' + idClienteRelacaoTipo, 500, 800, 'POPUP1', 'yes');
		}else {
			alert("Selecione o Tipo do Cliente para acionar o botão Inserir Cliente");
		}
	}
	
	function recuperarDadosClientePopUp(codigoCliente, nomeCliente) {
		var form = document.forms[0];
		form.idCliente.value = codigoCliente;
      	form.nomeCliente.value = nomeCliente;
		form.nomeCliente.style.color = "#000000";
	}
	
	function recuperarDadosAtualizarClientePopUp(codigoCliente, nomeCliente, cpfCnpj) {
		var form = document.forms[0];
		form.action = "atualizarImovelWizardAction.do?action=exibirAtualizarImovelClienteAction";
	   	submitForm(form);
		
		/*
		var form = document.forms[0];
		var html =
			'<a href="javascript:abrirPopupModalDeNome(\'exibirAtualizarClienteAction.do?POPUP=true&idRegistroAtualizacao=' + codigoCliente
			+ '&PopUpAtualizarClienteRetorno=exibirAtualizarImovelClienteAction\', 500, 800, \'POPUP3\', \'yes\');">'
			+ nomeCliente + '</a>';
		
		var objNome = document.getElementById('cliente_' + codigoCliente);
		var objCpfCnpj = document.getElementById('cliente_cpfcnpj_' + codigoCliente);

		objNome.innerHTML = html;
		objCpfCnpj.innerHTML = cpfCnpj;
		*/
	}
  
-->
</script>

</head>



<body leftmargin="5" topmargin="5"
	onload="javascript:verificarAberturaPopup('${requestScope.aberturaPopup}');">
<div id="formDiv">
<html:form action="/atualizarImovelWizardAction" method="post"
	onsubmit="return validateManterImovelActionForm(this);">
	
	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=3" />



	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="4" />
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="635" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar o(s) cliente(s), informe os dados
					abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelAtualizarAbaCliente', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td><strong>C&oacute;digo:<font color="#FF0000">*</font></strong></td>
					<td>
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><html:text maxlength="9" property="idCliente" size="9"
								onkeypress="javascript:validaEnter(event, 'atualizarImovelWizardAction.do?action=exibirAtualizarImovelClienteAction', 'idCliente');
								return isCampoNumerico(event);" /></td>
							<td>
							<td><logic:present name="idClienteImovelUsuario" scope="session">
								<logic:notEmpty name="idClienteImovelUsuario" scope="session">
									<input type="hidden" name="idClienteImovelUsuario"
										value="<bean:write name="idClienteImovelUsuario"/>" />
								</logic:notEmpty>
								<logic:empty name="idClienteImovelUsuario" scope="session">
									<html:hidden property="idClienteImovelUsuario" value="" />
								</logic:empty>
							</logic:present> <logic:notPresent name="idClienteImovelUsuario"
								scope="session">
								<html:hidden property="idClienteImovelUsuario" value="" />
							</logic:notPresent></td>
							<td width="442"><a
								href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limparForm=OK', 400, 800);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Cliente" /></a> <logic:present
								name="codigoClienteNaoEncontrado" scope="request">
								<input type="text" name="nomeCliente" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									value="<bean:message key="atencao.cliente.inexistente"/>" />
							</logic:present> <logic:notPresent
								name="codigoClienteNaoEncontrado" scope="request">
								<html:text property="nomeCliente" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent> <a href="javascript:limparPesquisaImovel();document.forms[0].idCliente.focus();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /></a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo do Cliente:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="tipoClienteImovel"
						onchange="javascript:document.ManterImovelActionForm.textoSelecionado.value = this[this.selectedIndex].text.substring(5);">
				         <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						 <html:options collection="tipoClientesImoveis" labelProperty="descricaoComId" property="id" />&nbsp;&nbsp;
						 <input type="button" class="bottonRightCol" style="bottonRightCol" value="Inserir Cliente"
						 onclick="javascript:abrirPopManterCliente();" />
					</html:select> <html:hidden property="textoSelecionado" /></td>
				</tr>
				<tr>
					<td><strong>Data Início Relação:<font color="#ff0000">*</font></strong></td>
					<td><html:text property="dataInicioClienteImovelRelacao"
						tabindex="10" size="10" maxlength="10"
						onkeypress="mascaraData(this, event); return isCampoNumerico(event); " /> <a
						href="javascript:abrirCalendario('ManterImovelActionForm', 'dataInicioClienteImovelRelacao')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					<font size="1">(dd/mm/aaaa) </font></td>
				</tr>
				<tr>
					<td height="24"><strong>Nome do Imóvel:</strong></td>
					<td><html:text maxlength="30" size="60"
						property="nomeDoImovel" /></td>
				</tr>	
				<tr>
					<td>&nbsp;</td>
					<td><font color="#FF0000">*</font> Campo obrigatório.</td>
				</tr>
				<tr>
					<td colspan="2">
					<p>&nbsp;<html:hidden property="nomeContaSelecionado"/></p>
					</td>
				</tr>
				<tr>
					<td><strong>Cliente(s) Informado(s)</strong></td>
					<td width="450" align="right"><input type="button"
						class="bottonRightCol" style="bottonRightCol" value="Adicionar"
						onclick="javascript:verificarAdicionar();" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td height="0">
							<table width="100%" bgcolor="#99CCFF">
								<!--header da tabela interna -->
								<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
									<td width="10%"><strong>Remover</strong></td>
									<td width="10%"><strong>Nome Conta</strong></td>
									<td width="10%"><strong>Código</strong></td>
									<td width="45%"><strong>Nome</strong></td>
									<td width="15%"><strong>Tipo</strong></td>
									<td width="20%"><strong>CPF/CNPJ</strong></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="83">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" align="left" bgcolor="#99CCFF">
								<!--corpo da segunda tabela-->
								<%int cont = 0;%>
								<logic:notEmpty name="imovelClientesNovos">
									<logic:iterate name="imovelClientesNovos"
										id="imovelClienteNovo" type="ClienteImovel">
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="10%">
											<div align="center"><input type="checkbox"
												name="idRemocaoClienteImovel"
												value="<%=""+GcomAction.obterTimestampIdObjeto(imovelClienteNovo)%>" />
											</div>
											</td>
											<td width="10%">
    											<div align="center">
												<% if (((ClienteImovel)imovelClienteNovo).getIndicadorNomeConta().shortValue() == 1){ %>
												<input type="radio" name="idNomeConta" 
	 											    onclick="javascript:validarNomeConta('${imovelClienteNovo.clienteRelacaoTipo.descricao}-${imovelClienteNovo.cliente.id}');" 
	 											    value="${imovelClienteNovo.clienteRelacaoTipo.descricao}-${imovelClienteNovo.cliente.id}" checked/>		
	 											 <%}else{ %>   
  												<input type="radio" name="idNomeConta" 
	 											    onclick="javascript:validarNomeConta('${imovelClienteNovo.clienteRelacaoTipo.descricao}-${imovelClienteNovo.cliente.id}');" 
	 											    value="${imovelClienteNovo.clienteRelacaoTipo.descricao}-${imovelClienteNovo.cliente.id}" />		
	 											 <%} %>   
    											</div>									
											</td>
											<td width="10%">
											<div align="center"><bean:write name="imovelClienteNovo"
												property="cliente.id" /></div>
											</td>
											<td align="left" width="35%">
											<div align="left"id='cliente_<bean:write name="imovelClienteNovo" property="cliente.id" />'>
												<a href="javascript:abrirPopupModalDeNome('exibirAtualizarClienteAction.do?POPUP=true&idRegistroAtualizacao=<bean:write name="imovelClienteNovo" property="cliente.id" />&PopUpAtualizarClienteRetorno=exibirAtualizarImovelClienteAction', 500, 800, 'POPUP2', 'yes');">
													<bean:write name="imovelClienteNovo" property="cliente.nome" />
												</a>
											</div>
											</td>
											<td width="15%">
											<div align="left"><bean:write name="imovelClienteNovo"
												property="clienteRelacaoTipo.descricao" /></div>
											</td>
											<td width="20%" align="right"><logic:notEmpty name="imovelClienteNovo"
												property="cliente.cpf">
												<bean:write name="imovelClienteNovo" property="cliente.cpfFormatado" />
											</logic:notEmpty> <logic:notEmpty name="imovelClienteNovo"
												property="cliente.cnpj">
												<bean:write name="imovelClienteNovo" property="cliente.cnpjFormatado"  />
											</logic:notEmpty></td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
							</table>
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<div><input name="botaoRemover" value="Remover"
						onclick="return CheckboxNaoVazio(document.forms[0].idRemocaoClienteImovel) && confirm('Confirma remoção ?') && redirecionarSubmit('removerAtualizarImovelClienteAction.do');"
						class="bottonRightCol" type="button"></div>
					</td>
				</tr>

				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=3" />
					</div>
					</td>
				</tr>
			</table>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp" %>
</html:form>
</div>
</body>



<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/atualizarImovelWizardAction.do?concluir=true&action=atualizarImovelClienteAction'); }
</script>



<script>

	if(document.forms[0].idNomeConta != undefined){

      verficarSelecao(document.forms[0].idNomeConta,'radio');
    }

</script>


</html:html>
