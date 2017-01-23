package eu.kaszkowiak.poc.web;

import eu.kaszkowiak.poc.domain.FileEntry;
import eu.kaszkowiak.poc.service.DirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.security.Principal;

@Controller
public class DirectoryWatchController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private DirectoryService directoryService;

    private final Logger logger = LoggerFactory.getLogger(DirectoryWatchController.class);

    @MessageMapping("/get/allFiles")
    @SendToUser("/queue/allFiles")
    @CrossOrigin("http://localhost:4200")
    public void getAll(Principal principal) {
        Flux<FileEntry> entriesFlux = directoryService.getDirectoryContents();
        entriesFlux.subscribe(
                entry -> template.convertAndSendToUser(
                        principal.getName(), "/queue/allFiles", entry)
        );
        entriesFlux.doOnComplete(() -> logger.info("Cold flux completed."));
    }

    @MessageMapping("/get/changes")
    @SendTo("/queue/changes")
    @CrossOrigin("http://localhost:4200")
    public void getChanges() {
        Flux<FileEntry> entriesFlux = directoryService.getDirectoryChanges();
        entriesFlux.subscribe(
                entry -> template.convertAndSend("/queue/changes", entry)
        );
        entriesFlux.doOnComplete(() -> logger.info("Changes completed."));
    }

}
