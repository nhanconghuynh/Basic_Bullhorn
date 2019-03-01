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

/*   @RequestMapping("/")
   public String listMessages(Model model){
        model.addAttribute("messages", messagesRepository.findAll());
        return "list";
    } */

    @RequestMapping("/login")
    public String loadLogin(Model model){
        model.addAttribute("message", new Message());
        return "loginform";
    }


   @RequestMapping("/processLogin")
    public String processLogin(@Valid @ModelAttribute("message") Message message, BindingResult result) {
        if (result.hasErrors()) {
            return "loginform";
        }
        return "confirmlogin";
    }


 /*   @GetMapping("/postmessage")
    public String courseForm(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }


    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("message") Message message, BindingResult result) {
        if (result.hasErrors()) {
            return "messageform";
        }
        messagesRepository.save(message);
        return "redirect:/";
    }


/*
    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("course", courseRepository.findById(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRepository.findById(id));
        return "courseform";
    }

    @RequestMapping("delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        courseRepository.deleteById(id);
        return "redirect:/";
    }

*/
}
