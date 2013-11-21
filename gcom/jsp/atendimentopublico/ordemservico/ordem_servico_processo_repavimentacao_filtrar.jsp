<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServico"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarOrdemServicoActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript"><!--

	function validarForm(){
   		var form = document.forms[0];

    	if(validateFiltrarOrdemPorcessoRepavimentacaoActionForm(form)){
			if(validaTodosPeriodos()){
				form.action = 'filtrarOrdemProcessoRepavimentacaoAction.do';
	   			form.submit();
			}
	  	}
    }
    function validaTodosPeriodos() {

		var form = document.forms[0];

    	if (comparaData(form.dataEncerramentoOSInicio.value, '>', form.dataEncerramentoOSFim.value)){

			alert('Data Final do Encerramento da OS é anterior à Data Inicial do Encerramento da OS');
			return false;

		} else if (comparaData(form.dataRetornoServicoInicio.value, '>', form.dataRetornoServicoFim.value)){

			alert('Data Final do Retorno do Serviço é anterior à Data Inicial do Retorno do Serviço');
			return false;
		} 

		return true;
    }
    
	//Replica Data de Retorno do Serviço
	function replicaDataRetornoServico() {
		var form = document.forms[0];
		form.dataRetornoServicoFim.value = form.dataRetornoServicoInicio.value;
	}

	//Replica Data de Encerramento
	function replicaDataEncerramento() {
		var form = document.forms[0];
		form.dataEncerramentoOSFim.value = form.dataEncerramentoOSInicio.value;
	}

	function validaForm(){
		var form = document.forms[0];
		
		if(form.numeroRA.value != null && form.numeroRA.value != ''){

			limparDocumentoCobranca();

   	    	form.documentoCobranca.disabled = true;
    		form.descricaoDocumentoCobranca.disabled = true;

		}else if(form.documentoCobranca.value != null && form.documentoCobranca.value != ''){

			limparPesquisaRA();
			
   	    	form.numeroRA.disabled = true;
    		form.descricaoRA.disabled = true;
   		
		}
		
		if(	form.situacaoOrdemServico.value == '1' || 
			form.situacaoOrdemServico.value == '3' ||
			form.situacaoOrdemServico.value == '4' ){

			limparPeriodoEncerramento();
			
			form.periodoEncerramentoInicial.disabled = true;
			form.periodoEncerramentoFinal.disabled = true;
		
		}else{

			form.periodoEncerramentoInicial.disabled = false;
			form.periodoEncerramentoFinal.disabled = false;
		
		}

	}
	
	//So chama a função abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem,objetoRelacionado,fieldNameDestino){
		
		if(objetoRelacionado.disabled != true){
			if(fieldNameDestino != ''){
				abrirCalendarioReplicando('FiltrarOrdemServicoActionForm', fieldNameOrigem,fieldNameDestino);
			}else{
				abrirCalendario('FiltrarOrdemServicoActionForm', fieldNameOrigem);
			}
		}
	}
--></script>

</head>

<body leftmargin="5" topmargin="5" onload="validaForm();window.focus();javascript:setarFoco('${requestScope.numeroOS}');">

<html:form action="/filtrarOrdemProcessoRepavimentacaoAction" method="post">


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

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Filtrar Ordens em Processo de Repavimentação - Contrato Prefeituras</td>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table bgcolor="#cbe5fe" align="center" border="0" cellpadding="0" cellspacing="0" height="24" width="100%">
				<tbody>
 					<tr>
		  				<td bgcolor="#cbe5fe" colspan="3">
			 				<div border="1" align="center">
			 					<img border="0" width="356" height="287" src="<bean:message key="caminho.imagens"/>UPA-PrefeituraRecife.jpg" />
				  				<br>
				  				<br>
			 				</div>
			 			</td>
					</tr>
					
					<tr>
					  <td><p><HR> <p></td>
					</tr>
					
        			<tr bgcolor="#cbe5fe" >
 		  				<td width="100%" align="center">
 			 				<p>&nbsp;
			 				<strong>Unidade Responsável:</strong><strong><font color="#ff0000">*</font></strong> &nbsp; 
			 				<html:select property="tipoPessoa" tabindex="3">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoUnidadesResponsaveis" labelProperty="descricao" property="id" />
	                   		</html:select>
	                   	
			  				
			 				&nbsp;&nbsp;&nbsp;&nbsp;
                 			
                 			<strong>Encerramento da OS:</strong> &nbsp;&nbsp; 
                            <strong> 
								
								<html:text property="dataEncerramentoOSInicio" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeyup="mascaraData(this, event);replicaDataEncerramento();"/>
								
								<a href="javascript:abrirCalendarioReplicando('FiltrarOrdemProcessoRepavimentacaoActionForm', 'dataEncerramentoOSInicio','dataEncerramentoOSFim');">
									<img border="0" 
										src="<bean:message key='caminho.imagens'/>calendario.gif" 
										width="16" 
										height="15" 
										border="0" alt="Exibir Calendário" 
										tabindex="4"/></a>
								a 
								
								<html:text property="dataEncerramentoOSFim" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeyup="mascaraData(this, event)"/>
									
								<a href="javascript:abrirCalendario('FiltrarOrdemProcessoRepavimentacaoActionForm', 'dataEncerramentoOSFim');">
									<img border="0" 
										src="<bean:message key='caminho.imagens'/>calendario.gif" 
										width="16" 
										height="15" 
										border="0" 
										alt="Exibir Calendário" 
										tabindex="4"/></a>
								
								</strong>(dd/mm/aaaa)<strong> 
		                  	</strong>
					   	</td>
					</tr>
					<p><BR><p>
					
					<tr>
          				<td bgcolor="#cbe5fe"><BR></td>
          				
        			<tr>
          				<td bgcolor="#cbe5fe" width="100%">
          					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  				<strong>Situação do Retorno</strong> &nbsp;&nbsp;<strong>:</strong> &nbsp;&nbsp;&nbsp;&nbsp;
			  				<strong> 
								<html:select property="situacaoOrdemServico" style="width: 300px;" onchange="javascript:validaForm();">
		
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
		
									<html:option
										value="<%=""+OrdemServico.SITUACAO_PENDENTE%>">PENDENTES
									</html:option>
		
									<html:option
										value="<%=""+OrdemServico.SITUACAO_ENCERRADO%>">ENCERRADOS
									</html:option>
								
								</html:select> 														
							</strong>

							  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                 			
                 			<strong>Retorno do Serviço</strong> &nbsp; <strong>:</strong>&nbsp;&nbsp;&nbsp;&nbsp;
                 			<strong> 
								
								<html:text property="dataRetornoServicoInicio" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeyup="mascaraData(this, event);replicaDataRetornoServico();"/>
								
								<a href="javascript:abrirCalendarioReplicando('FiltrarOrdemProcessoRepavimentacaoActionForm', 'dataRetornoServicoInicio','dataRetornoServicoFim');">
									<img border="0" 
										src="<bean:message key='caminho.imagens'/>calendario.gif" 
										width="16" 
										height="15" 
										border="0" alt="Exibir Calendário" 
										tabindex="4"/></a>
								a 
								
								<html:text property="dataRetornoServicoFim" 
									size="11" 
									maxlength="10" 
									tabindex="3" 
									onkeyup="mascaraData(this, event)"/>
									
								<a href="javascript:abrirCalendario('FiltrarOrdemProcessoRepavimentacaoActionForm', 'dataRetornoServicoFim');">
									<img border="0" 
										src="<bean:message key='caminho.imagens'/>calendario.gif" 
										width="16" 
										height="15" 
										border="0" 
										alt="Exibir Calendário" 
										tabindex="4"/></a>
								
								</strong>(dd/mm/aaaa)<strong> 
		                  	</strong>
			    		</td>
					</tr>
					
					<tr bgcolor="#cbe5fe">
					  	<td><p><p></td>
				    </tr>
				    
					<tr bgcolor="#cbe5fe">
					  	<td><p><HR><p><p></td>
				    </tr>
				    
					<tr bgcolor="#cbe5fe">
		  				<td height="19" align="left"><p><BR>
						   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   			   
			  				<input name="ButtonCancelar" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" type="button">
			   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

			  				<input name="Button2" class="bottonRightCol" value="Consultar" onClick="javascript:validarForm(document.forms[0])" type="button">
			  			</td>
			  			
						<td><p><BR><p></td>
					</tr>
					
					<tr bgcolor="#cbe5fe">
					  <td><p><BR><p></td>
				    </tr>
	    
	    			<tr bgcolor="#cbe5fe">
		  				<td>
		  					<HR>&nbsp;&nbsp;<strong> <font color="#ff0000"></font></strong><strong><font color="#ff0000">*</font></strong> Campos obrigatórios
		  				</td>
					</tr>
					
					<tr>
					  <td><p><BR><p></td>
					</tr>
	  			
				</tbody>
			</table>

			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form>
</body>
</html:html>
