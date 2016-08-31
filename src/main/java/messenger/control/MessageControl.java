package messenger.control;

import messenger.model.Message;
import messenger.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageControl {

    private final MailService mailService;

    @Autowired
    public MessageControl(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(value = "{id}/send", method = RequestMethod.POST)
    public void send(@RequestBody Message message,
                     @RequestParam("email") String email,
                     @PathVariable("id") int id) {
        mailService.sendEmail(email, id, message);
    }
}
