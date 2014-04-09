package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;


/**
 * [UC1000] Informar Medidor de Energia por Rota
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2010
 */

public class FiltrarColetaMedidorEnergiaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idLocalidade;
	private String localidadeDescricao;
	private String codigoSetorComercial;
	private String setorComercialDescricao;
    private String rota;
    private String tipo;
    private String inscricao;
    private String matricula;
    private String sequencialRota;
    private String medidorEnergia;
    
    private String localidadeNaoEncontrada;
    private String setorComercialNaoEncontrado;
    private String descricaoEmpresa;
    private String faturamentoGrupo;
    
    /** default constructor */
    public FiltrarColetaMedidorEnergiaActionForm() {
    }

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
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

	public String getSequencialRota() {
		return sequencialRota;
	}

	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getLocalidadeNaoEncontrada() {
		return localidadeNaoEncontrada;
	}

	public void setLocalidadeNaoEncontrada(String localidadeNaoEncontrada) {
		this.localidadeNaoEncontrada = localidadeNaoEncontrada;
	}

	public String getSetorComercialNaoEncontrado() {
		return setorComercialNaoEncontrado;
	}

	public void setSetorComercialNaoEncontrado(String setorComercialNaoEncontrado) {
		this.setorComercialNaoEncontrado = setorComercialNaoEncontrado;
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

	
}
