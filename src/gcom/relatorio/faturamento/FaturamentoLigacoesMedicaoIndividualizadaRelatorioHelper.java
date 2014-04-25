package gcom.relatorio.faturamento;

public class FaturamentoLigacoesMedicaoIndividualizadaRelatorioHelper {
	
	private String idLocalidade;
	private String nomeLocalidade; 
	private String matriculaImovel;
	private String nomeConsumidor;
	private String qtdeEconomias;
	private String leituraAnterior;
	private String dataLeituraAnterior;
	private String leituraAtual;
	private String dataLeituraAtual;
	private String media;
	private String consumoImoveisVinculados;
	private String consumoFaturado;
	private String rateio;
	private String consumoEsgoto;
	private String anormalidade;
	private String anormalidadeConsumo;
	private String tipoConsumo;
	private String indicadorPoco;

	private String indicadorQuebraImovelCondominio;
	private String totalConsumidoresRateioMacromedidor;
	private String numeroEconomiasRateio;
	
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String numeroLote;
	private String numeroSubLote;
	
	
	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}
	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	public String getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	public String getNumeroSubLote() {
		return numeroSubLote;
	}
	public void setNumeroSubLote(String numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}
	public String getAnormalidade() {
		return anormalidade;
	}
	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
	}
	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}
	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}
	public String getConsumoFaturado() {
		return consumoFaturado;
	}
	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}
	public String getConsumoImoveisVinculados() {
		return consumoImoveisVinculados;
	}
	public void setConsumoImoveisVinculados(String consumoImoveisVinculados) {
		this.consumoImoveisVinculados = consumoImoveisVinculados;
	}
	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}
	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}
	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}
	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIndicadorPoco() {
		return indicadorPoco;
	}
	public void setIndicadorPoco(String indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}
	public String getIndicadorQuebraImovelCondominio() {
		return indicadorQuebraImovelCondominio;
	}
	public void setIndicadorQuebraImovelCondominio(
			String indicadorQuebraImovelCondominio) {
		this.indicadorQuebraImovelCondominio = indicadorQuebraImovelCondominio;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}
	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}
	public String getLeituraAtual() {
		return leituraAtual;
	}
	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getNomeConsumidor() {
		return nomeConsumidor;
	}
	public void setNomeConsumidor(String nomeConsumidor) {
		this.nomeConsumidor = nomeConsumidor;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNumeroEconomiasRateio() {
		return numeroEconomiasRateio;
	}
	public void setNumeroEconomiasRateio(String numeroEconomiasRateio) {
		this.numeroEconomiasRateio = numeroEconomiasRateio;
	}
	public String getQtdeEconomias() {
		return qtdeEconomias;
	}
	public void setQtdeEconomias(String qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}
	public String getRateio() {
		return rateio;
	}
	public void setRateio(String rateio) {
		this.rateio = rateio;
	}
	public String getTipoConsumo() {
		return tipoConsumo;
	}
	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}
	public String getTotalConsumidoresRateioMacromedidor() {
		return totalConsumidoresRateioMacromedidor;
	}
	public void setTotalConsumidoresRateioMacromedidor(
			String totalConsumidoresRateioMacromedidor) {
		this.totalConsumidoresRateioMacromedidor = totalConsumidoresRateioMacromedidor;
	}
	
	public String getInscricaoFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = this.idLocalidade;
		setorComercial = this.codigoSetorComercial;
		quadra = this.numeroQuadra;
		lote = this.numeroLote;
		subLote = this.numeroSubLote;

		if (this.idLocalidade != null ){
			if (this.idLocalidade.length() < 3 && this.idLocalidade.length() > 1) {
				localidade = zeroUm + this.idLocalidade;
			} else if (String.valueOf(this.idLocalidade)
					.length() < 3) {
				localidade = zeroDois + this.idLocalidade;
			}	
		}

		if (this.codigoSetorComercial != null ){
			if (this.codigoSetorComercial.length() < 3 && this.codigoSetorComercial.length() > 1) {
				setorComercial = zeroUm + this.codigoSetorComercial;
			} else if (this.codigoSetorComercial.length() < 3) {
				setorComercial = zeroDois + this.codigoSetorComercial;
			}
		}
		
		if (this.numeroQuadra != null ){
			if (this.numeroQuadra.length() < 3 && this.numeroQuadra.length() > 1) {
				quadra = zeroUm + this.numeroQuadra;
			} else if (this.numeroQuadra.length() < 3) {
				quadra = zeroDois + this.numeroQuadra;
			}
		}
		
		if (this.numeroLote != null ){
			if (this.numeroLote.length() < 4 && this.numeroLote.length() > 2) {
				lote = zeroUm + this.numeroLote;
			} else if (this.numeroLote.length() < 3	&& this.numeroLote.length() > 1) {
				lote = zeroDois + this.numeroLote;
			} else if (this.numeroLote.length() < 2) {
				lote = zeroTres + this.numeroLote;
			}
		}
		
		if (this.numeroSubLote != null ){
			if (this.numeroSubLote.length() < 3 && this.numeroSubLote.length() > 1) {
				subLote = zeroUm + this.numeroSubLote;
			} else if (this.numeroSubLote.length() < 3) {
				subLote = zeroDois + this.numeroSubLote;
			}
		}
		
		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}

	public String getIndicadorPocoExtenso() {
		String poco = "";
		
		if (this.indicadorPoco != null && this.indicadorPoco.equals("1")){
			poco = "SIM";
		}else if (this.indicadorPoco != null && this.indicadorPoco.equals("2")){
			poco = "NÃO";
		}	
		return poco;
	}
}
