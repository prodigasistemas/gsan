<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
    href="<bean:message key="caminho.css"/>EstilosCompesa.css"
    type="text/css">

<%-- Carrega validações do validator --%>
<script language="JavaScript"
    src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
    formName="InserirEquipeActionForm" />
<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript"
    src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
    src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
    src="<bean:message key="caminho.js"/>Calendario.js"></script>
<%-- Novos Javascripts --%>
<script language="JavaScript">
    /* Valida Form */
    function validaForm() {
        var form = document.InserirEquipeActionForm;
        /* Validate */
        if (validateInserirEquipeActionForm(form)) {
            //if (validaQtdeComponentes('inserir')) {
                if (validaQtdeEquipeComponentes()) {

                    var cargaTrabalho = trim(form.cargaTrabalhoDia.value);

                    if (cargaTrabalho != null && cargaTrabalho != ''
                            && cargaTrabalho > 24) {
                        alert('Carga de Trabalho Diária(hora) não deve exceder 24');
                    } else {
                        submeterFormPadrao(form);
                    }

                }
            //}
        }
    }
    /* Valida se informou algum componente antes da inserção da equipe */
    function validaQtdeEquipeComponentes() {
        var form = document.InserirEquipeActionForm;
        if (form.tamanhoColecao.value == '0') {
            alert('Equipe deve possuir ao menos um componente.');
            return false;
        }
        return true;
    }
    /* Limpa Form */
    function limparForm() {
        var form = document.InserirEquipeActionForm;
        form.nomeEquipe.value = "";
        form.placaVeiculo.value = "";
        form.cargaTrabalhoDia.value = "";
        form.nomeUsuarioRespExecServico.value = "";
    	form.cdUsuarioRespExecServico.value = "";
        limparUnidadeOrganizacional();
        limparTipoPerfilServico();
        form.action = 'exibirInserirEquipeAction.do';
        form.submit();
    }
    function limparUsuario(){
    	var form = document.forms[0];
    	form.nomeUsuarioRespExecServico.value = "";
    	form.cdUsuarioRespExecServico.value = "";
    }
    /* [FS0006] Verificar quantidade de componentes da equipe em Tipo Perfil Serviço */
    function addComponente() {
        //if (validaQtdeComponentes('add')) {
            chamarPopup('exibirInserirEquipeAction.do?method=add',
                    'equipeComponente', null, null, 270, 590, '',
                    document.forms[0].equipeComponenteId);
        //}
    }

    /* Valida as informações do tamanho coleção com a qtde permitida 
    function validaQtdeComponentes(fluxo) {
        var form = document.InserirEquipeActionForm;

        //if (form.qtdeComponentesEquipe.value == '-1') {
        //    alert('Informe um Tipo de Perfil Válido.');
        //    return false;
        //} else 
        if (form.qtdeComponentesEquipe.value == '') {
            // Qtde ilimitada
        } else {
            if (form.tamanhoColecao.value >= form.qtdeComponentesEquipe.value) {
                if (form.tamanhoColecao.value == form.qtdeComponentesEquipe.value
                        && fluxo == 'inserir') {
                    // Vai inserir e está no limite
                } else {
                    alert('Quantidade de Componentes da Equipe incompatível ou no limite do permitido('
                            + form.qtdeComponentesEquipe.value
                            + ') em Tipo Perfil Serviço.');
                    return false;
                }
            }
        }
        return true;
    }
    */
    /* [FS0006] Verificar quantidade de componentes da equipe em Tipo Perfil Serviço */
    function addEquipamentosEspeciais() {
        abrirPopup('exibirInserirEquipeAction.do?method=addEquipamentos', 270,
                590);
    }

    /* Limpa Unidade Organizacional do Form */
    function limparUnidadeOrganizacional() {
        var form = document.InserirEquipeActionForm;
        form.unidadeOrganizacionalId.value = "";
        form.unidadeOrganizacionalDescricao.value = "";
    }
    /* Limpa Tipo Perfil Servico do Form */
    function limparTipoPerfilServico() {
        var form = document.InserirEquipeActionForm;
        form.tipoPerfilServicoId.value = "";
        form.tipoPerfilServicoDescricao.value = "";
    }
    /* Chama Popup */
    function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,
            objetoRelacionado) {
        if (objetoRelacionado.disabled != true) {
            if (objeto == null || codigoObjeto == null) {
                abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
            } else {
                if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
                    alert(msg);
                } else {
                    abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "="
                            + codigoObjeto + "&caminhoRetornoTelaPesquisa="
                            + tipo, altura, largura);
                }
            }
        }
    }
    /* Recupera Dados Popup */
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
            tipoConsulta) {
        var form = document.forms[0];

        form.action = 'exibirInserirEquipeAction.do';
        if (tipoConsulta == 'unidadeOrganizacional') {
            form.unidadeOrganizacionalId.value = codigoRegistro;
            form.unidadeOrganizacionalDescricao.value = descricaoRegistro;
            form.submit();
        } else if (tipoConsulta == 'servicoPerfilTipo') {
            form.tipoPerfilServicoId.value = codigoRegistro;
            form.tipoPerfilServicoDescricao.value = descricaoRegistro;
            form.submit();
        } else if (tipoConsulta == 'equipeComponente') {
            form.equipeComponenteId.value = codigoRegistro;
            form.funcionarioId.value = descricaoRegistro[0];
            form.equipeComponenteNome.value = descricaoRegistro[1];
            form.responsavelId.value = descricaoRegistro[2];
            form.action = 'exibirInserirEquipeAction.do';
            form.submit();
        }else if (tipoConsulta == 'equipeEquipamentosEspeciais') {
            //form.equipamentosEspeciasId.value = codigoRegistro;
            form.equipamentosEspeciasId.value = descricaoRegistro[0];
            form.descricao.value = descricaoRegistro[1];
            form.quantidade.value = descricaoRegistro[2];
            form.action = 'exibirInserirEquipeAction.do';
            form.submit();
        }else if(tipoConsulta == "usuario"){
        	form.cdUsuarioRespExecServico.value = codigoRegistro;
        	form.nomeUsuarioRespExecServico.value = descricaoRegistro;
        	form.action = 'exibirInserirEquipeAction.do?tipoPesquisa=usuario';
        	form.submit();
        }        
    }

    /* Remove Componente da grid */
    function remover(id, tipoComponente) {
        var form = document.InserirEquipeActionForm;
       
        if(tipoComponente == 'componente'){
            var where_to = confirm("Deseja realmente remover este componente ?");
            if (where_to == true) {
                form.action = 'exibirInserirEquipeAction.do?deleteComponente=' + id;
                form.submit();
            }
        }else{
            var where_to = confirm("Deseja realmente remover este equipamento ?");
            if (where_to == true) {
                form.action = 'exibirInserirEquipeAction.do?deleteEquipamento=' + id;
                form.submit();
            }
        }
    }
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/inserirEquipeAction.do"
    name="InserirEquipeActionForm"
    type="gcom.gui.atendimentopublico.ordemservico.InserirEquipeActionForm"
    method="post">

    <html:hidden property="equipeComponenteId" />
    <html:hidden property="equipeComponenteNome" />
    <html:hidden property="funcionarioId" />
    <html:hidden property="funcionarioName" />
    <html:hidden property="responsavelId" />
    <html:hidden property="qtdeComponentesEquipe" />
    <html:hidden property="tamanhoColecao" />


    <html:hidden property="equipamentosEspeciasId" />
    <html:hidden property="descricao" />
    <html:hidden property="quantidade" />

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
                    <td class="parabg">Inserir Equipe</td>
                    <td width="11"><img border="0"
                        src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
                    </td>
                </tr>
            </table>
            <p>&nbsp;</p>
            <!--Inicio da Tabela Ligação de Esgoto -->
            <table width="100%" border="0">
                <tr>
                    <td height="31">
                    <table width="100%" border="0" align="center">
                        <tr>
                            <td height="10" colspan="5">Para adicionar a equipe, informe
                            os dados abaixo:</td>
                        </tr>
                        <tr>
                            <td height="10" colspan="5">
                            <hr>
                            </td>
                        </tr>
                        <tr>
                            <td height="10" colspan="1"><strong> Nome da
                            Equipe: <span class="style2"> <strong> <font
                                color="#FF0000">*</font> </strong> </span> </strong></td>
                            <td colspan="3"><!-- Nome da Equipe --> <span class="style2">
                            <html:text property="nomeEquipe" size="42" maxlength="20" /> </span></td>
                        </tr>
                        <tr>
                            <td colspan="1"><strong> Placa do Ve&iacute;culo :
                            </strong></td>
                            <td colspan="4" align="right">
                            <div align="left"><html:text property="placaVeiculo"
                                size="15" maxlength="7" /></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1"><strong> Carga de Trabalho Dia
                            (hora): <font color="#FF0000">*</font> </strong></td>
                            <td colspan="3" align="right">
                            <div align="left"><html:text property="cargaTrabalhoDia"
                                size="2" maxlength="2" /></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1"><strong> Codigo DDD do Município: </strong></td>
                            <td colspan="4" align="right">
                            <div align="left"><html:text property="codigoDdd" size="2"
                                maxlength="2" /></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1"><strong> Número do Telefone: <font
                                color="#FF0000">*</font> </strong></td>
                            <td colspan="4" align="right">
                            <div align="left"><html:text property="numeroTelefone"
                                size="8" maxlength="8" /></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1"><strong> Número do IMEI do
                            aparelho: <font color="#FF0000">*</font> </strong></td>
                            <td colspan="4" align="right">
                            <div align="left"><html:text property="numeroImei"
                                size="15" maxlength="15" /></div>
                            </td>
                        </tr>
                        <tr>
                            <td width="192" colspan="1"><strong> Unidade
                            Organizacional: <font color="#FF0000">*</font> </strong></td>
                            <td colspan="4" align="right">
                            <div align="left"></div>
                            <div align="left"><strong></strong></div>
                            <div align="left"><html:text
                                property="unidadeOrganizacionalId" size="4" maxlength="4"
                                onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirEquipeAction.do?validaUnidadeOrganizacional=true', 'unidadeOrganizacionalId','Unidade Organizacional');" />
                            <img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
                                width="23" height="21" style="cursor: hand;" name="imagem"
                                onclick="chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?menu=sim', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeOrganizacionalId);"
                                alt="Pesquisar"> <logic:present
                                name="unidadeOrganizacionalIdEncontrada" scope="session">
                                <html:text property="unidadeOrganizacionalDescricao"
                                    readonly="true"
                                    style="background-color:#EFEFEF; border:0; color: #000000"
                                    size="42" maxlength="45" />
                            </logic:present> <logic:notPresent name="unidadeOrganizacionalIdEncontrada"
                                scope="session">
                                <html:text property="unidadeOrganizacionalDescricao"
                                    readonly="true"
                                    style="background-color:#EFEFEF; border:0; color: #ff0000"
                                    size="42" maxlength="45" />
                            </logic:notPresent> <a href="javascript:limparUnidadeOrganizacional();"> <img
                                border="0" title="Apagar"
                                src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1"><strong> Tipo Perfil Servi&ccedil;o
                            : </strong></td>
                            <td colspan="4" align="right">
                            <div align="left"></div>
                            <div align="left"><strong></strong></div>
                            <div align="left"><html:text property="tipoPerfilServicoId"
                                size="3" maxlength="3"
                                onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirEquipeAction.do?validaTipoPerfilServico=true', 'tipoPerfilServicoId','Tipo Perfil Serviço');" />
                            <img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
                                width="23" height="21" style="cursor: hand;" name="imagem"
                                onclick="chamarPopup('exibirPesquisarTipoPerfilServicoAction.do', 'servicoPerfilTipo', null, null, 275, 480, '',document.forms[0].tipoPerfilServicoId);"
                                alt="Pesquisar"> <logic:present
                                name="tipoPerfilServicoIdEncontrada" scope="session">
                                <html:text property="tipoPerfilServicoDescricao" readonly="true"
                                    style="background-color:#EFEFEF; border:0; color: #000000"
                                    size="45" maxlength="45" />
                            </logic:present> <logic:notPresent name="tipoPerfilServicoIdEncontrada"
                                scope="session">
                                <html:text property="tipoPerfilServicoDescricao" readonly="true"
                                    style="background-color:#EFEFEF; border:0; color: #ff0000"
                                    size="45" maxlength="45" />
                            </logic:notPresent> <a href="javascript:limparTipoPerfilServico();"> <img
                                border="0" title="Apagar"
                                src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a></div>
                            </td>
                        </tr>
                        
                        <!-- Começo usuário responsável pela execussão do serviço -->
                        
                        <tr>
							<td><strong>Usuario Responsavel Pela Execussão do Serviço:</strong></td>
					
							<td colspan="4">						
								<html:text maxlength="11" 
								tabindex="1"
								property="cdUsuarioRespExecServico" 
								name="InserirEquipeActionForm"
								size="9"
								style="text-transform: none;"
								onkeypress="validaEnterStringSemUpperCase(event, 'exibirInserirEquipeAction.do?tipoPesquisa=usuario','cdUsuarioRespExecServico','cdUsuarioRespExecServico');
							            return isCampoNumerico(event);"/>
								<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?mostrarLogin=s', 'usuario', null, null, 275, 480, '',document.forms[0].cdUsuarioRespExecServico);" id = "pesUsuario">
								<img width="23" 
									height="21" 
									border="0" 
									style="cursor:hand;"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar usuário"/>
								</a>
								

								<logic:present name="usuarioRespExecServicoEncontrado" scope="session">
									<html:text property="nomeUsuarioRespExecServico" 
										name="InserirEquipeActionForm"
										size="33"
										maxlength="30" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present> 

								<logic:notPresent name="usuarioRespExecServicoEncontrado" scope="session">
								<html:text property="nomeUsuarioRespExecServico" 
									name="InserirEquipeActionForm"
									size="33"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
								</logic:notPresent>
		
								<a href="javascript:limparUsuario();"> 
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
								</a>
							</td>
							
						</tr><!-- fim usuário responsável pela execussão do serviço -->
						<tr>
							<td><strong>Indicador de Programa&ccedil;&atilde;o Autom&aacute;tica:</strong></td>
					
							<td colspan="4">						
								<html:radio property="indicadorProgramacaoAutomatica"
									value="<%=ConstantesSistema.SIM.toString()%>" tabindex="1" />
								<strong>Sim</strong>
								<html:radio property="indicadorProgramacaoAutomatica"
									value="<%=ConstantesSistema.NAO.toString()%>" tabindex="2" />
								<strong>Não</strong>
							</td>
						</tr>
                        <tr>
                            <td height="24" colspan="5">
                            <hr>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"><strong> <font color="#000000">Componentes
                            da Equipe </font> </strong></td>
                            <td align="right" colspan="1">
                            <div align="right"><input type="button" name="Submit24"
                                class="bottonRightCol" value="Adicionar"
                                onclick="javascript:addComponente();"></div>
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" colspan="5">
                            <div align="center"><strong> <font color="#FF0000"></font>
                            </strong>
                            <table width="100%" align="center" bgcolor="#99CCFF">
                                <!--corpo da segunda tabela-->
                                <tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
                                    <td width="11%">
                                    <div align="center"><strong>Remover</strong></div>
                                    </td>
                                    <td width="15%">
                                    <div align="center"><strong>Respons&aacute;vel</strong></div>
                                    </td>
                                    <td width="16%">
                                    <div align="center"><strong>Funcion&aacute;rio</strong></div>
                                    </td>
                                    <td width="58%">
                                    <div align="center"><strong>Nome do Componente</strong></div>
                                    </td>
                                </tr>
                            </table>
                        </tr>
                       
                        <table width="100%" align="center" >
                        <tr>
                            <c:set var="count" value="0" />
                            <logic:iterate id="equipeComponente"
                                name="InserirEquipeActionForm" property="equipeComponentes"
                                type="gcom.atendimentopublico.ordemservico.EquipeComponentes"
                                scope="session">
                                <c:set var="count" value="${count+1}" />
                                <c:choose>
                                    <c:when test="${count%2 == '1'}">
                                        <tr bgcolor="#FFFFFF">
                                    </c:when>
                                    <c:otherwise>
                                        <tr bgcolor="#cbe5fe">
                                    </c:otherwise>
                                </c:choose>
                                <td bordercolor="#90c7fc" width="11%" colspan="1">
                                <div align="center"><img
                                    src="<bean:message key='caminho.imagens'/>Error.gif" width="14"
                                    height="14" style="cursor: hand;" name="imagem"
                                    onclick="remover('${count}','componente');" alt="Remover"></div>
                                </td>
                                <td bordercolor="#90c7fc" width="15%" colspan="1">
                                <div align="center"><c:choose>
                                    <c:when test="${equipeComponente.indicadorResponsavel == '1'}">
                                                                    SIM
                                                                </c:when>
                                    <c:otherwise>
                                                                    N&Atilde;O
                                                                </c:otherwise>
                                </c:choose></div>
                                </td>
                                <td bordercolor="#90c7fc" width="16%" colspan="1">
                                <div align="center"><c:if
                                    test="${equipeComponente.funcionario != null}">
                                    <bean:write name="equipeComponente" property="funcionario.id" />
                                </c:if></div>
                                </td>
                                <td bordercolor="#90c7fc" width="58%" colspan="1">
                                <div align="left"><c:choose>
                                    <c:when test="${equipeComponente.funcionario == null}">
                                        <bean:write name="equipeComponente" property="componentes" />
                                    </c:when>
                                    <c:otherwise>
                                        <bean:write name="equipeComponente"
                                            property="funcionario.nome" />
                                    </c:otherwise>
                                </c:choose></div>
                                </td>

                            </logic:iterate>
                        </tr>
                       </table>
                       <table width="100%" align="center" >
                        <tr>
                            <td colspan="4">
                            <hr>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><strong> <font color="#000000">Inclusão
                            de Equipamentos Especiais</font> </strong></td>
                            <td colspan="1" align="right">
                            <div align="right"><input type="button" name="Submit24"
                                class="bottonRightCol" value="Adicionar"
                                onclick="javascript:addEquipamentosEspeciais();"></div>
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" colspan="4"><!-- aqui está a tabela -->
                            <div align="center"><strong> <font color="#FF0000"></font>
                            </strong>
                            <table width="100%" align="center" bgcolor="#99CCFF">
                                <!--corpo da segunda tabela-->
                                <tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
                                    <td width="11%" colspan="1">
                                    <div align="center"><strong>Remover</strong></div>
                                    </td>
                                    <td width="73%" colspan="2">
                                    <div align="center"><strong>Descri&ccedil;&atilde;o</strong></div>
                                    </td>
                                    <td width="16%" colspan="1">
                                    <div align="center"><strong>Quantidade</strong></div>
                                    </td>
                                </tr>
                            </table>
                            </div>
                            </td>
                        </tr>

                        <tr>
                            <c:set var="count" value="0" />
                            <logic:iterate id="equipeEquipamentosEspeciais"
                                name="InserirEquipeActionForm"
                                property="equipeEquipamentosEspeciais"
                                type="gcom.atendimentopublico.ordemservico.EquipeEquipamentosEspeciais"
                                scope="session">
                                <c:set var="count" value="${count+1}" />
                                <c:choose>
                                    <c:when test="${count%2 == '1'}">
                                        <tr bgcolor="#FFFFFF">
                                    </c:when>
                                    <c:otherwise>
                                        <tr bgcolor="#cbe5fe">
                                    </c:otherwise>
                                </c:choose>
                                <td bordercolor="#90c7fc" width="11%" colspan="1">
                                <div align="center"><img
                                    src="<bean:message key='caminho.imagens'/>Error.gif" width="14"
                                    height="14" style="cursor: hand;" name="imagem"
                                    onclick="remover('${count}', 'equipamento');" alt="Remover"></div>
                                </td>

                                <td bordercolor="#90c7fc" width="73%" colspan="2">
                                <div align="left"><bean:write
                                    name="equipeEquipamentosEspeciais" property="equipamentosEspeciais.descricao" /></div>
                                </td>
                                <td bordercolor="#90c7fc" width="16%" colspan="1">
                                <div align="left"><bean:write
                                    name="equipeEquipamentosEspeciais" property="quantidade" /></div>
                                </td>
                            </logic:iterate>
                        </tr>
                        <tr>
                            <td colspan="4">
                            <hr>
                            </td>
                        </tr>
                        <tr>
                            <td><strong> <font color="#FF0000"></font> </strong></td>
                            <td colspan="3" align="right">
                            <div align="left"><strong> <font color="#FF0000">*</font>
                            </strong> Campos obrigat&oacute;rios</div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><strong> <font color="#FF0000"> <input
                                type="button" name="Submit22" class="bottonRightCol"
                                value="Desfazer"
                                onClick="javascript:window.location.href='/gsan/exibirInserirEquipeAction.do?menu=sim'">
                            <input type="button" name="Submit23" class="bottonRightCol"
                                value="Cancelar"
                                onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
                            </font> </strong></td>
                            <td colspan="2" align="right"><input type="button"
                                name="Submit2" class="bottonRightCol" value="Inserir"
                                onclick="validaForm();"></td>
                        </tr>
                        
                    </table>
                    </table>
                    <p>&nbsp;</p>
                </tr>
            </table>
            </td>
        </tr>
    </table>
    <!-- Fim do Corpo - Leonardo Regis -->
    <!-- Rodapé -->
    <%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>