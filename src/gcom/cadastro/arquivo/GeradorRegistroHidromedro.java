package gcom.cadastro.arquivo;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.util.Util;

public class GeradorRegistroHidromedro {
    ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = null;
    Imovel imovel = null;
    
    public GeradorRegistroHidromedro(ImovelAtualizacaoCadastral imovelAtualizacaoCadastral, Imovel imovel) {
        this.imovelAtualizacaoCadastral = imovelAtualizacaoCadastral;
        this.imovel = imovel;
    }


    public StringBuilder build() {
        StringBuilder linha = new StringBuilder();

        // TIPO DO REGISTRO
        linha.append("05");

        // MATRÍCULA DO IMÓVEL
        linha.append(Util.adicionarZerosEsquedaNumero(9, imovelAtualizacaoCadastral.getIdImovel().toString()));

        // IMOVEL POSSUI HIDROMETRO (1-SIM/2-NAO)
        boolean possuiHidrometro = false;
        if (imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null
                && (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getDataRetirada() == null
                        || imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getDataRetirada().equals(""))) {
            possuiHidrometro = true;
            linha.append("1");
        } else {
            linha.append("2");
        }

        if (possuiHidrometro) {

            // Número hidrômetro
            if (imovelAtualizacaoCadastral.getNumeroHidrometro() != null) {
                linha.append(Util.completaString(imovelAtualizacaoCadastral.getNumeroHidrometro().toString(), 10));
            } else {
                linha.append(Util.completaString("", 10));
            }

            // Marca hidrômetro
            if (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro() != null
                    && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getHidrometroMarca() != null
                    && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getHidrometroMarca().getId() != null) {
                linha.append(Util.adicionarZerosEsquedaNumero(2,
                        (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getHidrometroMarca().getId().toString())));
            } else {
                linha.append(Util.completaString("", 2));
            }

            // Capacidade hidrômetro
            if (imovelAtualizacaoCadastral.getIdCapacidadeHidrometro() != null) {
                linha.append(Util.adicionarZerosEsquedaNumero(2, imovelAtualizacaoCadastral.getIdCapacidadeHidrometro().toString()));
            } else {
                linha.append(Util.completaString("", 2));
            }

            // Proteção hidrômetro
            if (imovelAtualizacaoCadastral.getIdProtecaoHidrometro() != null) {
                linha.append(Util.adicionarZerosEsquedaNumero(2, imovelAtualizacaoCadastral.getIdProtecaoHidrometro().toString()));
            } else {
                linha.append(Util.completaString("", 2));
            }

        } else {
            linha.append(Util.completaString("", 16));
        }

        linha.append(System.getProperty("line.separator"));

        return linha;
    }
}