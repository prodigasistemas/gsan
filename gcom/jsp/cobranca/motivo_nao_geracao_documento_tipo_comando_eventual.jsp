<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<script>
	function validarFormSubmit(){
    	var form = document.MotivoNaoGeracaoDocumentoActionForm;
    	
    	if (validarCombinacao()){
    		
    		if (form.matriculaImovel.value != ''){
    			form.action = '/gsan/exibirMotivoNaoGeracaoDocumentoTipoComandoEventualAction.do?filtroPorImovel=true';
    		}else{
    			if (form.indicadorTipoRelatorio[0].checked){
	    			form.action = '/gsan/gerarRelatorioMotivoNaoGeracaoDocumentoEventualAction.do?filtroPorComandoSintetico=true';
    			}else{
    				form.action = '/gsan/gerarRelatorioMotivoNaoGeracaoDocumentoEventualAction.do?filtroPorComandoAnalitico=true';
    			}
    		}
    		
    		form.submit();
    	}
	}
	
	function verificarTipoSelecao1(){
	
		var form = document.MotivoNaoGeracaoDocumentoActionForm;
		form.matriculaImovel.disabled= true;
		form.matriculaImovel.value="";
		form.descricaoMotivo.value = "";
		
	}
	
	function verificarTipoSelecao2(){
		
		var form = document.MotivoNaoGeracaoDocumentoActionForm;
		form.matriculaImovel.disabled= false;
		
	}
	
	function validarCombinacao(){
		var retorno = true;
		var form = document.MotivoNaoGeracaoDocumentoActionForm;
		
		if (form.indicadorTipoPesquisa[1].checked && (form.matriculaImovel.value == null || form.matriculaImovel.value == '')){
			alert('Informe a Matrícula do Imóvel');
			retorno = false;
		}else if (form.idCobrancaAcaoAtividadeComando == null || form.idCobrancaAcaoAtividadeComando.selectedIndex == 0){
			alert('Informe Título do Comando');
			retorno = false;
		}
		
		return retorno;
	}
	
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		    abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	 }
	 
	 function pesquisarSetorComercial(){
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value,document.forms[0].idLocalidade.value,'Localidade', 400, 800);
	 }

	 function pesquisarQuadra(){
	 	    abrirPopupDependencia('exibirPesquisarQuadraAction.do', document.forms[0].idSetorComercial.value, 'Setor Comercial', 275, 480);
	 }
	
	 
	 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
	 	if(tipoConsulta == "localidade"){
	 		form.idLocalidade.value = codigoRegistro;
	 		form.descricaoLocalidade.value = descricaoRegistro;
	 		form.descricaoLocalidade.style.color = "#000000";
	 	}
		else if(tipoConsulta == "setorComercial"){
			form.idSetorComercial.value = codigoRegistro;
	 		form.descricaoSetorComercial.value = descricaoRegistro;
	 		form.descricaoSetorComercial.style.color = "#000000";
		}
		else if(tipoConsulta == "Quadra"){
			form.idQuadra.value = codigoRegistro;
	 		form.descricaoQuadra.value = descricaoRegistro;
	 		form.descricaoQuadra.style.color = "#000000";
		}
	}
	 
	function validarOpcoesGeograficas(){
		document.forms[0].action = "exibirMotivoNaoGeracaoDocumentoTipoComandoEventualAction.do?pesquisarComando=OK";
		document.forms[0].submit();		
	} 
	
	function limparConjunto(num){
		var form = document.forms[0];
		
		if(num == 1){
			form.idLocalidade.value = "";
			form.descricaoLocalidade.value = "";
		}
		else if(num == 2){
			form.idSetorComercial.value = "";
			form.descricaoSetorComercial.value = "";
			
		}
	}
		
	
</script>
<head>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarDocumentosCobrancaActionForm" />
	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body leftmargin="5" topmargin="5">

	<html:form action="/gerarRelatorioMotivoNaoGeracaoDocumentoCronogramaAction.do"
	name="MotivoNaoGeracaoDocumentoActionForm"
	type="gcom.gui.relatorio.cobranca.MotivoNaoGeracaoDocumentoActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Consultar Motivo da n&atilde;o Gera&ccedil;&atilde;o
				 de Documento de Cobran&ccedil;a - Cronograma</td>
	
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
					<tr>
						<td height="5" colspan="3"></td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td>
							<p>Informe os dados abaixo:</p>
							<p>&nbsp;</p>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%">
								<tr>
									<td><strong>T&iacute;tulo do Comando:<font color="#FF0000">*</font></strong></td>
									<td><html:select property="idCobrancaAcaoAtividadeComando"
										onchange="javascript:validarOpcoesGeograficas()"
										style="width: 510px;">
										<logic:present name="colecaoCobrancaAcaoAtividadeComando">
											<html:option value="" />
											<html:options collection="colecaoCobrancaAcaoAtividadeComando"
												labelProperty="descricaoTitulo" property="id" />
										</logic:present>
									</html:select></td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							<table width="100%">
								<tr>
									<td align="left" width="180px">
										<strong>Por Comando:</strong>
											<html:radio property="indicadorTipoPesquisa" value="1" onclick="javascript:verificarTipoSelecao1();" />
									</td>
									<td align="left">
										<strong>Sintético</strong>
											<html:radio property="indicadorTipoRelatorio" value="1" />
									</td>
									<td align="left">
										<strong>Analítico</strong>
											<html:radio property="indicadorTipoRelatorio" value="2" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr> 
	                <td width="100"><strong>Gerência Regional:</strong></td>
		                <td colspan="2">
				            <div align="left">
				                <strong>
					                <html:select   property="gerenciaRegional">
						                <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						                <logic:present name="colecaoGerenciaRegional" scope="session">
						            		<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
						            	</logic:present>
									</html:select>
				                </strong>
				            </div>
		            	</td>
	           		</tr>
	    	        <tr> 
	                <td width="100"><strong>Unidade Negócio:</strong></td>
		                <td colspan="2">
				            <div align="left">
				                <strong>
					                <html:select property="unidadeNegocio">
						                <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						                <logic:present name="colecaoUnidadeNegocio" scope="session">
						            		<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
						            	</logic:present>
									</html:select>
				                </strong>
				            </div>
		            	</td>
	            	</tr>

		 	        <tr>
		                <td><strong>Localidade :</strong></td>
		                <td colspan="2"><strong>
			                <html:text maxlength="3"
			                  			 size="3"
			                  			 tabindex="1"
			                  			 property="idLocalidade"
			                  			 readonly="${sessionScope.coordenadas}"
			                  			 onkeypress="javascript:validaEnterComMensagem(event, 'exibirMotivoNaoGeracaoDocumentoTipoComandoEventualAction.do?pesquisarLocalidade=OK', 'idLocalidade','Localidade');return isCampoNumerico(event);" />
			                <a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 400, 800, '',document.forms[0].idLocalidade);">
								<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								     title="Pesquisar Localidade" /></a>
							<logic:notPresent name="localidadeException" scope="request">	     
				     			<html:text
				     					   maxlength="40"
				     					   size="40"
				     					   readonly="true"
				     					   property="descricaoLocalidade"
		   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
			     			</logic:notPresent>
			     			<logic:present name="localidadeException" scope="request">
				     			<html:text
				     					   maxlength="40"
				     					   size="40"
				     					   readonly="true"
				     					   property="descricaoLocalidade"
				     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
			     			</logic:present>	     
			     			</strong>
			     			<a href="javascript:limparConjunto(1);">
								<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
							</a>
		     		</td>
	                </tr>
              
      		       <tr>
	                <td><strong>Setor Comercial :</strong></td>
	                <td colspan="2"><strong>
		                <html:text maxlength="3"
		                  			 size="3"
		                  			 tabindex="1"
		                  			 property="idSetorComercial"
		                  			 readonly="${sessionScope.coordenadas}"
	 	                  			 onkeypress="javascript:validaEnterDependencia(event, 'exibirMotivoNaoGeracaoDocumentoTipoComandoEventualAction.do?pesquisarSetorComercial=OK', this, document.forms[0].idLocalidade.value, 'Localidade');return isCampoNumerico(event);" />
		                    <a href="javascript:pesquisarSetorComercial()">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							     title="Pesquisar Setor Comercial" /></a>
						<logic:notPresent name="setorComercialException" scope="request">	     
			     			<html:text
			     					   maxlength="40"
			     					   size="40"
			     					   readonly="true"
			     					   property="descricaoSetorComercial"
	   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
		     			</logic:notPresent>
		     			<logic:present name="setorComercialException" scope="request">
			     			<html:text
			     					   maxlength="40"
			     					   size="40"
			     					   readonly="true"
			     					   property="descricaoSetorComercial"
			     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
		     			</logic:present>	   
		     			</strong>
		     			<a href="javascript:limparConjunto(2);">
							<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
						</a>  	     
		     		</td>
                  </tr>
              
	               <tr>
		                <td><strong>Quadra :</strong></td>
		                <td colspan="2"><strong>
			                <html:text maxlength="3"
			                  			 size="3"
			                  			 readonly="${sessionScope.coordenadas}"
			                  			 tabindex="1"
			                  			 property="idQuadra"
			                  			 onkeypress="javascript:validaEnterDependencia(event, 'exibirMotivoNaoGeracaoDocumentoTipoComandoEventualAction.do?pesquisarQuadra=OK',this,document.forms[0].idSetorComercial.value, 'Setor Comercial');" />
							<logic:notPresent name="quadra" scope="session">
								<span style="color:#ff0000" id="msgQuadra">
									<bean:write name="MotivoNaoGeracaoDocumentoActionForm" property="descricaoQuadra" />
								</span>
							</logic:notPresent> 
							<logic:present name="corQuadra" scope="request" />
			     			</strong> 
		     			</td>
	                </tr>
					
					
					<tr>
						<td colspan="3">
							<table width="100%">
								<tr >
									<td width="26%">
										<strong>Por Imóvel:</strong>
											<html:radio property="indicadorTipoPesquisa" value="2" onclick="javascript:verificarTipoSelecao2();" />
									</td>
									<td width="24%">
										<html:text maxlength="9" 
											property="matriculaImovel" 
											size="9"
											disabled="true"
											onkeyup="somente_numero_zero_a_nove(this);" 
											tabindex="1" />
									</td>
									<td align="left" width="50%">
					                	<html:text property="descricaoMotivo" readonly = "true" size="40"/>
								   	</td>
								</tr>
							</table>
						</td>
						
					</tr>
					
					<tr>
						<td colspan="1">
							<strong>Campo Obrigat&oacute;rio:<font color="#FF0000">*</font></strong>
						</td>
					</tr>
					<tr>
						<td>
						</td>
					</tr>
					<tr>
						<td>
						</td>
					</tr>
					<tr>
						<td>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td colspan="3" width="100%">
							<div align="right">
								<input name="Button" type="button" class="bottonRightCol"
									value="Cancelar" align="left"
									onClick="window.location.href='/gsan/telaPrincipal.do'">
								<input name="button11" type="button" class="bottonRightCol"
									value="Voltar"
									onclick="window.location.href='<html:rewrite page="/exibirMotivoNaoGeracaoDocumentoTipoComandoAction.do"/>'"
									align="left" >		
						   		<input name="Button345" type="button" class="bottonRightCol"
									value="Consultar" align="left"
									onClick="javascript:validarFormSubmit();">
							</div>
						</td>
						<td width="60">
						</td>
					</tr>
					
				</table>
			</td>
		</tr>
	</table>
</body>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>