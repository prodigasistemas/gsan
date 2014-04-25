package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0637] - Gerar Relatórios Volumes Faturados
 * 
 * @author Rafael Corrêa
 * @date 07/03/2007
 */
public class RelatorioVolumesFaturadosBean implements RelatorioBean {

	private String localidade;

	private String idSetorComercial;

	private String setorComercial;

	private String idQuadra;

	private String quadra;

	private String imovel;

	private String usuario;

	private String situacaoAgua;

	private String situacaoEsgoto;

	private String media;

	private String mesAno1;

	private String variacao1;

	private String mesAno2;

	private String variacao2;

	private String mesAno3;

	private String variacao3;

	private String mesAno4;

	private String variacao4;

	private String mesAno5;

	private String variacao5;

	private String mesAno6;

	private String variacao6;

	private String mediaTotalQuadra;

	private String mesAno1TotalQuadra;

	private String variacao1TotalQuadra;

	private String mesAno2TotalQuadra;

	private String variacao2TotalQuadra;

	private String mesAno3TotalQuadra;

	private String variacao3TotalQuadra;

	private String mesAno4TotalQuadra;

	private String variacao4TotalQuadra;

	private String mesAno5TotalQuadra;

	private String variacao5TotalQuadra;

	private String mesAno6TotalQuadra;

	private String variacao6TotalQuadra;

	private String mediaTotalSetorComercial;

	private String mesAno1TotalSetorComercial;

	private String variacao1TotalSetorComercial;

	private String mesAno2TotalSetorComercial;

	private String variacao2TotalSetorComercial;

	private String mesAno3TotalSetorComercial;

	private String variacao3TotalSetorComercial;

	private String mesAno4TotalSetorComercial;

	private String variacao4TotalSetorComercial;

	private String mesAno5TotalSetorComercial;

	private String variacao5TotalSetorComercial;

	private String mesAno6TotalSetorComercial;

	private String variacao6TotalSetorComercial;

	private String mediaTotalLocalidade;

	private String mesAno1TotalLocalidade;

	private String variacao1TotalLocalidade;

	private String mesAno2TotalLocalidade;

	private String variacao2TotalLocalidade;

	private String mesAno3TotalLocalidade;

	private String variacao3TotalLocalidade;

	private String mesAno4TotalLocalidade;

	private String variacao4TotalLocalidade;

	private String mesAno5TotalLocalidade;

	private String variacao5TotalLocalidade;

	private String mesAno6TotalLocalidade;

	private String variacao6TotalLocalidade;

	public RelatorioVolumesFaturadosBean(String localidade,
			String idSetorComercial, String setorComercial, String idQuadra,
			String quadra, String imovel, String usuario, String situacaoAgua,
			String situacaoEsgoto, String media, String mesAno1,
			String variacao1, String mesAno2, String variacao2, String mesAno3,
			String variacao3, String mesAno4, String variacao4, String mesAno5,
			String variacao5, String mesAno6, String variacao6,
			String mediaTotalQuadra, String mesAno1TotalQuadra,
			String variacao1TotalQuadra, String mesAno2TotalQuadra,
			String variacao2TotalQuadra, String mesAno3TotalQuadra,
			String variacao3TotalQuadra, String mesAno4TotalQuadra,
			String variacao4TotalQuadra, String mesAno5TotalQuadra,
			String variacao5TotalQuadra, String mesAno6TotalQuadra,
			String variacao6TotalQuadra, String mediaTotalSetorComercial,
			String mesAno1TotalSetorComercial,
			String variacao1TotalSetorComercial,
			String mesAno2TotalSetorComercial,
			String variacao2TotalSetorComercial,
			String mesAno3TotalSetorComercial,
			String variacao3TotalSetorComercial,
			String mesAno4TotalSetorComercial,
			String variacao4TotalSetorComercial,
			String mesAno5TotalSetorComercial,
			String variacao5TotalSetorComercial,
			String mesAno6TotalSetorComercial,
			String variacao6TotalSetorComercial, String mediaTotalLocalidade,
			String mesAno1TotalLocalidade, String variacao1TotalLocalidade,
			String mesAno2TotalLocalidade, String variacao2TotalLocalidade,
			String mesAno3TotalLocalidade, String variacao3TotalLocalidade,
			String mesAno4TotalLocalidade, String variacao4TotalLocalidade,
			String mesAno5TotalLocalidade, String variacao5TotalLocalidade,
			String mesAno6TotalLocalidade, String variacao6TotalLocalidade) {

		this.localidade = localidade;
		this.idSetorComercial = idSetorComercial;
		this.setorComercial = setorComercial;
		this.idQuadra = idQuadra;
		this.quadra = quadra;
		this.imovel = imovel;
		this.usuario = usuario;
		this.situacaoAgua = situacaoAgua;
		this.situacaoEsgoto = situacaoEsgoto;
		this.media = media;
		this.mesAno1 = mesAno1;
		this.variacao1 = variacao1;
		this.mesAno2 = mesAno2;
		this.variacao2 = variacao2;
		this.mesAno3 = mesAno3;
		this.variacao3 = variacao3;
		this.mesAno4 = mesAno4;
		this.variacao4 = variacao4;
		this.mesAno5 = mesAno5;
		this.variacao5 = variacao5;
		this.mesAno6 = mesAno6;
		this.variacao6 = variacao6;
		this.mediaTotalQuadra = mediaTotalQuadra;
		this.mesAno1TotalQuadra = mesAno1TotalQuadra;
		this.variacao1TotalQuadra = variacao1TotalQuadra;
		this.mesAno2TotalQuadra = mesAno2TotalQuadra;
		this.variacao2TotalQuadra = variacao2TotalQuadra;
		this.mesAno3TotalQuadra = mesAno3TotalQuadra;
		this.variacao3TotalQuadra = variacao3TotalQuadra;
		this.mesAno4TotalQuadra = mesAno4TotalQuadra;
		this.variacao4TotalQuadra = variacao4TotalQuadra;
		this.mesAno5TotalQuadra = mesAno5TotalQuadra;
		this.variacao5TotalQuadra = variacao5TotalQuadra;
		this.mesAno6TotalQuadra = mesAno6TotalQuadra;
		this.variacao6TotalQuadra = variacao6TotalQuadra;
		this.mediaTotalSetorComercial = mediaTotalSetorComercial;
		this.mesAno1TotalSetorComercial = mesAno1TotalSetorComercial;
		this.variacao1TotalSetorComercial = variacao1TotalSetorComercial;
		this.mesAno2TotalSetorComercial = mesAno2TotalSetorComercial;
		this.variacao2TotalSetorComercial = variacao2TotalSetorComercial;
		this.mesAno3TotalSetorComercial = mesAno3TotalSetorComercial;
		this.variacao3TotalSetorComercial = variacao3TotalSetorComercial;
		this.mesAno4TotalSetorComercial = mesAno4TotalSetorComercial;
		this.variacao4TotalSetorComercial = variacao4TotalSetorComercial;
		this.mesAno5TotalSetorComercial = mesAno5TotalSetorComercial;
		this.variacao5TotalSetorComercial = variacao5TotalSetorComercial;
		this.mesAno6TotalSetorComercial = mesAno6TotalSetorComercial;
		this.variacao6TotalSetorComercial = variacao6TotalSetorComercial;
		this.mediaTotalLocalidade = mediaTotalLocalidade;
		this.mesAno1TotalLocalidade = mesAno1TotalLocalidade;
		this.variacao1TotalLocalidade = variacao1TotalLocalidade;
		this.mesAno2TotalLocalidade = mesAno2TotalLocalidade;
		this.variacao2TotalLocalidade = variacao2TotalLocalidade;
		this.mesAno3TotalLocalidade = mesAno3TotalLocalidade;
		this.variacao3TotalLocalidade = variacao3TotalLocalidade;
		this.mesAno4TotalLocalidade = mesAno4TotalLocalidade;
		this.variacao4TotalLocalidade = variacao4TotalLocalidade;
		this.mesAno5TotalLocalidade = mesAno5TotalLocalidade;
		this.variacao5TotalLocalidade = variacao5TotalLocalidade;
		this.mesAno6TotalLocalidade = mesAno6TotalLocalidade;
		this.variacao6TotalLocalidade = variacao6TotalLocalidade;

	}
	
	public RelatorioVolumesFaturadosBean(String localidade,
			String idSetorComercial, String setorComercial,
			String quadra, String media, String mesAno1,
			String variacao1, String mesAno2, String variacao2, String mesAno3,
			String variacao3, String mesAno4, String variacao4, String mesAno5,
			String variacao5, String mesAno6, String variacao6,
			String mediaTotalSetorComercial,
			String mesAno1TotalSetorComercial,
			String variacao1TotalSetorComercial,
			String mesAno2TotalSetorComercial,
			String variacao2TotalSetorComercial,
			String mesAno3TotalSetorComercial,
			String variacao3TotalSetorComercial,
			String mesAno4TotalSetorComercial,
			String variacao4TotalSetorComercial,
			String mesAno5TotalSetorComercial,
			String variacao5TotalSetorComercial,
			String mesAno6TotalSetorComercial,
			String variacao6TotalSetorComercial, String mediaTotalLocalidade,
			String mesAno1TotalLocalidade, String variacao1TotalLocalidade,
			String mesAno2TotalLocalidade, String variacao2TotalLocalidade,
			String mesAno3TotalLocalidade, String variacao3TotalLocalidade,
			String mesAno4TotalLocalidade, String variacao4TotalLocalidade,
			String mesAno5TotalLocalidade, String variacao5TotalLocalidade,
			String mesAno6TotalLocalidade, String variacao6TotalLocalidade) {

		this.localidade = localidade;
		this.idSetorComercial = idSetorComercial;
		this.setorComercial = setorComercial;
		this.quadra = quadra;
		this.media = media;
		this.mesAno1 = mesAno1;
		this.variacao1 = variacao1;
		this.mesAno2 = mesAno2;
		this.variacao2 = variacao2;
		this.mesAno3 = mesAno3;
		this.variacao3 = variacao3;
		this.mesAno4 = mesAno4;
		this.variacao4 = variacao4;
		this.mesAno5 = mesAno5;
		this.variacao5 = variacao5;
		this.mesAno6 = mesAno6;
		this.variacao6 = variacao6;
		this.mediaTotalSetorComercial = mediaTotalSetorComercial;
		this.mesAno1TotalSetorComercial = mesAno1TotalSetorComercial;
		this.variacao1TotalSetorComercial = variacao1TotalSetorComercial;
		this.mesAno2TotalSetorComercial = mesAno2TotalSetorComercial;
		this.variacao2TotalSetorComercial = variacao2TotalSetorComercial;
		this.mesAno3TotalSetorComercial = mesAno3TotalSetorComercial;
		this.variacao3TotalSetorComercial = variacao3TotalSetorComercial;
		this.mesAno4TotalSetorComercial = mesAno4TotalSetorComercial;
		this.variacao4TotalSetorComercial = variacao4TotalSetorComercial;
		this.mesAno5TotalSetorComercial = mesAno5TotalSetorComercial;
		this.variacao5TotalSetorComercial = variacao5TotalSetorComercial;
		this.mesAno6TotalSetorComercial = mesAno6TotalSetorComercial;
		this.variacao6TotalSetorComercial = variacao6TotalSetorComercial;
		this.mediaTotalLocalidade = mediaTotalLocalidade;
		this.mesAno1TotalLocalidade = mesAno1TotalLocalidade;
		this.variacao1TotalLocalidade = variacao1TotalLocalidade;
		this.mesAno2TotalLocalidade = mesAno2TotalLocalidade;
		this.variacao2TotalLocalidade = variacao2TotalLocalidade;
		this.mesAno3TotalLocalidade = mesAno3TotalLocalidade;
		this.variacao3TotalLocalidade = variacao3TotalLocalidade;
		this.mesAno4TotalLocalidade = mesAno4TotalLocalidade;
		this.variacao4TotalLocalidade = variacao4TotalLocalidade;
		this.mesAno5TotalLocalidade = mesAno5TotalLocalidade;
		this.variacao5TotalLocalidade = variacao5TotalLocalidade;
		this.mesAno6TotalLocalidade = mesAno6TotalLocalidade;
		this.variacao6TotalLocalidade = variacao6TotalLocalidade;

	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getMediaTotalLocalidade() {
		return mediaTotalLocalidade;
	}

	public void setMediaTotalLocalidade(String mediaTotalLocalidade) {
		this.mediaTotalLocalidade = mediaTotalLocalidade;
	}

	public String getMediaTotalQuadra() {
		return mediaTotalQuadra;
	}

	public void setMediaTotalQuadra(String mediaTotalQuadra) {
		this.mediaTotalQuadra = mediaTotalQuadra;
	}

	public String getMediaTotalSetorComercial() {
		return mediaTotalSetorComercial;
	}

	public void setMediaTotalSetorComercial(String mediaTotalSetorComercial) {
		this.mediaTotalSetorComercial = mediaTotalSetorComercial;
	}

	public String getMesAno1() {
		return mesAno1;
	}

	public void setMesAno1(String mesAno1) {
		this.mesAno1 = mesAno1;
	}

	public String getMesAno1TotalLocalidade() {
		return mesAno1TotalLocalidade;
	}

	public void setMesAno1TotalLocalidade(String mesAno1TotalLocalidade) {
		this.mesAno1TotalLocalidade = mesAno1TotalLocalidade;
	}

	public String getMesAno1TotalQuadra() {
		return mesAno1TotalQuadra;
	}

	public void setMesAno1TotalQuadra(String mesAno1TotalQuadra) {
		this.mesAno1TotalQuadra = mesAno1TotalQuadra;
	}

	public String getMesAno1TotalSetorComercial() {
		return mesAno1TotalSetorComercial;
	}

	public void setMesAno1TotalSetorComercial(String mesAno1TotalSetorComercial) {
		this.mesAno1TotalSetorComercial = mesAno1TotalSetorComercial;
	}

	public String getMesAno2() {
		return mesAno2;
	}

	public void setMesAno2(String mesAno2) {
		this.mesAno2 = mesAno2;
	}

	public String getMesAno2TotalLocalidade() {
		return mesAno2TotalLocalidade;
	}

	public void setMesAno2TotalLocalidade(String mesAno2TotalLocalidade) {
		this.mesAno2TotalLocalidade = mesAno2TotalLocalidade;
	}

	public String getMesAno2TotalQuadra() {
		return mesAno2TotalQuadra;
	}

	public void setMesAno2TotalQuadra(String mesAno2TotalQuadra) {
		this.mesAno2TotalQuadra = mesAno2TotalQuadra;
	}

	public String getMesAno2TotalSetorComercial() {
		return mesAno2TotalSetorComercial;
	}

	public void setMesAno2TotalSetorComercial(String mesAno2TotalSetorComercial) {
		this.mesAno2TotalSetorComercial = mesAno2TotalSetorComercial;
	}

	public String getMesAno3() {
		return mesAno3;
	}

	public void setMesAno3(String mesAno3) {
		this.mesAno3 = mesAno3;
	}

	public String getMesAno3TotalLocalidade() {
		return mesAno3TotalLocalidade;
	}

	public void setMesAno3TotalLocalidade(String mesAno3TotalLocalidade) {
		this.mesAno3TotalLocalidade = mesAno3TotalLocalidade;
	}

	public String getMesAno3TotalQuadra() {
		return mesAno3TotalQuadra;
	}

	public void setMesAno3TotalQuadra(String mesAno3TotalQuadra) {
		this.mesAno3TotalQuadra = mesAno3TotalQuadra;
	}

	public String getMesAno3TotalSetorComercial() {
		return mesAno3TotalSetorComercial;
	}

	public void setMesAno3TotalSetorComercial(String mesAno3TotalSetorComercial) {
		this.mesAno3TotalSetorComercial = mesAno3TotalSetorComercial;
	}

	public String getMesAno4() {
		return mesAno4;
	}

	public void setMesAno4(String mesAno4) {
		this.mesAno4 = mesAno4;
	}

	public String getMesAno4TotalLocalidade() {
		return mesAno4TotalLocalidade;
	}

	public void setMesAno4TotalLocalidade(String mesAno4TotalLocalidade) {
		this.mesAno4TotalLocalidade = mesAno4TotalLocalidade;
	}

	public String getMesAno4TotalQuadra() {
		return mesAno4TotalQuadra;
	}

	public void setMesAno4TotalQuadra(String mesAno4TotalQuadra) {
		this.mesAno4TotalQuadra = mesAno4TotalQuadra;
	}

	public String getMesAno4TotalSetorComercial() {
		return mesAno4TotalSetorComercial;
	}

	public void setMesAno4TotalSetorComercial(String mesAno4TotalSetorComercial) {
		this.mesAno4TotalSetorComercial = mesAno4TotalSetorComercial;
	}

	public String getMesAno5() {
		return mesAno5;
	}

	public void setMesAno5(String mesAno5) {
		this.mesAno5 = mesAno5;
	}

	public String getMesAno5TotalLocalidade() {
		return mesAno5TotalLocalidade;
	}

	public void setMesAno5TotalLocalidade(String mesAno5TotalLocalidade) {
		this.mesAno5TotalLocalidade = mesAno5TotalLocalidade;
	}

	public String getMesAno5TotalQuadra() {
		return mesAno5TotalQuadra;
	}

	public void setMesAno5TotalQuadra(String mesAno5TotalQuadra) {
		this.mesAno5TotalQuadra = mesAno5TotalQuadra;
	}

	public String getMesAno5TotalSetorComercial() {
		return mesAno5TotalSetorComercial;
	}

	public void setMesAno5TotalSetorComercial(String mesAno5TotalSetorComercial) {
		this.mesAno5TotalSetorComercial = mesAno5TotalSetorComercial;
	}

	public String getMesAno6() {
		return mesAno6;
	}

	public void setMesAno6(String mesAno6) {
		this.mesAno6 = mesAno6;
	}

	public String getMesAno6TotalLocalidade() {
		return mesAno6TotalLocalidade;
	}

	public void setMesAno6TotalLocalidade(String mesAno6TotalLocalidade) {
		this.mesAno6TotalLocalidade = mesAno6TotalLocalidade;
	}

	public String getMesAno6TotalQuadra() {
		return mesAno6TotalQuadra;
	}

	public void setMesAno6TotalQuadra(String mesAno6TotalQuadra) {
		this.mesAno6TotalQuadra = mesAno6TotalQuadra;
	}

	public String getMesAno6TotalSetorComercial() {
		return mesAno6TotalSetorComercial;
	}

	public void setMesAno6TotalSetorComercial(String mesAno6TotalSetorComercial) {
		this.mesAno6TotalSetorComercial = mesAno6TotalSetorComercial;
	}

	public String getQuadra() {
		return quadra;
	}

	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getVariacao1() {
		return variacao1;
	}

	public void setVariacao1(String variacao1) {
		this.variacao1 = variacao1;
	}

	public String getVariacao1TotalLocalidade() {
		return variacao1TotalLocalidade;
	}

	public void setVariacao1TotalLocalidade(String variacao1TotalLocalidade) {
		this.variacao1TotalLocalidade = variacao1TotalLocalidade;
	}

	public String getVariacao1TotalQuadra() {
		return variacao1TotalQuadra;
	}

	public void setVariacao1TotalQuadra(String variacao1TotalQuadra) {
		this.variacao1TotalQuadra = variacao1TotalQuadra;
	}

	public String getVariacao1TotalSetorComercial() {
		return variacao1TotalSetorComercial;
	}

	public void setVariacao1TotalSetorComercial(String variacao1TotalSetorComercial) {
		this.variacao1TotalSetorComercial = variacao1TotalSetorComercial;
	}

	public String getVariacao2() {
		return variacao2;
	}

	public void setVariacao2(String variacao2) {
		this.variacao2 = variacao2;
	}

	public String getVariacao2TotalLocalidade() {
		return variacao2TotalLocalidade;
	}

	public void setVariacao2TotalLocalidade(String variacao2TotalLocalidade) {
		this.variacao2TotalLocalidade = variacao2TotalLocalidade;
	}

	public String getVariacao2TotalQuadra() {
		return variacao2TotalQuadra;
	}

	public void setVariacao2TotalQuadra(String variacao2TotalQuadra) {
		this.variacao2TotalQuadra = variacao2TotalQuadra;
	}

	public String getVariacao2TotalSetorComercial() {
		return variacao2TotalSetorComercial;
	}

	public void setVariacao2TotalSetorComercial(String variacao2TotalSetorComercial) {
		this.variacao2TotalSetorComercial = variacao2TotalSetorComercial;
	}

	public String getVariacao3() {
		return variacao3;
	}

	public void setVariacao3(String variacao3) {
		this.variacao3 = variacao3;
	}

	public String getVariacao3TotalLocalidade() {
		return variacao3TotalLocalidade;
	}

	public void setVariacao3TotalLocalidade(String variacao3TotalLocalidade) {
		this.variacao3TotalLocalidade = variacao3TotalLocalidade;
	}

	public String getVariacao3TotalQuadra() {
		return variacao3TotalQuadra;
	}

	public void setVariacao3TotalQuadra(String variacao3TotalQuadra) {
		this.variacao3TotalQuadra = variacao3TotalQuadra;
	}

	public String getVariacao3TotalSetorComercial() {
		return variacao3TotalSetorComercial;
	}

	public void setVariacao3TotalSetorComercial(String variacao3TotalSetorComercial) {
		this.variacao3TotalSetorComercial = variacao3TotalSetorComercial;
	}

	public String getVariacao4() {
		return variacao4;
	}

	public void setVariacao4(String variacao4) {
		this.variacao4 = variacao4;
	}

	public String getVariacao4TotalLocalidade() {
		return variacao4TotalLocalidade;
	}

	public void setVariacao4TotalLocalidade(String variacao4TotalLocalidade) {
		this.variacao4TotalLocalidade = variacao4TotalLocalidade;
	}

	public String getVariacao4TotalQuadra() {
		return variacao4TotalQuadra;
	}

	public void setVariacao4TotalQuadra(String variacao4TotalQuadra) {
		this.variacao4TotalQuadra = variacao4TotalQuadra;
	}

	public String getVariacao4TotalSetorComercial() {
		return variacao4TotalSetorComercial;
	}

	public void setVariacao4TotalSetorComercial(String variacao4TotalSetorComercial) {
		this.variacao4TotalSetorComercial = variacao4TotalSetorComercial;
	}

	public String getVariacao5() {
		return variacao5;
	}

	public void setVariacao5(String variacao5) {
		this.variacao5 = variacao5;
	}

	public String getVariacao5TotalLocalidade() {
		return variacao5TotalLocalidade;
	}

	public void setVariacao5TotalLocalidade(String variacao5TotalLocalidade) {
		this.variacao5TotalLocalidade = variacao5TotalLocalidade;
	}

	public String getVariacao5TotalQuadra() {
		return variacao5TotalQuadra;
	}

	public void setVariacao5TotalQuadra(String variacao5TotalQuadra) {
		this.variacao5TotalQuadra = variacao5TotalQuadra;
	}

	public String getVariacao5TotalSetorComercial() {
		return variacao5TotalSetorComercial;
	}

	public void setVariacao5TotalSetorComercial(String variacao5TotalSetorComercial) {
		this.variacao5TotalSetorComercial = variacao5TotalSetorComercial;
	}

	public String getVariacao6() {
		return variacao6;
	}

	public void setVariacao6(String variacao6) {
		this.variacao6 = variacao6;
	}

	public String getVariacao6TotalLocalidade() {
		return variacao6TotalLocalidade;
	}

	public void setVariacao6TotalLocalidade(String variacao6TotalLocalidade) {
		this.variacao6TotalLocalidade = variacao6TotalLocalidade;
	}

	public String getVariacao6TotalQuadra() {
		return variacao6TotalQuadra;
	}

	public void setVariacao6TotalQuadra(String variacao6TotalQuadra) {
		this.variacao6TotalQuadra = variacao6TotalQuadra;
	}

	public String getVariacao6TotalSetorComercial() {
		return variacao6TotalSetorComercial;
	}

	public void setVariacao6TotalSetorComercial(String variacao6TotalSetorComercial) {
		this.variacao6TotalSetorComercial = variacao6TotalSetorComercial;
	}

}
