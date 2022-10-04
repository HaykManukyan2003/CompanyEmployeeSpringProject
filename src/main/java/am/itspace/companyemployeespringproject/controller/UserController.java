package am.itspace.companyemployeespringproject.controller;

import am.itspace.companyemployeespringproject.entity.User;
import am.itspace.companyemployeespringproject.repository.UserRepository;
import am.itspace.companyemployeespringproject.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String mainPage() {
        return "index";
    }

    @GetMapping("/register")
    public String goToRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, ModelMap modelMap) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            modelMap.addAttribute("errorMessage", "username with this exact email already exists");
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/loginPage";
    }

    @GetMapping("/loginPage")
    public String goToLoginPage() {
        return "loginPage";
    }

    @PostMapping("/login")
    public String login(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser == null ? "loginPage" : "index";
    }
}
