package com.seg2105app.services;

import com.seg2105app.users.ServiceProvider;

public class ServiceListing {
    private Service service;
    private ServiceProvider provider;

    public ServiceListing(Service service, ServiceProvider provider){
        this.service = service;
        this.provider = provider;
    }
    public ServiceListing(){
        this.service = null;
        this.provider = null;
    }

    public Service getService(){
        return service;
    }
    public ServiceProvider getServiceProvider(){
        return provider;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setProvider(ServiceProvider provider) {
        this.provider = provider;
    }
}
