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

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ElaborarOrdemServicoRoteiroCriteriosActionForm"/>

<script language="JavaScript">

	function validarForm(){
   		var form = document.forms[0];

    	if(validateElaborarOrdemServicoRoteiroCriteriosActionForm(form)){
			if(validaTodosPeriodos()){
				enviarSelectMultiplo('ElaborarOrdemServicoRoteiroCriteriosActionForm','tipoServicoSelecionados');
	   			form.submit();
			}
	  	}
    }
    function validaTodosPeriodos() {

		var form = document.forms[0];

    	if (comparaData(form.periodoAtendimentoInicial.value, '>', form.periodoAtendimentoFinal.value)){

			alert('Data Final do Período de Atendimento é anterior à Data Inicial do Período de Atendimento');
			return false;

		} else if (comparaData(form.periodoGeracaoInicial.value, '>', form.periodoGeracaoFinal.value)){

			alert('Data Final do Período da Geração é anterior à Data Inicial do Período da Geração');
			return false;

		} else if (comparaData(form.periodoClienteInicial.value, '>', form.periodoClienteFinal.value)){

			alert('Data Final do Período de Previsão para Cliente é anterior à Data Inicial do Período de Previsão para Cliente');
			return false;

		} else if (comparaData(form.periodoAgenciaInicial.value, '>', form.periodoAgenciaFinal.value)){

			alert('Data Final do Período de Previsão para Agência Reguladora é anterior à Data Inicial do Período de Previsão para Agência Reguladora');
			return false;
		
		} else if(	(form.diaAtrasoInicial.value != "" && form.diaAtrasoFinal.value != "") && 
					(eval(form.diaAtrasoInicial.value) > eval(form.diaAtrasoFinal.value)) ){

			alert('Dias de Atraso Final do Período é anterior à Dias de Atraso Inicial');
			return false;
		
		}    

		return true;
    }

	function limparForm(){

		var form = document.forms[0];

		form.periodoAtendimentoInicial.value="";
		form.periodoAtendimentoFinal.value="";

		form.periodoGeracaoInicial.value="";
		form.periodoGeracaoFinal.value="";

		limparData();
		
		form.criterioSelecao.selectedIndex = 0;
		form.origemServicos[0].checked = true;
		
		form.servicoDiagnosticado[0].checked = true;
		form.servicoDiagnosticado[1].checked = false;
		form.servicoDiagnosticado[2].checked = false;		

		form.servicoAcompanhamento[0].checked = true;
		form.servicoAcompanhamento[1].checked = false;
		form.servicoAcompanhamento[2].checked = false;		

		MoverTodosDadosSelectMenu2PARAMenu1('ElaborarOrdemServicoRoteiroCriteriosActionForm', 'tipoServico', 'tipoServicoSelecionados');
		
		mudaForm();
		
		
	}

	//Replica Data de Atendimento
	function replicaDataAtendimento() {
		var form = document.forms[0];
		form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value;
	}

	//Replica Data de Geracao
	function replicaDataGeracao() {
		var form = document.forms[0];
		form.periodoGeracaoFinal.value = form.periodoGeracaoInicial.value;
	}
    
	//Replica Data de Previsão do Cliente
	function replicaDataCliente() {
		var form = document.forms[0];
		form.periodoClienteFinal.value = form.periodoClienteInicial.value;
	}

	//Replica Data de Previsão para Agência
	function replicaDataAgencia() {
		var form = document.forms[0];
		form.periodoAgenciaFinal.value = form.periodoAgenciaInicial.value;
	}
	
	function replicaDiaAtraso(){
		var form = document.forms[0];
		form.diaAtrasoFinal.value = form.diaAtrasoInicial.value;
	}

	function mudaForm(){

		var form = document.forms[0];
   		form.action = "exibirElaborarOrdemServicoRoteiroCriteriosAction.do";
   		form.submit();
	
	}
	
	function limparData(){
		var form = document.forms[0];
		
		form.diaAtrasoInicial.value="";
		form.diaAtrasoFinal.value="";

		form.periodoClienteInicial.value="";
		form.periodoClienteFinal.value="";

		form.periodoAgenciaInicial.value="";
		form.periodoAgenciaFinal.value="";
	}
	
	function validaForm(){
		var form = document.forms[0];
		
		if(form.origemServicos[1].checked){

			limparData();

			form.diaAtrasoInicial.disabled=true;
			form.diaAtrasoFinal.disabled=true;

			form.periodoClienteInicial.disabled=true;
			form.periodoClienteFinal.disabled=true;

			form.periodoAgenciaInicial.disabled=true;
			form.periodoAgenciaFinal.disabled=true;
		}else{

			form.diaAtrasoInicial.disabled=false;
			form.diaAtrasoFinal.disabled=false;

			form.periodoClienteInicial.disabled=false;
			form.periodoClienteFinal.disabled=false;

			form.periodoAgenciaInicial.disabled=false;
			form.periodoAgenciaFinal.disabled=false;
		}
	
	}
	
	//So chama a função abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(objetoRelacionado,fieldNameOrigem,fieldNameDestino){
		
		if(objetoRelacionado.disabled != true){
			if(fieldNameDestino != ''){
				abrirCalendarioReplicando('ElaborarOrdemServicoRoteiroCriteriosActionForm', fieldNameOrigem,fieldNameDestino);
			}else{
				abrirCalendario('ElaborarOrdemServicoRoteiroCriteriosActionForm', fieldNameOrigem);
			}
		}
	}
	
	
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:validaForm();">
	
<html:form action="/elaborarOrdemServicoRoteiroCriteriosAction.do"
	name="ElaborarOrdemServicoRoteiroCriteriosActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ElaborarOrdemServicoRoteiroCriteriosActionForm"
	method="post">
	

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
			
			<td width="615" valign="top" class="centercoltext">

			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Pesquisar Ordem de Servi&ccedil;o</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para 
                        selecionar ordens de servi&ccedil;o para elabora&ccedil;&atilde;o 
                        do roteiro do dia 
                        <strong>
                        	<bean:write name="ElaborarOrdemServicoRoteiroCriteriosActionForm" property="dataRoteiro"/>
						</strong>, informe os dados abaixo:</td>
				</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
				
				<tr>
					<td>
						<strong>Origem dos Servi&ccedil;os:</strong>
					</td>
					<td>
						<html:radio property="origemServicos" value="1" onclick="javascript:mudaForm();"/>
						<strong>Solicitados</strong>
						<html:radio property="origemServicos" value="2" onclick="javascript:mudaForm();"/>
						<strong>Seletivos</strong>
						<html:radio property="origemServicos" value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" onclick="javascript:mudaForm();"/>
						<strong>Ambos</strong> 
						
					</td>
				</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
				

				<tr>
					<td width="45%">
						<strong>Crit&eacute;rio de Sele&ccedil;&atilde;o:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="criterioSelecao" style="width: 180px;" onchange="javascript:mudaForm();">
							<html:option value="1">Tipo de Servi&ccedil;o</html:option>
							<html:option value="2">Tipo de Equipe - Perfil</html:option>
							<html:option value="3">Unidade de Atendimento</html:option>
							<html:option value="4">Localidade</html:option>
							<html:option value="5">Setor Comercial</html:option>
							<html:option value="6">Distrito Operacional</html:option>
						</html:select>
						</strong>
					</td>
				</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>
					<td colspan="2">
					<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
						<tr>
							<td width="200">
							
								<div align="left"><strong>Disponíveis</strong></div>

								<html:select property="tipoServico" size="6" multiple="true" style="width:230px">
									<html:options collection="colecaoTipoServico" labelProperty="descricao" property="id" />
								</html:select>
							</td>

							<td width="5" valign="center"><br>
								<table width="50" align="center">
									<tr>
										<td align="center"><input type="button" class="bottonRightCol"
											onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('ElaborarOrdemServicoRoteiroCriteriosActionForm', 'tipoServico', 'tipoServicoSelecionados');"
											value=" &gt;&gt; "></td>
									</tr>
	
									<tr>
										<td align="center"><input type="button" class="bottonRightCol"
											onclick="javascript:MoverDadosSelectMenu1PARAMenu2('ElaborarOrdemServicoRoteiroCriteriosActionForm', 'tipoServico', 'tipoServicoSelecionados');"
											value=" &nbsp;&gt;  "></td>
									</tr>
	
									<tr>
										<td align="center"><input type="button" class="bottonRightCol"
											onclick="javascript:MoverDadosSelectMenu2PARAMenu1('ElaborarOrdemServicoRoteiroCriteriosActionForm', 'tipoServico', 'tipoServicoSelecionados');"
											value=" &nbsp;&lt;  "></td>
									</tr>
	
									<tr>
										<td align="center"><input type="button" class="bottonRightCol"
											onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('ElaborarOrdemServicoRoteiroCriteriosActionForm', 'tipoServico', 'tipoServicoSelecionados');"
											value=" &lt;&lt; "></td>
									</tr>
								</table>
							</td>

							<td>
								<div align="left"><strong>Selecionados</strong></div>
								<html:select property="tipoServicoSelecionados" size="6" multiple="true" style="width:200px">
								</html:select>
							</td>
						</tr>
					</table>
					</td>
				</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
				
				<tr>
					<td width="600">
						<strong>Servi&ccedil;os Diagnosticados:</strong>
					</td>
					<td>
						<html:radio property="servicoDiagnosticado" value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>"/>
						<strong>Todos</strong> 
						<html:radio property="servicoDiagnosticado" value="<%=""+ConstantesSistema.SIM%>" />
						<strong>Sim</strong>
						<html:radio property="servicoDiagnosticado" value="<%=""+ConstantesSistema.NAO%>" />
						<strong>N&atilde;o</strong>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Servi&ccedil;os Acompanhamento pela Ag&ecirc;ncia Reguladora:</strong>
					</td>
					<td>
						<html:radio property="servicoAcompanhamento" value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" />
						<strong>Todos</strong> 
						<html:radio property="servicoAcompanhamento" value="<%=""+ConstantesSistema.SIM%>" />
						<strong>Sim</strong>
						<html:radio property="servicoAcompanhamento" value="<%=""+ConstantesSistema.NAO%>" />
						<strong>N&atilde;o</strong>
					</td>
				</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

              	<tr>
	                <td>
	                	<strong>Dias de Atraso:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	<strong> 
							<html:text property="diaAtrasoInicial" 
								size="4" 
								maxlength="4" 
								tabindex="3"
								onkeyup="replicaDiaAtraso();"/>
							a 
							<html:text property="diaAtrasoFinal" 
								size="4" 
								maxlength="4" 
								tabindex="3"/>
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Atendimento:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoAtendimentoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataAtendimento();"/>
							
							<a href="javascript:abrirCalendarioReplicando('ElaborarOrdemServicoRoteiroCriteriosActionForm', 'periodoAtendimentoInicial','periodoAtendimentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15"
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/>
							</a>
							a 
							
							<html:text property="periodoAtendimentoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"/>
								
							<a href="javascript:abrirCalendario('ElaborarOrdemServicoRoteiroCriteriosActionForm', 'periodoAtendimentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif"
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/>
							</a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Gera&ccedil;&atilde;o:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoGeracaoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataGeracao();"/>
							
							<a href="javascript:abrirCalendarioReplicando('ElaborarOrdemServicoRoteiroCriteriosActionForm', 'periodoGeracaoInicial','periodoGeracaoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/>
							</a>
							a 
							
							<html:text property="periodoGeracaoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"/>
								
							<a href="javascript:abrirCalendario('ElaborarOrdemServicoRoteiroCriteriosActionForm', 'periodoGeracaoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16"
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/>
							</a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Previs&atilde;o para Cliente:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoClienteInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataCliente();"/>
							
							<a href="javascript:chamarCalendario(document.forms[0].periodoClienteInicial,'periodoClienteInicial','periodoClienteFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/>
							</a>
							a 
							
							<html:text property="periodoClienteFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"/>
								
							<a href="javascript:chamarCalendario(document.forms[0].periodoClienteFinal,'periodoClienteFinal','');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/>
							</a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Previs&atilde;o para Ag&ecirc;ncia
                        Reguladora:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoAgenciaInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataAgencia();"/>
							
							<a href="javascript:chamarCalendario(document.forms[0].periodoAgenciaInicial,'periodoAgenciaInicial','periodoAgenciaFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16"
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/>
							</a>
							a 
							
							<html:text property="periodoAgenciaFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"/>
								
							<a href="javascript:chamarCalendario(document.forms[0].periodoAgenciaFinal,'periodoAgenciaFinal','');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/>
							</a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

           		<tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>
					<td height="24">
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limparForm();"/>
					</td>
					
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol"
							value="Pesquisar" 
							onClick="javascript:validarForm()" />
					</td>
					
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
