package com.ism.controllers.store;

import com.ism.services.IClientService;
import com.ism.controllers.IClientView;

public interface IApplicationStorekeeper  {
    void subMenuClient(IClientService clientService, IClientView clientView);
}
