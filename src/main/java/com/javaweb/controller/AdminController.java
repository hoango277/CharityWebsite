package com.javaweb.controller;

import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.model.response.CharityProgramResponse;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.model.response.TransactionAdminResponse;
import com.javaweb.model.response.UserResponse;
import com.javaweb.service.CharityProgramService;
import com.javaweb.service.TransactionService;
import com.javaweb.service.UploadImage;
import com.javaweb.service.UserService;
import com.javaweb.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private CharityProgramService charityProgramService;
    @Autowired
    private UploadImage uploadImage;
    @Autowired
    private TransactionService transactionService;


    @GetMapping("")
    public String adminPage(
            Model model,
            @RequestParam(name = "pageNumber", defaultValue = MessageUtils.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = MessageUtils.PAGE_SIZE, required = false) Integer pageSize) {

        Page<UserResponse> userAccounts = userService.getAllUser(pageNumber,pageSize);

        model.addAttribute("userAccounts", userAccounts.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", userAccounts.getTotalPages());
        return "admin";
    }

    @GetMapping("/user-manage")
    public String userManagePage(
            Model model,
            @RequestParam(name = "pageNumber", defaultValue = MessageUtils.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = MessageUtils.PAGE_SIZE, required = false) Integer pageSize) {

        Page<UserResponse> userAccounts = userService.getAllUser(pageNumber,pageSize);

        model.addAttribute("userAccounts", userAccounts.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", userAccounts.getTotalPages());
        return "admin/user-account";
    }

    @GetMapping("/program-manage")
    public String programManagePage(Model model, @RequestParam(defaultValue = "0") int pageNumber) throws ParseException {
        Page<CharityProgramResponse> list = charityProgramService.getAllCharityPrograms(pageNumber, 6);

        model.addAttribute("charityProjects", list.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("totalItems", list.getTotalElements());

        return "admin/project";
    }

    @PostMapping("/add-project")
    public String addProject(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                             @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                             @RequestParam("address") String address,
                             @RequestParam("amountNeeded") Long amountNeeded,
                             @RequestParam("image") MultipartFile image) throws IOException, ParseException {

        String img = uploadImage.uploadImage(image);

        CharityProgramEntity charityProgramEntity = new CharityProgramEntity();
        charityProgramEntity.setName(name);
        charityProgramEntity.setDescription(description);
        charityProgramEntity.setStartDate(startDate);
        charityProgramEntity.setEndDate(endDate);
        charityProgramEntity.setAddress(address);
        charityProgramEntity.setAmountNeeded(amountNeeded);
        charityProgramEntity.setImage(img);

        CharityProgramResponse charityProgramResponse = charityProgramService.createCharityProgram(charityProgramEntity);

        return "redirect:/admin/program-manage";
    }

    @PostMapping("/update-project")
    public String updateProject(@RequestParam("Id") Long id,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                @RequestParam("address") String address,
                                @RequestParam("amountNeeded") Long amountNeeded,
                                @RequestParam("image") MultipartFile image) throws IOException, ParseException {

        String img = null;
        if(!image.isEmpty()){
            img = uploadImage.uploadImage(image);
        }
        CharityProgramResponse charityProgramResponse = charityProgramService.getCharityProgramById(id);
        CharityProgramEntity charityProgramEntity = new CharityProgramEntity();

        charityProgramEntity.setName(name);
        charityProgramEntity.setDescription(description);
        charityProgramEntity.setStartDate(startDate);
        charityProgramEntity.setEndDate(endDate);
        charityProgramEntity.setAddress(address);
        charityProgramEntity.setAmountNeeded(amountNeeded);
        charityProgramEntity.setTotalAmount(charityProgramResponse.getTotalAmount());
        if(img != null){
            charityProgramEntity.setImage(img);
        }

        charityProgramResponse = charityProgramService.updateCharityProgram(id,charityProgramEntity);

        return "redirect:/admin/program-manage";
    }

    @RequestMapping("delete-project")
    public String deleteProject(@RequestParam("id") Long id) throws ParseException {
        StatusResponse statusResponse = charityProgramService.deleteCharityProgram(id);
        return "redirect:/admin/program-manage";
    }

    @GetMapping("/transaction-manage")
    public String transactionManagePage(
            Model model,
            @RequestParam(name = "pageNumber", defaultValue = MessageUtils.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = MessageUtils.PAGE_SIZE, required = false) Integer pageSize) throws ParseException {
        Page<TransactionAdminResponse> transactions = transactionService.getAllTransactions(pageNumber, pageSize);

        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("transactions", transactions.getContent());
        model.addAttribute("totalPage", transactions.getTotalPages());
        return "admin/transaction-manage";
    }

}
