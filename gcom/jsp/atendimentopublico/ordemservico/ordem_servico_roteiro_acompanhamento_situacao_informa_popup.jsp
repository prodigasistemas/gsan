<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServico"%>

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<%-- Novos Javascripts --%>

<script language="JavaScript">
   	function limpar() {

		var form = document.forms[0];
		form.motivoNaoEncerramento.disabled = true;
		
		form.situacaoOrdemServico.selectedIndex = 0;
		form.motivoNaoEncerramento.selectedIndex = 0;

   	}
   	
   	function concluir(actionTipo) {
		var form = document.forms[0];

		if(validaSelect() != false){
	    	
			if(form.situacaoOrdemServico.value == 2){
				
				var os = form.idOrdemServico.value;
				var data = form.dataRoteiro.value;
				var chaveArquivo = form.chaveArquivo.value;

				if (actionTipo == 'true'){
					data = form.dataProgramacao.value;
					var url = 'exibirEncerrarOrdemServicoPopupAction.do?numeroOS='+
					os+'&dataRetorno='+data+'&veioAcompanhamentoRoteiro=true&retornoTela=selecionarAcompanhamentoArquivosRoteiroAction.do';
				}else{
					var url = 'exibirEncerrarOrdemServicoPopupAction.do?numeroOS='+
					os+'&dataRetorno='+data+'&retornoTela=exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do';
				}
				
	    		form.action=url;
		    	form.submit();
			
			}else{
				if (actionTipo == 'true'){
					form.action='informarSituacaoOSAcompanhamentoArquivosRoteiroAction.do';
				    form.submit();
				}else{
					var lista = new Array();
			    	lista[0] = form.situacaoOrdemServico.value;
			    	lista[1] = form.motivoNaoEncerramento.value;
			    	lista[2] = form.idOrdemServico.value;
			    	lista[3] = form.chaveEquipe.value;
			    	lista[4] = form.chaveArquivo.value;
			    	
					window.opener.recuperarDadosPopup('', lista, 'situacaoOs');
					window.close();
				}		    	
			}
		}
   	}
   	
	function validaForm(){
		var form = document.forms[0];
		
		if(	form.situacaoOrdemServico.value == '1'){

			form.motivoNaoEncerramento.disabled = false;
		}else{
			form.motivoNaoEncerramento.disabled = true;
		}

	}

    
	function validaSelect() {
		var form = document.forms[0];
		
    	if (form.situacaoOrdemServico.value == '-1') {
			alert('Informe a Nova Situação da OS');
			form.situacaoOrdemServico.focus();
			return false;
    	}
    	
    	if(form.motivoNaoEncerramento.disabled == false && 
    		form.motivoNaoEncerramento.value == '-1'){

			alert('Informe o Motivo do Não Encerramento da OS');
			form.motivoNaoEncerramento.focus();
			return false;
    	
    	}
    	return true;
	}
</script>
</head>

<logic:notPresent name="fecharPopup">
	<body leftmargin="0" topmargin="0" onload="window.focus();limpar();">
</logic:notPresent>

<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="javascript:chamarReload('selecionarAcompanhamentoArquivosRoteiroAction.do');window.close();">
</logic:present>

<html:form action="/exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do"
	name="AcompanharRoteiroProgramacaoOrdemServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AcompanharRoteiroProgramacaoOrdemServicoActionForm"
	method="post">
	
	<html:hidden property="dataRoteiro"/>
	<html:hidden property="chaveEquipe"/>
	<html:hidden property="chaveArquivo"/>
	<html:hidden property="dataProgramacao"/>
		
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr> 
	    	<td width="560" valign="top" class="centercoltext">
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
	          			<td class="parabg">Informar Situa&ccedil;&atilde;o da Ordem de Servi&ccedil;o</td>
	          			<td width="11">
	          				<img border="0" 
	          					src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
	          			</td>
	        		</tr>
	      		</table>
	      		<p>&nbsp;</p>
	      		<table width="100%" border="0">

			        <tr> 
			          <td colspan="3">Selecione a nova situa&ccedil;&atilde;o da ordem de servi&ccedil;o:</td>
			        </tr>

					<tr>
						<td>
							<strong> Ordem de Servi&ccedil;o:</strong>
						</td>

						<td colspan="2">
							<strong> 
								<html:text property="idOrdemServico" 
									readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									size="9"
									maxlength="9" />

								<html:text property="descricaoOrdemServico" 
									readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									size="45"
									maxlength="45" />

							</strong>
						</td>
					</tr>

			        <tr>
			        	<td>
			        		<strong>Situa&ccedil;&atilde;o Atual:</strong>
			        	</td>
			          	
			          	<td colspan="2">
			          		<strong> 
								<html:text property="situacaoAtual" 
									readonly="true"
									style="background-color:#EFEFEF; border:0;" 
									size="50"
									maxlength="50" />

							</strong>			          		
						</td>
			        </tr>
					
			        <tr>
			        	<td height="24" colspan="3">
			        		<hr>
			        	</td>
			        </tr>

			        <tr> 
			        	<td height="24">
			        		<strong>Nova Situa&ccedil;&atilde;o da OS:</strong>
			        	</td>

						<td>
							<strong> 

							<html:select property="situacaoOrdemServico"
								style="width: 300px;"
								onchange="javascript:validaForm();">
	
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
	
								<html:option
									value="<%=""+OrdemServico.SITUACAO_PENDENTE%>">PENDENTES
								</html:option>
	
								<html:option
									value="<%=""+OrdemServico.SITUACAO_ENCERRADO%>">ENCERRADOS
								</html:option>
								<%if(request.getParameter("veioAcompanhamentoRoteiro") == null){%>
									<html:option
										value="<%=""+OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO%>">EXECU&Ccedil;&Atilde;O EM ANDAMENTO
									</html:option>
								<%}%>
							
							</html:select> 														
							</strong>
						</td>
					</tr>

			        <tr> 
			        	<td height="24">
			        		<strong>Motivo do N&atilde;o Encerramento da OS:</strong>
			        	</td>
						<td>
							<strong> 
							<html:select property="motivoNaoEncerramento" style="width: 230px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
						
								<logic:present name="colecaoMotivoNaoEncerramento"	scope="request">
									<html:options collection="colecaoMotivoNaoEncerramento"
										labelProperty="descricao" 
										property="id" />
								</logic:present>
							</html:select> 														
							</strong>
						</td>
        			</tr>

			        <tr> 
			        	<td height="27" colspan="2"> 
			        		<div align="left">

	                        	<input name="ButtonFechar" 
	                        		type="button" 
	                        		class="bottonRightCol" 
	                        		value="Fechar"
	                        		onClick="javascript:window.close();">
			        		
	              				<input name="Button2" 
	              					type="button" 
	              					class="bottonRightCol" 
	              					value="Limpar" 
	              					onClick="javascript:limpar();">
			            	</div>
			            </td>
			          	
			          	<td height="27" colspan="5">
	          				<div align="right"> 
	              				<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
		              				<input name="Button2" 
		              					type="button" 
		              					class="bottonRightCol" 
		              					value="Concluir" 
		              					onClick="javascript:concluir('true');">
		              			<%}else{%>
		              				<input name="Button2" 
		              					type="button" 
		              					class="bottonRightCol" 
		              					value="Concluir" 
		              					onClick="javascript:concluir('false');">
		              			<%}%>
			            	</div>
			            </td>
			        </tr>
			              	
	      		</table>
	      		<p>&nbsp;</p>
	      	</td>
	  	</tr>
	</table>
</html:form>
</body>
</html:html>