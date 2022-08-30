package gcom.faturamento.registroBoletos;

import gcom.arrecadacao.ArrecadadorContratoConvenio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.IControladorFaturamento;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.bean.FichaCompensacaoDTO;
import gcom.faturamento.bean.PagadorDTO;
import gcom.faturamento.conta.Conta;
import gcom.util.Util;

public class RegistroContaService extends Registro {

	public RegistroContaService(IRepositorioFaturamento repositorioFaturamento,
			IControladorFaturamento controladorFaturamento) {
		super(repositorioFaturamento, controladorFaturamento);
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
		Conta conta = repositorioFaturamento.contaFichaCompensacao(idDocumento);
		Imovel imovel = conta.getImovel();
		Cliente cliente = repositorioFaturamento.clienteFichaCompensacao(imovel.getId());
		String nomeMunicipio = repositorioFaturamento.municipio(imovel.getIdLocalidade()).getNome();
		String dataEmissao = Util.formatarDataComPontoDDMMAAAA(conta.getDataEmissao()).toString();
//		String dataVencimento = Util.formatarDataComPontoDDMMAAAA(conta.getDataVencimentoConta()).toString();
		String dataVencimento = "20.09.2022"; 
		Double valorOriginal = Double.valueOf(conta.getValorTotalConta());
		StringBuilder nossoNumero = controladorFaturamento.obterNossoNumeroFichaCompensacao(tipoDocumento.toString(),
				conta.getId().toString(), idConv);
		String numeroTituloCliente = nossoNumero.toString();

		PagadorDTO pagador = retornaPagador(cliente, imovel, nomeMunicipio);

		return new FichaCompensacaoDTO(idConv, numeroCarteira, numeroVariacaoCarteira, codigoModalidade, dataEmissao,
				dataVencimento, valorOriginal, codigoAceite, codigoTipoTitulo, indicadorPermissaoRecebimentoParcial,
				numeroTituloCliente, pagador);//, indicadorPix);
	}

	@Override
	public void salvarFichaBB(FichaCompensacaoDTO ficha, Integer idImovel, Integer idCliente, Integer idDocumento)
			throws Exception {
		repositorioFaturamento.inserirFichaCompensacao(ficha.getNumeroConvenio(), ficha.getNumeroCarteira(), ficha.getNumeroVariacaoCarteira(),
				ficha.getCodigoModalidade(), ficha.getDataEmissao(), ficha.getDataVencimento(), ficha.getValorOriginal(), ficha.getCodigoAceite(), ficha.getCodigoTipoTitulo(),
				ficha.getIndicadorPermissaoRecebimentoParcial(), ficha.getNumeroTituloCliente(), idImovel, idCliente, idDocumento);
		
	}


}
