package com.seg2105app.services;

import com.seg2105app.users.ServiceProvider;

public class ServiceListing {
    private Service service;
    private ServiceProvider provider;

    public ServiceListing(Service service, ServiceProvider provider){
        this.service = service;
        this.provider = provider;
    }

    public Service getService(){
        return service;
    }
    public ServiceProvider getServiceProvider(){
        return provider;
    }


}
