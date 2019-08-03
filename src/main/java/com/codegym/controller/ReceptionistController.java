package com.codegym.controller;

import com.codegym.model.Receptionist;
import com.codegym.model.ReceptionistFrom;
import com.codegym.service.ReceptionistService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/upload")
@PropertySource("classpath:upload.properties")
public class ReceptionistController {

    @Autowired
    Environment env;

    @Autowired
    private ReceptionistService receptionistService;

    @GetMapping("/list")
    public ModelAndView findAll() {
        Map<Integer, Receptionist> receptionist = receptionistService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("receptionists", receptionist);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showcreate() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("receptionistFrom", new ReceptionistFrom());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveReceptionist(@ModelAttribute ReceptionistFrom receptionistFrom) {
        int newId = receptionistFrom.getId();
        MultipartFile multipartFile = receptionistFrom.getAvartar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(receptionistFrom.getAvartar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Receptionist receptionistObject = new Receptionist(receptionistFrom.getId(), receptionistFrom.getName(), receptionistFrom.getAge(), receptionistFrom.getAddress(), receptionistFrom.getHobby(), fileName);
        receptionistService.uplate(newId, receptionistObject);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("productform", new ReceptionistFrom());
        modelAndView.addObject("message", "Created.");

        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView ViewReceptionist(@PathVariable("id") int id) {
        Receptionist receptionist = receptionistService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("receptionist", receptionist);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView EditReceptionistForm(@PathVariable int id) {
        Receptionist receptionist = receptionistService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("editReceptionist", receptionist);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView uplate(@ModelAttribute ReceptionistFrom receptionistFrom) {
        MultipartFile multipartFile = receptionistFrom.getAvartar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(receptionistFrom.getAvartar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Receptionist receptionist = new Receptionist(receptionistFrom.getId(), receptionistFrom.getName(), receptionistFrom.getAge(), receptionistFrom.getAddress(), receptionistFrom.getHobby(), fileName);
        receptionistService.uplate(receptionist.getId(), receptionist);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("receptionist", receptionistFrom);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView Delete(@PathVariable int id) {
        receptionistService.delete(id);
        return new ModelAndView("/list", "receptionists", receptionistService.findAll());
    }

    @GetMapping("/search")
    public ModelAndView Search(@RequestParam("search") String name) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("receptionists", receptionistService.searchByName(name));
        return modelAndView;
    }

}
