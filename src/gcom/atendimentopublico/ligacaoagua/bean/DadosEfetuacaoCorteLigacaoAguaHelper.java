package gcom.atendimentopublico.ligacaoagua.bean;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;

/**
 * [UC0460] Efetuar Instalação de Hidrômetro
 * 
 * Atualizar OS
 * 
 * @author Leonardo Regis
 * @date 19/09/2006
 */
public class DadosEfetuacaoCorteLigacaoAguaHelper {

	private OrdemServico ordemServico;
	private Imovel imovel;
	private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;
	private LigacaoAgua ligacaoAgua;
	
	private boolean veioEncerrarOS = false;
	private int qtdeParcelas;
	
	public int getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}

	public DadosEfetuacaoCorteLigacaoAguaHelper(){}

	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return hidrometroInstalacaoHistorico;
	}

	public void setHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public LigacaoAgua getLigacaoAgua() {
		return ligacaoAgua;
	}

	public void setLigacaoAgua(LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public boolean isVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(boolean veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

}
