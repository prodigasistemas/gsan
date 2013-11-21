<%@ page import="gcom.util.ConstantesSistema" %>
 <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript formName="RelatorioContasCanceladasRetificadasActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript">

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
		var form = document.forms[0];
	
	    if (tipoConsulta == 'localidade') {
	    	limparLocalidade();
	      	
	      	form.localidadeFiltro.value = codigoRegistro;
	     	form.nomeLocalidade.value = descricaoRegistro;
	      	form.nomeLocalidade.style.color = "#000000";
	    }
	    
	    if (tipoConsulta == 'usuario') {
	      
	    	limparResponsavel();
	      
	      	form.responsavelFiltro.value = codigoRegistro;
	      	form.nomeResponsavel.value = descricaoRegistro;
	      	form.nomeResponsavel.style.color = "#000000";
	    }
	
	}
	
	function chamarFiltrar(){
		var form = document.forms[0];
	
	  	if(form.relatorioTipo.value == ""){
	    	alert("Selecione um Tipo de Relatório!");
	    	return false;
	  	}else if(form.relatorioTipo.value == "1" && form.ordenacaoTipo.value == ""){
	    	alert("Selecione um Tipo de Ordenação!");
	    	return false;
	  	}if (form.ordenacaoTipo.value == "1"){
	   		if(form.periodoInicial != null || form.periodoInicial.value != "" || form.periodoFinal != null || form.periodoFinal.value != ""  ){
	
	    		form.periodoInicial.value = "";
	    		form.periodoFinal.value = "";
	
				if(validateRelatorioContasCanceladasRetificadasActionForm(form) ){
	  				form.submit();
	   			}
	   		} 
	  	}else if(form.ordenacaoTipo.value == "2"){
	   		if(form.periodoInicial == null || form.periodoInicial.value == "" || form.periodoFinal == null || form.periodoFinal.value == ""  ){
	    		alert("Insira todas as datas para a ordenação por datas.");
	    		form.periodoInicial.focus();
		    	return false;
	   		}else if(validateRelatorioContasCanceladasRetificadasActionForm(form) ){
	  			form.submit();
	   		}
	  	}else if(validateRelatorioContasCanceladasRetificadasActionForm(form) ){
	  		form.submit();
	  	}
	}

	function chamarReloadPagina(pagina){
		window.document.forms[0].action = pagina+"?tipoContaVar=sim"; 
		window.document.forms[0].submit();
	}

	function limparLocalidade(){
  		var form = document.forms[0];
  		form.nomeLocalidade.value='';
	}

	function limparLocalidadeBorracha(){
  		var form = document.forms[0];
  		
  		form.localidadeFiltro.value = '';
  		form.nomeLocalidade.value='';
	}

	function limparResponsavel(){
  		var form = document.forms[0];
  		form.nomeResponsavel.value='';
	}

	function limparResponsavelBorracha(){
  		var form = document.forms[0];
  		form.responsavelFiltro.value = '';
  		form.nomeResponsavel.value='';
	}

	function validaSelecaoRelatorioOrdenacao(relatorioTipo){
  		var form = document.forms[0];	
   		var obj = null;
   		if(relatorioTipo == null){
   			obj = form.relatorioTipo.value;
   		}else{
   			obj = relatorioTipo.value;
   		}
   		
   		if(obj == 1){
    		document.getElementById("relatorioOrdenacao").style.visibility = "visible" ;
   		}else{
    		document.getElementById("relatorioOrdenacao").style.visibility = "hidden" ;
    		document.getElementById("periodoBuscaData").style.visibility = "hidden";  	
   		}
   		
   		
	}

	function ordenacaoTipoVerificacao(ordenacaoTipo){
		var tipo = ordenacaoTipo;
 		var form = document.forms[0];
 
 		//verifica se o tipo de ordenacao selecionado é por data
 		if(tipo.value == "2"){
    		//caso seja por data torna a data visivel
    		document.getElementById("periodoBuscaData").style.visibility = "visible";    
 		}else if(tipo.value == "1"){
  			document.getElementById("periodoBuscaData").style.visibility = "hidden";  	
  			if (ordenacaoTipo.value == "1"){
   				if(form.periodoInicial != null ||  
   					form.periodoFinal != null ){
					if(form.periodoFinal.value != "" ){
  		 				form.periodoFinal.value = "";
					}
		
					if(form.periodoInicial.value != ""){
		 				form.periodoInicial.value = "";
					}
    			}
  			}
 		}
	}

	function replicaDataPeriodo() {
  		var form = document.forms[0];
  		form.periodoFinal.value = form.periodoInicial.value
	}

</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:validaSelecaoRelatorioOrdenacao(null);">

<html:form
  	action="/gerarRelatorioContasCanceladasRetificadasAction"
  	method="post"
  	name="RelatorioContasCanceladasRetificadasActionForm"
  	type="gcom.gui.relatorio.faturamento.RelatorioContasCanceladasRetificadasActionForm"
  	onsubmit="return validateRelatorioContasCanceladasRetificadasActionForm(this);" >

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp" %>
   

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="150" valign="top" class="leftcoltext">
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

         <%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>

          <td class="parabg">Gerar Relatório de Contas Canceladas ou Retificadas</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
        	<td width="100%" colspan=2>
	        	<table width="100%" border="0">
		          	<tr>
		          		<td>Para Gerar o relatório , informe os dados abaixo:</td>
		          		<td align="right"></td>
		          	</tr>
		          </table>
        	</td>
        </tr>
		<tr>
          <td width="25%"><strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong></td>
          <td width="75%">
          	<html:text property="mesAno" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event); return isCampoNumerico(event);"/>
          	&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
        	<td width="25%"><strong>Tipo da Conta:<font color="#FF0000">*</font></strong></td>
        	<td width="75%">
        		<html:radio property="tipoConta" value="1" onclick="chamarReloadPagina('exibirFiltrarRelatorioContasCanceladasRetificadasAction.do');">Canceladas</html:radio>
        		<html:radio property="tipoConta" value="2" onclick="chamarReloadPagina('exibirFiltrarRelatorioContasCanceladasRetificadasAction.do');">Retificadas</html:radio>
        		<html:radio property="tipoConta" value="3" onclick="chamarReloadPagina('exibirFiltrarRelatorioContasCanceladasRetificadasAction.do');">Prescritas</html:radio>
        	</td>
        </tr>
        <tr>
			<td>
				<strong>Tipo de Relatório:<font color="#FF0000">*</font></strong>
			</td>
			<td colspan="2">
				<html:select property="relatorioTipo" tabindex="1" style="width:200px;" onchange="validaSelecaoRelatorioOrdenacao(this)">
					<html:option value="1"> ANALITICO </html:option>
					<html:option value="2"> SINTETICO </html:option>				    
				</html:select>
			</td>
		</tr>	
		<tr id="relatorioOrdenacao" style="visibility:<%=session.getAttribute("visibilidade")%>">
			<td>
				<strong>Ordenação Por:<font color="#FF0000">*</font></strong>
			</td>
			<td colspan="2">
				<html:select property="ordenacaoTipo" tabindex="1" style="width:200px;" onchange="javascript:ordenacaoTipoVerificacao(this);">
				  	<html:option value="1"> INSCRIÇÃO </html:option>
					<html:option value="2"> DATA </html:option>				    
				</html:select>
			</td>
		</tr>
		<tr id="periodoBuscaData" style="visibility:<%=session.getAttribute("visibilidade2")%>"> 
                <td><strong>Período:</strong></td>
                <td colspan="5"><span class="style2"><strong> 
					<logic:equal name="RelatorioContasCanceladasRetificadasActionForm" 
						property="situacao" 
						value="1">
						
						<html:text property="periodoInicial" 
							size="11" 
							maxlength="10" 
							tabindex="3" 
							onkeypress="return isCampoNumerico(event);"
							onkeyup="mascaraData(this, event)"  
							readonly="true" 
							value=""/>
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
						a <html:text property="periodoFinal" size="11" maxlength="10" tabindex="3" onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event)"  readonly="true" value=""/>
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
					</logic:equal>
					
					<logic:notEqual name="RelatorioContasCanceladasRetificadasActionForm" property="situacao" value="1">
						<html:text property="periodoInicial" 
							size="11" 
							maxlength="10" 
							tabindex="3" 
							onkeypress="return isCampoNumerico(event);"
							onkeyup="mascaraData(this, event);replicaDataPeriodo();"/>
							
						<a href="javascript:abrirCalendario('RelatorioContasCanceladasRetificadasActionForm', 'periodoInicial');">
							<img border="0" 
								src="<bean:message key='caminho.imagens'/>calendario.gif" 
								width="16" 
								height="15" 
								border="0" 
								alt="Exibir Calendário" 
								tabindex="4"/></a>
							a 
						<html:text property="periodoFinal" 
							size="11" 
							maxlength="10" 
							tabindex="3" 
							onkeypress="return isCampoNumerico(event);"
							onkeyup="mascaraData(this, event)"/>

							<a href="javascript:abrirCalendario('RelatorioContasCanceladasRetificadasActionForm', 'periodoFinal');">
						  		<img border="0" 
						  			src="<bean:message key='caminho.imagens'/>calendario.gif" 
						  				width="16" 
						  				height="15" 
						  				border="0" 
						  				alt="Exibir Calendário" 
						  				tabindex="4"/></a>
					</logic:notEqual>
					</strong>(dd/mm/aaaa)<strong> 
                  </strong></span></td>
              </tr>	
              
              
        <tr>
			<td>
				<strong>Grupo:</strong>
			</td>

			<td>
				<strong> 
				<html:select property="grupo" style="width: 230px;" tabindex="2">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>
			
					<logic:present name="colecaoGrupo" scope="request">
					   <html:options collection="colecaoGrupo" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select> 														
				</strong>
			</td>
		</tr>      
              
        <tr>
			<td>
				<strong>Gerência Regional:</strong>
			</td>
			<td>
				<html:select property="idGerenciaRegional" tabindex="8">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<html:options collection="colecaoGerenciaRegional" property="id" labelProperty="nome" />
				</html:select>
			</td>
		</tr>
              
	   <tr>
		   <td><strong>Unidade Negócio:</strong></td>
		   <td>
				<html:select property="idUnidadeNegocio" tabindex="9">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id"/>
				</html:select>
		    </td>
   	    </tr>
        <tr>
	        <td><strong>Localidade:</strong></td>
    	    <td colspan="2">
				<html:text property="localidadeFiltro" size="5" maxlength="3" tabindex="1" 
				onkeypress="limparLocalidade();validaEnterComMensagem(event, 'exibirFiltrarRelatorioContasCanceladasRetificadasAction.do?objetoConsulta=1', 'localidadeFiltro','Localidade'); return isCampoNumerico(event);"/>
				<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 250, 495);">
					<img width="23" height="21" border="0"
					src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					title="Pesquisar Localidade" /></a>
	
				<logic:present name="codigoLocalidadeNaoEncontrada">
	
					<logic:equal name="codigoLocalidadeNaoEncontrada" value="exception">
						<html:text property="nomeLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
	
					<logic:notEqual name="codigoLocalidadeNaoEncontrada" value="exception">
						<html:text property="nomeLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>
	
				</logic:present>
	
				<logic:notPresent name="codigoLocalidadeNaoEncontrada">
	
					<logic:empty name="RelatorioContasCanceladasRetificadasActionForm" property="localidadeFiltro">
						<html:text property="nomeLocalidade" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					<logic:notEmpty name="RelatorioContasCanceladasRetificadasActionForm" property="localidadeFiltro">
						<html:text property="nomeLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
					
	
				</logic:notPresent>
	
				<a	href="javascript:limparLocalidadeBorracha();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar Localidade" /></a>
			</td>
    	</tr>
        
        <tr>
        	<td width="25%"><strong>Motivo:</strong></td>
        	<td width="75%">
        		<html:select property="idMotivo" multiple="true" size="5">
        			<html:option value="-1">&nbsp;</html:option>
        			<logic:present name="colecaoMotivoCancelamento">
						<html:options collection="colecaoMotivoCancelamento" labelProperty="descricaoMotivoCancelamentoConta" property="id"/>
					</logic:present>
					<logic:present name="colecaoMotivoRetificacao">
						<html:options collection="colecaoMotivoRetificacao" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
        	</td>
        </tr>
        
        <tr>
        	<td width="25%"><strong>Categoria:</strong></td>
        	<td width="75%">
        		<html:select property="idCategoria" multiple="true" size="5">
        			<html:option value="-1">&nbsp;</html:option>
					<html:options collection="colecaoCategoria" labelProperty="descricao" property="id"/>
				</html:select>
        	</td>
        </tr>
        
        <tr>
        	<td width="25%"><strong>Perfil do Imóvel:</strong></td>
        	<td width="75%">
        		<html:select property="idPerfil" multiple="true" size="5">
        			<html:option value="-1">&nbsp;</html:option>
					<html:options collection="colecaoPerfil" labelProperty="descricao" property="id"/>
				</html:select>
        	</td>
        </tr>
        
        <tr>
        	<td width="25%"><strong>Esfera de Poder:</strong></td>
        	<td width="75%">
        		<html:select property="idEsferaPoder" multiple="true" size="5">
        			<html:option value="-1">&nbsp;</html:option>
					<html:options collection="colecaoEsferaPoder" labelProperty="descricao" property="id"/>
				</html:select>
        	</td>
        </tr>
        
    <tr>
    		<td><strong>Responsável:</strong></td>
    	    <td colspan="2">
				<html:text property="responsavelFiltro" 
					size="7" 
					maxlength="6" 
					tabindex="1" 
					style="text-transform: none;"
					onkeypress="limparResponsavel();validaEnterStringSemUpperCase(event, 'exibirFiltrarRelatorioContasCanceladasRetificadasAction.do?objetoConsulta=1', 'responsavelFiltro'); return isCampoNumerico(event);"/>
					
				<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?indicadorUsoTodos=1', 250, 495);">
					<img width="23" 
						height="21" 
						border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Responsável" /></a>
	
				<logic:present name="codigoResponsavelNaoEncontrada">
	
					<logic:equal name="codigoResponsavelNaoEncontrada" value="exception">
						<html:text property="nomeResponsavel" 
							size="45" 
							readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
	
					<logic:notEqual name="codigoResponsavelNaoEncontrada" value="exception">
						<html:text property="nomeResponsavel" 
							size="45" 
							readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>
	
				</logic:present>
	
				<logic:notPresent name="codigoResponsavelNaoEncontrada">
	
					<logic:empty name="RelatorioContasCanceladasRetificadasActionForm" property="responsavelFiltro">
						<html:text property="nomeResponsavel" 
							size="45" 
							value="" 
							readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					<logic:notEmpty name="RelatorioContasCanceladasRetificadasActionForm" property="responsavelFiltro">
						<html:text property="nomeResponsavel" 
							size="45" 
							readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
					
	
				</logic:notPresent>
	
				<a	href="javascript:limparResponsavelBorracha();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" 
						title="Apagar Responsável" /></a>
			</td>
    </tr>
    <tr>
    	<td width="25%"><strong>Valor a partir:</strong></td>
    	<td width="75%"> <html:text property="valor" size="10" maxlength="10" onkeyup="formataValorMonetario(this, 10)"/></td>
    </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>
          	<input type="button" name="Button" class="bottonRightCol" value="Limpar" onclick="window.location.href='<html:rewrite page="/exibirFiltrarRelatorioContasCanceladasRetificadasAction.do?menu=sim"/>'">
          	<input type="button"name="ButtonCancelar" class="bottonRightCol" value="Cancelar" 
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
          </td>
          <td>
          	<div align="right">
				<input type="Button" 
					value="Gerar"
					onclick="javascript:chamarFiltrar();" 
					class="bottonRightCol"/>
			</div></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioContasCanceladasRetificadasAction" />
<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>