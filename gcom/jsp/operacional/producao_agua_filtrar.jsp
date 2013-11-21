<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>



<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarProducaoAguaActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function filtrar(formulario){
		if(validateFiltrarProducaoAguaActionForm(formulario)){
    		submeterFormPadrao(formulario);
		}
	}

	function limpar(tipo){
		var form = document.forms[0];
	
		switch (tipo){
			case 1:
		 		form.localidadeID.value = "";
				form.localidadeDescricao.value = "";
				form.localidadeDescricao.style.color = "#000000";
		   
		   //Coloca o foco no objeto selecionado
		   		form.localidadeID.focus();
		   		break;
			case 2:
		   		form.localidadeDescricao.value = "";
		   		form.localidadeDescricao.style.color = "#000000";
		   
		   //Coloca o foco no objeto selecionado
				form.localidadeID.focus();
		   		break; 
		    default:
          		break;
		}
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else {
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else {
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'localidade') {
			form.localidadeID.value = codigoRegistro;
			form.localidadeDescricao.value = descricaoRegistro;
			form.localidadeDescricao.style.color = "#000000";
		} 
	}
	
    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/filtrarProducaoAguaAction"
	name="FiltrarProducaoAguaActionForm"
	type="gcom.gui.operacional.FiltrarProducaoAguaActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="615" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Produ&ccedil;&atilde;o de &Aacute;gua</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar uma produ&ccedil;&atilde;o de &aacute;gua, informe os dados
					abaixo:</td>
					<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td>
						<html:text property="id" 
							size="2" 
							maxlength="2" />
							<br>
					</td>
					<td>&nbsp;</td>
				</tr>
		       
		       				<tr>
					<td><strong>Mês/Ano de Refer&ecirc;ncia: </strong></td>
					<td><html:text maxlength="7" property="anoMesReferencia" size="7"
						onkeyup="mascaraAnoMes(this, event);" /> &nbsp; mm/aaaa</td>
				</tr>
				<tr>
					
    				<td width="132"><strong>Localidade: </strong></td>
					<td colspan="3">
						<html:text property="localidadeID" 
							size="5"
							maxlength="3" 
							tabindex="15"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarProducaoAguaAction.do?objetoConsulta=1', 'localidadeID','Localidade');"
							onkeyup="limpar(2);" /> 

							<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?menu=sim',275,480);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Localidade" /></a> 
						
						<logic:present name="corLocalidade">

						<logic:equal name="corLocalidade" value="exception">
							<html:text property="localidadeDescricao" size="42"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corLocalidade" value="exception">
							<html:text property="localidadeDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corLocalidade">

						<logic:empty name="FiltrarProducaoAguaActionForm"
							property="localidadeID">
							<html:text property="localidadeDescricao"
								value="" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarProducaoAguaActionForm"
							property="localidadeID">
							<html:text property="localidadeDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limpar(1);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>
				
				<tr>
					<td><strong>Volume Produzido: </strong></td>
					<td><strong> <html:text property="volumeProduzido" size="16"
						maxlength="15" onkeyup="formataValorMonetario(this, 13);"/> </strong></td>
				</tr>
		       
		       	<tr>
					<td>
						<input name="Button" 	
							type="button" 
							class="bottonRightCol"
							value="Limpar" 
							align="left"
							onclick="window.location.href='/gsan/exibirFiltrarProducaoAguaAction.do?menu=sim'"
							tabindex="8">
					</td>
					<td align="right" colspan="2">&nbsp;</td>
					<td width="65" align="right">
						<input name="Button2" 
							type="button"
							class="bottonRightCol" 
							value="Filtrar" 
							align="right"
							onClick="javascript:filtrar(document.forms[0]);" tabindex="9">
					</td>
				</tr>

			</table>

			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
