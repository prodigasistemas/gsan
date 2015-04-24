<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.cadastro.imovel.Imovel" %>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="ManterImovelActionForm" dynamicJavascript="false" page="3" />
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

    function validateManterImovelActionForm(form) {
    	
    	if (bCancel)
      		return true;
        else
        	return testarCampoValorZero(document.ManterImovelActionForm.numeroPontos, 'Número de Pontos') && 
        		testarCampoValorZero(document.ManterImovelActionForm.numeroMoradores, 'Número de Moradores') && 
        		testarCampoValorZero(document.ManterImovelActionForm.numeroIptu, 'Número de IPTU') && 
        		testarCampoValorZero(document.ManterImovelActionForm.numeroContratoCelpe, 'Contrato Companhia de Energia') && 
        		testarCampoValorZero(document.ManterImovelActionForm.idImovel, 'Imóvel Principal') && 
        		validateCaracterEspecial(form) && 
        		validateRequired(form) && 
        		validateLong(form) && 
        		validateDecimal(form) && 
        		validarUTM() && 
        		validateBigInteger(form) &&
        		validateFloatNegativoPositivo(form); 
	}

   	function caracteresespeciais () {
     	this.af = new Array("numeroPontos", "Número de Pontos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ag = new Array("numeroMoradores", "Número de Moradores deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ah = new Array("numeroIptu", "Número de IPTU deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ai = new Array("numeroContratoCelpe", "Contrato Companhia de Energia deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.at = new Array("idImovel", "Imóvel Principal possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.au = new Array("sequencialRotaEntrega", "Sequencial da Rota de Entrega deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.aq = new Array("idFuncionario", "Funcionário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));     	
		this.aq = new Array("informacoesComplementares", "Informações Complementares deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function required () {
    }

    function BigIntegerValidations () {
		this.az = new Array("numeroIptu", "Número de IPTU deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}

    function IntegerValidations () {
     	this.al = new Array("numeroPontos", "Número de Pontos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.am = new Array("numeroMoradores", "Número de Moradores deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ao = new Array("numeroContratoCelpe", "Contrato Companhia de Energia deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.au = new Array("idImovel", "Imóvel Principal deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
		this.az = new Array("idFuncionario", "Funcionário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function InteiroZeroPositivoValidations () {
	    this.av = new Array("sequencialRotaEntrega", "Sequencial da Rota de Entrega deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
   	var tipoPesquisaRota = "ENTREGA";
    
   	function pesquisarRota(){
		tipoPesquisaRota='ALTERNATIVA';
        abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim', 400, 800);		
	}

</script>

<script>

	function limparZero(){
  		var form = document.forms[0];
	
	  	if(form.numeroPontos.value == '0' || form.numeroPontos.value == 'null'){form.numeroPontos.value = ''}
	  	if(form.numeroMoradores.value == '0' || form.numeroMoradores.value == 'null'){form.numeroMoradores.value = ''}
	  	if(form.numeroIptu.value == '0' || form.numeroIptu.value == 'null'){form.numeroIptu.value = ''}
	  	if(form.numeroContratoCelpe.value == '0' || form.numeroContratoCelpe.value == 'null'){form.numeroContratoCelpe.value = ''}
	  	if(form.cordenadasUtmX.value == '0' || form.cordenadasUtmX.value == 'null'){form.cordenadasUtmX.value = ''}
	  	if(form.cordenadasUtmY.value == '0' || form.cordenadasUtmY.value == 'null'){form.cordenadasUtmY.value = ''}
	}


  	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];

	    if (tipoConsulta == 'imovel') {

	      limparPesquisaImovel();
	      form.idImovel.value = codigoRegistro;

	      form.action='atualizarImovelWizardAction.do?action=exibirAtualizarImovelConclusaoAction&pesquisar=SIM';
	      form.submit();
	    }else if ('funcionario' == tipoConsulta) {
			
		 	form.idFuncionario.value = codigoRegistro;
		 	form.action = 'atualizarImovelWizardAction.do?action=exibirAtualizarImovelConclusaoAction';
		 	form.submit();
    	}
  	}

 	function limparPesquisaImovel() {
    	var form = document.forms[0];
      	form.idImovel.value = "";
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

  	function limpaImovelPrincipal(){
    	var form = document.ManterImovelActionForm;
    	var id = form.idImovel.value;

		if(confirm('Confirma remoção ?')){
		    form.idImovel.value= "";
	      	form.action='atualizarImovelWizardAction.do?action=exibirAtualizarImovelConclusaoAction&remover=REMOVER';
    	  	form.submit();
   		}
  	}
  
  	function limparId(){
    	var form = document.ManterImovelActionForm;
      	if(form.quantidadeElemento.value == 0){
    		form.idImovel.value= "";
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
	
	function receberRota(codigoRota,destino) {
 	  	var form = document.forms[0];
 	  
 	  	form.action = 'atualizarImovelWizardAction.do?action=exibirAtualizarImovelConclusaoAction';
 	  
 	  	if(tipoPesquisaRota == 'ALTERNATIVA'){
 	   	  	form.idRotaAlternativa.value = codigoRota;
 	  	}else{
 	   	  	form.idRota.value = codigoRota;
 	  	}

 	  	form.submit();
	}
	
			//Integraçã com o GIS
	function respostaGis(){
	     redirecionarSubmit('atualizarImovelWizardAction.do?destino=6&action=exibirAtualizarImovelConclusaoAction');
	}

</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:limparZero();limitTextArea(document.forms[0].informacoesComplementares, 750, document.getElementById('utilizado'), document.getElementById('limite'));">
<div id="formDiv">
<html:form action="/atualizarImovelWizardAction" method="post"
	onsubmit="return validateManterImovelActionForm(this);">

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
						<td class="parabg">Atualizar</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td>Para concluir o cadastro, informe os dados abaixo:</td>
						<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelAtualizarAbaConclusao', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
							<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
				</table>
			<br>
			<table width="100%" border="0">			    
				<tr>
					<td width="21%" height="24"><strong>N&uacute;mero de Pontos:<font
						color="#FF0000"></font></strong></td>
					<td width="79%"><html:text maxlength="4" size="4"
						property="numeroPontos" onkeypress="return isCampoNumerico(event);" /></td>
				</tr>
				<tr>
					<td height="24"><strong>N&uacute;mero de Moradores:<font
						color="#FF0000"></font></strong></td>
					<td><html:text maxlength="4" size="4" property="numeroMoradores" 
					onkeypress="return isCampoNumerico(event);" /></td>
				</tr>
				<tr>
					<td height="24"><strong>N&uacute;mero de IPTU:</strong></td>
					<td><logic:equal name="tarifaSocial" value="1">
						<html:text maxlength="20" size="20" property="numeroIptu"
							disabled="true" onkeypress="return isCampoNumerico(event);" />
					</logic:equal> <logic:notEqual name="tarifaSocial" value="1">
						<html:text maxlength="20" size="20" property="numeroIptu"
						onkeypress="return isCampoNumerico(event);" />
					</logic:notEqual></td>
				</tr>
				<tr>
					<td height="24"><strong>Contrato Companhia de Energia:</strong></td>
					<td><logic:equal name="tarifaSocial" value="1">
						<html:text maxlength="10" size="10" property="numeroContratoCelpe"
							disabled="true" />
					</logic:equal> <logic:notEqual name="tarifaSocial" value="1">
						<html:text maxlength="10" size="10" property="numeroContratoCelpe" />
					</logic:notEqual></td>
				</tr>
				<tr>
					<td height="24"><strong>Medidor de Energia:</strong></td>
					<td><html:text maxlength="10" size="10"
						property="numeroMedidorEnergia" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Data da Visita Comercial:</strong></td>
					<td><html:text maxlength="10" size="10" tabindex="10"
						onkeyup="mascaraData(this, event);"
						property="dataVisitaComercial" onkeypress="return isCampoNumerico(event)" />
						
						<a href="javascript:abrirCalendario('ManterImovelActionForm', 'dataVisitaComercial');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>	
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
						<td height="24">
							<strong>Extrato para Respons&aacute;vel:</strong>
						</td>
						<td>
							<logic:empty name="bloquearExtratoParaResponsavel" >
								<p>
									<label> 
										<html:radio value="1" property="extratoResponsavel" />Emitir
									</label> 
									<label> 
										<html:radio value="2" property="extratoResponsavel" /> Não Emitir
									</label> 
									<br>
									<label> 
									</label>
								</p>
							</logic:empty>
							<logic:notEmpty name="bloquearExtratoParaResponsavel">
								<p>
									<label> 
										<html:radio value="1" property="extratoResponsavel" disabled="true" />Emitir
									</label> 
									<label> 
										<html:radio value="2" property="extratoResponsavel" disabled="true" /> Não Emitir
									</label> 
									<br>
									<label> 
									</label>
								</p>
							</logic:notEmpty>
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
					<td><html:text maxlength="4" size="4" property="numeroQuadraEntrega" /></td>
				</tr>
				
				<tr>
					<td height="24"><strong>Código da Rota de Entrega:<font
						color="#FF0000"></font></strong></td>
					<td>
					<html:hidden property="idRota"/>
					<html:text maxlength="4" size="4" property="codigoRota" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					<a href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim', 400, 800);">
					
			<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a> 
			<a href="javascript:limparRota(false)"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td height="24"><strong>Sequencial da Rota de Entrega:<font
						color="#FF0000"></font></strong></td>
					<td><html:text maxlength="4" size="4" property="sequencialRotaEntrega" /></td>
				</tr>
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
				</logic:equal>
				
				<tr>
					<td height="24"><strong>Código da Rota de Alternativa:<font
						color="#FF0000"></font></strong></td>
					<td>
					<html:hidden property="idRotaAlternativa"/>
					<html:text maxlength="4" size="4" property="codigoRotaAlternativa" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					<a href="javascript:pesquisarRota();">
					<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a> 
					<a href="javascript:limparRota(true)"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
					</td>
				</tr>
				
				
				<logic:notEqual name="envioContaListar" value="listar">
					<html:hidden property="sequencialRotaEntrega" />
				</logic:notEqual>
				<tr>
					<td height="24"><strong>Coordenada X:</strong></td>
					<td><html:text maxlength="30" property="cordenadasUtmX"
						style="text-align: right;" 
						onkeypress="return validarNumeroDecimal(this, event)"
						onblur="limparVirgulaNumeroDecimal(this)" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Coordenada Y:</strong></td>
					<td><html:text maxlength="30" property="cordenadasUtmY"
						style="text-align: right;"
						onkeypress="return validarNumeroDecimal(this, event)"
						onblur="limparVirgulaNumeroDecimal(this)" /></td>
					<td>
						<input type="button" class="bottonRightCol" value="AcquaGIS" tabindex="3"  id="botaoGis" align="left" onclick="respostaGis();">
					</td>
				</tr>
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>

				<!-- ****************************************************************************************** -->
				<tr>
					<td><strong>Imóvel Principal:</strong></td>
					<td><html:text property="idImovel" maxlength="9"
						size="9"
						onkeypress="javascript: validaEnter(event, 'atualizarImovelWizardAction.do?action=exibirAtualizarImovelConclusaoAction&pesquisar=SIM', 'idImovel');
						return isCampoNumerico(event)" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);"><img
						width="23" height="21"
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
				<tr>
					<!-- *************** -->
					<td colspan="3" align="center" bgcolor="#99CCFF" bordercolor="#99CCFF">
							<strong>Endere&ccedil;o</strong>
					</td>
				</tr>
				<tr>
				 <td colspan="3">
					 <logic:present name="imoveisPrincipal">								
									<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%" align="center" bgcolor="#99CCFF">
											<!--corpo da segunda tabela-->
		
											<%String cor = "#FFFFFF";%>
											<logic:present name="imoveisPrincipal">
												<logic:iterate name="imoveisPrincipal" id="imovel"
													type="Imovel">
		
													<%if (cor.equalsIgnoreCase("#FFFFFF")) {
													   cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
													<%} else {
													    cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
														<%}%>
														<td align="center"><bean:write name="imovel"
															property="enderecoFormatado" /></td>
													</tr>
												</logic:iterate>
											</logic:present>
										</table>
									</div>								
							</logic:present>
				 </td>
				</tr>
				
				<tr>
					<td><strong>Funcion&aacute;rio:</strong></td>
					<td>
						
						<html:text maxlength="9" 
							property="idFuncionario" 
							size="9"
							onkeypress="javascript: validaEnter(event, 'atualizarImovelWizardAction.do?action=exibirAtualizarImovelConclusaoAction', 'idFuncionario');
							return isCampoNumerico(event)"
							onkeyup="limparCamposFuncionario();" /> 
						
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
							<img border="0" title="Apagar" src="imagens/limparcampo.gif" height="21" width="23">
						</a>
					</td>
				</tr>
				<tr>
      				<td align="left"><strong>Informações Compl.:</strong></td>
			       <td>
						<html:textarea property="informacoesComplementares" cols="45" rows="6" onkeyup="limitTextArea(document.forms[0].informacoesComplementares, 750, document.getElementById('utilizado'), document.getElementById('limite'));"/>					
				   </td>
		       </tr>	
		       <tr>
		        <td></td>
		        <td>
		            <strong><span id="utilizado">0</span>/<span id="limite">750</span></strong>
		        </td>
		       </tr>
		       
			   <logic:equal name="contaEnvioObrigatorio" value="obrigatorio">
			       <tr>
		                <td height="24">&nbsp;</td>
		                <td colspan="2"> <strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
	               </tr>				
			   </logic:equal>
			   
				<!-- **** -->
				<tr>
					<td colspan="3">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=6" />
					</div>
					</td>
				</tr>
			</table>
			</td>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/atualizarImovelWizardAction.do?concluir=true&action=atualizarImovelConclusaoAction'); }
</script>

</html:html>
