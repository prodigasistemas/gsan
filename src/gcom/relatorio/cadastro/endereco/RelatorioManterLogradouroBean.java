package gcom.relatorio.cadastro.endereco;

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
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterLogradouroBean implements RelatorioBean {
    private String codigo;

    private String nome;

    private String titulo;

    private String tipo;

    private String municipio;

    private String indicadorUso;

    private String bairro;

    /**
     * Construtor da classe RelatorioManterLogradouroBean
     * 
     * @param codigo
     *            Descrição do parâmetro
     * @param nome
     *            Descrição do parâmetro
     * @param titulo
     *            Descrição do parâmetro
     * @param tipo
     *            Descrição do parâmetro
     * @param municipio
     *            Descrição do parâmetro
     * @param indicadorUso
     *            Descrição do parâmetro
     */

    public RelatorioManterLogradouroBean(String codigo, String nome,
            String titulo, String tipo, String municipio, String bairro,
            String indicadorUso) {
        this.codigo = codigo;
        this.nome = nome;
        this.titulo = titulo;
        this.tipo = tipo;
        this.municipio = municipio;
        this.bairro = bairro;
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de codigo
     * 
     * @return O valor de codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Seta o valor de codigo
     * 
     * @param codigo
     *            O novo valor de codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Seta o valor de municipio
     * 
     * @param municipio
     *            O novo valor de municipio
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
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
        return indicadorUso;
    }

    /**
     * Retorna o valor de municipio
     * 
     * @return O valor de municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Retorna o valor de tipo
     * 
     * @return O valor de tipo
     */
    public String getTipo() {
        return tipo;
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
     * Retorna o valor de titulo
     * 
     * @return O valor de titulo
     */
    public String getTitulo() {
        return titulo;
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
     * Seta o valor de tipo
     * 
     * @param tipo
     *            O novo valor de tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Seta o valor de titulo
     * 
     * @param titulo
     *            O novo valor de titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

}
