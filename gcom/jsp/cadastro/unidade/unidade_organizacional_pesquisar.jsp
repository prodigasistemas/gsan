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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarUnidadeOrganizacionalActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

	function validarForm(){
   		var form = document.forms[0];

    	if(validatePesquisarUnidadeOrganizacionalActionForm(form)){
   			form.submit();
	  	}
    }

	function limparForm(){

		var form = document.forms[0];

		form.unidadeTipo.value = "";
		form.nivelHierarquico.value = "";
		form.descricao.value = "";
		form.sigla.value = "";
		
		form.unidadeTipo.selectedIndex = 0;
		form.gerenciaRegional.selectedIndex = 0;
		form.meioSolicitacao.selectedIndex = 0;

		form.unidadeEsgoto[0].checked = false;
		form.unidadeEsgoto[1].checked = false;
		form.unidadeEsgoto[2].checked = false;		
		
		form.unidadeAbreRegistro[0].checked = false;
		form.unidadeAbreRegistro[1].checked = false;
		form.unidadeAbreRegistro[2].checked = false;		

		form.unidadeAceita[0].checked = false;
		form.unidadeAceita[1].checked = false;
		form.unidadeAceita[2].checked = false;		
		
        form.idUnidadeCentralizadora.selectedIndex = 0;
        form.idEmpresa.selectedIndex = "";
        
		limparPesquisaLocalidade();
		limparUnidadeSuperior();
		
	}

	function limparPesquisaLocalidade() {

    	var form = document.forms[0];

    	form.idLocalidade.value = "";
    	form.descricaoLocalidade.value = "";
  	}

    function limparUnidadeSuperior() {
        var form = document.forms[0];

        form.idUnidadeSuperior.value = "";
    	form.descricaoUnidadeSuperior.value = "";
    }
  
    function submitForm() {
    	var form = document.forms[0];
    	
    	form.action = 'exibirPesquisarUnidadeOrganizacionalAction.do'
    	form.submit();
    }

</script>

</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(640, 590);javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarUnidadeOrganizacionalAction" method="post">
	
	<table width="600" border="0" cellspacing="5" cellpadding="0">
		<tr>
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
					<td class="parabg">Pesquisar Unidade Organizacional</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Preencha o campo para pesquisar uma unidade organizacional:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroUnidadeOrganizacionalPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td>
						<strong> Tipo da Unidade:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="unidadeTipo" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoUnidadeTipo" scope="session">
								<html:options collection="colecaoUnidadeTipo"
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>N&iacute;vel Hier&aacute;rquico:</strong></td>
					<td>
						<html:text maxlength="5" 
							property="nivelHierarquico" 
							tabindex="1"
							size="5" />
					</td>
				</tr>

				<tr>
					<td><strong>Localidade:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="idLocalidade" 
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarUnidadeOrganizacionalAction.do?objetoConsulta=1','idLocalidade','Localidade');"/> 
							
							<a href="javascript:redirecionarSubmit('exibirPesquisarLocalidadeAction.do?caminhoRetornoTelaPesquisa=exibirPesquisarUnidadeOrganizacionalAction');">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Localidade" /></a> 

							<logic:present name="idLocalidadeEncontrada" scope="request">
								
								<html:text property="descricaoLocalidade" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
								
							</logic:present> 

							<logic:notPresent name="idLocalidadeEncontrada" scope="request">
								
								<html:text property="descricaoLocalidade" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />

							</logic:notPresent>

							
							<a href="javascript:limparPesquisaLocalidade();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>
						</td>
				</tr>

				<tr>
					<td>
						<strong> Ger&ecirc;ncia Regional:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="gerenciaRegional" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoGerenciaRegional"
								scope="session">
								<html:options collection="colecaoGerenciaRegional"
								labelProperty="nome" property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td>
						<html:text maxlength="40" 
							property="descricao" 
							tabindex="1"
							size="40" /></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td width="66%">
						<html:radio property="tipoPesquisa"
							value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto
						<html:radio property="tipoPesquisa"
							tabindex="5"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
					</td>
				</tr>
				

				<tr>
					<td><strong>Sigla:</strong></td>
					<td>
						<html:text maxlength="5" 
							property="sigla" 
							tabindex="1"
							size="5" /></td>
				</tr>

				<tr>
					<td>
						<strong>Empresa:
						</strong>
					</td>

					<td>
						<strong> 
						<html:select property="idEmpresa" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoEmpresa" scope="session">
								<html:options collection="colecaoEmpresa"
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				
				</tr>

				<tr>
					<td><strong>Unidade Superior:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="idUnidadeSuperior" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarUnidadeOrganizacionalAction.do?objetoConsulta=2','idUnidadeSuperior','Unidade Superior');"/> 
							
							<a href="javascript:redirecionarSubmit('exibirPesquisarUnidadeSuperiorAction.do?caminhoRetornoTelaPesquisa=exibirPesquisarUnidadeOrganizacionalAction');">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Superior" /></a>

						<logic:present name="idUnidadeEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeSuperior" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
							
						</logic:present> 

						<logic:notPresent name="idUnidadeEncontrada" scope="request">
							
							<html:text property="descricaoUnidadeSuperior" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />

						</logic:notPresent>

						<a href="javascript:limparUnidadeSuperior();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					
					</td>
				</tr>

				<tr>
					<td>
						<strong> Unidade Centralizadora:
						</strong>
					</td>

					<td>
						<strong> 
						<html:select property="idUnidadeCentralizadora" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoUnidadeCentralizadora"
								scope="session">
								<html:options collection="colecaoUnidadeCentralizadora"
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Unidade de Esgoto?</strong>
					</td>
					<td>
						<html:radio property="unidadeEsgoto" value="<%=""+ConstantesSistema.SIM%>" />
						<strong>Sim</strong>
						<html:radio property="unidadeEsgoto" value="<%=""+ConstantesSistema.NAO%>" />
						<strong>N&atilde;o</strong>
						<html:radio property="unidadeEsgoto" value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" />
						<strong>Todas</strong> 
					</td>
				</tr>											

				<tr>
					<td>
						<strong>Unidade Abre Registro de Atendimento?</strong>
					</td>
					<td>
						<html:radio property="unidadeAbreRegistro" value="<%=""+ConstantesSistema.SIM%>" />
						<strong>Sim</strong>
						<html:radio property="unidadeAbreRegistro" value="<%=""+ConstantesSistema.NAO%>" />
						<strong>N&atilde;o</strong>
						<html:radio property="unidadeAbreRegistro" value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" />
						<strong>Todas</strong> 
					</td>
				</tr>											

				<tr>
					<td>
						<strong>Unidade Aceita Tramita&ccedil;&atilde;o?</strong>
					</td>
					<td>

						<html:radio property="unidadeAceita" value="<%=""+ConstantesSistema.SIM%>" />
						<strong>Sim</strong>
						<html:radio property="unidadeAceita" value="<%=""+ConstantesSistema.NAO%>" />
						<strong>N&atilde;o</strong>
						<html:radio property="unidadeAceita" value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" />
						<strong>Todas</strong> 
					</td>
				</tr>											

		        
				<tr>
					<td>
						<strong> Meio de Solicita&ccedil;&atilde;o Padr&atilde;o:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="meioSolicitacao" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoMeioSolicitacao"
								scope="session">
								<html:options collection="colecaoMeioSolicitacao"
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>


				<tr>
					<td height="24">
			          	<input type="button" class="bottonRightCol" value="Limpar" onclick="javascript:limparForm();"/>
							&nbsp;&nbsp;
	          			
	          			<logic:present name="caminhoRetornoTelaPesquisaUnidadeOrganizacional">
			          		<input type="button" 
			          			class="bottonRightCol" 
			          			value="Voltar" 
			          			onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaUnidadeOrganizacional}.do')"/>
			          	</logic:present>
        		  		
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
</html:form>
</body>
</html:html>
