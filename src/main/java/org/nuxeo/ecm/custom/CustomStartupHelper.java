package org.nuxeo.ecm.custom;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.webapp.helpers.StartupHelper;
import org.nuxeo.runtime.api.Framework;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("startupHelper")
@Scope(org.jboss.seam.ScopeType.SESSION)
@Install(precedence=Install.DEPLOYMENT)
public class CustomStartupHelper extends StartupHelper {

    protected static final String HOME_VIEW = "view_home";

    private static final long serialVersionUID = 1L;

    private static final Log LOGGER = LogFactory.getLog(CustomStartupHelper.class);

    @Override
    public String initDomainAndFindStartupPage(String domainTitle, String viewId) {
        String view = super.initDomainAndFindStartupPage(domainTitle, viewId);
        String viewOrPath = Framework.getProperty("CustomStartupHelper.viewOrPath");
        if (viewOrPath != null) {
            String defaultErrMessage = "Unable to redirect to " + viewOrPath + ", redirecting to default page ...";
            if (viewOrPath.startsWith("/")) {
                PathRef ref = new PathRef(viewOrPath);
                try {
                    if (documentManager.exists(ref)) {
                        view = navigationContext.navigateToRef(ref);
                    } else {
                        LOGGER.error(defaultErrMessage + " 1");
                    }
                } catch (ClientException e) {
                    LOGGER.error(defaultErrMessage + " 2");
                }
            } else {
                if (HOME_VIEW.equals(viewOrPath)) {
                    return dashboardNavigationHelper.navigateToDashboard();
                }
            }
        }
        return view;
    }

}
