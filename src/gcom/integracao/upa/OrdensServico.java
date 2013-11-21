package gcom.integracao.upa;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OrdensServico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer osUnidadeExecutora;

    /** nullable persistent field */
    private String osUsuarioExecutora;

    /** nullable persistent field */
    private Integer osUnidadeCentralProgramacao;

    /** persistent field */
    private Date osDataCadastro;

    /** persistent field */
    private Date osDataInicio;

    /** persistent field */
    private Date osDataEncerramento;

    /** persistent field */
    private int osSequencia;

    /** persistent field */
    private int tipoServicoId;

    /** persistent field */
    private int servicoId;

    /** nullable persistent field */
    private Integer servicoRetornoId;

    /** nullable persistent field */
    private String osSolicitacao;

    /** nullable persistent field */
    private String osMatriculaNumero;

    /** persistent field */
    private String osMatriculaReferencia;

    /** nullable persistent field */
    private String osInscricao;

    /** nullable persistent field */
    private String osSolicitante;

    /** nullable persistent field */
    private String osTelefoneSolicitante;

    /** persistent field */
    private String osEndereco;

    /** nullable persistent field */
    private String osPontoReferencia;

    /** persistent field */
    private String osBairro;

    /** persistent field */
    private String osLocalidade;

    /** persistent field */
    private String osElo;

    /** persistent field */
    private String osSetor;

    /** persistent field */
    private String osQuadra;

    /** nullable persistent field */
    private Integer equipeId;

    /** nullable persistent field */
    private Integer palmId;

    /** nullable persistent field */
    private String osPlacaVeiculo;

    /** nullable persistent field */
    private Integer motivoEncerramentoId;

    /** nullable persistent field */
    private String osObservacao;

    /** persistent field */
    private char osSituacao;

    /** persistent field */
    private Date osSituacaoData;

    /** nullable persistent field */
    private Date osExecucaoInicio;

    /** nullable persistent field */
    private Date osExecucaoEncerramento;

    /** nullable persistent field */
    private String osReaterro;

    /** nullable persistent field */
    private String osReposicaoCalcada;

    /** persistent field */
    private int osMetroExcedente;

    /** nullable persistent field */
    private Integer osPrioridade;

    /** nullable persistent field */
    private String osParecerTramite;

    /** nullable persistent field */
    private Integer osPavimentoRua;

    /** nullable persistent field */
    private Double osAreaPvtoRua;

    /** nullable persistent field */
    private Integer osPavimentoCalcada;

    /** nullable persistent field */
    private Double osAreaPvtoCalcada;

    /** nullable persistent field */
    private String osParecerEncerramento;

    /** nullable persistent field */
    private Integer osMotivoEncerramento;

    /** persistent field */
    private boolean osRetro;

    /** persistent field */
    private boolean osCompressor;

    /** persistent field */
    private boolean osFimsemana;

    /** full constructor */
    public OrdensServico(Integer id, Integer osUnidadeExecutora, String osUsuarioExecutora, Integer osUnidadeCentralProgramacao, Date osDataCadastro, Date osDataInicio, Date osDataEncerramento, int osSequencia, int tipoServicoId, int servicoId, Integer servicoRetornoId, String osSolicitacao, String osMatriculaNumero, String osMatriculaReferencia, String osInscricao, String osSolicitante, String osTelefoneSolicitante, String osEndereco, String osPontoReferencia, String osBairro, String osLocalidade, String osElo, String osSetor, String osQuadra, Integer equipeId, Integer palmId, String osPlacaVeiculo, Integer motivoEncerramentoId, String osObservacao, char osSituacao, Date osSituacaoData, Date osExecucaoInicio, Date osExecucaoEncerramento, String osReaterro, String osReposicaoCalcada, int osMetroExcedente, Integer osPrioridade, String osParecerTramite, Integer osPavimentoRua, Double osAreaPvtoRua, Integer osPavimentoCalcada, Double osAreaPvtoCalcada, String osParecerEncerramento, Integer osMotivoEncerramento, boolean osRetro, boolean osCompressor, boolean osFimsemana) {
        this.id = id;
        this.osUnidadeExecutora = osUnidadeExecutora;
        this.osUsuarioExecutora = osUsuarioExecutora;
        this.osUnidadeCentralProgramacao = osUnidadeCentralProgramacao;
        this.osDataCadastro = osDataCadastro;
        this.osDataInicio = osDataInicio;
        this.osDataEncerramento = osDataEncerramento;
        this.osSequencia = osSequencia;
        this.tipoServicoId = tipoServicoId;
        this.servicoId = servicoId;
        this.servicoRetornoId = servicoRetornoId;
        this.osSolicitacao = osSolicitacao;
        this.osMatriculaNumero = osMatriculaNumero;
        this.osMatriculaReferencia = osMatriculaReferencia;
        this.osInscricao = osInscricao;
        this.osSolicitante = osSolicitante;
        this.osTelefoneSolicitante = osTelefoneSolicitante;
        this.osEndereco = osEndereco;
        this.osPontoReferencia = osPontoReferencia;
        this.osBairro = osBairro;
        this.osLocalidade = osLocalidade;
        this.osElo = osElo;
        this.osSetor = osSetor;
        this.osQuadra = osQuadra;
        this.equipeId = equipeId;
        this.palmId = palmId;
        this.osPlacaVeiculo = osPlacaVeiculo;
        this.motivoEncerramentoId = motivoEncerramentoId;
        this.osObservacao = osObservacao;
        this.osSituacao = osSituacao;
        this.osSituacaoData = osSituacaoData;
        this.osExecucaoInicio = osExecucaoInicio;
        this.osExecucaoEncerramento = osExecucaoEncerramento;
        this.osReaterro = osReaterro;
        this.osReposicaoCalcada = osReposicaoCalcada;
        this.osMetroExcedente = osMetroExcedente;
        this.osPrioridade = osPrioridade;
        this.osParecerTramite = osParecerTramite;
        this.osPavimentoRua = osPavimentoRua;
        this.osAreaPvtoRua = osAreaPvtoRua;
        this.osPavimentoCalcada = osPavimentoCalcada;
        this.osAreaPvtoCalcada = osAreaPvtoCalcada;
        this.osParecerEncerramento = osParecerEncerramento;
        this.osMotivoEncerramento = osMotivoEncerramento;
        this.osRetro = osRetro;
        this.osCompressor = osCompressor;
        this.osFimsemana = osFimsemana;
    }

    /** default constructor */
    public OrdensServico() {
    }

    /** minimal constructor */
    public OrdensServico(Integer id, Date osDataCadastro, Date osDataInicio, Date osDataEncerramento, int osSequencia, int tipoServicoId, int servicoId, String osMatriculaReferencia, String osEndereco, String osBairro, String osLocalidade, String osElo, String osSetor, String osQuadra, char osSituacao, Date osSituacaoData, int osMetroExcedente, boolean osRetro, boolean osCompressor, boolean osFimsemana) {
        this.id = id;
        this.osDataCadastro = osDataCadastro;
        this.osDataInicio = osDataInicio;
        this.osDataEncerramento = osDataEncerramento;
        this.osSequencia = osSequencia;
        this.tipoServicoId = tipoServicoId;
        this.servicoId = servicoId;
        this.osMatriculaReferencia = osMatriculaReferencia;
        this.osEndereco = osEndereco;
        this.osBairro = osBairro;
        this.osLocalidade = osLocalidade;
        this.osElo = osElo;
        this.osSetor = osSetor;
        this.osQuadra = osQuadra;
        this.osSituacao = osSituacao;
        this.osSituacaoData = osSituacaoData;
        this.osMetroExcedente = osMetroExcedente;
        this.osRetro = osRetro;
        this.osCompressor = osCompressor;
        this.osFimsemana = osFimsemana;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOsUnidadeExecutora() {
        return this.osUnidadeExecutora;
    }

    public void setOsUnidadeExecutora(Integer osUnidadeExecutora) {
        this.osUnidadeExecutora = osUnidadeExecutora;
    }

    public String getOsUsuarioExecutora() {
        return this.osUsuarioExecutora;
    }

    public void setOsUsuarioExecutora(String osUsuarioExecutora) {
        this.osUsuarioExecutora = osUsuarioExecutora;
    }

    public Integer getOsUnidadeCentralProgramacao() {
        return this.osUnidadeCentralProgramacao;
    }

    public void setOsUnidadeCentralProgramacao(Integer osUnidadeCentralProgramacao) {
        this.osUnidadeCentralProgramacao = osUnidadeCentralProgramacao;
    }

    public Date getOsDataCadastro() {
        return this.osDataCadastro;
    }

    public void setOsDataCadastro(Date osDataCadastro) {
        this.osDataCadastro = osDataCadastro;
    }

    public Date getOsDataInicio() {
        return this.osDataInicio;
    }

    public void setOsDataInicio(Date osDataInicio) {
        this.osDataInicio = osDataInicio;
    }

    public Date getOsDataEncerramento() {
        return this.osDataEncerramento;
    }

    public void setOsDataEncerramento(Date osDataEncerramento) {
        this.osDataEncerramento = osDataEncerramento;
    }

    public int getOsSequencia() {
        return this.osSequencia;
    }

    public void setOsSequencia(int osSequencia) {
        this.osSequencia = osSequencia;
    }

    public int getTipoServicoId() {
        return this.tipoServicoId;
    }

    public void setTipoServicoId(int tipoServicoId) {
        this.tipoServicoId = tipoServicoId;
    }

    public int getServicoId() {
        return this.servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public Integer getServicoRetornoId() {
        return this.servicoRetornoId;
    }

    public void setServicoRetornoId(Integer servicoRetornoId) {
        this.servicoRetornoId = servicoRetornoId;
    }

    public String getOsSolicitacao() {
        return this.osSolicitacao;
    }

    public void setOsSolicitacao(String osSolicitacao) {
        this.osSolicitacao = osSolicitacao;
    }

    public String getOsMatriculaNumero() {
        return this.osMatriculaNumero;
    }

    public void setOsMatriculaNumero(String osMatriculaNumero) {
        this.osMatriculaNumero = osMatriculaNumero;
    }

    public String getOsMatriculaReferencia() {
        return this.osMatriculaReferencia;
    }

    public void setOsMatriculaReferencia(String osMatriculaReferencia) {
        this.osMatriculaReferencia = osMatriculaReferencia;
    }

    public String getOsInscricao() {
        return this.osInscricao;
    }

    public void setOsInscricao(String osInscricao) {
        this.osInscricao = osInscricao;
    }

    public String getOsSolicitante() {
        return this.osSolicitante;
    }

    public void setOsSolicitante(String osSolicitante) {
        this.osSolicitante = osSolicitante;
    }

    public String getOsTelefoneSolicitante() {
        return this.osTelefoneSolicitante;
    }

    public void setOsTelefoneSolicitante(String osTelefoneSolicitante) {
        this.osTelefoneSolicitante = osTelefoneSolicitante;
    }

    public String getOsEndereco() {
        return this.osEndereco;
    }

    public void setOsEndereco(String osEndereco) {
        this.osEndereco = osEndereco;
    }

    public String getOsPontoReferencia() {
        return this.osPontoReferencia;
    }

    public void setOsPontoReferencia(String osPontoReferencia) {
        this.osPontoReferencia = osPontoReferencia;
    }

    public String getOsBairro() {
        return this.osBairro;
    }

    public void setOsBairro(String osBairro) {
        this.osBairro = osBairro;
    }

    public String getOsLocalidade() {
        return this.osLocalidade;
    }

    public void setOsLocalidade(String osLocalidade) {
        this.osLocalidade = osLocalidade;
    }

    public String getOsElo() {
        return this.osElo;
    }

    public void setOsElo(String osElo) {
        this.osElo = osElo;
    }

    public String getOsSetor() {
        return this.osSetor;
    }

    public void setOsSetor(String osSetor) {
        this.osSetor = osSetor;
    }

    public String getOsQuadra() {
        return this.osQuadra;
    }

    public void setOsQuadra(String osQuadra) {
        this.osQuadra = osQuadra;
    }

    public Integer getEquipeId() {
        return this.equipeId;
    }

    public void setEquipeId(Integer equipeId) {
        this.equipeId = equipeId;
    }

    public Integer getPalmId() {
        return this.palmId;
    }

    public void setPalmId(Integer palmId) {
        this.palmId = palmId;
    }

    public String getOsPlacaVeiculo() {
        return this.osPlacaVeiculo;
    }

    public void setOsPlacaVeiculo(String osPlacaVeiculo) {
        this.osPlacaVeiculo = osPlacaVeiculo;
    }

    public Integer getMotivoEncerramentoId() {
        return this.motivoEncerramentoId;
    }

    public void setMotivoEncerramentoId(Integer motivoEncerramentoId) {
        this.motivoEncerramentoId = motivoEncerramentoId;
    }

    public String getOsObservacao() {
        return this.osObservacao;
    }

    public void setOsObservacao(String osObservacao) {
        this.osObservacao = osObservacao;
    }

    public char getOsSituacao() {
        return this.osSituacao;
    }

    public void setOsSituacao(char osSituacao) {
        this.osSituacao = osSituacao;
    }

    public Date getOsSituacaoData() {
        return this.osSituacaoData;
    }

    public void setOsSituacaoData(Date osSituacaoData) {
        this.osSituacaoData = osSituacaoData;
    }

    public Date getOsExecucaoInicio() {
        return this.osExecucaoInicio;
    }

    public void setOsExecucaoInicio(Date osExecucaoInicio) {
        this.osExecucaoInicio = osExecucaoInicio;
    }

    public Date getOsExecucaoEncerramento() {
        return this.osExecucaoEncerramento;
    }

    public void setOsExecucaoEncerramento(Date osExecucaoEncerramento) {
        this.osExecucaoEncerramento = osExecucaoEncerramento;
    }

    public String getOsReaterro() {
        return this.osReaterro;
    }

    public void setOsReaterro(String osReaterro) {
        this.osReaterro = osReaterro;
    }

    public String getOsReposicaoCalcada() {
        return this.osReposicaoCalcada;
    }

    public void setOsReposicaoCalcada(String osReposicaoCalcada) {
        this.osReposicaoCalcada = osReposicaoCalcada;
    }

    public int getOsMetroExcedente() {
        return this.osMetroExcedente;
    }

    public void setOsMetroExcedente(int osMetroExcedente) {
        this.osMetroExcedente = osMetroExcedente;
    }

    public Integer getOsPrioridade() {
        return this.osPrioridade;
    }

    public void setOsPrioridade(Integer osPrioridade) {
        this.osPrioridade = osPrioridade;
    }

    public String getOsParecerTramite() {
        return this.osParecerTramite;
    }

    public void setOsParecerTramite(String osParecerTramite) {
        this.osParecerTramite = osParecerTramite;
    }

    public Integer getOsPavimentoRua() {
        return this.osPavimentoRua;
    }

    public void setOsPavimentoRua(Integer osPavimentoRua) {
        this.osPavimentoRua = osPavimentoRua;
    }

    public Double getOsAreaPvtoRua() {
        return this.osAreaPvtoRua;
    }

    public void setOsAreaPvtoRua(Double osAreaPvtoRua) {
        this.osAreaPvtoRua = osAreaPvtoRua;
    }

    public Integer getOsPavimentoCalcada() {
        return this.osPavimentoCalcada;
    }

    public void setOsPavimentoCalcada(Integer osPavimentoCalcada) {
        this.osPavimentoCalcada = osPavimentoCalcada;
    }

    public Double getOsAreaPvtoCalcada() {
        return this.osAreaPvtoCalcada;
    }

    public void setOsAreaPvtoCalcada(Double osAreaPvtoCalcada) {
        this.osAreaPvtoCalcada = osAreaPvtoCalcada;
    }

    public String getOsParecerEncerramento() {
        return this.osParecerEncerramento;
    }

    public void setOsParecerEncerramento(String osParecerEncerramento) {
        this.osParecerEncerramento = osParecerEncerramento;
    }

    public Integer getOsMotivoEncerramento() {
        return this.osMotivoEncerramento;
    }

    public void setOsMotivoEncerramento(Integer osMotivoEncerramento) {
        this.osMotivoEncerramento = osMotivoEncerramento;
    }

    public boolean isOsRetro() {
        return this.osRetro;
    }

    public void setOsRetro(boolean osRetro) {
        this.osRetro = osRetro;
    }

    public boolean isOsCompressor() {
        return this.osCompressor;
    }

    public void setOsCompressor(boolean osCompressor) {
        this.osCompressor = osCompressor;
    }

    public boolean isOsFimsemana() {
        return this.osFimsemana;
    }

    public void setOsFimsemana(boolean osFimsemana) {
        this.osFimsemana = osFimsemana;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
