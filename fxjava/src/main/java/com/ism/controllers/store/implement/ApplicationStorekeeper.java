package com.ism.controllers.store.implement;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;


import com.ism.data.entities.User;
import com.ism.services.IClientService;
import com.ism.services.IDemandeDetteService;
import com.ism.services.IDetteService;
import com.ism.services.IPaiementService;
import com.ism.services.implement.DetteService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import com.ism.controllers.IClientView;
import com.ism.controllers.implement.Application;
import com.ism.controllers.store.IApplicationStorekeeper;
import com.ism.core.factory.IFactory;
import com.ism.core.factory.implement.Factory;
import com.ism.core.helper.Success;
import com.ism.core.helper.Tools;

public class ApplicationStorekeeper extends Application implements IApplicationStorekeeper, Initializable 
{
    private IClientService clientService;
    private IDetteService detteService;

    // >> Chargement de données du dashboard
    public Label numCustomer;
    public Label numDette;
    public Label numPaiement;
    public Label numDemandeDette;
    public Label infoConnect;
    // <<


    public ApplicationStorekeeper()
     {
      
        this.detteService = null;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {

        this.detteService = new DetteService();

    
        Success.showSuccessMsg("Message Connexion", "Vous êtes connecté avec succès.");
       
        loadDash();
    }

    public void loadDash() 
    {
        this.numCustomer.setText("Clients ("+ clientService.getAllActifsWithAccount().size() +")");
        this.numDette.setText("Dettes ("+ detteService.length() +")");

    }

    @FXML
    public void loadGestionClient(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Gestion Client", "/com/ism/views/gestion.store.client.fxml");
        // Mettre à jour les labels une fois les services initialisés
        loadDash(); 
    }

    @FXML
    public void loadGestionDetteClient(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Gestion Dette", "/com/ism/views/gestion.store.dette.fxml");
        // Mettre à jour les labels une fois les services initialisés
        loadDash(); 
    }

    @FXML
    public void loadCreatedPay(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Créer un paiement", "/com/ism/views/gestion.store.pay.fxml");
        // Mettre à jour les labels une fois les services initialisés
        loadDash(); 
    }

    @FXML
    public void loadDemandeDetteEnCour(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Liste Demande Dette", "/com/ism/views/list.demande.dette.fxml");
        // Mettre à jour les labels une fois les services initialisés
        loadDash(); 
    }

    @FXML
    @Override
    public void logout(ActionEvent e) throws IOException {
        // Retourner à la page de connexion
        Tools.load(e, "Gestion Dette Boutiquier", "/com/ism/views/connexion.fxml");
    }

    @Override
    public int menu() {
        throw new UnsupportedOperationException("Unimplemented method 'menu'");
    }

    @Override
    public void run(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    @Override
    public void subMenuClient(IClientService clientService, IClientView clientView) 
    {
        throw new UnsupportedOperationException("Unimplemented method 'subMenuClient'");
    }

  
   
}
