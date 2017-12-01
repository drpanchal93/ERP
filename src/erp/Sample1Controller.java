/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class Sample1Controller implements Initializable {

    @FXML
    private AnchorPane content;

    /**
     * Initializes the controller class.
     * @param url
    */
    
    public Node HeaderReuse()
    {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("HeaderFooter.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Sample1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList<Node> nodes = root.getChildrenUnmodifiable();
        String _id = "headerPane";
        for(Node node : nodes)
        {
            if(node.getId().equals(_id))
            {
                return node;
            }
        }
        return null;
    }
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       HeaderReuse();
    }    
    
    
}
