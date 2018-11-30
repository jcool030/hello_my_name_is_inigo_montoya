package com.seg2105app.services;

import com.seg2105app.users.ServiceProvider;

public class ServiceListing {
    private Service service;
    private ServiceProvider provider;

    public void attachServiceToProvider(Service service, ServiceProvider provider){
        this.service = service;
        this.provider = provider;
    }

}
