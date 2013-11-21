<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="entidadeBeneficenteActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	
<script type="text/javascript">
	function limparCliente() {
		document.getElementById('idCliente').value = '';
		document.getElementById('nomeCliente').value = '';
		document.getElementById('idCliente').focus();
	}
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    if (tipoConsulta == 'cliente') {
	    	document.getElementById('idCliente').value = codigoRegistro;
	    	document.getElementById('nomeCliente').value = descricaoRegistro;
	    	document.getElementById('nomeCliente').style.color = '#000000';
	    	document.getElementById('idTipoDebito').focus();
	    } else if (tipoConsulta == 'tipoDebito') {
	    	document.getElementById('idTipoDebito').value = codigoRegistro;
	    	document.getElementById('descricaoTipoDebito').value = descricaoRegistro;
	    	document.getElementById('descricaoTipoDebito').style.color = '#000000';
	    	document.getElementById('idEmpresa').focus();
	    }
	}
	function limparTipoDebito() {
		document.getElementById('idTipoDebito').value = '';
		document.getElementById('descricaoTipoDebito').value = '';
		document.getElementById('idTipoDebito').focus();
	}

function validarForm(form){
	submeter = false;

  idCliente = document.getElementById("idCliente").value;
  idTipoDebito = document.getElementById("idTipoDebito").value;
  inicioAdesao = document.getElementById("inicioMesAnoAdesao").value;
  fimAdesao = document.getElementById("fimMesAnoAdesao").value;
  empresas = document.getElementById("empresa");
  indicadorUso = document.getElementById("indicadorDeUsoTodos").checked;
  indicadorUso1 = document.getElementById("indicadorDeUsoTodos1").checked;
  indicadorUso2 = document.getElementById("indicadorDeUsoTodos2").checked;
  
  descricaoEmpresa    = empresas.options[empresas.selectedIndex].text;
  
	erro = 0;
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
  if( inicioAnoAdesao != "" || fimAnodesao != "" ){
  erro2 = false;
  }
	
	if( idCliente == "" ){
	erro += 1 ;
	}
	if( idTipoDebito == "" ){
	erro += 1 ;
	}
	if( inicioAdesao == "" ){
	erro += 1 ;
	}
	if( fimAdesao == "" ){
	erro += 1 ;
	}
	if( descricaoEmpresa == "" ){
    erro += 1 ;
	}
	if(indicadorUso == false){
	erro += 1;
	}
	if(indicadorUso1 == false){
	erro += 1;
	}
	if(indicadorUso2 == false){
	erro += 1;
	}
	
	if( erro == 8 ){
	alert("Informe pelo menos um campo!");
	document.getElementById("idCliente").focus();
	}
	else if(erro2 == true){
	alert("Fim de Adesão tem que ser maior que Início de Adesão");
	document.getElementById("inicioMesAnoAdesao").focus();
	}
	else if (erro2 == false){
	form.submit();
	}
	
	}
	
function setaIndicadorUsoDefault(){
document.getElementById("indicadorDeUsoTodos").checked = true;
}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');setaIndicadorUsoDefault();">

<html:form 
	action="/filtrarEntidadeBeneficenteAction.do"
	name="filtrarEntidadeBeneficenteActionForm"
	type="gcom.gui.cadastro.entidadebeneficente.FiltrarEntidadeBeneficenteActionForm">

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Entidade Beneficente</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para filtrar uma entidade beneficente, informe os dados abaixo:</td>
          <td width="100" align="right"><html:checkbox property="atualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>


				<tr>
					<td><strong>Cliente:<span style="color: red"></span></strong></td>

					<td colspan="3">
					
					
					
						<html:text property="cliente.id" size="9" 
							maxlength="9" tabindex="1"
							onkeypress="validaEnter(event, 'exibirFiltrarEntidadeBeneficenteAction.do?objetoConsulta=1&limparCampos=1', 'idCliente','Cliente');document.getElementById('nomeCliente').value = '';
							return isCampoNumerico(event);"
							 styleId="idCliente"  />
							<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 800, 490, '',document.getElementById('idCliente'));">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0"></a> 
							
						<logic:notPresent name="idClienteEncontrado" >
							<html:text property="cliente.nome" readonly="true"
								style="background-color:#EFEFEF; border:0; color: red"
								size="30" maxlength="30" styleId="nomeCliente"
							/>
						</logic:notPresent> 
						
						<logic:present name="idClienteEncontrado"	>
							<html:text property="cliente.nome" size="30" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: black" styleId="nomeCliente"
						 />
						</logic:present> 
						
						<a href="javascript:limparCliente();">
						 <img border="0" title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
						</a>
					
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Tipo de D&eacute;bito:<span style="color: red"></span></strong>
					</td>
					
					<td colspan="3">
						
						<html:text property="debitoTipo.id" 
							size="4" 
							maxlength="4"
							tabindex="2"
							onkeyup="validaEnter(event, 'exibirFiltrarEntidadeBeneficenteAction.do?objetoConsulta=1&limparCampos=1', 'idTipoDebito');document.getElementById('descricaoTipoDebito').value = '';"
							styleId="idTipoDebito"
                            onkeypress="javascript:return isCampoNumerico(event);"
                            />
						
						<a href="javascript:abrirPopup('exibirPesquisarTipoDebitoAction.do', 'tipoDebito', null, null, 400, 800, '',document.getElementById('idTipoDebito'));">
							<img width="23" 
								height="21"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								alt="Pesquisar" 
								border="0" /></a>
						
						
						<logic:present name="idDebitoTipoEncontrado" scope="request">
							<html:text property="debitoTipo.descricao" 
								size="30" 
								maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: black"
								styleId="descricaoTipoDebito" />
						</logic:present> 
						
						<logic:notPresent name="idDebitoTipoEncontrado" scope="request">
							<html:text property="debitoTipo.descricao" 
								size="30" 
								maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red"
								styleId="descricaoTipoDebito" />
						</logic:notPresent> 
												
						<a href="javascript:limparTipoDebito();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" />
						</a>

					</td>
				</tr>
				
				<tr>
					<td><strong>Empresa:<span style="color: red"></span></strong></td>
					<td colspan="3"><html:select property="empresa.id" tabindex="3" styleId="empresa">
						<option></option>
						<html:options name="request" collection="colecaoEmpresa"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
						
						
				<tr>
					<td><strong>In&iacute;cio de Ades&atilde;o:<span style="color: red"></span></strong></td>
					<td colspan="3">
					<html:text property="inicioMesAnoAdesao" 
								size="7" 
								maxlength="7"
								styleId="inicioMesAnoAdesao"
								onkeypress="javascript:return isCampoNumerico(event);"
								onkeyup="mascaraAnoMes(this, event);" />mm/aaaa
					</td>
				</tr>
				
				<tr>
					<td><strong>Fim de Ades&atilde;o:<span style="color: red"></span></strong></td>
					<td colspan="3">
					<html:text property="fimMesAnoAdesao" 
								size="7" 
								maxlength="7"
								styleId="fimMesAnoAdesao"
								onkeypress="javascript:return isCampoNumerico(event);"
								onkeyup="mascaraAnoMes(this, event);" />mm/aaaa
					</td>
				</tr>
				
				<tr height="30">
					<td>
						<strong>Indicador de Uso:</strong>
					</td>
					<td>
						<html:radio styleId="indicadorDeUsoTodos2" name="filtrarEntidadeBeneficenteActionForm" property="indicadorUso"
							value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
						<strong>Ativo</strong> 
						<html:radio styleId="indicadorDeUsoTodos1" name="filtrarEntidadeBeneficenteActionForm" property="indicadorUso"
							value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
						<strong>Inativo</strong>
						<html:radio styleId="indicadorDeUsoTodos" name="filtrarEntidadeBeneficenteActionForm"  property="indicadorUso"
							value="3"  />
						<strong>Todos</strong>
					</td>
				</tr>				

				<tr>
					<td>&nbsp;</td>
					<td align="left"><span style="color: red">*</span> Campo Obrigatório</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
		
				<tr>
				<td colspan="3">
				<table width="100%" border="0">
				<tr>
				<td align="left" width="50%">
				<input type="button" name="ButtonReset" class="bottonRightCol"
				value="Limpar" align="left"
				onclick="window.location.href='<html:rewrite page="/exibirFiltrarEntidadeBeneficenteAction.do?desfazer=sim"/>'">
				</td>
				<td align="right">
				<gsan:controleAcessoBotao name="Button" value="Filtrar"
				onclick="javascript:validarForm(document.forms[0]);" url="filtrarEntidadeBeneficenteAction.do"/>
				</td>
				</tr>	
				</table>
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
