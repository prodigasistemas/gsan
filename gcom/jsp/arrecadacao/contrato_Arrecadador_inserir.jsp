<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>
<%@page import="gcom.arrecadacao.ArrecadadorContratoTarifa"%>
<%@page import="gcom.util.Util"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css"	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<html:javascript staticJavascript="false" formName="InserirContratoArrecadadorActionForm" />
													
<script language="JavaScript">
   	var tipoContaBancaria;
   	
	function validarForm(form){
		
		if(validateInserirContratoArrecadadorActionForm(form)){
		    if(validateInteger(form)){
		    	if(validateEmail(form)){
	   				if( form.tamanhoColecao.value == '0' ) {
   						alert("Informe Contrato Tarifa");
   						return false;
   					}  else {	
    					submeterFormPadrao(document.forms[0]);
    				}
    			}	
	   		}
		}
	}
	
	function email () {
		this.aa = new Array("emailCliente", "E-Mail inválido.", new Function ("varName", " return this[varName];"));
	}
	
	function IntegerValidations () {
		this.aa = new Array("numeroContrato", "O campo Número do Contrato deve conter apenas números.", new Function ("varName", " return this[varName];"));
	}
	
	function caracteresespeciais () {
    	this.aa = new Array("numeroDiaFloat", "Quantidade de dias FLOAT possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }
	
	function limparContaBancaria(form, contaBancaria) {
		if(contaBancaria == 'arrecadacao'){
			form.idContaBancariaArrecadador.value = "";
	    	form.bcoArrecadadorConta.value = "";
	    	form.agArrecadadorConta.value = "";
	    	form.numeroArrecadadorConta.value = "";
		}else if(contaBancaria == 'tarifa'){
			form.idContaBancariaTarifa.value = "";
	    	form.bcoTarifaConta.value = "";
	    	form.agTarifaConta.value = "";
	    	form.numeroTarifaConta.value = "";
		}
	}

    function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) {
    	var form = document.forms[0];
	    if (tipoContaBancaria == 'arrecadacao') {
	    	form.idContaBancariaArrecadador.value = codigoRegistro;
	        form.bcoArrecadadorConta.value = descricaoRegistro1;
	        form.agArrecadadorConta.value = descricaoRegistro2;
	        form.numeroArrecadadorConta.value = descricaoRegistro3;
	      }else{
	         if (tipoContaBancaria == 'tarifa') {
		    	form.idContaBancariaTarifa.value = codigoRegistro;
		        form.bcoTarifaConta.value = descricaoRegistro1;
		        form.agTarifaConta.value = descricaoRegistro2;
		        form.numeroTarifaConta.value = descricaoRegistro3;
	         }abrirPopup('contaBancariaPesquisarAction.do?tipo=contaBancaria');
	      }
    }
	
    function pesquisaTipoConta(tipo) {
    	tipoContaBancaria = tipo;
    	abrirPopup('contaBancariaPesquisarAction.do?tipo=contaBancaria');
    }  
    
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
		if (tipoConsulta == 'cliente') {
	        form.idCliente.value = codigoRegistro;
	        form.nomeCliente.value = descricaoRegistro;
	     }
	}
	
	function habilitarPesquisaCliente(form) {
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.idCliente.value);
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}

	function limparForm(form){
		
		var form = document.forms[0];
		
		form.idClienteCombo.value = "-1";
        form.numeroContrato.value = "";
        form.bcoArrecadadorConta.value = "";
        form.agArrecadadorConta.value = "";
        form.numeroArrecadadorConta.value = "";
        form.bcoTarifaConta.value = "";
        form.agTarifaConta.value = "";
        form.numeroTarifaConta.value = "";
        form.idCliente.value = "";
        form.nomeCliente.value = "";
        form.idConvenio.value = "";
        form.indicadorCobranca[0].checked = false;
		form.indicadorCobranca[1].checked = false;
        form.dtInicioContrato.value = "";
        form.dtFimContrato.value = "";
        form.emailCliente.value = "";
        form.numeroDiaFloat.value = "";
        form.valorTarifa.value = "";
        form.formaDeArrecadacao.value = "-1";
        form.valorTarifaPercentual.value = "";     

		form.action = 'exibirInserirContratoArrecadadorAction.do?acao=limparForm';
		form.submit();
	}

	function limparCliente(form){
	var form = document.forms[0];
		form.idCliente.value = "";
		form.nomeCliente.value = "";
	}	 
	
	function adicionarObjeto(){

		var form = document.forms[0];
		
		var objFormaArrecadacao = form.formaDeArrecadacao;
		var objNmDiasFloat = form.numeroDiaFloat;
		var objValorTarifa = form.valorTarifa;
		var objValorTarifaPercentual = form.valorTarifaPercentual;
		
		if( objFormaArrecadacao.value != '-1' ) {

			if( (objValorTarifa.value != "" || objValorTarifaPercentual.value != "") 
					 && objNmDiasFloat.value != "" ) {
				form.action = 'exibirInserirContratoArrecadadorAction.do?acao=adicionar';
				form.submit();
			} else if (objValorTarifa.value == "" && objNmDiasFloat.value != "" 
						&& objValorTarifaPercentual.value == "") {
					alert("Informe Valor Tarifa"); 
			} else if (objValorTarifa.value != "" && objValorTarifaPercentual.value != "" 
						&& objNmDiasFloat.value == "" ) {
					alert("Informe Quantidade de dias FLOAT"); 
			} else {
					alert("Informe Valor Tarifa \nInforme Quantidade de dias FLOAT");
			}
		} else {
			alert("Informe Forma de Arrecadação");
		}
	}
	
	/* Remove componente da Colecao contrato tarifa */
	function remover(obj){
	
		var form = document.forms[0];
		
		if (confirm('Confirma Remoção?')) {
	
			form.action = 'exibirInserirContratoArrecadadorAction.do?acao=remover&id='+obj;
			form.submit();
		}
	}
	
	function desabilitaCampo(){
		var form = document.forms[0];
		var objFormaArrecadacao = form.formaDeArrecadacao;
	   
	    if( objFormaArrecadacao.value == '-1' ){	        	        
		 	form.numeroDiaFloat.disabled = true;
		 	form.valorTarifa.disabled = true;
	 		form.numeroDiaFloat.value = "";
		 	form.valorTarifa.value = "";
		 	
		 	form.valorTarifaPercentual.disabled = true;
			form.valorTarifaPercentual.value = "";
			
		  }else{
		 	form.numeroDiaFloat.disabled = false;
		 	form.valorTarifa.disabled = false;
		 	form.valorTarifaPercentual.disabled = false;
		 }		
	}
	
	 function numeroPositivo() { 
	 
    		
   		var form = document.forms[0];

   		if( form.numeroDiaFloat.value <= '0000' && form.numeroDiaFloat.value != '' ){
   			alert("Quantidade de Dias FLOAT deve somente conter números positivos.");
   			form.numeroDiaFloat.value = "";
   		} 
   		
   		if( ( form.valorTarifa.value <= '0,00' && form.valorTarifa.value != '' ) ||
	   		( form.valorTarifa.value == '00' && form.valorTarifa.value != '' ) ) {
   		
   					alert("Valor da Tarifa deve somente conter números positivos.");
		   			form.valorTarifa.value = "";
   		} 
	}
	
	function bloqueiaValorTarifa(){
		
		var form = document.forms[0];
		
		if (form.valorTarifa.value != ""){
			
			form.valorTarifaPercentual.disabled = true;
			form.valorTarifaPercentual.value = "";
			
		}else{
			
			form.valorTarifaPercentual.disabled = false;
		
		}
		
		if (form.valorTarifaPercentual.value != ""){
		
			form.valorTarifa.disabled = true;
			form.valorTarifa.value = "";
		
		}else{
		
			form.valorTarifa.disabled = false;
		
		}
	
	}
	
</script>
</head>



<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}'); desabilitaCampo();">

<html:form action="/inserirContratoArrecadadorAction.do"
	name="InserirContratoArrecadadorActionForm"
	type="gcom.gui.arrecadacao.InserirContratoArrecadadorActionForm"
	method="post" onsubmit="return validateInserirContratoArrecadadorActionForm(this);">
	
	<html:hidden property="tamanhoColecao"/>
	
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
					<td class="parabg">Inserir Contrato de Arrecadador</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar o contrato de arrecadador, informe
					os dados abaixo:</td>
				</tr>

				<tr>
					<td width="32%" class="style3"><strong>Arrecadador:<font color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><html:select property="idClienteCombo" tabindex="1" style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoClienteArrecadador" property="id" labelProperty="nome" />
				  </html:select></td>
				</tr>

				<tr>
					<td width="32%" class="style3"><strong>Número Contrato:<font color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text
						property="numeroContrato" size="10" maxlength="10" tabindex="2"
						onkeypress="return isCampoNumerico(event);"/></span></b></strong>
				  </td>
				</tr>
				<tr>
					<td width="32%"><strong>Conta Depósito Arrecadação:<font color="#FF0000">*</font></strong></td>
					<html:hidden property="idContaBancariaArrecadador"/>
					<td width="68%"><html:text maxlength="4" property="bcoArrecadadorConta" size="4"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text maxlength="9" property="agArrecadadorConta" size="9"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text maxlength="20" property="numeroArrecadadorConta" size="20"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<a href="javascript:pesquisaTipoConta('arrecadacao');">
					<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Conta Bancária" /></a>
					<a href="javascript:limparContaBancaria(document.forms[0],'arrecadacao');">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Limpar Conta Bancária"/></a>
				  </td>
				</tr>

				<tr>
					<td width="32%"><strong>Conta Depósito Tarifa:<font color="#FF0000">*</font></strong></td>
					<html:hidden property="idContaBancariaTarifa"/>
					<td width="68%"><html:text maxlength="4" property="bcoTarifaConta" size="4"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text maxlength="9" property="agTarifaConta" size="9"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text maxlength="20" property="numeroTarifaConta" size="20"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<a href="javascript:pesquisaTipoConta('tarifa');" >
					<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Conta Bancária" /></a>
					<a href="javascript:limparContaBancaria(document.forms[0],'tarifa');">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Limpar Conta Bancária"/></a>
				  </td>
				</tr>

				<tr>
					<td><strong>Cliente:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> <html:text property="idCliente" size="10" maxlength="10" tabindex="3"
						onkeyup="somente_numero(this);"
						onkeypress="javascript:return validaEnter(event, 'exibirInserirContratoArrecadadorAction.do?objetoConsulta=1', 'idCliente');" />
					</strong> 
					<a href="javascript:habilitarPesquisaCliente(document.forms[0]);"	alt="Pesquisar Cliente"><img 
					width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="existeCliente">
						<logic:equal name="existeCliente" value="exception">
							<html:text property="nomeCliente" size="35" maxlength="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="existeCliente" value="exception">
							<html:text property="nomeCliente" size="35" maxlength="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="existeCliente">
						<logic:empty name="InserirContratoArrecadadorActionForm" property="idCliente">
							<html:text property="nomeCliente" size="35" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="InserirContratoArrecadadorActionForm"
							property="idCliente">
							<html:text property="nomeCliente" size="35" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparCliente();"> <img
						border="0"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						style="cursor: hand;" /> </a></td>
				</tr>

				<tr>
					<td width="32%" class="style3"><strong>Código do Convênio:<font
						color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text
						property="idConvenio" size="28" maxlength="20" tabindex="4" />
				  </span></b></strong></td>
				</tr>
				
				<tr>
					<td width="32%"><strong>Indicador de Cobrança de ISS:</strong></td>
					<td width="68%"><strong> <span class="style1"><strong> 
					<html:radio property="indicadorCobranca" value="1" tabindex="5" /> <strong>Cobra ISS 
					<html:radio property="indicadorCobranca" value="2" tabindex="6" />Não Cobra ISS
				  </strong></strong></span></strong></td>
				</tr>

				<tr>
					<td><strong>Data Início do contrato:</strong></td>
					<td><html:text property="dtInicioContrato" size="10"
						maxlength="10" tabindex="7"
						onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dtFimContrato,this);"
						onkeypress="return isCampoNumerico(event);"
						onfocus="replicarCampo(document.forms[0].dtFimContrato,this);" />
					<a
						href="javascript:abrirCalendarioReplicando('InserirContratoArrecadadorActionForm', 'dtInicioContrato', 'dtFimContrato');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>

				<tr>
					<td><strong>Data Fim do Contrato:</strong></td>
					<td><html:text property="dtFimContrato" size="10" maxlength="10"
						tabindex="8" onkeyup="mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendario('InserirContratoArrecadadorActionForm', 'dtFimContrato')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				
				<tr>
					<td width="32%" class="style3"><strong>Tamanho Máximo para Identificação do Imóvel:<font
						color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2">
					  <html:text
						property="tamanhoMaximoIdentificacaoImovel" 
						size="2" 
						maxlength="2" 
						tabindex="9"
						onkeypress="return isCampoNumerico(event);" />
				  </td>
				</tr>
				
				<tr>
					<td><strong>E-mail:</strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="emailCliente" size="50" maxlength="40" tabindex="6" style="text-transform: none;" /></span>
					  	</b></strong>
					</td>
				</tr>
				<tr>
					<td height="10" colspan="3">
						<hr>
					</td>
				</tr>
				
				<tr>
					<td width="32%" class="style3"><strong>Forma:<font color="#FF0000">*</font></strong></td>
					<td width="78%" colspan="2">
						<html:select property="formaDeArrecadacao" 
									tabindex="1" 
									onclick="javascript:desabilitaCampo();"
									style="width:400px;">
						<html:option value="-1"> &nbsp; </html:option>
						<logic:present name="colecaoFormaArrecadacao" scope="session">
							<html:options collection="colecaoFormaArrecadacao" property="id" labelProperty="descricao" />
						</logic:present>
				  		</html:select>
				   </td>
				</tr>
				<tr>
					<td><strong>Valor da Tarifa:<font color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><span class="style2"> 
					  <html:text property="valorTarifa" 
					  			size="15" 
					  			maxlength="17" 
					  			tabindex="10"
					  			onblur="javascript:desabilitaCampo();numeroPositivo();"
					  			onkeypress="formataValorMonetario( this, 17 );bloqueiaValorTarifa();return isCampoNumerico(event);" 
								onkeyup="formataValorMonetario( this, 17 );bloqueiaValorTarifa();"
								style="text-align: right;" />
						</span>
					</td>
				</tr>
				<tr>
					<td><strong>Percentual da Tarifa:<font color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><span class="style2">
						<html:text property="valorTarifaPercentual" 
					  			size="15" 
					  			maxlength="6" 
					  			tabindex="11"
					  			onblur="javascript:desabilitaCampo();numeroPositivo();"
					  			onkeypress="formataValorMonetario( this, 6 );bloqueiaValorTarifa();return isCampoNumerico(event);" 
								onkeyup="formataValorMonetario( this, 6 );bloqueiaValorTarifa();"
								style="text-align: right;" />
						</span>
					</td>				
				</tr>
				<tr>
					<td><strong>Quantidade de dias FLOAT:<font color="#FF0000">*</font></strong></td>
					<td width="68%" colspan="2"><strong><b><span class="style2"> 
					  <html:text property="numeroDiaFloat" 
					  			 size="4" 
  					  			 onblur="javascript:desabilitaCampo();numeroPositivo();"
					  			 maxlength="4" 
					  			 tabindex="12"
					  			 style="text-align: right;" 
					  			 onkeyup="javascript:verificaNumeroInteiro(this);"
					  			
					  	/></span></b></strong>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td><strong>Tarifas Contrato:</strong></td>
					<td colspan="3" align="right">
							<input type="button" class="bottonRightCol" value="Adicionar"
							tabindex="25" style="width: 80px; align: right;"
							onclick="javascript:adicionarObjeto();" name="botaoAdicionarArrecadacaoContratoTarifa">
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center" width="10%">&nbsp;</td>
									<td width="35%">
										<div align="left"><strong>Forma</strong></div>
									</td>
									<td width="20%">
										<div align="left"><strong>Valor da Tarifa</strong></div>
									</td>
									<td width="20%">
										<div align="left"><strong>Perc. da Tarifa</strong></div>
									</td>
									<td width="15%">
										<div align="left"><strong>N. de dias FLOAT</strong></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						
						<tr>
							<td>
					
							<%String cor = "#FFFFFF";%> 
							<logic:present name="colecaoArrecadadorContratoTarifaSelecionados" scope="session">
		
								<div style="width: 100%; height: 100; overflow: auto;">
		
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td><%cor = "#cbe5fe";%>
		
										<table width="100%" align="center" bgcolor="#99CCFF">
		
											<logic:iterate name="colecaoArrecadadorContratoTarifaSelecionados" 
												id="arrecadadorContratoTarifa"
												type="ArrecadadorContratoTarifa">
												<c:set var="count" value="${count+1}" />
												<c:choose>
													<c:when test="${count%2 == '1'}">
														<tr bgcolor="#FFFFFF">
													</c:when>
													<c:otherwise>
														<tr bgcolor="#cbe5fe">
													</c:otherwise>
												</c:choose>
		
													<td align="center" width="10%">
														<img
														src="<bean:message key='caminho.imagens'/>Error.gif"
														onclick="remover('${count}')"
														title="Remover" style="cursor: hand;">
														
															
													<td width="35%">
														<div align="left">
															<logic:present name="arrecadadorContratoTarifa" property="arrecadacaoForma">
																<bean:write name="arrecadadorContratoTarifa" property="arrecadacaoForma.descricao" />
															</logic:present> 
		
															<logic:notPresent name="arrecadadorContratoTarifa" property="arrecadacaoForma">&nbsp;</logic:notPresent>
														</div>
													</td>
													
													<td width="20%">
														<div align="left">
															<logic:present name="arrecadadorContratoTarifa" property="valorTarifa">
																<%= Util.formatarMoedaReal(arrecadadorContratoTarifa.getValorTarifa())%>
															</logic:present> 
															<logic:notPresent name="arrecadadorContratoTarifa" property="valorTarifa">&nbsp;</logic:notPresent>
														</div>
													</td>
													
													<td width="20%">
														<div align="left">
															<logic:present name="arrecadadorContratoTarifa" property="valorTarifaPercentual">
																<%= Util.formatarMoedaReal(arrecadadorContratoTarifa.getValorTarifaPercentual())%>
																</logic:present> 
															<logic:notPresent name="arrecadadorContratoTarifa" property="valorTarifaPercentual">&nbsp;</logic:notPresent>
														</div>
													</td>
													
													<td width="15%">
														<div align="left">
															<logic:present name="arrecadadorContratoTarifa" property="numeroDiaFloat">
																<bean:write name="arrecadadorContratoTarifa" property="numeroDiaFloat" />
															</logic:present> 
		
															<logic:notPresent name="arrecadadorContratoTarifa" property="numeroDiaFloat">&nbsp;</logic:notPresent>
														</div>
													</td>
												
		
											</logic:iterate>
		
										</table>
		
										</td>
									</tr>
		
								</table>
		
								</div>
							</logic:present>
							
							</td>
						</tr>
					</table>
					
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>	
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>

					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td width="33%" align="left"><input type="button"
						name="ButtonReset" class="bottonRightCol" value="Limpar"
						onClick="limparForm();"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Inserir" onClick="validarForm(document.forms[0]);"
						tabindex="11" /></td>
				</tr>

			</table>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>
