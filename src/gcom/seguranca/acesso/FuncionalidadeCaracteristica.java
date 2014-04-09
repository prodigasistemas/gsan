package gcom.seguranca.acesso;

import java.io.Serializable;



/**
 * Representa as características de uma funcionalidade
 *
 * @author   rodrigo
 */
public class FuncionalidadeCaracteristica implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6996508709453691626L;

	/**
     * Description of the Field
     */
    ///public static int FUNCIONALIDADE = 1;

    /**
     * Description of the Field
     */
    ///public static int OPERACAO = 2;
    /**
     * Indica se o action é funcionalidade ou operação para o sistema
     */
    private int especificacao;
    /**
     * Caminho da funcionalidade
     */
    private String caminhoFuncionalidade;
    /**
     * Título da funcionalidade para o menu
     */
    private String tituloFuncionalidade;
    /**
     * Id do módulo
     */
    private Integer moduloId;
    /**
     * Caminho que faz parte do menu
     */
    private String caminhoMenu;

    private Integer tipoFuncionalidadeId;

    private Integer operacaoId;
    /**
     * Campo que indica quais as funcionalidades que dependem desta
     */
    private String[] funcionalidadesDependentes;

    //Indica se a funcionalidade deve ser mostrada no menu
    private boolean pontoEntrada;

    //Representa o caminho da funcionalidade quando a Funcionalidade representa uma operação
    private String caminhoFuncionalidadePaiOperacao;

    private Class classe;


    /**
     * Construtor da classe FuncionalidadeCaracteristica
     */
    /*
     *  public FuncionalidadeCaracteristica(int especificacao,
     *  String caminhoFuncionalidade,
     *  String tituloFuncionalidade,
     *  Integer moduloId,
     *  String caminhoMenu,
     *  Integer tipoFuncionalidadeId,
     *  Integer operacaoId,
     *  String[] funcionalidadesDependentes,
     *  boolean pontoEntrada) {
     *  this.especificacao = especificacao;
     *  this.caminhoFuncionalidade = caminhoFuncionalidade;
     *  this.tituloFuncionalidade = tituloFuncionalidade;
     *  this.moduloId = moduloId;
     *  this.caminhoMenu = caminhoMenu;
     *  this.tipoFuncionalidadeId = tipoFuncionalidadeId;
     *  this.operacaoId = operacaoId;
     *  this.funcionalidadesDependentes = funcionalidadesDependentes;
     *  this.pontoEntrada = pontoEntrada;
     *  }
     */
    /**
     * Construtor da classe FuncionalidadeCaracteristica
     *
     * @param especificacao               Descrição do parâmetro
     * @param caminhoFuncionalidade       Descrição do parâmetro
     * @param tituloFuncionalidade        Descrição do parâmetro
     * @param moduloId                    Descrição do parâmetro
     * @param caminhoMenu                 Descrição do parâmetro
     * @param funcionalidadesDependentes  Descrição do parâmetro
     * @param pontoEntrada                Descrição do parâmetro
     */
    public FuncionalidadeCaracteristica(int especificacao,
            String caminhoFuncionalidade,
            String tituloFuncionalidade,
            Integer moduloId,
            String caminhoMenu,
            String[] funcionalidadesDependentes,
            boolean pontoEntrada) {
        this.especificacao = especificacao;
        this.caminhoFuncionalidade = caminhoFuncionalidade;
        this.tituloFuncionalidade = tituloFuncionalidade;
        this.moduloId = moduloId;
        this.caminhoMenu = caminhoMenu;
        this.funcionalidadesDependentes = funcionalidadesDependentes;
        this.pontoEntrada = pontoEntrada;

    }

    /**
     * Construtor da classe FuncionalidadeCaracteristica
     *
     * @param especificacao               Descrição do parâmetro
     * @param caminhoFuncionalidade       Descrição do parâmetro
     * @param tituloFuncionalidade        Descrição do parâmetro
     * @param moduloId                    Descrição do parâmetro
     * @param caminhoMenu                 Descrição do parâmetro
     * @param funcionalidadesDependentes  Descrição do parâmetro
     * @param classe                      Descrição do parâmetro
     * @param pontoEntrada                Descrição do parâmetro
     */
    public FuncionalidadeCaracteristica(int especificacao,
            String caminhoFuncionalidade,
            String tituloFuncionalidade,
            Integer moduloId,
            String caminhoMenu,
            String[] funcionalidadesDependentes,
            Class classe,
            boolean pontoEntrada) {
        this.especificacao = especificacao;
        this.caminhoFuncionalidade = caminhoFuncionalidade;
        this.tituloFuncionalidade = tituloFuncionalidade;
        this.moduloId = moduloId;
        this.caminhoMenu = caminhoMenu;
        this.funcionalidadesDependentes = funcionalidadesDependentes;
        this.pontoEntrada = pontoEntrada;
        this.classe = classe;

    }


    /**
     * Construtor da classe FuncionalidadeCaracteristica
     *
     * @param especificacao                     Descrição do parâmetro
     * @param caminhoFuncionalidade             Descrição do parâmetro
     * @param tituloFuncionalidade              Descrição do parâmetro
     * @param moduloId                          Descrição do parâmetro
     * @param caminhoMenu                       Descrição do parâmetro
     * @param funcionalidadesDependentes        Descrição do parâmetro
     * @param pontoEntrada                      Descrição do parâmetro
     * @param caminhoFuncionalidadePaiOperacao  Descrição do parâmetro
     */
    public FuncionalidadeCaracteristica(int especificacao,
            String caminhoFuncionalidade,
            String tituloFuncionalidade,
            Integer moduloId,
            String caminhoMenu,
            String[] funcionalidadesDependentes,
            boolean pontoEntrada,
            String caminhoFuncionalidadePaiOperacao) {
        this.especificacao = especificacao;
        this.caminhoFuncionalidade = caminhoFuncionalidade;
        this.tituloFuncionalidade = tituloFuncionalidade;
        this.moduloId = moduloId;
        this.caminhoMenu = caminhoMenu;
        this.funcionalidadesDependentes = funcionalidadesDependentes;
        this.pontoEntrada = pontoEntrada;
        this.caminhoFuncionalidadePaiOperacao = caminhoFuncionalidadePaiOperacao;

    }

    /**
     * Construtor da classe FuncionalidadeCaracteristica
     *
     * @param especificacao                     Descrição do parâmetro
     * @param caminhoFuncionalidade             Descrição do parâmetro
     * @param tituloFuncionalidade              Descrição do parâmetro
     * @param moduloId                          Descrição do parâmetro
     * @param caminhoMenu                       Descrição do parâmetro
     * @param funcionalidadesDependentes        Descrição do parâmetro
     * @param pontoEntrada                      Descrição do parâmetro
     * @param caminhoFuncionalidadePaiOperacao  Descrição do parâmetro
     * @param classe                            Descrição do parâmetro
     */
    public FuncionalidadeCaracteristica(int especificacao,
            String caminhoFuncionalidade,
            String tituloFuncionalidade,
            Integer moduloId,
            String caminhoMenu,
            String[] funcionalidadesDependentes,
            boolean pontoEntrada,
            String caminhoFuncionalidadePaiOperacao,
            Class classe) {
        this.especificacao = especificacao;
        this.caminhoFuncionalidade = caminhoFuncionalidade;
        this.tituloFuncionalidade = tituloFuncionalidade;
        this.moduloId = moduloId;
        this.caminhoMenu = caminhoMenu;
        this.funcionalidadesDependentes = funcionalidadesDependentes;
        this.pontoEntrada = pontoEntrada;
        this.caminhoFuncionalidadePaiOperacao = caminhoFuncionalidadePaiOperacao;
        this.classe = classe;

    }


    /**
     * Retorna o valor de caminhoFuncionalidade
     *
     * @return   O valor de caminhoFuncionalidade
     */
    public String getCaminhoFuncionalidade() {
        return caminhoFuncionalidade;
    }

    /**
     * Seta o valor de caminhoFuncionalidade
     *
     * @param caminhoFuncionalidade  O novo valor de caminhoFuncionalidade
     */
    public void setCaminhoFuncionalidade(String caminhoFuncionalidade) {
        this.caminhoFuncionalidade = caminhoFuncionalidade;
    }

    /**
     * Retorna o valor de caminhoMenu
     *
     * @return   O valor de caminhoMenu
     */
    public String getCaminhoMenu() {
        return caminhoMenu;
    }

    /**
     * Seta o valor de caminhoMenu
     *
     * @param caminhoMenu  O novo valor de caminhoMenu
     */
    public void setCaminhoMenu(String caminhoMenu) {
        this.caminhoMenu = caminhoMenu;
    }

    /**
     * Retorna o valor de especificacao
     *
     * @return   O valor de especificacao
     */
    public int getEspecificacao() {
        return especificacao;
    }

    /**
     * Seta o valor de especificacao
     *
     * @param especificacao  O novo valor de especificacao
     */
    public void setEspecificacao(int especificacao) {
        this.especificacao = especificacao;
    }

    /**
     * Retorna o valor de funcionalidadesDependentes
     *
     * @return   O valor de funcionalidadesDependentes
     */
    public String[] getFuncionalidadesDependentes() {
        return funcionalidadesDependentes;
    }

    /**
     * Seta o valor de funcionalidadesDependentes
     *
     * @param funcionalidadesDependentes  O novo valor de
     *      funcionalidadesDependentes
     */
    public void setFuncionalidadesDependentes(String[] funcionalidadesDependentes) {
        this.funcionalidadesDependentes = funcionalidadesDependentes;
    }

    /**
     * Retorna o valor de moduloId
     *
     * @return   O valor de moduloId
     */
    public Integer getModuloId() {
        return moduloId;
    }

    /**
     * Seta o valor de moduloId
     *
     * @param moduloId  O novo valor de moduloId
     */
    public void setModuloId(Integer moduloId) {
        this.moduloId = moduloId;
    }

    /**
     * Retorna o valor de tituloFuncionalidade
     *
     * @return   O valor de tituloFuncionalidade
     */
    public String getTituloFuncionalidade() {
        return tituloFuncionalidade;
    }

    /**
     * Seta o valor de tituloFuncionalidade
     *
     * @param tituloFuncionalidade  O novo valor de tituloFuncionalidade
     */
    public void setTituloFuncionalidade(String tituloFuncionalidade) {
        this.tituloFuncionalidade = tituloFuncionalidade;
    }

    /**
     * Retorna o valor de tipoFuncionalidade
     *
     * @return   O valor de tipoFuncionalidade
     */
    public Integer getTipoFuncionalidadeId() {
        return tipoFuncionalidadeId;
    }

    /**
     * Seta o valor de tipoFuncionalidade
     *
     * @param tipoFuncionalidadeId  O novo valor de tipoFuncionalidade
     */
    public void setTipoFuncionalidadeId(Integer tipoFuncionalidadeId) {
        this.tipoFuncionalidadeId = tipoFuncionalidadeId;
    }

    /**
     * Retorna o valor de operacaoId
     *
     * @return   O valor de operacaoId
     */
    public Integer getOperacaoId() {
        return operacaoId;
    }

    /**
     * Seta o valor de operacaoId
     *
     * @param operacaoId  O novo valor de operacaoId
     */
    public void setOperacaoId(Integer operacaoId) {
        this.operacaoId = operacaoId;
    }

    /**
     * Retorna o valor de pontoEntrada
     *
     * @return   O valor de pontoEntrada
     */
    public boolean isPontoEntrada() {
        return pontoEntrada;
    }

    /**
     * Seta o valor de pontoEntrada
     *
     * @param pontoEntrada  O novo valor de pontoEntrada
     */
    public void setPontoEntrada(boolean pontoEntrada) {
        this.pontoEntrada = pontoEntrada;
    }

    /**
     * Retorna o valor de caminhoFuncionalidadePaiOperacao
     *
     * @return   O valor de caminhoFuncionalidadePaiOperacao
     */
    public String getCaminhoFuncionalidadePaiOperacao() {
        return caminhoFuncionalidadePaiOperacao;
    }

    /**
     * Seta o valor de caminhoFuncionalidadePaiOperacao
     *
     * @param caminhoFuncionalidadePaiOperacao  O novo valor de
     *      caminhoFuncionalidadePaiOperacao
     */
    public void setCaminhoFuncionalidadePaiOperacao(String caminhoFuncionalidadePaiOperacao) {
        this.caminhoFuncionalidadePaiOperacao = caminhoFuncionalidadePaiOperacao;
    }

    /**
     * Retorna o valor de nomeClasse
     *
     * @return   O valor de nomeClasse
     */
    public Class getClasse() {
        return classe;
    }

    /**
     * Seta o valor de nomeClasse
     *
     * @param nomeClasse  O novo valor de nomeClasse
     */
    public void setClasse(Class nomeClasse) {
        this.classe = nomeClasse;
    }

}
