package gcom.gui.micromedicao;

import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;

/**
 * Form utilizado no Inserir Roteiro Empresa e no Atualizar Roteiro Empresa
 * 
 * @author Francisco Nascimento
 * @created 24/07/2007
 */
public class InserirRoteiroEmpresaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String empresa;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String faturamentoGrupo;
	private String idLeiturista;
	private String nomeLeiturista;
	private String []idSetorComercial;
	private String []idSetorComercialSelecionados;
	private String[] idQuadraAdicionar;
	private String indicadorUso;
	private String ultimaAlteracao;
	private String quadra;
	
	/**
	 * @param quadra The quadra to set.
	 */
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	/**
	 * @return Returns the idLeiturista.
	 */
	public String getIdLeiturista() {
		return idLeiturista;
	}
	/**
	 * @param idLeiturista The idLeiturista to set.
	 */
	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}
	/**
	 * @return Returns the nomeLeiturista.
	 */
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}
	/**
	 * @param nomeLeiturista The nomeLeiturista to set.
	 */
	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}
	/**
	 * @return Returns the descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade The descricaoLocalidade to set.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return Returns the empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa The empresa to set.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return Returns the idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade The idLocalidade to set.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Returns the indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}
	/**
	 * @param indicadorUso The indicadorUso to set.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	/**
	 * @return Returns the quadras.
	 */
	public String getQuadra() {
		return quadra;
	}
	/**
	 * @param quadras The quadras to set.
	 */
	public void setQuadras(String quadras) {
		this.quadra = quadras;
	}
	/**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	indicadorUso = ConstantesSistema.INDICADOR_USO_ATIVO.toString();
    	ultimaAlteracao = null;
    	setIdQuadraAdicionar(new String[0]);
    }
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	/**
	 * @return Returns the faturamentoGrupo.
	 */
	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}
	/**
	 * @param faturamentoGrupo The faturamentoGrupo to set.
	 */
	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}
	/**
	 * @return Returns the idSetorComercial.
	 */
	public String[] getIdSetorComercial() {
		return idSetorComercial;
	}
	/**
	 * @param idSetorComercial The idSetorComercial to set.
	 */
	public void setIdSetorComercial(String idSetorComercial[]) {
		this.idSetorComercial = idSetorComercial;
	}
	/**
	 * @return Returns the idSetorComercialSelecionados.
	 */
	public String[] getIdSetorComercialSelecionados() {
		return idSetorComercialSelecionados;
	}
	/**
	 * @param idSetorComercialSelecionados The idSetorComercialSelecionados to set.
	 */
	public void setIdSetorComercialSelecionados(
			String[] idSetorComercialSelecionados) {
		this.idSetorComercialSelecionados = idSetorComercialSelecionados;
	}
	/**
	 * @return Returns the idQuadraAdicionar.
	 */
	public String[] getIdQuadraAdicionar() {
		return idQuadraAdicionar;
	}
	/**
	 * @param idQuadraAdicionar The idQuadraAdicionar to set.
	 */
	public void setIdQuadraAdicionar(String[] idQuadraAdicionar) {
		this.idQuadraAdicionar = idQuadraAdicionar;
	}
	
	}
