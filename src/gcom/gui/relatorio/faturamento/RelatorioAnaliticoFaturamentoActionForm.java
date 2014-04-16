package gcom.gui.relatorio.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Flávio Cordeiro
 */
public class RelatorioAnaliticoFaturamentoActionForm extends ValidatorActionForm {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mesAno;
	private String localidadeFiltro;
	private String nomeLocalidade;
	private String setorComercialFiltro;
	private String setorComercialNome;
	private String setorComercialID;
	private String quadraNome;
	private String quadraFiltro;
	private String quadraMensagem;
	private String quadraID;
	private String indicadorLocalidadeInformatizada;
	private String idGrupoFaturamento;
	//-------Usado bara as buscas um nivel abaixo ex: idLocalidade da busca de setor
	private String idLocalidadeSetor;
	private String idSetorQuadra;

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getIndicadorLocalidadeInformatizada() {
		return indicadorLocalidadeInformatizada;
	}

	public void setIndicadorLocalidadeInformatizada(
			String indicadorLocalidadeInformatizada) {
		this.indicadorLocalidadeInformatizada = indicadorLocalidadeInformatizada;
	}

	public String getSetorComercialID() {
		return setorComercialID;
	}

	public void setSetorComercialID(String comercialID) {
		setorComercialID = comercialID;
	}

	public String getSetorComercialFiltro() {
		return setorComercialFiltro;
	}

	public void setSetorComercialFiltro(String setorComercialFiltro) {
		this.setorComercialFiltro = setorComercialFiltro;
	}

	public String getSetorComercialNome() {
		return setorComercialNome;
	}

	public void setSetorComercialNome(String setorComercialNome) {
		this.setorComercialNome = setorComercialNome;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getLocalidadeFiltro() {
		return localidadeFiltro;
	}

	public void setLocalidadeFiltro(String localidadeFiltro) {
		this.localidadeFiltro = localidadeFiltro;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getQuadraFiltro() {
		return quadraFiltro;
	}

	public void setQuadraFiltro(String quadraFiltro) {
		this.quadraFiltro = quadraFiltro;
	}

	public String getQuadraMensagem() {
		return quadraMensagem;
	}

	public void setQuadraMensagem(String quadraMensagem) {
		this.quadraMensagem = quadraMensagem;
	}

	public String getQuadraNome() {
		return quadraNome;
	}

	public void setQuadraNome(String quadraNome) {
		this.quadraNome = quadraNome;
	}

	public String getQuadraID() {
		return quadraID;
	}

	public void setQuadraID(String quadraID) {
		this.quadraID = quadraID;
	}

	public String getIdLocalidadeSetor() {
		return idLocalidadeSetor;
	}

	public void setIdLocalidadeSetor(String idLocalidadeSetor) {
		this.idLocalidadeSetor = idLocalidadeSetor;
	}

	public String getIdSetorQuadra() {
		return idSetorQuadra;
	}

	public void setIdSetorQuadra(String idSetorQuadra) {
		this.idSetorQuadra = idSetorQuadra;
	}

}
