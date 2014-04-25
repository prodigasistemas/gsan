package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Fernanda Paiva
 */
public class FiltroHidrometroMovimentacao extends Filtro implements
        Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String DATA = "data";

	/**
	 * Description of the Field
	 */
	public final static String HORA = "hora";

	/**
	 * Description of the Field
	 */
	public final static String PARECER = "parecer";

	/**
	 * Description of the Field
	 */
	public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MOTIVO = "hidrometroMotivoMovimentacao.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_LOCAL_ARMAZENAGEM = "hidrometroLocalArmazenagem.id";

	/**
	 * Description of the Field
	 */
	public final static String USUARIO = "usuario.id";

	public final static String HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_DESTINO_ID = "hidrometroLocalArmazenagemDestino.id";

	public final static String HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_ORIGEM_ID = "hidrometroLocalArmazenagemOrigem.id";
	
	/**
     * Construtor da classe FiltroHidrometroMovimentado
     */
    public FiltroHidrometroMovimentacao() {
    }

    /**
     * Construtor da classe FiltroHidrometroMovimentado
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroHidrometroMovimentacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
