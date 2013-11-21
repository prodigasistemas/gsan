<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="EstilosCompesa.css" type="text/css">
<link rel="stylesheet" href="jtree.css" type="text/css">

<script language="JavaScript" type="text/JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="770" height="52"  valign="top" class="topstrip">
      <table width="100%" height="52" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="52" colspan="2" valign="top" class="topstrip"><html:img src="imagens/logocompesa.gif"/></td>

          <td valign="top" align="right"><html:img width="192" height="51" src="imagens/logocht.gif"/></td>
        </tr>
      </table>
   </td>
  </tr>
  <tr>
  <td border=0>
  <table border="0" class="layerMenu">
  <tr>
    <td>
	<link rel="StyleSheet" href="dtree.css" type="text/css" /><script type="text/javascript" src="dtree.js"></script>

<div class="dtree">
<script><!--
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
//--></script>
</div>
    </td>
  </tr>
</table>

 <table width="770" border="0" cellpadding="0" cellspacing="0">
        <tr>
              <td width="135" height="381" valign="top" class="leftcoltext">

      <div align="center">
  <p align="left">&nbsp;</p>
  <p align="left">&nbsp;</p>
  <p align="left">&nbsp;</p>

<table border="0" class="tableinlayerusuario">
  <tr>
    <td><strong>Data Atual:</strong><br>
    15/04/2005
    </td>

  </tr>
  <tr>
    <td><strong>Usu&aacute;rio:</strong><br>
     jeronimo
    </td>
  </tr>
  <tr>
    <td><strong>Grupo:</strong><br>



	      Administradores<br>

	      Gerentes<br>


  </td>
  </tr>
   <tr>
    <td><strong>Nº Acesso:</strong><br>
      1334
  </td>

  </tr>
  <tr>
    <td><strong>Data Ult. Acesso:</strong><br>
      15/04/2005
  </td>
  </tr>
  <tr>
    <td><strong><html:link href="jsp/util/logoff.jsp">Sair</html:link></strong><br>

  </td>
  </tr>

</table>

</div>    </td>
          <td width="5" valign="top" > <div align="center">
            </div></td>
          <td width="650" valign="top" class="centercoltext">
            <!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><html:img src="imagens/parahead_left.gif" border="0"/></td>
                <td class="parabg">Filtrar Dados da Tarifa Social</td>
                <td width="11" valign="top"><html:img src="imagens/parahead_right.gif" border="0"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
<p>&nbsp;</p>
            <table width="100%" border="0" >
              <html:form action="">
                <tr>
                  <td colspan="2">Para filtrar dados da tarifa social, informe
                    os par&acirc;metros abaixo:</td>
                </tr>
                <tr>
                  <td height="23" colspan="2"> <hr></td>
                </tr>
                <tr>
                  <td width="51%" height="18" class="style1"><strong>Per&iacute;odo
                    de Implanta&ccedil;&atilde;o: </strong></td>
                  <td width="49%" height="18" class="style1"><html:text maxlength="10" property="periodoImplantacaoInicial" size="10"/>
                    a <html:text maxlength="10" property="periodoImplantacaoFinal" size="10"/>
                    <html:img width="16" height="15" src="imagens/calendario.gif"/>
                    (dd/mm/aaaa) </td>
                </tr>
                <tr>
                  <td height="18" class="style1"><strong>Per&iacute;odo de Exclus&atilde;o:
                    </strong></td>
                  <td height="18" class="style1"><html:text maxlength="10" property="periodoExclusaoInicial" size="10"/>
                    a
                    <html:text maxlength="10" property="periodoExclusaoFinal" size="10"/>
                    <html:img width="16" height="15" src="imagens/calendario.gif"/>
                    (dd/mm/aaaa) </td>
                </tr>
                <tr>
                  <td><strong>Motivo de Exclus&atilde;o:</strong></td>
                  <td><html:select property="motivoExclusao">
                      <html:option value="">Motivo 1</html:option>
                      <html:option value="">Motivo 2 </html:option>
                      <html:option value="">Motivo 3</html:option>
                    </html:select></td>
                </tr>
                <tr>
                  <td height="18" class="style1"><strong>Per&iacute;odo de Validade
                    do Cart&atilde;o do Programa Social: </strong></td>
                  <td height="18" class="style1"><html:text maxlength="10" property="periodoValidadeInicial" size="10"/>
                    a
                    <html:text maxlength="10" property="periodoValidadeFinal" size="10"/>
                    <html:img width="16" height="15" src="imagens/calendario.gif"/>
                    (dd/mm/aaaa) </td>
                </tr>
                <tr>
                  <td><strong>N&uacute;mero de Meses de Ades&atilde;o:</strong></td>
                  <td><html:text maxlength="2" property="numeroMesesAdesao" size="2"/>
                  </td>
                </tr>
                <tr>
                  <td><strong>Tipo do Cart&atilde;o:</strong></td>
                  <td><html:select property="tipoCartao">
                      <html:option value="">Tipo Cart&atilde;o 1 </html:option>
                      <html:option value="">Tipo Cart&atilde;o 2 </html:option>
                      <html:option value="">Tipo Cart&atilde;o 3</html:option>
                    </html:select> </td>
                </tr>
                <tr>
                  <td><strong>Tipo de Renda:</strong></td>
                  <td><html:select property="tipoRenda">
                      <html:option value="">Tipo Renda 1 </html:option>
                      <html:option value="">Tipo Renda 2</html:option>
                      <html:option value="">Tipo Renda 3</html:option>
                    </html:select> </td>
                </tr>
                <tr>
                  <td><strong>N&uacute;mero de Meses de Ades&atilde;o:</strong></td>
                  <td><html:text maxlength="2" property="numeroMesesAdesao" size="2"/>
                  </td>
                </tr>
                <tr>
                  <td><strong>Renda Familiar:</strong></td>
                  <td><html:text maxlength="11" property="rendaFamiliar" size="11"/>
                  </td>
                </tr>
                <tr>
                  <td><strong>Consumo Companhia de Energia:</strong></td>
                  <td><html:text maxlength="5" property="consumoCelpe" size="5"/>
                  </td>
                </tr>
                <tr>
                  <td height="18" class="style1"><strong>Per&iacute;odo de Recadastramento:
                    </strong></td>
                  <td height="18" class="style1"><html:text maxlength="10" property="periodoRecadastramentoInicial" size="10"/>
                    a
                    <html:text maxlength="10" property="periodoRecadastramentoFinal" size="10"/>
                    <html:img width="16" height="15" src="imagens/calendario.gif"/>
                    (dd/mm/aaaa) </td>
                </tr>
                <tr>
                  <td height="18" class="style1"><strong>N&uacute;mero de Recadastramentos:
                    </strong></td>
                  <td height="18" class="style1"><html:text maxlength="3" property="numeroRecadastramentos" size="3"/>
                  </td>
                </tr>
                <tr>
                  <td><p>&nbsp;</p></td>
                  <td> <table>
                      <tr>
                        <td width="500" align="right"> <html:submit styleClass="bottonRightCol" value="Filtrar" property="adicionar"/></td>
                        <td>&nbsp;</td>
                      </tr>
                    </table></td>
                </tr>
              </html:form>
            </table>
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">




<table width="770" cellspacing="0" cellpadding="0">
  <tr  >
     <td  height="4" colspan="3"></td>
  </tr>

  <tr>
    <td> <table width="100%" cellpadding="0"  cellspacing="0" class="footer">
                    <tr>
                      <td height="20"  align="left">&nbsp;Compesa</td>
                      <td align="right">Powered by GPD/DDM&nbsp; </td>
        </tr>
      </table></td>
  </tr>
</table>



		</td>
		</tr>
	  </table>



  </td>
  </tr>

</table>


</body>
</html:html>
