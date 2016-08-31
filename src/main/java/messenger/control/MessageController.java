package messenger.control;

import messenger.model.Message;
import messenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "{id}/send", method = RequestMethod.POST)
    public void send(@RequestBody Message message,
                     @RequestParam("email") String email,
                     @PathVariable("id") int id) {
        messageService.sendEmail(email, message);
    }
}
