package gcom.micromedicao.bean;

import gcom.cadastro.imovel.Imovel;
import gcom.micromedicao.MovimentoRoteiroEmpresa;

import java.io.Serializable;

public class ImovelPorRotaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	private Imovel imovel;
	private MovimentoRoteiroEmpresa movimentoRoteiroEmpresa;

	public ImovelPorRotaHelper() {
	}
	
	public ImovelPorRotaHelper(Imovel imovel, MovimentoRoteiroEmpresa movimento) {
		this.imovel = imovel;
		this.movimentoRoteiroEmpresa = movimento;
	}
	
	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public MovimentoRoteiroEmpresa getMovimentoRoteiroEmpresa() {
		return movimentoRoteiroEmpresa;
	}

	public void setMovimentoRoteiroEmpresa(
			MovimentoRoteiroEmpresa movimentoRoteiroEmpresa) {
		this.movimentoRoteiroEmpresa = movimentoRoteiroEmpresa;
	}
}
