package gcom.atualizacaocadastral;

import java.util.Date;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;

public class Visita extends ObjetoTransacao {

	private static final long serialVersionUID = 3619726654733392236L;
	
	public static final Integer QUANTIDADE_MAXIMA_SEM_PRE_AGENDAMENTO = 3;
	
	private Integer id;
	private ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral;
	private CadastroOcorrencia cadastroOcorrencia;
	private Date ultimaAlteracao;
	private String coordenadaX;
	private String coordenadaY;
	private Usuario usuario;

	public Visita() {
	}

	public Visita(ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral, AtualizacaoCadastralImovel atualizacaoCadastralImovel) {
		this.imovelControleAtualizacaoCadastral = imovelControleAtualizacaoCadastral;
		this.cadastroOcorrencia = imovelControleAtualizacaoCadastral.getCadastroOcorrencia();
		setCoordenadaX(atualizacaoCadastralImovel.getLinhaAnormalidade("latitude"));
		setCoordenadaY(atualizacaoCadastralImovel.getLinhaAnormalidade("longitude"));
		setUltimaAlteracao(new Date());
		this.usuario = atualizacaoCadastralImovel.getUsuario();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ImovelControleAtualizacaoCadastral getImovelControleAtualizacaoCadastral() {
		return imovelControleAtualizacaoCadastral;
	}

	public void setImovelControleAtualizacaoCadastral(
			ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral) {
		this.imovelControleAtualizacaoCadastral = imovelControleAtualizacaoCadastral;
	}

	public CadastroOcorrencia getCadastroOcorrencia() {
		return cadastroOcorrencia;
	}

	public void setCadastroOcorrencia(CadastroOcorrencia cadastroOcorrencia) {
		this.cadastroOcorrencia = cadastroOcorrencia;
	}

	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public String getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroTabelaAuxiliar filtroTabelaAuxiliar = new FiltroTabelaAuxiliar();
		filtroTabelaAuxiliar.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliar.ID, this.getId()));

		return filtroTabelaAuxiliar;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}
