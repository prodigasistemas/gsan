package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.SituacaoAtualizacaoCadastral;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

public class FiltrarAlteracaoAtualizacaoCadastralActionHelper {

	private String idEmpresa;

	private String idLeiturista;
	
	private String periodoInicial;
	
	private String periodoFinal;

	private int situacao;

	private String[] colunaImoveisSelecionados;

	private String idLocalidadeInicial;

	private String nomeLocalidadeInicial;

	private String cdSetorComercialInicial;

	private String cdRotaInicial;

	private String idLocalidadeFinal;

	private String nomeLocalidadeFinal;

	private String cdSetorComercialFinal;

	private String cdRotaFinal;

	private Boolean alteracaoHidrometro;

	private Boolean alteracaoSituacaoAgua;

	private Boolean alteracaoSituacaoEsgoto;

	private Boolean alteracaoCategoria;

	private int totalImoveis;

	private String ocorrenciaCadastro;
	
	private String lote;
	
	private String matricula;
	
	public FiltrarAlteracaoAtualizacaoCadastralActionHelper() {
	}

	public FiltrarAlteracaoAtualizacaoCadastralActionHelper(FiltrarAlteracaoAtualizacaoCadastralActionForm form) {
		this.idEmpresa = form.getIdEmpresa();
		this.idLeiturista = form.getIdLeiturista();
		this.periodoInicial = form.getPeriodoInicial();
		this.periodoFinal = form.getPeriodoFinal();
		this.situacao = Integer.valueOf(form.getSituacao());
		this.colunaImoveisSelecionados = form.getColunaImoveisSelecionados();
		this.idLocalidadeInicial = form.getIdLocalidadeInicial();
		this.nomeLocalidadeInicial = form.getNomeLocalidadeInicial();
		this.nomeLocalidadeFinal = form.getNomeLocalidadeFinal();
		this.cdSetorComercialInicial = form.getCdSetorComercialInicial();
		this.cdRotaInicial = form.getCdRotaInicial();
		this.idLocalidadeFinal = form.getIdLocalidadeFinal();
		this.cdSetorComercialFinal = form.getCdSetorComercialFinal();
		this.cdRotaFinal = form.getCdRotaFinal();

		this.alteracaoHidrometro = consisteAlteracao(form.getAlteracaoHidrometro());
		this.alteracaoSituacaoAgua = consisteAlteracao(form.getAlteracaoSituacaoAgua());
		this.alteracaoSituacaoEsgoto = consisteAlteracao(form.getAlteracaoSituacaoEsgoto());
		this.alteracaoCategoria = consisteAlteracao(form.getAlteracaoCategoria());
		
		this.ocorrenciaCadastro = form.getOcorrenciaCadastro();
		
		this.lote = form.getLote();
		this.matricula = form.getMatricula();
	}

	private Boolean consisteAlteracao(String campo) {
		Boolean aplicaFiltro = null;

		if (this.situacao == FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PARA_APROVACAO_EM_LOTE) {
			aplicaFiltro = true;
		} else if (StringUtils.isNotEmpty(campo)) {
			Integer altera = Integer.parseInt(campo);
			if (altera == 1) {
				aplicaFiltro = true;
			}
			if (altera == 2) {
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

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setExibirCampos(int exibirCampos) {
		this.situacao = exibirCampos;
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

	public boolean paraAprovacaoEmLote() {
		return this.situacao == FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PARA_APROVACAO_EM_LOTE ? true : false;
	}
	
	public boolean loteInformado() {
		return this.lote != null && !this.lote.trim().equals("") && Integer.parseInt(this.lote.trim()) > 0 && permiteAprovarLote();
	}

	public int getTotalImoveis() {
		return totalImoveis;
	}

	public void setTotalImoveis(int totalImoveis) {
		this.totalImoveis = totalImoveis;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public void setOcorrenciaCadastro(String ocorrenciaCadastro) {
		this.ocorrenciaCadastro = ocorrenciaCadastro;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	private boolean permiteAprovarLote() {
		return this.situacao == FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES ||
			   this.situacao == SituacaoAtualizacaoCadastral.PRE_APROVADO ||
			   this.situacao == SituacaoAtualizacaoCadastral.EM_FISCALIZACAO ||
			   this.situacao == SituacaoAtualizacaoCadastral.FISCALIZADO;
	}
}