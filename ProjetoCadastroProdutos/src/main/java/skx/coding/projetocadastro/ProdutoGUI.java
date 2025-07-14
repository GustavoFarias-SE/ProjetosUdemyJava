package skx.coding.projetocadastro;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import skx.coding.projetocadastro.Codigo.ConexaoDB;
import skx.coding.projetocadastro.Codigo.Produto;
import skx.coding.projetocadastro.Codigo.ProdutoDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProdutoGUI extends Application {
    private ProdutoDAO produtoDAO;
    private ObservableList<Produto> produtos;
    private TableView<Produto> tableView;
    private TextField nomeInput, quantidadeInput, precoInput;
    private ComboBox<String> statusCombox;
    private Connection connexaoDB;

    @Override
    public void start(Stage stage) throws IOException {
        connexaoDB = ConexaoDB.conectarGenerico("jdbc:mysql://127.0.0.1:3306/javaprojects", "Gamer", "gustavo1967");
        produtoDAO = new ProdutoDAO(connexaoDB);
        produtos = FXCollections.observableArrayList(produtoDAO.listarTudo()); //Carrega todos os produtos do BD

        stage.setTitle("Gerenciamento de estoque de produtos");
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);

        HBox nomeProdutoHbox = new HBox();
        nomeProdutoHbox.setSpacing(10);
        Label label = new Label("Produto: ");
        nomeInput = new TextField();
        nomeProdutoHbox.getChildren().addAll(label, nomeInput);

        HBox quantidadeBox = new HBox();
        quantidadeBox.setSpacing(10);
        Label quantidadeLabel = new Label("Quantidade: ");
        quantidadeInput = new TextField();
        quantidadeBox.getChildren().addAll(quantidadeLabel, quantidadeInput);

        HBox precoBox = new HBox();
        precoBox.setSpacing(10);
        Label precoLabel = new Label("Preço: ");
        precoInput = new TextField();
        precoBox.getChildren().addAll(precoLabel, precoInput);

        HBox statusBox = new HBox();
        statusBox.setSpacing(10);
        Label statusLabel = new Label("Status: ");
        statusCombox = new ComboBox<>();
        statusCombox.getItems().addAll("Estoque normal", "Estoque baixo");
        statusBox.getChildren().addAll(statusLabel, statusCombox);


        Button addButton = new Button("Adicionar");
        addButton.setOnAction(e -> {
            String nome = nomeInput.getText();
            String quantidadeStr = quantidadeInput.getText();
            String precoStr = precoInput.getText().replace(',', '.');
            String status = statusCombox.getValue();

            try {
                int quantidade = Integer.parseInt(quantidadeStr);
                double preco = Double.parseDouble(precoStr);

                Produto produto = new Produto(nome, quantidade, preco, status);
                produtoDAO.inserir(produto);
                produtos.setAll(produtoDAO.listarTudo());
                tableView.getSelectionModel().selectLast();
                limparCampos();
            } catch (NumberFormatException ex) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro de número");
                alerta.setHeaderText(null);
                alerta.setContentText("Quantidade e preço devem conter valores válidos!");
                alerta.showAndWait();
            }
            limparCampos(); //Limpa os campos de entrada para uma nova Digitação
        });

        Button updateButton = new Button("Atualizar");
        updateButton.setOnAction(e -> {
            Produto selectedProduto = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduto != null) {
                selectedProduto.setNome(nomeInput.getText());
                selectedProduto.setQuantidade(Integer.parseInt(quantidadeInput.getText()));
                String preco = precoInput.getText().replace(',', '.');
                selectedProduto.setPreco(Double.parseDouble(preco));
                selectedProduto.setStatus(statusCombox.getValue());
                produtoDAO.atualizar(selectedProduto);
                produtos.setAll(produtoDAO.listarTudo());
                limparCampos();
            }
        });

        Button deletButton = new Button("Excluir");
        deletButton.setOnAction(e -> {
            Produto selectedProduto = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduto != null){
                produtoDAO.excluir(selectedProduto.getId());
                produtos.setAll(produtoDAO.listarTudo());
                limparCampos();
            }
        });

        Button clearButton = new Button("Limpar");
        clearButton.setOnAction(e -> limparCampos());

        tableView = new TableView<>();
        tableView.setItems(produtos);
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        List<TableColumn<Produto, ?>> columns = List.of(
                criarColuna("ID", "id"),
                criarColuna("Produto", "nome"),
                criarColuna("Quantidade", "quantidade"),
                criarColuna("Preço", "preco"),
                criarColuna("Status", "status")
        );
        tableView.getColumns().addAll(columns);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null){
                nomeInput.setText(newSelection.getNome());
                quantidadeInput.setText(String.valueOf(newSelection.getQuantidade()));
                precoInput.setText(String.valueOf(newSelection.getPreco()));
                statusCombox.setValue(newSelection.getStatus());
            }
        });

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(addButton, updateButton, deletButton, clearButton);

        vBox.getChildren().addAll(nomeProdutoHbox, quantidadeBox, precoBox, statusBox, buttonBox, tableView);

        Scene scene= new Scene(vBox, 800, 600);
        scene.getStylesheets().add("/estilos.css");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop(){
        try{
            connexaoDB.close();
        } catch (SQLException e) {
          System.err.println("ERRO ao fechar conexao " + e.getMessage());
        }
    }

    //Método limparCampos()
    private void limparCampos(){
        nomeInput.clear();
        quantidadeInput.clear();
        precoInput.clear();
        statusCombox.setValue(null);
    }
    /*
    Cria coluna para a tableView
    @param title da coluna será exibido no cabeçalho
    @param property A propriedade do objetivo Produto que esta coluna deve exibir
    @return A coluna configurada para a tableView
     */

    //método criarColuna
    private <T> TableColumn<Produto, T> criarColuna(String title, String property) {
        TableColumn<Produto, T> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }

    public static void main(String[] args) {
        launch();
    }
}