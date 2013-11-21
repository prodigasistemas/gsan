<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


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
	formName="GerarOSSeletivaFiscalizacaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function limparDecricao() {
		var form = document.GerarOSSeletivaFiscalizacaoActionForm;
		

	 }

	function validarForm(){
		var form = document.forms[0];
		if (validateGerarOSSeletivaFiscalizacaoActionForm(form)){
		
			var value = form.percentualOSgeradas.value;
			value = value.replace(/\./g, '');
			value = value.replace(/,/g, '.');

			if ((value * 1) > 100 || (value * 1) < 2){
				alert ('O valor do Percentual de OS Geradas para Fiscalização deve ser entre 2% e 100%.');
			}else{
				submeterFormPadrao(form);
			}
		}	
	}

	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}

	function limparForm() {
		var form = document.GerarOSSeletivaFiscalizacaoActionForm;
		form.action = 'exibirGerarOSSeletivaFiscalizacaoAction.do?menu=sim';
		form.submit();
	}
	
	function pesquisar() {
		var form = document.GerarOSSeletivaFiscalizacaoActionForm;
		
			form.qtdeOSEncerradasComConclusao.value = "";
			form.qtdeOSEncerradasSemConclusao.value = "";
			form.action = 'exibirGerarOSSeletivaFiscalizacaoAction.do?pesquisar=sim';
			form.submit();
		
	}

	function duplicaLocalidade(form) {
		form.idLocalidadeFinal.value = form.idLocalidadeInicial.value;
	}
	
	function habilitarPesquisaLocalidade(form, tipo) {
		if (form.idLocalidadeInicial.disabled == false) {
		    if(tipo == 'origem'){
				chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', 'indicadorUsoTodos', '1', 275, 480, '',form.idLocalidadeInicial.value);
			}
			else if(tipo == 'destino'){
				chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', 'indicadorUsoTodos', '1', 275, 480, '',form.idLocalidadeFinal.value);
			}
		}	
	}
	
	function limparLocalidadeInicial() {
		var form = document.GerarOSSeletivaFiscalizacaoActionForm;
		if (form.idLocalidadeInicial.disabled == false) {
			form.idLocalidadeInicial.value = "";
			form.descricaoLocalidadeInicial.value = "";
			form.idLocalidadeFinal.value = "";
			form.descricaoLocalidadeFinal.value = "";
			
		}
	}

	function limparLocalidadeFinal() {
		var form = document.GerarOSSeletivaFiscalizacaoActionForm;
		form.idLocalidadeFinal.value = "";
		form.descricaoLocalidadeFinal.value = "";
	}
	function limparServicoTipo() {
		var form = document.GerarOSSeletivaFiscalizacaoActionForm;
		form.idServicoTipo.value = "";
		form.descricaoServicoTipo.value = "";
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'ordemServico') {

	    	form.idOrdemServico.value = codigoRegistro;
	      	form.action='exibirGerarOSSeletivaFiscalizacaoAction.do';
	      	form.submit();
	      
	    }else if (tipoConsulta == 'localidadeOrigem') {
	       form.idLocalidadeInicial.value = codigoRegistro;
		   form.descricaoLocalidadeInicial.value = descricaoRegistro;
		   form.descricaoLocalidadeInicial.style.color = "#000000";
		   if(form.idLocalidadeFinal.value == ""){
		      form.idLocalidadeFinal.value = codigoRegistro;
			  form.descricaoLocalidadeFinal.value = descricaoRegistro;
			  form.descricaoLocalidadeFinal.style.color = "#000000";
		   }
		}else if (tipoConsulta == 'localidadeDestino') {
	    	form.idLocalidadeFinal.value = codigoRegistro;
	    	form.descricaoLocalidadeFinal.value = descricaoRegistro;
	 		form.descricaoLocalidadeFinal.style.color = "#000000";    		

		}else if (tipoConsulta == 'tipoServico') {
	      	form.idServicoTipo.value = codigoRegistro;
	 		form.descricaoServicoTipo.value = descricaoRegistro;
	        form.descricaoServicoTipo.style.color = "#000000";
	    }
	
	}
	
	function habilitaTela(){
	var form = document.forms[0];
	
		if(form.idOrdemServico.value != null && form.idOrdemServico.value != ""){
		
		 form.idLocalidadeInicial.value = "";
		 form.idLocalidadeFinal.value = "";
		 form.descricaoLocalidadeInicial.value = "";
		 form.descricaoLocalidadeFinal.value = "";
		 form.idLocalidadeInicial.disabled = true;
		 form.idLocalidadeFinal.disabled = true;
		 form.idServicoTipo.value = "";
	 	 form.descricaoServicoTipo.value = "";
	 	 form.idServicoTipo.disabled = true;
	 	 
	 	 form.grupoCobranca.value = "";
		 form.grupoCobranca.disabled = true;
		 form.gerenciaRegional.value = "";
		 form.gerenciaRegional.disabled = true;
		 form.unidadeNegocio.value = "";
		 form.unidadeNegocio.disabled = true;
		 
		}else{
		 
		 form.idLocalidadeInicial.disabled = false;
		 form.idLocalidadeFinal.disabled = false;
	 	 form.idServicoTipo.disabled = false;
		 form.grupoCobranca.disabled = false;
		 form.gerenciaRegional.disabled = false;
		 form.unidadeNegocio.disabled = false;
		 
		}

		if((form.qtdeOSEncerradasComConclusao.value != null && form.qtdeOSEncerradasComConclusao.value != "")||
		(form.qtdeOSEncerradasSemConclusao.value != null && form.qtdeOSEncerradasSemConclusao.value != "")){
			form.btGerar.disabled = false;
		}else{
			form.btGerar.disabled = true;
		
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="habilitaTela();">

<html:form action="/gerarOSSeletivaFiscalizacaoAction.do"
	name="GerarOSSeletivaFiscalizacaoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.GerarOSSeletivaFiscalizacaoActionForm" method="post"
	onsubmit="return validateGerarOSSeletivaFiscalizacaoActionForm(this);">

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
					<td class="parabg">Gerar OS Seletiva de Fiscalização</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>

					<td colspan="3">Informe os dados abaixo para selecionar as ordens de serviço a serem fiscalizadas:</td>
				</tr>
				<tr>
					<td height="10"><strong>Ordem de Serviço:</strong></td>

					<td><span class="style2"> 
						<html:text property="idOrdemServico" size="8" maxlength="9"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarOSSeletivaFiscalizacaoAction.do', 'idOrdemServico','Ordem de Serviço');return isCampoNumerico(event);"
						onkeyup="javascript:limparDecricao();habilitaTela();" /> <a
						href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 600, 500, '',document.forms[0].idOrdemServico);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Ordem de Serviço" /></a> 
						<logic:present name="OrdemServioInexistente">
							<html:text property="nomeOrdemServico" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="37" maxlength="40" />
						</logic:present> 
						<!--<logic:notPresent name="OrdemServioInexistente">
							<html:text property="nomeOrdemServico" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="37" maxlength="40" />
						</logic:notPresent> <a href="javascript:limparForm();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
					</a> </span>--></td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Grupo de Cobrança:</strong></td>
					<td colspan="2">
						<html:select property="grupoCobranca" style="width: 230px;" tabindex="1" onkeyup="javascript:habilitaTela();">
						<logic:present name="collectionCobrancaGrupo" scope="session">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="collectionCobrancaGrupo" labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td colspan="2">
						<html:select property="gerenciaRegional" style="width: 230px;" tabindex="2">
						<logic:present name="gerenciasRegionais" scope="session">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="gerenciasRegionais" labelProperty="nome" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Unidade de Negócio:</strong></td>
					<td colspan="2">
						<html:select property="unidadeNegocio" style="width: 230px;" tabindex="3">
						<logic:present name="colecaoUnidadeNegocio" scope="session">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				
				
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					<td colspan="2"><html:text property="idLocalidadeInicial" size="4" maxlength="4" tabindex="4"
						onkeyup="javascript:duplicaLocalidade(document.forms[0]);return validaEnterComMensagem(event, 'exibirGerarOSSeletivaFiscalizacaoAction.do', 'idLocalidadeInicial','Localidade Inicial');"  
						onkeypress="document.forms[0].descricaoLocalidadeInicial.value='';document.forms[0].descricaoLocalidadeFinal.value='';return isCampoNumerico(event);"/>
					<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'origem');" >
						<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Localidade Inicial"/></a>		
					<logic:present name="localidadeInicialInexistente" scope="request">
						<html:text property="descricaoLocalidadeInicial" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> 
					<logic:notPresent
						name="localidadeInicialInexistente" scope="request">
						<html:text property="descricaoLocalidadeInicial" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent><a href="javascript:limparLocalidadeInicial();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Localidade Final:</strong></td>
					<td colspan="2"><html:text property="idLocalidadeFinal" size="4" maxlength="4" tabindex="5"
						onkeyup="javascript:return validaEnterComMensagem(event, 'exibirGerarOSSeletivaFiscalizacaoAction.do', 'idLocalidadeFinal','Localidade Final');"
						onkeypress="document.forms[0].descricaoLocalidadeFinal.value='';return isCampoNumerico(event);"/>
					<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'destino');" >
						<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Localidade Final"/></a>		
					 <logic:present
						name="localidadeFinalInexistente" scope="request">
						<html:text property="descricaoLocalidadeFinal" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="localidadeFinalInexistente" scope="request">
						<html:text property="descricaoLocalidadeFinal" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent><a href="javascript:limparLocalidadeInicial();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				
				<tr> 
	            <td><strong>Tipo de Serviço:</strong></td>
	            <td colspan="2">
	               <html:text maxlength="4" property="idServicoTipo" size="4" tabindex="6"
					 onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarOSSeletivaFiscalizacaoAction.do', 'idServicoTipo', 'Tipo de Serviço');document.forms[0].descricaoServicoTipo.value = '';return isCampoNumerico(event);" />
						<a href="javascript:abrirPopup('exibirPesquisarTipoServicoAction.do',400,600);">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Serviço" /></a>
						<logic:notEmpty name="GerarOSSeletivaFiscalizacaoActionForm" property="idServicoTipo">
							<html:text property="descricaoServicoTipo" size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
						<logic:empty name="GerarOSSeletivaFiscalizacaoActionForm" property="idServicoTipo">
						 <html:text property="descricaoServicoTipo" size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty> 
						<a href="javascript:limparServicoTipo();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
				</td>
              </tr>

				<tr>
					<td><strong>Quantidade de Dias de Encerramento da OS:</strong></td>
					<td><html:text maxlength="4" property="qtdeDiasEncerramentoOS"
						size="4" tabindex="7" onkeypress="return isCampoNumerico(event);" /></td>
					<td aligns="right">
						<input type="button" name="Button2" class="bottonRightCol" value="Pesquisar"
						onClick="javascript:pesquisar();" />
					</td>
				</tr>
				<tr>
					<td height="10" colspan="3"><hr></td>
				</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td width="60%"><strong>Quantidade de OS Encerradas Sem Conclusão do Serviço:</strong></td>
					<td colspan="2"><html:text maxlength="4" property="qtdeOSEncerradasSemConclusao"
						size="4" tabindex="7" onkeypress="return isCampoNumerico(event);"/></td>
				</tr>
				<tr>
					<td><strong>Quantidade de OS Encerradas Com Conclusão do Serviço:</strong></td>
					<td colspan="2"><html:text maxlength="4" property="qtdeOSEncerradasComConclusao"
						size="4" tabindex="7" onkeypress="return isCampoNumerico(event);"/></td>
				</tr>
				
				<tr>
					<td><strong>Percentual de OS Encerradas c/Conclusão do Serviço para Fiscalizar:</strong></td>
					<td colspan="2"><html:text maxlength="3" property="percentualOSgeradas"
						size="6" tabindex="7" onkeypress="return isCampoNumerico(event);" /> %</td>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Desfazer" 
						align="left" onclick="javascript:limparForm();"> 
						<input name="Button" type="button" class="bottonRightCol" value="Cancelar" 
						align="left" onclick="window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td width="53" height="24" align="right">
					
					<logic:equal name="habilitaBtGerar" value="1" scope="request">
						<input type="button" name="Button2" class="bottonRightCol" value="Gerar OS"
						onClick="javascript:validarForm(document.forms[0])" />
					</logic:equal>
					<logic:notEqual name="habilitaBtGerar" value="1" scope="request">
						<input type="button" id="btGerar" name="Button2" class="bottonRightCol" value="Gerar OS"
						disabled="disabled" />
					</logic:notEqual>
						
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
<script language="JavaScript">
	<!--
	habilitaTela();
	//-->
	</script>
</body>
</html:html>
