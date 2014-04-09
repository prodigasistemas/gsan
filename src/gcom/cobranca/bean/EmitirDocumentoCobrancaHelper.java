package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** @author Hibernate CodeGenerator */
public class EmitirDocumentoCobrancaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idDocumentoCobranca;
	
	private Integer idLocalidade;

	private String descricaoLocalidade;

	private Integer codigoSetorComercial;

	private int numeroQuadra;

	private short lote;
	
	private short subLote;

	private Integer idImovel;

	private Short numeroDiasValidade;
	
	private int numeroSequenciaDocumento;
	
	private String descricaoImovelPerfil;
	
    private Date emissao;
    
    private Integer idCobrancaGrupo;
	
    private BigDecimal valorDocumento;
    
    private Integer idEmpresa;
    
    private String descricaoEmpresa;
    
    private String nomeAbreviadoGerencia;
    
    private String nomeGerencia;
    
    private Integer idLigacaoAguaSituacao;
    
    private Integer idLigacaoEsgotoSituacao;
    
    private Integer idGerencia;
    
    private Date dtImovelUltimaAlteracao;
    
    private String descricaoSituacaoLigacaoAgua;
    
    private String descricaoSituacaoLigacaoEsgoto;    
    
    private Integer idLeituraAnormalidade;
    
    private String descricaoLeitAnormalidade;
    
    private Short codigoRota;
    
    private Integer numeroSequencialRota;
    
    private Date dataEmissaoPredecessor;
    
    private Integer numeroOS;
    
    private Integer numeroDiasVencimento;
    

	/**
	 * @return Retorna o campo dataEmissaoPredecessor.
	 */
	public Date getDataEmissaoPredecessor() {
		return dataEmissaoPredecessor;
	}

	/**
	 * @param dataEmissaoPredecessor O dataEmissaoPredecessor a ser setado.
	 */
	public void setDataEmissaoPredecessor(Date dataEmissaoPredecessor) {
		this.dataEmissaoPredecessor = dataEmissaoPredecessor;
	}

	/**
	 * @return Retorna o campo descricaoLeitAnormalidade.
	 */
	public String getDescricaoLeitAnormalidade() {
		return descricaoLeitAnormalidade;
	}

	/**
	 * @param descricaoLeitAnormalidade O descricaoLeitAnormalidade a ser setado.
	 */
	public void setDescricaoLeitAnormalidade(String descricaoLeitAnormalidade) {
		this.descricaoLeitAnormalidade = descricaoLeitAnormalidade;
	}

	/**
	 * @return Retorna o campo idLeituraAnormalidade.
	 */
	public Integer getIdLeituraAnormalidade() {
		return idLeituraAnormalidade;
	}

	/**
	 * @param idLeituraAnormalidade O idLeituraAnormalidade a ser setado.
	 */
	public void setIdLeituraAnormalidade(Integer idLeituraAnormalidade) {
		this.idLeituraAnormalidade = idLeituraAnormalidade;
	}

	public Integer getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public String getDescricaoImovelPerfil() {
		return descricaoImovelPerfil;
	}

	public void setDescricaoImovelPerfil(String descricaoImovelPerfil) {
		this.descricaoImovelPerfil = descricaoImovelPerfil;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public Integer getIdCobrancaGrupo() {
		return idCobrancaGrupo;
	}

	public void setIdCobrancaGrupo(Integer idCobrancaGrupo) {
		this.idCobrancaGrupo = idCobrancaGrupo;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public short getLote() {
		return lote;
	}

	public void setLote(short lote) {
		this.lote = lote;
	}

	public String getNomeAbreviadoGerencia() {
		return nomeAbreviadoGerencia;
	}

	public void setNomeAbreviadoGerencia(String nomeAbreviadoGerencia) {
		this.nomeAbreviadoGerencia = nomeAbreviadoGerencia;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	public Short getNumeroDiasValidade() {
		return numeroDiasValidade;
	}

	public void setNumeroDiasValidade(Short numeroDiasValidade) {
		this.numeroDiasValidade = numeroDiasValidade;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public int getNumeroSequenciaDocumento() {
		return numeroSequenciaDocumento;
	}

	public void setNumeroSequenciaDocumento(int numeroSequenciaDocumento) {
		this.numeroSequenciaDocumento = numeroSequenciaDocumento;
	}

	public short getSubLote() {
		return subLote;
	}

	public void setSubLote(short subLote) {
		this.subLote = subLote;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	
	public Integer getIdDocumentoCobranca() {
		return idDocumentoCobranca;
	}

	public void setIdDocumentoCobranca(Integer idDocumentoCobranca) {
		this.idDocumentoCobranca = idDocumentoCobranca;
	}

	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}

	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}
	
	public EmitirDocumentoCobrancaHelper() {
	}
	
	public EmitirDocumentoCobrancaHelper(Integer idDocumentoCobranca, 
			Integer idLocalidade, 
			String descricaoLocalidade, 
			Integer codigoSetorComercial, 
			int numeroQuadra, 
			short lote, 
			short subLote, 
			Integer idImovel, 
			Short numeroDiasValidade, 
			Integer numeroOS, 
			String descricaoImovelPerfil,
			Date emissao, 
			Integer idCobrancaGrupo, 
			BigDecimal valorDocumento, 
			Integer idEmpresa, 
			String descricaoEmpresa, 
			String nomeAbreviadoGerencia, 
			String nomeGerencia, 
			Integer idLigacaoAguaSituacao, 
			Integer idLigacaoEsgotoSituacao, 
			Integer idGerencia, 
			Date dtImovelUltimaAlteracao,
            String descricaoSituacaoLigacaoAgua, 
            String descricaoSituacaoLigacaoEsgoto, 
            Integer idLeituraAnormalidade, 
            String descricaoLeitAnormalidade) {
		this.idDocumentoCobranca = idDocumentoCobranca;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.lote = lote;
		this.subLote = subLote;
		this.idImovel = idImovel;
		this.numeroDiasValidade = numeroDiasValidade;
		this.numeroOS = numeroOS;
		this.descricaoImovelPerfil = descricaoImovelPerfil;
		this.emissao = emissao;
		this.idCobrancaGrupo = idCobrancaGrupo;
		this.valorDocumento = valorDocumento;
		this.idEmpresa = idEmpresa;
		this.descricaoEmpresa = descricaoEmpresa;
		this.nomeAbreviadoGerencia = nomeAbreviadoGerencia;
		this.nomeGerencia = nomeGerencia;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idGerencia = idGerencia;
		this.dtImovelUltimaAlteracao = dtImovelUltimaAlteracao;
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
		this.idLeituraAnormalidade = idLeituraAnormalidade;
		this.descricaoLeitAnormalidade = descricaoLeitAnormalidade;
	}
	
	public EmitirDocumentoCobrancaHelper(Integer idDocumentoCobranca,
			Integer idLocalidade, 
			String descricaoLocalidade, 
			Integer codigoSetorComercial, 
			int numeroQuadra, 
			short lote, 
			short subLote, 
			Integer idImovel, 
			Short numeroDiasValidade, 
			Integer numeroOS, 
			String descricaoImovelPerfil, 
			Date emissao, 
			Integer idCobrancaGrupo, 
			BigDecimal valorDocumento, 
			Integer idEmpresa, 
			String descricaoEmpresa, 
			String nomeAbreviadoGerencia, 
			String nomeGerencia, 
			Integer idLigacaoAguaSituacao, 
			Integer idLigacaoEsgotoSituacao, 
			Integer idGerencia, 
			Date dtImovelUltimaAlteracao,
            String descricaoSituacaoLigacaoAgua, 
            String descricaoSituacaoLigacaoEsgoto, 
            Integer idLeituraAnormalidade, 
            String descricaoLeitAnormalidade, 
            Short codigoRota,
            Integer numeroSequencialRota) {
		this.idDocumentoCobranca = idDocumentoCobranca;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.lote = lote;
		this.subLote = subLote;
		this.idImovel = idImovel;
		this.numeroDiasValidade = numeroDiasValidade;
		this.numeroOS = numeroOS;
		this.descricaoImovelPerfil = descricaoImovelPerfil;
		this.emissao = emissao;
		this.idCobrancaGrupo = idCobrancaGrupo;
		this.valorDocumento = valorDocumento;
		this.idEmpresa = idEmpresa;
		this.descricaoEmpresa = descricaoEmpresa;
		this.nomeAbreviadoGerencia = nomeAbreviadoGerencia;
		this.nomeGerencia = nomeGerencia;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idGerencia = idGerencia;
		this.dtImovelUltimaAlteracao = dtImovelUltimaAlteracao;
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
		this.idLeituraAnormalidade = idLeituraAnormalidade;
		this.descricaoLeitAnormalidade = descricaoLeitAnormalidade;
		this.codigoRota = codigoRota;
		this.numeroSequencialRota = numeroSequencialRota;
	}
	
	public EmitirDocumentoCobrancaHelper(Integer idDocumentoCobranca, 
			Integer idLocalidade, 
			String descricaoLocalidade, 
			Integer codigoSetorComercial, 
			int numeroQuadra, 
			short lote, 
			short subLote, 
			Integer idImovel, 
			Short numeroDiasValidade, 
			Integer numeroOS, 
			String descricaoImovelPerfil, 
			Date emissao, 
			Integer idCobrancaGrupo, 
			BigDecimal valorDocumento, 
			Integer idEmpresa, 
			String descricaoEmpresa, 
			String nomeAbreviadoGerencia, 
			String nomeGerencia, 
			Integer idLigacaoAguaSituacao, 
			Integer idLigacaoEsgotoSituacao, 
			Integer idGerencia, 
			Date dtImovelUltimaAlteracao,
            String descricaoSituacaoLigacaoAgua, 
            String descricaoSituacaoLigacaoEsgoto, 
            Integer idLeituraAnormalidade, 
            String descricaoLeitAnormalidade,
            Date dataEmissaoPredecessor,
            int numeroSequenciaDocumento) {
		this.idDocumentoCobranca = idDocumentoCobranca;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.lote = lote;
		this.subLote = subLote;
		this.idImovel = idImovel;
		this.numeroDiasValidade = numeroDiasValidade;
		this.numeroOS = numeroOS;
		this.descricaoImovelPerfil = descricaoImovelPerfil;
		this.emissao = emissao;
		this.idCobrancaGrupo = idCobrancaGrupo;
		this.valorDocumento = valorDocumento;
		this.idEmpresa = idEmpresa;
		this.descricaoEmpresa = descricaoEmpresa;
		this.nomeAbreviadoGerencia = nomeAbreviadoGerencia;
		this.nomeGerencia = nomeGerencia;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idGerencia = idGerencia;
		this.dtImovelUltimaAlteracao = dtImovelUltimaAlteracao;
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
		this.idLeituraAnormalidade = idLeituraAnormalidade;
		this.descricaoLeitAnormalidade = descricaoLeitAnormalidade;
		this.dataEmissaoPredecessor = dataEmissaoPredecessor;
		this.numeroSequenciaDocumento = numeroSequenciaDocumento;
	}
	
	public EmitirDocumentoCobrancaHelper(Integer idDocumentoCobranca, 
			Integer idLocalidade, 
			String descricaoLocalidade, 
			Integer codigoSetorComercial, 
			int numeroQuadra, 
			short lote, 
			short subLote, 
			Integer idImovel, 
			Short numeroDiasValidade, 
			Integer numeroOS, 
			String descricaoImovelPerfil, 
			Date emissao, 
			Integer idCobrancaGrupo, 
			BigDecimal valorDocumento, 
			Integer idEmpresa, 
			String descricaoEmpresa, 
			String nomeAbreviadoGerencia, 
			String nomeGerencia, 
			Integer idLigacaoAguaSituacao, 
			Integer idLigacaoEsgotoSituacao, 
			Integer idGerencia, 
			Date dtImovelUltimaAlteracao,
            String descricaoSituacaoLigacaoAgua, 
            String descricaoSituacaoLigacaoEsgoto, 
            Integer idLeituraAnormalidade, 
            String descricaoLeitAnormalidade,
            Date dataEmissaoPredecessor,
            int numeroSequenciaDocumento,
            Integer numeroDiasVencimento,
            Short codigoRota,
            Integer numeroSequencialRota) {
		this.idDocumentoCobranca = idDocumentoCobranca;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.lote = lote;
		this.subLote = subLote;
		this.idImovel = idImovel;
		this.numeroDiasValidade = numeroDiasValidade;
		this.numeroOS = numeroOS;
		this.descricaoImovelPerfil = descricaoImovelPerfil;
		this.emissao = emissao;
		this.idCobrancaGrupo = idCobrancaGrupo;
		this.valorDocumento = valorDocumento;
		this.idEmpresa = idEmpresa;
		this.descricaoEmpresa = descricaoEmpresa;
		this.nomeAbreviadoGerencia = nomeAbreviadoGerencia;
		this.nomeGerencia = nomeGerencia;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idGerencia = idGerencia;
		this.dtImovelUltimaAlteracao = dtImovelUltimaAlteracao;
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
		this.idLeituraAnormalidade = idLeituraAnormalidade;
		this.descricaoLeitAnormalidade = descricaoLeitAnormalidade;
		this.dataEmissaoPredecessor = dataEmissaoPredecessor;
		this.numeroSequenciaDocumento = numeroSequenciaDocumento;
		this.numeroDiasVencimento = numeroDiasVencimento;
		this.codigoRota = codigoRota;
		this.numeroSequencialRota = numeroSequencialRota;
	}

	/**
	 * @return Retorna o campo descricaoSituacaoLigacaoAgua.
	 */
	public String getDescricaoSituacaoLigacaoAgua() {
		return descricaoSituacaoLigacaoAgua;
	}

	/**
	 * @param descricaoSituacaoLigacaoAgua O descricaoSituacaoLigacaoAgua a ser setado.
	 */
	public void setDescricaoSituacaoLigacaoAgua(String descricaoSituacaoLigacaoAgua) {
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo descricaoSituacaoLigacaoEsgoto.
	 */
	public String getDescricaoSituacaoLigacaoEsgoto() {
		return descricaoSituacaoLigacaoEsgoto;
	}

	/**
	 * @param descricaoSituacaoLigacaoEsgoto O descricaoSituacaoLigacaoEsgoto a ser setado.
	 */
	public void setDescricaoSituacaoLigacaoEsgoto(
			String descricaoSituacaoLigacaoEsgoto) {
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
	}

	/**
	 * @return Retorna o campo dtImovelUltimaAlteracao.
	 */
	public Date getDtImovelUltimaAlteracao() {
		return dtImovelUltimaAlteracao;
	}

	/**
	 * @param dtImovelUltimaAlteracao O dtImovelUltimaAlteracao a ser setado.
	 */
	public void setDtImovelUltimaAlteracao(Date dtImovelUltimaAlteracao) {
		this.dtImovelUltimaAlteracao = dtImovelUltimaAlteracao;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	public void setNumeroSequencialRota(Integer numeroSequencialRota) {
		this.numeroSequencialRota = numeroSequencialRota;
	}

	public Integer getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(Integer numeroOS) {
		this.numeroOS = numeroOS;
	}

	public Integer getNumeroDiasVencimento() {
		return numeroDiasVencimento;
	}

	public void setNumeroDiasVencimento(Integer numeroDiasVencimento) {
		this.numeroDiasVencimento = numeroDiasVencimento;
	}
	
	

}
