<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="height=device-height, initial-scale=1.0">

<title>Cosanpa - Loja Virtual</title>

<link href="<bean:message key="caminho.portal.css"/>portal.css" rel="stylesheet">


<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="AguaParaActionForm"/>

</head>

<body>
	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	<%@ include file="/jsp/util/mensagens.jsp"%>
	<%-- 	<%@ include file="/jsp/portal/acesso-barra.jsp"%> --%>

<html:form action="/agua-para.do" name="AguaParaActionForm" enctype="multipart/form-data" type="gcom.gui.portal.AguaParaActionForm" method="post" onsubmit="return validateAguaParaActionForm(this);">

		<div class="page-wrap">
			<div class="container pagina">
				<div class="container container-breadcrumb">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href="portal.do">Página Inicial</a></li>
						<li class="breadcrumb-item active">Recadastramento Água Pará</li>
					</ul>
				</div>

				<div class="pagina-titulo">
					<h2>Recadastramento Água Pará</h2>
				</div>

				<div class="pagina-agua">
					<p>
						Informe os dados do <b>Cliente</b> e a <b>Matrícula</b> do imóvel, que se encontra na parte superior da sua conta de água:
					</p>

					<h3>Dados do Recadastramento:</h3>
					
					    <div>
						    <td>Nome:</td>
							<html:text property="nome" styleClass="form-control col-sm-5" />
						</div>
						
						<div>
						<td>CPF:</td>
							<html:text property="cpf" size="11" maxlength="11" onblur="ehUmCPF(event);" onkeypress="return isCampoNumerico(event);" styleClass="form-control col-sm-3" />							
						</div>
						
						<div>
						<td>Matrícula:</td>
							<html:text property="idImovel" size="9" maxlength="9" onkeypress="return isCampoNumerico(event);" styleClass="form-control col-sm-2" />													  					
						</div>											 
						 <div>
							<td>Telefone:</td>
							<html:text property="telefone" size="15" maxlength="15" onkeyup="handlePhone(event)" onkeypress="return isCampoNumerico(event);" styleClass="form-control col-sm-2" />
							</div>
						<div>
						<td>RG:</td>
							<html:text property="rg" size="8" maxlength="8" onkeypress="return isCampoNumerico(event);" styleClass="form-control col-sm-2" />						
						</div>
						<div>
						<td>NIS:</td>
							<html:text property="nis" size="12" maxlength="12" onblur="validarNIS(event);" onkeypress="return isCampoNumerico(event);" styleClass="form-control col-sm-2" />						
						</div>
						<br>
						<br>
					<tr>
						<td heigth="30"><strong>Arquivo Cpf:</strong></td>
						<td><input type="file" name="arquivoCpf" property="arquivoCpf" size="54" multiple /></td>
					</tr>
					<br>
					<tr>
						<td heigth="30"><strong>Arquivo RG:</strong></td>
						<td><input type="file" name="arquivoRg" property="arquivoRg" size="54" multiple /></td>
					</tr>
					<br>
					<tr>
						<td heigth="30"><strong>Arquivo Comprovante Conta Cosanpa:</strong></td>
						<td><input type="file" name="arquivoConta" property="arquivoConta" size="54" multiple /></td>
					</tr>
					<br>
					<tr>
						<td heigth="30"><strong>Arquivo Cartão bolsa família ou NIS:</strong></td>
						<td><input type="file" name="arquivoBolsaFamiliaNis" property="arquivoBolsaFamiliaNis" size="54" multiple /></td>
					</tr>
					<br>
					<td align="right">
						<input type="button" name="" value="Finalizar cadastro." class="bottonRightCol" 
						onclick="javascript:submeterCadastro(document.forms[0]);" url="cadastroAguaParaAction.do"/>
					</td>			
					<br> <span><b>OBS:</b> Verifique os dados antes de finalizar o cadastro.</span>
				</div>
			</div>
		</div>
	</html:form>

	<%@ include file="/jsp/portal/rodape.jsp"%>

	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-3.2.1.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-ui.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>popper.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>bootstrap.min.js"></script>
	<SCRIPT LANGUAGE="JavaScript">

function submeterCadastro(form){
	var form = document.forms[0];
  	form.action = "/gsan/cadastroAguaParaAction.do";
     		  		
			submeterFormPadrao(form);   		  
	      	
}

 function validarNIS(event) {
	event.preventDefault();
	var numeroNis = event.target.value;
	var arrayNis = numeroNis.split("");
	var peso1 = 3;
	var peso2 = 9;
	var soma = 0;
	
	for(var i = 0; i < 2; i++){
		soma += peso1 * arrayNis[i];
		peso1--;
	}
	
	for(var i = 2; i < 10; i++){
		soma += peso2 * arrayNis[i]
		peso2--;
	}
	
	var resultado = 11 - (soma % 11);
	
	if(resultado == 10 || resultado == 11) {
		resultado = 0;
	}
	
	if(!(resultado == arrayNis[10])) {
		alert("Número NIS Inválido. Por Favor, verificar os dígitos.")
	}
		
}

function ehUmCPF(event) {
    const cpf = event.target.value;
    if(validaNumerosRepetidos(cpf) || validaPrimeiroDigito(cpf) || validaSegundoDigito(cpf)) {
    	alert('CPF Inválido.')
    }
}
function validaNumerosRepetidos(cpf) {
    const numerosRepetidos = [
        '00000000000',
        '11111111111',
        '22222222222',
        '33333333333',
        '44444444444',
        '55555555555',
        '66666666666',
        '77777777777',
        '88888888888',
        '99999999999'
    ]
    return numerosRepetidos.includes(cpf)
}
function validaPrimeiroDigito(cpf) {
    let soma = 0;
    let multi = 10;
    for(let i = 0; i < 9; i++) {
        soma += cpf[i] * multi;
        multi--;
    }
    soma = (soma * 10) % 11;
    if(soma == 10 || soma == 11) {
        soma = 0;
    }
    return soma != cpf[9];
}
function validaSegundoDigito(cpf) {
    let soma = 0;
    let multi = 11;
    for(let i = 0; i < 10; i++) {
        soma += cpf[i] * multi;
        multi--;
    }
    soma = (soma * 10) % 11;
    if(soma == 10 || soma == 11) {
        soma = 0;
    }
    return soma != cpf[10];
}

function handlePhone (event) {
	  let input = event.target
	  input.value = phoneMask(input.value)
}

function phoneMask(value) {
	  if (!value) return ""
	  value = value.replace(/\D/g,'')
	  value = value.replace(/(\d{2})(\d)/,"($1) $2")
	  value = value.replace(/(\d)(\d{4})$/,"$1-$2")
	  return value
} 

</SCRIPT>
</body>
</html:html>