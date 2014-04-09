package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

/**
 * [UC12008] Cadastrar Mensagens de Acompanhamento de Serviços
 * 
 * @author Magno Gouveia
 * @since 11/08/2011
 */
public class MensagemAcompanhamentoServico extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final Integer INDICADOR_SITUACAO_RECEBIDA = 1;
	
	public static final Integer INDICADOR_SITUACAO_CADASTRADA = 2;

	private Integer id;
	
	private ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico;
	
	private String descricaoMensagem;
	
	private Usuario usuario;
	
	private Integer indicadorSituacao;
	
	private Date ultimaAlteracao;

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;		
	}

	@Override
	public Filtro retornaFiltro() {

		FiltroMensagemAcompanhamentoServico filtroMensagemAcompanhamentoServico = new FiltroMensagemAcompanhamentoServico();
		filtroMensagemAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroMensagemAcompanhamentoServico.ID, this.getId()));

		return filtroMensagemAcompanhamentoServico;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public ArquivoTextoAcompanhamentoServico getArquivoTextoAcompanhamentoServico() {
		return arquivoTextoAcompanhamentoServico;
	}

	public void setArquivoTextoAcompanhamentoServico(ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico) {
		this.arquivoTextoAcompanhamentoServico = arquivoTextoAcompanhamentoServico;
	}

	public String getDescricaoMensagem() {
		return descricaoMensagem;
	}

	public void setDescricaoMensagem(String descricaoMensagem) {
		this.descricaoMensagem = descricaoMensagem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndicadorSituacao() {
		return indicadorSituacao;
	}

	public void setIndicadorSituacao(Integer indicadorSituacao) {
		this.indicadorSituacao = indicadorSituacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
