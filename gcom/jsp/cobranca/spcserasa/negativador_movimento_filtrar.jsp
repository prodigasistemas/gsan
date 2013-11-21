<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>


<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<html:javascript staticJavascript="false"  formName="FiltrarNegativadorMovimentoActionForm" />	
			
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">


	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
			form.indicadorAtualizar.checked = true;
		}else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	function limpar(){
		form = document.forms[0];

		limparImovel();

		form.idNegativador.value = '-1';
		form.numeroSequencialArquivo.value = '';
		form.dataProcessamentoInicial.value = '';
		form.dataProcessamentoFinal.value = '';
		form.action = 'exibirFiltrarNegativadorMovimentoAction.do?menu=sim';
        form.submit();
	}
		
	function DateValidations () {
    	this.aa = new Array("dataProcessamentoInicial", "Data de Processamento Inicial não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     	this.ab = new Array("dataProcessamentoFinal", "Data de Processamento Final não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }
	
	function validateFiltrarNegativadorMovimentoActionForm() {                                                                   
    	var form = document.forms[0];       

       	if(validateDate(form)){
    		submeterFormPadrao(form);
		}
   } 

	function verificarMovimentoRegistroChecado(valor){
		form = document.forms[0];
		if( valor==1){	
			form.movimentoCorrigido[0].disabled = true;
         	form.movimentoCorrigido[1].disabled = true;
     	}else{
     	 	form.movimentoCorrigido[0].disabled = false;
         	form.movimentoCorrigido[1].disabled = false;
     	}
	}
	
	
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.readOnly != true){
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
	
	
	function habilitarPesquisaImovel(form) {
		if (form.idImovel.readOnly == false) {
			chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',form.idImovel.value);
		}	
	}


	function validaEnterImovel(event, caminho, campo) {		
		retorno = validaEnter(event, caminho, campo);			
	}

	function limparImovel() {
		if (document.forms[0].nomeImovel != undefined){
	 		document.forms[0].nomeImovel.value = '';
	 	}
	 	document.forms[0].idImovel.value = ''; 	
	}

	function habilitarPesquisaEloPolo(form) {
		chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',form.idEloPolo.value);
	}
	
	function habilitarPesquisaLocalidade(form) {
		chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',form.idLocalidade.value);
	}
	
	function validaEnterEloPolo(tecla, caminhoActionReload, nomeCampo) {
		var form = document.forms[0];
		validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Elo Pólo");
	}
	
	function validaEnterLocalidade(tecla, caminhoActionReload, nomeCampo) {
		var form = document.forms[0];
		validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código da Localidade");
	}

	function limparForm(tipo){
    	var form = document.forms[0];
		if(tipo == 'eloPolo') {
			form.idEloPolo.value = "";
   	    	form.descricaoEloPolo.value = "";
   	    	form.setOkEloPolo.value = "true";
		}
		if(tipo == 'localidade') {
	    	form.idLocalidade.value = "";
   	    	form.descricaoLocalidade.value = "";
   	    	form.setOkLocalidade.value = "true";
		}
	}

	function reload(){
	
		form = document.forms[0];
		form.action = 'exibirFiltrarNegativadorMovimentoAction.do';
        form.submit()
	}

</script>
</head>

<body leftmargin="0" topmargin="0" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarNegativadorMovimentoAction"
	name="FiltrarNegativadorMovimentoActionForm"
	type="gcom.gui.cobranca.spcserasa.FiltrarNegativadorMovimentoActionForm"
	method="post">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp" %>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="120" valign="top" class="leftcoltext">
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
			
			<td valign="top" class="centercoltext">
				<p>&nbsp;</p>
	            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              		<tr>
	                	<td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
	                	<td class="parabg">Consultar Movimento de Negativador</td>
	                	<td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
	              	</tr>
	            </table>

            	<p>&nbsp;</p>
            	
            	<table width="100%" border="0" >
              		<tr> 
                		<td colspan="4">Para consultar movimento de negativador, informe 
                  		os dados abaixo:</td>
              		</tr>
	              	
	              	<tr> 
	                	<td width="274">
	                		<strong>Negativador:<font color="#FF0000"></font></strong>
	                	</td>
	                	
	                	<td width="337" colspan="3">
	                		<logic:present name="colecaoNegativador">  
	            	 			<html:select property="idNegativador" tabindex="7" onchange="reload();">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					   				<logic:present name="colecaoNegativador">
							 		<html:options collection="colecaoNegativador" labelProperty="cliente.nome" property="id"/>
					    			</logic:present>
								</html:select>
	         		 		</logic:present>    
	         			 </td>
	              	</tr>
              		
              		<tr> 
	                	<td width="274">
	                		<strong>Tipo do Movimento:</strong>
	                	</td>
	                	<td colspan="3">
	                  		<html:radio property="tipoMovimento" value="1"/>    
	                  		<strong>Inclus&atilde;o</strong>
	                   		<html:radio property="tipoMovimento" value="2"/> 
							<strong>Exclus&atilde;o</strong>
					   		<html:radio property="tipoMovimento" value="3" /> 
							<strong>Todos</strong>
						</td>
              		</tr>
             
             		<tr>

						<td width="25%"><strong>Matrícula do Imóvel:</strong></td>
						
						<td width="75%">
							<html:text maxlength="9"
								name="FiltrarNegativadorMovimentoActionForm"
								property="idImovel" 
								size="9"
								onkeypress="javascript: return validaEnter(event, 'exibirFiltrarNegativadorMovimentoAction.do', 'idImovel'); "
								onkeyup="somente_numero(this);"/>
							<a href="javascript:habilitarPesquisaImovel(document.forms[0]);">
								<img width="23" 
									height="21"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									border="0" 
									title="Pesquisar Imóvel" /></a> 
							<logic:equal property="imovelNaoEncontrado" name="FiltrarNegativadorMovimentoActionForm" value="true">
								<input type="text" 
									name="nomeImovel" 
									size="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									value="<bean:message key="atencao.imovel.inexistente"/>" />
									
							</logic:equal> 
							
							<logic:equal property="imovelNaoEncontrado" name="FiltrarNegativadorMovimentoActionForm" value="false">
								<html:text size="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									name="FiltrarNegativadorMovimentoActionForm"
									property="nomeImovel" />
							</logic:equal> 
							
							<a href="javascript:limparImovel();"> 
								<img border="0" 
									src="imagens/limparcampo.gif" 
									height="21" 
									width="23" 
									title="Apagar Imóvel"></a>
						</td>
					</tr>
              
              		<tr> 
                		<td><strong>N&uacute;mero Sequencial do Arquivo (NSA):</strong></td>
                		<td colspan="3">
	                  		<html:text 
	                  			property="numeroSequencialArquivo" 
	                  			size="9" 
	                  			maxlength="9"
	                  			onkeypress="return isCampoNumerico(event);"/>    
                  		</td>
              		</tr>
              
              
	              	<tr> 
	                	<td height="25"><strong>Per&iacute;odo de Processamento do Movimento:</strong></td>
	               	 	<td colspan="3">
	                   		<html:text property="dataProcessamentoInicial" 
	                   			size="10" 
	                   			maxlength="10"
								tabindex="8" 
								onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dataProcessamentoFinal, document.forms[0].dataProcessamentoInicial);"  
								onkeypress="return isCampoNumerico(event);"/> 
							<a href="javascript:abrirCalendarioReplicando('FiltrarNegativadorMovimentoActionForm', 'dataProcessamentoInicial','dataProcessamentoFinal')">
					    		<img border="0"
									src="<bean:message key="caminho.imagens"/>calendario.gif"
									width="20" 
									border="0" 
									align="absmiddle" 
									alt="Exibir Calendário" /></a>
							<strong>a</strong> 
	                		<html:text property="dataProcessamentoFinal" 
	                			size="10" 
	                			maxlength="10"
								tabindex="8" 
								onkeyup="mascaraData(this, event); " 
								onkeypress="return isCampoNumerico(event);" /> 
							<a href="javascript:abrirCalendario('FiltrarNegativadorMovimentoActionForm', 'dataProcessamentoFinal')">
					    		<img border="0"
									src="<bean:message key="caminho.imagens"/>calendario.gif"
									width="20" 
									border="0" 
									align="absmiddle" 
									alt="Exibir Calendário" /></a> 
							(dd/mm/aaaa)  
						</td>
	              	</tr>
              
	               	<tr> 
	                  <td> <strong>Ger&ecirc;ncia Regional: </strong></td>
	                  <td>
	                  	<logic:present name="collGerenciaRegional">  
	                   	   	<html:select property="arrayGerenciaRegional" tabindex="7" multiple="true" size="4">
							    <html:option value="">&nbsp;</html:option>
								<logic:iterate name="collGerenciaRegional" id="gerenciaRegional" >
									 <option value="<bean:write name="gerenciaRegional" property="id" />" >
									 	<bean:write name="gerenciaRegional" property="nome" />
									 </option>
								</logic:iterate>
							</html:select>
	                	</logic:present>
	                  	</td>
	                </tr>
                	
                	<tr>
                  		<td><strong>Unidade de Neg&oacute;cio: </strong></td>
                  		<td>
              	   	  		<logic:present name="collUnidadeNegocio">  
                   	   			<html:select property="arrayUnidadeNegocio" tabindex="7" multiple="true" size="4">
							    	<html:option value="">&nbsp;</html:option>
									<logic:iterate name="collUnidadeNegocio" id="unidadeNegocio" >
										<option value="<bean:write name="unidadeNegocio" property="id" />" >
											<bean:write name="unidadeNegocio" property="nome" />
										</option>
								 	</logic:iterate>
								</html:select>
                			</logic:present>
				  		</td>
                	</tr>
                	
                	<tr> 
                  		<td><strong> Localidade Pólo:</strong></td>
                  		<td>
                  			<html:text property="idEloPolo" 
                  				maxlength="3" 
                  				size="3" 
                  				onkeyup="return validaEnterEloPolo(event, 'exibirFiltrarNegativadorMovimentoAction.do', 'idEloPolo'); " />
							<a href="javascript:habilitarPesquisaEloPolo(document.forms[0]);" 
								alt="Pesquisar Elo Polo">
								<img width="23" 
									height="21" 
									src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
									border="0" 
									title="Pesquisar Localidade Pólo" /></a>

							<logic:present name="corEloPolo">
								
								<logic:equal name="corEloPolo" value="exception">
									<html:text property="descricaoEloPolo" 
										size="38"	
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								
								<logic:notEqual name="corEloPolo" value="exception">
									<html:text property="descricaoEloPolo" 
										size="38"	
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							
							<logic:notPresent name="corEloPolo">
								<logic:empty name="FiltrarNegativadorMovimentoActionForm"	property="idEloPolo">
									<html:text property="descricaoEloPolo" 
										size="38" 
										value="" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								<logic:notEmpty name="FiltrarNegativadorMovimentoActionForm" property="idEloPolo">
									<html:text property="descricaoEloPolo" 
										size="38"	
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent>
							
							<a href="javascript:limparForm('eloPolo');"> 
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
										style="cursor: hand;" 
										title="Apagar Localidade Pólo"/></a>
                  		</td>
               	 	</tr>
                	
                	<tr> 
                  		<td><strong>Localidade:</strong></td>
                  		<td>
                  			<html:text property="idLocalidade" 
                  				maxlength="3" 
                  				size="3" 
                  				onkeyup="return validaEnterLocalidade(event, 'exibirFiltrarNegativadorMovimentoAction.do', 'idLocalidade'); " />
							<a href="javascript:habilitarPesquisaLocalidade(document.forms[0]);" 
								alt="Pesquisar Localidade">
								<img width="23" 
									height="21" 
									src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
									border="0" 
									title="Pesquisar Localidade"/></a>
							
							<logic:present name="corLocalidade">
								<logic:equal name="corLocalidade" value="exception">
									<html:text property="descricaoLocalidade" 
										size="38"	
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
								
								<logic:notEqual name="corLocalidade" value="exception">
									<html:text property="descricaoLocalidade" 
										size="38"	
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							
							<logic:notPresent name="corLocalidade">
								
								<logic:empty name="FiltrarNegativadorMovimentoActionForm"	property="idLocalidade">
									<html:text property="descricaoLocalidade" 
										size="38" 
										value="" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty name="FiltrarNegativadorMovimentoActionForm" property="idLocalidade">
									<html:text property="descricaoLocalidade" 
										size="38"	
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent>
							
							<a href="javascript:limparForm('localidade');"> 
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
									style="cursor: hand;" 
									title="Apagar Localidade" /></a>
                  		</td>
                	</tr>
              
	              	<tr> 
		                <td><strong> Movimentos:</strong></td>
		                <td colspan="3">
		                	<html:radio property="movimento" value="1"/> 
		                  	<strong>Com Retorno </strong>
		                    <html:radio property="movimento" value="2"/> 
		                  	<strong>Sem Retorno</strong>
						    <html:radio property="movimento" value="3"/> 
							<strong>Todos</strong>
						</td>
	              	</tr>
              		
              		<tr> 
		              	<td><strong>Movimentos com Registros:</strong></td>
		                <td colspan="3">
		                	<html:radio property="movimentoRegistro" value="1" onclick="javascript:verificarMovimentoRegistroChecado(1);reload();"/> 
		                  	<strong>Aceitos</strong>
		                    <html:radio property="movimentoRegistro" value="2" onclick="javascript:verificarMovimentoRegistroChecado(2);reload();"/> 
		                  	<strong>N&atilde;o Aceitos</strong>
		                   <html:radio property="movimentoRegistro" value="3" onclick="javascript:verificarMovimentoRegistroChecado(3);reload();"/> 
							<strong>Todos</strong>
						</td>
             	 	</tr>
              
	              	<logic:present name="collMotivoRejeicao"> 
						<tr> 
	                  		<td><strong>Motivos de Rejeição:<font color="#FF0000"></font></strong></td>
			               	<td>
			                	<html:select property="arrayMotivoRejeicao" tabindex="10" multiple="true" size="4" style="width:350px;" >
									<html:option value="">&nbsp;</html:option>
									<logic:iterate name="collMotivoRejeicao" id="motivoRejeicao" >
										<option value="<bean:write name="motivoRejeicao" property="id" />" >
											<bean:write name="motivoRejeicao" property="descricaoRetornocodigo" />
										</option>
									</logic:iterate>
								</html:select>
			              	</td>
	                	</tr>
	       		  	</logic:present>
              
               		<tr> 
		                <td><strong>Movimentos com Registros:</strong></td>
		                <td colspan="3">
		                	<html:radio property="movimentoCorrigido" value="1" /> 
		                  	<strong>Corrigidos</strong>
		                    <html:radio property="movimentoCorrigido" value="2"/> 
		                  	<strong>N&atilde;o Corrigidos</strong>
		                    <html:radio property="movimentoCorrigido" value="3"/> 
							<strong>Todos</strong>
						</td>
              		</tr>
              
	              	<tr> 
	              		<td align="left">
	              			<input type="button" 
	              			name="ButtonReset"
							class="bottonRightCol" 
							value="Limpar"
							onclick="javascript:limpar();"></td>
	                	
	                	<td colspan="4">
	                		<div align="right"> 
	                    		<input type="button" 
	                    			class="bottonRightCol" 
	                    			value="Filtrar" 
	                    			onclick="javascript:validateFiltrarNegativadorMovimentoActionForm();">
	                  		</div>
	                  	</td>
	              	</tr>
            	</table>
          		<p>&nbsp;</p>
          	</td>
        </tr>
	</table>
                 
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
