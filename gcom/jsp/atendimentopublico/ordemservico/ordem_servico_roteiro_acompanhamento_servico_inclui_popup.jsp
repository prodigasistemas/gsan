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

		form.ButtonGerar.disabled = true;
		
		form.sequencialProgramacaoPrevisto.value = "";
		limparPesquisaRA();
		limparPesquisaOs();
   	}
   	
   	function gerarOS(actionTipo){
		var form = document.forms[0];

		if (actionTipo == 'true'){
			form.action = 'exibirGerarOrdemServicoAction.do?veioAcompanhamentoRoteiro=true&forward=exibirGerarOrdemServicoPopup&idRegistroAtendimento='+form.numeroRA.value;
		}else{
			form.action = 'exibirGerarOrdemServicoAction.do?veioAcompanhamento=true&forward=exibirGerarOrdemServicoPopup&idRegistroAtendimento='+form.numeroRA.value;
		}
		
		form.submit();
	}
   	
   	function extendeTabela(display){
		var form = document.forms[0];

		if(display){
 			layerHideDados.style.display = 'none';
 			layerShowDados.style.display = 'block';
		}else{
			layerHideDados.style.display = 'block';
 			layerShowDados.style.display = 'none';
		}
	}

	function chamarForm(caminhoAction,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			var form = document.forms[0];
	   		form.action = caminhoAction;
	   		form.submit();
		}
	}
	
	function limparPesquisaRA() {

    	var form = document.forms[0];

    	form.numeroRA.value = "";
    	form.descricaoRA.value = "";
    	
		form.ButtonGerar.disabled = true;
  	}

	function limparPesquisaOs() {

    	var form = document.forms[0];

    	form.idOrdemServico.value = "";
    	form.descricaoOrdemServico.value = "";
  	}

	function habilitaGerarOs(){
    	var form = document.forms[0];

		if(form.numeroRA.value != ''){
			form.ButtonGerar.disabled = false;
		}else{
			form.ButtonGerar.disabled = true;
		}
	}

   	function concluir(actionTipo) {
    	var form = document.forms[0];
		if(validaForm()){
			if(actionTipo == 'true'){
				form.action='acompanhamentoArquivosRoteiroIncluirOrdemServicoAction.do';
			    form.submit();
			}else{			
			   	if (validateAcompanharRoteiroProgramacaoOrdemServicoActionForm(form)){
					var lista = new Array();
					var lista = new Array();
			    	lista[0] = form.idOrdemServico.value;
			    	lista[1] = form.sequencialProgramacaoPrevisto.value;
			    	lista[2] = form.sequencialProgramacao.value;
			    	lista[3] = form.numeroRA.value;
				
					enviarDados('', lista, 'incluirOrdemServico');
		
					//window.opener.recuperarDadosPopup('', lista, 'incluirOrdemServico');
					if(actionTipo != 'true'){
						window.close();
					}
			   	}
			}
		}
   	}
   	

   	function validaForm(){

   		var form = document.forms[0];

   		if(form.veioAcompanhamentoRoteiro.value != null && form.veioAcompanhamentoRoteiro.value == "true"){
			if (form.numeroRA.value == ""){
				alert("Informe um Registro de Atendimento");
	   			return false;
			}
   		}
   		
   		if(form.idOrdemServico.value == ""){
   			alert("Informe uma Ordem de Serviço");
   			return false;
   		}
   		
   		if(form.sequencialProgramacaoPrevisto.value == ""){
   			alert("Informe um sequencial na programação");
   			return false;
   		} else if(form.sequencialProgramacaoPrevisto.value > form.sequencialProgramacao.value){
   			alert("O maior sequencial na programação deve ser "+form.sequencialProgramacao.value);
   			return false;
   		}
   		
   		return true;
   	}
	   	
   	function validaAcao(tecla, parametro, actionTipo){
   		var actionRetorno = '';
   		var form = document.forms[0];
   		
   		if(actionTipo == 'true'){
   			actionRetorno = 'exibirAcompanhamentoArquivosRoteiroIncluirOrdemServicoAction.do?veioAcompanhamentoRoteiro=true&';
   		}else{
   			actionRetorno = 'exibirAcompanharRoteiroProgramacaoOrdemServicoIncluirAction.do?';
   		}

   		if (parametro == 'registroAtendimento'){
	   		validaEnterComMensagem(tecla, actionRetorno+'objetoConsulta=1','numeroRA','Numero RA');
	   	}else if (parametro == 'ordemServico'){
	   		validaEnterComMensagem(tecla, actionRetorno+'objetoConsulta=2','idOrdemServico','Numero OS');
	   	}
   	}
   	
</script>
</head>

<logic:notPresent name="fecharPopup">
	<body leftmargin="0" topmargin="0" onload="javascript:habilitaGerarOs();window.focus();resizePageSemLink(570, 380);">
</logic:notPresent>

<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="javascript:chamarReload('selecionarAcompanhamentoArquivosRoteiroAction.do');window.close();">
</logic:present>

<html:form action="/exibirAcompanharRoteiroProgramacaoOrdemServicoAction.do"
	name="AcompanharRoteiroProgramacaoOrdemServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AcompanharRoteiroProgramacaoOrdemServicoActionForm"
	method="post">

	<html:hidden property="sequencialProgramacao"/>
	<html:hidden property="dataProgramacao"/>
	<html:hidden property="idEmpresa"/>
	<html:hidden property="idSituacao"/>
	
	<html:hidden property="dataRoteiro"/>
	
	<input type="hidden" id="veioAcompanhamentoRoteiro" value="<%=request.getParameter("veioAcompanhamentoRoteiro")%>"/>
	
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
	          			<td class="parabg">Incluir Ordem de Servi&ccedil;o na Programa&ccedil;&atilde;o da Equipe</td>
	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
	          			</td>
	        		</tr>
	      		</table>
	
	      		<p>&nbsp;</p>
	
	      		<table width="100%" border="0">
        			<tr> 
          				<td align="center">

       					<div id="layerHideDados" style="display:block">

           					<table width="100%" border="0" bgcolor="#cbe5fe">
	    						<tr bgcolor="#99CCFF">
                     				<td align="center">
                     					<a href="javascript:extendeTabela(true);"/>
                     						<b>Dados Gerais da Equipe</b>
                     					</a>
                     				</td>
                    			</tr>
                   			</table>
           				</div>
             				                        		
                  		<div id="layerShowDados" style="display:none">

           					<table width="100%" border="0" bgcolor="#cbe5fe">

	    						<tr bgcolor="#99CCFF">

                     				<td colspan="2">
                     				<div align="center">
                     					<a href="javascript:extendeTabela(false);"/>
                     						<b>Dados Gerais da Equipe</b>
                     					</a>
                   					</div>
                     				</td>

                    			</tr>
	
								<tr>
									<td>
										<strong>C&oacute;digo da Equipe:</strong>
									</td>
									<td>
										<html:text property="idEquipe" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="5"
											maxlength="5" />
									</td>
								</tr>
	
								<tr>
									<td>
										<strong>Nome da Equipe:</strong>
									</td>
									<td>
										<html:text property="nomeEquipe" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="40"
											maxlength="40" />
									</td>
								</tr>
						
	           					<tr> 
	             					<td>
	             						<strong>Placa do Ve&iacute;culo :</strong>
	             					</td>
	             					<td>
										<html:text property="placaVeiculo" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="8"
											maxlength="8" />
	                 				</td>
	           					</tr>
	
	           					<tr> 
	               					<td>
	               						<strong>Carga de Trabalho Dia (hora):</strong>
	               					</td>
	               					<td>
										<html:text property="cargaTrabalhoDia" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="5"
											maxlength="5" />
	                   				</td>
	          					</tr>
	                           	<tr> 
	                           		<td><strong>Unidade Organizacional:</strong></td>
	                           		<td>
										<html:text property="idUnidade" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="4"
											maxlength="4" />
	                             		
										<html:text property="descricaoUnidade" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="40"
											maxlength="40" />
	                               	</td>
	                           	</tr>
	
	                           	<tr> 
	                           		<td><strong>Tipo Perfil Servi&ccedil;o:</strong></td>
	                           		<td>
										<html:text property="idTipoPerfilServico" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="4"
											maxlength="4" />
	                             		
										<html:text property="descricaoTipoPerfilServico" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											size="40"
											maxlength="40" />
	                               	</td>
	                           	</tr>
                    			
                    			<tr> 
                      				<td height="10" colspan="2">
                      					<hr> <span class="style2"><strong>
                        				</strong></span> 
                        			</td>
                    			</tr>
                    			
                    			<tr>
	                      			<td colspan="2">
	                      				<strong>Componentes da Equipe</strong> 
	                      			</td>
                    			</tr>
	                           	
			                    <tr> 
			                   		<td class="style3" colspan="2">
			                   		
			                   		<table width="100%" align="center" bgcolor="#99CCFF">
			                        	
			                        	<!--corpo da segunda tabela-->
			                          	<tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
			                            	<td width="15%">
			                            		<div align="center"><strong>Respons&aacute;vel</strong></div>
			                            	</td>
			                            	<td width="16%">
			                            		<div align="center"><strong>Funcion&aacute;rio</strong></div>
			                            	</td>
			                            	<td width="58%">
			                            		<div align="center"><strong>Nome do Componente</strong></div>
			                            	</td>
			                          	</tr>

										<logic:present name="AcompanharRoteiroProgramacaoOrdemServicoActionForm" 
											property="equipeComponentes"
											scope="session">
					                    
						                    <c:set var="count" value="0"/>
	
	  			                      		<logic:iterate id="equipeComponente"
	  			                      			name="AcompanharRoteiroProgramacaoOrdemServicoActionForm" 
	  			                      			property="equipeComponentes" 
	  			                      			type="gcom.atendimentopublico.ordemservico.EquipeComponentes" 
	  			                      			scope="session">
	  			                      			
	  			                      			<c:set var="count" value="${count+1}"/>
					                        	
					                        	<c:choose>
					                        		<c:when test="${count%2 == '1'}">
					                        			<tr bgcolor="#FFFFFF">
					                        		</c:when>
					                        		<c:otherwise>
					                        			<tr bgcolor="#cbe5fe">
					                        		</c:otherwise>
					                        	</c:choose>
					                        	
						                        <td bordercolor="#90c7fc">
						                        	<div align="center">
						                        		<c:choose>
						                        			<c:when test="${equipeComponente.indicadorResponsavel == '1'}">
						                        				SIM
						                        			</c:when>
						                        			<c:otherwise>
						                        				N&Atilde;O
						                        			</c:otherwise>
						                        		</c:choose>
						                        	</div>
						                        </td>
	
						                        <td bordercolor="#90c7fc">
						                        	<div align="center">
						                        		<c:if test="${equipeComponente.funcionario != null}">
						                        			<bean:write name="equipeComponente" property="funcionario.id" />
						                        		</c:if>
						                        	</div>
						                        </td>
	
						                        <td bordercolor="#90c7fc">
						                        	<div align="left">
						                        		<c:choose>
						                        			<c:when test="${equipeComponente.funcionario == null}">
						                        				<bean:write name="equipeComponente" property="componentes" />
						                        			</c:when>
						                        			<c:otherwise>
						                        				<bean:write name="equipeComponente" property="funcionario.nome" />	
						                        			</c:otherwise>
						                        		</c:choose>
						                        	</div>
						                        </td>
	
						                    </logic:iterate>	
			                          	</logic:present>
		                          	</table>
		                          	</td>
	                          	</tr>
	                           	
	                           	
							</table>
						</div>
						</td>
					</tr>

			        <tr> 
			          	<td colspan="4">
			          		<p>Para incluir uma ordem de servi&ccedil;o na programa&ccedil;&atilde;o
			              	da equipe, informe os dados abaixo:</p>
			          	</td>
			        </tr>

			        <tr> 
			          	<td colspan="4">
				          	<table width="100%" border="0" bgcolor="#cbe5fe">
				            	<tr bgcolor="#99CCFF"> 
				                	<td height="18" colspan="2">
				                	<div align="center">
				                    	<strong>Dados da Ordem de Servi&ccedil;o</strong>
				                    </div>
				                    </td>
				              	</tr>
			              	</table>
		              	</td>
	              	</tr>
              		
              		<tr bgcolor="#cbe5fe">
                		<td> 
	                		<table border="0" width="100%">
								<tr>
									<td height="10">
										<strong>
											N&uacute;mero do RA:
											<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
												<font color="#FF0000">*</font>
											<%}%>	
										</strong>
									</td>
																			
									<td>
										<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
											<html:text maxlength="9" 
												tabindex="1"
												property="numeroRA" 
												size="9"
												onkeypress="javascript:validaAcao(event,'registroAtendimento','true'); return isCampoNumerico(event);"/>
										<%}else{%>
											<html:text maxlength="9" 
												tabindex="1"
												property="numeroRA" 
												size="9"
												onkeypress="javascript:validaAcao(event,'registroAtendimento','false'); return isCampoNumerico(event);"/>
										<%}%>
										
										<a href="javascript:chamarForm('exibirPesquisarRegistroAtendimentoAction.do?caminhoRetornoTelaPesquisaRegistroAtendimento=
											<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
												exibirAcompanhamentoArquivosRoteiroIncluirOrdemServicoAction'
											<%}else{%>
												exibirAcompanharRoteiroProgramacaoOrdemServicoIncluirAction'
											<%}%>
											,document.forms[0].numeroRA);">
				
												<img width="23" 
													height="21" 
													border="0"
													src="<bean:message key="caminho.imagens"/>pesquisa.gif"
													title="Pesquisar RA" /></a>
				
											<logic:present name="numeroRAEncontrada" scope="request">
												<html:text property="descricaoRA" 
													size="30"
													maxlength="30" 
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:present> 
				
											<logic:notPresent name="numeroRAEncontrada" scope="request">
												<html:text property="descricaoRA" 
													size="30"
													maxlength="30" 
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: red" />
											</logic:notPresent>
				
											
											<a href="javascript:limparPesquisaRA();"> 
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a>
									</td>
								</tr>
								
								<tr>
									<td height="10"><strong>Ordem de Servi&ccedil;o:
										<font color="#FF0000">*</font></strong>
									</td>
									
									<td>
										<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
											<html:text maxlength="9" 
											tabindex="1"
											property="idOrdemServico" 
											size="9"
											onkeypress="javascript:validaAcao(event,'ordemServico','true'); return isCampoNumerico(event);"/>
										<%}else{%>
											<html:text maxlength="9" 
											tabindex="1"
											property="idOrdemServico" 
											size="9"
											onkeypress="javascript:validaAcao(event,'ordemServico','false'); return isCampoNumerico(event);"/>
										<%}%>
										
											<a href="javascript:chamarForm('exibirPesquisarOrdemServicoAction.do?caminhoRetornoTelaPesquisaOrdemServico=
											<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
												exibirAcompanhamentoArquivosRoteiroIncluirOrdemServicoAction'
											<%}else{%>
												exibirAcompanharRoteiroProgramacaoOrdemServicoIncluirAction'
											<%}%>
											,document.forms[0].idOrdemServico);">
				
												<img width="23" 
													height="21" 
													border="0"
													src="<bean:message key="caminho.imagens"/>pesquisa.gif"
													title="Pesquisar Os" /></a> 
				
											<logic:present name="idOsEncontrada" scope="request">
												<html:text property="descricaoOrdemServico" 
													size="30"
													maxlength="30" 
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:present> 
				
											<logic:notPresent name="idOsEncontrada" scope="request">
												<html:text property="descricaoOrdemServico" 
													size="30"
													maxlength="30" 
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: red" />
											</logic:notPresent>
				
											
											<a href="javascript:limparPesquisaOs();"> 
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
													border="0" 
													title="Apagar" /></a>
									</td>
								</tr>

			                    <tr> 
			                      	<td height="10"> 
			                      		<strong>Sequencial na Programa&ccedil;&atilde;o:<font color="#FF0000">*</font></strong>
			                      	</td>
			                      	<td>
										<html:text maxlength="4" 
											property="sequencialProgramacaoPrevisto" 
											size="4"
											onkeypress="return isCampoNumerico(event);"/>
			                        </td>
			                    </tr>


	                		</table>
                		</td>
               		</tr>
               		</table>

                    <table width="100%" border="0">
                    <tr> 
                      	<td height="10">
                        	<input name="ButtonFechar" 
                        		type="button" 
                        		class="bottonRightCol" 
                        		value="Fechar"
                        		onClick="javascript:window.close();">

                        	<input name="ButtonLimpar" 
                        		type="button" 
                        		class="bottonRightCol" 
                        		value="Limpar"
                        		onClick="javascript:limpar();">

                        	<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
								<input name="ButtonGerar" 
	                        		type="button" 
	                        		class="bottonRightCol" 
	                        		value="Gerar Ordem de Servi&ccedil;o"
	                        		onClick="javascript:gerarOS('true');">
							<%}else{%>
								<input name="ButtonGerar" 
	                        		type="button" 
	                        		class="bottonRightCol" 
	                        		value="Gerar Ordem de Servi&ccedil;o"
	                        		onClick="javascript:gerarOS('false');">
							<%}%>
                        	
                        		
						</td>
						<td height="27"> 
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