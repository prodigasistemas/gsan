<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		if(	validateFiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm(form) && 
			validarCampos()){
			
			toggleBox('demodiv',1);
			//submeterFormPadrao(form);
		}
	}
		
	function validarCampos(){
		var form = document.forms[0];
		var retorno = true;
		
		if(form.empresa.value == '-1'){
			alert('Empresa precisa ser selecionada.');
			retorno = false;
		}else if(form.grupo.value == '-1'){
			alert('Grupo precisa ser selecionado.');
			retorno = false;
		}else if(form.rota.value != "" && form.codigoSetorComercial.value == ""){
			alert('Preencha o campo do Setor.');
			retorno = false;
		}
		
		return retorno;
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
	
  	function reloadForm(){
  		var form = document.forms[0];
  	
  		form.action='exibirFiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaAction.do';
	    form.submit();
  	}
	
  	function limpar(){

  		var form = document.forms[0];

  		form.anoMesReferencia.value = "";
  		form.empresa.value = '-1';
  		form.grupo.value = '-1';
		form.localidade.value = "";
		form.nomeLocalidade.value = "";		
		form.nomeLocalidade.style.color = "#000000";	  	
	  	
	  	form.codigoSetorComercial.value = "";
		form.setorComercialDescricao.value = "";		
		
		form.setorComercialDescricao.style.color = "#000000";		
		
		form.rota.value = "";		
		
  		form.grupo.value = "-1";
  		
  		form.action='exibirFiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaAction.do?menu=sim';
	    form.submit();
  	}
  	  	
	
	function limparOrigem(tipo){
		var form = document.forms[0];

		switch(tipo){
				   
			case 1: //De localidade pra baixo
				form.nomeLocalidade.value = "";
				
				form.codigoSetorComercial.value = "";
			    form.setorComercialDescricao.value = "";
			    
			  	form.rota.value = "";			   	
			   	break;

		    case 2: //De Setor Comercial para baixo
			   form.setorComercialDescricao.value = "";
			   
			   form.rota.value = "";			   
			   break;
			   
		}
	}
	
	function limparBorracha(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidara pra baixo

				form.localidade.value = "";
				form.nomeLocalidade.value = "";
				
				form.codigoSetorComercial.value = "";
			    form.setorComercialDescricao.value = "";
			    
			    form.rota.value = "";			    
				break;
				
			case 2: //De Setor Comercial para baixo

				form.codigoSetorComercial.value = "";
			    form.setorComercialDescricao.value = "";
			    
			    form.rota.value = "";	    
				break;
		}
	}
	
	
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];
	
		if (tipoConsulta == 'setorComercial') {
	        form.codigoSetorComercial.value = codigoRegistro;
	  		form.setorComercialDescricao.value = descricaoRegistro;
	  		      		
	  		form.setorComercialDescricao.style.color = "#000000";	  		
	  		
	  		form.rota.focus();
	     
		}
		
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidade') {
      		
      		form.localidade.value = codigoRegistro;
	  		form.nomeLocalidade.value = descricaoRegistro;
	  			  		
	  		form.nomeLocalidade.style.color = "#000000";
	  			  		
	  		form.codigoSetorComercial.focus();
		}

				
		if (tipoConsulta == 'setorComercial') {
      		
      		form.codigoSetorComercial.value = codigoRegistro;
	  		form.setorComercialDescricao.value = descricaoRegistro;
	  			  		      		
	  		form.setorComercialDescricao.style.color = "#000000";
	  			  		
	  		form.rota.focus();
		}				
				
	}

	function pesquisarEmpresa() {
		var form = document.forms[0];

			abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 495, 300);
	}
  	
</script>

</head>

<body leftmargin="5" topmargin="5" >

<html:form action="/exibirFiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaAction.do"
	name="FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm"
	type="gcom.gui.relatorio.micromedicao.ExibirFiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaAction"
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
					<td class="parabg">Filtrar Notificação de Débitos para Impressão Simultânea</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para selecionar ordens de serviço para geração do relatório, informe os dados abaixo:</td>
				</tr>	

				<tr>
					<td width="200"><strong>Refer&ecirc;ncia:&nbsp;<font color="#FF0000">*</font></strong></td>
					<td align="left">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><html:text property="anoMesReferencia" size="6" maxlength="7" 
								               onkeyup="mascaraAnoMes(this, event);" 
								               onkeypress="return isCampoNumerico(event);"
								               tabindex="2"></html:text><strong>&nbsp;MM/AAAA</strong></td>
							</tr>
						</table>
					</td>
				</tr>
 				
				<tr>
					<td width="70"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td >
						<html:select property="empresa" style="width: 250px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa"
								labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>			

				<tr>
					<td>
						<strong>Grupo de Cobrança:<font color="#FF0000">*</font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="grupo" style="width: 230px;" tabindex="2">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoGrupo" 
										   scope="request">
										   <html:options collection="colecaoGrupo"
														 labelProperty="descricao" 
														 property="id"/>
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>			

				<tr>
					<td><strong>Localidade:</strong></td>
					<td>	
						<html:text maxlength="3" 
							tabindex="1"
							property="localidade" 
							size="3"							
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirFiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaAction.do?objetoConsulta=1','localidade','Localidade');return isCampoNumerico(event);"/>
							
							
						<a href="javascript:limparOrigem(1);chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',document.forms[0].localidade);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								
						<logic:present name="localidadeEncontrada" scope="request">
							<html:text property="nomeLocalidade" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeEncontrada" scope="request">
							<html:text property="nomeLocalidade" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparBorracha(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
								
				<tr>
					<td><strong>Setor Comercial:<font color="#FF0000"></font></strong></td>
					
					<td>	
						<html:text maxlength="3" 
							tabindex="3"
							property="codigoSetorComercial" 
							size="3"							
							onkeypress="javascript:limparOrigem(2);validaEnterDependencia(event, 'exibirFiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaAction.do?objetoConsulta=2', this, document.forms[0].localidade.value,'Localidade');return isCampoNumerico(event);"/>
							
						<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidade.value+'&tipo=setorComercial&indicadorUsoTodos=1',document.forms[0].localidade.value,'Localidade', 400, 800);">
		
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a> 
								
						<logic:present name="setorComercialEncontrada" scope="request">
							<html:text property="setorComercialDescricao" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialEncontrada" scope="request">
							<html:text property="setorComercialDescricao" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparBorracha(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				
				<tr>
					<td><strong>Rota:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="rota"
							onkeypress="return isCampoNumerico(event);"							
							size="4"/>
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
					<td height="24" >
			        	<input type="button" 
			        		class="bottonRightCol" 
			        		value="Limpar" 
					        onclick="javascript:limpar();"/>
			       	 <font color="#FF0000"> <input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</font>
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
<%@ include file="/jsp/util/rodape.jsp"%>
</body>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioNotificacaoDebitosImpressaoSimultaneaAction.do" />

</html:form>
</html:html>