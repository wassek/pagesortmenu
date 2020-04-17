package org.jahia.modules.pagesortmenu.activator;

import javax.servlet.ServletException;

import org.jahia.modules.pagesortmenu.servlet.SortPageServlet;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageSortMenuActivator implements BundleActivator {

	 private static final Logger logger = LoggerFactory.getLogger(PageSortMenuActivator.class);
	@Override
	public void start(BundleContext bundleContext) throws Exception {
        ServiceReference<?> realServiceReference = bundleContext.getServiceReference(HttpService.class.getName());
        HttpService httpService = (HttpService) bundleContext.getService(realServiceReference);
        SortPageServlet pageServlet = new SortPageServlet();
        try {
            httpService.registerServlet("/org.jahia.modules.sort.page", pageServlet, null, null);
            logger.info("Successfully registered custom servlet at /modules/org.jahia.modules.sort.page");
        } catch (ServletException e) {
           logger.error("ServletInitialization of SortPage failed: ", e);
        }
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		
		
        ServiceReference<?> realServiceReference = bundleContext.getServiceReference(HttpService.class.getName());
        HttpService httpService = (HttpService) bundleContext.getService(realServiceReference);
        httpService.unregister("/org.jahia.modules.sort.page");
        logger.info("Successfully unregistered custom servlet /modules/org.jahia.modules.sort.page");
	}

}
