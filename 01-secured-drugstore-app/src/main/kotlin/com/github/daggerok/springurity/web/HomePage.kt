package com.github.daggerok.springurity.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomePage {

    @GetMapping(path = ["", "/", "home", "/home", "/home/"])
    fun index(model: Model): String = model.run {
        addAttribute("message", "This is an index home page")
        "index"
    }
}
