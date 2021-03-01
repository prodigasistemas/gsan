package gcom.api.ordemservico.bo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import gcom.api.ordemservico.dto.FotoDTO;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.enums.TipoImagem;
import gcom.fachada.Fachada;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class RequisicaoFotosOrdemServico {

	private Fachada fachada = Fachada.getInstancia();
	private List<FotoDTO> listaDTO;

	public RequisicaoFotosOrdemServico(List<FotoDTO> listaDTO) {
		super();
		this.listaDTO = listaDTO;
	}

	public void processar() throws Exception {
		for (FotoDTO dto : listaDTO) {
			OrdemServico ordemServico = new OrdemServico(dto.getOrdemServicoId(), dto.getImovelId());

			OrdemServicoFoto ordemServicoFoto = new OrdemServicoFoto(
					ordemServico,
					Util.converterStringParaDateComDataETempo(dto.getData()), 
					dto.getDescricao(),
					dto.getNome());

			byte[] arquivoEmBytes = Base64.decodeBase64(dto.getArquivo().getBytes());
			
			inserirFoto(ordemServicoFoto, arquivoEmBytes);
		}
	}
	
	private void inserirFoto(
			OrdemServicoFoto ordemServicoFoto,
			byte[] arquivoEmBytes) {
		
		File diretorioArquivo = null;
		String nomeArquivo = ordemServicoFoto.getNomeFoto();

		try {
			diretorioArquivo = criarDiretorio(ordemServicoFoto.getOrdemServico().getImovel().getId());
			escreverBytesNoArquivo(arquivoEmBytes, diretorioArquivo, nomeArquivo);
			ordemServicoFoto.setCaminhoFoto(diretorioArquivo.getAbsolutePath());
			fachada.inserir(ordemServicoFoto);
		} catch (Exception e) {
			File arquivo = new File(diretorioArquivo, nomeArquivo);
			if (arquivo.exists()) {
				arquivo.delete();
			}

			e.printStackTrace();
		}
	}
	
	private File criarDiretorio(Integer idImovel) throws ControladorException {
		String diretorioModulo = fachada.getCaminhoDownloadArquivos("atendimento");
		String diretorioTabela = "ordem_servico_foto/";
		String subdiretorio = montarSubdiretorio(idImovel);		
		String diretorioFinal = diretorioModulo + diretorioTabela + subdiretorio;

		File diretorio = new File(diretorioFinal);

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		return diretorio;
	}
	
	@SuppressWarnings("unchecked")
	private String montarSubdiretorio(Integer idImovel) throws ControladorException {
		Filtro filtro = new FiltroImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA_ROTA);

		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, Imovel.class.getName()));

		return Util.completaStringComZeroAEsquerda(imovel.getLocalidade().getId() + "", 3) + "_" + 
			   Util.completaStringComZeroAEsquerda(imovel.getSetorComercial().getCodigo() + "", 3) + "_" +
			   Util.completaStringComZeroAEsquerda(imovel.getQuadra().getRota().getCodigo() + "", 2);
	}
	
	private void escreverBytesNoArquivo(
			byte[] arquivoEmBytes, 
			File diretorioArquivo, 
			String nomeArquivo) throws IOException {
		
		File arquivo = new File(diretorioArquivo, nomeArquivo);
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(arquivoEmBytes));
		ImageIO.write(img, TipoImagem.JPEG.name(), arquivo);
	}	
}
