package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTO.ClientDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

import static com.mindhub.homebanking.utils.AccountUtils.getNumberAccount;
import static com.mindhub.homebanking.utils.CardUtils.*;

@RestController//Escucha y responde peticiones con las restricciones de REST
public class ClientControllers {
    @Autowired //Genera una instacia del repositorio, a travez de la inyeccion de dependecias
    ClientService clientImplements;

    @Autowired
    private AccountService accountService;
    @Autowired
    private CardService cardService;

    //Este metodo devuelve un listado de clientes, el mapping indica la ruta y lo anato abajo.
    @GetMapping("/api/clients")//Peticion asociada a una ruta
    public List<ClientDTO> getClientList(){
        //traigo una listado de clientesDTO, el mapping indica la ruta
        return clientImplements.getAllClient().stream().map(ClientDTO::new).collect(Collectors.toList());
        //se deben agregar los metodos getters para poder acceder a las propiedades en las forma deseada,
    }

   @GetMapping("/api/clients/{id}") //Peticion asociada a una ruta
    public ClientDTO clientRepository(@PathVariable Long id){//toma la variable de ruta asignada

        return new ClientDTO(clientImplements.getClientById(id));
        //clientRep.findById(id).map(client -> new ClientDTO(client)).orElse(null) otra forma de acerlo
        // Me devuelve una opcional asi como puede que encuentre la ruta puede que no la encuentre.
        //error No encontraba el cliente DTO porque se estaba nombrando el cliente DTO de forma correcta
       //Solucion: se asigna el cliente DTO a la condicion del (map) con la variable condicional que
       // es equivalente a cada cliente DTO.

   }
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/clients")
    //
    public ResponseEntity<Object> register(

            @RequestParam String name, @RequestParam String lastName,

            @RequestParam String emailAddress, @RequestParam String password) {

        if (name.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        }
        if (clientImplements.findClientEmailAddress(emailAddress) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }
        //Creando la tarjeta.
        String cardHolder = name + " " + lastName;
        int aCvv = getCVV();
        String numberCard = getNumber();

        Card newCard = new Card(cardHolder,CardType.DEBITO,CardColor.SILVER,numberCard,aCvv,LocalDate.now().plusYears(5),LocalDate.now(),true);
        cardService.saveCard(newCard);
        //Creacion de la cuenta
        String number = getNumberAccount();
        boolean estado = true;

        Account newAccount = new Account(number, LocalDateTime.now(), 0.0,AccountType.AHORROS,estado);
        newAccount.setCard_id(newCard);
        accountService.saveAccount(newAccount);
        //Creacion del cliente.
        Client clientCreate = new Client(name, lastName, emailAddress, passwordEncoder.encode(password));
        clientCreate.addCard(newCard);
        clientCreate.addAccount(newAccount);

        clientImplements.saveClient(clientCreate);

        return new ResponseEntity<>(clientCreate,HttpStatus.CREATED);
    }



    @GetMapping("/api/clients/current")

    public ClientDTO getClientDTOAutorization(Authentication authentication) {

        return new ClientDTO( clientImplements.findClientEmailAddress(authentication.getName()));
    }

}