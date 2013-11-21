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

<html:javascript staticJavascript="false" formName="GerarRelatorioResumoDistritoOperacionalActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
	
function validarForm(){
		
		var form = document.GerarRelatorioResumoDistritoOperacionalActionForm;
		if(validateGerarRelatorioResumoDistritoOperacionalActionForm(form) && validarCampos()){
			botaoAvancarTelaEspera('/gsan/gerarRelatorioResumoDistritoOperacionalAction.do');

		}
}
	
function validarCampos(){
		
		var form = document.GerarRelatorioResumoDistritoOperacionalActionForm;
		
		msg = verificarAtributosInicialFinal(form.localidadeInicial,form.localidadeFinal,"Localidade");
		if( msg != ""){
			alert(msg);
			return false;
		}
		msg = verificarAtributosInicialFinal(form.setorComercialInicial,form.setorComercialFinal,"Setor Comercial");
		if( msg != ""){
				alert(msg);
				return false;
		}

		return true;
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
function desabilitaIntervaloDiferente(){
	var form = document.GerarRelatorioResumoDistritoOperacionalActionForm;	
		    if(form.localidadeInicial.value != form.localidadeFinal.value){	        
		        form.setorComercialInicial.disabled = true;		
			 	form.setorComercialFinal.disabled = true;
			 	form.setorComercialInicial.value = "";		
			 	form.setorComercialFinal.value = "";
	 			form.nomeSetorComercialInicial.value = "";		
			 	form.nomeSetorComercialFinal.value = "";
			  }else{
			 	form.setorComercialInicial.disabled = false;		
			 	form.setorComercialFinal.disabled = false;
			 }		
}
			
function validarLocalidade(){
	var form = document.GerarRelatorioResumoDistritoOperacionalActionForm;

	if( form.localidadeOrigemID.value == form.localidadeDestinoID.value ){
		form.setorComercialInicial.disabled = false;
		form.nomeSetorComercialInicial.disabled = false;
		form.setorComercialFinal.disabled = false;
		form.nomeSetorComercialFinal.disabled = false;
		
	}
	else if( form.localidadeOrigemID.value != form.localidadeDestinoID.value ){
		form.setorComercialInicial.disabled = true;
		form.nomeSetorComercialInicial.disabled = true;
		form.setorComercialFinal.disabled = true;
		form.nomeSetorComercialFinal.disabled = true;
	}
	
}
function limpar(){

  		var form = document.GerarRelatorioResumoDistritoOperacionalActionForm;
  		
		form.localidadeInicial.value = "";
		form.nomeLocalidadeInicial.value = "";
		form.localidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		
		form.setorComercialInicial.value = "";
		form.nomeSetorComercialInicial.value = "";
		form.setorComercialFinal.value = "";
		form.nomeSetorComercialFinal.value = "";
		
  		form.gerenciaRegional.value = "-1";
  		form.unidadeNegocio.value = "-1";
  		form.distritoOperacional.value= "-1";
  		
  		form.mesAno.value = "";

  		form.action='exibirGerarRelatorioResumoDistritoOperacionalAction.do';
	    form.submit();
 }
  	
function replicarLocalidade(){
		var formulario = document.forms[0]; 
		
		formulario.localidadeFinal.value = formulario.localidadeInicial.value;
		formulario.setorComercialInicial.focus;
}
	
function replicarSetorComercial(){
		var formulario = document.forms[0]; 
		formulario.setorComercialFinal.value = formulario.setorComercialInicial.value;
		formulario.rotaInicial.focus;
} 
	 
	
function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
		
		case 1: //De localidade pra baixo

			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			form.setorComercialInicial.value = "";
		    form.setorComercialFinal.value = "";
	
		    
		case 2: //De setor para baixo

		   form.nomeSetorComercialInicial.value = "";
		   form.setorComercialFinal.value = "";
		   form.nomeSetorComercialFinal.value = "";

		 }
}
	
function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidara pra baixo

				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				break;
			case 2: //De setor para baixo
		     	
		     	form.setorComercialInicial.value = "";
		     	form.nomeSetorComercialInicial.value = "";
		     	form.setorComercialFinal.value = "";
		     	form.nomeSetorComercialFinal.value = "";
		}
}
	
function limparBorrachaDestino(tipo){
		var form = document.forms[0];

		switch(tipo){
			case 1: //De localidade pra baixo
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				form.setorComercialFinal.value = "";
				
			case 2: //De setor para baixo		   
		   		form.setorComercialFinal.value = ""; 
		   		form.nomeSetorComercialFinal.value = "";		   
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
	  		
	  		form.action = 'exibirGerarRelatorioResumoDistritoOperacionalAction.do';
	  		form.submit();
	  		
	  		form.setorComercialInicial.focus();
	  		
	  		
		}

		if (tipoConsulta == 'localidadeDestino') {
		
      		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	  		
	  		form.action = 'exibirGerarRelatorioResumoDistritoOperacionalAction.do';
	  		form.submit();
	  		
	  		form.setorComercialFinal.focus();


		}
}
	
	
function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'setorComercialOrigem') {
		  	form.setorComercialInicial.value = codigoRegistro;
		  	form.nomeSetorComercialInicial.value = descricaoRegistro;
		  	form.nomeSetorComercialInicial.style.color = "#000000"; 
		  	
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		  	
		}

		if (tipoConsulta == 'setorComercialDestino') {
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		}
}	
	  	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.mesAnoFaturamento}');desabilitaIntervaloDiferente();">
<div id="formDiv"><html:form
	action="/gerarRelatorioResumoDistritoOperacionalAction.do"
	name="GerarRelatorioResumoDistritoOperacionalActionForm"
	type="gcom.gui.relatorio.gerencial.micromedicao.GerarRelatorioResumoDistritoOperacionalActionForm"
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
					<td class="parabg">Gerar Relat&oacute;rio Resumo do Distrito
					Operacional</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados
					abaixo:</td>
				</tr>
				<tr>
					<td><strong>Refer&ecirc;ncia:<font color="#FF0000">*</font></strong></td>
					<td colsan="3"><html:text maxlength="7" tabindex="1"
						property="mesAno" size="7" onkeyup="mascaraAnoMes(this, event);" 
						onkeypress="return isCampoNumerico(event);"/>(mm/aaaa)
					</td>
				</tr>
				<tr>
					<td><strong>Ger&ecirc;ncia Regional:</strong></td>

					<td><strong> <html:select property="gerenciaRegional"
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

					<td><strong> <html:select property="unidadeNegocio"
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
					<td><strong>Distrito Operacional:</strong></td>

					<td><strong> <html:select property="distritoOperacional"
						style="width: 230px;">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

						<logic:present name="colecaoDistritoOperacional" scope="request">
							<html:options collection="colecaoDistritoOperacional"	
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select> </strong></td>
				</tr>
				<tr>
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeInicial" 
							size="3"
							onblur="javascript:replicarLocalidade();" 
							onkeyup="javascript:limparOrigem(1);desabilitaIntervaloDiferente();"
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarRelatorioResumoDistritoOperacionalAction.do?objetoConsulta=1','localidadeInicial','Localidade Inicial');
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
					<td><strong>Setor Comercial Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="setorComercialInicial" 
							size="3"
							onblur="javascript:replicarSetorComercial();"
							onkeyup="limparOrigem(2);"
							onkeypress="javascript:limparOrigem(2);validaEnterComMensagem(event, 'exibirGerarRelatorioResumoDistritoOperacionalAction.do?objetoConsulta=2','setorComercialInicial','Setor Comercial Inicial');
							            return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeInicial.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].setorComercialInicial);
						         limparOrigem(2);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
								

						<logic:present name="setorComercialInicialEncontrado" scope="request">
							<html:text property="nomeSetorComercialInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialInicialEncontrado" scope="request">
							<html:text property="nomeSetorComercialInicial" 
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
					<td><strong>Localidade Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeFinal" 
							size="3"
							onblur="javascript:desabilitaIntervaloDiferente();"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioResumoDistritoOperacionalAction.do?objetoConsulta=3','localidadeFinal','Localidade Final');
							            return isCampoNumerico(event);"
							onkeyup="javascript:desabilitaIntervaloDiferente();"
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

						
						<a href="javascript:limparBorrachaDestino(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" property="setorComercialFinal"
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioResumoDistritoOperacionalAction.do?objetoConsulta=4','setorComercialFinal','Setor Comercial Final');
							            return isCampoNumerico(event);"
							tabindex="8"/>
								
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].localidadeFinal.value, 275, 480, 'Informe Localidade Final',document.forms[0].setorComercialFinal);
							        limparDestino(2);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
								

						<logic:present name="setorComercialFinalEncontrado" scope="request">
							<html:text property="nomeSetorComercialFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialFinalEncontrado" scope="request">
							<html:text property="nomeSetorComercialFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaDestino(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>



				<tr>
					<td><strong>Tipo do Relatório:</strong></td>
					<td colspan="2"><input type="radio" name="tipo" tabindex="3"
						value="A" checked="checked"> <strong>Agrupadas</strong>&nbsp; <input
						type="radio" name="tipo" tabindex="3" value="D"> <strong>Descritas</strong>
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
						class="bottonRightCol" value="Gerar"
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
