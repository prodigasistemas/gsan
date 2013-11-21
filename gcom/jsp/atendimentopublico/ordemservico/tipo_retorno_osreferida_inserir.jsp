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
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirTipoRetornoOrdemServicoReferidaActionForm"/>	
	
<script language="JavaScript">

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
  		var form=document.forms[0];
    	if (tipoConsulta == 'hidrometro') {
      		form.numeroHidrometro.value = codigoRegistro;
    	}    
  	}	
  
	function validaForm() {
	  	var form = document.forms[0];
	  	form.action = "/gsan/inserirTipoRetornoOrdemServicoReferidaAction.do";
		if(validateInserirTipoRetornoOrdemServicoReferidaActionForm(form)) {
		  	if(validaTodosRadioButton()) {		     		  		
				submeterFormPadrao(form);   		  
   	      	}
   	    }
	}
	 
  	function validaTodosRadioButton() {
		var form = document.forms[0];		
		if (!form.deferimento[0].checked
				&& !form.deferimento[1].checked) {
			alert("Informe Indicador de Deferimento.");		
			return false;
		}		
		if (!form.trocaServico[0].checked
				&& !form.trocaServico[1].checked) {
			alert("Informe Indicador de Troca de Serviço.");		
			return false;
		}		
		return true;
   	}
	 
	function limparForm() {
		var form = document.InserirTipoRetornoOrdemServicoReferidaActionForm;
		form.descricao.value = "";
		form.abreviatura.value = "";
		form.servicoTipoReferencia.selectedIndex = 0;
		form.deferimento[0].checked = false;
		form.deferimento[1].checked = false;		
		form.trocaServico[0].checked = false;
		form.trocaServico[1].checked = false;			
		form.trocaServico[0].disabled = false;
		form.trocaServico[1].disabled = false;			
		form.situacao[0].checked = false;
		form.situacao[1].checked = false;			
		form.situacao[0].disabled = false;
		form.situacao[1].disabled = false;			
		form.atendimentoMotivoEncerramento.disabled = false;
		form.atendimentoMotivoEncerramento.selectedIndex = 0;			
	}  
	
	function reload() {
		var form = document.InserirTipoRetornoOrdemServicoReferidaActionForm;
		form.action = "/gsan/exibirInserirTipoRetornoOrdemServicoReferidaAction.do";
		form.submit();
	}  
	
	function pesquisarTipoOSReferencia() {
		var form = document.InserirTipoRetornoOrdemServicoReferidaActionForm;
		if (form.servicoTipoReferencia.selectedIndex != 0) {
			reload();
		} else {
			form.deferimento[0].checked = false;
			form.deferimento[1].checked = false;			
			form.trocaServico[0].checked = false;
			form.trocaServico[1].checked = false;			
			form.trocaServico[0].disabled = false;
			form.trocaServico[1].disabled = false;			
			form.situacao[0].checked = false;
			form.situacao[1].checked = false;			
			form.situacao[0].disabled = false;
			form.situacao[1].disabled = false;			
			form.atendimentoMotivoEncerramento.disabled = false;
			form.atendimentoMotivoEncerramento.selectedIndex = 0;			
		}
	}  

	function pesquisarAtendimentoMotivoEncerramento() {
		var form = document.InserirTipoRetornoOrdemServicoReferidaActionForm;
		if (form.atendimentoMotivoEncerramento.selectedIndex != 0) {
			if (form.deferimento[0].checked == true
					|| form.deferimento[1].checked == true) {
				reload();
			} else {
				alert("Informe Indicador de Deferimento.");		
				form.atendimentoMotivoEncerramento.selectedIndex = 0;
			}
		}
	}  
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado) {
		if(objetoRelacionado.disabled != true) {
			if (objeto == null || codigoObjeto == null) {
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	
	function setarIndicadorTrocaServico(valor, indicadorExistencia) {
		var form = document.forms[0];
		if (valor == '1') {
			form.trocaServico[1].checked = true;
			form.trocaServico[0].disabled = true;
			form.trocaServico[1].disabled = true;
		} else if (valor == '2') {
			if (indicadorExistencia != '2') {
				form.trocaServico[0].disabled = false;
				form.trocaServico[1].disabled = false;
			}
		}
	}	 
	
</script>
  
</head>

<body leftmargin="5" topmargin="5" onload="setarIndicadorTrocaServico('${requestScope.deferimento}', '${servicoTipoReferencia.indicadorExistenciaOsReferencia}');">

<html:form 	action="/inserirTipoRetornoOrdemServicoReferidaAction.do"
			name="InserirTipoRetornoOrdemServicoReferidaActionForm"
			type="gcom.gui.atendimentopublico.ordemservico.InserirTipoRetornoOrdemServicoReferidaActionForm"
			method="post"
			onsubmit="return validateInserirTipoRetornoOrdemServicoReferidaActionForm(this);">

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

			<!-- centercoltext -->			
			
          	<td width="625" valign="top" class="centercoltext"> 
            	
            	<table>
              		<tr>
                		<td></td>
              		</tr>
            	</table>
            	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              		<tr>
                		<td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                		<td class="parabg">Inserir Tipo Retorno da Ordem de Serviço Referida</td>
                		<td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              		</tr>
            	</table>
            	<table width="100%" height="311" border="0">
              		<tr> 
                		<td height="10" colspan="3">Para adicionar o tipo de retorno, informe os dados abaixo:</td>
              		</tr>
					<tr>
                        <td height="10" colspan="3"><hr></td>
                   	</tr>              		
              		<!-- Descricao -->
              		
					<tr>
						<td><strong>Descri&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
						<td colspan="2">
							<span class="style2">
							<html:text property="descricao" size="50" maxlength="50" />
							</span>
						</td>						
					</tr>
              		
              		<!-- Abreviatura -->
              		
					<tr>
						<td><strong>Abreviatura:</strong></td>
						<td colspan="2">
							<span class="style2">
							<html:text property="abreviatura" size="10" maxlength="8" />
							</span>
						</td>						
					</tr>
					
					<!-- Referencia do Tipo de Servico -->
					
              		<tr> 
                		<td><strong>Refer&ecirc;ncia do Tipo de Servi&ccedil;o:<font color="#FF0000">*</font></strong></td>
                		<td colspan="2" align="left">
					  		<html:select property="servicoTipoReferencia" onchange="javascript:pesquisarTipoOSReferencia();">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoServicoTipoReferencia" labelProperty="descricao" property="id" />
							</html:select> 
                  		</td>
              		</tr>
              		
              		<!-- Indicador do Deferimento -->
              		
            		<tr>
              			<td><strong><span class="style2">Indicador de Deferimento:<font color="#FF0000">*</font></span></strong></td>
		                <td align="left" width="20%">
                    		<label>
                  			<html:radio property="deferimento" value="1" onclick="javascript:setarIndicadorTrocaServico(1, '${servicoTipoReferencia.indicadorExistenciaOsReferencia}');"/>
                    		<strong>Deferido</strong></label>
		                </td>
		                <td align="left">
                    		<label>
                   			<html:radio property="deferimento" value="2" onclick="javascript:setarIndicadorTrocaServico(2, '${servicoTipoReferencia.indicadorExistenciaOsReferencia}');"/>
                    		<strong>Indeferido</strong></label>
              			</td>
            		</tr>

              		<!-- Indicador da Troca de Serviço -->
              		
            		<tr>
              			<td><strong><span class="style2">Indicador de Troca de Servi&ccedil;o:<font color="#FF0000">*</font></span></strong></td>
						<c:choose>
							<c:when test="${servicoTipoReferencia.indicadorExistenciaOsReferencia == '2'}">
				                <td align="left">
		                    		<label>
		                  			<html:radio property="trocaServico" value="1" disabled="true" />
		                    		<strong>Sim</strong></label>
		                    	</td>
				                <td align="left">                    	
		                    		<label>
		                   			<html:radio property="trocaServico" value="2" disabled="true" />
		                    		<strong>Não</strong></label>		                    		
		                    		<script>
		                    			document.forms[0].trocaServico[1].checked = true;
		                    		</script>
		              			</td>
	                    		<input type="hidden" name="trocaServico" value="" />
							</c:when>
							<c:otherwise>
				                <td align="left">
		                    		<label>
		                  			<html:radio property="trocaServico" value="1" />
		                    		<strong>Sim</strong></label>
		                    	</td>
				                <td align="left">                    	
		                    		<label>
		                   			<html:radio property="trocaServico" value="2" />
		                    		<strong>Não</strong></label>
		              			</td>
							</c:otherwise>
						</c:choose>
            		</tr>
            		
              		<!-- Código da Situação -->
              		
            		<tr>
              			<td><strong><span class="style2">C&oacute;digo da Situa&ccedil;&atilde;o:</span></strong></td>
						<c:choose>
							<c:when test="${servicoTipoReferencia.indicadorExistenciaOsReferencia == '2'}">
								<td align="left">                    	
		                    		<label>
		                  			<html:radio property="situacao" value="1" disabled="true" />
		                    		<strong>Pendente</strong></label>
		                    	</td>
		                    	<td align="left">                    	
		                    		<label>
		                   			<html:radio property="situacao" value="2" disabled="true" />
		                    		<strong>Encerrada</strong></label>
		              			</td>
	                    		<input type="hidden" name="situacao" value="" />		              			
	                    		<script>
	                    			document.forms[0].situacao[0].checked = false;
	                    			document.forms[0].situacao[1].checked = false;		                    			
	                    		</script>
							</c:when>
							<c:otherwise>
								<td align="left">                    	
		                    		<label>
		                  			<html:radio property="situacao" value="1" />
		                    		<strong>Pendente</strong></label>
		                    	</td>
		                    	<td align="left">                    	
		                    		<label>
		                   			<html:radio property="situacao" value="2" />
		                    		<strong>Encerrada</strong></label>
		              			</td>
							</c:otherwise>
						</c:choose>
            		</tr>

					<!-- Motivo do Encerramento -->            		
              		
              		<tr> 
                		<td width="200"><strong><span class="style2">Motivo de Encerramento do Atendimento:</span></strong></td>
						<c:choose>
							<c:when test="${servicoTipoReferencia.indicadorExistenciaOsReferencia == '2'}">
		                		<td colspan="2" align="left">
							  		<html:select property="atendimentoMotivoEncerramento" disabled="true">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoAtendimentoMotivoEncerramento" labelProperty="descricao" property="id" />
									</html:select> 
		                    		<script>
		                    			document.forms[0].atendimentoMotivoEncerramento.selectedIndex = 0;
		                    		</script>
		                  		</td>
							</c:when>
							<c:otherwise>
		                		<td colspan="2" align="left">
							  		<html:select property="atendimentoMotivoEncerramento" onchange="javascript:pesquisarAtendimentoMotivoEncerramento();">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoAtendimentoMotivoEncerramento" labelProperty="descricao" property="id" />
									</html:select> 
		                  		</td>
							</c:otherwise>
						</c:choose>
              		</tr>
              		
              		<!-- Botões -->

					<tr>
						<td align="left">
							<input 	type="button" 
									name="ButtonReset" 
									class="bottonRightCol"
									value="Desfazer" 
									onClick="javascript:limparForm();"> 
							<input 	type="button"
									name="ButtonCancelar" 
									class="bottonRightCol" 
									value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td colspan="2" align="right">
							<input 	name="Button" 
									type="button"
									class="bottonRightCol" 
									value="Inserir" 
									onclick="validaForm();">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
	
</html:form>
</html:html>
