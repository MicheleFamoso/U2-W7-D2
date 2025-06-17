package it.epicode.U2_W7_D2.controller;


import it.epicode.U2_W7_D2.dto.PrenotazioneDto;
import it.epicode.U2_W7_D2.exception.NonTrovatoException;
import it.epicode.U2_W7_D2.model.Prenotazione;
import it.epicode.U2_W7_D2.service.PrenotazioneService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrenotazioneController {


    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/prenotazioni")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione saveprenotazione(@RequestBody @Validated PrenotazioneDto prenotazioneDto, BindingResult bindingResult) throws NonTrovatoException {
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).reduce("",(e, c)->e+c));
        }
        return prenotazioneService.savePrenotazione(prenotazioneDto);

    }


    @GetMapping("/prenotazioni")
    public List<Prenotazione> getPrenotazioni(){
        return prenotazioneService.getPrenotazioni();
    }

    @GetMapping("/prenotazioni/{id}")
    public Prenotazione getPrenotazione(@PathVariable int id) throws NonTrovatoException {
        return prenotazioneService.getPrenotazione(id);
    }


    @PutMapping("/prenotazioni/{id}")
    public Prenotazione updatePrenotazione(@PathVariable int id,
                                           @RequestBody @Validated PrenotazioneDto prenotazioneDto,
                                           BindingResult bindingResult) throws NonTrovatoException {
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).reduce("",(e, c)->e+c));
        }
        return prenotazioneService.updatePrenotazione(id, prenotazioneDto);


    }

    @DeleteMapping("/prenotazioni/{id}")
    public void deletePrenotazione(@PathVariable int id) throws NonTrovatoException {
        prenotazioneService.deletePrenotazione(id);
    }
}
