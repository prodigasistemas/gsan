package gcom.cadastro.arquivo;

import gcom.cadastro.imovel.enums.AcessoHidrometro;
import gcom.util.Util;

public class GeradorRegistroAcessoHidrometro {
    public StringBuilder build() {
        StringBuilder linha = new StringBuilder();
        
        for(AcessoHidrometro classe: AcessoHidrometro.values()){
            linha.append("20");

            linha.append(Util.adicionarZerosEsquedaNumero(2, classe.getId()));

            linha.append(Util.completaString(classe.getDescricao(), 10));
            
            linha.append(System.getProperty("line.separator"));
        }
        
        return linha;
    }
}
