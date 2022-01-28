package spring.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomePageController {

    @GetMapping("/index.html")
    public String homePage() {
        return "index";
    }

    @GetMapping("/post.html")
    public String postPage() {
        return "post";
    }

    @GetMapping("/about.html")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/contact.html")
    public String contactPage() {
        return "contact";
    }

    @GetMapping("/test")
    public String testPage() {
        return "test";
    }
}
