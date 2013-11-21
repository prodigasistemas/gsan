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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarUnidadeSuperiorActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

	function validarForm(){
   		var form = document.forms[0];

    	if(validatePesquisarUnidadeSuperiorActionForm(form)){
   			form.submit();
	  	}
    }

	function limparForm(){

		var form = document.forms[0];

		form.unidadeTipoFilho.value = "";
		form.nivelHierarquicoFilho.value = "";
		form.descricaoFilho.value = "";
		form.siglaFilho.value = "";
		
		form.unidadeTipoFilho.selectedIndex = 0;
		form.gerenciaRegionalFilho.selectedIndex = 0;

		form.unidadeEsgotoFilho[0].checked = false;
		form.unidadeEsgotoFilho[1].checked = false;
		form.unidadeEsgotoFilho[2].checked = false;		
		
		form.unidadeAbreRegistroFilho[0].checked = false;
		form.unidadeAbreRegistroFilho[1].checked = false;
		form.unidadeAbreRegistroFilho[2].checked = false;		

		form.unidadeAceitaFilho[0].checked = false;
		form.unidadeAceitaFilho[1].checked = false;
		form.unidadeAceitaFilho[2].checked = false;		
		
		form.meioSolicitacaoFilho.selectedIndex = 0;
        form.idEmpresaFilho.selectedIndex = "";
        
		limparPesquisaLocalidade();
		
	}

	function limparPesquisaLocalidade() {

    	var form = document.forms[0];

    	form.idLocalidadeFilho.value = "";
    	form.descricaoLocalidadeFilho.value = "";
  	}
  
    function submitForm() {
    	var form = document.forms[0];
    	
    	form.action = 'exibirPesquisarUnidadeSuperiorAction.do'
    	form.submit();
    }

</script>

</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(650, 520);javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarUnidadeSuperiorAction" method="post">
	
	<table width="600" border="0" cellspacing="0" cellpadding="0">
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
					<td class="parabg">Pesquisar Unidade Superior</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Preencha o campo para pesquisar uma unidade Superior:</td>
				</tr>

				<tr>
					<td>
						<strong> Tipo da Unidade:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="unidadeTipoFilho" style="width: 230px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
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
							property="nivelHierarquicoFilho" 
							tabindex="1"
							size="5" />
					</td>
				</tr>

				<tr>
					<td><strong>Localidade:</strong></td>
					
					<td>
						<html:text maxlength="3" 
							tabindex="1"
							property="idLocalidadeFilho" 
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarUnidadeSuperiorAction.do?objetoConsulta=1','idLocalidadeFilho','Localidade');"/> 
							
							<a href="javascript:redirecionarSubmit('exibirPesquisarLocalidadeAction.do?caminhoRetornoTelaPesquisa=exibirPesquisarUnidadeSuperiorAction');">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Localidade" /></a> 

							<logic:present name="idLocalidadeEncontradaFilho" scope="request">
								<html:text property="descricaoLocalidadeFilho" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="idLocalidadeEncontradaFilho" scope="request">
								<html:text property="descricaoLocalidadeFilho" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							
							<a href="javascript:limparPesquisaLocalidade();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>

				<tr>
					<td>
						<strong> Ger&ecirc;ncia Regional:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="gerenciaRegionalFilho" style="width: 230px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoGerenciaRegional" scope="session">
								<html:options collection="colecaoGerenciaRegional"
									labelProperty="nome" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="142"><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td>
						<html:text maxlength="9" 
							property="descricaoFilho" 
							tabindex="1"
							size="40" />
					</td>
				</tr>

				<tr>
					<td width="142"><strong>Sigla:</strong></td>
					<td>
						<html:text maxlength="5" 
							property="siglaFilho" 
							tabindex="1"
							size="5" />
					</td>
				</tr>

				<tr>
					<td>
						<strong>Empresa:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="idEmpresaFilho" style="width: 230px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
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
					<td>
						<strong>Unidade de Esgoto?</strong>
					</td>
					<td>
						<strong> 
							<html:radio property="unidadeEsgotoFilho" value="<%=""+ConstantesSistema.SIM%>" />
							Sim
							<html:radio property="unidadeEsgotoFilho" value="<%=""+ConstantesSistema.NAO%>" />
							N&atilde;o
							<html:radio property="unidadeEsgotoFilho" value="" />
							Todas
						</strong>
					</td>
				</tr>											

				<tr>
					<td>
						<strong>Unidade Abre Registro de Atendimento?</strong>
					</td>
					<td>
						<strong> 
							<html:radio property="unidadeAbreRegistroFilho" value="<%=""+ConstantesSistema.SIM%>" />
							Sim
							<html:radio property="unidadeAbreRegistroFilho" value="<%=""+ConstantesSistema.NAO%>" />
							N&atilde;o
							<html:radio property="unidadeAbreRegistroFilho" value="" />
							Todas
						</strong>
					</td>
				</tr>											

				<tr>
					<td>
						<strong>Unidade Aceita Tramita&ccedil;&atilde;o?</strong>
					</td>
					<td>
						<strong> 
							<html:radio property="unidadeAceitaFilho" value="<%=""+ConstantesSistema.SIM%>" />
							Sim
							<html:radio property="unidadeAceitaFilho" value="<%=""+ConstantesSistema.NAO%>" />
							N&atilde;o
							<html:radio property="unidadeAceitaFilho" value="" />
							Todas
						</strong>
					</td>
				</tr>											

		        
				<tr>
					<td>
						<strong> Meio de Solicita&ccedil;&atilde;o Padr&atilde;o:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="meioSolicitacaoFilho" style="width: 230px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoMeioSolicitacao" scope="session">
								<html:options collection="colecaoMeioSolicitacao"
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>


				<tr>
					<td>
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limparForm();"/>
							&nbsp;&nbsp;
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
