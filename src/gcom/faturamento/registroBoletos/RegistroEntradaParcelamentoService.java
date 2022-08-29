package gcom.faturamento.registroBoletos;

import gcom.arrecadacao.ArrecadadorContratoConvenio;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.IControladorFaturamento;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.bean.FichaCompensacaoDTO;
import gcom.faturamento.bean.PagadorDTO;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class RegistroEntradaParcelamentoService extends Registro {

	public RegistroEntradaParcelamentoService(IRepositorioFaturamento repositorioFaturamento,
			IControladorFaturamento controladorFaturamento) {
		super(repositorioFaturamento, controladorFaturamento);
	}

	@Override
	public FichaCompensacaoDTO montaBoletoBB(Integer idDocumento, Integer tipoDocumento,
			ArrecadadorContratoConvenio convenio) throws Exception {
		
		GuiaPagamento guiaPagamento = pesquisarGuiaPagamentoParcelamento(idDocumento);
		
		Short codigoModalidade = 1;
		String codigoAceite = "A";
		String indicadorPermissaoRecebimentoParcial = "N";
		Integer idConv = convenio.getConvenio();
		Integer numeroCarteira = convenio.getNumeroCarteira();
		Integer numeroVariacaoCarteira = convenio.getNumeroVariacaoCarteira();
		Short codigoTipoTitulo = convenio.getCodigoTipoTitulo();
		Imovel imovel = repositorioFaturamento.pesquisarImovel(guiaPagamento.getImovel().getId());
		Cliente cliente = repositorioFaturamento.clienteFichaCompensacao(imovel.getId());
		String nomeMunicipio = repositorioFaturamento.municipio(imovel.getIdLocalidade()).getNome();
		String dataEmissao = (guiaPagamento.getDataEmissao()).toString();
		String dataVencimento = (guiaPagamento.getDataVencimento()).toString();
		Double valorOriginal = guiaPagamento.getValorDebito().doubleValue();
		StringBuilder nossoNumero = controladorFaturamento.obterNossoNumeroFichaCompensacao(tipoDocumento.toString(), idDocumento.toString(), idConv);
		String numeroTituloCliente = nossoNumero.toString();

		PagadorDTO pagador = retornaPagador(cliente, imovel, nomeMunicipio);

		return new FichaCompensacaoDTO(idConv, numeroCarteira, numeroVariacaoCarteira, codigoModalidade, dataEmissao,
				dataVencimento, valorOriginal, codigoAceite, codigoTipoTitulo, indicadorPermissaoRecebimentoParcial,
				numeroTituloCliente, pagador);
	}
	
	@SuppressWarnings("unchecked")
	private GuiaPagamento pesquisarGuiaPagamentoParcelamento(Integer idParcelamento) {
		FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
		filtroGuiaPagamento
				.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.PARCELAMENTO_ID, idParcelamento));
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
		GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(
				Fachada.getInstancia().pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName()));

		return guiaPagamento;
	}

	@Override
	public void salvarFichaBB(FichaCompensacaoDTO ficha, Integer idImovel, Integer idCliente, Integer idDocumento)
			throws Exception {
		repositorioFaturamento.inserirFichaCompensacaoGuiaPagamento(ficha.getNumeroConvenio(), ficha.getNumeroCarteira(), ficha.getNumeroVariacaoCarteira(),
				ficha.getCodigoModalidade(), ficha.getDataEmissao(), ficha.getDataVencimento(), ficha.getValorOriginal(), ficha.getCodigoAceite(), ficha.getCodigoTipoTitulo(),
				ficha.getIndicadorPermissaoRecebimentoParcial(), ficha.getNumeroTituloCliente(), idImovel, idCliente, idDocumento);
		
	}

}
