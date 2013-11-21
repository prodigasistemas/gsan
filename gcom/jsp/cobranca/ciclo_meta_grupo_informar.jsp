<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>
<%@ page import="java.util.Collection, java.util.Iterator, gcom.cobranca.InformarCicloMetaGrupoHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript">
<!-- Begin

 var bCancel = false;
 
	function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	} 

    function validateCicloMetaGrupoActionForm(form) {
        if (bCancel)
      return true;
        else
       return validaMesAno(form.anoMesCobranca);
   }

	function validaMesAno(anoMes){
		if(anoMes.value != ""){
			return verificaAnoMes(anoMes);
		}else{
			return true;
		}
	}
	
	function IntegerValidations () { 
       this.aa = new Array("metaTotal", "Meta total deve conter somente números positivos.", new Function ("varName", " return this[varName];"));
    }

	function gerarMetas(){
	  var form = document.forms[0];
	  
	  if (form.metaTotal.value == ""){
	  	alert('Informe a Meta Total.');
	  }else{
		  if (form.metaTotal.value > 0){
			  if (validateCicloMetaGrupoActionForm(form)) {
			    var conf = true;
			  	if (form.btnFiltrar.disabled == true){ // significa que as localidades estao carregadas
			  		conf = confirm('Esta operação sobreescreverá os ajustes realizados nas metas. \nDeseja continuar?');	  		
			  	}
			  	if (conf){
				    form.action = 'informarCicloMetaGrupoContinuarAction.do';
				  	form.submit();
				}
			  }
		  }else{
		  		alert('Informe um valor maior que 0.');
  	 	  }
	  }
	}
	
	function regerarMetas(){
	  var form = document.forms[0];
	  
	  if (form.metaTotal.value == ""){
	  	alert('Informe a Meta Total.');
	  }else{
		 if (form.metaTotal.value > 0){
		 	if (validateCicloMetaGrupoActionForm(form)) {
			    var conf = true;
			  	if (form.btnFiltrar.disabled == true){ // significa que as localidades estao carregadas
			  		conf = confirm('Esta operação sobreescreverá os ajustes realizados nas metas. \nDeseja continuar?');	  		
			  	}
			  	if (conf){
				    form.action = 'informarCicloMetaGrupoContinuarAction.do?regerarMetas=true';
				  	form.submit();
				}
		  	}
		 	
		 }else{
		  		alert('Informe um valor maior que 0.');
	  	 }
	  }
	}

	function chamarSalvar(){
		if (chamarAlterarExcluir() == 3){
		  	var form = document.forms[0];
		  	if (validateCicloMetaGrupoActionForm(form) /*&& validateLong(form)*/) {
		    	form.action = 'informarCicloMetaGrupoSalvarAction.do';
		  		form.submit();
		  	}
	  	}	
	}
	
	function voltarConsulta(){
		var form = document.forms[0];
		form.btnConcluir.disabled = true;
		form.action = 'exibirInformarCicloMetaGrupoAction.do?voltar=1';
	  	form.submit();
	}
	
	function bloquearContinuar(){
	  var form = document.forms[0];
	  if (form.btnContinuar != null)
		  form.btnContinuar.disabled = true;
		  form.btnConcluir.disabled = true;
	}
	
	function chamarFiltrar(){
	  var form = document.forms[0];
	  if (form.anoMesCobranca != null && form.anoMesCobranca.value != ""){
	  	if (form.idCobrancaAcao.value > 0){
	  		if (validateCicloMetaGrupoActionForm(form)) {
	  			form.action = 'exibirInformarCicloMetaGrupoAction.do';
		  		form.submit();
		  	}
	  	}else{
	  		alert('Informe Ação de Cobrança');
	  	}
	  }else{
	  	alert('Informe Ano/Mês de referência');
	  }
	}
	
	function limparForm(){
		var form = document.forms[0];
		
		form.idCobrancaAcao.value = "-1";
		form.anoMesCobranca.value = "";
	}
	
	function redistribuirAlteracao(obj){
		var nomeAlterado = obj.name;
		var form = document.forms[0];
		
		var textos = document.getElementsByTagName("INPUT");

		var iHid = nomeAlterado.indexOf('_Hide');
		
		var valorAnteriorObjetoAlterado = '';
		var valorObjetoAlterado = obj.value;
		
		if (iHid == -1){
			var objHidden = eval('document.forms[0].' + nomeAlterado + '_Hide');
		} else {
			nomeAlterado = nomeAlterado.substr(0,iHid);
			var objHidden = eval('document.forms[0].' + nomeAlterado);
		}
		if (objHidden != null) {
			valorAnteriorObjetoAlterado = objHidden.value;
			//objHidden.value = obj.value;
		}
		var diferenca = parseInt(obj.value) - parseInt(valorAnteriorObjetoAlterado);
		// caso não tenha L => objeto alterado não foi uma localidade, dai.. 
		// vamos voltar o valor antes da alteracao, pois o novo valor sera calculado
		if(nomeAlterado.indexOf('L') == -1){
			obj.value = valorAnteriorObjetoAlterado;  			
		}
		
		var diferencaAcumulada = 0;
		var ultimoIndiceAlterado = -1;
		
		if (textos != null) {
			for(i = 0; i < textos.length; i++){
			    var nomeElemento = textos[i].name;
				if (textos[i].type == 'text' 
					&& nomeElemento.indexOf(nomeAlterado) != -1 
					&& nomeElemento != nomeAlterado    // nao atualizar o objeto alterado
					&& nomeElemento != (nomeAlterado + '_Hide') // nem o oculto do alterado
					&& nomeElemento.indexOf('L') != -1 // alterar apenas as localidades
					&& nomeElemento.indexOf('_Hide') == -1 // nao atualizar os hidden
					&& textos[i].disabled == false){
					textos[i].style.color = "red";
					
					// novameta_localidade = antigametalocalidade * novameta da unidade (ou gerencia) 
					// / antiga meta da unidade (ou gerencia)
					
					var valorMetaOrig = eval('document.forms[0].' + nomeAlterado + '_Org.value');
					var novaMeta = parseFloat(textos[i].value) * parseFloat(valorObjetoAlterado)
						/ parseFloat(valorAnteriorObjetoAlterado);

					diferencaAcumulada += parseInt(novaMeta) - parseInt(textos[i].value);
										
					textos[i].value = parseInt(novaMeta);
					
					if (textos[i].value == 'NaN' || textos[i].value == 'NAN' ){
						textos[i].value = 0;
					}else if (textos[i].value < 0){
						textos[i].value = 0;
					}
		
					atualizarUnidade(textos[i]);
					
					ultimoIndiceAlterado = i;
										
				}
		
			}	
			
			if (diferenca != diferencaAcumulada){
			   
			   var ajuste = diferenca - diferencaAcumulada;
			   textos[ultimoIndiceAlterado].value = parseInt(textos[ultimoIndiceAlterado].value) + ajuste;
			   
			   if (textos[ultimoIndiceAlterado].value == 'NaN' || textos[ultimoIndiceAlterado].value == 'NAN' ){
						textos[ultimoIndiceAlterado].value = 0;
				}else if (textos[ultimoIndiceAlterado].value < 0){
					textos[ultimoIndiceAlterado].value = 0;
				}
			   
			   atualizarUnidade(textos[ultimoIndiceAlterado]);
			   
			   //var objRespectivo = obterObjetoRespectivo(textos[ultimoIndiceAlterado]);
			   //objRespectivo.value = textos[ultimoIndiceAlterado].value;
			}
			
		}
		
	}
	
	// cada caixa de texto, tem o seu hidden respectivo para guardar o valor anterior
	// este metodo vai retornar o hidden caso seja passado o normal, ou vice-versa.
	function obterObjetoRespectivo(obj){
		var nome = obj.name;
		var objHidden;
		var iHid = nome.indexOf('_Hide');
		if (iHid == -1){
			objHidden = eval('document.forms[0].' + nome + '_Hide');
		} else {
			objHidden = eval('document.forms[0].' + nome);
		}
		return objHidden;
	}
	
	function atualizarUnidade(obj){
	    var nomeUnidade = obj.name.substr(0, obj.name.indexOf('L'));
	    var objUnid = eval('document.forms[0].' + nomeUnidade);
	    var valorAntigoUnid = objUnid.value;
	    var objLocHid = eval('document.forms[0].' + obj.name + '_Hide');
	    var valorAntigo = objLocHid.value;
	    var diferenca = parseInt(obj.value) - parseInt(valorAntigo);
	    objUnid.value =  parseInt(objUnid.value) + diferenca;
	    var objUnidHid = eval('document.forms[0].' + objUnid.name + '_Hide');	    
	    objUnidHid.value = objUnid.value;
	    objLocHid.value = obj.value;
	    if (objLocHid.value == 'NaN' ||objLocHid.value == 'NAN' ){
	    	objLocHid.value = 0;
	    }else if (objLocHid.value < 0){
	    	objLocHid.value = 0;
	    }
	    atualizarGerencia(objUnid, diferenca);
	}
	
	function atualizarGerencia(obj, diferenca){
	    var nomeGerencia = obj.name.substr(0, obj.name.indexOf('U'));
	    var objGer = eval('document.forms[0].' + nomeGerencia);
	    var valorAntigoGer = objGer.value;
	    objGer.value =  parseInt(objGer.value) + diferenca;
	    var objGerHid = eval('document.forms[0].' + objGer.name + '_Hide');	    
	    objGerHid.value = objGer.value;
	    
	    if ( objGerHid.value == 'NaN' || objGerHid.value == 'NAN' ){
	    	objGerHid.value = 0;
	    }else if (objGerHid.value < 0){
	    	objGerHid.value = 0;
	    }	    
		
		var metaTotal = document.getElementById("metaTotalAjustada");
		
		if (diferenca.value == 'NaN' || diferenca.value == 'NAN' ){
			diferenca.value = 0;
		}
		
		metaTotal.value = parseInt(metaTotal.value) + diferenca;
	}
	
	function chamarAlterarExcluir(){
	  var form = document.forms[0];
	  var valorLimite = 0;
	  var metaTotalCalculada = 0;
	  var confAlteracao = false;
	  var retorno = 3;
	  
	  if (document.getElementById("valorLimiteEmissao") != null){
	  	valorLimite = document.getElementById("valorLimiteEmissao");
	  	valorLimite.value = parseFloat(valorLimite.value.replace(",","."));
	  }
	  
	  if (document.getElementById("metaTotalCalculada") != null){
	  	metaTotalCalculada = document.getElementById("metaTotalCalculada");
	  	metaTotalCalculada.value = parseInt(metaTotalCalculada.value);
	  }
	
	  if (validateCicloMetaGrupoActionForm(form)) {
	  	
	  	if (valorLimite.value > 0 && metaTotalCalculada.value == 0 && form.indicadorValor.checked){
	  		confAlteracao = confirm('Esta operação remover o Valor Limite para Emissão. \nDeseja continuar?');
	  		
	  		if (confAlteracao){
	  			retorno = 1;
		    	form.action = 'exibirInformarCicloMetaGrupoAction.do?excluirCicloMeta=true';
	  			form.submit();
			}else{
				retorno = 2;
			}
		}else if (valorLimite.value == 0 && metaTotalCalculada.value > 0 && form.indicadorMeta.checked){ //&& form.indicadorValor.checked == false){
	  		confAlteracao = confirm('Esta operação irá remover a Meta Total do Mês/Ano informado \npara Ação de Cobrança selecionada . \nDeseja continuar?');
	  		
	  		if (confAlteracao){
	  			retorno = 1;
		    	form.action = 'exibirInformarCicloMetaGrupoAction.do?excluirCicloMetaCicloMetaGrupo=true';
	  			form.submit();
			}else{
				retorno = 2;
			}
		}else if (valorLimite.value > 0 && metaTotalCalculada.value > 0 && form.indicadorValor.checked && form.indicadorMeta.checked == false){
	  		confAlteracao = confirm('Esta operação irá alterar o Valor Limite Emissão para 0,00 do Mês/Ano informado \npara Ação de Cobrança selecionada . \nDeseja continuar?');
	  		
	  		if (confAlteracao){
	  			retorno = 1;
		    	form.action = 'exibirInformarCicloMetaGrupoAction.do?alterarApenasValorLimite=true';
	  			form.submit();
			}else{
				retorno = 2;
			}
		}else if (valorLimite.value > 0 && metaTotalCalculada.value > 0 && form.indicadorMeta.checked && form.indicadorValor.checked == false){
	  		confAlteracao = confirm('Esta operação irá excluir a Meta Total do Mês/Ano informado \npara Ação de Cobrança selecionada . \nDeseja continuar?');
	  		
	  		if (confAlteracao){
	  			retorno = 1;
		    	form.action = 'exibirInformarCicloMetaGrupoAction.do?alterarMetaExcluirCicloMetaGrupo=true';
	  			form.submit();
			}else{
				retorno = 2;
			}
		}else if (valorLimite.value > 0 && metaTotalCalculada.value > 0 && form.indicadorValor.checked && form.indicadorMeta.checked){
	  		confAlteracao = confirm('Esta operação irá excluir a Meta Total/Valor Limite do Mês/Ano informado \npara Ação de Cobrança selecionada . \nDeseja continuar?');
	  		
	  		if (confAlteracao){
	  			retorno = 1;
		    	form.action = 'exibirInformarCicloMetaGrupoAction.do?excluirTudo=true';
	  			form.submit();
			}else{
				retorno = 2;
			}
		}
	  }
	  return retorno;	
	}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<input type="hidden" name="metaTotalCalculada" id="metaTotalCalculada" value="<%=request.getAttribute("metaTotalCalculada")%>">
<input type="hidden" name="valorLimiteEmissao" id="valorLimiteEmissao"  value="<%=request.getAttribute("valorLimiteEmissao")%>">

<html:form
  action="/exibirInformarCicloMetaGrupoAction.do"
  method="post"
  name="CicloMetaGrupoActionForm"
  type="gcom.gui.cobranca.CicloMetaGrupoActionForm"
  onsubmit="return validateCicloMetaGrupoActionForm(this);"
>
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

         <%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      </div></td>
    <td width="605" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>

          <td class="parabg">Informar Metas de Documentos de Cobrança para o Ciclo </td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
          <td width="70%" colspan="2">
          	Para filtrar as metas de cobrança do ciclo, informe os dados abaixo:
          </td>
        </tr>
        
        <tr>
          <td width="20%"><strong>Ação de cobrança:</strong> </td>
          <td width="80%"><html:select property="idCobrancaAcao">
          	  <html:option value="-1">&nbsp;</html:option>
              <html:options collection="colecaoCobrancaAcao" labelProperty="descricaoCobrancaAcao" property="id"/>
             </html:select>
          </td>
        </tr>
        <tr>
          <td width="20%"><strong>Mês/Ano:</strong></td>
          <td width="80%">
          	<html:text property="anoMesCobranca" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
          <td align="right" colspan="4">
	      	<gsan:controleAcessoBotao name="btnFiltrar" value="Filtrar" onclick="javascript:chamarFiltrar();" url="exibirInformarCicloMetaGrupoAction.do"/>
          </td>
        </tr>
        <logic:notEmpty name="CicloMetaGrupoActionForm" property="idCicloMeta">
		<tr>
          <td><strong>Valor Limite</strong></td>
          <td colspan="3">
          	<html:text property="valorLimite" size="14"  maxlength="7" style="text-align:right" onkeyup="javascript:formataValorMonetario(this, 11);"/>
          	<logic:present name="cicloMeta">
          		<logic:notEmpty name="CicloMetaGrupoActionForm" property="valorLimite">
          			&nbsp;&nbsp;<html:checkbox property="indicadorValor" value="2" /><strong> Remover Valor Limite</strong>
          		</logic:notEmpty>
	      	</logic:present>
          </td>
        </tr>
        <tr>
        	<td></td>
        	<td colspan="3">(acima deste valor, o documento sempre será gerado)</td></tr>
        <tr>
          <td><strong>Meta Total:</strong></td>
          <td>
          	<html:text property="metaTotal" size="14"  maxlength="7" style="text-align:right" onkeypress="javascript:blockNumbers(event);" onkeyup="somente_numero_zero_a_nove(this);"/>&nbsp;&nbsp;
          	<logic:present name="helpers">
				<html:checkbox property="indicadorMeta" value="2" /><strong> Remover Meta Total</strong>
		  	</logic:present>
          </td>
		  <logic:present name="helpers">
          	<td align="right"><gsan:controleAcessoBotao name="btnContinuar" value="Regerar Metas" onclick="javascript:regerarMetas();" url="exibirInformarCicloMetaGrupoAction.do"/></td>
	     </logic:present>
          <logic:notPresent name="helpers">
          	<td align="right"><gsan:controleAcessoBotao name="btnContinuar" value="Gerar Metas" onclick="javascript:gerarMetas();" url="exibirInformarCicloMetaGrupoAction.do"/></td>
	     </logic:notPresent>
        </tr>       
		</logic:notEmpty>        
      </table>
      <p>&nbsp;</p>

<%
	int widthDescricaoItem = 370;
%>
  <logic:present name="helpers">

   	<table width="100%">
		<tr bgcolor="#79bbfd">
		  <td width="<%=widthDescricaoItem%>" >
		  	<strong>Gerência Regional<BR>&nbsp;&nbsp;&nbsp;&nbsp;> Unidade de Negócio<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;> Localidade</strong>
		  </td>
		  <td>
		  	<strong>Qtd Imóveis</strong>
		  </td>
		  <td>
		  	<strong>Meta inicial</strong>
		  </td>
		  <td>
		  	<strong>Meta ajustada</strong>
		  </td>		  
		</tr>
		<tr bgcolor="#79bbfd">
		  <td colspan=2 align="right">
		  	<strong>Total:</strong>
		  </td>
		  <td>
		  	<input type="text" size=7 style="text-align:right" disabled="disabled" value="<%=request.getAttribute("metaTotalCalculada")%>">
		  </td>
		  <td>
		  	<input type="text" size=7 style="text-align:right" disabled="disabled" name="metaTotalAjustada" id="metaTotalAjustada" value="<%=request.getAttribute("metaTotalAjustada")%>">
		  </td>		  
		</tr>
		<logic:iterate name="helpers" id="item">
				<tr>
				  <td colspan=4>
				  	<div id='layerHide<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>' style="display:block">
		     			<table width="100%" border="0" bgcolor="#79bbfd">
		   					<tr bgcolor="#79bbfd">
		           				<td width="<%=widthDescricaoItem%>">             
		          					<a href="javascript:extendeTabela('<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>',true);"/>
		          						<bean:write name="item" property="nomeItem"/>
		           					</a>		           					
		                   		</td>
		                   		<td>
		                   			<input type="text" size=7 style="text-align:right" disabled="disabled" value="<bean:write name="item" property="qtdImoveisSituacao" />">
		                   		</td>
		                   		<td>
		                   			<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>_OrgH' 
		                   				value="<bean:write name="item" property="metaOriginal" />" disabled="disabled">
								</td>
								<td>
									<input type="text" size=7 maxlength="8" style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>_Hide' 
							   			id='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>_Hide' 
		                   				value="<bean:write name="item" property="metaAtual"/>" onchange="javascript:redistribuirAlteracao(this);">
		                   		</td>
		                  	</tr>
		                 </table>
		         		</div>
		         		<div id='layerShow<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>' style="display:none">
					    <table width="100%" bgcolor="#79bbfd">						
							<tr bgcolor="#79bbfd">
		           				<td width="<%=widthDescricaoItem%>">             
		          					<a href="javascript:extendeTabela('<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>',false);"/>
		          						<bean:write name="item" property="nomeItem"/>
		           					</a>		           					
		                   		</td>
		                   		<td>
		                   			<input type="text" size=7 style="text-align:right" disabled="disabled" value="<bean:write name="item" property="qtdImoveisSituacao" />">
		                   		</td>		                   		
		                   		<td >
		                   			<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>_Org' 
		                   				value="<bean:write name="item" property="metaOriginal" />" disabled="disabled">
								</td>
								<td>
		                   			<input type="text" size=7 maxlength="8" style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>' 
		                   				id='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/>' 
		                   				value="<bean:write name="item" property="metaAtual"/>"  onchange="javascript:redistribuirAlteracao(this);">		                   				
		                   		</td>
						  	</tr>      
			  <logic:notEmpty name="item" property="subItens">				
				<tr>
					<td colspan="4">
					  		<bean:define name="item" property="subItens" id="helpersUnidades" />
					  		<logic:iterate name="helpersUnidades" id="itemUnegHash">
						  		<bean:define name="itemUnegHash" property="value" id="itemUneg" type="gcom.cobranca.InformarCicloMetaGrupoHelper"/>
                 			<table width="100%" bgcolor="#90c7fc" id='layerHide<bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>' style="display:block">
								<tr bgcolor="#cbe5fe">
			           				<td width="<%=widthDescricaoItem%>">&nbsp;&nbsp;&nbsp;&nbsp;
			          					<a href="javascript:extendeTabela('<bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>',true);"/>
			          						<bean:write name="itemUneg" property="nomeItem"/>
			           					</a>		           					
			                   		</td>
			                   		<td>
			                   			<input type="text" size=7 style="text-align:right" disabled="disabled" value="<bean:write name="itemUneg" property="qtdImoveisSituacao" />">
			                   		</td>		                   		
			                   		<td>
			                   			<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>_OrgH' 
			                   				value="<bean:write name="itemUneg" property="metaOriginal" />" disabled="disabled">
									</td>
									<td>
										<input type="text" size=7 maxlength="8" style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>_Hide' 
										    id='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>_Hide' 
			                   				value="<bean:write name="itemUneg" property="metaAtual"/>"  onchange="javascript:redistribuirAlteracao(this);">
			                   		</td>
			                  	</tr>
			                </table>
                 			<table width="100%" bgcolor="#90c7fc" id='layerShow<bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>' style="display:none">
								<tr bgcolor="#cbe5fe">
			           				<td width="<%=widthDescricaoItem%>">&nbsp;&nbsp;&nbsp;&nbsp;
			          					<a href="javascript:extendeTabela('<bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>',false);"/>
			          						<bean:write name="itemUneg" property="nomeItem"/>
			           					</a>		           					
			                   		</td>
			                   		<td>
			                   			<input type="text" size=7 style="text-align:right" disabled="disabled" value="<bean:write name="itemUneg" property="qtdImoveisSituacao" />">
			                   		</td>			                   		
			                   		<td >
			                   			<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>_Org' 
			                   				value="<bean:write name="itemUneg" property="metaOriginal" />" disabled="disabled">
									</td>
									<td>
										<input type="text" size=7 maxlength="8" style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>' 
											id='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/>'
			                   				value="<bean:write name="itemUneg" property="metaAtual"/>"  onchange="javascript:redistribuirAlteracao(this);">
			                   		</td>
			                  	</tr>

							  <logic:notEmpty name="itemUneg" property="subItens">				
								<tr>
									<td colspan=4>
									    <table width="100%" bgcolor="#90c7fc">
									  		<bean:define name="itemUneg" property="subItens" id="helpersLocalidade" />
									  		<logic:iterate name="helpersLocalidade" id="itemLocHash">
										  		<bean:define name="itemLocHash" property="value" id="itemLoc" type="gcom.cobranca.InformarCicloMetaGrupoHelper"/>
												<tr bgcolor="#ffffff">
							           				<td width="<%=widthDescricaoItem%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							          					<bean:write name="itemLoc" property="nomeItem"/>
							                   		</td>
							                   		<td>
							                   			<input type="text" size=7 style="text-align:right" disabled="disabled" value="<bean:write name="itemLoc" property="qtdImoveisSituacao" />">
							                   		</td>							                   		
							                   		<td>
							                   			<input type="text" size=7 style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/><bean:write name="itemLoc" property="tipoItem"/><bean:write name="itemLoc" property="idItem"/>_Org' 
							                   				value="<bean:write name="itemLoc" property="metaOriginal" />" disabled="disabled">
													</td>
													<td>
														<input type="text" size=7 maxlength="8" style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/><bean:write name="itemLoc" property="tipoItem"/><bean:write name="itemLoc" property="idItem"/>' 
															id='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/><bean:write name="itemLoc" property="tipoItem"/><bean:write name="itemLoc" property="idItem"/>' 
		            					       				value="<bean:write name="itemLoc" property="metaAtual"/>"  onchange="javascript:atualizarUnidade(this);">
														<input type="hidden" size=7 maxlength="8" style="text-align:right" name='<bean:write name="item" property="tipoItem"/><bean:write name="item" property="idItem"/><bean:write name="itemUneg" property="tipoItem"/><bean:write name="itemUneg" property="idItem"/><bean:write name="itemLoc" property="tipoItem"/><bean:write name="itemLoc" property="idItem"/>_Hide'
		            					       				value="<bean:write name="itemLoc" property="metaAtual"/>">
							                   		</td>
							                  	</tr>
						                  	</logic:iterate>							                  	
					                  	</table>
				                  	</td>
			                  	</tr>
			                  	</logic:notEmpty>
			                 </table>
		                  	</logic:iterate>
	                 </td>
                 </tr>
               </logic:notEmpty>
			</table>
			</div>
		</td>
		</tr>      
		<script>
			document.forms[0].btnFiltrar.disabled = true;
			document.forms[0].idCobrancaAcao.disabled = true;
			document.forms[0].anoMesCobranca.disabled = true;
		</script>
      </logic:iterate>                              

	</table>           
 </logic:present>
 	<logic:notEmpty name="CicloMetaGrupoActionForm" property="idCicloMeta">
 	<table table width="100%">
 		<tr>
 			<td width="80%"></td>
	   		<td align="right" colspan="3"><gsan:controleAcessoBotao name="btVoltar" value="Limpar" onclick="javascript:voltarConsulta()" url="exibirInformarCicloMetaGrupoAction.do"/></td>
			<td align="right" colspan="3"><gsan:controleAcessoBotao name="btnConcluir" value="Concluir" onclick="javascript:chamarSalvar();" url="exibirInformarCicloMetaGrupoAction.do"/></td>
	   </tr>
 	</table>
 	</logic:notEmpty>
	</td>
  </tr>

  
</table>
<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>