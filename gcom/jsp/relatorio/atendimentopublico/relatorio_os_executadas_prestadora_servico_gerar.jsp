<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioOSExecutadasPrestadoraServicoActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript"><!--

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'localidade') {

			form.codigoLocalidade.value = codigoRegistro;
	  		form.descricaoLocalidade.value = descricaoRegistro;
	  		form.descricaoLocalidade.style.color = "#000000";
	  		form.codigoLocalidade.focus();
	  		desabilitaCampos();
	 	}
	}

	function limparLocalidade() {
	    var form = document.GerarRelatorioOSExecutadasPrestadoraServicoActionForm;
	     form.codigoLocalidade.value = '';
	     form.descricaoLocalidade.value = '';
	     desabilitaCampos();
	}
	
	function validarForm(){
   		var form = document.forms[0];
   		
   		desabilitaCampos();
    	if(validateGerarRelatorioOSExecutadasPrestadoraServicoActionForm(form)){
			if(validaTodosPeriodos()){
				form.action = 'gerarRelatorioOSExecutadasPrestadoraServicoAction.do';
	   			submitForm(form);
			}
	  	}

    }
    function validaTodosPeriodos() {

		var form = document.forms[0];
		
    	 if (comparaData(form.periodoEncerramentoInicial.value, '>', form.periodoEncerramentoFinal.value)){

			alert('Data Final do Período de Encerramento é anterior à Data Inicial do Período de Encerramento');
			return false;

		} 

		return true;
    }

	function limparForm(){

		var form = document.forms[0];

		form.periodoEncerramentoInicial.value="";
		form.periodoEncerramentoFinal.value="";
	
		form.idGerencia.selectedIndex = -1;
		form.idUnidadeNegocio.selectedIndex = -1;
		form.colecaoRegiao.selectedIndex = -1;
		form.colecaoMicrorregiao.selectedIndex = -1;
		form.colecaoMunicipio.selectedIndex = -1;
		form.opcaoRelatorio[0].checked = false;
		form.opcaoRelatorio[1].checked = true;
		form.opcaoRelatorio[2].checked = false;
		
		limparLocalidade();
		
	}

	//Replica Data de Encerramento
	function replicaDataEncerramento() {
		var form = document.forms[0];
		form.periodoEncerramentoFinal.value = form.periodoEncerramentoInicial.value;
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
	
	function reload() {
		var form = document.forms[0];
		form.action = "/gsan/exibirGerarRelatorioOSExecutadasPrestadoraServicoAction.do";
		form.submit();
	}
	
	//So chama a função abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem,objetoRelacionado,fieldNameDestino){
		
		if(objetoRelacionado.disabled != true){
			if(fieldNameDestino != ''){
				abrirCalendarioReplicando('GerarRelatorioOSExecutadasPrestadoraServicoActionForm', fieldNameOrigem,fieldNameDestino);
			}else{
				abrirCalendario('GerarRelatorioOSExecutadasPrestadoraServicoActionForm', fieldNameOrigem);
			}
		}
	}
	
	
	function desabilitaCampos(){

		var form = document.forms[0];
		form.colecaoRegiao.disabled = false;
		form.colecaoMicrorregiao.disabled = false;
		form.colecaoMunicipio.disabled = false;
		form.idGerencia.disabled = false;
		form.idUnidadeNegocio.disabled = false;
		form.codigoLocalidade.disabled = false;
	
		if( form.idGerencia.value != -1 ||
			form.idUnidadeNegocio.value != -1 || 
			form.codigoLocalidade.value != ''){
		
			form.colecaoRegiao.disabled = true;
			form.colecaoMicrorregiao.disabled = true;
			form.colecaoMunicipio.disabled = true;
			
		
		}else if( (form.colecaoRegiao.value != -1 && form.colecaoRegiao.value != '')||
			(form.colecaoMicrorregiao.value != -1 && form.colecaoMicrorregiao.value != '')|| 
			(form.colecaoMunicipio.value != -1 && form.colecaoMunicipio.value != '')){
		
			form.idGerencia.disabled = true;
			form.idUnidadeNegocio.disabled = true;
			form.codigoLocalidade.disabled = true;
		
		
		}
		



	}
	
	function gerar(){
		var form = document.forms[0];	
		desabilitaCampos();
    	if(validateGerarRelatorioOSExecutadasPrestadoraServicoActionForm(form)){
			if(validaTodosPeriodos()){
			form.action = 'gerarRelatorioOSExecutadasPrestadoraServicoAction.do';
			submeterFormPadrao(form);
			}	
		}
	}
	
--></script>

</head>

<body leftmargin="5" topmargin="5" onload="desabilitaCampos();">

<div id="formDiv">


<html:form 

	action="/gerarRelatorioOSExecutadasPrestadoraServicoAction.do"

	name="GerarRelatorioOSExecutadasPrestadoraServicoActionForm"

	type="gcom.gui.relatorio.atendimentopublico.ordemservico.GerarRelatorioOSExecutadasPrestadoraServicoActionForm"

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
					<td class="parabg">Relatório de OS Executadas por Prestadora de Serviço </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Preencha os campos para gerar relatório:</td>
				</tr>
				
				<tr>
	                <td><strong>Período de Encerramento da OS:<font color="#FF0000">*</font></strong></td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	<strong> 
							
							<html:text property="periodoEncerramentoInicial" size="11" maxlength="10" tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataEncerramento();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarCalendario('periodoEncerramentoInicial',document.forms[0].periodoEncerramentoInicial,'periodoEncerramentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
							a 
							
							<html:text property="periodoEncerramentoFinal" size="11" maxlength="10" tabindex="3" 
								onkeyup="mascaraData(this, event)" onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarCalendario('periodoEncerramentoFinal',document.forms[0].periodoEncerramentoFinal,'');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" height="15" border="0" alt="Exibir Calendário" tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>
				
				<tr>
					<td><strong>Empresa:</strong></td>

					<td>
						<strong> 
						<html:text property="empresa" size="30" maxlength="30" readonly="true"/>													
						</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Gerência:</strong></td>

					<td>
						<strong> 
						<html:select property="idGerencia" style="width: 230px;" 
							onclick="desabilitaCampos();" 
							onchange="reload();">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					
							<logic:present name="colecaoGerenciaRegional" scope="request">
								<html:options collection="colecaoGerenciaRegional"
									labelProperty="nome" property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade de Negócio:</strong></td>

					<td>
						<strong> 
						<html:select property="idUnidadeNegocio" style="width: 230px;" onclick="desabilitaCampos();">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					
							<logic:present name="colecaoUnidadeNegocio" scope="request">
								<html:options collection="colecaoUnidadeNegocio"
									labelProperty="nome" property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td align="left"><strong> Localidade</strong></td>
					<td align="left">
						<html:text value="${requestScope.codigoLocalidade}"	property="codigoLocalidade" size="3" maxlength="3"
						onkeypress="validaEnter(event, 'exibirGerarRelatorioOSExecutadasPrestadoraServicoAction.do', 'codigoLocalidade');desabilitaCampos();return isCampoNumerico(event);" />
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif" width="23" height="21" border="0"
						onclick="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
					<input type="text" name="descricaoLocalidade" readonly
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" value="${requestScope.descricaoLocalidade}" />
					<c:if test="${empty requestScope.codigoLocalidade}" var="testeCor">
						<script>document.GerarRelatorioOSExecutadasPrestadoraServicoActionForm.descricaoLocalidade.style.color = '#FF0000';</script>
					</c:if>
					<c:if test="${!testeCor}">
						<script>document.GerarRelatorioOSExecutadasPrestadoraServicoActionForm.descricaoLocalidade.style.color = '#000000';</script>
					</c:if>
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						width="23" height="21" onclick="javascript:limparLocalidade();"></td>
				</tr>
				
				<tr> 
                  <td><strong>Região:</strong></td>
                  <td colspan="6"><span class="style2"><strong>
					  <html:select property="colecaoRegiao" style="width: 350px; height:100px;" multiple="true" onclick="desabilitaCampos();">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoRegiao" scope="session">
							<html:options collection="colecaoRegiao" labelProperty="nome" property="id" />
						</logic:present>
					  </html:select>
                   </strong></span></td>
                 </tr>
           		
           		
           		<tr> 
                  <td><strong>Microregião:</strong></td>
                  <td colspan="6"><span class="style2"><strong>
					  <html:select property="colecaoMicrorregiao" style="width: 350px; height:100px;" multiple="true" onclick="desabilitaCampos();">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoMicrorregiao" scope="session">
							<html:options collection="colecaoMicrorregiao" labelProperty="nome" property="id" />
						</logic:present>
					  </html:select>
                   </strong></span></td>
                 </tr>
           		
           		
           		<tr> 
                  <td><strong>Município:</strong></td>
                  <td colspan="6"><span class="style2"><strong>
					  <html:select property="colecaoMunicipio" style="width: 350px; height:100px;" multiple="true" onclick="desabilitaCampos();">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoMunicipio" scope="session">
							<html:options collection="colecaoMunicipio" labelProperty="nome" property="id" />
						</logic:present>
					  </html:select>
                   </strong></span></td>
                 </tr>
           		
				<tr>
		          <td><strong>Opção do Relatório:<font color="#FF0000">*</font></strong></td>
		          <td>
		            <html:radio property="opcaoRelatorio" value="1" ><strong>Analítico</strong></html:radio>
		            <html:radio property="opcaoRelatorio" value="2" ><strong>Resumido</strong></html:radio>
		            <html:radio property="opcaoRelatorio" value="3" ><strong>TXT Analítico</strong></html:radio>
		          </td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campos Obrigatórios</td>
				</tr>	
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limparForm();"/>
					</td>
					
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar" 
							
							onclick="javascript:gerar();"
						 />
					</td>
					
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>
