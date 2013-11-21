<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/menus-taglib.tld" prefix="menu"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

</head>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarFaturamentoImediatoAjusteActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

var tipoPesquisaRota = "ENTREGA";

function validarForm(form){
	
	if(validateFiltrarFaturamentoImediatoAjusteActionForm(form)){
				
		if(form.rotaId.value != "" || form.imovelId.value != "" || form.faturamentoGrupo.value != "-1"){
			botaoAvancarTelaEspera('/gsan/filtrarFaturamentoImediatoAjusteAction.do');
		}else {
			alert("Informe pelo menos uma das opções: Grupo, Rota, Imóvel");
		}
	}
}

function limpar(){
	var form = document.forms[0];
	form.mesAnoReferencia.value = "";
	form.faturamentoGrupo.value = "-1";
	form.rotaId.value = "";
	form.imovelId.value = "";
	form.imovelInscricao.value = "";
	//form.rotaId.value = "";
	form.codigoRota.value = "";
}

function limparImovel(){
	var form = document.forms[0];
	form.imovelId.value = "";
	form.imovelInscricao.value = "";
}

function limparRota(){
	var form = document.forms[0];

	//form.rotaId.value = "";
	form.codigoRota.value = "";
}

function validaEnterImovel(event, caminho, campo) {
	var form = document.forms[0];
	validaEnter(event, caminho, campo);
	
}

function validaEnterRota(event, caminho, campo) {
	var form = document.forms[0];
	validaEnter(event, caminho, campo);
	
}

function habilitarPesquisaImovel(form) {
	if (form.imovelId.disabled == false) {
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
	}	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
	    
	if (tipoConsulta == 'imovel') {
      form.imovelId.value = codigoRegistro;
      form.imovelInscricao.value = descricaoRegistro;
    } else { 
      form.rotaId.value = codigoRegistro;
    }  
}

function receberRota(codigoRota,destino) {
 	  var form = document.forms[0];
	  form.rotaId.value = codigoRota;		
	  form.action = 'exibirFiltrarFaturamentoImediatoAjusteAction.do?objetoConsulta=2';
	  form.submit();
}


</script>
<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/filtrarFaturamentoImediatoAjusteAction" 
		   type="gcom.gui.faturamento.FiltrarFaturamentoImediatoAjusteActionForm"
		   name="FiltrarFaturamentoImediatoAjusteActionForm"	 
		method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
			<div align="center"><%@ include
				file="/jsp/util/informacoes_usuario.jsp"%></div>
			</td>
			<td width="625" valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Filtro de Faturamento Imediato Ajuste</td>

					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="80%">Preencha os campos para pesquisar Faturamento Imediato Ajuste:</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="200">
						<strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong>
					</td>

					<td colspan="3">
						<html:text  property="mesAnoReferencia" 
									size="7" 
									maxlength="7" 
									tabindex="1"
									onkeyup="mascaraAnoMes(this, event);"
									onkeypress="return isCampoNumerico(event);"
						/> (mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Grupo:<font color="#FF0000"></font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="faturamentoGrupo" style="width: 230px;" tabindex="2">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoFaturamentoGrupo" 
										   scope="request">
										   <html:options collection="colecaoFaturamentoGrupo"
														 labelProperty="descricao" 
														 property="id" 
										   />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="25%"><strong>Imóvel:</strong></td>
					<td width="75%">
						<html:text  maxlength="9"
									name="FiltrarFaturamentoImediatoAjusteActionForm"
									property="imovelId" 
									size="9"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="javascript:return validaEnterImovel(event, 'exibirFiltrarFaturamentoImediatoAjusteAction.do?objetoConsulta=1', 'imovelId');" />
						<a href="javascript:habilitarPesquisaImovel(document.forms[0]);">
							<img width="23" 
								 height="21"
								 src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								 border="0" 
								 title="Pesquisar Imóvel"/></a> 
						<logic:present name="imovelNaoEncontrado" 
		   		      				   scope="request">
							<input type="text" 
								   name="imovelInscricao" 
								   size="30" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="atencao.imovel.inexistente"/>
							"/>
		                 </logic:present>
		                 <logic:notPresent name="imovelNaoEncontrado" 
		                      			   scope="request">
                        	<html:text property="imovelInscricao" 
                        			   size="30" 
                        			   readonly="true" 
                        			   style="background-color:#EFEFEF; border:0; color: #000000"
                        	/>
		                 </logic:notPresent>
						<a href="javascript:limparImovel();"> 
							<img border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar"></a>
					</td>
				</tr>
				
				<tr>
					<td height="24"><strong>Rota:<font
					color="#FF0000"></font></strong></td>
					<td>
						<html:hidden property="rotaId"/>
						<html:text maxlength="4" 
							size="4" 
						   property="codigoRota" 
						   readonly="true" 
						   style="background-color:#EFEFEF; border:0; color: #000000" 
						   onkeypress="return isCampoNumerico(event);"
						   onkeyup="javascript:return validaEnterRota(event, 'exibirFiltrarFaturamentoImediatoAjusteAction.do', 'rotaId');"/>
						
						<a href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim&caminhoRetornoTelaPesquisa=exibirFiltrarFaturamentoImediatoAjusteAction.do', 400, 800);">
						
							<img border="0" title="Pesquisar Rota" src="<bean:message key="caminho.imagens"/>pesquisa.gif" /></a>
						<a href="javascript:limparRota()"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" 
							title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="0"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="javascript:limpar();"></td>
					<td align="right">
						<input  align="right"
								class="bottonRightCol"
								type="button"
								value="Filtrar" 
								onclick="javascript:validarForm(document.forms[0]);"/>
					</td>

				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>	
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
			</table>
			<p>&nbsp;</p>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>

</html:form>
</div>
</html:html>
