<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
 <link rel="stylesheet" href="<bean:message key="caminho.css"/>jquery.autocomplete.css" type="text/css" />
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarContratoParcelamentoClienteActionForm" dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
 <script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
 <script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.autocomplete.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.js"/>popup.js"></script>	
	
<script language="JavaScript" 
	type="text/JavaScript"><!--


    var origemDestino;
	
	
	
	function validarForm(){
		var form = document.forms[0];
		if(form.idLocalidadeInicial.value == ""){
			if(form.idLocalidadeFinal.value != ""){
				alert("Informe Localidade Inicial");
				return false;
			}
		}
		
		if(form.idLocalidadeFinal.value == ""){
			if(form.idLocalidadeInicial.value != ""){
				alert("Informe Localidade Final");
				return false;
			}
		}
		
		if(form.idSetorComercialInicial.value == ""){
			if(form.idSetorComercialFinal.value != ""){
				alert("Informe Setor Comercial Inicial");
				return false;
			}
		}
		
		if(form.idSetorComercialFinal.value == ""){
			if(form.idSetorComercialInicial.value != ""){
				alert("Informe Setor Comercial Final");
				return false;
			}
		}
		
		if(form.idQuadraInicial.value == ""){
			if(form.idQuadraFinal.value != ""){
				alert("Informe Quadra Inicial");
				return false;
			}
		}
		
		if(form.idQuadraFinal.value == ""){
			if(form.idQuadraInicial.value != ""){
				alert("Informe Quadra Final");
				return false;
			}
		}
		
		toggleBox('demodiv',1);
		
	}
	
	function limparForm(){
		var form = document.forms[0];
		
			form.idLocalidadeInicial.value = "";
	 		form.descricaoLocalidadeInicial.value = "";
	 		form.idSetorComercialInicial.value = "";
	 		form.descricaoSetorComercialInicial.value = "";
	 		form.idQuadraInicial.value = "";
	 		form.descricaoQuadraInicial.value = "";
	 		form.idLocalidadeFinal.value = "";
	 		form.descricaoLocalidadeFinal.value = "";
	 		form.idSetorComercialFinal.value = "";
	 		form.descricaoSetorComercialFinal.value = "";
	 		form.idQuadraFinal.value = "";
	 		form.descricaoQuadraFinal.value = "";
	 		form.idLocalidadeInicial.disabled = false;
	 		form.idLocalidadeFinal.disabled = false;
	 		
      		var gr = form.gerenciaRegional.options;
	 	    var un = form.unidadeNegocio.options;	
	 	    var ct = form.categoriaImovel.options;
	 	    var pi = form.perfilImovel.options;
	 	    
	 	    desmarcarColecao(gr);
	 	    desmarcarColecao(un);
	 	    desmarcarColecao(ct);
	 	    desmarcarColecao(pi);
	 	    
	 	  	
	 }
	 
	 function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.readOnly != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	 }
	 
	 function pesquisarSetorComercial(arg1){
	    origemDestino = arg1;
	    if(arg1 == 'origem')
		 	abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeInicial.value,document.forms[0].idLocalidadeInicial.value,'Localidade Inicial', 400, 800);
		else
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeF.value,document.forms[0].idLocalidadeFinal.value,'Localidade Final', 400, 800);
	 }

	 function pesquisarQuadra(arg1, arg2){
	 	  origemDestino = arg1;
          if(arg1 == 'origem')
	 		  abrirPopupDependencia('exibirPesquisarQuadraAction.do', document.forms[0].idSetorComercialInicial.value, 'Setor Comercial Inicial', 275, 480);
	 	  else
	 	  	  abrirPopupDependencia('exibirPesquisarQuadraAction.do', document.forms[0].idSetorComercialFinal.value, 'Setor Comercial Final', 275, 480);
	 }		 
	 
	 
	 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
	 	if(tipoConsulta == "localidadeOrigem"){
	 		form.idLocalidadeInicial.value = codigoRegistro;
	 		form.descricaoLocalidadeInicial.value = descricaoRegistro;
	 		form.descricaoLocalidadeInicial.style.color = "#000000";
	 		form.idLocalidadeFinal.value = codigoRegistro;
	 		form.descricaoLocalidadeFinal.value = descricaoRegistro;
	 		form.descricaoLocalidadeFinal.style.color = "#000000";
	 	}
	 	else if(tipoConsulta == "localidadeDestino"){
	 		form.idLocalidadeFinal.value = codigoRegistro;
	 		form.descricaoLocalidadeFinal.value = descricaoRegistro;
	 		form.descricaoLocalidadeFinal.style.color = "#000000";
	 		
	 	}
	 	else if(tipoConsulta == "setorComercial"){
		 	if(origemDestino == "origem"){
		 		form.idSetorComercialInicial.value = codigoRegistro;
		 		form.descricaoSetorComercialInicial.value = descricaoRegistro;
		 		form.descricaoSetorComercialInicial.style.color = "#000000";
		 		form.idSetorComercialFinal.value = codigoRegistro;
		 		form.descricaoSetorComercialFinal.value = descricaoRegistro;
     	 		form.descricaoSetorComercialFinal.style.color = "#000000";
		 	}
		 	else{
		 		form.idSetorComercialFinal.value = codigoRegistro;
		 		form.descricaoSetorComercialFinal.value = descricaoRegistro;
     	 		form.descricaoSetorComercialFinal.style.color = "#000000";
		 	}
	 		
	 	}
	 	else if(tipoConsulta == "Quadra"){
		 	if(origemDestino == "origem"){
		 		form.idQuadraInicial.value = codigoRegistro;
		 		form.descricaoQuadraInicial.value = descricaoRegistro;
		 		form.descricaoQuadraInicial.style.color = "#000000";
		 		form.idQuadraFinal.value = codigoRegistro;
		 		form.descricaoQuadraFinal.value = descricaoRegistro;
		 		form.descricaoQuadraFinal.style.color = "#000000";
	 		}
	 		else{
		 		form.idQuadraFinal.value = codigoRegistro;
		 		form.descricaoQuadraFinal.value = descricaoRegistro;
		 		form.descricaoQuadraFinal.style.color = "#000000";
	 		}
	 	}
	 }
	 
	 function habilitarLocalOcorrencia(){
	 	var form = document.forms[0];
	 	
	 	  var gr = form.gerenciaRegional.options;
	 	  var un = form.unidadeNegocio.options;	
	 	
		  var i;
		  var j;
		  var count1 = 0;
		  var count2 = 0;
		  
		  for (i=0; i<gr.length; i++) {
		    if (gr[i].selected) {
		      count1++;
		    }
		  }
		  
		  for (j=0; j<un.length; j++) {
		    if (un[j].selected) {
		      count2++;
		    }
		  }
		  
	 	
	 	if(count1 > 0 || count2 > 0){
	 		form.idLocalidadeInicial.value = "";
	 		form.descricaoLocalidadeInicial.value = "";
	 		form.idSetorComercialInicial.value = "";
	 		form.descricaoSetorComercialInicial.value = "";
	 		form.idQuadraInicial.value = "";
	 		form.descricaoQuadraInicial.value = "";
	 		form.idLocalidadeFinal.value = "";
	 		form.descricaoLocalidadeFinal.value = "";
	 		form.idSetorComercialFinal.value = "";
	 		form.descricaoSetorComercialFinal.value = "";
	 		form.idQuadraFinal.value = "";
	 		form.descricaoQuadraFinal.value = "";
	 		form.idLocalidadeInicial.disabled = true;
	 		form.idLocalidadeFinal.disabled = true;
	 	}
	 	else{
	 		form.idLocalidadeInicial.disabled = false;
	 		form.idLocalidadeFinal.disabled = false;
	 	}
	 	
	 }
	 
	 function limpar(id,descricao){
	 	id.value = "";
	 	descricao.value = "";
	 }
	 
	 function limparConjunto(id){
	    var form = document.forms[0];
	 	if(id == 1){
	 		form.idLocalidadeInicial.value = "";
	 		form.descricaoLocalidadeInicial.value = "";
	 		form.idSetorComercialInicial.value = "";
	 		form.descricaoSetorComercialInicial.value = "";
	 		form.idQuadraInicial.value = "";
	 		form.idLocalidadeFinal.value = "";
	 		form.descricaoLocalidadeFinal.value = "";
	 		form.idSetorComercialFinal.value = "";
	 		form.descricaoSetorComercialFinal.value = "";
	 		form.idQuadraFinal.value = "";
	 	}
	 	else if(id == 2){
	 		form.idSetorComercialInicial.value = "";
	 		form.descricaoSetorComercialInicial.value = "";
	 		form.idQuadraInicial.value = "";
	 		form.idSetorComercialFinal.value = "";
	 		form.descricaoSetorComercialFinal.value = "";
	 		form.idQuadraFinal.value = "";
	 	}
	 	else if(id == 3){
	 		form.idLocalidadeFinal.value = "";
	 		form.descricaoLocalidadeFinal.value = "";
	 		form.idSetorComercialFinal.value = "";
	 		form.descricaoSetorComercialFinal.value = "";
	 		form.idQuadraFinal.value = "";
	 	}
	 	else if(id == 4){
	 		form.idSetorComercialFinal.value = "";
	 		form.descricaoSetorComercialFinal.value = "";
	 		form.idQuadraFinal.value = "";
	 	}
	 
	 }
	 
	 function desmarcarColecao(obj){
	 	var i;
	    for (i=0; i<obj.length; i++) {
	      if (obj[i].selected) {
	        obj[i].selected = false;
	      }
	    }
	 }
	 
	 
//
--></script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/gerarRelatorioOSAcompanhamentoCobrancaResultadoAction.do" 
		   name="GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm" 
		   type="gcom.gui.relatorio.cobranca.GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm"
		   method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="660" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
			
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Gerar Relatório Ordem de Serviço Cobrança p/Resultado</td>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
              </tr>
            </table>
            <table width="100%" border="0" >
				<tr> 
					<td colspan="3">
						Para gerar o relatório de OS das contas em cobrança por resultado, informe os dados abaixo:</td>
						<td width="90">
					</td>
				</tr>
				<tr>
	              <td height="10" colspan="3"> 
		       		  <div align="right"> 
		           	  	&nbsp;
		         	  </div>
		         	  <div align="right"> </div>
	              </td>
           	    </tr>
           	    <tr>
	                <td><strong>Comando:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="40"
	                  			 size="40"
	                  			 tabindex="1"
	                  		     readonly="true"
	                  			 property="comando"
	                  			 style="background-color:#EFEFEF; border:0; color: #000000"
	                  			 /></strong>	            
	               </td>
	            </tr>
           	    <tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
	            <tr> 
	                <td width="100"><strong>Categoria:</strong></td>
	                <td colspan="2">
			            <div align="left">
			                <strong>
				                <html:select style="width: 350px; height:100px;"
				                               multiple="true"
					                  		   property="categoriaImovel">
					                <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					                <logic:present name="colecaoCategoria" scope="session">
						            	<html:options collection="colecaoCategoria" labelProperty="descricao" property="id" />
					                </logic:present>
								</html:select>
			                </strong>
			            </div>
	            	</td>
	            </tr>
	            <tr> 
	                <td width="100"><strong>Perfil do Imóvel:</strong></td>
	                <td colspan="2">
			            <div align="left">
			                <strong>
				                <html:select style="width: 350px; height:100px;"
				                               multiple="true"
					                  		   property="perfilImovel">
					                <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
   					                <logic:present name="colecaoPerfilImovel" scope="session">
						            	<html:options collection="colecaoPerfilImovel" labelProperty="descricao" property="id" />
					            	</logic:present>
								</html:select>
			                </strong>
			            </div>
	            	</td>
	            </tr>
	            <tr> 
	                <td width="100"><strong>Gerência:</strong></td>
	                <td colspan="2">
			            <div align="left">
			                <strong>
				                <html:select style="width: 350px; height:100px;"
				                               multiple="true"
					                  		   property="gerenciaRegional"
					                  		   onchange="habilitarLocalOcorrencia()"
					                  		   >
					                <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					                <logic:present name="colecaoGerenciaRegional" scope="session">
					            		<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
					            	</logic:present>
								</html:select>
			                </strong>
			            </div>
	            	</td>
	            </tr>
	            <tr> 
	                <td width="100"><strong>Unidade Negócio:</strong></td>
	                <td colspan="2">
			            <div align="left">
			                <strong>
				                <html:select style="width: 350px; height:100px;"
				                               multiple="true"
				                               onchange="habilitarLocalOcorrencia()"
					                  		   property="unidadeNegocio">
					                <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					                <logic:present name="colecaoUnidadeNegocio" scope="session">
					            		<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
					            	</logic:present>
								</html:select>
			                </strong>
			            </div>
	            	</td>
	            </tr>
	            
	            <tr>
	                <td><strong>Localidade Inicial:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idLocalidadeInicial"
	                  			 onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarRelatorioOSAcompanhamentoCobrancaResultadoAction.do?pesquisarLocalidadeInicial=OK', 'idLocalidadeInicial','Localidade Inicial');return isCampoNumerico(event);" />
	                <a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 400, 800, '',document.forms[0].idLocalidadeInicial);">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						     title="Pesquisar Localidade" /></a>
					<logic:notPresent name="localidadeInicialException" scope="request">	     
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoLocalidadeInicial"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</logic:notPresent>
	     			<logic:present name="localidadeInicialException" scope="request">
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoLocalidadeInicial"
		     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
	     			</logic:present>	     
	     			</strong>
	     			<a href="javascript:limparConjunto(1);">
						<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
					</a>
	     		</td>
              </tr>
              
              <tr>
	                <td><strong>Setor Comercial Inicial:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idSetorComercialInicial"
 	                  			 onkeypress="javascript:validaEnterDependencia(event, 'exibirGerarRelatorioOSAcompanhamentoCobrancaResultadoAction.do?pesquisarSetorComercialInicial=OK', this, document.forms[0].idLocalidadeInicial.value, 'Localidade Inicial');return isCampoNumerico(event);" />
	                    <a href="javascript:pesquisarSetorComercial('origem')">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						     title="Pesquisar Setor Comercial" /></a>
					<logic:notPresent name="setorComercialInicialException" scope="request">	     
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoSetorComercialInicial"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</logic:notPresent>
	     			<logic:present name="setorComercialInicialException" scope="request">
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoSetorComercialInicial"
		     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
	     			</logic:present>	   
	     			</strong>
	     			<a href="javascript:limparConjunto(2);">
						<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
					</a>  	     
	     		</td>
              </tr>
              
               <tr>
	                <td><strong>Quadra Inicial: </strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idQuadraInicial"
	                  			 onkeypress="javascript:validaEnterDependencia(event, 'exibirGerarRelatorioOSAcompanhamentoCobrancaResultadoAction.do?pesquisarQuadraInicial=OK',this,document.forms[0].idSetorComercialInicial.value, 'Setor Comercial Inicial');" />
					<logic:notPresent name="quadraInicial" scope="session">
						<span style="color:#ff0000" id="msgQuadraInicial">
							<bean:write name="GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm" property="descricaoQuadraInicial" />
						</span>
					</logic:notPresent> 
					<logic:present name="corQuadraOrigem" scope="request" />
	     			</strong> 
	     		</td>
              </tr>
              
               <tr>
	                <td><strong>Localidade Final:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idLocalidadeFinal"
	                  			 onkeypress="javascript:validaEnterDependencia(event, 'exibirGerarRelatorioOSAcompanhamentoCobrancaResultadoAction.do?pesquisarLocalidadeFinal=OK', this, document.forms[0].idLocalidadeInicial.value, 'Localidade Inicial');return isCampoNumerico(event);" />	                  			 						
	                <a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 400, 800, '',document.forms[0].idLocalidadeFinal);">	
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						     title="Pesquisar Localidade" /></a>
					<logic:notPresent name="localidadeFinalException" scope="request">	     
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoLocalidadeFinal"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</logic:notPresent>
	     			<logic:present name="localidadeFinalException" scope="request">
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoLocalidadeFinal"
		     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
	     			</logic:present>
	     			</strong>
	     			<a href="javascript:limparConjunto(3);">
						<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
					</a>  
	     		</td>
              </tr>
              
              <tr>
	                <td><strong>Setor Comercial Final:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idSetorComercialFinal"
	                  			 onkeypress="javascript:validaEnterDependencia(event, 'exibirGerarRelatorioOSAcompanhamentoCobrancaResultadoAction.do?pesquisarSetorComercialFinal=OK', this, document.forms[0].idLocalidadeFinal.value, 'Localidade Final');return isCampoNumerico(event);" />
   	                    <a href="javascript:pesquisarSetorComercial('destino')">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						     title="Pesquisar Setor Comercial" /></a>
					<logic:notPresent name="setorComercialFinalException" scope="request">	     
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoSetorComercialFinal"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</logic:notPresent>
	     			<logic:present name="setorComercialFinalException" scope="request">
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoSetorComercialFinal"
		     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
	     			</logic:present>	     	     	    
	     			</strong>
	     			<a href="javascript:limparConjunto(4);">
						<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
					</a>  
	     		</td>
              </tr>
              
               <tr>
	                <td><strong>Quadra Final:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idQuadraFinal"
	                  			 onkeypress="javascript:validaEnterDependenciaComMensagem(event, 'exibirGerarRelatorioOSAcompanhamentoCobrancaResultadoAction.do?pesquisarQuadraFinal=OK',this,document.forms[0].idSetorComercialFinal.value, 'Setor Comercial Final');" />
	               <logic:notPresent name="quadraFinal" scope="session">
						<span style="color:#ff0000" id="msgQuadraFinal">
							<bean:write name="GerarRelatorioOSAcompanhamentoCobrancaResultadoActionForm" property="descricaoQuadraFinal" />
						</span>
					</logic:notPresent> 
					<logic:present name="quadraFinal" scope="session" ></logic:present>       					   
	     			</strong>
	     		</td>
              </tr>										 
                  		
              <tr>
	              <td><strong>Tipo da OS:</strong></td>
	              <td colspan="2">
	           		  <html:select property="tipoServico">
		                <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
		                <logic:present name="colecaoTipoServico" scope="session">
		            		<html:options collection="colecaoTipoServico" labelProperty="descricao" property="id" />
		            	</logic:present>
						</html:select>
	                </td>
              </tr>
              
				 <tr>
             		<td height="10" colspan="4"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
           		
           	  <tr>
				<td colspan="3">
	                	<input type="button" name="adicionar2" 
	                			class="bottonRightCol" 
	                			value="Limpar" 
	                			onclick="javascript:limparForm();">
               			<input type="button" name="voltar" 
				                class="bottonRightCol" 
				                value="Voltar" 
				                onclick="javascript:history.go(-1);">
	                	<input type="button" name="adicionar" 
				                class="bottonRightCol" 
				                value="Cancelar" 
				                onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				                
				                
				               
	                </td>
					<td align="right"><input type="button" name="Submit"
					class="bottonRightCol" value="Emitir" onclick="javascript:validarForm();"></td>
			 </tr>
           	  
            </table>
		  </td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioOSAcompanhamentoCobrancaResultadoAction.do" />
</html:form>
</body>
</html:html>