package Controller;
import Model.Mercadoria;
import Util.AllanArch;
import Util.Arvore;
import Util.Fila;
/**
 * 
 * @author allan
 */
public class Handler {
    Arvore arv = new Arvore();
    /**
     * 
     * @param caminho
     * @return String
     */
    public String carregarListaInicial(String caminho){
        String str = AllanArch.ReadDoCsv(caminho, arv);
        return str;
    }
    /**
     * Sem parametros
     * @return String
     */
    public String carregarDados(){
        // esse método vai carregar uma lista de mercadorias já salvas através de um arquivo na pasta do sistema
        String arquivo = "C:\\Users\\allan\\OneDrive\\Documentos\\NetBeansProjects\\WMSLojas\\WMSBD.txt";
        String str = AllanArch.ReadDoSistema(arquivo, arv);
        return str;
        
    }
    /**
     * Sem parametros
     * @return String
     */
    public String salvarDados(){
        // esse método vai salvar/escrever dados no arquivo que fica na pasta do sistema
        // pegar a árvore, percorrer, e ir salvando os dados no arquivo
        String str = AllanArch.Write("C:\\Users\\allan\\OneDrive\\Documentos\\NetBeansProjects\\WMSLojas\\WMSBD.txt", arv);
        return str;
    }
    /**
     * 
     * @param codigo
     * @param bloco
     * @param numero
     * @param fornecedor
     * @param data
     * @param hora
     * @param endereco
     * @return String
     */
    public String cadastrarMercadoria(String codigo, String bloco, String numero, String fornecedor, String data, String hora, String endereco) {
        // inserir mercadoria na árvore
        Mercadoria m = new Mercadoria(codigo,bloco,numero,fornecedor, data, hora, endereco);
        arv.inserir(m);
        return "Inserido com sucesso!";
    }
    /**
     * 
     * @param mercadoria
     * @return String
     */
    public Mercadoria removerMercadoria(Mercadoria mercadoria){
        // pesquisar a mercadoria a ser removida na árvore e remover ela
        Mercadoria m = (Mercadoria) arv.remove(mercadoria);
        return m;
    }
    /**
     * 
     * @param merc
     * @return String
     */
    public Mercadoria buscarMercadoria(Mercadoria merc){ // a fazer
        // pesquisar mercadoria na árvore através do seu código que é único
        // retornar essa mercadoria
        Mercadoria m = (Mercadoria) arv.buscaBin(merc);
        return m;
    }
    /**
     * Sem parametros
     * @return Fila
     */
    public Fila listarMercadorias(){ // a fazer
        // usar o iterador da árvore para passar as mercadorias para um vetor
        // ordenar antes de retornar o vetor
        Fila fila = arv.inOrdem();
        return fila;
    }
    /**
     * Sem parametros
     * @return int
     */
    public int contarMercadorias(){ // a fazer
        // obter tamanho da árvore
        return arv.size();
    }
}
