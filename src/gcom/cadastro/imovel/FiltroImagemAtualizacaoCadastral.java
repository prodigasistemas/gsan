package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca da ImagemAtualizacaoCadastral 
 * 
 * @author Matheus Souza
 */
public class FiltroImagemAtualizacaoCadastral extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public static final String ID = "id";

    public static final String IDIMOVEL = "idImovel";

    public static final String IMAGEM = "imagem";

    public FiltroImagemAtualizacaoCadastral() {
    }

    public FiltroImagemAtualizacaoCadastral(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}