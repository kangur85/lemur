package eu.kaszkowiak.poc.web;

import eu.kaszkowiak.poc.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.security.Principal;

@Controller
public class DirectoryWatchController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private DirectoryService directoryService;

    @MessageMapping("/lemur")
    @SendToUser("/queue/allFiles")
    @CrossOrigin("http://localhost:4200")
    public void getAll(Principal principal) {
        directoryService.getDirectoryContents().subscribe(
                entry -> template.convertAndSendToUser(
                        principal.getName(), "/queue/allFiles", entry)
        );
    }

}
