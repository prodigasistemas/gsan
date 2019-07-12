package gcom.gui.cadastro.atualizacaocadastral;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.util.Util;

public class FiltrarAlteracaoAtualizacaoCadastralActionHelper {

	private String idEmpresa;

	private String idLeiturista;
	
	private Date periodoInicial;
	
	private Date periodoFinal;

	private int situacaoImoveis;

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

	private int ocorrenciaCadastro;
	
	private int ocorrenciaCadastroSelecionada;
	
	private String lote;
	
	private String matricula;
	
	private int quantidadeVisitas;
	
	private short cpfCnpjCadastrado;
	
	private short cpfCnpjTransmitido;
	
	private Integer idImovelRetorno;
	
	public FiltrarAlteracaoAtualizacaoCadastralActionHelper() {
	}

	public FiltrarAlteracaoAtualizacaoCadastralActionHelper(FiltrarAlteracaoAtualizacaoCadastralActionForm form) {
		this.idEmpresa = form.getIdEmpresa();
		this.idLeiturista = form.getIdLeiturista();
		this.periodoInicial = Util.formatarDataInicial(Util.converteStringParaDate(form.getPeriodoInicial()));
		this.periodoFinal = Util.formatarDataFinal(Util.converteStringParaDate(form.getPeriodoFinal()));
		this.situacaoImoveis = Integer.valueOf(form.getSituacaoImoveis());
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
		
		this.ocorrenciaCadastro = Integer.valueOf(form.getOcorrenciaCadastro());
		this.ocorrenciaCadastroSelecionada = form.getOcorrenciaCadastroSelecionada() != null ? Integer.valueOf(form.getOcorrenciaCadastroSelecionada()) : 0;
		
		this.lote = form.getLote();
		this.matricula = form.getMatricula();
		this.quantidadeVisitas = Integer.valueOf(form.getQuantidadeVisitas());
		
		this.cpfCnpjCadastrado = Short.valueOf(form.getCpfCnpjCadastrado());
		this.cpfCnpjTransmitido = Short.valueOf(form.getCpfCnpjTransmitido());
	}

	private Boolean consisteAlteracao(String campo) {
		Boolean aplicaFiltro = null;

		if (this.situacaoImoveis == FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PARA_APROVACAO_EM_LOTE) {
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

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public int getSituacaoImoveis() {
		return situacaoImoveis;
	}

	public void setSituacaoImoveis(int situacaoImoveis) {
		this.situacaoImoveis = situacaoImoveis;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public String getCdSetorComercialInicial() {
		return cdSetorComercialInicial;
	}

	public String getCdRotaInicial() {
		return cdRotaInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public String getCdSetorComercialFinal() {
		return cdSetorComercialFinal;
	}

	public String getCdRotaFinal() {
		return cdRotaFinal;
	}

	public Boolean isAlteracaoHidrometro() {
		return alteracaoHidrometro;
	}

	public Boolean isAlteracaoSituacaoAgua() {
		return alteracaoSituacaoAgua;
	}

	public Boolean isAlteracaoSituacaoEsgoto() {
		return alteracaoSituacaoEsgoto;
	}

	public Boolean isAlteracaoCategoria() {
		return alteracaoCategoria;
	}

	public boolean paraAprovacaoEmLote() {
		return this.situacaoImoveis == FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PARA_APROVACAO_EM_LOTE ? true : false;
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

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public int getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public int getOcorrenciaCadastroSelecionada() {
		return ocorrenciaCadastroSelecionada;
	}

	public String getLote() {
		return lote;
	}

	public String getMatricula() {
		return matricula;
	}

	public int getQuantidadeVisitas() {
		return quantidadeVisitas;
	}

	public short getCpfCnpjCadastrado() {
		return cpfCnpjCadastrado;
	}

	public short getCpfCnpjTransmitido() {
		return cpfCnpjTransmitido;
	}

	public void setAlteracaoHidrometro(Boolean alteracaoHidrometro) {
		this.alteracaoHidrometro = alteracaoHidrometro;
	}

	public void setAlteracaoSituacaoAgua(Boolean alteracaoSituacaoAgua) {
		this.alteracaoSituacaoAgua = alteracaoSituacaoAgua;
	}

	public void setAlteracaoSituacaoEsgoto(Boolean alteracaoSituacaoEsgoto) {
		this.alteracaoSituacaoEsgoto = alteracaoSituacaoEsgoto;
	}

	public void setAlteracaoCategoria(Boolean alteracaoCategoria) {
		this.alteracaoCategoria = alteracaoCategoria;
	}
	
	public Integer getIdImovelRetorno() {
		return idImovelRetorno;
	}

	public void setIdImovelRetorno(Integer idImovelRetorno) {
		this.idImovelRetorno = idImovelRetorno;
	}

	private boolean permiteAprovarLote() {
		return this.situacaoImoveis == FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PENDENTES ||
			   this.situacaoImoveis == SituacaoAtualizacaoCadastral.PRE_APROVADO ||
			   this.situacaoImoveis == SituacaoAtualizacaoCadastral.EM_FISCALIZACAO ||
			   this.situacaoImoveis == SituacaoAtualizacaoCadastral.FISCALIZADO;
	}
}