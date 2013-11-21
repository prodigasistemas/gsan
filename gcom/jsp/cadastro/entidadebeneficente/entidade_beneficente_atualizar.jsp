<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
	
	cliente             = document.getElementById("cliente").value;
	debitoTipo          = document.getElementById("debitoTipo").value;
	empresas            = document.getElementById("empresa");	
	
	descricaoEmpresa    = empresas.options[empresas.selectedIndex].text;
	
	erro = false;
	erro2 = false;
	
  inicioMesAnoAdesao = document.getElementById("inicioMesAnoAdesao").value;
  fimMesAnoAdesao = document.getElementById("fimMesAnoAdesao").value;	
  
  inicioAnoAdesao = inicioMesAnoAdesao.substring(3);
  inicioMesAdesao = inicioMesAnoAdesao.substring(0,2);	
  
  fimAnodesao = fimMesAnoAdesao.substring(3);
  fimMesAdesao   = fimMesAnoAdesao.substring(0,2);
  
  if( inicioAnoAdesao > fimAnodesao  ){
   erro2 = true;
  }
  else{
  if( inicioMesAdesao > fimMesAdesao ){
     erro2 = true;
  }
  }
  if( inicioMesAdesao != "" || fimMesAdesao != "" ){
  erro2 = false;
  }
	
	if( cliente == "" ){
	alert("Informe cliente");
	erro = true;
	cliente.focus();
	}
	if( debitoTipo == "" ){
	alert("Informe Tipo de Débito");
	erro = true;
	debitoTipo.focus();
	}
	if( descricaoEmpresa == "" ){
	alert("Informe Uma empresa");
	erro = true;
	empresas.focus();
	}

	if( erro == false && erro2 == false){
	form.submit();
	}
	else if(erro2 == true){
		alert("Fim de Adesão tem que ser maior que Início de Adesão");
	document.getElementById("inicioMesAnoAdesao").focus();
	}

	}
	
	function limparCliente(){
	document.getElementById("cliente").value= "";
	document.getElementById("nomeCliente").value= "";
	document.getElementById("cliente").focus();
	}
	
    function limparDebitoTipo(){
	document.getElementById("debitoTipo").value= "";
	document.getElementById("debitoTipoDescricao").value= "";
	document.getElementById("debitoTipo").focus();
	}

	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "&" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	
	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    if (tipoConsulta == 'cliente') {
	    	document.getElementById('cliente').value = codigoRegistro;
	    	document.getElementById('nomeCliente').value = descricaoRegistro;
	    	document.getElementById('nomeCliente').style.color = '#000000';
	    	document.getElementById('debitoTipo').focus();
	    } 
	    else if (tipoConsulta == 'tipoDebito') {
	    	document.getElementById('debitoTipo').value = codigoRegistro;
	    	document.getElementById('debitoTipoDescricao').value = descricaoRegistro;
	    	document.getElementById('debitoTipoDescricao').style.color = '#000000';
	    	document.getElementById('empresa').focus();
	    }
	}
	

	

</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
	
<html:form action="/atualizarEntidadeBeneficenteAction.do" 
	method="post"
	name="AtualizarEntidadeBeneficenteActionForm"
	type="gcom.gui.cadastro.entidadebeneficente.AtualizarEntidadeBeneficenteActionForm"
	onsubmit="">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />

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

				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Atualizar Entidade Beneficente</td>
						<td width="11" valign="top">
							<img border="0"
								src="imagens/parahead_right.gif" /></td>
					</tr>
	
				</table>
				
				<p>&nbsp;</p>
		
				<table width="100%" border="0">
					
					<tr>
						<td colspan="2">Para atualizar uma Entidade Beneficente , informe os dados
						abaixo:</td>
					</tr>
					
					<tr>
						<td><strong>C&oacute;digo:</strong></td>
						<td>
							<bean:write name="AtualizarEntidadeBeneficenteActionForm" 
								property="id" />
						</td>
					</tr>
					
					<tr>
						<td><strong>Cliente:<font color="#FF0000">*</font></strong></td>
						<td>
						<html:text maxlength="9" 
								property="cliente.id"
								size="9" 
								styleId="cliente"
								onkeyup="javascript:limparUnidadeTecla();" 
								onkeypress="validaEnter(event, 'exibirAtualizarEntidadeBeneficenteAction.do?enter=sim', 'cliente','Cliente');document.getElementById('nomeCliente').value = '';
							return isCampoNumerico(event);" />
							<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 800, 490, '',document.getElementById('cliente'));">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Cliente" /></a> 
							
							<logic:present name="idClienteEncontrado" scope="request">
								<html:text maxlength="30" 
									property="cliente.nome" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: black"
									size="40"
									styleId="nomeCliente" />
							</logic:present> 
							
							<logic:present name="idClienteNaoEncontrado" scope="request">
								<html:text maxlength="30" 
									property="cliente.nome" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red"
									size="40"
									styleId="nomeCliente" />
							</logic:present> 
							
							<a href="javascript:limparCliente();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>
						</td>
					</tr>					
					
					<tr>
						<td><strong>Tipo de D&eacute;bito:<font color="#FF0000">*</font></strong></td>
						<td>
						
								<html:text maxlength="4" 
								property="debitoTipo.id"
								size="4" 
								styleId="debitoTipo"
								onkeyup="javascript:limparUnidadeTecla();" 
								onkeypress="javascript:validaEnter(event, 'exibirAtualizarEntidadeBeneficenteAction.do?enter=sim', 'debitoTipo', 'Tipo de Debito');
								document.getElementById('debitoTipoDescricao').value = '';return isCampoNumerico(event);" />
							<a href="javascript:abrirPopup('exibirPesquisarTipoDebitoAction.do', 'tipoDebito', null, null, 400, 800, '',document.getElementById('debitoTipo'));">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Debito" /></a> 
							
							<logic:present name="idDebitoTipoEncontrado" scope="request">
								<html:text maxlength="30" 
									property="debitoTipo.descricao" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: black"
									size="40"
									styleId="debitoTipoDescricao" />
							</logic:present> 
							
							<logic:present name="idDebitoNaoTipoEncontrado" scope="request">
								<html:text maxlength="30" 
									property="debitoTipo.descricao" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red"
									size="40"
									styleId="debitoTipoDescricao" />
							</logic:present> 
							
							<a href="javascript:limparDebitoTipo();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>
						</td>
						</tr>
						
					<tr>
						<td>
						<strong>Empresa:<font color="#ff0000">*</font></strong></td>
						<td>
						<html:select property="empresa.id" styleId="empresa">
								<option value=""></option>
								<html:options name="request" 
									collection="colecaoEmpresa"
									labelProperty="descricao" 
									property="id" />
						</html:select>
						</td>
					</tr>
					
					<tr>
					    <td height="31">
							<strong>In&iacute;cio Ades&atilde;o:</strong>
					    </td>
						<td>
							<html:text property="inicioMesAnoAdesao" 
								size="7" 
								styleId="inicioMesAnoAdesao"
								maxlength="7" 
								tabindex="1"
								onkeypress="javascript:return isCampoNumerico(event);" onkeyup="mascaraAnoMes(this, event);"
								 />
							mm/aaaa	
						</td>
					</tr>	
					
					
					<tr>
					    <td height="31">
							<strong>Fim Ades&atilde;o:</strong>
					    </td>
						<td>
							<html:text property="fimMesAnoAdesao" 
								size="7" 
								maxlength="7" 
								styleId="fimMesAnoAdesao"
								tabindex="1"
								onkeypress="javascript:return isCampoNumerico(event);" onkeyup="mascaraAnoMes(this, event);"
								 />
							mm/aaaa	
						</td>
					</tr>	
					
				<tr height="30">
					<td>
						<strong>Indicador de Uso:</strong>
					</td>
					<td>
						<html:radio  property="indicadorUso"
							value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
						<strong>Ativo</strong> 
						<html:radio  property="indicadorUso"
							value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
						<strong>Inativo</strong>
						<html:radio    property="indicadorUso"
							value="3"  />
						<strong>Todos</strong>
					</td>
				</tr>		
				
					<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td align="right">
							<div align="left"><strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</div>
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0">
					<tr>
						<td>
							<logic:present name="manter" scope="session">
								<input type="button" 
									name="ButtonReset" 
									class="bottonRightCol" 
									value="Voltar"
									onClick="javascript:window.location.href='/gsan/exibirManterEntidadeBeneficenteAction.do'">
							</logic:present>
	
							<logic:notPresent name="manter" scope="session">
								<input type="button" 
									name="ButtonReset"  
									class="bottonRightCol" 
									value="Voltar"
									onClick="javascript:window.location.href='/gsan/exibirFiltrarEntidadeBeneficenteAction.do'">
							</logic:notPresent> 
							
							<input name="Button" 
								type="button" 
								class="bottonRightCol"
								value="Desfazer" 
								align="left"
								onclick="window.location.href='<html:rewrite page="/exibirAtualizarEntidadeBeneficenteAction.do?desfazer=sim"/>'">
							
							<input name="Button" 
								type="button" 
								class="bottonRightCol"
								value="Cancelar" 
								align="left"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
							
						<td align="right">
							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Atualizar" 
								align="right"
								onClick="javascript:validarForm(document.forms[0]);">
						</td>
					</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>