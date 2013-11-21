<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

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
  </script>
    
    <table border="0" width="100%">
      <tr >
        <td height="10"><strong>N&uacute;mero do Contrato:<span class="style2"></span></strong></td>
        <td width="19%"><div align="left"><strong>
         <input type="text" disabled="disabled" size="20" value="${contratoManter.numero}" maxlength="9" />
        </strong></div>
        </td>
        <td width="14%" height="10"><strong>Data do 
            Contrato:</strong></td>
          <td colspan="1"> 
                  <input disabled="disabled" type="text" value="${contratoManter.dataContratoFormatada}"  
					 size="20" maxlength="10" /> 
          </td>
     </tr>
     <tr > 
        <td width="12%"><strong>No.  Contrato Anterior:
	          </strong>
          </td>
	        <td width="21%"><strong>
	          <input type="text" disabled="disabled"  size="20" value="${contratoManter.contratoAnterior.numero}" maxlength="9" onkeyup="javascript: habilitaTipoRelacao();" onkeypress="return isCampoNumerico(event);" />
	          </strong>
           </td>
        <td width="9%"><strong>Tipo Rela&ccedil;&atilde;o:</strong></td>
        <td width="25%"><strong>
        	 	<input type="text" disabled="disabled"  size="20" value="<c:if test="${contratoManter.relacaoAnterior != null}"><c:out value="${contratoManter.relacaoAnterior.descricao}"></c:out></c:if>" />
        </strong></td>
     </tr>
        <tr > 
           <td width="14%" height="10"><strong>Situação de Cancelamento:</strong></td>
          <td>
          	<c:if test="${contratoManter.motivoDesfazer != null}">
				<input type="text" disabled="disabled"  size="20" value="Cancelado" />
			</c:if>
			<c:if test="${contratoManter.motivoDesfazer == null}">
				<input type="text" disabled="disabled"  size="20" value="Não Cancelado" />
			</c:if>
          </td>
           <td width="14%" height="10"><strong>Situação de Pagamento:</strong></td>
          <td colspan="3"> 
                  <c:if test="${contratoManter.valorParcelamentoACobrar != null && contratoManter.valorParcelamentoACobrar > 0}">
					<input type="text" disabled="disabled"  size="20" value="Pendente" />
				</c:if>
				<c:if test="${contratoManter.valorParcelamentoACobrar != null && contratoManter.valorParcelamentoACobrar == 0}">
					<input type="text" disabled="disabled"  size="20" value="Pago" />
				</c:if>
          </td>
        </tr>
        <tr >
          <td class="style3"><strong>Usu&aacute;rio  Respons&aacute;vel:<span class="style2"></span></strong></td>
          
            <td colspan="5">
            <strong>
	           	<input type="text" 
	           		name="loginUsuario" 
					size="12" 
					maxlength="11"
					style="text-transform: none;"
					disabled="disabled"
					value="${contratoManter.usuarioResponsavel.login}"/>
				<input type="text" 
					name="nomeUsuario" 
					disabled="disabled"
					size="36" 
					maxlength="36"
					value="${contratoManter.usuarioResponsavel.nomeUsuario}" />
        	</strong>
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
          <td class="style3"><strong>Cliente:</strong></td>
          <td colspan="5">
          <strong>
            <c:if test="${clienteContrato != null}">
                <bean:define id="clienteAutocomplete" value="${clienteContrato.id} - ${clienteContrato.nome}"></bean:define>
				<bean:define id="clienteCnpj" value="${clienteContrato.cnpjFormatado}"></bean:define>
			</c:if>
			
            <input type="text"  disabled="disabled" value="${clienteAutocomplete}"size="50" maxlength="65" id="autocompleteCliente" name="autocompleteCliente" />
				<input type="text" value="${clienteCnpj}" style="border:0; background: #EFEFEF;" size="16" readonly="readonly"  disabled="disabled" name="cnpjCpfCliente" />
        	</strong>
        	</td>
        </tr>
        <tr>
          <td class="style3"><strong>Tipo da <br />Rela&ccedil;&atilde;o:</strong></td>
          <td colspan="5">
          			<input type="text" disabled="disabled"  size="9" value="<c:if test="${contratoManter.relacaoCliente != null}"><c:out value="${contratoManter.relacaoCliente.descricao}"></c:out></c:if>" />
          </td>
        </tr>
        <tr>
          <td class="style3" width="30"><strong>Respons&aacute;vel:</strong></td>
          <td colspan="5"><strong>
            <label>
              <input type="radio" disabled="disabled" <c:if test="${contratoManter.indicadorResponsavel == 1}">checked="checked"</c:if>  value="1" />
              Indicado na Conta</label>
            <label>
              <input type="radio" disabled="disabled" <c:if test="${contratoManter.indicadorResponsavel == 2}">checked="checked"</c:if>  value="2" />
              Atual do Im&oacute;vel</label>
            <label>
              <input type="radio" disabled="disabled" <c:if test="${contratoManter.indicadorResponsavel == 3}">checked="checked"</c:if>  value="3" />
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
          	<input type="text"  disabled="disabled" value="${contratoManter.anoMesDebitoInicioFormatado}" size="7"  maxlength="7" />
            <strong> a</strong>
            <input type="text"  disabled="disabled" value="${contratoManter.anoMesDebitoFinalFormatado}" size="7"  maxlength="7" />
            (mm/aaaa) </td>
        </tr>
        <tr>
          <td class="style3"><strong>Per&iacute;odo de Vencimento do D&eacute;bito:</strong></td>
          <td colspan="5">
              	<input type="text" value="${contratoManter.dataVencimentoInicioFormatada}"  disabled="disabled"
					 size="10" maxlength="10" /> 
					
                <strong> a</strong>
                <input type="text" value="${contratoManter.dataVencimentoFinalFormatada}"  disabled="disabled"
					 size="10" maxlength="10" /> 
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
            <textarea disabled="disabled" style="width: 400px;" ><c:out value="${contratoManter.observacao}"></c:out></textarea>
            </strong></span> </td>
        </tr>
      </table>
