package gcom.gui.micromedicao;


/**
 * [UC1000] Informar Medidor de Energia por Rota
 * 
 * @author Hugo Leonardo
 *
 * @date 11/03/2010
 * 
 */

public class ColetaMedidorEnergiaHelper {
	private static final long serialVersionUID = 1L;

	private String localidadeId;
    private String setorComercialId;
    private String rotaId;
    private String inscricao;
    private String matricula;
    private String sequencialRota;
    private String medidorEnergia;
    private String tipo;
    private String descricaoEmpresa;
    private String faturamentoGrupo;
    private String imovel;
  
    public ColetaMedidorEnergiaHelper() {
    }

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLocalidadeId() {
		return localidadeId;
	}

	public void setLocalidadeId(String localidadeId) {
		this.localidadeId = localidadeId;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMedidorEnergia() {
		return medidorEnergia;
	}

	public void setMedidorEnergia(String medidorEnergia) {
		this.medidorEnergia = medidorEnergia;
	}

	public String getRotaId() {
		return rotaId;
	}

	public void setRotaId(String rotaId) {
		this.rotaId = rotaId;
	}

	public String getSequencialRota() {
		return sequencialRota;
	}

	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getSetorComercialId() {
		return setorComercialId;
	}

	public void setSetorComercialId(String setorComercialId) {
		this.setorComercialId = setorComercialId;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
	}
	
}
