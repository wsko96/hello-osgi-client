package org.example.tutorial.hello.osgi.client;

import org.example.tutorial.hello.osgi.service.api.SayingTeller;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

public class SayingClient implements BundleActivator, ServiceListener {

    private BundleContext ctx;
    private ServiceReference serviceRef;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting SayingClient.");
        this.ctx = context;

        try {
            ctx.addServiceListener(this, "(objectclass=" + SayingTeller.class.getName() + ")");
        }
        catch (InvalidSyntaxException ise) {
            ise.printStackTrace();
        }

        try {
            if (serviceRef == null) {
                serviceRef = ctx.getServiceReference(SayingTeller.class);
            }

            SayingTeller service = (SayingTeller) (ctx.getService(serviceRef));
            System.out.println(service.tellSaying());
        }
        catch (IllegalStateException ise) {
            ise.printStackTrace();
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping SayingClient.");

        if (serviceRef != null) {
            ctx.ungetService(serviceRef);
            serviceRef = null;
        }
    }

    @Override
    public void serviceChanged(ServiceEvent event) {
        final int eventType = event.getType();

        switch (eventType) {
        case (ServiceEvent.REGISTERED):
            System.out.println("Notification of service registered.");
            serviceRef = event.getServiceReference();
            SayingTeller service = (SayingTeller) (ctx.getService(serviceRef));
            System.out.println(service.tellSaying());
            break;
        case (ServiceEvent.UNREGISTERING):
            System.out.println("Notification of service unregistered.");
            ctx.ungetService(event.getServiceReference());
            break;
        default:
            break;
        }
    }

}
