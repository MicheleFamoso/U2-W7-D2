package it.epicode.U2_W7_D2.controller;



import it.epicode.U2_W7_D2.dto.ViaggioDto;
import it.epicode.U2_W7_D2.exception.NonTrovatoException;
import it.epicode.U2_W7_D2.model.Viaggio;
import it.epicode.U2_W7_D2.service.ViaggioService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ViaggioController {


    @Autowired
    private ViaggioService viaggioService;

    @PostMapping("/viaggi")
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio saveViaggio(@RequestBody @Validated ViaggioDto viaggioDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).reduce("",(e, c)->e+c));
        }
        return viaggioService.saveViaggio(viaggioDto);

    }

    @GetMapping("/viaggi")
    public List<Viaggio> getViaggi(){
        return viaggioService.getViaggi();
    }

    @GetMapping("/viaggi/{id}")
    public Viaggio getViaggio(@PathVariable int id) throws NonTrovatoException {
        return viaggioService.getViaggio(id);
    }

    @PutMapping("/viaggi/{id}")
    public Viaggio updateViaggio(@PathVariable int id,@RequestBody ViaggioDto viaggioDto) throws NonTrovatoException {
        return viaggioService.updateViaggio(id, viaggioDto);
    }


    @DeleteMapping("viaggi/{id}")
    public void deleteViaggio(@PathVariable int id) throws NonTrovatoException {
        viaggioService.deleteViaggio(id);
    }

    @PatchMapping("/viaggi/{id}")
    public String patchViaggio(@PathVariable int id,@RequestBody String stato) throws NonTrovatoException {
        return viaggioService.patchViaggio(id, stato);
    }
}
