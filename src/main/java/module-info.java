module com.btree {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.btree to javafx.fxml;
    exports com.btree;
}