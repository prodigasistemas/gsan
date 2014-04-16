package gcom.atendimentopublico.ligacaoagua;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Flávio Leonardo C. Cordeiro
 */
public class FiltroLigacaoAgua extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroLigacaoAgua(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroLigacaoAgua() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String ID_HIDROMETRO_INSTALACAO_HISTORICO = "hidrometroInstalacaoHistorico.id";
    
    public final static String HIDROMETRO_INSTALACAO_HISTORICO = "hidrometroInstalacaoHistorico";

    public final static String  LIGACAO_AGUA_DIAMETRO = "ligacaoAguaDiametro";
    
    public final static String  LIGACAO_AGUA_MATERIAL = "ligacaoAguaMaterial";
    
    public final static String  LIGACAO_AGUA_PERFIL = "ligacaoAguaPerfil";
    
    public final static String  RAMAL_LOCAL_LIGACAO = "ramalLocalInstalacao";
    
    public final static String  CORTE_TIPO = "corteTipo";
    
    public final static String  CORTE_MOTIVO = "motivoCorte";
    
}
