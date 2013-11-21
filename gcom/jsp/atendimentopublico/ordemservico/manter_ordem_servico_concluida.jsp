<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="ManterOrdemServicoConcluidaActionForm" staticJavascript="false" dynamicJavascript="true"/>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

	function validarPesquisa() {
		var form = document.forms[0];
		
		if (form.idOrdemServicoPesquisado.value == '') {
			form.idOrdemServicoPesquisado.focus();
    		alert('Infome a Ordem de Serviço.');
    		return;
		}else {
			//submeterFormPadrao(form);
			window.location.href='/gsan/exibirAtualizarOrdemServicoConcluidaAction.do?idOrdemServicoPesquisado='+form.idOrdemServicoPesquisado.value;
		}
	}
	
	function verificarDataFiscalizacao() {
		var form = document.forms[0];
		
		if (form.dataFiscalizacao1.value != "" && form.dataFiscalizacao2.value != "" && 
		    form.dataFiscalizacao3.value == "" && (form.codigoFiscalizacao[1].checked ||
		    form.codigoFiscalizacao[2].checked) ) {
		    
			form.dataFiscalizacao3.readOnly = false;
			form.dataFiscalizacao3.style.backgroundColor = '';
			
			form.dataFiscalizacao1.readOnly = true;
			form.dataFiscalizacao1.style.backgroundColor = '#EFEFEF';
			form.dataFiscalizacao2.readOnly = true;
			form.dataFiscalizacao2.style.backgroundColor = '#EFEFEF';
		
		}else if (form.dataFiscalizacao1.value != "" && form.dataFiscalizacao2.value == "" && 
		    form.dataFiscalizacao3.value == "" && (form.codigoFiscalizacao[1].checked ||
		    form.codigoFiscalizacao[2].checked) ) {
		    
			form.dataFiscalizacao2.readOnly = false;
			form.dataFiscalizacao2.style.backgroundColor = '';
			
			form.dataFiscalizacao1.readOnly = true;
			form.dataFiscalizacao1.style.backgroundColor = '#EFEFEF';
			form.dataFiscalizacao3.readOnly = true;
			form.dataFiscalizacao3.style.backgroundColor = '#EFEFEF';
		
		}else if (form.dataFiscalizacao1.value == "" && form.dataFiscalizacao2.value == "" && 
		    form.dataFiscalizacao3.value == "" && (form.codigoFiscalizacao[1].checked ||
		    form.codigoFiscalizacao[2].checked) && form.idOrdemServico.value != "") {
		    
			form.dataFiscalizacao1.readOnly = false;
			form.dataFiscalizacao1.style.backgroundColor = '';
			
			form.dataFiscalizacao2.readOnly = true;
			form.dataFiscalizacao2.style.backgroundColor = '#EFEFEF';
			form.dataFiscalizacao3.readOnly = true;
			form.dataFiscalizacao3.style.backgroundColor = '#EFEFEF';
		
		}else if (form.dataFiscalizacao1.value != "" && form.dataFiscalizacao2.value != "" && 
		    form.dataFiscalizacao3.value != "" && (form.codigoFiscalizacao[1].checked ||
		    form.codigoFiscalizacao[2].checked) && form.idOrdemServico.value != "") {
		    
			form.dataFiscalizacao1.readOnly = true;
			form.dataFiscalizacao1.style.backgroundColor = '#EFEFEF';
			form.dataFiscalizacao2.readOnly = true;
			form.dataFiscalizacao2.style.backgroundColor = '#EFEFEF';
			form.dataFiscalizacao3.readOnly = true;
			form.dataFiscalizacao3.style.backgroundColor = '#EFEFEF';
		}
	}
	
	function habilitaDesabilitaDataFiscalizacao() {
		var form = document.forms[0];
		
		if (form.dataFiscalizacao1.readOnly && form.dataFiscalizacao1.value == "" &&
			form.dataFiscalizacao2.readOnly && form.dataFiscalizacao2.value == "" &&
			form.dataFiscalizacao3.readOnly && form.dataFiscalizacao3.value == "") {
			
			form.dataFiscalizacao1.readOnly = false;
			form.dataFiscalizacao1.style.backgroundColor = '';
		}
	}
	
	function desabilitaDataFiscalizacao() {
		var form = document.forms[0];
		
		form.dataFiscalizacao1.readOnly = true;
		form.dataFiscalizacao1.style.backgroundColor = '#EFEFEF';
		form.dataFiscalizacao1.value = "";
		form.dataFiscalizacao2.readOnly = true;
		form.dataFiscalizacao2.style.backgroundColor = '#EFEFEF';
		form.dataFiscalizacao2.value = "";
		form.dataFiscalizacao3.readOnly = true;
		form.dataFiscalizacao3.style.backgroundColor = '#EFEFEF';
		form.dataFiscalizacao3.value = "";
		
		form.idUsuario.readOnly = true;
		form.idUsuario.style.backgroundColor = '#EFEFEF';
		form.idUsuario.value = "";
	}
	
	function verificaFuncionario() {
		var form = document.forms[0];
		
		if (form.idOrdemServico.value != "" &&
			(form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked ) &&
			 form.dataFiscalizacao1.value != "" && form.dataFiscalizacao2.value != "" && 
		     form.dataFiscalizacao3.value != "") {
			
			form.idUsuario.readOnly = true;
			form.idUsuario.style.backgroundColor = '#EFEFEF';
		}else if (form.idOrdemServico.value != "" &&
			(form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked ) ) {
		     
			form.idUsuario.readOnly = false;
			form.idUsuario.style.backgroundColor = '';
		}else {
			form.idUsuario.readOnly = true;
			form.idUsuario.style.backgroundColor = '#EFEFEF';
		}
	}
	
	function verificaDataEncerramentoBoletim() {
		var form = document.forms[0];
		var retorno = false;
		
		if (form.dataEncerramentoBoletim.value != "") {
			// Verifica a situacao do Boletim
			if (form.codigoFiscalizacao[1].checked) {
				alert('Esta Ordem já foi Encerrada e Aprovada. Não pode ser Alterada.');
				form.ButtonAtualizar.disabled = true;
			}else {
				retorno = true;
			}
		//}else if (form.codigoFiscalizacao[1].checked) {
		//	alert('Esta Ordem já está Aprovada. Não pode ser Alterada.');
		//	form.ButtonAtualizar.disabled = true;
		}else {
			//form.ButtonAtualizar.disabled = false;
			retorno = true;
		}
		return retorno;
	}
	
	function validarForm(){
   		var form = document.forms[0];
		
		if (form.idOrdemServico.value != "") {
			if (!form.codigoFiscalizacao[0].checked) {
				// Verifica as Datas de Fiscalizacao
				if (!form.dataFiscalizacao1.readOnly &&
					(form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked) ) {
					
					if (form.dataFiscalizacao1.value == "") {
						form.dataFiscalizacao1.focus();
			    		alert('Infome a 1a Data de Fiscalização.');
		    			return;
					}
				}else if (form.dataFiscalizacao1.value != "" && form.dataFiscalizacao1.readOnly &&
						  !form.dataFiscalizacao2.readOnly &&
						  (form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked) ) {
					
					if (form.dataFiscalizacao2.value == "") {
						form.dataFiscalizacao2.focus();
			    		alert('Infome a 2a Data de Fiscalização.');
		    			return;
					}
				}else if (form.dataFiscalizacao2.value != "" && form.dataFiscalizacao2.readOnly &&
						  !form.dataFiscalizacao3.readOnly &&
						  (form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked) ) {
						  
					if (form.dataFiscalizacao3.value == "") {
						form.dataFiscalizacao3.focus();
			    		alert('Infome a 3a Data de Fiscalização.');
		    			return;
					}
				}
				
				if (form.codigoFiscalizacao[1].checked || form.codigoFiscalizacao[2].checked) {
					if (form.idUsuario.value == "") {
						form.idUsuario.focus();
			    		alert('Infome o Funcionário.');
		    			return;
					}
				}
				
				//form.action = "exibirAtualizarOrdemServicoConcluidaAction.do?atualizar=sim";
				//form.submit();
				submeterFormPadrao(form);
			}else {
				//alert("Para Atualizar os dados da Fiscalização da Ordem informe a Situação Aprovado ou Reprovado.");
				//return;
				submeterFormPadrao(form);
			}
		}else {
			form.idOrdemServicoPesquisado.focus();
			alert("Para Atualizar os dados da Fiscalização Informe uma Ordem de Servico.");
			return;
		}
    }
    
    function caracteresespeciais () { 
		this.aa = new Array("quantidadeDias", "Quantidade de Dias possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 
    
    function IntegerValidations () { 
		this.aa = new Array("quantidadeDias", "Quantidade de Dias deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');" >

	<html:form action="/manterOrdemServicoConcluidaAction" method="post"
		name="ManterOrdemServicoConcluidaActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.ManterOrdemServicoConcluidaActionForm">
		
		<html:hidden property="dataEncerramentoBoletim" />
		<html:hidden property="dataUltimaAlteracao" />
		<html:hidden property="codigoFiscalizacaoAnterior" />
		
		<logic:equal name="usuarioPermissaoDesfazerFiscalizacao" value="true">
			<input type="hidden" name="permissaoDesfazerFiscalizacao" value="SIM">
		</logic:equal>
		<logic:equal name="usuarioPermissaoDesfazerFiscalizacao" value="false">
			<input type="hidden" name="permissaoDesfazerFiscalizacao" value="NAO">
		</logic:equal>
		
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
				<td width="600" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
	
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Atualizar dados da Fiscaliza&ccedil;&atilde;o</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td colspan="2">
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td width="120" nowrap="nowrap"><strong>N&uacute;mero da OS:</strong></td>
									<td align="left" width="100">
										<html:text property="idOrdemServicoPesquisado" maxlength="9" size="9" onkeyup="verificaNumeroInteiro(this);"></html:text>
									</td>
									<td align="left"><input type="button" class="bottonRightCol" value="Pesquisar"
										style="width: 80px" onclick="validarPesquisa();">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr><td colspan="2"><hr></td></tr>
				</table>
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2" align="center"><strong>Dados do Encerramento da Ordem de Servi&ccedil;o</strong></td>
					</tr>
					
					<tr><td colspan="2"></td></tr>
					
					<tr>
						<td width="180"><strong>N&uacute;mero da OS:&nbsp;</strong></td>
						<td align="left"><html:text property="idOrdemServico" readonly="true"
							style="background-color:#EFEFEF; border:0;" size="9" />
						</td>
					</tr>
					
					<tr>
						<td><strong>Data de Emiss&atilde;o:&nbsp;</strong></td>
						<td align="left"><html:text property="dataEmissao" readonly="true"
							style="background-color:#EFEFEF; border:0;" size="10" />
						</td>
					</tr>
					
					<tr>
						<td><strong>Data de Encerramento:&nbsp;</strong></td>
						<td align="left"><html:text property="dataEncerramento" readonly="true"
							style="background-color:#EFEFEF; border:0;" size="10" />
						</td>
					</tr>
					
					<tr>
						<td><strong>Matr&iacute;cula do Im&oacute;vel:&nbsp;</strong></td>
						<td align="left"><html:text property="idImovel" readonly="true"
							style="background-color:#EFEFEF; border:0;" size="15" />
						</td>
					</tr>
					
					<tr>
						<td><strong>Local de Instala&ccedil;&atilde;o:&nbsp;</strong></td>
						<td align="left"><html:text property="descricaoHidrometroLocalInstalacao" readonly="true"
							style="background-color:#EFEFEF; border:0;" size="20" />
						</td>
					</tr>
					
					<tr>
						<td><strong>Troca de Prote&ccedil;&atilde;o:</strong></td>
						<td align="left">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<logic:present name="indicadorTrocaProtecao">
										<logic:equal name="indicadorTrocaProtecao" value="1">
											<td width="70" nowrap><input type="radio" name="indicadorTrocaProtecao" value="1" checked="checked" disabled="disabled">&nbsp;SIM</td>
											<td align="left" nowrap><input type="radio" name="indicadorTrocaProtecao" value="2" disabled="disabled">&nbsp;N&Atilde;O</td>
										</logic:equal>
										<logic:equal name="indicadorTrocaProtecao" value="2">
											<td width="70" nowrap><input type="radio" name="indicadorTrocaProtecao" value="1" disabled="disabled">&nbsp;SIM</td>
											<td align="left" nowrap><input type="radio" name="indicadorTrocaProtecao" value="2" checked="checked" disabled="disabled">&nbsp;N&Atilde;O</td>
										</logic:equal>
									</logic:present>
									<logic:notPresent name="indicadorTrocaProtecao">
										<td width="70" nowrap><input type="radio" name="indicadorTrocaProtecao" value="1" disabled="disabled">&nbsp;SIM</td>
										<td align="left" nowrap><input type="radio" name="indicadorTrocaProtecao" value="2" disabled="disabled">&nbsp;N&Atilde;O</td>
									</logic:notPresent>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td><strong>Troca de Registro:</strong></td>
						<td align="left">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<logic:present name="indicadorTrocaRegistro">
										<logic:equal name="indicadorTrocaRegistro" value="1">
											<td width="70" nowrap><input type="radio" name="indicadorTrocaRegistro" value="1" checked="checked" disabled="disabled">&nbsp;SIM</td>
											<td align="left" nowrap><input type="radio" name="indicadorTrocaRegistro" value="2" disabled="disabled">&nbsp;N&Atilde;O</td>
										</logic:equal>
										<logic:equal name="indicadorTrocaRegistro" value="2">
											<td width="70" nowrap><input type="radio" name="indicadorTrocaRegistro" value="1" disabled="disabled">&nbsp;SIM</td>
											<td align="left" nowrap><input type="radio" name="indicadorTrocaRegistro" value="2" checked="checked" disabled="disabled">&nbsp;N&Atilde;O</td>
										</logic:equal>
									</logic:present>
									<logic:notPresent name="indicadorTrocaRegistro">
										<td width="70" nowrap><input type="radio" name="indicadorTrocaRegistro" value="1" disabled="disabled">&nbsp;SIM</td>
										<td align="left" nowrap><input type="radio" name="indicadorTrocaRegistro" value="2" disabled="disabled">&nbsp;N&Atilde;O</td>
									</logic:notPresent>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td><strong>Situa&ccedil;&atilde;o da Fiscaliza&ccedil;&atilde;o:</strong></td>
						<td align="left">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<logic:present name="codigoFiscalizacao">
										<logic:equal name="codigoFiscalizacao" value="0">
											<td nowrap><input type="radio" name="codigoFiscalizacao" value="0" checked="checked" onclick="desabilitaDataFiscalizacao();verificaFuncionario();">&nbsp;N&atilde;o Fiscalizado</td>
											<td align="left" nowrap><input type="radio" name="codigoFiscalizacao" value="1" onclick="habilitaDesabilitaDataFiscalizacao();verificaFuncionario();">&nbsp;Aprovado</td>
											<td align="left" nowrap><input type="radio" name="codigoFiscalizacao" value="2" onclick="habilitaDesabilitaDataFiscalizacao();verificaFuncionario();">&nbsp;Reprovado</td>
										</logic:equal>
										<logic:equal name="codigoFiscalizacao" value="1">
											<td nowrap><input type="radio" name="codigoFiscalizacao" value="0" onclick="desabilitaDataFiscalizacao();verificaFuncionario();">&nbsp;N&atilde;o Fiscalizado</td>
											<td align="left" nowrap><input type="radio" name="codigoFiscalizacao" value="1" checked="checked" onclick="habilitaDesabilitaDataFiscalizacao();verificaFuncionario();">&nbsp;Aprovado</td>
											<td align="left" nowrap><input type="radio" name="codigoFiscalizacao" value="2" onclick="habilitaDesabilitaDataFiscalizacao();verificaFuncionario();">&nbsp;Reprovado</td>
										</logic:equal>
										<logic:equal name="codigoFiscalizacao" value="2">
											<td nowrap><input type="radio" name="codigoFiscalizacao" value="0" onclick="desabilitaDataFiscalizacao();verificaFuncionario();">&nbsp;N&atilde;o Fiscalizado</td>
											<td align="left" nowrap><input type="radio" name="codigoFiscalizacao" value="1" onclick="habilitaDesabilitaDataFiscalizacao();verificaFuncionario();">&nbsp;Aprovado</td>
											<td align="left" nowrap><input type="radio" name="codigoFiscalizacao" value="2" checked="checked" onclick="habilitaDesabilitaDataFiscalizacao();verificaFuncionario();">&nbsp;Reprovado</td>
										</logic:equal>
									</logic:present>
									<logic:notPresent name="codigoFiscalizacao">
										<td nowrap><input type="radio" name="codigoFiscalizacao" value="0" disabled="disabled">&nbsp;N&atilde;o Fiscalizado</td>
										<td align="left" nowrap><input type="radio" name="codigoFiscalizacao" value="1" disabled="disabled">&nbsp;Aprovado</td>
										<td align="left" nowrap><input type="radio" name="codigoFiscalizacao" value="2" disabled="disabled">&nbsp;Reprovado</td>
									</logic:notPresent>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td><strong>Data da 1a. Fiscaliza&ccedil;&atilde;o:&nbsp;</strong></td>
						<td align="left"><html:text property="dataFiscalizacao1" readonly="true" size="10" maxlength="10" onkeyup="mascaraData(this, event);"
										 style="background-color:#EFEFEF; border:0;" /></td>
					</tr>
					
					<tr>
						<td><strong>Data da 2a. Fiscaliza&ccedil;&atilde;o:&nbsp;</strong></td>
						<td align="left"><html:text property="dataFiscalizacao2" readonly="true" size="10" maxlength="10" onkeyup="mascaraData(this, event);" 
										 style="background-color:#EFEFEF; border:0;" /></td>
					</tr>
					
					<tr>
						<td><strong>Data da 3a. Fiscaliza&ccedil;&atilde;o:&nbsp;</strong></td>
						<td align="left"><html:text property="dataFiscalizacao3" readonly="true" size="10" maxlength="10" onkeyup="mascaraData(this, event);"
										 style="background-color:#EFEFEF; border:0;" /></td>
					</tr>
					
					<tr>
						<td><strong>Funcion&aacute;rio:&nbsp;</strong></td>
						<td align="left"><html:text property="idUsuario" readonly="true" size="12"
										 style="background-color:#EFEFEF; border:0;"
										 onkeyup="verificaNumeroInteiro(this);" /></td>
					</tr>
					
					<tr>
						<td height="10" colspan="3"><hr></td>
					</tr>
					
					<tr>
						<td height="24"><input type="button" class="bottonRightCol" 
							value="Desfazer"
							onclick="window.location.href='/gsan/exibirAtualizarOrdemServicoConcluidaAction.do?desfazer=sim&numeroOs=<bean:write name="ManterOrdemServicoConcluidaActionForm" property="idOrdemServico" />';" />
							<input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						
						<logic:equal name="usuarioPermissaoAtualizar" value="true">
							<td align="right"><input type="button" name="ButtonAtualizar" class="bottonRightCol" value="Atualizar"
								onClick="javascript:validarForm()" /></td>
						</logic:equal>
						<logic:equal name="usuarioPermissaoAtualizar" value="false">
							<td align="right"><input type="button" name="ButtonAtualizar" class="bottonRightCol" value="Atualizar"
								onClick="javascript:validarForm()" disabled="disabled" /></td>
						</logic:equal>
						
					</tr>
				</table>
				<p>&nbsp;</p>
				</td>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
	
	<script>
		if (verificaDataEncerramentoBoletim()) {
			verificarDataFiscalizacao();
			verificaFuncionario();
		}
		
		// Verifica se o usuario tem permissao para desfazer a fiscalizacao
		var form = document.forms[0];

		if (form.codigoFiscalizacaoAnterior.value != 0 &&
			form.permissaoDesfazerFiscalizacao.value == 'NAO') {
			
			form.codigoFiscalizacao[0].disabled = true;
		}
		
		// Desabilita a Situacao Nao Fiscalizado caso a OS ja tenha sido Fiscalizada;
		//var form = document.forms[0];
		//if (form.codigoFiscalizacao[2].checked) {
		//	form.codigoFiscalizacao[0].disabled = true;
		//}
	</script>
	
</body>
</html:html>