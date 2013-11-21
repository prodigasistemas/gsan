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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarProgramacaoAbastecimentoManutencaoActionForm" />

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<%-- Novos Javascripts --%>

<script language="JavaScript">

	/* Valida Form */
	function validaForm() {
		var form = document.forms[0];
		
		if(	validaCampos() && 
			validateInformarProgramacaoAbastecimentoManutencaoActionForm(form) && 
			validaDatas() ){
			
    		form.action='exibirAtualizarProgramacaoAbastecimentoManutencaoAction.do?tipoOperacao=A';
	    	form.submit();

		}
	}

	function validaDatas(){

		var form = document.forms[0];
		
		var msg = '';
		var tipoProgramacao = '';
		var retorno = true;
		if(form.tipoProgramacao.value == 'M'){
			tipoProgramacao = 'Manutenção';
		}else{
			tipoProgramacao = 'Abastecimento';
		}

		var mesReferencia = form.mesAnoReferencia.value.substring(0, 2);
		var anoReferencia = form.mesAnoReferencia.value.substring(3, 7);
		
		var mesInicio = form.dataInicio.value.substring(3, 5);
		var anoInicio = form.dataInicio.value.substring(6, 10);

		var mesFim = form.dataFim.value.substring(3, 5);
		var anoFim = form.dataFim.value.substring(6, 10);
		
		if(mesReferencia != mesInicio || anoReferencia != anoInicio){
			msg = 'Mês e Ano da Data Início de '+tipoProgramacao+' não corresponde ao Mês e Ano de Referência informado.\n';
		}
			
		if(mesReferencia != mesFim || anoReferencia != anoFim){
			msg = msg + 'Mês e Ano da Data Fim de '+tipoProgramacao+' não corresponde ao Mês e Ano de Referência informado.\n';
		}

    	if (comparaData(form.dataInicio.value, '>', form.dataFim.value)){

			msg = msg + 'Data Final de '+tipoProgramacao+' deve ser igual ou posterior à Data Início de '+tipoProgramacao+'.\n';

		}else if (comparaData(form.dataInicio.value, '=', form.dataFim.value)){

			if(!validarIntervaloHora(form.horaInicio.value,form.horaFim.value)){
				msg = msg + 'Hora Fim de '+tipoProgramacao+' deve ser igual ou posterior à Hora Início de '+tipoProgramacao+'.\n';
			}		
		}		

		if( msg != '' ){
			alert(msg);
			return false;
		}else {
			return true;
		}
	}

	function validaCampos () {

		var form = document.forms[0];
		
		var msg = '';

		if(form.tipoProgramacao.value == 'M'){

			if(form.descricaoManutencaoProgramacao != null && form.descricaoManutencaoProgramacao.value  == '' ) {
				msg = 'Informe Descrição.\n';
			}
			
			if( form.situacaoManutencaoProgramacao != null && form.situacaoManutencaoProgramacao.value  == '' ) {
				msg = msg + 'Informe Situação.\n';
			}
		}
		
		if( msg != '' ){
			alert(msg);
			return false;
		}else {
			return true;
		}
		
		
	}

   	/* Fecha Popup */
   	function fechar() {
   		window.close();
   	}
   	
   	function replicaData() {
		var form = document.forms[0];
		form.dataFim.value = form.dataInicio.value;
	}
	
   	function replicaHora() {
		var form = document.forms[0];
		form.horaFim.value = form.horaInicio.value;
	}   	
</script>
</head>

<logic:notPresent name="fechaPopup" scope="request">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(600, 550)";>
</logic:notPresent>

<logic:present name="fechaPopup" scope="request">
	<body leftmargin="0" topmargin="0" onload="chamarReload('exibirInformarProgramacaoAbastecimentoManutencaoAction.do?tipoOperacao=A');window.close()">
</logic:present>


<html:form action="/exibirAtualizarProgramacaoAbastecimentoManutencaoAction.do"
	name="InformarProgramacaoAbastecimentoManutencaoActionForm"
	type="gcom.gui.operacional.abastecimento.InformarProgramacaoAbastecimentoManutencaoActionForm"
	method="post">

	<html:hidden property="tipoProgramacao"/>
	<html:hidden property="municipioCopiar"/>
	<html:hidden property="bairroCopiar"/>

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
                   		<c:choose>
                   			<c:when test="${InformarProgramacaoAbastecimentoManutencaoActionForm.tipoProgramacao == 'A'}">
	          					<td class="parabg">Informar Programa&ccedil;&atilde;o de Abastecimento</td>
          						<html:hidden property="descricaoManutencaoProgramacao"/>
                   			</c:when>
                   			<c:otherwise>
	          					<td class="parabg">Informar Programa&ccedil;&atilde;o de Manuten&ccedil;&atilde;o </td>
                   			</c:otherwise>
                   		</c:choose>
	          			

	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
	          			</td>
	        		</tr>
	      		</table>
	      		<p>&nbsp;</p>
	      		<table width="100%" border="0">
					<tr>
						<td><strong>Mês e Ano de Referência:</strong></td>

						<td>
							<html:text property="mesAnoReferencia" 
								size="7"
								maxlength="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>
						</td>
					</tr>
				  	
				  	<tr> 
           				<td colspan="4"><hr></td>
				  	</tr>
					

					<tr>
						<td><strong>Munic&iacute;pio:</strong></td>

						<td>
							<html:text property="municipio" 
								size="7"
								maxlength="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>

							<html:text property="nomeMunicipio" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>
						</td>
					</tr>

					<tr>
						<td><strong>Bairro:</strong></td>

						<td>
							<html:text property="bairro" 
								size="7"
								maxlength="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>

							<html:text property="nomeBairro" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>
						</td>
					</tr>

					<tr>
						<td><strong>&Aacute;rea de Bairro:</strong></td>

						<td>
							<html:text property="areaBairro" 
								size="7"
								maxlength="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>

							<html:text property="nomeAreaBairro" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>
						</td>
					</tr>

   					<tr> 
     					<td height="24" colspan="3">
     						<hr>
     					</td>
   					</tr>

					<tr>
						<td><strong>Sistema de Abastecimento:</strong></td>

						<td>
							<html:text property="sistemaAbastecimento" 
								size="7"
								maxlength="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>

							<html:text property="nomeSistemaAbastecimento" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>
						</td>
					</tr>

					<tr>
						<td><strong>Setor de Abastecimento:</strong></td>

						<td>
							<html:text property="setorAbastecimento" 
								size="7"
								maxlength="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>

							<html:text property="nomeSetorAbastecimento" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>
						</td>
					</tr>

					<tr>
						<td><strong>Zona de Abastecimento:</strong></td>

						<td>
							<html:text property="zonaAbastecimento" 
								size="7"
								maxlength="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>

							<html:text property="nomeZonaAbastecimento" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>
						</td>
					</tr>

					<tr>
						<td><strong>Distrito Operacional:</strong></td>

						<td>
							<html:text property="distritoOperacional" 
								size="7"
								maxlength="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>

							<html:text property="nomeDistritoOperacional" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>
						</td>
					</tr>

   					<tr> 
     					<td height="24" colspan="3">
     						<hr>
     					</td>
   					</tr>

	           		<tr>
                   		<c:choose>
                   			<c:when test="${InformarProgramacaoAbastecimentoManutencaoActionForm.tipoProgramacao == 'A'}">
			                	<td colspan="4">
			                		<p>Para informar uma programa&ccedil;&atilde;o de abastecimento, 
			                		informe os dados abaixo:</p>
			                	</td>

                   			</c:when>
                   			<c:otherwise>
			                	<td colspan="4">
			                		<p>Para informar uma programa&ccedil;&atilde;o de manuten&ccedil;&atilde;o, 
			                		informe os dados abaixo:</p>
			                	</td>
                   			</c:otherwise>
                   		</c:choose>

	               	</tr>

                  	<tr>
                    	<td colspan="2">
                    	<table width="100%" border="0" bgcolor="#99CCFF">
                        	<tr bgcolor="#99CCFF">
                          		<td height="18" colspan="2">
                          			<div align="center">
			                   		<c:choose>
			                   			<c:when test="${InformarProgramacaoAbastecimentoManutencaoActionForm.tipoProgramacao == 'A'}">
			                          		<strong>Dados da Programa&ccedil;&atilde;o de Abastecimento </strong>
			                   			</c:when>
			                   			<c:otherwise>
                          					<strong>Dados da Programa&ccedil;&atilde;o de Manuten&ccedil;&atilde;o </strong>
			                   			</c:otherwise>
			                   		</c:choose>
                          			</div>
                          		</td>
                        	</tr>
                        	<tr bgcolor="#cbe5fe">
                          		<td>
                          		<table border="0" width="100%">

	                        		<c:if test="${InformarProgramacaoAbastecimentoManutencaoActionForm.tipoProgramacao == 'M'}">
										<tr>
											<td><strong>Descri&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
					
											<td>
												<html:textarea property="descricaoManutencaoProgramacao" 
													cols="45" 
													rows="6"/>													
											</td>
										</tr>

										<tr>
											<td><strong>Situa&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
					
											<td>
												<html:select property="situacaoManutencaoProgramacao" style="width: 100px;">
													<html:option value="1">Em Aberto</html:option>
													<html:option value="2">Conclu&iacute;da</html:option>
												</html:select> 														
											</td>
										</tr>

	                        		</c:if>

									<tr>
										<td><strong>Data In&iacute;cio:<font color="#FF0000">*</font></strong></td>
				
										<td>
											<html:text property="dataInicio" 
												size="10"
												maxlength="10"
												onkeyup="mascaraData(this, event);replicaData();"/>

											<a href="javascript:abrirCalendario('InformarProgramacaoAbastecimentoManutencaoActionForm', 'dataInicio');">
												<img border="0" 
													src="<bean:message key='caminho.imagens'/>calendario.gif" 
													width="16" 
													height="15" 
													border="0" 
													alt="Exibir Calendário" 
													tabindex="4"/></a>
											
											<strong>(dd/mm/aaaa)</strong> 
												
										</td>
									</tr>

									<tr>
										<td><strong>Data Fim :<font color="#FF0000">*</font></strong></td>
				
										<td>
											<html:text property="dataFim" 
												size="10"
												maxlength="10"
												onkeyup="mascaraData(this, event);"/>
												
											<a href="javascript:abrirCalendario('InformarProgramacaoAbastecimentoManutencaoActionForm', 'dataFim');">
												<img border="0" 
													src="<bean:message key='caminho.imagens'/>calendario.gif" 
													width="16" 
													height="15" 
													border="0" 
													alt="Exibir Calendário" 
													tabindex="4"/></a>
											
											<strong>(dd/mm/aaaa)</strong> 
										</td>
									</tr>

									<tr>
										<td><strong>Hora In&iacute;cio :<font color="#FF0000">*</font></strong></td>
				
										<td>
											<html:text property="horaInicio" 
												size="5"
												maxlength="5"
												onkeyup="mascaraHoraSemMensagem(this, event);replicaHora();"/>
											<strong>(hh:mm)</strong>
										</td>
									</tr>

									<tr>
										<td><strong>Hora Fim :<font color="#FF0000">*</font></strong></td>
				
										<td>
											<html:text property="horaFim" 
												size="5"
												maxlength="5"
												onkeyup="mascaraHoraSemMensagem(this, event)"/>
											<strong>(hh:mm)</strong>
										</td>
									</tr>
                          		</table>
                          		</td>
                       		</tr>
                   		</table>
                   		</td>
               		</tr>

	        		<tr> 
	          			<td height="27" colspan="5"> 
	          				<div align="right"> 

	              				<input name="Button2" 
	              					type="button" 
	              					class="bottonRightCol" 
	              					value="Fechar" 
	              					onClick="javascript:fechar();">

	              				<input name="Button" 
	              					type="button" 
	              					class="bottonRightCol" 
	              					value="Atualizar" 
	              					onclick="javascript:validaForm();">
	              				
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