package gcom.cobranca.parcelamento.emissaoboleto;

import gcom.fachada.Fachada;

public abstract class Emissao {
	
	protected Emissao proxima;

	public Emissao(Emissao proxima) {
		this.proxima = proxima;
	}
	
	public abstract String emitirBoleto(Integer idParcelamento, Integer idImovel, Fachada fachada);
	
}
