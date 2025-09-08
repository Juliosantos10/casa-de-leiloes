package beans;

import Connection.Conexao;
import beans.ProdutosDTO;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Connection;

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
         boolean encontrado = false;
        for (ProdutosDTO p : listagem) {
            if (p.getId() == id) {
                p.setStatu("Vendido"); // muda o status na lista em memória
                encontrado = true;

                // Atualiza também no banco de dados
                String sql = "UPDATE produtos SET statu = 'Vendido' WHERE id = ?";
                try (Connection conn = Conexao.getConnection();
                     PreparedStatement pst = conn.prepareStatement(sql)) {
                    pst.setInt(1, id);
                    pst.executeUpdate();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar banco: " + e.getMessage());
                }

                JOptionPane.showMessageDialog(null, "Produto ID " + id + " vendido com sucesso!");
                break;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Produto ID " + id + " não encontrado!");
        }
    }
    
     // Inicializa alguns produtos de exemplo
    public static void inicializarProdutos() {
        if (listagem.isEmpty()) {
            listagem.add(new ProdutosDTO(1, "Notebook", 3500.00, "Disponível"));
            listagem.add(new ProdutosDTO(2, "Celular", 1800.00, "Disponível"));
            listagem.add(new ProdutosDTO(3, "Fone Bluetooth", 250.00, "Disponível"));
        }
    }

    // Lista apenas produtos vendidos
    public ArrayList<ProdutosDTO> listarVendidos() {
        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        // Usando a lista em memória
        for (ProdutosDTO p : listagem) {
            if ("Vendido".equals(p.getStatu())) {
                lista.add(p);
            }
        }
        return lista;
    }
}
    
