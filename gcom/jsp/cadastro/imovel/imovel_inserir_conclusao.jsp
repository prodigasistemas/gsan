<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript formName="InserirImovelActionForm" dynamicJavascript="false" staticJavascript="false" page="3" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
	
	function validarUTM(){
    	var form = document.forms[0];
		var retorno = true;
	    
	    if( trim(form.cordenadasUtmX.value).length > 0 && trim(form.cordenadasUtmY.value).length == 0){
			retorno = false;
   	      	alert("Informar ambas ou nenhuma das coordenadas UTM");
	 	}else if (trim(form.cordenadasUtmY.value).length > 0  && trim(form.cordenadasUtmX.value).length == 0){
			retorno = false;	      
	      	alert("Informar ambas ou nenhuma das coordenadas UTM");
	    }
	    
	    return retorno;
	}
    
    var bCancel = false;

    function validateInserirImovelActionForm(form) {
    	if (bCancel)
      		return true;
        else
       		return testarCampoValorZero(document.InserirImovelActionForm.numeroPontos, 'N�mero de Pontos') && 
       			testarCampoValorZero(document.InserirImovelActionForm.numeroMoradores, 'N�mero de Moradores') && 
       			testarCampoValorZero(document.InserirImovelActionForm.numeroContratoCelpe, 'Contrato Companhia de Energia')	&& 
       			testarCampoValorZero(document.InserirImovelActionForm.idImovel, 'Im�vel Principal') && 
       			validateCaracterEspecial(form) && 
       			validateRequired(form) && 
       			validateLong(form) && 
       			validateDecimal(form) && 
       			validarUTM() && 
       			validateBigInteger(form); 
   	}

    function caracteresespeciais () {
     	this.af = new Array("numeroPontos", "N�mero de Pontos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ag = new Array("numeroMoradores", "N�mero de Moradores deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ah = new Array("numeroIptu", "N�mero de IPTU deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ai = new Array("numeroContratoCelpe", "Contrato Companhia de Energia deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.at = new Array("idImovel", "Im�vel Principal deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.av = new Array("sequencialRotaEntrega", "Sequ�ncia da Rota de Entrega deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.aq = new Array("idFuncionario", "Funcion�rio deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.aq = new Array("informacoesComplementares", "Informa��es Complementares deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     
    }

    function isFloat(campo, evt) {
        var valor = campo.value;
    	var charCode = (evt.which) ? evt.which : evt.keyCode;
    	
    	if (charCode != 44 && charCode != 45 && charCode > 31 && (charCode < 48 || charCode > 57)) {
    	    return false;
    	} else {
    		if (charCode == 44) {
    			var indexNegativo = valor.indexOf("-");
				if (indexNegativo == 0 && valor.length == 1) {
					return false;
				}

				if (valor.length == 0) {
					return false;
				}
				
    			var parts = evt.srcElement.value.split(',');
        	    if (parts.length > 1) {
            	    return false;
        	    }
    	    }
    		
    	    if (charCode == 45 && valor.length > 0) {
        	    return false;
    	    }
    	    
    	    return true;
    	}
    }
    
    function required () {
    }
    
    function BigIntegerValidations () {
		this.az = new Array("numeroIptu", "N�mero de IPTU deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	}
    

    function IntegerValidations () {
    	this.al = new Array("numeroPontos", "N�mero de Pontos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.am = new Array("numeroMoradores", "N�mero de Moradores deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ao = new Array("numeroContratoCelpe", "Contrato Companhia de Energia deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.au = new Array("idImovel", "Im�vel Principal deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.az = new Array("idFuncionario", "Funcion�rio deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
    }

     function InteiroZeroPositivoValidations () {
     	this.ax = new Array("sequencialRotaEntrega", "Sequ�ncial da Rota de Entrega deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     }

</script>

<script>
	var tipoPesquisaRota = "ENTREGA";
  //Recebe os dados do(s) popup(s)
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];

    	if (tipoConsulta == 'imovel') {
      		
      		limparPesquisaImovel();
      		
      		form.idImovel.value = codigoRegistro;
      		form.action='inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction&pesquisar=SIM';
      		form.submit();
      		
    	}else if ('funcionario' == tipoConsulta) {
			
		 	form.idFuncionario.value = codigoRegistro;
		 	form.action = 'inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction';
		 	form.submit();
    	}
  	}

	function limparPesquisaImovel() {
    	var form = document.forms[0];

      	form.idImovel.value = "";
  	}

  	function limpaImovelPrincipal(){
    	var form = document.InserirImovelActionForm;
    	var id = form.idImovel.value;
		
		if(confirm('Confirma remo��o ?')){
			form.idImovel.value= "";
          	form.action = "removerInserirImovelPrincipalAction.do";
	      	form.submit()	
    	}
  	}
	
	function limparCamposFuncionario(){
		var form = document.forms[0];
 		form.nomeFuncionario.value = '';
	}

	function limparFuncionario() {
		document.forms[0].idFuncionario.value = '';
		document.forms[0].nomeFuncionario.value = '';
	}
	
	function limparRota(ehAlternativa) {
    	var form = document.forms[0];
    	if(ehAlternativa){
      		form.idRotaAlternativa.value = "";
      		form.codigoRotaAlternativa.value = "";
    	}else{
      		form.idRota.value = "";
      		form.codigoRota.value = "";
      	}
  	}
	
	function receberRota(codigoRota,destino) {
 		var form = document.forms[0];
 	  
 	  	form.action = 'inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction';

 	  	if(tipoPesquisaRota == 'ALTERNATIVA'){
 	   	  	form.idRotaAlternativa.value = codigoRota;
 	  	}else{
 	   	  	form.idRota.value = codigoRota;
 	  	}

 	  	form.submit();
	}
	
		//Integra�� com o GIS
	function respostaGis(){
	     redirecionarSubmit('inserirImovelWizardAction.do?destino=6&action=exibirInserirImovelConclusaoAction');		
	}
	
	function pesquisarRota(){
		tipoPesquisaRota='ALTERNATIVA';
		
		abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim&destinoRota=Final', 400, 800);
	}
	
	function limiteCaracteres(obj,limit){
	 var limite = limit;
	 var form = obj;
	 var tamanho = form.value.length;
	 var restantes = document.forms[0].restantes;
	 
	 if(form.value.length > limite){
 	   form.value = form.value.substring(0,tamanho-(tamanho-limite));
 	   restantes.value = limite;
	   alert("Voc� s� pode digitar no m�ximo "+limite+" caracteres");	 
	 }else {	  
	    restantes.value = limite - tamanho;
	 }	 
	}
	
	function validarEnvioEmail() {
		
		var form = document.forms[0];
		
		form.action='inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction';
		form.submit();
		
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5" onload="limitTextArea(document.forms[0].informacoesComplementares, 750, document.getElementById('utilizado'), document.getElementById('limite'));">
<div id="formDiv">
<html:form action="/inserirImovelWizardAction" method="post"
	onsubmit="return validateInserirImovelActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=6" />



	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="7" />
		<tr>
			<td valign="top" class="leftcoltext">
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
			<td width="603" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Im�vel</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
							<tr>
					<td>Para concluir o cadastro, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelInserirAbaConclusao', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>   
				</tr>
			</table>
			<br>
			<table width="100%" border="0">
				<!-- <tr>
	      <tdcolspan="2">&nbsp;</td>
	    </tr> -->
				<tr>
					<td width="21%" height="24"><strong>N&uacute;mero de Pontos:<font
						color="#FF0000"></font></strong></td>
					<td width="79%"><html:text maxlength="4" size="4"
						property="numeroPontos"
						onkeypress="return isCampoNumerico(event)" /></td>
				</tr>
				<tr>
					<td height="24"><strong>N&uacute;mero de Moradores:<font
						color="#FF0000"></font></strong></td>
					<td><html:text maxlength="4" size="4" property="numeroMoradores" 
					onkeypress="return isCampoNumerico(event)"/></td>
				</tr>
				<tr>
					<td height="24"><strong>N&uacute;mero de IPTU:</strong></td>
					<td><html:text maxlength="20" size="20" property="numeroIptu" 
					onkeypress="return isCampoNumerico(event)"/></td>
				</tr>
				<tr>
					<td height="24"><strong>Contrato Companhia de Energia:</strong></td>
					<td><html:text maxlength="10" size="10"
						property="numeroContratoCelpe" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Medidor de Energia:</strong></td>
					<td><html:text maxlength="10" size="10"
						property="numeroMedidorEnergia" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Data da Visita Comercial:</strong></td>
					<td><html:text size="10" maxlength="10" tabindex="10"
						onkeyup="mascaraData(this, event);"
						property="dataVisitaComercial" 
						onkeypress="return isCampoNumerico(event)"/>
						<a href="javascript:abrirCalendario('InserirImovelActionForm', 'dataVisitaComercial');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calend�rio" tabindex="4"/></a>
						(dd/mm/aaaa)
					</td>
				</tr>
				
				<!-- ********* IMOVEL CONTA ENVIO **************** -->
				<logic:equal name="colecaoImovelEnvioContaVazia" value="false">
	
					<logic:equal name="contaEnvioObrigatorio" value="obrigatorio">
					<tr>
						<td height="24"><strong>Envio da Conta:<font color="#FF0000">*</font></strong></td>
						<td><html:select property="imovelContaEnvio"
							onchange="validarEnvioEmail();">
							<html:option value="-1">&nbsp;</html:option>
			                   <html:options collection="colecaoImovelEnnvioConta" 
			                   labelProperty="descricao" property="id"/>
						</html:select></td>
					</tr>
					</logic:equal>
					<logic:equal name="contaEnvioObrigatorio" value="opcional">
						<tr>
							<td height="24"><strong>Envio da Conta:</strong></td>
							<td><html:select property="imovelContaEnvio"
								onchange="validarEnvioEmail();">
								<html:option value="-1">&nbsp;</html:option>
				                   <html:options collection="colecaoImovelEnnvioConta" 
				                   labelProperty="descricao" property="id"/>
							</html:select></td>
						</tr>
					</logic:equal>
					
				</logic:equal>
				
				<logic:equal name="colecaoImovelEnvioContaVazia" value="true">
					
					<logic:equal name="contaEnvioObrigatorio" value="obrigatorio">
						<tr>
							<td height="24"><strong>Envio da Conta:<font color="#FF0000">*</font></strong></td>
							<td><html:select property="imovelContaEnvio"
									onchange="validarEnvioEmail();"
									disabled="true">
							</html:select></td>
						</tr>
					</logic:equal>
					<logic:equal name="contaEnvioObrigatorio" value="opcional">
						<tr>
							<td height="24"><strong>Envio da Conta:</strong></td>
							<td><html:select property="imovelContaEnvio"
									onchange="validarEnvioEmail();"
									disabled="true">
								</html:select></td>
						</tr>
					</logic:equal>
					
				</logic:equal>
				
				<!--  ********* FIM IMOVEL CONTA ENVIO ********* -->
					
				<logic:equal name="envioContaListar" value="listar">
					<tr>
						<td height="24"><strong>Extrato para Respons&aacute;vel:</strong></td>
						<td>
						<p><label> <html:radio value="1" property="extratoResponsavel" />
						Emitir</label> <label> <html:radio value="2"
							property="extratoResponsavel" /> N�o Emitir</label> <br>
						<label> </label></p>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
				
				<logic:equal name="envioContaListar" value="listar">
				
					<tr>
						<td height="24"><strong>Quadra de Entrega:<font
							color="#FF0000"></font></strong></td>
						<td><html:text maxlength="4" size="4" property="numeroQuadraEntrega" 
						onkeypress="return isCampoNumerico(event)"/></td>
					</tr>
					
					<tr>
						<td height="24"><strong>C�digo da Rota de Entrega:<font
							color="#FF0000"></font></strong></td>
						<td>
	
						<html:hidden property="idRota"/>
						<html:text maxlength="4" 
							size="4" 
							property="codigoRota" 
							readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #000000" />
							
						<a href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim', 400, 800);">
						
							<img border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/>
						</a> 
						
						<a href="javascript:limparRota(false)"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
						</td>
					</tr>
					
					<tr>
						<td height="24"><strong>Sequencial da Rota de Entrega:<font
							color="#FF0000"></font></strong></td>
						<td><html:text maxlength="4" size="4" property="sequencialRotaEntrega" 
						onkeypress="return isCampoNumerico(event)"/></td>
					</tr>
					<tr>
						<td height="24" colspan="3">
						<hr>
						</td>
					</tr>
				</logic:equal>
				<logic:notEqual name="envioContaListar" value="listar">
					<html:hidden property="sequencialRotaEntrega" />
				</logic:notEqual>
				
				
			 <tr>
					<td height="24"><strong>C�digo da Rota Alternativa:<font
						color="#FF0000"></font></strong></td>
					<td>
						<html:hidden property="idRotaAlternativa"/>
						
						<html:text maxlength="4" 
							size="4" 
							property="codigoRotaAlternativa" 
							style="background-color:#EFEFEF; border:0; color: #000000" />
							
						<a href="javascript:pesquisarRota();">
							<img border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
								border="0"/></a> 
							
						<a href="javascript:limparRota(true)"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
<!-- ----------------------------------Coordenadas Gis-------------------------------------------- -->
				<tr>
					<td height="24"><strong>Coordenada X:</strong></td>
					<td><html:text maxlength="30" property="cordenadasUtmX"
						style="text-align: right;"
						onkeypress="return isFloat(this, event)" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Coordenada Y:</strong></td>
					<td><html:text maxlength="30" property="cordenadasUtmY"
						style="text-align: right;"
						onkeypress="return isFloat(this, event)" /></td>
					<td>
						<input type="button" class="bottonRightCol" value="AcquaGIS" tabindex="3"  id="botaoGis" align="left" onclick="respostaGis();">
					</td>
				</tr>
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
<!-- --------------------------------------------------------------------------------------------- -->
				<tr>
					<td width="10%"><strong>Im�vel Principal:</strong></td>
					<td width="90%"><html:text property="idImovel" maxlength="9"
						size="9"
						onkeypress="javascript:return validaEnter(event, 'inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction&pesquisar=SIM', 'idImovel');"
						onkeyup="somente_numero(this);" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" /></a>
		   		        <logic:present name="idImovelPrincipalNaoEncontrado" scope="request">
                        	<input type="text" name="matriculaImovelPrincipal" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"
                        		value="${valorMatriculaImovelPrincipal}">
                        </logic:present>
                        <logic:notPresent name="idImovelPrincipalNaoEncontrado" scope="request">
                        	<logic:present name="valorMatriculaImovelPrincipal" scope="request">
                        	<input type="text" name="matriculaImovelPrincipal" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
    	                    	value="<bean:write name="valorMatriculaImovelPrincipal" scope="request" />">
                        	</logic:present>
                        	<logic:notPresent name="valorMatriculaImovelPrincipal" scope="request">
                        	<input type="text" name="matriculaImovelPrincipal" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
	                        	value="">
                        	</logic:notPresent>
                        </logic:notPresent>
						<a href="javascript:limpaImovelPrincipal()"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>						
                        
					</td>
				</tr>

				<!-- ************************************************************************ -->
				<tr>
					<td width="100%" colspan="3">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="0"
								bgcolor="#90c7fc" bordercolor="#90c7fc">
								<!--header da tabela interna -->
								<tr>
									<td align="center"><strong>Endere&ccedil;o</strong></td>
								</tr>
							</table>
							</td>
						</tr>

						<logic:present name="imoveisPrincipal">

							<tr>
								<td height="40">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" align="left" bgcolor="#99CCFF">
									<!--corpo da segunda tabela-->

									<%String cor = "#FFFFFF";%>

									<logic:iterate name="imoveisPrincipal" id="endereco">

										<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td align="center"><bean:write name="endereco"
												property="enderecoFormatado" /></td>
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
					<td width="26%"><strong>Funcion�rio:</strong></td>
					<td width="74%">
						
						<html:text maxlength="9" 
							property="idFuncionario" 
							size="9"
							onkeypress="javascript:return validaEnter(event, 'inserirImovelWizardAction.do?action=exibirInserirImovelConclusaoAction', 'idFuncionario');"
							onkeyup="limparCamposFuncionario(); somente_numero(this);" /> 
						
						<a href="javascript:abrirPopup('exibirFuncionarioPesquisar.do?limpaForm=S', 495, 300);">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								width="23" 
								height="21" 
								alt="Pesquisar" 
								border="0"></a> 

		   		        <logic:present name="idImovelPrincipalEncontrado" scope="request">
								
							<html:text property="nomeFuncionario" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
								
						</logic:present> 
						
						<logic:notPresent name="idImovelPrincipalEncontrado" scope="request">						

							<html:text property="nomeFuncionario" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />								
								
						</logic:notPresent> 
						
						<a href="javascript:limparFuncionario();"> 
							<img border="0" src="imagens/limparcampo.gif" height="21" width="23">
						</a>
					</td>
				</tr>
				<tr>
					<td align="left"><strong>Informa��es Compl.:</strong></td>
					<td>
						<html:textarea property="informacoesComplementares" cols="50" rows="6" onkeyup="limitTextArea(document.forms[0].informacoesComplementares, 750, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
						<strong><span id="utilizado">0</span>/<span id="limite">750</span></strong>	
					</td>
				</tr>
		       <logic:equal name="contaEnvioObrigatorio" value="obrigatorio">
			       <tr>
		                <td height="24">&nbsp;</td>
		                <td> <strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
	               </tr>				
			   </logic:equal>
				<tr>
					<td colspan="3">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=6" />
					</div>
					</td>
				</tr>
								

				<!-- ************************************************************************ -->
			</table>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>




</html:form>
</div>
</body>
<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirImovelWizardAction.do?concluir=true&action=inserirImovelConclusaoAction'); }
</script>


</html:html>
