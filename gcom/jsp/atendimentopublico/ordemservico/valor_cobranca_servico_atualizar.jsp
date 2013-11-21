<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%-- Carrega validações do validator --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarValorCobrancaServicoActionForm" />
<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<%-- Novos Javascripts --%>
<script language="JavaScript">
	
	/* Valida Form */
	function validaForm(capacidadeObrigatorio,valorObrigatorio) {
		var form = document.forms[0];

		/* Validate */
		if (validateAtualizarValorCobrancaServicoActionForm(form)) {
			if (validaRadioButton()) {
				if (validaIndicadorMedido()) {
					if(validaIndicativoEconomias()){
						if(validaObrigatorios(capacidadeObrigatorio,valorObrigatorio)){
							if (form.quantidadeEconomiasInicial.value != '' && form.quantidadeEconomiasFinal.value != '') {													
								verificarAtributosInicialFinal(form.quantidadeEconomiasInicial, form.quantidadeEconomiasFinal, "Economias")
							}
							if (form.dataVigenciaInicial.value != '' && form.dataVigenciaFinal.value != ''){
									if (comparaData(form.dataVigenciaInicial.value, "<", form.dataVigenciaFinal.value )){
				  						submeterFormPadrao(form);
				  					}else{
				  						alert('Data final do período de vencimento é anterior à data inicial do período de vencimento');			
				  					}
				  			}else{
				  				submeterFormPadrao(form);
				  			}
						}
			  		}
				}
			}	
		}	
	}

	function validaObrigatorios(capacidadeObrigatorio,valorObrigatorio){
		var form = document.forms[0];

		if(capacidadeObrigatorio == 'sim'){	
			if(form.capacidadeHidrometro.value == '-1'){
				alert('Informe Capacidade do Hidrômetro.')
				return false;
			}
		}
		if(valorObrigatorio == 'sim'){	
			if(form.valorServico.value == ''){
				alert('Informe Valor do Serviço.')
				return false;
			}
		}

		return true;	

	}
	
	/* Valida Indicador de Medido */
	function validaIndicadorMedido() {
		var form = document.forms[0];
		
		if (form.indicadorMedido[0].checked && 
				form.capacidadeHidrometro.selectedIndex == '-1') {
			alert('Informe a Capacidade do Hidrômetro');
			return false;
		}
		return true;
	}
	
	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];

		form.indicadorMedido[0].checked = false;
		form.indicadorMedido[1].checked = false;
		form.capacidadeHidrometro.selectedIndex = '-1';
		form.perfilImovel.selectedIndex = '-1';
		form.valorServico.value = "";
		limparTipoServico();
	}
	
	/* Limpa Tipo Perfil Servico do Form */	 
	function limparTipoServico() {
		var form = document.forms[0];
		form.tipoServico.value = "";
		form.nomeTipoServico.value = "";
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
	
	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.AtualizarValorCobrancaServicoActionForm

      	if (tipoConsulta == 'tipoServico') {
        	form.tipoServico.value = codigoRegistro;
        	form.nomeTipoServico.value = descricaoRegistro;
        	form.nomeTipoServico.style.color = "#000000";
      	}
    }
    
    	
	/* Valida Indicativo de Economias */
	function validaIndicativoEconomias() {
		var form = document.forms[0];
		
		if (form.indicativoTipoSevicoEconomias[0].checked ) {
			if(form.quantidadeEconomiasInicial.value == "" || form.quantidadeEconomiasFinal.value == ""){
				alert('Informe a quantidade de Economias');
				return false;
			}
		}
		return true;
	}
    
	/* Recarrega tela de exibição */
	function habilitarCapacidadeHidrometro() {
		var form = document.forms[0];
		
		if (form.indicadorMedido[0].checked) {
			form.capacidadeHidrometro.disabled = false;
		} else {
			form.capacidadeHidrometro.value = '-1';
			form.capacidadeHidrometro.disabled = true;
		}
	}
	
	/* Valida Radio */
  	function validaRadioButton() {
		var form = document.forms[0];
		if (!form.indicadorMedido[0].checked
				&& !form.indicadorMedido[1].checked) {
			alert("Informe Indicador de Medido.");		
			return false;
		}		
		return true;
   	}	
 		
 	function habilitaEconomia() {
   		var form = document.forms[0];
   		
   		form.quantidadeEconomiasInicial.disabled = false;
   		form.quantidadeEconomiasFinal.disabled = false;
 	}
 	
 	function desabilitaEconomia() {
 		var form = document.forms[0];
   		
   		form.quantidadeEconomiasInicial.value = "";
   		form.quantidadeEconomiasFinal.value = "";   		
   		form.quantidadeEconomiasInicial.disabled = true;
   		form.quantidadeEconomiasFinal.disabled = true;
 	}

 	function verificarEconomia(){
 		var form = document.forms[0];

		
 		if(form.indicativoTipoSevicoEconomias.value = 2){

 			desabilitaEconomia();

 	 	}else if(form.indicativoTipoSevicoEconomias.value = 1){

 	 		habilitaEconomia();
			
 	 	}	
		
 	}
   	
   	function reload(){
   		var form = document.forms[0];
   		
   		form.action = "/gsan/exibirAtualizarValorCobrancaServicoAction.do";	
   		form.submit();
   	}

   	function verificarObrigatoriedade(capacidadeObrigatoria,valorObrigatorio){
	   	
   		if(capacidadeObrigatoria == 'sim'){
   			document.getElementById("obrigaCapacidade").innerHTML="*";			
   	   	}
   		if(valorObrigatorio == 'sim'){
   			document.getElementById("obrigaValor").innerHTML="*";			
   	   	}    	   	
   	}

   	function validarExibicaoValorServico(){

 		var form = document.forms[0];
		
		if(form.indicadorGeracaoDebito[0].checked){
			form.valorServico.disabled = false;
		}else if(form.indicadorGeracaoDebito[1].checked){
	   		form.valorServico.value = "";   		
	   		form.valorServico.disabled = true;
		}
 	}
   		
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="habilitarCapacidadeHidrometro();
	validarExibicaoValorServico();
	verificarObrigatoriedade('${sessionScope.capacidadeObrigatoria}','${sessionScope.valorObrigatorio}');">

<html:form action="/atualizarValorCobrancaServicoAction.do"
	name="AtualizarValorCobrancaServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarValorCobrancaServicoActionForm"
	method="post" focus="tipoServico">

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
			<td width="615" valign="top" class="centercoltext">
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
					<td class="parabg">Atualizar Valor Cobrança do Serviço</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<!--Inicio da Tabela Ligação de Esgoto -->
			<table width="100%" border="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="3">Para atualizar um valor de cobrança,
							informe os dados abaixo:</td>
						</tr>
						<tr>
							<td height="10" colspan="3">
							<hr>
							</td>
						</tr>
						<!-- <tr>
							<td><strong>Código:</strong></td>
							<td><html:hidden property="idServicoCobrancaValor" /> <bean:write
								name="AtualizarValorCobrancaServicoActionForm"
								property="idServicoCobrancaValor" /></td>
						</tr> -->


						<tr>
							<td height="10"><strong> Tipo do Serviço: </strong></td>
							<td width="330" colspan="2"><html:text property="tipoServico" readonly="true" size="5"
								maxlength="4"
								style="background-color:#EFEFEF; border:0; color:0000" /> <html:text
								property="nomeTipoServico" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="50" /></td>
						</tr>
						
						<tr>
							<td>
								<strong>Categoria:<font color="#FF0000"></font></strong>
							</td>
		
							<td>
								<strong> 
								<html:select property="idCategoria" style="width: 230px;" tabindex="2" onchange="javascript:reload();">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
							
									<logic:present name="colecaoCategoria"  scope="request">
												   <html:options collection="colecaoCategoria"
																 labelProperty="descricao" 
																 property="id" />
									</logic:present>
								</html:select> 														
								</strong>
							</td>
						</tr>

						<tr>
							<td>
								<strong>Subcategoria:<font color="#FF0000"></font></strong>
							</td>
		
							<td>
								<strong> 
								<html:select property="idSubCategoria" style="width: 230px;" tabindex="2">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
							
									<logic:present name="colecaoSubCategoria" 
												   scope="request">
												   <html:options collection="colecaoSubCategoria"
																 labelProperty="descricao" 
																 property="id" 
												   />
									</logic:present>
								</html:select> 														
								</strong>
							</td>
						</tr>
						
						<tr>
							<td><strong> Perfil do Im&oacute;vel: </strong></td>
							<td colspan="3" align="right" width="330">
							<div align="left"><html:select property="perfilImovel"
								style="width: 230px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoImovelPerfil" scope="session">
									<html:options collection="colecaoImovelPerfil"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></div>
							</td>
						</tr>
						<tr>
							<td><strong> Indicador de Medido: <font color="#FF0000">*</font>
							</strong></td>
							<td colspan="3" align="right" width="330">
							<div align="left"><label> <html:radio property="indicadorMedido"
								value="1" onclick="javascript:habilitarCapacidadeHidrometro();" onchange="javascript:reload();"/>
							<strong>Sim</strong> </label> <label> <html:radio property="indicadorMedido"
								value="2" onclick="javascript:habilitarCapacidadeHidrometro();" onchange="javascript:reload();"/>
							<strong>Não</strong> </label></div>
							</td>
						</tr>
						<tr>
							<td width="192"><strong> Capacidade do Hidrômetro:<font id="obrigaCapacidade" color="#FF0000"></font></strong></td>
							<td colspan="3" align="right">
							<div align="left"><html:select property="capacidadeHidrometro"
								style="width: 230px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoHidrometroCapacidade"
									scope="session">
									<html:options collection="colecaoHidrometroCapacidade"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select></div>
							</td>
						</tr>					
						<tr>
							<td><strong><span class="style2">Indicador de Geração de Débito:<font color="#FF0000">*</font></span></strong></td>
							<td align="left" colspan="2">
								<label> 
									<html:radio	property="indicadorGeracaoDebito" value="1" onchange="javascript:validarExibicaoValorServico();javascript:reload();"/><strong>Sim</strong>
								</label>
								<label>
									<html:radio property="indicadorGeracaoDebito" value="2" onchange="javascript:validarExibicaoValorServico();javascript:reload();"/> <strong>Não</strong>
								</label>
							</td>
						</tr>
						<tr>
							<td><strong> Valor do Serviço:<font id="obrigaValor" color="#FF0000"></font></strong>
							</td>
							<td colspan="3" align="right" width="330">
							<div align="left"><html:text property="valorServico" size="14"
								maxlength="14"
								onkeyup="javascript:formataValorMonetario(this, 11)"
								style="text-align:right;" /></div>
							</td>
						</tr>
						
						<!-- Vigência do valor do Serviço -->
						<tr> 
				        	<td><strong>Vigência do valor do Serviço:<font color="#FF0000">*</font> </strong></td>
				        	<td valign="middle" colspan="3">
					            <html:text maxlength="10" 
					            		   property="dataVigenciaInicial" 
					            		   size="10" 
					            		   onkeypress="return isCampoNumerico(event);"
					            		   onkeyup="javascript:mascaraData(this,event);"/>
					            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
					            	 onclick="javascript:abrirCalendarioReplicando('AtualizarValorCobrancaServicoActionForm', 'dataVigenciaInicial', 'dataVigenciaFinal');" 
					            	 width="20" border="0" 
					            	 align="middle" alt="Exibir Calendário" /><strong> a </strong> 
					            <html:text maxlength="10" 
					            		   property="dataVigenciaFinal" 
					            		   size="10" 
					            		   onkeypress="return isCampoNumerico(event);"
					            		   onkeyup="javascript:mascaraData(this,event);"/>
					            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
					            	 onclick="javascript:abrirCalendario('AtualizarValorCobrancaServicoActionForm', 'dataVigenciaFinal')"
					            	 width="20" border="0" 
					            	 align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
				        	</td>
				        </tr>
				        
				        <!-- Indicativo de Tipo Serviço por Economias -->
						<tr>
							<td><strong><span class="style2">Indicativo do Tipo de Serviço por Economias:<font color="#FF0000">*</font></span></strong></td>
							<td align="left" colspan="2">
								<label> 
									<html:radio	property="indicativoTipoSevicoEconomias" value="1" onchange="javascript:habilitaEconomia();"/><strong>Sim</strong>
								</label>
								<label>
									<html:radio property="indicativoTipoSevicoEconomias" value="2" onchange="javascript:desabilitaEconomia();"/> <strong>Não</strong>
								</label>
							</td>
						</tr>
						
						<!-- quantidade Economias-->
						<tr>
							<td><strong> Quantidade de Economias de <font color="#FF0000"></font> </strong>
							</td>
							<td colspan="3" align="left">
							
								<html:text property="quantidadeEconomiasInicial" 
										   size="4"
										   maxlength="3" 
										   onkeypress="return isCampoNumerico(event);" 
										   style="text-align:right;" />
								<strong> até </strong> 		   
								<html:text property="quantidadeEconomiasFinal" 
											   size="4"
											   maxlength="3"
											   onkeypress="return isCampoNumerico(event);"
											   style="text-align:right;" />
							</td>
						</tr>
						
						<tr>
							<td><strong> <font color="#FF0000"></font> </strong></td>
							<td colspan="3" align="right">
							<div align="left"><strong> <font color="#FF0000">*</font> </strong>
							Campos obrigat&oacute;rios</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><font color="#FF0000"><logic:present
								name="manter" scope="session">
								<input type="button" name="ButtonReset" class="bottonRightCol"
									value="Voltar"
									onClick="javascript:window.location.href='/gsan/filtrarValorCobrancaServicoAction.do'">
							</logic:present><logic:notPresent name="manter" scope="session">
								<input type="button" name="ButtonReset" class="bottonRightCol"
									value="Voltar"
									onClick="javascript:window.location.href='/gsan/exibirFiltrarValorCobrancaServicoAction.do'">
							</logic:notPresent> <input type="button" name="ButtonReset"
								class="bottonRightCol" value="Desfazer"
								onClick="window.location.href='<html:rewrite page="/exibirAtualizarValorCobrancaServicoAction.do?desfazer=S&manter=sim&idServicoCobrancaValor=${requestScope.idServicoCobrancaValor}"/>'">
							<input type="button" name="ButtonCancelar" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</font></td>
							<td align="right"><input type="button" name="Button"
								class="bottonRightCol" value="Atualizar"
								onClick="javascript:validaForm('${sessionScope.capacidadeObrigatoria}','${sessionScope.valorObrigatorio}');" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!-- Fim do Corpo - Rômulo Aurélio -->

	<!-- Rodapé -->
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
