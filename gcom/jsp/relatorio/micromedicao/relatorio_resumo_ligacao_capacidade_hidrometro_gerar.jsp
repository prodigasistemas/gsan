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

<html:javascript staticJavascript="false"  formName="GerarRelatorioResumoLigacoesCapacidadeHidrometroActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		
		if(validarCampos()){
			if((form.opcaoTotalizacao.value == "6" || form.opcaoTotalizacao.value == "7") && form.regional.value == "-1"){
				alert("Informe a Gerência Regional")
			} else if ((form.opcaoTotalizacao.value == "8" || form.opcaoTotalizacao.value == "9") && form.unidadeNegocio.value == "-1"){
				alert("Informe a Unidade de Negocio")
			} else if(form.opcaoTotalizacao.value == "10" && form.idLocalidade.value == ""){
				alert("Informe a Localidade")
			} else if( form.opcaoTotalizacao.value == "-1"){
				alert("Informe a Opção de Totalização")
			} else {
				toggleBox('demodiv',1);
			}
		}
	}


	function validarCampos(){

		var form = document.forms[0];
		if (form.opcaoTotalizacao.value != "-1"){
       		return true;
       	}else{
	  		if ( form.opcaoTotalizacao.value == "-1"){
	  			alert("Selecione a opção de Totalização.");
	   		}
	   		return false;
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
  		
  		form.opcaoTotalizacao.value = "-1";
  		form.regional.value = "-1";
  		form.regional.disabled = true;
		form.unidadeNegocio.value = "-1";
		form.unidadeNegocio.disabled = true;
		form.idLocalidade.value = "";
		form.nomeLocalidade.value = "";
		form.idLocalidade.disabled = true;
  		
  	}	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidade') {
      		
      		form.idLocalidade.value = codigoRegistro;
	  		form.nomeLocalidade.value = descricaoRegistro;
      		
	  		form.nomeLocalidade.style.color = "#000000";
	  		form.idLocalidade.disabled = false;
		}
	}		
	
	function limparPesquisaLocalidade() {
		var form = document.forms[0];
		
		form.idLocalidade.value = "";
		form.nomeLocalidade.value = "";	
	}	
	
	function desabilitarCampos(){
		var form = document.forms[0];

		if(form.opcaoTotalizacao.value == "6" || form.opcaoTotalizacao.value == "7"){
			form.regional.disabled = false;
			form.unidadeNegocio.disabled = true;
			form.idLocalidade.disabled = true;
			form.regional.value = "-1";
		}else if (form.opcaoTotalizacao.value == "8" || form.opcaoTotalizacao.value == "9"){
			form.unidadeNegocio.disabled = false;
			form.regional.disabled = true;
			form.idLocalidade.disabled = true;
			form.unidadeNegocio.value = "-1";
		}else if (form.opcaoTotalizacao.value == "10"){
			form.idLocalidade.disabled = false;
			form.regional.disabled = true;
			form.unidadeNegocio.disabled = true;	
		}else{
			form.regional.disabled = true;
			form.unidadeNegocio.disabled = true;
			form.idLocalidade.disabled = true;
			form.regional.value = "-1";
			form.unidadeNegocio.value = "-1";
			form.idLocalidade.value = "";
			form.nomeLocalidade.value = "";
		}
	}
		  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:desabilitarCampos();">

<div id="formDiv"><html:form action="/gerarRelatorioResumoLigacoesCapacidadeHidrometroAction.do"
	name="GerarRelatorioResumoLigacoesCapacidadeHidrometroActionForm"
	type="gcom.gui.relatorio.cadastro.micromedicao.GerarRelatorioResumoLigacoesCapacidadeHidrometroActionForm"
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
					<td class="parabg">Gerar Resumo de Liga&ccedil;&otilde;es por Capacidade de Hidr&ocirc;metro </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o resumo, informe os dados abaixo:</td>
				</tr>

				<tr>
					<td width="200">
						<strong>Op&ccedil;&atilde;o de Totaliza&ccedil;&atilde;o:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="3">
						<html:select property="opcaoTotalizacao" tabindex="1" style="width:230px;" onclick="javascript:desabilitarCampos();">
							<html:option 
								value="-1"> &nbsp; 
							</html:option>
							<html:option 
								value="1">Estado
							</html:option>
							<html:option 
								value="2">Estado por Gerência Regional
							</html:option>
							<html:option 
								value="3">Estado por Unidade de Negócio
							</html:option>
							<html:option 
								value="4">Estado por Gerência Regional e por Localidade
							</html:option>
							<html:option 
								value="5">Estado por Unidade de Negócio e por Localidade
							</html:option>
							<html:option 
								value="6">Gerência Regional
							</html:option>
							<html:option 
								value="7">Gerência Regional por Localidade
							</html:option>
							<html:option 
								value="8">Unidade de Negócio
							</html:option>
							<html:option 
								value="9">Unidade de Negócio por Localidade
							</html:option>
							<html:option 
								value="10">Localidade
							</html:option>
							
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="200">
						<strong>Gerência Regional:</strong>
					</td>
					<td colspan="3">
						<html:select property="regional" 
									 tabindex="2" 
									 style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoGerenciaRegional" property="id" labelProperty="nome" />
						</html:select>
					</td>
				</tr>

				<tr>
					<td width="200">
						<strong>Unidade de Negócio:</strong>
					</td>
					<td colspan="3">
						<html:select property="unidadeNegocio" 
									 tabindex="3" 
									 style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoUnidadeNegocio" property="id" 
							labelProperty="nome" />
						</html:select>
					</td>
				</tr>	
				
				<tr>
				   <td width="22%"><strong>Localidade:<font color="#FF0000"></font></strong></td>
		                   <td width="81%" height="24" colspan="2">
		                   		<html:text maxlength="3" 
		                   				   property="idLocalidade"
		                   				   size="3"  
		                   				   tabindex="4" 
		                   				   onkeypress="javascript:validaEnter(event, 'exibirGerarRelatorioResumoLigacoesCapacidadeHidrometroAction.do', 'idLocalidade'); return isCampoNumerico(event);"
		                   		/>
		                      	<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);">
		                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Localidade" /></a>
							
		   		      			<logic:present name="localidadeInexistente" 
		   		      						   scope="request">
									<input type="text" 
										   name="nomeLocalidade" 
										   size="50" 
										   readonly="true" 
										   style="background-color:#EFEFEF; border:0; color: #ff0000" 
										   value="<bean:message key="atencao.localidade.inexistente"/>
									"/>
		                      </logic:present>
		
		                      <logic:notPresent name="localidadeInexistente" 
		                      					scope="request">
		                        	<html:text property="nomeLocalidade" 
		                        			   size="50" 
		                        			   readonly="true" 
		                        			   style="background-color:#EFEFEF; border:0; color: #000000"
		                        	/>
		                      </logic:notPresent>
								<a href="javascript:limparPesquisaLocalidade();document.forms[0].idLocalidade.focus();"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /></a>                   
					</td>
                 </tr>
				
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
								          	
				<tr>
					<td height="24" colspan="2">
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   value="Limpar" 
			          		   onclick="javascript:limpar();"/>
			          		
			          	<font color="#FF0000"> <input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</font>
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
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioResumoLigacoesCapacidadeHidrometroAction.do" 
	/>	
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
