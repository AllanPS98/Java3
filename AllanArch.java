package Util;

import Model.Mercadoria;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author allan
 */
public class AllanArch {

    /**
     *
     * @param caminho
     * @param arv
     * @return String
     */
    public static String ReadDoSistema(String caminho, Arvore arv) {
        try {
            FileReader arq = new FileReader(caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha;
            try {
                do {
                    linha = lerArq.readLine();
                    if (linha != null) {
                        String lote = linha.split(";")[0];
                        String endereco = linha.split(";")[1];
                        String bloco = linha.split(";")[2];
                        String numero = linha.split(";")[3];
                        String fornecedor = linha.split(";")[4];
                        String data = linha.split(";")[5];
                        String hora = linha.split(";")[6];
                        Mercadoria m = new Mercadoria(lote, endereco, bloco, numero, fornecedor, data, hora);
                        arv.inserir(m);
                    }
                } while (linha != null);
                arq.close();
                return "Arquivo lido com sucesso.";
            } catch (IOException ex) {
                return "Erro: Não foi possível ler o arquivo!" + ex.getMessage();
            }
        } catch (FileNotFoundException ex) {
            return "Erro: Arquivo não encontrado!" + ex.getMessage();
        }
    }

    /**
     *
     * @param caminho
     * @param arv
     * @return String
     */
    public static String ReadDoCsv(String caminho, Arvore arv) {
        try {
            FileReader arq = new FileReader(caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha;
            try {
                do {
                    linha = lerArq.readLine();
                    if (linha != null) {
                        String lote = linha.split(",")[0];
                        String endereco = linha.split(",")[1];
                        String bloco = linha.split(",")[2];
                        String numero = linha.split(",")[3];
                        String fornecedor = linha.split(",")[4];
                        String data = linha.split(",")[5];
                        String hora = linha.split(",")[6];
                        Mercadoria m = new Mercadoria(lote, endereco, bloco, numero, fornecedor, data, hora);
                        arv.inserir(m);
                    }
                } while (linha != null);
                arq.close();
                return "Arquivo lido com sucesso.";
            } catch (IOException ex) {
                return "Erro: Não foi possível ler o arquivo!" + ex.getMessage();
            }
        } catch (FileNotFoundException ex) {
            return "Erro: Arquivo não encontrado!" + ex.getMessage();
        }
    }

    /**
     *
     * @param caminho
     * @param arv
     * @return String
     */
    public static String Write(String caminho, Arvore arv) {
        try {
            FileWriter arq = new FileWriter(caminho);
            try (PrintWriter gravarArq = new PrintWriter(arq)) {
                Fila fila = arv.inOrdem();
                while (!fila.estaVazia()) {
                    Mercadoria merc = (Mercadoria) fila.removerInicio();
                    String texto = merc.getCodigoDeLote() + ";" + merc.getEndereco() + ";" + merc.getBloco() + ";" + merc.getNumero()
                            + ";" + merc.getFornecedor() + ";" + merc.getData() + ";" + merc.getHora() + ";";
                    gravarArq.println(texto);
                }
            }
            return "Dados salvos.";
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
