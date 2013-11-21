<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioBoletimMedicaoCobrancaForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		if(validarCampos()){
			if(validateGerarRelatorioBoletimMedicaoCobrancaForm(form)){
				if (form.formaGeracao[0].checked == false
					&& form.formaGeracao[1].checked == false) {
	  				alert("Selecione a Operação.");
				} else if (form.formaGeracao[1].checked == true) {
					if (form.idLocalidadeFinal.value != ""
						&& form.idLocalidadeInicial.value == "") {
	  					alert("Informe a Localidade Inicial.");
					} else if (form.idLocalidadeFinal.value != ""
						&& form.idLocalidadeInicial.value > form.idLocalidadeFinal.value ) {
	  					alert("Localidade Final é menor que Localidade Inicial.");
					} else {
						toggleBox('demodiv',1);
					}
				} else {
					form.submit();
				}
			}
		}
	}
	
  	function limpar(){
  		var form = document.forms[0];
		
		form.grupoCobranca.value = "-1";
		form.mesAnoReferencia.value = "";
		form.gerenciaRegional.value = "-1";
		form.idLocalidadeInicial.value = "";
		form.idLocalidadeFinal.value = "";
		form.descricaoLocalidadeInicial.value = "";
		form.descricaoLocalidadeFinal.value = "";
		form.formaGeracao[0].checked = false;
		form.formaGeracao[1].checked = false;
		document.getElementById('tipoEmitir').style.display = 'none';
		document.getElementById('tipoGerar').style.display = 'none';
		form.tipoOperacao.value = "";
  	}		
	
	function validarCampos(){

		var form = document.forms[0];
		
		if (form.mesAnoReferencia.value == "") {
		
	  		alert("Selecione o Mês/Ano de Referência.");
			return false;
	  		
		} else if (form.grupoCobranca.value == "-1"){
				
  			alert("Selecione o Grupo de Cobrança.");
			return false;
			
       	}
	  		
   		return true;
	}

	function selecionarOperacao(tipo){
		var form = document.forms[0];
		if(tipo==1){
			form.tipoOperacao.value = 1;
			document.getElementById('tipoEmitir').style.display = 'none';
			document.getElementById('tipoGerar').style.display = 'block';
		}else if(tipo==2){
			form.tipoOperacao.value = 2;
			document.getElementById('tipoEmitir').style.display = 'block';
			document.getElementById('tipoGerar').style.display = 'none';
		}
	}
	
	function limparDescLocalidadeInicial(form){
	
		form.idLocalidadeFinal.value = "";
    	form.descricaoLocalidadeInicial.value = "";
    	form.descricaoLocalidadeFinal.value = "";
		
	}
   
   function habilitarPesquisaLocalidade(form, tipo) {
		if (form.idLocalidadeInicial.disabled == false) 
		{
		    if(tipo == 'origem')
		    {
				chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', 'indicadorUsoTodos', '1', 275, 480, '',form.idLocalidadeInicial.value);
			}
			else if(tipo == 'destino')
		    {
				chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', 'indicadorUsoTodos', '1', 275, 480, '',form.idLocalidadeFinal.value);
			}
		}	
	}
	
	function limparLocalidadeInicial(form) {
	
		form.idLocalidadeInicial.value = "";
		form.idLocalidadeFinal.value = "";
    	form.descricaoLocalidadeInicial.value = "";
    	form.descricaoLocalidadeFinal.value = "";
		
	}

	function limparDescLocalidadeFinal(form) {
	
    	form.descricaoLocalidadeFinal.value = "";
    	
	}
	
	function limparLocalidadeFinal(form){
	
		form.idLocalidadeFinal.value = "";
		form.descricaoLocalidadeFinal.value = "";
		
	}
	
	function verificaSelecao() {
		var form = document.forms[0];
		if(form.tipoOperacao.value==1){
			document.getElementById('tipoEmitir').style.display = 'none';
			document.getElementById('tipoGerar').style.display = 'block';
		}else if(form.tipoOperacao.value==2){
			document.getElementById('tipoEmitir').style.display = 'block';
			document.getElementById('tipoGerar').style.display = 'none';
		}
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	
 	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];
	
	    if (tipoConsulta == 'localidadeOrigem') {
	      	limparLocalidadeInicial(form);
	      	limparLocalidadeFinal(form);
	      	
	      	form.idLocalidadeInicial.value = codigoRegistro;
	    	form.descricaoLocalidadeInicial.value = descricaoRegistro;
	    	form.descricaoLocalidadeInicial.style.color = "#000000";
	    	
	      	form.idLocalidadeFinal.value = codigoRegistro;
	    	form.descricaoLocalidadeFinal.value = descricaoRegistro;
	    	form.descricaoLocalidadeFinal.style.color = "#000000";
	    	
	    }
	    
	     if (tipoConsulta == 'localidadeDestino') {
	      	limparLocalidadeFinal(form);
	      	form.idLocalidadeFinal.value = codigoRegistro;
	    	form.descricaoLocalidadeFinal.value = descricaoRegistro;
	    	form.descricaoLocalidadeFinal.style.color = "#000000";
	    }
	
	    
   }
	  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="verificaSelecao();">

<div id="formDiv"><html:form action="/exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do"
	name="GerarRelatorioBoletimMedicaoCobrancaForm"
	type="gcom.gui.relatorio.cobranca.GerarRelatorioBoletimMedicaoCobrancaForm"
	method="post"> 

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	  <html:hidden property="tipoOperacao"/>
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
					<td class="parabg">Solicitar Geração/Emissão Boletim de Medição de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar/emitir o Boletim de Medição de Cobrança, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td width="18%"><strong>Operação:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:radio property="formaGeracao"
						    value="1" 
							onclick="selecionarOperacao(this.value);"/>
							Gerar Boletim de Medição
						<html:radio property="formaGeracao"
						    value="2" 
							onclick="selecionarOperacao(this.value);"/>
							Emitir Relatório
					</td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>
				
				<tr>
					<td>
						<strong>Grupo de Cobrança:<font color="#FF0000">*</font></strong>
					</td>

					<td colspan="3">
						<strong> 
						<html:select property="grupoCobranca" 
									 style="width: 230px;" 
									 tabindex="4">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoCobrancaGrupo" 
										   scope="request">
							   <html:options collection="colecaoCobrancaGrupo"
											 labelProperty="descricao" 
											 property="id"/>
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="200">
						<strong>Mês/Ano do Grupo de Cobrança:<font color="#FF0000">*</font></strong>
					</td>

					<td colspan="3">
						<html:text  property="mesAnoReferencia" 
									size="7" 
									maxlength="7" 
									tabindex="1"
									onkeyup="mascaraAnoMes(this, event);"
									onkeypress="return isCampoNumerico(event);" /> (mm/aaaa)
					</td>
				</tr><tr>
					<td colspan="2">
						<div id="tipoGerar" style="display:none;">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="tipoEmitir" style="display:none;">
							<table border="0">
							<tr>
							
								<td height="24">
									<strong>Gerência Regional:<font color="#FF0000"></font></strong>
								</td>
			
								<td colspan="3">
									<strong> 
									<html:select property="gerenciaRegional" 
												 style="width: 230px;" 
												 tabindex="4"
												 onchange="javascript:reloadForm();">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
										</html:option>
								
										<logic:present name="colecaoGerenciaRegional" 
													   scope="request">
										   <html:options collection="colecaoGerenciaRegional"
														 labelProperty="nome" 
														 property="id"/>
										</logic:present>
									</html:select> 														
									</strong>
								</td>
							</tr>
							<tr>
								<td>
									<strong>Unidade de Neg&oacute;cio:</strong>
								</td>
			
								<td>
									<strong> 
									<html:select property="unidadeNegocio" style="width: 230px;">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
										</html:option>
								
										<logic:present name="colecaoUnidadeNegocio" scope="request">
											<html:options collection="colecaoUnidadeNegocio"
												labelProperty="nome" 
												property="id" />
										</logic:present>
									</html:select> 														
									</strong>
								</td>
							</tr>	
							<tr>
								<td><strong>Localidade Inicial:</strong></td>
								<td height="24" colspan="3"><html:text maxlength="3" tabindex="6"
									property="idLocalidadeInicial" size="3"
									onkeypress="javascript:limparDescLocalidadeInicial(document.forms[0]);
									validaEnterComMensagem(event, 'exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do?objetoConsulta=1', 'idLocalidadeInicial','Localidade');"
									/>
									<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'origem');" >
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Localidade" /></a> 
										
										<logic:present
											name="idLocalidadeInicialNaoEncontrado">
										<logic:equal name="idLocalidadeInicialNaoEncontrado" value="exception">
											<html:text property="descricaoLocalidadeInicial" size="40"
												maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										
										<logic:notEqual name="idLocalidadeInicialNaoEncontrado" value="exception">
											<html:text property="descricaoLocalidadeInicial" size="40"
												maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
										</logic:present> 
										
										<logic:notPresent name="idLocalidadeInicialNaoEncontrado">
											<logic:empty name="GerarRelatorioBoletimMedicaoCobrancaForm" property="idLocalidadeInicial">
												<html:text property="descricaoLocalidadeInicial" value="" size="40"
													maxlength="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000" />
											</logic:empty>
											
											<logic:notEmpty name="GerarRelatorioBoletimMedicaoCobrancaForm" property="idLocalidadeInicial">
												<html:text property="descricaoLocalidadeInicial" size="40"
													maxlength="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notEmpty>
				
									</logic:notPresent> 					<a
										href="javascript:limparLocalidadeInicial(document.forms[0]);">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
									</a>
								</td>
							</tr>
							
							<tr>
								<td><strong>Localidade Final:</strong></td>
								<td height="24" colspan="3"><html:text maxlength="3" tabindex="7"
									property="idLocalidadeFinal" size="3"
									onkeypress="javascript:limparDescLocalidadeFinal(document.forms[0]);
									validaEnterComMensagem(event, 'exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do?objetoConsulta=1', 'idLocalidadeFinal','Localidade Final');"
									 />
									<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'destino');" >
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Localidade" /></a> 
										
										<logic:present
											name="idLocalidadeFinalNaoEncontrado">
										<logic:equal name="idLocalidadeFinalNaoEncontrado" value="exception">
											<html:text property="descricaoLocalidadeFinal" size="40"
												maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										
										<logic:notEqual name="idLocalidadeFinalNaoEncontrado" value="exception">
											<html:text property="descricaoLocalidadeFinal" size="40"
												maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
										</logic:present> 
										
										<logic:notPresent name="idLocalidadeFinalNaoEncontrado">
											<logic:empty name="GerarRelatorioBoletimMedicaoCobrancaForm" property="idLocalidadeFinal">
												<html:text property="descricaoLocalidadeFinal" value="" size="40"
													maxlength="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000" />
											</logic:empty>
											
											<logic:notEmpty name="GerarRelatorioBoletimMedicaoCobrancaForm" property="idLocalidadeFinal">
												<html:text property="descricaoLocalidadeFinal" size="40"
													maxlength="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notEmpty>
				
									</logic:notPresent>
									<a
										href="javascript:limparLocalidadeFinal(document.forms[0]);">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
									</a>
								</td>
							</tr>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
	
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
								          	
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   value="Limpar" 
			          		   onclick="javascript:limpar();"/>
					</td>
				
					<td align="right">
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol" 
							   value="Enviar" 
							   onClick="javascript:validarForm()" />
					</td>
					
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioBoletimMedicaoCobrancaAction.do" 
	/>	
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
