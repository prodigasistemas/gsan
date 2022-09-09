package gcom.faturamento.registroBoletos;

import gcom.arrecadacao.ArrecadadorContratoConvenio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.bean.FichaCompensacaoDTO;
import gcom.faturamento.bean.PagadorDTO;
import gcom.faturamento.conta.Conta;
import gcom.util.Util;

public class RegistroContaService extends Registro {

	private Conta conta;
	private Imovel imovel;
	private Cliente cliente;
	private String nossoNumero;

	public RegistroContaService(Conta conta, Imovel imovel, Cliente cliente, String nossoNumero) {
		this.conta = conta;
		this.imovel = imovel;
		this.cliente = cliente;
		this.nossoNumero = nossoNumero;
	}

	@Override
	public FichaCompensacaoDTO salvarFichaDeCompensacao(ArrecadadorContratoConvenio convenio,
			IRepositorioFaturamento repositorioFaturamento) throws Exception {

		FichaCompensacaoDTO ficha = montaBoletoBB(convenio);

		ficha.setDataEmissao(Util.formatarDataComPontoDDMMAAAA(conta.getDataEmissao()).toString());
		ficha.setDataVencimento(Util.formatarDataComPontoDDMMAAAA(conta.getDataVencimentoConta()).toString());
		ficha.setValorOriginal(Double.valueOf(conta.getValorTotalConta()));
		ficha.setNumeroTituloCliente(nossoNumero.toString());
		PagadorDTO pagador = retornaPagador(cliente, imovel);
		ficha.setPagador(pagador);

		repositorioFaturamento.inserirFichaCompensacao(ficha.getNumeroConvenio(), ficha.getNumeroCarteira(),
				ficha.getNumeroVariacaoCarteira(), ficha.getCodigoModalidade(), ficha.getDataEmissao(),
				ficha.getDataVencimento(), ficha.getValorOriginal(), ficha.getCodigoAceite(),
				ficha.getCodigoTipoTitulo(), ficha.getIndicadorPermissaoRecebimentoParcial(),
				ficha.getNumeroTituloCliente(), imovel.getId(), cliente.getId(), conta.getId());

		return ficha;
	}

}
