package gcom.relatorio.cadastro.localidade;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Erivan Sousa
 * @version 1.0
 */

public class RelatorioManterGerenciaRegionalBean implements RelatorioBean {
    private String id;

    private String nome;

    private String nomeAbreviado;

    private String indicadorUso;

    /**
     * Construtor da classe RelatorioManterLogradouroBean
     * 
     * @param codigo
     *            Descrição do parâmetro
     * @param nome
     *            Descrição do parâmetro
     * @param nomeAbreviado
     *            Descrição do parâmetro
     * @param indicadorUso
     *            Descrição do parâmetro
     */

    public RelatorioManterGerenciaRegionalBean(String id, String nome,
            String nomeAbreviado, String indicadorUso) {
        this.id = id;
        this.nome = nome;
        this.nomeAbreviado = nomeAbreviado;
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de codigo
     * 
     * @return O valor de codigo
     */
    public String getId() {
        return id;
    }

    /**
     * Seta o valor de codigo
     * 
     * @param codigo
     *            O novo valor de codigo
     */
    public void setCodigo(String id) {
        this.id = id;
    }

    /**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     */
    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public String getIndicadorUso() {
    	
        return indicadorUso.equals("1")?"Ativo":"Inativo";
    }

    /**
     * Retorna o valor de nome
     * 
     * @return O valor de nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Seta o valor de nome
     * 
     * @param nome
     *            O novo valor de nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Retorna o valor de nomeAbreviado
     * 
     * @return O valor de nomeAbreviado
     */
    public String getNomeAbreviado() {
        return nomeAbreviado;
    }

    /**
     * Seta o valor de nomeAbreviado
     * 
     * @param nomeAbreviado
     *            O novo valor de nomeAbreviado
     */
    public void setNomeAbreviado(String nomeAbreviado) {
        this.nomeAbreviado = nomeAbreviado;
    }

}
