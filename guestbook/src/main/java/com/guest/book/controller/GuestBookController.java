package com.guest.book.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guest.book.model.UserData;
import com.guest.book.model.UserModel;
import com.guest.book.service.UserService;

@Controller
public class GuestBookController {
	Logger logger = LoggerFactory.getLogger(GuestBookController.class);
	private final String UPLOAD_DIR = "D://uploads//";
	@Autowired

	Environment env;
	@Autowired
	UserService userServ;

	@RequestMapping(path = "/")
	public String index(Model model) {
		logger.info("index");
		model.addAttribute("user", new UserModel());
		return "index";

	}

	@PostMapping("/login")
	public String submitForm(@ModelAttribute UserModel user, Model model) {
		logger.info("login ");
		model.addAttribute("user", userServ.validateUser(user));
		logger.info("login End");
	
		if (user.getRole()=="user") {
			return "user.html";
		}
		else {
			return "admin.html";
		}
	
	}

	@PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes attributes,UserData data) {
if(data.isImage())
{
		
        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
    data.setImageStatus("success");
  	model.addAttribute("user", data);
	
}
else
{
	data.setEntryStatus("success");
	model.addAttribute("user", data);
	
}
return "user.html";
    }

	
}
