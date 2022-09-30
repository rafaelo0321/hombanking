package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
		System.out.println("Hola Rafael");
	}
	@Autowired
	PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData(ClientRepository client, AccountRepository account, TransactioRepository transaction, LoanRepository loan, ClientLoanRepository clientLoan,CardRepository card){
		return (args) -> {
			//Creando una cart
			CardColor color =CardColor.SILVER;
			CardColor colorDos =CardColor.GOLD;
			CardColor colorTres =CardColor.TITANIUM;
			CardType typeCard =CardType.DEBITO;
			LocalDate horaActua = LocalDate.now();
			//Se pasa el parametro indicando lo que se va a guardar en la tabla de la base de datos
			//en este caso cliente que es el argumento el tipo de operacion que vamos a realizar,
			//se crea un objeto en base al constructor, en este caso se le pasan tres parametros
			Client primerClient = new Client("Jack","Bronson","jack@goto.com",passwordEncoder.encode("lolita"));
			Client melba = new Client("Melba","Bronson","melba@mindhub.com",passwordEncoder.encode("melba"));
			Client segundoClient = new Client("Rafael","Niño","rafael@goto.com",passwordEncoder.encode("lolita"));
			Client admin = new Client("admin","Niño","rafael@admin.com",passwordEncoder.encode("rafael"));
			client.save(primerClient);
			client.save(segundoClient);
			client.save(melba);
			client.save(admin);


			//clientRepository.save(primerClient);
			//Creando cuentas y una variable para la hora actual
			LocalDateTime horaActual= LocalDateTime.now(); //Crea la hora actual en la que se crea la cuanta
			Account primeraAccount = new Account("VIN00000001",horaActual,5000.0,AccountType.AHORROS,true,primerClient);
			Account segundaAccount = new Account("VIN00000002",horaActual.plusDays(1),7500.0,AccountType.AHORROS,true,primerClient);
			Account terceraAccount = new Account("VIN00000003",horaActual,7400.0,AccountType.AHORROS,true,segundoClient);

			//Agregando cuanta a cada client
			account.save(primeraAccount);
			account.save(segundaAccount);
			account.save(terceraAccount);

			Card cardClienteUno = new Card((primerClient.getName()+" "+primerClient.getLastName()),typeCard,color,"1234 5421 2345 3243",123,horaActua.plusYears(5),horaActua,true,primeraAccount);
			Card cardClienteDos = new Card((primerClient.getName()+" "+primerClient.getLastName()),typeCard,color,"1234 8788 2345 7654",456,horaActua.plusYears(5),horaActua,true,segundaAccount);
			Card cardClienteTres = new Card((segundoClient.getName()+" "+segundoClient.getLastName()),typeCard,color,"1234 2321 7656 9876",789,horaActua.plusYears(5),horaActua,true,terceraAccount);
			card.save(cardClienteUno);
			card.save(cardClienteDos);
			card.save(cardClienteTres);




			//Desclaracion de las variables de tipo transaccion
			TransactionType type = TransactionType.CREDITO;
			TransactionType typeDos = TransactionType.DEBITO;

			//Creando transacciones a cada cuenta
			Transaction transactionUno = new Transaction(type,"de otra cuenta a esta cuenta",450.4,45.7,horaActual.plusDays(2),true,primeraAccount);

			transaction.save(transactionUno);
			Transaction transactionDos = new Transaction(typeDos,"de esta cuenta a otra cuenta",560.4,35.6,horaActual,true,primeraAccount);
			transactionDos.setShowTransaction(true);

			transaction.save(transactionDos);
			Transaction transactionTres = new Transaction(type,"de otra cuenta a esta cuenta",878.8,53.6,horaActual.plusDays(2),true,segundaAccount);
			transactionTres.setShowTransaction(true);
			transaction.save(transactionTres);
			Transaction transactionCuatro = new Transaction(typeDos,"de esta cuenta a otra cuenta",4580.4,53.6,horaActual,true,segundaAccount);
			transactionCuatro.setShowTransaction(true);
			transaction.save(transactionCuatro);

			//
			Transaction transactionCinco = new Transaction(typeDos,"de esta cuenta a otra cuenta",5172.4,243.5,horaActual,true,terceraAccount);
			transaction.save(transactionCinco);
			Transaction transactionSeis = new Transaction(type,"de otra cuenta a esta cuenta",1470.4,243.5,horaActual,true,terceraAccount);
			transaction.save(transactionSeis);
			List<Integer> mortgage = List.of(12,24,36,48,60);
			List<Integer> personnel = List.of(6,12,24);
			List<Integer> automotive = List.of(6,12,24,36);

			//Lista de prestamos.
			Loan Mortgage = new Loan("Mortgage",500000.0,mortgage,1.2);
			Loan Personnel = new Loan("Personnel",100000.0,personnel,1.1);
			Loan Automotive = new Loan("Automotive",300000.0,automotive,1.15);

			//Ingreso prestamos
			loan.save(Mortgage);
			loan.save(Personnel);
			loan.save(Automotive);

			//Solicitud de prestamo
			ClientLoan solicitudUno = new ClientLoan(400000.0,60,primerClient,Mortgage);
			ClientLoan solicitudDos = new ClientLoan(50000.0,12,primerClient,Personnel);
			ClientLoan solicitudTres = new ClientLoan(100000.0,24,segundoClient,Personnel);
			ClientLoan solicitudCuatro = new ClientLoan(200000.0,24,segundoClient,Automotive);
			clientLoan.save(solicitudUno);
			clientLoan.save(solicitudDos);
			clientLoan.save(solicitudTres);
			clientLoan.save(solicitudCuatro);
		};
	}
	}
	//