<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarEquipeActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin
	
	//Recebe os dados do(s) popup(s)
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.FiltrarEquipeActionForm;

		if (tipoConsulta == 'unidadeOrganizacional') {
       
			form.idUnidade.value = codigoRegistro;
			form.nomeUnidade.value = descricaoRegistro;
			form.nomeUnidade.style.color = "#000000";
        
		}
	
		if (tipoConsulta == 'funcionario') {
       	
			if(form.equipamentosEspeciasId.value == "-1"){
			 form.idFuncionario.value = codigoRegistro;
			 form.nomeFuncionario.value = descricaoRegistro;
			 form.nomeFuncionario.style.color = "#000000";
			}
        
		}

		if (tipoConsulta == 'servicoPerfilTipo') {
       	
			form.idPerfilServico.value = codigoRegistro;
			form.descricaoPerfilServico.value = descricaoRegistro;
			form.descricaoPerfilServico.style.color = "#000000";
        
		}
		
		if(tipoConsulta == "usuario"){
		
        	form.cdUsuarioRespExecServico.value = codigoRegistro;
        	form.nomeUsuarioRespExecServico.value = descricaoRegistro;
        	form.action = 'exibirFiltrarEquipeAction.do?tipoPesquisa=usuario';
        	form.submit();
        }  

	}

	function limparUnidade() {
	
		var form = document.forms[0];
	
		form.idUnidade.value = "";
		form.nomeUnidade.value = "";
	
	}
	
	function limparUnidadeTecla() {
	
		var form = document.forms[0];
	
		form.nomeUnidade.value = "";
	
	}
	
	function limparFuncionario() {
	
		var form = document.forms[0];
	
		form.idFuncionario.value = "";
		form.nomeFuncionario.value = "";
	
	}
	
	function limparFuncionarioTecla() {
	
		var form = document.forms[0];
	
		form.nomeFuncionario.value = "";
	
	}
	
	function limparServicoPerfil() {
	
		var form = document.forms[0];
	
		form.idPerfilServico.value = "";
		form.descricaoPerfilServico.value = "";
	
	}
	
	
	function limparServicoPerfilTecla() {
	
		var form = document.forms[0];
	
		form.descricaoPerfilServico.value = "";
	
	}
	
	function verificarValorAtualizar() {
	
		var form = document.forms[0];
	
		if (form.atualizar.checked == true) {
			form.atualizar.value = '1';
		} else {
			form.atualizar.value = '';
		}
		
	}
	
	function limparFuncionarioClick() {
	  var form = document.forms[0];
	  if(form.equipamentosEspeciasId.value != "-1"){
		  form.nomeFuncionario.value = "";
		  form.idFuncionario.value = "";
		  form.idFuncionario.disabled = true;
	  }else{
		  form.idFuncionario.disabled = false; 
	  }
	}
	
	
	function checkAtualizar(valor) {
	
		var form = document.forms[0];
		
		if (valor == '1') {
			form.atualizar.checked = true;
		} else {
			form.atualizar.checked = false;
		}
		
	}

	function validarForm(form){
				
		if(testarCampoValorZero(form.codigo, 'Código da Equipe')
		&& testarCampoValorZero(form.nome, 'Nome da Equipe')
		&& testarCampoValorZero(form.placa, 'Número da Placa')
		&& testarCampoValorZero(form.cargaTrabalho, 'Carga de Trabalho Diária')
		&& testarCampoValorZero(form.codigoDdd, 'Código DDD do Município')
		&& testarCampoValorZero(form.numeroTelefone, 'Numero de Telefone')
		&& testarCampoValorZero(form.numeroImei, 'Numero Imei do Aparelho')
		&& testarCampoValorZero(form.idUnidade, 'Unidade Organizacional')
		&& testarCampoValorZero(form.idFuncionario, 'Funcionário')
		&& testarCampoValorZero(form.idPerfilServico, 'Serviço Perfil Tipo')) { 		
			if(validateFiltrarEquipeActionForm(form)){
			
				var cargaTrabalho = trim(form.cargaTrabalho.value);
				
				if (cargaTrabalho != null 
				&& cargaTrabalho != '' 
				&& form.cargaTrabalho.value > 24) {
					alert('Carga de Trabalho Diária(hora) não deve exceder 24');
				} else {
					var atualizar = form.atualizar.value;
					form.action = 'filtrarEquipeAction.do?atualizar=' + atualizar;
   					submeterFormPadrao(form);
   				}
			}
		}	
	}
	 function limparUsuario(){
    	var form = document.forms[0];
    	form.nomeUsuarioRespExecServico.value = "";
    	form.cdUsuarioRespExecServico.value = "";
    }


</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');checkAtualizar('${sessionScope.atualizar}');verificarValorAtualizar();">

<html:form action="/filtrarEquipeAction" name="FiltrarEquipeActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarEquipeActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Equipe</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para manter a(s) equipe(s), informe os dados
					abaixo:</td>
					<td align="right"><html:checkbox property="atualizar" value="1"
						onclick="javascript:verificarValorAtualizar()" /><strong>Atualizar</strong></td>
				</tr>
				<tr>
					<td width="100"><strong>Código da Equipe:</strong></td>
					<td colspan="2"><html:text property="codigo" size="5" maxlength="5" /></td>
				</tr>
				<tr>
					<td width="100"><strong>Nome da Equipe:</strong></td>
					<td><html:text property="nome" size="30" maxlength="20" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="4"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="100"><strong>Número da Placa:</strong></td>
					<td colspan="2"><html:text property="placa" size="8" maxlength="7" /></td>
				</tr>
				<tr>
					<td width="100"><strong>Carga de Trabalho Diária(hora):</strong></td>
					<td colspan="2"><html:text property="cargaTrabalho" size="2"
						maxlength="2" /></td>
				</tr>
				<tr>
					<td width="100"><strong>Código DDD do Município:</strong></td>
					<td colspan="2"><html:text property="codigoDdd" size="2"
						maxlength="2" /></td>
				</tr>
				<tr>
					<td width="100"><strong>Número do Telefone:</strong></td>
					<td colspan="2"><html:text property="numeroTelefone" size="8"
						maxlength="8" /></td>
				</tr>
				<tr>
					<td width="100"><strong>Número do IMEI do aparelho:</strong></td>
					<td colspan="2"><html:text property="numeroImei" size="15"
						maxlength="15" /></td>
				</tr>
				<tr>
					<td width="130"><strong>Unidade Organizacional:</strong></td>
					<td colspan="2"><html:text maxlength="4" property="idUnidade"
						tabindex="1" size="4" onkeyup="javascript:limparUnidadeTecla();"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarEquipeAction.do?atualizar=' + document.forms[0].atualizar.value, 'idUnidade', 'Unidade Organizacional');" />
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
					<td width="130"><strong>Funcionário:</strong></td>
					<td colspan="2"><html:text maxlength="9" property="idFuncionario"
						tabindex="1" size="9"
						onkeyup="javascript:limparFuncionarioTecla();"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarEquipeAction.do?atualizar=' + document.forms[0].atualizar.value, 'idFuncionario', 'Funcionário');" />
					<a
						href="javascript:abrirPopup('exibirFuncionarioPesquisar.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Funcionário" /></a> <logic:present
						name="idFuncionarioNaoEncontrado" scope="request">
						<html:text maxlength="30" property="nomeFuncionario"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" />
					</logic:present> <logic:notPresent
						name="idFuncionarioNaoEncontrado" scope="request">
						<html:text maxlength="30" property="nomeFuncionario"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" />
					</logic:notPresent> <a href="javascript:limparFuncionario();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td width="130"><strong>Serviço Perfil Tipo:</strong></td>
					<td colspan="2"><html:text maxlength="3" property="idPerfilServico"
						tabindex="1" size="4"
						onkeyup="javascript:limparServicoPerfilTecla();"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarEquipeAction.do?atualizar=' + document.forms[0].atualizar.value, 'idPerfilServico', 'Serviço Perfil Tipo');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarTipoPerfilServicoAction.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Serviço Perfil Tipo" /></a> <logic:present
						name="idServicoPerfilNaoEncontrado" scope="request">
						<html:text maxlength="40" property="descricaoPerfilServico"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" />
					</logic:present> <logic:notPresent
						name="idServicoPerfilNaoEncontrado" scope="request">
						<html:text maxlength="40" property="descricaoPerfilServico"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" />
					</logic:notPresent> <a href="javascript:limparServicoPerfil();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				
				 
                        <!-- Começo usuário responsável pela Execução do serviço -->
                        
                        <tr>
							<td><strong>Usuario Responsavel Pela Execução do Serviço:</strong></td>
					
							<td colspan="4">						
								<html:text maxlength="11" 
								tabindex="1"
								property="cdUsuarioRespExecServico" 
								name="FiltrarEquipeActionForm"
								size="9"
								style="text-transform: none;"
								onkeypress="validaEnterStringSemUpperCase(event, 'exibirFiltrarEquipeAction.do?tipoPesquisa=usuario','cdUsuarioRespExecServico','cdUsuarioRespExecServico');
							            return isCampoNumerico(event);"/>
								<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?mostrarLogin=s')">
								<img width="23" 
									height="21" 
									border="0" 
									style="cursor:hand;"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar usuário"/>
								</a>
								

								<logic:present name="usuarioRespExecServicoEncontrado" scope="session">
									<html:text property="nomeUsuarioRespExecServico" 
										name="FiltrarEquipeActionForm"
										size="33"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present> 

								<logic:notPresent name="usuarioRespExecServicoEncontrado" scope="session">
								<html:text property="nomeUsuarioRespExecServico" 
									name="FiltrarEquipeActionForm"
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
							
						</tr><!-- fim usuário responsável pela Execução do serviço -->	
				
				<tr>
					<td><strong>Equipamento Especial:</strong> <font
						color="#FF0000">*</font></td>

					<td colspan="2"><html:select property="equipamentosEspeciasId"
						style="width: 230px;" onchange="limparFuncionarioClick();">
						<logic:present name="colecaoEquipamentosEspeciais" scope="request">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEquipamentosEspeciais"
								labelProperty="descricao" property="id"/>

						</logic:present>
					</html:select></td>

				</tr>
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td align="right" colspan="2">
					<div align="left"><html:radio property="indicadorUso" tabindex="4"
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
					<strong>Ativo</strong> <html:radio property="indicadorUso"
						tabindex="5"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
					<strong>Inativo</strong> <html:radio property="indicadorUso"
						tabindex="5" value="" /> <strong>Todos</strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de Programa&ccedil;&atilde;o Autom&aacute;tica:</strong></td>
					<td align="right" colspan="2">
						<div align="left">
							<html:radio property="indicadorProgramacaoAutomatica" tabindex="6"
								value="<%=ConstantesSistema.SIM.toString()%>" />
							<strong>Ativo</strong>
						    <html:radio property="indicadorProgramacaoAutomatica" tabindex="7"
								value="<%=ConstantesSistema.NAO.toString()%>" />
							<strong>Inativo</strong>
							<html:radio property="indicadorProgramacaoAutomatica"
								tabindex="8" value="" />
							<strong>Todos</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td><input type="button" name="ButtonReset" class="bottonRightCol"
						value="Limpar"
						onclick="window.location.href='/gsan/exibirFiltrarEquipeAction.do?menu=sim';">
					<td colspan="2" align="right"><gsan:controleAcessoBotao
						name="Button" value="Filtrar"
						onclick="javascript:validarForm(document.forms[0]);"
						url="filtrarEquipeAction.do" /> <%-- <input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
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
