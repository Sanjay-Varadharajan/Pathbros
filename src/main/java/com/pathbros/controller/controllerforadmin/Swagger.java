package com.pathbros.controller.controllerforadmin;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/docs")
public class Swagger {

    @GetMapping("/swagger")
    public void redirectToSwagger(HttpServletResponse response) throws Exception{
        response.sendRedirect("/swagger-ui/index.html");
    }
}
