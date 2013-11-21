<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="PesquisarObservacaoRegistroAtendimentoActionForm" /> 

<script language="JavaScript">

	function validarForm(form){
		if(validatePesquisarObservacaoRegistroAtendimentoActionForm(form)){
    		submeterFormPadrao(form);
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

   		var form = document.forms[0];

    	if (tipoConsulta == 'imovel') {
      
      		form.matriculaImovel.value = codigoRegistro;
      		form.inscricaoImovel.value = descricaoRegistro;
      		form.inscricaoImovel.style.color = "#000000";
	  
    	}
    
	}

	function limparForm(){
   		var form = document.forms[0];
   		
		form.periodoAtendimentoInicial.value = '';
		form.periodoAtendimentoFinal.value = '';
		form.matriculaImovel.value = '';
		form.inscricaoImovel.value = '';		   		
	
	}

	/* Replica Data de Atendimento */
	function replicaDataAtendimento() {
		var form = document.forms[0];
		form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value
	}

</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('matriculaImovel')">

<html:form action="/pesquisarObservacaoRegistroAtendimentoAction.do"
	name="PesquisarObservacaoRegistroAtendimentoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.PesquisarObservacaoRegistroAtendimentoActionForm" method="post"
	onsubmit="return validatePesquisarObservacaoRegistroAtendimentoActionForm(this);">

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
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Pesquisar Observações do Registro de Atendimento</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>			
			
			<p>&nbsp;</p>
			
			
					<!--Inicio da Tabela Ligação Água -->
            <table width="100%" border="0">
            	<tr>
                	<td height="31">
                    <table width="100%" border="0" align="center">
                   		<tr>
                       		<td height="10" colspan="2">
                       		Para pesquisar as observações do imóvel,informe os dados abaixo:. </td>
                   		</tr>
                   		
                   		<tr bgcolor="#cbe5fe">

                      		<td align="center" colspan="2">
                      		<table width="100%" border="0" bgcolor="#99CCFF">

					    		<tr bgcolor="#99CCFF">
                         			<td height="18" colspan="2">
                         				<div align="center">
                         					<b>
                         					<span class="style2">Dados do Imóvel </span>
                         					</b>
                         				</div>
                         			</td>
                        		</tr>
                        		
                        		<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
									
										<tr>
											<td bordercolor="#000000" width="25%">
												<strong>Im&oacute;vel:<font color="#FF0000">*</font></strong>
											</td>
											
											<td width="75%">
												<html:text property="matriculaImovel" 
													maxlength="9" 
													size="9"
													onkeypress="validaEnterComMensagem(event, 'exibirPesquisarObservacaoRegistroAtendimentoAction.do','matriculaImovel','Im&oacute;vel');" />
												
												<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
										
													<img width="23" 
														height="21"
														src="<bean:message key="caminho.imagens"/>pesquisa.gif"
														border="0" /></a> 
											
												<logic:present name="imovelNaoEncontrado" scope="request">
													<html:text property="inscricaoImovel"
														size="40" 
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:present> 
										
												<logic:notPresent name="imovelNaoEncontrado" scope="request">
													<html:text property="inscricaoImovel"
														size="40" 
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notPresent> 
										
												<a href="javascript:limparForm();"> 
													<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
														border="0" 
														title="Apagar" /></a>
											</td>
										</tr>
			
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						
						<tr bgcolor="#cbe5fe">

                      		<td align="center" colspan="2">
                      		<table width="100%" border="0" bgcolor="#99CCFF">

					    		<tr bgcolor="#99CCFF">
                         			<td height="18" colspan="2">
                         				<div align="center">
                         					<span class="style2"><b>Dados do Registro Atendimento</b> </span>
                         				</div>
                         			</td>
                        		</tr>
                        		
                        		<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
 									
 										<tr> 
						                	<td><strong>Per&iacute;odo de Atendimento:</strong></td>
						                	
						                	<td>
						                											
												<html:text property="periodoAtendimentoInicial" 
													size="11" 
													maxlength="10" 
													tabindex="3" 
													onkeyup="mascaraData(this, event);replicaDataAtendimento();"/>
												
												<a href="javascript:abrirCalendario('PesquisarObservacaoRegistroAtendimentoActionForm', 'periodoAtendimentoInicial');">
													<img border="0" 
														src="<bean:message key='caminho.imagens'/>calendario.gif" 
														width="16" 
														height="15" 
														border="0" 
														alt="Exibir Calendário" 
														tabindex="4"/></a>
												a <html:text property="periodoAtendimentoFinal" 
													size="11" 
													maxlength="10" 
													tabindex="3" 
													onkeyup="mascaraData(this, event)"/>
												<a href="javascript:abrirCalendario('PesquisarObservacaoRegistroAtendimentoActionForm', 'periodoAtendimentoFinal');">
													<img border="0" 
														src="<bean:message key='caminho.imagens'/>calendario.gif" 
														width="16" 
														height="15" 
														border="0" 
														alt="Exibir Calendário" 
														tabindex="4"/></a>
											
												(dd/mm/aaaa)</td>
			              				</tr>										
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>			
               		</table>
			  		</td>
           		</tr>
        	</table>
 			<table width="100%" border="0">
  				<tr>
					<td>	
						<div align="right">
							<input type="button" name="Button"	class="bottonRightCol" value="Pesquisar"
							   onClick="javascript:validarForm(document.forms[0])" />
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>



	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
