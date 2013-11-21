<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="FiltrarAcompanhamentoRegistroAtendimentoActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	function selecionarTipoRegistro(){
		var form = document.forms[0]; 
		
		if(form.situacaoRA[0].checked){
			
			form.periodoAtendimentoInicial.disabled = false;
			form.periodoAtendimentoFinal.disabled = false;
		
			form.periodoEncerramentoInicial.value = "";
			form.periodoEncerramentoFinal.value = "";
			form.periodoEncerramentoInicial.disabled = true;
			form.periodoEncerramentoFinal.disabled = true;
			
			form.situacaoRAAbertos[0].checked = false;
			form.situacaoRAAbertos[1].checked = false;
			form.situacaoRAAbertos[2].checked = true;
			form.situacaoRAAbertos[0].disabled = false;
			form.situacaoRAAbertos[1].disabled = false;
			form.situacaoRAAbertos[2].disabled = false;
			
			form.botao1.disabled = true;
			form.botao2.disabled = true;
			form.botao3.disabled = true;
			form.botao4.disabled = true;
			
			form.idsMotivoEncerramentoDisponiveis.disabled = true;
			
			MoverTodosDadosSelectMenu2PARAMenu1('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'idsMotivoEncerramentoDisponiveis', 'idsMotivoEncerramentoSelecionados');
		
		}else if(form.situacaoRA[1].checked){
			
			form.situacaoRAAbertos[0].disabled = true;
			form.situacaoRAAbertos[1].disabled = true;
			form.situacaoRAAbertos[2].disabled = true
			form.situacaoRAAbertos[0].checked = false;
			form.situacaoRAAbertos[1].checked = false;
			form.situacaoRAAbertos[2].checked = false;
		
			form.periodoAtendimentoInicial.disabled = true;
			form.periodoAtendimentoFinal.disabled = true;
			form.periodoAtendimentoInicial.value = "";
			form.periodoAtendimentoFinal.value = "";

			form.periodoEncerramentoInicial.disabled = false;
			form.periodoEncerramentoFinal.disabled = false;
			
			form.idsMotivoEncerramentoDisponiveis.disabled = false;
			
			form.botao1.disabled = false;
			form.botao2.disabled = false;
			form.botao3.disabled = false;
			form.botao4.disabled = false;
			
			MoverTodosDadosSelectMenu2PARAMenu1('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'idsMotivoEncerramentoDisponiveis', 'idsMotivoEncerramentoSelecionados');
		}	
		
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
					//abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
					abrirPopupComSubmit(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
		
	    if (tipoConsulta == 'unidadeOrganizacional') {
			form.unidadeAtendimentoId.value = codigoRegistro;
		    form.unidadeAtendimentoDescricao.value = descricaoRegistro;
			form.unidadeAtendimentoDescricao.style.color = '#000000';
	    }
	}
	
	/* Limpar Form */
	function limparForm(){
		
		window.location.href = '/gsan/exibirFiltrarAcompanhamentoRegistroAtendimentoAction.do?menu=sim';
	}

	/* Limpar Unidade Atendimento */
	function limparUnidadeAtendimento() {
		
		var form = document.forms[0];
		
      	form.unidadeAtendimentoId.value = '';
	    form.unidadeAtendimentoDescricao.value = '';
		form.unidadeAtendimentoId.focus();
	}

	/* Replica Data de Atendimento */
	function replicaDataAtendimento() {
		
		var form = document.forms[0]; 
		form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value;
	}
	
	/* Replica Data de Encerramento */	
	function replicaDataEncerramento() {
		
		var form = document.forms[0];
		form.periodoEncerramentoFinal.value = form.periodoEncerramentoInicial.value;
	}
	
	function MoveSelectedDataFromMenu1TO2(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.meioSolicitacao.value != '-1') {
			MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);
		}
	}
	
	function MoveSelectedDataFromMenu2TO1(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.meioSolicitacao.value != '-1') {
			MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		}
	}
	
	function validaForm(){
		
		var form = document.forms[0];
		
		if(validateFiltrarAcompanhamentoRegistroAtendimentoActionForm(form)){
			
			if(RadioNaoVazioMensagemPorNome("Registro de Atendimento","situacaoRA")){
				
				enviarSelectMultiplo('FiltrarAcompanhamentoRegistroAtendimentoActionForm','idsMotivoEncerramentoSelecionados');

				if (form.situacaoRA[0].checked && form.periodoAtendimentoInicial.value != '' && form.periodoAtendimentoFinal.value != ''){
					if( validaData(form.periodoAtendimentoInicial) && validaData(form.periodoAtendimentoFinal)){
						if ( comparaData(form.periodoAtendimentoInicial.value, "<", form.periodoAtendimentoFinal.value )
								|| comparaData(form.periodoAtendimentoInicial.value, "=", form.periodoAtendimentoFinal.value) ){
			  				
			  				//enviarSelectMultiplo('FiltrarAcompanhamentoRegistroAtendimentoActionForm','idsMotivoEncerramentoSelecionados');
			  				
			  				if(form.opcaoRelatorio[0].checked || form.opcaoRelatorio[1].checked){
			  					
			  					toggleBox('divAux',1);			
								toggleBox('demodiv',1);
			  				}
			  				
			  				//submeterFormPadrao(form);
			  			}else{
			  				alert('Data final do período de abertura é anterior à data inicial do período de abertura.');			
			  			}
			  		}
				}else if (form.situacaoRA[0].checked){
					alert('Informar o Período de Abertura da RA.');
				}
				
				if (form.situacaoRA[1].checked && form.periodoEncerramentoInicial.value != '' && form.periodoEncerramentoFinal.value != ''){
					if(validaData(form.periodoEncerramentoInicial) && validaData(form.periodoEncerramentoFinal)){
						if ( comparaData(form.periodoEncerramentoInicial.value, "<", form.periodoEncerramentoFinal.value )
								|| comparaData(form.periodoEncerramentoInicial.value, "=", form.periodoEncerramentoFinal.value) ){
						
			  				if (form.idsMotivoEncerramentoSelecionados.value != "" && form.idsMotivoEncerramentoSelecionados.value != "-1" ){
				  				//enviarSelectMultiplo('FiltrarAcompanhamentoRegistroAtendimentoActionForm','idsMotivoEncerramentoSelecionados');
				  				
				  				if(form.opcaoRelatorio[0].checked || form.opcaoRelatorio[1].checked){
				  				
				  					toggleBox('divAux',1);			
									toggleBox('demodiv',1);
								}
								
				  				//submeterFormPadrao(form);
			  				}else{
			  					alert('Informar o(s) Motivo(s) de Encerramento.');
			  				}
			  			}else{
			  				alert('Data final do período de encerramento é anterior à data inicial do período de encerramento.');			
			  			}
			  		}
				}else if (form.situacaoRA[1].checked){
					alert('Informar o Período de Encerramento da RA.');
				}
			}
		}
	}
	
	
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="OnDivScroll(document.forms[0].idsMotivoEncerramentoDisponiveis, 6); 
OnDivScroll(document.forms[0].idsMotivoEncerramentoSelecionados, 6); selecionarTipoRegistro();">

<div id="formDiv">
<html:form action="/gerarRelatorioAcompanhamentoRegistroAtendimentoAction.do" 
		   name="FiltrarAcompanhamentoRegistroAtendimentoActionForm" 
		   type="gcom.gui.atendimentopublico.registroatendimento.FiltrarAcompanhamentoRegistroAtendimentoActionForm"
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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Filtrar Relat&oacute;rio de Acompanhamento dos RA's</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
			  <tr> 
                	<td colspan="5" bordercolor="#000000" class="style1">Para 
                  		selecionar Registro de Atendimento para geração do relatório de acompanhamento, 
                  		informe os dados abaixo:
                	</td>
              </tr>
	          <tr> 
	              	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
	                  	<hr>
	                </td>
			  </tr>
              
              <tr> 
              		<td ><strong>Registro de Atendimento:<font color="#FF0000">*</font></strong></td>
                	<td colspan="3">
                		<span class="style2">
		                  <strong> 
							  <html:radio property="situacaoRA" value="0" onclick="javascript:selecionarTipoRegistro();"/>
			 				  Abertos
							  <html:radio property="situacaoRA" value="1" onclick="javascript:selecionarTipoRegistro();"/>
							  Encerrados
						  </strong>
						</span>
			    	</td>
              </tr>
              <tr> 
              		<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  		<hr>
                	</td>
			  </tr>
			  
			  <tr> 
                	<td><strong>Situa&ccedil;&atilde;o dos RA`s Abertos:</strong></td>
                	<td colspan="3">
		                <span class="style2">
		                  <strong> 
							  <html:radio property="situacaoRAAbertos" value="0"/>
			 				  No Prazo
							  <html:radio property="situacaoRAAbertos" value="1"/>
							  Fora do Prazo
							  <html:radio property="situacaoRAAbertos" value="2"/>
							  Ambos
						  </strong>
						</span>
			    	</td>
              </tr>
              <tr> 
                	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  		<hr>
                	</td>
			  </tr>
              <tr>
					<td >
						<div><strong>Motivo de Encerramento:</strong></div>
					</td>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" align="left">
						<tr>
							<td width="165">
								<strong>Disponíveis</strong>
								<div id='disponiveis2' style="OVERFLOW: auto;WIDTH: 180px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idsMotivoEncerramentoDisponiveis, 6);" >
									<html:select property="idsMotivoEncerramentoDisponiveis" 
											 	 size="6" 
											 	 multiple="true"
											 	 style="width: 400px;" >
										<logic:notEmpty name="colecaoAtendimentoMotivoEncerramento">
											<html:options collection="colecaoAtendimentoMotivoEncerramento" 
														  labelProperty="descricao" 
														  property="id"/>
										</logic:notEmpty>
									</html:select>
								</div>
							</td>
							
							
							<td width="5" valign="center"><br>
								<table width="50" align="center" border="0">
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol" 
												name="botao1"
												onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'idsMotivoEncerramentoDisponiveis', 'idsMotivoEncerramentoSelecionados');"
												value=" &gt;&gt; ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												name="botao2"
												onclick="javascript:MoverDadosSelectMenu1PARAMenu2('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'idsMotivoEncerramentoDisponiveis', 'idsMotivoEncerramentoSelecionados');"
												value=" &nbsp;&gt;  ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												name="botao3"
												onclick="javascript:MoverDadosSelectMenu2PARAMenu1('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'idsMotivoEncerramentoDisponiveis', 'idsMotivoEncerramentoSelecionados');"
												value=" &nbsp;&lt;  ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												name="botao4"
												onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'idsMotivoEncerramentoDisponiveis', 'idsMotivoEncerramentoSelecionados');"
												value=" &lt;&lt; ">
										</td>
									</tr>
								</table>
							</td>

							<td align="left">
								<strong>Selecionados</strong>
								<div id='selecionados' style="OVERFLOW: auto;WIDTH: 180px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idsMotivoEncerramentoSelecionados, 6);" >
								<html:select property="idsMotivoEncerramentoSelecionados" 
											 size="6" 
											 multiple="true"
											 style="width: 400px;" >
										<logic:notEmpty name="colecaoAtendimentoMotivoEncerramentoSelecionado">
											<html:options collection="colecaoAtendimentoMotivoEncerramentoSelecionado" 
												          labelProperty="descricao" 
														  property="id"/>
										</logic:notEmpty>
									
								</html:select>
								</div>
							</td>
						</tr>
					</table>
					</td>
			  </tr>
              
              <tr> 
                	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  		<hr>
                  	</td>
              </tr>
              <tr> 
                	<td><strong>Per&iacute;odo de Abertura:</strong></td>
                	<td colspan="3"><span class="style2"><strong> 
					
						<html:text property="periodoAtendimentoInicial" 
						 		size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataAtendimento();" 
								onkeypress="return isCampoNumerico(event);" />
						<a href="javascript:abrirCalendarioReplicando('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'periodoAtendimentoInicial', 'periodoAtendimentoFinal');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
						 a <html:text property="periodoAtendimentoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeyup="mascaraData(this, event);"  
									onkeypress="return isCampoNumerico(event);" />
						<a href="javascript:abrirCalendario('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'periodoAtendimentoFinal');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
						</strong>(dd/mm/aaaa)</span>
					</td>
              </tr>
              <tr> 
                	<td><strong>Per&iacute;odo de Encerramento:</strong></td>
	                <td colspan="3"><span class="style2"><strong> 
							<html:text property="periodoEncerramentoInicial" 
										size="11" 
										maxlength="10" 
										tabindex="3" 
										onkeypress="return isCampoNumerico(event);"
										onkeyup="mascaraData(this, event); replicaDataEncerramento();" />
								<a href="javascript:abrirCalendarioReplicando('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'periodoEncerramentoInicial', 'periodoEncerramentoFinal');">
									<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
								 a <html:text property="periodoEncerramentoFinal" 
										size="11" 
										maxlength="10" 
										tabindex="3" 
										onkeypress="return isCampoNumerico(event);"
										onkeyup="mascaraData(this, event);" />
							<a href="javascript:abrirCalendario('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'periodoEncerramentoFinal');">
								<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
							</strong>(dd/mm/aaaa)</span>
					</td>
              </tr>
              
              <tr> 
                	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  		<hr>
                  	</td>
              </tr>
              
              <tr> 
                	<td><strong>Unidade de Atendimento:<font color="#FF0000"></font></strong></td>
                	<td colspan="3"><span class="style2"><strong>
					<html:text property="unidadeAtendimentoId" 
							   size="4" 
							   maxlength="4"
							   onkeypress="javascript:validaEnter(event, 'exibirFiltrarAcompanhamentoRegistroAtendimentoAction.do', 'unidadeAtendimentoId'); return isCampoNumerico(event);"/>
					
						<a href="javascript:chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limparForm=sim', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeAtendimentoId);">
						<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Unidade Atendimento"/></a>
							 
						<logic:present name="unidadeInexistente" 
   		      						   scope="request">
							<input type="text" 
								   name="unidadeAtendimentoDescricao" 
								   size="50" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="atencao.unidade.organizacional.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="unidadeInexistente" 
                      					scope="request">
	                       	<html:text property="unidadeAtendimentoDescricao" 
	                       			   size="50" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>	 
						
						<a href="javascript:limparUnidadeAtendimento();">
							<img border="0" title="Apagar" 
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
	                </strong></span>
                  	</td>
              </tr>
              
              <tr> 
               	<td><strong>Município:</strong></td>
               	<td colspan="6">
              			<strong>
					<html:select property="municipiosAssociados" style="width: 350px; height:100px;" 	multiple="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoMunicipioAssociado" scope="request">
							<html:options collection="colecaoMunicipioAssociado" labelProperty="nome" property="id" />
						</logic:present>
					</html:select>                
                		</strong>
                	 </td>
           	  </tr> 
              
              <tr> 
                	<td><strong>Tipo de Relat&oacute;rio:</strong></td>
                	<td colspan="3">
                <span class="style2">
                  <strong> 
					  <html:radio property="opcaoRelatorio" value="0"/>
	 				  Anal&iacute;tico
					  <html:radio property="opcaoRelatorio" value="1"/>
					  Sint&eacute;tico
				  </strong>
				</span>
			    </td>
              </tr>
             
              <tr> 
                <td>&nbsp;</td>
                <td colspan="3" align="right">&nbsp;</td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td colspan="3"> <div align="right"> </div></td>
              </tr>
              
              <tr> 
                <td>
                	<input type="button" name="adicionar2" 
                			class="bottonRightCol" 
                			value="Limpar" 
                			onclick="javascript:limparForm();">
                	<input type="button" name="adicionar" 
			                class="bottonRightCol" 
			                value="Cancelar" 
			                onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
                </td>
                
                <td colspan="3"> 
	                <div align="right"> 
	                    <input type="button" name="Submit" 
	                    		class="bottonRightCol" 
	                    		value="Gerar" 
	                    		onclick="javascript:validaForm();">
	                </div>
                </td>
              </tr>      
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<div id="divAux" style="position:absolute; left:110px; top:300px; width:200px; height:70px;">
	  <jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=/gsan/gerarRelatorioAcompanhamentoRegistroAtendimentoAction.do"/>
	</div>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>
