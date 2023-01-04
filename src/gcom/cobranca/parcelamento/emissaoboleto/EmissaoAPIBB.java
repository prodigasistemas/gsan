package gcom.cobranca.parcelamento.emissaoboleto;

import gcom.fachada.Fachada;

public class EmissaoAPIBB extends Emissao {

	public EmissaoAPIBB(Emissao proxima) {
		super(proxima);
	}

	@Override
	public String emitirBoleto(Integer idParcelamento, Integer idImovel, Fachada fachada, boolean primeiraVia) {
		try {
			return fachada.registrarEntradaParcelamento(idParcelamento, idImovel);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO AO REGISTRAR VIA API IMOVEL : " + idImovel + " " + e.getMessage());
		}
		
		return proxima.emitirBoleto(idParcelamento, idImovel, fachada, primeiraVia);	
	}

}
