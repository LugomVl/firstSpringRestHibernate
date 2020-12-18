package ru.lugom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.lugom.model.Film;
import ru.lugom.service.FilmService;
import ru.lugom.service.FilmServiceImpl;

@Controller
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping(value="/")
    public ModelAndView allFilms(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("filmsList", filmService.allFilms());
        return modelAndView;
    }

    @GetMapping(value="/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", filmService.getById(id));
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView editFilm(@ModelAttribute("film") Film film){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        filmService.edit(film);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @PostMapping(value="/add")
    public ModelAndView addFilm(@ModelAttribute Film film){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        filmService.add(film);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteFilm(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        filmService.delete(filmService.getById(id));
        return modelAndView;
    }
}
