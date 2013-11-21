<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarCertidaoNegativaActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'imovel') {      		
	      form.idImovel.value = codigoRegistro;
		  form.action = 'exibirGerarCertidaoNegativaAction.do?objetoConsulta=1';
		  form.submit();
		}
	}
	
	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarCertidaoNegativaActionForm(form) && 
			validarCampos() ){
			
			submeterFormPadrao(form);
		}
	}
	
	function validarCampos(){
		
		var form = document.forms[0];
		
		if ( form.idImovel.value == null || form.idImovel.value == "" ){
			alert("Informe o imovel cuja certidao negativa será gerada.");
			return false;		
		}		
		
		return true;
	}
	
	function limparForm(){
	
		var form = document.forms[0];
		
		form.idImovel.value = "";
		form.matriculaImovel.value = "";
		form.nomeClienteUsuario.value = "";
		form.cpfCnpj.value = "";
		form.enderecoImovel.value = "";

	}
	
	function limpar(){
		var form = document.forms[0];
		
		form.idImovel.value = "";	
		form.matriculaImovel.value = "";
		form.nomeClienteUsuario.value = "";
		form.cpfCnpj.value = "";
		form.enderecoImovel.value = "";
	}
	
	
  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.mesAnoFaturamento}');">

<html:form action="/gerarCertidaoNegativaAction.do"
	name="GerarCertidaoNegativaActionForm"
	type="gcom.gui.atendimentopublico.GerarCertidaoNegativaActionForm"
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
					<td class="parabg">Gerar Certid&atilde;o Negativa </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar a Certid&atilde;o Negativa, informe os dados abaixo:</td>
				</tr>
			
				<tr>
					<td bordercolor="#000000"><strong>Matr&iacute;cula do Im&oacute;vel:<font
						color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text
							property="idImovel" maxlength="9" size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarCertidaoNegativaAction.do?objetoConsulta=1','idImovel','Localidade Imovel');return isCampoNumerico(event);"/>
						<a
							href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
						<img width="23" height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0"  title="Pesquisar Imovel"></a> 
							
						<logic:present name="idImovelNaoEncontrado" scope="request">
							<html:text property="matriculaImovel" size="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
		
						</logic:present> 
						
						<logic:notPresent name="idImovelNaoEncontrado" scope="request">
							<logic:present name="matriculaImovel"
								scope="request">
								<html:text property="matriculaImovel" size="30"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present>
							
							<logic:notPresent name="matriculaImovel" scope="request">
								<html:text property="matriculaImovel" size="30"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent>
						</logic:notPresent> 
						
						<a href="javascript:limpar();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
						</a>
					</td>	
				</tr>
				<tr>
					<td>
						<strong>Cliente Usu&aacute;rio: </strong>					
					</td>
					<td>
						<strong>
							<html:text property="nomeClienteUsuario"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="40" maxlength="40" />
						</strong>					
					</td>										
				</tr>
				
				<tr>
					<td>
						<strong>CPF ou CNPJ: </strong>					
					</td>
					<td>
						<strong>
							<html:text property="cpfCnpj"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="40" maxlength="40" />
						</strong>					
					</td>					
				</tr>
				
				<tr>
					<td align="center" colspan="2">
						<strong>Endere&ccedil;o do Im&oacute;vel : </strong>					
					</td>
				</tr>
				
				<tr>
					<td colspan="2">					
						<html:text property="enderecoImovel"
							readonly="true"								
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="80" maxlength="80" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
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
			          		onclick="javascript:limparForm();"/>
			          		
			          		<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">				
			          		
					</td>
				
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar Certidão" 
							onClick="javascript:validarForm()" />
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
