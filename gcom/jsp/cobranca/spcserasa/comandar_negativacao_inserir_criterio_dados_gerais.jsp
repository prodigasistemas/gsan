<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.cobranca.NegativacaoCriterioCpfTipo"%>
<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>

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
	  if(form.simular[1].checked){
	    if(form.executarSimulacao[0].checked && (form.descricaoComandoSimulado.value == null || form.descricaoComandoSimulado.value == "")){
	      alert("Pesquisar Comando de Simulação");
	      return false;
	    }
	  }
      return validateRequired(form)
   } 
   
    function required () { 
       this.aa = new Array("titulo", "Informe Título.", new Function ("varName", " return this[varName];"));
       this.ab = new Array("solicitacao", "Descricão da Solicitação.", new Function ("varName", " return this[varName];"));
       this.ac = new Array("usuario", "Informe Usuário Responsável.", new Function ("varName", " return this[varName];"));
    } 
    
    var comando = 0;
	
	function setComandoNegativacao(tipo) {
		comando = tipo;
	}
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	var form = document.forms[0];
    	
	    if (tipoConsulta == 'usuario') {
	      form.usuario.value = codigoRegistro;
	      form.nomeUsuario.value = descricaoRegistro;
	      form.nomeUsuario.style.color = "#000000";
	    } else if (tipoConsulta == 'comandoNegativacao') {
	      if (comando == 1) {
      		form.action = 'inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosGeraisAction&idComandoNegativacao='+codigoRegistro
      		form.submit();
	      } else if (comando == 2) {
      		form.action = 'inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosGeraisAction&idComandoNegativacaoSimulado='+codigoRegistro
      		form.submit();		      
	      }
	      comando = 0;
	    }    
  	}

	  function limparUsuario() {
			var form = document.forms[0];
			
	      	form.usuario.value = '';
		    form.nomeUsuario.value = '';
		
			form.usuario.focus();
	  }
	  
	function removerTitularidade(idTitularidade){
	  var formulario = document.forms[0]; 
	    formulario.action =  'inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosGeraisAction&idTitularidadeRemover='+ idTitularidade;
	    formulario.submit();
    }   
    
    
    function pesquisarComandoNegativacao(){
	  var formulario = document.forms[0]; 
	  abrirPopup ('exibirPesquisarComandoNegativacaoAction.do?menu=ok', 400, 750);
    }      
    
    function desabilitarBotaoPesquisar(){
	  var form = document.forms[0]; 
	  if(form.executarSimulacao[0].checked){
	  	 form.ButaoPesquisar.disabled=false;     	     	     
	  }else{
	  	 form.idComandoSimulado.value = "";
	     form.descricaoComandoSimulado.value = "";	  
		 form.ButaoPesquisar.disabled=true;
	  }
	}
  

</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}'); desabilitarBotaoPesquisar();limitTextArea(document.forms[0].titulo, 100, document.getElementById('utilizado'), document.getElementById('limite'));">

<div id="formDiv">
<html:form action="/inserirComandoNegativacaPorCriterioWizardAction" method="post">

<html:hidden property="idNegativador"/>
<html:hidden property="idComandoSimulado"/>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1"/>


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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Comando de Negativação - Por Critério - Dados Gerais</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para determinar a negativação a ser comandada, informe os dados gerais abaixo:</td>
      </tr>
      <tr>
      	<td width="35%"><strong>Negativador:</strong></td>
       	<td colspan="2">
			<html:text property="nomeNegativador" size="50" maxlength="60" tabindex="4" readonly="true"
			    style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
	  </tr>
	  <tr>
	    <td colspan="3">
	      <div align="right"><input name="Button32232" type="button"
						class="bottonRightCol" value="Pesquisar Comandos"
						onclick="setComandoNegativacao(1); abrirPopup('exibirPesquisarComandoNegativacaoAction.do?menu=ok', 400, 750);" /></div>
	    </td>
	  </tr>
	  <tr>
         <td colspan="3"><hr></td>
      </tr>
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Título:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:textarea property="titulo" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].titulo, 100, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
			<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Descrição da Solicitação:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:textarea property="solicitacao" cols="40" rows="4" /><br>
		</td>
      </tr>      
      
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Simular a Negativação:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="simular" value="1" tabindex="1" onclick="redirecionarSubmit('inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosGeraisAction&determinarData=ok');"/><strong>Sim
			<html:radio property="simular" value="2" tabindex="2" onclick="redirecionarSubmit('inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosGeraisAction&determinarData=ok');"/>Não</strong>
		</td>
      </tr>
      <logic:notPresent name="habilitarExecutarSimulacao">
      	<html:hidden property="executarSimulacao"/>
		<html:hidden property="descricaoComandoSimulado"/>
      </logic:notPresent>
      <logic:present name="habilitarExecutarSimulacao">
            <tr>
      	<td HEIGHT="30" width="35%"><strong>Executar o comando a partir de uma simulação:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="executarSimulacao" value="1" tabindex="1" onclick="javascript:desabilitarBotaoPesquisar();redirecionarSubmit('inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosGeraisAction&determinarData=ok');"/><strong>Sim
			<html:radio property="executarSimulacao" value="2" tabindex="2" onclick="javascript:desabilitarBotaoPesquisar();redirecionarSubmit('inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosGeraisAction&determinarData=ok');"/>Não</strong>
		</td>
      </tr>  
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Comando de Simulação:</strong></td>
        <td>
			<html:text property="descricaoComandoSimulado" size="47" maxlength="100" tabindex="4" readonly="true"
			    style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
		<td>
			<div align="right"><input name="ButaoPesquisar" type="button"
						class="bottonRightCol" value="Pesquisar" onclick="setComandoNegativacao(2); abrirPopup('exibirPesquisarComandoNegativacaoAction.do?menu=ok', 400, 750);" /></div>
		</td>
      </tr> 
      </logic:present> 
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Data Prevista para execução:</strong></td>
        <td colspan="2">
			<html:text property="dataPrevista" size="11" maxlength="10" tabindex="12" onkeyup="mascaraData(this, event)" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
      </tr>
      <tr>
		<td width="35%"><strong>Usuário Responsável:<font color="#FF0000">*</font></strong></td>
		<td colspan="2">
			<html:text property="usuario" size="9" maxlength="9"
				onkeypress="validaEnter(event, 'inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosGeraisAction&pesquisarUsuario=ok', 'usuario');return isCampoNumerico(event);" />
			<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?limpaForm=OK', 300, 350);">
			   <img src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
			width="23" height="21" style="cursor:hand;"	alt="Pesquisar"></a> 
			<logic:present name="corUsuario">
				<logic:equal name="corUsuario" value="exception">
					<html:text property="nomeUsuario" size="35"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:equal>
				<logic:notEqual name="corUsuario" value="exception">
					<html:text property="nomeUsuario" size="35"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEqual>
			</logic:present> 
			<logic:notPresent name="corUsuario">
				<logic:empty name="InserirComandoNegativacaoActionForm" property="nomeUsuario">
					<html:text property="nomeUsuario" size="35"
						value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:empty>
				<logic:notEmpty name="InserirComandoNegativacaoActionForm" property="nomeUsuario">
					<html:text property="nomeUsuario" size="35"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEmpty>
			</logic:notPresent> 

			<a href="javascript:limparUsuario();"> 
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
		</td>
	  </tr>
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Quantidade Máxima de inclusões:</strong></td>
        <td colspan="2">
		  <html:text property="qtdMaximaInclusao" size="8" maxlength="5" tabindex="3"
		  onkeypress="return isCampoNumerico(event);"/>
		</td>
      </tr>	  	  
	  <tr>
         <td colspan="3"><hr></td>
      </tr>
      <tr>
        <td HEIGHT="30" width="35%"><strong>Titularidade do CPF/CNPJ da Negativação:<font color="#FF0000">*</font></strong></td>
        <td>
          <logic:present name="colecaoCPFTipo">
		  <html:select property="titularidadeNegativacao" tabindex="10">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<html:options collection="colecaoCPFTipo" labelProperty="descricaoTipoCpf" property="id"/>
		  </html:select>
		  </logic:present>
		</td>
		<td align="right">
	      <input type="button" class="bottonRightCol" value="Adicionar" tabindex="3" id="botaoEndereco" onclick="redirecionarSubmit('inserirComandoNegativacaPorCriterioWizardAction.do?action=exibirInserirComandoNegativacaoDadosGeraisAction&adicionarTitularidade=ok');">	      							      		
	    </td>
      </tr>	  
	  <tr>
		<td colspan="3">

		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				<table width="100%" bgcolor="#99CCFF">
					<tr bgcolor="#99CCFF">
						<td align="center" width="15%"><strong>Remover</strong></td>
						<td>
						  <div align="left" width="55%"><strong>Titularidade do CPF/CNPJ da Negativação</strong></div>
						</td>
						<td>
						  <div align="center" width="15%"><strong>Ordem</strong></div>
						</td>
						<td>
						  <div align="center" width="15%"><strong>Coincidente</strong></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			<%int cont = 0;%>

			<logic:present name="colecaoNegativacaoCriterioCpfTipo">
			<tr>
				<td>

				<div style="width: 100%; height: 100; overflow: auto;">

				<table width="100%" align="center" bgcolor="#99CCFF">

					<logic:iterate name="colecaoNegativacaoCriterioCpfTipo" id="negativacaoCriterioCpfTipo"
						type="NegativacaoCriterioCpfTipo">

						<%cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
						<%} else {%>
						<tr bgcolor="#FFFFFF">
						<%}%>
							<td align="center" width="15%" valign="middle">
								<a href="javascript:removerTitularidade(<%="" + negativacaoCriterioCpfTipo.getCpfTipo().getId()%>)">
									<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
								</a>
							</td>
							<td width="55%"><bean:write name="negativacaoCriterioCpfTipo"
								property="cpfTipo.descricaoTipoCpf" />
							</td>	
							<%if(((Collection) session.getAttribute("colecaoNegativacaoCriterioCpfTipo")).size() == 1){ %>
								<td width="15%" align="center">
									<input type="text" style="text-align: center;font-size: xx-small;" 
									size="4" maxlength="2" align="center" disabled="true"/>
								</td>	
								<td width="15%" align="center">
									<div align="center"><input type="checkbox" 
									     disabled="true"/>
									</div>
								</td>							  						
							<%}else{ %>
								<%if (negativacaoCriterioCpfTipo.getNumeroOrdemSelecao() == null) {%>
								<td width="15%" align="center">
									<input type="text" style="text-align: center;font-size: xx-small;" 
									size="4" maxlength="2" align="center"
									name="ordem<bean:write name="negativacaoCriterioCpfTipo" property="cpfTipo.id"/>" 
									value="" 
									/>
								</td>
								<%} else {%>
								<td width="15%" align="center">
									<input type="text" style="text-align: center;font-size: xx-small;" 
									size="4" maxlength="2" align="center" onkeypress="return isCampoNumerico(event);"
									name="ordem<bean:write name="negativacaoCriterioCpfTipo" property="cpfTipo.id"/>" 
									value="<%="" + negativacaoCriterioCpfTipo.getNumeroOrdemSelecao()%>" 
									/>
								</td>							
								<%}%>	
								<%if (negativacaoCriterioCpfTipo.getIndicadorCoincidente() == 2) {%>
								<td width="15%" align="center">
									<div align="center"><input type="checkbox" 
									name="coincidente<bean:write name="negativacaoCriterioCpfTipo" property="cpfTipo.id"/>" 
									value="<%="" + negativacaoCriterioCpfTipo.getCpfTipo().getId()%>" />
									</div>
								</td>
								<%} else {%>
								<td width="15%" align="center">
									<div align="center"><input type="checkbox" checked
									name="coincidente<bean:write name="negativacaoCriterioCpfTipo" property="cpfTipo.id"/>" 
									value="<%="" + negativacaoCriterioCpfTipo.getCpfTipo().getId()%>" />
									</div>
								</td>	
								<%}%>														
							<%} %>											
						</tr>
					</logic:iterate>
					</table>
					</div>
					</td>
					</tr>
				   </logic:present>
				</table>
			</td>
	  </tr>
      <tr>
      	<td colspan="3" height="10"></td>
      </tr>
      <tr>
        <td colspan="3">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=1" />
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
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirComandoNegativacaPorCriterioWizardAction.do?concluir=true&action=inserirComandoNegativacaoDadosGeraisAction'); }
</script>

</body>
</html:html>

