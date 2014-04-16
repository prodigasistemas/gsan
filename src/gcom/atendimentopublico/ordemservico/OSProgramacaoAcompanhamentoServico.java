package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.imovel.Imovel;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OSProgramacaoAcompanhamentoServico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataProgramacao;

    /** persistent field */
    private int sequencialProgramacao;

    /** persistent field */
    private Imovel imovel;

    /** nullable persistent field */
    private String descricaoPontoReferencia;

    /** nullable persistent field */
    private String numeroImovel;

    /** persistent field */
    private String nomeSolicitante;

    /** nullable persistent field */
    private String numeroTelefone;

    /** persistent field */
    private String descricaoEndereco;

    /** nullable persistent field */
    private String descricaoLigacaoAguaSituacao;

    /** nullable persistent field */
    private String descricaoLigacaoEsgotoSituacao;

    /** nullable persistent field */
    private String inscricaoImovel;

    /** nullable persistent field */
    private String numeroHidrometro;

    /** nullable persistent field */
    private String descricaoHidrometroCapacidade;

    /** persistent field */
    private short indicadorAtualizacaoOS;

    /** persistent field */
    private short indicadorTrasmissaoOS;

    /** persistent field */
    private Date dataUltimaAlteracao;

    /** persistent field */
    private ServicoTipo servicoTipo;

    /** persistent field */
    private OrdemServicoSituacao ordemServicoSituacao;

    /** persistent field */
    private OrdemServico ordemServico;

    /** persistent field */
    private Equipe equipe;

    /** persistent field */
    private ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico;
    
    /** persistent field */
    private EquipamentosEspeciais equipamentosEspeciaisFaltante;

    /** persistent field */
    private OsProgramNaoEncerMotivo osProgramacaoNaoEncerramentoMotivo;
    
	/** persistent field */
	private short indicadorExcluido;
    
    /**
     * Description of the Field
     */
    public final static short INDICADOR_TRANSMISSAO_SIM = 1;
    
    /**
     * Description of the Field
     */
    public final static short INDICADOR_TRANSMISSAO_NAO = 2;

    /**
     * Description of the Field
     */
    public final static short INDICADOR_ATUALIZA_OS_SIM = 1;
    
    /**
     * Description of the Field
     */
    public final static short INDICADOR_ATUALIZA_OS_NAO = 2;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer osasId) {
        this.id = osasId;
    }

    public Date getDataProgramacao() {
        return this.dataProgramacao;
    }

    public void setDataProgramacao(Date osasDtprogramacao) {
        this.dataProgramacao = osasDtprogramacao;
    }

    public int getSequencialProgramacao() {
        return this.sequencialProgramacao;
    }

    public void setSequencialProgramacao(int osasNnseqprogramacao) {
        this.sequencialProgramacao = osasNnseqprogramacao;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public String getDescricaoPontoReferencia() {
        return this.descricaoPontoReferencia;
    }

    public void setDescricaoPontoReferencia(String osasDspontoreferencia) {
        this.descricaoPontoReferencia = osasDspontoreferencia;
    }

    public String getNumeroImovel() {
        return this.numeroImovel;
    }

    public void setNumeroImovel(String osasNnimovel) {
        this.numeroImovel = osasNnimovel;
    }

    public String getNomeSolicitante() {
        return this.nomeSolicitante;
    }

    public void setNomeSolicitante(String osasNmsolicitante) {
        this.nomeSolicitante = osasNmsolicitante;
    }

    public String getNumeroTelefone() {
        return this.numeroTelefone;
    }

    public void setNumeroTelefone(String osasNnfone) {
        this.numeroTelefone = osasNnfone;
    }

    public String getDescricaoEndereco() {
        return this.descricaoEndereco;
    }

    public void setDescricaoEndereco(String osasDsendereco) {
        this.descricaoEndereco = osasDsendereco;
    }

    public String getDescricaoLigacaoAguaSituacao() {
        return this.descricaoLigacaoAguaSituacao;
    }

    public void setDescricaoLigacaoAguaSituacao(String osasDsligaguasituacao) {
        this.descricaoLigacaoAguaSituacao = osasDsligaguasituacao;
    }

    public String getDescricaoLigacaoEsgotoSituacao() {
        return this.descricaoLigacaoEsgotoSituacao;
    }

    public void setDescricaoLigacaoEsgotoSituacao(String osasDsligesgotosituacao) {
        this.descricaoLigacaoEsgotoSituacao = osasDsligesgotosituacao;
    }

    public String getInscricaoImovel() {
        return this.inscricaoImovel;
    }

    public void setInscricaoImovel(String osasInscricaoimovel) {
        this.inscricaoImovel = osasInscricaoimovel;
    }

    public String getNumeroHidrometro() {
        return this.numeroHidrometro;
    }

    public void setNumeroHidrometro(String osasNnhidrometro) {
        this.numeroHidrometro = osasNnhidrometro;
    }

    public String getDescricaoHidrometroCapacidade() {
        return this.descricaoHidrometroCapacidade;
    }

    public void setDescricaoHidrometroCapacidade(String osasDshidrometrocapacidade) {
        this.descricaoHidrometroCapacidade = osasDshidrometrocapacidade;
    }

    public short getIndicadorAtualizacaoOS() {
        return this.indicadorAtualizacaoOS;
    }

    public void setIndicadorAtualizacaoOS(short osasIcatualizaos) {
        this.indicadorAtualizacaoOS = osasIcatualizaos;
    }

    public short getIndicadorTrasmissaoOS() {
        return this.indicadorTrasmissaoOS;
    }

    public void setIndicadorTrasmissaoOS(short osasIctransmissao) {
        this.indicadorTrasmissaoOS = osasIctransmissao;
    }

    public Date getDataUltimaAlteracao() {
        return this.dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date osasTmultimaalteracao) {
        this.dataUltimaAlteracao = osasTmultimaalteracao;
    }

    public ServicoTipo getServicoTipo() {
        return this.servicoTipo;
    }

    public void setServicoTipo(ServicoTipo servicoTipo) {
        this.servicoTipo = servicoTipo;
    }

    public OrdemServicoSituacao getOrdemServicoSituacao() {
        return this.ordemServicoSituacao;
    }

    public void setOrdemServicoSituacao(OrdemServicoSituacao ordemServicoSituacao) {
        this.ordemServicoSituacao = ordemServicoSituacao;
    }

    public OrdemServico getOrdemServico() {
        return this.ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Equipe getEquipe() {
        return this.equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public ArquivoTextoAcompanhamentoServico getArquivoTextoAcompanhamentoServico() {
        return this.arquivoTextoAcompanhamentoServico;
    }

    public void setArquivoTextoAcompanhamentoServico(ArquivoTextoAcompanhamentoServico arqTxtAcompServico) {
        this.arquivoTextoAcompanhamentoServico = arqTxtAcompServico;
    }

    public OsProgramNaoEncerMotivo getOsProgramacaoNaoEncerramentoMotivo() {
        return this.osProgramacaoNaoEncerramentoMotivo;
    }

    public void setOsProgramacaoNaoEncerramentoMotivo(OsProgramNaoEncerMotivo osProgNaoEncerMotivo) {
        this.osProgramacaoNaoEncerramentoMotivo = osProgNaoEncerMotivo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("osasId", getId())
            .toString();
    }

	public EquipamentosEspeciais getEquipamentosEspeciaisFaltante() {
		return equipamentosEspeciaisFaltante;
	}

	public void setEquipamentosEspeciaisFaltante(
			EquipamentosEspeciais equipamentosEspeciaisFaltante) {
		this.equipamentosEspeciaisFaltante = equipamentosEspeciaisFaltante;
	}

	public short getIndicadorExcluido() {
		return indicadorExcluido;
	}

	public void setIndicadorExcluido(short indicadorExcluido) {
		this.indicadorExcluido = indicadorExcluido;
	}
	
	

}
