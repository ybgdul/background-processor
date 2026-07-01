package side_job.app.components;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import side_job.app.utilities.Interfaces.JobHandler;

@Component
public class JobMatcher {
    private final Map<String, JobHandler> handlers;

    public JobMatcher(List<JobHandler> handlerList) { 
        this.handlers = handlerList.stream().collect(Collectors.toMap(JobHandler::type, h -> h));
    }

    public JobHandler findHandler(String type) { 
        return handlers.get(type);
    }
}
