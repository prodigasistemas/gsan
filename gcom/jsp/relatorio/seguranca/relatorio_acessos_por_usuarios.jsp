<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioAcessosUsuariosActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript">
		
	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarRelatorioAcessosUsuariosActionForm(form)){
			
			toggleBox('demodiv',1);	
		}
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}
	
  	function limpar(){
  		var form = document.forms[0];
  		
  		form.operacao.disabled = true;
		form.idFuncionalidade.disabled = false;
		form.idsPermissaoEspecial.disabled = false;
		form.idsUsuarioTipo.disabled = false;
		form.idsSituacaoUsuario.disabled = false;
		form.idsUnidadeOrganizacional.disabled = false;
		form.idsGrupoAcesso.disabled = false;
		form.modulo.disabled = false;
  		form.idUsuario.disabled = false;
  		
  		form.operacao.value = "-1";
		form.modulo.value = "-1";
		form.idUsuario.value = "";
		form.nomeUsuario.value = "";
		form.idFuncionalidade.value = "";
  		form.nomeFuncionalidade.value = "";
		
		form.idsPermissaoEspecial.selectedIndex = "-1";
		form.idsUnidadeOrganizacional.selectedIndex = "-1";
		form.idsGrupoAcesso.selectedIndex = "-1";
		form.idsUsuarioTipo.selectedIndex = "-1";
		form.idsSituacaoUsuario.selectedIndex = "-1";
  	}	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'funcionalidade') {
      		
      		form.idFuncionalidade.value = codigoRegistro;
	  		form.nomeFuncionalidade.value = descricaoRegistro;
	  		form.nomeFuncionalidade.style.color = "#000000";
	  		form.action = 'exibirGerarRelatorioAcessosUsuariosAction.do';
 			form.submit();
		}
		
		if (tipoConsulta == 'usuario') {
      		
      		form.idUsuario.value = codigoRegistro;
	  		form.nomeUsuario.value = descricaoRegistro;
	  		form.nomeUsuario.style.color = "#000000";
		}
	}	

	function limparPesquisaFuncionalidade() {
		var form = document.forms[0];
		
		form.idFuncionalidade.value = "";
		form.nomeFuncionalidade.value = "";	
		form.modulo.disabled = false;
		form.modulo.value = "-1";
		form.operacao.disabled = true;
		form.operacao.value = "-1";
	}	
	function limparPesquisaUsuario(){
		var form = document.forms[0];
		form.idUsuario.value = "";
		form.nomeUsuario.value = "";
		desabilitarCampos();
	}
	
	function desabilitarCampos(){
		var form = document.forms[0];
		var boolean  = false;
		
		form.operacao.disabled = true;
		form.idFuncionalidade.disabled = false;
		form.idsPermissaoEspecial.disabled = false;
		form.idsUsuarioTipo.disabled = false;
		form.idsSituacaoUsuario.disabled = false;
		form.idsUnidadeOrganizacional.disabled = false;
		form.idsGrupoAcesso.disabled = false;
		form.modulo.disabled = false;
		
		if (form.idUsuario.value != ""){
			
			form.idsUsuarioTipo.selectedIndex = "-1";
			form.idsSituacaoUsuario.selectedIndex = "-1";
			form.idsGrupoAcesso.selectedIndex = "-1";
			form.idsUnidadeOrganizacional.selectedIndex = "-1";
			form.idsPermissaoEspecial.selectedIndex = "-1";
			
			form.idsSituacaoUsuario.disabled = true;
			form.idsUsuarioTipo.disabled = true;
			form.idsGrupoAcesso.disabled = true;
			form.idsUnidadeOrganizacional.disabled = true;
			form.modulo.disabled = true;
			form.idsPermissaoEspecial.disabled = true;
			form.operacao.disabled = true;
			form.idFuncionalidade.disabled = true;
			
			boolean  = true;
		}else if(form.idsPermissaoEspecial.selectedIndex > 0 && form.idUsuario.value == ""){
			form.idsUsuarioTipo.disabled = false;
			form.idsGrupoAcesso.disabled = false;
			form.idsSituacaoUsuario.disabled = false;
			form.idsUnidadeOrganizacional.disabled = false;
			form.modulo.disabled = false;
			form.idsPermissaoEspecial.disabled = false;
			form.operacao.disabled = false;
			form.idFuncionalidade.disabled = false;
		}
		
		if (form.idsPermissaoEspecial.selectedIndex > 0){
			
			form.idsUsuarioTipo.selectedIndex = "-1";
			form.idsSituacaoUsuario.selectedIndex = "-1";
			form.idsGrupoAcesso.selectedIndex = "-1";
			
			form.idsGrupoAcesso.disabled = true;
			form.idsUsuarioTipo.disabled = true;
			form.idsSituacaoUsuario.disabled = true;
			form.idsUnidadeOrganizacional.disabled = false;
			
			form.idUsuario.disabled = true;
			form.modulo.disabled = true;
			form.operacao.disabled = true;
			form.idFuncionalidade.disabled = true;
			
			boolean  = true;
		}else if( form.idUsuario.value == "" && form.idsPermissaoEspecial.selectedIndex > 0){
			
			form.idsGrupoAcesso.disabled = false;
			form.idsUsuarioTipo.disabled = false;
			form.idsSituacaoUsuario.disabled = false;
			form.idsUnidadeOrganizacional.disabled = false;
			
			form.modulo.disabled = false;
			form.idUsuario.disabled = false;
			form.operacao.disabled = false;
			form.idFuncionalidade.disabled = false;
		}
		
		if(form.idFuncionalidade.value != ""){
			form.modulo.disabled = true;
			form.modulo.value = "-1";
			form.operacao.disabled = false;
			form.operacao.value = "-1";
			boolean  = true;
		} 
		
		if (form.modulo.value != '-1'){
			form.idFuncionalidade.disabled = true;
			form.idFuncionalidade.value = "";
			form.nomeFuncionalidade.value = "";
			boolean  = true;
		} 
		
		if(form.idsSituacaoUsuario.selectedIndex > 0 ){
			form.idUsuario.disabled = true;
			form.idUsuario.value = "";
			form.nomeUsuario.value = "";
			boolean  = true;
		}

		if (form.idsUsuarioTipo.selectedIndex > 0 && form.idsSituacaoUsuario.selectedIndex > 0){
			
			form.idUsuario.disabled = true;
			form.idUsuario.value == "";
			boolean  = true;
		}
		
		if(form.idsUnidadeOrganizacional.selectedIndex > 0){
			boolean  = true;
		}
		
		if(form.idsGrupoAcesso.selectedIndex > 0){
			boolean  = true;
		}
	}
		  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload ="javascript:desabilitarCampos();
OnDivScroll(document.forms[0].idsPermissaoEspecial, 6);OnDivScroll(document.forms[0].idsUnidadeOrganizacional, 6);">

<div id="formDiv"><html:form action="/gerarRelatorioAcessosUsuariosAction.do"
	name="GerarRelatorioAcessosUsuariosActionForm"
	type="gcom.gui.relatorio.seguranca.GerarRelatorioAcessosUsuariosActionForm"
	method="post">

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
					<td class="parabg">Gerar Relatório de Acessos por Usuário </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>
				
				<tr>
				   <td width="22%">
				   		<strong>Usuário:<font color="#FF0000"></font></strong>
				   </td>
                   <td width="81%" height="24" colspan="2">
                   		<html:text maxlength="9" 
                   				   property="idUsuario" 
                   				   size="9"  
                   				   tabindex="1" 
                   				   onclick="javascript:desabilitarCampos();"
                   				   onkeypress="javascript:validaEnter(event, 'exibirGerarRelatorioAcessosUsuariosAction.do', 'idUsuario'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do', 250, 495);">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Usuário"/></a>
					
   		      			<logic:present name="usuarioInexistente" 
   		      						   scope="request">
							<input type="text" 
								   name="nomeUsuario" 
								   size="50" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="pesquisa.usuario.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="usuarioInexistente" 
                      					scope="request">
	                       	<html:text property="nomeUsuario" 
	                       			   size="50" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>
                        
						<a href="javascript:limparPesquisaUsuario();document.forms[0].idUsuario.focus();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>                   
				   </td>
                </tr>
                
                <tr>
					<td width="200"><strong>Tipo de Usuário: </strong></td>
					<td colspan="3">
						<html:select property="idsUsuarioTipo"
									 style="width: 230px;" 
									 multiple="mutiple" 
									 size="4" 
									 tabindex="2"
									 onclick="javascript:desabilitarCampos();" >
							<logic:notEmpty name="colecaoUsuarioTipo">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoUsuarioTipo"
											  labelProperty="descricao" 
											  property="id" />
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Situação do Usuário:<font color="#FF0000"></font></strong></td>
					<td colspan="3">
						<html:select property="idsSituacaoUsuario"
									 style="width: 230px;" 
									 multiple="mutiple" 
									 size="4" 
									 tabindex="3"
									 onclick="javascript:desabilitarCampos();" >
							<logic:notEmpty name="colecaoUsuarioSituacao">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoUsuarioSituacao"
											  labelProperty="descricaoUsuarioSituacao" 
											  property="id" />
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td width="200"><strong>Unidade Organizacional: <font color="#FF0000"></font></strong></td>
					<td colspan="3">
						<div id='disponiveis2' style="OVERFLOW: auto;WIDTH: 280px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idsUnidadeOrganizacional, 6);" >
							<html:select property="idsUnidadeOrganizacional" 
										 multiple="mutiple" 
										 size="4" 
										 tabindex="4"
										 onclick="javascript:desabilitarCampos();">
								<logic:notEmpty name="colecaoUnidadeOrganizacional">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoUnidadeOrganizacional"
										labelProperty="descricao" property="id" />
								</logic:notEmpty>
							</html:select>
							</div>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td width="200"><strong>Grupo de Acesso: </strong></td>
					<td colspan="3">
						<html:select property="idsGrupoAcesso"
									 style="width: 320px;" 
									 multiple="mutiple" 
									 size="4" 
									 tabindex="5"
									 onclick="javascript:desabilitarCampos();">
							<logic:notEmpty name="colecaoGrupoAcesso">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoGrupoAcesso"
									labelProperty="descricao" property="id" />
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="120"><strong>Módulo:</strong></td>
					<td colspan="2">
						<html:select property="modulo" 
									 tabindex="6" 
									 onclick="javascript:desabilitarCampos();">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoModulo"
										  labelProperty="descricaoModulo" 
										  property="id" />
						</html:select> 
						<font size="1">&nbsp; </font>
					</td>
				</tr>
				
				<tr>
				   <td width="22%">
				   		<strong>Funcionalidade:<font color="#FF0000"></font></strong>
				   </td>
                   <td width="81%" height="24" colspan="2">
                   		<html:text maxlength="9" 
                   				   property="idFuncionalidade" 
                   				   size="9"  
                   				   tabindex="7" 
                   				   onclick="javascript:desabilitarCampos();"
                   				   onkeypress="javascript:validaEnter(event, 'exibirGerarRelatorioAcessosUsuariosAction.do', 'idFuncionalidade'); return isCampoNumerico(event);"
                   		/>
						<a href="javascript:abrirPopup('exibirPesquisarFuncionalidadeAction.do', 600, 500);">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Funcionalidade"/></a>
					
					
   		      			<logic:present name="funcionalidadeInexistente" 
   		      						   scope="request">
							<input type="text" 
								   name="nomeFuncionalidade" 
								   size="50" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="atencao.funcionalidade.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="funcionalidadeInexistente" 
                      					scope="request">
	                       	<html:text property="nomeFuncionalidade" 
	                       			   size="50" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>
                        
						<a href="javascript:limparPesquisaFuncionalidade();document.forms[0].idFuncionalidade.focus();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>                   
				   </td>
                </tr>
				
				<tr>
					<td>
						<strong>Operação:<font color="#FF0000"></font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="operacao" 
									 style="width: 230px;" 
									 tabindex="8" 
									 disabled="true" 
									 onclick="javascript:desabilitarCampos();">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoOperacao" 
										   scope="request">
							   <html:options collection="colecaoOperacao"
											 labelProperty="descricao" 
											 property="id"/>
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>	
		
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td width="200"><strong>Permissão Especial: </strong></td>
					<td colspan="3">
						<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 340px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idsPermissaoEspecial, 6);" >
							<html:select property="idsPermissaoEspecial" 
										 multiple="mutiple" 
										 size="4" 
										 tabindex="5"
										 onclick="javascript:desabilitarCampos();">
								<logic:notEmpty name="colecaoPermissaoEspecial">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoPermissaoEspecial"
												  labelProperty="descricao" 
												  property="id" />
								</logic:notEmpty>
							</html:select>
						</div>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
								          	
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   value="Limpar" 
			          		   onclick="javascript:limpar();"/>
					</td>
				
					<td align="right">
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol" 
							   value="Gerar" 
							   onClick="javascript:validarForm()" />
					</td>
					
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioAcessosUsuariosAction.do" 
	/>	
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
