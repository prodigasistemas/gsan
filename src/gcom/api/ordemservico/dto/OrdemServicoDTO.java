package gcom.api.ordemservico.dto;

import java.math.BigDecimal;
import java.util.Date;

import gcom.util.FormatoData;
import gcom.util.Util;

public class OrdemServicoDTO {

	private Integer id;
	
	private Integer situacao;
	
	private String dataGeracao;
	
	private String servicoTipoDescricao;
	
	private BigDecimal servicoTipoValor;
	
	private String observacao;
	
	private String dataProgramacao;
	
	private String equipeProgramacao;
	
	private Integer operacao;
	
    private String dataEncerramento;
    
    private Integer motivoEncerramento;

    private String parecerEncerramento;
	
    private Integer idUsuarioEncerramento;
    
	private ImovelDTO imovel;
	
	private HidrometroDTO hidrometro;
	
	private HidrometroInstalacaoDTO hidrometroInstalacao;
	
	private LigacaoAguaDTO ligacaoAgua;
	
	public OrdemServicoDTO(
			Integer id, 
			Integer situacao,
			Date dataGeracao, 
			String servicoTipoDescricao, 
			BigDecimal servicoTipoValor,
			String observacao, 
			Date dataProgramacao, 
			String equipeProgramacao, 
			Integer operacao) {
		
		super();

		this.id = id;
		this.situacao = situacao;
		this.dataGeracao = Util.formatarData(dataGeracao, FormatoData.AMERICANO_COM_TRACO);
		this.servicoTipoDescricao = servicoTipoDescricao;
		this.servicoTipoValor = servicoTipoValor;
		this.observacao = observacao;
		this.dataProgramacao = Util.formatarData(dataProgramacao, FormatoData.AMERICANO_COM_TRACO);
		this.equipeProgramacao = equipeProgramacao;
		this.operacao = operacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getServicoTipoDescricao() {
		return servicoTipoDescricao;
	}

	public void setServicoTipoDescricao(String servicoTipoDescricao) {
		this.servicoTipoDescricao = servicoTipoDescricao;
	}

	public BigDecimal getServicoTipoValor() {
		return servicoTipoValor;
	}

	public void setServicoTipoValor(BigDecimal servicoTipoValor) {
		this.servicoTipoValor = servicoTipoValor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDataProgramacao() {
		return dataProgramacao;
	}

	public void setDataProgramacao(String dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	public String getEquipeProgramacao() {
		return equipeProgramacao;
	}

	public void setEquipeProgramacao(String equipeProgramacao) {
		this.equipeProgramacao = equipeProgramacao;
	}

	public Integer getOperacao() {
		return operacao;
	}

	public void setOperacao(Integer operacao) {
		this.operacao = operacao;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Integer getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(Integer motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public Integer getIdUsuarioEncerramento() {
		return idUsuarioEncerramento;
	}

	public void setIdUsuarioEncerramento(Integer idUsuarioEncerramento) {
		this.idUsuarioEncerramento = idUsuarioEncerramento;
	}

	public ImovelDTO getImovel() {
		return imovel;
	}

	public void setImovel(ImovelDTO imovel) {
		this.imovel = imovel;
	}

	public HidrometroDTO getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(HidrometroDTO hidrometro) {
		this.hidrometro = hidrometro;
	}

	public HidrometroInstalacaoDTO getHidrometroInstalacao() {
		return hidrometroInstalacao;
	}

	public void setHidrometroInstalacao(HidrometroInstalacaoDTO hidrometroInstalacao) {
		this.hidrometroInstalacao = hidrometroInstalacao;
	}

	public LigacaoAguaDTO getLigacaoAgua() {
		return ligacaoAgua;
	}

	public void setLigacaoAgua(LigacaoAguaDTO ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}
}
