package gcom.micromedicao.bean;

import java.io.Serializable;

public class InformarSubdivisoesDeRotaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idRota;
	private Short codigoRota;
	
	private Integer quadraInicial;
	private Integer quadraFinal;
	private Integer idLeiturista;
	
	private Integer idGrupoFaturamento;
	private Integer idCobrancaGrupo;
	
	private Short indicadorTransmissaoOffline;
	
	public Short getIndicadorTransmissaoOffline() {
		return indicadorTransmissaoOffline;
	}

	public void setIndicadorTransmissaoOffline(Short indicadorTransmissaoOffline) {
		this.indicadorTransmissaoOffline = indicadorTransmissaoOffline;
	}

	public InformarSubdivisoesDeRotaHelper() { }

	public Integer getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(Integer idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public Integer getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(Integer quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public Integer getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(Integer quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public Integer getIdCobrancaGrupo() {
		return idCobrancaGrupo;
	}

	public void setIdCobrancaGrupo(Integer idCobrancaGrupo) {
		this.idCobrancaGrupo = idCobrancaGrupo;
	}

}
