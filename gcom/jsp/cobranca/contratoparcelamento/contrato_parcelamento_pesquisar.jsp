<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cobranca.parcelamento.ParcelamentoSituacao"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"	href="<bean:message key="caminho.css"/>popup.css"	type="text/css">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
 <link rel="stylesheet" href="<bean:message key="caminho.css"/>jquery.autocomplete.css" type="text/css" />
 <script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
 <script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.autocomplete.js"></script>

	<script language="JavaScript">
 	  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		  var form = document.forms[0];
		
		  if (tipoConsulta == 'usuario') {
		    
	  	      	form.loginUsuario.value = codigoRegistro;
		      	form.nomeUsuario.value = descricaoRegistro;
		      	form.nomeUsuario.style.color = '#000000';
		      	
		  }
	  } 
			  
		/* Chama Popup */ 
		function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
			if(objetoRelacionado.disabled != true){
				if (objeto == null || codigoObjeto == null){
					abrirPopup(url + "&" + "tipo=" + tipo, altura, largura);
				} else{
					if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
						alert(msg);
					} else{
						abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
					}
				}
			}
		}
	</script>
<script language="JavaScript">


	function validarForm(form){

		if(form.indicadorSituacao != null && (form.indicadorSituacao[0].checked == true 
				|| form.indicadorSituacao[1].checked == true || form.indicadorSituacao[2].checked == true)){
			
			if(form.dataContrato.value != null && form.dataContrato.value != "" && validarData(form.dataContrato) == false){
				alert("Data inválida");
			}else{
				form.submit();	
			}
			
		}else{
			alert("Informe pelo menos uma opção de seleção");
		}
		
	}

	function verificaData(event){
		
      	var valor = null;
      		if (event.which == null){
      			valor= String.fromCharCode(event.keyCode);   
			}else if (event.which != 0 && event.charCode != 0){
				valor= String.fromCharCode(event.which);
			 }   
			 
			if(valor != '/'){
				return isCampoNumerico(event);
			}
		
      }
</script>
  <script type="text/javascript">
  
  function validarData(campo){

  	if (campo.value!="") {
  		
  		var erro=0;
  		var barras = campo.value.split("/");
          
  		if (barras.length == 3){
  			
  			dia = barras[0];
  			mes = barras[1];
  			ano = barras[2];
              
  			resultado = (!isNaN(dia) && (dia > 0) && (dia < 32)) && (!isNaN(mes) && (mes > 0) && (mes < 13)) && (!isNaN(ano) && (ano.length == 4) && (ano >= 1900));
              
  			if (!resultado){
  				
  				campo.focus();
  				return false;
  			}
  		}
  		else{
  			campo.focus();
  			return false;
  		}
  	
  		return true;
  	}
  }
  </script>
<script type="text/javascript">

function verificaQtdItensCliente(item, i) { 
	if(i == 200){
		var div = document.getElementById("autocompleteMsgCliente");
		div.style.display = "block";
		select.hideResultsNow();
	}
	
	return item.resultado;
}

function parse_cliente(data){
	 return $.map(data, function(item) {
             return {
                     data: item,
                     value: item.resultado,
                     result: item.resultado
             }
     });
}

$(document).ready(function(){
 	$("#autocompleteCliente").autocomplete("autocomplete?method=1",
 			{
			width:450,
 		delay:1000,
 		minChars:3,
 		matchSubset:1,
 		max: 200,
 		mustMatch: true,
 		matchContains:1,
 		cacheLength:1,
 		autoFill:false,
 		parse: parse_cliente, 
 		dataType:'json',
		formatItem: verificaQtdItensCliente
  }).result(function(event, data, formatted) {
	 	 var form = document.forms[0];
		form.cnpjCpfCliente.value = data.cnpj;
		esconderDivMsg('autocompleteMsgCliente');
	});
});

function limparCliente(){
    var form = document.forms[0];
	form.cnpjCpfCliente.value = "";
	form.autocompleteCliente.value = "";
}

function limparUsuario(){
	var form = document.forms[0];
	
    form.loginUsuario.value = "";
    form.nomeUsuario.value = "";

	form.loginUsuario.focus();
}

function setSituacaoContrato(indicador){
	var form = document.forms[0];
	
	if(indicador == '1'){
		form.indicadorSituacao[1].checked = true;
		form.indicadorSituacao[0].disabled = true;
		form.indicadorSituacao[2].disabled = true;
	}else{
		form.indicadorSituacao[2].checked = true;
		form.indicadorSituacao[0].disabled = false;
		form.indicadorSituacao[1].disabled = false;
	}
	
}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(610, 460); setSituacaoContrato('<c:out value="${indicadorPesquisaApenasContEncerrados}"></c:out>');">

<html:form action="/retornarContratoParcelamentoPesquisar"
	name="PesquisarContratoParcelamentoActionForm"
	type="gcom.gui.cobranca.contratoparcelamento.PesquisarContratoParcelamentoActionForm"
	method="post"
	onsubmit="return validatePesquisarContratoParcelamentoActionForm(this);">
	<table width="580" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="520" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Contrato Parcelamento por Cliente</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		

			<table width="100%" border="0">
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					Preencha os campos para pesquisar contratos de parcelamento por cliente:
					</td>
				</tr>
				<tr>
					<td>
						<b>Número do Contrato:</b>
					</td>
					<td>
						<input type="text" name="numeroContrato" />
					</td>
				</tr>
				<tr>
					 <td width="14%" height="10"><strong>Data do 
				            Contrato:</strong></td>
				          <td colspan="5"> 
				                  <html:text value="${form.dataContratoFormatada}" 
									onkeyup="mascaraData(this, event);" onkeypress="return verificaData(event);"
									property="dataContrato" size="10" maxlength="10" /> 
								<a href="javascript:abrirCalendario('PesquisarContratoParcelamentoActionForm', 'dataContrato')">
								<img border="0"
									src="<bean:message key='caminho.imagens'/>calendario.gif"
									width="20" border="0" align="middle" alt="Exibir Calendário" /></a> (dd/mm/aaaa)
				          </td>
				</tr>
				<tr >
		          <td class="style3"><strong>Usu&aacute;rio  Respons&aacute;vel:</strong></td>
		          
		             <td colspan="6"><span class="style2"><strong>
						<html:text property="loginUsuario" 
							size="12" 
							maxlength="11"
							style="text-transform: none;"
							onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirContratoParcelamentoPesquisar.do?consulta=usuario', 'loginUsuario');"/>
						
						<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?limparForm=OK&tipoConsulta=usuario&mostrarLogin=S&caminhoRetornoTelaPesquisaUsuario=exibirContratoParcelamentoPesquisar', 'usuario', null, null, 370, 600, '',document.forms[0].loginUsuario);">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Usuário"></a>
							 
						<logic:present name="usuarioEncontrado" scope="session">
							<html:text property="nomeUsuario" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" 
								size="36" 
								maxlength="36" />
								
						</logic:present> 
		
						<logic:notPresent name="usuarioEncontrado" scope="session">
							<html:text property="nomeUsuario" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" 
								size="36" 
								maxlength="36" />
						</logic:notPresent>
						
						<a href="javascript:limparUsuario();">
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
						</a>
		                 </strong></span>
		              </td>
		        </tr>
				<tr>
		          <td class="style3"><strong>Cliente:</strong></td>
		          <td colspan="5">
		          
		          
		          <div class="mensagem" id="autocompleteMsgCliente">
		                	<img onclick="esconderDivMsg('autocompleteMsgCliente');" src="<bean:message key="caminho.imagens"/>close.png" style="cursor: pointer; margin-left: 270px; position: absolute; margin-top: -10px; "  />
		                	Sua pesquisa encontrou mais de 200 registros. <br />
		                	Digite mais caracteres para filtrar os resultados!
		                </div>
		          <strong>
		            <c:if test="${cliente != null}">
		                <bean:define id="clienteAutocomplete" value="${cliente.id} - ${cliente.nome}"></bean:define>
						<bean:define id="clienteCnpj" value="${cliente.cnpjFormatado}"></bean:define>
					</c:if>
					
		            <input type="text" value="${clienteAutocomplete}" onblur="esconderDivMsg('autocompleteMsgCliente');"  size="40" maxlength="65" id="autocompleteCliente" name="autocompleteCliente" title="Campo auto-completável, retorna resultados da busca a partir de três caracteres digitados." />
		            	<a id="btBuscarCliente"
		            	href="javascript:redirecionarSubmit('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&caminhoRetornoTelaPesquisaCliente=exibirContratoParcelamentoPesquisar');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Cliente" /></a> 
						<input type="text" value="${clienteCnpj}" style="border:0; background: #EFEFEF;" size="15" readonly="readonly"  disabled="disabled" name="cnpjCpfCliente" />
		        	</strong>
		       			<a href="javascript:limparCliente();">
							<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
						</a>
		        	</td>
		        </tr>
		        
		        <tr>
		        	<td>
		        		<b>Situação do Contrato:</b>
		        	</td>
		        	<td>
		        		<input type="radio" name="indicadorSituacao" value="1" /> Não Encerrado 
		        		<input type="radio" name="indicadorSituacao" value="2" /> Encerrado 
		        		<input type="radio" name="indicadorSituacao" value="3" /> Todos 
		        	</td>
		        </tr>

				<tr>
					
					<td>
						<input name="Button" type="button"
							class="bottonRightCol" value="Limpar" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirContratoParcelamentoPesquisar.do?desfazer=S"/>'">
					</td>

					<td align="right" colspan="3"><input type="button" name="Button"
						class="bottonRightCol" value="Pesquisar" tabindex="4"
						onClick="validarForm(document.forms[0])" /></td>
				</tr>

			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
