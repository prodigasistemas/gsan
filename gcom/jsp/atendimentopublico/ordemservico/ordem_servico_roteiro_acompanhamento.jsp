<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="java.math.BigDecimal"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>



<script language="JavaScript">
	
	function selecionouEquipe(){

		var form = document.forms[0];
	    var selecionados = form.elements['equipesSelecionadas'];
		var jaSelecionado = false;
		var retorno = false;
		
		for (i=0; i< selecionados.length; i++) {

			if(selecionados[i].checked){

				if(jaSelecionado == false){
					jaSelecionado = true;
					retorno = selecionados[i].value;
				}else if(selecionados[i].value != retorno){
					alert('Marque apenas uma Equipe');
					return false;
				}
			}
		}

		if(jaSelecionado == false){
			alert('É necessário marcar alguma equipe');
			return false;
		}
		
		return retorno;
	}

	function selecionouOrdemServico(){
		var form = document.forms[0];
	    var selecionados = form.elements['osSelecionada'];
		var jaSelecionado = false;
		var retorno = false;
		

		if(selecionados.length == null){
			if(selecionados.checked){
				retorno = selecionados.value;
				jaSelecionado = true;
			}
		}else{

			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
	
					if(jaSelecionado == false){
						jaSelecionado = true;
						retorno = selecionados[i].value;
					}else{
						alert('Marque apenas uma Ordem de Serviço');
						return false;
					}
				}
			}
		}
		
		if(jaSelecionado == false){
			alert('É necessário marcar alguma Ordem de Serviço');
			return false;
		}
		
		return retorno;
	}

	function abrirAlerta(chaveEquipe,idOs){
   		var form = document.forms[0];
		
		var url = "exibirAcompanharRoteiroProgramacaoOrdemServicoAlertaAction.do?chaveEquipe="+chaveEquipe+"&idOs="+idOs;
		abrirPopup(url,400,600);

    }


	function extendeTabela(tabela,display){
		var form = document.forms[0];
		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}
	function consultarOs(idOs){
		var url = 'exibirConsultarDadosOrdemServicoPopupAction.do?numeroOS='+idOs;
		abrirPopup(url);
	}

	function consultarRa(idRa){
		abrirPopup("exibirConsultarRegistroAtendimentoPopupAction.do?numeroRA="+idRa);
	}
	

	function voltar(){
   		var form = document.forms[0];

   		form.action = "exibirCalendarioElaboracaoAcompanhamentoRoteiroAction.do?menu=sim";
   		form.submit();
		
    }
	
	function incluirOS(){
		chave = selecionouEquipe();
		if(chave != false){
			var url = 'exibirAcompanharRoteiroProgramacaoOrdemServicoIncluirAction.do?chave='+chave;
			abrirPopup(url,400,600);
		}
	}

	function retornaValor(chave){
		myString = new String(chave);
		splitString = myString.split("___");
		//Primeira posição idOs	
		return splitString[0];
	}
	
	function retornaValorEquipe(chave){
		myString = new String(chave);
		splitString = myString.split("___");
			
		//Segunda posição nomeEquipe
		return splitString[1];
	}

	function alocarEquipes(){
		chave = selecionouOrdemServico();
		if(chave != false){

			chave = retornaValor(chave);
		
			var url = 'exibirAcompanharRoteiroProgramacaoOrdemServicoAlocaEquipeAction.do?chave='+chave;
			abrirPopup(url,400,600);
		}
	}

	function remanejarEquipe(){
		chave = selecionouOrdemServico();
		if(chave != false){
			chave = retornaValor(chave);

			var url = 'exibirAcompanharRoteiroProgramacaoOrdemServicoRemanejarEquipeAction.do?chave='+chave;
			abrirPopup(url,400,600);
		}
	}
	
	function informarSituacao(){
		var form = document.forms[0];
	
		chave = selecionouOrdemServico();
		if(chave != false){
			// chave = retornaValor(chave);
			
			myString = new String(chave);
			splitString = myString.split("___");
			
			chave = splitString[0];
			nomeEquipeSemEspaco = splitString[1];
			
			var chaveEquipe = eval('form.'+nomeEquipeSemEspaco).value;
					
			var url = 'exibirAcompanharRoteiroProgramacaoOrdemServicoInformarSituacaoAction.do?chave='+chave+'&chaveEquipe='+chaveEquipe;
			abrirPopup(url,400,600);
		}
	}
	
	function informarDadosAtividades(){
   		var form = document.forms[0];
		chave = selecionouOrdemServico();
		if(chave != false){
			chave = retornaValor(chave);
			var url = 'exibirManterDadosAtividadesOrdemServicoAction.do?caminhoRetorno=exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do&numeroOS='+chave+ '&dataRoteiro=' + form.dataRoteiro.value;
			abrirPopup(url,600,680);
		}
	}
	
	
	function atualizarOs(){
		chave = selecionouOrdemServico();
		if(chave != false){
			chave = retornaValor(chave);		

			var url = 'exibirAtualizarOrdemServicoAction.do?retornoTela=exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do&primeiraVez=1&ehPopup=true&numeroOS='+chave;
			abrirPopup(url,400,600);
		}
	}
	
	function reordenarSequencial(){
   		var form = document.forms[0];

		chave = selecionouOrdemServico();
		if(chave != false){

			var sequencial = eval('form.sequencial'+chave).value;						

			myString = new String(chave);
			splitString = myString.split("___");

			chave = splitString[0];
			nomeEquipeSemEspaco = splitString[1];
			

			var chaveEquipe = eval('form.'+nomeEquipeSemEspaco).value;

			var url = 'exibirAcompanharRoteiroProgramacaoOrdemServicoReordenarAction.do?chave='+
				chave+'&sequencial='+sequencial+'&chaveEquipe='+chaveEquipe;
				
			abrirPopup(url,400,600);
			
		}
	}

	function consultarEquipe(){

		chave = selecionouEquipe();
		if(chave != false){
			var url = 'exibirConsultarDadosEquipeAction.do?ehPopup=true&chave='+chave;
			abrirPopup(url,400,600);
		}
	}


	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];
	    
		if(tipoConsulta == 'incluirOrdemServico'){

	    	form.idOrdemServico.value = descricaoRegistro[0];
	    	form.sequencialProgramacaoPrevisto.value = descricaoRegistro[1];
	    	form.sequencialProgramacao.value = descricaoRegistro[2];
	
	    	form.action='exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do?tipoAcao=I';
		    form.submit();
		
		}else if(tipoConsulta == 'alocaEquipe'){

	    	form.equipeSelecionada.value	= descricaoRegistro[0];
	    	form.idEquipePrincipal.value 	= descricaoRegistro[1];
	    	form.idOrdemServico.value 	= descricaoRegistro[2];
	
	    	form.action='exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do?tipoAcao=A';
		    form.submit();
		
		}else if(tipoConsulta == 'remanejaEquipe'){

	    	form.idEquipePrincipal.value = descricaoRegistro[0];
	    	form.idEquipeAtual.value	= descricaoRegistro[1];
	    	form.idOrdemServico.value 	= descricaoRegistro[2];
	
	    	form.action='exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do?tipoAcao=R';
		    form.submit();
		
		}else if(tipoConsulta == 'situacaoOs'){

	    	form.situacaoOrdemServico.value = descricaoRegistro[0];
	    	form.motivoNaoEncerramento.value = descricaoRegistro[1];
	    	form.idOrdemServico.value = descricaoRegistro[2];
	    	form.chaveEquipe.value = descricaoRegistro[3];
	
	    	form.action='exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do?tipoAcao=S';
		    form.submit();

		
		}else if(tipoConsulta == 'reordenarProgramacao'){

	    	form.sequencialProgramacaoPrevisto.value = descricaoRegistro[0];
	    	form.sequencialProgramacao.value = descricaoRegistro[1];
	    	form.chaveEquipe.value = descricaoRegistro[2];
	
	    	form.action='exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do?tipoAcao=P';
		    form.submit();
		}

	}
	
	function imprimirOS(){

		/*chave = selecionouOrdemServico();
		if(chave != false){
			chave = retornaValor(chave);		

	    	window.location.href='gerarRelatorioOrdemServicoAction.do?idOrdemServico='+chave;
		}*/

   		var form = document.forms[0];
		var chaves = montarChaveOs();

		if(chaves != null && chaves != '' && chaves != 'undefined'){
			window.location.href='gerarRelatorioOrdemServicoAction.do?idsOS='+chaves;
		}else{
			alert('É necessário marcar alguma Ordem de Serviço');
			return false;
		}
	}


	function montarChaveOs(){
   		var form = document.forms[0];

		var selecionados = form.elements['osSelecionada'];
		var chave = "";
		
		for (j=0; j< selecionados.length; j++) {

			if(selecionados[j].checked){
				
				var valorObjeto = retornaValor(selecionados[j].value);

				if(chaveJaSelecionada(valorObjeto,chave) == false){
					if(chave != ""){
						chave = chave+"$"+valorObjeto;
					}else{
						chave = valorObjeto;
					}
				}
			}
		}
		
		if(chave == ""){
			if(selecionados.length == null && selecionados.checked){
				chave = retornaValor(selecionados.value);
			}
		}
		return chave;		
	}
	
	
	function imprimirEquipes(){
	
   		var form = document.forms[0];
		
		var chave = form.chavesRelatorio.value;
		if(chave != null && chave != '' && chave != 'undefined'){
			toggleBox('demodiv',1);
		}else{
			alert('É necessário marcar alguma equipe');
			return false;
		}
		
	}
	
	
	
	function montarChaveEquipe(objeto){
		
		var form = document.forms[0];
		
		var retorno = form.chavesRelatorio.value;
		var valorObjeto = objeto.value;
		var selecionados = form.elements['equipesSelecionadas'];
		
		if(objeto.checked){

			if(!chaveJaSelecionada(valorObjeto,retorno)){
				if(retorno != null && retorno != '' && retorno != 'undefined'){
					retorno = retorno+"$"+valorObjeto;
				}else{
					retorno = valorObjeto;
				}
			}

		    
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].value == valorObjeto){
					selecionados[i].checked = true;
				}
			}		    
		    
		} else {

			myString = new String(form.chavesRelatorio.value);
			splitString = myString.split("$");
			
			for (i=0; i< splitString.length; i++) {

				if(splitString[i] == valorObjeto){
					splitString.splice( i, 1 );
					break;
				}
			}
			
			retorno = "";
			for (i=0; i< splitString.length; i++) {
				if(retorno == ""){
					retorno = splitString[i];
				}else{
					retorno = retorno+"$"+splitString[i];
				}
			}

			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].value == valorObjeto){
					selecionados[i].checked = false;
				}
			}
		}

		form.chavesRelatorio.value = retorno;
	}

	function chaveJaSelecionada(valorObjeto,chavePesquisa){

		var retorno = false;
		var form = document.forms[0];

		if(chavePesquisa != null && chavePesquisa != ""){

			myString = new String(chavePesquisa);
			splitString = myString.split("$");

			for (i=0; i< splitString.length; i++) {
				if(splitString[i] == valorObjeto){
					retorno = true;
					break;
				}
			}
		}

		return retorno;
	}
	
	function facilitador(objeto,nomeEquipe){

		if (objeto.value == "0"){

			objeto.value = "1";
			checkedTodosPorEquipe(nomeEquipe,false);

		}else{
			objeto.value = "0";
			checkedTodosPorEquipe(nomeEquipe,true);	
		}
	}
	
	function checkedTodosPorEquipe(nomeEquipe,checked){

		var form = document.forms[0];
	    var selecionados = form.elements['osSelecionada'];

		if(selecionados.length == null){
			selecionados.checked = checked;
		}else{
			for (i=0; i< selecionados.length; i++) {

				var valor = retornaValorEquipe(selecionados[i].value);
				if(valor == nomeEquipe){
					selecionados[i].checked = checked;
				}
			}
		}
	}

 	

</script>

</head>

<body leftmargin="5" topmargin="5">
	
<html:form action="/exibirAcompanharRoteiroProgramacaoOrdemServicoIncluirAction.do"
	name="AcompanharRoteiroProgramacaoOrdemServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AcompanharRoteiroProgramacaoOrdemServicoActionForm"
	method="post">
	
	<html:hidden property="numeroRA"/>
	<html:hidden property="idOrdemServico"/>
	<html:hidden property="sequencialProgramacaoPrevisto"/>	
	
	<html:hidden property="equipeSelecionada"/>
	
	<html:hidden property="idEquipePrincipal"/>	
	<html:hidden property="idEquipeAtual"/>
	
	<html:hidden property="situacaoOrdemServico"/>
	<html:hidden property="motivoNaoEncerramento"/>
	<html:hidden property="sequencialProgramacao"/>
	<html:hidden property="chaveEquipe"/>
	<html:hidden property="dataRoteiro"/>
	<html:hidden property="chavesRelatorio"/>

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

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Acompanhamento de Programa&ccedil;&atilde;o de Ordens de Servi&ccedil;o</td>
					<td width="11">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Roteiro do dia 
                        <strong>
							<bean:write name="AcompanharRoteiroProgramacaoOrdemServicoActionForm" property="dataRoteiro"/>
						</strong>:
					</td>
				</tr>

				<tr>
					<td colspan="3">
                  	<table width="100%" border="0" >
                    	<tr bgcolor="#cbe5fe"> 
                      		<td width="27%" rowspan="2" bgcolor="#99CCFF">
                      			<div align="center"><strong>Equipe</strong></div>
                      		</td>
                      		
                      		<td colspan="3" bgcolor="#99CCFF"> 
                      			<div align="center"></div>
		                        <div align="center"></div>
		                        <div align="center"></div>
		                        <div align="center"></div>
                        		<div align="center"><strong>Carga de Tabalho</strong></div>
                        	</td>
                    	</tr>
                    	
                    	<tr bgcolor="#cbe5fe"> 
                      		<td colspan="2" bgcolor="#99CCFF">
                      			<div align="center">
                      			<strong>Prevista</strong>
                      			</div>
                      		</td>
                      		<td width="38%" bgcolor="#99CCFF">
                      			<div align="center">
									<strong>Realizada</strong>
								</div>
							</td>
                    	</tr>
                  	</table>
                  	</td>
                </tr>

				<tr bgcolor="#cbe5fe"> 
                  	<td colspan="3">
					
					<logic:iterate name="mapOsProgramacaoHelper" id="osProgramacaoHelper" >
						
						<bean:define id="idEquipe" 
							name="osProgramacaoHelper" 
							property="key"
							type="String"/>

						<bean:define id="nomeEquipe" 
							name="osProgramacaoHelper" 
							property="key"
							type="String"/>

						<%	nomeEquipe = nomeEquipe.replace("-","");	%>
						<%	nomeEquipe = nomeEquipe.replace(" ","");	%>						

						<bean:define id="percentualTrabalhoPrevista" 
							name="<%="percentualTrabalhoPrevista"+nomeEquipe%>"
							type="BigDecimal"
							scope="session"/>

						<bean:define id="percentualTrabalhoRealizada" 
							name="<%="percentualTrabalhoRealizada"+nomeEquipe%>"
							type="BigDecimal"
							scope="session"/>
							
       					<div id="layerHide<%=nomeEquipe%>" style="display:block">

                  			<table border="0" width="100%">
                    			
		                       	<tr  bgcolor="#99CCFF">
	                            	<td>
					                  	<table width="100%" border="0" >
					                    	<tr> 
					                      		<td width="15%">
													<input type="checkbox"
														name="equipesSelecionadas" 
														value="<%=idEquipe%>" 
														onchange="javascript:montarChaveEquipe(this);"/>
					                      		</td>
					                      		
					                      		<td>
			                     					<a href="javascript:extendeTabela('<%=nomeEquipe%>',true);"/>
			                     						<b><bean:write name="osProgramacaoHelper" property="key"/></b>
			                     					</a>
					                        	</td>
					                    	</tr>
										</table>	                            	
	                            	</td>
	                            	
	                            	<td width="35%">
	                            		<div align="center">
											
			                        		<c:choose>
		                        				<c:when test="${percentualTrabalhoPrevista gt 100.00}">
				                               		<input name="cargaTrabalhoPrevista" 
				                               			type="text" 
				                               			disabled 
				                               			style="background-color:red; border:0"
				                               			size="15" 
				                               			maxlength="15">
			                        			</c:when>
			                        			<c:otherwise>
					                        		<c:choose>			                        			
				                        				<c:when test="${percentualTrabalhoPrevista gt 90.00}">
						                               		<input name="cargaTrabalhoPrevista" 
						                               			type="text" 
						                               			disabled 
						                               			style="background-color:yellow; border:0" 
						                               			size="15" 
						                               			maxlength="15">
					                        			</c:when>
					                        			<c:otherwise>
						                               		<input name="cargaTrabalhoPrevista" 
						                               			type="text" 
						                               			disabled 
						                               			style="background-color:green; border:0"
						                               			size="15" 
						                               			maxlength="15">
					                        			</c:otherwise>
				                        			</c:choose>
			                        			</c:otherwise>
		                        			</c:choose>
		                               		
		                               		<input name="percentualTrabalhoPrevista"
		                               			type="text" 
		                               			disabled 
		                               			style="background-color:#EFEFEF; border:0" 
		                               			size="7" 
		                               			maxlength="7"
		                               			value="<%=percentualTrabalhoPrevista.toString()%>%">
	                            		</div>
									</td>
	
	                            	<td width="38%">
	                            		<div align="center">
			                        		<c:choose>
		                        				<c:when test="${percentualTrabalhoRealizada gt 100.00}">
				                               		<input name="cargaTrabalhoRealizada" 
				                               			type="text" 
				                               			disabled 
				                               			style="background-color:red; border:0" 
				                               			size="15" 
				                               			maxlength="15">
			                        			</c:when>
			                        			<c:otherwise>
					                        		<c:choose>			                        			
				                        				<c:when test="${percentualTrabalhoRealizada gt 90.00}">
						                               		<input name="cargaTrabalhoRealizada" 
						                               			type="text" 
						                               			disabled 
						                               			style="background-color:yellow; border:0" 
						                               			size="15" 
						                               			maxlength="15">
					                        			</c:when>
					                        			<c:otherwise>
						                               		<input name="cargaTrabalhoRealizada" 
						                               			type="text" 
						                               			disabled 
						                               			style="background-color:green; border:0"
						                               			size="15" 
						                               			maxlength="15">
					                        			</c:otherwise>
				                        			</c:choose>				                        			
			                        			</c:otherwise>
		                        			</c:choose>
	                            		
		                               		<input name="percentualTrabalhoRealizada" 
		                               			type="text" 
		                               			disabled 
		                               			style="background-color:#EFEFEF; border:0" 
		                               			size="7" 
		                               			maxlength="7"
		                               			value="<%=percentualTrabalhoRealizada.toString()%>%">
	                            		</div>
									</td>
		                     	</tr>
                    			
                   			</table>
           				</div>
					
                  		<div id="layerShow<%=nomeEquipe%>" style="display:none">

	                  		<table border="0" width="100%">
	                  	
		                       	<tr  bgcolor="#99CCFF">
	                            	<td width="27%" height="18">
	                            		
					                  	<table width="100%" border="0" >
					                    	<tr> 
					                      		<td width="15%">
													<input type="checkbox"
														name="equipesSelecionadas" 
														value="<%=idEquipe%>" 
														onchange="javascript:montarChaveEquipe(this);"/>
					                      		</td>
					                      		
					                      		<td>
			                     					<a href="javascript:extendeTabela('<%=nomeEquipe%>',false);"/>
			                     						<b><bean:write name="osProgramacaoHelper" property="key"/></b>
			                     					</a>
					                        	</td>
					                    	</tr>
										</table>	                            	
	                            	</td>
	                            	
	                            	<td width="35%">
	                            		<div align="center">
											
			                        		<c:choose>
		                        				<c:when test="${percentualTrabalhoPrevista gt 100.00}">
				                               		<input name="cargaTrabalhoPrevista" 
				                               			type="text" 
				                               			disabled 
				                               			style="background-color:red; border:0" 
				                               			size="15" 
				                               			maxlength="15">
			                        			</c:when>
			                        			<c:otherwise>
					                        		<c:choose>			                        			
				                        				<c:when test="${percentualTrabalhoPrevista gt 90.00}">
						                               		<input name="cargaTrabalhoPrevista" 
						                               			type="text" 
						                               			disabled 
						                               			style="background-color:yellow; border:0" 
						                               			size="15" 
						                               			maxlength="15">
					                        			</c:when>
					                        			<c:otherwise>
						                               		<input name="cargaTrabalhoPrevista" 
						                               			type="text" 
						                               			disabled 
						                               			style="background-color:green; border:0"
						                               			size="15" 
						                               			maxlength="15">
					                        			</c:otherwise>
				                        			</c:choose>				                        			
			                        			</c:otherwise>
		                        			</c:choose>
		                               		
		                               		<input name="percentualTrabalhoPrevista"
		                               			type="text" 
		                               			disabled 
		                               			style="background-color:#EFEFEF; border:0" 
		                               			size="7"
		                               			maxlength="7"
		                               			value="<%=percentualTrabalhoPrevista.toString()%>%">
	                            		</div>
									</td>
	
	                            	<td width="38%">
	                            		<div align="center">
			                        		<c:choose>
		                        				<c:when test="${percentualTrabalhoRealizada gt 100.00}">
				                               		<input name="cargaTrabalhoRealizada" 
				                               			type="text" 
				                               			disabled 
				                               			style="background-color:red; border:0" 
				                               			size="15" 
				                               			maxlength="15">
			                        			</c:when>
			                        			<c:otherwise>
					                        		<c:choose>			                        			
				                        				<c:when test="${percentualTrabalhoRealizada gt 90.00}">
						                               		<input name="cargaTrabalhoRealizada" 
						                               			type="text" 
						                               			disabled 
						                               			style="background-color:yellow; border:0" 
						                               			size="15" 
						                               			maxlength="15">
					                        			</c:when>
					                        			<c:otherwise>
						                               		<input name="cargaTrabalhoRealizada" 
						                               			type="text" 
						                               			disabled 
						                               			style="background-color:green; border:0"
						                               			size="15" 
						                               			maxlength="15">
					                        			</c:otherwise>
				                        			</c:choose>				                        			
			                        			</c:otherwise>
		                        			</c:choose>
	                            		
		                               		<input name="percentualTrabalhoRealizada" 
		                               			type="text" 
		                               			disabled 
		                               			style="background-color:#EFEFEF; border:0" 
		                               			size="7" 
		                               			maxlength="7"
		                               			value="<%=percentualTrabalhoRealizada.toString()%>%">
	                            		</div>
									</td>
	                            	
		                     	</tr>
								
								<tr bgcolor="#cbe5fe"> 
		                        	<td colspan="3">

									<input type="hidden"
										name="<%=nomeEquipe%>"
										value="<%=idEquipe%>" />
		                        	 
		                        	<table border="0" width="100%" >
		                            	<tr  bgcolor="#99CCFF"> 
			                              
			                            	<td rowspan="2">
			                              		<div align="center">
												<strong><a href="javascript:facilitador(this,'<%=nomeEquipe%>')">Todos</a></strong>
			                              		</div>
			                              	</td>
			                              
			                              	<td width="5%" rowspan="2">
			                              		<div align="center"><strong>Seq.</strong></div>
			                              	</td>
			                              
			                              	<td colspan="2">
			                              		<div align="center"><strong>Dias Atr.</strong></div>
			                              	</td>
	
			                              	<td width="4%" rowspan="2">
			                              		<div align="center"><strong>Pri.</strong></div>
			                              	</td>
	
			                              	<td width="2%" rowspan="2">
			                              		<div align="center"><strong>Situa&ccedil;&atilde;o</strong></div>
			                              	</td>
	
			                              	<td width="11%" rowspan="2"><div align="center"><strong>No. 
			                                  do RA</strong></div>
			                                </td>
	
			                              	<td width="5%" rowspan="2">
			                              		<strong>Serv.</strong>
			                              	</td>
			                              	<td width="34%" rowspan="2">
			                              		<div align="center"><strong>Endere&ccedil;o</strong></div>
			                              	</td>
	
			                              	<td width="7%" rowspan="2">
			                              		<div align="center"><strong>Alerta</strong></div>
			                              	</td>
		                                </tr>
	
		                                <tr  bgcolor="#99CCFF"> 
		                                  	<td width="9%"><strong>Cliente</strong></td>
		                                  	<td width="8%"><strong>Ag.Reg</strong></td>
		                                </tr>
	

										<c:set var="count" value="0"/>
	
	              						<logic:iterate name="osProgramacaoHelper" property="value" id="osProgramacao" >
											
											<bean:define id="idOs" 
												name="osProgramacao" 
												property="ordemServicoProgramacao.ordemServico.id" 
												type="Integer"/>
												
		                     		  		<c:set var="count" value="${count+1}"/>
		
			                        		<c:choose>
		                        				<c:when test="${count%2 == '1'}">
			                        				<tr bgcolor="#FFFFFF">
			                        			</c:when>
			                        			<c:otherwise>
			                        				<tr bgcolor="#cbe5fe">
			                        			</c:otherwise>
		                        			</c:choose>
	
	                              			<td bordercolor="#90c7fc">
	                              				<div align="center">
													<input type="checkbox"
														name="osSelecionada" 
														value="<%=idOs+"___"+nomeEquipe%>" />
	                                  			</div>
	                                  		</td>
	                                  		
											<input type="hidden"
												name="sequencial<%=idOs+"___"+nomeEquipe%>"
												value="<bean:write name="osProgramacao" property="ordemServicoProgramacao.nnSequencialProgramacao" />"/>

	                              			<td height="25" bordercolor="#90c7fc">
	                              				<div align="center">
													<bean:write name="osProgramacao" property="ordemServicoProgramacao.nnSequencialProgramacao" />
	                                  			</div>
	                              			</td>
			                              	
	                                  		<td width="7%" bordercolor="#90c7fc">
	                                  			<div align="center">
	                                  				<bean:write name="osProgramacao" property="diasAtrasoCliente" />
	                                  			</div>
	                                  		</td>
	
	                                  		<td width="7%" bordercolor="#90c7fc">
	                                  			<div align="center">
	                                  				<bean:write name="osProgramacao" property="diasAtrasoAgencia" />
	                                  			</div>
	                                  		</td>
	
	                                  		<td bordercolor="#90c7fc">
												<logic:notEmpty name="osProgramacao" property="ordemServicoProgramacao.ordemServico.servicoTipoPrioridadeAtual">
													<div align="center">													
														
													<bean:write name="osProgramacao" 
															property="ordemServicoProgramacao.ordemServico.servicoTipoPrioridadeAtual.id"/>
													</div>
												</logic:notEmpty>
	                                  		</td>

	                                  		<td bordercolor="#90c7fc">
	                                  			<div align="center">
	                                  				<bean:write name="osProgramacao" property="situacao" />
	                                  			</div>
	                               			</td>
	                                  		
	                                  		<td>
		                                  		<logic:notEmpty name="osProgramacao" property="ordemServicoProgramacao.ordemServico.registroAtendimento">
		                                  			<div align="center">
				                        				<a  title="Consultar Dados do Registro de Atendimento" 
				                        					href="javascript:consultarRa('<bean:write name="osProgramacao" property="ordemServicoProgramacao.ordemServico.registroAtendimento.id" />');">
			                                  				<bean:write name="osProgramacao" property="ordemServicoProgramacao.ordemServico.registroAtendimento.id" />
			                                  			</a>
	                                  				</div>
	                                  			</logic:notEmpty>
	                                  		</td>
	                                  		
	                                  		<td>
												<logic:notEmpty name="osProgramacao" property="ordemServicoProgramacao.ordemServico.servicoTipo">
													
													<div align="center">
				                        			<a  title="Consultar Dados da Ordem de Serviço" 
				                        				href="javascript:consultarOs('<bean:write name="osProgramacao" property="ordemServicoProgramacao.ordemServico.id"/>');">
													
														<bean:write name="osProgramacao" property="ordemServicoProgramacao.ordemServico.servicoTipo.id"/>
													</a>
													</div>
												</logic:notEmpty>
	                                  		</td>
	
	                                  		<td>
												<logic:notEmpty name="osProgramacao" property="endereco">
													<bean:write name="osProgramacao" property="endereco"/>
												</logic:notEmpty>
	                                  		</td>
			                              	
			                              	<td bordercolor="#90c7fc">
			                              		<div align="center"> 
													<logic:equal name="osProgramacao" property="temAlerta" value="true" >
													
													<a href="javascript:abrirAlerta('<%=idEquipe%>', '<%=idOs.toString()%>');">
			                       						<img src="imagens/alerta.gif" 
			                       							width="14" 
			                       							border="0"
			                       							height="14"></a>
	                           						
	                           						</logic:equal>
			                              		</div>
			                              	</td>
	
	              						</logic:iterate>
		                                
				                  	</table>
				                  	</td>
	                			</tr>
	                  		</table>
	                  	</div>
                    </logic:iterate>
                  	</td>
                  	
                  	
                </tr>

              	<tr> 
                	<td colspan="2"> 
                	<table width="100%">
                    	<tr> 
                      		<td colspan="2">
                      			<input name="ButtonIncluirOS"
                      				type="button"
                      				class="bottonRightCol"
									onClick="javascript:incluirOS();"
                      				value="Incluir OS">
                        			
                        		<input name="ButtonAlocarEquipes"
                        			type="button"
                        			class="bottonRightCol"
									onClick="javascript:alocarEquipes();"
                        			value="Alocar Equipes para OS">

                      			<input name="ButtonRemanejarEquipes"
                      				type="button"
                      				class="bottonRightCol"
                      				onClick="javascript:remanejarEquipe();"
                      				value="Remanejar OS">

                        		<input name="ButtonAtualizarOs" 
                        			type="button"
                        			class="bottonRightCol"
                      				onClick="javascript:atualizarOs();"
                        			value="Atualizar OS">

							</td>
                    	</tr>
                    	
                    	<tr> 
                      		<td colspan="2">

                        		<input name="ButtonInformarSituacao" 
                        			type="button" 
                        			class="bottonRightCol" 
                      				onClick="javascript:informarSituacao();"
                        			value="Informar Situa&ccedil;&atilde;o da OS na Programa&ccedil;&atilde;o">

                        		<input name="ButtonReordenar" 
                        			type="button"
                        			class="bottonRightCol"
                      				onClick="javascript:reordenarSequencial();"
                        			value="Reordenar Sequencial">

							</td>
                    	</tr>

                    	<tr> 
                      		<td colspan="2">

                      			<input name="ButtonDadosAtividades"
                      				type="button"
                      				class="bottonRightCol"
                      				onClick="javascript:informarDadosAtividades();"
                      				value="Dados das Atividades da OS">

                        		<input name="ButtonConsultarEquipe" 
                        			type="button"
                        			class="bottonRightCol"
                      				onClick="javascript:consultarEquipe();"
                        			value="Consultar Equipe">
							</td>
                    	</tr>

                    	<tr> 
                      		<td colspan="2"> 
                      				
                        		<input name="ButtonImprimirOs"
                        			type="button"
                        			class="bottonRightCol"
                        			onClick="javascript:imprimirOS();"
                        			value="Imprimir OS">
                        		
                        		<input name="ButtonImprimirEquipes"
                        			type="button"
                        			class="bottonRightCol"
                        			onClick="javascript:imprimirEquipes();"
                        			value="Imprimir Roteiro">

							</td>
                    	</tr>

                    	<tr> 
                      		<td align="left"> 
								<input type="button" 
									name="Button" 
									class="bottonRightCol"
									value="Voltar" 
									onClick="javascript:voltar();" />
                      		</td>
                    	</tr>
                  	</table>
                  	</td>
              	</tr>

				
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<jsp:include
	page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioRoteiroProgramacaoAction.do" />

<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
