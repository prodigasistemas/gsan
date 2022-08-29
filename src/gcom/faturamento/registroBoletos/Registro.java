package gcom.faturamento.registroBoletos;

import gcom.api.GsanApi;
import gcom.arrecadacao.ArrecadadorContratoConvenio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.IControladorFaturamento;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.bean.FichaCompensacaoDTO;
import gcom.faturamento.bean.PagadorDTO;
import gcom.seguranca.SegurancaParametro;

public abstract class Registro {

	protected IRepositorioFaturamento repositorioFaturamento;
	protected IControladorFaturamento controladorFaturamento;

	public Registro(IRepositorioFaturamento repositorioFaturamento, IControladorFaturamento controladorFaturamento) {
		this.repositorioFaturamento = repositorioFaturamento;
		this.controladorFaturamento = controladorFaturamento;
	}

	abstract FichaCompensacaoDTO montaBoletoBB(Integer idDocumento, Integer tipoDocumento,
			ArrecadadorContratoConvenio convenio) throws Exception;	

	
	abstract void salvarFichaBB(FichaCompensacaoDTO ficha, Integer idImovel, Integer idCliente, Integer idDocumento) throws Exception;

	public void registroFichaBB(FichaCompensacaoDTO ficha) throws Exception {
		String url = Fachada.getInstancia().getSegurancaParametro(
				SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_API_REGISTRAR_BOLETO_BB.toString());

		GsanApi api = new GsanApi(url);
		
		api.invoke(ficha);
	}

	protected PagadorDTO retornaPagador(Cliente cliente, Imovel imovel, String nomeMunicipio) {
		PagadorDTO pagador = new PagadorDTO(); // Identifica o pagador do boleto.
		if (cliente.getCpf() != null) {
			pagador.setTipoInscricao((short) 1);
			pagador.setNumeroInscricao(cliente.getCpf());
		} else {
			pagador.setTipoInscricao((short) 2);
			pagador.setNumeroInscricao(cliente.getCnpj());
		}
		if (cliente.getNome().length() > 30) {
			pagador.setNome(cliente.getNome().substring(0, 30));
		} else {
			pagador.setNome(cliente.getNome());
		}
		if (imovel.getEnderecoFormatado().length() > 30) {
			pagador.setEndereco(imovel.getEnderecoFormatado().substring(0, 30));
		} else {
			pagador.setEndereco(imovel.getEnderecoFormatado());
		}
		pagador.setCidade(nomeMunicipio);
		pagador.setBairro(imovel.getNomeBairro());
		pagador.setUf("PA");
		pagador.setCep(imovel.getCodigoCep());
		
		return pagador;
	}
}
