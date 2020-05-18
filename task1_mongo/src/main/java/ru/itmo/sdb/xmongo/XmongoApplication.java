package ru.itmo.sdb.xmongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmo.sdb.xmongo.entities.*;
import ru.itmo.sdb.xmongo.repositories.*;

@SpringBootApplication
public class XmongoApplication implements CommandLineRunner {

	@Autowired
	private Dorms dormsRepository;

	@Autowired
	private Penalties penaltiesRepository;

	@Autowired
    private Rooms roomsRepository;

	@Autowired
    private Tenants tenantsRepository;

	@Autowired
    private Visits visitsRepository;

	public static void main(String[] args) {
		SpringApplication.run(XmongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    for (int i = 0; i < 10000; ++i) {
            dormsRepository.save(EntityGenerator.generate(Dormitory.class));
            penaltiesRepository.save(EntityGenerator.generate(Penalty.class));
            roomsRepository.save(EntityGenerator.generate(Room.class));
            tenantsRepository.save(EntityGenerator.generate(Tenant.class));
            visitsRepository.save(EntityGenerator.generate(Visit.class));
        }
	}
}
