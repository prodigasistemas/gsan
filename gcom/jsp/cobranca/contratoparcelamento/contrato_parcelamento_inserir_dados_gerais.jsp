<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
 
	<script language="JavaScript">
		  
		  function recuperarDadosPopupClienteContrParcel(codigoRegistro, descricaoRegistro, cnpj, tipoConsulta) {
		    var form = document.forms[0];
		
		     if (tipoConsulta == 'cliente') {
			      	form.autocompleteCliente.value = codigoRegistro + " - " + descricaoRegistro;
			      	if(cnpj == null || cnpj == ""){
			      		form.cnpjCpfCliente.value = "";
			      	}else{
				      	form.cnpjCpfCliente.value = cnpj;
			      	}
				     verificarClienteHabilitado(<c:out value="${permissaoEspecial}"></c:out>, tipoConsulta, descricaoRegistro, '');
		   	 }else if(tipoConsulta == 'clienteSuperior'){
		    		form.autocompleteClienteSuperior.value = codigoRegistro + " - " + descricaoRegistro;
		    		if(cnpj == null || cnpj == ""){
			      		form.cnpjCpfClienteSuperior.value = "";
			      	}else{
				      	form.cnpjCpfClienteSuperior.value = cnpj;
			      	}
			      	verificarClienteHabilitado(<c:out value="${permissaoEspecial}"></c:out>, tipoConsulta, '',descricaoRegistro);
		   	 }
		     
		  }
		  
		 
			/* Chama Popup */ 
			function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
				if(objetoRelacionado.disabled != true){
					if (objeto == null || codigoObjeto == null){
						abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
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
		 	function verificaQtdItensUsuario(row, i) { 
				if(i == 200){
					var div = document.getElementById("autocompleteMsgUsuario");
					div.style.display = "block";
					select.hideResultsNow();
				}
				return row[0]; 
			}
			
			function verificaQtdItensCliente(item, i) { 
				if(i == 200){
					var div = document.getElementById("autocompleteMsgCliente");
					div.style.display = "block";
					select.hideResultsNow();
				}
				
				return item.resultado;
			}
			
			function verificaQtdItensClienteSuperior(item, i) { 
				if(i == 200){
					var div = document.getElementById("autocompleteMsgClienteSuperior");
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
						
						verificarClienteHabilitado(<c:out value="${permissaoEspecial}"></c:out>,'cliente',data.resultado,'');
						esconderDivMsg('autocompleteMsgCliente');
					});
			  });
			  
			   $(document).ready(function(){
			     	$("#autocompleteClienteSuperior").autocomplete("autocomplete?method=3",
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
						formatItem: verificaQtdItensClienteSuperior
				  }).result(function(event, data, formatted) {
					  var form = document.forms[0];
					  form.cnpjCpfClienteSuperior.value =  data.cnpj;
					  verificarClienteHabilitado(<c:out value="${permissaoEspecial}"></c:out>,'clienteSuperior', '',data.resultado);
					  esconderDivMsg('autocompleteMsgClienteSuperior');
				});
				  
			  });
			  
  </script>
  <script>
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
         
         function verificaRadioButtons(indicadorResponsavel){
         
         		var form = document.forms[0];
         		if(indicadorResponsavel != null && indicadorResponsavel != ''){
				if(indicadorResponsavel == '1'){
					form.indicadorResponsavel[0].checked = true;
				}else if(indicadorResponsavel == '2'){
					form.indicadorResponsavel[1].checked = true;
				}else if(indicadorResponsavel == '3'){
					form.indicadorResponsavel[2].checked = true;
				}
			}else{
				form.indicadorResponsavel[1].checked = true;
			}
         
         }
         
         function habilitaTipoRelacao(){
         		var form = document.forms[0];
         	
         		if(form.numeroContratoAnt.value != null && form.numeroContratoAnt.value != ""){
         			form.relacaoAnterior.disabled = false;
         		}else{
         			form.relacaoAnterior.disabled = true;
      			}
         }
         
         
         function verificarClienteHabilitado(permissaoEspecial, tipo, autocompleteCliente, autocompleteClienteSuperior){
         		var form = document.forms[0];
         	
         		if(autocompleteClienteSuperior != null && autocompleteClienteSuperior != "" && tipo == 'clienteSuperior'){
         			form.cnpjCpfCliente.value = "";
         			form.autocompleteCliente.value = "";
         			form.relacaoCliente.disabled = true;
         			form.autocompleteCliente.disabled = true;
         			document.getElementById("btBuscarCliente").style.display = "none";

         			form.indicadorResponsavel[0].disabled = true;
         			form.indicadorResponsavel[1].checked = true;
         			form.indicadorResponsavel[2].disabled = true;
         			
         		}else if(autocompleteCliente != null && autocompleteCliente != "" && tipo == 'cliente'){
         			form.autocompleteClienteSuperior.disabled = true;
         			form.cnpjCpfClienteSuperior.value = "";
         			form.autocompleteClienteSuperior.value = "";
         			form.relacaoCliente.disabled = false;
         			document.getElementById("btBuscarClienteSuperior").style.display = "none";
         			
         			if(permissaoEspecial == true){
          			form.indicadorResponsavel[0].disabled = false;
          			form.indicadorResponsavel[1].checked = true;
          			form.indicadorResponsavel[2].disabled = false;
         			}else{
         				form.indicadorResponsavel[0].disabled = true;
             			form.indicadorResponsavel[1].checked = true;
             			form.indicadorResponsavel[2].disabled = true;
         			}
         			
      			}
      			
      			if((autocompleteCliente == null || autocompleteCliente == "")
      				 && (autocompleteClienteSuperior == null || autocompleteClienteSuperior == "")){
      				form.autocompleteClienteSuperior.disabled = false;
      				document.getElementById("btBuscarCliente").style.display = "";
      				form.cnpjCpfClienteSuperior.value = "";
      				form.autocompleteCliente.disabled = false;
      				document.getElementById("btBuscarClienteSuperior").style.display = "";
      				form.cnpjCpfCliente.value = "";
      				form.relacaoCliente.disabled = false;
      				form.relacaoCliente.selectedIndex = 0;
      				form.indicadorResponsavel[0].disabled = false;
         			form.indicadorResponsavel[1].checked = true;
         			form.indicadorResponsavel[2].disabled = false;
      			}
         }
         
         function verificarCampoVazio(tipo, event){
       		var form = document.forms[0];
       		
       		if(tipo == 'clienteSuperior'){
        		esconderDivMsg('autocompleteMsgClienteSuperior');
       		}else if (tipo == 'cliente'){
       			esconderDivMsg('autocompleteMsgCliente');
       		}
       		
       		var verificaUltimaTecla = false;
       		
         		if(event.keyCode == 8){
         			verificaUltimaTecla = true;
         		}
       		 
    			if(verificaUltimaTecla ||
  						((form.autocompleteCliente.value == null || form.autocompleteCliente.value == "")
    				 && (form.autocompleteClienteSuperior.value == null || form.autocompleteClienteSuperior.value == ""))){
    				form.autocompleteClienteSuperior.disabled = false;
    				document.getElementById("btBuscarCliente").style.display = "";
    				form.cnpjCpfClienteSuperior.value = "";
    				form.autocompleteCliente.disabled = false;
    				document.getElementById("btBuscarClienteSuperior").style.display = "";
    				form.cnpjCpfCliente.value = "";
    				form.relacaoCliente.disabled = false;
    				form.relacaoCliente.selectedIndex = 0;
    				form.indicadorResponsavel[0].disabled = false;
       			form.indicadorResponsavel[1].checked = true;
       			form.indicadorResponsavel[2].disabled = false;
    			}
       }
         
         function limparClienteSuperior(){
       	  var form = document.forms[0];
       	 	
       	  form.autocompleteClienteSuperior.disabled = false;
       	  form.autocompleteClienteSuperior.value = "";
  			document.getElementById("btBuscarCliente").style.display = "";
  			form.cnpjCpfClienteSuperior.value = "";
  			form.autocompleteCliente.disabled = false;
  			form.autocompleteCliente.value = "";
  			document.getElementById("btBuscarClienteSuperior").style.display = "";
  			form.cnpjCpfCliente.value = "";
  			form.relacaoCliente.disabled = false;
 			
 				form.indicadorResponsavel[0].disabled = false;
   			form.indicadorResponsavel[1].checked = true;
   			form.indicadorResponsavel[2].disabled = false;
         }
         
         function limparCliente(){
       	  var form = document.forms[0];
       	  form.autocompleteClienteSuperior.disabled = false;
       	  form.autocompleteClienteSuperior.value = "";
  			document.getElementById("btBuscarCliente").style.display = "";
  			form.cnpjCpfClienteSuperior.value = "";
  			form.autocompleteCliente.disabled = false;
  			form.autocompleteCliente.value = "";
  			document.getElementById("btBuscarClienteSuperior").style.display = "";
  			form.cnpjCpfCliente.value = "";
  			form.relacaoCliente.disabled = false;
 			
 				form.indicadorResponsavel[0].disabled = false;
   			form.indicadorResponsavel[1].checked = true;
   			form.indicadorResponsavel[2].disabled = false;
         }
         
			function verificaCliqueEnterContratoAnt(tecla){
       	  
       	  var codigo = null;
       	  if (document.all) {
       		  codigo = event.keyCode;
      		  }else{
      			 codigo = tecla.which;
      		  }
       	  
       	  if (codigo == 13) {
       		  var form = document.forms[0]; 
       		    form.action = "exibirInserirContratoParcelamentoClienteAction.do?method=mostrarPrimeiraEtapa";
       			 form.submit();
       	  }
       	  
         }
         
		function limparUsuario() {
			var form = document.forms[0];
			
	      	form.loginUsuario.value = "";
		    form.nomeUsuario.value = "";
		
			form.loginUsuario.focus();
		}	
  </script>
    
    <table border="0" width="100%">
      <tr >
        <td height="10"><strong>N&uacute;mero do Contrato:<span class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
        <td width="19%"><div align="left"><strong>
         <html:text 
	         property="numeroContrato" 
	         size="15" 
	         value="${contratoCadastrar.numero}" 
	         maxlength="15"
	         />
        </strong></div></td>
        <td width="14%" height="10"><strong>Data do 
            Contrato:<span class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
          <td colspan="5"> 
                  <html:text value="${contratoCadastrar.dataContratoFormatada}" 
					onkeyup="mascaraData(this, event);" onkeypress="return verificaData(event);"
					property="dataContrato" size="10" maxlength="10" /> 
				<a href="javascript:abrirCalendario('InserirContratoParcelamentoPorClienteActionForm', 'dataContrato')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
          </td>
      </tr>
      <tr>
        <td width="12%"><strong>No.  Contrato Anterior:
          </strong></td>
        <td width="21%"><strong>
          
          <html:text 
          		property="numeroContratoAnt" 
          		size="15" 
	          	value="${contratoCadastrar.contratoAnterior.numero}" 
	          	maxlength="15" 
	          	onkeyup="javascript: habilitaTipoRelacao();" 
	          	onkeypress="pesquisaEnterSemUpperCase(event, 'exibirInserirContratoParcelamentoClienteAction.do?consulta=contratoAnterior', 'numeroContratoAnt');" />
          <span class="style2"><a href="javascript:abrirPopup('exibirContratoParcelamentoPesquisar.do?indicadorUsoTodos=1&limparForm=OK&tipoConsulta=contratoAnterior&indicadorPesquisaApenasContEncerrados=1', 500, 700);">
          	<img src="imagens/pesquisa.gif" alt="Pesquisar Contrato Parcelamento Anterior" width="23" height="21" border="0" /></a></span></strong></td>
       
        <td width="9%"><strong>Tipo Rela&ccedil;&atilde;o:</strong></td>
        <td width="25%"><strong>
          	<select name="relacaoAnterior" id="relacaoAnterior" <c:if test="${contratoCadastrar.contratoAnterior == null}">disabled="true"</c:if> >
				<option value=""></option>
				<logic:notEmpty name="collTipoRelacao">
					<logic:iterate name="collTipoRelacao" id="tipoRelacao">
						<option value="${tipoRelacao.id}" <c:if test="${contratoCadastrar.relacaoAnterior != null && tipoRelacao.id == contratoCadastrar.relacaoAnterior.id }">selected="selected"</c:if> >
							<c:out value="${tipoRelacao.descricao}"></c:out>
						</option>
					</logic:iterate>
				</logic:notEmpty>
			</select>
			
        </strong></td>
      </tr>
        <tr >
          <td class="style3"><strong>Usu&aacute;rio  Respons&aacute;vel:<span class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
          
            <td colspan="6"><span class="style2"><strong>
				<html:text property="loginUsuario" 
					size="12" 
					maxlength="11"
					style="text-transform: none;"
					onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirInserirContratoParcelamentoClienteAction.do?consulta=usuario', 'loginUsuario');"/>
				
				<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?limparForm=OK&tipoConsulta=usuario&mostrarLogin=S', 'usuario', null, null, 370, 600, '',document.forms[0].loginUsuario);">
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
          <td colspan="6" class="style3"><div align="left">
            <div align="left">
              <hr>
            </div>
          </div></td>
        </tr>
       </table>
       <table border="0" width="100%">
        <tr>
          <td class="style3"><strong>Cliente Superior:</strong></td>
          <td colspan="5">
          
          		<div class="mensagem" id="autocompleteMsgClienteSuperior">
                	<img onclick="esconderDivMsg('autocompleteMsgClienteSuperior');" src="<bean:message key="caminho.imagens"/>close.png" style="cursor: pointer; margin-left: 270px; position: absolute; margin-top: -10px; "  />
                	Sua pesquisa encontrou mais de 200 registros. <br />
                	Digite mais caracteres para filtrar os resultados!
                </div>
          <strong>
          	<c:if test="${clienteSuperiorContrato != null}">
                <bean:define id="clienteSuperiorAutocomplete" value="${clienteSuperiorContrato.cliente.id} - ${clienteSuperiorContrato.cliente.nome}"></bean:define>
				<bean:define id="clienteSuperiorCnpj" value="${clienteSuperiorContrato.cliente.cnpjFormatado}"></bean:define>
			</c:if>
            <input type="text" value="${clienteSuperiorAutocomplete}" onblur="esconderDivMsg('autocompleteMsgClienteSuperior');"  onchange="verificarCampoVazio('clienteSuperior',event);" onkeypress="verificarCampoVazio('clienteSuperior',event);" size="48" maxlength="65" id="autocompleteClienteSuperior" name="autocompleteClienteSuperior" title="Campo auto-completável, retorna resultados da busca a partir de três caracteres digitados." />
            	<a id="btBuscarClienteSuperior" 
					href="javascript:abrirPopup('exibirPesquisarResponsavelSuperiorAction.do?indicadorUsoTodos=1&limparForm=OK&tipoConsulta=clienteSuperior', 400, 800); ">
				<img width="23" height="21" border="0"
					src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					title="Pesquisar Cliente" /></a> 
				<input type="text" value="${clienteSuperiorCnpj}" style="border:0; background: #EFEFEF;" size="15" readonly="readonly"  disabled="disabled" name="cnpjCpfClienteSuperior" />
        	</strong>
	        	<a href="javascript:limparClienteSuperior();">
					<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
				</a>
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
            <c:if test="${clienteContrato != null}">
                <bean:define id="clienteAutocomplete" value="${clienteContrato.cliente.id} - ${clienteContrato.cliente.nome}"></bean:define>
				<bean:define id="clienteCnpj" value="${clienteContrato.cliente.cnpjFormatado}"></bean:define>
			</c:if>
			
            <input type="text" value="${clienteAutocomplete}" onblur="esconderDivMsg('autocompleteMsgCliente');" onchange="verificarCampoVazio('cliente',event);" onkeypress="verificarCampoVazio('cliente',event);"  size="48" maxlength="65" id="autocompleteCliente" name="autocompleteCliente" title="Campo auto-completável, retorna resultados da busca a partir de três caracteres digitados." />
            	<a id="btBuscarCliente"
					href="javascript:abrirPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK&tipoConsulta=cliente', 400, 800); ">
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
          <td class="style3"><strong>Tipo da <br />Rela&ccedil;&atilde;o:</strong></td>
          <td colspan="5">
          		<html:select property="relacaoCliente">
					<option value=""></option>
					<logic:notEmpty name="collRelacaoCliente">
						<logic:iterate name="collRelacaoCliente" id="relacaoCliente">
							<option value="${relacaoCliente.id}" <c:if test="${contratoCadastrar.relacaoCliente.id == relacaoCliente.id}">selected="selected"</c:if> >
								<c:out value="${relacaoCliente.descricao}"></c:out>
							</option>
						</logic:iterate>
					</logic:notEmpty>
				</html:select>
          </td>
        </tr>
        <tr>
          <td class="style3" width="30"><strong>Respons&aacute;vel:</strong></td>
          <td colspan="5"><strong>
            <label>
              <html:radio property="indicadorResponsavel" value="1" />
              Indicado na Conta</label>
            <label>
              <html:radio property="indicadorResponsavel" value="2" />
              Atual do Im&oacute;vel</label>
            <label>
              <html:radio property="indicadorResponsavel" value="3" />
              Todos</label>
          </strong></td>
        </tr>
     </table>
       <table border="0">
        <tr>
          <td colspan="6" class="style3"><div align="left">
            <div align="left">
              <hr>
            </div>
          </div></td>
        </tr>
        <tr>
          <td class="style3"><strong>Per&iacute;odo de Refer&ecirc;ncia do D&eacute;bito:</strong></td>
          <td colspan="5">
          	<html:text onkeypress="return verificaData(event);" onkeyup="mascaraAnoMes(this, event);" value="${contratoCadastrar.anoMesDebitoInicioFormatado}" property="anoMesDebitoInicio" size="7"  maxlength="7" />
            <strong> a</strong>
            <html:text onkeypress="return verificaData(event);" onkeyup="mascaraAnoMes(this, event);" value="${contratoCadastrar.anoMesDebitoFinalFormatado}" property="anoMesDebitoFinal" size="7"  maxlength="7" />
            (mm/aaaa) </td>
        </tr>
        <tr>
          <td class="style3"><strong>Per&iacute;odo de Vencimento do D&eacute;bito:</strong></td>
          <td colspan="5">
              	<html:text value="${contratoCadastrar.dataVencimentoInicioFormatada}"
					onkeyup="mascaraData(this, event);" onkeypress="return verificaData(event);"
					property="dataVencimentoInicio" size="10" maxlength="10" /> <a
					href="javascript:abrirCalendario('InserirContratoParcelamentoPorClienteActionForm', 'dataVencimentoInicio')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					
                <strong> a</strong>
                <html:text value="${contratoCadastrar.dataVencimentoFinalFormatada}"
					onkeyup="mascaraData(this, event);" onkeypress="return verificaData(event);"
					property="dataVencimentoFinal" size="10" maxlength="10" /> <a
					href="javascript:abrirCalendario('InserirContratoParcelamentoPorClienteActionForm', 'dataVencimentoFinal')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					
				 (dd/mm/aaaa) 
          </td>
        </tr>
        <tr>
          <td colspan="6" class="style3"><div align="left">
            <div align="left">
              <hr>
            </div>
          </div></td>
        </tr>
        <tr> 
          <td class="style3"><strong>Observa&ccedil;&atilde;o:</strong></td>
          <td colspan="5"><span class="style2"><strong> 
            <html:textarea cols="48" rows="6" property="observacao" value="${contratoCadastrar.observacao}" 
            	onkeyup="limitTextArea(document.forms[0].observacao, 400, document.getElementById('utilizado'), document.getElementById('limite'));"/>
            	<br>
					<span id="utilizado">0</span>/<span id="limite">400</span>
            </strong></span> </td>
        </tr>
      </table>
