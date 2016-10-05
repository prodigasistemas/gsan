package gcom.cadastro.arquivo;

import gcom.cadastro.imovel.enums.TipoUsoImovel;
import gcom.util.Util;

public class GeradorRegistroTipoUsoImovel {
    public StringBuilder build() {
        StringBuilder linha = new StringBuilder();
        
        for(TipoUsoImovel classe: TipoUsoImovel.values()){
            linha.append("19");

            linha.append(Util.adicionarZerosEsquedaNumero(2, classe.getId()));

            linha.append(Util.completaString(classe.getDescricao(), 20));
            
            linha.append(System.getProperty("line.separator"));
        }
        
        return linha;
    }
}
