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

<html:javascript staticJavascript="false"  formName="GerarRelatorioBoletimMedicaoForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
		
	function validarForm(){
		
		var form = document.forms[0];
		if(validarCampos()){
			if(validateGerarRelatorioBoletimMedicaoForm(form)){
				if (form.formaGeracao[0].checked == true
					|| form.formaGeracao[2].checked == true) {
					toggleBox('demodiv',1);	
				} else {
					form.submit();
				}
			}
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
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidadeOrigem') {
      		
      		form.localidadeInicial.value = codigoRegistro;
	  		form.nomeLocalidadeInicial.value = descricaoRegistro;
	  		
	  		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
      		
	  		form.nomeLocalidadeInicial.style.color = "#000000";
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	  		
	  		form.action='exibirGerarRelatorioBoletimMedicaoAction.do';
  		    form.submit();
		}

		if (tipoConsulta == 'localidadeDestino') {
		
      		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
	  		form.nomeLocalidadeFinal.style.color = "#000000";
			
			form.action='exibirGerarRelatorioBoletimMedicaoAction.do';	
	    	form.submit();
		}
	}
	
  	function limpar(){
  		var form = document.forms[0];
		
		form.empresa.value = "-1";
		form.gerenciaRegional.value = "-1";
		form.numeroContrato.value = "-1";
		form.mesAnoReferencia.value = "";
		form.nomeLocalidadeInicial.value = "";
		form.localidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		form.localidadeInicial.value = "";
		form.formaGeracao[0].checked = false;
		form.formaGeracao[1].checked = false;
		form.formaGeracao[2].checked = true;
  	}		
	
	function validarCampos(){

		var form = document.forms[0];
		
		msg = verificarAtributosInicialFinal(form.localidadeInicial,form.localidadeFinal, "Localidade");
		
		if (form.mesAnoReferencia.value !="" && form.numeroContrato.value != "-1" 
				&& form.empresa.value != "-1"){
			
			if( msg != ""){
				alert(msg);
				return false;
			}else{
				return true;
			}
			
       	}else{
	  		if (form.mesAnoReferencia.value == ""){
	  			alert("Selecione o Mês/Ano de Referência.");
	   		}
	   		if (form.empresa.value == "-1"){
	  			alert("Selecione a Empresa.");
	   		}
	   		if ( form.numeroContrato.value == "-1"){
	  			alert("Selecione o Número do Contrato.");
	   		}
	   		return false;
   		}
	}
	
	function replicarLocalidade(){
		var formulario = document.forms[0]; 
		
		formulario.localidadeFinal.value = formulario.localidadeInicial.value;
	}
	
	function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidade pra baixo
		
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				form.localidadeInicial.value = "";
		}
	}
	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidade inicial pra baixo

				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				break;
			case 2: //De localidade final
		     	
		     	form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
		}
	}
	
	function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
		
		case 1: //De localidade pra baixo

			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";

		}
	}
	
	function carregarContratos(){
		var form = document.forms[0];
		
		if(form.empresa.value != "-1"){
			form.action='exibirGerarRelatorioBoletimMedicaoAction.do';	
		    form.submit();
	    }
	}
		  	
</script>

</head>

<body leftmargin="5" topmargin="5" >

<div id="formDiv"><html:form action="/gerarRelatorioBoletimMedicaoAction.do"
	name="GerarRelatorioBoletimMedicaoForm"
	type="gcom.gui.relatorio.micromedicao.GerarRelatorioBoletimMedicaoForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
					<td class="parabg">Gerar Relatório Boletim de Medição </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o Boletim de Medição, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td width="200">
						<strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong>
					</td>

					<td colspan="3">
						<html:text  property="mesAnoReferencia" 
									size="7" 
									maxlength="7" 
									tabindex="1"
									onkeyup="mascaraAnoMes(this, event);"
									onkeypress="return isCampoNumerico(event);" /> (mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>
				
				<tr>
					<td>
						<strong>Empresa:<font color="#FF0000">*</font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="empresa" 
									 style="width: 230px;" 
									 tabindex="2"
									 onclick="javascript:carregarContratos();">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoEmpresa" 
										   scope="request">
							   <html:options collection="colecaoEmpresa"
											 labelProperty="descricao" 
											 property="id"/>
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Número do Contrato:<font color="#FF0000">*</font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="numeroContrato" 
									 style="width: 140px;" 
									 tabindex="3">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoContrato" 
										   scope="request">
							   <html:options collection="colecaoContrato"
											 labelProperty="descricaoContrato" 
											 property="id"/>
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Gerência Regional:<font color="#FF0000"></font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="gerenciaRegional" 
									 style="width: 230px;" 
									 tabindex="4">
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
					<td><strong>Localidade Inicial:</strong></td>
					
					<td>
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeInicial" 
							size="3"
							onblur="javascript:replicarLocalidade();" 
							onkeyup="javascript:limparOrigem(1);"
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarRelatorioBoletimMedicaoAction.do?objetoConsulta=1','localidadeInicial','Localidade Inicial'); return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeInicial);limparOrigem(1);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>

						<logic:present name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
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
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioBoletimMedicaoAction.do?objetoConsulta=3','localidadeFinal','Localidade Final');return isCampoNumerico(event);" />
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeFinal);limparDestino(1);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								 
						<logic:present name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparBorrachaOrigem(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td width="18%"><strong>Forma de Gera&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
					<td colspan="1">
						<html:radio property="formaGeracao"
						    value="1" />
							Relatório
						<html:radio property="formaGeracao"
						    value="2" />
							Arquivo TXT
						<html:radio property="formaGeracao"
						    value="3" />
							Ambos
					</td>
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
							   value="Gerar" 
							   onClick="javascript:validarForm()" />
					</td>
					
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioBoletimMedicaoAction.do" 
	/>	
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
