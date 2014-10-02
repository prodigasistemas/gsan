<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InformarLeituraFiscalizacaoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){

		if(testarCampoValorZero(document.InformarLeituraFiscalizacaoActionForm.dataLeituraFiscalizacao, 'Data da Leitura') && 
			testarCampoValorZero(document.InformarLeituraFiscalizacaoActionForm.matriculaLeituristaFiscalizacao, 'Matrícula Leiturista') /*&& 
			testarCampoValorZero(document.InformarLeituraFiscalizacaoActionForm.mesAnoReferencia, 'Mês/Ano de Referência')*/) {
			//validarDadosFiscalizacao(form);
			if(validateInformarLeituraFiscalizacaoActionForm(form) && validarDadosFiscalizacao(form) ){
    			submeterFormPadrao(form);
			}
		}
	}

	function validarDadosFiscalizacao(form){
	
		if((form.anormalidadeFiscalizacao.value == '' || form.anormalidadeFiscalizacao.value == '-1') && form.leituraFiscalizacao.value == '') {
			alert('Informe Anormalidade');
		}else{
		return true;
		
		}
			
	}

	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];
   
    	if (tipoConsulta == 'imovel') {
	    	form.matricula.value = codigoRegistro;
	      	form.action='exibirFiltrarInformarLeituraFiscalizacaoAction.do';
	      	form.submit();
	    }
  	}

	function limparForm() {

		var form = document.InformarLeituraFiscalizacaoActionForm;

		form.matricula.value = "";
		form.inscricaoImovel.value = "";
	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form action="/informarLeituraFiscalizacaoAction.do"
	name="InformarLeituraFiscalizacaoActionForm"
	type="gcom.gui.micromedicao.leitura.InformarLeituraFiscalizacaoActionForm"
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
			<td width="600" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Informar Leitura de Fiscalização</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="2" width="100%">Para informar a
							leitura de fiscalização, informe os dados abaixo:</td>
				</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td width="15%" height="10"><strong>Matrícula do Im&oacute;vel:</strong></td>
					<td align="left" width="85%"><html:text
						property="matricula" maxlength="9" size="9" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
						onkeypress="validaEnterComMensagem(event, 'exibirFiltrarInformarLeituraFiscalizacaoAction.do','matricula','imovel');"/> 
					<a 
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" /></a> <logic:present
						name="matriculaNaoEncontrado" scope="request">
						<html:text property="inscricaoImovel" size="20"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="matriculaNaoEncontrado" scope="request">
							<html:text property="inscricaoImovel"
								size="20" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparForm();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
					<tr>
					<td><strong>Tipo de Medição:</strong></td>
					<td><html:select property="medicaoTipo" disabled="true">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoMedicaoTipo"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<!--  <tr>
					<td width="15%"><strong>Tipo de Medição:</strong></td>
					<td width="85%"><html:text property="medicaoTipo" maxlength="12" size="12" readonly="true" 
					style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
				</tr>-->
				<tr>
					<td width="15%"><strong>Mês e Ano de Referência:</strong></td>
					<td colspan="2" width="85%"><html:text property="mesAnoReferencia" size="7"
						maxlength="7" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						mm/aaaa</td>
				</tr>
				
				
				<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"> Dados da Leitura Normal </span></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td width="200" height="10"><strong>Leitura:</strong></td>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text
												property="leituraNormal" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="8" maxlength="8" /></td>
										</tr>
										<tr>
											<td width="200"><strong> Data da Leitura:</strong></td>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text property="dataLeituraNormal"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="10" maxlength="10" /></td>
										</tr>
										<tr>
											<td width="200"><strong>Anormalidade:</strong></td>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text property="anormalidadeNormal"
												readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000"
												size="25" maxlength="25" /></td>
										</tr>
										<tr>
											<td width="200"><strong>Matrícula Leiturista:</strong></td>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text property="matriculaLeituristaNormal"
												readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000"
												size="9" maxlength="9" onkeyup="javascript:verificaNumeroInteiro(this);"/></td>
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
									<div align="center"><span class="style2"> Dados da Leitura de Fiscalização </span></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td width="200" height="10"><strong>Leitura:</strong></td>
											<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text
												property="leituraFiscalizacao" 
												size="8" maxlength="8" onkeyup="javascript:verificaNumeroInteiro(this);"/></td>
										</tr>
										
										<tr> 
	           								<td width="200" class="style3"><strong>Data da Leitura:<font color="#FF0000">*</font></strong></td>
											<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:text property="dataLeituraFiscalizacao" size="10" maxlength="10" onkeyup="mascaraData(this,event)"/>
											<a href="javascript:abrirCalendario('InformarLeituraFiscalizacaoActionForm', 'dataLeituraFiscalizacao')">
											<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
											dd/mm/aaaa
											</td>
	           							</tr>	
										
										<tr>
											<td width="200"><strong>Anormalidade:</strong></td>
											<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:select property="anormalidadeFiscalizacao">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoLeituraAnormalidade"
													labelProperty="descricao" property="id" />
											</html:select> <font size="1">&nbsp; </font></td>
										</tr>
										<tr>
											<td width="200"><strong>Matrícula Leiturista:<font color="#FF0000">*</font></strong></td>
											<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text property="matriculaLeituristaFiscalizacao"
												size="9" maxlength="9" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
				
				
										
				<tr colspan="1" align="center">
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="center">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

			<tr>
				<td width="40%" align="left"> 
				<input type="button"
					name="ButtonCancelar" class="bottonRightCol"
					value="Voltar"
					onClick="history.back();">
				<input type="button"
					name="ButtonCancelar" class="bottonRightCol"
					value="Desfazer"
					onClick="javascript:window.location.href='exibirInformarLeituraFiscalizacaoAction.do'">
				
				<input type="button"
					name="ButtonCancelar" class="bottonRightCol"
					value="Cancelar"
					onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				</td>
				<td align="right"><input name="Button" type="button"
					class="bottonRightCol" value="Concluir"
					onClick="javascript:validarForm(document.forms[0]);"></td>
			</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>

