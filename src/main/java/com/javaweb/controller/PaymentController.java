package com.javaweb.controller;

import com.javaweb.service.PaymentServices;
import com.javaweb.service.VolunteerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;


@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    private PaymentServices paymentServices;
    @Autowired
    private VolunteerService volunteerService;

    @PostMapping
    public void processDonation(@RequestParam String amount,
                                @RequestParam Long projectID,
                                @RequestParam(required = false,defaultValue = "false") Boolean anonymous,
                                @RequestParam String userID,
                                HttpServletResponse response,
                                HttpSession session,
                                HttpServletRequest request
                                ) throws IOException {
        session.setAttribute("userID", userID);
        session.setAttribute("amount", amount);
        session.setAttribute("projectID", projectID);
        session.setAttribute("anonymous", anonymous);
        String vnpUrl = paymentServices.createVnPayPayment(request, amount).getBody().getUrl();
        response.sendRedirect(vnpUrl);
    }

    @GetMapping("/return")
    public String VNPAYReturn(@RequestParam Map<String, String> allParams, HttpSession session, Model model) {
        String vnpResponseCode = allParams.get("vnp_ResponseCode");
        String sessionAmount = (String) session.getAttribute("amount");
        Long projectId = (Long) session.getAttribute("projectID");
        Boolean anonymous = (Boolean) session.getAttribute("anonymous");
        String userID = (String) session.getAttribute("userID");

        if ("00".equals(vnpResponseCode)) {
            model.addAttribute("message", "Giao dịch thành công");
            model.addAttribute("amount", sessionAmount);
            model.addAttribute("projectID", projectId);
            model.addAttribute("anonymous", anonymous);
            volunteerService.addVolunteer(projectId, Long.parseLong(sessionAmount), Long.parseLong(userID),anonymous);
        } else {
            model.addAttribute("message", "Giao dịch thất bại");
        }

        return "donation/result";
    }


}
