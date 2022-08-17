package teamMurange.Murange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import teamMurange.Murange.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public String index(Model model) {
        System.out.println("OK");
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("googleName", user.getName());
        }
        return "index";
    }
//
//    @GetMapping("/{user-id}")
//    public String control(Long id, Model model){
//        model.addAttribute("googleName",userRepository.findById(id).get());
//        return "index";
//    }
    @GetMapping("/idx")
    public String idx(Model model) {
        System.out.println("OK");
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("googleName", user.getName());
        }
        return "idx";

    }
    @GetMapping("/face")
    public String face(Model model) {
        System.out.println("OK");
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("googleName", user.getName());
        }
        return "face";
    }
}

