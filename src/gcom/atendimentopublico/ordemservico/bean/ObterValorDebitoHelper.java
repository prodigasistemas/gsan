package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;

/**
 * [UC0475] Obter Valor Débito
 * 
 * Helper para auxiliar na obtenção dos dados do valor do débito
 * 
 * @author Leonardo Regis
 * @date 09/09/2006
 */
public class ObterValorDebitoHelper {
	
	private ImovelPerfil imovelPerfil = null;
	private Short situacaoMedicao = null;
	private HidrometroCapacidade hidrometroCapacidade = null;
	private ServicoTipo servicoTipo = null;
	private Short consideraEconomias = null;
	private Subcategoria subcategoria;
	private Categoria categoria;
	
	
	private Integer quantidadeEconomia = 0;
	
	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public ObterValorDebitoHelper(){}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public Short getSituacaoMedicao() {
		return situacaoMedicao;
	}

	public void setSituacaoMedicao(Short situacaoMedicao) {
		this.situacaoMedicao = situacaoMedicao;
	}

	public HidrometroCapacidade getHidrometroCapacidade() {
		return hidrometroCapacidade;
	}

	public void setHidrometroCapacidade(HidrometroCapacidade hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}

	public Integer getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	public void setQuantidadeEconomia(Integer quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}

	public Short getConsideraEconomias() {
		return consideraEconomias;
	}

	public void setConsideraEconomias(Short consideraEconomias) {
		this.consideraEconomias = consideraEconomias;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
