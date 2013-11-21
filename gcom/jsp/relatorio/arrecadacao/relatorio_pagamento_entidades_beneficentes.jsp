<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.util.ConstantesSistema"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<script>
	function validarFormSubmit(){		
    	var form = document.GerarRelatorioPagamentoEntidadesBeneficentesActionForm;
    	
		if(validateGerarRelatorioPagamentoEntidadesBeneficentesActionForm(form)){
	    	if (validarTipos()){
	    		toggleBox('divAux',1);			
				toggleBox('demodiv',1);		
	    	}else{
	    		alert('Seleção de Critérios Inválida');
	    	}
		}
	}
	function validarTipos(){
		var form = document.GerarRelatorioPagamentoEntidadesBeneficentesActionForm;
		var retorno = false;
		//validações do tipos
		if (form.indicadorEstado.checked
			&& form.indicadorGerenciaRegional.checked == false
			&& form.indicadorUnidadeNegocio.checked == false
			&& form.indicadorLocalidade.checked == false
			&& form.idGerenciaRegional.value == -1
			&& form.idUnidadeNegocio.value == -1
			&& form.idLocalidade.value == -1){
			//tipo 1
			form.opcaoTotalizacao.value = 1;			
			retorno= true;
		}else if (form.indicadorEstado.checked
			&& form.indicadorGerenciaRegional.checked
			&& form.indicadorUnidadeNegocio.checked == false
			&& form.indicadorLocalidade.checked == false
			&& form.idGerenciaRegional.value == -1
			&& form.idUnidadeNegocio.value == -1
			&& form.idLocalidade.value == -1){
			//tipo 2
			form.opcaoTotalizacao.value = 2;
			retorno= true;
		}else if (form.indicadorEstado.checked == false
			&& form.indicadorGerenciaRegional.checked
			&& form.indicadorUnidadeNegocio.checked == false
			&& form.indicadorLocalidade.checked == false
			&& form.idGerenciaRegional.value != -1
			&& form.idUnidadeNegocio.value == -1
			&& form.idLocalidade.value == -1){
			//tipo 3
			form.opcaoTotalizacao.value = 3;
			retorno= true;
		}else if (form.indicadorEstado.checked
			&& form.indicadorGerenciaRegional.checked
			&& form.indicadorUnidadeNegocio.checked
			&& form.indicadorLocalidade.checked == false
			&& form.idGerenciaRegional.value == -1
			&& form.idUnidadeNegocio.value == -1
			&& form.idLocalidade.value == -1){
			//tipo 4
			form.opcaoTotalizacao.value = 4;
			retorno= true;
		}else if (form.indicadorEstado.checked == false
			&& form.indicadorGerenciaRegional.checked == false
			&& form.indicadorUnidadeNegocio.checked
			&& form.indicadorLocalidade.checked == false
			&& form.idGerenciaRegional.value == -1
			&& form.idUnidadeNegocio.value != -1
			&& form.idLocalidade.value == -1){
			//tipo 5
			form.opcaoTotalizacao.value = 5;
			retorno= true;
		}else if (form.indicadorEstado.checked
			&& form.indicadorGerenciaRegional.checked
			&& form.indicadorUnidadeNegocio.checked
			&& form.indicadorLocalidade.checked
			&& form.idGerenciaRegional.value == -1
			&& form.idUnidadeNegocio.value == -1
			&& form.idLocalidade.value == -1){
			//tipo 6
			form.opcaoTotalizacao.value = 6;
			retorno= true;
		}else if (form.indicadorEstado.checked == false
			&& form.indicadorGerenciaRegional.checked == false
			&& form.indicadorUnidadeNegocio.checked == false
			&& form.indicadorLocalidade.checked
			&& form.idGerenciaRegional.value == -1
			&& form.idUnidadeNegocio.value == -1
			&& form.idLocalidade.value != -1){
			//tipo 7
			form.opcaoTotalizacao.value = 7;
			retorno= true;
		}else if (form.indicadorEstado.checked == false
			&& form.indicadorGerenciaRegional.checked
			&& form.indicadorUnidadeNegocio.checked
			&& form.indicadorLocalidade.checked == false
			&& form.idGerenciaRegional.value != -1
			&& form.idUnidadeNegocio.value == -1
			&& form.idLocalidade.value == -1){
			//tipo 8
			form.opcaoTotalizacao.value = 8;
			retorno= true;
		}else if (form.indicadorEstado.checked == false
			&& form.indicadorGerenciaRegional.checked
			&& form.indicadorUnidadeNegocio.checked
			&& form.indicadorLocalidade.checked
			&& form.idGerenciaRegional.value != -1
			&& form.idUnidadeNegocio.value == -1
			&& form.idLocalidade.value == -1){
			//tipo 9
			form.opcaoTotalizacao.value = 9;
			retorno= true;
		}else if (form.indicadorEstado.checked == false
			&& form.indicadorGerenciaRegional.checked == false
			&& form.indicadorUnidadeNegocio.checked
			&& form.indicadorLocalidade.checked
			&& form.idGerenciaRegional.value == -1
			&& form.idUnidadeNegocio.value != -1
			&& form.idLocalidade.value == -1){
			//tipo 10
			form.opcaoTotalizacao.value = 10;
			retorno= true;
		}
		
		return retorno;
	}
	
	function replicaDados(campoOrigem, campoDestino){
		campoDestino.value = campoOrigem.value;
	}

	
</script>
<head>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="GerarRelatorioPagamentoEntidadesBeneficentesActionForm" />
	
	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body leftmargin="5" topmargin="5">              
    <div id="formDiv">          
	<html:form action="/gerarRelatorioPagamentoEntidadesBeneficentesAction.do"
	           name="GerarRelatorioPagamentoEntidadesBeneficentesActionForm"	                                  
	           type="gcom.gui.relatorio.arrecadacao.GerarRelatorioPagamentoEntidadesBeneficentesActionForm"
	           method="post">
	<html:hidden property="opcaoTotalizacao" />
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
						<td class="parabg">Gerar Relatório de Pagamento para Entidades Beneficentes</td>
	
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
					<tr>
						<td height="5" colspan="3"></td>
					</tr>
				</table>
				<table width="100%" border="0">				    			    
				    <tr>
						<td colspan="2" width="574px">
							<p>Para gerar o Relat&oacute;rio, informe os dados abaixo:</p>							
						</td>
					</tr>
					
					<tr>					    
						<td width="26%">
						  <STRONG>Tipo de Relat&oacute;rio:<FONT color=#ff0000>*</FONT></STRONG>
						</td>						
						<td width="74%">
						  <html:radio property="tipo" value="analitico" />Anal&iacute;tico						  
						  <html:radio property="tipo" value="sintetico" />Sint&eacute;tico						 
						</td>
					</tr>
					
					<tr>
						<td><STRONG>Entidade Beneficente:</STRONG></td>						
						<td colSpan=2>
						  <html:select property="idEntidadeBeneficente" style="WIDTH: 250px"> 
						    <html:option value="-1">&nbsp;</html:option>
						    <html:options collection="colecaoEntidadesBeneficentes"  labelProperty="cliente.nome" property="id"/>							  
					      </html:select>					      
						</td>												
					</tr>
					
					<tr>
							<td width="50"><strong>Per&iacute;odo:<font color="#FF0000">*</font></strong></td>
		
							<td><strong> 
							    <html:text property="mesAnoInicial" size="7"
								  onkeyup="mascaraAnoMes(this, event); replicaDados(document.GerarRelatorioPagamentoEntidadesBeneficentesActionForm.mesAnoInicial, document.GerarRelatorioPagamentoEntidadesBeneficentesActionForm.mesAnoFinal);somente_numero(this);"
								  onkeypress="return isCampoNumerico(event);" maxlength="7" />
								
						  	    a</strong> 
							
							    <html:text property="mesAnoFinal" size="7"
								  onkeyup="mascaraAnoMes(this, event); somente_numero(this);"
								  onkeypress="return isCampoNumerico(event);" maxlength="7"/> 
								
								(mm/aaaa)
							</td>
					</tr>										
					<tr>
							<td width="70">
							  <strong>Op&ccedil;&atilde;o de Totaliza&ccedil;&atilde;o:<font color="#FF0000">*</font></strong>
							</td>
							<td>
							  <html:checkbox property="indicadorEstado" value="true"/>
							  <strong> Por Estado</strong>
							</td>
							<td/>
					</tr>
					<tr>
							<td width="70"/>
							<td width="1600">
							  <html:checkbox property="indicadorGerenciaRegional" value="true" />
							  <strong> Por Gerência Regional</strong>
								&nbsp;&nbsp;
								<html:select property="idGerenciaRegional" style="width: 250px;">
								  <html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							  	  <html:options collection="colecaoGerenciaRegional" labelProperty="nome"
									property="id" />
								</html:select>								
							</td>
							<td/>
					</tr>
					<tr>
							<td width="70"/>
							<td width="450">
								<html:checkbox property="indicadorUnidadeNegocio" value="true" />
								<strong> Por Unidade de Negócio</strong>
								<html:select property="idUnidadeNegocio" style="width: 250px;">
								  <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								  <html:options collection="colecaoUnidadeNegocio" 
								  		labelProperty="nome"								  
										property="id" />
								</html:select>
							</td>
					</tr>
					<tr>
							<td width="70"/>
							<td width="450">
							  <html:checkbox property="indicadorLocalidade" value="true" />
							  <strong> Por Localidade</strong>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <html:select property="idLocalidade" style="width: 250px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoLocalidade" labelProperty="descricao"
									property="id" />
							  </html:select>
							</td>
					</tr>
					             		
					<tr>
						<td/>
						<td>
							<strong><font color="#FF0000">*</font>Campos Obrigat&oacute;rios</strong>
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
						  <font color="#FF0000"> 
						    <input type="button" name="ButtonCancelar" 
						      class="bottonRightCol" value="Cancelar"
						      onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						  </font>
						</td>
						
						<td>
							<div align="right">
							  <gsan:controleAcessoBotao name="Submit23" value="Gerar" onclick="validarFormSubmit();" url="gerarRelatorioPagamentoEntidadesBeneficentesAction.do"/>							  
							</div>
						</td>
						<td width="60">
						</td>
					</tr>
					
				</table>
			</td>
		</tr>
	</table>	

    <div id="divAux" style="position:absolute; left:11px; top:50px;">
	  <jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioPagamentoEntidadesBeneficentesAction.do"/>
	</div>

<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>