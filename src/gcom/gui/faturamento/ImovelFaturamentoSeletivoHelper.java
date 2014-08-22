package gcom.gui.faturamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.cadastro.imovel.Imovel;
import gcom.micromedicao.MovimentoRoteiroEmpresa;
import gcom.micromedicao.Rota;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;

public class ImovelFaturamentoSeletivoHelper {
	
	private Imovel imovel;
	private MovimentoRoteiroEmpresa movimento;
	private LigacaoAgua ligacaoAgua;
	private HidrometroInstalacaoHistorico hidrInstHistoricoAgua;
	private HidrometroInstalacaoHistorico hidrInstHistoricoPoco;
	private Rota rota;
	
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public MovimentoRoteiroEmpresa getMovimento() {
		return movimento;
	}
	public void setMovimento(MovimentoRoteiroEmpresa movimento) {
		this.movimento = movimento;
	}
	public LigacaoAgua getLigacaoAgua() {
		return ligacaoAgua;
	}
	public void setLigacaoAgua(LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}
	public HidrometroInstalacaoHistorico getHidrInstHistoricoAgua() {
		return hidrInstHistoricoAgua;
	}
	public void setHidrInstHistoricoAgua(
			HidrometroInstalacaoHistorico hidrInstHistoricoAgua) {
		this.hidrInstHistoricoAgua = hidrInstHistoricoAgua;
	}
	public HidrometroInstalacaoHistorico getHidrInstHistoricoPoco() {
		return hidrInstHistoricoPoco;
	}
	public void setHidrInstHistoricoPoco(
			HidrometroInstalacaoHistorico hidrInstHistoricoPoco) {
		this.hidrInstHistoricoPoco = hidrInstHistoricoPoco;
	}
	public Rota getRota() {
		return rota;
	}
	public void setRota(Rota rota) {
		this.rota = rota;
	}
}
