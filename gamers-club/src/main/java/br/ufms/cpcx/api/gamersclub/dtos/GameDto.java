package br.ufms.cpcx.api.gamersclub.dtos;

import br.ufms.cpcx.api.gamersclub.models.ConsoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class GameDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    private ConsoleEnum console;

    @NotBlank
    @Size(max = 100)
    private String owner;

    @NotBlank
    @Size(max = 15)
    private String ownerPhoneNumber;
}
