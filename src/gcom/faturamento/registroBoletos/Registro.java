package gcom.faturamento.registroBoletos;

import gcom.api.GsanApi;
import gcom.arrecadacao.ArrecadadorContratoConvenio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.bean.FichaCompensacaoDTO;
import gcom.faturamento.bean.PagadorDTO;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.SegurancaParametro.NOME_PARAMETRO_SEGURANCA;

public abstract class Registro {
	
	protected FichaCompensacaoDTO montaBoletoBB(ArrecadadorContratoConvenio convenio) throws Exception {
		
		FichaCompensacaoDTO fichaCompensacaoDTO = new FichaCompensacaoDTO();
		
		fichaCompensacaoDTO.setCodigoModalidade((short) 1);
		fichaCompensacaoDTO.setCodigoAceite("A");
		fichaCompensacaoDTO.setIndicadorPermissaoRecebimentoParcial("N");
//		fichaCOmpensacaoDTO.setIndicadorPix("N");
		fichaCompensacaoDTO.setNumeroConvenio(convenio.getConvenio());
		fichaCompensacaoDTO.setNumeroCarteira(convenio.getNumeroCarteira());
		fichaCompensacaoDTO.setNumeroVariacaoCarteira(convenio.getNumeroVariacaoCarteira());
		fichaCompensacaoDTO.setNumeroConvenio(convenio.getConvenio());
		fichaCompensacaoDTO.setCodigoTipoTitulo(convenio.getCodigoTipoTitulo());
		
		return fichaCompensacaoDTO;
	};	

	
	public abstract FichaCompensacaoDTO salvarFichaDeCompensacao(ArrecadadorContratoConvenio convenio, IRepositorioFaturamento repositorioFaturamento) throws Exception;
	
	public void registroFichaTeste(FichaCompensacaoDTO ficha) throws Exception {
		String url = retornaUrlApi(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_REGISTRO_BB_TESTE);

		GsanApi api = new GsanApi(url);
		
		api.invoke(ficha);
	}
	
	public void registroFichaBB(FichaCompensacaoDTO ficha) throws Exception {
		String url = retornaUrlApi(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_API_REGISTRAR_BOLETO_BB);
		
		GsanApi api = new GsanApi(url);
		
		api.invoke(ficha);
	}

	private String retornaUrlApi(NOME_PARAMETRO_SEGURANCA urlApi) {
		return Fachada.getInstancia().getSegurancaParametro(urlApi.toString());
	}
	

	protected PagadorDTO retornaPagador(Cliente cliente, Imovel imovel) {
		PagadorDTO pagador = new PagadorDTO(); // Identifica o pagador do boleto.
		
		Municipio municipio = imovel.getLogradouroBairro().getBairro().getMunicipio();
		String nomeCidade = municipio.getNome();
		String siglaUF = municipio.getUnidadeFederacao().getSigla();
		
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
		pagador.setCidade(nomeCidade);
		pagador.setBairro(imovel.getNomeBairro());
		pagador.setUf(siglaUF);
		pagador.setCep(imovel.getCodigoCep());
		
		return pagador;
	}
}
