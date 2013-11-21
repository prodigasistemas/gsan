<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarLeituristaActionForm" />

<SCRIPT LANGUAGE="JavaScript">

	function chamarPopup(url,altura, largura,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			abrirPopup(url, altura, largura);
		}
			
    }
    
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}    
    
	function limparCliente(){
		var form = document.forms[0];
		
		form.idCliente.value = "";
		form.descricaoCliente.value = "";
	}
	
	function limparFuncionario(){
		var form = document.forms[0];
		
		form.idFuncionario.value = "";
		form.descricaoFuncionario.value = "";
	}
	
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	
    	var form= document.forms[0];
    	//var form= document.AtualizarHistoricoAlteracaoSistemaActionForm;
    	
		if ( tipoConsulta == 'cliente' ){
	      	form.idCliente.value = codigoRegistro;  
	      	form.descricaoCliente.value = descricaoRegistro;
	      	form.descricaoCliente.style.color = "#000000";  
	    } else if ( tipoConsulta == 'usuario' ){
  	      	form.loginUsuario.value = codigoRegistro;
	      	form.nomeUsuario.value = descricaoRegistro;
	      	form.nomeUsuario.style.color = '#000000';
		}else if ( tipoConsulta == 'funcionario' ){
  	      	form.idFuncionario.value = codigoRegistro;
	      	form.descricaoFuncionario.value = descricaoRegistro;
	      	form.descricaoFuncionario.style.color = '#000000';
		} 	    
    }	
    
	/* Limpar Usuário */
	function limparUsuario() {
		var form = document.forms[0];
		
      	form.loginUsuario.value = "";
	    form.nomeUsuario.value = "";
	
		form.loginUsuario.focus();
	}    

	function validarForm(){
		var form = document.AtualizarLeituristaActionForm;
		
		if(validateAtualizarLeituristaActionForm(form)){
			if((form.idFuncionario.value != null && form.idFuncionario.value != "")
				||(form.idCliente.value != null && form.idCliente.value != "")){
	       		submeterFormPadrao(form);
	       	}else{
	       		alert("Selecione um Funcionário ou um Cliente");
	       	}
	    }
	}
	
	  	function validaNumeroDocumento() {
		var form = document.forms[0];		
		if (!form.numeroDocumento1[""].checked
				&& !form.deferimento[1].checked) {
			alert("Informe Numero do Documento.");		
			return false;
		}		
		if (!form.idCodigo1[0].checked
				&& !form.idCodigo1[""].checked) {
			alert("Informe Motivo.");		
			return false;
		}		
		return true;
   	}
	
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form action="/atualizarLeituristaAction"
	name="AtualizarLeituristaActionForm"
	type="gcom.gui.cadastro.AtualizarLeituristaActionForm" method="post">

	<INPUT TYPE="hidden" name="removerEndereco">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


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
					<td class="parabg">Atualizar Leiturista</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar um Leiturista, informe os dados
					abaixo:</td>
					
 					
				<tr>
					<td><strong>Código do Leiturista:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="id" size="6" maxlength="6" tabindex="4" readonly="true" /></td>
				</tr>

				<tr>
					<td><strong>Funcionário :</strong></td>
					<td colspan="2">
						<html:text property="idFuncionario" 
									size="8"
									maxlength="8" tabindex="2"
									onkeypress="validaEnterComMensagem(event, 'exibirAtualizarLeituristaManterAction.do', 'idFuncionario','Funcionario');return isCampoNumerico(event);" />

					<a href="javascript:chamarPopup('exibirFuncionarioPesquisar.do?limpaForm=S', 495, 300,document.forms[0].idFuncionario);">
						<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Funcionario"
							border="0" height="21" width="23" title="Pesquisar Funcionário">
					</a> 
					<logic:present name="funcionalidadeEncontrada">
						<logic:equal name="funcionalidadeEncontrada" value="exception">
							<html:text property="descricaoFuncionario" size="40"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="funcionalidadeEncontrada" value="exception">
							<html:text property="descricaoFuncionario" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="funcionalidadeEncontrada">
						<logic:empty name="AtualizarLeituristaActionForm"
							property="idFuncionario">
							<html:text property="descricaoFuncionario" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="AtualizarLeituristaActionForm"
							property="idFuncionario">
							<html:text property="descricaoFuncionario" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> 
					<a href="javascript:limparFuncionario()"> 
						<img border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> 
					</a>
					</td>
				</tr>

				<tr>
					<td><strong>Cliente :</strong></td>
					<td colspan="2">
						<html:text property="idCliente" 
								   size="8"
								   maxlength="8" 
									tabindex="2"
									onkeypress="validaEnterComMensagem(event, 'exibirAtualizarLeituristaManterAction.do', 'idCliente','Cliente');return isCampoNumerico(event);"/>
						<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do');">
							<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Cliente"
								border="0" height="21" width="23" title="Pesquisar Cliente">
						</a> 
						<logic:present name="funcionalidadeEncontrada">
							<logic:equal name="funcionalidadeEncontrada" value="exception">
								<html:text property="descricaoCliente" size="40" maxlength="50"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="funcionalidadeEncontrada" value="exception">
								<html:text property="descricaoCliente" size="40" maxlength="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="funcionalidadeEncontrada">
							<logic:empty name="AtualizarLeituristaActionForm"
								property="idCliente">
								<html:text property="descricaoCliente" size="40" maxlength="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="AtualizarLeituristaActionForm"
								property="idCliente">
								<html:text property="descricaoCliente" size="40" maxlength="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparCliente()">
							<img border="0" title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> 
						</a>
					</td>
				</tr>

				<tr>
					<td><strong>Código do DDD do Município:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="ddd" 
								   size="2" 
								   maxlength="2" 
								   tabindex="4" 
								   onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>

				<tr>
					<td><strong>Numero Telefone:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="telefone"
									size="8" 
									maxlength="8"
									tabindex="4" 
									onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				
				<tr>
					<td><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left">
					
					<c:if test="${bloquearEmpresa == null}">
					<html:select property="empresaID">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
					</html:select>
					</c:if>
					
					<c:if test="${bloquearEmpresa != null}">
					<html:select property="empresaID" disabled="true">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
					</html:select>
					</c:if>
					
					</td>
				</tr>
				
				<tr>
					<td><strong>Número do IMEI:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="numeroImei" 
								   size="15" 
								   maxlength="15"
		   						   tabindex="4" 
		   						   onkeypress="return isCampoNumerico(event);"/>
		   			</td>
				</tr>
				
	            <tr> 
	              <td><strong>Login do usuário:</strong></td>
	              <td colspan="6"><span class="style2"><strong>
				<html:text property="loginUsuario" 
					size="11" 
					maxlength="11"
					style="text-transform: none;"
					onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirAtualizarLeituristaManterAction.do', 'loginUsuario');"/>
				
				<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=S', 'usuario', null, null, 370, 600, '',document.forms[0].loginUsuario);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Usuário"></a>
					 
				<logic:present name="usuarioEncontrado" scope="session">
					<html:text property="nomeUsuario" 
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" 
						size="36" 
						maxlength="36" />
						
				</logic:present> 
	
				<logic:notPresent name="usuarioEncontrado" scope="session">
					<html:text property="nomeUsuario" 
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000" 
						size="36" 
						maxlength="36" />
				</logic:notPresent>
				
				<a href="javascript:limparUsuario();">
					<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
				</a>
	                </strong></span></td>
	            </tr>				

				<tr>
        		<td><strong>Indicador de uso:</strong></td>
        		<td>
					<html:radio property="indicadorUso" value="1"/><strong>Ativo
					<html:radio property="indicadorUso" value="2"/>Inativo</strong>
				</td>
      			</tr>
				
				<tr>
					<td></td>
					<td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
				</tr>

				<tr>
					<td>
						<input type="button"
							   name="ButtonCancelar" 
							   class="bottonRightCol" 
							   value="Voltar"
							   onClick="window.history.go(-1)">
						<input name="Button" 
							   type="button" 
							   class="bottonRightCol"
							   value="Desfazer" 
							   align="left"
							   onclick="window.location.href='<html:rewrite page="/exibirAtualizarLeituristaManterAction.do?desfazer=S"/>'">
						<input type="button" 
							   name="ButtonCancelar" 
							   class="bottonRightCol"
							   value="Cancelar"
							   onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						 <gsan:controleAcessoBotao name="Botao" 
							 	value="Atualizar"
								onclick="validarForm(document.forms[0]);"
								url="atualizarLeituristaAction.do" 
								tabindex="13" />
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

