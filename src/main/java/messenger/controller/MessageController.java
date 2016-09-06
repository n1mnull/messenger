package messenger.controller;

import messenger.domain.Message;
import messenger.exception.BadRequestException;
import messenger.model.MessageModel;
import messenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
    public Message send(@RequestBody MessageModel message,
                        @RequestParam("email") String email,
                        @PathVariable("id") String id) {
        try {
            return messageService.sendEmail(id, email, message);
        } catch (DuplicateKeyException e) {
            throw new BadRequestException();
        }
    }
}
