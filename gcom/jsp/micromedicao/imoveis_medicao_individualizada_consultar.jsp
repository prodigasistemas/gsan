<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="ConsultarImoveisMedicaoIndividualizadaActionForm"
	dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
<!-- Begin
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
        form.codigoImovel.value = codigoRegistro;
        form.action = 'exibirConsultarImoveisMedicaoIndividualizadaAction.do?objetoConsulta=1'
        form.submit();
    }
}
 

function consultar(){

 	var form = document.forms[0];
    form.action = 'exibirConsultarHistoricoMedicaoIndividualizadaAction.do?objetoConsulta = 1'
    form.submit();
 	
}

function limparCampos(){
	var form = document.forms[0];
	form.codigoImovel.value = "";
	form.nomeCliente.value = "";
	form.situacaoAgua.value = "";
	form.situacaoEsgoto.value = "";
	form.endereco.value = "";
	form.rateioTipo.value = "";
	form.quantidadeDeImoveisVinculados.value = "";
	
}

function limparForm(){

 	var form = document.forms[0];
 	
 	for (var i=0;i < form.elements.length;i++){
		var elemento = form.elements[i];
		if (elemento.type == "text"){
			elemento.value = "";
		}
	}
	
	redirecionarSubmit("exibirConsultarImoveisMedicaoIndividualizadaAction.do?menu=sim");
}


function validaEnterImovelCondominio(tecla, caminhoActionReload, nomeCampo,mensagem) {
  
 	var form = document.forms[0];
 	var codigo;
 	if( document.all){
      var codigo = tecla.keyCode;
     }else{
      var codigo = tecla.which;
     }     
     if(codigo == 13){
     	if(!isNaN(form.codigoImovel.value) && form.codigoImovel.value < 0){
     		alert("Matrícula do Imóvel Condomínio deve somente conter números positivos.")
        }if(!validaEnterComMensagem(tecla,caminhoActionReload, nomeCampo,mensagem)){
 	     return false;
 	    }else{
 	     return true;
 	    }
 		
 	 }else{
 	 	 return true;
 	 }
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload = "setarFoco('${requestScope.nomeCampo}');">

<html:form
	action="/exibirConsultarImoveisMedicaoIndividualizadaAction.do"
	name="ConsultarImoveisMedicaoIndividualizadaActionForm"
	type="gcom.gui.micromedicao.ConsultarImoveisMedicaoIndividualizadaActionForm"
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">

				<tr>
					<td width="11"><img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Consultar Im&oacute;veis com
					Medi&ccedil;&atilde;o Individualizada</td>

					<td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td>Para consultar im&oacute;veis com medi&ccedil;&atilde;o
					individualizada, informe o im&oacute;vel condom&iacute;nio:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelConsultarMedicaoIndividualizada', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>								
				</tr>

			</table>
			<table width="100%" border="0">
				<tr>
					<td>

		            <table width="100%" border="0" bgcolor="#79bbfd" align="center" cellpadding="0"
						cellspacing="0"> 
		            	<!--header da tabela interna --> 
			            <tr bgcolor="#79bbfd" height="18">
		    	            <td align="center" class="style11"><strong>Dados do Im&oacute;vel </strong></td>
		        	    </tr>
		            </table>			

					<table width="100%">
						<!--header da tabela interna -->
						<tr>
							<td width="20%"><strong>Matr&iacute;cula do Im&oacute;vel
							Condom&iacute;nio:<font color="#FF0000">*</font></strong></td>
							<td width="78%"><html:text property="codigoImovel" maxlength="9"
								size="9"
								onkeypress="return validaEnterImovelCondominio(event, 'exibirConsultarImoveisMedicaoIndividualizadaAction.do?objetoConsulta=1', 'codigoImovel','Matrícula do Imóvel Condomínio');" />
							<a
								href="javascript:abrirPopup('exibirPesquisarImovelAction.do?pesquisarImovelCondominio=OK', 400, 800);">
							<img width="23" height="21"
								src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								border="0" /></a> 
								<logic:equal name="corImovel" value="exception">
							       <html:text property="inscricaoImovel" size="30" readonly="true"
								 style="background-color:#EFEFEF; border:0; color: #ff0000"
								/>
						      </logic:equal>

						      <logic:notEqual name="corImovel" value="exception">
							  <html:text property="inscricaoImovel" size="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								/>
						</logic:notEqual>
								<img border="0"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif"
								onclick="document.forms[0].action = 'exibirConsultarImoveisMedicaoIndividualizadaAction.do?limparCampos=SIM'; document.forms[0].submit();limparCampos();"
								style="cursor: hand;" /> &nbsp;</td>
						</tr>
					</table>
					<p>&nbsp;</p>
		            <table width="100%" border="0" bgcolor="#79bbfd" align="center"  cellpadding="0"
				cellspacing="0"> 
		            	<!--header da tabela interna --> 
			            <tr bgcolor="#79bbfd" height="18">
		    	            <td align="center" class="style11"><strong>Dados do Im&oacute;vel
							Condom&iacute;nio </strong></td>
		        	    </tr>
		            </table>						
					<table width="100%"  cellpadding="0"
				cellspacing="0">
						<tr>
							<td>
							<table width="100%" bgcolor="#90c7fc">
								<!--header da tabela interna -->
								<tr bgcolor="#90c7fc">
									<td width="17%">
									<div align="center"><strong><strong>Situa&ccedil;&atilde;o de
									&Aacute;gua</strong></div>
									</td>
									<td width="22%">
									<div align="center"><strong>Situa&ccedil;&atilde;o de Esgoto</strong></div>
									</td>
									<td width="35%">
									<div align="center"><strong>Tipo de Rateio</strong></div>
									</td>
									<td width="18%">
									<div align="center"><strong>Qtd. Imov. Vinc.</strong></div>
									</td>
								</tr>
								<logic:present name="ConsultarImoveisMedicaoIndividualizadaActionForm"
										property="codigoImovel">
								<tr bgcolor="#FFFFFF">
									<td>
									<div align="center"><bean:write
										name="ConsultarImoveisMedicaoIndividualizadaActionForm"
										property="situacaoAgua" /></div>
									</td>
									<td>
									<div align="center"><bean:write
										name="ConsultarImoveisMedicaoIndividualizadaActionForm"
										property="situacaoEsgoto" /></div>
									</td>
									<td>
									<div align="center"><bean:write
										name="ConsultarImoveisMedicaoIndividualizadaActionForm"
										property="rateioTipo" /></div>
									</td>
									<td>
									<div align="center"><bean:write
										name="ConsultarImoveisMedicaoIndividualizadaActionForm"
										property="quantidadeDeImoveisVinculados" /></div>
									</td>
								</tr>
								</logic:present>
								<logic:notPresent name="ConsultarImoveisMedicaoIndividualizadaActionForm"
										property="codigoImovel">
								</logic:notPresent>
								
							</table>
							</td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td>
									<table width="100%" bgcolor="#90c7fc">
										<!--header da tabela interna -->
										<tr bgcolor="#90c7fc">
											<td>
											<div align="center"><strong>Endere&ccedil;o do Im&oacute;vel</strong></div>
											</td>
										</tr>
									</table>
								</tr>
								<tr>
									<td>
									<table width="100%" align="center" bgcolor="#90c7fc">
										<!--corpo da segunda tabela-->
											<logic:present name="ConsultarImoveisMedicaoIndividualizadaActionForm"
												property="endereco">
												<logic:notEmpty name="ConsultarImoveisMedicaoIndividualizadaActionForm"
													property="endereco" >
													<tr bgcolor="#FFFFFF">
														<td width="90%">
															<div align="center"><bean:write
															name="ConsultarImoveisMedicaoIndividualizadaActionForm"
															property="endereco" /> &nbsp;</div>
														</td>
													</tr>
												</logic:notEmpty>
												<logic:empty name="ConsultarImoveisMedicaoIndividualizadaActionForm"
													property="endereco">
												</logic:empty>
											</logic:present>
									</table>

									</td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
		            <table width="100%" bgcolor="#79bbfd" align="center" cellpadding="0"
				cellspacing="0"> 
		            	<!--header da tabela interna --> 
			            <tr bgcolor="#79bbfd" height="18">
		    	            <td align="center" class="style11"><strong>Im&oacute;veis Vinculados </strong></td>
		        	    </tr>
		            </table>						
					<table width="100%" cellpadding="0"
				cellspacing="0">
						<tr>
							<td>
							<table width="100%" bgcolor="#90c7fc">
								<!--header da tabela interna -->
								<tr bgcolor="#90c7fc">

									<td width="11%" align="center">
									<div><strong>Matr&iacute;cula do Im&oacute;vel</strong></div>
									</td>
									<td width="50%" align="center">
									<div><strong>Nome do Cliente Usu&aacute;rio</strong></div>
									</td>
									<td width="10%" align="center">
									<div><strong>Sublote</strong></div>
									</td>
									<td width="17%" align="center">
									<div><strong>Complemento do Endere&ccedil;o</strong></div>
									</td>
								</tr>

								<logic:present name="colecaoImoveisASerVinculados">
									<%int cont = 0;%>
									<%String styleColor = "";%>
									<logic:iterate name="colecaoImoveisASerVinculados" id="clienteImovel">
										<%
										cont = cont + 1;
										styleColor = "";
										if (cont % 2 == 0) { %>
				                          <tr bgcolor="#cbe5fe">
										
										<% } else { %>
											<tr bgcolor="#FFFFFF">
										<% } %>
										
											<logic:equal name="clienteImovel" property="imovel.indicadorImovelAreaComum" value="<%=""+ConstantesSistema.INDICADOR_USO_ATIVO%>">
												<% styleColor = "color:#F00;"; %>
											</logic:equal>
											
											<td width="11%" align="center" style="<%=styleColor %>">
												<bean:write name="clienteImovel" property="imovel.id" />
											</td>
											<td width="50%" align="left" style="<%=styleColor %>">
												<bean:write name="clienteImovel" property="cliente.nome" />
											</td>
											<td width="10%" align="left" style="<%=styleColor %>">
												<div align="center">
													<bean:write name="clienteImovel" property="imovel.subLote" />
												</div>
											</td>
											<td width="17%" align="left" style="<%=styleColor %>">
												<div align="left">
													<bean:write name="clienteImovel" property="imovel.complementoEndereco" />
												</div>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>
							</table>
							</td>
						</tr>
					</table>
			</table>

			<table height="100%">
				<tr>
					<td>
					<td width="100%"><input type="button" name="limpar"
						value="Limpar" class="bottonRightCol" onclick="limparForm();">
						<input type="button" name="Button" class="bottonRightCol"
								value="Cancelar"
								onClick="window.location.href='/gsan/telaPrincipal.do'" /></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>



	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
