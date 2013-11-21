package gcom.cobranca;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class CobrancaBoletimDesconto extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    private CobrancaBoletimDescontoPK comp_id;

    private Date ultimaAlteracao;

    private BigDecimal valorDesconto;
    
    private CobrancaBoletimMedicao cobrancaBoletimMedicao;
    
    private OrdemServico ordemServico;
    
    private Localidade localidade;


	public CobrancaBoletimDesconto() {
		super();
	}

	public CobrancaBoletimDesconto(CobrancaBoletimDescontoPK comp_id, Date ultimaAlteracao, BigDecimal valorDesconto, CobrancaBoletimMedicao cobrancaBoletimMedicao, OrdemServico ordemServico) {
		super();
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.valorDesconto = valorDesconto;
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

	public CobrancaBoletimDescontoPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(CobrancaBoletimDescontoPK comp_id) {
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

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
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
		FiltroCobrancaBoletimDesconto filtroCobrancaBoletimDesconto = new FiltroCobrancaBoletimDesconto();
		
		filtroCobrancaBoletimDesconto.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroCobrancaBoletimDesconto.adicionarCaminhoParaCarregamentoEntidade("cobrancaBoletimMedicao");
		filtroCobrancaBoletimDesconto.adicionarCaminhoParaCarregamentoEntidade("ordemServico");

		
		filtroCobrancaBoletimDesconto.adicionarParametro(
						new ParametroSimples(FiltroCobrancaBoletimDesconto.COMP_ID_COBRANCA_BOLETIM_MEDICAO_ID, cobrancaBoletimMedicao.getId()));
		
		filtroCobrancaBoletimDesconto.adicionarParametro(
				new ParametroSimples(FiltroCobrancaBoletimDesconto.COMP_ID_ORDEM_SERVICO_ID, ordemServico.getId()));
		
		return filtroCobrancaBoletimDesconto; 
	}

}
