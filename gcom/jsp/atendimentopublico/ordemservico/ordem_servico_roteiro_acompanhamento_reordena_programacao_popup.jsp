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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AcompanharRoteiroProgramacaoOrdemServicoActionForm"/>

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<%-- Novos Javascripts --%>

<script language="JavaScript">
   	function limpar() {

		var form = document.forms[0];
		form.sequencialProgramacaoPrevisto.value = "";
   	}
   	
   	function concluir(actionTipo) {
		var form = document.forms[0];

		if(validaForm() != false){
		   	if (validateAcompanharRoteiroProgramacaoOrdemServicoActionForm(form)){
		   		if(actionTipo == 'true'){
					form.action='acompanhamentoArquivosRoteiroReordenarSequencialAction.do';
				    form.submit();
				}else{
					var lista = new Array();
			    	
			    	lista[0] = form.sequencialProgramacaoPrevisto.value;
			    	lista[1] = form.sequencialProgramacao.value;
			    	lista[2] = form.chaveEquipe.value;
			    	
					window.opener.recuperarDadosPopup('', lista, 'reordenarProgramacao');
					window.close();
				}
			}
		}
   	}
   	
	function validaForm(){
		var form = document.forms[0];
		
   		if(form.sequencialProgramacaoPrevisto.value == ""){
   			alert("Informe um sequencial na programação");
   			return false;
		}
	}

</script>
</head>

<logic:notPresent name="fecharPopup">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(600, 400);limpar();setarFoco(document.forms[0].sequencialProgramacaoPrevisto);">	
</logic:notPresent>

<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="javascript:chamarReload('selecionarAcompanhamentoArquivosRoteiroAction.do');limpar();window.close();">
</logic:present>

<html:form action="/exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do"
	name="AcompanharRoteiroProgramacaoOrdemServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AcompanharRoteiroProgramacaoOrdemServicoActionForm"
	method="post">

	<html:hidden property="sequencialProgramacao"/>
	<html:hidden property="chaveEquipe"/>
	<html:hidden property="numeroRA"/>

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
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
	          			</td>
	          			<td class="parabg">Reordenar Sequencial</td>
	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
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
									size="50"
									maxlength="50" />

							</strong>
						</td>
					</tr>
					<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
						<tr>
							<td>
								<strong> Sequencial:</strong>
							</td>
	
							<td colspan="2">
								<strong> 
									<html:text property="sequencialProgramacao" 
										readonly="true"
										style="background-color:#EFEFEF; border:0;" 
										size="4"
										maxlength="9" />
								</strong>
							</td>
						</tr>
					<%}%>
			        <tr>
			        	<td height="24" colspan="3">
			        		<hr>
			        	</td>
			        </tr>

                    <tr> 
                      	<td colspan="3"> 
                      		<strong>Sequencial na Programa&ccedil;&atilde;o:<font color="#FF0000">*</font></strong>
                      		&nbsp;
							<html:text maxlength="4" 
								property="sequencialProgramacaoPrevisto" 
								size="4"/>

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