package gcom.faturamento.registroBoletos;

import gcom.arrecadacao.ArrecadadorContratoConvenio;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.bean.FichaCompensacaoDTO;
import gcom.faturamento.bean.PagadorDTO;
import gcom.util.Util;

public class RegistroEntradaParcelamentoService extends Registro {

	private GuiaPagamento guia;
	private Imovel imovel;
	private Cliente cliente;
	private String nossoNumero;

	public RegistroEntradaParcelamentoService(GuiaPagamento guia, Imovel imovel,
			Cliente cliente, String nossoNumero) {
		this.guia = guia;
		this.imovel = imovel;
		this.cliente = cliente;
		this.nossoNumero = nossoNumero;
	}

	@Override
	public FichaCompensacaoDTO salvarFichaDeCompensacao(ArrecadadorContratoConvenio convenio,
			IRepositorioFaturamento repositorioFaturamento) throws Exception {

		FichaCompensacaoDTO ficha = montaBoletoBB(convenio);

		ficha.setDataEmissao(Util.formatarDataComPontoDDMMAAAA(guia.getDataEmissao()).toString());
		ficha.setDataVencimento(Util.formatarDataComPontoDDMMAAAA(guia.getDataVencimento()).toString());
		ficha.setValorOriginal(guia.getValorDebito().doubleValue());
		ficha.setNumeroTituloCliente(nossoNumero.toString());
		PagadorDTO pagador = retornaPagador(cliente, imovel);
		ficha.setPagador(pagador);

		repositorioFaturamento.inserirFichaCompensacaoGuiaPagamento(ficha.getNumeroConvenio(),
				ficha.getNumeroCarteira(), ficha.getNumeroVariacaoCarteira(), ficha.getCodigoModalidade(),
				ficha.getDataEmissao(), ficha.getDataVencimento(), ficha.getValorOriginal(), ficha.getCodigoAceite(),
				ficha.getCodigoTipoTitulo(), ficha.getIndicadorPermissaoRecebimentoParcial(),
				ficha.getNumeroTituloCliente(), imovel.getId(), cliente.getId(), guia.getId());

		return ficha;
	}

}
