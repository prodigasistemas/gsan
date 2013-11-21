<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.cadastro.localidade.GerenciaRegional" %>
<%@ page import="gcom.cadastro.localidade.UnidadeNegocio" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InformarDadosGeracaoResumoAcaoConsultaEventualActionForm"
	dynamicJavascript="false" />
<SCRIPT LANGUAGE="JavaScript">
<!--

     var bCancel = false; 

    function validateInformarDadosGeracaoResumoAcaoConsultaEventualActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateLong(form) && validateDate(form); 
   } 

    function caracteresespeciais () { 
     this.ab = new Array("eloPolo", "Elo Pólo possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("localidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("setorComercial", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("quadra", "Quadra possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("eloPolo", "Elo Pólo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("localidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("setorComercial", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("quadra", "Quadra deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 
    
    function DateValidations () { 
	this.aa = new Array("dataInicialEmissao", "Data Inicial do Período de Emissão inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ab = new Array("dataFinalEmissao", "Data Final do Período de Emissão inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }



function validarForm(form){
	if (validateInformarDadosGeracaoResumoAcaoConsultaEventualActionForm(form) && verificaDataInicialMaiorQueFinal(form)){
		submeterFormPadrao(form);
	}
						
}

function verificaDataInicialMaiorQueFinal(form){

 if(comparaData(form.dataInicialEmissao.value, ">", form.dataFinalEmissao.value )){
  alert('Data Final do Período Emissão Anterior à Data Inicial.'); 
  return false;
 }else{
  return true;
 }

}




function limparEloPolo(){
	var form = document.forms[0];
	
	form.eloPolo.disabled = false;
	
	form.eloPolo.value = "";
	form.descricaoEloPolo.value = "";
}

function limparLocalidade(){
	var form = document.forms[0];
	
	form.localidade.disabled = false;
	
	form.localidade.value = "";
	form.descricaoLocalidade.value = "";
	
}

function limparSetorComercial(){
	var form = document.forms[0];
	
	form.setorComercial.disabled = false;
	
	form.setorComercial.value = "";
	form.idSetorComercial.value = "";
	form.descricaoSetorComercial.value = "";
	
}

function limparQuadra(){
	var form = document.forms[0];
	
	form.quadra.disabled = false;
	
	form.quadra.value = "";
	var msgQuadra = document.getElementById("msgQuadra");
	
	if (msgQuadra != null){
		msgQuadra.innerHTML = "";
	}
	
}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
    
    if (tipoConsulta == 'cobrancaAcaoAtividadeComando') {
      form.idCobrancaAcaoAtividadeComando.value = codigoRegistro;
      form.tituloCobrancaAcaoAtividadeComando.value = descricaoRegistro;
    }

	if (tipoConsulta == 'elo') {
    	limparEloPolo();
      	form.eloPolo.value = codigoRegistro;
      	form.descricaoEloPolo.value = descricaoRegistro;
      	form.descricaoEloPolo.style.color = "#000000";
    
    	if (form.localidade.disabled){
	  		setarFoco('perfilImovel');
	  	}
	  	else{
	  		form.localidade.focus();
	  	}
    }
    
    if (tipoConsulta == 'localidade') {
    	limparLocalidade();
      	form.localidade.value = codigoRegistro;
      	form.descricaoLocalidade.value = descricaoRegistro;
      	form.descricaoLocalidade.style.color = "#000000";
    
    	if (form.setorComercial.disabled){
	  		setarFoco('perfilImovel');
	  	}
	  	else{
	  		form.setorComercial.focus();
	  	}
    }

    if (tipoConsulta == 'setorComercial') {
      	limparSetorComercial();
      	form.setorComercial.value = codigoRegistro;
      	form.descricaoSetorComercial.value = descricaoRegistro;
      	form.descricaoSetorComercial.style.color = "#000000";
    
    	if (form.quadra.disabled){
	  		setarFoco('perfilImovel');
	  	}
	  	else{
	  		form.quadra.focus();
	  	}
    }
    
    if (tipoConsulta == 'quadra') {
      	limparQuadra();
      	form.quadra.value = codigoRegistro;
      	form.descricaoQuadra.value = descricaoRegistro;
      	form.descricaoQuadra.style.color = "#000000";
    
    	setarFoco('perfilImovel');
    }

}

function gerenciadorHabilitacaoImagemPesquisa(tipoPesquisa){

	switch (tipoPesquisa) { 
    case "EloPolo":
    
    	 abrirPopup('exibirPesquisarEloAction.do', 320, 810);
		 
        
       break; 
    case "Localidade": 
        
         abrirPopup('exibirPesquisarLocalidadeAction.do', 320, 810);
		
       break; 
    case "SetorComercial": 
        
        	abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidade.value+'&tipo=SetorComercial',document.forms[0].localidade.value,'Localidade', 400, 800);
			limparQuadra();
		
		
       break;
    case "Quadra": 
        	abrirPopupDependencia('exibirPesquisarQuadraAction.do?idSetorComercial='+document.forms[0].idSetorComercial.value+'&tipo=Quadra', document.forms[0].setorComercial.value, 'Setor Comercial',400, 800);
			
       break;
    default:
    
    }
}


function gerenciadorHabilitacaoImagemLimpar(tipoPesquisa){

	switch (tipoPesquisa) { 
    case "EloPolo":
    
    		limparEloPolo();
		 
        
       break; 
    case "Localidade": 
        
        	limparLocalidade();
		
		
       break; 
    case "SetorComercial": 
        
        	limparSetorComercial();
		
		
       break;
    case "Quadra": 
        
        	limparQuadra();
		
		
       break;
    default:
    
    }
}

function limparTitulo(){
var form = document.forms[0];
form.idCobrancaAcaoAtividadeComando.value = '';
form.tituloCobrancaAcaoAtividadeComando.value = '';
}





//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}'); ">

<html:form action="/informarDadosGeracaoResumoAcaoConsultaEventualAction" 
    name="InformarDadosGeracaoResumoAcaoConsultaEventualActionForm"
	type="gcom.gui.gerencial.cobranca.InformarDadosGeracaoResumoAcaoConsultaEventualActionForm"
	method="post">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="150" valign="top" class="leftcoltext">
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
          <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif"/></td>
          <td class="parabg">Informação de Dados para Geração de Resumo Ação Consulta</td>
          <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="2">Para gerar a consulta de resumo ação, informe os dados abaixo:</td>
      </tr>
	  <tr>
      	<td HEIGHT="30"><strong>Período de Emissão:</strong></td>
        <td>
			<html:text property="dataInicialEmissao" size="11" maxlength="10" tabindex="1" onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dataFinalEmissao,document.forms[0].dataInicialEmissao);"/> 
			<a
						href="javascript:abrirCalendario('InformarDadosGeracaoResumoAcaoConsultaEventualActionForm', 'dataInicialEmissao')" onblur="replicarCampo(document.forms[0].dataFinalEmissao,document.forms[0].dataInicialEmissao);">
            <img border="0"
						src="/gsan/imagens/calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a><strong>
					a</strong>
					
			<html:text property="dataFinalEmissao" size="11" maxlength="10" tabindex="2" onkeyup="mascaraData(this, event);"/>
			<a
						href="javascript:abrirCalendario('InformarDadosGeracaoResumoAcaoConsultaEventualActionForm', 'dataFinalEmissao')">
            <img border="0"
						src="/gsan/imagens/calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
		</td>
		
      </tr>
       <tr>
      	<td HEIGHT="30"><strong>Título do Comando:</strong></td>
        <td>
           <html:hidden property="idCobrancaAcaoAtividadeComando"/>
        	<html:text property="tituloCobrancaAcaoAtividadeComando" size="55" maxlength="55" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000"/>
			<a href="javascript:abrirPopup('exibirPesquisarComandoAcaoCobrancaAction.do', 400, 750);" title="Pesquisar"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a><a href="javascript:limparTitulo();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>

	    </td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Grupo de Cobraça:</strong></td>
        <td>
			<html:select property="grupoCobranca" style="width: 200px;height: 100px" tabindex="15" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoGrupoCobranca" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Gerência Regional:</strong></td>
        <td>
        
        <html:select property="gerencialRegional" style="width: 200px;height: 100px" tabindex="15" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<logic:iterate name="colecaoGerenciaRegional" id="gerenciaRegional" type="GerenciaRegional">
					<html:option value="<%=""+ gerenciaRegional.getId()%>">
					<%= gerenciaRegional.getNomeAbreviado() + " - " + gerenciaRegional.getNome()%></html:option>
				</logic:iterate>
		</html:select>
		</td>
      </tr>
      
        <tr>
               <td><strong> <font color="#FF0000"></font></strong><strong> <strong>Unidade de Neg&oacute;cio: </strong></strong></td>
               <td>
           	   	  <logic:present name="collUnidadeNegocio">  
                	   <html:select property="unidadeNegocio" tabindex="7" multiple="true" size="4">
			    <html:option value="">&nbsp;</html:option>
				<logic:iterate name="collUnidadeNegocio" id="unidadeNegocio" type="UnidadeNegocio" >
					 <html:option value="<%=""+ unidadeNegocio.getId()%>">
		              <%= unidadeNegocio.getNomeAbreviado() + " - " + unidadeNegocio.getNome()%></html:option>
				 </logic:iterate>
				</html:select>
             	</logic:present>
		  </td>
      </tr>
      
      <tr>
      	<td width="183" HEIGHT="30"><strong>Localidade Pólo:</strong></td>
        <td width="432">
        	
        	<html:text property="eloPolo" size="4" maxlength="3" tabindex="5" onkeypress="validaEnterComMensagem(event, 'exibirInformarDadosGeracaoResumoAcaoConsultaEventualAction.do?pesquisarEloPolo=OK', 'eloPolo', 'Elo Pólo');"/>
			<a href="javascript:gerenciadorHabilitacaoImagemPesquisa('EloPolo')" title="Pesquisar"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>

			<logic:present name="corEloPolo">

				<logic:equal name="corEloPolo" value="exception">
					<html:text property="descricaoEloPolo" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corEloPolo" value="exception">
					<html:text property="descricaoEloPolo" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corEloPolo">

				<logic:empty name="InformarDadosGeracaoResumoAcaoConsultaEventualActionForm" property="eloPolo">
					<html:text property="descricaoEloPolo" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="InformarDadosGeracaoResumoAcaoConsultaEventualActionForm" property="eloPolo">
					<html:text property="descricaoEloPolo" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>
        	
        	<a href="javascript:gerenciadorHabilitacaoImagemLimpar('EloPolo')" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>
		</td>
      </tr>
      <tr>
      	<td width="183" HEIGHT="30"><strong>Localidade:</strong></td>
        <td width="432">
        	
        	<html:text property="localidade" size="4" maxlength="3" tabindex="6" onkeypress="validaEnterComMensagem(event, 'exibirInformarDadosGeracaoResumoAcaoConsultaEventualAction.do?pesquisarLocalidade=OK', 'localidade', 'Localidade');"/>
			<a href="javascript:gerenciadorHabilitacaoImagemPesquisa('Localidade')" title="Pesquisar">
			<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>

			<logic:present name="corLocalidade">

				<logic:equal name="corLocalidade" value="exception">
					<html:text property="descricaoLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corLocalidade" value="exception">
					<html:text property="descricaoLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corLocalidade">

				<logic:empty name="InformarDadosGeracaoResumoAcaoConsultaEventualActionForm" property="localidade">
					<html:text property="descricaoLocalidade" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="InformarDadosGeracaoResumoAcaoConsultaEventualActionForm" property="localidade">
					<html:text property="descricaoLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>
        	
        	<a href="javascript:gerenciadorHabilitacaoImagemLimpar('Localidade')" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>
		</td>
      </tr>
      <tr>
      	<td width="183" HEIGHT="30"><strong>Setor Comercial:</strong></td>
        <td width="432">
        	
        	<html:hidden property="idSetorComercial"/>
        	<html:text property="setorComercial" size="4" maxlength="3" tabindex="7" onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarDadosGeracaoResumoAcaoConsultaEventualAction.do?pesquisarSetorComercial=OK', this, document.forms[0].localidade.value, 'Localidade', 'Setor Comercial');"/>
			<a href="javascript:gerenciadorHabilitacaoImagemPesquisa('SetorComercial')" title="Pesquisar"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>

			<logic:present name="corSetorComercial">

				<logic:equal name="corSetorComercial" value="exception">
					<html:text property="descricaoSetorComercial" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corSetorComercial" value="exception">
					<html:text property="descricaoSetorComercial" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corSetorComercial">

				<logic:empty name="InformarDadosGeracaoResumoAcaoConsultaEventualActionForm" property="setorComercial">
					<html:text property="descricaoSetorComercial" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="InformarDadosGeracaoResumoAcaoConsultaEventualActionForm" property="setorComercial">
					<html:text property="descricaoSetorComercial" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>
        	
        	<a href="javascript:gerenciadorHabilitacaoImagemLimpar('SetorComercial')" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>
		</td>
      </tr>
      <tr>
      	<td width="183" HEIGHT="30"><strong>Quadra:</strong></td>
        <td width="432">
        	
        	<html:text property="quadra" size="4" maxlength="4" tabindex="8" onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarDadosGeracaoResumoAcaoConsultaEventualAction.do?pesquisarQuadra=OK', this, document.forms[0].setorComercial.value, 'Setor Comercial', 'Quadra');"/>

			<logic:present name="msgQuadra">
				<span style="color:#ff0000" id="msgQuadra"><bean:write name="msgQuadra"/></span>
			</logic:present>        	
        	
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Perfil do Imóvel:</strong></td>
        <td>
			<html:select property="perfilImovel" style="width: 200px;" tabindex="9" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoImovelPerfil" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Ligação de Água:</strong></td>
        <td>
			<html:select property="situacaoLigacaoAgua" style="width: 200px;" tabindex="11" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoLigacaoAguaSituacao" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Ligação de Esgoto:</strong></td>
        <td>
			<html:select property="situacaoLigacaoEsgoto" style="width: 200px;" tabindex="12" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoLigacaoEsgotoSituacao" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Categoria:</strong></td>
        <td>
			<html:select property="categoria" style="width: 200px;" tabindex="13" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoCategoria" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Esfera de Poder:</strong></td>
        <td>
			<html:select property="esferaPoder" style="width: 200px;" tabindex="14" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoEsferaPoder" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30"><strong>Empresa:</strong></td>
        <td>
			<html:select property="empresa" style="width: 200px;height: 100px" tabindex="15" multiple="true">
				<html:option value="">&nbsp;</html:option>
				<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id"/>
			</html:select>
		</td>
      </tr>
      
      <tr>
      	<td></td>
      	<td><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
      </tr>
      <tr>
      	<td colspan="2">&nbsp;</td>
      </tr>
      <tr>
      	<td align="left"><INPUT type="button" onclick="window.location.href='exibirInformarDadosGeracaoResumoAcaoConsultaEventualAction.do?limparForm=OK'" style="width: 70px;" name="botaoLimpar" class="bottonRightCol" value="Limpar" tabindex="15"></td>
      	<td align="right"><INPUT type="button" onclick="validarForm(document.forms[0]);" name="botaoGerar" class="bottonRightCol" value="Gerar Consulta" tabindex="16"></td>
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
