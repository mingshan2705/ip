module yapper {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    
    opens yapper to javafx.fxml;
    opens yapper.ui to javafx.fxml;
    exports yapper;
    exports yapper.ui;
}