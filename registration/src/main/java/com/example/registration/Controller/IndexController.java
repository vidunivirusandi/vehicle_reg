package com.example.registration.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.registration.Domain.DriverDAO;
import com.example.registration.Domain.Registration;
import com.example.registration.Repository.DriverRepository;
import com.example.registration.Service.DriverService;
import com.example.registration.Service.RegistrationService;

@Controller
@RequestMapping("/")
public class IndexController {
	 
		@Autowired
		private RegistrationService service;
		
		@Autowired
		private DriverService services;
		
		@Autowired
		private DriverRepository driverRepository;
		
		@RequestMapping(value= "/driver",method = RequestMethod.GET)
		public String viewDriverPage(Model model)
		{
			
			List<DriverDAO>  li = new ArrayList<>();
			for(Object[]o : driverRepository.findDriver()) {
				DriverDAO driver = new DriverDAO();
				driver.setId(Long.parseLong(String.valueOf(o[0])));
				driver.setDrname((String) o[1]);
				driver.setRegistration_plate((String) o[2]);
				li.add(driver);
			}
			
			model.addAttribute("listdriver", li);
			return "driver";
		}
		
		
		
		
		
		@RequestMapping(value = "/index", method = RequestMethod.GET)
		public String index()
		{
			return "index";
		}
		
		@RequestMapping(value = "/registration", method = RequestMethod.GET)
		public String viewHomePage(Model model)
		{
			List<Registration> listregistration = service.listAll();
			model.addAttribute("listregistration",listregistration);
			System.out.print("Get /");
			return "registration";
		}
		
}
