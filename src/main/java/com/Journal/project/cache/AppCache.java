package com.Journal.project.cache;
import com.Journal.project.entity.ConfigJournalApp;
import com.Journal.project.repository.ConfigJournalAppRepos;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API,
        ELEVEN_API;
    }

    @Autowired
    private ConfigJournalAppRepos configJournalAppRepos;
    
    public Map<String,String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<String,String>();
        List<ConfigJournalApp> all = configJournalAppRepos.findAll();
        for(ConfigJournalApp configJournalApp : all){
            appCache.put(configJournalApp.getKey(),configJournalApp.getValue());
        }
    }
}
