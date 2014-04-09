package gcom.micromedicao.bean;

/**
 * Coleção dos dados do Consumo Historico da Medicao Individualizada
 * @author Rafael Santos
 * @since 19/01/2006
 *
 */
public class ConsultarHistoricoMedicaoIndividualizadaHelper {

	private String matriculaImovel;
	private String nomeClienteUsuario;
	private String tipoConsumo;
	private String consumoAguaMedido;
	private String consumoAguaFaturado;
	private String consumoEsgoto;
	private String consumoRateio;
	private String consumoImovel;
	private String indicadorImovelAreaComum;
	
	public String getConsumoAguaFaturado() {
		return consumoAguaFaturado;
	}

	public void setConsumoAguaFaturado(String consumoAguaFaturado) {
		this.consumoAguaFaturado = consumoAguaFaturado;
	}

	public String getConsumoAguaMedido() {
		return consumoAguaMedido;
	}

	public void setConsumoAguaMedido(String consumoAguaMedido) {
		this.consumoAguaMedido = consumoAguaMedido;
	}

	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public String getConsumoRateio() {
		return consumoRateio;
	}

	public void setConsumoRateio(String consumoRateio) {
		this.consumoRateio = consumoRateio;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

	public String getConsumoImovel() {
		return consumoImovel;
	}

	public void setConsumoImovel(String consumoImovel) {
		this.consumoImovel = consumoImovel;
	}

	public String getIndicadorImovelAreaComum() {
		return indicadorImovelAreaComum;
	}

	public void setIndicadorImovelAreaComum(String indicadorImovelAreaComum) {
		this.indicadorImovelAreaComum = indicadorImovelAreaComum;
	}
}
