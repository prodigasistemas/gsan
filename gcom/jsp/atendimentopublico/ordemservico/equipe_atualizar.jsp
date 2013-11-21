<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.atendimentopublico.ordemservico.EquipeComponentes"%>


<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%-- Carrega validações do validator --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="AtualizarEquipeActionForm"	dynamicJavascript="true" />

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<%-- Novos Javascripts --%>
<script language="JavaScript">

	/* Valida Form */
	function validarForm() {

		var form = document.AtualizarEquipeActionForm;

		/* Validate */
		if (validateAtualizarEquipeActionForm(form)) {

			if (validaQtdeEquipeComponentes()) {
			
				var cargaTrabalho = trim(form.cargaTrabalhoDia.value);
			
				if (cargaTrabalho != null 
				&& cargaTrabalho != '' 
				&& cargaTrabalho > 24) {
					alert('Carga de Trabalho Diária(hora) não deve exceder 24');
				} else {
   					submeterFormPadrao(form);
   				}
			}
		}
	}
	/* Valida se informou algum componente antes da inserção da equipe */
	function validaQtdeEquipeComponentes(){
		var form = document.AtualizarEquipeActionForm;
		if (form.tamanhoColecao.value == '0') {
			alert('Equipe deve possuir ao menos um componente.');
			return false;
		}
		return true;
	}

	/* [FS0006] Verificar quantidade de componentes da equipe em Tipo Perfil Serviço */
	function addComponente() {
		chamarPopup('exibirAtualizarEquipeAction.do?popUpAdicionarComponente=sim', 'equipeComponente', null, null, 270, 590, '',document.forms[0].idEquipeComponente);
	}
	
	function atualizarComponente(id) {
		chamarPopup('exibirAtualizarEquipeAction.do?popUpAtualizarComponente=sim&atualizaComponente=' + id, 'equipeComponenteAtualizar', null, null, 270, 590, '',document.forms[0].idEquipeComponente);
	}
	
	function addEquipamentosEspeciais() {
		abrirPopup('exibirAtualizarEquipeAction.do?method=addEquipamentos', 270,
				590);
				
	}
	
	
	/* Limpa Unidade Organizacional do Form */
	function limparUnidade() {
		var form = document.AtualizarEquipeActionForm;
		form.idUnidade.value = "";
		form.nomeUnidade.value = "";
	}
	
	function limparUnidadeTecla() {
		var form = document.AtualizarEquipeActionForm;
		form.nomeUnidade.value = "";
	}
	/* Limpa Tipo Perfil Servico do Form */	
	function limparTipoPerfilServico() {
		var form = document.AtualizarEquipeActionForm;
		form.idServicoPerfilTipo.value = "";		
		form.descricaoServicoPerfilTipo.value = "";		
	}
	 
	function limparTipoPerfilServicoTecla() {
		var form = document.AtualizarEquipeActionForm;
		form.descricaoServicoPerfilTipo.value = "";		
	}
	function limparUsuario(){
		var form = document.forms[0];
		form.cdUsuarioRespExecServico.value = "";
		form.nomeUsuarioRespExecServico.value = "";
		
	}
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "&" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
	    form.action='exibirAtualizarEquipeAction.do';
	    if (tipoConsulta == 'unidadeOrganizacional') {
			form.idUnidade.value = codigoRegistro;
			form.nomeUnidade.value = descricaoRegistro;
			form.nomeUnidade.style.color = "#000000";
			submeterFormPadrao(form);
	    } else if (tipoConsulta == 'servicoPerfilTipo') {
	    	form.idServicoPerfilTipo.value = codigoRegistro;
	    	form.descricaoServicoPerfilTipo.value = descricaoRegistro;
	    	form.descricaoServicoPerfilTipo.style.color = "#000000";
	    	submeterFormPadrao(form);
	    } else if (tipoConsulta == 'equipeComponente') {
	    	form.idEquipeComponente.value = codigoRegistro;
	    	form.idFuncionario.value = descricaoRegistro[0];
	    	form.nomeComponente.value = descricaoRegistro[1];
	    	form.indicadorResponsavel.value = descricaoRegistro[2];
	    	form.action='exibirAtualizarEquipeAction.do?adicionarComponente=sim&popUpAdicionarComponente=sim';
	    	submeterFormPadrao(form);
	    } else if (tipoConsulta == 'equipeComponenteAtualizar') {
	    	form.idEquipeComponente.value = codigoRegistro;
	    	form.idFuncionario.value = descricaoRegistro[0];
	    	form.nomeComponente.value = descricaoRegistro[1];
	    	form.indicadorResponsavel.value = descricaoRegistro[2];
	    	form.action='exibirAtualizarEquipeAction.do?atualizarComponente=sim&popUpAtualizarComponente=sim';
	    	submeterFormPadrao(form);
	    }else if (tipoConsulta == 'equipeEquipamentosEspeciais') {
			//form.equipamentosEspeciasId.value = codigoRegistro;
			form.equipamentosEspeciasId.value = descricaoRegistro[0];
			form.descricao.value = descricaoRegistro[1];
			form.quantidade.value = descricaoRegistro[2];
			form.action = 'exibirAtualizarEquipeAction.do';
			submeterFormPadrao(form);
		}else if(tipoConsulta == 'usuario'){
			form.cdUsuarioRespExecServico.value = codigoRegistro;
			form.nomeUsuarioRespExecServico.value = descricaoRegistro;			
			form.action = 'exibirAtualizarEquipeAction.do?tipoPesquisa=usuario';
			submeterFormPadrao(form);
		}	    
	}
	
	/* Remove Componente da grid */	
	
	function remover(id, tipoComponente) {
		var form = document.AtualizarEquipeActionForm;

		if (tipoComponente == 'componente') {

			var where_to = confirm("Deseja realmente remover este componente ?");
			if (where_to == true) {
				form.action = 'exibirAtualizarEquipeAction.do?deleteComponente='
						+ id;
				form.submit();
			}
		} else {
			var where_to = confirm("Deseja realmente remover este equipamento ?");
			if (where_to == true) {
				form.action = 'exibirAtualizarEquipeAction.do?deleteEquipamento=' + id;
				form.submit();
			}
		}
	}
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarEquipeAction.do"
	name="AtualizarEquipeActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarEquipeActionForm"
	method="post">

	<html:hidden property="idEquipeComponente" />
	<html:hidden property="nomeComponente" />
	<html:hidden property="idFuncionario" />
	<html:hidden property="nomeFuncionario" />
	<html:hidden property="indicadorResponsavel" />
	<html:hidden property="tamanhoColecao" />
	<html:hidden property="equipamentosEspeciasId" />
	<html:hidden property="descricao" />
	<html:hidden property="quantidade" />

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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Equipe</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<!--Inicio da Tabela Ligação de Esgoto -->
			<table width="100%" border="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="3">Para atualizar a equipe, informe os
							dados abaixo:</td>
						</tr>
						<tr>
							<td height="10" colspan="3">
							<hr>
							</td>
						</tr>
						<tr>
							<td><strong>Código da Equipe:</strong></td>
							<td colspan="2"><html:text property="idEquipe"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="5" maxlength="5" readonly="true" /></td>
						</tr>
						<tr>
							<td height="10"><strong> Nome da Equipe: <span class="style2"> <strong>
							<font color="#FF0000">*</font> </strong> </span> </strong></td>
							<td colspan="2"><!-- Nome da Equipe --> <span class="style2"> <html:text
								property="nomeEquipe" size="42" maxlength="20" /> </span></td>
						</tr>
						<tr>
							<td><strong> Placa do Ve&iacute;culo : </strong></td>
							<td colspan="2" align="right">
							<div align="left"><html:text property="placaVeiculo" size="15"
								maxlength="7" /></div>
							</td>
						</tr>
						<tr>
							<td><strong> Carga de Trabalho Dia (hora): <font color="#FF0000">*</font>
							</strong></td>
							<td colspan="2" align="right">
							<div align="left"><html:text property="cargaTrabalhoDia" size="2"
								maxlength="2" /></div>
							</td>
						</tr>
						<tr>
							<td><strong> Codigo DDD do Município :</strong></td>
							<td colspan="2" align="right">
							<div align="left"><html:text property="codigoDdd" size="2"
								maxlength="2" /></div>
							</td>
						</tr>
						<tr>
							<td><strong> Numero Telefone : <font color="#FF0000">*</font>
							</strong></td>
							<td colspan="2" align="right">
							<div align="left"><html:text property="numeroTelefone" size="8"
								maxlength="8" /></div>
							</td>
						</tr>
						<tr>
							<td><strong> Numero IMEI do Aperelho : <font color="#FF0000">*</font>
							</strong></td>
							<td colspan="2" align="right">
							<div align="left"><html:text property="numeroImei" size="15"
								maxlength="15" /></div>
							</td>
						</tr>
						<tr>
							<td width="130"><strong>Unidade Organizacional: <font color="#FF0000">*</font></strong></td>
							<td colspan="2"><html:text maxlength="4" property="idUnidade"
								tabindex="1" size="4" onkeyup="javascript:limparUnidadeTecla();"
								onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarEquipeAction.do', 'idUnidade', 'Unidade Organizacional');" />
							<a
								href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do');">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Unidade Organizacional" /></a> <logic:present
								name="idUnidadeNaoEncontrado" scope="request">
								<html:text maxlength="30" property="nomeUnidade" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="40" />
							</logic:present> <logic:notPresent name="idUnidadeNaoEncontrado"
								scope="request">
								<html:text maxlength="30" property="nomeUnidade" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="40" />
							</logic:notPresent> <a href="javascript:limparUnidade();"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /></a></td>
						</tr>
						<tr>
							<td><strong> Tipo Perfil Servi&ccedil;o:
							</strong></td>
							<td colspan="2"><html:text maxlength="3"
								property="idServicoPerfilTipo" tabindex="1" size="4"
								onkeyup="javascript:limparTipoPerfilServicoTecla();"
								onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarEquipeAction.do', 'idServicoPerfilTipo', 'Serviço Perfil Tipo');" />
							<a
								href="javascript:abrirPopup('exibirPesquisarTipoPerfilServicoAction.do');">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Serviço Perfil Tipo" /></a> <logic:present
								name="idServicoPerfilNaoEncontrado" scope="request">
								<html:text maxlength="40" property="descricaoServicoPerfilTipo"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="40" />
							</logic:present> <logic:notPresent
								name="idServicoPerfilNaoEncontrado" scope="request">
								<html:text maxlength="40" property="descricaoServicoPerfilTipo"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="40" />
							</logic:notPresent> <a href="javascript:limparTipoPerfilServico();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /></a></td>
						</tr>
						
						 <!-- Começo usuário responsável pela execussão do serviço -->
                        
                        <tr>
							<td><strong>Usuario Responsavel Pela Execussão do Serviço:</strong></td>
					
							<td colspan="4">						
								<html:text maxlength="11" 
								tabindex="1"
								property="cdUsuarioRespExecServico" 
								name="AtualizarEquipeActionForm"
								size="9"
								style="text-transform: none;"
								onkeypress="validaEnterStringSemUpperCase(event, 'exibirAtualizarEquipeAction.do?tipoPesquisa=usuario','cdUsuarioRespExecServico','cdUsuarioRespExecServico');
							            return isCampoNumerico(event);"/>
								<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'usuario', null, null, 275, 480, '',document.forms[0].cdUsuarioRespExecServico)">
								<img width="23" 
									height="21" 
									border="0" 
									style="cursor:hand;"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar usuário"/>
								</a>
								

								<logic:present name="usuarioRespExecServicoEncontrado" scope="session">
									<html:text property="nomeUsuarioRespExecServico" 
										name="AtualizarEquipeActionForm"
										size="33"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present> 

								<logic:notPresent name="usuarioRespExecServicoEncontrado" scope="session">
								<html:text property="nomeUsuarioRespExecServico" 
									name="AtualizarEquipeActionForm"
									size="33"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
								</logic:notPresent>
		
								<a href="javascript:limparUsuario();"> 
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
								</a>
							</td>
							
						</tr><!-- fim usuário responsável pela execussão do serviço -->	
						<tr>
							<td><strong>Indicador de Uso:</strong></td>
							<td align="right" colspan="2">
							<div align="left">
							<html:radio property="indicadorUso"
								tabindex="4"
								value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
							<strong>Ativo</strong> <html:radio property="indicadorUso"
								tabindex="5"
								value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" /><strong>
							Inativo</strong></div>
							</td>
						</tr>
						<tr>
							<td><strong>Indicador de Programa&ccedil;&atilde;o Autom&aacute;tica:</strong></td>
					
							<td colspan="4">						
								<html:radio property="indicadorProgramacaoAutomatica"
									value="<%=ConstantesSistema.SIM.toString()%>" tabindex="6" />
								<strong>Sim</strong>
								<html:radio property="indicadorProgramacaoAutomatica"
									value="<%=ConstantesSistema.NAO.toString()%>" tabindex="7" />
								<strong>Não</strong>
							</td>
						</tr>
						<tr>
							<td height="24" colspan="3">
							<hr>
							</td>
						</tr>
						<tr>
							<td><strong> <font color="#000000">Componentes da Equipe </font>
							</strong></td>
							<td colspan="2" align="right">
							<div align="right"><input type="button" name="Submit24"
								class="bottonRightCol" value="Adicionar"
								onclick="javascript:addComponente();"></div>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="3">
							<div align="center"><strong> <font color="#FF0000"></font> </strong>
							<table width="100%" align="center" bgcolor="#99CCFF">
								<!--corpo da segunda tabela-->
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
									<td width="11%">
									<div align="center"><strong>Remover</strong></div>
									</td>
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
								<logic:present name="colecaoEquipeComponentes">
									<logic:iterate name="colecaoEquipeComponentes"
										id="equipeComponente" type="EquipeComponentes">
										<c:set var="count" value="${count+1}" />
										<c:choose>
											<c:when test="${count%2 == '1'}">
												<tr bgcolor="#FFFFFF">
											</c:when>
											<c:otherwise>
												<tr bgcolor="#cbe5fe">
											</c:otherwise>
										</c:choose>
										<td bordercolor="#90c7fc">
										<div align="center"><img
											src="<bean:message key='caminho.imagens'/>Error.gif"
											width="14" height="14" style="cursor:hand;" name="imagem"
											onclick="remover('${count}', 'componente');" alt="Remover"></div>
										</td>
										<td bordercolor="#90c7fc">
										<div align="center"><c:choose>
											<c:when
												test="${equipeComponente.indicadorResponsavel == '1'}">
							                        				SIM
							                        			</c:when>
											<c:otherwise>
							                        				N&Atilde;O
							                        			</c:otherwise>
										</c:choose></div>
										</td>
										<td bordercolor="#90c7fc">
										<div align="center"><c:if
											test="${equipeComponente.funcionario != null}">
											<bean:write name="equipeComponente" property="funcionario.id" />
										</c:if></div>
										</td>
										<td bordercolor="#90c7fc">
										<div align="left"><c:choose>
											<c:when test="${equipeComponente.funcionario == null}">
												<a href="javascript:atualizarComponente('${count}')"> <bean:write
													name="equipeComponente" property="componentes" /> </a>
											</c:when>
											<c:otherwise>
												<a href="javascript:atualizarComponente('${count}')"> <bean:write
													name="equipeComponente" property="funcionario.nome" /> </a>
											</c:otherwise>
										</c:choose></div>
										</td>
									</logic:iterate>
								</logic:present>
							</table>
							</div>
							</td>
						</tr>
						
						<tr>
							<td colspan="2"><strong> <font color="#000000">Inclusão
							de Equipamentos Especiais</font> </strong></td>
							<td align="right">
							<div align="right"><input type="button" name="Submit24"
								class="bottonRightCol" value="Adicionar"
								onclick="javascript:addEquipamentosEspeciais();"></div>
							</td>
						</tr>
						
						<tr>
							<td width="100%" colspan="3"><!-- aqui está a tabela -->
							<div align="center"><strong> <font color="#FF0000"></font>
							</strong>
							<table width="100%" align="center" bgcolor="#99CCFF">
								<!--corpo da segunda tabela-->
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
									<td width="11%" colspan="1">
									<div align="center"><strong>Remover</strong></div>
									</td>
									<td width="73%" colspan="1">
									<div align="left"><strong>Descri&ccedil;&atilde;o</strong></div>
									</td>
									<td width="16%" colspan="1">
									<div align="left"><strong>Quantidade</strong></div>
									</td>
								</tr>
							</table>
							</div>
							</td>
						</tr>

						<tr>
							<c:set var="count" value="0" />
							<logic:iterate id="equipeEquipamentosEspeciais"
								name="AtualizarEquipeActionForm"
								property="equipeEquipamentosEspeciais"
								type="gcom.atendimentopublico.ordemservico.EquipeEquipamentosEspeciais"
								scope="session">
								<c:set var="count" value="${count+1}" />
								<c:choose>
									<c:when test="${count%2 == '1'}">
										<tr bgcolor="#FFFFFF">
									</c:when>
									<c:otherwise>
										<tr bgcolor="#cbe5fe">
									</c:otherwise>
								</c:choose>
								<td bordercolor="#90c7fc" width="11%" colspan="1">
								<div align="center"><img
									src="<bean:message key='caminho.imagens'/>Error.gif" width="14"
									height="14" style="cursor: hand;" name="imagem"
									onclick="remover('${count}', 'equipamento');" alt="Remover"></div>
								</td>

								<td bordercolor="#90c7fc" width="73%" colspan="1">
								<div align="left"><bean:write
									name="equipeEquipamentosEspeciais" property="equipamentosEspeciais.descricao" /></div>
								</td>
								<td bordercolor="#90c7fc" width="16%" colspan="1">
								<div align="left"><bean:write
									name="equipeEquipamentosEspeciais" property="quantidade" /></div>
								</td>
							</logic:iterate>
						</tr>
						
						<tr>
							<td colspan="3"><strong> <font color="#FF0000"></font> </strong>
							<div align="left">
							<hr>
							</div>
							</td>
						</tr>
						<tr>
							<td><strong> <font color="#FF0000"></font> </strong></td>
							<td colspan="3" align="right">
							<div align="left"><strong> <font color="#FF0000">*</font> </strong>
							Campos obrigat&oacute;rios</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><strong><logic:present name="filtrar">
								<logic:present name="inserir">
									<input type="button" name="ButtonReset" class="bottonRightCol"
										value="Voltar"
										onClick="javascript:window.location.href='/gsan/exibirFiltrarEquipeAction.do?menu=sim'">
								</logic:present>
								<logic:notPresent name="inserir">
									<input type="button" name="ButtonReset" class="bottonRightCol"
										value="Voltar"
										onClick="javascript:window.location.href='/gsan/exibirFiltrarEquipeAction.do'">
								</logic:notPresent>
							</logic:present><logic:notPresent name="filtrar">
								<input type="button" name="ButtonReset" class="bottonRightCol"
									value="Voltar"
									onClick="javascript:window.location.href='/gsan/exibirManterEquipeAction.do'">
							</logic:notPresent><input type="button" name="Submit22"
								class="bottonRightCol" value="Desfazer"
								onClick="javascript:window.location.href='/gsan/exibirAtualizarEquipeAction.do?desfazer=sim'">
							<input type="button" name="Submit23" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</strong></td>
							<td align="right"><gsan:controleAcessoBotao
								name="Button" value="Atualizar"
								onclick="javascript:validarForm(document.forms[0]);"
								url="atualizarEquipeAction.do" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!-- Fim do Corpo - Leonardo Regis -->

	<!-- Rodapé -->
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
