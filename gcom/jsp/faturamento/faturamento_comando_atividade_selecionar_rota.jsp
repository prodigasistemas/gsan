<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.micromedicao.Rota" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key='caminho.css'/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript">
<!--
var bCancel = false; 

function validatePesquisarActionForm(form) {                                                                   
	if (bCancel) 
		return true; 
	else 
		return validateCaracterEspecial(form) && validateLong(form) && validateInteiroZeroPositivo(form); 
} 

function caracteresespeciais () { 
	this.an = new Array("idLocalidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ao = new Array("numeroSetorComercial", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ap = new Array("idRota", "Rota possui caracteres especiais.", new Function ("varName", " return this[varName];"));
} 

function IntegerValidations () { 
	this.ak = new Array("idLocalidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.al = new Array("numeroSetorComercial", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}

function InteiroZeroPositivoValidations () { 
    this.aa = new Array("idRota", "Rota deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
} 

function limpar(tipo){
	var form = document.forms[0];

	switch (tipo){
        case 1:
		   form.idLocalidade.value = "";
		   form.descricaoLocalidade.value = "";
		   form.numeroSetorComercial.value = "";
		   form.descricaoSetorComercial.value = "";
		   form.idSetorComercial.value = "";
		   form.idRota.value = "";
		   form.descricaoRota.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.idLocalidade.focus();
		   break;
		case 2:
		   form.numeroSetorComercial.value = "";
		   form.descricaoSetorComercial.value = "";
		   form.idSetorComercial.value = "";
		   form.idRota.value = "";
		   form.descricaoRota.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.numeroSetorComercial.focus();
		   break;
		case 3:
		   form.idRota.value = "";
		   form.descricaoRota.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.idRota.focus();
		   break;
		case 4:
		   form.numeroSetorComercial.value = "";
		   form.descricaoSetorComercial.value = "";
		   form.idSetorComercial.value = "";
		   form.idRota.value = "";
		   form.descricaoRota.value = "";
		   
		   break;
		case 5:
		   form.idSetorComercial.value = "";
		   form.idRota.value = "";
		   form.descricaoRota.value = "";
		   
		   break;
		default:
           break;
	}
}

function validarForm(form){
	var objLocalidade = returnObject(form, "idLocalidade");
	var objSetorComercial = returnObject(form, "numeroSetorComercial");
	var objRota = returnObject(form, "idRota");
	
	if (validatePesquisarActionForm(form)){
	
		if (objLocalidade.value.length > 0 &&
			!testarCampoValorZero(objLocalidade, "Localidade ")){
			objLocalidade.focus();
		}
		else if (objSetorComercial.value.length > 0 &&
			!testarCampoValorZero(objSetorComercial, "Setor comercial ")){
			objSetorComercial.focus();
		}
		else{
			redirecionarSubmit('pesquisarSelecionarRotaAction.do');
		} 
	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		resizePageComLink(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			resizePageComLink(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
		}
	}
}

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
      form.numeroSetorComercial.value = codigoRegistro;
      form.idSetorComercial.value = idRegistro;
	  form.descricaoSetorComercial.value = descricaoRegistro;
	  form.descricaoSetorComercial.style.color = "#000000";
	  
	  limpar(3);
	}	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];
	if (tipoConsulta == 'localidade') {
		form.idLocalidade.value = codigoRegistro;
		form.descricaoLocalidade.value = descricaoRegistro;
		form.descricaoLocalidade.style.color = "#000000";
		
		limpar(2);
	}

	if (tipoConsulta == 'rota') {
		form.idRota.value = codigoRegistro;
		form.descricaoRota.value = descricaoRegistro;
		form.descricaoRota.style.color = "#000000";
	}	
}

function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

// Verifica se há item selecionado
function verificarSelecao(objeto){
	if (CheckboxNaoVazioMensagemGenerico("Nenhum registro foi selecionado.", objeto)){
		redirecionarSubmit('inserirSelecaoRotaAction.do');
	}
}

// Verifica se há item selecionado
function verificarSelecaoParaRemocao(objeto){
	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			redirecionarSubmit('removerSelecaoRotaAction.do?forward=exibirFiltrarRotaComandoAtividade');
		 }
	}
}

//-->
</script>
</head>

<logic:present name="retornarUseCase">
	<body topmargin="5" leftmargin="5" onload="chamarReload('atualizarComandoAtividadeFaturamentoWizardAction.do?action=exibirAtualizarComandoAtividadeFaturamentoRotasAction'),window.close();">
</logic:present>

<logic:notPresent name="retornarUseCase">
	<body topmargin="5" leftmargin="5" onload="window.focus();resizePageSemLink(700, 480); setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form action="/pesquisarSelecionarRotaAction" method="post">

<table width="650" border="0" cellpadding="0" cellspacing="5">
	<tr>
		<td width="640" valign="top" class="centercoltext">
			<table height="100%">
				<tr><td></td></tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img src="<bean:message key='caminho.imagens'/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" />
					</td>
					<td class="parabg">Selecionar Rotas</td>
					<td width="11">
						<img src="<bean:message key='caminho.imagens'/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>

<!-- ============================================================================================================================== -->

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha os campos para selecionar rotas:</td>
				</tr>
				<tr> 
					<td><strong>Grupo de Faturamento:</strong></td>
					<td>
						<html:select property="idGrupoFaturamento" style="width: 200px;" tabindex="1">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoGrupoFaturamento" labelProperty="descricao" property="id"/>
						</html:select>
					</td>
				</tr>
				<tr> 
					<td><strong>Ger&ecirc;ncia Regional:</strong></td>
					<td>
						<html:select property="idGerenciaRegional" style="width: 200px;" tabindex="2">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional" labelProperty="nomeAbreviado" property="id"/>
						</html:select>
					</td>
		 		</tr>
				<tr>
					<td><strong>Localidade:</strong></td>
					<td>
						<html:text property="idLocalidade" size="5" maxlength="3" tabindex="3" 
							onkeypress="limpar(4), validaEnter(event, 'exibirSelecionarRotaAction.do?objetoConsulta=1', 'idLocalidade');"/>
						<a href="javascript:resizePageComLink('exibirPesquisarLocalidadeAction.do?tipo=selecionarRotas', 250, 495);">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
								width="23" height="21" style="cursor:hand;" alt="Pesquisar"></a>
						<logic:present name="corLocalidade">
							<logic:equal name="corLocalidade" value="exception">
								<html:text property="descricaoLocalidade" size="45" readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:equal>
							<logic:notEqual name="corLocalidade" value="exception">
								<html:text property="descricaoLocalidade" size="45" readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="corLocalidade">
							<logic:empty name="PesquisarActionForm" property="idLocalidade">
								<html:text property="descricaoLocalidade" value="" size="45" readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:empty>
							<logic:notEmpty name="PesquisarActionForm" property="idLocalidade">
								<html:text property="descricaoLocalidade"size="45" readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limpar(1);">
							<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"
								style="cursor:hand;" alt="Apagar"></a>
					</td>
				</tr>
				<tr>
			        <td><strong>Setor Comercial:</strong></td>
			        <td>
						<html:text property="numeroSetorComercial" size="5" maxlength="3" tabindex="4" 
							onkeypress="limpar(5), validaEnterDependencia(event, 'exibirSelecionarRotaAction.do?objetoConsulta=2', document.forms[0].numeroSetorComercial, document.forms[0].idLocalidade.value, 'Localidade');"/>
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'selecionarRotas', 'idLocalidade', document.forms[0].idLocalidade.value, 275, 480, 'Informe a Localidade');">	
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
								width="23" height="21" style="cursor:hand;" alt="Pesquisar"></a>
						<logic:present name="corSetorComercial">
							<logic:equal name="corSetorComercial" value="exception">
								<html:text property="descricaoSetorComercial" size="45" readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:equal>
							<logic:notEqual name="corSetorComercial" value="exception">
								<html:text property="descricaoSetorComercial" size="45" readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="corSetorComercial">
							<logic:empty name="PesquisarActionForm" property="numeroSetorComercial">
								<html:text property="descricaoSetorComercial" value="" size="45" readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:empty>
							<logic:notEmpty name="PesquisarActionForm" property="numeroSetorComercial">
								<html:text property="descricaoSetorComercial" size="45" readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEmpty>
						</logic:notPresent>
						<html:hidden property="idSetorComercial"/>
						<a href="javascript:limpar(2);">
							<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"
								style="cursor:hand;" alt="Apagar"></a>
				    </td>
				</tr>
				<tr>
					<td><strong>Rota:</strong></td>
					<td>
						<html:text property="idRota" size="5" maxlength="5" tabindex="5" 
							onkeypress="validaEnterDependenciaAceitaZERO(event, 'exibirSelecionarRotaAction.do?objetoConsulta=3', document.forms[0].idRota, document.forms[0].numeroSetorComercial.value, 'Setor Comercial')"/>
						<a href="javascript:alert('Pesquisa não disponível. Informe Rota e tecle ENTER.'); document.forms[0].idRota.focus();">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
							width="23" height="21" style="cursor:hand;" alt="Pesquisar"></a>
						<logic:present name="corRotaMensagem">
							<logic:equal name="corRotaMensagem" value="exception">
								<html:text property="descricaoRota" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:equal>
							<logic:notEqual name="corRotaMensagem" value="exception">
								<html:text property="descricaoRota" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="corRotaMensagem">
							<logic:empty name="PesquisarActionForm" property="idRota">
								<html:text property="descricaoRota" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value=""/>
							</logic:empty>
							<logic:notEmpty name="PesquisarActionForm" property="idRota">
								<html:text property="descricaoRota" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limpar(3);">
							<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"
								style="cursor:hand;" alt="Apagar"></a>
					</td>
				</tr>
				<tr>
		        	<td height="10" colspan="2"></td>
				</tr>
				<tr> 
					<td><strong>Rotas selecionadas:</strong></td>
					<td align="right">
						<input type="button" class="bottonRightCol" value="Selecionar" 
							onclick="validarForm(document.forms[0]);" style="width: 70px;" name="botaoSelecionar">
					</td>
				</tr>
				<tr> 
					<td colspan="2">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr> 
                				<td> 
									<table width="100%" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF"> 
											<td align="center" width="10%">
												<a href="javascript:facilitador(this);" id="0"><strong>Todos</strong></a>
											</td>
											<td width="11%" align="center"><strong>Grupo</strong></td>
					                        <td width="9%" align="center"><strong>Ger&ecirc;ncia</strong></td>
					                        <td width="9%" align="center"><strong>Unidade Negócio</strong></td>
					                        <td width="26%" align="center"><strong>Localidade</strong></td>
					                        <td width="20%" align="center"><strong>Setor</strong></td>
					                        <td width="20%" align="center"><strong>Rota</strong></td>
					                    </tr>
                    				</table>
								</td>
				            </tr>
			            </table>
			
						<logic:present name="colecaoRotasSelecionadas" scope="session">
						<div style="width: 100%; height: 100; overflow: auto;">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr> 
								<td> 
									<%int cont = 0;%>
									<table width="100%" align="center" bgcolor="#99CCFF">
									<logic:iterate name="colecaoRotasSelecionadas" id="rota" type="Rota">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td align="center" width="10%">
												<html:checkbox property="idRotaSelecao" value="<%="" + rota.getId()%>"/>
											</td>
											<td width="11%" align="center">
												<logic:present name="rota" property="faturamentoGrupo">
													<bean:write name="rota" property="faturamentoGrupo.id"/>
												</logic:present>
												<logic:notPresent name="rota" property="faturamentoGrupo">
													&nbsp;
												</logic:notPresent>
											</td>
											<td width="9%" align="center">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.gerenciaRegional.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>
											<td width="9%" align="center">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.unidadeNegocio.nomeAbreviado"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>

											<td width="26%" align="left">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.localidade.descricao"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">
													&nbsp;
												</logic:notPresent>	
											</td>
											<td width="20%" align="center">
												<logic:present name="rota" property="setorComercial">
													<bean:write name="rota" property="setorComercial.codigo"/>
												</logic:present>
												<logic:notPresent name="rota" property="setorComercial">
													&nbsp;
												</logic:notPresent>
											</td>
											<td width="20%" align="center">
												<bean:write name="rota" property="codigo"/>
											</td>
										</tr>
									</logic:iterate>
									</table>
								</td>
				            </tr>
						</table>
						</div>
						</logic:present>
					</td>
        		</tr>
        
<!-- ============================================================================================================================== -->

				<tr>
					<td> 
						<input type="button" class="bottonRightCol" value="Remover" tabindex="7" 
							onclick="verificarSelecaoParaRemocao(idRotaSelecao);" style="width: 70px;" name="botaoRemover">
					</td>
					<td align="right"> 
						<input type="button" class="bottonRightCol" value="Inserir" tabindex="6" 
							onclick="verificarSelecao(idRotaSelecao);" style="width: 70px;" name="botaoInserir">
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