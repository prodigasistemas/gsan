<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoNegativacaoActionForm" dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">

	function validateInserirComandoNegativacaoActionForm(form) {
		var f = document.forms[0];
	
		if(verificarSelecaoTipoRelacao()){
			if (f.indicadorImovelCategoriaPublico[0].checked) {
				if (f.idCliente.value == "" || f.tipoRelacao.value == -1) {
					alert("Informe Cliente e Tipo da Relação.");
					return false;
				}else {
					return true;
				}
				
			}else {
				return true;
			}
		}
	}
	
	function required () { 
       this.aa = new Array("idCliente", "Informe Cliente.", new Function ("varName", " return this[varName];"));
       this.ab = new Array("tipoRelacao", "Informe Tipo de Relacao.", new Function ("varName", " return this[varName];"));
    } 
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

      if (tipoConsulta == 'cliente') {
        form.idCliente.value = codigoRegistro;
        form.descricaoCliente.value = descricaoRegistro;
        form.descricaoCliente.style.color = "#000000";        
      }
   }
   
   function limparCliente(){
   	 var form = document.forms[0];
   	 form.idCliente.value = "";
	 form.descricaoCliente.value = "";
	 form.idCliente.focus();
   }
   
   function verificarSelecaoTipoRelacao(){
   	 var form = document.forms[0]; 
   	 if(form.tipoRelacao.value != -1 && (form.idCliente.value == null || form.idCliente.value == "")){
   	   alert("Tipo de Relação não pode ser informado.");
     }else{
		return true;
	  }
   }
   
   function SubmitForm(){
	  var formulario = document.forms[0]; 
	  formulario.action =  'inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosImovelAction';
	  formulario.submit();     
   }
   
   	function desabilitarCliente(){
	  var formulario = document.forms[0]; 
	  if(formulario.tipoCliente.selectedIndex != -1){
		  if(formulario.tipoCliente.value != -1){   
		     formulario.idCliente.disabled = true;
		     formulario.idCliente.value = "";
		     formulario.descricaoCliente.value = "";
		     formulario.tipoRelacao.selectedIndex = 0;     
		     formulario.tipoRelacao.disabled = true;	   	       	     	     	     
		  }else{
		  	 formulario.idCliente.disabled = false;     
		     formulario.tipoRelacao.disabled  = false;	    		         		  
		  }
	   }
	  }
	  
	function atualizaListSubcategoria() {
		var formulario = document.forms[0]; 
	  	formulario.action =  'inserirComandoNegativacaPorCriterioWizardAction.do?destino=3&action=inserirComandoNegativacaoDadosDebitoAction&atualizaListSubcategoria=s';
		formulario.submit();
	}
	  
	  function habilitaSituacaoCobranca(habilita,ehSituacaoEspecial){
	  	var form = document.forms[0];
	  	
	  	if(habilita){
			if(ehSituacaoEspecial){
				form.cobrancaSituacaoTipo.disabled = false;
			}else{
				form.cobrancaSituacao.disabled = false;
			}
	  	}else{
			if(ehSituacaoEspecial){
				form.cobrancaSituacaoTipo.disabled = true;
			}else{
				form.cobrancaSituacao.disabled = true;
			}
	  	}
	  }
   
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/inserirComandoNegativacaPorCriterioWizardAction" method="post">

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=3"/>


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

	<td width="610" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Comando de Negativação - Por Critério - Dados Imóvel</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="2">Para determinar a negativação a ser comandada, informe os dados abaixo:</td>
      </tr>
      <tr>
      	<td width="30%"><strong>Negativador:</strong></td>
       	<td>
			<html:text property="nomeNegativador" size="50" maxlength="60" tabindex="4" readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
	  </tr>
	  <tr>
         <td colspan="2"><hr></td>
      </tr>
   	  <tr>
		<td width="30%"><strong>Cliente:</strong></td>
		<td>
		<logic:present name="desabilitar">         
		<html:text maxlength="3"
			tabindex="1" property="idCliente" size="3" disabled="true"/>		
		   <img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Localidade" />
		</logic:present>
		<logic:notPresent name="desabilitar">  
		<html:text property="idCliente" size="8"
			maxlength="8" tabindex="2"
			onkeypress="validaEnterComMensagem(event, 'inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosImovelAction', 'idCliente','Cliente');return isCampoNumerico(event);" />

		<a
			href="javascript:abrirPopup('exibirPesquisarClienteAction.do');">
		<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Cliente"
			border="0" height="21" width="23"></a> 
		</logic:notPresent>
			<logic:present name="funcionalidadeEncontrada">

			<logic:equal name="funcionalidadeEncontrada" value="exception">
				<html:text property="descricaoCliente" size="38"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:equal>

			<logic:notEqual name="funcionalidadeEncontrada" value="exception">
				<html:text property="descricaoCliente" size="38" 
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEqual>
		</logic:present> <logic:notPresent name="funcionalidadeEncontrada">
			<logic:empty name="InserirComandoNegativacaoActionForm"
				property="idCliente">
				<html:text property="descricaoCliente" size="38" 
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:empty>

			<logic:notEmpty name="InserirComandoNegativacaoActionForm"
				property="idCliente">
				<html:text property="descricaoCliente" size="38"
					readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEmpty>
		</logic:notPresent> <a href="javascript:limparCliente()">
		<img border="0" title="Apagar"
			src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
		</td>
	  </tr>
      <tr>
        <td HEIGHT="30" width="30%"><strong>Tipo de Relação:</strong></td>
        <td>
          <logic:present name="colecaoClienteRelacaoTipo">
		  <html:select property="tipoRelacao" tabindex="10">
		  	<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
		  	<logic:notPresent name="desabilitar">  
			  <html:options collection="colecaoClienteRelacaoTipo" labelProperty="descricao" property="id"/>
			</logic:notPresent>
		  </html:select>
		  </logic:present>
		</td>
      </tr>	
      <tr>
         <td colspan="3"><hr></td>
      </tr>      
      <tr>
      	<td HEIGHT="30" width="30%"><strong>Imóvel com Sit. Especial de Cobrança:<font color="#FF0000">*</font></strong></td>
        <td>
            <logic:present name="desabilitar">
			  <html:radio property="imovSitEspecialCobranca" value="1" tabindex="1" disabled="true"/><strong>Sim
			  <html:radio property="imovSitEspecialCobranca" value="2" tabindex="2" disabled="true"/>Não</strong>              
            </logic:present>
          	<logic:notPresent name="desabilitar">
			  <html:radio property="imovSitEspecialCobranca" value="1" tabindex="1" onchange="javascript:habilitaSituacaoCobranca(true,true);"/><strong>Sim
			  <html:radio property="imovSitEspecialCobranca" value="2" tabindex="2" onchange="javascript:habilitaSituacaoCobranca(false,true);"/>Não</strong>
			</logic:notPresent>
		</td>
      </tr> 
      
      
	<tr> 
    	<td><strong>Situa&ccedil;&atilde;o Especial de Cobrança:</strong></td>
        <td colspan="6">
        	<span class="style2"><strong>
			
			<logic:notPresent name="desabilitar">
				<html:select property="cobrancaSituacaoTipo" 
					style="width: 350px; height:100px;"
					multiple="true">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoCobrancaoSituacaoTipo" scope="session">
						<html:options collection="colecaoCobrancaoSituacaoTipo" 
							labelProperty="descricao" 
							property="id" />
					</logic:present>
				</html:select>
			</logic:notPresent>

		 	<logic:present name="desabilitar">
				<html:select property="cobrancaSituacaoTipo" 
					style="width: 350px; height:100px;"
					multiple="true"
					disabled="true">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoCobrancaoSituacaoTipo" scope="session">
						<html:options collection="colecaoCobrancaoSituacaoTipo" 
							labelProperty="descricao" 
							property="id" />
					</logic:present>
				</html:select>
			 </logic:present>
			
            </strong></span>
        </td>
    </tr>
      
      
      <tr>
      	<td HEIGHT="30" width="30%"><strong>Imóvel com Sit. de Cobrança:<font color="#FF0000">*</font></strong></td>
        <td>
			<html:radio property="imovSitCobranca" value="1" tabindex="1" onchange="javascript:habilitaSituacaoCobranca(true,false);"/><strong>Sim
			<html:radio property="imovSitCobranca" value="2" tabindex="2" onchange="javascript:habilitaSituacaoCobranca(false,false);"/>Não</strong>
		</td>
      </tr>     
      
      
	<tr> 
    	<td><strong>Situa&ccedil;&atilde;o de Cobrança:</strong></td>
        <td colspan="6">
        	<span class="style2"><strong>
				<html:select property="cobrancaSituacao" 
					style="width: 350px; height:100px;"
					multiple="true">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoCobrancaoSituacao" scope="session">
								<html:options collection="colecaoCobrancaoSituacao"
									labelProperty="descricao" 
									property="id" />
					</logic:present>
				</html:select>
            </strong></span>
        </td>
    </tr>
      
      
      
      <tr>
      	<td HEIGHT="30" width="30%"><strong>Imóvel com Baixa Renda:<font color="#FF0000">*</font></strong></td>
        <td>
			<html:radio property="indicadorBaixaRenda" value="1" tabindex="1" /><strong>Sim
			<html:radio property="indicadorBaixaRenda" value="2" tabindex="2" />Não</strong>
		</td>
      </tr>   
      <tr>
      	<td HEIGHT="30" width="30%"><strong>Imóvel de Categoria Público:<font color="#FF0000">*</font></strong></td>
        <td>
			<html:radio property="indicadorImovelCategoriaPublico" value="1" tabindex="1" onclick="atualizaListSubcategoria();" /><strong>Sim
			<html:radio property="indicadorImovelCategoriaPublico" value="2" tabindex="2"  onclick="atualizaListSubcategoria();" />Não</strong>
		</td>
      </tr>
	  <tr>
         <td colspan="2"><hr></td>
      </tr>
      <tr> 
        <td width="30%"><strong>Situação da Ligação de Água:</strong></td>
        <td colspan="6"><span class="style2"><strong>
         <logic:notPresent name="desabilitar">
		  <html:select property="ligacaoAguaSituacao"  style="width: 350px; height:100px;" multiple="true"> 
		  	<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoLigAguaSituacao" scope="session">
				<html:options collection="colecaoLigAguaSituacao" labelProperty="descricao" property="id" />
			</logic:present>
		  </html:select>  
		 </logic:notPresent> 
		 <logic:present name="desabilitar">
		  <html:select property="ligacaoAguaSituacao"  style="width: 350px; height:100px;" multiple="true" disabled="true"> 
		  	<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoLigAguaSituacao" scope="session">
				<html:options collection="colecaoLigAguaSituacao" labelProperty="descricao" property="id" />
			</logic:present>
		  </html:select>  
		 </logic:present>
          </strong></span></td>
      </tr>  
      <tr> 
        <td width="30%"><strong>Situação da Ligação de Esgoto:</strong></td>
        <td colspan="6"><span class="style2"><strong>
         <logic:notPresent name="desabilitar">
		  <html:select property="ligacaoEsgotoSituacao"  style="width: 350px; height:100px;" multiple="true"> 
		  	<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoLigEsgotoSituacao" scope="session">
				<html:options collection="colecaoLigEsgotoSituacao" labelProperty="descricao" property="id" />
			</logic:present>
		  </html:select>  
		 </logic:notPresent> 
		 <logic:present name="desabilitar">
		  <html:select property="ligacaoEsgotoSituacao"  style="width: 350px; height:100px;" multiple="true" disabled="true"> 
		  	<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoLigEsgotoSituacao" scope="session">
				<html:options collection="colecaoLigEsgotoSituacao" labelProperty="descricao" property="id" />
			</logic:present>
		  </html:select>  
		 </logic:present>
          </strong></span></td>
      </tr> 
      <tr> 
        <td width="30%"><strong>Subcategoria:</strong></td>
        <td colspan="6"><span class="style2"><strong>
         <logic:notPresent name="desabilitar">
		  <html:select property="subCategoria"  style="width: 350px; height:100px;" multiple="true"> 
		  	<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoSubcategoria" scope="session">
				<html:options collection="colecaoSubcategoria" labelProperty="descricao" property="id" />
			</logic:present>
		  </html:select>  
		 </logic:notPresent> 
		 <logic:present name="desabilitar">
		  <html:select property="subCategoria"  style="width: 350px; height:100px;" multiple="true" disabled="true"> 
		  	<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<logic:present name="colecaoSubcategoria" scope="session">
				<html:options collection="colecaoSubcategoria" labelProperty="descricao" property="id" />
			</logic:present>
		  </html:select>  
		 </logic:present>
          </strong></span></td>
      </tr>     
      <tr> 
        <td width="30%"><strong>Perfil Imóvel:</strong></td>
        <td colspan="6"><span class="style2"><strong>
          <logic:notPresent name="desabilitar">
		    <html:select property="perfilImovel"  style="width: 350px; height:100px;" multiple="true"> 
			  <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			  <logic:present name="colecaoPerfilImovel" scope="session">
				<html:options collection="colecaoPerfilImovel" labelProperty="descricao" property="id" />
			  </logic:present>
		    </html:select>  
		  </logic:notPresent>
		  <logic:present name="desabilitar">
		    <html:select property="perfilImovel"  style="width: 350px; height:100px;" multiple="true" disabled="true"> 
			  <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			  <logic:present name="colecaoPerfilImovel" scope="session">
				<html:options collection="colecaoPerfilImovel" labelProperty="descricao" property="id" />
			  </logic:present>
		    </html:select> 		  
		  </logic:present>
          </strong></span></td>
      </tr>     
      <tr> 
        <td width="30%"><strong>Tipo de Cliente:</strong></td>
        <td colspan="6"><span class="style2"><strong>
          <logic:notPresent name="desabilitar">        
		    <html:select property="tipoCliente"  style="width: 350px; height:100px;" multiple="true" onchange="javascript:desabilitarCliente();"> 
			  <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			  <logic:notPresent name="desabilitar">
			  <logic:present name="colecaoTipoCliente" scope="session">
				<html:options collection="colecaoTipoCliente" labelProperty="descricao" property="id" />
			  </logic:present>
			  </logic:notPresent>
		    </html:select>  
		   </logic:notPresent>
		   <logic:present name="desabilitar">
		    <html:select property="tipoCliente"  style="width: 350px; height:100px;" multiple="true" disabled="true"> 
			  <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			  <logic:present name="colecaoTipoCliente" scope="session">
				<html:options collection="colecaoTipoCliente" labelProperty="descricao" property="id" />
			  </logic:present>
		    </html:select>  		   
		   </logic:present>
          </strong></span></td>
      </tr>  
	  <tr>
         <td colspan="2"><hr></td>
      </tr>                             
      <tr>
        <td colspan="2">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=3" />
			</div>
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

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan//inserirComandoNegativacaPorCriterioWizardAction.do?concluir=true&action=inserirComandoNegativacaoDadosImovelAction'); }
</script>

</body>
</html:html>