<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
 <%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarProgramacaoAbastecimentoManutencaoActionForm" />

<%@ page import="gcom.util.ConstantesSistema"%>

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<%-- Novos Javascripts --%>

<script language="JavaScript">

	/* Valida Form */
	function validaForm() {
		var form = document.forms[0];
		
		if(	validaCampos() &&
			validateActionForm(form) ){
			
    		form.action='exibirCopiarProgramacaoAbastecimentoManutencaoAction.do?tipoOperacao=C';
	    	form.submit();
		}
	}

	function validaCampos () {

		var form = document.forms[0];
		
		var msg = '';

		if(form.municipioCopiar != null && form.municipioCopiar.value  == '' ) {
			msg = 'Informe Município.\n';
		}
		
		if( form.bairroCopiar != null && form.bairroCopiar.value  == '' ) {
			msg = msg + 'Informe Bairro.\n';
		}

		if( form.areaBairroCopiar.selectedIndex == 0) {
			msg = msg + 'Informe Área do Bairro.\n';
		}
		
		if( msg != '' ){
			alert(msg);
			return false;
		}else {
			return true;
		}
	}

   	/* Fecha Popup */
   	function fechar() {
   		window.close();
   	}
   	
	function limparMunicipio(){
   		var form = document.forms[0];
		
		form.municipioCopiar.value = "";
		form.nomeMunicipioCopiar.value = "";

		limparBairro();
	}

	function limparBairro(){
   		var form = document.forms[0];
		
		form.bairroCopiar.value = "";
		form.nomeBairroCopiar.value = "";
		form.areaBairroCopiar.selectedIndex = 0;
	}   	
 	
    function validateActionForm(form) {

		if(validateCaracterEspecial(form)){
			if(validateLong(form)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
   	}
   	
	function caracteresespeciais () {
    	this.aa = new Array("municipioCopiar", "Município possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ab = new Array("bairroCopiar", "Bairro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
  	}

    function IntegerValidations () {
     	this.ac = new Array("municipioCopiar", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     	this.ad = new Array("bairroCopiar", "Bairro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
   	
	function pesquisarBairro(){
  		var form = document.forms[0];
  		municipio = form.municipioCopiar.value;
		
		if( municipio.length < 1 || isNaN(municipio) || ((municipio * 1) == 0) ||
			municipio.indexOf(',') != -1 || municipio.indexOf('.') != -1 || ((municipio * 1) == 0)){
     		
     		alert('Informe Município');
		}else{
			caminho = 'exibirPesquisarBairroAction.do?caminhoRetornoTelaPesquisaBairro=exibirCopiarProgramacaoAbastecimentoManutencaoAction';
			redirecionarSubmit(caminho+'&tipo=imovel&idMunicipio='+municipio);
		}
	}
	
</script>
</head>

<logic:notPresent name="fechaPopup" scope="request">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(600, 550)";>
</logic:notPresent>

<logic:present name="fechaPopup" scope="request">
	<body leftmargin="0" topmargin="0" onload="chamarReload('exibirInformarProgramacaoAbastecimentoManutencaoAction.do?tipoOperacao=C');window.close()">
</logic:present>


<html:form action="/exibirCopiarProgramacaoAbastecimentoManutencaoAction.do"
	name="InformarProgramacaoAbastecimentoManutencaoActionForm"
	type="gcom.gui.operacional.abastecimento.InformarProgramacaoAbastecimentoManutencaoActionForm"
	method="post">

	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr> 
	    	<td width="560" valign="top" class="centercoltext">
	    		<table height="100%">
	        		<tr> 
	          			<td></td>
	        		</tr>
	      		</table>
	      		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        		<tr> 
	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
	          			</td>

       					<td class="parabg">Copiar Programa&ccedil;&atilde;o de Abastecimento</td>

	          			<td width="11">
	          				<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
	          			</td>
	        		</tr>
	      		</table>
	      		<p>&nbsp;</p>
	      		<table width="100%" border="0">
					<tr>
						<td><strong>Mês e Ano de Referência:</strong></td>

						<td>
							<html:text property="mesAnoReferencia" 
								size="7"
								maxlength="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0;"/>
						</td>
					</tr>
				  	
				  	<tr> 
           				<td colspan="4"><hr></td>
				  	</tr>
					
					<tr>
						<td><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>
						<td colspan="3">
							<strong> 
							<html:text maxlength="4"
								property="municipioCopiar" 
								size="4"
								onkeypress="javascript:limparBairro();validaEnter(event, 'exibirCopiarProgramacaoAbastecimentoManutencaoAction.do?objetoConsulta=1', 'municipioCopiar');" />
							
							<a href="javascript:redirecionarSubmit('exibirPesquisarMunicipioAction.do?caminhoRetornoTelaPesquisaMunicipio=exibirCopiarProgramacaoAbastecimentoManutencaoAction');">
								<img width="23" 
									height="21"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									border="0"
									alt="Pesquisar" /></a>
							
							<logic:present name="municipioEncontrado" scope="request">
								<html:text property="nomeMunicipioCopiar" 
									size="40" 
									maxlength="30"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 
							
							<logic:notPresent name="municipioEncontrado" scope="request">
								<html:text property="nomeMunicipioCopiar" 
									size="40" 
									maxlength="30"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:notPresent> 
							
							<a href="javascript:limparMunicipio();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a> 
							</strong>
						</td>
					</tr>
					
	
					<tr>
						<td><strong>Bairro:<font color="#FF0000">*</font></strong></td>
						<td colspan="3">
							<strong> 
								<html:text maxlength="4" 
									property="bairroCopiar"
									size="4"
									onkeypress="javascript:return validaEnterDependencia(event, 'exibirCopiarProgramacaoAbastecimentoManutencaoAction.do?objetoConsulta=2', this, document.forms[0].municipioCopiar.value,'Municipio');" />
								
								<a href="javascript:pesquisarBairro();">
									<img width="23" 
										height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0"
										alt="Pesquisar" /></a>
									
								<logic:present name="bairroEncontrado" scope="request">
									<html:text property="nomeBairroCopiar" 
										size="40" 
										maxlength="30"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present> 
								
								<logic:notPresent name="bairroEncontrado" scope="request">
									<html:text property="nomeBairroCopiar" 
										size="40" 
										maxlength="30"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:notPresent> 
								<a href="javascript:limparBairro();"> 
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" 
										title="Apagar" /></a> 
							</strong>
						</td>
					</tr>
	
					<tr>
						<td>
							<strong>Área de Bairro:</strong><font color="#FF0000">*</font>
						</td>

						<td colspan="2">
							<strong> 
							<html:select property="areaBairroCopiar" style="width: 230px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
						
								<logic:present name="colecaoAreaBairroCopiar" scope="request">
									<html:options collection="colecaoAreaBairroCopiar"
										labelProperty="nome" 
										property="id" />
								</logic:present>
							</html:select> 														
							</strong>
						</td>
						
					</tr>

	        		<tr> 
	          			<td height="27" colspan="5"> 
	          				<div align="right"> 

	              				<input name="Button2" 
	              					type="button" 
	              					class="bottonRightCol" 
	              					value="Fechar" 
	              					onClick="javascript:fechar();">

	              				<input name="Button" 
	              					type="button" 
	              					class="bottonRightCol" 
	              					value="Copiar" 
	              					onclick="javascript :validaForm();">
	              				
	            			</div>
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