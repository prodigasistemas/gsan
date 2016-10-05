package gcom.cadastro.arquivo;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.util.Util;

public class GeradorRegistroServicos {
    ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = null;
    Imovel imovel = null;

    public GeradorRegistroServicos(ImovelAtualizacaoCadastral imovelAtualizacaoCadastral, Imovel imovel) {
        super();
        this.imovelAtualizacaoCadastral = imovelAtualizacaoCadastral;
        this.imovel = imovel;
    }

    public StringBuilder build() {

        StringBuilder linha = new StringBuilder();

        // TIPO DO REGISTRO
        linha.append("04");

        // MATRÍCULA DO IMÓVEL
        linha.append(Util.adicionarZerosEsquedaNumero(9, imovelAtualizacaoCadastral.getIdImovel().toString()));

        // LIGACAO_SITUACAO_AGUA
        linha.append(Util.adicionarZerosEsquedaNumero(2, imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao().toString()));

        // LIGACAO_SITUACAO_ESGOTO
        linha.append(Util.adicionarZerosEsquedaNumero(2, imovelAtualizacaoCadastral.getIdLigacaoEsgotoSituacao().toString()));

        if (imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getRamalLocalInstalacao() != null) {
            linha.append(Util.adicionarZerosEsquedaNumero(2, imovel.getLigacaoAgua().getRamalLocalInstalacao().getId() + ""));
        } else {
            linha.append("00");
        }

        linha.append(System.getProperty("line.separator"));

        return linha;

    }

}
