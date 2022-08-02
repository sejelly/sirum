package teamMurange.Murange.controller;

import teamMurange.Murange.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class PageController {
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("googleName", user.getName());
        }
        return "index";
    }

}

