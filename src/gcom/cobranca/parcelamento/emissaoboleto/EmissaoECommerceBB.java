package gcom.cobranca.parcelamento.emissaoboleto;

import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class EmissaoECommerceBB extends Emissao{

	public EmissaoECommerceBB(Emissao emissao) {
		super(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String emitirBoleto(Integer idParcelamento, Integer idImovel, Fachada fachada, boolean primeiraVia) {
		
			FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
			filtroParcelamento.adicionarParametro(new ParametroSimples(FiltroParcelamento.ID, idParcelamento));
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Parcelamento parcelamento = (Parcelamento) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroParcelamento, Parcelamento.class.getName()));
			// Primeira via
			String linkBoletoBB = fachada.montarLinkBB(parcelamento.getImovel().getId(),
					parcelamento.getId(), parcelamento.getCliente(), parcelamento.getValorEntrada(), primeiraVia);

			return linkBoletoBB;
	}

}
