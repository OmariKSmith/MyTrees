package com.btree.view;

import com.btree.model.BinaryTree;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BTViewUserControls extends BorderPane{

    public BTViewUserControls(BTView treeView, BinaryTree treeModel){
        BorderPane controlPane = this;
        controlPane.setCenter(treeView);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);

        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key "),
                tfKey, btInsert, btDelete);
        hBox.setAlignment(Pos.CENTER);
        controlPane.setBottom(hBox);

        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            System.out.println(" KEY = " + key);
            if (treeModel.search(key)) {
                treeView.displayTree();
                treeView.setStatus(key + "is already in the tree");
            }
            else {
                treeModel.insert(key);
                treeView.displayTree();
                treeView.setStatus(key + "is inserted in the tree");
            }
        });

        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!treeModel.search(key)) {
                treeView.displayTree();
                treeView.setStatus(key + "is not in tree");
            }
            else {
                treeModel.delete(key);
                treeView.displayTree();
                treeView.setStatus(key + " is deleted from tree");
            }
        });
    }


}
