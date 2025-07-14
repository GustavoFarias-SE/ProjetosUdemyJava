module skx.coding.projetocadastro {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens skx.coding.projetocadastro to javafx.fxml;
    opens skx.coding.projetocadastro.Codigo to javafx.base;

    exports skx.coding.projetocadastro;
}