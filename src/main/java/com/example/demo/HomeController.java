package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    MessagesRepository messagesRepository;

   @RequestMapping("/")
   public String index(){
       return "index";
   }


   @RequestMapping("/list")
   public String listMessages(Model model){
        model.addAttribute("messages", messagesRepository.findAll());
        return "list";
    }

    @RequestMapping("/login")
    public String loadLogin(Model model){
        model.addAttribute("message", new Message("title", "content", "date" ));
        return "loginform";
    }

  /*  @RequestMapping("/add")
    public String addMessage(Model model){
//       Message message = new Message("init", "init", "init", "init");
       model.addAttribute("message", new Message());
        return "loginform";
    }
*/

   @PostMapping("/processLogin")
    public String processLogin(@Valid @ModelAttribute("message") Message message, BindingResult result,
                              Model model) {
       if (result.hasErrors()) {
            return "loginform";
        }
        messagesRepository.save(message);
        return "messageform";
    }

    @PostMapping("/processMessage")
    public String processMessage(@Valid @ModelAttribute("message") Message message, BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "messageform";
        }
        messagesRepository.save(message);
        return "redirect:/";
    }
 
    @RequestMapping("/detail/{id}")
    public String showMessage(@PathVariable("id") long id, Model model) {
        model.addAttribute("message", messagesRepository.findById(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messagesRepository.findById(id));
        return "messageform";
    }

    @RequestMapping("delete/{id}")
    public String delMessage(@PathVariable("id") long id){
        messagesRepository.deleteById(id);
        return "redirect:/";
    }


}
