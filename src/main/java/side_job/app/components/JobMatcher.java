package side_job.app.components;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import side_job.app.entities.Job;
import side_job.app.entities.JobContext;
import side_job.app.utilities.Interfaces.JobHandler;

@Component
public class JobMatcher {
    private final Map<String, JobHandler<?>> handlers;

    private final ModelMapper modelMapper;

    public JobMatcher(List<JobHandler<?>> handlerList, ModelMapper modelMapper) { 
        this.handlers = handlerList.stream().collect(Collectors.toMap(JobHandler::getType, h -> h));
        this.modelMapper = modelMapper;
    }

    public void dispatch(Job job, int attempt) throws Exception { 
        JobHandler<?> handler = handlers.get(job.getType());
        if(handler == null) throw new IllegalStateException("no such handler yo: " + job.getType());
        invoke(handler, job, attempt);
    }

    public <T> void invoke(JobHandler<T> handler,  Job job, int attempt) throws Exception {
        T payload = modelMapper.map(job.getPayload(), handler.payloadType());

        JobContext context = new JobContext(job.getId(), attempt, Instant.now());

        handler.handle(context, payload);
    }
}
