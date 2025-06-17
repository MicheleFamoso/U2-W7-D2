package it.epicode.U2_W7_D2.security;



import it.epicode.U2_W7_D2.exception.UnAutorizedEx;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class jwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTool jwtTool;

    //Verra chiamato ad ogni richesta e fara
    // Verifica se e presente token
    //1 Se no token, eccezzione
    //2 se ha token, viene verificato, se non valido eccezzionew,
    //se valido accede la richesta ai filtri successivi
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


         String authorization = request.getHeader("Authorization");


         if (authorization== null || !authorization.startsWith("Bearer ")){
             throw new UnAutorizedEx("Token non valido");
         }else {
             String token = authorization.substring(7);

             jwtTool.validateToken(token);
             filterChain.doFilter(request,response);

         }



    }
        //Serve a non controllare perche appunto quei path permettono di registrasi
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

}
