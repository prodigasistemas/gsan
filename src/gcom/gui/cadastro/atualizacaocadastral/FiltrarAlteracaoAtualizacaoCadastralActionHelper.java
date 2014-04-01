package gcom.gui.cadastro.atualizacaocadastral;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

public class FiltrarAlteracaoAtualizacaoCadastralActionHelper {
	
	private String idEmpresa;
    
    private String idLeiturista;
    
	private String exibirCampos;
	
	private String[] colunaImoveisSelecionados;
	
	private String idLocalidadeInicial;	

	private String cdSetorComercialInicial;	
	
	private String cdRotaInicial;
	
	private String idLocalidadeFinal;
	
	private String cdSetorComercialFinal;

	private String cdRotaFinal;
	
	private Boolean alteracaoHidrometro;

	private Boolean alteracaoSituacaoAgua;
	
	private Boolean alteracaoSituacaoEsgoto;
	
	private Boolean alteracaoCategoria;
	

	public FiltrarAlteracaoAtualizacaoCadastralActionHelper() {
	}
	
	public FiltrarAlteracaoAtualizacaoCadastralActionHelper(FiltrarAlteracaoAtualizacaoCadastralActionForm form){
		this.idEmpresa = form.getIdEmpresa();
		this.idLeiturista = form.getIdLeiturista();
		this.exibirCampos = form.getExibirCampos();
		this.colunaImoveisSelecionados = form.getColunaImoveisSelecionados();
		this.idLocalidadeInicial = form.getIdLocalidadeInicial();
		this.cdSetorComercialInicial = form.getCdSetorComercialInicial();
		this.cdRotaInicial = form.getCdRotaInicial();
		this.idLocalidadeFinal = form.getIdLocalidadeFinal();
		this.cdSetorComercialFinal = form.getCdSetorComercialFinal();
		this.cdRotaFinal = form.getCdRotaFinal();
		
		alteracaoHidrometro = consisteAlteracao(form.getAlteracaoHidrometro(), this.exibirCampos);
		alteracaoSituacaoAgua   = consisteAlteracao(form.getAlteracaoSituacaoAgua(), this.exibirCampos);
		alteracaoSituacaoEsgoto = consisteAlteracao(form.getAlteracaoSituacaoEsgoto(), this.exibirCampos);
		alteracaoCategoria      = consisteAlteracao(form.getAlteracaoCategoria(), this.exibirCampos);
	}
	
	private Boolean consisteAlteracao(String campo, String exibirCampos){
		Boolean aplicaFiltro = null;
		
		if (exibirCampos.equals(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_APROVACAO_EM_LOTE.toString())) {
			aplicaFiltro = true;
		} else if (StringUtils.isNotEmpty(campo)){
			Integer altera = Integer.parseInt(campo);
			if (altera == 1){
				aplicaFiltro = true;
			}
			if (altera == 2){
				aplicaFiltro = false;
			}
		}
		return aplicaFiltro;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getExibirCampos() {
		return exibirCampos;
	}

	public void setExibirCampos(String exibirCampos) {
		this.exibirCampos = exibirCampos;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection getColunaImoveisSelecionados() {
		if (colunaImoveisSelecionados != null && colunaImoveisSelecionados.length > 0) {
			Collection colecaoColunaImoveisSelecionados = new ArrayList();
			
			for (int i = 0; i < colunaImoveisSelecionados.length; i++) {
				colecaoColunaImoveisSelecionados.add(colunaImoveisSelecionados[i]);
			}
			
			return colecaoColunaImoveisSelecionados;
		} else {
			return null;
		}
	}

	public void setColunaImoveisSelecionados(String[] colunaImoveisSelecionados) {
		this.colunaImoveisSelecionados = colunaImoveisSelecionados;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getCdSetorComercialInicial() {
		return cdSetorComercialInicial;
	}

	public void setCdSetorComercialInicial(String cdSetorComercialInicial) {
		this.cdSetorComercialInicial = cdSetorComercialInicial;
	}

	public String getCdRotaInicial() {
		return cdRotaInicial;
	}

	public void setCdRotaInicial(String cdRotaInicial) {
		this.cdRotaInicial = cdRotaInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getCdSetorComercialFinal() {
		return cdSetorComercialFinal;
	}

	public void setCdSetorComercialFinal(String cdSetorComercialFinal) {
		this.cdSetorComercialFinal = cdSetorComercialFinal;
	}

	public String getCdRotaFinal() {
		return cdRotaFinal;
	}

	public void setCdRotaFinal(String cdRotaFinal) {
		this.cdRotaFinal = cdRotaFinal;
	}

	public Boolean isAlteracaoHidrometro() {
		return alteracaoHidrometro;
	}

	public void setAlteracaoHidrometro(Boolean alteracaoHidrometro) {
		this.alteracaoHidrometro = alteracaoHidrometro;
	}

	public Boolean isAlteracaoSituacaoAgua() {
		return alteracaoSituacaoAgua;
	}

	public void setAlteracaoSituacaoAgua(Boolean alteracaoSituacaoAgua) {
		this.alteracaoSituacaoAgua = alteracaoSituacaoAgua;
	}

	public Boolean isAlteracaoSituacaoEsgoto() {
		return alteracaoSituacaoEsgoto;
	}

	public void setAlteracaoSituacaoEsgoto(Boolean alteracaoSituacaoEsgoto) {
		this.alteracaoSituacaoEsgoto = alteracaoSituacaoEsgoto;
	}

	public Boolean isAlteracaoCategoria() {
		return alteracaoCategoria;
	}

	public void setAlteracaoCategoria(Boolean alteracaoCategoria) {
		this.alteracaoCategoria = alteracaoCategoria;
	}
	
	public boolean isAprovacaoEmLote() {
		return this.exibirCampos.equals(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_APROVACAO_EM_LOTE.toString())? true : false;
	}
 }
