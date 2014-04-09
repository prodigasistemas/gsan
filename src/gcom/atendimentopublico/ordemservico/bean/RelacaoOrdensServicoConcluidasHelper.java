package gcom.atendimentopublico.ordemservico.bean;


/**
 * [UC0732] Gerar Relatorio Acompanhamento de Ordem de Servico de Hidrometro
 * 			(Relatorio Relacao das Ordens de Servico Concluidas)
 * 
 * @author Ivan Sérgio
 * @date 04/04/2008
 */
public class RelacaoOrdensServicoConcluidasHelper {
	private Integer idOrdemServico;
	private Integer idImovel;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Short lote;
	private Short subLote;
	private String dataGeracao;
	private String dataEncerramento;
	private String motivoEncerramento;
	private Integer idSetorComercial;
	private String nomeSetorComercial;
	private Integer idMotivoEncerramento;
	private Integer idLocalInstalacaoHidrometro;
	private String descricaoLocalInstalacaoHidrometro;
	
	public String getDescricaoLocalInstalacaoHidrometro() {
		return descricaoLocalInstalacaoHidrometro;
	}
	public void setDescricaoLocalInstalacaoHidrometro(
			String descricaoLocalInstalacaoHidrometro) {
		this.descricaoLocalInstalacaoHidrometro = descricaoLocalInstalacaoHidrometro;
	}
	public Integer getIdLocalInstalacaoHidrometro() {
		return idLocalInstalacaoHidrometro;
	}
	public void setIdLocalInstalacaoHidrometro(Integer idLocalInstalacaoHidrometro) {
		this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
	}
	public Integer getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}
	public void setIdMotivoEncerramento(Integer idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
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
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public Short getLote() {
		return lote;
	}
	public void setLote(Short lote) {
		this.lote = lote;
	}
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	public Short getSubLote() {
		return subLote;
	}
	public void setSubLote(Short subLote) {
		this.subLote = subLote;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
		
}
