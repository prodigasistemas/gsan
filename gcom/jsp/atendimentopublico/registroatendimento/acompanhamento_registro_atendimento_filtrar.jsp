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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarAcompanhamentoRegistroAtendimentoActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<SCRIPT LANGUAGE="JavaScript">
	function reload() {
		//var form = document.forms[0];
		
		form.action = '/gsan/exibirFiltrarAcompanhamentoRegistroAtendimentoAction.do';
		form.submit();
	}

	function selecionarTipoRegistro(){
		var form = document.forms[0]; 

		if(form.situacaoRA[0].checked){
			form.periodoEncerramentoInicial.value="";
			form.periodoEncerramentoFinal.value="";
			form.periodoAtendimentoInicial.disabled=false;
			form.periodoAtendimentoFinal.disabled=false;
			form.periodoEncerramentoInicial.disabled=true;
			form.periodoEncerramentoFinal.disabled=true;
			form.situacaoRAAbertos[0].disabled=false;
			form.situacaoRAAbertos[1].disabled=false;
			form.situacaoRAAbertos[2].disabled=false;
			form.idsMotivoEncerramentoDisponiveis.disabled=true;
			
			if (form.periodoAtendimentoInicial.value != '' && form.periodoAtendimentoFinal.value != ''){
				if(comparaData(form.periodoAtendimentoInicial.value, "<", form.periodoAtendimentoFinal.value))
					alert('Data final do período de vencimento é anterior à data inicial do período de vencimento');
			}
		}
		if(form.situacaoRA[1].checked){
			form.periodoAtendimentoInicial.value="";
			form.periodoAtendimentoFinal.value="";
			form.periodoEncerramentoInicial.disabled=false;
			form.periodoEncerramentoFinal.disabled=false;
			form.periodoAtendimentoInicial.disabled=true;
			form.periodoAtendimentoFinal.disabled=true;
			form.situacaoRAAbertos[0].disabled=true;
			form.situacaoRAAbertos[0].checked=false;
			form.situacaoRAAbertos[1].disabled=true;
			form.situacaoRAAbertos[1].checked=false;
			form.situacaoRAAbertos[2].disabled=true;
			form.situacaoRAAbertos[2].checked=false;
			form.idsMotivoEncerramentoDisponiveis.disabled=false;
			
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	var unidade = 0;
	
	function setUnidade(tipo) {
		unidade = tipo;
	}
	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
		
		//form.action='exibirFiltrarRegistroAtendimentoAction.do';
	    if (tipoConsulta == 'unidadeOrganizacional') {
	      if (unidade == 1) {
		      form.unidadeAtendimentoId.value = codigoRegistro;
		      form.unidadeAtendimentoDescricao.value = descricaoRegistro;
			  form.unidadeAtendimentoDescricao.style.color = '#000000';
	      } else if (unidade == 2) {
		      form.unidadeAtualId.value = codigoRegistro;
		      form.unidadeAtualDescricao.value = descricaoRegistro;
			  form.unidadeAtualDescricao.style.color = '#000000';		      
	      }
	      unidade = 0;
	    } else if (tipoConsulta == 'unidadeSuperior') {
		      form.unidadeSuperiorId.value = codigoRegistro;
		      form.unidadeSuperiorDescricao.value = descricaoRegistro;
		      form.action = 'exibirFiltrarAcompanhamentoRegistroAtendimentoAction.do?validaUnidadeOrganizacional=true';
		      form.submit();
	    } 
	}
	
    function limparPeriodoAtendimento() {
        var form = document.forms[0];
        
        if (form.periodoAtendimentoInicial.value != ''){
			form.periodoAtendimentoInicial.value="";
		}
		
		if (form.periodoAtendimentoFinal.value != ''){
			form.periodoAtendimentoFinal.value="";
		}			
  	}

    function limparPeriodos() {
        var form = document.forms[0];
        
        if (form.periodoAtendimentoInicial.value != ''){
			form.periodoAtendimentoInicial.value="";
		}
		if (form.periodoAtendimentoFinal.value != ''){
			form.periodoAtendimentoFinal.value="";
		}			
        if (form.periodoEncerramentoInicial.value != ''){
			form.periodoEncerramentoInicial.value="";
		}
		if (form.periodoEncerramentoFinal.value != ''){
			form.periodoEncerramentoFinal.value="";
		}			
        			
  	}
	
	/* Limpar Form */
	function limparForm(){
		var form = document.forms[0];

		form.periodoAtendimentoInicial.value="";
		form.periodoAtendimentoFinal.value="";
		form.periodoEncerramentoInicial.value="";
		form.periodoEncerramentoFinal.value="";
		form.situacaoRAAbertos[0].disabled=false;
		form.situacaoRAAbertos[0].checked=false;
		form.situacaoRAAbertos[1].disabled=false;
		form.situacaoRAAbertos[1].checked=false;
		form.situacaoRAAbertos[2].disabled=false;
		form.situacaoRAAbertos[2].checked=false;
		form.idsMotivoEncerramentoDisponiveis.disabled=false;
		form.situacaoRA.value="";
		form.tipoRelatorio.value="";
		MoverTodosDadosSelectMenu2PARAMenu1('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'idsMotivoEncerramentoDisponiveis', 'idsMotivoEncerramentoSelecionados');
		limparUnidadeAtendimento();
		
		window.location.href = '/gsan/exibirFiltrarAcompanhamentoRegistroAtendimentoAction.do?menu=sim';
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
	
	function validaForm(){
		var form = document.forms[0];
		if(validateFiltrarAcompanhamentoRegistroAtendimentoActionForm(form) && validarFormatacaoNumeracaoRAManual(form.numeroRAManual, "Número Manual")){
			submeterFormPadrao(form);
		}
		
		if(validateFiltrarAcompanhamentoRegistroAtendimentoActionForm(form)){
			var unidadeAtual = trim(form.unidadeAtualId.value);
    		var unidadeSuperior = trim(form.unidadeSuperiorId.value);
    		if (unidadeAtual != '' && unidadeSuperior != '') {
    			alert('Informe somente Unidade Atual OU Unidade Superior');
    			return false;
    		}
    		
			/*if (validaQtdeRAReiteradas(form)) {
				//form.submit();
				submitForm(form);
			}*/
		}
	}

--></SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');
OnDivScroll(document.forms[0].idsMotivoEncerramentoDisponiveis, 6); OnDivScroll(document.forms[0].idsMotivoEncerramentoSelecionados, 6);">

<div id="formDiv">
<html:form action="/filtrarAcompanhamentoRegistroAtendimentoAction.do" 
		   name="FiltrarAcompanhamentoRegistroAtendimentoActionForm" 
		   type="gcom.gui.atendimentopublico.registroatendimento.FiltrarAcompanhamentoRegistroAtendimentoActionForm"
		   method="post">

<html:hidden property="selectedTipoSolicitacaoSize" />
<html:hidden property="situacaoId"/>

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
                  filtrar o registro de atendimento, informe os dados abaixo:
                </td>
            </tr>
              <tr> 
                <td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  <hr></td>
              </tr>
              
              <tr> 
                <td width="200"><strong>Registro de Atendimento:
                <font color="#FF0000">*</font></strong></td>
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
					  <html:radio property="situacaoRAAbertos" value="0" onclick="javascript:selecionarTipoRegistro();"/>
	 				  No Prazo
					  <html:radio property="situacaoRAAbertos" value="1" onclick="javascript:selecionarTipoRegistro();"/>
					  Fora do Prazo
					  <html:radio property="situacaoRAAbertos" value="2" onclick="javascript:selecionarTipoRegistro();"/>
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
					<td width="120">
						<div><strong>Motivo de Encerramento:</strong></div>
					</td>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" align="left">
						<tr>
							<td width="165">
								<strong>Disponíveis</strong>
								<div id='disponiveis2' style="OVERFLOW: auto;WIDTH: 165px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idsMotivoEncerramentoDisponiveis, 6);" >
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
								<div id='selecionados' style="OVERFLOW: auto;WIDTH: 165px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idsMotivoEncerramentoSelecionados, 6);" >
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
                  <hr></td>
              </tr>
              <tr> 
                <td><strong>Per&iacute;odo de Abertura:</strong></td>
                <td colspan="3"><span class="style2"><strong> 
					
						<html:text property="periodoAtendimentoInicial" 
						 		size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataAtendimento();" 
								onkeypress="return isCampoNumerico(event);"
								value=""/>
						<a href="javascript:abrirCalendario('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'periodoAtendimentoInicial');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/> 
						</a>
						a<html:text property="periodoAtendimentoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeyup="mascaraData(this, event);selecionarTipoRegistro();"  
									onkeypress="return isCampoNumerico(event);"
									value=""/>
						<a href="javascript:abrirCalendario('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'periodoAtendimentoFinal');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
						</a>
						</strong>(dd/mm/aaaa)<strong> 
                  </strong></span></td>
              </tr>
              <tr> 
                <td><strong>Per&iacute;odo de Encerramento:</strong></td>
                <td colspan="3"><span class="style2"><strong> 
						<html:text property="periodoEncerramentoInicial" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event);replicaDataEncerramento();"
									value=""/>
							<a href="javascript:abrirCalendario('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'periodoEncerramentoInicial');">
								<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
							</a>a<html:text property="periodoEncerramentoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event);replicaDataEncerramento();"  
									value=""/>
						<a href="javascript:abrirCalendario('FiltrarAcompanhamentoRegistroAtendimentoActionForm', 'periodoEncerramentoFinal');">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/>
						</a>
					</strong>(dd/mm/aaaa)<strong> 
                  </strong></span></td>
              </tr>
              
              <tr> 
                <td height="24" colspan="4" bordercolor="#000000" class="style1"> 
                  <hr></td>
              </tr>
              
              <tr> 
                <td><strong>Unidade de Atendimento:</strong></td>
                <td colspan="3"><span class="style2"><strong>
					<html:text property="unidadeAtendimentoId" 
							   size="4" 
							   maxlength="4"
							   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarAcompanhamentoRegistroAtendimentoAction.do?validaUnidadeAtendimento=true', 'unidadeAtendimentoId','Unidade Atendimento');return isCampoNumerico(event);"/>
					
					<a href="javascript:setUnidade(1); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limparForm=sim', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeAtendimentoId);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Unidade de Atendimento"></a>
						 
					<logic:present name="unidadeAtendimentoEncontrada" scope="session">
						<html:text property="unidadeAtendimentoDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
					</logic:present> 

					<logic:notPresent name="unidadeAtendimentoEncontrada" scope="session">
						<html:text property="unidadeAtendimentoDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
					</logic:notPresent>
					
					<a href="javascript:limparUnidadeAtendimento();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  </strong></span></td>
              </tr>
               
              <tr> 
                <td><strong>Tipo de Relat&oacute;rio:</strong></td>
                <td colspan="3">
                <span class="style2">
                  <strong> 
					  <html:radio property="tipoRelatorio" value="0" onclick="javascript:selecionarTipoRegistro;"/>
	 				  Anal&iacute;tico
					  <html:radio property="tipoRelatorio" value="1" onclick="javascript:selecionarTipoRegistro;"/>
					  Sint&eacute;tico
					  <html:radio property="tipoRelatorio" value="2" onclick="javascript:selecionarTipoRegistro;"/>
					  Ambos
				  </strong>
				</span>
			    </td>
              </tr>
              <tr>
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
	                    		onclick="validaForm();">
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
</div>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>
