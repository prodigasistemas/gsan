package gcom.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import java.util.Date;

public class UnidadeNegocio extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

    private Integer id;
	private String nome;
	private String nomeAbreviado;
	private Short indicadorUso;
    private Date ultimaAlteracao;
    private String cnpj;
    private GerenciaRegional gerenciaRegional;
    private Cliente cliente;
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, this.getId()));		
		return filtroUnidadeNegocio; 
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeNegocio() {
		super();
	}
	
	public UnidadeNegocio(Integer id) {
		super();
		this.id = id;
	}

	public UnidadeNegocio(Integer id, String nome, String nomeAbreviado, Short indicadorUso, GerenciaRegional gerenciaRegional, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.indicadorUso = indicadorUso;
		this.gerenciaRegional = gerenciaRegional;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String getCnpjFormatado() {
		String cnpjFormatado = this.cnpj;
		String zeros = "";
		
		if (cnpjFormatado != null) {
			
			for (int a = 0; a < (14 - cnpjFormatado.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero
			// caso o numero seja diferente de nulo
			cnpjFormatado = zeros.concat(cnpjFormatado);
			
			cnpjFormatado = cnpjFormatado.substring(0, 2) + "."
					+ cnpjFormatado.substring(2, 5) + "."
					+ cnpjFormatado.substring(5, 8) + "/"
					+ cnpjFormatado.substring(8, 12) + "-"
					+ cnpjFormatado.substring(12, 14);
		}
		
		return cnpjFormatado;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getNome();
	}

}
