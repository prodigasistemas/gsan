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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm"
	dynamicJavascript="true" />

<script language="JavaScript">
	
	function validaForm() {
		var form = document.forms[0];
		if (validateEfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm(form) && validaDebito()){
		 var submeter = true;
			if(form.numeroHidrometro.value != ''){
				if(form.dataInstalacao.value==''){
					submeter = false;
					alert('Informe Data Instalação');
				}else
				if(form.localInstalacao.selectedIndex == 0){
					submeter = false;
					alert('Informe Local de Instalação');
				}else
				if(form.protecao.selectedIndex == 0){
					submeter = false;
					alert('Informe Proteção');
				}
				else
				if(form.leituraInstalacao.value ==''){
					submeter = false;
					alert('Informe Leitura instalação');
				/*}else
				if(form.numeroSelo.value ==''){
					submeter = false;
					alert('Informe Número do Selo');*/
				}else
				if(!form.situacaoCavalete[0].checked && !form.situacaoCavalete[1].checked){
					submeter = false;
					alert('Informe Cavalete');
				}
			}
			if(submeter == true){
				submeterFormPadrao(form);
			}

		}
	}
	
	function limparForm() {
		var form = document.forms[0];

		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.diametroLigacao.selectedIndex = 0;
		form.materialLigacao.selectedIndex = 0;
		form.perfilLigacao.selectedIndex = 0;
		form.ramalLocalInstalacao.selectedIndex = 0;
		form.numeroHidrometro.value = "";
		form.localInstalacao.selectedIndex = 0;
		form.protecao.selectedIndex = 0;
		form.leituraInstalacao.value = "";
		form.numeroSelo.value = "";
		form.situacaoCavalete[0].checked = false;
		form.situacaoCavalete[1].checked = false;	
		form.numeroLacre.value = "";
		form.aceitaLacre[0].checked = false;
		form.aceitaLacre[1].checked = false;	

		form.action = 'exibirEfetuarLigacaoAguaComInstalacaoHidrometroSemRAAction.do?desfazer=sim';
		form.submit();
	 }

	 function limparDecricaoEfetuar() {
		var form = document.forms[0];

		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataLigacao.value = "";
		form.diametroLigacao.selectedIndex = 0;
		form.materialLigacao.selectedIndex = 0;
		form.perfilLigacao.selectedIndex = 0;
		form.ramalLocalInstalacao.selectedIndex = 0;
	 }
	 
	 function limparImovel() {
		var form = document.forms[0];
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataLigacao.value = "";
		form.diametroLigacao.selectedIndex = 0;
		form.materialLigacao.selectedIndex = 0;
		form.perfilLigacao.selectedIndex = 0;
		form.ramalLocalInstalacao.selectedIndex = 0;
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
					abrirPopup(url + "?" + "menu=sim&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaOrdemServico=" + tipo, altura, largura);
				}
			}
		}
	}
	 
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'hidrometro') {
      		form.numeroHidrometro.value = codigoRegistro;

	      	form.action='exibirEfetuarLigacaoAguaComInstalacaoHidrometroSemRAAction.do?objetoConsulta=1';
	      	form.submit();
      		
    	}
    	if (tipoConsulta == 'imovel') {
	      form.matriculaImovel.value = codigoRegistro;
	      form.inscricaoImovel.value = descricaoRegistro;
		  form.inscricaoImovel.style.color = '#000000';	
		  form.action='exibirEfetuarLigacaoAguaComInstalacaoHidrometroSemRAAction.do?objetoConsulta=1';
	      form.submit();      
	    }
  	}
	function limparNumeroHidrometro() {
		var form = document.forms[0];
		form.numeroHidrometro.value = "";
	}
	
	function desabilitaCamposNumeroLacreOnClick(){
		var form = document.forms[0];
	  if(form.aceitaLacre[0].checked){
		form.numeroLacre.disabled = false;
	  }else{
		form.numeroLacre.value = "";
	    form.numeroLacre.disabled = true;
	    form.numeroLacre.style.color = "#000000";
	  }
	}		
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');verificaForm();desabilitaCamposNumeroLacreOnClick();">

<html:form
	action="/efetuarLigacaoAguaComInstalacaoHidrometroSemRAAction.do"
	name="EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm"
	type="gcom.gui.atendimentopublico.EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm"
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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Efetuar a Ligação de Água</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<!--Inicio da Tabela Ligação Agua -->
			<table width="100%" border="0">
				<tr>

					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="2">Para efetuar a liga&ccedil;&atilde;o
							de &aacute;gua com instalação de hidrômetro, informe os dados
							abaixo:</td>
						</tr>
						<tr>
							<td height="10" colspan="2">
							<hr>
							</td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">

							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"> Dados do Imóvel </span></div>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>

											<td width="25%"><strong>Matrícula do Imóvel:</strong></td>
											<td width="75%"><html:text maxlength="9"
												property="matriculaImovel" size="9"
												onkeypress="limparDecricaoEfetuar();validaEnterString(event, 'exibirEfetuarLigacaoAguaComInstalacaoHidrometroSemRAAction.do?objetoConsulta=1', 'matriculaImovel','Imóvel');" />
											<a
												href="javascript:abrirPopup('exibirPesquisarImovelAction.do',600,640);">
											<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
												width="23" height="21" name="imagem" alt="Pesquisar"
												border="0"></a> <logic:equal property="imovelNaoEncontrado"
												value="true"
												name="EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm">
												<input type="text" name="inscricaoImovel" size="30"
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000"
													value="<bean:message key="atencao.imovel.inexistente"/>" />
											</logic:equal> <logic:notEqual property="imovelNaoEncontrado"
												value="true"
												name="EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm">
												<html:text size="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000"
													property="inscricaoImovel" />
											</logic:notEqual> <a href="javascript:limparImovel();"> <img
												border="0" src="imagens/limparcampo.gif" height="21"
												width="23"> </a></td>
										</tr>

										<tr>
											<td><strong> Cliente Usu&aacute;rio:</strong></td>
											<td><html:text property="clienteUsuario" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>

										</tr>
										<tr>
											<td><strong>CPF ou CNPJ:</strong></td>
											<td><html:text property="cpfCnpjCliente" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>
										<tr>

											<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o
											de &Aacute;gua:</strong></td>
											<td><html:text property="situacaoLigacaoAgua" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>
										<tr>
											<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o
											de Esgoto:</strong></td>

											<td><html:text property="situacaoLigacaoEsgoto"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="40" maxlength="40" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>

						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"> Dados da
									Liga&ccedil;&atilde;o </span></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td width="42%" height="10"><strong>Data da
											Liga&ccedil;&atilde;o:<font color="#FF0000">*</font> </strong>
											</td>

											<td colspan="2"><strong><b> <html:text property="dataLigacao"
												size="10" maxlength="10" onkeyup="mascaraData(this, event)" />
											</b></strong> <a
												href="javascript:abrirCalendario('EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm', 'dataLigacao')">
											<img border="0"
												src="<bean:message key="caminho.imagens"/>calendario.gif"
												width="20" border="0" align="absmiddle"
												title="Exibir Calendário" /> </a></td>
										</tr>
										<tr>
											<td><strong> Diametro da Liga&ccedil;&atilde;o:<font
												color="#FF0000">*</font></strong></td>

											<td colspan="2"><strong> <html:select
												property="diametroLigacao" style="width: 230px;">
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoDiametroLigacao"
													labelProperty="descricao" property="id" />
											</html:select> </strong></td>
										</tr>
										<tr>
											<td class="style3"><strong>Material da Liga&ccedil;&atilde;o:<font
												color="#FF0000">*</font></strong></td>

											<td colspan="2"><strong> <html:select
												property="materialLigacao" style="width: 230px;">
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoMaterialLigacao"
													labelProperty="descricao" property="id" />
											</html:select> </strong></td>
										</tr>

										<tr>
											<td class="style3"><strong>Perfil da Liga&ccedil;&atilde;o:<font
												color="#FF0000">*</font></strong></td>

											<td colspan="2"><strong> <html:select
												property="perfilLigacao" style="width: 230px;">
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoPerfilLigacao"
													labelProperty="descricao" property="id" />
											</html:select> </strong></td>
										</tr>

										<tr>
											<td class="style3"><strong>Local de Instala&ccedil;&atilde;o
											do Ramal :</strong></td>

											<td colspan="2"><strong> <html:select
												property="ramalLocalInstalacao" style="width: 230px;">
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoRamalLocalInstalacao"
													labelProperty="descricao" property="id" />
											</html:select></strong></td>
										</tr>
										<tr>
											<td><strong>Existe Lacre?</strong></td>
											<td width="66%" align="right">
											<div align="left"><html:radio property="aceitaLacre"
												tabindex="4" onclick="desabilitaCamposNumeroLacreOnClick();"
												value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
											<strong>Sim</strong> <html:radio property="aceitaLacre"
												tabindex="5" onclick="desabilitaCamposNumeroLacreOnClick();"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
											<strong>Não</strong></div>
											</td>
										</tr>
										<tr>
											<td><strong> Número do Lacre:</strong></td>
											<td><html:text property="numeroLacre" size="9" maxlength="9" /></td>

										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">

									<td height="18" colspan="2">
									<div align="center"><span class="style2"> Dados da Instalação
									do Hidrômetro</span></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td width="37%" class="style3"><strong>N&uacute;mero do
											Hidr&ocirc;metro:</strong></td>

											<td width="58%"><html:text property="numeroHidrometro"
												size="13" maxlength="10"
												onkeypress="validaEnterString(event, 'exibirEfetuarLigacaoAguaComInstalacaoHidrometroSemRAAction.do?objetoConsulta=1', 'numeroHidrometro','Número do Hidrômetro');" />
											<a
												href="javascript:abrirPopup('exibirPesquisarHidrometroAction.do',600,640);"><img
												src="/gsan/imagens/pesquisa.gif" alt="Pesquisar" border="0"
												height="21" width="23"></a> <a
												href="javascript:limparNumeroHidrometro()"><img border="0"
												title="Apagar"
												src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
											</td>
										</tr>
										<tr>
											<td height="10"><strong>Data da Instalação:<font color="#FF0000">*</font></strong></td>
											<td colspan="2"><strong><b> <!-- Campo Data da Ligacao --> <html:text
												property="dataInstalacao" onkeyup="mascaraData(this, event)"
												size="10" maxlength="10" /> </b></strong> <a
												href="javascript:abrirCalendario('EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm', 'dataInstalacao')">
											<img border="0"
												src="<bean:message key="caminho.imagens"/>calendario.gif"
												width="20" border="0" align="absmiddle"
												title="Exibir Calendário" /> </a></td>
										</tr>
										<tr>
											<td><strong>Local de Instalação:<font color="#FF0000">*</font></strong></td>
											<td><html:select property="localInstalacao" tabindex="4">
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoHidrometroLocalInstalacao"
													labelProperty="descricao" property="id" />
											</html:select> <font size="1">&nbsp; </font></td>
										</tr>
										<tr>
											<td><strong>Proteção:<font color="#FF0000">*</font></strong></td>
											<td><html:select property="protecao" tabindex="4">
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoHidrometroProtecao"
													labelProperty="descricao" property="id" />
											</html:select> <font size="1">&nbsp; </font></td>
										</tr>
										<tr>
											<td class="style3"><strong>Leitura Instalação:<font color="#FF0000">*</font></strong></td>
											<td colspan="2"><strong><b><span class="style2"> <html:text
												property="leituraInstalacao" size="6" maxlength="6"
												onkeyup="" onkeydown="" onkeypress="" /> </span></b></strong></td>
										</tr>
										<tr>
											<td class="style3"><strong>Número do Selo:</strong></td>
											<td colspan="2"><strong><b><span class="style2"> <html:text
												property="numeroSelo" size="12" maxlength="12" onkeyup=""
												onkeydown="" onkeypress="" /> </span></b></strong></td>
										</tr>
										<tr>
											<td><strong>Cavalete:<font color="#FF0000">*</font></strong></td>
											<td><strong> <html:radio property="situacaoCavalete"
												value="1" /> <strong>COM <html:radio
												property="situacaoCavalete" value="2" /> SEM</strong> </strong></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%">

						<tr>
							<td width="40%" align="left"><input type="button"
								name="ButtonReset" class="bottonRightCol" value="Desfazer"
								onClick="limparForm();"> <input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>

							<td align="right"><input name="Button" type="button"
								class="bottonRightCol" value="Efetuar" onclick="validaForm();"></td>
						</tr>
					</table>
					</td>

				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	</td>
	</tr>
	</table>
</html:form>
</body>
</html:html>
