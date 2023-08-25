package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Despejo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelInscricaoAlterada;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelTipoCobertura;
import gcom.cadastro.imovel.ImovelTipoConstrucao;
import gcom.cadastro.imovel.ImovelTipoHabitacao;
import gcom.cadastro.imovel.ImovelTipoPropriedade;
import gcom.cadastro.imovel.PiscinaVolumeFaixa;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.imovel.ReservatorioVolumeFaixa;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeRetornoHelper;
import gcom.cadastro.imovel.bean.InserirImovelHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Classe responï¿½vel pela conclusï¿½o do cadastro de um imï¿½vel 
 *
 * @author Raphael Rossiter
 * @date 12/05/2009
 */
public class AtualizarImovelAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		
		if (httpServletRequest.getParameter("confirmado") != null && 
			httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			sessao.setAttribute("confirmou", "sim");
		}
		

		// Cria Variaveis
		Imovel imovelAtualizar = (Imovel) sessao.getAttribute("imovelAtualizacao");
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Collection colecaoImovelSubcategoriasRemovidas = (Collection) sessao.getAttribute("colecaoImovelSubcategoriasRemoviadas");
		Collection colecaoImovelRamosAtividadesRemovidos = (Collection) sessao.getAttribute("colecaoImovelRamosAtividadesRemovidos");
		Collection colecaoClientesImoveisRemovidos = (Collection) sessao.getAttribute("colecaoClientesImoveisRemovidos");
		Date dataInicioRelacaoUsuario = (Date) sessao.getAttribute("dataInicioRelacaoUsuario");
		
		String idLocalidade = (String) inserirImovelActionForm.get("idLocalidade");
		String idSetorComercial = (String) inserirImovelActionForm.get("idSetorComercial");
		String idQuadra = (String) inserirImovelActionForm.get("idQuadra");
		String idQuadraFace = (String) inserirImovelActionForm.get("idQuadraFace");
		String lote = (String) inserirImovelActionForm.get("lote");
		String subLote = (String) inserirImovelActionForm.get("subLote");
		String testadaLote = (String) inserirImovelActionForm.get("testadaLote");
		String areaConstruida = (String) inserirImovelActionForm.get("areaConstruida");
		String faixaAreaConstruida = (String) inserirImovelActionForm.get("faixaAreaConstruida");
		String volumeReservatorioSuperior = (String) inserirImovelActionForm.get("reservatorioSuperior");
		String faixaVolumeReservatorioSuperior = (String) inserirImovelActionForm.get("faixaResevatorioSuperior");
		String volumeReservatorioInferior = (String) inserirImovelActionForm.get("reservatorioInferior");
		String faixaVolumeReservaotirio = (String) inserirImovelActionForm.get("faixaReservatorioInferior");
		String piscina = (String) inserirImovelActionForm.get("piscina");
		String jardim = (String) inserirImovelActionForm.get("jardim");
		String faixaPiscina = (String) inserirImovelActionForm.get("faixaPiscina");
		String pavimentoCalcada = (String) inserirImovelActionForm.get("pavimentoCalcada");
		String pavimentoRua = (String) inserirImovelActionForm.get("pavimentoRua");
		String fonteAbastecimento = (String) inserirImovelActionForm.get("fonteAbastecimento");
		String situacaoLigacaoAgua = (String) inserirImovelActionForm.get("situacaoLigacaoAgua");
		String situacaoLigacaoEsgoto = (String) inserirImovelActionForm.get("situacaoLigacaoEsgoto");
		String perfilImovel = (String) inserirImovelActionForm.get("perfilImovel");
		String poco = (String) inserirImovelActionForm.get("poco");
		String idLigacaoEsgotoEsgotamento = (String) inserirImovelActionForm.get("idLigacaoEsgotoEsgotamento");

		String tipoHabitacao = (String) inserirImovelActionForm.get("imovelTipoHabitacao");
		String tipoPropriedade = (String) inserirImovelActionForm.get("imovelTipoPropriedade");
		String tipoConstrucao = (String) inserirImovelActionForm.get("imovelTipoConstrucao");
		String tipoCobertura = (String) inserirImovelActionForm.get("imovelTipoCobertura");
		
		String pontoUtilizaco = (String) inserirImovelActionForm.get("numeroPontos");
		String numeroMoradores = (String) inserirImovelActionForm.get("numeroMoradores");
		String indicadorEnvioContaFisica = (String) inserirImovelActionForm.get("indicadorEnvioContaFisica");
		String numeroIptu = (String) inserirImovelActionForm.get("numeroIptu");
		String numeroContratoCelpe = (String) inserirImovelActionForm.get("numeroContratoCelpe");
		String imovelContaEnvioForm = (String) inserirImovelActionForm.get("imovelContaEnvio");
		String cordenadasX = (String) inserirImovelActionForm.get("cordenadasUtmX");
		String cordenadasY = (String) inserirImovelActionForm.get("cordenadasUtmY");
		String extratoResponsavel = (String) inserirImovelActionForm.get("extratoResponsavel");
		String tipoDespejo = (String) inserirImovelActionForm.get("tipoDespejo");
		String idImovelPrincipal = (String) inserirImovelActionForm.get("idImovel");
		String sequencialRota = (String) inserirImovelActionForm.get("sequencialRota");
		String sequencialRotaEntrega = (String) inserirImovelActionForm.get("sequencialRotaEntrega");
		String idRotaEntrega = (String) inserirImovelActionForm.get("idRota");
		String idRotaAlternativa = (String) inserirImovelActionForm.get("idRotaAlternativa");
		String idFuncionario = (String) inserirImovelActionForm.get("idFuncionario"); 
		String numeroMedidorEnergia = (String) inserirImovelActionForm.get("numeroMedidorEnergia");
		String dataVisitaComercialInformada  = (String) inserirImovelActionForm.get("dataVisitaComercial");
		String informacoesComplementares = (String) inserirImovelActionForm.get("informacoesComplementares");
		String nomeDoImovel = (String) inserirImovelActionForm.get("nomeDoImovel");
		String numeroQuadraEntrega = (String) inserirImovelActionForm.get("numeroQuadraEntrega");
		String indicadorNivelInstalacaoEsgoto = (String) inserirImovelActionForm.get("indicadorNivelInstalacaoEsgoto");
		
		Imovel imovel = imovelAtualizar;
		
		// ABA LOCALIDADE
		ImovelAbaLocalidadeHelper helperLocalidade = new ImovelAbaLocalidadeHelper();
		helperLocalidade.setIdImovel(imovelAtualizar.getId());
		helperLocalidade.setIdLocalidade(idLocalidade);
		helperLocalidade.setCodigoSetorComercial(idSetorComercial);
		helperLocalidade.setNumeroQuadra(idQuadra);
		helperLocalidade.setIdQuadraFace(idQuadraFace);
		helperLocalidade.setLote(lote);
		helperLocalidade.setSublote(subLote);
		ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade = fachada.validarImovelAbaLocalidade(helperLocalidade);
		
		
		if (Util.verificarNaoVazio(testadaLote)) {
			imovel.setTestadaLote(new Short(testadaLote).shortValue());
		} else {
			imovel.setTestadaLote(null);
		}
		if (Util.verificarNaoVazio(sequencialRota)) {
			imovel.setNumeroSequencialRota(new Integer(sequencialRota));
		} else {
			imovel.setNumeroSequencialRota(null);
		}
		
		// ABA ENDERECO
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");

		if (sessao.getAttribute("POPUP") != null) {
			if (sessao.getAttribute("POPUP").equals("true")) {
				if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
					Object obj = (Object) colecaoEnderecos.iterator().next();
					if (!(obj instanceof Imovel)) {
						sessao.removeAttribute("colecaoEnderecos");
					}
					if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
						colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecosImovel");
					}
				}else if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
					colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecosImovel");
				}
			}
		}
		
		ImovelAbaEnderecoHelper helperEndereco = new ImovelAbaEnderecoHelper();
		helperEndereco.setImovelEnderecos(colecaoEnderecos);
		helperEndereco.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		helperEndereco.setUsuarioLogado(usuario);
		helperEndereco.setIdFuncionalidade(Funcionalidade.MANTER_IMOVEL);
		
		fachada.validarImovelAbaEndereco(helperEndereco);
		
		Imovel imovelEndereco = (Imovel) Util.retonarObjetoDeColecao(colecaoEnderecos);
		
		Logradouro logradouro = null;
		if (imovelEndereco.getLogradouroCep() != null && 
			imovelEndereco.getLogradouroCep().getLogradouro() != null && 
			!imovelEndereco.getLogradouroCep().getLogradouro().equals("")) {
			Integer idLogradouro = imovelEndereco.getLogradouroCep().getLogradouro().getId();
			logradouro = new Logradouro();
			logradouro.setId(idLogradouro);
		} else {
			logradouro = new Logradouro();
			logradouro.setId(new Integer("0"));
		}
		
		LogradouroCep logradouroCep = null;
		if (imovelEndereco.getLogradouroCep() != null && 
			imovelEndereco.getLogradouroCep().getCep() != null) {
			Integer cep = imovelEndereco.getLogradouroCep().getCep().getCepId();
			Cep cepObj = new Cep();
			cepObj.setCepId(cep);
			logradouroCep = fachada.pesquisarAssociacaoLogradouroCep(cepObj.getCepId(), logradouro.getId());
		}
		
		EnderecoReferencia enderecoReferencia = null;
		if (imovelEndereco.getEnderecoReferencia() != null) {
			Integer idEnderecoReferencia = imovelEndereco.getEnderecoReferencia().getId();
			if (idEnderecoReferencia != null && !idEnderecoReferencia.toString().trim().equals("")) {
				enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setId(idEnderecoReferencia);
			}
		}
		
		imovel.setNumeroImovel(imovelEndereco.getNumeroImovel());
		imovel.setComplementoEndereco(imovelEndereco.getComplementoEndereco());
		imovel.setLogradouroCep(logradouroCep);
		imovel.setEnderecoReferencia(enderecoReferencia);
		imovel.setLogradouroBairro(imovelEndereco.getLogradouroBairro());
		imovel.setPerimetroInicial(imovelEndereco.getPerimetroInicial());
		imovel.setPerimetroFinal(imovelEndereco.getPerimetroFinal());
		
		imovel.setNomeImovel(nomeDoImovel);
		// ABA CLIENTES
		Collection clientes = (Collection) sessao.getAttribute("imovelClientesNovos");
		fachada.validarImovelAbaCliente(clientes, usuario);
		
		// ABA SUBCATEGORIA ECONOMIAS
        Collection subcategorias = (Collection) sessao.getAttribute("colecaoImovelSubCategorias");
		fachada.validarAbaInserirImovelSubcategoria(
				subcategorias, PermissaoEspecial.INSERIR_IMOVEL_PARA_ORGAO_PUBLICO, usuario);
		
		// ABA CARACTERISTICA
		ImovelAbaCaracteristicasHelper helperCaracteristica = new ImovelAbaCaracteristicasHelper();
		helperCaracteristica.setImovelAtualizar(imovel);
		helperCaracteristica.setAreaConstruida(areaConstruida);
		helperCaracteristica.setIdAreaConstruidaFaixa(faixaAreaConstruida);
		helperCaracteristica.setIdPavimentoCalcada(pavimentoCalcada);
		helperCaracteristica.setIdPavimentoRua(pavimentoRua);
		helperCaracteristica.setIdFonteAbastecimento(fonteAbastecimento);
		helperCaracteristica.setIdLigacaoAguaSituacao(situacaoLigacaoAgua);
		helperCaracteristica.setIdLigacaoEsgotoSituacao(situacaoLigacaoEsgoto);
		helperCaracteristica.setIdLigacaoEsgotoEsgotamento(idLigacaoEsgotoEsgotamento);
		helperCaracteristica.setIdImovelPerfil(perfilImovel);
		helperCaracteristica.setIdSetorComercial(idSetorComercial);
		helperCaracteristica.setIdQuadra(idQuadra);
		helperCaracteristica.setIdNivelInstalacaoEsgoto(indicadorNivelInstalacaoEsgoto);
		
		Integer perfilAntigo = imovel.getImovelPerfil().getId();
		
		Integer imovelPerfil = Integer.parseInt(helperCaracteristica.getIdImovelPerfil());
		Integer perfilSubcategoria = null;
		
		Iterator<ImovelSubcategoria> iteratorSubcategoria = subcategorias.iterator();
		while ((iteratorSubcategoria).hasNext()) {
			ImovelSubcategoria subcategoria = iteratorSubcategoria.next();
			if (imovelPerfil.equals(ImovelPerfil.BOLSA_AGUA) || imovelPerfil == ImovelPerfil.BOLSA_AGUA) {
				perfilSubcategoria = 2;
				if ((subcategoria.getComp_id().getSubcategoria().getId().equals(Subcategoria.RESIDENCIAL_R1)
						|| subcategoria.getComp_id().getSubcategoria().getId().equals(Subcategoria.RESIDENCIAL_R2)
						|| subcategoria.getComp_id().getSubcategoria().getId().equals(Subcategoria.RESIDENCIAL_R3)
						|| subcategoria.getComp_id().getSubcategoria().getId().equals(Subcategoria.RESIDENCIAL_R4))) {
					if (imovel.getIndicadorImovelCondominio().equals(ConstantesSistema.SIM)
							|| (perfilAntigo.equals(ImovelPerfil.CORPORATIVO_101)
									|| perfilAntigo.equals(ImovelPerfil.GOVERNO_METRO_401)
									|| perfilAntigo.equals(ImovelPerfil.GOVERNO_METRO_401)
									|| perfilAntigo.equals(ImovelPerfil.CONDOMINIAL_101))) {
						throw new ActionServletException("atencao.imovel_agua_para_e_um_condominio");
					} else {
						perfilSubcategoria = 1;
						break;
					}
				}
			}
		}
		
		if(perfilSubcategoria != null) {
			if(!perfilSubcategoria.equals(1)) {
				throw new ActionServletException("atencao.perfil_agua_para_sem_residencia");
			}
		}
		
		
		ImovelAbaCaracteristicasRetornoHelper resultadoAbaCaracteristicas = 
			fachada.validarImovelAbaCaracteristicas(helperCaracteristica);
		
		fachada.validarPerfilImovelAoAtualizarImovelAbaCaracteristicas(imovelAtualizar.getId(), getClienteResponsavel(clientes), new Integer(perfilImovel));
		
		if (Util.verificarNaoVazio(areaConstruida)) {
			imovel.setAreaConstruida(Util.formatarMoedaRealparaBigDecimal(areaConstruida));
		}
		imovel.setAreaConstruidaFaixa(resultadoAbaCaracteristicas.getAreaConstruidaFaixa());

		if (Util.verificarIdNaoVazio(faixaVolumeReservaotirio)) {
			ReservatorioVolumeFaixa reservatorioVolumeFaixaInf = new ReservatorioVolumeFaixa();
			reservatorioVolumeFaixaInf.setId(new Integer(faixaVolumeReservaotirio));
			imovel.setReservatorioVolumeFaixaInferior(reservatorioVolumeFaixaInf);
		}
		if (Util.verificarNaoVazio(volumeReservatorioInferior)) {
			imovel.setVolumeReservatorioInferior(Util.formatarMoedaRealparaBigDecimal(volumeReservatorioInferior));
		}
		if (Util.verificarIdNaoVazio(faixaVolumeReservatorioSuperior)) {
			ReservatorioVolumeFaixa reservatorioVolumeFaixaSup = new ReservatorioVolumeFaixa();
			reservatorioVolumeFaixaSup.setId(new Integer(faixaVolumeReservatorioSuperior));
			imovel.setReservatorioVolumeFaixaSuperior(reservatorioVolumeFaixaSup);
		}
		if (Util.verificarNaoVazio(volumeReservatorioSuperior)) {
			imovel.setVolumeReservatorioSuperior(Util.formatarMoedaRealparaBigDecimal(volumeReservatorioSuperior));
		}
		if (Util.verificarIdNaoVazio(faixaPiscina)) {
			PiscinaVolumeFaixa piscinaVolumeFaixa = new PiscinaVolumeFaixa();
			piscinaVolumeFaixa.setId(new Integer(faixaPiscina));
			imovel.setPiscinaVolumeFaixa(piscinaVolumeFaixa);
		}
		if (Util.verificarNaoVazio(piscina)) {
			imovel.setVolumePiscina(Util.formatarMoedaRealparaBigDecimal(piscina));
		}
		if (Util.verificarNaoVazio(jardim)) {
			imovel.setIndicadorJardim(new Short(jardim));
		}
		
		if (Util.verificarNaoVazio(indicadorEnvioContaFisica)) {
			imovel.setIndicadorEnvioContaFisica(new Short(indicadorEnvioContaFisica));
		}
		
		imovel.setPavimentoCalcada(resultadoAbaCaracteristicas.getPavimentoCalcada());
		imovel.setPavimentoRua(resultadoAbaCaracteristicas.getPavimentoRua());
		imovel.setFonteAbastecimento(resultadoAbaCaracteristicas.getFonteAbastecimento());
		imovel.setLigacaoAguaSituacao(resultadoAbaCaracteristicas.getLigacaoAguaSituacao());
		imovel.setLigacaoEsgotoSituacao(resultadoAbaCaracteristicas.getLigacaoEsgotoSituacao());
		imovel.setImovelPerfil(resultadoAbaCaracteristicas.getImovelPerfil());
		imovel.setIndicadorNivelInstalacaoEsgoto(new Short(indicadorNivelInstalacaoEsgoto));
		
		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento = resultadoAbaCaracteristicas.getLigacaoEsgotoEsgotamento();
		
		if (Util.verificarIdNaoVazio(poco)) {
			PocoTipo pocoTipo = new PocoTipo();
			pocoTipo.setId(new Integer(poco));
			imovel.setPocoTipo(pocoTipo);
		}
		if(poco.equals("-1")){
			imovel.setPocoTipo(null);
		}
		if (Util.verificarIdNaoVazio(tipoDespejo)) {
			Despejo despejo = new Despejo();
			despejo.setId(new Integer(tipoDespejo));
			imovel.setDespejo(despejo);
		}
		if (Util.verificarIdNaoVazio(tipoHabitacao)) {
			ImovelTipoHabitacao imovelTipoHabitacao = new ImovelTipoHabitacao();
			imovelTipoHabitacao.setId(new Integer(tipoHabitacao));
			imovel.setImovelTipoHabitacao(imovelTipoHabitacao);
		}
		if (Util.verificarIdNaoVazio(tipoPropriedade)) {
			ImovelTipoPropriedade imovelTipoPropriedade = new ImovelTipoPropriedade();
			imovelTipoPropriedade.setId(new Integer(tipoPropriedade));
			imovel.setImovelTipoPropriedade(imovelTipoPropriedade);
		}
		if (Util.verificarIdNaoVazio(tipoConstrucao)) {
			ImovelTipoConstrucao imovelTipoConstrucao = new ImovelTipoConstrucao();
			imovelTipoConstrucao.setId(new Integer(tipoConstrucao));
			imovel.setImovelTipoConstrucao(imovelTipoConstrucao);
		}
		if (Util.verificarIdNaoVazio(tipoCobertura)) {
			ImovelTipoCobertura imovelTipoCobertura = new ImovelTipoCobertura();
			imovelTipoCobertura.setId(new Integer(tipoCobertura));
			imovel.setImovelTipoCobertura(imovelTipoCobertura);
		}
		if (Util.verificarIdNaoVazio(numeroMedidorEnergia)) {
			
			imovel.setNumeroMedidorEnergia(numeroMedidorEnergia);
		}
		if (dataVisitaComercialInformada !=null && !dataVisitaComercialInformada.equals("")) {
			
			Date dataVisitaComercial = Util.converteStringParaDate(dataVisitaComercialInformada);
			imovel.setDataVisitaComercial(dataVisitaComercial);
		}
		
		// ABA CONCLUSAO
		ImovelAbaConclusaoHelper helperConclusao = new ImovelAbaConclusaoHelper();
		helperConclusao.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		helperConclusao.setIdQuadraImovel(idQuadra);
		helperConclusao.setIdImovelPrincipal(idImovelPrincipal);
		helperConclusao.setNumeroIptu(numeroIptu);
		helperConclusao.setNumeroContratoCelpe(numeroContratoCelpe);
		helperConclusao.setCoordenadasUtmX(cordenadasX);
		helperConclusao.setCoordenadasUtmY(cordenadasY);
		helperConclusao.setSequencialRotaEntrega(sequencialRotaEntrega);
		helperConclusao.setNumeroQuadraEntrega(numeroQuadraEntrega);
		helperConclusao.setIdRotaEntrega(idRotaEntrega);
		helperConclusao.setIdRotaAlternativa(idRotaAlternativa);
		helperConclusao.setImoveisClientes(clientes);
		helperConclusao.setIdImovelAtualizar(imovel.getId());
		helperConclusao.setNumeroMedidorEnergia(numeroMedidorEnergia);
		ImovelAbaConclusaoRetornoHelper resultadoAbaConclusao = fachada.validarImovelAbaConclusao(helperConclusao);
		
		if (numeroQuadraEntrega != null && !numeroQuadraEntrega.equals("")){
			imovel.setNumeroQuadraEntrega(new Integer(numeroQuadraEntrega));
		} else if (numeroQuadraEntrega.equals("")){
			imovel.setNumeroQuadraEntrega(null);
		}
		
		if (resultadoAbaConclusao.getSequencialRotaEntrega() != null && !resultadoAbaConclusao.getSequencialRotaEntrega().equals("")) {
			imovel.setNumeroSequencialRotaEntrega(new Integer(resultadoAbaConclusao.getSequencialRotaEntrega()));
		}else {
			imovel.setNumeroSequencialRotaEntrega(null);			
		}
		if (resultadoAbaConclusao.getRotaEntrega() != null && !resultadoAbaConclusao.getRotaEntrega().equals("")) {
			imovel.setRotaEntrega(resultadoAbaConclusao.getRotaEntrega());
		}else{
			imovel.setRotaEntrega(null);
		}
		
		if (resultadoAbaConclusao.getRotaAlternativa() != null) {
	        	imovel.setRotaAlternativa(resultadoAbaConclusao.getRotaAlternativa());
		}
		else{
			imovel.setRotaAlternativa(null);
		}
		
		imovel.setNumeroPontosUtilizacao(Util.verificarNaoVazio(pontoUtilizaco) ? new Short(pontoUtilizaco) : null);
		imovel.setNumeroMorador(Util.verificarNaoVazio(numeroMoradores) ? new Short(numeroMoradores) : null);
		imovel.setNumeroIptu(Util.verificarNaoVazio(numeroIptu) ? Util.formatarIPTU(numeroIptu) : null);
		imovel.setNumeroCelpe(Util.verificarNaoVazio(numeroContratoCelpe) ? new Long(numeroContratoCelpe) : null);
		imovel.setCoordenadaX(Util.verificarNaoVazio(cordenadasX) ? new String(cordenadasX.replace(',','.')) : null);
		imovel.setCoordenadaY(Util.verificarNaoVazio(cordenadasY) ? new String(cordenadasY.replace(',','.')) : null);
		if (Util.verificarIdNaoVazio(idImovelPrincipal)) {
			Imovel imovelPrincipal = new Imovel();
			imovelPrincipal.setId(new Integer(idImovelPrincipal));
			imovel.setImovelPrincipal(imovelPrincipal);
		}
		if (Util.verificarIdNaoVazio(idFuncionario)) {
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(idFuncionario));
			imovel.setFuncionario(funcionario);
		} else {
			imovel.setFuncionario(null);
		}
		
		if ( Util.verificarNaoVazio(informacoesComplementares) ){
			
			imovel.setInformacoesComplementares(informacoesComplementares);
		} else {
			imovel.setInformacoesComplementares("");
		}
		
		
		if (Util.verificarIdNaoVazio(numeroMedidorEnergia)) {
			
			imovel.setNumeroMedidorEnergia(numeroMedidorEnergia);
		}
		if (dataVisitaComercialInformada !=null && !dataVisitaComercialInformada.equals("")) {
			
			Date dataVisitaComercial = Util.converteStringParaDate(dataVisitaComercialInformada);
			imovel.setDataVisitaComercial(dataVisitaComercial);
		}
		
		// OUTROS
		imovel.setIndicadorEmissaoExtratoFaturamento(Util.verificarNaoVazio(extratoResponsavel) ? new Short(extratoResponsavel) : ConstantesSistema.NAO);
		
		if(imovelAtualizar.getIndicadorExclusao() == null){
			imovel.setIndicadorExclusao(ConstantesSistema.NAO);
		}else{
			imovel.setIndicadorExclusao(imovelAtualizar.getIndicadorExclusao());
		}
		imovel.setIndicadorImovelCondominio(imovelAtualizar.getIndicadorImovelCondominio());
		imovel.setIndicadorDebitoConta(imovelAtualizar.getIndicadorDebitoConta());
		imovel.setConsumoTarifa(imovelAtualizar.getConsumoTarifa());
		
		short quantidadeEconomias = 0;
		Iterator iteratorSubcategorias = subcategorias.iterator();
		while (iteratorSubcategorias.hasNext()) {
			ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iteratorSubcategorias.next();
			quantidadeEconomias += imovelSubcategoria.getQuantidadeEconomias();
		}
		imovel.setQuantidadeEconomias(quantidadeEconomias);
		
		ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
		if (Util.verificarIdNaoVazio(imovelContaEnvioForm)) {
			imovelContaEnvio = new ImovelContaEnvio();
			imovelContaEnvio.setId(new Integer(imovelContaEnvioForm));
		} else {
			imovelContaEnvio = new ImovelContaEnvio();
			imovelContaEnvio.setId(new Integer(2));
		}
		imovel.setImovelContaEnvio(imovelContaEnvio);
		
		if (imovel.getIndicadorEmissaoExtratoFaturamento() != null){
			if (imovel.getIndicadorEmissaoExtratoFaturamento().equals(new Short("0"))){
				imovel.setIndicadorEmissaoExtratoFaturamento(new Short("2"));
			}
		} else {
			imovel.setIndicadorEmissaoExtratoFaturamento(new Short("2"));
		}
		
		if (imovel.getIndicadorVencimentoMesSeguinte() == null){
			imovel.setIndicadorVencimentoMesSeguinte(new Short("2"));
		}
		
		if(imovel.getImovelPerfil()!=null){
		boolean achou = false;	
			if(imovel.getImovelPerfil().getSubcategoria()!=null){
				
				for (Iterator iterator = subcategorias.iterator(); iterator.hasNext();) {
					ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();
					Subcategoria subcategoria = imovelSubcategoria.getComp_id().getSubcategoria();
					if(subcategoria.getId().compareTo(imovel.getImovelPerfil().getSubcategoria().getId())==0){
						achou = true;
					}
				}
			
			
			if(!achou || subcategorias.size()>1){
				
				FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
				
				filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, imovel.getImovelPerfil().getSubcategoria().getId()));
				
				Collection<Subcategoria> colecaoSubs =
					 fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
				
				Subcategoria subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubs);
				
				throw new ActionServletException("atencao.perfil_exige_subcategoria",subcategoria.getDescricao());
			}
			}
		}		

		/**
		 * [SB0004] ï¿½ Verificar Alteraï¿½ï¿½o da Inscriï¿½ï¿½o do Imï¿½vel
		 * @author Arthur Carvalho
		 * @date 18/09/2010
		 */
		Imovel imovelSemAtualizacao = pesquisarImovel(imovelAtualizar.getId(), fachada);
		
		boolean flagNaoConfirmado = false;
		
		boolean inscricaoAlterada = this.verificarInscricaoAlterada(imovelSemAtualizacao,resultadoAbaLocalidade,helperLocalidade);

		String confirmado = httpServletRequest.getParameter("confirmado");
		String confirmacaoDupla = httpServletRequest.getParameter("confirmacaoDupla");
		
		//		1.	Caso os dados da inscriï¿½ï¿½o tenham sido alterados e o imï¿½vel 
		//      nï¿½o tenha rota alternativa (ROTA_IDALTERNATIVA com o valor nulo na tabela IMOVEL):
		if ( inscricaoAlterada &&  imovelSemAtualizacao.getRotaAlternativa() == null ) {
			
			if ( confirmado == null || confirmacaoDupla == null ||
				 (confirmacaoDupla != null && confirmacaoDupla.equalsIgnoreCase(""))) { 
				
				//1.3.	Caso a alteraï¿½ï¿½o da inscriï¿½ï¿½o acarrete a mudanï¿½a do grupo de faturamento do imï¿½vel 
				
				retorno = this.verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(
							imovelSemAtualizacao.getId(), imovelSemAtualizacao.getQuadra().getId(), resultadoAbaLocalidade.getQuadra().getId(), 
								httpServletRequest, actionMapping, fachada, imovel, resultadoAbaLocalidade, helperLocalidade, sessao);
				
			}
			
		} 
		else {
			atualizarImovel( imovel, resultadoAbaLocalidade, helperLocalidade);
		}
		
		// Se tiver sido aprensentada a tela confirmaï¿½ï¿½o e o usuario confirmou.
		if(confirmado != null && confirmado.equalsIgnoreCase("ok") &&
		   confirmacaoDupla != null && confirmacaoDupla.equalsIgnoreCase("ok")){
			
			retorno = actionMapping.findForward("telaSucesso");
			sessao.removeAttribute("remove");
			//[SB0005] ï¿½ Preparar Alteraï¿½ï¿½o Inscriï¿½ï¿½o no Encerramento Faturamento
			prepararAlteracaoInscricaoEncerramentoFaturamento( imovel, imovelSemAtualizacao,
					 resultadoAbaLocalidade,  fachada,  helperLocalidade, usuario );
		} else if (confirmado != null && confirmado.equalsIgnoreCase("nao")) {
			flagNaoConfirmado =  true;
			retorno = actionMapping.findForward("telaSucesso");
			
			httpServletRequest.removeAttribute("nomeBotao1");
			httpServletRequest.removeAttribute("nomeBotao3");
			httpServletRequest.removeAttribute("confirmacaoDupla");

			sessao.removeAttribute("remove");
			//httpServletRequest.setAttribute("url","javascript:window.location.href='/gsan/atualizarImovelWizardAction.do?action=exibirAtualizarImovelLocalidadeAction';");
		}
		
		
		Categoria principalCategoria = fachada.obterPrincipalCategoria(subcategorias);
		
		if (principalCategoria != null){
			
			imovel.setCategoriaPrincipalId(principalCategoria.getId());
			

			ImovelSubcategoria principalSubCategoria = 
				fachada.obterPrincipalSubcategoria(principalCategoria.getId(), subcategorias);
	
			imovel.setSubCategoriaPrincipalId(principalSubCategoria.getComp_id().getSubcategoria().getId());
		}
		
		
		/**
		 * alterado por pedro alexandre dia 17/11/2006 Recupera o usuï¿½rio logado
		 * para passar no metï¿½do de atualizar imï¿½vel para verificar se o usuï¿½rio
		 * tem abrangï¿½ncia para atualizar o imï¿½vel informado.
		 */
		
	    Collection ramosAtividades = (Collection) sessao.getAttribute("colecaoImovelRamosAtividade");
		
	    InserirImovelHelper inserirImovelHelper = new InserirImovelHelper(imovel, subcategorias,ramosAtividades, 
	    		colecaoEnderecos, clientes, ligacaoEsgotoEsgotamento, usuario);
		
		inserirImovelHelper.setColecaoClientesImoveisRemovidos(colecaoClientesImoveisRemovidos);
		inserirImovelHelper.setColecaoImovelSubcategoriasRemovidas(colecaoImovelSubcategoriasRemovidas);
		inserirImovelHelper.setColecaoRamoAtividadesRemovidas(colecaoImovelRamosAtividadesRemovidos);
		inserirImovelHelper.setDataInicioRelacaoUsuario(dataInicioRelacaoUsuario);

		fachada.atualizarImovel(inserirImovelHelper);
		
		String mensagemSucesso  = "Imóvel de matrícula "
			+ imovelAtualizar.getId().toString()
			+ " atualizado com sucesso.";
		
		// Caso imï¿½vel esteja em processo de faturamento, e o usuï¿½rio nï¿½o 
		// tenha confirmado na tela de confirmaï¿½ao sera acrescentado a mensagem de conclusï¿½o
		// aviso de nï¿½o alteraï¿½ï¿½o dos dados da inscriï¿½ï¿½o.
		if(flagNaoConfirmado){
			mensagemSucesso  = "Imóvel de matrícula "
				+ imovelAtualizar.getId().toString()
				+ " atualizado com sucesso. \n"
				+ " Atenção! Dados da inscrição não alterados devido ao imóvel estar em processo de faturamento.";
		}
		
		if(sessao.getAttribute("caminhoVoltarPromais")!=null){
		
			montarPaginaSucesso(httpServletRequest, "Imóvel de matrícula "
					+ imovelAtualizar.getId().toString()
					+ " atualizado com sucesso.",
					"Realizar outra Manutenção de Imóvel",
					"exibirFiltrarImovelAction.do?menu=sim",
					"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?idImovel="
							+ imovelAtualizar.getId().toString(),
					"Informar Ocorrências / Anormalidades",
					"Retornar ao Consultar Imóvel.",
					(String)sessao.getAttribute("caminhoVoltarPromais")+".do?promais=nao");
			
			sessao.setAttribute("promaisExecutado", "sim");
			sessao.setAttribute("idImovelPromaisExecutado", imovelAtualizar.getId());
			
			
		}else{
			montarPaginaSucesso(httpServletRequest, mensagemSucesso,
					"Realizar outra Manutenção de Imóvel",
					"exibirFiltrarImovelAction.do?menu=sim",
					"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?idImovel="
							+ imovelAtualizar.getId().toString(),
					"Informar Ocorrências / Anormalidades");
		}

		if ( sessao.getAttribute("remove") == null ){
			
		
		sessao.removeAttribute("colecaoEnderecos");
		sessao.removeAttribute("imovelAtualizacao");
		sessao.removeAttribute("localidade");
		sessao.removeAttribute("setorComercial");
		sessao.removeAttribute("quadra");
		sessao.removeAttribute("colecaoImovelSubCategorias");
		sessao.removeAttribute("colecaoImovelRamosAtividadesRemovidos");
		sessao.removeAttribute("idQuadraInicial");
		}
		
		if (sessao.getAttribute("colecaoClientesImoveisRemovidos") != null) {
			sessao.removeAttribute("colecaoClientesImoveisRemovidos");
		}
		if (sessao.getAttribute("colecaoImovelSubcategoriasRemoviadas") != null) {
			sessao.removeAttribute("colecaoImovelSubcategoriasRemoviadas");
		}
		

		// [FS0035] ï¿½ Verifica se existe RA e ordem pendente
		if (imovelAtualizar != null && imovelAtualizar.getId() != null && retorno.getName().equals("telaSucesso")) {

			//Caso nï¿½o tenha permissï¿½o especial verifica se tem apenas ï¿½umaï¿½ RA pendente (RGAT_CDSITUACAO = 1) 
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
			filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");
			filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao.solicitacaoTipo");
			filtroRegistroAtendimento.adicionarParametro( 
					new ParametroSimples (FiltroRegistroAtendimento.IMOVEL_ID , 
							imovelAtualizar.getId() ));
			filtroRegistroAtendimento.adicionarParametro( 
					new ParametroSimples (FiltroRegistroAtendimento.CODIGO_SITUACAO , 
							ConstantesSistema.SITUACAO_PENDENTE ));
			
			Collection colecaoRegistroAtendiomento = fachada.pesquisar(
					filtroRegistroAtendimento, RegistroAtendimento.class.getName());
			
			if ( colecaoRegistroAtendiomento != null && colecaoRegistroAtendiomento.size() == 1 ) {
				
				RegistroAtendimento registroAtendimento = (RegistroAtendimento) 
										Util.retonarObjetoDeColecao(colecaoRegistroAtendiomento);
				
				//Caso exista OS pendente o RA nï¿½o poderï¿½ ser encerrado. Retornar ao fluxo principal
				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(
						new ParametroSimples(FiltroOrdemServico.REGISTRO_ATENDIMENTO_ID, 
								registroAtendimento.getId()));
				filtroOrdemServico.adicionarParametro(
						new ParametroSimples(FiltroOrdemServico.SITUACAO, 
								ConstantesSistema.SITUACAO_PENDENTE ) );
				
				Collection colecaoOrdemServico = fachada.pesquisar(
						filtroOrdemServico, OrdemServico.class.getName());
				
				if ( colecaoOrdemServico == null || colecaoOrdemServico.isEmpty() ) {
					
					// grava o "numeroRA" na sessao para ser recuperado 
					// no action "ExibirEncerrarRegistroAtendimentoAction".
					sessao.setAttribute("numeroRA",registroAtendimento.getId());
						
					// coloca no request a URL do action que sera processado caso
					// o usuario pressionar o botao confirmar na tela de confirmacao.
					httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/exibirEncerrarRegistroAtendimentoAction.do");
					
					httpServletRequest.setAttribute("cancelamento", "TRUE");
					httpServletRequest.setAttribute("nomeBotao1", "Sim");
					httpServletRequest.setAttribute("nomeBotao2", "Não");
					sessao.setAttribute("idImovel",imovelAtualizar.getId().toString());
					
					//Monta o array de variaveis que sera montada na mensagem de confirmaï¿½ï¿½o 
					String[] mensagem = new String[3];
					
					mensagem[0] = registroAtendimento.getId().toString();
					
					mensagem[1] = registroAtendimento.getSolicitacaoTipoEspecificacao().
										getSolicitacaoTipo().getDescricao();
					
					mensagem[2] = registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao();
						
                    // Monta a pï¿½gina de confirmaï¿½ï¿½o para perguntar se o usuï¿½rio
					// deseja encerrar o registro de atendimento.
					return montarPaginaConfirmacao("atencao.encerrar_ra_atualizar_imovel",
							httpServletRequest, actionMapping, mensagem);
						
				}
			}
			
				
		}		

		return retorno;
	}

	/**
	 *		1.2.	Caso a alteraï¿½ï¿½o da inscriï¿½ï¿½o acarrete a mudanï¿½a do grupo de faturamento do imï¿½vel 
	 *	(grupo de faturamento origem (FTGR_ID da tabela ROTA para ROTA_ID=ROTA_ID da tabela QUADRA para QDRA_ID=QDRA_IDANTERIOR)
	 * 	diferente do grupo de faturamento destino (FTGR_ID da tabela ROTA para ROTA_ID=ROTA_ID da tabela QUADRA para QDRA_ID=QDRA_IDATUAL)):
	 * 
	 * @author Arthur Carvalho
	 * @date 16/09/2010
	 * @param imovelSemAtualizacao
	 * @param resultadoAbaLocalidade
	 * @param helperLocalidade
	 * @return
	 */
	private boolean verificarInscricaoAlterada(Imovel imovelSemAtualizacao,
			ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade,
			ImovelAbaLocalidadeHelper helperLocalidade) {
		
		boolean retorno = false;
		
		if(imovelSemAtualizacao.getLocalidade()!=null && resultadoAbaLocalidade.getLocalidade()!=null){
			if(imovelSemAtualizacao.getLocalidade().getId()
					.compareTo(resultadoAbaLocalidade.getLocalidade().getId())!=0){
				retorno = true;
			}
		}
		if(imovelSemAtualizacao.getSetorComercial()!=null && resultadoAbaLocalidade.getSetorComercial()!=null){
			if(imovelSemAtualizacao.getSetorComercial().getId()
					.compareTo(resultadoAbaLocalidade.getSetorComercial().getId())!=0){
				retorno = true;
			}
		}
		if(imovelSemAtualizacao.getQuadra()!=null && resultadoAbaLocalidade.getQuadra()!=null){
			if(imovelSemAtualizacao.getQuadra().getId()
					.compareTo(resultadoAbaLocalidade.getQuadra().getId())!=0){
				retorno = true;
			}
		}	
		if(imovelSemAtualizacao.getQuadraFace()!=null && resultadoAbaLocalidade.getQuadraFace()!=null){
			if(imovelSemAtualizacao.getQuadraFace().getId()
					.compareTo(resultadoAbaLocalidade.getQuadraFace().getId())!=0){
				retorno = true;
			}
		}
		
		if(imovelSemAtualizacao.getLote() != new Short(helperLocalidade.getLote()).shortValue()){
			retorno = true;
		}
		if(imovelSemAtualizacao.getSubLote() != new Short(helperLocalidade.getSublote()).shortValue()){
			retorno = true;
		}

		return retorno;
	}
	
	/**
	 * [SB0004] ï¿½ Verificar Alteraï¿½ï¿½o da Inscriï¿½ï¿½o do Imï¿½vel
	 * 
	 * @author Arthur Carvalho
	 * @date 16/09/2010
	 * @param imovelSemAtualizacao
	 * @param resultadoAbaLocalidade
	 * @param helperLocalidade
	 * @return
	 */
	private ActionForward verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(Integer idImovel, Integer idQuadraAnterior,Integer idQuadraAtual, 
			HttpServletRequest httpServletRequest, ActionMapping actionMapping, Fachada fachada, Imovel imovel,
			ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade, ImovelAbaLocalidadeHelper helperLocalidade, HttpSession sessao) {
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		FaturamentoGrupo[] faturamentoGrupos = new FaturamentoGrupo[2];
		FaturamentoGrupo faturamentoGrupoOrigem = new FaturamentoGrupo();
		FaturamentoGrupo faturamentoGrupoDestino = new FaturamentoGrupo();
		//boolean retorno = false;
		
		//localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		/*
		 * 1. Caso os dados da inscriï¿½ï¿½o tenham sido alterados e o indicador de alteraï¿½ï¿½o da inscriï¿½ï¿½o esteja ativo 
		 * (PARM_ICALTERACAOINSCRICAOIMOVEL=1 da tabela SISTEMA_PARAMETROS):
		 */
		if (sistemaParametro.getIndicadorAlteracaoInscricaoImovel().equals(ConstantesSistema.SIM)){
			
			/*
			 * 1.1.Caso o imï¿½vel esteja em processo de faturamento (existe ocorrï¿½ncia na tabela MOVIMENTO_ROTEIRO_EMPRESA para IMOV_ID=IMOV_ID 
			 * da tabela IMOVEL e MREM_AMMOVIMENTO=FTGR_AMREFERENCIA da tabela FATURAMENTO_GRUPO para FTGR_ID=FTGR_ID da 
			 * tabela MOVIMENTO_ROTEIRO_EMPRESA):
			 */
			//if (fachada.verificarImovelEmProcessoDeFaturamento(imovel.getId())){
				
				httpServletRequest.setAttribute("nomeBotao1", "Sim");
				httpServletRequest.setAttribute("nomeBotao3", "Não");
				httpServletRequest.setAttribute("confirmacaoDupla", "ok");

				sessao.setAttribute("remove" , "nao");
				
				return montarPaginaConfirmacaoWizard("atencao.imovel_em_processo_de_faturamento",
				httpServletRequest, actionMapping);
			//}
			
		}
		/*
		 * 2.Caso os dados da inscriï¿½ï¿½o tenham sido alterados e o indicador de alteraï¿½ï¿½o da inscriï¿½ï¿½o esteja inativo 
		 * (PARM_ICALTERACAOINSCRICAOIMOVEL=2 da tabela SISTEMA_PARAMETROS) e o imï¿½vel nï¿½o tenha rota alternativa 
		 * (ROTA_IDALTERNATIVA com o valor nulo na tabela IMOVEL):
		 */
		else{
			
			faturamentoGrupos = fachada.verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(idQuadraAnterior, idQuadraAtual);
			faturamentoGrupoOrigem = faturamentoGrupos[0];
			faturamentoGrupoDestino = faturamentoGrupos[1];
			
			
			
			//verifica se o imovel foi mudado de grupo ao ser alterado a quadra
			if ( !faturamentoGrupoOrigem.getId().toString().equals(faturamentoGrupoDestino.getId().toString()) ) {

				//1.3.1
				if ( faturamentoGrupoOrigem.getAnoMesReferencia() > sistemaParametro.getAnoMesFaturamento() ) {
				
					//1.3.1.1
					if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoOrigem) ) {
					
						//1.3.1.1.1 
						//1.3.1.1.2
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao3", "Não");
						httpServletRequest.setAttribute("confirmacaoDupla", "ok");
						
						sessao.setAttribute("remove" , "nao");
						
						return montarPaginaConfirmacaoWizard(
								"atencao.faturamento_grupo_origem_dados_leitura_gerados",
								httpServletRequest, actionMapping, idImovel.toString(), faturamentoGrupoDestino.getId().toString() ,faturamentoGrupoOrigem.getId().toString()   );
						
					}//1.3.1.2 
					else {
						
						//1.3.1.2.1
						if ( faturamentoGrupoDestino.getAnoMesReferencia() > sistemaParametro.getAnoMesFaturamento() ) {
							
							//1.3.1.2.1.1.1
							if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino)) {
								//1.3.1.2.1.1.1 
								//1.3.1.2.1.1.2
								httpServletRequest.setAttribute("nomeBotao1", "Sim");
								httpServletRequest.setAttribute("nomeBotao3", "Não");
								httpServletRequest.setAttribute("confirmacaoDupla", "ok");

								sessao.setAttribute("remove" , "nao");
								
								return 	montarPaginaConfirmacaoWizard(
										"atencao.faturamento_grupo_destino_dados_leitura_gerados",
										httpServletRequest, actionMapping, idImovel.toString(), faturamentoGrupoDestino.getId().toString() );
						
							}//1.3.1.2.1.2
							else {
								//efetivar a alteraï¿½ï¿½o da inscriï¿½ï¿½o na tabela IMOVEL
								atualizarImovel( imovel, resultadoAbaLocalidade, helperLocalidade);
							}
							
						}//1.3.1.2.2
						else {
							
							//1.3.1.2.2.1
							if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino)) {
								
								//1.3.1.2.2.1.1 
								//1.3.1.2.2.1.2
								httpServletRequest.setAttribute("nomeBotao1", "Sim");
								httpServletRequest.setAttribute("nomeBotao3", "Não");
								httpServletRequest.setAttribute("confirmacaoDupla", "ok");

								sessao.setAttribute("remove" , "nao");
								
								return montarPaginaConfirmacaoWizard(
										"atencao.dados_leituras_gerados_faturamento_grupo_destino",
										httpServletRequest, actionMapping, idImovel.toString(), faturamentoGrupoDestino.getId().toString() );

								//[SB0005 ï¿½ Preparar Alteraï¿½ï¿½o Inscriï¿½ï¿½o no Encerramento Faturamento]
							} else {
								//1.3.1.2.2.2
								//efetivar a alteraï¿½ï¿½o da inscriï¿½ï¿½o na tabela IMOVEL
								atualizarImovel( imovel, resultadoAbaLocalidade, helperLocalidade);
							}
						}
					}
				} //1.3.2
				else {
					//1.3.2.1
					if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoOrigem) ) {
						//1.3.2.1.1
						//1.3.2.1.2
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao3", "Não");
						httpServletRequest.setAttribute("confirmacaoDupla", "ok");

						sessao.setAttribute("remove" , "nao");
						
						return montarPaginaConfirmacaoWizard(
								"atencao.dados_leituras_gerados_faturamento_grupo_origem",
									httpServletRequest, actionMapping, idImovel.toString(), 
										faturamentoGrupoDestino.getId().toString(), faturamentoGrupoOrigem.getId().toString() );
					}//1.3.2.2
					else {
						//1.3.2.2.1
						if ( faturamentoGrupoDestino.getAnoMesReferencia() > sistemaParametro.getAnoMesFaturamento() ) {
							//1.3.2.2.1.1
							//1.3.2.2.1.2
							httpServletRequest.setAttribute("nomeBotao1", "Sim");
							httpServletRequest.setAttribute("nomeBotao3", "Não");
							httpServletRequest.setAttribute("confirmacaoDupla", "ok");

							sessao.setAttribute("remove" , "nao");
							
							return montarPaginaConfirmacaoWizard(
									"atencao.faturamento_grupo_destino_ja_faturado",
										httpServletRequest, actionMapping, idImovel.toString(), 
											faturamentoGrupoDestino.getId().toString() );
						}//1.3.2.2.2
						else {
							//1.3.2.2.2.1
							if ( fachada.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino) ) {
								//1.3.2.2.2.1.1
								//1.3.2.2.2.1.2
								httpServletRequest.setAttribute("nomeBotao1", "Sim");
								httpServletRequest.setAttribute("nomeBotao3", "Não");
								httpServletRequest.setAttribute("confirmacaoDupla", "ok");

								sessao.setAttribute("remove" , "nao");
								
								return montarPaginaConfirmacaoWizard(
										"atencao.faturamento_grupo_destino_nao_faturado",
											httpServletRequest, actionMapping, idImovel.toString(), 
												faturamentoGrupoDestino.getId().toString() );
							}//1.3.2.2.2.2 
							else {
								//efetivar a alteraï¿½ï¿½o da inscriï¿½ï¿½o na tabela IMOVEL
								atualizarImovel( imovel, resultadoAbaLocalidade, helperLocalidade);
							}
						}
					}
				}
			}
		}
		
		
		//efetivar a alteraï¿½ï¿½o da inscriï¿½ï¿½o na tabela IMOVEL
		atualizarImovel( imovel, resultadoAbaLocalidade, helperLocalidade);
		
		return retorno;
	}
	
	/**
	 * [SB0004] – Verificar Alteração da Inscrição do Imóvel
	 * 
	 * @author Arthur Carvalho
	 * @date 16/09/2010
	 * @param imovelSemAtualizacao
	 * @param resultadoAbaLocalidade
	 * @param helperLocalidade
	 * @return
	 */
	private ActionForward verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(
			Imovel imovelSemAtualizacao, Integer idQuadraAtual,
			HttpServletRequest httpServletRequest, ActionMapping actionMapping,
			Fachada fachada, Imovel imovel,
			ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade,
			ImovelAbaLocalidadeHelper helperLocalidade, HttpSession sessao) {
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		FaturamentoGrupo[] faturamentoGrupos = new FaturamentoGrupo[2];
		FaturamentoGrupo faturamentoGrupoOrigem = new FaturamentoGrupo();
		FaturamentoGrupo faturamentoGrupoDestino = new FaturamentoGrupo();
		// boolean retorno = false;

		faturamentoGrupos = fachada
				.verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(
						imovelSemAtualizacao.getQuadra().getId(), idQuadraAtual);
		faturamentoGrupoOrigem = faturamentoGrupos[0];
		faturamentoGrupoDestino = faturamentoGrupos[1];

		/**
		 * 
		 * 
		 * Pamela Gatinho - 02/08/2011
		 * 
		 * Adicionando consulta da rota para verificar se ela já foi gerada ou
		 * não
		 * 
		 */
		// Rota que o imóvel pertence
		Rota rotaSemAtualizacao = null; 
		//fachada.obterRotaPorLocalidadeSetorComercial(
		//		imovelSemAtualizacao.getLocalidade().getId(), idQuadraAtual);

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// verifica se o imovel foi mudado de grupo ao ser alterado a quadra
		if (!faturamentoGrupoOrigem.getId().toString()
				.equals(faturamentoGrupoDestino.getId().toString())) {

			// 1.3.1
			if (faturamentoGrupoOrigem.getAnoMesReferencia() > sistemaParametro
					.getAnoMesFaturamento()) {

				// 1.3.1.1
				if (fachada
						.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoOrigem)) {

					// 1.3.1.1.1
					// 1.3.1.1.2
					httpServletRequest.setAttribute("nomeBotao1", "Sim");
					httpServletRequest.setAttribute("nomeBotao3", "Não");
					sessao.setAttribute("remove", "nao");
					return montarPaginaConfirmacaoWizard(
							"atencao.faturamento_grupo_origem_dados_leitura_gerados",
							httpServletRequest, actionMapping,
							imovelSemAtualizacao.getId().toString(),
							faturamentoGrupoDestino.getId().toString(),
							faturamentoGrupoOrigem.getId().toString());

				}
				/**
				 *  Alteracao para não permitir alterar a rota se
				 * ela estiver sigo gerada
				 */
//				else if (rotaSemAtualizacao != null && fachada.verificaGeracaoDadosLeituraRota(
//						faturamentoGrupoOrigem, rotaSemAtualizacao)) {
//					System.out
//							.println("Imóvel em rota já gerada para referência!");
//				}

				// 1.3.1.2
				else {

					// 1.3.1.2.1
					if (faturamentoGrupoDestino.getAnoMesReferencia() > sistemaParametro
							.getAnoMesFaturamento()) {

						// 1.3.1.2.1.1.1
						if (fachada
								.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino)) {
							// 1.3.1.2.1.1.1
							// 1.3.1.2.1.1.2
							httpServletRequest
									.setAttribute("nomeBotao1", "Sim");
							httpServletRequest
									.setAttribute("nomeBotao3", "Não");

							sessao.setAttribute("remove", "nao");
							return montarPaginaConfirmacaoWizard(
									"atencao.faturamento_grupo_destino_dados_leitura_gerados",
									httpServletRequest, actionMapping,
									imovelSemAtualizacao.getId().toString(),
									faturamentoGrupoDestino.getId().toString());

						}/**
						 *  Alteracao para não permitir alterar a
						 * rota se ela estiver sigo gerada
						 */
//						else if (rotaSemAtualizacao != null && fachada.verificaGeracaoDadosLeituraRota(
//								faturamentoGrupoOrigem, rotaSemAtualizacao)) {
//							System.out
//									.println("Imóvel em rota já gerada para referência!");
//						}// 1.3.1.2.1.2
						else {
							// efetivar a alteração da inscrição na tabela
							// IMOVEL
							atualizarImovel(imovel, resultadoAbaLocalidade,
									helperLocalidade);
						}

					}// 1.3.1.2.2
					else {

						// 1.3.1.2.2.1
						if (fachada
								.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino)) {

							// 1.3.1.2.2.1.1
							// 1.3.1.2.2.1.2
							httpServletRequest
									.setAttribute("nomeBotao1", "Sim");
							httpServletRequest
									.setAttribute("nomeBotao3", "Não");

							sessao.setAttribute("remove", "nao");
							return montarPaginaConfirmacaoWizard(
									"atencao.dados_leituras_gerados_faturamento_grupo_destino",
									httpServletRequest, actionMapping,
									imovelSemAtualizacao.getId().toString(),
									faturamentoGrupoDestino.getId().toString());

							// [SB0005 – Preparar Alteração Inscrição no
							// Encerramento Faturamento]
						}/**
						 *  Alteracao para não permitir alterar a
						 * rota se ela estiver sigo gerada
						 */
//						else if (rotaSemAtualizacao != null &&  fachada.verificaGeracaoDadosLeituraRota(
//								faturamentoGrupoOrigem, rotaSemAtualizacao)) {
//							System.out
//									.println("Imóvel em rota já gerada para referência!");
//						} 
						else {
							// 1.3.1.2.2.2
							// efetivar a alteração da inscrição na tabela
							// IMOVEL
							atualizarImovel(imovel, resultadoAbaLocalidade,
									helperLocalidade);
						}
					}
				}
			} // 1.3.2
			else {
				// 1.3.2.1
				if (fachada
						.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoOrigem)) {
					// 1.3.2.1.1
					// 1.3.2.1.2
					httpServletRequest.setAttribute("nomeBotao1", "Sim");
					httpServletRequest.setAttribute("nomeBotao3", "Não");

					sessao.setAttribute("remove", "nao");
					return montarPaginaConfirmacaoWizard(
							"atencao.dados_leituras_gerados_faturamento_grupo_origem",
							httpServletRequest, actionMapping,
							imovelSemAtualizacao.getId().toString(),
							faturamentoGrupoDestino.getId().toString(),
							faturamentoGrupoOrigem.getId().toString());
				}/**
				 *  Alteracao para não permitir alterar a rota se
				 * ela estiver sigo gerada
				 */
//				else if (rotaSemAtualizacao != null && fachada.verificaGeracaoDadosLeituraRota(
//						faturamentoGrupoOrigem, rotaSemAtualizacao)) {
//					System.out
//							.println("Imóvel em rota já gerada para referência!");
//				}// 1.3.2.2
				else {
					// 1.3.2.2.1
					if (faturamentoGrupoDestino.getAnoMesReferencia() > sistemaParametro
							.getAnoMesFaturamento()) {
						// 1.3.2.2.1.1
						// 1.3.2.2.1.2
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao3", "Não");

						sessao.setAttribute("remove", "nao");
						return montarPaginaConfirmacaoWizard(
								"atencao.faturamento_grupo_destino_ja_faturado",
								httpServletRequest, actionMapping,
								imovelSemAtualizacao.getId().toString(),
								faturamentoGrupoDestino.getId().toString());
					}// 1.3.2.2.2
					else {
						// 1.3.2.2.2.1
						if (fachada
								.verificaGeracaoDadosLeituraGrupoFaturamento(faturamentoGrupoDestino)) {
							// 1.3.2.2.2.1.1
							// 1.3.2.2.2.1.2
							httpServletRequest
									.setAttribute("nomeBotao1", "Sim");
							httpServletRequest
									.setAttribute("nomeBotao3", "Não");

							sessao.setAttribute("remove", "nao");
							return montarPaginaConfirmacaoWizard(
									"atencao.faturamento_grupo_destino_nao_faturado",
									httpServletRequest, actionMapping,
									imovelSemAtualizacao.getId().toString(),
									faturamentoGrupoDestino.getId().toString());
						}/**
						 *  Alteracao para não permitir alterar a
						 * rota se ela estiver sigo gerada
						 */
//						else if (rotaSemAtualizacao != null &&  fachada.verificaGeracaoDadosLeituraRota(
//								faturamentoGrupoOrigem, rotaSemAtualizacao)) {
//							System.out
//									.println("Imóvel em rota já gerada para referência!");
//						}// 1.3.2.2.2.2
						else {
							// efetivar a alteração da inscrição na tabela
							// IMOVEL
							atualizarImovel(imovel, resultadoAbaLocalidade,
									helperLocalidade);
						}
					}
				}
			}
		}
		// efetivar a alteração da inscrição na tabela IMOVEL
		atualizarImovel(imovel, resultadoAbaLocalidade, helperLocalidade);

		return retorno;
	}
	
	/**
	 * [SB0005] ï¿½ Preparar Alteraï¿½ï¿½o Inscriï¿½ï¿½o no Encerramento Faturamento
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 */
	private void prepararAlteracaoInscricaoEncerramentoFaturamento( Imovel imovel, Imovel imovelSemAtualizacao,
			ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade, Fachada fachada, ImovelAbaLocalidadeHelper helperLocalidade, Usuario usuario ){
		
		//[FS0038 Verificar Existï¿½ncia de Alteraï¿½ï¿½o de Inscriï¿½ï¿½o Pendente para o Imï¿½vel] 
		verificarExistenciaAlteracaoInscricaoPendenteImovel(imovel, fachada);

		//[FS0039] ï¿½ Verificar Duplicidade de Inscriï¿½ï¿½o
		verificarDuplicidadeInscricao( resultadoAbaLocalidade, helperLocalidade, fachada );
		
		ImovelInscricaoAlterada imovelInscricaoAlterada = new ImovelInscricaoAlterada();
		
		imovelInscricaoAlterada.setImovel(imovel);
		imovelInscricaoAlterada.setFaturamentoGrupo(null);
		//Dados da inscriï¿½ï¿½o Anterior
		imovelInscricaoAlterada.setLocalidadeAnterior(imovelSemAtualizacao.getLocalidade());
		imovelInscricaoAlterada.setSetorComercialAnterior(imovelSemAtualizacao.getSetorComercial());
		imovelInscricaoAlterada.setQuadraAnterior(imovelSemAtualizacao.getQuadra());
		imovelInscricaoAlterada.setQuadraFaceAnterior(
				imovelSemAtualizacao.getQuadraFace()!=null?imovelSemAtualizacao.getQuadraFace():null);
		imovelInscricaoAlterada.setLoteAnterior(imovelSemAtualizacao.getLote());
		imovelInscricaoAlterada.setSubLoteAnterior(imovelSemAtualizacao.getSubLote());
		//Dados da inscriï¿½ï¿½o Atual
		imovelInscricaoAlterada.setLocalidadeAtual(resultadoAbaLocalidade.getLocalidade());
		imovelInscricaoAlterada.setSetorComercialAtual(resultadoAbaLocalidade.getSetorComercial());
		imovelInscricaoAlterada.setQuadraAtual(resultadoAbaLocalidade.getQuadra());
		imovelInscricaoAlterada.setQuadraFaceAtual(
				resultadoAbaLocalidade.getQuadraFace()!=null?resultadoAbaLocalidade.getQuadraFace():null);
		imovelInscricaoAlterada.setLoteAtual(new Short(helperLocalidade.getLote()));
		imovelInscricaoAlterada.setSubLoteAtual(new Short(helperLocalidade.getSublote()));
		
		imovelInscricaoAlterada.setIndicadorAtualizado(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorAtualizacaoExcluida(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorImovelExcluido(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorErroAlteracao(null);
		
		if (this.getSistemaParametro().getIndicadorAlteracaoInscricaoImovel().toString().equals(""+ConstantesSistema.SIM)){
			
			imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.NAO);
		} else {
			
			imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.SIM);
		}
		
		imovelInscricaoAlterada.setUsuarioAlteracao(usuario);
		imovelInscricaoAlterada.setDataAlteracaoOnline(new Date());
		imovelInscricaoAlterada.setDataAlteracaoBatch(null);
		imovelInscricaoAlterada.setUltimaAlteracao(new Date());
		
		fachada.inserir(imovelInscricaoAlterada);
		
	}
	
	/**
	 * [FS0039] ï¿½ Verificar Duplicidade de Inscriï¿½ï¿½o
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 * @return
	 */
	private void verificarDuplicidadeInscricao( ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade,  
			ImovelAbaLocalidadeHelper helperLocalidade, Fachada fachada ) {
		
		ImovelInscricaoAlterada imovelInscricaoAlterada = new ImovelInscricaoAlterada();
		
		Integer idQuadraFace = null;
		if ( resultadoAbaLocalidade.getQuadraFace() != null && resultadoAbaLocalidade.getQuadraFace().getId() != null ) {
			idQuadraFace = resultadoAbaLocalidade.getQuadraFace().getId();
		}
		imovelInscricaoAlterada = fachada.verificarDuplicidadeImovelInscricaoAlterada(resultadoAbaLocalidade.getLocalidade().getId(), 
				resultadoAbaLocalidade.getSetorComercial().getId(), resultadoAbaLocalidade.getQuadra().getId(),
				idQuadraFace, new Integer(helperLocalidade.getLote()), new Integer(helperLocalidade.getSublote())
				);
		

		if ( imovelInscricaoAlterada != null && imovelInscricaoAlterada.getId() != null ) {
			String[] idImovelInscricaoAlterada = new String[1];
			idImovelInscricaoAlterada[0] = imovelInscricaoAlterada.getImovel().getId().toString();
			throw new ActionServletException("atencao.duplicidade_imovel_inscricao_alterada", 
					"atualizarImovelWizardAction.do?action=exibirAtualizarImovelLocalidadeAction",
					null,
					idImovelInscricaoAlterada);
		}
		
	}

	/**
	 * Atualiza Imovel
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 * @param imovel
	 */
	private void atualizarImovel(Imovel imovel,  ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade, ImovelAbaLocalidadeHelper helperLocalidade ){
		
		imovel.setLocalidade(resultadoAbaLocalidade.getLocalidade());
		imovel.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		imovel.setQuadra(resultadoAbaLocalidade.getQuadra());
		imovel.setQuadraFace(resultadoAbaLocalidade.getQuadraFace());
		imovel.setLote(new Short(helperLocalidade.getLote()).shortValue());
		imovel.setSubLote(new Short(helperLocalidade.getSublote()).shortValue());
	}

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 18/09/2010
	 * @param imovelAtualizar
	 * @param fachada
	 * @return
	 */
	private Imovel pesquisarImovel(Integer imovelAtualizar, Fachada fachada){
		
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovelAtualizar));
		
		Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
		
		return imovel;
	}
	
	/**
	 * [FS0038 Verificar Existï¿½ncia de Alteraï¿½ï¿½o de Inscriï¿½ï¿½o Pendente para o Imï¿½vel] 
	 * @author Arthur Carvalho
	 * @date 17/09/2010
	 * @param imovelSemAtualizacao
	 * @param fachada
	 */
	private void verificarExistenciaAlteracaoInscricaoPendenteImovel(Imovel imovelSemAtualizacao, Fachada fachada) {
	
		FiltroImovelInscricaoAlterada filtroImovelInscricaoAlterada = new FiltroImovelInscricaoAlterada();
		
		filtroImovelInscricaoAlterada.adicionarParametro(new ParametroSimples(
				FiltroImovelInscricaoAlterada.IMOVEL_ID, imovelSemAtualizacao.getId()));
		
		filtroImovelInscricaoAlterada.adicionarParametro(new ParametroSimples(
				FiltroImovelInscricaoAlterada.INDICADOR_ATUALIZADO, ConstantesSistema.NAO));
		
		filtroImovelInscricaoAlterada.adicionarParametro(new ParametroSimples(
				FiltroImovelInscricaoAlterada.INDICADOR_ALTERACAO_EXCLUIDA,ConstantesSistema.NAO));
		
		filtroImovelInscricaoAlterada.adicionarParametro(new ParametroNulo(
				FiltroImovelInscricaoAlterada.INDICADOR_ERRO_ALTERACAO));
		
		Collection<ImovelInscricaoAlterada> colecaoImovelInscricaoAlterada 
			= fachada.pesquisar(filtroImovelInscricaoAlterada,
								ImovelInscricaoAlterada.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoImovelInscricaoAlterada)){		
			for (ImovelInscricaoAlterada imovelInscricaoAlterada : colecaoImovelInscricaoAlterada) {
				
				imovelInscricaoAlterada.setIndicadorAtualizacaoExcluida(ConstantesSistema.SIM);
				imovelInscricaoAlterada.setUltimaAlteracao(new Date());
				
				fachada.atualizar(imovelInscricaoAlterada);
			}
		}
		
	}
	
	private ClienteImovel getClienteResponsavel(Collection<ClienteImovel> clientes) {
    	
    	for (ClienteImovel clienteImovel : clientes) {
    		if (clienteImovel.isClienteResponsavel() || clienteImovel.getIndicadorNomeConta().equals(ConstantesSistema.SIM)) {
    			return clienteImovel;
    		}
    	}
    	
    	return null;
    }
}
