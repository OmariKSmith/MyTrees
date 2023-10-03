package com.btree;

import com.btree.model.BinaryTree;
import com.btree.view.BTView;
import com.btree.view.BTViewUserControls;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BTreeVisualizer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BinaryTree<Integer> treeModel = new BinaryTree<>();
        BTView treeView = new BTView(treeModel);
        BTViewUserControls controlPane = new BTViewUserControls(treeView, treeModel);
        treeModel.insert(10);
        treeModel.insert(20);
        treeModel.insert(15);
        treeModel.insert(30);
        treeModel.insert(5);
        treeModel.insert(6);
        treeModel.insert(2);

        System.out.println();
        treeModel.inorder();
        System.out.println();
        treeModel.postorder();
        System.out.println();
        treeModel.preorder();
        Scene scene = new Scene(controlPane, 450, 250);
        stage.setTitle("BTreeVisualizer");
        stage.setScene(scene);
        stage.show();

    }
}