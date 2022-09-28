package am.itspace.companyemployeespringproject.controller;

import am.itspace.companyemployeespringproject.entity.Company;
import am.itspace.companyemployeespringproject.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository companyRepository;

    @GetMapping("/companies")
    public String goToCompaniesPage(ModelMap modelMap) {
        modelMap.addAttribute("companyList", companyRepository.findAll());
        return "/companies";
    }

    @GetMapping("company/add")
    public String goToAddCompanyPage() {
        return "addCompany";
    }

    @PostMapping("/company/add")
    public String addAndGoToCompaniesPage(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/company/remove")
    public String removeCompanyById(@RequestParam("id") int id) {
        companyRepository.deleteById(id);
        return "redirect:/companies";
    }

}
