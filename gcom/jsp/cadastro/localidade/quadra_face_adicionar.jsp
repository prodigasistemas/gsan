<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>


<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="AdicionarQuadraFaceActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--

function limpar(tipo){

	var form = document.forms[0];

	switch (tipo){
        case 4:
		   form.distritoOperacionalID.value = "";
		   form.distritoOperacionalDescricao.value = "";
           form.distritoOperacionalDescricao.style.color = "#000000";

		   //Coloca o foco no objeto selecionado
		   form.distritoOperacionalID.focus();
		   break;
		case 10:
		   form.distritoOperacionalDescricao.value = "";
           form.distritoOperacionalDescricao.style.color = "#000000";

		   //Coloca o foco no objeto selecionado
		   form.distritoOperacionalID.focus();
		   break;
		default:
          break;
	}
}

function limpar1(){
	var form = document.forms[0];
	alert('ok');
	form.grauDificuldadeExecucaoID.value = "-1"; 
	form.grauRiscoSegurancaFisicaID.value = "-1"; 
	form.nivelPressaoID.value = "-1"; 
	form.grauIntermitenciaID.value = "-1"; 
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'distrito') {
      form.distritoOperacionalID.value = codigoRegistro;
	  form.distritoOperacionalDescricao.value = descricaoRegistro;
	  form.distritoOperacionalDescricao.style.color = "#000000";
	}
}

function habilitacaoDistritoOperacional(indicadorRedeAguaAux){
	var form = document.forms[0];
	if (indicadorRedeAguaAux[0].checked){
		limpar(4);
		form.distritoOperacionalID.disabled = true;
		form.distritoOperacionalID.value = ""; 
	}else{
		form.distritoOperacionalID.disabled = false;
	}
}
   
function habilitacaoSistemaEsgotoBacia(indicadorRedeEsgotoAux){
	
	var form = document.forms[0];
	if (indicadorRedeEsgotoAux[0].checked){
		form.sistemaEsgotoID.disabled = true;
		form.sistemaEsgotoID.value = "-1"; 
		form.baciaID.disabled = true;
		form.baciaID.value = "-1"; 
		
		form.grauDificuldadeExecucaoID.value = "-1"; 
		form.grauRiscoSegurancaFisicaID.value = "-1"; 
		form.nivelPressaoID.value = "-1"; 
		form.grauIntermitenciaID.value = "-1"; 
	}else{
		form.sistemaEsgotoID.disabled = false;
		form.baciaID.disabled = false;
	}
}

//Verifica o valor do objeto radio em tempo de execução
function verificarMarcacao(valor, tipoIndicador){
	if(tipoIndicador == "Agua"){
		document.getElementById("indicadorRedeAguaHTML").value = valor;
	}
	else{
		document.getElementById("indicadorRedeEsgotoHTML").value = valor;
	}
}

function atualizarDadosIndicador(formulario){
	var objIndicadorRedeAgua = returnObject(formulario, "indicadorRedeAguaAux");
	var objIndicadorRedeEsgoto = returnObject(formulario, "indicadorRedeEsgotoAux");
	var valorIndicadorAgua = objIndicadorRedeAgua.value;
	var valorIndicadorEsgoto = objIndicadorRedeEsgoto.value;
	
	for(x=0; x < formulario.elements.length; x++){
		if(formulario.elements[x].type == "radio"){
			if(formulario.elements[x].name == "indicadorRedeEsgotoAux"){
				if (formulario.elements[x].checked){
					valorIndicadorEsgoto = formulario.elements[x].value;
				}
			}
			else{
				if (formulario.elements[x].checked){
					valorIndicadorAgua = formulario.elements[x].value;
				}
			}
		}
	}

	document.getElementById("indicadorRedeAguaHTML").value = valorIndicadorAgua;
	document.getElementById("indicadorRedeEsgotoHTML").value = valorIndicadorEsgoto;
}

function validarIndicadorRedeAguaAux(){

    var form = document.forms[0];
    
    var indice;
    var array = new Array(form.indicadorRedeAguaAux.length);
    var selecionado = "";
    var formulario = document.forms[0]; 
    for(indice = 0; indice < form.elements.length; indice++){
	   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorRedeAguaAux") {
	      selecionado = form.elements[indice].value;
	      indice = form.elements.length;
	   }
    }    
	if(selecionado == ''){
		alert('Informe Rede de Água.');
		indicadorRedeAguaAux.focus();
	}	
}

function validarCondicaoAbastecimento(){
	var form = document.forms[0];
	
	if(form.nivelPressaoID.value != '-1' && form.grauIntermitenciaID.value =='-1'){
		return true;
	}
	
	return false;
}

function validarIndicadorRedeEsgotoAux(){

    var form = document.forms[0];
    
    var indice;
    var array = new Array(form.indicadorRedeEsgotoAux.length);
    var selecionado = "";
    var formulario = document.forms[0]; 
    for(indice = 0; indice < form.elements.length; indice++){
	   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorRedeEsgotoAux") {
	      selecionado = form.elements[indice].value;
	      indice = form.elements.length;
	   }
    }    
	if(selecionado == ''){
		alert('Informe Rede de Esgoto.');
		indicadorRedeEsgotoAux.focus();
	}	
}

function validarForm(formulario){
	
	if (validateAdicionarQuadraFaceActionForm(formulario)){
	
		var objSistemaEsgotoID = returnObject(formulario, "sistemaEsgotoID");
		var objBaciaID = returnObject(formulario, "baciaID");
		var objDistritoOperacionalID = returnObject(formulario, "distritoOperacionalID");
	
		var objIndicadorAguaHTML = document.getElementById("indicadorRedeAguaHTML");
		var objIndicadorEsgotoHTML = document.getElementById("indicadorRedeEsgotoHTML");
		
		validarIndicadorRedeAguaAux();
		validarIndicadorRedeEsgotoAux();
		
		if(validarCondicaoAbastecimento()){
			alert('Informe o Grau de Intermitência.');
		}
		//com rede ou parcial
		else if(document.forms[0].indicadorRedeEsgotoAux[1].checked || document.forms[0].indicadorRedeEsgotoAux[2].checked){
			
			if(objSistemaEsgotoID.options[objSistemaEsgotoID.options.selectedIndex].value == "-1"){
				alert("Informe Sistema de Esgoto.");
				objSistemaEsgotoID.focus();
			}
			else if (objBaciaID.length == "1"){
				alert("Sistema de Esgoto selecionado não contém Bacia.");
				objSistemaEsgotoID.focus();
			}
			else if (objBaciaID.options[objBaciaID.options.selectedIndex].value == "-1") {
				alert("Informe Bacia.");
				objSistemaEsgotoID.focus();
			}
			//com rede ou parcial
			else if(document.forms[0].indicadorRedeAguaAux[1].checked || document.forms[0].indicadorRedeAguaAux[2].checked){
				if (objDistritoOperacionalID.value.length < 1) {
					alert("Informe Distrito Operacional.");
					objDistritoOperacionalID.focus();
				}
				else if(objDistritoOperacionalID.value.length > 0 && !testarCampoValorZero(objDistritoOperacionalID, "Distrito Operacional")){
					objDistritoOperacionalID.focus();
				}
				else if (validateAdicionarQuadraFaceActionForm(formulario)){
					
					formulario.sistemaEsgotoID.disabled = false;
					formulario.baciaID.disabled = false;
					formulario.distritoOperacionalID.disabled = false;
					
					formulario.action = "/gsan/adicionarQuadraFaceAction.do";
					submeterFormPadrao(formulario);
				}
			}
			else if(objDistritoOperacionalID.value.length > 0 && !testarCampoValorZero(objDistritoOperacionalID, "Distrito Operacional")){
				objDistritoOperacionalID.focus();
			}
			else if (validateAdicionarQuadraFaceActionForm(formulario)){
				
				formulario.sistemaEsgotoID.disabled = false;
				formulario.baciaID.disabled = false;
				formulario.distritoOperacionalID.disabled = false;
					
				formulario.action = "/gsan/adicionarQuadraFaceAction.do";
				submeterFormPadrao(formulario);
			}
		}
		//com rede ou parcial
		else if(document.forms[0].indicadorRedeAguaAux[1].checked || document.forms[0].indicadorRedeAguaAux[2].checked){
		
			if (objDistritoOperacionalID.value.length < 1) {
				alert("Informe Distrito Operacional.");
				objDistritoOperacionalID.focus();
			}
			else if(objDistritoOperacionalID.value.length > 0 && !testarCampoValorZero(objDistritoOperacionalID, "Distrito Operacional")){
				objDistritoOperacionalID.focus();
			}
			else if (validateAdicionarQuadraFaceActionForm(formulario)){
				
				formulario.sistemaEsgotoID.disabled = false;
				formulario.baciaID.disabled = false;
				formulario.distritoOperacionalID.disabled = false;
					
				formulario.action = "/gsan/adicionarQuadraFaceAction.do";
				submeterFormPadrao(formulario);
			}
		}
		else if(objDistritoOperacionalID.value.length > 0 && !testarCampoValorZero(objDistritoOperacionalID, "Distrito Operacional")){
			objDistritoOperacionalID.focus();
		}
		else if (validateAdicionarQuadraFaceActionForm(formulario)){
			
			formulario.sistemaEsgotoID.disabled = false;
			formulario.baciaID.disabled = false;
			formulario.distritoOperacionalID.disabled = false;
					
			formulario.action = "/gsan/adicionarQuadraFaceAction.do";
			submeterFormPadrao(formulario);
		}
	}
}

//-->
</SCRIPT>

</head>

<logic:present name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('${sessionScope.telaRetorno}'); window.close();">
</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(630, 560); atualizarDadosIndicador(document.forms[0]); setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>


<html:form action="/adicionarQuadraFaceAction" method="post">

<INPUT TYPE="hidden" NAME="indicadorRedeAguaHTML" ID="indicadorRedeAguaHTML">

<INPUT TYPE="hidden" NAME="indicadorRedeEsgotoHTML" ID="indicadorRedeEsgotoHTML">

<table width="600" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="590" valign="top" class="centercoltext"> 
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Informar Face da Quadra</td>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table> 
      <p>&nbsp;</p>

      <table width="100%" border="0">
        <tr>
          <td colspan="4">Informe os dados abaixo para adicionar uma face para quadra:</td>
          <td align="right"></td>
        </tr>
        </table>
      <table width="100%" border="0">
		<tr>
			<td colspan="3" height="5"></td>
		</tr>
		<tr>
			<td><strong>Número:<font color="#FF0000">*</font></strong></td>
			<td>
			
				<logic:equal name="AdicionarQuadraFaceActionForm" property="acao" value="inserir">
					<html:text property="numeroFace" size="5" tabindex="1" maxlength="3" />
				</logic:equal>
				
				<logic:equal name="AdicionarQuadraFaceActionForm" property="acao" value="atualizar">
					<bean:write name="AdicionarQuadraFaceActionForm" property="numeroFace"/>
					<html:hidden property="numeroFace" />
				</logic:equal>
			
			</td>
		</tr>
		<tr>
			<td><strong>Rede de Água:</strong></td>
			<td>
				<html:radio property="indicadorRedeAguaAux" value="1" tabindex="2"
				onclick="verificarMarcacao(1, 'Agua');habilitacaoDistritoOperacional(document.forms[0].indicadorRedeAguaAux);" /><strong>
				Sem Rede de Água 
											
				<html:radio property="indicadorRedeAguaAux" value="2" tabindex="3"
				onclick="verificarMarcacao(2, 'Agua');habilitacaoDistritoOperacional(document.forms[0].indicadorRedeAguaAux);" />
				Com Rede de Água 
											
				<html:radio property="indicadorRedeAguaAux" value="3" tabindex="4"
				onclick="verificarMarcacao(3, 'Agua');habilitacaoDistritoOperacional(document.forms[0].indicadorRedeAguaAux);" />
				Rede de Água Parcial</strong>
			</td>
		</tr>
		<tr>
			<td><strong>Rede de Esgoto:</strong></td>
			<td>
				<html:radio property="indicadorRedeEsgotoAux" value="1" tabindex="5"
				onclick="verificarMarcacao(1, 'Esgoto');habilitacaoSistemaEsgotoBacia(document.forms[0].indicadorRedeEsgotoAux);"/><strong>
				Sem Rede de Esgoto 
											
				<html:radio property="indicadorRedeEsgotoAux" value="2" tabindex="6"
				onclick="verificarMarcacao(2, 'Esgoto');habilitacaoSistemaEsgotoBacia(document.forms[0].indicadorRedeEsgotoAux);" />
				Com Rede de Esgoto 
											
				<html:radio property="indicadorRedeEsgotoAux" value="3" tabindex="7"
				onclick="verificarMarcacao(3, 'Esgoto');habilitacaoSistemaEsgotoBacia(document.forms[0].indicadorRedeEsgotoAux);" />
				Rede de Esgoto Parcial</strong>
			</td>
		</tr>
				
		<!-- Autor: Hugo Leonardo 27/04/2010 -->	
		<!-- Grau Dificuldade de Execução  -->		
		<tr>
			<td><strong>Grau Dificuldade de Execução:</strong></td>
			<td>
				<html:select property="grauDificuldadeExecucaoID" style="width: 200px;" tabindex="8">
											
					<html:option 
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>
					
					<logic:present name="colecaoGrauDificuldadeExecucao" scope="session">
						<html:options collection="colecaoGrauDificuldadeExecucao"
									  labelProperty="descricao" 
									  property="id" />
					</logic:present>
				</html:select>
			</td>
		</tr>		
				
		<!-- Grau de Risco Segurança Física -->		
		<tr>
			<td><strong>Grau de Risco Segurança Física:</strong></td>
			<td>
				<html:select property="grauRiscoSegurancaFisicaID" style="width: 200px;" tabindex="9">
											
					<html:option 
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>
					
					<logic:present name="colecaoGrauRiscoSegurancaFisica" scope="session">
						<html:options collection="colecaoGrauRiscoSegurancaFisica"
									  labelProperty="descricao" 
									  property="id" />
					</logic:present>
					
											
				</html:select>
			</td>
		</tr>

		<tr>
			<th colspan="3" align="left"><strong>Condição de Abastecimento de Água:</strong></th>
		</tr>
		  
	  	<!-- Nível de Pressão -->		
		<tr>
			<td align="right"><strong>Nível de Pressão:</strong></td>
			<td>
				<html:select property="nivelPressaoID" style="width: 200px;" tabindex="10">
											
					<html:option 
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>
					
					<logic:present name="colecaoNivelPressao" scope="session">
						<html:options collection="colecaoNivelPressao"
									  labelProperty="descricao" 
									  property="id" />
					</logic:present>
					
											
				</html:select>
			</td>
		</tr>
		
		<!-- Grau de Grau de Intermitência -->		
		<tr>
			<td align="right"><strong>Grau de Intermitência:</strong></td>
			<td>
				<html:select property="grauIntermitenciaID" style="width: 200px;" tabindex="10">
											
					<html:option 
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>
					
					<logic:present name="colecaoGrauIntermitencia" scope="session">
						<html:options collection="colecaoGrauIntermitencia"
									  labelProperty="descricao" 
									  property="id" />
					</logic:present>
					
											
				</html:select>
			</td>
		</tr>
								
		<tr>
			<td><strong>Sistema Esgoto:</strong></td>
			<td>
				<html:select property="sistemaEsgotoID" style="width: 200px;" tabindex="11"
				onchange="redirecionarSubmit('exibirAdicionarQuadraFaceAction.do?objetoConsulta=7');">
											
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<html:options collection="colecaoSistemaEsgoto" labelProperty="descricaoAbreviada" property="id" />
											
				</html:select>
			</td>
		</tr>
		<tr>
			<td><strong>Bacia:</strong></td>
			<td>
				<html:select property="baciaID" style="width: 200px;" tabindex="12">
											
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
												
					<logic:present name="colecaoBacia">
						<html:options collection="colecaoBacia" labelProperty="descricao" property="id" />
					</logic:present>
												
				</html:select>
			</td>
		</tr>
		<tr>
			<td><strong>Distrito Operacional:</strong></td>
			<td>
				<html:text property="distritoOperacionalID" size="5"
				maxlength="3" tabindex="13" onkeypress="validaEnterComMensagem(event, 'exibirAdicionarQuadraFaceAction.do?objetoConsulta=5', 'distritoOperacionalID','Distrito Operacional');" onkeyup="limpar(10);" /> 
				
				<a href="javascript:redirecionarSubmit('exibirAdicionarQuadraFaceAction.do?pesquisarDistrito=OK');">
				<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Distrito Operacional" /></a> 
											
				<logic:present name="corDistritoOperacional">
					
					<logic:equal name="corDistritoOperacional" value="exception">
						
						<html:text property="distritoOperacionalDescricao" size="45" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000" />

					</logic:equal>
						
					<logic:notEqual name="corDistritoOperacional" value="exception">
						
						<html:text property="distritoOperacionalDescricao" size="45" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />

					</logic:notEqual>
					
				</logic:present> 
											
				<logic:notPresent name="corDistritoOperacional">
					
					<logic:empty name="AdicionarQuadraFaceActionForm" property="distritoOperacionalID">
													
						<html:text property="distritoOperacionalDescricao" value="" size="45" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000" />
					
					</logic:empty>
					
					<logic:notEmpty name="AdicionarQuadraFaceActionForm" property="distritoOperacionalID">
													
						<html:text property="distritoOperacionalDescricao" size="45" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />
						
					</logic:notEmpty>
					
				</logic:notPresent> 
											
				<a href="javascript:limpar(4);"><img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /></a>
										
			</td>
		</tr>
        <tr>
          <td colspan="4" height="5"></td>
        </tr>
        <tr>
          <td height="30" colspan="4"> 
          	<div align="right">
              
              <logic:equal name="AdicionarQuadraFaceActionForm" property="acao" value="inserir">
              	<input type="button" tabindex="14" onclick="validarForm(document.forms[0]); " class="bottonRightCol" value="Adicionar">&nbsp;
              </logic:equal>
              
              <logic:equal name="AdicionarQuadraFaceActionForm" property="acao" value="atualizar">
              	<input type="button" tabindex="14" onclick="validarForm(document.forms[0]); " class="bottonRightCol" value="Atualizar">&nbsp;
              </logic:equal>
              
              <input type="button" tabindex="15" onclick="window.close();" class="bottonRightCol" value="Fechar" style="width: 70px;">
			</div>
		  </td>
        </tr>
	  </table>
      
      <p>&nbsp;</p>
      </td>
  </tr>
</table>

</html:form>

<script language="JavaScript">
	
		<!--
		habilitacaoDistritoOperacional(document.forms[0].indicadorRedeAguaAux);
		habilitacaoSistemaEsgotoBacia(document.forms[0].indicadorRedeEsgotoAux);
		//-->

	</script>

</body>
</html:html>
