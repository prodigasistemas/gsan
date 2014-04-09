package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * Este caso de uso gera relatorio de anormalidade Informadas - Amostragem
 * 
 * [UC1050]  Filtrar Anormalidade Informadas - Amostragem
 * 
 * @author Hugo Leonardo
 * @date 06/08/2010
 * 
 */

public class GerarRelatorioAnormalidadePorAmostragemActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;	
	
	private String grupo;
	private String unidadeNegocio;
	private String regional;
	private String idLocalidadeInicial;
	private String nomeLocalidadeInicial;
	private String idLocalidadeFinal;
	private String nomeLocalidadeFinal;
	private String codigoSetorComercialInicial;
	private String nomeSetorComercialInicial;
	private String codigoSetorComercialFinal;
	private String nomeSetorComercialFinal;
	private String [] colecaoIdsEmpresa;
	
	private String referencia;
	private String idImovelPerfil;
	private String numOcorrenciasConsecutivas;
	private String indicadorOcorrenciasIguais;
	private String intervaloMediaConsumoInicial;
	private String intervaloMediaConsumoFinal;
	private String[] colecaoIdsConsumoAnormalidade;
	private String[] colecaoIdsLeituraAnormalidade;
	private String[] colecaoIdsLeituraAnormalidadeInformada;
	private String rota;
	private String tipoMedicao;
	
	private String idCategoria;
	private String quadraInicialNM;
	private String quadraFinalNM;
	private String quadraMensagemInicial;
	private String quadraMensagemFinal;
	private String quadraInicialID;
	private String quadraFinalID;
	private String idSetorComercialInicial;
	private String idSetorComercialFinal;
	
	private String amostragem;

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}
	
	public String getIndicadorOcorrenciasIguais() {
		return indicadorOcorrenciasIguais;
	}
	
	public void setIndicadorOcorrenciasIguais(String indicadorOcorrenciasIguais) {
		this.indicadorOcorrenciasIguais = indicadorOcorrenciasIguais;
	}
	
	public String getReferencia() {
		return referencia;
	}
	
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}
	
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}
	
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}
	
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	
	public String getRegional() {
		return regional;
	}
	
	public void setRegional(String regional) {
		this.regional = regional;
	}
	
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	
	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}
	
	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}
	
	public String getNumOcorrenciasConsecutivas() {
		return numOcorrenciasConsecutivas;
	}
	
	public void setNumOcorrenciasConsecutivas(String numOcorrenciasConsecutivas) {
		this.numOcorrenciasConsecutivas = numOcorrenciasConsecutivas;
	}
	
	public String[] getColecaoIdsConsumoAnormalidade() {
		return colecaoIdsConsumoAnormalidade;
	}
	
	public void setColecaoIdsConsumoAnormalidade(
			String[] colecaoIdsConsumoAnormalidade) {
		this.colecaoIdsConsumoAnormalidade = colecaoIdsConsumoAnormalidade;
	}
	
	public String[] getColecaoIdsLeituraAnormalidade() {
		return colecaoIdsLeituraAnormalidade;
	}
	
	public void setColecaoIdsLeituraAnormalidade(
			String[] colecaoIdsLeituraAnormalidade) {
		this.colecaoIdsLeituraAnormalidade = colecaoIdsLeituraAnormalidade;
	}

	public String getIntervaloMediaConsumoFinal() {
		return intervaloMediaConsumoFinal;
	}
	
	public void setIntervaloMediaConsumoFinal(String intervaloMediaConsumoFinal) {
		this.intervaloMediaConsumoFinal = intervaloMediaConsumoFinal;
	}
	
	public String getIntervaloMediaConsumoInicial() {
		return intervaloMediaConsumoInicial;
	}
	
	public void setIntervaloMediaConsumoInicial(String intervaloMediaConsumoInicial) {
		this.intervaloMediaConsumoInicial = intervaloMediaConsumoInicial;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}
	
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}
	
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}
	
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}
	
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}
	
	public String[] getColecaoIdsLeituraAnormalidadeInformada() {
		return colecaoIdsLeituraAnormalidadeInformada;
	}
	
	public void setColecaoIdsLeituraAnormalidadeInformada(
			String[] colecaoIdsLeituraAnormalidadeInformada) {
		this.colecaoIdsLeituraAnormalidadeInformada = colecaoIdsLeituraAnormalidadeInformada;
	}
	
	public String[] getColecaoIdsEmpresa() {
		return colecaoIdsEmpresa;
	}
	
	public void setColecaoIdsEmpresa(String[] colecaoIdsEmpresa) {
		this.colecaoIdsEmpresa = colecaoIdsEmpresa;
	}
	
	public String getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getQuadraFinalID() {
		return quadraFinalID;
	}
	
	public void setQuadraFinalID(String quadraFinalID) {
		this.quadraFinalID = quadraFinalID;
	}
	
	public String getQuadraFinalNM() {
		return quadraFinalNM;
	}
	
	public void setQuadraFinalNM(String quadraFinalNM) {
		this.quadraFinalNM = quadraFinalNM;
	}
	
	public String getQuadraInicialID() {
		return quadraInicialID;
	}
	
	public void setQuadraInicialID(String quadraInicialID) {
		this.quadraInicialID = quadraInicialID;
	}
	
	public String getQuadraInicialNM() {
		return quadraInicialNM;
	}
	
	public void setQuadraInicialNM(String quadraInicialNM) {
		this.quadraInicialNM = quadraInicialNM;
	}
	
	public String getQuadraMensagemFinal() {
		return quadraMensagemFinal;
	}
	
	public void setQuadraMensagemFinal(String quadraMensagemFinal) {
		this.quadraMensagemFinal = quadraMensagemFinal;
	}
	
	public String getQuadraMensagemInicial() {
		return quadraMensagemInicial;
	}
	
	public void setQuadraMensagemInicial(String quadraMensagemInicial) {
		this.quadraMensagemInicial = quadraMensagemInicial;
	}
	
	public String getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}
	
	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}
	
	public String getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}
	
	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}

	public String getAmostragem() {
		return amostragem;
	}

	public void setAmostragem(String amostragem) {
		this.amostragem = amostragem;
	}
	
}
