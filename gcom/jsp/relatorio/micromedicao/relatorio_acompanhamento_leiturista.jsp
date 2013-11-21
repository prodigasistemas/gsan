<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gcom"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<html:javascript staticJavascript="false"
	formName="FiltrarRelatorioAcompanhamentoLeituristaForm"
	dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript">

	function validaForm(form){
			var form = document.forms[0];
			if(validateFiltrarRelatorioAcompanhamentoLeituristaForm(form)){
				enviarSelectMultiplo('FiltrarRelatorioAcompanhamentoLeituristaForm','rotasSelecionadas');
    			botaoAvancarTelaEspera('filtrarRelatorioAcompanhamentoLeituristaAction.do');
     		}
  	}	
	
    function limparForm(form) {
		form.empresaID.value = "";
		form.grupoFaturamentoID.value = "";
		form.mesAno.value = "";
		
	}
	
	function listarLeiturista(){
		 var form = document.forms[0];
		 form.action = 'exibirFiltrarRelatorioAcompanhamentoLeituristaAction.do';
	  	 form.submit();
	
	}
	
	function listarRota(){
		 var form = document.forms[0];
		 form.action = 'exibirFiltrarRelatorioAcompanhamentoLeituristaAction.do';
	  	 form.submit();
	
	}
	
	function MoveSelectedDataFromMenu1TO2(form, object, selectedObject){
		var form2 = document.forms[0];
		
		MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);

		if(form2.rotasSelecionadas[0]!=null){
			form2.idEmpresa.value = -1;
			form2.idLeiturista.value = -1;
			form2.idEmpresa.disabled = true;
			form2.idLeiturista.disabled = true;
		}	
		
	}
	function MoveTodosFromMenu1TO2(form, object, selectedObject){
		var form2 = document.forms[0];
		
		MoverTodosDadosSelectMenu1PARAMenu2(form, object, selectedObject);

		if(form2.rotasSelecionadas[0]!=null){
			form2.idEmpresa.value = -1;
			form2.idLeiturista.value = -1;
			form2.idEmpresa.disabled = true;
			form2.idLeiturista.disabled = true;
		}	
		
	}
	function MoveSelectedDataFromMenu2TO1(form, object, selectedObject){
		var form2 = document.forms[0];
		
		MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		if(form2.rotasSelecionadas[0]==null || form2.rotasSelecionadas[0].value == undefined){
			form2.idEmpresa.value = -1;
			form2.idLeiturista.value = -1;
			form2.idEmpresa.disabled = false;
			form2.idLeiturista.disabled = false;
		}
		
	}
	function MoveTodosFromMenu2TO1(form, object, selectedObject){
		var form2 = document.forms[0];
		
		MoverTodosDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		if(form2.rotasSelecionadas[0]==null || form2.rotasSelecionadas[0].value == undefined){
			form2.idEmpresa.value = -1;
			form2.idLeiturista.value = -1;
			form2.idEmpresa.disabled = false;
			form2.idLeiturista.disabled = false;
		}
		
	}	
</script>
</head>
<body leftmargin="5" topmargin="5" onload="">
<div id="formDiv"><html:form action="/filtrarRelatorioAcompanhamentoLeituristaAction"
	name="FiltrarRelatorioAcompanhamentoLeituristaForm"
	type="gcom.gui.relatorio.micromedicao.FiltrarRelatorioAcompanhamentoLeituristaForm"
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
					<td class="parabg">Filtrar Relatório de Acompanhamento do Leiturista</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">Para filtrar os dados para
					geração do relatório, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>

				<tr>

					<td height="10" width="145"><strong>Mês/Ano de Referência:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="mesAno" size="8" maxlength="7"
						tabindex="1" onkeyup="mascaraAnoMes(this, event);" /></td>

				</tr>

				<tr>
					<td width="150"><strong>Grupo de Faturamento:</strong></td>
					<td colspan="2" align="left"><html:select
						property="idGrupoFaturamento" tabindex="2" onchange="javascript:listarRota()">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoFaturamentoGrupo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td width="120">
						<strong>Rotas:</strong>
					</td>
					<td colspan="2">
					<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
						<tr>
							<td width="175">
							
								<div align="left"><strong>Disponíveis</strong></div>
								<logic:notPresent name="colecaoRotas">
								<html:select property="rotas" 
									size="6" 
									multiple="true" 
									style="width:190px">
									
									<html:option value="-1">&nbsp;</html:option>
								</html:select>
								</logic:notPresent>
								
								<logic:present name="colecaoRotas">
								<html:select property="rotas" 
									size="6" 
									multiple="true" 
									style="width:190px">
									
									<html:options collection="colecaoRotas" 
										labelProperty="descricao" 
										property="id" />
								</html:select>
								</logic:present>
							</td>

							<td width="5" valign="center"><br>
								<table width="50" align="center">
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												onclick="javascript:MoveTodosFromMenu1TO2('FiltrarRelatorioAcompanhamentoLeituristaForm', 'rotas', 'rotasSelecionadas');"
												value=" &gt;&gt; ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												onclick="javascript:MoveSelectedDataFromMenu1TO2('FiltrarRelatorioAcompanhamentoLeituristaForm', 'rotas', 'rotasSelecionadas');"
												value=" &nbsp;&gt;  ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												onclick="javascript:MoveSelectedDataFromMenu2TO1('FiltrarRelatorioAcompanhamentoLeituristaForm', 'rotas', 'rotasSelecionadas');"
												value=" &nbsp;&lt;  ">
										</td>
									</tr>
	
									<tr>
										<td align="center">
											<input type="button" 
												class="bottonRightCol"
												onclick="javascript:MoveTodosFromMenu2TO1('FiltrarRelatorioAcompanhamentoLeituristaForm', 'rotas', 'rotasSelecionadas');"
												value=" &lt;&lt; ">
										</td>
									</tr>
								</table>
							</td>

							<td>
								<div align="left">
									<strong>Selecionados</strong>
								</div>
								
								<html:select property="rotasSelecionadas" 
									size="6" 
									multiple="true" 
									style="width:190px">
								
								</html:select>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				

				<tr>
					<td width="150"><strong>Empresa:</strong></td>
					<td colspan="2" align="left">
						<html:select property="idEmpresa" tabindex="3"
							onchange="javascript:listarLeiturista()">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa"
								labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>


				<tr>

					<td height="10" width="145"><strong>Leiturista:</strong></td>
					<td colspan="2" align="left"><html:select property="idLeiturista"
						tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeiturista"
							labelProperty="descricao" property="id" />
					</html:select></td>

				</tr>
				<tr>
					<td><strong>Apresentar Horarios:</strong></td>
					<td colspan="2"><input type="radio" name="indicadorHorario" tabindex="3"
						value="S" checked="checked"> <strong>Sim</strong>&nbsp; <input
						type="radio" name="indicadorHorario" tabindex="3" value="N"> <strong>Não</strong>
				</tr>

				<tr>
					<td></td>
				</tr>
				<tr>
					<td>
						<input name="Button" 
							type="button" 
							class="bottonRightCol"
							value="Limpar" 
							align="left"
							onclick="window.location.href='/gsan/exibirFiltrarRelatorioAcompanhamentoLeituristaAction.do?menu=sim'"/>

					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right">
					<input type="button" name="ButtonFiltrar" class="bottonRightCol"
						value="Gerar"
						onClick="javascript:validaForm();">
					</td>
				</tr>
				
			</table>
			<p>&nbsp;</p>
		</tr>
		
		
	</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</html:form></div>
</body>
</html:html>

