package gcom.atualizacaocadastral;

import java.util.Date;

import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

public class ImovelControleAtualizacaoCadastral {

	private Integer id;

	private Imovel imovel;

	private Date dataGeracao;
	private Date dataRetorno;
	private Date dataAprovacao;
	private Date dataProcessamento;
	private Date dataPreAprovacao;
	private Date dataLiberacaoProcessamento;

	private SituacaoAtualizacaoCadastral situacaoAtualizacaoCadastral;

	private ImovelRetorno imovelRetorno;

	private CadastroOcorrencia cadastroOcorrencia;

	private Integer lote;
	private Date dataGeracaoLote;
	private Date dataAprovacaoLote;
	private Date dataReprovacaoLote;
	
	private ImovelAtualizacaoCadastral imovelAtualizacaoCadastral;
	private Integer informativo;
	private Integer quantidadeVisitas;
	
	private Integer quantidadeVisitaNaoExcluidas;

	public ImovelControleAtualizacaoCadastral() {
		super();
	}

	public ImovelControleAtualizacaoCadastral(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public SituacaoAtualizacaoCadastral getSituacaoAtualizacaoCadastral() {
		return situacaoAtualizacaoCadastral;
	}

	public void setSituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral situacaoAtualizacaoCadastral) {
		this.situacaoAtualizacaoCadastral = situacaoAtualizacaoCadastral;
	}

	public ImovelRetorno getImovelRetorno() {
		return imovelRetorno;
	}

	public void setImovelRetorno(ImovelRetorno imovelRetorno) {
		this.imovelRetorno = imovelRetorno;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public CadastroOcorrencia getCadastroOcorrencia() {
		return cadastroOcorrencia;
	}

	public void setCadastroOcorrencia(CadastroOcorrencia cadastroOcorrencia) {
		this.cadastroOcorrencia = cadastroOcorrencia;
	}

	public Integer getLote() {
		return lote;
	}

	public void setLote(Integer lote) {
		this.lote = lote;
	}

	public Date getDataGeracaoLote() {
		return dataGeracaoLote;
	}

	public void setDataGeracaoLote(Date dataGeracaoLote) {
		this.dataGeracaoLote = dataGeracaoLote;
	}

	public Date getDataAprovacaoLote() {
		return dataAprovacaoLote;
	}

	public void setDataAprovacaoLote(Date dataAprovacaoLote) {
		this.dataAprovacaoLote = dataAprovacaoLote;
	}

	public boolean isEmFiscalizacao() {
		return this.situacaoAtualizacaoCadastral.getId().equals(SituacaoAtualizacaoCadastral.EM_FISCALIZACAO);
	}
	
	public boolean isAguardandoAnalise() {
		return SituacaoAtualizacaoCadastral.AGUARDANDO_ANALISE.equals(this.situacaoAtualizacaoCadastral.getId());
	}

	public boolean isFiscalizado() {
		return this.situacaoAtualizacaoCadastral.getId().equals(SituacaoAtualizacaoCadastral.FISCALIZADO);
	}

	public boolean isPreAprovado() {
		return this.situacaoAtualizacaoCadastral.getId().equals(SituacaoAtualizacaoCadastral.PRE_APROVADO);
	}

	public boolean isAprovado() {
		return this.situacaoAtualizacaoCadastral.getId().equals(SituacaoAtualizacaoCadastral.APROVADO);
	}
	
	public boolean isAtualizado() {
		return this.situacaoAtualizacaoCadastral.getId().equals(SituacaoAtualizacaoCadastral.ATUALIZADO);
	}
	
	public boolean isEmCampo() {
		return SituacaoAtualizacaoCadastral.EM_CAMPO.equals(this.situacaoAtualizacaoCadastral.getId());
	}
	
	public boolean isTransmitido() {
		return SituacaoAtualizacaoCadastral.TRANSMITIDO.equals(this.situacaoAtualizacaoCadastral.getId());
	}

	public boolean isRevisita() {
		return SituacaoAtualizacaoCadastral.REVISITA.equals(this.situacaoAtualizacaoCadastral.getId());
	}
	
	public boolean isEmRevisao() {
		return SituacaoAtualizacaoCadastral.EM_REVISAO.equals(this.situacaoAtualizacaoCadastral.getId());
	}
	
	public boolean isTransmitidoOuRevisita() {
		return SituacaoAtualizacaoCadastral.TRANSMITIDO.equals(this.situacaoAtualizacaoCadastral.getId()) || 
			   SituacaoAtualizacaoCadastral.REVISITA.equals(this.situacaoAtualizacaoCadastral.getId());
	}

	public boolean isProntoParaAprovacao() {
		return this.isPreAprovado() || this.isFiscalizado();
	}

	public boolean isImovelNovoOuNaSituacao(Integer idSituacao) {
		return this.situacaoAtualizacaoCadastral == null || this.situacaoAtualizacaoCadastral.getId().equals(idSituacao);
	}

	public Date getDataPreAprovacao() {
		return dataPreAprovacao;
	}

	public void setDataPreAprovacao(Date dataPreAprovacao) {
		this.dataPreAprovacao = dataPreAprovacao;
	}
	
	public Date getDataReprovacaoLote() {
		return dataReprovacaoLote;
	}

	public void setDataReprovacaoLote(Date dataReprovacaoLote) {
		this.dataReprovacaoLote = dataReprovacaoLote;
	}

	public Date getDataLiberacaoProcessamento() {
		return this.dataLiberacaoProcessamento;
	}

	public void setDataLiberacaoProcessamento(Date dataLiberacaoProcessamento) {
		this.dataLiberacaoProcessamento = dataLiberacaoProcessamento;
	}

	public ImovelAtualizacaoCadastral getImovelAtualizacaoCadastral() {
		return imovelAtualizacaoCadastral;
	}

	public void setImovelAtualizacaoCadastral(ImovelAtualizacaoCadastral imovelAtualizacaoCadastral) {
		this.imovelAtualizacaoCadastral = imovelAtualizacaoCadastral;
	}
	
	public Integer getInformativo() {
		return informativo;
	}

	public void setInformativo(Integer informativo) {
		this.informativo = informativo;
	}
	
	public Integer getQuantidadeVisitas() {
		return quantidadeVisitas;
	}

	public void setQuantidadeVisitas(Integer quantidadeVisitas) {
		this.quantidadeVisitas = quantidadeVisitas;
	}

	public boolean ehInformativo() {
		return informativo != null && informativo.intValue() == 1;
	}

	public Integer getQuantidadeVisitaNaoExcluidas() {
		if (quantidadeVisitaNaoExcluidas == null) {
			return 0;
		}
		return quantidadeVisitaNaoExcluidas;
	}

	public void setQuantidadeVisitaNaoExcluidas(Integer quantidadeVisitaNaoExcluidas) {
		this.quantidadeVisitaNaoExcluidas = quantidadeVisitaNaoExcluidas;
	}
	
	public boolean fazParteArquivoTodasSituacoes() {
		return this.situacaoAtualizacaoCadastral.getId().equals(SituacaoAtualizacaoCadastral.EM_CAMPO) || 
			   this.situacaoAtualizacaoCadastral.getId().equals(SituacaoAtualizacaoCadastral.TRANSMITIDO) ||
			   this.situacaoAtualizacaoCadastral.getId().equals(SituacaoAtualizacaoCadastral.REVISITA) ||
			   this.situacaoAtualizacaoCadastral.getId().equals(SituacaoAtualizacaoCadastral.EM_REVISAO);
	}
	

	public boolean quantidadeVisitasMenorQuePermitido() {
		return this.quantidadeVisitas.intValue() < Visita.QUANTIDADE_MAXIMA_SEM_PRE_AGENDAMENTO.intValue();
	}
	
	public void verificarInformativoArquivoTodasSituacoes(Date dataUltimaTransmissao) {
		this.informativo = ConstantesSistema.SIM.intValue();

		if (fazParteArquivoTodasSituacoes()) {
			if (isEmRevisao()) {
				this.informativo = ConstantesSistema.NAO.intValue();

			} else if (isEmCampo() && quantidadeVisitasMenorQuePermitido()) { 
				this.informativo = ConstantesSistema.NAO.intValue();
				
			} else if (isTransmitidoOuRevisita() && permiteRevisita() && dataRetornoMenorOuIgual(dataUltimaTransmissao)) {
				this.informativo = ConstantesSistema.NAO.intValue();
			}
		}
	}

	private boolean permiteRevisita() {
		return this.cadastroOcorrencia.getIndicadorVisita().intValue() == ConstantesSistema.SIM.intValue();
	}
	
	private boolean dataRetornoMenorOuIgual(Date dataUltimaTransmissao) {
		return Util.formatarDataInicial(this.dataRetorno).before(Util.formatarDataFinal(dataUltimaTransmissao)) ||
			   Util.formatarDataInicial(this.dataRetorno).equals(Util.formatarDataFinal(dataUltimaTransmissao));
	}
}
