package beans;

import beans.ProdutosDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    // Lista dinâmica que mantém os produtos em memória
    private static ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    private static int ultimoId = 0; // controla o último ID usado

    // Método para cadastrar produto na lista
    public void cadastrarProduto(ProdutosDTO produto) {
        try {
             // gera ID automaticamente
            ultimoId++;
            produto.setId(ultimoId);
            
            listagem.add(produto);
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    // Retorna a lista de produtos já cadastrados
    public ArrayList<ProdutosDTO> listarProdutos() {
        return listagem;
    }

    // Método para vender (alterar status do produto pelo ID)
    public void venderProduto(int id) {
        for (ProdutosDTO p : listagem) {
            if (p.getId() == id) {
                p.setStatu("Vendido"); // muda o status
                JOptionPane.showMessageDialog(null, "Produto ID " + id + " vendido com sucesso!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Produto ID " + id + " não encontrado!");
    }

    // Inicializa alguns produtos de exemplo
    public static void inicializarProdutos() {
        if (listagem.isEmpty()) {
            listagem.add(new ProdutosDTO(1, "Notebook", 3500.00, "Disponível"));
            listagem.add(new ProdutosDTO(2, "Celular", 1800.00, "Disponível"));
            listagem.add(new ProdutosDTO(3, "Fone Bluetooth", 250.00, "Disponível"));
        }
    }
}