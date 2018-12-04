package com.happybudui.venuesystem.controller;

import com.happybudui.venuesystem.service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Controller
@RequestMapping("/venue/")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService){
        this.venueService=venueService;
    }
    

}
