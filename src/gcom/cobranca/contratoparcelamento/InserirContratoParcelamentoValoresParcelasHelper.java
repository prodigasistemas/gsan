package gcom.cobranca.contratoparcelamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class InserirContratoParcelamentoValoresParcelasHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ContratoParcelamento contratoParcelamento;
	
	private BigDecimal valorParcelado;
	
	private List<PrestacaoContratoParcelamento> listaDeParcelas;
	
	private List<Float> listaValoresDistintos;
	

	public ContratoParcelamento getContratoParcelamento() {
		return contratoParcelamento;
	}

	public void setContratoParcelamento(ContratoParcelamento contratoParcelamento) {
		this.contratoParcelamento = contratoParcelamento;
	}

	public List<PrestacaoContratoParcelamento> getListaDeParcelas() {
		return listaDeParcelas;
	}

	public void setListaDeParcelas(
			List<PrestacaoContratoParcelamento> listaDeParcelas) {
		this.listaDeParcelas = listaDeParcelas;
	}

	public List<Float> getListaValoresDistintos() {
		return listaValoresDistintos;
	}

	public void setListaValoresDistintos(List<Float> listaValoresDistintos) {
		this.listaValoresDistintos = listaValoresDistintos;
	}

	public BigDecimal getValorParcelado() {
		return valorParcelado;
	}

	public void setValorParcelado(BigDecimal valorParcelado) {
		this.valorParcelado = valorParcelado;
	}
	
	
}
