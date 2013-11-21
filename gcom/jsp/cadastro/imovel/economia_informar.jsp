<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.cadastro.cliente.ClienteTipo" isELIgnored="false"%>
<%@ page import="gcom.cadastro.cliente.ClienteRelacaoTipo"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="EconomiaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">

 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.idImovel.value = codigoRegistro;
      form.action='exibirInformarEconomiaAction.do?tipoConsulta=1';
      form.submit();
    }
  }
 function limparPesquisaImovel() {
    var form = document.forms[0];
      form.action='exibirInformarEconomiaAction.do';
      form.matriculaImovel.value = "";
      form.submit();


  }

function validarForm(form){

   if(testarCampoValorZero(form.idImovel, 'Matrícula')){
         if(validateEconomiaActionForm(form)){
           form.submit();
   }
     }
}

</script>
<script>
function validaEnterImovel(tecla, caminhoActionReload, nomeCampo,mensagem) {
  
 	var form = document.forms[0];
 	var codigo;
 	if( document.all){
      var codigo = tecla.keyCode;
     }else{
      var codigo = tecla.which;
     }     
     if(codigo == 13){
        if(!validaEnterComMensagem(tecla,caminhoActionReload, nomeCampo,mensagem)){
 	     return false;
 	    }else{
 	     return true;
 	    }
 		
 	 }else{
 	 	 return true;
 	 }
}
  </script>
</head>


<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/informarEconomiaAction.do" name="EconomiaActionForm"
	type="gcom.gui.cadastro.imovel.EconomiaActionForm" method="post">




	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
			<td valign="top" class="centercoltext">
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
					<td class="parabg">Informar Economia</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<table width="100%" border="0">
					<tr>
						<td>Pesquisar um im&oacute;vel para inserir a(s) economia(s):</td>
						<logic:present scope="application" name="urlHelp">
							<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelEconomiaInformar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%">
						<tr>
							<td width="10%"><strong>Matrícula:</strong></td>
							<td width="90%"><html:text maxlength="9" property="idImovel"
								size="10"
								onkeypress="return validaEnterImovel(event, 'exibirInformarEconomiaAction.do?tipoConsulta=1', 'idImovel','Matrícula');" />
							<a
								href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Imóvel" /></a> <logic:present
								name="matInexistente" scope="request">
								<html:text property="matriculaImovel" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #FF0000"
									size="30" maxlength="30" />

							</logic:present> <logic:notPresent name="matInexistente"
								scope="request">
								<html:text property="matriculaImovel" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="30" maxlength="30" />

							</logic:notPresent> <a href="javascript:limparPesquisaImovel();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar Imóvel" /></a></td>


						</tr>
						<tr>
							<td colspan="2" valign="top">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td>
									<table width="100%" border="0" cellpadding="1" cellspacing="0"
										bgcolor="#99CCFF" bordercolor="#99CCFF">
										<!--header da tabela interna -->
										<tr>
											<td align="center"><strong>Endere&ccedil;o</strong></td>
										</tr>
									</table>
									</td>

								</tr>
								<tr>
									<td><logic:notEmpty name="EconomiaActionForm"
										property="enderecoImovel">
										<table width="100%" align="center" bgcolor="#99CCFF">
											<tr bgcolor="#FFFFFF">
												<td>
												<div align="center"><span id="endereco"><bean:write
													name="EconomiaActionForm" property="enderecoImovel" /></span></div>
												</td>
											</tr>

										</table>
									</logic:notEmpty></td>
								</tr>
							</table>

							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td height="300">
					<div style="width: 100%; height: 100%; overflow: auto;">
					<table width="100%">
						<logic:iterate name="colecaoImovelSubCategoriasCadastradas"
							id="imovelSubCategoria" indexId="cont">
							<logic:equal name="cont" value="0">
								<tr>
									<td>
									<table width="100%" bgcolor="#90c7fc">
										<tr>
											<td><strong>Matrícula do imóvel selecionado:&nbsp;<bean:write
												name="imovelSubCategoria" property="comp_id.imovel.id" /></strong></td>
										</tr>
									</table>
									</td>
								</tr>
							</logic:equal>
							<tr>
								<td>
								<table width="100%" bgcolor="#90c7fc">
									<!--header da tabela interna -->

									<tr bgcolor="#79bbfd">
										<td width="40%" bgcolor="#79bbfd">
										<div align="center"><strong>Subcategoria</strong></div>
										</td>
										<td width="37%">
										<div align="center"><strong>Categoria</strong></div>
										</td>
										<td width="20%">
										<div align="center"><strong>Quantidade</strong></div>
										</td>
									</tr>
									<tr bgcolor="#FFFFFF">
										<td>
										<div align="center"><a
											href="javascript:abrirPopup('exibirInserirEconomiaPopupAction.do?codigoImovelSubCategoria=<bean:write name="imovelSubCategoria" property="ultimaAlteracao.time"/>',710,440);">
										<img width="14" height="14" border="0"
											src="<bean:message key="caminho.imagens"/>adicaoVerde.gif"
											title="Inserir Imóvel Economia" /></a> &nbsp; <bean:write
											name="imovelSubCategoria"
											property="comp_id.subcategoria.descricao" /></div>
										</td>
										<td>
										<div align="center"><bean:write name="imovelSubCategoria"
											property="comp_id.subcategoria.categoria.descricao" /></div>
										</td>
										<td>
										<div align="center"><bean:write name="imovelSubCategoria"
											property="quantidadeEconomias" /></div>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td>
								<table width="100%" bgcolor="#90c7fc">
									<!--header da tabela interna -->
									<tr bordercolor="#FFFFFF" bgcolor="#cccccc">
										<td width="10%" align="center"><strong>Remover</strong></td>
										<td width="40%"><strong>Cliente Usuário</strong></td>
										<td width="24%"><strong>Complemento</strong></td>
										<td width="24%"><strong>IPTU</strong></td>

									</tr>
									<%String cor = "#FFFFFF";%>
									<logic:notEmpty name="imovelSubCategoria"
										property="imovelEconomias">
										<logic:iterate name="imovelSubCategoria"
											property="imovelEconomias" id="imovelEconomia">

											<%if (cor.equalsIgnoreCase("#FFFFFF")) {
									cor = "#cbe5fe";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
									cor = "#FFFFFF";%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td>
												<div align="center"><font color="#333333"> <a
													href="javascript:if(confirm('Confirma remoção?')){redirecionarSubmit('removerEconomiaAction.do?codigoImovelEconomia=<bean:write name="imovelEconomia" property="ultimaAlteracao.time"/>');}">
												<img width="14" height="14" border="0"
													src="<bean:message key="caminho.imagens"/>Error.gif" /></a>
												</font></div>
												</td>
												<td><logic:notEmpty name="imovelEconomia"
													property="clienteImovelEconomias">
													<logic:iterate name="imovelEconomia"
														property="clienteImovelEconomias"
														id="clienteImovelEconomia">
														<logic:equal name="clienteImovelEconomia"
															property="clienteRelacaoTipo.id"
															value="<%=""+ClienteRelacaoTipo.USUARIO%>">
															<logic:empty name="clienteImovelEconomia"
																property="dataFimRelacao">
																<font color="#333333"> <a
																	href="javascript:abrirPopup('exibirAtualizarEconomiaPopupAction.do?limpa=sim&auxiliar=sim&codigoImovelEconomia=<bean:write name="imovelEconomia" property="ultimaAlteracao.time"/>',710,440);">
																<bean:write name="clienteImovelEconomia"
																	property="cliente.nome" /> </a> </font>
															</logic:empty>
														</logic:equal>
													</logic:iterate>
												</logic:notEmpty></td>
												<td><font color="#333333"> <logic:notEmpty
													name="imovelEconomia" property="complementoEndereco">
													<bean:write name="imovelEconomia"
														property="complementoEndereco" />
												</logic:notEmpty> <logic:empty name="imovelEconomia"
													property="complementoEndereco">
                                   &nbsp;
                                </logic:empty> </font></td>
												<td><font color="#333333"> <logic:notEmpty
													name="imovelEconomia" property="numeroIptu">
													<bean:write name="imovelEconomia" property="numeroIptu" />
												</logic:notEmpty> <logic:empty name="imovelEconomia"
													property="numeroIptu">
                                   &nbsp;
                                </logic:empty> </font></td>

											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								</td>
							</tr>
							<tr>
								<td height="7"></td>
							</tr>
						</logic:iterate>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td align="left" colspan="2"><input type="button" name="Button"
								class="bottonRightCol" value="Desfazer"
								onClick="window.location.href='/gsan/exibirInformarEconomiaAction.do?tipoConsulta=1'" />
							<input type="button" name="Button" class="bottonRightCol"
								value="Cancelar"
								onClick="window.location.href='/gsan/telaPrincipal.do'" /></td>
							<td align="right">
							<div align="right"><gsan:controleAcessoBotao name="Button"
								value="Concluir"
								onclick="javascript:validarForm(document.forms[0]);"
								url="inserirEconomiaPopupAction.do" /> <!-- <input type="button" name="Button"
								class="bottonRightCol" value="Concluir"
								onClick="javascript:validarForm(document.forms[0])" /> --></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
