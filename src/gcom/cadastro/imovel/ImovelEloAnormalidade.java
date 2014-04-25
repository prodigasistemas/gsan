package gcom.cadastro.imovel;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;



/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ImovelEloAnormalidade extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	private static final int ATRIBUTOS_OCORRENCIA_CADASTRO_ANORMALIDADE_IMOVEL = 698;
	// Operacao.OPERACAO_OCORRENCIA_ANORMALIDADE_INSERIR

    /** identifier field */
    private Integer id;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_OCORRENCIA_CADASTRO_ANORMALIDADE_IMOVEL})
    private Date dataAnormalidade;

    /** nullable persistent field */
    private byte[] fotoAnormalidade;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    @ControleAlteracao(value=FiltroImovelEloAnormalidade.ELO_ANORMALIDADE, funcionalidade={ATRIBUTOS_OCORRENCIA_CADASTRO_ANORMALIDADE_IMOVEL})
    private gcom.cadastro.imovel.EloAnormalidade eloAnormalidade;

    /** persistent field */
    private gcom.cadastro.imovel.Imovel imovel;

	public Date getDataAnormalidade() {
		return dataAnormalidade;
	}

	public ImovelEloAnormalidade() {

	}

	public ImovelEloAnormalidade(Integer id, Date dataAnormalidade, byte[] fotoAnormalidade, Date ultimaAlteracao, EloAnormalidade eloAnormalidade, Imovel imovel) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.dataAnormalidade = dataAnormalidade;
		this.fotoAnormalidade = fotoAnormalidade;
		this.ultimaAlteracao = ultimaAlteracao;
		this.eloAnormalidade = eloAnormalidade;
		this.imovel = imovel;
	}

	public void setDataAnormalidade(Date dataAnormalidade) {
		this.dataAnormalidade = dataAnormalidade;
	}

	public gcom.cadastro.imovel.EloAnormalidade getEloAnormalidade() {
		return eloAnormalidade;
	}

	public void setEloAnormalidade(
			gcom.cadastro.imovel.EloAnormalidade eloAnormalidade) {
		this.eloAnormalidade = eloAnormalidade;
	}

	public byte[] getFotoAnormalidade() {
		return fotoAnormalidade;
	}

	public void setFotoAnormalidade(byte[] fotoAnormalidade) {
		this.fotoAnormalidade = fotoAnormalidade;
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
		FiltroImovelEloAnormalidade filtroImovelEloAnormalidade = new FiltroImovelEloAnormalidade();
		
		filtroImovelEloAnormalidade. adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroImovelEloAnormalidade. adicionarCaminhoParaCarregamentoEntidade("eloAnormalidade");

		filtroImovelEloAnormalidade. adicionarParametro(
				new ParametroSimples(FiltroImovelEloAnormalidade.ID, this.getId()));
		return filtroImovelEloAnormalidade; 
	}
	
	public String getDescricaoParaRegistroTransacao(){
		return eloAnormalidade.getId() + " - " + eloAnormalidade.getDescricao();	
	}
}
