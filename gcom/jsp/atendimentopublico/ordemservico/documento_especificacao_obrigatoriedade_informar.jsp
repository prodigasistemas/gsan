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

<html:javascript staticJavascript="false"  formName="ExibirInformarSolicitacaoDocumentoObrigatorioActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
		
	function validarForm(){
		
		var form = document.forms[0];
		
		if(validateExibirInformarSolicitacaoDocumentoObrigatorioActionForm(form)){
			if(form.icValidarDocumentoClienteResponsavel[0].checked == true || form.icValidarDocumentoClienteResponsavel[1].checked == true){
				
				enviarSelectMultiplo('ExibirInformarSolicitacaoDocumentoObrigatorioActionForm','idsMeioSolicitacaoObrigatorio');
				submeterFormPadrao(form);			
			}else{
				if(form.idTipoSolicitacao.value == "-1"){
					alert("Informe o Tipo de Solicitação.");
				}
				
				if(!verificarListBoxSelecionado()){
					alert("Informe o Tipo de Especificação.");
				}
				
				if(form.icValidarDocumentoClienteResponsavel[0].checked == false && form.icValidarDocumentoClienteResponsavel[1].checked == false){
					alert("Informar Documento Solicitante Obrigatório.");
				}
			}
		}
	}
	
  	function limpar(){
  		var form = document.forms[0];
  	
  		form.idTipoSolicitacao.value = "-1";
  		form.idsTipoEspecificacao.selectedIndex = "-1";
  		form.icDocumentoSolicitanteObrigatorio.value = "";
  		form.icValidarDocumentoClienteResponsavel.value = "";
		
		MoverTodosDadosSelectMenu2PARAMenu1('ExibirInformarSolicitacaoDocumentoObrigatorioActionForm', 'idsMeioSolicitacaoNaoObrigatorio', 'idsMeioSolicitacaoObrigatorio');
  	}		
	
	function MoveSelectedDataFromMenu1TO2(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.meioSolicitacao.value != '-1') {
			MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);
		}
	}
	
	function MoveSelectedDataFromMenu2TO1(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.meioSolicitacao.value != '-1') {
			MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		}
	}
	
	function reload() {
		var form = document.forms[0];
		
		if (form.idsTipoEspecificacao == null || form.idsTipoEspecificacao.value == ''){
			
			form.icDocumentoSolicitanteObrigatorio.value = "";
			form.icDocumentoSolicitanteObrigatorio[0].checked = false;
			form.icDocumentoSolicitanteObrigatorio[1].checked = false;
		}
		
		if(form.idTipoSolicitacao.value != "-1" && verificarListBoxMarcado() ){
			
			form.action = "/gsan/exibirInformarSolicitacaoDocumentoObrigatorioAction.do";
			form.submit();
		}else if(form.idTipoSolicitacao.value != "-1" && form.idsTipoEspecificacao.value != "-1" 
			&& !verificarListBoxMarcado() ){
			
			form.action = "/gsan/exibirInformarSolicitacaoDocumentoObrigatorioAction.do?tipoEspecificacao=muitos";
			form.submit();
		}
	}
	
	function reloadIndicadorDocSolObrig() {
		var form = document.forms[0];
		
		if(form.idTipoSolicitacao.value != "-1" 
			&& verificarListBoxMarcado() ){
			
			form.action = "/gsan/exibirInformarSolicitacaoDocumentoObrigatorioAction.do?indicadorDoc=alterado";
			form.submit();
		}
	}
	
	function reloadTipoSolicitacao() {
	
		var form = document.forms[0];
		
		if (form.idsTipoEspecificacao == null || form.idsTipoEspecificacao.value == ''){
			
			form.icDocumentoSolicitanteObrigatorio.value = "";
			form.icDocumentoSolicitanteObrigatorio[0].checked = false;
			form.icDocumentoSolicitanteObrigatorio[1].checked = false;
		}

		form.action = "/gsan/exibirInformarSolicitacaoDocumentoObrigatorioAction.do?pesquisa=tipoEspecificacao";
		form.submit();
	}
	
	function verificarListBoxMarcadoPorNomeApenasUmElemento(){
		form = document.forms[0];
		cont = 0;
		retorno = false;
		
		if (form.idsTipoEspecificacao != null && form.idsTipoEspecificacao != 'undefined'){
		
			listBox = form.idsTipoEspecificacao;
		
			for ( i = 0; i < listBox.length ; i++){
        	
				if(listBox.options[i].selected == true){
					cont = cont + 1;
				}
   			}
		}

		if(cont <= 1){
			retorno = true;
		}

		return retorno;
	}
	
	function verificarListBoxMarcado(){
		form = document.forms[0];
		cont = 0;
		retorno = false;
		
		if (form.idsTipoEspecificacao != null && form.idsTipoEspecificacao != 'undefined'){
			
			listBox = form.idsTipoEspecificacao;
			
			for ( i = 0; i < listBox.length ; i++){
        	
				if(listBox.options[i].selected == true){
					retorno = true;
				}
   			}
		}
		
		if(retorno == true){
			
			if(verificarListBoxMarcadoPorNomeApenasUmElemento()){
				retorno == true;
			}else{
				retorno == false;
			}
		}

		return retorno;
	}
	
	function verificarListBoxSelecionado(){
		form = document.forms[0];
		cont = 0;
		retorno = false;
		
		if (form.idsTipoEspecificacao != null && form.idsTipoEspecificacao != 'undefined'){
			
			listBox = form.idsTipoEspecificacao;
			
			for ( i = 0; i < listBox.length ; i++){
        	
				if(listBox.options[i].selected == true){
					retorno = true;
				}
   			}
		}

		return retorno;
	}
	
	function limpaDocumentoSolicitante(){
		form = document.forms[0];
		
		form.icDocumentoSolicitanteObrigatorio.value = "";
		form.icDocumentoSolicitanteObrigatorio[0].checked = false;
		form.icDocumentoSolicitanteObrigatorio[1].checked = false;
	}
	
	function desabilitarCampos(){
		var form = document.forms[0];
		var boolean  = false;
		
		//bloqueia
		if(form.icDocumentoSolicitanteObrigatorio != null 
			&& form.icDocumentoSolicitanteObrigatorio[1].checked == true ){
			
			form.botao1.disabled = true;
			form.botao2.disabled = true;
			form.botao3.disabled = true;
			form.botao4.disabled = true;
			
		//desbloqueia
		}else{
			form.botao1.disabled = false;
			form.botao2.disabled = false;
			form.botao3.disabled = false;
			form.botao4.disabled = false;
		}
	}
		  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload ="javascript:desabilitarCampos();" >

<div id="formDiv"><html:form action="/informarSolicitacaoDocumentoObrigatorioAction.do"
	name="ExibirInformarSolicitacaoDocumentoObrigatorioActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ExibirInformarSolicitacaoDocumentoObrigatorioActionForm"
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
					<td class="parabg">Informar Obrigatoriedade de Documento por Especificação </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2"> &nbsp; </td>
				</tr>
                
                <tr>
					<td width="200"><strong>Tipo de Solicitação:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:select property="idTipoSolicitacao"
									 style="width: 230px;"
									 size="1" 
									 tabindex="1" 
									 onchange="javascript:desabilitarCampos();reloadTipoSolicitacao();" >
							
							<logic:notEmpty name="colecaoSolicitacaoTipo" scope="session">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoSolicitacaoTipo"
											  labelProperty="descricao" 
											  property="id" />
							</logic:notEmpty>

							<font size="1">&nbsp; </font>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td width="200"><strong>Tipo de Especificação: <font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:select property="idsTipoEspecificacao" 
									 multiple="mutiple" 
									 size="4" 
									 tabindex="2"
									 onchange="javascript:desabilitarCampos();limpaDocumentoSolicitante();reload();"
									 style="width: 390px;">
							 
							<logic:notEmpty name="colecaoSolicitacaoTipoEspecificacao">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoSolicitacaoTipoEspecificacao"
											  labelProperty="descricao" 
											  property="id" />
							</logic:notEmpty>
							
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td ><strong>Documento Solicitante Obrigatório:<font color="#FF0000">*</font></strong></td>
					<td style="width: 440px;">
						<html:radio property="icDocumentoSolicitanteObrigatorio" value="1" tabindex="3" onclick="reloadIndicadorDocSolObrig();" /> <strong>Sim</strong>
						<html:radio property="icDocumentoSolicitanteObrigatorio" value="2" tabindex="4" onclick="reloadIndicadorDocSolObrig();" /> <strong>Não</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Validar Documento do Cliente Responsável:<font color="#FF0000">*</font></strong></td>
					<td style="width: 440px;">
						<html:radio property="icValidarDocumentoClienteResponsavel" value="1" tabindex="5" onclick="reloadIndicadorDocSolObrig();" /> <strong>Sim</strong>
						<html:radio property="icValidarDocumentoClienteResponsavel" value="2" tabindex="6" onclick="reloadIndicadorDocSolObrig();" /> <strong>Não</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td width="120">
						<strong>Meio de Solicitação:</strong>
					</td>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" align="left">
						<tr>
							<td width="165">
								<div align="left"><strong>Documento não Obrigatório</strong></div>
									
									<html:select property="idsMeioSolicitacaoNaoObrigatorio" 
											 	 size="6" 
											 	 multiple="true"
											 	 style="width: 140px;" >
										<logic:notEmpty name="colecaoDocumentoNaoObrigatorio">
											<html:options collection="colecaoDocumentoNaoObrigatorio" 
														  labelProperty="descricao" 
														  property="id"/>
										</logic:notEmpty>
									</html:select>
							</td>
							
							
							<td width="5" valign="center"><br>
								<table width="50" align="center" border="0">
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol" 
												name="botao1"
												onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('ExibirInformarSolicitacaoDocumentoObrigatorioActionForm', 'idsMeioSolicitacaoNaoObrigatorio', 'idsMeioSolicitacaoObrigatorio');"
												value=" &gt;&gt; ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												name="botao2"
												onclick="javascript:MoverDadosSelectMenu1PARAMenu2('ExibirInformarSolicitacaoDocumentoObrigatorioActionForm', 'idsMeioSolicitacaoNaoObrigatorio', 'idsMeioSolicitacaoObrigatorio');"
												value=" &nbsp;&gt;  ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												name="botao3"
												onclick="javascript:MoverDadosSelectMenu2PARAMenu1('ExibirInformarSolicitacaoDocumentoObrigatorioActionForm', 'idsMeioSolicitacaoNaoObrigatorio', 'idsMeioSolicitacaoObrigatorio');"
												value=" &nbsp;&lt;  ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												name="botao4"
												onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('ExibirInformarSolicitacaoDocumentoObrigatorioActionForm', 'idsMeioSolicitacaoNaoObrigatorio', 'idsMeioSolicitacaoObrigatorio');"
												value=" &lt;&lt; ">
										</td>
									</tr>
								</table>
							</td>

							<td align="center">
								<div align="center"><strong>Documento Obrigatório</strong></div>
	
								<html:select property="idsMeioSolicitacaoObrigatorio" 
											 size="6" 
											 multiple="true"
											 style="width: 140px;" >
										<logic:notEmpty name="colecaoDocumentoObrigatorio">
											<html:options collection="colecaoDocumentoObrigatorio" 
												          labelProperty="descricao" 
														  property="id"/>
										</logic:notEmpty>
									
								</html:select>

							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
								          	
				<tr>
					<td align="left" >
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   value="Limpar" 
			          		   onClick="window.location.href='/gsan/exibirInformarSolicitacaoDocumentoObrigatorioAction.do?menu=sim'" />
			          		   
			          	<input type="button" 
							   name="ButtonCancelar" 
							   class="bottonRightCol" 
							   value="Cancelar"
							   onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
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
		
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
