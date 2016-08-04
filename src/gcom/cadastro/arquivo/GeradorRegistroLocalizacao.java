package gcom.cadastro.arquivo;

import gcom.util.Util;

public class GeradorRegistroLocalizacao {
    private Integer idImovel;

    public GeradorRegistroLocalizacao(Integer idImovel) {
        this.idImovel = idImovel;
    }

    public StringBuilder build() {
        StringBuilder linha = new StringBuilder();

        // TIPO DO REGISTRO
        linha.append("06");

        // ID IMOVEL
        linha.append(Util.adicionarZerosEsquedaNumero(9, idImovel.toString()));

        // Latitude
        linha.append(Util.adicionarZerosEsquedaNumero(15, "0"));

        // Longitude
        linha.append(Util.adicionarZerosEsquedaNumero(15, "0"));

        linha.append(System.getProperty("line.separator"));

        return linha;
    }

}
