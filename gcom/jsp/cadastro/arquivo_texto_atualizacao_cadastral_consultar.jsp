<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<html:javascript staticJavascript="false" formName="ConsultarArquivoTextoAtualizacaoCadastralActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<style>
.styleFontePequena {
	font-size: 9px;
    color: #000000;
	font: Verdana, Arial, Helvetica, sans-serif
}
				   
.styleFontePeqNegrito {
	font-size: 11px;
    color: #000000;
	font-weight: bold;
}
</style>

<script language="JavaScript">

  function validaForm(form){
	if(testarCampoValorZero(document.ConsultarArquivoTextoAtualizacaoCadastralActionForm.idEmpresa, 'Empresa')) {
		if(validateConsultarArquivoTextoAtualizacaoCadastralActionForm(form)){
				form.action = 'consultarArquivoTextoAtualizacaoCadastralAction.do' ;
	    		submeterFormPadrao(form);
	     }
	  }
  }
  
  function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidadeOrigem') {
      		
      		form.idLocalidade.value = codigoRegistro;
	  		form.nomeLocalidade.value = descricaoRegistro;     		
	  		form.nomeLocalidade.style.color = "#000000";  		
		}
		
		if (tipoConsulta == 'setorComercial') {
      		
      		form.codigoSetorComercial.value = codigoRegistro;
	  		form.nomeSetorComercial.value = descricaoRegistro;     		
	  		form.nomeSetorComercial.style.color = "#000000";  		
		}
	}	
	
	function limparLocalidade(){
		var form = document.forms[0];
		form.idLocalidade.value = "";
		form.nomeLocalidade.value = "";
		
		limparSetorComercial();
	}
	
	function limparSetorComercial(){
		var form = document.forms[0];
		form.codigoSetorComercial.value = "";
		form.nomeSetorComercial.value = "";
	}	
	
    function facilitador(objeto){
		if (objeto.id == "0" || objeto.id == undefined){
			objeto.id = "1";
			marcarTodos();
			atualizarImoveisSelecionadosComTodos(true);
		}
		else{
			objeto.id = "0";
			desmarcarTodos();
			atualizarImoveisSelecionadosComTodos(false);
		}
	}

    function CheckboxNaoVazio(campo) {
        form = document.forms[0];
        retorno = false;

        for(indice = 0; indice < form.elements.length; indice++) {
            if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
                retorno = true;
                break;
            }
  	    }

  	    if (!retorno) {
  	  	    alert('Informe o(s) arquivo(s) desejado(s).');
  	    }
  		
  	    return retorno;
    }

		function gerarZip(url) {
			var form = document.forms[0];
			
			if(CheckboxNaoVazio(document.forms[0].idsRegistros)){
				form.action = url;
				form.submit();
			}
		}

    
    function gerarZipFiscalizacao(url) {
		var form = document.forms[0];
		form.action = url;
		form.submit();
	}

    function gerarZipRevisita(url) {
		var form = document.forms[0];

		var dataUltimaTransmissao = trim(form.dataUltimaTransmissao.value);
		
		if(CheckboxNaoVazio(document.forms[0].idsRegistros)) {
			 if (dataUltimaTransmissao == null || dataUltimaTransmissao == '') {
				alert('Informe a Data da �ltima Transmiss�o.');
				return false;
			} else {
				form.action = url;
				form.submit();
			}
		}
	}

	function gerarZipTodasSituacoes(url) {
		var form = document.forms[0];

		var dataUltimaTransmissao = trim(form.dataUltimaTransmissao.value);

		if(CheckboxNaoVazio(document.forms[0].idsRegistros)) {
			 if (dataUltimaTransmissao == null || dataUltimaTransmissao == '') {
				alert('Informe a Data da �ltima Transmiss�o.');
				return false;
			} else {
				form.action = url;
				form.submit();
			}
		
		}
	}

    function listarLeiturista(){
		 var form = document.forms[0];
		 form.action = 'exibirConsultarArquivoTextoAtualizacaoCadastralAction.do';
	  	 form.submit();
	}

	function atualizarImoveisSelecionados(checkbox) {
		var form = document.forms[0];
		var situacoes = ['em-campo', 'revisita', 'revisao', 'em-fiscalizacao'];
		situacoes.forEach(function (situacao, indice, array) {
			atualizarPorSituacao(situacao, checkbox);
		});
		$("#div-qnt-imoveis-selecionados").css("background-color", "DeepSkyBlue");
		setTimeout(function(){ $("#div-qnt-imoveis-selecionados").css("background-color", ""); }, 1000);
	}

	function atualizarPorSituacao(situacao, checkbox) {
		var quantidadeAtual = parseInt($('#qnt-imoveis-selecionados-' + situacao ).text(), 10);
		var idTx = '#div-' + situacao + '-' + checkbox.value;
		var quantidadeRegistro = parseInt($('#div-' + situacao + '-' + checkbox.value).text(), 10);
		var novaQuantidade = 0;
		if (checkbox.checked) {
			novaQuantidade = quantidadeAtual + quantidadeRegistro;
		} else {
			novaQuantidade = quantidadeAtual - quantidadeRegistro;
		}
		$('#qnt-imoveis-selecionados-' + situacao).text(novaQuantidade);
	}

	function atualizarImoveisSelecionadosComTodos(selecionarTodos) {
		form = document.forms[0];
		var situacoes = ['em-campo', 'revisita', 'revisao', 'em-fiscalizacao'];
		situacoes.forEach(function (situacao, indice, array) {
			$('#qnt-imoveis-selecionados-' + situacao).text(0);
		});
		if (selecionarTodos === true) {
			for(indice = 0; indice < form.elements.length; indice++) {
					if (form.elements[indice].type == "checkbox") {
						atualizarImoveisSelecionados(form.elements[indice]);
					}
			}
		} 
	}
</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form action="/consultarArquivoTextoAtualizacaoCadastralAction"
	name="ConsultarArquivoTextoAtualizacaoCadastralActionForm"
	type="gcom.gui.cadastro.ConsultarArquivoTextoAtualizacaoCadastralActionForm"
	method="post"
	onsubmit="return validateConsultarArquivoTextoAtualizacaoCadastralActionForm(this);">

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


			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar Arquivos Texto para Atualiza��o Cadastral</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">Para consultar os arquivos textos para
					atualiza��o cadastral, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>
				
				<tr>
					<td width="150"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><logic:equal name="permissao"
						value="1">
						<html:select property="idEmpresa" tabindex="3"
							onchange="javascript:listarLeiturista()">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:equal> <logic:equal name="permissao" value="2">
						<html:select property="idEmpresa" tabindex="3" disabled="true">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:equal></td>
				</tr>
				
				<tr>
					<td width="150"><strong>Agente Cadastral:</strong></td>
					<td colspan="2" align="left"><html:select property="leituristaID"
						tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeiturista"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td width="150"><strong>Localidade:</strong></td>
					<td colspan="2">
						<html:text tabindex="8" maxlength="3" property="idLocalidade" size="5"
							onkeypress="form.target=''; validaEnter(event, 'exibirConsultarArquivoTextoAtualizacaoCadastralAction.do?objetoConsulta=1', 'idLocalidade'); limparSetorComercial();"
							 />
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidade);" id="btPesqLocalidadeInicial">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar" /></a>
						<logic:present name="corLocalidadeOrigem">
							<logic:equal name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidade" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #FF0000" />
							</logic:equal>
	
							<logic:notEqual name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidade" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>						
						<logic:notPresent name="corLocalidadeOrigem">
							<logic:empty name="ConsultarArquivoTextoAtualizacaoCadastralActionForm"
								property="idLocalidade">
								<html:text property="nomeLocalidade" value="" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>

							<logic:notEmpty name="ConsultarArquivoTextoAtualizacaoCadastralActionForm"
								property="idLocalidade">
								<html:text property="nomeLocalidade" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent>
						
						<a href="javascript:limparLocalidade();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" />
						</a>
					</td>
				</tr>
				
				<tr>
					<td width="150"><strong>Setor Comercial:</strong></td>
					<td colspan="2">
						<html:text tabindex="8" maxlength="3" property="codigoSetorComercial" size="5"
							onkeypress="form.target=''; validaEnter(event, 'exibirConsultarArquivoTextoAtualizacaoCadastralAction.do?objetoConsulta=2', 'codigoSetorComercial');"
							 />
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'origem', null, null, 275, 480, '',document.forms[0].codigoSetorComercial);" id="btPesqSetorComercialInicial">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar" /></a>
						<logic:present name="corSetorComercialOrigem">
							<logic:equal name="corSetorComercialOrigem" value="exception">
								<html:text property="nomeSetorComercial" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #FF0000" />
							</logic:equal>
	
							<logic:notEqual name="corSetorComercialOrigem" value="exception">
								<html:text property="nomeSetorComercial" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>						
						<logic:notPresent name="corSetorComercialOrigem">
							<logic:empty name="ConsultarArquivoTextoAtualizacaoCadastralActionForm"
								property="codigoSetorComercial">
								<html:text property="nomeSetorComercial" value="" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>

							<logic:notEmpty name="ConsultarArquivoTextoAtualizacaoCadastralActionForm"
								property="codigoSetorComercial">
								<html:text property="nomeSetorComercial" size="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						
						<a href="javascript:limparSetorComercial();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" />
						</a>
					</td>
				</tr>			

				<tr>
					<td width="150"><strong>Situa��o Transmiss�o:</strong></td>
					<td colspan="2">
					    <strong><html:radio property="idSituacaoTransmissao" value="2" />Liberado</strong> 
					    <strong><html:radio property="idSituacaoTransmissao" value="3" />Em Campo</strong> 
					    <strong><html:radio property="idSituacaoTransmissao" value="4" />Finalizado</strong>
    					<strong><html:radio property="idSituacaoTransmissao" value="" />Todos</strong>
					</td>
				</tr>
				
				<tr>
					<td width="150"><strong>Exibi��o:</strong></td>
					<td colspan="2">
    					<strong><html:radio property="exibicao" value="" />Todos</strong>
					    <strong><html:radio property="exibicao" value="1" />Rotas com Im�veis em Revis�o</strong> 
					</td>
				</tr>
				
				<tr>
					<td>
						<input type="button" name="Button" class="bottonRightCol" value="Desfazer" align="left" onclick="window.location.href='<html:rewrite page="/exibirConsultarArquivoTextoAtualizacaoCadastralAction.do?menu=sim"/>'">
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					
					<td align="right">
						<gsan:controleAcessoBotao name="Botao" value="Selecionar" onclick="validaForm(document.forms[0]);" url="consultarArquivoTextoAtualizacaoCadastralAction.do" tabindex="13" />
					</td>
				</tr>
				
				<tr></tr>
				

				<logic:present name="colecaoArquivoTextoAtualizacaoCadastral">
					<logic:notEmpty name="colecaoArquivoTextoAtualizacaoCadastral">
						<tr>
								<td colspan="3"><hr></td>
						</tr>
						<tr>
							<td colspan="3">
								<center><strong>H� ${requestScope.quantidadeTotalEmCampo} de ${requestScope.quantidadeTotal} im�vel(is) 'EM CAMPO' de acordo com a pesquisa executada</strong></center>
							</td>
						</tr>
						<tr>
							<td colspan="3"><br></td>
						</tr>
						<tr>
							<td colspan="3"> 
								<strong>Tipo de arquivo para baixar:</strong>
								&nbsp;
								<gsan:controleAcessoBotao name="Button" value="Compactado" onclick="javascript:gerarZip('retornarZipArquivoTxtAtualizacaoCadastralAction.do');" url="retornarZipArquivoTxtAtualizacaoCadastralAction.do" tabindex="13" />
								<gsan:controleAcessoBotao name="Button" value="Im�veis N�o Transmitidos" onclick="javascript:gerarZip('retornarArquivosImoveisNaoTransmitidosAtualizacaoCadastralAction.do');" url="retornarArquivosImoveisNaoTransmitidosAtualizacaoCadastralAction.do" tabindex="13" />
							</td>
						</tr>

						<tr>
							<td colspan="3"><hr></td>
						</tr>
						
						<tr>
							<td colspan="3">
								<strong>Data da �ltima Transmiss�o:</strong>
								<html:text property="dataUltimaTransmissao" size="10" maxlength="10" onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event);" />
								<a href="javascript:abrirCalendario('ConsultarArquivoTextoAtualizacaoCadastralActionForm', 'dataUltimaTransmissao');">
									<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calend�rio" />
								</a>
								<gsan:controleAcessoBotao name="Button" value="Rota(s) para Revisita" onclick="javascript:gerarZipRevisita('retornarArquivosImoveisARevisitarAtualizacaoCadastralAction.do');" url="retornarArquivosImoveisARevisitarAtualizacaoCadastralAction.do" tabindex="14" />
							</td>
						</tr>
						
						<tr>
							<td colspan="3"><hr></td>
						</tr>
					    
						<tr>
							<td colspan="1">
								<strong>Percentual de Im�veis Aleat�rios:</strong>
							</td>
							<td colspan="1">
								<html:text property="percentualAleatorios" size="4" maxlength="2" onkeyup="javascript:verificaNumeroInteiro(this);"/>
							</td> 
						</tr>
						
						<tr>
							<td colspan="1">
								<strong>Lote de Fiscaliza��o:</strong>
							</td>
							<td colspan="1">
								<html:text property="lote" size="4" maxlength="5" onkeyup="javascript:verificaNumeroInteiro(this);"/>
							</td> 
						</tr>
						
						<tr>
							<td colspan="3">
								<gsan:controleAcessoBotao name="Button" value="Rota(s) para Revis�o" onclick="javascript:gerarZip('retornarArquivosImoveisARevisarAtualizacaoCadastralAction.do');" url="retornarArquivosImoveisARevisarAtualizacaoCadastralAction.do" tabindex="15" />
								<gsan:controleAcessoBotao name="Button" value="Rota(s) para Fiscaliza��o" onclick="javascript:gerarZipFiscalizacao('retornarArquivosImoveisAFiscalizarAtualizacaoCadastralAction.do');" url="retornarArquivosImoveisARevisarAtualizacaoCadastralAction.do" tabindex="15" />
								<gsan:controleAcessoBotao name="Button" value="Rota(s) com Todas as Situa��es" onclick="javascript:gerarZipTodasSituacoes('retornarArquivosImoveisTodasSituacoesAtualizacaoCadastralAction.do');" url="retornarArquivosImoveisTodasSituacoesAtualizacaoCadastralAction.do" tabindex="15" />
							</td>
						</tr>
						
						<tr>
							<td colspan="3"><hr></td>
						</tr>					

						<tr>
							<td><strong>Resumo da sele��o:</strong></td>
							<td colspan="2">
								<div id="div-qnt-imoveis-selecionados" style="text-align: right">
									<strong><span id="qnt-imoveis-selecionados-em-campo">0</span> im�vel(eis) 'EM CAMPO'</strong><br>
									<strong><span id="qnt-imoveis-selecionados-revisita">0</span> im�vel(eis) 'REVISITA'</strong><br>
									<strong><span id="qnt-imoveis-selecionados-revisao">0</span> im�vel(eis) 'REVIS�O'</strong><br>
									<strong><span id="qnt-imoveis-selecionados-em-fiscalizacao">0</span> im�vel(eis) 'EM FISCALIZA��O'</strong><br>
								</div>
							</td>
						</tr>
							
					</logic:notEmpty>
				</logic:present>
				
				<tr><td><p>&nbsp;</p></td></tr>

				<tr>
					<td colspan="5" bgcolor="#000000" height="1" valign="baseline"></td>
				</tr>

				<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
					<tr bgcolor="#cbe5fe" >
						<td width="100%" align="center">
						 <table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
								<td width="8%" bgcolor="#90c7fc">
								<div align="center"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>Arquivo</strong></div>
								</td>
							  <td width="10%" bgcolor="#90c7fc">
									<div align="center"><strong>Qtd Im�veis</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
									<div align="center"><strong>Qtd Em Campo</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
									<div align="center"><strong>Localidade</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
									<div align="center"><strong>Setor Comercial</strong></div>
								</td>
								<td width="7%" bgcolor="#90c7fc">
									<div align="center"><strong>Rota</strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
									<div align="center"><strong>Agente Cadastral</strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
									<div align="center"><strong>Sit. Arquivo</strong></div>
								</td>
							</tr>
						</table>

						<div style="height:500px;overflow:auto">
						<table width="100%" bgcolor="#99CCFF">
							<tr bordercolor="#000000" bgcolor="#90c7fc">
								<logic:present name="colecaoArquivoTextoAtualizacaoCadastral">
									<%int cont = 0;%>
									<logic:iterate name="colecaoArquivoTextoAtualizacaoCadastral"
										id="arquivoTextoAtualizacaoCadastral">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe" class="styleFontePequena">
											<%} else {%>
										<tr bgcolor="#FFFFFF" class="styleFontePequena">
											<%}%>
											<td width="8%">
												<div align="center">
														<html:checkbox property="idsRegistros"
															value="${arquivoTextoAtualizacaoCadastral.id}" onclick="atualizarImoveisSelecionados(this);" />
												</div>
											</td>	
											
											<td width="15%" align="center"><html:link
												page="/retornarArquivoTxtAtualizacaoCadastralAction.do"
												title="${arquivoTextoAtualizacaoCadastral.descricaoArquivo}"
												paramName="arquivoTextoAtualizacaoCadastral" paramProperty="id"
												paramId="idRegistroAtualizacao">
										 		<div align="center">${arquivoTextoAtualizacaoCadastral.descricaoArquivo}</div>	
										 		</html:link>
											</td>								
											
											<td width="10%" align="center">
										 		<div align="center">${arquivoTextoAtualizacaoCadastral.quantidadeImoveisTransmitidos} / ${arquivoTextoAtualizacaoCadastral.quantidadeImoveisSemBloqueados}</div>
												<div style="display: none" id="div-revisita-${arquivoTextoAtualizacaoCadastral.id}" align="center">
													${arquivoTextoAtualizacaoCadastral.quantidadeRevisita}
												</div>
												<div style="display: none" id="div-revisao-${arquivoTextoAtualizacaoCadastral.id}" align="center">
													${arquivoTextoAtualizacaoCadastral.quantidadeRevisao}
												</div>
												<div style="display: none" id="div-em-fiscalizacao-${arquivoTextoAtualizacaoCadastral.id}" align="center">
													${arquivoTextoAtualizacaoCadastral.quantidadeEmFiscalizacao}
												</div>
											</td>

											<td width="10%" align="center">
										 		<div id="div-em-campo-${arquivoTextoAtualizacaoCadastral.id}" align="center">${arquivoTextoAtualizacaoCadastral.quantidadeEmCampo}</div>
											</td>

											<td width="10%">
												<div align="center">${arquivoTextoAtualizacaoCadastral.localidade.id}</div>
											</td>
											
											<td width="10%">
												<div align="center">${arquivoTextoAtualizacaoCadastral.codigoSetorComercial}</div>
											</td>
											
											<td width="7%" align="center">
												<div align="center">${arquivoTextoAtualizacaoCadastral.rota.codigo}</div>
											</td>

											<td width="15%">
											<div align="center"><c:choose>
												<c:when
													test='${arquivoTextoAtualizacaoCadastral.leiturista.funcionario!=null}'>
														${arquivoTextoAtualizacaoCadastral.leiturista.funcionario.nome}
													</c:when>
												<c:otherwise>
														${arquivoTextoAtualizacaoCadastral.leiturista.cliente.nome}
													</c:otherwise>
											</c:choose></div>
											</td>
											<td width="15%">
												<div align="center">
													<a href="javascript:abrirPopup('exibirAnaliseSituacaoArquivoAtualizacaoCadastralPopupAction.do?idArquivo=${arquivoTextoAtualizacaoCadastral.id}', 480, 800);">
														${arquivoTextoAtualizacaoCadastral.situacaoTransmissaoLeitura.descricaoSituacao}
													</a>
												</div>
											</td>

											<html:link href="/gsan/liberarArquivoLeituraAction.do"
												paramId="codigo" paramProperty="id"
												paramName="arquivoTextoAtualizacaoCadastral">
											</html:link>

										</tr>
									</logic:iterate>
								</logic:present>
						</table>
						</div>
					
					</tr>
				</table>
			</table>
			<p>&nbsp;</p>
		</tr>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>

</html:form>
</body>
</html:html>

