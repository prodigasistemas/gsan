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

<html:javascript staticJavascript="false" formName="GerarRelatorioImoveisDoacoesActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(){
		
		var form = document.forms[0];
		
		if(validateGerarRelatorioImoveisDoacoesActionForm(form) && validarCampos()){
			toggleBox('demodiv',1);
		}
	}
	
	function validarCampos(){
		
		var form = document.forms[0];
		var msg = "";
		
		if(comparaData(form.periodoAdesaoInicial.value, ">", form.periodoAdesaoFinal.value)){
			msg = "Data de Adesão Final deve ser maior ou igual a Data de Adesão Inicial \n";
		}
		
		if(comparaData(form.periodoCancelamentoInicial.value, ">", form.periodoCancelamentoFinal.value)){
			msg = msg + "Data de Cancelamento Final deve ser maior ou igual a Data de Cancelamento Inicial \n";
		}				
		
		if(comparaMesAno(form.referenciaInicioDoacaoInicial.value,">",form.referenciaInicioDoacaoFinal.value) ){
			msg = msg + "Referência de Inicio da Doação Final deve ser maior ou igual a Referência de Inicio da Doacao Inicial \n";
		}

		if(comparaMesAno(form.referenciaFimDoacaoInicial.value,">",form.referenciaFimDoacaoFinal.value) ){
			msg = msg + "Referência de Fim de Doação Final deve ser maior ou igual a Referência de Fim de Doação Inicial";
		}

		if( msg != ""){
			alert(msg);
			return false;
		}else{
			return true;
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
	
  		form.imovel.value = "";
  		form.nomeImovel.value = "";
  		form.entidadeBeneficente.value = "<%="" + ConstantesSistema.NUMERO_NAO_INFORMADO%>";
  		form.periodoAdesaoInicial.value = "";
  		form.periodoAdesaoFinal.value = "";
  		form.periodoCancelamentoInicial.value = "";
  		form.periodoCancelamentoFinal.value = "";
  		form.usuarioAdesao.value = "";
  		form.nomeUsuarioAdesao.value = "";
  		form.usuarioCancelamento.value = "";
  		form.nomeUsuarioCancelamento.value = "";
  		form.referenciaInicioDoacaoInicial.value = "";
  		form.referenciaInicioDoacaoFinal.value = "";
  		form.referenciaFimDoacaoInicial.value = "";
  		form.referenciaFimDoacaoFinal.value = "";
	}
	
	function tipoPesquisaImovel(){
		var form = document.forms[0];
		if(form.opcaoRelatorio[0].checked){
			
			form.imovel.disabled = 0;
			form.opcaoRelatorio[0].checked = true;
		  	form.opcaoRelatorio[1].checked = false;
			form.nomeImovel.disabled = 0;
			document.getElementById('pesIm').href = "javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].imovel);";
			form.entidadeBeneficente.disabled = 1;
			form.periodoAdesaoInicial.disabled = 1;
			document.getElementById('cldAds1').href = "#";
			document.getElementById('cldAds2').href = "#";
			document.getElementById('cldCan1').href = "#";
			document.getElementById('cldCan2').href = "#";
			document.getElementById('pesUsAd').href = "#";
			document.getElementById('pesUsCan').href = "#";
		  	form.periodoAdesaoFinal.disabled = 1;
		  	form.periodoCancelamentoInicial.disabled = 1;
		  	form.periodoCancelamentoFinal.disabled = 1;
		  	form.usuarioAdesao.disabled = 1;
		  	form.nomeUsuarioCancelamento.disabled = 1;
		  	form.usuarioCancelamento.disabled = 1;
		  	form.nomeUsuarioCancelamento.disabled = 1;
		  	form.referenciaInicioDoacaoInicial.disabled = 1;
		  	form.referenciaInicioDoacaoFinal.disabled = 1;
		  	form.referenciaFimDoacaoInicial.disabled = 1;
		  	form.referenciaFimDoacaoFinal.disabled = 1;
		}
	}

	function tipoPesquisaEntidade(){
		var form = document.forms[0];
		if(form.opcaoRelatorio[1].checked){
			
			form.imovel.disabled = 1;
			form.nomeImovel.disabled = 1;
			document.getElementById('pesIm').href = "#";
			form.entidadeBeneficente.disabled = 0;
			form.periodoAdesaoInicial.disabled = 0;
			document.getElementById('cldAds1').href = "javascript:abrirCalendarioReplicando('GerarRelatorioImoveisDoacoesActionForm', 'periodoAdesaoInicial','periodoAdesaoFinal');";
		  	document.getElementById('cldAds2').href ="javascript:abrirCalendario('GerarRelatorioImoveisDoacoesActionForm', 'periodoAdesaoFinal');";
		  	document.getElementById('cldCan1').href = "javascript:abrirCalendarioReplicando('GerarRelatorioImoveisDoacoesActionForm', 'periodoCancelamentoInicial','periodoCancelamentoFinal');";
			document.getElementById('cldCan2').href = "javascript:abrirCalendario('GerarRelatorioImoveisDoacoesActionForm', 'periodoCancelamentoFinal');";
		  	document.getElementById('pesUsAd').href = "javascript:chamarPopup('exibirUsuarioPesquisar.do', 'usAdesao', null, null, 275, 480, '',document.forms[0].usuarioAdesao);";
			document.getElementById('pesUsCan').href = "javascript:chamarPopup('exibirUsuarioPesquisar.do', 'usCancela', null, null, 275, 480, '',document.forms[0].usuarioCancelamento);";
		  	form.periodoAdesaoFinal.disabled = 0;
		  	form.periodoCancelamentoInicial.disabled = 0;
		  	form.periodoCancelamentoFinal.disabled = 0;
		  	form.usuarioAdesao.disabled = 0;
		  	form.nomeUsuarioCancelamento.disabled = 0;
		  	form.usuarioCancelamento.disabled = 0;
		  	form.nomeUsuarioCancelamento.disabled = 0;
		  	form.referenciaInicioDoacaoInicial.disabled = 0;
		  	form.referenciaInicioDoacaoFinal.disabled = 0;
		  	form.referenciaFimDoacaoInicial.disabled = 0;
		  	form.referenciaFimDoacaoFinal.disabled = 0;
		}
	}
  		
function limparOrigem(tipo){
		var form = document.forms[0];
		
		if(tipo == 1){
			 
				form.imovel.value = "";
				form.nomeImovel.value = "";
			}
		if(tipo == 2){
			 
				form.usuarioAdesao.value = "";
				form.nomeUsuarioAdesao.value = "";
			}
		if(tipo == 3){
			 
				form.usuarioCancelamento.value = "";
				form.nomeUsuarioCancelamento.value = "";
			}
}

//Define o campo que será atualizado pela pesquisa de usuario;
var tipoRetorno = 'adesao';

function usAdesao(){	
	tipoRetorno = 'adesao';
}
function usCancela(){
	tipoRetorno = 'cancelamento';
}	
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];
		if (tipoConsulta == 'imovel') {
      		
      		form.imovel.value = codigoRegistro;
	  		form.nomeImovel.value = descricaoRegistro;
	  		      		
	  		form.nomeImovel.style.color = "#000000";
	  			  		
	  		form.action = 'exibirGerarRelatorioImoveisDoacoesAction.do';
	  		form.submit();
	  		
		}
		if (tipoConsulta == 'usuario') {
			if(tipoRetorno == 'adesao'){
				form.usuarioAdesao.value = codigoRegistro;
	  			form.nomeUsuarioAdesao.value = descricaoRegistro;
	  			      		
	  			form.nomeUsuarioAdesao.style.color = "#000000";
	  			  		
	  			form.action = 'exibirGerarRelatorioImoveisDoacoesAction.do';
	  			form.submit();
	  		}
		}
		if (tipoConsulta == 'usuario') {
			if(tipoRetorno == 'cancelamento'){
      			form.usuarioCancelamento.value = codigoRegistro;
	  			form.nomeUsuarioCancelamento.value = descricaoRegistro;
	  		      		
	  			form.nomeUsuarioCancelamento.style.color = "#000000";
	  			  		
	  			form.action = 'exibirGerarRelatorioImoveisDoacoesAction.do';
	  			form.submit();
			}
		}
}

/* Replica Data Periodo de Referencia */	
function replicaCampo(campo1, campo2) {
	/*var form = document.forms[0];*/
	campo2.value = campo1.value;
}

  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:tipoPesquisaImovel()">
<div id="formDiv">
<html:form
	action="/gerarRelatorioImoveisDoacoesAction.do"
	name="GerarRelatorioImoveisDoacoesActionForm"
	type="gcom.gui.relatorio.cadastro.GerarRelatorioImoveisDoacoesActionForm"
	method="post">
<!-- /gerarRelatorioImoveisDoacoesAction.do -->
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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Gerar Relatório de Imóveis com Doações</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!-- <p>&nbsp;</p> -->
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Selecione a opção do relatorio e informe os dados abaixo para filtrar o(s) 
					imóvel(eis):</td>
					
				</tr>
				<tr>
					<td><strong>Opção de Relatório:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<html:radio property="opcaoRelatorio" name="GerarRelatorioImoveisDoacoesActionForm" value="1" onclick="javascript:tipoPesquisaImovel(); limpar();"/> <strong>Por Imóvel</strong>&nbsp; 
						<html:radio property="opcaoRelatorio" name="GerarRelatorioImoveisDoacoesActionForm" value="2" onclick="javascript:tipoPesquisaEntidade(); limpar();"/> <strong>Por Entidade Beneficente</strong>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr /></td>
				</tr>
				
				<tr>
					<td><strong>Imóvel:</strong></td>
					
					<td>						
						<html:text maxlength="9" 
							tabindex="1"
							property="imovel" 
							name="GerarRelatorioImoveisDoacoesActionForm"
							size="9"
							onkeyup="javascript:limparOrigem(2);"
							onkeypress="javascript:limparOrigem(2);validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisDoacoesAction.do?objetoConsulta=1','imovel','Imovel');
							            return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].imovel);limparOrigem(2);" id="pesIm">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Imóvel" /></a>
								

						<logic:present name="imovelEncontrado" scope="request">
							<html:text property="nomeImovel" 
								name="GerarRelatorioImoveisDoacoesActionForm"
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="imovelEncontrado" scope="request">
							<html:text property="nomeImovel" 
								name="GerarRelatorioImoveisDoacoesActionForm"
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
		
						<a href="javascript:limparOrigem(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr><!-- Fim campo imóvel -->
				
				<tr>
					<td colspan="2"><hr /></td>
				</tr>
				
				<tr>
					<td><strong>Entidade Beneficente:</strong></td>

					<td><strong> 					
					<html:select property="entidadeBeneficente" name="GerarRelatorioImoveisDoacoesActionForm"
						style="width: 230px;" onchange=""><!-- javascript:reloadForm(); -->

						<html:option
							value="<%=""+ ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
							
						<logic:present name="colecaoEntidadeBeneficente" scope="request">
							<html:options collection="colecaoEntidadeBeneficente" property="id"
							labelProperty="cliente.nome" />							
						</logic:present>
					</html:select> </strong></td>
				</tr><!-- Fim de Entidade Beneficente -->
				
				<tr> 
                <td><strong>Per&iacute;odo de Adesão:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
						<html:text property="periodoAdesaoInicial" 
									name="GerarRelatorioImoveisDoacoesActionForm"
									size="10" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event);replicaCampo(periodoAdesaoInicial, periodoAdesaoFinal);"/>
							<a href="javascript:abrirCalendarioReplicando('GerarRelatorioImoveisDoacoesActionForm', 'periodoAdesaoInicial','periodoAdesaoFinal');" id="cldAds1">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="5"/></a>
						a <html:text property="periodoAdesaoFinal"
									name="GerarRelatorioImoveisDoacoesActionForm" 
									size="10" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event)"/>
						<a href="javascript:abrirCalendario('GerarRelatorioImoveisDoacoesActionForm', 'periodoAdesaoFinal');" id="cldAds2">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="5"/></a>
						</strong>(dd/mm/aaaa)<strong> 
                  </strong></span></td>
                </tr><!-- fim de período de adesão -->	
                
                <tr> 
                <td><strong>Per&iacute;odo de Cancelamento:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
						<html:text property="periodoCancelamentoInicial" 
									name="GerarRelatorioImoveisDoacoesActionForm"
									size="10" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event);replicaCampo(periodoCancelamentoInicial, periodoCancelamentoFinal);"/>
							<a href="javascript:abrirCalendarioReplicando('GerarRelatorioImoveisDoacoesActionForm', 'periodoCancelamentoInicial','periodoCancelamentoFinal');" id="cldCan1">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="5"/></a>
						a <html:text property="periodoCancelamentoFinal"
									name="GerarRelatorioImoveisDoacoesActionForm" 
									size="10" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event)"/>
						<a href="javascript:abrirCalendario('GerarRelatorioImoveisDoacoesActionForm', 'periodoCancelamentoFinal');" id="cldCan2">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="5"/></a>
						</strong>(dd/mm/aaaa)<strong> 
                  </strong></span></td>
                </tr><!-- fim de período de cancelamento -->	
                			
                <tr>
					<td><strong>Usuário de Adesão:</strong></td>
					
					<td>
						
						<html:text maxlength="11" 
							tabindex="1"
							property="usuarioAdesao" 
							name="GerarRelatorioImoveisDoacoesActionForm"
							size="11"
							style="text-transform: none;"
							onkeypress="validaEnterStringSemUpperCase(event, 'exibirGerarRelatorioImoveisDoacoesAction.do?objetoConsulta=2','usuarioAdesao','Usuario de Adesão');
							            return isCampoNumerico(event);"/>
						<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'usuario', null, null, 275, 480, '',document.forms[0].usuarioAdesao);" id = "pesUsAd">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Usuário" onclick="javascript: usAdesao();"/></a>
								

						<logic:present name="usuarioAdesaoEncontrado" scope="request">
							<html:text property="nomeUsuarioAdesao" 
								name="GerarRelatorioImoveisDoacoesActionForm"
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="usuarioAdesaoEncontrado" scope="request">
							<html:text property="nomeUsuarioAdesao" 
								name="GerarRelatorioImoveisDoacoesActionForm"
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
		
						<a href="javascript:limparOrigem(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr><!-- fim de usuário de adesão -->	
				
				<tr>
					<td><strong>Usuário de Cancelamento:</strong></td>
					
					<td>
						
						<html:text maxlength="11" 
							tabindex="1"
							property="usuarioCancelamento" 
							name="GerarRelatorioImoveisDoacoesActionForm"
							size="11"
							style="text-transform: none;"
							onkeypress="validaEnterStringSemUpperCase(event, 'exibirGerarRelatorioImoveisDoacoesAction.do?objetoConsulta=3','usuarioCancelamento','Usuário de Cancelamento');
							            return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'usuario', null, null, 275, 480, '',document.forms[0].usuarioCancelamento);" id ="pesUsCan">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Usuário" onclick="javascript:usCancela();"/></a>
								

						<logic:present name="usuarioCancelamentoEncontrado" scope="request">
							<html:text property="nomeUsuarioCancelamento" 
								name="GerarRelatorioImoveisDoacoesActionForm"
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="usuarioCancelamentoEncontrado" scope="request">
							<html:text property="nomeUsuarioCancelamento" 
								name="GerarRelatorioImoveisDoacoesActionForm"
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
		
						<a href="javascript:limparOrigem(3);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr><!-- fim de usuário de cancelamento -->
				
				<tr> 
                <td><strong>Referência do Início da Doação:</strong></td>
                <td colsan="3">						
						<html:text maxlength="7"
							tabindex="1"
							property="referenciaInicioDoacaoInicial" 
							size="7" onkeyup="javascript:replicaCampo( document.forms[0].referenciaInicioDoacaoInicial, document.forms[0].referenciaInicioDoacaoFinal );mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event)"/>
						<strong>a</strong>
						<html:text maxlength="7"
							tabindex="1"
							property="referenciaInicioDoacaoFinal" 
							size="7"
							onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event)"/>(mm/aaaa)
					</td>
                </tr><!-- fim de referencia do inicio da doação -->	
                
                <tr> 
                <td><strong>Referencia do Fim da Doação:</strong></td>
                <td colsan="3">						
						<html:text maxlength="7"
							tabindex="1"
							property="referenciaFimDoacaoInicial" 
							size="7" onkeyup="javascript:replicaCampo( document.forms[0].referenciaFimDoacaoInicial, document.forms[0].referenciaFimDoacaoFinal );mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event)"/>
						<strong>a</strong>
						<html:text maxlength="7"
							tabindex="1"
							property="referenciaFimDoacaoFinal" 
							size="7"
							onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event)"/>(mm/aaaa)
					</td>
                </tr><!-- fim de referencia do fim da adesão -->
                
                <tr>
					<td colspan="2"><hr /></td>
				</tr>
								
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left" colspan="2"><strong><font color="#FF0000">*</font></strong>

					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td height="24">
					<input type="button" class="bottonRightCol"
						value="Limpar" onclick="javascript:limpar(); tipoPesquisaImovel();" />
					<input type="button" name="botaoCancelar"
						class="bottonRightCol" value="Cancelar"
						onclick="window.location.href='/gsan/telaPrincipal.do';"/>
					</td>

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
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioImoveisDoacoesAction.do" />
</html:form></div>
</body>
</html:html>
