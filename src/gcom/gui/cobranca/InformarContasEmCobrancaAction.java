package gcom.gui.cobranca;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarContasEmCobrancaAction extends GcomAction {

	private InformarContasEmCobrancaActionForm form;
	private boolean algumParametroInformado = false;
	private ComandoEmpresaCobrancaConta comando;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = mapping.findForward("telaSucesso");
		form = (InformarContasEmCobrancaActionForm) actionForm;

		getFachada().gerarComandoCobrancaEmpresa(montarHelper(getUsuarioLogado(request)));

		montarPaginaSucesso(request, "Geração do Comando de cobrança enviado para batch com sucesso. ", "Informar outro Comando de Cobrança de Conta", "exibirInformarContasEmCobrancaAction.do?menu=sim");

		return retorno;
	}

	private void montarDatasCiclo() {
		String inicio = form.getDataInicioCiclo();
		String quantidadeDiasCiclo = form.getQuantidadeDiasCiclo();
		if (inicio != null && !inicio.trim().equals("")) {
			Date inicioFormatado = Util.converteStringParaDate(inicio);

			if (quantidadeDiasCiclo != null && !quantidadeDiasCiclo.trim().equals("")) {
				Integer quantidadeDias = new Integer(quantidadeDiasCiclo);
				Date dataFimCicloFormatada = Util.adicionarNumeroDiasDeUmaData(inicioFormatado, quantidadeDias);
				comando.setDataFimCiclo(dataFimCicloFormatada);
			}

			comando.setDataInicioCiclo(inicioFormatado);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void pesquisarEmpresa() {
		String id = form.getIdEmpresa();
		if (id != null && !id.trim().equals("")) {
			Filtro filtro = new FiltroEmpresa();
			filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, id));

			Collection colecao = getFachada().pesquisar(filtro, Empresa.class.getName());
			if (colecao != null && !colecao.isEmpty()) {
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecao);

				if (empresa.getIndicadorEmpresaContratadaCobranca() != null && empresa.getIndicadorEmpresaContratadaCobranca().intValue() == Empresa.INDICADOR_EMPRESA_COBRANCA.intValue()) {
					comando.setEmpresa(empresa);
				} else {
					throw new ActionServletException("atencao.empresa_nao_cobranca");
				}
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
			}
		}
	}

	private ComandoEmpresaCobrancaContaHelper montarHelper(Usuario usuario) {
		comando = new ComandoEmpresaCobrancaConta();

		pesquisarImovel();
		pesquisarCliente();
		pesquisarCobrancaSituacao();
		pesquisarServicoTipo();
		montarIndicadoresCategoria();
		pesquisarLocalidadeInicial();
		pesquisarLocalidadeFinal();
		pesquisarSetorComercialInicial();
		pesquisarSetorComercialFinal();
		pesquisarQuadraInicial();
		pesquisarQuadraFinal();
		montarReferencias();
		montarDatasVencimento();
		montarValores();
		montarQuantidadeContas();
		montarQuantidadeDiasVencimento();
		montarIndicadorCobrancaTelemarketing();
		montarQuantidadeMaximaClientes();
		montarIndicadorDebitoPreterito();
		pesquisarEmpresa();
		montarDatasCiclo();
		montarIndicadorPossuiCpfCnpj();

		comando.setIndicadorGeracaoTxt(ConstantesSistema.NAO.intValue());

		if (algumParametroInformado) {
			ComandoEmpresaCobrancaContaHelper helper = new ComandoEmpresaCobrancaContaHelper();
			helper.setComando(comando);
			helper.setIdsUnidadeNegocio(montarListaIds(form.getIdsUnidadeNegocio()));
			helper.setIdsGerenciaRegional(montarListaIds(form.getIdsGerenciaRegional()));
			helper.setIdsImovelPerfil(montarListaIds(form.getIdsImovelPerfil()));
			helper.setIdsLigacaoAguaSituacao(montarListaIds(form.getIdsLigacaoAguaSituacao()));
			helper.setUsuario(usuario);
			return helper;
		} else {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
	}

	private void montarIndicadorPossuiCpfCnpj() {
		if (form.getIndicadorPossuiCpfCnpj() != null && !form.getIndicadorPossuiCpfCnpj().trim().equals("")) {
			comando.setIndicadorPossuiCpfCnpj(new Short(form.getIndicadorPossuiCpfCnpj()));
		}
	}

	private void montarIndicadorDebitoPreterito() {
		if (form.getIndicadorGerarComDebitoPreterito() != null && !form.getIndicadorGerarComDebitoPreterito().trim().equals("")) {
			comando.setIndicadorGerarComDebitoPreterito(new Short(form.getIndicadorGerarComDebitoPreterito()));
		}
	}

	private void montarQuantidadeMaximaClientes() {
		if (form.getQuantidadeMaximaClientes() != null && !form.getQuantidadeMaximaClientes().trim().equals("") && Integer.parseInt(form.getQuantidadeMaximaClientes()) > 0) {
			comando.setQtdMaximaClientes(new Integer(form.getQuantidadeMaximaClientes()));
		}
	}

	private void montarIndicadorCobrancaTelemarketing() {
		if (form.getIndicadorCobrancaTelemarketing() != null) {
			comando.setIndicadorCobrancaTelemarketing(new Short(form.getIndicadorCobrancaTelemarketing()));
		} 
	}

	private void montarQuantidadeDiasVencimento() {
		if (form.getQuantidadeDiasVencimento() != null && !form.getQuantidadeDiasVencimento().equals("")) {
			algumParametroInformado = true;
			comando.setQtdDiasVencimento(new Integer(form.getQuantidadeDiasVencimento()));
		}
	}

	private void montarQuantidadeContas() {
		if (form.getQuantidadeContasInicial() != null && !form.getQuantidadeContasInicial().equals("")) {
			algumParametroInformado = true;
			comando.setQtdContasInicial(new Integer(form.getQuantidadeContasInicial()));
		} else {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", "Quantidade de Contas Final");
		}

		if (form.getQuantidadeContasFinal() != null && !form.getQuantidadeContasFinal().equals("")) {
			algumParametroInformado = true;
			comando.setQtdContasFinal(new Integer(form.getQuantidadeContasFinal()));
		} else {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", "Quantidade de Contas Inicial");
		}

		if (comando.getQtdContasFinal().compareTo(comando.getQtdContasInicial()) < 0) {
			throw new ActionServletException("atencao.quantidade.contas_final.menor.quantidade_inicial");
		}
	}

	private void pesquisarImovel() {
		String id = form.getIdImovel();
		if (id != null && !id.equals("")) {
			Imovel imovel = getFachada().pesquisarImovelDigitado(new Integer(id));
			if (imovel != null) {
				algumParametroInformado = true;
				comando.setImovel(imovel);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Imóvel");
			}
		}
	}

	private void pesquisarCliente() {
		String id = form.getIdCliente();
		if (id != null && !id.equals("")) {
			Cliente cliente = getFachada().pesquisarClienteDigitado(new Integer(id));
			if (cliente != null) {
				algumParametroInformado = true;
				comando.setCliente(cliente);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
			}
		}
	}

	private void pesquisarCobrancaSituacao() {
		Integer id = getFachada().pesquisarCobrancaSituacao(CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);
		if (id != null) {
			comando.setCobrancaSituacao(new CobrancaSituacao(id));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void pesquisarServicoTipo() {
		String id = form.getIdServicoTipo();
		if (id != null && !id.trim().equals("")) {
			Filtro filtro = new FiltroServicoTipo();
			filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, id));

			Collection colecao = getFachada().pesquisar(filtro, ServicoTipo.class.getName());
			if (colecao != null && !colecao.isEmpty()) {
				comando.setServicoTipo((ServicoTipo) Util.retonarObjetoDeColecao(colecao));
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Tipo de Serviço");
			}
		}
	}

	private void montarIndicadoresCategoria() {
		String[] ids = form.getIdsCategoria();
		comando.setIndicadorResidencial(ConstantesSistema.NAO.intValue());
		comando.setIndicadorComercial(ConstantesSistema.NAO.intValue());
		comando.setIndicadorIndustrial(ConstantesSistema.NAO.intValue());
		comando.setIndicadorPublico(ConstantesSistema.NAO.intValue());
		
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				if (ids[i].equals(Categoria.COMERCIAL.toString())) {
					comando.setIndicadorComercial(ConstantesSistema.SIM.intValue());
				} else if (ids[i].equals(Categoria.INDUSTRIAL.toString())) {
					comando.setIndicadorIndustrial(ConstantesSistema.SIM.intValue());
				} else if (ids[i].equals(Categoria.RESIDENCIAL.toString())) {
					comando.setIndicadorResidencial(ConstantesSistema.SIM.intValue());
				} else if (ids[i].equals(Categoria.PUBLICO.toString())) {
					comando.setIndicadorPublico(ConstantesSistema.SIM.intValue());
				}
			}
		}
	}

	private List<Integer> montarListaIds(String[] ids) {
		List<Integer> lista = new ArrayList<Integer>();
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				Integer id = new Integer(ids[i]);
				if (id == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					lista = null;
					break;
				}
				lista.add(id);
			}
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private void pesquisarLocalidadeInicial() {
		String id = form.getIdLocalidadeOrigem();
		if (id != null && !id.equals("")) {
			Filtro filtro = new FiltroLocalidade();
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, id));
			Collection<Localidade> colecao = this.getFachada().pesquisar(filtro, Localidade.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				algumParametroInformado = true;
				comando.setLocalidadeInicial((Localidade) colecao.iterator().next());
			} else {
				throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarLocalidadeFinal() {
		String id = form.getIdLocalidadeDestino();
		if (id != null && !id.equals("")) {
			Filtro filtro = new FiltroLocalidade();
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, id));
			Collection<Localidade> colecao = this.getFachada().pesquisar(filtro, Localidade.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				algumParametroInformado = true;
				comando.setLocalidadeFinal((Localidade) colecao.iterator().next());
			} else {
				throw new ActionServletException("atencao.pesquisa.localidade_final_inexistente");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarSetorComercialInicial() {
		String codigo = form.getCodigoSetorComercialOrigem();
		if (codigo != null && !codigo.equals("")) {
			Filtro filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidadeOrigem()));
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigo));
			Collection<SetorComercial> colecao = this.getFachada().pesquisar(filtro, SetorComercial.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				algumParametroInformado = true;
				SetorComercial setor = colecao.iterator().next();
				form.setIdSetorComercialOrigem(setor.getId().toString());
				comando.setCodigoSetorComercialInicial(setor.getCodigo());
			} else {
				throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarSetorComercialFinal() {
		String codigo = form.getCodigoSetorComercialDestino();
		if (codigo != null && !codigo.equals("")) {
			Filtro filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidadeDestino()));
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigo));
			Collection<SetorComercial> colecao = this.getFachada().pesquisar(filtro, SetorComercial.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				algumParametroInformado = true;
				SetorComercial setor = colecao.iterator().next();
				form.setIdSetorComercialDestino(setor.getId().toString());
				comando.setCodigoSetorComercialFinal(setor.getCodigo());
			} else {
				throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarQuadraInicial() {
		String numero = form.getCodigoQuadraInicial();
		if (numero != null && !numero.equals("")) {
			Filtro filtro = new FiltroQuadra();
			filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getIdSetorComercialOrigem()));
			filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, numero));
			Collection<Quadra> colecao = this.getFachada().pesquisar(filtro, Quadra.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				algumParametroInformado = true;
				comando.setNumeroQuadraInicial(new Integer(numero));
			} else {
				throw new ActionServletException("atencao.pesquisa.quadra_inicial_inexistente");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarQuadraFinal() {
		String numero = form.getCodigoQuadraFinal();
		if (numero != null && !numero.equals("")) {
			Filtro filtro = new FiltroQuadra();
			filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getIdSetorComercialDestino()));
			filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, numero));
			Collection<Quadra> colecao = this.getFachada().pesquisar(filtro, Quadra.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				algumParametroInformado = true;
				comando.setNumeroQuadraFinal(new Integer(form.getCodigoQuadraFinal()));
			} else {
				throw new ActionServletException("atencao.pesquisa.quadra_final_inexistente");
			}
		}
	}

	private void montarReferencias() {
		String inicio = form.getReferenciaInicial();
		String fim = form.getReferenciaFinal();

		Integer inicioFormatado = null;
		Integer finalFormatado = null;

		if (inicio != null && !inicio.trim().equals("")) {
			inicioFormatado = Util.formatarMesAnoComBarraParaAnoMes(inicio);
			if (fim != null && !fim.equals("")) {
				finalFormatado = Util.formatarMesAnoComBarraParaAnoMes(fim);
			} else {
				SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
				finalFormatado = sistemaParametro.getAnoMesArrecadacao();
			}

			if (inicioFormatado.compareTo(finalFormatado) > 0) {
				throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
			}
		} else {
			inicioFormatado = 198001;
			if (fim == null || fim.equals("")) {
				SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
				finalFormatado = sistemaParametro.getAnoMesArrecadacao();
			} else {
				finalFormatado = Util.formatarMesAnoComBarraParaAnoMes(fim);
			}

			if (inicioFormatado.compareTo(finalFormatado) > 0) {
				throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
			}
		}
		comando.setReferenciaContaInicial(inicioFormatado);
		comando.setReferenciaContaFinal(finalFormatado);
	}

	private void montarDatasVencimento() {
		String inicio = form.getDataVencimentoInicial();
		String fim = form.getDataVencimentoFinal();
		if (inicio != null && !inicio.trim().equals("")) {
			Date inicioFormatado = Util.converteStringParaDate(inicio);
			Date fimFormatado = Util.converteStringParaDate(fim);

			if (inicioFormatado.compareTo(fimFormatado) > 0) {
				throw new ActionServletException("atencao.data.intervalo.invalido");
			}

			comando.setDataVencimentoContaInicial(inicioFormatado);
			comando.setDataVencimentoContaFinal(fimFormatado);
		}
	}

	private void montarValores() {
		String maximo = form.getValorMaximo();
		String minimo = form.getValorMinimo();
		if (minimo != null && !minimo.trim().equals("")) {
			BigDecimal maximoFormatado = Util.formatarMoedaRealparaBigDecimal(maximo);
			BigDecimal minimoFormatado = Util.formatarMoedaRealparaBigDecimal(minimo);

			if (minimo.length() > 14) {
				throw new ActionServletException("atencao.tamanho.valor.minimo.invalido");
			}

			if (maximo.length() > 14) {
				throw new ActionServletException("atencao.tamanho.valor.maximo.invalido");
			}

			if (minimoFormatado.compareTo(maximoFormatado) > 0) {
				throw new ActionServletException("atencao.situacao.valor.servico.invalida");
			}

			comando.setValorMinimoConta(minimoFormatado);
			comando.setValorMaximoConta(maximoFormatado);
		}
	}
}
