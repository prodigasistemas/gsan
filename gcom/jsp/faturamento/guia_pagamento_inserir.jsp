<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.faturamento.debito.DebitoTipo, gcom.arrecadacao.pagamento.GuiaPagamentoItem"%>
<%@ page import="gcom.util.Util"%>
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InserirGuiaPagamentoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--
	// Verifica se o usuário tem permissão especial para inserir Guia de Pagamento sem RA(38)
	var inserirGuiaPagamentoSemRa = ${requestScope.inserirGuiaPagamentoSemRa};

    //Recebe os dados do(s) popup(s)
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.InserirGuiaPagamentoActionForm;

       	if (tipoConsulta == 'cliente') {
       		form.codigoCliente.value = codigoRegistro;
        	form.action = 'exibirInserirGuiaPagamentoAction.do'
        	form.submit();
      	}

       	if (tipoConsulta == 'imovel') {
        	form.idImovel.value = codigoRegistro;
        	form.action = 'exibirInserirGuiaPagamentoAction.do'
        	form.submit();
      	}
 
       	if (tipoConsulta == 'registroAtendimento') {
        	form.registroAtendimento.value = codigoRegistro;
			form.nomeRegistroAtendimento.value = descricaoRegistro;
			form.nomeRegistroAtendimento.style.color = "#000000";
			
			form.action='exibirInserirGuiaPagamentoAction.do?objetoConsulta=1';
	      	form.submit();
			
			
     	}
       	
       	if (tipoConsulta == 'ordemServico') {
        	form.ordemServico.value = codigoRegistro;
        	form.descricaoOrdemServico.value = descricaoRegistro;

			form.action='exibirInserirGuiaPagamentoAction.do?objetoConsulta=2';
	      	form.submit();
        	
      	}

    }

    function limparImovel() {
        var form = document.InserirGuiaPagamentoActionForm;
		
		form.codigoCliente.disabled = false;
        form.idImovel.value = "";
        form.inscricaoImovel.value = "";
        form.nomeClienteUsuario.value = "";
        form.situacaoAgua.value = "";
        form.situacaoEsgoto.value = "";
        form.localidade.value = "";
    }
    
    function limparCliente() {
        var form = document.InserirGuiaPagamentoActionForm;
	
		form.idImovel.disabled = false;
        form.codigoCliente.value = "";
        form.nomeCliente.value = "";
        form.cpf.value = "";
        form.profissao.value = "";
        form.ramoAtividade.value = "";
    }
    
	function validaEnterCliente(event, caminho, campo) {
		var form = document.InserirGuiaPagamentoActionForm;
		validaEnter(event, caminho, campo);
		
		if(form.codigoCliente.value.length > 0) {
			form.idImovel.disabled = true;
        } else {
			form.idImovel.disabled = false;
			form.codigoCliente.value = "";
	        form.nomeCliente.value = "";
	        form.cpf.value = "";
	        form.profissao.value = "";
	        form.ramoAtividade.value = "";
		}
	}
	
	function controleImovel(form){
		form.codigoCliente.disabled = true;
	}
	
	function controleCliente(form){
		form.idImovel.disabled = true;
	}
	
	function validaEnterImovel(event, caminho, campo) {
		var form = document.InserirGuiaPagamentoActionForm;
		validaEnter(event, caminho, campo);
		
		if(form.idImovel.value.length > 0) {
			form.codigoCliente.disabled = true;
			
		} else {
			form.codigoCliente.disabled = false;
			form.idImovel.value = "";
	        form.inscricaoImovel.value = "";
	        form.nomeClienteUsuario.value = "";
	        form.situacaoAgua.value = "";
	        form.situacaoEsgoto.value = "";
	        form.localidade.value = "";
		}
	}
	
	function habilitarPesquisa(objeto,action) {
		if (objeto.disabled == false) {
			abrirPopup(action, 600, 500);
		}	
	}
    
	function validarForm(form){
	
		var msgDataVencimento = "Data de Vencimento anterior à data corrente.";
		var msgDataVencimento60 = "Data de vencimento posterior a data corrente mais 60 dias.";
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
		var DATA_ATUAL_60 = document.getElementById("DATA_ATUAL_60").value;
		urlRedirect = "/gsan/inserirGuiaPagamentoAction.do"
	
		if(document.InserirGuiaPagamentoActionForm.codigoCliente.value == '' && document.InserirGuiaPagamentoActionForm.idImovel.value == '' ){
			alert('Informe Matrícula do Imóvel ou Código do Cliente');
		} else {
			if (testarCampoValorZero(document.InserirGuiaPagamentoActionForm.registroAtendimento, 'Registro de Atendimento')){
				// Verifica se o usuário tem permissão especial para inserir Guia de Pagamento sem RA(38)
				if (!inserirGuiaPagamentoSemRa || form.codigoCliente.value != '') {
					var RA = document.InserirGuiaPagamentoActionForm.registroAtendimento;
					if (trim(RA.value) == '') {
						alert('Informe Registro de Atendimento.');
						RA.focus();
						return;
					}
				}
			}else {
				return;
			}
			
			if(testarCampoValorZero(document.InserirGuiaPagamentoActionForm.ordemServico, 'Ordem de Serviço')
   				&& testarCampoValorZero(document.InserirGuiaPagamentoActionForm.dataVencimento, 'Data de Vencimento')
   				//&& validateCaracterEspecial(form) 
   				&& validateRequired(form) 
   				&& validateLong(form)){
				
				if(validateInserirGuiaPagamentoActionForm(form) && validarObservacao()){
					
					if (document.InserirGuiaPagamentoActionForm.numeroTotalPrestacao.value > 1 &&
	   					document.InserirGuiaPagamentoActionForm.observacaoPagamentoParcial != undefined){
	   				
	   					alert('Quantidade de prestações para este tipo de débito deve ser 1(um)');
	   					return;
	   				}
	   				
	   				if(document.InserirGuiaPagamentoActionForm.numeroTotalPrestacao.value != 1 &&
	   					document.InserirGuiaPagamentoActionForm.qtdeDiasVencimento.value == 0){
	   				 alert('Quantidade de Dias entre os Vencimentos deve somente conter números positivos.');
	   				 return;
	   				}
	   				
					if (comparaData(form.dataVencimento.value, "<", DATA_ATUAL )){
						if (confirm(msgDataVencimento)){
							redirecionarSubmit(urlRedirect);
						}
					} else if (comparaData(form.dataVencimento.value, ">", DATA_ATUAL_60 )){
						if (confirm(msgDataVencimento60)){
							redirecionarSubmit(urlRedirect);
						}				
					} else {
						redirecionarSubmit(urlRedirect);
					}
				}
			}
		}
	}
	
	function validarObservacao() {
		var form = document.forms[0];
		
		var observacao = trim(form.observacao.value);
		
		if (form.indicadorEmitirObservacao.value == "1" && (observacao == null || observacao == "")) {
			alert("Informe Observação");
			return false;
		}
		
		return true;
	}
	
	function limparPesquisaRA() {

    	var form = document.forms[0];

		if(form.registroAtendimento.disabled == false){
	    	form.registroAtendimento.value = "";
	    	form.nomeRegistroAtendimento.value = "";
	    	if (form.codigoCliente.value != '') {
	    		form.idLocalidade.value = "";
	    	}
	    	
		}
		if(form.ordemServico.disabled){
			form.ordemServico.disabled = false;
	    	form.ordemServico.value = "";
	    	form.descricaoOrdemServico.value = "";
		}
		
  	}

	function limparPesquisaOs() {

    	var form = document.forms[0];

		if(form.registroAtendimento.disabled){
			form.registroAtendimento.disabled = false;
			limparPesquisaRA();
		}

		if(form.ordemServico.value != ""){
			form.action='exibirInserirGuiaPagamentoAction.do?limparDebitoTipo=s&limparOS=s';
		    form.submit();
		}

    	form.ordemServico.value = "";
    	form.descricaoOrdemServico.value = "";
    	
  	}
  	
  	function habilitaCampo(){
  		var form = document.forms[0];
  		
  		if((form.ordemServico.value != null && form.ordemServico.value != "") && 
  			(form.registroAtendimento.value != null && form.registroAtendimento.value != "" ) ){
  		
  			form.registroAtendimento.disabled = true;
  		}
  		
  	}
	function bloquearOrdemServico(){
 		var form = document.forms[0];
		if(form.registroAtendimento.value != ""){
			form.ordemServico.disabled = true;
		} else{
			form.ordemServico.disabled = false;
    		limparPesquisaOs();
		}
	}
	
	function bloquearRegistroAtendimento(){
	 	var form = document.forms[0];
	
		if(form.ordemServico.value != ""){
			form.registroAtendimento.disabled = true;
		}else{
			form.registroAtendimento.disabled = false;		
			limparPesquisaRA();
		}
	 
	}
	
	function adicionarItem(form){
		
		urlAdicionarItem = "exibirAdicionarGuiaPagamentoItemPopupAction.do?limparForm=1";
		
		if (form.idImovel.value.length > 0){
			urlAdicionarItem = urlAdicionarItem + "&matriculaImovel=" + form.idImovel.value;
		}
		
		abrirPopup(urlAdicionarItem, 490, 800);
	}
-->
</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:habilitaCampo();setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirInserirGuiaPagamentoAction" method="post"
	onsubmit="return validateInserirGuiaPagamentoActionForm(this);">

	<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />
	<INPUT TYPE="hidden" ID="DATA_ATUAL_60" value="${requestScope.dataAtual60}" />

	<html:hidden property="habilitaTipoDebito" />
	

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Inserir Guia de Pagamento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td>Para inserir a guia de pagamento, informe os dados
					abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoGuiaPagamentoInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td height="10" width="110">
						<strong>Matrícula do Imóvel:</strong>
					</td>
					
					<td align="left">
						<html:text property="idImovel" 
							maxlength="9"
							size="9"
							onkeyup="validaEnterImovel(event, 'exibirInserirGuiaPagamentoAction.do', 'idImovel');" />
							
						<a href="javascript:habilitarPesquisa(document.forms[0].idImovel,'exibirPesquisarImovelAction.do');"> 
							<img width="23" 
								height="21"
								src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
								border="0" /></a>
								
						<a href="javascript:limparImovel();">
							<img border="0" 
								src="<bean:message key='caminho.imagens'/>limparcampo.gif"
								style="cursor: hand;" /></a>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Dados do Imóvel:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">

								<tr>
									<td><strong>Inscri&ccedil;&atilde;o do Im&oacute;vel:</strong></td>
									<td colspan="3" align="right">
									<div align="left"><html:text property="inscricaoImovel"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="20" maxlength="20" /></div>
									</td>
								</tr>
								<tr>
									<td><strong>Nome do Cliente Usu&aacute;rio:</strong></td>

									<td colspan="3" align="right">
									<div align="left"><a href="usuario_pesquisar.htm"> </a> <span
										class="style1"> <html:text property="nomeClienteUsuario"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="50" maxlength="45" /></span></div>
									</td>
								</tr>
								<tr>
									<td><strong> Situa&ccedil;&atilde;o de &Aacute;gua: </strong></td>

									<td align="right">
									<div align="left"><html:text property="situacaoAgua"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="20" maxlength="20" /></div>
									</td>
								</tr>
								<tr>
									<td align="left">
									<div align="left"><strong> Situa&ccedil;&atilde;o de Esgoto:</strong></div>
									</td>
									<td align="left"><html:text property="situacaoEsgoto"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="20" maxlength="20" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td align="left">
						<strong>Código do Cliente:</strong>
					</td>
					
					<td>
						<html:text property="codigoCliente" 
						maxlength="9" 
						size="9"
						onkeyup="return validaEnterCliente(event, 'exibirInserirGuiaPagamentoAction.do', 'codigoCliente'); " />
						
						<a href="javascript:habilitarPesquisa(document.forms[0].codigoCliente,'exibirPesquisarClienteAction.do');">
							<img width="23" 
								height="21"
								src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
								border="0" /></a>
						
						<a href="javascript:limparCliente();">
							<img border="0"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif"
							style="cursor: hand;" /></a>
					</td>
				</tr>


				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF">
						<tr>
							<td><strong>Dados do Cliente:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td width="180"><strong>CPF/CNPJ:</strong></td>

									<td colspan="3" align="left"><html:text property="cpf"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="20" maxlength="17" /></td>
								</tr>
								<tr>
									<td width="180"><strong>Nome do Cliente:</strong></td>
									<td colspan="3" align="left">
									<div align="left"><html:text property="nomeCliente"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="50" maxlength="45" /></div>
									</td>
								</tr>
								<tr>
									<td width="180"><strong> Profiss&atilde;o: </strong></td>

									<td align="right">
									<div align="left"><html:text property="profissao"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="30" maxlength="30" /></div>
									</td>
								</tr>

								<tr>
									<td width="180"><strong> Ramo de Atividade:</strong></td>
									<td align="right">
									<div align="left"><html:text property="ramoAtividade"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="20" maxlength="20" /></div>
									</td>

								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td width="180">
						<logic:equal name="inserirGuiaPagamentoSemRa" value="false" scope="request">
							<strong>Registro de Atendimento:<font color="#FF0000">*</font></strong>
						</logic:equal>
						<logic:equal name="inserirGuiaPagamentoSemRa" value="true" scope="request">
							<strong>Registro de Atendimento:</strong>
						</logic:equal>
					</td>

					<td colspan="3" align="right">
						<div align="left">

						<html:text maxlength="9" 
							tabindex="1"
							property="registroAtendimento" 
							size="9"
							onfocus="javascript:bloquearOrdemServico();"
							onkeyup="javascript:bloquearOrdemServico();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirGuiaPagamentoAction.do?objetoConsulta=1','registroAtendimento','Registro Atendimento');"/>
							
							<a href="javascript:javascript:habilitarPesquisa(document.forms[0].registroAtendimento,'exibirPesquisarRegistroAtendimentoAction.do?objetoConsulta=1');">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar RA" /></a> 

							<logic:present name="numeroRAEncontrada" scope="request">
								<html:text property="nomeRegistroAtendimento" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="numeroRAEncontrada" scope="request">
								<html:text property="nomeRegistroAtendimento" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							<a href="javascript:limparPesquisaRA();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>
						
						</div>
					</td>
				</tr>
				<tr>
					<td width="180"><strong>Ordem de Servi&ccedil;o:</strong></td>

					<td colspan="3" align="right">
						
						<div align="left">
					
						<html:text maxlength="9" 
							tabindex="1"
							property="ordemServico" 
							size="9"
							onfocus="javascript:bloquearRegistroAtendimento();"
							onkeyup="javascript:bloquearRegistroAtendimento();"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirGuiaPagamentoAction.do?objetoConsulta=2','ordemServico','Ordem de Serviço');"/>
							
							<a href="javascript:javascript:habilitarPesquisa(document.forms[0].ordemServico,'exibirPesquisarOrdemServicoAction.do');">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar RA" /></a> 

							<logic:present name="ordemServicoEncontrada" scope="request">
								<html:text property="descricaoOrdemServico" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="ordemServicoEncontrada" scope="request">
								<html:text property="descricaoOrdemServico" 
									size="43"
									maxlength="43" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							<a href="javascript:limparPesquisaOs();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>
						</div>
					</td>
				</tr>
				<tr>
					<td width="180"><strong>Data de Vencimento:<strong><font
						color="#FF0000">*</font></strong></strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:text property="dataVencimento" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('InserirGuiaPagamentoActionForm', 'dataVencimento')"><img
						border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> dd/mm/aaaa</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Quantidade de Dias entre os Vencimentos:<strong><font color="#FF0000">*</font></strong></strong></td>
					<td align="left">
						<div align="left">
							<html:text property="qtdeDiasVencimento" size="4" maxlength="2"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><strong>Número de Prestações:<strong><font color="#FF0000">*</font></strong></strong></td>
					<td align="left">
						<div align="left">
							<html:text property="numeroTotalPrestacao" size="10" maxlength="6"/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="180"><strong>Localidade:<strong><font color="#FF0000"> </font></strong></strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:text property="localidade" size="3"
						maxlength="3" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /></div>
					</td>
				</tr>
				<tr>
		      		<td><strong>Observação:</strong></td>
			        <td>
						<html:textarea property="observacao" cols="40" rows="5" onkeyup="limitTextArea(document.forms[0].observacao, 200, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
						<strong><span id="utilizado">0</span>/<span id="limite">200</span></strong>
					</td>
			    </tr>
			    <tr>
					<td><strong>Emitir Observação?</strong></td>
					<td><strong> <html:radio property="indicadorEmitirObservacao"
						value="1" /> <strong>Sim <html:radio
						property="indicadorEmitirObservacao"
						value="2" /> Não</strong> </strong></td>
				</tr>
				
				<!-- Parte nova de debito tipo -->
				<tr>
					<td colspan="4" align="right">
					  <logic:present name="desabilitaPorOS">
						  <input type="button" class="bottonRightCol" value="Adicionar Item"
							  onclick="javascript:adicionarItem(document.forms[0]);" disabled="true">
					  </logic:present>
					  <logic:notPresent name="desabilitaPorOS">
					      <input type="button" class="bottonRightCol" value="Adicionar Item"
							  onclick="javascript:adicionarItem(document.forms[0]);">
					  </logic:notPresent> 
					</td>
				</tr>
				
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#79bbfd">
						<tr>
							<td colspan="4"><strong>Débito Tipo:</strong></td>
						</tr>
						<tr bgcolor="#99CCFF">
						    <td td width="10%"><strong>Excluir</strong></td>
							<td td width="10%"><strong>Código</strong></td>
							<td td width="60%"><strong>Descrição</strong></td>
							<td td width="20%"><strong>Valor</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center" colspan="4">
							
							<table width="100%" border="0">
							   <%--Esquema de paginação--%>
  							   <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
												export="currentPageNumber=pageNumber;pageOffset"
												maxPageItems="10" items="${sessionScope.totalRegistros}">
								  <pg:param name="pg" />
								  <pg:param name="q" />
								    <logic:present name="colecaoGuiaDebitoTipo">
									  <%int cont = 1;%>
										<logic:iterate name="colecaoGuiaDebitoTipo" id="guiaPagamentoItem" type="GuiaPagamentoItem">
										  <pg:item>
											<%cont = cont + 1;
											if (cont % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
											<%} else {
											%>
											<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="10%" align="center">
											  <logic:notPresent name="desabilitaPorOS">
												   <div>
												      <img width="14"
														height="14" border="0"
														src="<bean:message key="caminho.imagens"/>Error.gif"
														onclick="javascript:if(confirm('Confirma remoção?')){redirecionarSubmitSemUpperCase('exibirInserirGuiaPagamentoAction.do?idGuiaPagamentoItem=${guiaPagamentoItem.debitoTipo.id}');}">
													</div> 
												</logic:notPresent>
											  </td>
											 <td width="10%" align="center">
											   <div>
											      ${guiaPagamentoItem.debitoTipo.id}
												</div> 
											  </td>
											  <td width="60%">
											    <logic:notPresent name="desabilitaPorOS">
												   <div>
												     
												     <%if (guiaPagamentoItem.getContaGeral() != null){ %>
												     	<html:hidden property="observacaoPagamentoParcial" value="<%="" + Util.formatarAnoMesParaMesAno(guiaPagamentoItem.getContaGeral().getConta().getReferencia())%>"/>
												     	<%="" + Util.formatarAnoMesParaMesAno(guiaPagamentoItem.getContaGeral().getConta().getReferencia())%> - 
												     <%}%>
												     
												      ${guiaPagamentoItem.debitoTipo.descricao}
												     
												   </div> 
												</logic:notPresent>
												<logic:present name="desabilitaPorOS">
												   <a href="javascript:abrirPopup('exibirAdicionarGuiaPagamentoItemPopupAction.do?idDebitoTipo=${guiaPagamentoItem.debitoTipo.id}', 490, 800);"">
													${guiaPagamentoItem.debitoTipo.descricao}
												   </a>
												</logic:present>
											  </td>
											  <td width="20%" align="right">
											   <div>
											      <bean:write name="guiaPagamentoItem" property="valorDebito" formatKey="money.format"/>
												</div> 
											  </td>
											</tr>
										</pg:item>
									  </logic:iterate>
								  </logic:present>
								</pg:pager>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<!-- fim da parte nova -->
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">
					<div align="left"></div>
					<div align="left"><strong></strong></div>

					<div align="left"><font color="#FF0000">*</font> Campo
					Obrigat&oacute;rio</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirInserirGuiaPagamentoAction.do?menu=sim';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
					<td align="right">
					<gsan:controleAcessoBotao name="Button" value="Inserir"
							  onclick="javascript:validarForm(document.forms[0]);" url="inserirGuiaPagamentoAction.do"/>
<!--
					<input type="button" name="Button"
						class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm(document.forms[0]);" /> --></td>
				</tr>
			</table>

			<p>&nbsp;</p>








			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>

	<logic:notEqual name="InserirGuiaPagamentoActionForm"
		property="idImovel" value="">
		<script language="JavaScript">
	<!--
		controleImovel(document.forms[0]);
	-->
	</script>
	</logic:notEqual>

	<logic:notEqual name="InserirGuiaPagamentoActionForm"
		property="codigoCliente" value="">
		<script language="JavaScript">
	<!--
		controleCliente(document.forms[0]);
	-->
	</script>
	</logic:notEqual>

</html:form>
</body>
</html:html>
