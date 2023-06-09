package com.plantplaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.plantplaces.dto.SpecimenDTO;
import com.plantplaces.service.ISpecimenService;

@Controller
public class PlantPlacesController {
	
	@Autowired
	private ISpecimenService specimenServiceStub;
	
	@RequestMapping(value="/start", method=RequestMethod.GET)
	@ResponseBody
	public SpecimenDTO read(Model model) {
		SpecimenDTO specimenDTO = specimenServiceStub.fetchById(43);
		model.addAttribute("specimenDTO", specimenDTO);
		return specimenDTO;
	}
	
	@RequestMapping(value="/start", method=RequestMethod.GET, params= {"loyalty=blue"})
	public String readBlue() {
		return "start";
	}
	
	@RequestMapping(value="/start", method=RequestMethod.GET, params= {"loyalty=silver"})
	public ModelAndView readSilver() {
		SpecimenDTO specimenDTO = specimenServiceStub.fetchById(43);
		specimenDTO.setSpecimenId(83);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("start");
		modelAndView.addObject("specimenDTO", specimenDTO);
		return modelAndView;
	}
	
	@RequestMapping(value="/start", method=RequestMethod.GET, headers= {"content-type=text/json"})
	public String readJSON() {
		return "start";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String addSpecimen(Model model, @RequestParam(value="latitude", required=false, defaultValue="39.74") String latitude) {
		SpecimenDTO specimenDTO = specimenServiceStub.fetchById(43);
		specimenDTO.setLatitude(latitude);
		model.addAttribute("specimenDTO", specimenDTO);
		return "start";
	}
	
	@PostMapping("/start")
	public String create() {
		return "start";
	}

}
