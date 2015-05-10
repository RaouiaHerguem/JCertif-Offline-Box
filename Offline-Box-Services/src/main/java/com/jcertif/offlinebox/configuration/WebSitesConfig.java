package com.jcertif.offlinebox.configuration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcertif.offlinebox.beans.WebSite; 
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;  
import com.jcertif.offlinebox.beans.WebSiteWrapper;
/**
 *
 * @author Komi Serge Innocent <komi.innocent@gmail.com>
 */
public class WebSitesConfig {
    
    private static final String WEB_SITES_FILE_NAME = "webSites.json";
    
    private static final WebSitesConfig INSTANCE = new WebSitesConfig();

    public static WebSitesConfig getInstance() {
        return INSTANCE;
    }

    /**
     * 
     * @return 
     */
    public List<WebSite> retieveWebSiteFromConfigFile() {
        
        List<WebSite> sites = null;
        
        try {
            InputStream in = WebSitesConfig.class.getResourceAsStream(WEB_SITES_FILE_NAME);
            final ObjectMapper objectMapper = new ObjectMapper();
            WebSiteWrapper webSiteWrapper = objectMapper.readValue(in, WebSiteWrapper.class);            
            sites = webSiteWrapper.getWebSites();
        } catch (JsonParseException e) {
            Logger.getLogger(WebSitesConfig.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(WebSitesConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sites;
    }
}
