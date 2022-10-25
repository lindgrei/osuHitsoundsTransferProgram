module git.lindgrei.ohstp.ohstp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens git.lindgrei.ohstp.ohstp to javafx.fxml;
    exports git.lindgrei.ohstp.ohstp;
}