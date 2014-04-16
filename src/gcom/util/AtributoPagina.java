package gcom.util;

/**
 * < <Descrição da Classe>>
 * 
 * @author Cesar
 */
public class AtributoPagina {
    private Pagina owner;

    private String id;

    private String descricao;

    private Integer tamanho;

    /**
     * Construtor da classe AtributoPagina
     * 
     * @param id
     *            Descrição do parâmetro
     * @param desdricao
     *            Descrição do parâmetro
     */
    public AtributoPagina(Pagina owner, String id, String descricao) {
        this.owner = owner;
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * Retorna o valor de descricao
     * 
     * @return O valor de descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta o valor de descricao
     * 
     * @param descricao
     *            O novo valor de descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor de id
     * 
     * @return O valor de id
     */
    public String getId() {
        return id;
    }

    /**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retorna o valor de tamanho
     * 
     * @return O valor de tamanho
     */
    public Integer getTamanho() {
        return tamanho;
    }

    /**
     * Seta o valor de tamanho
     * 
     * @param tamanho
     *            O novo valor de tamanho
     */
    public void setTamanho(Integer tamanho) {
        owner.setTamanhoMaiorDescricao(tamanho);
        this.tamanho = tamanho;
    }
}
