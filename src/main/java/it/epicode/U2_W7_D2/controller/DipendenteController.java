package it.epicode.U2_W7_D2.controller;



import it.epicode.U2_W7_D2.dto.DipendenteDto;
import it.epicode.U2_W7_D2.exception.NonTrovatoException;
import it.epicode.U2_W7_D2.model.Dipendente;
import it.epicode.U2_W7_D2.service.DipendenteService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class DipendenteController {

    @Autowired
    DipendenteService dipendenteService;


    @PostMapping("/dipendenti")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).reduce("",(e, c)->e+c));
        }
        return dipendenteService.saveDipendente(dipendenteDto);

    }
    @GetMapping("/dipendenti")
    public List<Dipendente> getDipendenti(){
        return dipendenteService.getDipendenti();
    }

    @GetMapping("/dipendenti/{id}")
    public Dipendente getdipendente(@PathVariable int id) throws NonTrovatoException {
        return dipendenteService.getDipendente(id);
    }

    @PutMapping("/dipendenti/{id}")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody DipendenteDto dipendenteDto) throws NonTrovatoException {
        return dipendenteService.updateDipendente(id, dipendenteDto);
    }

    @DeleteMapping("/dipendenti/{id}")
    public void deleteDipendente(@PathVariable int id) throws NonTrovatoException {
        dipendenteService.deleteDipendente(id);
    }

  @PatchMapping("/dipendenti/{id}")
    public String patchDipendente(@PathVariable int id, @RequestBody MultipartFile file) throws NonTrovatoException, IOException {
        return dipendenteService.patchDipendente(id, file);
    }


}
