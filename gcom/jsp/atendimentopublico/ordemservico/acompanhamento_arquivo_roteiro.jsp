<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<%@ page import="gcom.util.ConstantesSistema"%>
		<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServico"%>
		
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js">
		</script>
		<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>/lightbox/jquery.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>lightbox/jquery.lightbox-0.5.js"></script>
		<link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jquery.lightbox-0.5.css" media="screen" />
		<html:javascript staticJavascript="false"  formName="AcompanhamentoArquivosRoteiroActionForm" dynamicJavascript="true"/>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js">
		</script><html:javascript staticJavascript="false"  formName="AcompanharRoteiroProgramacaoOrdemServicoActionForm" dynamicJavascript="true"/>
		
		<style type="text/css">
			/* jQuery lightBox plugin - Gallery style */
			#gallery {
				background-color: #90C7FC;
				padding: 10px;
				width: 520px;
			}
			#gallery ul { list-style: none; }
			#gallery ul li { display: inline; }
			#gallery ul img {
				border: 5px solid #CBE5FE;
				border-width: 5px 5px 20px;
			}
			#gallery ul a:hover img {
				border: 5px solid #fff;
				border-width: 5px 5px 20px;
				color: #fff;
			}
			#gallery ul a:hover { color: #fff; }
		</style>
		
		<logic:present name="fecharPopup">
			<script type="text/javascript">
				$(document).ready(function(){
					$('[name=idOsProgramacaoAcompanhamentoServico]').removeAttr('checked');
					$('[name=idsRegistros]').removeAttr('checked');
				});
			</script>
		</logic:present>
		
		<logic:present name="exibirBotoes" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$('[name=idsRegistros]').change(function(){
						if($('[name=idsRegistros]:checked').size() > 0){
							$('#cadastrarMensagem, #incluirOs').removeAttr('disabled');
						}else{
							$('#cadastrarMensagem, #incluirOs').attr('disabled', true);
						}
					});
		
					$('[name=idOsProgramacaoAcompanhamentoServico]').change(function(){
						if($('[name=idOsProgramacaoAcompanhamentoServico]:checked').size() > 0){
							$('#remanejarOsEquipe, #informarSituacaoOS, #atualizarOsEquipe, #reordenarSequencialOS').removeAttr('disabled');
						}else{
							$('#remanejarOsEquipe, #informarSituacaoOS, #atualizarOsEquipe, #reordenarSequencialOS').attr('disabled', true);
						}
					});

					if ($('[name=idsRegistros]:checked').size() > 0){
						$('#cadastrarMensagem, #incluirOs').removeAttr('disabled');
					} else {
						$('#cadastrarMensagem, #incluirOs').attr('disabled', true);
					}

					if ($('[name=idOsProgramacaoAcompanhamentoServico]:checked').size() > 0){
						$('#remanejarOsEquipe, #informarSituacaoOS, #atualizarOsEquipe, #reordenarSequencialOS').removeAttr('disabled');
					} else {
						$('#remanejarOsEquipe, #informarSituacaoOS, #atualizarOsEquipe, #reordenarSequencialOS').attr('disabled', true);
					}
				});
			</script>
		</logic:present>
		
		<logic:notPresent name="exibirBotoes" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$('#cadastrarMensagem, #incluirOs, #remanejarOsEquipe, #informarSituacaoOS, #atualizarOsEquipe, #reordenarSequencialOS').attr('disabled', true);
				});
			</script>
		</logic:notPresent>
	
		<script type="text/javascript">
		
			$(document).ready(function(){
				
				$('[name=idOsProgramacaoAcompanhamentoServico]').change(function(){
					if($('[name=idOsProgramacaoAcompanhamentoServico]:checked').size() > 0){
						$('#botaoVisualizar').removeAttr('disabled');
					}else{
						$('#botaoVisualizar').attr('disabled', true);
					}
				});
			
				$('[name=idsRegistros]').change(function(){
					$('#consultarPercurso').attr('disabled', ($('[name=idsRegistros]:checked').size() == 0));
				});
			
				$('#botaoLiberado, #botaoEmCampo, #botaoFinalizado').click(function(){
					if ($('[name=idsRegistros]:checked').size() > 0) {
						var action = 'atualizarAcompanhamentoArqRoteiroAction.do?atualizar=' + $(this).attr('atualizar');
						$('#form').attr('action', action).submit(); 
					} else {
						alert("É necessário selecionar pelo menos um arquivo.");
					}
				});
				
				$('#botaoVisualizar').click(function(){
					if($('[name=idOsProgramacaoAcompanhamentoServico]:checked').size() == 1){
						var chave = $('[name=idOsProgramacaoAcompanhamentoServico]:checked').val();
						
						var myString = new String(chave);
						var splitString = myString.split("___");
						
						chave = splitString[0];

						var action = 'selecionarAcompanhamentoArquivosRoteiroAction.do?metodo=visualizarFotos&idOS=' + chave;
						
						$('#result').load(action, function(response, status, xhr) {
							if (status == "error") {
							    alert("Não existem fotos cadastradas para a Ordem de Serviço selecionada.");
						    } else {
								$('#gallery a').lightBox();
								$('#gallery a:first').click();
								window.scroll(0,0);
						    }
						});
					}else{
						alert('Marque apenas uma Ordem de Serviço.');
					}
				});
				
				if ($('[name=idOsProgramacaoAcompanhamentoServico]:checked').size() > 0){
					$('#botaoVisualizar').removeAttr('disabled');
				} else {
					$('#botaoVisualizar').attr('disabled', true);
				}

				if ($('[name=idsRegistros]:checked').size() > 0){
					$('#consultarPercurso').removeAttr('disabled');
				} else {
					$('#consultarPercurso').attr('disabled', true);
				}
			});
			
			var bCancel = false;
			
			function validarForm(form){
				var form = document.forms[0];
				if(testarCampoValorZero(document.AcompanharRoteiroProgramacaoOrdemServicoActionForm.idEmpresa, 'Empresa')  && 
				   testarCampoValorZero(document.AcompanharRoteiroProgramacaoOrdemServicoActionForm.dataProgramacao, 'Data Programação')) {
					var camposInvalidos = "";
					
					if(form.dataProgramacao.value == null || form.dataProgramacao.value == ""){
						camposInvalidos += "Informe a Data de Programação.\n";
					}if(form.idEmpresa.selectedIndex == 0){
						camposInvalidos += "Selecione a Empresa.";
					}
					if(camposInvalidos == ""){
						if (validaData(form.dataProgramacao)){
							form.action = 'selecionarAcompanhamentoArquivosRoteiroAction.do?limparSessao=true';
						    form.submit();
						}
					}else{
						alert(camposInvalidos);
					}
				}
			}
			
			function extendeTabelaArquivos(display){
				var form = document.forms[0];
			
				if(display){
				  	eval('layerHideDadosArquivos').style.display = 'none';
						eval('layerShowDadosArquivos').style.display = 'block';
				}else{
				  	eval('layerHideDadosArquivos').style.display = 'block';
						eval('layerShowDadosArquivos').style.display = 'none';
				}
			}
			
			function verificaTabela(achou){
				 if (achou == '2'){
				  	eval('layerHideDadosArquivos').style.display = 'block';
					eval('layerShowDadosArquivos').style.display = 'none';
				 }else if (achou == '1'){
					eval('layerHideDadosArquivos').style.display = 'none';
					eval('layerShowDadosArquivos').style.display = 'block';
				 }
			}
			
			function extendeTabelaOS(idEquipe){
				var form = document.forms[0];
				var display = eval('layer'+idEquipe).style.display;
				if(display == "none"){
			  	    eval('layer'+idEquipe).style.display = 'block';
				}else{
				    eval('layer'+idEquipe).style.display = 'none';
				}
			}
			
			function getIdEmpresa(){
			 	var form = document.forms[0];
				if ( form.idPriorizacaoTipo.value != null ) {
			    	form.action = form.idEmpresa.value;
				    form.submit();
				}
			}
			
			function getIdSituacao(){
			 	var form = document.forms[0];
				if ( form.idPriorizacaoTipo.value != null ) {
			    	form.action = form.idEmpresa.value;
				    form.submit();
				}
			}
			
			function atualizarOs(){
				var form = document.forms[0];
				var chave = selecionouOrdemServico();
				if(chave != false){
					myString = new String(chave);
					splitString = myString.split("___");
					
					chave = splitString[0];
					chaveArquivo = splitString[1];
					
					var dataProgramacaoRoteiro = form.dataProgramacao.value;

					var url = 'exibirAtualizarOrdemServicoAction.do?veioAcompanhamentoRoteiro=true&retornoTela=selecionarAcompanhamentoArquivosRoteiroAction.do&primeiraVez=1&ehPopup=true&numeroOS='+
						chave+'&dataProgramacao='+dataProgramacaoRoteiro+'&chaveArquivo='+chaveArquivo;
					abrirPopup(url,400,600);
				}
			}
			
			function CheckboxNaoVazio(campo){
				form = document.forms[0];
				retorno = false;
					
				for(indice = 0; indice < form.elements.length; indice++){
					if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
						retorno = true;
						break;
					}
				}
					
				if (!retorno){
					alert('É necessário marcar algum arquivo.');
				}
					
				return retorno;
			}
			
			function verificarQtdArquivosSelecionado(campo){
				form = document.forms[0];
				retorno = false;
				cont = 0;
				
				for(indice = 0; indice < form.elements.length; indice++){
					if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
						cont = cont + 1;
					}
				}
				
				if (cont > 1){
					retorno = true;
				}
				
				return retorno;
			}
			
			function abrirPopupConsultarPercurso(){
				form = document.forms[0];
				
				if(CheckboxNaoVazio(document.forms[0].idsRegistros)){
					if (verificarQtdArquivosSelecionado(document.forms[0].idsRegistros)){
						alert('Apenas um arquivo deverá ser selecionado!');
					}else{
						abrirPopup('exibirConsultarPercursoAcompanhamentoArqRoteiroPopupAction.do', 300, 600);
					}
				}
			}
			
			function selecionouArquivo(){
				var form = document.forms[0];
			    var selecionados = form.elements['idsRegistros'];
				var jaSelecionado = false;
				var retorno = false;
				
				for (i=0; i< selecionados.length; i++) {
			
					if(selecionados[i].checked){
			
						if(jaSelecionado == false){
							jaSelecionado = true;
							retorno = selecionados[i].value;
						}else if(selecionados[i].value != retorno){
							alert('Marque apenas um Arquivo');
							return false;
						}
					}
				}
			
				if(jaSelecionado == false){
					alert('É necessário marcar algum Arquivo');
					return false;
				}
				
				return retorno;
			}

			function incluirOS(){
				chave = selecionouArquivo();
				if(chave != false){
					var url = 'exibirAcompanhamentoArquivosRoteiroIncluirOrdemServicoAction.do?veioAcompanhamentoRoteiro=true&chave='+chave;
					abrirPopup(url,400,600);
				}
			}

			function reordenarSequencial(){
				chave = selecionouOrdemServico();
				if(chave != false){

					myString = new String(chave);
					splitString = myString.split("___");

					chave = splitString[0];
					chaveArquivo = splitString[1];

					var dataProgramacaoRoteiro = form.dataProgramacao.value;
					
					var url = 'exibirAcompanhamentoArquivosRoteiroReordenarSequencialAction.do?veioAcompanhamentoRoteiro=true&chave='+
						chave+'&chaveArquivo='+chaveArquivo+'&dataProgramacao='+dataProgramacaoRoteiro;
						
					abrirPopup(url,300,600);
				}
			}
			
			function remanejarOs(){
				chave = selecionouOrdemServico();
				if(chave != false){
					myString = new String(chave);
					splitString = myString.split("___");
					
					chave = splitString[0];
					chaveArquivo = splitString[1];
					
					var url = 'exibirAcompanhamentoArquivosRoteiroRemanejarOrdemServicoAction.do?veioAcompanhamentoRoteiro=true&chave='+chave+'&chaveArquivo='+chaveArquivo;
					abrirPopup(url,400,600);
				}
			}
			
			function informarSituacao(){
				var form = document.forms[0];
				chave = selecionouOrdemServico();
				if(chave != false){
					myString = new String(chave);
					splitString = myString.split("___");
					
					chave = splitString[0];
					chaveArquivo = splitString[1];
					
					var url = 'exibirInformarSituacaoOSAcompanhamentoArquivosRoteiroAction.do?veioAcompanhamentoRoteiro=true&chave='+chave+'&chaveArquivo='+chaveArquivo;
					abrirPopup(url,330,660);
				}
			}
			
			function selecionouOrdemServico(){
					var form = document.forms[0];
				    var selecionados = form.elements['idOsProgramacaoAcompanhamentoServico'];
					var jaSelecionado = false;
					var retorno = false;
					
			
					if(selecionados.length == null){
						if(selecionados.checked){
							retorno = selecionados.value;
							jaSelecionado = true;
						}
					}else{
			
						for (i=0; i< selecionados.length; i++) {
							if(selecionados[i].checked){
				
								if(jaSelecionado == false){
									jaSelecionado = true;
									retorno = selecionados[i].value;
								}else{
									alert('Marque apenas uma Ordem de Serviço');
									return false;
								}
							}
						}
					}
					
					if(jaSelecionado == false){
						alert('É necessário marcar alguma Ordem de Serviço');
						return false;
					}
					
					return retorno;
				}
			
			function cadastrarMensagemPopup(){
				chave = selecionouArquivo();
				if(chave != false){
					var url = 'exibirCadastrarMensagemAcompanhamentoServicoAction.do?limpar=ok&txac_id='+chave;
					abrirPopup(url,415,670);
				}
			}
			
		</script>
	
	</head>

	<body leftmargin="5" topmargin="5" onload="verificaTabela('<%=session.getAttribute("achou")%>'); setarFoco(document.forms[0].dataProgramacao);">
		<div id="formDiv">
			<html:form action="/selecionarAcompanhamentoArquivosRoteiroAction"
				name="AcompanharRoteiroProgramacaoOrdemServicoActionForm"
				type="gcom.gui.atendimentopublico.ordemservico.AcompanharRoteiroProgramacaoOrdemServicoActionForm"
				method="post"
				onsubmit="return validatorAcompanhamentoArquivosRoteiroActionForm(this);" styleId="form">
				
				<html:hidden property="numeroRA"/>
				<html:hidden property="idOrdemServico"/>
				<html:hidden property="sequencialProgramacaoPrevisto"/>
				
				<html:hidden property="equipeSelecionada"/>
				
				<html:hidden property="idEquipePrincipal"/>	
				<html:hidden property="idEquipeAtual"/>
				
				<html:hidden property="situacaoOrdemServico"/>
				<html:hidden property="motivoNaoEncerramento"/>
				<html:hidden property="sequencialProgramacao"/>
				<html:hidden property="chaveEquipe"/>
				<html:hidden property="dataRoteiro"/>
				<html:hidden property="chavesRelatorio"/>
			
				<%@ include file="/jsp/util/cabecalho.jsp"%>
				<%@ include file="/jsp/util/menu.jsp" %>
			
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
					  <td width="600" valign="top" class="centercoltext">
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
									<td class="parabg">Acompanhamento dos Arquivos de Roteiro</td>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
								</tr>
							</table>
							<p>&nbsp;</p>
							<table width="100%" border="0">
								<tr>
									<td colspan="2">Para consultar os arquivos informe os dados abaixo:</td>
								</tr>
								<tr>
							</table>
							<table width="100%" border="0">
								<tr>
									<td colspan="2" width="70%">
										<strong>
											Data da Programa&ccedil;&atilde;o:
											<font color="#FF0000">*</font>
										</strong>
									</td>
					                <td colspan="6">
					                	<span class="style2">
					                	
					                	<strong> 
											
											<html:text property="dataProgramacao" 
												size="11" 
												maxlength="10" 
												tabindex="1" 
												onkeyup="mascaraData(this, event);"
												onkeypress="return isCampoNumerico(event);"/>
											
											<a href="javascript:abrirCalendarioReplicando('AcompanharRoteiroProgramacaoOrdemServicoActionForm', 'dataProgramacao');">
												<img border="0" 
													src="<bean:message key='caminho.imagens'/>calendario.gif" 
													width="16" 
													height="15" 
													border="0" alt="Exibir Calendário" 
													/></a>
											</strong>
											<strong>(dd/mm/aaaa)</strong>
					                  	</span>
					              	</td>
								</tr>
								<tr>
									<td colspan="2">
										<strong>
											Empresa:
											<font color="#FF0000">*</font>
										</strong>
									</td>
									<td colspan="2">
										<html:select property="idEmpresa" tabindex="3">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<strong>
											Situa&ccedil;&atilde;o:
										</strong>
									</td>
									<td colspan="2">
										<html:select property="idSituacao" tabindex="4">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoSituacaoTransmissaoLeitura" labelProperty="descricaoSituacao" property="id" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
									</td>
								</tr>
								<tr>
									<td colspan="2" >
										<input name="Button" type="button" class="bottonRightCol"
											value="Desfazer" align="left" tabindex="5"
											onclick="window.location.href='<html:rewrite page="/exibirAcompanhamentoArquivosRoteiroAction.do?menu=sim"/>'">
										<input type="button" name="ButtonCancelar" class="bottonRightCol"
											value="Cancelar" tabindex="6 "
											onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									</td>
									<td align="right">
										<gsan:controleAcessoBotao name="Botao" value="Selecionar"
											onclick="validarForm(document.forms[0]);"
											url="selecionarAcompanhamentoArquivosRoteiroAction.do" tabindex="7" />
									</td>
								</tr>
								<tr height="10">
									<td colspan="6">
										<font color="#000000" style="font-size:11px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Mudar Situa&ccedil;&atilde;o para:</strong></font>
									</td>
								</tr>
								<tr>
									<td colspan="6" height="23">
										<input name="botaoLiberarArq" type="button" class="bottonRightCol" value="Liberado" tabindex="8" id="botaoLiberado" atualizar="2" />
										<input name="botaoEmCampoArq" type="button" class="bottonRightCol" value="Em Campo" tabindex="9" id="botaoEmCampo" atualizar="3" />
										<input name="botaoFinalizarArq" type="button" class="bottonRightCol" value="Finalizado" tabindex="10" id="botaoFinalizado" atualizar="4" />
									</td>						
								</tr>
								<tr>
									<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
								</tr>
								<tr>
									<td colspan="5" width="100%">
								 		<div id="layerHideDadosArquivos" <%if(session.getAttribute("achou") != null){%> style="display:none"<%}else{ %>style="display:block" <%} %>>
				             				<table width="100%" border="0" bgcolor="#99CCFF">
				    							<tr bgcolor="#99CCFF">
				                    				<td align="center">
					                   					<span class="style2">
						                   					<a href="javascript:extendeTabelaArquivos(true);" tabindex="11"/>
						                    					<b >Dados dos Arquivos</b>
						                    				</a>
					                   					</span>
				                    				</td>
				                   				</tr>
				                  			</table>
				           				</div>
								 	</td>
								</tr>
								</table>
										<div id="layerShowDadosArquivos" <%if(session.getAttribute("achou") == null){%> style="display:none"<%}else{ %>style="display:block" <%} %>>
											<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
												<tr bgcolor="#99CCFF">
							                   		<td align="center">
							           					<span class="style2">
							             					<a href="javascript:extendeTabelaArquivos(false);" tabindex="12"/>
							             						<b>Dados dos Arquivos</b>
							             					</a>
							           					</span>
							               			</td>
							              		</tr>
							              		<tr bgcolor="#cbe5fe" >
							              			<td width="100%" align="center">
														<div style="height:400px; overflow: auto;">
															<table width="100%" bgcolor="#99CCFF">
																<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
																	<td width="10%" bgcolor="#90c7fc">
																	</td>
																	<td width="15%" bgcolor="#90c7fc">
																	<div align="center"><strong>Equipe</strong></div>
																	</td>
																	<td width="15%" bgcolor="#90c7fc">
																	<div align="center"><strong>Qtde de OS programadas/<br>Qtde de OS transmitidas</strong></div>
																	</td>
																	<td width="10%" bgcolor="#90c7fc">
																	<div align="center"><strong>Situa&ccedil;&atilde;o</strong></div>
																	</td>
																</tr>
																
																	<logic:present name="colecaoAcompanhamentoArquivosRoteiro">
																		<%int cont = 0;%>
																		<logic:iterate name="colecaoAcompanhamentoArquivosRoteiro"
																			id="acompanhamentoArquivosRoteiro">
																			<%cont = cont + 1;
																			if (cont % 2 == 0) {%>
																				<tr bgcolor="#cbe5fe" class="styleFontePequena" >
																					<%} else {%>
																				<tr bgcolor="#FFFFFF" class="styleFontePequena">
																					<%}%>
																					<!-- <pg:item>  -->
																					<td width="10%">
																						<div align="center">
																							<logic:present name="acompanhamentoArquivosRoteiro" property="idAcompanhamentoArquivosRoteiro">
																								<c:choose>
																									<c:when test='${acompanhamentoArquivosRoteiro.qtdOrdemServicoProgramadas == acompanhamentoArquivosRoteiro.qtdOrdemServicoTransmitidas && acompanhamentoArquivosRoteiro.dsSituacao == "FINALIZADO"}'>
																										<html:checkbox property="idsRegistros"
																										value="${acompanhamentoArquivosRoteiro.idAcompanhamentoArquivosRoteiro}" disabled="true" tabindex="13"/>
																									</c:when>
																									<c:otherwise>
																										<html:checkbox property="idsRegistros"
																										value="${acompanhamentoArquivosRoteiro.idAcompanhamentoArquivosRoteiro}"/>
																									</c:otherwise>
																								</c:choose>
																							</logic:present>
																						</div>
																					</td>
																					<td width="15%" align="center">	
															                     		<span class="style2">
															                     		<a href="javascript:extendeTabelaOS(${acompanhamentoArquivosRoteiro.idAcompanhamentoArquivosRoteiro});"/>
															                      			<b>${acompanhamentoArquivosRoteiro.nmEquipe}</b>
															                      		</a>
															                     		</span>
																					</td>
																					<td width="15%">
																						<div align="center">${acompanhamentoArquivosRoteiro.qtdOrdemServicoProgramadas} / ${acompanhamentoArquivosRoteiro.qtdOrdemServicoTransmitidas}</div> 
																					</td>
																					<td width="10%">
																						<logic:present name="acompanhamentoArquivosRoteiro" property="dsSituacao">
																							<div align="center">
																								<c:choose>
																									<c:when test='${acompanhamentoArquivosRoteiro.dsSituacao == "EM CAMPO"}'>
																										<span class="style2">
																			                     			<html:link page="/retornarArquivoTxtAcompanhamentoArqRoteiroAction.do"
																											    title="${acompanhamentoArquivosRoteiro.dsSituacao}"
																											    paramName="acompanhamentoArquivosRoteiro" paramProperty="idAcompanhamentoArquivosRoteiro"
																											    paramId="idRegistroAtualizacao">
																				                      				<b>${acompanhamentoArquivosRoteiro.dsSituacao}</b>
																			                      			</html:link>
																			                     		</span>
																									</c:when>
																									<c:otherwise>
																										${acompanhamentoArquivosRoteiro.dsSituacao}
																									</c:otherwise>
																								</c:choose>
																								<html:hidden property="dsSituacao" name="acompanhamentoArquivosRoteiro" styleId="situacao"/>
																							</div>
																						</logic:present>
																					</td>
																				</tr>	
																				<tr class="styleFontePequena">
																					<td  align="center" colspan="4">
																							<div id="layer${acompanhamentoArquivosRoteiro.idAcompanhamentoArquivosRoteiro}"  style="display:none">
																									<table bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito" bgcolor="#99CCFF" cellpadding="0" cellspacing="2">
																										<tr  bgcolor="#90c7fc"> 
																			                            	
																			                            	<td width="5%" >
																			                              	</td>
																			                              	<td width="8%" >
																			                              		<div align="center"><strong>Ordem de<br>Servi&ccedil;o</strong></div>
																			                              	</td>
																			                              	<td width="8%" >
																			                              		<div align="center"><strong>Sequencial</strong></div>
																			                              	</td>
																			                              
																			                              	<td width="20%">
																			                              		<div align="center"><strong>Endere&ccedil;o</strong></div>
																			                              	</td>
																	
																			                              	<td width="8%" >
																			                              		<div align="center"><strong>Situa&ccedil;&atilde;o</strong></div>
																			                              	</td>
																			                              	
																			                              	<td width="15%" >
																			                              		<div align="center"><strong>Tipo de Servi&ccedil;o</strong></div>
																			                              	</td>
																		                                </tr>
																											<%int cont2 = 0;%>
																										<logic:present name="acompanhamentoArquivosRoteiro" property="osProgramacaoAcompServicoHelper">
																											<logic:iterate name="acompanhamentoArquivosRoteiro"
																												property="osProgramacaoAcompServicoHelper" id="osProgramacaoAcompServico">
																												<%cont2 = cont2 + 1;
																												if (cont2 % 2 == 0) {%>
																													<tr bgcolor="#cbe5fe">
																														<%} else {%>
																													<tr bgcolor="#FFFFFF">
																														<%}%>
																														<!-- <pg:item>  -->
																														
																														<td width="5%">
																															<div align="center">
																																<logic:present name="osProgramacaoAcompServico" property="idOsProgramacaoAcompanhamentoServico">
			
																																	<html:checkbox property="idOsProgramacaoAcompanhamentoServico" 
																																		value="${osProgramacaoAcompServico.idOrdemServico}___${acompanhamentoArquivosRoteiro.idAcompanhamentoArquivosRoteiro}" />
																																</logic:present>
																															</div>
																														</td>
																														<td width="8%" align="center">	
																								                     		${osProgramacaoAcompServico.idOrdemServico}
																														</td>
																														<td width="8%" align="center">	
																								                     		${osProgramacaoAcompServico.nnSequencialProgramacao}
																														</td>
																														<td width="20%">
																															<div align="center">${osProgramacaoAcompServico.dsEndereco}</div>
																														</td>
																														<td width="8%">
																															<div align="center">${osProgramacaoAcompServico.dsSituacao}</div>
																														</td>
																														<td width="15%">
																															<div align="center">${osProgramacaoAcompServico.dsServicoTipo}</div>
																														</td>
																													</tr>
																												</logic:iterate>
																											</logic:present>
																									</table>
																							</div>
																					</td>
																				</tr>							
																	</logic:iterate>
																</logic:present>
														</table>
													</div>
												</td>
											</tr>
											<tr>
												<td>
												<%if (session.getAttribute("achou") != null){%>
													<table width="100%" bgcolor="#cbe5fe">
														<tr>
															<td align="left">
																<input name="ButtonIncluirOS"
								                      				type="button"
								                      				class="bottonRightCol"
																	onClick="javascript:incluirOS();"
								                      				value="Incluir OS" tabindex="14"
								                      				id="incluirOs"
								                      				>
																<input name="ButtonRemanejarOs"
								                        			type="button"
								                        			class="bottonRightCol"
																	onClick="javascript:remanejarOs();"
								                        			value="Remanejar OS"
								                        			tabindex="15"
								                        			id="remanejarOsEquipe"
								                        			>
								                        		<input name="ButtonAtualizarOs"
								                        			type="button"
								                        			class="bottonRightCol"
																	onClick="javascript:atualizarOs();"
								                        			value="Atualizar OS"
								                        			tabindex="15"
								                        			id="atualizarOsEquipe"
								                        			>
															</td>
														</tr>
														<tr>
															<td align="left">
																<input name="ButtonInformarSituacaoOS"
								                      				type="button"
								                      				class="bottonRightCol"
																	onClick="javascript:informarSituacao();"
								                      				value="Informar Situação da OS na Programação" tabindex="14"
								                      				id="informarSituacaoOS"
								                      				>
								                      			<input type="button"
																	name="botaoVisualizarFotos"
																	class="bottonRightCol"
																	value="Visualizar Foto"
																	tabindex="18"
																	id="botaoVisualizar"
																	>
															</td>
														</tr>
														<tr>
															<td colspan="2" align="left">
																<input type="button"
																	name="BotaoConsultarPercurso"
																	value="Consultar Percurso" 
																	class="bottonRightCol"
																    onclick="javascript:abrirPopupConsultarPercurso();"
																    id="consultarPercurso"
																	tabindex="16"
																	>
																<input name="ButtonCadastrarMensagem"
								                      				type="button"
								                      				class="bottonRightCol"
																	onClick="javascript:cadastrarMensagemPopup();"
								                      				value="Cadastrar Mensagem"
								                      				tabindex="17"
								                      				id="cadastrarMensagem"
								                      				>
																<input name="ButtonReordenarSequencial"
								                      				type="button"
								                      				class="bottonRightCol"
																	onClick="javascript:reordenarSequencial();"
								                      				value="Reordenar Sequencial" tabindex="14"
								                      				id="reordenarSequencialOS"
								                      				>
															</td>
														</tr>
													</table>
												<%}%>
												<td>
											</tr>
											<tr>
												<td height="27" valign="top" colspan="10">
			                                    	&nbsp;
				                     			</td>
											</tr>
										</table>				
									<p>&nbsp;</p>
									</div>
								</td>
							</tr>
						</table>
				
					<%@ include file="/jsp/util/rodape.jsp"%>	
				</html:form>
		</div>
		<div id="result" style="display:none;">
		</div>
	</body>
	
	<%@ include file="/jsp/util/telaespera.jsp"%>


</html:html>