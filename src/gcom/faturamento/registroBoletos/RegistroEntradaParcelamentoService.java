package gcom.faturamento.registroBoletos;

import gcom.arrecadacao.ArrecadadorContratoConvenio;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.IControladorFaturamento;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.bean.FichaCompensacaoDTO;
import gcom.faturamento.bean.PagadorDTO;
import gcom.util.Util;

public class RegistroEntradaParcelamentoService extends Registro {

	private GuiaPagamento guiaPagamento;

	public RegistroEntradaParcelamentoService(IRepositorioFaturamento repositorioFaturamento,
			IControladorFaturamento controladorFaturamento, GuiaPagamento guiaPagamento) {
		super(repositorioFaturamento, controladorFaturamento);
		this.guiaPagamento = guiaPagamento;
	}

	@Override
	public FichaCompensacaoDTO montaBoletoBB(Integer idDocumento, Integer tipoDocumento,
			ArrecadadorContratoConvenio convenio) throws Exception {
				
		Short codigoModalidade = 1;
		String codigoAceite = "A";
		String indicadorPermissaoRecebimentoParcial = "N";
//		String indicadorPix = "N";
		Integer idConv = convenio.getConvenio();
		Integer numeroCarteira = convenio.getNumeroCarteira();
		Integer numeroVariacaoCarteira = convenio.getNumeroVariacaoCarteira();
		Short codigoTipoTitulo = convenio.getCodigoTipoTitulo();
		Imovel imovel = repositorioFaturamento.pesquisarImovel(guiaPagamento.getImovel().getId());
		Cliente cliente = repositorioFaturamento.clienteFichaCompensacao(imovel.getId());
		String nomeMunicipio = repositorioFaturamento.municipio(imovel.getIdLocalidade()).getNome();
		String dataEmissao = Util.formatarDataComPontoDDMMAAAA(guiaPagamento.getDataEmissao()).toString();
		String dataVencimento = Util.formatarDataComPontoDDMMAAAA(guiaPagamento.getDataVencimento()).toString();
		Double valorOriginal = guiaPagamento.getValorDebito().doubleValue();
		StringBuilder nossoNumero = controladorFaturamento.obterNossoNumeroFichaCompensacao(tipoDocumento.toString(), idDocumento.toString(), idConv);
		String numeroTituloCliente = nossoNumero.toString();

		PagadorDTO pagador = retornaPagador(cliente, imovel, nomeMunicipio);

		return new FichaCompensacaoDTO(idConv, numeroCarteira, numeroVariacaoCarteira, codigoModalidade, dataEmissao,
				dataVencimento, valorOriginal, codigoAceite, codigoTipoTitulo, indicadorPermissaoRecebimentoParcial,
				numeroTituloCliente, pagador);//, indicadorPix);
	}

	@Override
	public void salvarFichaBB(FichaCompensacaoDTO ficha, Integer idImovel, Integer idCliente, Integer idDocumento)
			throws Exception {
		repositorioFaturamento.inserirFichaCompensacaoGuiaPagamento(ficha.getNumeroConvenio(), ficha.getNumeroCarteira(), ficha.getNumeroVariacaoCarteira(),
				ficha.getCodigoModalidade(), ficha.getDataEmissao(), ficha.getDataVencimento(), ficha.getValorOriginal(), ficha.getCodigoAceite(), ficha.getCodigoTipoTitulo(),
				ficha.getIndicadorPermissaoRecebimentoParcial(), ficha.getNumeroTituloCliente(), idImovel, idCliente, idDocumento);
		
	}

}
