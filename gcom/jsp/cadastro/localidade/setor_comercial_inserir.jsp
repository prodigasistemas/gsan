<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.operacional.FonteCaptacao"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	function limparLocalidade() {
    	var form = document.PesquisarAtualizarSetorComercialActionForm;
    	
	    form.localidadeID.value = "";
	    form.localidadeNome.value = "";
	    form.setorComercialCD.value = "";
	}

	function limparMunicipio() {
    	var form = document.PesquisarAtualizarSetorComercialActionForm;
    	
	    form.municipioID.value = "";
	    form.municipioNome.value = "";
	}

	function validarForm(formulario) {
		if (validatePesquisarAtualizarSetorComercialActionForm(formulario)){
			submeterFormPadrao(formulario);
		}
	}

	function validarLocalidade(){
	
		var form = document.forms[0];
		
		if(form.localidadeID.value == ""){
			alert("Informe Localidade");
			form.localidadeID.focus();
		}else{
			abrirPopup('exibirPesquisarSetorComercialAction.do?consulta=sim&idLocalidade='+document.forms[0].localidadeID.value);
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
		var form = document.forms[0];
	
		if (tipoConsulta == 'municipio') {
			form.municipioID.value = codigoRegistro;
		  	form.municipioNome.value = descricaoRegistro;
		  	form.municipioNome.style.color = "#000000";
		}
	
		if (tipoConsulta == 'localidade') {
	      	form.localidadeID.value = codigoRegistro;
	      	form.localidadeNome.value = descricaoRegistro;
		  	form.localidadeNome.style.color = "#000000";
		 	
		 	urlRedirect = "/gsan/exibirInserirSetorComercialAction.do?objetoConsulta=1";
		  	redirecionarSubmit(urlRedirect);
		}
		
		if (tipoConsulta == 'fonteCaptacao') {
			form.fonteCaptacao.value = codigoRegistro;
		  	form.descricaoFonteCaptacao.value = descricaoRegistro;
		  	form.descricaoFonteCaptacao.style.color = "#000000";
		}
		
	}

	function redirecionarSubmitAtualizar(idSetorComercial) {
		urlRedirect = "/gsan/exibirAtualizarSetorComercialAction.do?setorComercialID=" + idSetorComercial;
		redirecionarSubmit(urlRedirect);
	}
	
    function verificarAdicionar() {

    	var form = document.forms[0];
      	if (form.fonteCaptacao.value == ""){
			alert("Informe Fonte de Captação.");
	  	}else if (form.fonteCaptacao.value != '') {
        	if(form.fonteCaptacao.value == '0'){
      	  		alert("Fonte de Captação deve somente conter números positivos.");
			}else if(form.fonteCaptacao.value < '0'){
		  		alert("Fonte de Captação deve somente conter números positivos.");
			}else{
		  		if(!validateCaracterEspecial(form)){
    	    		alert("Fonte de Captação deve somente conter números positivos.");
        		}else if(!validateLong(form)){

				}else{
   	    			form.action='exibirInserirSetorComercialAction.do?acao=A';
			    	submeterFormPadrao(form);
				}		    
	  		}
	    }
  	}
  	
  	function limparFonteCaptacao(){
  		var form = document.forms[0];
  	
  		form.fonteCaptacao.value = "";
  		form.descricaoFonteCaptacao.value = "";
  	}
  	
	/* Remove Componente da grid */	
	function remover(id){
  		
  		var form = document.forms[0];
  		
		var where_to= confirm("Deseja realmente remover esta fonte ?");
		if (where_to== true) {
   			form.action='exibirInserirSetorComercialAction.do?acao=R&idRemover='+id;
	    	submeterFormPadrao(form);
 		}
	}
  	

</SCRIPT>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="PesquisarAtualizarSetorComercialActionForm" />


</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirSetorComercialAction" method="post">


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

			<td width="625" valign="top" class="centercoltext">

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
					<td class="parabg">Inserir Setor Comercial</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar o setor comercial, informe os dados
					abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroLocalizacaoSetorComercialInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>				
    			</tr>
    		</table>
    
    		<table width="100%" border="0">
				<tr>
					<td width="183">
						<strong>Localidade:<font color="#FF0000">*</font></strong>
					</td>
					
					<td width="432">
						<html:text property="localidadeID" 
							size="3"
							maxlength="3" 
							tabindex="1"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirSetorComercialAction.do?objetoConsulta=1', 'localidadeID', 'Localidade');" 
							onkeyup="somente_numero_zero_a_nove(this);" />
						
						<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 250, 495);">
							<img src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
								border="0"
								width="23" 
								height="21" 
								title="Pesquisar Localidade"></a> 
						
						<logic:present name="corLocalidade">
							<logic:equal name="corLocalidade" value="exception">
								<html:text property="localidadeNome" 
									size="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="corLocalidade" value="exception">
								<html:text property="localidadeNome" 
									size="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
							
							<a href="javascript:limparLocalidade();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Localidade" /></a>
									
						</logic:present> 
						
						<logic:notPresent name="corLocalidade">

							<logic:empty name="PesquisarAtualizarSetorComercialActionForm"
								property="localidadeID">
								
								<html:text property="localidadeNome" 
									value="" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							
							<logic:notEmpty name="PesquisarAtualizarSetorComercialActionForm" property="localidadeID">
							
								<html:text property="localidadeNome" 
									size="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
							
							<a href="javascript:limparLocalidade();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Localidade" /></a>
						</logic:notPresent>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>C&oacute;digo do Setor Comercial:
						<font color="#FF0000">*</font>
						</strong>
					</td>
					
					<td>
						<html:text maxlength="3" 
							property="setorComercialCD" 
							size="3"
							tabindex="2" 
							onkeyup="somente_numero_zero_a_nove(this);" />&nbsp;
							<a href="javascript:validarLocalidade();">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa_verde.gif"
									title="Pesquisar Setor Comercial" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Nome do Setor Comercial:
						<font color="#FF0000">*</font></strong>
					</td>
					
					<td align="right">
					<div align="left">
						<html:text maxlength="30"
							property="setorComercialNome" 
							size="45" 
							tabindex="3" /></div>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Munic&iacute;pio:
							<font color="#FF0000">*</font></strong>
					</td>
					
					<td align="right">
						<div align="left">
							<html:text property="municipioID" 
								size="5"
								maxlength="4" 
								tabindex="4"
								onkeypress="validaEnterComMensagem(event, 'exibirInserirSetorComercialAction.do?objetoConsulta=2', 'municipioID', 'Localidade');" 
								onkeyup="somente_numero_zero_a_nove(this);"/>
							
							<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 250, 495);">
								<img src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
									border="0"
									width="23" 
									height="21" 
									title="Pesquisar Município"></a> 
							
							<logic:present name="corMunicipio">
								<logic:equal name="corMunicipio" value="exception">
									<html:text property="municipioNome" 
										size="45" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>

								<logic:notEqual name="corMunicipio" value="exception">
									<html:text property="municipioNome" 
										size="45" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
								<a href="javascript:limparMunicipio();"> 
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" 
										title="Apagar Município" /></a>
							</logic:present>
							
							<logic:notPresent name="corMunicipio">
								<logic:empty name="PesquisarAtualizarSetorComercialActionForm"
									property="municipioID">
								
									<html:text property="municipioNome" 
										size="45" 
										value=""
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty name="PesquisarAtualizarSetorComercialActionForm" property="municipioID">
									<html:text property="municipioNome" 
										size="45" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
								
								<a href="javascript:limparMunicipio();"> 
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" 
										title="Apagar Município" /></a>
							</logic:notPresent>
						</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Alternativo? <font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorSetorAlternativo" 
						value="1" tabindex="4" /><strong>Sim</strong> <html:radio
						property="indicadorSetorAlternativo" value="2" tabindex="5" /><strong>Não</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="2">Para adicionar a(s) fonte(s) de captação, informe os dados abaixo:</td>
				</tr>

				<tr>
					<td><strong>Fonte de Captação:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="fonteCaptacao" 
							size="3"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirSetorComercialAction.do?objetoConsulta=3','fonteCaptacao','Fonte de Captação');"
							onkeyup="somente_numero_zero_a_nove(this);"/>

						<a href="javascript:abrirPopup('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=fonteCaptacao',275,480);">							
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Fonte de Captação" /></a>
								

						<logic:present name="fonteCaptacaoEncontrada" scope="request">
							<html:text property="descricaoFonteCaptacao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="fonteCaptacaoEncontrada" scope="request">
							<html:text property="descricaoFonteCaptacao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparFonteCaptacao();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar Fonte de Captação" /></a>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td align="right">
						<div align="left">
							<strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td><strong>Fonte(s) Informada(s)</strong></td>

					<td width="450" align="right">
						<input type="button"
							class="bottonRightCol" 
							style="bottonRightCol" 
							value="Adicionar"
							onclick="javascript:verificarAdicionar();" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">
						
						<tr>
							<td height="0">
							<table width="100%" bgcolor="#99CCFF">

								<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
									<td width="6%"><strong>Remover</strong></td>
									<td width="35%"><strong>Descrição</strong></td>
									<td width="15%"><strong>Descrição Abreviada</strong></td>
								</tr>
							</table>
							</td>
						</tr>
						
						<tr>
							<td height="83">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" align="left" bgcolor="#99CCFF">
								
							<%	int cont = 0;	%>
								
								<logic:notEmpty name="colecaoFonteCaptacao" scope="session">
								
									<logic:iterate name="colecaoFonteCaptacao"
										id="fonte" 
										type="FonteCaptacao">
									<%	cont = cont + 1;
										if (cont % 2 == 0) {	%>
											<tr bgcolor="#cbe5fe">
									<%	} else {	%>
											<tr bgcolor="#FFFFFF">
									<%	}	%>
											
											<td width="6%">
												<div align="center">
													<a href="javascript:remover('<bean:write name="fonte" property="id"/>');">
														<img src="<bean:message key="caminho.imagens"/>Error.gif" 
															border="0"
															width="14" 
															height="14" 
															title="Remover"></a> 												
												</div>
											</td>
											
											<td align="left" width="35%">
												<div align="left">
													<bean:write name="fonte" property="descricao"/>
												</div>
											</td>
											
											<td width="15%">
												<div align="left">
													<bean:write name="fonte" property="descricaoAbreviada"/>
												</div>
											</td>
											</tr>
									</logic:iterate>
								</logic:notEmpty>
							</table>
							</div>
							</td>
						</tr>						
						

					</table>
					</td>
				</tr>				

				<tr>
					<td colspan="2">
						<input type="button" 
							name="Button" 
							class="bottonRightCol"
							value="Desfazer" 
							tabindex="6"
							onClick="javascript:window.location.href='/gsan/exibirInserirSetorComercialAction.do?menu=sim'"
							style="width: 80px" />&nbsp; 
					&nbsp;
						<input type="button" 
								name="Button"
								class="bottonRightCol" 
								value="Cancelar" 
								tabindex="6"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
								style="width: 80px" />
					</td>
					
					<td align="right">
						<gsan:controleAcessoBotao name="Button" 
							value="Inserir"
							onclick="javascript:validarForm(document.forms[0]);"
							url="inserirSetorComercialAction.do" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>
