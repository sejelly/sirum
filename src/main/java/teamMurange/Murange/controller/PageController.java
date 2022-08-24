package teamMurange.Murange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import teamMurange.Murange.repository.UserRepository;
import teamMurange.Murange.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class PageController {
    private final HttpSession httpSession;
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value="/user/current",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUser() throws Exception{
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        Map<String,Object> returnMap = new HashMap<>();
        if(user!=null){
            returnMap.put("name",user.getName());
            returnMap.put("email",user.getEmail());
            returnMap.put("img_path",user.getPicture());
            returnMap.put("userId",user.getUserId());

        }
        return returnMap;
    }

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

