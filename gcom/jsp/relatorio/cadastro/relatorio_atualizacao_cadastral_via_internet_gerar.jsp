	<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false" formName="GerarRelatorioAtualizacaoCadastralViaInternetActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
	
function validarForm(){
		
		var form = document.forms[0];

		if(validateGerarRelatorioAtualizacaoCadastralViaInternetActionForm(form)){
			submeterFormPadrao(form);
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
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
}
			
function limpar(){

  		var form = document.forms[0];
  		
		form.localidadeInicial.value = "";
		form.nomeLocalidadeInicial.value = "";
		form.localidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		
  		form.gerenciaRegional.value = "-1";
  		form.unidadeNegocio.value = "-1";
  		
  		form.periodoReferenciaInicial.value = "";
  		form.periodoReferenciaFinal.value = "";

  		form.opcaoRelatorio.value = "A";

  		form.action='exibirGerarRelatorioAtualizacaoCadastralViaInternetAction.do';
	    form.submit();
}
  	
function replicarLocalidade(){
		var formulario = document.forms[0]; 
		
		formulario.localidadeFinal.value = formulario.localidadeInicial.value;
}	 
	
function limparOrigem(tipo){
		var form = document.forms[0];
		
		if(tipo == 1){ 
			//De localidade pra baixo

			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
		 }
}
	
function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		if(tipo == 1){
			 //De localidara pra baixo

				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
			
		}
}
	
function limparBorrachaDestino(tipo){
		var form = document.forms[0];

		if(tipo == 1){
			 //De localidade pra baixo
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
			
		}
}	
	
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidadeOrigem') {
      		
      		form.localidadeInicial.value = codigoRegistro;
	  		form.nomeLocalidadeInicial.value = descricaoRegistro;
	  		
	  		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
      		
	  		form.nomeLocalidadeInicial.style.color = "#000000";
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	  		
	  		form.action = 'exibirGerarRelatorioAtualizacaoCadastralViaInternetAction.do';
	  		form.submit();
	  		
		} else if (tipoConsulta == 'localidadeDestino') {
		
      		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	  		
	  		form.action = 'exibirGerarRelatorioAtualizacaoCadastralViaInternetAction.do';
	  		form.submit();
		}
}

/* Replica Data Periodo de Referencia */	
function replicaPeriodoReferencia() {
	var form = document.forms[0];
	form.periodoReferenciaFinal.value = form.periodoReferenciaInicial.value;
}

  	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.periodoReferenciaInicial}');">
<div id="formDiv"><html:form
	action="/gerarRelatorioAtualizacaoCadastralViaInternetAction.do"
	name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
	type="gcom.gui.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
	method="post">

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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Relat&oacute;rio Atualização Cadastral via Internet</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio de atualização cadastral via internet, informe os dados
					abaixo:</td>
				</tr>
			
				<tr> 
                <td><strong>Per&iacute;odo de Refer&ecirc;ncia:<font color="#FF0000">*</font></strong></td>
                <td colspan="6"><span class="style2"><strong> 
						<html:text property="periodoReferenciaInicial" 
									name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event);replicaPeriodoReferencia();"/>
							<a href="javascript:abrirCalendarioReplicando('GerarRelatorioAtualizacaoCadastralViaInternetActionForm', 'periodoReferenciaInicial','periodoReferenciaFinal');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="5"/></a>
						a <html:text property="periodoReferenciaFinal"
									name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event)"/>
						<a href="javascript:abrirCalendario('GerarRelatorioAtualizacaoCadastralViaInternetActionForm', 'periodoReferenciaFinal');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="5"/></a>
						</strong>(dd/mm/aaaa)<strong> 
                  </strong></span></td>
                </tr>
                
				<tr>
					<td><strong>Ger&ecirc;ncia Regional:</strong></td>

					<td><strong> <html:select property="gerenciaRegional" name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
						style="width: 230px;" onchange="javascript:reloadForm();">

						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

						<logic:present name="colecaoGerenciaRegional" scope="request">
							<html:options collection="colecaoGerenciaRegional"
								labelProperty="nome" property="id" />
						</logic:present>
					</html:select> </strong></td>
				</tr>
				<tr>
					<td><strong>Unidade de Neg&oacute;cio:</strong></td>

					<td><strong> <html:select property="unidadeNegocio" name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
						style="width: 230px;">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

						<logic:present name="colecaoUnidadeNegocio" scope="request">
							<html:options collection="colecaoUnidadeNegocio"
								labelProperty="nome" property="id" />
						</logic:present>
					</html:select> </strong></td>
				</tr>
				
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeInicial" 
							name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
							size="3"
							onblur="javascript:replicarLocalidade();" 
							onkeyup="javascript:limparOrigem(1);"
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarRelatorioAtualizacaoCadastralViaInternetAction.do?objetoConsulta=1','localidadeInicial','Localidade Inicial');
							            return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeInicial);limparOrigem(1);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								

						<logic:present name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
		
						<a href="javascript:limparBorrachaOrigem(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Localidade Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeFinal" 
							name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioAtualizacaoCadastralViaInternetAction.do?objetoConsulta=3','localidadeFinal','Localidade Final');
							            return isCampoNumerico(event);"
							/>
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeFinal);limparDestino(1);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								 

						<logic:present name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm"
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparBorrachaDestino(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Opção de Relatório:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<html:radio property="opcaoRelatorio" name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm" value="A"/> <strong>Analítico</strong>&nbsp; 
						<html:radio property="opcaoRelatorio" name="GerarRelatorioAtualizacaoCadastralViaInternetActionForm" value="R"/> <strong>Resumido</strong>
					</td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left" colspan="2"><strong><font color="#FF0000">*</font></strong>

					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td height="24"><input type="button" class="bottonRightCol"
						value="Limpar" onclick="javascript:limpar();" /></td>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Gerar Relatório"
						onClick="javascript:validarForm()" /></td>
				</tr>


			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form></div>
</body>
</html:html>
