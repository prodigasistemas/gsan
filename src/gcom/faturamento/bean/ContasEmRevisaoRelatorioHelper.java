package gcom.faturamento.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Helper utilizado pelos relatórios de volumes faturados
 * 
 * @author Rafael Corrêa
 * @since 11/09/2007
 */
public class ContasEmRevisaoRelatorioHelper {

	private Integer idImovel;
	
	private Integer idGerenciaRegional;
	
	private String nomeGerenciaRegional;
	
	private Integer idElo;
	
	private String nomeElo;
	
	private Integer idUnidadeNegocio;
	
	private String nomeUnidadeNegocio;

	private Integer idLocalidade;

	private String nomeLocalidade;

	private Integer codigoSetorComercial;
	
	private String nomeSetorComercial;

	private Integer numeroQuadra;
	
	private Short lote;
	
	private Short sublote;

	private String nomeUsuario;

	private String ddd;

	private String telefone;

	private String ramal;

	private Integer anoMesReferenciaConta;

	private BigDecimal valorConta;

	private Date dataRevisao;

	private Integer idMotivoRevisao;

	private String descricaoMotivoRevisao;
	
	private Integer qtdeContas;

	/**
	 * @return Retorna o campo anoMesReferenciaConta.
	 */
	public Integer getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}

	/**
	 * @param anoMesReferenciaConta O anoMesReferenciaConta a ser setado.
	 */
	public void setAnoMesReferenciaConta(Integer anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo dataRevisao.
	 */
	public Date getDataRevisao() {
		return dataRevisao;
	}

	/**
	 * @param dataRevisao O dataRevisao a ser setado.
	 */
	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	/**
	 * @return Retorna o campo ddd.
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * @param ddd O ddd a ser setado.
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * @return Retorna o campo descricaoMotivoRevisao.
	 */
	public String getDescricaoMotivoRevisao() {
		return descricaoMotivoRevisao;
	}

	/**
	 * @param descricaoMotivoRevisao O descricaoMotivoRevisao a ser setado.
	 */
	public void setDescricaoMotivoRevisao(String descricaoMotivoRevisao) {
		this.descricaoMotivoRevisao = descricaoMotivoRevisao;
	}

	/**
	 * @return Retorna o campo idElo.
	 */
	public Integer getIdElo() {
		return idElo;
	}

	/**
	 * @param idElo O idElo a ser setado.
	 */
	public void setIdElo(Integer idElo) {
		this.idElo = idElo;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idMotivoRevisao.
	 */
	public Integer getIdMotivoRevisao() {
		return idMotivoRevisao;
	}

	/**
	 * @param idMotivoRevisao O idMotivoRevisao a ser setado.
	 */
	public void setIdMotivoRevisao(Integer idMotivoRevisao) {
		this.idMotivoRevisao = idMotivoRevisao;
	}

	/**
	 * @return Retorna o campo lote.
	 */
	public Short getLote() {
		return lote;
	}

	/**
	 * @param lote O lote a ser setado.
	 */
	public void setLote(Short lote) {
		this.lote = lote;
	}

	/**
	 * @return Retorna o campo nomeElo.
	 */
	public String getNomeElo() {
		return nomeElo;
	}

	/**
	 * @param nomeElo O nomeElo a ser setado.
	 */
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}

	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	/**
	 * @param nomeGerenciaRegional O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	/**
	 * @return Retorna o campo nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade O nomeLocalidade a ser setado.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Retorna o campo ramal.
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * @param ramal O ramal a ser setado.
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	/**
	 * @return Retorna o campo sublote.
	 */
	public Short getSublote() {
		return sublote;
	}

	/**
	 * @param sublote O sublote a ser setado.
	 */
	public void setSublote(Short sublote) {
		this.sublote = sublote;
	}

	/**
	 * @return Retorna o campo telefone.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone O telefone a ser setado.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return Retorna o campo valorConta.
	 */
	public BigDecimal getValorConta() {
		return valorConta;
	}

	/**
	 * @param valorConta O valorConta a ser setado.
	 */
	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}
	
	/**
	 * @return Retorna o campo qtdeContas.
	 */
	public Integer getQtdeContas() {
		return qtdeContas;
	}

	/**
	 * @param qtdeContas O qtdeContas a ser setado.
	 */
	public void setQtdeContas(Integer qtdeContas) {
		this.qtdeContas = qtdeContas;
	}

	/*
	 * Retorna a inscrição do imóvel.
	 */
	public String getInscricaoFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = String.valueOf(this.getIdLocalidade().intValue());
		setorComercial = String.valueOf(this.getCodigoSetorComercial().intValue());
		quadra = String.valueOf(this.getNumeroQuadra().intValue());
		lote = String.valueOf(this.getLote().shortValue());
		subLote = String.valueOf(this.getSublote().shortValue());

		if (String.valueOf(this.getIdLocalidade().intValue()).length() < 3
				&& String.valueOf(this.getIdLocalidade().intValue())
						.length() > 1) {
			localidade = zeroUm + this.getIdLocalidade().intValue();
		} else if (String.valueOf(this.getIdLocalidade().intValue())
				.length() < 3) {
			localidade = zeroDois + this.getIdLocalidade().intValue();
		}

		if (String.valueOf(this.getCodigoSetorComercial().intValue()).length() < 3
				&& String.valueOf(this.getCodigoSetorComercial().intValue())
						.length() > 1) {
			setorComercial = zeroUm + this.getCodigoSetorComercial().intValue();
		} else if (String.valueOf(this.getCodigoSetorComercial().intValue())
				.length() < 3) {
			setorComercial = zeroDois + this.getCodigoSetorComercial().intValue();
		}

		if (String.valueOf(this.getNumeroQuadra().intValue()).length() < 3
				&& String.valueOf(this.getNumeroQuadra().intValue()).length() > 1) {
			quadra = zeroUm + this.getNumeroQuadra().intValue();
		} else if (String.valueOf(this.getNumeroQuadra().intValue()).length() < 3) {
			quadra = zeroDois + this.getNumeroQuadra().intValue();
		}

		if (String.valueOf(this.getLote().shortValue()).length() < 4
				&& String.valueOf(this.getLote().shortValue()).length() > 2) {
			lote = zeroUm + this.getLote().shortValue();
		} else if (String.valueOf(this.getLote().shortValue()).length() < 3
				&& String.valueOf(this.getLote().shortValue()).length() > 1) {
			lote = zeroDois + this.getLote().shortValue();
		} else if (String.valueOf(this.getLote().shortValue()).length() < 2) {
			lote = zeroTres + this.getLote().shortValue();
		}

		if (String.valueOf(this.getSublote().shortValue()).length() < 3
				&& String.valueOf(this.getSublote().shortValue()).length() > 1) {
			subLote = zeroUm + this.getSublote().shortValue();
		} else if (String.valueOf(this.getSublote().shortValue()).length() < 3) {
			subLote = zeroDois + this.getSublote().shortValue();
		}

		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}
	
	/**
	 * Retorna o valor de dddTelefone
	 * 
	 * @return O valor de dddTelefone
	 */
	public String getDddTelefone() {
		
		if (this.ddd != null){
			return "(" + this.ddd + ")" + this.telefone;
		}
		return this.telefone;
	}
	
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}

	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

}
