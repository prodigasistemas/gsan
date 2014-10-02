package gcom.cobranca;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class CobrancaBoletimExecutado extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private CobrancaBoletimExecutadoPK comp_id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private BigDecimal valorServico;
    
    private CobrancaBoletimMedicao cobrancaBoletimMedicao;
    
    private OrdemServico ordemServico;
    
    private Localidade localidade;

	public CobrancaBoletimExecutado() {
		super();
		
	}

	public CobrancaBoletimExecutado(CobrancaBoletimExecutadoPK comp_id, Date ultimaAlteracao, BigDecimal valorServico, CobrancaBoletimMedicao cobrancaBoletimMedicao, OrdemServico ordemServico) {
		super();
		
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.valorServico = valorServico;
		this.cobrancaBoletimMedicao = cobrancaBoletimMedicao;
		this.ordemServico = ordemServico;
	}

	public CobrancaBoletimMedicao getCobrancaBoletimMedicao() {
		return cobrancaBoletimMedicao;
	}

	public void setCobrancaBoletimMedicao(
			CobrancaBoletimMedicao cobrancaBoletimMedicao) {
		this.cobrancaBoletimMedicao = cobrancaBoletimMedicao;
	}

	public CobrancaBoletimExecutadoPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(CobrancaBoletimExecutadoPK comp_id) {
		this.comp_id = comp_id;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}    
    
	public Filtro retornaFiltro(){
		FiltroCobrancaBoletimExecutado filtroCobrancaBoletimExecutado = new FiltroCobrancaBoletimExecutado();
		
		filtroCobrancaBoletimExecutado.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroCobrancaBoletimExecutado.adicionarCaminhoParaCarregamentoEntidade("cobrancaBoletimMedicao");
		filtroCobrancaBoletimExecutado.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

		
		filtroCobrancaBoletimExecutado.adicionarParametro(
						new ParametroSimples(FiltroCobrancaBoletimExecutado.COMP_ID_COBRANCA_BOLETIM_MEDICAO_ID, cobrancaBoletimMedicao.getId()));
		
		filtroCobrancaBoletimExecutado.adicionarParametro(
				new ParametroSimples(FiltroCobrancaBoletimExecutado.COMP_ID_ORDEM_SERVICO_ID, ordemServico.getId()));
		
		return filtroCobrancaBoletimExecutado; 
	}

}
