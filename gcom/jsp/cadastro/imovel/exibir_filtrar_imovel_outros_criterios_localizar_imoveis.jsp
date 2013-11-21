<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="EstilosCompesa.css" type="text/css">

</head>

<body leftmargin="5" topmargin="5">
<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="770" height="52" valign="top" class="topstrip">
		<table width="100%" height="52" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="52" colspan="2" valign="top" class="topstrip"><img
					src="imagens/logocompesa.gif"></td>

				<td valign="top" align="right"><img src="imagens/logocht.gif"
					width="192" height="51"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td border=0>
		<table border="0" class="layerMenu">
			<tr>
				<td>
3
				<script type="text/javascript" src="dtree.js"></script>

				<div class="dtree"><script><!--
/* d = new dTree('d');
d.add(0,-1,'Menu GSAN');
d.add(1,0,'GSAN','#');
d.add(2,1,'Acesso','#');
d.add(3,2,'Controlar Funcionalidade Operação','exibirControlarGrupoUsuarioAction.do');
d.add(4,2,'Inserir Grupo','exibirInserirGrupoAction.do');
d.add(5,2,'Manter Grupo ','exibirManterGrupoAction.do');
d.add(6,1,'Amostra','#');
d.add(7,6,'Inserir Característica da Amostra','exibirInserirCaracteristicaAction.do');
d.add(8,6,'Manter Caracteristica','exibirManterCaracteristicaAction.do');
d.add(9,1,'Analise','#');
d.add(10,9,'Imprimir Relatórios de Analise','exibirFiltroRelatorioAnaliseHidrobiologicaAguaLaboratorioAction.do');
d.add(11,1,'Análise','#');
d.add(12,11,'Definir Análise','exibirDefinirAnaliseAction.do');
d.add(13,11,'Inserir Ítem Físico Químico','exibirInserirItemFisicoQuimicoAction.do');
d.add(14,11,'Manter Item Físico Químico','exibirManterItemFisicoQuimicoAction.do');
d.add(15,1,'Empresa','#');
d.add(16,15,'Inserir Distrito','exibirInserirDistritoAction.do');
d.add(17,15,'Inserir Unidade Operacional','exibirInserirUnidadeOperacionalAction.do');
d.add(18,15,'Manter Empresa','exibirManterEmpresaAction.do');
d.add(19,15,'Manter Unidade Operacional','exibirManterUnidadeOperacionalAction.do');
d.add(20,15,'Remover Unidade Operacional Origem','exibirEscolherUnidadeOperacionalOrigemRemocaoAction.do');
d.add(21,1,'Funcionário','#');
d.add(22,21,'Inserir Equipe','exibirInserirEquipeAction.do');
d.add(23,21,'Inserir Funcionário','exibirInserirFuncionarioAction.do');
d.add(24,21,'Manter Equipe','exibirManterEquipeAction.do');
d.add(25,21,'Manter Funcionário','exibirManterFuncionarioAction.do');
d.add(26,1,'Laboratório','#');
d.add(27,26,'Inserir Aumento para o Microscópio','exibirInserirMicroscopioAumentoAction.do');
d.add(28,26,'Inserir Fator de Correção','exibirInserirFatorCorrecaoAction.do');
d.add(29,26,'Inserir Microscópio','exibirInserirMicroscopioAction.do');
d.add(30,26,'Manter Aumento para o Microscópio','exibirManterMicroscopioAumentoAction.do');
d.add(31,26,'Manter Fator Correção','exibirManterFatorCorrecaoAction.do');
d.add(32,26,'Manter Microscópio','exibirManterMicroscopioAction.do');
d.add(33,1,'Localidade','#');
d.add(34,33,'Inserir Endereço Desvinculado','exibirInserirEnderecoDesvinculadoAction.do');
d.add(35,33,'Manter Endereço Desvinculado ','exibirManterEnderecoDesvinculadoAction.do');
d.add(36,33,'Manter Vínculo Cliente Imóvel ','exibirManterVinculoClienteImovelAction.do');
d.add(37,33,'Vincular Cliente Imóvel','exibirInserirVincularClienteImovelAction.do');
d.add(38,1,'Serviço','#');
d.add(39,38,'Abrir Serviço','exibirAbrirServicoAction.do');
d.add(40,38,'Inserir Atividade por Serviço','exibirInserirServicoAtividadeAction.do');
d.add(41,38,'Inserir Especificação de Tipo de Solicitação','exibirInserirEspecificacaoTipoSolicitacaoAction.do');
d.add(42,38,'Inserir Situação Registro Atendimento','exibirInserirSituacaoRegistroAtendimentoAction.do');
d.add(43,38,'Inserir Unidade Executora','exibirInserirUnidadeExecutoraAction.do');
d.add(44,38,'Manter Atividades por Serviço','exibirManterServicoAtividadeAction.do');
d.add(45,38,'Manter Especificação de Tipo de Solicitação','exibirManterEspecificacaoTipoSolicitacaoAction.do');
d.add(46,38,'Manter Serviço','exibirManterServicoAction.do');
d.add(47,38,'Manter Situação Registro Atendimento','exibirManterSituacaoRegistroAtendimentoAction.do');
d.add(48,38,'Manter Unidade Executora','exibirManterUnidadeExecutoraAction.do');
d.add(49,38,'Registrar Funcionário em Atividades por Serviço','exibirRegistrarServicoAtividadeFuncionarioAction.do');
d.draw(); */

d = new dTree('d');
d.add(0,-1,'Menu GSAN');
d.add(1,0,'GSAN','#');
d.add(2,1,'Cadastro','#');
d.add(3,2,'Inserir Cliente','processoinserircliente1.htm');
d.add(4,2,'Inserir Imóvel','processoinseririmovel1.htm');
d.add(5,1,'Micromedição','#');
d.add(6,5,'Em Desenvolvimento','#');
d.add(7,1,'Arrecadação','#');
d.add(8,7,'Em Desenvolvimento','#');
d.add(9,1,'Cobrança','#');
d.add(10,9,'Em Desenvolvimento','#');
d.add(11,1,'Segurança','#');
d.add(12,11,'Em Desenvolvimento','#');
d.add(13,1,'Gerencial','#');
d.add(14,13,'Em Desenvolvimento','#');
d.add(14,1,'Atendimento Público','#');
d.add(15,14,'Em Desenvolvimento','#');
d.add(16,1,'Operacional','#');
d.add(17,16,'Em Desenvolvimento','#');
d.draw();
//--></script></div>
				</td>
			</tr>
		</table>

		<table width="770" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="147" height="381" valign="top" class="leftcoltext">

				<div align="center">
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>

				<table border="0" class="tableinlayerusuario">
					<tr>
						<td><strong>Data Atual:</strong><br>
						15/04/2005</td>
					</tr>
					<tr>
						<td><strong>Usu&aacute;rio:</strong><br>
						jeronimo</td>
					</tr>
					<tr>
						<td><strong>Grupo:</strong><br>
						Administradores<br>
						Gerentes<br>
						</td>
					</tr>
					<tr>
						<td><strong>Nº Acesso:</strong><br>
						1334</td>
					</tr>
					<tr>
						<td><strong>Data Ult. Acesso:</strong><br>
						15/04/2005</td>
					</tr>
					<tr>
						<td><strong><a href="jsp/util/logoff.jsp">Sair</a></strong><br>
						</td>
					</tr>
				</table>
				</div>
				</td>
				<td width="4" valign="top">
				<div align="center"></div>
				</td>
				<td width="619" valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
				<table>
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Filtrar Im&oacute;vel</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
				<!--Fim Tabela Reference a Páginação da Tela de Processo-->
				<p>&nbsp;</p>


				<table width="100%" border="0">
					<tr>
						<td colspan="4">Para filtrar o(s) im&oacute;vel(is) pela
						localiza&ccedil;&atilde;o, informe os dados abaixo:</td>
					</tr>
					<tr>
						<td width="123"><strong>Ger&ecirc;ncia Regional:</strong></td>
						<td colspan="3" align="right">
						<div align="left"><a href="usuario_pesquisar.htm"> <html:select
							property="nomeAbreviadoGerenciaRegional">
							
							<html:options collection="colecaoGerenciasRegionais"
								labelProperty="nomeAbreviado" property="id" />
						</html:select> 
						
						</a></div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td colspan="3"><strong> Inscri&ccedil;&atilde;o Inicial</strong></td>
						<td width="191"><strong>Inscri&ccedil;&atilde;o Final</strong></td>
					</tr>
					<tr>
						<td><strong>Localidade:<font color="#FF0000"> </font></strong></td>
						<td width="176" align="right">
						<div align="left"><input name="textfield2252" type="text" size="3"
							maxlength="3"> <a href="usuario_pesquisar.htm"><img
							src="imagens/pesquisa.gif" width="23" height="21" border="0"></a></div>
						<div align="left"><strong></strong></div>
						<div align="left"></div>
						</td>
						<td width="99"><strong>Localidade:<font color="#FF0000"> </font></strong></td>
						<td align="right">
						<div align="left"><input name="textfield2253" type="text" size="3"
							maxlength="3"> <a href="usuario_pesquisar.htm"><img
							src="imagens/pesquisa.gif" width="23" height="21" border="0"></a></div>
						<div align="left"><strong></strong></div>
						<div align="left"></div>
						</td>
					</tr>
					<tr>
						<td><strong>Setor Comercial: </strong></td>
						<td align="right">
						<div align="left"><input name="textfield2232" type="text" size="3"
							maxlength="3"> <a href="usuario_pesquisar.htm"> <img
							src="imagens/pesquisa.gif" width="23" height="21" border="0"></a>
						</div>
						</td>
						<td><strong>Setor Comercial: </strong></td>
						<td align="right">
						<div align="left"><input name="textfield2232" type="text" size="3"
							maxlength="3"> <a href="usuario_pesquisar.htm"> <img
							src="imagens/pesquisa.gif" width="23" height="21" border="0"></a>
						</div>
						</td>
					</tr>
					<tr>
						<td><strong>Quadra:<font color="#FF0000"> </font></strong></td>
						<td align="right">
						<div align="left"><input name="textfield222" type="text" size="3"
							maxlength="4"> <a href="usuario_pesquisar.htm"><img
							src="imagens/pesquisa.gif" width="23" height="21" border="0"></a></div>
						<div align="left"><strong></strong></div>
						<div align="left"></div>
						</td>
						<td><strong>Quadra:<font color="#FF0000"> </font></strong></td>
						<td align="right">
						<div align="left"><input name="textfield222" type="text" size="3"
							maxlength="4"> <a href="usuario_pesquisar.htm"><img
							src="imagens/pesquisa.gif" width="23" height="21" border="0"></a></div>
						<div align="left"><strong></strong></div>
						<div align="left"></div>
						</td>
					</tr>
					<tr>
						<td><strong>Lote:<font color="#FF0000"> </font></strong></td>
						<td align="right">
						<div align="left"><input name="textfield222" type="text" size="4"
							maxlength="4"></div>
						<div align="left"><strong></strong></div>
						<div align="left"></div>
						</td>
						<td><strong>Lote:<font color="#FF0000"> </font></strong></td>
						<td align="right">
						<div align="left"><input name="textfield222" type="text" size="4"
							maxlength="4"></div>
						<div align="left"><strong></strong></div>
						<div align="left"></div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td><strong>Munic&iacute;pio:</strong></td>
						<td colspan="2" align="right">
						<div align="left"><a href="usuario_pesquisar.htm"> </a><strong> <input
							name="textfield222233" type="text" size="4" maxlength="4"> <a
							href="usuario_pesquisar.htm"><img src="imagens/pesquisa.gif"
							width="23" height="21" border="0"></a> </strong></div>
						</td>
						<td align="right">&nbsp;</td>
					</tr>
					<tr>
						<td><strong>Bairro:</strong></td>
						<td colspan="2" align="right">
						<div align="left"><a href="usuario_pesquisar.htm"> </a><strong> <input
							name="textfield222233" type="text" size="4" maxlength="4"> <a
							href="usuario_pesquisar.htm"><img src="imagens/pesquisa.gif"
							width="23" height="21" border="0"></a> </strong></div>
						</td>
						<td align="right">&nbsp;</td>
					</tr>
					<tr>
						<td><strong>CEP:</strong></td>
						<td colspan="2" align="right">
						<div align="left"><a href="usuario_pesquisar.htm"> </a><strong> <input
							name="textfield2222322" type="text" size="9" maxlength="9"> </strong></div>
						</td>
						<td align="right">&nbsp;</td>
					</tr>
					<tr>
						<td><strong>Logradouro:</strong></td>
						<td colspan="2" align="right">
						<div align="left"><a href="usuario_pesquisar.htm"> </a><strong> <input
							name="textfield222232" type="text" size="9" maxlength="9"> <a
							href="usuario_pesquisar.htm"><img src="imagens/pesquisa.gif"
							width="23" height="21" border="0"></a> </strong></div>
						</td>
						<td align="right">&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="3" align="right">&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="3" align="right"><input type="submit" name="Submit22"
							class="bottonRightCol" value="Filtrar"></td>
					</tr>
				</table>



				<p>&nbsp;</p>
			</tr>
			<tr>
				<td colspan="3">




				<table width="770" cellspacing="0" cellpadding="0">
					<tr>
						<td height="4" colspan="3"></td>
					</tr>

					<tr>
						<td>
						<table width="100%" cellpadding="0" cellspacing="0" class="footer">
							<tr>
								<td height="20" align="left">&nbsp;Compesa</td>
								<td align="right">Powered by GPD/DDM&nbsp;</td>
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


</body>
</html>
