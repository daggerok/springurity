package com.github.daggerok.springurity.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AboutPage {

    @GetMapping(path = ["about", "/about", "/about/"])
    fun about(model: Model): String = model.run {
        addAttribute("message", "This is an about page")
        "about"
    }
}
