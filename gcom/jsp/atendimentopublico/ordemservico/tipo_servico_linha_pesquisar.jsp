<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>




<script type="text/javascript" language="Javascript1.1"> 
		
	function validaForm(){
		 var form = document.forms[0];
		if(validatePesquisarTipoServicoActionForm){
		alert("Passou do validator.ZZZZ");		
    		//submeterFormPadrao(form);
    		form.action='pesquisarTipoServicoAction.do';
    		form.submit();
		}
	}

		function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado) {
		if(objetoRelacionado.disabled != true) {
			if (objeto == null || codigoObjeto == null) {
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}	 

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
  		var form=document.forms[0];
  	
    	if (tipoConsulta == 'servicoTipoReferencia') {
      		form.tipoServicoReferencia.value = codigoRegistro;
      		form.dsTipoServicoReferencia.value = descricaoRegistro; 
    	} else if (tipoConsulta == 'tipoDebito') { 	
	 	  	form.tipoDebito.value = codigoRegistro;
	 	  	form.dsTipoDebito.value = descricaoRegistro; 
    	} else if (tipoConsulta == 'servicoPerfilTipo') { 	
	 	  	form.perfilServco.value = codigoRegistro;
	 	  	form.dsPerfilServco.value = descricaoRegistro; 
 		} else if (tipoConsulta == 'atividade') { 	
	 	  	form.atividadesTipoServico.value = codigoRegistro;
	 	  	form.dsAtividadesTipoServico.value = descricaoRegistro; 
 		} else if (tipoConsulta == 'material') { 	
	 	  	form.perfilServco.value = codigoRegistro;
	 	  	form.dsPerfilServco.value = descricaoRegistro; 
 		} 
  	}	
  	
  	function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro, codigoAuxiliar, tipoConsulta) {
  		if (tipoConsulta == 'atividade') {
	 		insertRowTableAtividades(codigoRegistro, descricaoRegistro, codigoAuxiliar);
  		} else if (tipoConsulta == 'material') {
	 		insertRowTableMateriais(codigoRegistro, descricaoRegistro, codigoAuxiliar);
 		}
  	}
  
	function reload() {
		var form = document.forms[0];
		form.action = "/gsan/exibirPesquisarTipoServicoAction.do";
		form.submit();
	}  
	
</script>

</head>

<body leftmargin="0" topmargin="0"
	onload="resizePageSemLink(600,600);javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirPesquisarConsultarTipoServicoAction.do"
	name="ConsultarTipoServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ConsultarTipoServicoActionForm"
	method="post">

	<table width="100%" border="0" cellspacing="5" cellpadding="5">
		<tr>
			<td width="100%" valign="top" class="centercoltext">

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consulta Tipo de Serviço</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<html:hidden property="mostrarPavimento" /> <html:hidden
				property="mostrarComercial" /> <html:hidden
				property="mostrarTerceirizado" /> <html:hidden
				property="mostrarServico" />
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td width="50%" height="0"><strong>Descrição Do Tipo de Serviço:</strong></td>
					<td colspan="3"><html:text property="dsTipoServico" readonly="true"
						style="background-color:#EFEFEF; border:0;" size="40"
						maxlength="40" /></td>
				</tr>
				<tr>
					<td width="40%" height="0"><strong>Descrição Abreviada do Tipo de
					Serviço:</strong></td>
					<td colspan="3"><html:text maxlength="50"
						property="dsAbreviadaTipoServico" size="40" readonly="true"
						style="background-color:#EFEFEF; border:0;" /></td>
				</tr>
				<tr>
					<td class="style3"><strong>Subgrupo do tipo de Serviço:<span
						class="style2"><strong><strong><strong><font color="#FF0000"></font></strong></strong></strong></span></strong></td>
					<td colspan="2"><strong> <html:text maxlength="30"
						property="subgrupoTipoServico" size="40" readonly="true"
						style="background-color:#EFEFEF; border:0;" /></strong></td>
				</tr>
				<tr>
					<td height="24"><strong>Indicador de Pavimento:</strong></td>
					<td><c:choose>
						<c:when test="${mostrarPavimento == '1'}">
							<input name="indicadorPavimento" type="radio" value="1" checked>
							<strong>Sim </strong>
						</c:when>
						<c:when test="${mostrarPavimento == '2'}">
							<input name="indicadorPavimento" type="radio" value="1" checked>
							<strong>Não</strong>
						</c:when>
						<c:otherwise>
							<input name="indicadorPavimento" type="radio" value="3" checked>
							<strong>Todos</strong>
						</c:otherwise>
					</c:choose></td>
				</tr>
				<tr>
					<td width="40%" height="0"><strong>Valor do Serviço:</strong></td>
					<td colspan="3"><html:text maxlength="30" property="valorServico"
						size="10" readonly="true"
						style="background-color:#EFEFEF; border:0;" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Indicador Atualização do Comercial:</strong>
					</td>
					<td><c:choose>
						<c:when test="${mostrarComecial == '1'}">
							<input name="indicadorAtualizacaoComercio" type="radio" value="1"
								checked>
							<strong>Sim</strong>
						</c:when>
						<c:when test="${mostrarComecial == '2'}">
							<input name="indicadorAtualizacaoComercio" type="radio" value="2"
								checked>
							<strong>Não</strong>
						</c:when>
						<c:otherwise>
							<input name="indicadorAtualizacaoComercio" type="radio" value="3"
								checked>
							<strong> Todos</strong>
						</c:otherwise>
					</c:choose><strong></strong></td>
				</tr>
				<tr>
					<td height="24"><strong>Indicador Serviço Terceirizado:</strong></td>
					<td><c:choose>
						<c:when test="${mostrarServico == '1'}">
							<input name="indicadorServicoTerceirizado" type="radio" value="1"
								checked>
							<strong>Sim</strong>
						</c:when>
						<c:when test="${mostrarServico == '2'}">
							<input name="indicadorServicoTerceirizado" type="radio" value="2"
								checked>
							<strong>Não</strong>
						</c:when>
						<c:otherwise>
							<input name="indicadorServicoTerceirizado" type="radio" value="3"
								checked>
							<strong>Todos</strong>
						</c:otherwise>
					</c:choose><strong></strong></td>
				</tr>
				<tr>
					<td height="24"><strong>Código do Serviço:</strong></td>
					<td><c:choose>
						<c:when test="${mostrarServico == '1'}">
							<input name="codigoServico" type="radio" value="3" checked>
							<strong>Operacional</strong>
						</c:when>
						<c:when test="${mostrarServico == '2'}">
							<input name="codigoServico" type="radio" value="2" checked>
							<strong>Comercial</strong>
						</c:when>
						<c:otherwise>
							<input name="codigoServico" type="radio" value="3" checked>
							<strong>Todos</strong>
						</c:otherwise>
					</c:choose><strong></strong></td>
				</tr>

				<tr>
					<td height="24"><strong>Tempo Médio de Execução:</strong></td>

					<td height="24"><html:text property="tempoMedioExecucaoInicial"
						size="10" maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0;" /></td>
				</tr>
				<!-- Tipo de Débito -->
				<tr>
					<td><strong>Tipo de Débito:</strong></td>
					<td colspan="3"><html:text property="tipoDebito" size="30"
						maxlength="30" readonly="true"
						style="background-color:#EFEFEF; border:0;" /></td>
				</tr>

				<!-- Tipo de Crédito -->

				<tr>
					<td width="200"><strong>Tipo de Crédito:</strong></td>
					<td colspan="3" align="left"><html:text property="tipoCredito"
						size="30" maxlength="50" readonly="true"
						style="background-color:#EFEFEF; border:0;" /></td>
				</tr>

				<!-- Prioridade do Serviço -->

				<tr>
					<td width="200"><strong>Prioridade do Servi&ccedil;o:</strong></td>
					<td colspan="3" align="left"><html:text
						property="prioridadeServico" size="30" maxlength="50"
						readonly="true" style="background-color:#EFEFEF; border:0;" /></td>
				</tr>

				<!-- Perfil do Tipo de Serviço -->

				<tr>
					<td width="200"><strong>Perfil do Serviço:</strong></td>
					<td colspan="3" align="left"><html:text property="perfilServco"
						size="30" maxlength="50" readonly="true"
						style="background-color:#EFEFEF; border:0;" /></td>
				</tr>

				<!-- Tipo do Serviço Referência -->

				<tr>
					<td width="200"><strong>Tipo de Serviço Referência:</strong></td>
					<td colspan="3" align="left"><html:text
						property="tipoServicoReferencia" size="30" maxlength="4"
						readonly="true" style="background-color:#EFEFEF; border:0;" /></td>
				</tr>

				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>

				<!-- Atividades do Tipo de Serviço -->
				<tr>
					<td width="200"><strong>Atividades do Tipo de Serviço</strong></td>
					<td colspan="3" align="left"></td>
				</tr>
				<tr>
					<td colspan="4"><strong> <font color="#FF0000"></font></strong>
					<div align="left">
					<table width="100%" id="tableAtividades" align="center"
						bgcolor="#99CCFF">
						<!--corpo da segunda tabela-->
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td>
							<div align="center"><strong>Descri&ccedil;&atilde;o dos
							Atividades </strong></div>
							</td>
							<td>
							<div align="center"><strong>Ordem de Execu&ccedil;&atilde;o </strong></div>
							</td>
						</tr>

						<logic:present name="arrayAtividade">
							<%int cont = 1;%>
							<logic:iterate name="arrayAtividade" id="servicoTipoAtividade">
								<%	cont = cont + 1;
									if (cont % 2 == 0) {	%>
									<tr bgcolor="#FFFFFF">
								<%	} else {	%>
									<tr bgcolor="#cbe5fe">
								<%	}	%>
								<td width="10%">
								<div align="left"><bean:write name="servicoTipoAtividade"
									property="atividade.descricao" /></div>

								</td>
								<td width="10%">
								<div align="center"><bean:write name="servicoTipoAtividade"
									property="numeroExecucao" /></div>

								</td>
								</tr>

							</logic:iterate>
						</logic:present>

					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td width="200"><strong>Materiais do Tipo de Serviço</strong></td>
					<td colspan="3" align="left"></td>
				</tr>
				<tr>
					<td colspan="4"><strong> <font color="#FF0000"></font></strong>
					<div align="left">
					<table width="100%" id="tableMateriais" align="center" bgcolor="#99CCFF">
						<!--corpo da segunda tabela-->
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td>
							<div align="center"><strong>Descri&ccedil;&atilde;o dos Materiais
							</strong></div>
							</td>
							<td>
							<div align="center"><strong>Quantidade Padr&atilde;o </strong></div>
							</td>
						</tr>
						
						<logic:present name="arrayMateriais">
							<%int cont = 1;%>
							<logic:iterate name="arrayMateriais" id="servicoTipoMaterial">
								<%	cont = cont + 1;
									if (cont % 2 == 0) {	%>
									<tr bgcolor="#FFFFFF">
								<%	} else {	%>
									<tr bgcolor="#cbe5fe">
								<%	}	%>
								<td width="10%">
								<div align="left"><bean:write name="servicoTipoMaterial"
									property="material.descricao" /></div>

								</td>
								<td width="10%">
								<div align="center"><bean:write name="servicoTipoMaterial"
									property="quantidadePadrao" /></div>

								</td>
								</tr>

							</logic:iterate>
						</logic:present>
						
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<!-- Botões -->
				<tr>

					<td colspan="3" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Voltar"
						onclick="javascript:history.back();"></td>
				</tr>
				<tr>
					<td height="24">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

</html:form>
</body>
</html:html>
