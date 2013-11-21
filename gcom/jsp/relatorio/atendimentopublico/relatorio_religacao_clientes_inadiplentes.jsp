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

<html:javascript staticJavascript="false"  formName="GerarRelatorioReligacaoClientesInadiplentesForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>


<script language="JavaScript">
		
	function validarForm(){
		var form = document.forms[0];
		if(validateGerarRelatorioReligacaoClientesInadiplentesForm(form)){
			
			if(form.escolhaRelatorio.value != "-1"){
				
				if(form.escolhaRelatorio.value == "1" && form.dataInicioEncerramento.value != "" && form.dataFimEncerramento.value != "" ){
					
					if(validaData(form.dataInicioEncerramento) && validaData(form.dataFimEncerramento)){
						if (comparaData(form.dataInicioEncerramento.value, "<=", form.dataFimEncerramento.value )){
			  				
			  				toggleBox('demodiv',1);
			  			}else{
			  				alert('O período de alteração final é anterior ao período de alteração inicial.');			
			  			}
			  		}
				}else if( form.escolhaRelatorio.value == "1"){
						
					alert("Informe o período de encerramento da S.O.");
					form.dataInicioEncerramento.focus();
				}else if( form.escolhaRelatorio.value != "1"){
					
					toggleBox('demodiv',1);
				}
			}else{
				alert("Informe o Tipo do Relatório.");
				form.escolhaRelatorio.focus();
			}
		}
	}
  	
  	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'localidade') {
	      	form.localidadeID.value = codigoRegistro;
	      	form.nomeLocalidade.value = descricaoRegistro;
	      	form.nomeLocalidade.style.color = "#000000";
	   	  	form.setorComercialCD.focus();		  
		}
		
		if (tipoConsulta == 'setorComercial') {
	      	form.setorComercialCD.value = codigoRegistro;
	      	form.setorComercialID.value = idRegistro;
		  	form.nomeSetorComercial.value = descricaoRegistro;
		  	form.nomeSetorComercial.style.color = "#000000";
		  	form.clienteID.focus();
		}
		
		if (tipoConsulta == 'usuario') {
		 	form.usuarioID.value = codigoRegistro;
		 	form.nomeUsuario.value = descricaoRegistro;
		 	form.nomeUsuario.style.color = "#000000";
	 	}
	 	
 	    if (tipoConsulta == 'cliente') {
		    form.clienteID.value = codigoRegistro;
		    form.nomeCliente.value = descricaoRegistro;
	      	form.nomeCliente.style.color = "#000000";
	    }
		
	}
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'setorComercial') {
	      	form.setorComercialCD.value = codigoRegistro;
	      	form.setorComercialID.value = idRegistro;
		  	form.nomeSetorComercial.value = descricaoRegistro;
		  	form.nomeSetorComercial.style.color = "#000000"; 
		  	form.clienteID.focus();
		}
	}
	
	function limparBorracha(tipo){
		var form = document.forms[0];
	
		switch(tipo){
			case 1: //De localidara pra baixo
			    
				form.localidadeID.value = "";
				form.nomeLocalidade.value = "";
				
				form.setorComercialCD.value = "";
			    form.setorComercialID.value = "";
			    form.nomeSetorComercial.value = "";
			    break;
				
			case 2: //De setor para baixo
			    
			    form.setorComercialCD.value = "";
			    form.setorComercialID.value = "";
			    form.nomeSetorComercial.value = "";
			    break;
			
			case 3: //Cliente
			    
			    form.clienteID.value = "";
			    form.nomeCliente.value = "";
			    break;
			    
			case 4: //Usuário
			    
			    form.usuarioID.value = "";
			    form.nomeUsuario.value = "";
			    break;
			    
			default:
				break;
		}
	
	} 
  	
  	function replicarDataEncerramento(){
  		var form = document.forms[0];
  		
		form.dataFimEncerramento.value = form.dataInicioEncerramento.value;
  	}
  	
  	function replicarDataRecorrencia(){
  		var form = document.forms[0];
  		
		form.dataFimRecorrencia.value = form.dataInicioRecorrencia.value;
  	}
  	
  	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
	
	function reload(){
		var form = document.forms[0];
		
		if(form.gerenciaRegionalID.value == '-1'){
			
			form.unidadeNegocioID.value = '-1';
		}
	
		form.action='exibirGerarRelatorioReligacaoClientesInadiplentesAction.do';	
	    form.submit();
	}
	
	function bloquear(){
		var form = document.forms[0];
		
		if(form.dataInicioEncerramento.value != "" && form.escolhaRelatorio.value == "1"){
			
			form.dataInicioRecorrencia.disabled = true;
			form.dataFimRecorrencia.disabled = true;
			
			form.dataInicioRecorrencia.value = "";
			form.dataFimRecorrencia.value = "";
		}else{
			
			form.dataInicioRecorrencia.disabled = false;
			form.dataFimRecorrencia.disabled = false;
		}
		
		if(form.dataInicioRecorrencia.value != "" && form.escolhaRelatorio.value != "1"){
			
			form.dataInicioEncerramento.disabled = true;
			form.dataFimEncerramento.disabled = true;
			
			form.dataInicioEncerramento.value = "";
			form.dataFimEncerramento.value = "";
		}else{
			
			form.dataInicioEncerramento.disabled = false;
			form.dataFimEncerramento.disabled = false;
		}
	}
		  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="">

<div id="formDiv"><html:form action="/gerarRelatorioReligacaoClientesInadiplentesAction.do"
	name="GerarRelatorioReligacaoClientesInadiplentesForm"
	type="gcom.gui.relatorio.atendimentopublico.ordemservico.GerarRelatorioReligacaoClientesInadiplentesForm"
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
					<td class="parabg">Gerar Relatório de Re-ligações para Clientes Inadiplentes</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td style="width: 100%" colspan="4">Para gerar o relatório, informe os dados abaixo:</td>
				</tr>
				
				<tr height="1">
					<td colspan="4">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<strong>Gerência Regional:<font color="#FF0000"></font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="gerenciaRegionalID" 
									 style="width: 230px;" 
									 tabindex="1"
									 onchange="javascript:reload();">
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
					<td colspan="2">
						<strong>Unidade de Negócio: <font color="#FF0000"></font></strong>
					</td>
					
					<td>
						<strong>
							<html:select property="unidadeNegocioID" 
										 tabindex="2">
								
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
									
								<logic:notEmpty name="colecaoUnidadeNegocio">
									<html:options collection="colecaoUnidadeNegocio"
										labelProperty="nome" property="id" />
								</logic:notEmpty>
							</html:select>
						</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><strong>Localidade:</strong></td>
					<td>
						<html:text tabindex="3" maxlength="3"
								   property="localidadeID" size="5"
								   onkeypress="validaEnter(event, 'exibirGerarRelatorioReligacaoClientesInadiplentesAction.do?objetoConsulta=1', 'localidadeID');return isCampoNumerico(event);" />
						
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', '', null, null, 275, 480, '');">
							<img width="23" height="21" border="0"
						         src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								 title="Pesquisar" /></a> 
						
						<logic:present name="corLocalidade">
							<logic:equal name="corLocalidade" value="exception">
								<html:text property="nomeLocalidade" size="45"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
	
							<logic:notEqual name="corLocalidade" value="exception">
								<html:text property="nomeLocalidade" size="45"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="corLocalidade">
							<logic:empty name="GerarRelatorioReligacaoClientesInadiplentesForm" property="localidadeID">
								<html:text property="nomeLocalidade" value="" size="45"
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							
							<logic:notEmpty name="GerarRelatorioReligacaoClientesInadiplentesForm" property="localidadeID">
								<html:text property="nomeLocalidade" size="45"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparBorracha(1);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								 border="0" title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><strong>Setor Comercial:</strong></td>
					<td>
						<html:text tabindex="4" maxlength="3"
								   property="setorComercialCD" size="5"
								   onkeypress="validaEnterDependencia(event, 'exibirGerarRelatorioReligacaoClientesInadiplentesAction.do?objetoConsulta=2', document.forms[0].setorComercialCD, document.forms[0].localidadeID.value, 'Localidade.');return isCampoNumerico(event);" />
					
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem','idLocalidade', document.forms[0].localidadeID.value, 275, 480, 'Informe Localidade.');">
						<img width="23" height="21" border="0"
						     src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						     title="Pesquisar" /></a> 
					   
						<logic:present name="corSetorComercial">
							<logic:equal name="corSetorComercial" value="exception">
								<html:text property="nomeSetorComercial" size="45"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="corSetorComercial" value="exception">
								<html:text property="nomeSetorComercial" size="45"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="corSetorComercial">
							<logic:empty name="GerarRelatorioReligacaoClientesInadiplentesForm" property="setorComercialCD">
								<html:text property="nomeSetorComercial" value=""
										   size="45" readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							
							<logic:notEmpty name="GerarRelatorioReligacaoClientesInadiplentesForm" property="setorComercialCD">
								<html:text property="nomeSetorComercial" size="45"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparBorracha(2);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								 border="0" title="Apagar" /></a> 
						
						<html:hidden property="setorComercialID" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><strong>Cliente:</strong></td>
					<td>
						<html:text tabindex="5" maxlength="8"
								   property="clienteID" size="10"
								   onkeypress="validaEnter(event, 'exibirGerarRelatorioReligacaoClientesInadiplentesAction.do', 'clienteID');return isCampoNumerico(event);" />
						
						<a href="javascript:chamarPopup('exibirPesquisarClienteAction.do', '', null, null, 475, 800, '');">
							<img width="23" height="21" border="0"
						         src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								 title="Pesquisar" /></a> 
						
						<logic:present name="corCliente">
							<logic:equal name="corCliente" value="exception">
								<html:text property="nomeCliente" size="35"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
	
							<logic:notEqual name="corCliente" value="exception">
								<html:text property="nomeCliente" size="35"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="corCliente">
							<logic:empty name="GerarRelatorioReligacaoClientesInadiplentesForm" property="clienteID">
								<html:text property="nomeCliente" value="" size="35"
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							
							<logic:notEmpty name="GerarRelatorioReligacaoClientesInadiplentesForm" property="clienteID">
								<html:text property="nomeCliente" size="35"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparBorracha(3);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								 border="0" title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" width="20%"><strong>Usuário:</strong></td>
					<td>
						<html:text tabindex="11" maxlength="10"
								   property="usuarioID" size="10"
								   onkeypress="validaEnter(event, 'exibirGerarRelatorioReligacaoClientesInadiplentesAction.do', 'usuarioID');return isCampoNumerico(event);" />
						
						<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do', '', null, null, 250, 495, '');">
							<img width="23" height="21" border="0"
						         src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								 title="Pesquisar" /></a> 
						
						<logic:present name="corUsuario">
							<logic:equal name="corUsuario" value="exception">
								<html:text property="nomeUsuario" size="45"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
	
							<logic:notEqual name="corUsuario" value="exception">
								<html:text property="nomeUsuario" size="45"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="corUsuario">
							<logic:empty name="GerarRelatorioReligacaoClientesInadiplentesForm" property="usuarioID">
								<html:text property="nomeUsuario" value="" size="45"
										   readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							
							<logic:notEmpty name="GerarRelatorioReligacaoClientesInadiplentesForm" property="usuarioID">
								<html:text property="nomeUsuario" size="45"
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparBorracha(4);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								 border="0" title="Apagar" /></a>
					</td>
				</tr>
				
				<tr> 
		        	<td colspan="2"><strong>Período de Encerramento da O.S.:<font color="#FF0000"></font> </strong></td>
		        	<td valign="middle">
			            <html:text maxlength="10" 
			            		   property="dataInicioEncerramento" 
			            		   size="10" tabindex="7"
			            		   onkeypress="return isCampoNumerico(event);"
			            		   onkeyup="javascript:mascaraData(this,event);replicarDataEncerramento();bloquear();"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:abrirCalendarioReplicandoComFuncaoRetorno('GerarRelatorioReligacaoClientesInadiplentesForm', 'dataInicioEncerramento', 'dataFimEncerramento', 'bloquear()');" 
			            	 width="20" border="0" 
			            	 align="middle" alt="Exibir Calendário" /><strong> a </strong> 
			            <html:text maxlength="10" 
			            		   property="dataFimEncerramento" 
			            		   size="10" tabindex="8"
			            		   onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:abrirCalendarioComFuncaoRetorno('GerarRelatorioReligacaoClientesInadiplentesForm', 'dataFimEncerramento', 'bloquear()')"
			            	 width="20" border="0" 
			            	 align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
		        	</td>
		        </tr>
		        
		        <tr> 
		        	<td colspan="2"><strong>Período de Recorrência:<font color="#FF0000"></font> </strong></td>
		        	<td valign="middle">
			            <html:text maxlength="10" 
			            		   property="dataInicioRecorrencia" 
			            		   size="10" tabindex="9"
			            		   onkeypress="return isCampoNumerico(event);"
			            		   onkeyup="javascript:mascaraData(this,event);replicarDataRecorrencia();bloquear();"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:abrirCalendarioReplicandoComFuncaoRetorno('GerarRelatorioReligacaoClientesInadiplentesForm', 'dataInicioRecorrencia', 'dataFimRecorrencia', 'bloquear()');" 
			            	 width="20" border="0" 
			            	 align="middle" alt="Exibir Calendário" /><strong> a </strong> 
			            <html:text maxlength="10" 
			            		   property="dataFimRecorrencia" 
			            		   size="10" tabindex="10"
			            		   onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:abrirCalendarioComFuncaoRetorno('GerarRelatorioReligacaoClientesInadiplentesForm', 'dataFimRecorrencia', 'bloquear()')"
			            	 width="20" border="0" 
			            	 align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
		        	</td>
		        </tr>
		        
		        <tr>
					<td colspan="2" width="20%"><strong>Tipo do Relatório:<font color="#FF0000">*</font></strong></td>
					
					<td >
					  	<html:select property="escolhaRelatorio" tabindex="11" onchange="javascript:bloquear();">
		                     <html:option value="-1">&nbsp;</html:option>
		                     <html:option value="1">Todas as ocorrências no Período</html:option>
		                     <html:option value="2">Recorrências de re-ligações para o mesmo cliente inadimplente</html:option>
		                     <html:option value="3">Recorrências por usuário que abriu a O.S</html:option>	
		                     <html:option value="4">Recorrências por usuário que encerrou a O.S</html:option>
		                     <html:option value="5">Recorrências de usuário para um mesmo cliente</html:option>	                     						  
						 </html:select>
					</td>
				</tr>

				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
			
				<tr>
					<td align="center" colspan="4" style="width: 140px;"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				
				<tr>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="24" >
					          	<input type="button" 
					          		   class="bottonRightCol" 
					          		   value="Limpar"
					          		   onclick="window.location.href='/gsan/exibirGerarRelatorioReligacaoClientesInadiplentesAction.do?menu=sim'"/>
					          	
					          	<input name="Button" type="button" 
					          		   class="bottonRightCol" value="Cancelar" 
					          		   onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							
					        </td>
					        
							<td align="right">
								<input type="button" 
									   name="Button" 
									   class="bottonRightCol" 
									   value="Gerar" 
									   onClick="javascript:validarForm()" />
							</td>
						</tr>
						
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
					</table>
				</tr>
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
								
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioReligacaoClientesInadiplentesAction.do" />	
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
