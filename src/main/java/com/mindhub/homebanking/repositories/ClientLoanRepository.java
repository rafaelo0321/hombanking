package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
//como estan los datos representado Json o XML
//Estate como va a ser el estado el de ahora
//Transfer como se van a transferir los datos HTTP que son los metodos GET, POST, Pasch, Delate y CRUP (Crear ver obtener actualizar y eliminar)
//Restriction a los metodos anteriores.
public interface ClientLoanRepository extends JpaRepository <ClientLoan, Long> {
    //Indica que es una estencion o hijo del JpaRepository y que es de la clase y tipo de dato de la llave primaria


}