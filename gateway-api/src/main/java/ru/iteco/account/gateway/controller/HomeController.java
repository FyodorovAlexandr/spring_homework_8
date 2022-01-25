package ru.iteco.account.gateway.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.iteco.account.gateway.model.Tickets;
import ru.iteco.account.gateway.service.CurrencyApiService;
import ru.iteco.account.gateway.service.StockApiService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final CurrencyApiService currencyApiService;
    private final StockApiService stockApiService;

    @GetMapping("/")
    public String getHome(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            log.info("USER: {}", principal.getClaims());
            log.info("AUT: {}", principal.getAuthorities());
            model.addAttribute("profile", principal.getClaims());
        }
        return "index";
    }

    @GetMapping("/all-exchange")
    public @ResponseBody String getAllExchange() {
        return currencyApiService.getAllExchange();
    }

    @GetMapping("/convert")
    public @ResponseBody String convert() {
        return currencyApiService.convert();
    }

    @PostMapping("/get-stock-quotes")
    public @ResponseBody String getStockQuotes(@ModelAttribute("tickets") Tickets tickets) {
        return stockApiService.getStockQuotes(tickets.getData());
    }

}
