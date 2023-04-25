package com.example.musicplayer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.musicplayer.model.Song;
import com.example.musicplayer.service.SongService;

@Controller
public class SongController {
	@Autowired
	private SongService Service;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Song> listSong = Service.listAll();
		model.addAttribute("listSong", listSong);
		System.out.print("Get / ");
		return "index";
	}

	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("Song", new Song());
		return "new";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveSong(@ModelAttribute("Song") Song std) {
		Service.save(std);
		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditSongPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("new");
		Song std = Service.get(id);
		mav.addObject("Song", std);
		return mav;
	}

	@RequestMapping("/delete/{id}")
	public String deleteSong(@PathVariable(name = "id") int id) {
		Service.delete(id);
		return "redirect:/";
	}
}