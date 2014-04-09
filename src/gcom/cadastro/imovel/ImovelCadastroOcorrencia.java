package gcom.cadastro.imovel;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ImovelCadastroOcorrencia extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	private static final int ATRIBUTOS_OCORRENCIA_CADASTRO_ANORMALIDADE_IMOVEL = 698;
	// Operacao.OPERACAO_OCORRENCIA_ANORMALIDADE_INSERIR

    /** identifier field */
    private Integer id;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_OCORRENCIA_CADASTRO_ANORMALIDADE_IMOVEL})
    private Date dataOcorrencia;

    /** nullable persistent field */
    private byte[] fotoOcorrencia;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cadastro.imovel.Imovel imovel;

    /** persistent field */
    @ControleAlteracao(value=FiltroImovelCadastroOcorrencia.CADASTRO_OCORRENCIA, funcionalidade={ATRIBUTOS_OCORRENCIA_CADASTRO_ANORMALIDADE_IMOVEL})
    private gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia;

	public ImovelCadastroOcorrencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImovelCadastroOcorrencia(Integer id, Date dataOcorrencia, byte[] fotoOcorrencia, Date ultimaAlteracao, Imovel imovel, CadastroOcorrencia cadastroOcorrencia) {
		super();
		this.id = id;
		this.dataOcorrencia = dataOcorrencia;
		this.fotoOcorrencia = fotoOcorrencia;
		this.ultimaAlteracao = ultimaAlteracao;
		this.imovel = imovel;
		this.cadastroOcorrencia = cadastroOcorrencia;
	}

	public gcom.cadastro.imovel.CadastroOcorrencia getCadastroOcorrencia() {
		return cadastroOcorrencia;
	}

	public void setCadastroOcorrencia(
			gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia) {
		this.cadastroOcorrencia = cadastroOcorrencia;
	}

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public byte[] getFotoOcorrencia() {
		return fotoOcorrencia;
	}

	public void setFotoOcorrencia(byte[] fotoOcorrencia) {
		this.fotoOcorrencia = fotoOcorrencia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public gcom.cadastro.imovel.Imovel getImovel() {
		return imovel;
	}

	public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
		this.imovel = imovel;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroImovelCadastroOcorrencia filtroImovelCadastroOcorrencia = new FiltroImovelCadastroOcorrencia();
		
		filtroImovelCadastroOcorrencia.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroImovelCadastroOcorrencia.adicionarCaminhoParaCarregamentoEntidade("cadastroOcorrencia");
		
		filtroImovelCadastroOcorrencia. adicionarParametro(
				new ParametroSimples(FiltroImovelCadastroOcorrencia.ID, this.getId()));
		
		return filtroImovelCadastroOcorrencia; 
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao(){		
			return cadastroOcorrencia.getId() + " - " + cadastroOcorrencia.getDescricao();
	}
}
