<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AlterarFaturamentoDadosActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--

    //Recebe os dados do(s) popup(s)
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

      var form = document.AlterarFaturamentoDadosActionForm;

       if (tipoConsulta == 'imovel') {
        form.idImovel.value = codigoRegistro;
        form.inscricaoImovel.value = descricaoRegistro;
        form.action = 'exibirDadosFaturamentoAction.do'
        submeterFormPadrao(form);
      }

       if (tipoConsulta == 'leituraAnormalidade') {
        form.idAnormalidade.value = codigoRegistro;
        form.nomeAnormalidade.value = descricaoRegistro;
        form.nomeAnormalidade.style.color = "#000000";
      }

    }


	function limparConsultar(){
		
		var form = document.AlterarFaturamentoDadosActionForm;
		
        form.idImovelSelecionado.value = "";
        form.tipoMedicaoSelecionada.value = "";
        form.idLocalidade.value = "";
        form.nomeLocalidade.value = "";
        form.idSetorComercial.value = "";
        form.nomeSetorComercial.value = "";
        form.leituraAnterior.value = "";
        form.dataLeituraAnterior.value = "";
        form.idLeiturista.value = "";
        form.nomeLeiturista.value = "";
        form.leituraAtual.value = ""; 
        form.dataLeituraAtual.value = "";
        form.idSituacaoLeituraAtual.value = "";
        form.nomeSituacaoLeituraAtual.value = "";
        form.idAnormalidade.value = "";
        form.nomeAnormalidade.value = "";
        form.consumoMedido.value = "";
        form.consumoInformado.value = "";
        if(document.getElementById("endereco") != null){
	        document.getElementById("endereco").innerHTML = "";
	    }
	}

    function limparImovel() {
        var form = document.AlterarFaturamentoDadosActionForm;

        form.idImovel.value = "";
        form.inscricaoImovel.value = "";
        form.idImovelSelecionado.value = "";
        form.tipoMedicaoSelecionada.value = "";
        form.idLocalidade.value = "";
        form.nomeLocalidade.value = "";
        form.idSetorComercial.value = "";
        form.nomeSetorComercial.value = "";
        form.leituraAnterior.value = "";
        form.dataLeituraAnterior.value = "";
        form.idLeiturista.value = "";
        form.nomeLeiturista.value = "";
        form.leituraAtual.value = ""; 
        form.dataLeituraAtual.value = "";
        form.idSituacaoLeituraAtual.value = "";
        form.nomeSituacaoLeituraAtual.value = "";
        form.idAnormalidade.value = "";
        form.nomeAnormalidade.value = "";
        form.consumoMedido.value = "";
        form.consumoInformado.value = "";
        
        document.getElementById("endereco").innerHTML = "";
        
		form.action = 'exibirDadosFaturamentoAction.do'
		 submeterFormPadrao(form);
        
    
                
    }

    function limparAnormalidade() {
        var form = document.AlterarFaturamentoDadosActionForm;

        form.idAnormalidade.value = "";
        form.nomeAnormalidade.value = "";
    }
    
    function validarForm(form){

      if(validateAlterarFaturamentoDadosActionForm(form)){
      		if(form.tipoMedicao.value == '-1'){
      			alert('Informe Tipo de medição.');
      		}
      
	      	var idAnormalidade = trim(form.idAnormalidade.value);
	      	
	      	if(idAnormalidade.length == 0 && form.nomeAnormalidade.value.length > 0) {
	      		alert('Anormalidade de leitura inválida. Pesquisa inexistente.');
	      	
	      	} else {
	     		if(form.dataLeituraAtual.value != '' && form.leituraAtual.value ==''){
			alert('Informe Leitura Atual');
		}else
			if (form.dataLeituraAtual.value == '' && form.leituraAtual.value !=''){
				alert('Informe Data Atual');
			}else{
				if(form.dataLeituraAnterior.value != '' && form.leituraAnterior.value ==''){
					alert('Informe Leitura Anterior');
				}else
				if (form.dataLeituraAnterior.value == '' && form.leituraAnterior.value !=''){
					alert('Informe Data Anterior');
				}else{
					submeterFormPadrao(form);
				}
			}
	      }
      }
    }

    
    function submitForm() {
    	var form = document.forms[0];
    	if(form.dataLeituraAtual.value != '' && form.leituraAtual.value ==''){
			alert('Informe Leitura Atual');
		}else
		if (form.dataLeituraAtual.value == '' && form.leituraAtual.value !=''){
			alert('Informe Data Atual');
		}else{
			if(form.dataLeituraAnterior.value != '' && form.leituraAnterior.value ==''){
				alert('Informe Leitura Anterior');
			}else
			if (form.dataLeituraAnterior.value == '' && form.leituraAnterior.value !=''){
				alert('Informe Data Anterior');
			}else{
				form.action = 'exibirDadosFaturamentoAction.do?menu=sim'
    			submeterFormPadrao(form);
			}
		}
    }
    
    function habilitaDesabilitaIndicadorConfirmacao(){
      var form = document.forms[0];
      
      if ( form.leituraAtual.value.length == 0 ){
        form.indicadorConfirmacao[1].checked = true;
      }      
   
      form.indicadorConfirmacao[0].disabled = form.leituraAtual.value.length == 0;
      form.indicadorConfirmacao[1].disabled = form.leituraAtual.value.length == 0;
    }
    
    function habilitaCamposSubmit(){
    	var form = document.forms[0];
    	
    	form.idImovel.disabled = "false";
    	form.tipoMedicao.disabled = "false";
    }
-->
</script>
<script language="JavaScript">

function validacaoData(){
	var form = document.forms[0];
	
	if(form.dataLeituraAnterior.value != "" && !verificaData(form.dataLeituraAnterior)){
		return false;
	}else if(form.dataLeituraAtual.value != "" && !verificaData(form.dataLeituraAtual)){
		return false;
	}else{
		return true;
	}
}

 function consultar(){
		var form = document.forms[0];
		
		if(form.idImovel.value == '' && form.tipoMedicao.value == '-1'){
			alert('Informe Imóvel. \n Informe Tipo de Medição.');
		}else if(form.idImovel.value == '' && form.tipoMedicao.value != '-1'){
			alert('Informe Imóvel.');
		}else if(form.idImovel.value != '' && form.tipoMedicao.value == '-1'){
			alert('Informe Tipo de Medição.');
		}else if(isNaN(form.idImovel.value)){
			alert('Imóvel deve somente conter números positivos.');
		}else{
			submitForm();
		}
		
	}
	
	function apagarImovel(valor){
		if(valor == 'idImovel'){
			document.forms[0].idImovel.value = "";
		}
        document.forms[0].inscricaoImovel.value = "";
	}
	
	function verificarBloqueioOperacao(){
	idImovel    = document.forms[0].idImovel.value;
	tipoMedicao = document.forms[0].tipoMedicao.value;
	
	if(idImovel != "" ){
	window.location = "exibirDadosFaturamentoAction.do?idImovel="+idImovel+"&tipoMedicao="+tipoMedicao+"&pesquisaImovel=S";
	}
	}

	function abrirCalendarioVerificandoBloqueio(formName, fieldName) {

		var campo = document.forms[formName].elements[fieldName]
		                                      		
		if(campo.disabled == false) {
		
		    nomeForm = formName;
		    nomeCampo = fieldName;
			centerpopup('./jsp/util/calendario.jsp','calendario',225,268);

		}

	}

	function abrirPopupVerificandoBloqueio(caminho, altura, largura, fieldName, formName) {

		var campo = document.forms[formName].elements[fieldName]
		                                      		
		if(campo.disabled == false) {
		
			abrirPopupDeNome(caminho, altura, largura, 'Pesquisar', 'yes');

		}
			
	}	
	
	
	    function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
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
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaAnormalidadeLeitura=" + tipo, altura, largura);
				}
			}
		  }
		}
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/alterarDadosFaturamentoAction" method="post">

	<INPUT TYPE="hidden" ID="CONSUMO_MEDIO_5"
		value="${requestScope.consumoMedio5}" />
	<INPUT TYPE="hidden" ID="QTDE_ECONOMIAS_30"
		value="${requestScope.qtdeEconomias30}" />

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
					<td class="parabg">Atualizar Dados do Faturamento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<!-- ============================================================================================================================== -->

			<table width="100%" border="0">
				<tr>
					<td colspan="3">Pesquisar um im&oacute;vel para atualizar os dados
					do faturamento:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a
							href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoLeituraConsumoDadosFaturamentoAlterar', 500, 700);"><span
							style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font
							color=#696969><u>Ajuda</u></font></span></td>
					</logic:notPresent>
				</tr>
			</table>
			<table width="100%" border="0">
				<logic:notPresent name="bloqueaCampos">
					<tr>
						<td width="25%"><strong>Im&oacute;vel:</strong><font
							color="#FF0000"> *</font></td>
						<td colspan="2"><html:text property="idImovel" size="9"
							maxlength="9"
							onkeypress="apagarImovel('semId');validaEnter(event, 'exibirDadosFaturamentoAction.do?pesquisaImovel=S', 'idImovel');
							return isCampoNumerico(event);"
							styleId="idImovel" onblur="verificarBloqueioOperacao();" /> <a
							href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
						<img width="23" height="21"
							src="<bean:message key='caminho.imagens'/>pesquisa.gif"
							border="0" title="Pesquisar Imóvel" /> </a> <html:text
							property="inscricaoImovel" size="22" readonly="true"
							style="background-color:#EFEFEF; border:0" /> <a
							href="javascript:apagarImovel('idImovel');"><img border="0"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif"
							style="cursor: hand;" title="Apagar Imóvel" /></a></td>
					</tr>
					<tr>
						<td><strong>Tipo de Medi&ccedil;&atilde;o:</strong><font
							color="#FF0000"> *</font></td>
						<td colspan="2"><html:select property="tipoMedicao" onchange=""
							styleId="tipoMedicao">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="medicoesTipo" labelProperty="descricao"
								property="id" />
						</html:select></td>
						<td align="right"><input type="button" name="button"
							value="Consultar" class="bottonRightCol"
							onclick="limparConsultar();consultar();"></td>
					</tr>
				</logic:notPresent>
				<logic:present name="bloqueaCampos">
					<tr>
						<td width="25%"><strong>Im&oacute;vel:</strong></td>
						<td colspan="2"><html:text property="idImovel" size="9"
							maxlength="9" disabled="true" /></td>
					</tr>
					<tr>
						<td><strong>Tipo de Medi&ccedil;&atilde;o:</strong><font
							color="#FF0000"> *</font></td>
						<td><html:select property="tipoMedicao" disabled="true">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="medicoesTipo" labelProperty="descricao"
								property="id" />
						</html:select></td>
						<td align="right"><input type="button" name="button"
							value="Consultar" class="bottonRightCol" onclick="consultar();"></td>
					</tr>
				</logic:present>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="25%"><strong>Im&oacute;vel Selecionado:</strong></td>
					<td><html:text property="idImovelSelecionado" size="9"
						maxlength="9" style="background-color:#EFEFEF; border:0"
						readonly="true" /></td>
				</tr>
				<tr>
					<td><strong>Tipo de Medi&ccedil;&atilde;o:</strong></td>
					<td><html:text property="tipoMedicaoSelecionada" readonly="true"
						style="background-color:#EFEFEF; border:0" size="25"
						maxlength="25" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Dados do Imóvel:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td><strong>Nome do Cliente Usu&aacute;rio:</strong></td>

									<td><html:text property="nomeUsuario" readonly="true"
										style="background-color:#EFEFEF; border:0" size="50"
										maxlength="45" /></td>
								</tr>
								<tr>
									<td><strong> Situa&ccedil;&atilde;o de &Aacute;gua: </strong></td>

									<td><html:text property="ligacaoAguaSituacao" readonly="true"
										style="background-color:#EFEFEF; border:0" size="20"
										maxlength="20" /></td>
								</tr>
								<tr>
									<td><strong> Situa&ccedil;&atilde;o de Esgoto:</strong></td>
									<td><html:text property="ligacaoEsgotoSituacao" readonly="true"
										style="background-color:#EFEFEF; border:0" size="20"
										maxlength="20" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" align="center" border="0" bgcolor="#90c7fc">
						<tr>
							<td align="center"><strong>Endereço</strong></td>
						</tr>
						<tr>
							<logic:present name="imovel" scope="session">
								<td bgcolor="#ffffff">
								<div align="center"><span id="endereco">
								${requestScope.enderecoCompleto} </span></div>
								</td>
							</logic:present>
							<%--<logic:notPresent name="imovel" scope="session">
									<td bgcolor="#ffffff">&nbsp;</td>
								</logic:notPresent>--%>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>




				<tr>
					<td colspan="2">
					<table width="100%" align="center" border="0" bgcolor="#90c7fc">
						<tr>
							<td align="center"><strong>Dados do Faturamento</strong></td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="left">
							<table width="100%" border="0">

								<tr>
									<td width="40%"><strong> Localidade:</strong></td>
									<td width="60%"><html:text property="idLocalidade" size="4"
										maxlength="4" readonly="true"
										style="background-color:#EFEFEF; border:0" /> <html:text
										property="nomeLocalidade" readonly="true"
										style="background-color:#EFEFEF; border:0" size="30"
										maxlength="30" /></td>
								</tr>
								<tr>
									<td><strong> Setor Comercial:</strong></td>
									<td><html:text property="idSetorComercial" size="4"
										maxlength="4" readonly="true"
										style="background-color:#EFEFEF; border:0" /> <html:text
										property="nomeSetorComercial" readonly="true"
										style="background-color:#EFEFEF; border:0" size="30"
										maxlength="30" /></td>
								</tr>
								<tr>
									<td><strong> Leitura Anterior:</strong></td>
									<td><logic:equal name="bloquearAtualizar" value="sim"
										scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<html:text property="leituraAnterior" size="8" maxlength="8"
												readonly="true" disabled="true"
												onkeypress="return isCampoNumerico(event);" />
										</logic:present>
										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:empty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="leituraAnterior" size="8" maxlength="8"
													readonly="true" disabled="true"
													onkeypress="return isCampoNumerico(event);" />
											</logic:empty>
											<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<logic:present name="bloquearAtualizarleitura"
													scope="session">
													<logic:equal name="bloquearAtualizarleitura" value="sim"
														scope="session">
														<html:text property="leituraAnterior" disabled="true"
															size="8" maxlength="8"
															onkeypress="return isCampoNumerico(event);" />
													</logic:equal>
													<logic:equal name="bloquearAtualizarleitura" value="nao"
														scope="session">
														<html:text property="leituraAnterior" disabled="false"
															size="8" maxlength="8"
															onkeypress="return isCampoNumerico(event);" />
													</logic:equal>
												</logic:present>
												<logic:notPresent name="bloquearAtualizarleitura"
													scope="session">
													<html:text property="leituraAnterior" disabled="true"
														size="8" maxlength="8"
														onkeypress="return isCampoNumerico(event);" />
												</logic:notPresent>
											</logic:notEmpty>
										</logic:notPresent>
									</logic:equal> <logic:equal name="bloquearAtualizar"
										value="nao" scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<html:text property="leituraAnterior" size="8" maxlength="8"
												readonly="true" onkeypress="return isCampoNumerico(event);" />
										</logic:present>
										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:empty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="leituraAnterior" size="8" maxlength="8"
													readonly="true" onkeypress="return isCampoNumerico(event);" />
											</logic:empty>
											<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="leituraAnterior" size="8" maxlength="8"
													onkeypress="return isCampoNumerico(event);" />
											</logic:notEmpty>
										</logic:notPresent>
									</logic:equal></td>
								</tr>
								<tr>
									<td><strong> Data Leitura Anterior:</strong></td>
									<td><logic:equal name="bloquearAtualizar" value="sim"
										scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<html:text property="dataLeituraAnterior" size="10"
												maxlength="10" readonly="true" disabled="true"
												onkeypress="return isCampoNumerico(event);" />
											<img border="0"
												src="<bean:message key='caminho.imagens'/>calendario.gif"
												width="20" border="0" align="top" alt="Exibir Calendário" />
										</logic:present>
										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:empty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="dataLeituraAnterior" size="10"
													maxlength="10" readonly="true" disabled="true"
													onkeypress="return isCampoNumerico(event);" />
												<img border="0"
													src="<bean:message key='caminho.imagens'/>calendario.gif"
													width="20" border="0" align="top" alt="Exibir Calendário" />
											</logic:empty>
											<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<logic:present name="bloquearAtualizarleitura"
													scope="session">
													<logic:equal name="bloquearAtualizarleitura" value="sim"
														scope="session">
														<html:text property="dataLeituraAnterior" size="10"
															maxlength="10" disabled="true"
															onkeyup="mascaraData(this, event);"
															onkeypress="return isCampoNumerico(event);" />
														<a
															href="javascript:abrirCalendarioVerificandoBloqueio('AlterarFaturamentoDadosActionForm', 'dataLeituraAnterior')">
														<img border="0"
															src="<bean:message key='caminho.imagens'/>calendario.gif"
															width="20" border="0" align="top" alt="Exibir Calendário" /></a>
													</logic:equal>
													<logic:equal name="bloquearAtualizarleitura" value="nao"
														scope="session">
														<html:text property="dataLeituraAnterior" size="10"
															maxlength="10" disabled="false"
															onkeyup="mascaraData(this, event);"
															onkeypress="return isCampoNumerico(event);" />
														<a
															href="javascript:abrirCalendarioVerificandoBloqueio('AlterarFaturamentoDadosActionForm', 'dataLeituraAnterior')">
														<img border="0"
															src="<bean:message key='caminho.imagens'/>calendario.gif"
															width="20" border="0" align="top" alt="Exibir Calendário" /></a>
													</logic:equal>
												</logic:present>
												<logic:notPresent name="bloquearAtualizarleitura"
													scope="session">
													<html:text property="dataLeituraAnterior" size="10"
														maxlength="10" disabled="true"
														onkeyup="mascaraData(this, event);"
														onkeypress="return isCampoNumerico(event);" />
													<a
														href="javascript:abrirCalendarioVerificandoBloqueio('AlterarFaturamentoDadosActionForm', 'dataLeituraAnterior')">
													<img border="0"
														src="<bean:message key='caminho.imagens'/>calendario.gif"
														width="20" border="0" align="top" alt="Exibir Calendário" /></a>
												</logic:notPresent>
											</logic:notEmpty>
										</logic:notPresent> dd/mm/aaaa
									</logic:equal> <logic:equal name="bloquearAtualizar"
										value="nao" scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<html:text property="dataLeituraAnterior" size="10"
												maxlength="10" readonly="true"
												onkeypress="return isCampoNumerico(event);" />
											<img border="0"
												src="<bean:message key='caminho.imagens'/>calendario.gif"
												width="20" border="0" align="top" alt="Exibir Calendário" />
										</logic:present>
										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:empty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="dataLeituraAnterior" size="10"
													maxlength="10" readonly="true"
													onkeypress="return isCampoNumerico(event);" />
												<img border="0"
													src="<bean:message key='caminho.imagens'/>calendario.gif"
													width="20" border="0" align="top" alt="Exibir Calendário" />
											</logic:empty>
											<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="dataLeituraAnterior" size="10"
													maxlength="10" onkeyup="mascaraData(this, event);"
													onkeypress="return isCampoNumerico(event);" />
												<a
													href="javascript:abrirCalendarioVerificandoBloqueio('AlterarFaturamentoDadosActionForm', 'dataLeituraAnterior')">
												<img border="0"
													src="<bean:message key='caminho.imagens'/>calendario.gif"
													width="20" border="0" align="top" alt="Exibir Calendário" /></a>
											</logic:notEmpty>
										</logic:notPresent> dd/mm/aaaa
									</logic:equal></td>
								</tr>
								<tr>
									<td><strong> Leiturista:<font color="#FF0000"> </font></strong></td>
									<td><html:text property="idLeiturista" size="9" maxlength="9"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									<html:text property="nomeLeiturista" readonly="true"
										style="background-color:#EFEFEF; border:0" size="35"
										maxlength="40" /></td>
								</tr>
								<tr>
									<td><strong> Leitura Atual Informada:</strong></td>
									<td><logic:equal name="bloquearAtualizar" value="sim"
										scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<html:text disabled="true" property="leituraAtual" size="8"
												maxlength="8" readonly="true"
												onkeypress="return isCampoNumerico(event);" />
										</logic:present>
										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:empty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text disabled="true" property="leituraAtual" size="8"
													maxlength="8" readonly="true"
													onkeypress="return isCampoNumerico(event);" />
											</logic:empty>
											<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text disabled="true" property="leituraAtual" size="8"
													maxlength="8"
													onkeyup="habilitaDesabilitaIndicadorConfirmacao();"
													onkeypress="return isCampoNumerico(event);" />
											</logic:notEmpty>
										</logic:notPresent>
									</logic:equal> <logic:equal name="bloquearAtualizar"
										value="nao" scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<html:text property="leituraAtual" size="8" maxlength="8"
												readonly="true" onkeypress="return isCampoNumerico(event);" />
										</logic:present>
										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:empty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="leituraAtual" size="8" maxlength="8"
													readonly="true" onkeypress="return isCampoNumerico(event);" />
											</logic:empty>
											<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="leituraAtual" size="8" maxlength="8"
													onkeyup="habilitaDesabilitaIndicadorConfirmacao();"
													onkeypress="return isCampoNumerico(event);" />
											</logic:notEmpty>
										</logic:notPresent>
									</logic:equal></td>
								</tr>
								<tr>
									<td><strong> Data Leitura Atual Informada:</strong></td>
									<td><logic:equal name="bloquearAtualizar" value="sim"
										scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<html:text disabled="true" property="dataLeituraAtual"
												size="10" maxlength="10" readonly="true"
												onkeypress="return isCampoNumerico(event);" />
											<img border="0"
												src="<bean:message key='caminho.imagens'/>calendario.gif"
												width="20" border="0" align="middle" alt="Exibir Calendário" />
										</logic:present>
										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:empty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text disabled="true" property="dataLeituraAtual"
													size="10" maxlength="10" readonly="true"
													onkeypress="return isCampoNumerico(event);" />
												<img border="0"
													src="<bean:message key='caminho.imagens'/>calendario.gif"
													width="20" border="0" align="top" alt="Exibir Calendário" />
											</logic:empty>
											<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text disabled="true" property="dataLeituraAtual"
													size="10" maxlength="10"
													onkeyup="mascaraData(this, event);"
													onkeypress="return isCampoNumerico(event);" />
												<a
													href="javascript:abrirCalendarioVerificandoBloqueio('AlterarFaturamentoDadosActionForm', 'dataLeituraAtual')">
												<img border="0"
													src="<bean:message key='caminho.imagens'/>calendario.gif"
													width="20" border="0" align="top" alt="Exibir Calendário" /></a>
											</logic:notEmpty>
										</logic:notPresent> dd/mm/aaaa
								</logic:equal> <logic:equal name="bloquearAtualizar" value="nao"
										scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<html:text property="dataLeituraAtual" size="10"
												maxlength="10" readonly="true"
												onkeypress="return isCampoNumerico(event);" />
											<img border="0"
												src="<bean:message key='caminho.imagens'/>calendario.gif"
												width="20" border="0" align="middle" alt="Exibir Calendário" />
										</logic:present>
										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:empty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="dataLeituraAtual" size="10"
													maxlength="10" readonly="true"
													onkeypress="return isCampoNumerico(event);" />
												<img border="0"
													src="<bean:message key='caminho.imagens'/>calendario.gif"
													width="20" border="0" align="top" alt="Exibir Calendário" />
											</logic:empty>
											<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="dataLeituraAtual" size="10"
													maxlength="10" onkeyup="mascaraData(this, event);"
													onkeypress="return isCampoNumerico(event);" />
												<a
													href="javascript:abrirCalendarioVerificandoBloqueio('AlterarFaturamentoDadosActionForm', 'dataLeituraAtual')">
												<img border="0"
													src="<bean:message key='caminho.imagens'/>calendario.gif"
													width="20" border="0" align="top" alt="Exibir Calendário" /></a>
											</logic:notEmpty>
										</logic:notPresent> dd/mm/aaaa
								</logic:equal></td>
								</tr>

								<tr>
									<td><strong> Indicador de Confirma&ccedil;&atilde;o Leitura
									Atual Informada:</strong></td>
									<td align="right"><logic:equal name="bloquearAtualizar"
										value="sim" scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<div align="left"><html:radio property="indicadorConfirmacao"
												value="<%=ConstantesSistema.CONFIRMADA%>" disabled="true" />
											<strong>Confirmada <html:radio
												property="indicadorConfirmacao"
												value="<%=ConstantesSistema.NAO_CONFIRMADA%>"
												disabled="true" /> N&atilde;o Confirmada </strong></div>
										</logic:present>

										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:present name="dataLeituraAtualNaoInformada"
												scope="request">
												<div align="left"><html:radio
													property="indicadorConfirmacao"
													value="<%=ConstantesSistema.CONFIRMADA%>" disabled="true" />
												<strong>Confirmada <html:radio
													property="indicadorConfirmacao"
													value="<%=ConstantesSistema.NAO_CONFIRMADA%>"
													disabled="true" /> N&atilde;o Confirmada </strong></div>
											</logic:present>

											<logic:notPresent name="dataLeituraAtualNaoInformada"
												scope="request">
												<div align="left"><html:radio
													property="indicadorConfirmacao"
													value="<%=ConstantesSistema.CONFIRMADA%>" disabled="true" />
												<strong>Confirmada <html:radio
													property="indicadorConfirmacao"
													value="<%=ConstantesSistema.NAO_CONFIRMADA%>"
													disabled="true" /> N&atilde;o Confirmada </strong></div>
											</logic:notPresent>
										</logic:notPresent>
									</logic:equal> <logic:equal name="bloquearAtualizar"
										value="nao" scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<div align="left"><html:radio property="indicadorConfirmacao"
												value="<%=ConstantesSistema.CONFIRMADA%>" disabled="true" />
											<strong>Confirmada <html:radio
												property="indicadorConfirmacao"
												value="<%=ConstantesSistema.NAO_CONFIRMADA%>"
												disabled="true" /> N&atilde;o Confirmada </strong></div>
										</logic:present>

										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:present name="dataLeituraAtualNaoInformada"
												scope="request">
												<div align="left"><html:radio
													property="indicadorConfirmacao"
													value="<%=ConstantesSistema.CONFIRMADA%>" disabled="true" />
												<strong>Confirmada <html:radio
													property="indicadorConfirmacao"
													value="<%=ConstantesSistema.NAO_CONFIRMADA%>"
													disabled="true" /> N&atilde;o Confirmada </strong></div>
											</logic:present>

											<logic:notPresent name="dataLeituraAtualNaoInformada"
												scope="request">
												<div align="left"><html:radio
													property="indicadorConfirmacao"
													value="<%=ConstantesSistema.CONFIRMADA%>" /> <strong>Confirmada
												<html:radio property="indicadorConfirmacao"
													value="<%=ConstantesSistema.NAO_CONFIRMADA%>" />
												N&atilde;o Confirmada </strong></div>
											</logic:notPresent>
										</logic:notPresent>
									</logic:equal></td>
								</tr>
								<tr>
									<td><strong> Situa&ccedil;&atilde;o de Leitura Atual:</strong></td>
									<td><html:text property="idSituacaoLeituraAtual" size="2"
										maxlength="2" readonly="true"
										style="background-color:#EFEFEF; border:0" /> <html:text
										property="nomeSituacaoLeituraAtual" readonly="true"
										style="background-color:#EFEFEF; border:0" size="30"
										maxlength="30" /></td>
								</tr>
								<tr>
									<td><strong> Anormalidade de Leitura Informada:</strong></td>
									<td><logic:equal name="bloquearAtualizar" value="sim"
										scope="session">

										<logic:empty name="AlterarFaturamentoDadosActionForm"
											property="idImovelSelecionado">
											<html:text disabled="true" property="idAnormalidade" size="2"
												maxlength="2" readonly="true" />
											<img width="23" height="21"
												src="<bean:message key='caminho.imagens'/>pesquisa.gif"
												border="0" title="Pesquisar Anormalidade" />
										</logic:empty>

										<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
											property="idImovelSelecionado">
											<html:text disabled="true" property="idAnormalidade" size="2"
												maxlength="2"
												onkeypress="validaEnter(event, 'exibirDadosFaturamentoAction.do', 'idAnormalidade')" />
											<a
												href="javascript:abrirPopupVerificandoBloqueio('exibirPesquisarLeituraAnormalidadeAction.do', 400, 800, 'idAnormalidade', 'AlterarFaturamentoDadosActionForm');">
											<img width="23" height="21"
												src="<bean:message key='caminho.imagens'/>pesquisa.gif"
												border="0" title="Pesquisar Anormalidade" /> </a>
										</logic:notEmpty>
									</logic:equal> <logic:equal name="bloquearAtualizar"
										value="nao" scope="session">
										<logic:empty name="AlterarFaturamentoDadosActionForm"
											property="idImovelSelecionado">
											<html:text property="idAnormalidade" size="2" maxlength="2"
												readonly="true" />
											<img width="23" height="21"
												src="<bean:message key='caminho.imagens'/>pesquisa.gif"
												border="0" title="Pesquisar Anormalidade" />
										</logic:empty>
										<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
											property="idImovelSelecionado">
											<html:text property="idAnormalidade" size="2"
												disabled="false" maxlength="2"
												onkeypress="validaEnter(event, 'exibirDadosFaturamentoAction.do', 'idAnormalidade')" />
											<a
												href="javascript:chamarPopup('exibirPesquisarLeituraAnormalidadeAction.do', 'idAnormalidade', null, null, 600, 500, '', AlterarFaturamentoDadosActionForm);">
											<img width="23" height="21"
												src="<bean:message key='caminho.imagens'/>pesquisa.gif"
												border="0" title="Pesquisar Anormalidade" /> </a>
										</logic:notEmpty>
									</logic:equal> <logic:present
										name="idAnormalidadeNaoEncontrada" scope="request">
										<html:text property="nomeAnormalidade" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000;"
											size="35" maxlength="43" />
									</logic:present> <logic:notPresent
										name="idAnormalidadeNaoEncontrada" scope="request">
										<html:text property="nomeAnormalidade" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000;"
											size="35" maxlength="43" />
									</logic:notPresent> <img border="0"
										src="<bean:message key='caminho.imagens'/>limparcampo.gif"
										onclick="limparAnormalidade();" title="Apagar Anormalidade"
										style="cursor: hand;" /></td>
								</tr>
								<tr>
									<td><strong> Consumo Medido do M&ecirc;s:<font color="#FF0000">
									</font></strong></td>
									<td><html:text property="consumoMedido" size="8" maxlength="8"
										readonly="true" style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td><strong> Consumo Informado do M&ecirc;s:</strong></td>
									<td><logic:equal name="bloquearAtualizar" value="sim"
										scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<html:text disabled="true" property="consumoInformado"
												size="8" maxlength="8" readonly="true"
												onkeypress="return isCampoNumerico(event);" />
										</logic:present>
										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:empty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text disabled="true" property="consumoInformado"
													size="8" maxlength="8" readonly="true"
													onkeypress="return isCampoNumerico(event);" />
											</logic:empty>
											<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text disabled="true" property="consumoInformado"
													size="8" maxlength="8"
													onkeypress="return isCampoNumerico(event);" />
											</logic:notEmpty>
										</logic:notPresent>
									</logic:equal> <logic:equal name="bloquearAtualizar"
										value="nao" scope="session">
										<logic:present name="hidrometroInexistente" scope="request">
											<html:text property="consumoInformado" size="8" maxlength="8"
												readonly="true" onkeypress="return isCampoNumerico(event);" />
										</logic:present>
										<logic:notPresent name="hidrometroInexistente" scope="request">
											<logic:empty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="consumoInformado" size="8"
													maxlength="8" readonly="true"
													onkeypress="return isCampoNumerico(event);" />
											</logic:empty>
											<logic:notEmpty name="AlterarFaturamentoDadosActionForm"
												property="idImovelSelecionado">
												<html:text property="consumoInformado" size="8"
													maxlength="8" onkeypress="return isCampoNumerico(event);" />
											</logic:notEmpty>
										</logic:notPresent>
									</logic:equal></td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>





				<tr>
					<td class="style1">
					<p>&nbsp;</p>
					</td>
					<td class="style1">
					<table>
						<tr>
							<td width="500" align="right">
							<div align="left"><font color="#FF0000">*</font> Campos
							obrigatórios</div>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td align="left"><logic:present
								name="habilitaBotaoAlterarDadosFaturamento" scope="session">

								<input type="button" value="Voltar"
									onclick="javascript:redirecionarSubmit('/gsan/exibirDadosAnaliseMedicaoConsumoResumoAction.do');"
									class="bottonRightCol" />

								<input type="button" value="Desfazer"
									onclick="window.location.href='/gsan/exibirDadosFaturamentoAction.do?idRegistroAtualizacao=${idImovel}&medicaoTipo=${tipoMedicao}'"
									class="bottonRightCol" />

							</logic:present> <logic:notPresent
								name="habilitaBotaoAlterarDadosFaturamento" scope="session">

								<logic:present name="voltar" scope="request">
									<input type="button" value="Voltar"
										onclick="javascript:redirecionarSubmit('/gsan/exibirDadosAnaliseMedicaoConsumoAction.do?idRegistroAtualizacao=${sessionScope.idImovelSessao}');"
										class="bottonRightCol" />
								</logic:present>

								<logic:present name="idImovelSessao" scope="session">
									<input type="button" value="Desfazer"
										onclick="javascript:redirecionarSubmit('/gsan/exibirDadosFaturamentoAction.do?idImovel=${sessionScope.idImovelSessao}&idTipoMedicao=${sessionScope.idTipoMedicaoSessao}&voltar=S&bloquearCampos=S&desfazer=1');"
										class="bottonRightCol" />
								</logic:present>

								<logic:notPresent name="idImovelSessao" scope="session">
									<input type="button" value="Desfazer"
										onclick="window.location.href='/gsan/exibirDadosFaturamentoAction.do?menu=sim';"
										class="bottonRightCol" />
								</logic:notPresent>

								<input name="Button" type="button" class="bottonRightCol"
									value="Cancelar" align="left"
									onclick="window.location.href='/gsan/telaPrincipal.do'">

							</logic:notPresent></td>

							<logic:present name="habilitaBotaoAlterarDadosFaturamento"
								scope="session">

								<td height="26" colspan="" align="right" width="50%"><input
									name="Atualizar" type="button" class="bottonRightCol"
									id="Atualizar" value="Atualizar"
									onClick="validarForm(document.forms[0])" /></td>

							</logic:present>

							<logic:notPresent name="habilitaBotaoAlterarDadosFaturamento"
								scope="session">

								<logic:equal name="bloquearAtualizar" value="sim"
									scope="session">
									<td height="26" colspan="" align="right" width="50%"><logic:present
										name="bloquearAtualizarleitura" scope="session">
										<logic:equal name="bloquearAtualizarleitura" value="sim"
											scope="session">
											<input disabled="disabled" name="Atualizar" type="button"
												class="bottonRightCol" id="Atualizar" value="Atualizar"
												onClick="validarForm(document.forms[0])" />
										</logic:equal>
										<logic:equal name="bloquearAtualizarleitura" value="nao"
											scope="session">
											<input name="Atualizar" type="button" class="bottonRightCol"
												id="Atualizar" value="Atualizar"
												onClick="validarForm(document.forms[0])" />
										</logic:equal>
									</logic:present> <logic:notPresent
										name="bloquearAtualizarleitura" scope="session">
										<input disabled="disabled" name="Atualizar" type="button"
											class="bottonRightCol" id="Atualizar" value="Atualizar"
											onClick="validarForm(document.forms[0])" />
									</logic:notPresent></td>
								</logic:equal>

								<logic:equal name="bloquearAtualizar" value="nao"
									scope="session">
									<td height="26" colspan="" align="right" width="50%"><input
										name="Atualizar" type="button" class="bottonRightCol"
										id="Atualizar" value="Atualizar"
										onClick="document.forms[0].action ='/gsan/alterarDadosFaturamentoAction.do';validarForm(document.forms[0])" />
									</td>
								</logic:equal>

							</logic:notPresent>
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
</body>
</html:form>
</html:html>
