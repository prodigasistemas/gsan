package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ServicoCobrancaValor extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR = 514; //Operacao.OPERACAO_VALOR_COBRANCA_SERVICO_INSERIR
	public static final int ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR = 647; //Operacao.OPERACAO_VALOR_COBRANCA_SERVICO_ATUALIZAR
	public static final int ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR = 648; //Operacao.OPERACAO_VALOR_COBRANCA_SERVICO_REMOVER
	

	public final static Short INDICADOR_MEDICAO_SIM = new Short("1");

	public final static Short INDICADOR_MEDICAO_NAO = new Short("2");

	/** identifier field */
	private Integer id;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private BigDecimal valor;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private short indicadorMedido;

	/** persistent field */
	private Date ultimaAlteracao;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private HidrometroCapacidade hidrometroCapacidade;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private ImovelPerfil imovelPerfil;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private Date dataVigenciaInicial;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private Date dataVigenciaFinal;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private Integer quantidadeEconomiasInicial;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private Integer quantidadeEconomiasFinal;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private Short indicadorConsideraEconomias;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private gcom.cadastro.imovel.Subcategoria subCategoria;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_SERVICO_COBRANCA_VALOR_INSERIR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_ATUALIZAR,ATRIBUTOS_SERVICO_COBRANCA_VALOR_EXCLUIR})
	private Categoria categoria;
	
	private Short indicadorGeracaoDebito;

	/** full constructor */
	public ServicoCobrancaValor(BigDecimal valor, short indicadorMedido,
			Date ultimaAlteracao, HidrometroCapacidade hidrometroCapacidade,
			ImovelPerfil imovelPerfil,
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo,
			Date dataVigenciaInicial, Date dataVigenciaFinal,
			Integer quantidadeEconomiasInicial, Integer quantidadeEconomiasFinal,
			Short indicadorConsideraEconomias,
			gcom.cadastro.imovel.Subcategoria subCategoria) {
		this.valor = valor;
		this.indicadorMedido = indicadorMedido;
		this.ultimaAlteracao = ultimaAlteracao;
		this.hidrometroCapacidade = hidrometroCapacidade;
		this.imovelPerfil = imovelPerfil;
		this.servicoTipo = servicoTipo;
		this.dataVigenciaInicial = dataVigenciaInicial;
		this.dataVigenciaFinal = dataVigenciaFinal;
		this.quantidadeEconomiasInicial = quantidadeEconomiasInicial;
		this.quantidadeEconomiasFinal = quantidadeEconomiasFinal;
		this.indicadorConsideraEconomias = indicadorConsideraEconomias;
		this.subCategoria = subCategoria;
		
	}

	/** default constructor */
	public ServicoCobrancaValor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public short getIndicadorMedido() {
		return this.indicadorMedido;
	}

	public void setIndicadorMedido(short indicadorMedido) {
		this.indicadorMedido = indicadorMedido;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public HidrometroCapacidade getHidrometroCapacidade() {
		return this.hidrometroCapacidade;
	}

	public void setHidrometroCapacidade(
			HidrometroCapacidade hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}

	public ImovelPerfil getImovelPerfil() {
		return this.imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
		return this.servicoTipo;
	}

	public void setServicoTipo(
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
	public Date getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}

	public void setDataVigenciaFinal(Date dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}

	public Date getDataVigenciaInicial() {
		return dataVigenciaInicial;
	}

	public void setDataVigenciaInicial(Date dataVigenciaInicial) {
		this.dataVigenciaInicial = dataVigenciaInicial;
	}

	public Integer getQuantidadeEconomiasFinal() {
		return quantidadeEconomiasFinal;
	}

	public void setQuantidadeEconomiasFinal(Integer quantidadeEconomiasFinal) {
		this.quantidadeEconomiasFinal = quantidadeEconomiasFinal;
	}

	public Integer getQuantidadeEconomiasInicial() {
		return quantidadeEconomiasInicial;
	}

	public void setQuantidadeEconomiasInicial(Integer quantidadeEconomiasInicial) {
		this.quantidadeEconomiasInicial = quantidadeEconomiasInicial;
	}

	public gcom.cadastro.imovel.Subcategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(gcom.cadastro.imovel.Subcategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public Short getIndicadorConsideraEconomias() {
		return indicadorConsideraEconomias;
	}

	public void setIndicadorConsideraEconomias(Short indicadorConsideraEconomias) {
		this.indicadorConsideraEconomias = indicadorConsideraEconomias;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroServicoCobrancaValor filtroServicoCobrancaValor = new FiltroServicoCobrancaValor();


		filtroServicoCobrancaValor
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
		filtroServicoCobrancaValor
				.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroServicoCobrancaValor
				.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
		filtroServicoCobrancaValor
				.adicionarCaminhoParaCarregamentoEntidade("subCategoria");

		filtroServicoCobrancaValor.adicionarParametro(new ParametroSimples(
				FiltroServicoCobrancaValor.ID, this.getId()));
		return filtroServicoCobrancaValor;
	}

	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {
				"id", 
				"servicoTipo.descricao", 
				"valor"};
		
		return atributos;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = {"Serv. Cobranca Valor",
				"Servico Tipo", 
				"Valor do Servico"
				};
			return labels;		
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}

	public void setIndicadorConsideraEconomias(short indicadorConsideraEconomias) {
		this.indicadorConsideraEconomias = indicadorConsideraEconomias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Short getIndicadorGeracaoDebito() {
		return indicadorGeracaoDebito;
	}

	public void setIndicadorGeracaoDebito(Short indicadorGeracaoDebito) {
		this.indicadorGeracaoDebito = indicadorGeracaoDebito;
	}

}
