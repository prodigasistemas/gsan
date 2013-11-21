<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<%@ page import="gcom.cadastro.cliente.ClienteImovel"%>
<%@ page import="gcom.cadastro.cliente.ClienteFone"%>
<%@ page import="gcom.cadastro.imovel.ImovelSubcategoria"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<html:javascript staticJavascript="false" formName="ExibirAtualizarDadosClientesPopupActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"><!--

    function validarForm() {
		var form = document.forms[0];
		atualizarCliente(form.codClienteImovel.value);
		form.action = 'atualizarDadosImovelPopupPROMAISAction.do';
		submeterFormPadrao(form);
	}


	//Função para pegar o clienteImovel selecionado para atualizar.
	function atualizarCliente(cliente){
		var form = document.forms[0];

		 if(cliente != undefined && cliente != ''){			
			 
			form.codClienteImovel.value = cliente;
			 
			var identificador = document.getElementById(form.codClienteImovel.value  + 'idCliente');
			var nome = document.getElementById(form.codClienteImovel.value  + 'nome');
			var cpfCnpj = document.getElementById(form.codClienteImovel.value  + 'cpfCnpj');
			//var tipoPessoa = document.getElementById(form.codClienteImovel.value  + 'tipoPessoa');

			document.getElementById(form.codClienteImovel.value+'cpfCnpj').disabled = false;
			document.getElementById(form.codClienteImovel.value+'nome').disabled = false;
			document.getElementById(form.codClienteImovel.value+'radio').checked = true;
			
			desabilitarClientes(form.codClienteImovel.value); 
		
			form.idCliente.value = identificador.value;
			form.novoNomeCliente.value = nome.value;			
			form.cpfCnpjCliente.value = cpfCnpj.value;
			//form.tipoPessoa.value = tipoPessoa.value;

		}
	}

	function desabilitarClientes(codClienteImovel){
		var form = document.forms[0];
		var totalFields = form.elements.length;

		for (i = 0; i < totalFields; i++) {
			if(form.elements[i].type == 'radio'){
	    		if(form.elements[i].value != codClienteImovel 
	    	    	&& document.getElementById(form.elements[i].value+'cpfCnpj')!=null
	    	    	&& document.getElementById(form.elements[i].value+'nome')!=null){
					document.getElementById(form.elements[i].value+'cpfCnpj').disabled = true;
					document.getElementById(form.elements[i].value+'nome').disabled = true;
	    		}		
			}
		}
	}

	function acaoManterCliente(){
		var form = document.forms[0];
		if(form.idCliente.value != null && form.idCliente.value != ''){		
			enviarDados(form.idCliente.value, 'url', 'manterCliente');
			
		}else{
			alert('Selecione o cliente que deseja manter.');
		}
	}

	function acaoManterImovel(){
		var form = document.forms[0];
		if(form.matricula.value != null && form.matricula.value != ''){

			enviarDados(form.matricula.value, 'url', 'manterImovel');
			
		}
	}

	function redirecionarUrl(url,codigoClienteImovel){
  		var form = document.forms[0];
  	
		if(!document.getElementById(codigoClienteImovel+'cpfCnpj').disabled){
  			form.action = url;
	    	form.submit();
	    }
  	}

	function removerClienteFone(codigoClienteImovel,codigoClienteFone){
		if(!document.getElementById(codigoClienteImovel+'cpfCnpj').disabled){
			if (confirm ("Confirma remoção?")) {
				
				var nome = document.getElementById(codigoClienteImovel  + 'nome').value;
				var cpfCnpj = document.getElementById(codigoClienteImovel  + 'cpfCnpj').value;
						
				redirecionarSubmit('exibirAtualizarDadosClientesPopupAction.do?codigoClienteRemover='+codigoClienteImovel+'&removerFone=' + codigoClienteFone +'&nomeDigitado='+nome+'&cpfCnpj='+cpfCnpj);
			}
		}
	}

	function comandoFecharPopup(){
		var form = document.forms[0];

		if(form.fecharPopup != null && form.fecharPopup.value != '' && form.fecharPopup.value == 'true'){
			form.fecharPopup.value = null;
			window.close();
		}
	}

	function telefonePadrao(codigoCliente, codigoClienteFone){
		redirecionarSubmit('exibirAtualizarDadosClientesPopupAction.do?idCliente=' + codigoCliente + '&fonePadrao=' + codigoClienteFone);
	}

	function reload(){
		 var form = document.forms[0];
		 form.action = 'exibirAtualizarDadosClientesPopupAction.do';
	  	 form.submit();
	}

	function abrirManterImovel(idImovel){
		
		opener.redirecionarManterImovel(idImovel);
		window.close();
	}
	function abrirManterCliente(idCliente){
		
		opener.redirecionarManterCliente(idCliente);
		window.close();
	}
	
	function abrirPopupAddTelefone(idImovel,idCliente,clienteImovel){
		
		var nome = document.getElementById(clienteImovel  + 'nome').value;
		var cpfCnpj = document.getElementById(clienteImovel  + 'cpfCnpj').value;
		
		abrirPopup('exibirInformarTelefoneAction.do?limparForm=S&idImovel='+idImovel+'&idCliente='+idCliente+'&nomeDigitado='+nome+'&cpfCnpj='+cpfCnpj+'&clienteImovel='+clienteImovel, 300, 600);
	}
	function atualizarForm(nome,cpfCnpj){
		
		var form = document.forms[0];
			
		if(nome != undefined && nome != ''){					
			form.novoNomeCliente.value = nome;
			document.getElementById(form.codClienteImovel.value  + 'nome').value = nome;	
		}	
		if(cpfCnpj != undefined && cpfCnpj != ''){	
			form.cpfCnpjCliente.value = cpfCnpj;
			document.getElementById(form.codClienteImovel.value  + 'cpfCnpj').value = cpfCnpj;
		}			
	}
	
--></script>
</head>


<body leftmargin="0" topmargin="0" onunload="self.focus()"
	onload="comandoFecharPopup(); 
	resizePageSemLink(800,600);
	setarFoco('${requestScope.nomeCampo}');
	atualizarCliente('${requestScope.cliente}');
	atualizarForm('${requestScope.nomeDigitado}','${requestScope.cpfCnpj}')">


<html:form action="/atualizarDadosClientesPopupAction.do" 
	name="ExibirAtualizarDadosClientesPopupActionForm"
	type="gcom.gui.atendimentopublico.ExibirAtualizarDadosClientesPopupActionForm"
	method="post">
	
	<html:hidden name="ExibirAtualizarDadosClientesPopupActionForm"	property="idCliente"/>
    <html:hidden name="ExibirAtualizarDadosClientesPopupActionForm" property="novoNomeCliente"/>
    <html:hidden name="ExibirAtualizarDadosClientesPopupActionForm"	property="cpfCnpjCliente"/>
    <html:hidden name="ExibirAtualizarDadosClientesPopupActionForm"	property="fecharPopup"/>
    
	<table width="680" border="0" cellpadding="0" cellspacing="0">
		<tr>
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
					<td class="parabg" align="center">Atualizar Dados Cadastrais de Clientes - PROMAIS</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td align="center">IMPORTANTE : Só será possível ajustar nesta
					tela:</td>
				</tr>
				<tr>
					<td align="center">1. O NOME DO CLIENTE (em até cinco caracteres);</td>
				</tr>
				<tr>
					<td align="center">2. O CPF/CNPJ (sem alteração do tipo de pessoa - PF/PJ);</td>
				</tr>
				<tr>
					<td align="center">3. Cadastro de Telefones dos Clientes (Incluindo ou removendo).</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="center"> *** As demais alterações serão disponibilizadas através dos Botões de Manutenção. *** </td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="center" style="background-color: #79BBFD">
					   <strong>Dados do Imóvel</strong></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="1" border="0">
				<tr>
					<td>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td>
								<strong>Imovel:</strong>
								<a href="" onclick="abrirManterImovel('<bean:write name="ExibirAtualizarDadosClientesPopupActionForm" property="matricula" />');">
								<bean:write name="ExibirAtualizarDadosClientesPopupActionForm" property="matricula" />
								</a>
							</td>

							
						</tr>
						<tr>
							<td>
								<strong>Inscrição:</strong>
								<bean:write name="ExibirAtualizarDadosClientesPopupActionForm" property="inscricao" />
							</td>
						</tr>
					</table>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td align="center" style="background-color: #79BBFD">
							   <strong>Endereço</strong>
							</td>
						</tr>
						
						<tr bgcolor="#FFFFFF">
							<td align="center">
							<div class="style9">
								<bean:write name="ExibirAtualizarDadosClientesPopupActionForm" property="endereco" />
							 &nbsp;</div>
							</td>

						</tr>
						
					</table>
					
					<br>
					
					<table width="100%" cellpadding="0" cellspacing="1">
						
						<tr>
						</tr>
						
						<tr>
							<td width="19" bgcolor="#99CCFF" align="center"><strong>Categoria</strong></td>
							<td width="56%" bgcolor="#99CCFF" align="center"><strong>Subcategoria</strong></td>
							<td width="25%" bgcolor="#99CCFF" align="center"><strong>Quantidade de Economias</strong></td>
						</tr>
						
						<%int cont = 0;%>
						<logic:iterate name="ExibirAtualizarDadosClientesPopupActionForm"
							id="imovelSubcategoria" 
							type="ImovelSubcategoria" 
							property="categoriaSubcategoriaEconomia">
							
							<% cont = cont + 1;
							if (cont % 2 == 0) { %>
								<tr bgcolor="#cbe5fe">
							<% } else { %>
								<tr bgcolor="#FFFFFF">
							<% } %>

								<td width="19%" align="left">
									<div align="center">
									<font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> 
										<logic:present	name="imovelSubcategoria" property="comp_id">
											<bean:define name="imovelSubcategoria" property="comp_id" id="comp_id" />
											<logic:present name="comp_id" property="subcategoria">
												<bean:define name="comp_id" property="subcategoria" id="subcategoria" />
												<logic:present name="subcategoria" property="categoria">
													<bean:define name="subcategoria" property="categoria" id="categoria" />
													<bean:write name="categoria" property="descricao" />
												</logic:present>
											</logic:present>
										</logic:present> 
									</font>
									</div>
								</td>
								
								<td width="56%" align="left">
									<div align="center">
									<font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> 

										<logic:present name="imovelSubcategoria" property="comp_id">
											<bean:define name="imovelSubcategoria" property="comp_id" id="comp_id" />
											<logic:present name="comp_id" property="subcategoria">
												<bean:define name="comp_id" property="subcategoria" id="subcategoria" />
												<bean:write name="subcategoria" property="descricao" />
											</logic:present>
										</logic:present> 
									</font>
									</div>
								</td>

								<td width="25%" align="center">
									<div align="center">
									<font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> 
										<logic:present name="imovelSubcategoria" property="quantidadeEconomias">
											<bean:write name="imovelSubcategoria"
												property="quantidadeEconomias" />
										</logic:present> 
									</font>
									</div>
								</td>
								</tr>
						</logic:iterate>						

						
						<tr>	
						    <td  align="center" bgcolor="#99CCFF"> </td>
												    
							<td  align="center" bgcolor="#99CCFF">
								<strong>Total de Economias</strong>
							</td>

							<td align="center" bgcolor="#90c7fc">
								<font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:write name="ExibirAtualizarDadosClientesPopupActionForm" property="totalEconomias" />
								</font>
							</td>
						</tr>
					</table>

					<table width="100%" cellpadding="0" cellspacing="3">
						<tr>
							<td>							   
							</td>
						</tr>
					</table>

					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td align="center" style="background-color: #79BBFD" >
							<strong>Dados do(s) Cliente(s)</strong></td>
						</tr>
						<tr>
							<td><logic:iterate
								name="ExibirAtualizarDadosClientesPopupActionForm"
								id="clienteImovel" 
								type="ClienteImovel" 
								property="colecaoClienteImovel">
								
								<table width="100%" cellpadding="0" cellspacing="1">
									<tr>
										<td bgcolor="#99CCFF" width="20%">
											<strong>Tipo Relação</strong>
										</td>

										<td style="background-color: FFFFFF" width="30%"><bean:write
											name="clienteImovel" property="clienteRelacaoTipo.descricao" />
										</td>

										<td bgcolor="#99CCFF" width="25%">
											<strong>Tipo Pessoa:</strong>
										</td>
																						
										<td style="background-color: FFFFFF" width="25%">
											<logic:equal name="clienteImovel" 
												property="cliente.clienteTipo.indicadorPessoaFisicaJuridica" 
												value="1">
											PESSOA FÍSICA
										    </logic:equal>

											<logic:equal name="clienteImovel" 
												property="cliente.clienteTipo.indicadorPessoaFisicaJuridica" 
												value="2">
											PESSOA JURÍDICA
										    </logic:equal>
										</td>																
									</tr>

									<tr>
										<td align="center" style="background-color: #79BBFD">
											<strong>Código</strong>
										</td>

										<td style="background-color: #79BBFD" colspan="2">
											<strong>Nome do Cliente</strong>
										</td>

										<td style="background-color: #79BBFD">
											<strong>CPF/CNPJ</strong>
										</td>
									</tr>
									<tr>
										<td>
										    <input type="radio" name="codClienteImovel" 
												id="${clienteImovel.id}radio" 
												value="${clienteImovel.id}"											
												onclick="atualizarCliente(${clienteImovel.id});" />											
											<a href="" onclick="abrirManterCliente('<bean:write name="clienteImovel" property="cliente.id" />');">
										   		<bean:write name="clienteImovel" property="cliente.id" />
										   	</a>
										</td>
										<td colspan="2">
											<html:hidden name="clienteImovel" property="cliente.id" styleId="${clienteImovel.id}idCliente"/>
											<input type="text" 
												size="54" 
												value="${clienteImovel.cliente.descricao}" 
												id="${clienteImovel.id}nome" 
												maxlength="52"/>
										</td>																									
										
										
										<td width="25%">											
											<logic:equal name="clienteImovel" property="cliente.clienteTipo.indicadorPessoaFisicaJuridica" value="1">											
												<input value="${clienteImovel.cliente.cpf}" 
													id="${clienteImovel.id}cpfCnpj" 
													size="23" 
													onkeypress="return isCampoNumerico(event);" 
													maxlength="11"/>
											</logic:equal>	
																						
										    <logic:equal name="clienteImovel" property="cliente.clienteTipo.indicadorPessoaFisicaJuridica" value="2">
												<input value="${clienteImovel.cliente.cnpj}" 
													id="${clienteImovel.id}cpfCnpj" 
													size="23" 
													onkeypress="return isCampoNumerico(event);" 
													maxlength="14"/>
										    </logic:equal>											
										</td>
										
									</tr>
									
									<tr>
										<td colspan="4">

										<table width="100%" cellpadding="0" cellspacing="1">
											
											<tr>
												<td align="center" width="80%">
													<strong>Telefone(s) do Cliente</strong>
												</td>
												
												<td align="right" width="20%">
												    <input type="button"
														name="cancelar" 
														class="bottonRightCol" 
														value="Adicionar"
														onclick="javascript:abrirPopupAddTelefone(
															<bean:write name="ExibirAtualizarDadosClientesPopupActionForm" property="matricula"/>,
															${clienteImovel.cliente.id},
															${clienteImovel.id}
															);">
												</td>
											</tr>

											<tr>
												<td colspan="2">
												
													<table width="100%" bgcolor="#99CCFF" cellpadding="1"
														cellspacing="1">
														<tr bgcolor="#99CCFF">

															<td align="center" width="10%">
																<strong>Remover</strong>
															</td>
															
															<td width="10%">
																<div align="center"><strong>Principal</strong></div>
															</td>
															
															<td width="20%">
																<div align="center"><strong>Telefone</strong></div>
															</td>
															
															<td width="15%">
																<div align="center"><strong>Tipo</strong></div>
															</td>
															
															<td width="45%">
																<div align="center"><strong>Contato</strong></div>
															</td>

														</tr>
														<logic:present name="clienteImovel" property="cliente.clienteFones">
														<logic:notEmpty name="clienteImovel" property="cliente.clienteFones">
														<logic:notEmpty name="clienteImovel" property="cliente.clienteFones">
														<% int cont2 = 0;%>													
														<logic:iterate name="clienteImovel" 
															id="clienteFone"
															type="ClienteFone"
															property="cliente.clienteFones">
															<tr>

															<% cont2 = cont2 + 1;
															if (cont2 % 2 == 0) { %>
																<tr bgcolor="#cbe5fe">
															<% } else { %>
																<tr bgcolor="#FFFFFF">
															<% } %>
							
															<td align="center">
																<img
																src="<bean:message key='caminho.imagens'/>Error.gif" 
																style="cursor: hand;"
																onclick="removerClienteFone('${clienteImovel.id}','<%= cont2%>'); "
																title="Remover" >
															</td>
																	
															<td>
																<div align="center">
																	<logic:equal name="clienteFone" 
																		property="indicadorTelefonePadrao" 
																		value="1">

																		<input type="radio" 
																			name="${clienteImovel.id}" 
																			value="${clienteFone.id}" 
																			checked="checked" onchange="telefonePadrao('${clienteImovel.id}','${clienteFone.id}');"/>
																			
																	</logic:equal>
																		
																	<logic:equal name="clienteFone" 
																		property="indicadorTelefonePadrao" 
																		value="2">

																		<input type="radio" 
																			name="${clienteImovel.id}" 
																			value="${clienteFone.id}" onchange="telefonePadrao('${clienteImovel.id}','${clienteFone.id}');" />
																	</logic:equal>		
																</div>
															</td>

															<td>
																<div align="center">
																	<logic:present name="clienteFone" property="dddTelefone">
																		<bean:write name="clienteFone" property="dddTelefone" /> 
																	</logic:present> 
																</div>
															</td>
															
															<td>
																<div align="left">
																	<logic:present name="clienteFone" property="foneTipo.descricao">
																		<bean:write name="clienteFone" property="foneTipo.descricao" />
																	</logic:present> 
																</div>
															</td>
															<td width="40%">
																<div align="left">
																	<logic:present name="clienteFone" property="contato">
																		<bean:write name="clienteFone" property="contato" />
																	</logic:present> 
				
																	<logic:notPresent name="clienteFone" property="contato">&nbsp;</logic:notPresent>
																</div>
															</td>		
														</tr>
														</logic:iterate>
														</logic:notEmpty>
														</logic:notEmpty>
														</logic:present>
													</table>
												</td>
											</tr>

										</table>

										</td>
									</tr>
								</table>
								
								<table width="100%" cellpadding="0" cellspacing="2" >
									<tr>
										<td>							   
										</td>
									</tr>
								</table>
									
							</logic:iterate>

							</td>
			 		   </tr>

						<tr>
							<td>
							<table width="100%" cellpadding="0" cellspacing="1">
								<tr>
									<td><input type="button" name="cancelar"
										class="bottonRightCol" value="Cancelar"
										onclick="javascript:window.close()">
									</td>
									<td align="right">
										<%-- <input type="button" name="manterCliente"
											class="bottonRightCol" value="Manter Cliente"
											onclick="javascript:acaoManterCliente(); "> --%>
									</td>
									<td>
									    <%-- <input type="button" name="manterImovel"
										class="bottonRightCol" value="Manter Imóvel"
										onclick="javascript:acaoManterImovel()"> --%>
									</td>
									<td align="right">
										<input type="button" 
											name="atualizar"
											class="bottonRightCol" 
											value="Atualizar Dados"
											onclick="javascript:validarForm();">
									<td>
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
	</table>
</html:form>
</body>
</html:html>

