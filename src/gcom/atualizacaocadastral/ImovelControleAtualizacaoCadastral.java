package gcom.atualizacaocadastral;

import java.util.Date;

import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.Imovel;

public class ImovelControleAtualizacaoCadastral {

	private Integer id;
	private Imovel imovel;
	private Date dataGeracao;
	private Date dataRetorno;
	private Date dataAprovacao;
	private Date dataProcessamento;
	private SituacaoAtualizacaoCadastral situacaoAtualizacaoCadastral;
	private ImovelRetorno imovelRetorno;
	private CadastroOcorrencia cadastroOcorrencia;
	
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
	public void setSituacaoAtualizacaoCadastral(
			SituacaoAtualizacaoCadastral situacaoAtualizacaoCadastral) {
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
	
}
