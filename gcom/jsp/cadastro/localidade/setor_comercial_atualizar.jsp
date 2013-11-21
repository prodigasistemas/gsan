<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.operacional.FonteCaptacao"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  
	formName="PesquisarAtualizarSetorComercialActionForm" 
	dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">
	var bCancel = false; 

   	function validatePesquisarAtualizarSetorComercialActionForm(form) {
	   	if (bCancel) 
   			return true; 
       	else 
   	 		return validateCaracterEspecial(form) && validateLong(form); 
	}

    function caracteresespeciais () { 
		this.ac = new Array("setorComercialNome", "Nome do Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("municipioID", "Município possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.ab = new Array("municipioID", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

	function validarForm(formulario){

		var objSetorComercialNome = returnObject(formulario, "setorComercialNome");
		var objMunicipioID = returnObject(formulario, "municipioID");

		if(objSetorComercialNome.value.length < 1){
			alert("Informe Nome do Setor Comercial.");
			objSetorComercialNome.focus();
		} else if(objMunicipioID.value.length < 1){
			alert("Informe Município.");
			objMunicipioID.focus();
		} else if (validatePesquisarAtualizarSetorComercialActionForm(formulario)){
			submeterFormPadrao(formulario);
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'municipio') {
	  		form.municipioID.value = codigoRegistro;
	  		form.municipioNome.value = descricaoRegistro;
	  		form.municipioNome.style.color = "#000000";
		}
		
		if (tipoConsulta == 'fonteCaptacao') {
			form.fonteCaptacao.value = codigoRegistro;
		  	form.descricaoFonteCaptacao.value = descricaoRegistro;
		  	form.descricaoFonteCaptacao.style.color = "#000000";
		}
		
	}
	
	function limpar(){
   		var form = document.forms[0];

   		form.municipioID.value = "";
   		form.municipioNome.value = "";
   		form.municipioID.focus();
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
   	    			form.action='exibirAtualizarSetorComercialAction.do?acao=A';
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
   			form.action='exibirAtualizarSetorComercialAction.do?acao=R&idRemover='+id;
	    	submeterFormPadrao(form);
 		}
	}
	
</SCRIPT>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarSetorComercialAction" method="post">
	
	<html:hidden name="PesquisarAtualizarSetorComercialActionForm" property="setorComercialID" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp" %>

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

	      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        	<tr>
	          		<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
	          		<td class="parabg">Atualizar Setor Comercial</td>
	          		<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
	        	</tr>
	      	</table>
	      	
      		<p>&nbsp;</p>

	      	<table width="100%" border="0">
		      	<tr>
		      		<td colspan="2">Para alterar o setor comercial, informe os dados abaixo:</td>
		      		<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroLocalizacaoSetorComercialAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
		      	</tr>
	      	</table>
    
      		<table width="100%" border="0">
	      	
	      		<tr>
	      			<td width="30%" height="20"><strong>Localidade:</strong></td>
	        		<td width="70%">
						<bean:write name="PesquisarAtualizarSetorComercialActionForm" 
							property="localidadeID"/> - 
	        			<bean:write name="PesquisarAtualizarSetorComercialActionForm" 
	        				property="localidadeNome"/>
					</td>
	      		</tr>

		      	<tr>
		      		<td height="20">
		      			<strong>C&oacute;digo do Setor Comercial:</strong>
		      		</td>
		        	<td>
		        		<bean:write name="PesquisarAtualizarSetorComercialActionForm" 
		        			property="setorComercialCD"/>
		        	</td>
		      </tr>
		      
		      <tr>
		      	<td height="20"><strong>Nome do Setor Comercial:
		      		<font color="#FF0000">*</font></strong>
		      	</td>
		        
		        <td align="right">
					<div align="left">
					<html:text maxlength="30" 
						property="setorComercialNome" 
						size="45" 
						tabindex="1"/>

					</div>
				</td>
      		</tr>
      		
		   	<tr>
		    	<td height="20">
		    		<strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong>
		    	</td>
		        
		        <td align="right">
					<div align="left">
					
					<html:text property="municipioID" 
						size="5" 
						maxlength="4" 
						tabindex="2" 
						onkeypress="validaEnterComMensagem(event, 'exibirAtualizarSetorComercialAction.do?objetoConsulta=2', 'municipioID', 'Município');"/>
					
					<a	href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 250, 495);">
						<img src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
							border="0"
							width="23" 
							height="21" 
							title="Pesquisar"></a> 

					<logic:present name="corMunicipio">
						<logic:equal name="corMunicipio" value="exception">
							<html:text property="municipioNome" 
								size="45" 
								readonly="true" 
								style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:equal>

						<logic:notEqual name="corMunicipio" value="exception">
							<html:text property="municipioNome" 
								size="45" 
								readonly="true" 
								style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEqual>
					</logic:present>

					<logic:notPresent name="corMunicipio">
						<logic:empty name="PesquisarAtualizarSetorComercialActionForm" property="municipioID">
							<html:text property="municipioNome" 
								size="45" 
								value="" 
								readonly="true" 
								style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:empty>
						
						<logic:notEmpty name="PesquisarAtualizarSetorComercialActionForm" property="municipioID">
							<html:text property="municipioNome" 
								size="45" 
								readonly="true" 
								style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEmpty>
					</logic:notPresent>
					
					<a href="javascript:limpar();"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" 
							border="0" 
							title="Apagar" /></a>

					</div>
				</td>
      		</tr>
	  		
	  		<tr>
        		<td><strong>Indicador de uso:</strong></td>
        		<td>
					<html:radio property="indicadorUso" value="1"/><strong>Ativo
					<html:radio property="indicadorUso" value="2"/>Inativo</strong>
				</td>
      		</tr>
      		
      		<logic:equal name="pemissaoIndicadorBloqueio" value="1">
			   	<tr>
			        <td><strong>Indicador de Bloqueio:</strong></td>
			        <td>
						<html:radio property="indicadorBloqueio" value="1"/><strong>Sim
						<html:radio property="indicadorBloqueio" value="2"/>Não</strong>
					</td>
			    </tr>
    		</logic:equal>
    		
    		<logic:equal name="pemissaoIndicadorBloqueio" value="2">
			   	<tr>
			        <td><strong>Indicador de Bloqueio:</strong></td>
			        <td>
			        	<logic:equal name="bloqueio" value="true">
							<input type="radio" name="indicadorBloqueio" value="1" disabled checked><strong>Sim</strong>
							<input type="radio" name="indicadorBloqueio" value="2" disabled><strong>Não</strong>
						</logic:equal>
						<logic:equal name="bloqueio" value="false">
							<input type="radio" name="indicadorBloqueio" value="1" disabled><strong>Sim</strong>
							<input type="radio" name="indicadorBloqueio" value="2" disabled checked><strong>Não</strong>
						</logic:equal>
					</td>
	    		</tr>
    		</logic:equal>
      		
      		<tr>
        		<td><strong>Setor Alternativo:</strong></td>
        		<td>
					<html:radio property="indicadorSetorAlternativo" value="1"/><strong>Sim</strong>
					<html:radio property="indicadorSetorAlternativo" value="2"/><strong>Não</strong>
				</td>
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
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarSetorComercialAction.do?objetoConsulta=3','fonteCaptacao','Fonte de Captação');"/>

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
							title="Apagar" /></a>
				</td>
			</tr>

	      	<tr>
	      		<td><strong> <font color="#FF0000"></font></strong></td>
	        	<td align="right">
	        		<div align="left"><strong><font color="#FF0000">*</font></strong>
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
      			<td colspan="2" widt="100%">
      			<table width="100%">
					<tr>
						<td> 
		      				<logic:present name="voltar">
							
								<logic:equal name="voltar" value="filtrar">
									<input name="Button" 
										type="button" 
										class="bottonRightCol"
										value="Voltar" 
										align="left"
										onclick="window.location.href='<html:rewrite page="/exibirAtualizarSetorComercialAction.do?objetoConsulta=1"/>'">
								</logic:equal>
								
								<logic:equal name="voltar" value="filtrarNovo">
									<input name="Button" 
										type="button" 
										class="bottonRightCol"
										value="Voltar" 
										align="left"
										onclick="window.location.href='<html:rewrite page="/exibirFiltrarSetorComercialAction.do"/>'">
								</logic:equal>
								
								<logic:equal name="voltar" value="manter">
									<input name="Button" 
										type="button" 
										class="bottonRightCol"
										value="Voltar" 
										align="left"
										onclick="window.history.back();">
								</logic:equal>
							</logic:present>
							
							<logic:notPresent name="voltar">
								<input name="Button" 
									type="button" 
									class="bottonRightCol"
									value="Voltar" 
									align="left"
									onclick="window.location.href='<html:rewrite page="/exibirAtualizarSetorComercialAction.do"/>'">
							</logic:notPresent>
							
							<input name="Submit22"
								class="bottonRightCol" 
								value="Desfazer" 
								type="button"
								onclick="window.location.href='/gsan/exibirAtualizarSetorComercialAction.do?setorComercialID=<bean:write name="PesquisarAtualizarSetorComercialActionForm" property="setorComercialID" />';">	
							
							<input name="Submit23" 
								class="bottonRightCol" 
								value="Cancelar"
								type="button"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
	      				</td>
	      				
	      				<td align="right">
	      					<gsan:controleAcessoBotao name="Button" 
	      						value="Atualizar"
							  	onclick="javascript:validarForm(document.forms[0]);" 
							  	url="atualizarSetorComercialAction.do"/>
						</td>
	      			</tr>
      			</table>
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
