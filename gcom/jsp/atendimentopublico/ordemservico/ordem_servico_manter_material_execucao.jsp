<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OsAtividadeMaterialExecucao"%>

<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterMaterialExecucaoOSActionForm"/>

<script>

function limpar(){

	var form = document.forms[0];
	
	form.idMaterialNaoProgramado.value = "";
	form.descricaoMaterialNaoProgramado.value = "";
			   
	//Coloca o foco no objeto selecionado
	form.idMaterialNaoProgramado.focus();
}

function limparDescricao(){
	
	var form = document.forms[0];
	form.descricaoMaterialNaoProgramado.value = "";
}

function adicionar(){
	var form = document.forms[0];
	
	if (validateManterMaterialExecucaoOSActionForm(form)){
		
		if (form.idMaterialProgramado.value.length < 1 && 
			form.idMaterialNaoProgramado.value.length < 1){
			alert("Informe Material Padrão ou Outro Material");
			form.idMaterialProgramado.focus();
		}
		else if (form.idMaterialProgramado.value.length > 0 && 
				 form.idMaterialNaoProgramado.value.length > 0){
			alert("Informe Material Padrão ou Outro Material");
			form.idMaterialProgramado.focus();
		}
		else{
			form.action = "/gsan/exibirManterMaterialExecucaoOSAction.do?adicionarMaterial=OK";
			form.submit();
		}
	}
} 

function remover(objetoRemocao){
  redirecionarSubmit("/gsan/exibirManterMaterialExecucaoOSAction.do?removerMaterial=" + objetoRemocao);
}


function validarCamposDinamicos(form){
 
 	var camposValidos = true;
 
 	for (i=0; i < form.elements.length; i++) {
	    if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
    		
			switch (form.elements[i].id){
			
				case "material":
				
					var value = form.elements[i].value;
					value = value.replace(/\./g, '');
					value = value.replace(/,/g, '.');
				
					if (value.length < 1){
						alert("Informe Quantidade.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(value) || value < 0){
						alert("Quantidade deve somente conter números positivos.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZeroDecimal(form.elements[i], "Quantidade")){
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (obterQuantidadeInteiro(form.elements[i].value) > 4 ||
				 		obterQuantidadeFracao(form.elements[i].value) > 2){
						alert("Quantidade inválida");
						form.elements[i].focus();
						camposValidos = false;
					}
					
					converteVirgula(form.elements[i]);
					
					break;
					
				default:
					break;
			}	
    	}
    	
    	if (!camposValidos){
    		break;
    	}
    }
    
    return camposValidos;
}


function atualizarQuantidades(){

	var form = document.forms[0];
	
	if (validarCamposDinamicos(form)){
		redirecionarSubmit("/gsan/exibirManterMaterialExecucaoOSAction.do?atualizarQuantidade=OK");
	}
} 
 
</script>
</head>

<logic:present name="voltar">
	<body leftmargin="0" topmargin="0" onload="window.location.href='${sessionScope.caminhoRetornoManterMaterial}';">
</logic:present>

<logic:notPresent name="voltar">
	
	<logic:present name="inserir">
		<body leftmargin="0" topmargin="0" onload="window.close();">	
	</logic:present>
	
	<logic:notPresent name="inserir">
		<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(680, 450);setarFoco('${requestScope.nomeCampo}');">
	</logic:notPresent>

</logic:notPresent>



<html:form action="/exibirManterMaterialExecucaoOSAction" method="post">

<html:hidden property="idAtividade"/>

<table width="652" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="652" valign="top" class="centercoltext">
    <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Manter Material de Execução da Ordem de Serviço</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="4">
          	Apropriar material de execução para a atividade da Ordem de Serviço:
          </td>
        </tr>
        <tr>
          <td width="25%"><strong>Número da OS: </strong></td>
          <td width="75%" colspan="3">
          	<html:text property="numeroOS" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td width="25%"><strong>Atividade: </strong></td>
          <td width="75%" colspan="3">
          	<html:text property="descricaoAtividade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td colspan="4" height="10"></td>
        </tr>
        <tr>
          <td width="25%"><strong>Material Padrão:</strong></td>
          <td width="75%" colspan="3">
          	<html:select property="idMaterialProgramado" style="width: 200px;" tabindex="3">
				<html:option value="">&nbsp;</html:option>
				<logic:present name="colecaoMaterialProgramdo">
					<html:options collection="colecaoMaterialProgramdo" labelProperty="descricao" property="id"/>
				</logic:present>
			</html:select>
          </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="3"><strong>OU</strong></td>
        </tr>
        <tr>
      		<td WIDTH="25%"><strong>Outro Material:</strong></td>
        	<td width="75%" colspan="3">
        	
        		<html:text property="idMaterialNaoProgramado" size="4" maxlength="5" tabindex="4" onkeypress="limparDescricao();validaEnterComMensagem(event, 'exibirManterMaterialExecucaoOSAction.do', 'idMaterialNaoProgramado', 'Outro Material');"/>
				<a href="javascript:redirecionarSubmit('exibirManterMaterialExecucaoOSAction.do?pesquisarMaterial=OK');">
				<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0"></a>
	
				<logic:present name="corMaterial">
	
					<logic:equal name="corMaterial" value="exception">
						<html:text property="descricaoMaterialNaoProgramado" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
	
					<logic:notEqual name="corMaterial" value="exception">
						<html:text property="descricaoMaterialNaoProgramado" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>
	
				</logic:present>
	
				<logic:notPresent name="corMaterial">
	
					<logic:empty name="ManterMaterialExecucaoOSActionForm" property="idMaterialNaoProgramado">
						<html:text property="descricaoMaterialNaoProgramado" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					<logic:notEmpty name="ManterMaterialExecucaoOSActionForm" property="idMaterialNaoProgramado">
						<html:text property="descricaoMaterialNaoProgramado" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
					
	
				</logic:notPresent>
	        	
	        	<a href="javascript:limpar();">
	        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0"></a>
	        
	       </td>
	  	</tr>
	  	<tr>
          <td colspan="4" height="10"></td>
        </tr>
        <tr>
          <td><strong>Material de execução:</strong></td>
          <td colspan="3" align="right"><input type="button" class="bottonRightCol" value="Adicionar"
			tabindex="5" style="width: 80px" onclick="adicionar();" name="botaoAdicionar"></td>
        </tr>
        <tr>
      		<td colspan="4">
      		
      			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF" height="18">
								<td width="10%" align="center"><strong>Remover</strong></td>
								<td width="70%" align="center"><strong>Material</strong></td>
									<td width="20%" align="center"><strong>Qtd</strong></td>
						</tr>
						</table>
					</td>
				</tr>
      			</table>
      			
      		
      			<logic:present name="colecaoManterDadosAtividadesOrdemServicoHelper">
      			
      				<logic:notEmpty name="colecaoManterDadosAtividadesOrdemServicoHelper">
      				
      				
      				<logic:iterate name="colecaoManterDadosAtividadesOrdemServicoHelper" id="dadosAtividadesOrdemServico" type="ManterDadosAtividadesOrdemServicoHelper">
      				
      				<logic:equal name="dadosAtividadesOrdemServico" property="ordemServicoAtividade.ordemServico.id" value="${requestScope.numeroOS}">
      				
      				<logic:equal name="dadosAtividadesOrdemServico" property="ordemServicoAtividade.atividade.id" value="${requestScope.idAtividade}">
      				
      					<logic:present name="dadosAtividadesOrdemServico" property="colecaoOsAtividadeMaterialExecucao">
      					
      					<bean:define name="dadosAtividadesOrdemServico" property="colecaoOsAtividadeMaterialExecucao" type="java.util.Collection" id="colecaoOsAtividadeMaterialExecucao" />
      					
      					
	      				<!-- Coleção Material -->
	      					
	      				<div style="width: 100%; height: 100; overflow: auto;">	
	      				
      					<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								
									
									
									<table width="100%" align="center" bgcolor="#99CCFF">
									<!--corpo da segunda tabela-->
									
										<% String cor = "#cbe5fe";%>
									
										<logic:iterate name="colecaoOsAtividadeMaterialExecucao" id="osAtividadeMaterialExecucao" type="OsAtividadeMaterialExecucao">
															
											<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
													cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF" height="18">	
											<%} else{	
													cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe" height="18">		
											<%}%>
															
												<td width="10%" align="center">
																			
													<a href="javascript:if(confirm('Confirma remoção?')){remover(<%="" + osAtividadeMaterialExecucao.getMaterial().getId() %>);}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
																		
												</td>
																	
												<td width="70%" align="left">
													<bean:write name="osAtividadeMaterialExecucao" property="material.descricao"/>
												</td>
												
												<td width="20%" align="center">
													
													<logic:present name="osAtividadeMaterialExecucao" property="quantidadeMaterial">
														<INPUT TYPE="text" NAME="material<%="" + osAtividadeMaterialExecucao.getMaterial().getId() %>"
															size="6" id="material" maxlength="7"
															value="<%="" + Util.formataBigDecimal(osAtividadeMaterialExecucao.getQuantidadeMaterial(), 2, false) %>"
															style="text-align: right;" />
													</logic:present>
												
													<logic:notPresent name="osAtividadeMaterialExecucao" property="quantidadeMaterial">
														<INPUT TYPE="text" NAME="material<%="" + osAtividadeMaterialExecucao.getMaterial().getId() %>"
															size="6" id="material" maxlength="7" style="text-align: right;" onkeyup="formataValorQuantidade(this, 7)" />
													</logic:notPresent>
												</td>
																	
											</tr>
															
										</logic:iterate>
									
									</table>
									
									 
									
								</td>
							</tr>
							</table>
							
							</div>
							
							</logic:present>
							
							<!-- FIM Coleção Material -->
											
										
      				</logic:equal>
      				
      				</logic:equal>
      				
      				</logic:iterate>
					
					</logic:notEmpty>
				
				
				
				</logic:present>
				
				
			</td>
      	</tr>
        
        <tr>
          <logic:present name="caminhoRetornoManterMaterial">
          		<td colspan="4" height="20"><input type="button" class="bottonRightCol" value="Voltar"
				style="width: 80px" onclick="atualizarQuantidades();"></td>
          </logic:present>
          
          <logic:notPresent name="caminhoRetornoManterMaterial">
          		<td colspan="4" height="20" align="right"><input type="button" class="bottonRightCol" value="Inserir"
				style="width: 80px" onclick="atualizarQuantidades();"></td>
          </logic:notPresent>
        </tr>
      </table>
      
	</td>
  </tr>
</table>

</body>
</html:form>
</html:html>



